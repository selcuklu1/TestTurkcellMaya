package tests.BirimYonetimi;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import common.ReusableSteps;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
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

    @BeforeMethod
    public void loginBeforeTests() {

        birimYonetimiPage = new BirimYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        evrakOlusturPage = new EvrakOlusturPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        mainPage = new MainPage();
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
    @Test(enabled = true, description = "TS2336: Birimin olur metnini güncelleme")
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

        String birimAdı = birim.get(0);
        String birimKisaAdi = birim.get(1);
        String idariBirimKimlikKodu = birim.get(2);

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("scelik")
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerEkle()
                .kullaniciBirimAtamaBirimDoldur(birimAdı)
                .kullaniciBirimAtamaGorevDoldur("Uzman Test Mühendisi")
                .kullaniciBirimAtamaKaydet()
                .rolListeriEkle()
                .yeniRolIliskilendirmeKullaniciBirimDoldur(birimKisaAdi)
                .yeniRolIliskilendirmeKullaniciRolSec("STANDART KULLANICI YETKİSİ")
                .yeniRolIliskilendirmeKaydet()
                .kullaniciGuncelleKaydet();

        clearCookies();

        login(TestData.usernameSCELIK, TestData.passwordSCELIK);

        mainPage
                .birimSec(Condition.text(birimAdı))
                .evrakIslemleriIslemYaptiklarimMenuKontrol();

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
}
