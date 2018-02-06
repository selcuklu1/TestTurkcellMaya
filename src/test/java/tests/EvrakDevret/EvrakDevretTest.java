package tests.EvrakDevret;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;


/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/

public class EvrakDevretTest extends BaseTest {

    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    EvrakOlusturPage evrakOlusturPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;

    User mbozdemir = new User("mbozdemir", "123");
    User username22n = new User("username22n", "123");

    //    String konu = "TS2178 20180205135705";
    String konu = "TS2178 " + getSysDate();
    String tur = "IMZALAMA";
    String icerik = "Test Otomasyon " + getSysDate();
    String konuKodu = "010.01";
    String kaldiralacakKlasor = "Diğer";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String gizlilikDerecesi = "Normal";
    String ivedilik = "Normal";
    String geregi = "Optiim Birim";
    String kullaniciNormal = "USERNAME22N TEST";
    String basariMesaji = "İşlem başarılıdır!";

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2178 : İlgisi olan İşlem Bekleyen Cevap Evrakı Devretme ve Sonrasında Devralandan Silinmesi ve İlginin Kontrolü")
    public void TS2178() throws InterruptedException {

        login(mbozdemir);
        evrakOlustur();

        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec("username21g")
                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi("İmza Bekleyen Evraklar", konu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);
        kullaniciEvrakDevretPage
                .tabloEvrakKontrolu(konu, false);

        login(username22n);
        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(konu);

        //9. adım ve sonrası yazılacak

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            ,dependsOnMethods = {"TS2178"}
            , description = "TS2179 : Devredilen evrakların devralan kullanıcıda hareket/evrak geçmişinin kontrolü")
    public void TS2179() throws InterruptedException {
        login(username22n);
        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);
        evrakDetayiPage
                .sayfaAcilmali();

    }

    @Step("Test datası oluşturuldu.")
    private void evrakOlustur() {
        String imzacı = "username21g";
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("gündem") //kaldırılacakKlasör
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedilikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullanicilarDoldur(imzacı)
                .kullniciIsmineGoreImzaParafSec(imzacı, tur)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakParafla();

    }
}
