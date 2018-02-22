package tests.GelenGidenEvrakKayit;

import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TopluEvrakOnizleme;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.HavaleEdilenEvrakRaporuPage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/****************************************************
 * Tarih: 2018-02-22
 * Proje: Türksat Functional Test Automation
 * Class: "KaydedilenGelenEvrakHavale" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class GelenEvrakListesindenHavale extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;
    TopluEvrakOnizleme topluEvrakOnizleme;
    HavaleEdilenEvrakRaporuPage havaleEdilenEvrakRaporuPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;

    static final Logger logger = LogManager.getLogger("GelenEvrakListesindenHavale");

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
    String kullanici = "Yasemin Çakıl";

    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String details = "BHUPGMY";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BHUPGMY";

    @BeforeMethod
    public void loginBeforeTests() {
        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        havaleOnayiVerdiklerim = new HavaleOnayiVerdiklerimPage();
        topluEvrakOnizleme = new TopluEvrakOnizleme();
        havaleEdilenEvrakRaporuPage = new HavaleEdilenEvrakRaporuPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
    }


//    public String getDocPath1() {
//        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
//    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS823: Kişi seçilerek evrakın onaylı havale edilmesi (içerikten)")
    public void TS823() throws InterruptedException {
        String testid = "TS-823";
        konu = "TS-823-" + getSysDate();

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet()
                .popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(kullanici,details)
                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

}

