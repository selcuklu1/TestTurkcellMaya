package tests.KullaniciListesiYonetimi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;

    String ad = "TS1005 " + createRandomNumber(8);
    String aciklama = "TS1005 " + getSysDate();
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String kullanici1 = "Username22n TEST";
    String kullanici2 = "Username21g TEST";
    String dikkatMesaji = "Kullanıcı grubunda en az 2 kullanıcı olmak zorundadır!";
    String basariMesaji = "İşlem başarılıdır!";
    String durumPasifler = "Sadece Pasifler";
    String durumAktifler = "Sadece Aktifler";
    String guncelAd = ad + " Guncellendi";
    String mesaj ="Kullanıcı grubunun durumunu değiştirmek istediğinize emin misiniz?";
    String btnEvet = "Evet";


    String konuKodu = "Diğer";
    String konu = "Kullanıcı Listesi " + createRandomNumber(9);
    String konu2 = "Kullanıcı Listesi " + createRandomNumber(9);
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
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();

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
                .kullaniciListesiListedenCikartButonKontrolu()
                .kaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        kullaniciListesiYonetimiPage
                .kullanicilarSec(kullanici2)
                .kullaniciListesiListedenCikartButonKontrolu()
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciListesiYonetimiPage
                .sayfaKontrolu()
                .sorgulaVeFiltreleAdDoldur(ad)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad,true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1005"}
            , description = "TS1001 : Kullanıcı Listesinin Pasif Duruma Getirilmesi")
    public void TS1001() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

//        String ad = "TS1005 120143";


        kullaniciListesiYonetimiPage
                .openPage()
//                .sorgulaVeFiltreleAdDoldur(ad)
                .durumSec(durumAktifler)
                .ara()
                .tabloAktifPasifButonKontrolu(durumAktifler)
                .sorgulaVeFiltreleTabAc()
                .sorgulaVeFiltreleAdDoldur(ad)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, true)
                .pasifYap(ad)
                .islemOnayiPopUpEvetHayır(btnEvet,mesaj)
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



//        String ad = "TS1005 142310";
        kullaniciListesiYonetimiPage
                .openPage()
                .sorgulaVeFiltreleAdDoldur(ad)
                .durumSec(durumPasifler)
                .ara()
                .aktifYap(ad)
                .islemOnayiPopUpEvetHayır(btnEvet,mesaj)
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
            , dependsOnMethods = {"TS1464"}   //1464 lerden bırı eklenecek
            , description = "TS1000 : Kullanıcı Listesi Güncelleme")
    public void TS1000() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
//        String ad = "TS1005 113204";
//
//        String guncelAd = ad + " Guncellendi";

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
                .sayfaKontrolu()
                .sorgulaVeFiltreleTabAc()
                .sorgulaVeFiltreleAdTemizle()
                .sorgulaVeFiltreleAdDoldur(guncelAd)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(guncelAd, true);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1000"}
            , description = "TS1466 : Kullanıcı Listesi Güncelleme Sonrası Ekranlardan Kontrolü")
    public void TS1466() throws InterruptedException {

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
//        String guncelAd = "TS1005 121034 GUNCELLENDİ";

        gelenEvrakKayit(konu);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(guncelAd)
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

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al")
                .evrakTeslimAlPopUpEvet();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimeGelenEvrakKayit();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreIcerikSec(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);


        evrakDetayiPage
                .btnTikla("İade Et")
                .iadeEt();
//                .islemMesaji().basariliOlmali(basariMesaji);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(guncelAd)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            ,dependsOnMethods = {"TS1003"}
            , description = "TS1465 : Yeni Kullanıcı Listesi Tanımlama Sonrası Ekranlardan Kontrolü")
    public void TS1465() throws InterruptedException {

//        TS1005();
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String dikkatMesaji = "Havaleyi onaylayacak kullanıcıyı seçiniz.";
        String kullanici = "Username21g TEST";
        String onaylayacakKisi = "Unvv TEST";

//String guncelAd="TS1005 142310";
//String konu = "TS1466 171246305";
//String konu2 = "TS1466 163471520";
        gelenEvrakKayit(konu);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .kullaniciListesiDetay()
                .kullaniciGrupDetayKontrol(kullanici)
                .kullaniciGrupDetayCheckBoxKontrolu(true)
                .kullaniciGrupDetayCheckBoxSecimi(kullanici, false)
                .kullaniciGrupDetayKullan()
                .havaleYapButon("Gönder");
//                .islemMesaji().isBasarili(basariMesaji);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
        gelenEvrakKayit(konu2);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu2);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .kullaniciListesiDetay()
                .kullaniciGrupDetayKontrol(kullanici)
                .kullaniciGrupDetayKontrol(kullanici)
                .kullaniciGrupDetayCheckBoxKontrolu(true)
                .kullaniciGrupDetayCheckBoxSecimi(kullanici, false)
                .kullaniciGrupDetayKullan()
                .havaleYapButon("Havale Onayına Gönder")
                .islemMesaji().dikkatOlmali(dikkatMesaji);


        evrakDetayiPage
                .onaylayacakKisiSec(onaylayacakKisi)
                .havaleYapHavaleOnayınaGonder();
