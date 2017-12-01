package GelenGidenEvrakKayit;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.BirimHavaleEdilenlerPage;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GercekKisiYonetimPage;
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
        login("optiim", "Avis1111");
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
                .ustYaziDegistirilmisPopUpKontrol()
                .evrakEkleriDosyaEkleDosyaAdiKontrol(excelAdi)
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
                .filtreleAc()
                .tarihDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNO321);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2163 : Kaydedilen gelen evrakın güncellenmesi")
    public void TC2163() throws InterruptedException {

        String evrakTuru = "D";
        String ustYaziPath = "C:\\TestAutomation2\\BelgenetFTA\\documents\\Otomasyon.pdf";
        String ustYaziAdi = "Otomasyon.pdf";
        String basariMesaji = "İşlem başarılıdır!";
        String aciklama = "Test Otomasyon";
        String evrakTarihi = getSysDateForKis();
        String evrakTuru2 = "Dilekçe";

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloRaporIcerik("5012");

        String evrakNo = gelenEvrakKayitPage
                .evrakDetayiEvrakNoTextAl();

        gelenEvrakKayitPage
                .evrakDetaylariAlanGuncellenebilirlikKontrolü()
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .evrakDetayiPdfDegisiklikpopUpClose()
                .ustYaziPdfAdiKontrol(ustYaziAdi)
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
    @Test(enabled = true, description = "TC1401 : Kaydedilen Gelen Evrak raporu")
    public void TC1401() throws InterruptedException, IOException {

        String basariMesaji = "İşlem başarılıdır!";
        String evrakNo = evrakNO321;
        String evrakNo1 = evrakNO328;
        String geldigiYer = "D";

        kaydedilenGelenEvrakPage
                .openPage()
                .gelenEvrakNoDoldur("4998")
                .sorgula()
                .tabloKontrolu("4998")
                .raporAlExcel();
//                .islemMesaji().basariliOlmali(basariMesaji);
        Thread.sleep(3000);
        kaydedilenGelenEvrakPage
                .txtClear()
                .gelenEvrakNoDoldur("4999")
                .sorgula()
                .geldigiYerSec(geldigiYer)
                .sorgula()
                .tabloKontrolu("4999")
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
    @Test(enabled = true, description = "Gelen maillerin evrak olarak sisteme dahil edilmesi")
    public void TC0394() throws InterruptedException {

        String birim = "OPTİİM BİRİM11";

        String pathToFilePdf = "C:\\TestAutomation2\\BelgenetFTA\\documents\\TestOtomasyon.msg";
        String pathToFileExcel = "C:\\TestAutomation2\\BelgenetFTA\\documents\\test.xlsx";
        String ustYaziAdi = "TestOtomasyon.msg";
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
                .evrakEkleriDosyaEkleEkMetinDoldur(ekMetni)
                .evrakEkleriDosyaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-1") //Webservise  baglanılamadı hatası alnıyor.
                .ekBilgiFizikselEkEkle()
                .evrakEkTabFizikselEkMetniDoldur(ekMetni)
                .fizikselEkTabViewAciklamaEkle()
                .dosyaEkleTabTabloKontrolu("Ek-2")
                .kaydet();
        String evrakNo = gelenEvrakKayitPage.popUps();
        //   page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        //  page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
       kaydedilenGelenEvraklarPage
                .openPage()
                .filtreleAc()
                .tarihDoldur(getSysDateForKis())
                .tabloKontrolu(evrakNo);
    }
}
