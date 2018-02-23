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
    HavaleEttiklerimPage havaleEttiklerimPage;

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
        havaleEttiklerimPage = new HavaleEttiklerimPage();
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

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1591: Evrakın havale onayından geri çekilmesi")
    public void TS1591() throws InterruptedException {
        String testid = "TS-1591";
        konu = "TS-1591-" + getSysDate();

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

        testStatus(testid, "Test Başladı");
        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .onizlemeHavaleBilgisiKontrol()
                .onizlemeGeriAlKontrol()
                .havaleBilgisiSec()
                .kisiKontrol(kullanici)
                .geriAlSec()
                .notAlaniKontrol()
                .geriAlNotDoldur(konu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1597: Havale onayından geri çekilen evrakın havale edilmesi")
    public void TS1597() throws InterruptedException {
        String testid = "TS-1597";
        konu = "TS-1597-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo;

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
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

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
                .evrakNoIleEvrakSec(konu)
                .onizlemeHavaleBilgisiKontrol()
                .onizlemeGeriAlKontrol()
                .havaleBilgisiSec()
                .kisiKontrol(kullanici)
                .geriAlSec()
                .notAlaniKontrol()
                .geriAlNotDoldur(konu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();


        testStatus(testid, "Test Başladı");

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()
                .havaleIslemleriKisiSec(kullanici,details)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .havaleIslemleriKisiKontrol(kullanici)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .havaleIslemleriKisiOpsiyonSec(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali();

        havaleEttiklerimPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,birim,evrakTarihi,evrakNo);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);
    }


}

