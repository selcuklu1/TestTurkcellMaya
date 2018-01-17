package tests.GelenGidenEvrakKayit;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.BirimHavaleEdilenlerPage;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.KaydedilenGidenEvraklarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GercekKisiYonetimPage;
import pages.ustMenuPages.GidenEvrakKayitPage;
import pages.ustMenuPages.KaydedilenGelenEvrakPage;

import java.io.IOException;
import java.text.ParseException;

public class GelenGidenEvrakKayitTest extends BaseTest {
    MainPage mainPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvrakPage kaydedilenGelenEvrakPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    GercekKisiYonetimPage gercekKisiYonetimPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;
    KaydedilenGidenEvraklarPage kaydedilenGidenEvraklarPage;

    User optiim = new User("optiim", "123");
    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");


    @BeforeMethod
    public void loginBeforeTests() {
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        kaydedilenGelenEvrakPage = new KaydedilenGelenEvrakPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        kaydedilenGidenEvraklarPage = new KaydedilenGidenEvraklarPage();

//        login("ztekin", "123");
    }

    public String getDocPath1() {
        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
    }

    String evrakNO321;
    String evrakNO328;
    String konuKodu = "010.01";
    String konu = "Test " + getSysDate();
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String evrakTarihi = getSysDateForKis();
    String gizlilikDerecesi = "Normal";
    String kisiKurum = "Kurum";
    String geldigiKurum = "Esk Kurum 071216 2";
    String kisiGercek = "Gerçek Kişi";
    String evrakGelisTipi = "Posta";
    String ivedilik = "Normal";
    String ekMetni = "test otomasyon" + getSysDateForKis();
    String aciklama = "Test Otomasyon";

//    String pathToFilePdf = getDocPath() + "Otomasyon.pdf";
//    String pdfName = "Otomasyon.pdf";
//    String pathToFileExcel = getDocPath() + "test.xlsx";
//    String excelName = "test.xlsx";
//    String pathToFileEmail = getDocPath() + "ekranGoruntuleri.msg";
//    String ustYaziAdiMail = "ekranGoruntuleri.msg";
//    String bigPdfPath = getDocPath() + "emresencan.pdf";

