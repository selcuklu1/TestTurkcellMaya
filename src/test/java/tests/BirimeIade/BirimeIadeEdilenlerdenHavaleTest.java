package tests.BirimeIade;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;


public class BirimeIadeEdilenlerdenHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerimPage;

    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin" , "123");

    @BeforeMethod
    public void loginBeforeTests() {
        login(ztekin);
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleOnayiVerdiklerimPage = new HavaleOnayiVerdiklerimPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1587: Birim seçilerek evrak havale etme (detay ekranından)")
    public void TS1587() throws InterruptedException {
        String testid= "TS-1587";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-1587-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String fileName ="test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        testStatus(testid,"PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
        gelenEvrakKayitPage
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
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .btnIadeEt()
                .btnIadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true)
                .içeriktenEvrakTeslimAlHavaleEt()
                .dagitimBilgileriBirimDoldur2(birim)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .teslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1588: Kişi seçilerek evrak havalesini onaya sunma (önizleme ekranından)")
    public void TS1588() throws InterruptedException {
        String testid= "TS-1588";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-1588-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String onaylayacakKisi = "Mehmet BOZDEMİR";
        String onayKisiDetails = "YGD";

        String fileName ="test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        testStatus(testid,"PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
        gelenEvrakKayitPage
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
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .btnIadeEt()
                .btnIadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeTeslimAlveHavaleYap()
                .havaleIslemleriKisiDoldur(kisi)
                //geregi bilgi convert
                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .onizlemeDosyaEkle()
                .onizlemeHavaleDosyaEkle(pathToFileText)
                .onizlemeHavaleDosyaEkleDosyaAdiKontrol(fileName)
                .havaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleBilgisi()
                .kisiKontrol(kisi);

        login(mbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu,true)
                .icerikHavaleOnay()
                .dagitimBilgileriBirimDoldur2(birim)
                //geregi bilgi convert
                .dagitimOnayla()
                .dagitimOnaylaEvet()
                .islemMesaji().basariliOlmali(basariMesaji);


        havaleOnayiVerdiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(ztekin);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu);

        login(mbozdemir);

        gelenEvrakKayitPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }
}
