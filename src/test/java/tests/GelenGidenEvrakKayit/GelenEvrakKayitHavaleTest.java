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

public class GelenEvrakKayitHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    //    User optiim = new User("optiim", "123");
    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin", "123");
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
    String kisi = "Zübeyde TEKİN";
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    //    String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
    String details = "BHUPGMY";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BHUPGMY";
    String basariMesaji = "İşlem başarılıdır!";
    String gerek = "GEREĞİ İÇİN GÖNDER";
    String bilgi = "BİLGİ İÇİN GÖNDER";
    String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        havaleOnayiVerdiklerim = new HavaleOnayiVerdiklerimPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
    }

    public String getDocPath1() {
        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1683: Gelen evrak kayıt ekranı havale alanları kontrolü")
    public void TS1683() throws InterruptedException {
        String testid = "TS-1683";
        konu = "TS-1683-" + getSysDate();
        String disKullanici = "distest";
        String tuzelKisi = "Büyük Küçük Harflerle Tüzel Kişi";
        String gercekKisi = "Gercek Kisi";
        String kurum = "Cumhurbaşkanlığı";
        String ustBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String dikkatMesaj = "Evrakı kendinize havale edemezsiniz!";
        String ustBirimKullanici = "alkanseker";

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
                .kaydet()
                .popUpsv2();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        login(mbozdemir);

        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .evrakOnizlemeKontrolu()
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()
                .havaleIslemleriKisiStatusKontrol(disKullanici,false)
                .havaleIslemleriKisiStatusKontrol(tuzelKisi,false)
                .havaleIslemleriKisiStatusKontrol(gercekKisi,false)
                .havaleIslemleriKisiStatusKontrol(kurum,false)
                .havaleIslemleriBirimKontrol(ustBirim)
                .havaleIslemleriKisiStatusKontrol(ustBirimKullanici,false);


        login(mbozdemir);
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYap()
                .havaleIslemleriKisiSec(onaylayacakKisi, onayKisiDetails)
                .havaleYapGonder()
                .islemMesaji().dikkatOlmali(dikkatMesaj);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2172: Evrakın Onaylı havale edilmesi ve onayın reddedilmesi (içerikten)")
    public void TS2172() throws InterruptedException {
        String testid = "TS-2172";
        konu = "TS-2172-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String sayfa = "Gelen Evraklar";
        String evrakSayiSag = createRandomNumber(5);
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        testStatus(testid, "Test Başladı");
        gelenEvrakKayitPage
                .openPage()
                .sayfaKontrol(sayfa)
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuKoduKontrol(konuKodu)
                .konuDoldur(konu)
                .konuKontrol(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTuruKontrolu(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakDiliKontrol(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakTarihiKontrol(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .gizlilikDerecesiKontrol(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .kisiKurumKontrol(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .geldigiKurumKontrol(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakSayiSagKontrol(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .evrakGelisTipiKontrol(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .ivedilikKontrol(ivedilik)

                .havaleIslemleriKisiDoldur(kisi)
                .havaleAlanKontrolleri()
                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenOnaylayanKontrolu(onaylayacakKisi)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
//                .dagitimBilgileriBirimOpsiyon(gerek)
                .eklenenBirimOpsiyonKontrolu(gerek)
                .kaydet()
                .gelenEvrakKayitKaydetEvet2()
                .popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali();

        login(mbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakSecIcerikGoster(konu, true)
                .icerikEkranKontrol()
                .icerikHavaleOnayButonKontrolu()
                .icerikHavaleOnay()
                .dagitimIcinGonderileceklerBirimKontrolu(birim)
                .dagitimIcinGonderileceklerKisiKontrolu(kisi)
                .icerikNotAlanınıDoldur(konu)
                .icerikOnayıReddetButonKontrolu()
                .icerikOnaylaButonKontrolu()
                .icerikOnayıReddet()
                .icerikOnayıReddetEvet()
                .islemMesaji().basariliOlmali();

        login(ztekin);
        birimHavaleEdilenlerPage
                .openPage()
                .evrakBulunamadı(konu);

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileEvrakKontrolu(konu);

        login(mbozdemir);
        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2171: Gelen evrak kaydederken havale edilen evrakın geri alınması\n")
    public void TS2171() throws InterruptedException {
        String testid = "TS-2171";
        String islemSureci = "Evrak geri alındı ";
        konu = "TS-2171-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .havaleAlanKontrolleri()
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .dagitimBilgileriBirimOpsiyon(bilgi)
                .dagitimBilgileriKullaniciListesiDoldur("OPTİİM")
                .dagitimBilgileriBirimOpsiyon(gerek)
                .dagitimBilgileriBirimOpsiyon(koordinasyon)
                .kaydet()
                .popUps();

        testStatus(testid, "Test Başladı");
        login(mbozdemir);
        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanıKontrol(konu)
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(birim, islemSureci);

        login(mbozdemir);
        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konu);

        login(yakyol);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoGelmedigiGorme(konu);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS397: Havale yeri Birim, Kişi, Kullanıcı Listesi seçilerek evrakın havale edilmesi\n")
    public void TS397() throws InterruptedException {
        String testid = "TS-397";
        konu = "TS-397-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String sayfa = "Gelen Evraklar";
        String evrakSayiSag = createRandomNumber(5);
        String evrakNo = "";

        testStatus(testid, "Test Başladı");
        gelenEvrakKayitPage
                .openPage()
                .sayfaKontrol(sayfa)
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuKoduKontrol(konuKodu)
                .konuDoldur(konu)
                .konuKontrol(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTuruKontrolu(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakDiliKontrol(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakTarihiKontrol(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .gizlilikDerecesiKontrol(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .kisiKurumKontrol(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .geldigiKurumKontrol(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakSayiSagKontrol(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .evrakGelisTipiKontrol(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .ivedilikKontrol(ivedilik)

                .havaleIslemleriKisiDoldur(kisi)
                .havaleAlanKontrolleri()

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .dagitimBilgileriKisiDoldurWithDetails(onaylayacakKisi,onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .dagitimBilgileriKisiOpsiyon(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)

                .dagitimBilgileriKullaniciListesiDoldur("OPTİİM")
                .eklenenKullaniciListesiKontrolu("OPTİİM")
                .dagitimBilgileriKullaniciListesiOpsiyon(gerek)
                .eklenenKullaniciListesiOpsiyonKontrolu(gerek)
                .dagitimBilgileriKullaniciListesiOpsiyon(koordinasyon)
                .eklenenKullaniciListesiOpsiyonKontrolu(koordinasyon)

                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu)
                .evrakAlanKontrolleri(konu, geldigiKurum, birim, evrakTarihi, evrakNo);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAlanKontrolleri(konu, geldigiKurum, evrakTarihi, evrakSayiSag);


        login(ztekin);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konu)
                .evrakAlanKontrolleri(konu, geldigiKurum, evrakTarihi, evrakSayiSag);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS407: Ek ve ilgisi olan gelen evrakın havalesi")
    public void TS407() throws InterruptedException {
        String testid = "TS-407";
        konu = "TS-407-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String ekMetni = "test otomasyon" + getSysDateForKis();
        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        testStatus(testid, "Test Başladı");
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
                .birimEvrakEkleriKontrol("EK-1", "EK-2", "EK-3")
                .birimEvrakEkleri("İlgi Bilgileri")
                .birimIlgiBilgileriEvrakEkleriKontrol("a", "b");

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .teslimEvrakEkleri("Evrak Ekleri")
                .teslimEvrakEkleriKontrol("EK-1", "EK-2", "EK-3")
                .teslimEvrakEkleri("İlgi Bilgileri")
                .teslimIlgiBilgileriEvrakEkleriKontrol("a", "b");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS435: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (detay ekranından)")
    public void TS435() throws InterruptedException {
        String testid = "TS-435";
        konu = "TS-435-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String sayfa = "Gelen Evraklar";
        String evrakSayiSag = createRandomNumber(5);

        testStatus(testid, "Test Başladı");
        gelenEvrakKayitPage
                .openPage()
                .sayfaKontrol(sayfa)
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuKoduKontrol(konuKodu)
                .konuDoldur(konu)
                .konuKontrol(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTuruKontrolu(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakDiliKontrol(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakTarihiKontrol(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .gizlilikDerecesiKontrol(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .kisiKurumKontrol(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .geldigiKurumKontrol(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakSayiSagKontrol(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .evrakGelisTipiKontrol(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .ivedilikKontrol(ivedilik)

                .havaleIslemleriKisiDoldur(kisi)
                .havaleAlanKontrolleri()

                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .kaydet()
                .gelenEvrakKayitKaydetEvet2()
                .popUpsv2();

        gelenEvrakKayitPage
//                .islemMesaji().basariliOlmali()
                .islemMesaji().basariliOlmali(basariMesaji);


        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .onizlemeEkranKontrol()
                .onizlemeHavaleBilgisiKontrol()
                .onizlemeGeriAlKontrol()
                .geriAlSec()
                .geriAlNotKontrol()
                .geriAlNotDoldur(konu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileEvrakKontrolu(konu)
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeHavaleYap()
                .havaleDagitimEkranKontrolu()

                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenOnaylayanKontrolu(onaylayacakKisi)

                .havaleOnayinaGonderDisabled()
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)

                .havaleOnayinaGonder()
                .islemMesaji().basariliOlmali();


        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(mbozdemir);
        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .onizlemeEkranKontrol()
                .havaleOnayIkonKontrolu()
                .evrakGosterButonKontrolu()
                .havaleOnay()
                .onizlemeEkranKontrol()
                .havaleAlanKontrolleri()
                .onizlemeOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .onayıOnaylaEvet()
                .islemMesaji().basariliOlmali();

        login(ztekin);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1582: Evrakın Onaylı havale edilmesi ve güncellenerek onaylanması")
    public void TS1582() throws InterruptedException {
        String testid = "TS-1582";
        konu = "TS-1582-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getUploadPath() + "test.xlsx";
        String excelName = "test.xlsx";
        String islemSureci1 = "Evrak havale edildi (gereği için)";
        String islemSureci2 = "Evrak havale edildi (bilgi için)";
        String sayfa = "Gelen Evraklar";
        String evrakSayiSag = createRandomNumber(5);

        testStatus(testid, "Test Başladı");
        gelenEvrakKayitPage
                .openPage()
                .sayfaKontrol(sayfa)
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuKoduKontrol(konuKodu)
                .konuDoldur(konu)
                .konuKontrol(konu)
                .evrakTuruSec(evrakTuru)
                .evrakTuruKontrolu(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakDiliKontrol(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakTarihiKontrol(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .gizlilikDerecesiKontrol(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .kisiKurumKontrol(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .geldigiKurumKontrol(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakSayiSagKontrol(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .evrakGelisTipiKontrol(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .ivedilikKontrol(ivedilik)

                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenOnaylayanKontrolu(onaylayacakKisi)

                .havaleIslemleriKisiDoldur(onaylayacakKisi)

                .havaleAlanKontrolleri()
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .kaydet()
                .gelenEvrakKayitKaydetEvet2()
                .popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali();

        login(mbozdemir);

          //19. adım Evrak Onayla Butonuna basılır ise 24. adımdan devam:
//        havaleOnayınaGelenlerPage
//                .openPage()
//                .evrakNoIleEvrakSec(konu)
//                .evrakSecEvrakOnayla(konu, true)
//                .islemMesaji().basariliOlmali();

         //20.adım Havale Onay ikonu tıklanır ise 21. adımdan devam ediyor
        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleOnay()
                .eklenenKisiKontrolu(onaylayacakKisi)
                .havaleOnayiBirimDoldur(birim)
                .eklenenBirimKontrolu(birim)
                .dagitimBilgileriBirimOpsiyon(bilgi)
                .eklenenBirimOpsiyonKontrolu(bilgi)
                .onizlemeOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .onayıOnaylaEvet()
                .islemMesaji().basariliOlmali();

        havaleOnayiVerdiklerim
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(onaylayacakKisi, islemSureci1)
                .evrakGecmisi(birim, islemSureci2);

        login(ztekin);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAlanKontrolleri(konu,"(B)");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS405: Havaleden geri alınan evrakın tekrar havalesi\n")
    public void TS405() throws InterruptedException {
        String testid = "TS-405";
        String islemSureci = "Evrak geri alındı ";
        konu = "TS-405-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";
        String evrakNo = "";
        String mesaj = "Havale işleminin eki bulunmaktadır.";

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .havaleAlanKontrolleri()
//                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .dagitimBilgileriBirimOpsiyon(bilgi)
                .dagitimBilgileriKullaniciListesiDoldur("OPTİİM")
                .dagitimBilgileriBirimOpsiyon(gerek)
                .dagitimBilgileriBirimOpsiyon(koordinasyon)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUps();


        login(ztekin);
        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu)
                .evrakSecIcerikGoster(konu, true)
                .havaleGeriAl()
                .notAlanınıDoldur(konu)
                .geriAl();
//                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeHavaleButtonKontrol()
                .onizlemeHavaleYap()
                .havaleAlanKontrolleri()

                .havaleIslemleriKisiDoldur(kisi)
                .eklenenKisiKontrolu(kisi)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .dagitimBilgileriKisiOpsiyon(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)

                .aciklamaAlaniKontrolu()
                .aciklamaAlaniDoldur(konu)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .buttonGonder()
                .islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo)
                .genelNotPopupKontrol()
                .genelNotPopupMesajKontrol(mesaj);

    }

}