//                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        gelenEvrakKayit(konu);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiSec(ad)
                .tebligEtKullaniciListesiDetay()
                .tebligEtKullaniciGrupDetayKontrol()
                .tebligEtKullaniciGrupDetayEkraniKapat()
//                .kullaniciGrupDetayKontrol(kullanici)
//                .kullaniciGrupDetayKontrol(kullanici)
//                .kullaniciGrupDetayCheckBoxKontrolu(true)
//                .kullaniciGrupDetayCheckBoxSecimi(kullanici,false)
//                .kullaniciGrupDetayKullan()
                .havaleYapButon("Tebliğ Et");
//                .islemMesaji().basariliOlmali(basariMesaji)

        teslimAlinmayiBekleyenEvrak();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(ad)
                .kullaniciListesiDetay()
                .kullaniciGrupDetayKontrol(kullanici)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al")
                .evrakTeslimAlPopUpEvet();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimeGelenEvrakKayit();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreIcerikSec(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("İade Et")
                .iadeEt();
//                .islemMesaji().basariliOlmali(basariMesaji);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(ad)
                .kullaniciListesiDetay()
                .kullaniciGrupDetayKontrol(kullanici1)
                .kullaniciGrupDetayKontrol(kullanici2)
                .kullaniciGrupDetayEkraniKapat()
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            ,dependsOnMethods = {"TS1465"}
            , description = "TS1464 : Kullanıcı Listeleri Aktiflik/Pasiflik Kontrolü")
    public void TS1464() throws InterruptedException {

//        TS1005();
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String dikkatMesaji = "Havaleyi onaylayacak kullanıcıyı seçiniz.";
        String kullanici = "Username21g TEST";
        String onaylayacakKisi = "Zübeyde Tekin";

//String ad="TS1005 142310";
//String konu = "TS1466 171246305";
//String konu2 = "TS1466 163471520";

        kullaniciListesiYonetimiPage
                .openPage()
                .sorgulaVeFiltreleAdDoldur(ad)
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTabloKontrolu()
                .pasifYap(ad)
                .islemOnayiPopUpEvetHayır(btnEvet,mesaj)
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false)
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumPasifler)
                .ara()
                .kullaniciListesiTabloKontrolu()
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false);

        gelenEvrakKayit(konu);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiKontrolu(ad, false);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiKontrolu(ad, false);

        teslimAlinmayiBekleyenEvrak();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiKontrolu(ad,false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al")
                .evrakTeslimAlPopUpEvet();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiKontrolu(ad,false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinanlarPage
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiKontrolu(ad,false);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimeGelenEvrakKayit();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreIcerikSec(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiKontrolu(ad,false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("İade Et")
                .iadeEt();
//                .islemMesaji().basariliOlmali(basariMesaji);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiKontrolu(ad,false);

        kullaniciListesiYonetimiPage
                .openPage()
                .durumSec(durumPasifler)
                .sorgulaVeFiltreleAdDoldur(ad)
                .ara()
                .kullaniciListesiTabloKontrolu()
                .aktifYap(ad)
                .islemOnayiPopUpEvetHayır(btnEvet,mesaj)
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false)
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumAktifler)
                .ara()
                .kullaniciListesiTabloKontrolu()
                .sorgulaVeFiltreleTabAc()
                .durumSec(durumPasifler)
                .ara()
                .kullaniciListesiTablosuKullaniciAdiKontrolu(ad, false);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        gelenEvrakKayit(konu);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiSec(ad);

        teslimAlinmayiBekleyenEvrak();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Teslim Al")
                .evrakTeslimAlPopUpEvet();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinanlarPage
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("Tebliğ Et")
                .tebligEtKullaniciListesiSec(ad);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        birimeGelenEvrakKayit();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreIcerikSec(konu);

        evrakDetayiPage
                .btnTikla("Havale Yap")
                .kullaniciListesiSec(ad)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .btnTikla("İade Et")
                .iadeEt();
//                .islemMesaji().basariliOlmali(basariMesaji);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .btnTikla("Teslim Al ve Havale Et")
                .kullaniciListesiSec(ad);


    }


    @Step("Test datası oluşturuldu.")
    private void gelenEvrakKayit(String konu) {

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

//        gelenEvrakKayitPage
//                .islemMesaji().basariliOlmali(basariMesaji);
    }
}
