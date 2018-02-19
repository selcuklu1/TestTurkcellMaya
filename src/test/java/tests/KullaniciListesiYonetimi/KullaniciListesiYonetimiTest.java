package tests.KullaniciListesiYonetimi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageData.UstMenuData;
import pages.ustMenuPages.KullaniciListesiYonetimiPage;
import pages.ustMenuPages.KullaniciYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KullaniciListesiYonetimiTest extends BaseTest {

    KullaniciListesiYonetimiPage kullaniciListesiYonetimiPage;

    String ad = "TS1005 "+createRandomNumber(6);
    String aciklama = "TS1005 " + getSysDate();
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String kullanici1 = "Username22n TEST";
    String kullanici2 = "Username21g TEST";
    String dikkatMesaji = "Kullanıcı grubunda en az 2 kullanıcı olmak zorundadır!";
    String basariMesaji = "İşlem başarılıdır!";

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
//            ,dependsOnMethods = {"TS1005"}
            , description = "TS1001 : Kullanıcı Listesinin Pasif Duruma Getirilmesi")
    public void TS1001() throws InterruptedException{

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
String ad ="TS1005 123041";
        kullaniciListesiYonetimiPage
                .openPage()
                .durumSec("Sadece Aktifler")
                .ara()
                .kullaniciListesiTabloKontrolu(ad,true)
                .pasifYap(ad)
                .islemOnayiPopUpEvetHayır("Evet")
                .durumSec("Sadece Pasifler")
                .ara()
                .kullaniciListesiTabloKontrolu(ad,true);


    }
}
