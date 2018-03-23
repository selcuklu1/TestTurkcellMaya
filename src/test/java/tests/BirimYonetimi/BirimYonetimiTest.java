package tests.BirimYonetimi;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import common.ReusableSteps;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageData.UstMenuData;
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

import java.util.List;

/****************************************************
 * Tarih: 2018-02-15
 * Proje: Türksat Functional Test Automation
 * Class: "Birim Yönetimi" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
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

        login();

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

//TODO: Burada defect var. Seçilme durumu istenmiş ama seçilemiyor.
        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                //.geldigiBirimDoldur(birimAdi)
                //.evrakSayisiSolAlanKontrolu(idariBirimKimlikKodu) //TS2337_Birim_20180215132100
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
    @Test(enabled = true, description = "TS1109: Yeni birim kayıt ve evrak işlemlerinden kontrolü - Görünür")
    public void TS1109() {

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

        login();

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

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .secilenOnayAkisiSil()
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
    @Test(enabled = true, description = "TS1116: Tanımlı kullanıcısı olan birimin pasif yapılması")
    public void TS1116() throws InterruptedException {

     //   String birimAdı="TS1116-2017";

        login("alkanseker","123");

        testStatus("TS1116", "Birim Oluşturma");
        //1109 senaryosu yerine pre. con. koşuluyor
        List<String> birim = new ReusableSteps().yeniBirimKayit();

        String birimAdi = birim.get(0);
        //String birimKisaAdi = birim.get(1);
        //String idariBirimKimlikKodu = birim.get(2);
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
                .cmbBirimAmiriAtamaBagTipiSec("Amir")
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
                .yeniRolIliskilendirmeKaydet();
                logout();

     /*   evrakOlusturPage
        .evrakOlusturSayfaKapat()
        .islemPenceresiKapatmaOnayiPopup("Kapat");*/


     login("alkanseker","123");
                birimYonetimiPage
                .openPage()
                .birimYonetimiFiltrelemeAlanKontrolleri()
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimTuruSec("İç Birim")
                .durumSec("Sadece Aktifler")
                .ara()
                .birimPasifYap(birimAdi)
                .popupIslemOnayiAciklamaDoldur("Ts için yazılmıştır")
                .popupIslemOnayiEvet();

      Thread.sleep(500);

        birimYonetimiPage
                .popupIslemOnayiHayir()
                .birimdekikullanıcılarbutonunatıkla()
                ;

        kullaniciYonetimiPage
                .kullaniciListesiGuncelle2()
                .gorevliOlduguBirimSil(birimAdi)
                .rolSil(birimAdi)
                .kaydet()
        ;
        birimYonetimiPage
                .openPage()
                .birimPasifYap(birimAdi)
                .aciklamaDoldur("TS1116 işlemleri ")
                .popupIslemOnayiEvet();


    }


}
