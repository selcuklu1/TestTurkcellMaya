package tests.KullaniciListesiYonetimi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.KullaniciListesiYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KullaniciListesiYonetimiTest extends BaseTest {

    KullaniciListesiYonetimiPage kullaniciListesiYonetimiPage;

    String ad = "TS1005 " + createRandomNumber(6);
    String aciklama = "TS1005 " + getSysDate();
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String kullanici1 = "Username22n TEST";
    String kullanici2 = "Username21g TEST";
    String dikkatMesaji = "Kullanıcı grubunda en az 2 kullanıcı olmak zorundadır!";
    String basariMesaji = "İşlem başarılıdır!";
    String durumPasifler = "Sadece Pasifler";
    String durumAktifler = "Sadece Aktifler";

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciListesiYonetimiPage = new KullaniciListesiYonetimiPage();
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
}
