package tests.BirimYonetimi;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import common.ReusableSteps;
import data.TestData;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.switchTo;

/******************
 * Tarih: 2018-02-15
 * Proje: Türksat Functional Test Automation
 * Class: "Birim Yönetimi" konulu senaryoları içerir
 * Yazan: Sezai Çelik
 ******************/

@Feature("Birim Yönetimi")
public class BirimYonetimiTest extends BaseTest {

    BirimYonetimiPage birimYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;
    EvrakOlusturPage evrakOlusturPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;
    MainPage mainPage;
    GelenEvraklarPage gelenEvraklarPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
    BirimlerdekiKisilerRaporuPage birimlerdekiKisilerRaporuPage;

    @BeforeMethod
    public void loginBeforeTests() {

        birimYonetimiPage = new BirimYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        evrakOlusturPage = new EvrakOlusturPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        mainPage = new MainPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
        birimlerdekiKisilerRaporuPage = new BirimlerdekiKisilerRaporuPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2337: Yeni birim kayıt ve evrak işlemlerinden kontrolü - Görünür Seçilmez")
    public void TS2337() {

        String testID = "TS2337";
        String sistemTarihi = getSysDate();
        String birimAdi = "TS2337_Birim_" + sistemTarihi;
        String birimKisaAdi = "ts2337b_" + sistemTarihi;
        String idariBirimKimlikKodu = sistemTarihi;
        String birim = "Optiim Birim";
        String birimDetail = "YGD";
        String birimTipi = "Genel Müdürlüğü";
        String gelenEvrakNumaratoru = "Türksat AŞ_numarator - Gelen Evrak";
        String gidenEvrakNumaratoru = "Türksat AŞ_numarator - Giden Evrak";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.optiim);

        testStatus(testID, "Birim Oluşturma");

        birimYonetimiPage
                .openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()
                .gorunurlukTipiSec("Görünür Seçilmez")
//                .disBirimSec(true)
                .adDoldur(birimAdi)
                .kisaAdiDoldur(birimKisaAdi)
                .antetTipiSec("Normal")
                .antetBilgisiDoldur(birimAdi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustBirimSec(birim, birimDetail)
                .birimTipiSec(birimTipi)
                .gelenEvraklariNumaratoruDoldur(gelenEvrakNumaratoru)
                .gidenEvraklariNumaratoruDoldur(gidenEvrakNumaratoru)
                .birimBagTuruSec("Bağlı Kuruluş")
                .postaBirimiSec(birim, birimDetail)
                .kepPostaBirimiSec(birim, birimDetail)
                .postaSekliSec("Otomatik")
                .belgenetKullanıyormuSec("Evet")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimKayitKontrolu(birimAdi);

//TODO: Burası değiştirildi excelde
        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                .geregiAlanindaBiriminGeldigiVeSecilemedigiKontrolu(birimAdi, "Birim Adı"); //TS2337_Birim_20180215155258

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiVeSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiVeSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TS2336: Birimin olur metnini güncelleme")
    public void TS2336() {

        login();

        String birimAdi = "TS2336 Birim";

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1108: Birim Sorgulama")
    public void TS1108() {

        String aktifIcBirimAdi = "TS1108 Aktif İç Birim";
        String pasifIcBirimAdi = "TS1108 Pasif İç Birim";
        String pasifDisBirimAdi = "TS1108 Pasif Dış Birim";

        login();

        birimYonetimiPage
                .openPage()

                .birimTuruSec("İç Birim")
                .durumSec("Sadece Aktifler")
                .ara()
                .pasifYapButonuKontrolu()
                .aktiflerIlkBirimGuncelle()
                .disBirimChkBoxBosOlduguKontrolu()

                .birimTuruSec("İç Birim")
                .durumSec("Sadece Pasifler")
                .ara()
                .aktifYapButonuKontrolu()
                .pasiflerIlkBirimGuncelle()
                .disBirimChkBoxBosOlduguKontrolu()

                .birimTuruSec("Dış Birim")
                .filtreSorgulamaPaneliAc()
                .durumSec("Sadece Aktifler")
                .ara()
                .pasifYapButonuKontrolu()
                .aktiflerIlkBirimGuncelle()
                .disBirimChkBoxDoluOlduguKontrolu()

                .birimTuruSec("Dış Birim")
                .durumSec("Sadece Pasifler")
                .ara()
                .aktifYapButonuKontrolu()
                .pasiflerIlkBirimGuncelle()
                .disBirimChkBoxDoluOlduguKontrolu();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1109b: Yeni birim kayıt ve evrak işlemlerinden kontrolü - Görünür")
    public void TS1109b() {

        String testID = "TS1109";
        String sistemTarihi = getSysDate();
        String birimAdi = "TS1109_Birim_" + sistemTarihi;
        String birimKisaAdi = "ts1109b_" + sistemTarihi;
        String idariBirimKimlikKodu = sistemTarihi;
        String birim = "Optiim Birim";
        String birimDetail = "YGD";
        String birimTipi = "Genel Müdürlüğü";
        String gelenEvrakNumaratoru = "Türksat AŞ_numarator - Gelen Evrak";
        String gidenEvrakNumaratoru = "Türksat AŞ_numarator - Giden Evrak";
        String basariMesaji = "İşlem başarılıdır!";

        login();

        testStatus(testID, "Birim Oluşturma");

        birimYonetimiPage
                .openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()
                .gorunurlukTipiSec("Görünür")
                .adDoldur(birimAdi)
                .kisaAdiDoldur(birimKisaAdi)
                .antetTipiSec("Normal")
                .antetBilgisiDoldur(birimAdi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustBirimSec(birim, birimDetail)
                .birimTipiSec(birimTipi)
                .gelenEvraklariNumaratoruDoldur(gelenEvrakNumaratoru)
                .gidenEvraklariNumaratoruDoldur(gidenEvrakNumaratoru)
                .birimBagTuruSec("Bağlı Kuruluş")
                .postaBirimiSec(birim, birimDetail)
                .kepPostaBirimiSec(birim, birimDetail)
                .postaSekliSec("Otomatik")
                .belgenetKullanıyormuSec("Evet")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimKayitKontrolu(birimAdi);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı");

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")
                .geregiTemizle()

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")
                .geregiTemizle()

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")
                .geregiTemizle()

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1461: Birime kullanıcı ataması ve sisteme girişi")
    public void TS1461() {

        User user1 = new User("scelik", "123", "Sezai ÇELİK", "Optiim Birim");

        login(user1);

        testStatus("TS1109", "Birim Oluşturma");
        //1109 senaryosu yerine pre. con. koşuluyor
        List<String> birim = new ReusableSteps().yeniBirimKayit();

        String birimAdi = birim.get(0);
        String birimKisaAdi = birim.get(1);
        String idariBirimKimlikKodu = birim.get(2);
        String basariMesaji = "İşlem başarılıdır!";

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("scelik")
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerEkle()
                .kullaniciBirimAtamaBirimDoldur(birimAdi)
                .kullaniciBirimAtamaGorevDoldur("Uzman Test Mühendisi")
                .kullaniciBirimAtamaKaydet()
                .rolListeriEkle()
                .yeniRolIliskilendirmeKullaniciBirimDoldur(birimKisaAdi)
                .yeniRolIliskilendirmeKullaniciRolSec("STANDART KULLANICI YETKİSİ")
                .yeniRolIliskilendirmeKaydet()
                .kullaniciGuncelleKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        clearCookies();

        login(TestData.usernameSCELIK, TestData.passwordSCELIK);

        mainPage
                .birimSec(Condition.text(birimAdi))
                .evrakIslemleriIslemYaptiklarimMenuKontrol()

                .birimSec(Condition.text("Optiim Birim"));


        testStatus("TS1461", "Data Resetleme: Rol Silme ve Birimi pasife alma");

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("scelik")
                .ara()
                .kullaniciListesiGuncelle()
                .rolSil(birimAdi)
                .gorevliOlduguBirimSil(birimAdi)

                .kaydet();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1122: İşlem yapılan birimin silinememesi")
    public void TS1122() {

        String birim = "TS1122_Birim";
        String soru = "Birimin ve bağlı alt birimlerin durumunu değiştirmek istediğinize emin misiniz?";
        String aciklama = "TS2112_Birim pasif yapma";
        String dikkatMesaji1 = "Birimde tanımlı kullanıcılar bulunmaktadır. Lütfen kullanıcıları başka birime taşıyınız ya da siliniz.";

        login();

        birimYonetimiPage
                .openPage()
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birim)
                .ara()
                .birimKayitKontrolu(birim)

                .birimTuruSec("İç Birim")
                .durumSec("Sadece Aktifler")
                .ara()
                .birimKayitKontrolu(birim)
                .birimPasifYap(birim)

                .islemOnayiPopupSorusu(soru)
                .popupIslemOnayiAciklamaDoldur(aciklama)
                .popupIslemOnayiEvet()
                .islemMesaji().dikkatOlmali(dikkatMesaji1);

        birimYonetimiPage
                .popupIslemOnayiHayir();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1112: Kaydedilen birime amir eklenmesi")
    public void TS1112() {

        login(TestData.optiim);

        testStatus("TS1109", "Birim Oluşturma");
        //1109 senaryosu yerine pre. con. koşuluyor
        List<String> birim = new ReusableSteps().yeniBirimKayit();

        String birimAdi = birim.get(0);
        //String birimKisaAdi = birim.get(1);
        //String idariBirimKimlikKodu = birim.get(2);

        String amirAdi = "Sezai ÇELİK";
        String gorev = "Uzman Test Mühendisi";
        String basariMesaji = "İşlem başarılıdır!";

        birimYonetimiPage
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimGüncelle(birimAdi)

                .birimAmiriEkle()
                .txtBirimAmiriAtamaKullaniciDoldur(amirAdi)
                .txtBirimAmiriAtamaGorevDoldur(gorev)
                .birimAmiriAtamaBaslangicBitisTarihiKontrol()
                .cmbBirimAmiriAtamaBagTipiSec("Amir")
                .cmbBirimAmiriAtamaGizlilikDerecesiSec("Çok Gizli")
                .birimAmiriAtamaKaydet()
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.userSezaiCelik);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .secilenOnayAkisiSil()
                .otomatikOnayAkisiSec()
                .otomatikOnayAkisiAmirKontrolu(amirAdi)
                .otomatikOnayAkisiKapat()
                .onayAkisiEkle()
                .onayAkisiKullaniciEkle(amirAdi, birimAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1110: Alta yeni ekle fonksiyonu ile yeni birim kaydetme")
    public void TS1110() {

        String ustBirim = "TS1110 Ust Birim";

        String sistemTarihi = getSysDate();
        String altBirim = "TS1110_Alt_Birim_" + sistemTarihi;
        String altBirimKisaAdi = "ts1110altb_" + sistemTarihi;
        String idariBirimKimlikKodu = sistemTarihi;
        String birimDetail = "Optiim Birim";
        String birimTipi = "Genel Müdürlüğü";
        String gelenEvrakNumaratoru = "Türksat AŞ_numarator - Gelen Evrak";
        String gidenEvrakNumaratoru = "Türksat AŞ_numarator - Giden Evrak";
        String basariMesaji = "İşlem başarılıdır!";
        String kepAdresi = "turksat@testkep.pttkep.gov.tr";
        String kepHizmetSaglayici = "PTT KEP Servisi";
        String amirAdi = "Sezai ÇELİK";
        String gorev = "Uzman Test Mühendisi";

        login();

        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(ustBirim)
                .ara()
                .birimKayitKontrolu(ustBirim)
                .yeniAltBirimEkle()
                .birimSeciliGeldigiGorme(ustBirim)

                .gorunurlukTipiSec("Görünür")
                .adDoldur(altBirim)
                .kisaAdiDoldur(altBirimKisaAdi)
                .antetTipiSec("Normal")
                .antetBilgisiDoldur(altBirim)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                //.ustBirimSec(birim, birimDetail)
                .birimTipiSec(birimTipi)
                .gelenEvraklariNumaratoruDoldur(gelenEvrakNumaratoru)
                .gidenEvraklariNumaratoruDoldur(gidenEvrakNumaratoru)
                .birimBagTuruSec("Bağlı Kuruluş")
                .postaBirimiSec(ustBirim, birimDetail)
                .kepPostaBirimiSec(ustBirim, birimDetail)
                .postaSekliSec("Otomatik")
                .belgenetKullanıyormuSec("Evet")

                .birimAmiriEkle()
                .txtBirimAmiriAtamaKullaniciDoldur(amirAdi)
                .txtBirimAmiriAtamaGorevDoldur(gorev)
                .birimAmiriAtamaBaslangicBitisTarihiKontrol()
                .cmbBirimAmiriAtamaBagTipiSec("Amir")
                .cmbBirimAmiriAtamaGizlilikDerecesiSec("Çok Gizli")
                .birimAmiriAtamaKaydet()

                .yeniKepAdresBilgileriEkle()
                .popupKepAdresiDoldur(kepAdresi)
                .popupKepHizmetSaglayicisiSec(kepHizmetSaglayici)
                .popupKepAdresBilgileriKaydet()

                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(ustBirim)
                .ara()
                .birimKayitKontrolu(ustBirim)

                .altBirimleriAcma()
                .birimKayitKontrolu(altBirim);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1499: Kep adres bilgileri alanından 2 tane kep hesabı tanımlaması")
    public void TS1499() {

        String birimAdi = "TS1499 Birim";
        String kepAdresi1 = "turksat@testkep.pttkep.gov.tr";
        String kepAdresi2 = "turksat.2@testkep.pttkep.gov.tr";
        String kepHizmetSaglayici = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";

        login();

        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimKayitKontrolu(birimAdi)
                .aktiflerIlkBirimGuncelle()
                .kepAdresiKullaniyorSec(true)

                .kepAdresBilgileriDataResetleme(kepAdresi1, kepAdresi2)
                .yeniKepAdresBilgileriEkle()
                .popupKepAdresiDoldur(kepAdresi1)
                .popupKepHizmetSaglayicisiSec(kepHizmetSaglayici)
                .popupKepAdresBilgileriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .yeniKepAdresBilgileriEkle()
                .popupKepAdresiDoldur(kepAdresi2)
                .popupKepHizmetSaglayicisiSec(kepHizmetSaglayici)
                .popupKepAdresBilgileriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .kepAdresiVarsayilanYap(kepAdresi2)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .aktiflerIlkBirimGuncelle()
                .kepAdresBilgileriKontrolu(kepAdresi1)
                .kepAdresBilgileriKontrolu(kepAdresi2)

                .kepAdresBilgileriDataResetleme(kepAdresi1, kepAdresi2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1123: Pasif yapılan birimin aktif edilmesi ve evrak işlemleriden kontrolü")
    public void TS1123() throws InterruptedException {

        String birimAdi = "TS1123 Birim";
        String soru = "Birimin ve bağlı alt birimlerin durumunu değiştirmek istediğinize emin misiniz?";
        String aciklama = "TS2113 Birim aktif yapma";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimYonetimiPage
                .openPage()
                .dataResetlemeBirimAktifIsePasifYap(birimAdi)
                .birimFiltreDoldur(birimAdi)
                .durumSec("Sadece Pasifler")
                .ara()
                .pasifBirimKayitKontrolu(birimAdi)
                .birimAktifYap(birimAdi)
                .islemOnayiPopupSorusu(soru)
                .popupIslemOnayiAciklamaDoldur(aciklama)
                .popupIslemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(birimAdi)
                .durumSec("Sadece Aktifler")
                .ara()
                .birimKayitKontrolu(birimAdi)

                .durumSec("Sadece Pasifler")
                .ara()
                .aktifYapilanKaydinGelmedigiKontrolu();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Birim")
                .geregiDoldur(birimAdi, "Birim")
                .secilenGeregiSil()
                .bilgiSecimTipiSec("Birim")
                .bilgiDoldur(birimAdi, "Birim");

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                .geldigiBirimDoldur(birimAdi);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Birim")
                .geregiDoldur(birimAdi, "Birim")
                .secilenGeregiSil()
                .bilgiSecimTipiSecByText("Birim")
                .bilgiDoldur(birimAdi, "Birim");

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleDoldur(birimAdi);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeHavaleEt()
                .birimeHavaleDoldur(birimAdi);

        teslimAlinanlarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleDoldur(birimAdi);

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleDoldur(birimAdi);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleDoldur(birimAdi);

        birimYonetimiPage
                .openPage()
                .dataResetlemeBirimAktifIsePasifYap(birimAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1114: Birimin pasif yapılması ve evrak işlemlerinden kontrolü")
    public void TS1114() throws InterruptedException {

        String birimAdi = "TS1114 Birim";
        String soru = "Birimin ve bağlı alt birimlerin durumunu değiştirmek istediğinize emin misiniz?";
        String aciklama = "TS1114 Birim pasif yapma";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimYonetimiPage
                .openPage()
                .dataResetlemeBirimPasifIseAktifYap(birimAdi)
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimKayitKontrolu(birimAdi)

                .birimPasifYap(birimAdi)
                .islemOnayiPopupSorusu(soru)
                .popupIslemOnayiAciklamaDoldur(aciklama)
                .popupIslemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(birimAdi)
                .durumSec("Sadece Pasifler")
                .ara()
                .pasifBirimKayitKontrolu(birimAdi)

                .durumSec("Sadece Aktifler")
                .ara()
                .pasifKaydinAktifteGelmedigiKontrolu(birimAdi);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Birim")
                .geregiAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim")

                .bilgiSecimTipiSec("Birim")
                .bilgiAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                .geldigiAlanindaBirimGoruntulenmemeKontrolu(birimAdi, "Birim");

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeHavaleEt()
                .birimeHavaleAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        teslimAlinanlarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .birimeHavaleAlanindaGoruntulenmemeKontrolu(birimAdi, "Birim");

        birimYonetimiPage
                .openPage()
                .dataResetlemeBirimPasifIseAktifYap(birimAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1119b: Birim iletişim bilgilerinin değiştirilmesi")
    public void TS1119b() {

        login("optiimotomasyon1","123");

        String birim = "TS1119";
        String iletisim1 = "Ankara Üniversitesi Ankütek Teknopark E Blok Kat:1";
        String iletisim2 = "Tel: 0312 222 22 22";
        String iletisim3 = "Web: www.turksat.com.tr";
        String basariMesaji = "İşlem başarılıdır!";
        String mobilTel = "0212 536 12 52";
        String telNo = " 0568 255 12 45";
        String isNo = " 0212 555 55 25";
        String faksNo1 = " 0212 585 58 96";
        String faksNo2 = "0212 587 12 21";
        String adres = "Ankara Üniversitesi Ankütek Teknopark E Blok Kat:1";
        String il = "ankara";
        String ilce = "gölbaşı";
        String eposta = "turksat@turksat.com";
        String webAdresi = "www.turksat.com.tr";
        String eskiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";


        birimYonetimiPage
                .openPage()
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birim)
                .ara()
                .birimKayitKontrolu(birim)
                .iletisimBilgisi(birim)
                .birimYonetimiİletisimBilgisiAlanKontrolleri()
                .guncelle()
                .iletisimPopupGeldigiGorme()
                .iletisimBilgisiSatır1Doldur(iletisim1)
                .iletisimBilgisiSatır2Doldur(iletisim2)
                .iletisimBilgisiSatır3Doldur(iletisim3)
                .iletisimKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .iletisimGuncelle()
                .yeniİletisimPopupGeldigiGorme()
                .mobilTel(mobilTel)
                .telefonNo(telNo)
                .isTelefonNo(isNo)
                .faksNo1(faksNo1)
                .faksNo2(faksNo2)
                .adres(adres)
                .ilDoldur(il)
                .ilceDoldur(ilce)
                .eposta(eposta)
                .webAdresi(webAdresi)
                .iletisimBilgileriKaydet()
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        mainPage
                .birimSec(Condition.text(birim))
                .currentBirimKontrol(Condition.text(birim));

        evrakOlusturPage
                .openPage()
                .editorTabAc();

        evrakOlusturPage
                .editorTabKontrol();

        evrakOlusturPage
                .iletisimBilgileriGeldigiGorme(iletisim1, iletisim2, iletisim3)
                .pdfGoster();
        switchTo().window(1);

        evrakOlusturPage
                .pdfİletisimBilgileriGeldigiGorme(iletisim1, iletisim2, iletisim3);

        closeNewWindow();
        switchTo().window(0);
        mainPage
                .birimSec(Condition.text(eskiBirim))
                .currentBirimKontrol(Condition.text(eskiBirim));

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1459by: Birim bilgisi güncelleme ")
    public void TS1459by() {

        login(TestData.optiim);

        String geregiTipi = "Birim";
        String eskiBirimAdi = "Ts1459 Birim";
        String yeniIdariBirimKimlikKodu = createRandomNumber(10);
        String basariMesaji = "İşlem başarılıdır!";
        String detail = "Optiim Birim";
        String yeniBirimAdi = "Ts1459 Birim" + " " + getSysDate();
        String optiimBirimIdariKimlikKodu = "234234234234222221";

        birimYonetimiPage
                .openPage()
                .birimFiltreDoldurWithDetail(eskiBirimAdi, detail)
                .ara()
                .birimKayitKontrolu(eskiBirimAdi)
                .birimGüncelle(eskiBirimAdi)
                .birimAdGuncelle(yeniBirimAdi)
                .birimIdariKoduGuncelle(yeniIdariBirimKimlikKodu)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimYonetimiPageKontrol1();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiGeldigiGorme(yeniBirimAdi)
                .bilgiGeldigiGorme(yeniBirimAdi);

        evrakOlusturPage
                .editorTabAc()
                .sayiAlanindaIdariBirimKimlikKoduKontrolu(optiimBirimIdariKimlikKodu);

        mainPage
                .evrakOlusturSayfayiKapat();

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecimi(geregiTipi)
                .geldigiBirimDoldur(yeniBirimAdi)
                .geldigiBirimGoruntulenmeKontrolu(yeniBirimAdi)
                .solEvrakKontrol(yeniIdariBirimKimlikKodu);

        mainPage
                .evrakOlusturSayfayiKapat();

        gidenEvrakKayitPage
                .openPage()
                .geregiGeldigiGorme(yeniBirimAdi)
                .bilgiGeldigiGorme(yeniBirimAdi);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgiGeldigiGorme(yeniBirimAdi)
                .geregiGeldigiGorme(yeniBirimAdi);

        olurYazisiOlusturPage
                .editorTabAc()
                .sayiAlanindaIdariBirimKimlikKoduKontrolu(optiimBirimIdariKimlikKodu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1116: Tanımlı kullanıcısı olan birimin pasif yapılması")
    public void TS1116() throws InterruptedException {

        login("alkanseker", "123");

        testStatus("TS1116", "Birim Oluşturma");
        //1109 senaryosu yerine pre. con. koşuluyor
        List<String> birim = new ReusableSteps().yeniBirimKayit();

        String birimAdi = birim.get(0);
        String amirAdi = "Alkan Ako SEKER";
        String gorev = "Genel Müdür";
        String basariMesaji = "İşlem başarılıdır!";


        birimYonetimiPage
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimGüncelle(birimAdi)
                .birimAmiriEkle()
                .txtBirimAmiriAtamaKullaniciDoldur(amirAdi)
                .txtBirimAmiriAtamaGorevDoldur(gorev)
                .birimAmiriAtamaBaslangicBitisTarihiKontrol()
                .cmbBirimAmiriAtamaBagTipiSec("Vekaleten Amir Yardımcısı")
                .cmbBirimAmiriAtamaGizlilikDerecesiSec("Çok Gizli")
                .birimAmiriAtamaKaydet()
                .kaydet();

        kullaniciYonetimiPage
                .openPage()
                .TCKimlikNoDoldur("20987987455")
                .ara()
                .kullaniciListesiGuncelle()
                .rolListeriEkle()
                .yeniRolIliskilendirmeKullaniciBirimDoldur(birimAdi)
                .yeniRolIliskilendirmeKullaniciRolSec("ENTERPRİSE")
                .yeniRolIliskilendirmeKaydet()
                .kaydet();


        testStatus("TS1116 ", "Test Başlamıştır");
        login("alkanseker", "123");

        birimYonetimiPage
                .openPage()
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .biriminListelendigiKontrolu()
                .birimTuruSec("İç Birim")
                .durumSec("Sadece Aktifler")
                .ara()
                .biriminListelendigiKontrolu()
                .birimPasifYap(birimAdi)
                .biriminOnaydialoguKontrolu()
                .popupIslemOnayiAciklamaDoldur("Ts için yazılmıştır")
                .popupIslemOnayiEvet()
                .islemMesaji().isDikkat("Birimde tanımlı kullanıcılar bulunmaktadır. Lütfen kullanıcıları başka birime taşıyınız ya da siliniz.");

        Thread.sleep(500);

        birimYonetimiPage

                .popupIslemOnayiHayir()
                .birimdekikullanıcılarbutonunatıkla();
        Thread.sleep(1000);

        kullaniciYonetimiPage
                .kullaniciYönetimiekranıKontrol()
                .birimKullanicikontrol()
                .kullaniciListesiGeldigiGorme()
                .kullaniciListesiGuncelle2()
                .rolSil(birimAdi)
                .gorevListesiSonSayfaTikla()
                .gorevliOlduguBirimSil(birimAdi)
                .kaydet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        birimYonetimiPage
                .openPage()
                .birimPasifYap(birimAdi)
                .biriminOnaydialoguKontrolu()
                .islemOnayiAciklamaDoldur("TS1116 işlemleri ")
                .popupIslemOnayiEvet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1959: Birimlerdeki Kişiler Raporunun birim yönetiminden kontrolü")
    public void TS1959() {

        String defaultBirim = "Optiim Birim";
        String birimAdi = "TS1959_Birim";
        String kullanici1 = "Ts1959 USER1";
        String kullanici2 = "Ts1959 USER2";

        login();

        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimdekiKullanicilarTikla();

        kullaniciYonetimiPage
                .birimKontrol(birimAdi)
                .altBirimlerDahilSec(true)
                .ara()
                .kullaniciListesiGeldigiGorme();

        String kullaniciSayisi1 = kullaniciYonetimiPage.kullaniciSayisiniAl();

        birimlerdekiKisilerRaporuPage
                .openPage()
                .birimKontrol(defaultBirim)
                .birimDoldur(birimAdi)
                .altBirimlerDahilSec(true)
                .durumSec("Sadece Aktifler")
                .sorgula()
                .kullaniciListesiGeldigiGorme();

        String kullaniciSayisi2 = birimlerdekiKisilerRaporuPage.kullaniciSayisiniAl();

        birimlerdekiKisilerRaporuPage
                .kullaniciSayilarininAyniOldugunuGorme(kullaniciSayisi1, kullaniciSayisi2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2334: Birim tanımlama ekranı alan kontrolleri")
    public void TS2334() {

        String birimAdi = "TS2334 Birim";
        String atentBilgisi = "TS2334 Birim Atenti";
        String idariBirimKimlikKodu = "22233112234065";
        String postaBirimi = "Optiim Birim";
        String postaBirimDetail = "YGD";
        String uyariMesaji = "Zorunlu alanları doldurunuz";

        login(TestData.optiim);

        birimYonetimiPage
                .openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()

                .gorunurlukTipiSec("Görünür")
                .adDoldur(birimAdi)
                .antetBilgisiDoldur(atentBilgisi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .postaBirimiSec(postaBirimi, postaBirimDetail)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        birimYonetimiPage
                .ekle()
                .gorunurlukTipiSec("Görünür")
                .antetBilgisiDoldur(atentBilgisi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .postaBirimiSec(postaBirimi, postaBirimDetail)
                .kepPostaBirimiSec(postaBirimi, postaBirimDetail)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        birimYonetimiPage
                .ekle()
                .gorunurlukTipiSec("Görünür")
                .adDoldur(birimAdi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .postaBirimiSec(postaBirimi, postaBirimDetail)
                .kepPostaBirimiSec(postaBirimi, postaBirimDetail)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        birimYonetimiPage
                .ekle()
                .gorunurlukTipiSec("Görünür")
                .adDoldur(birimAdi)
                .postaBirimiSec(postaBirimi, postaBirimDetail)
                .kepPostaBirimiSec(postaBirimi, postaBirimDetail)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2335: Hızlı birim tanımlama")
    public void TS2335() {

    }
}