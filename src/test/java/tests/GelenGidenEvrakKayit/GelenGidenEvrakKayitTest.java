package tests.GelenGidenEvrakKayit;

import common.BaseTest;
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

    String evrakNO321;
    String evrakNO328;
    String konuKodu = "010.01";
    String evrakTuru = "R";
    String evrakDili = "917";
    String evrakTarihi = "16.11.2017";
    String gizlilikDerecesi = "N";
    String kisiKurum = "D";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakGelisTipi = "P";
    String ivedilik = "N";
    String ekMetni = "test otomasyon";

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
        login("optiim", "123");
//        login("ztekin", "123");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0321: Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String ustYaziPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\pdf.pdf";
        String excelPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\test.xlsx";
        String ustYaziAdi = "pdf.pdf";
        String excelAdi = "test.xlsx";


        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .ustYaziPdfAdiKontrol(ustYaziAdi)
                .islemMesaji().isBasarili();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
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
                .evrakEkleriDosyaEkleme(excelPath)
                .evrakEkleriDosyaEkleDosyaAdiKontrol(excelAdi)
                .ustYaziDegistirilmisPopUpKontrol()
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkTabViewEkle()
//                .dosyaEkleTabTabloKontrolu("Ek-1")  //
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
//                .dosyaEkleTabTabloKontrolu("Ek-2")
                .kaydet();

        evrakNO321 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvraklarPage
                .openPage()
                .filtreleAc()
                .tarihDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNO321);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TC2163 : Kaydedilen gelen evrakın güncellenmesi")
    public void TC2163() throws InterruptedException {

        String evrakTuru = "D";
        String ustYaziPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\Otomasyon.pdf";
        String ustYaziAdi = "Otomasyon.pdf";
        String basariMesaji = "İşlem başarılıdır!";
        String aciklama = "Test Otomasyon";
        String evrakTarihi = getSysDateForKis();
        String evrakTuru2 = "Dilekçe";

// TC0321 de oluşturulan evrak no burada kullanılacak.

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec("5140"); // sırayla çalışma yapıldığında evrakNO321 parametre olarak eklenecek

        String evrakNo = gelenEvrakKayitPage
                .evrakDetayiEvrakNoTextAl();

        gelenEvrakKayitPage
                .evrakDetaylariAlanGuncellenebilirlikKontrolü()
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .evrakDetayiPdfDegisiklikpopUpClose()
//                .ustYaziPdfAdiKontrol(ustYaziAdi)
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .evrakDetayiEkleriTab()
                .evrakDetayiFizikselEkEkleTab()
                .evrakDetayiAciklamaDoldur(aciklama)
                .evrakDetayiEkle()
//                .dosyaEkleTabTabloKontrolu("Ek-3") // TC0321 deki evrak no kullanıldığında bu satır aktif olarak kullanılabilir.
                .evrakDetayiKaydet()
                .evrakDetayiKaydetPopUpClose()
                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec(evrakNo);

        gelenEvrakKayitPage
                .guncellenenAlanKontrolleri(evrakTarihi, evrakTuru, gizlilikDerecesi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0328 : Gelen evrak kayıt ekranından havale")
    public void TC0328() throws InterruptedException {

        String birim = "OPTİİM BİRİM";

        gelenEvrakKayitPage
                .openPage()
//                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf.pdf")
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur(birim)
                .kaydet();
        evrakNO328 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().isBasarili();

        birimHavaleEdilenlerPage
                .openPage()
                .filtreleAc()
                .baslangiçTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNO328);

        //TeslimAlınanBelgeler sayfasında yetkili bir kullanıcı ile giriş yapılacak.
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .filtreleAc()
                .tarihiDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNO328);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC0321", "TC0328"}, description = "TC1401 : Kaydedilen Gelen Evrak raporu")
    public void TC1401() throws InterruptedException, IOException {

        String basariMesaji = "İşlem başarılıdır!";
        String evrakNo = evrakNO321;
        String evrakNo1 = evrakNO328;
        String geldigiYer = "D";
// Testin öncesinde TC0321 ve TC0328 caselerinin çalışması gerekli..

        kaydedilenGelenEvrakPage
                .openPage()
                .gelenEvrakNoDoldur(evrakNO321)
                .sorgula()
                .tabloKontrolu(evrakNO328)
                .raporAlExcel();
//                .islemMesaji().basariliOlmali(basariMesaji);

        kaydedilenGelenEvrakPage
                .txtClear()
                .gelenEvrakNoDoldur(evrakNO328)
                .sorgula()
                .geldigiYerSec(geldigiYer)
                .sorgula()
                .tabloKontrolu(evrakNO328)
                .raporAlPdf();
//                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Gelen evrak kaydederken yeni gerçek ve tüzel kişi tanımlama")
    public void TC1136() throws InterruptedException {

        String TCKN = "51091330934";
        String ad = "Test";
        String soyad = "Otomasyon";
        String kisiKurum = "G";

        String mernisNo = createMernisTCKN();

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec(kisiKurum)
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
    @Test(enabled = true,dependsOnMethods = {"TC0328"}, description = "Gelen maillerin evrak olarak sisteme dahil edilmesi")
    public void TC0394() throws InterruptedException {

        String birim = "OPTİİM BİRİM11";

        String pathToFilePdf = "C:\\TestAutomation2\\BelgenetFTA\\documents\\TestOtomasyon.msg";
        String pathToFileExcel = "C:\\TestAutomation2\\BelgenetFTA\\documents\\test.xlsx";
        String ustYaziAdi = "TestOtomasyon.msg";
//        String ustYaziAdi = "ustYazi.pdf";  // TestOtomasyon.msg ekiini eklememe rağmen ustYazi.pdf  olarak ekrana geliyor.
        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziMailAdiKontrol(ustYaziAdi)
                .konuKoduDoldur(konuKodu)
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
                .ustYaziDegistirilmisPopUpKontrol()
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkleriDosyaEkle()
//                .dosyaEkleTabTabloKontrolu("Ek-2") //Webservise  baglanılamadı hatası alnıyor.
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
//                .dosyaEkleTabTabloKontrolu("Ek-3")
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();

        kaydedilenGelenEvraklarPage
                .openPage()
                .filtreleAc()
//                .tarihDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Gelen evrak kayıtta alan kontrolleri")
    public void TC0322() throws InterruptedException {

        String kisiKurum = "G";
        String kisiKurum1 = "D";
        String geldigiKurum = "Cumhurbaşkanlığı";
        String solAlan = "24301012-";
        String evrakTuru = "Dilekçe";
        String ivedilik = "G";
        String evrakGelisTipi = "P";
        String message = "Zorunlu alanları doldurunuz";
        String message2 = "Dosya büyüklüğü uygun değildir.";
        String evrakTuru1 = "A";
        String path = "C:\\Users\\Emre_Sencan\\Pictures\\tsunami_posteroct08.pdf";
        String ustYaziPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\pdf.pdf";
        String birim = "OPTİİM BİRİM";

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec(kisiKurum)
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
                .islemMesaji().beklenenMesaj(message);

        gelenEvrakKayitPage
                .miatDoldur(getSysDateForKis())
                .konuKoduSil()
                .kaydet()
                .islemMesaji().beklenenMesaj(message);

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .evrakTarihiSil()
                .kaydet()
                .islemMesaji().beklenenMesaj(message);

        gelenEvrakKayitPage
                .evrakTarihiDoldur(evrakTarihi)
                .evrakSayiSagSil()
                .kaydet()
                .islemMesaji().beklenenMesaj(message);

        gelenEvrakKayitPage
                .evrakTuruSec(evrakTuru1);
//        250 mb pdf yuklerken timeouta düşüyor....
//                .kaydet()
//                .evrakBilgileriUstYaziEkle(path)
//                .islemMesaji().beklenenMesaj(message2);

        gelenEvrakKayitPage
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .kaydet()
                .popUpKontrol()
                .dagitimBilgileriBirimDoldur(birim)
                .kaydet()
                .popUps();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Giden evrak kaydı")
    public void TC1340() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String ustYaziPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\pdf.pdf";
        String excelPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\test.xlsx";
        String ustYaziAdi = "pdf.pdf";
        String excelAdi = "test.xlsx";
        String miatTarihi = getSysDateForKis();

        gidenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .ustYaziPdfAdiKontrol(ustYaziAdi)
                .islemMesaji().isBasarili();

        gidenEvrakKayitPage
                .evrakTuruSec(evrakTuru)
                .ivedilikSec(ivedilik)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .konuKoduDoldur(konuKodu)
                .evrakDiliSec(evrakDili)
                .miatDoldur(miatTarihi)
                .geregiDoldur("AFYON VALİLİĞİ")
                .kaldiralacakKlasorDoldur("ESK05")
                .bilgiDoldur("TAŞRA TEŞKİLATI")
                .evrakTarihiDoldur("16.02.2017")

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
                .islemMesaji().isBasarili();

        Thread.sleep(2000);

        kaydedilenGidenEvraklarPage
                .openPage()
//                .filtreleAc()
//                .tarihDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNo1340);
    }
}
