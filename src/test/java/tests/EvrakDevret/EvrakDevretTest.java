package tests.EvrakDevret;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;


/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/

public class EvrakDevretTest extends BaseTest{

    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    EvrakOlusturPage evrakOlusturPage;

    User mbozdemir = new User("mbozdemir", "123");

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        evrakOlusturPage = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2178 : İlgisi olan İşlem Bekleyen Cevap Evrakı Devretme ve Sonrasında Devralandan Silinmesi ve İlginin Kontrolü")
    public void TS2178() throws InterruptedException {

        login(mbozdemir);
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

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedilikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .IlkKullaniciImzalamaVeyaParaflamaSec(tur);

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakImzala();

        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec("Yasemin Çakıl")
                .listele()
                .tabloAlanKontrolleri();




    }
}
