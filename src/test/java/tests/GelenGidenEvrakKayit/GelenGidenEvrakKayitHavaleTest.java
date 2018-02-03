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
 * Tarih: 2018-01-26
 * Proje: Türksat Functional Test Automation
 * Class: "GelenGidenEvrakKayitHavaleTest" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class GelenGidenEvrakKayitHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;

//
//    User optiim = new User("optiim", "123");
    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin", "123");
    //    String evrakNO321;
//    String evrakNO328;
    String konuKodu = "010.01";
    //      String konu = "TS-2314-" + getSysDate();
    String konu = "";
    String prereqKonu = "";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String evrakTarihi = getSysDateForKis();
    String gizlilikDerecesi = "Normal";
    String kisiKurum = "Kurum";
    String geldigiKurum = "Esk Kurum 071216 2";
    //    String kisiGercek = "Gerçek Kişi";
    String evrakGelisTipi = "Posta";
    String ivedilik = "Normal";
    String kisi = "Zübeyde Tekin";
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
    //      String onaylayacakKisi = "Can ŞEKER";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
    String basariMesaji = "İşlem başarılıdır!";

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
//        kaydedilenGelenEvrakPage = new KaydedilenGelenEvrakPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
//          havaleEttiklerimPage = new HavaleEttiklerimPage();
//        gercekKisiYonetimPage = new GercekKisiYonetimPage();
//        gidenEvrakKayitPage = new GidenEvrakKayitPage();
//        kaydedilenGidenEvraklarPage = new KaydedilenGidenEvraklarPage();
    }

    public String getDocPath1() {
        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2172: Evrakın Onaylı havale edilmesi ve onayın reddedilmesi (içerikten)")
    public void TS2172() throws InterruptedException {
        konu = "TS-2172-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";


        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

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

                .havaleIslemleriKisiDoldur(kisi)
                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)

                .kaydet()
                .gelenEvrakKayitKaydetEvet2()
                .popUpsv2();
//
        login(mbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakSecIcerikGoster(konu, true)
                .havaleOnay()
                .notAlanınıDoldur(konu)
                .onayıReddet()
                .onayıReddetEvet()

                .islemMesaji().basariliOlmali();

        login(ztekin);
        birimHavaleEdilenlerPage
                .openPage()
                .evrakBulunamadı(konu);

        kaydedilenGelenEvraklarPage
                .openPage()
                //iade edilmiştir butonu kontrolü yapılabilir
                .tabloEvrakNoileEvrakKontrolu(konu);

        login(mbozdemir);
        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konu);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS397: Havale yeri Birim, Kişi, Kullanıcı Listesi seçilerek evrakın havale edilmesi\n")
    public void TS397() throws InterruptedException {
        konu = "TS-397-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

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
                .havaleIslemleriKisiDoldur(kisi)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .dagitimBilgileriBirimOpsiyon("B")
                .dagitimBilgileriKullaniciListesiDoldur("OPTİİM")
                .dagitimBilgileriBirimOpsiyon("S")
                .dagitimBilgileriBirimOpsiyon("G")
                .kaydet()
                .popUps();

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(ztekin);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(yakyol);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        prereqKonu = konu;

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2171: Gelen evrak kaydederken havale edilen evrakın geri alınması\n", dependsOnMethods = {"TS397"})
//    @Test(enabled = true, priority = 0, description = "TS2171: Gelen evrak kaydederken havale edilen evrakın geri alınması\n"	)
    public void TS2171() throws InterruptedException {
//        prereqKonu = "TS-397-20180127130012";
        String islemSureci = "Evrak geri alındı ";

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(prereqKonu)
                .evrakSecIcerikGoster(prereqKonu, true)
                .havaleGeriAl()
                .notAlanınıDoldur(prereqKonu)
                .geriAl()
                .islemMesaji().basariliOlmali();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(prereqKonu)
                .secilenEvrakEvrakGecmisi()
                //iade edilmiştir butonu kontrolü yapılabilir
                .evrakGecmisi(birim, islemSureci);

        login(mbozdemir);
        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(prereqKonu);

        login(yakyol);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoGelmedigiGorme(prereqKonu);

    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS407: Ek ve ilgisi olan gelen evrakın havalesi")
    public void TS407() throws InterruptedException {
        String testid= "TS-407";
        konu = "TS-407-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String ekMetni = "test otomasyon" + getSysDateForKis();
        String fileName ="test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        testStatus(testid,"Test Başladı");
//        gelenEvrakKayitPage
//                .openPage()
//                .evrakBilgileriUstYaziEkle(pathToFilePdf)
//                .ustYaziPdfAdiKontrol(pdfName)
//                .islemMesaji().basariliOlmali();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)

                .havaleAlanKontrolleri()
//                .havaleIslemleriKisiDoldur(kisi)
////                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)

                .ekBilgiFiltreAc()
                .evrakEkleriDosyaEkleme(pathToFileText)
                .evrakEkleriDosyaEkleDosyaAdiKontrol(fileName)
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkTabViewEkle()
                .dosyaEkleTabTabloKontrolu("Ek-1")

                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-2")

                 .sistemdeKayitliEvrakEkle()
                 //        evrak tarihi
                 //        evrakın aranacağı yer
                 //        evrak arama alanlarının geldiği görülür
                 .evrakEkTabEvrakAramaDoldur("1")
                 .dokumanAraButton()
                 .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .ekEkleButton1()
                .dosyaEkleTabTabloKontrolu("Ek-3")


//                .evrakEkleriDosyaEkleme(fileName)

                .ilgiliBilgiFiltreAc()
//        dosya ekle
//        metin ekle
//        sistemde kayıttlı evrak ekle
//        arşivde kayıtlı evrak ekle alanlarının geldiği görülür.
                .ilgiIslemleriTabDosyaEkle()
                .ilgiBilgileriDosyaEkleme(pathToFileText)
                .ilgiBilgileriDosyaEkleDosyaAdiKontrol(fileName)
                .ilgiBilgileriDosyaEkleEkMetinDoldur(ekMetni)
                .ilgiBilgileriTabViewEkle()
                .ilgiBilgileridosyaEkleTabloKontrolu("a")



                .ilgiBilgileriSistemdeKayitliEvrakEkle()
                //        evrak tarihi
                //        evrakın aranacağı yer
                //        evrak arama alanlarının geldiği görülür
                .ilgiBilgileriSistemdeKayitliEvrakEkleArama("1")
                .ilgiBilgileridokumanAraButton();

        gelenEvrakKayitPage
                .ilgiBilgileriEkEkleButton1()
                .ilgiBilgileridosyaEkleTabloKontrolu("b")

                .kaydet()
                .popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
//        Geldiği yer
//        Konu
//        Gideceği yer
//        Evrak tarihi / no alanlarının doğru biçimde olacak şekilde evrakın listelendiği görülür.

                .evrakNoIleTablodanEvrakSecme(konu)
                .evrakOnizlemeKontrol()
                .birimEvrakEkleri("Evrak Ekleri")
                .birimEvrakEkleriKontrol("EK-1","EK-2","EK-3")
                .birimEvrakEkleri("İlgi Bilgileri")
                .birimIlgiBilgileriEvrakEkleriKontrol("a","b");

                login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .teslimEvrakEkleri("Evrak Ekleri")
                .teslimEvrakEkleriKontrol("EK-1","EK-2","EK-3")
                .teslimEvrakEkleri("İlgi Bilgileri")
                .teslimIlgiBilgileriEvrakEkleriKontrol("a","b");
    }

}
