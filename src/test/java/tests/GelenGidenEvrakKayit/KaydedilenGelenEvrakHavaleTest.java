package tests.GelenGidenEvrakKayit;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "KaydedilenGelenEvrakHavale" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class KaydedilenGelenEvrakHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
//    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
//    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
//    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;

//    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");
//    User ztekin = new User("ztekin", "123");
    String konuKodu = "010.01";
    String konu = "";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String evrakTarihi = getSysDateForKis();
    String gizlilikDerecesi = "Normal";
    String kisiKurum = "Kurum";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakGelisTipi = "Posta";
    String ivedilik = "Normal";
    String kisi = "Zübeyde Tekin";
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String details = "BHUPGMY";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BHUPGMY";
//    String basariMesaji = "İşlem başarılıdır!";

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
//        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
//        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
//        havaleOnayiVerdiklerim = new HavaleOnayiVerdiklerimPage();
    }

    public String getDocPath1() {
        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS422: Kişi seçilerek evrak havale etme (detay ekranından)")
    public void TS422() throws InterruptedException {
        String testid= "TS-422";
        konu = "TS-422-" + getSysDate();
//        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
//        String pdfName = "Otomasyon.pdf";
//        String pathToFileExcel = getUploadPath() + "test.xlsx";
//        String excelName = "test.xlsx";

        testStatus(testid,"PreCondition Evrak Oluşturma");

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .kaydet()
                .popUpsv2();

        testStatus(testid,"Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }


}
