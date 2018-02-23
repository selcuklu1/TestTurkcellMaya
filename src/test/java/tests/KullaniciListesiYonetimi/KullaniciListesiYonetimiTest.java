package tests.KullaniciListesiYonetimi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.newPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciListesiYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KullaniciListesiYonetimiTest extends BaseTest {

    KullaniciListesiYonetimiPage kullaniciListesiYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    pages.altMenuPages.EvrakDetayiPage evrakDetayiPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;

    String ad = "TS1005 " + createRandomNumber(6);
    String aciklama = "TS1005 " + getSysDate();
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String kullanici1 = "Username22n TEST";
    String kullanici2 = "Username21g TEST";
    String dikkatMesaji = "Kullanıcı grubunda en az 2 kullanıcı olmak zorundadır!";
    String basariMesaji = "İşlem başarılıdır!";
    String durumPasifler = "Sadece Pasifler";
    String durumAktifler = "Sadece Aktifler";
    String guncelAd = ad + " Guncellendi";


    String konuKodu = "Diğer";
    String konu = "TS1466 " + createRandomNumber(9);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String kullaniciAdi = "Mehmet Bozdemir";

    @BeforeMethod
    public void loginBeforeTests() {

        kullaniciListesiYonetimiPage = new KullaniciListesiYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new pages.altMenuPages.EvrakDetayiPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        teslimAlinanlarPage =new TeslimAlinanlarPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1005 : Yeni Bir Kullanıcı Listesi Tanımlama")
    public void TS1005() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        kullaniciListesiYonetimiPage
                .openPage()
                .ekranAlanKontrolu()
                .yeniEkle()
                .kullaniciListesiEklemeAlanKontrolleri()
                .adDoldur(ad)
                .aciklamaDoldur(aciklama)
                .birimSec(birim)
                .kullanicilarSec(kullanici1)
                .kaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        kullaniciListesiYonetimiPage
                .kullanicilarSec(kullanici2)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1005"}
            , description = "TS1001 : Kullanıcı Listesinin Pasif Duruma Getirilmesi")
    public void TS1001() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        kullaniciListesiYonetimiPage
                .openPage()
                .sorgulaVeFiltreleAdDoldur(ad)
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, true)
                .pasifYap(ad)
                .islemOnayiPopUpEvetHayır("Evet")
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumPasifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, true)
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1001"}
            , description = "TS1003 : Kullanıcı Listesinin Aktif Duruma Getirilmesi")
    public void TS1003() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String btnEvet = "Evet";

//        String ad = "TS1005 142310";
        kullaniciListesiYonetimiPage
                .openPage()
                .sorgulaVeFiltreleAdDoldur(ad)
                .durumSec(durumPasifler)
                .ara()
                .aktifYap(ad)
                .islemOnayiPopUpEvetHayır(btnEvet)
                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciListesiYonetimiPage
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false)
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, true)
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumPasifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1003"}
            , description = "TS1000 : Kullanıcı Listesi Güncelleme")
    public void TS1000() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String guncelAd = ad + " Guncellendi";

        kullaniciListesiYonetimiPage
                .openPage()
                .ekranAlanKontrolu()
                .durumKontrolu(durumAktifler)
                .ara()
                .tumTabloButonKontrolu()
                .sorgulaVeFiltreleTabAc()
                .sorgulaVeFiltreleAdDoldur(ad)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, true)
                .guncelle(ad)
                .adTemizle()
                .adDoldur(guncelAd)
                .aciklamaTemizle()
                .aciklamaDoldur(aciklama + "Guncellendi")
                .birimSec("Optiim")
                .kullanicilarSec("Username20n TEST")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciListesiYonetimiPage
                .sorgulaVeFiltreleTabAc()
                .sorgulaVeFiltreleAdTemizle()
                .sorgulaVeFiltreleAdDoldur(guncelAd)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(guncelAd, true);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1466 : Kullanıcı Listesi Güncelleme Sonrası Ekranlardan Kontrolü")
    public void TS1466() throws InterruptedException {
        String ad = "TS1005 121034";
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
        String guncelAd = "TS1005 121034 GUNCELLENDİ";

        gelenEvrakKayit();

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .havaleYapKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenEvrak();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreIcerikGoster(konu);
//                .btnTikla("Teslim Al ve Havale Et")
//                .teslimAlVeHavaleEtKullaniciListesiDoldur(guncelAd);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .havaleYapKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al")
                .evrakTeslimAlPopUpEvet();

//        teslimAlinmayiBekleyenlerPage
//                .openPage()
//                .konuyaGoreIcerikGoster(konu)
//                .btnTikla("Teslim Al")
//                .evrakTeslimAlPopUpEvet();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);
//                .btnTikla("Havale Yap")
//                .havaleYapKullaniciListesiSec(guncelAd);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .havaleYapKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");
//
//        teslimAlinanlarPage
//                .openPage()
//                .konuyaGoreEvrakIcerikGoster(konu)
//                .btnTikla("Tebliğ Et")
//                .tebligEtKullaniciListesiSec(guncelAd);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        birimeGelenEvrakKayit();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreIcerikSec(konu);
//                .btnTikla("Havale Yap")
//                .havaleYapKullaniciListesiSec(guncelAd);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .havaleYapKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

//        gelenEvrakKayit();

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);


        evrakDetayiPage
                .btnTikla("İade Et")
                .iadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu,true);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .havaleYapKullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");


    }


    @Step("Test datası oluşturuldu.")
    private void gelenEvrakKayit() {

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriKisiDoldur(kullaniciAdi)
                .kaydet()
                .popUps();
    }

    @Step("Test datası oluşturuldu.")
    private void teslimAlinmayiBekleyenEvrak() {

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .popUps(true);
    }

    @Step("Test datası oluşturuldu.")
    private void birimeGelenEvrakKayit() {
        String evrakTuru = "Resmi Yazışma";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTarihiDoldur(evrakTarihi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .kaydet();

        gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);
    }
}