    String basariMesaji = "İşlem başarılıdır!";

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS0321: Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TS0321() throws InterruptedException {
        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";
        String pathToFileExcel = getDocPath1() + "test.xlsx";
        String excelName = "test.xlsx";

        login(mbozdemir);

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
                .ekBilgiFiltreAc()
                .evrakEkleriDosyaEkleme(pathToFileExcel)
                .evrakEkleriDosyaEkleDosyaAdiKontrol(excelName)
                .ustYaziDegistirilmisPopUpKontrol(false)
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkTabViewEkle()
                .dosyaEkleTabTabloKontrolu("Ek-1")
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-2")
                .kaydet();

        evrakNO321 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 1, description = "TS2163 : Kaydedilen gelen evrakın güncellenmesi"
            , dependsOnMethods = {"TS0321"})
    public void TS2163() throws InterruptedException {

        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String evrakTuru = "Dilekçe";
        login(mbozdemir);
//        String evrakNO321 = "5569";

// TS0321 de oluşturulan evrak no burada kullanılacak.

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec(evrakNO321); // sırayla çalışma yapıldığında evrakNO321 parametre olarak eklenecek

        gelenEvrakKayitPage
                .evrakDetaylariAlanGuncellenebilirlikKontrolü()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .evrakDetayiPdfDegisiklikpopUpClose()
//                .ustYaziPdfAdiKontrol(ustYaziAdi)
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .evrakDetayiEkleriTab()
                .evrakDetayiFizikselEkEkleTab()
                .evrakDetayiAciklamaDoldur(aciklama)
                .evrakDetayiEkle()
                .dosyaEkleTabTabloKontrolu("Ek-3") // office converter açıldıktan sonra TS0321 deki evrak no kullanıldığında bu satır aktif olarak kullanılabilir.
                .evrakDetayiKaydet()
                .evrakDetayiKaydetPopUpClose()
                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvraklarPage
                .openPage()
//                .tabloEvrakNoileEvrakKontrolu(evrakNO321)
                .tabloEvrakNoileIcerikSec(evrakNO321)
                .guncellenenAlanKontrolleri(evrakTarihi, evrakTuru, gizlilikDerecesi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS0321"}, description = "TS0328 : Gelen evrak kayıt ekranından havale")
    public void TS0328() throws InterruptedException {

        String birim = "OPTİİM BİRİM";
        String details = "YGD";
        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";

        login(mbozdemir);

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
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
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet();
        evrakNO328 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(evrakNO328);
        logout();
        login(optiim);

        //TeslimAlınanBelgeler sayfasında yetkili bir kullanıcı ile giriş yapılacak.
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .tabloKontrolu(evrakNO328);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 7, description = "TS1401 : Kaydedilen Gelen Evrak raporu")
    public void TS1401() throws InterruptedException, IOException, ParseException {

//        String evrakNO321 = "6387";
//        String evrakNO328 = "6391";
//        String evrakNo = evrakNO321;
//        String evrakNo1 = evrakNO328;
        String geldigiYer = "Kurum";

        login(yakyol);

// Testin öncesinde TS0321 ve TS0328 caselerinin çalışması gerekli..

        kaydedilenGelenEvrakPage
                .openPage()
                .ekranAlanKontrolleri()
                .birimKontrol()
                .evrakTarihiKontrol()
                .altBirimSec(true)
                .sorgula()
                .gelenEvrakNoDoldur(evrakNO328)
                .sorgula()
                .tabloKontrolu(evrakNO328)

                .gelenEvrakNoDoldur(evrakNO321)
                .sorgula()
                .tabloKontrolu(evrakNO321)

                .geldigiYerSec(geldigiYer)
                .geldigiKurumSec("Esk Kurum 071216 2")
                .gelenEvrakNoDoldur(evrakNO321)
                .sorgula()
                .tabloKontrolu(evrakNO321);
//                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(mbozdemir);

        kaydedilenGelenEvrakPage
                .openPage()
                .evrakTarihiBaslangicDoldur(getSysDateForKis())
//                .gelenEvrakNoDoldur(evrakNO321)
                .sorgula()
//                .tabloKontrolu(evrakNO321)
                .tabloKontrouAll(evrakNO321, evrakNO328)
//                .tabloKontrouAll(evrakNO328)
                .raporAlExcel()
                .waitForLoadingJS(WebDriverRunner.getWebDriver(), 180);

//        kaydedilenGelenEvrakPage
//                .gelenEvrakNoDoldur(evrakNO328)
//                .sorgula()
//                .tabloKontrolu(evrakNO328)
//                .raporAlPdf()
//                .waitForLoadingJS(WebDriverRunner.getWebDriver(), 180);
//                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvrakPage
                .raporAlPdf()
                .waitForLoadingJS(WebDriverRunner.getWebDriver(), 180);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1136 : Gelen evrak kaydederken yeni gerçek ve tüzel kişi tanımlama")
    public void TS1136() throws InterruptedException {

//        String TCKN = "51091330934";
        String ad = "Test";
        String soyad = "Otomasyon";
        String mernisNo = createMernisTCKN();

        login(optiim);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec(kisiGercek)
                .geldigiKisiEkle()
                .iletisimBilgisiTCKNEkle(mernisNo)
                .iletisimBilgisiTCKNAra()
                .iletisimBilgisiAdDoldur(ad)
                .iletisimBilgisiSoyadDoldur(soyad)
                .iletisimBilgisikaydet();

        gercekKisiYonetimPage
                .openPage()
                .filtreTCKimlikNoDoldur(mernisNo)
                .ara()
                .tabloTCKNKontrol(mernisNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0394 : Gelen maillerin evrak olarak sisteme dahil edilmesi")
    public void TS0394() throws InterruptedException {

        String birim = "OPTİİM BİRİM11";

        String pathToFileExcel = getDocPath1() + "test.xlsx";
        String pathToFileEmail = getDocPath1() + "ekranGoruntuleri.msg";

        login(optiim);
//        String ustYaziAdi = "ustYazi.pdf";  // TestOtomasyon.msg ekini eklememe rağmen ustYazi.pdf  olarak ekrana geliyor.
        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFileEmail)
//                .ustYaziMailAdiKontrol(ustYaziAdiMail)
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
                .ekBilgiFiltreAc()
                .evrakEkleriDosyaEkleme(pathToFileExcel)
                .ustYaziDegistirilmisPopUpKontrol(false)
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkleriDosyaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-3") //Webservise  baglanılamadı hatası alnıyor.
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-4")
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileEvrakKontrolu(evrakNo);
//                .tabloKonuyaGoreEvrakKontrolu(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0322 : Gelen evrak kayıtta alan kontrolleri")
    public void TS0322() throws InterruptedException {

        String kisiKurum1 = "Kurum";
        String geldigiKurum = "Cumhurbaşkanlığı";
        String solAlan = "24301012-";
        String evrakTuru = "Dilekçe";
        String ivedilik = "Günlü";
        String evrakGelisTipi = "Posta";
        String message = "Zorunlu alanları doldurunuz";
        String message2 = "Dosya büyüklüğü uygun değildir.";
        String evrakTuru1 = "Diğer";
        String birim = "OPTİİM BİRİM";
        String uyariMesajı = "Dosya büyüklüğü uygun değildir!!";
        String secim = "Hayır";
        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String bigPdfPath = getDocPath1() + "emresencan.pdf";

        login(optiim);
        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(bigPdfPath)
                .islemMesaji().uyariOlmali(uyariMesajı);
        gelenEvrakKayitPage.alanKontrolleri()
                .kisiKurumSec(kisiGercek)
                .evrakTuruKontrol(evrakTuru)
                .kisiKurumSec(kisiKurum1)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayisiSolAlanKontrolu(solAlan)
                .ivedilikSec(ivedilik)
                .konuKoduDoldur(konuKodu)
                .evrakTarihiDoldur(evrakTarihi)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .kaydet()
                .islemMesaji().uyariOlmali(message);


        gelenEvrakKayitPage
                .miatDoldur(getSysDateForKis())
                .konuKoduSil()
                .kaydet()
                .islemMesaji().uyariOlmali(message);

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .evrakTarihiSil()
                .kaydet()
                .islemMesaji().uyariOlmali(message);

        gelenEvrakKayitPage
                .evrakTarihiDoldur(evrakTarihi)
                .evrakSayiSagSil()
                .kaydet()
                .islemMesaji().uyariOlmali(message);


        gelenEvrakKayitPage
                .evrakTuruSec(evrakTuru1)
                .kaydet()
                .popUpKontrol2(secim);

//
//        gelenEvrakKayitPage
//                .evrakBilgileriUstYaziEkle(bigPdfPath);
//        gelenEvrakKayitPage.islemMesaji().uyariOlmali(uyariMesajı);
////                250 mb pdf yuklerken timeouta düşüyor....
//
//        waitForLoadingJS(WebDriverRunner.getWebDriver(),1200);

//        gelenEvrakKayitPage.islemMesaji().uyariOlmali(uyariMesajı);
//işlem mesajı eklenecek

        gelenEvrakKayitPage
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziGizle()
                .ustYaziGoster()
                .kaydet()
                .popUpKontrol(secim);
        gelenEvrakKayitPage
                .dagitimBilgileriBirimDoldur2(birim)
                .kaydet()
                .popUps();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1340 : Giden evrak kaydı")
    public void TS1340() throws InterruptedException {

        String miatTarihi = getSysDateForKis();
        String geregi = "OPTİİM BİRİM";
        String kaldirlacakKlasor = "ESK05";
        String bilgi = "TAŞRA TEŞKİLATI";
        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";

        login(optiim);

        gidenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

        gidenEvrakKayitPage
                .evrakTuruSec(evrakTuru)
                .ivedilikSec(ivedilik)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .konuKoduDoldur(konuKodu)
                .evrakTuruIcerikKontrolu("Resmi Yazışma")
                .evrakTuruIcerikKontrolu("Olur Yazısı")
                .evrakTuruSec("Olur Yazısı")
                .ivedilikIcerikKontrol()
                .gizlilikDerecesiIcerikKontrol()
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .konuDoldur(konu)
                .evrakDiliSec(evrakDili)
                .miatDoldur(miatTarihi)
                .geregiDoldur(geregi, "Ad")
                .kaldiralacakKlasorDoldur(kaldirlacakKlasor)
                .bilgiDoldur(geregi)
                .evrakTarihiDoldur(evrakTarihi)
                .ekBilgiFiltreAc()
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
                .ilgiEkleriFiltreAc()
                .ilgiEkleriMetinTabAc()
                .ilgiEkleriMetinEkMetniDoldur(ekMetni)
                .ilgiEkleriMetinEkle()
                .kaydet()
                .popUpkaydetEvet();
        String evrakNo1340 = gidenEvrakKayitPage.popUpBasariliKapat();
        gidenEvrakKayitPage
                .islemMesaji().basariliOlmali();

//        Thread.sleep(2000);

        kaydedilenGidenEvraklarPage
                .openPage()
                .tabloKontrolu(evrakNo1340);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0326 : Gelen evrakın tarama havuzundan evrak eklenerek kaydedilmesi")
    public void TS0326() throws InterruptedException {


        String birim = "OPTİİM BİRİM";
        String evrakNO326 = "";
        String pdfText = "";
        String details = "YGD";
        String pathToFilePdf = getDocPath1() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";

        login(yakyol);

        gelenEvrakKayitPage
                .openPage()
                .taramaHavuzundanEkleSayfasiAc()
                .taramaHavuzuIlkKayitSec()
                .taramaHavuzuIlkKayitTurSec("Üst Yazı")
                .taramaHavuzuTamam();

        gelenEvrakKayitPage
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziDegistirilmisPopUpKontrol(true)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();
        pdfText = gelenEvrakKayitPage.onIzlemePdfText();


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
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet();

        evrakNO326 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(evrakNO326)
                .evrakOnizlemeEklenenUstYaziKontrolu(pdfText);

        logout();
        login(optiim);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(evrakNO326)
                .evrakOnizlemeEklenenUstYaziKontrolu(pdfText);
    }
}
