package GelenGidenEvrakKayit;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.BirimHavaleEdilenlerPage;
import pages.ustMenuPages.KaydedilenGelenEvrakPage;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

public class GelenGidenEvrakKayitTest extends BaseTest {
    MainPage mainPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvrakPage kaydedilenGelenEvrakPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;

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
        login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0321: Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String ustYaziPath = "C:\\Users\\Emre_Sencan\\Pictures\\pdf.pdf";
        String excelPath = "C:\\Users\\Emre_Sencan\\Pictures\\test.xlsx";
        String ustYaziAdi = "pdf.pdf";
        String excelAdi = "test.xlsx";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(ustYaziPath)
                .evrakBilgileriDosyaEklemeUstYaziAdiKontrol(ustYaziAdi)
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur()
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .evrakBilgileriEkBilgiFiltreAc()
                .evrakBilgileriDosyaEkleme(excelPath)
                .evrakBilgileriDosyaEklemeDosyaAdiKontrol(excelAdi)
                .evrakBilgileriDosyaEklemeAciklamaDoldur(ekMetni)
                .evrakEkTabViewEkle()
                .evrakBilgileriEkBilgiFizikselEkEkle()
                .evrakEkTabViewFizikselEkMetniDoldur(ekMetni)
                .evrakFizikselEkTabViewAciklamaEkle()
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
    @Test(enabled = true, description = "Gelen evrak kayıt ekranından havale")
    public void TC0328() throws InterruptedException {

        String birim = "OPTİİM BİRİM11";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur()
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur("OPTİİM")
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
    @Test(enabled = false, description = "Kaydedilen Gelen Evrak raporu")
    public void TC1401() throws InterruptedException {


        String evrakNo = evrakNO321;
        String evrakNo1 = evrakNO328;
        String geldigiYer = "D";
        kaydedilenGelenEvrakPage
                .openPage()
                .gelenEvrakNoDoldur(evrakNo)
                .sorgula()
//                .raporAlExcel()
                .txtClear()
                .gelenEvrakNoDoldur(evrakNo1)
                .sorgula()
                .geldigiYerSec(geldigiYer)
                .sorgula();
//                .raporAlExcel();  pop upta ok butonuna basılacak
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Gelen evrak kaydederken yeni gerçek ve tüzel kişi tanımlama")
    public void TC1136() throws InterruptedException {

        String TCKN = "51091330934";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriListKisiKurumSec("G")
                .evrakBilgileriGeldigiKisiEkle()
                .IletisimBilgisiTCKNEkle(TCKN)
                .IletisimBilgisiTCKNAra()
//                .IletisimBilgisiAdDoldur("Test")
//                .IletisimBilgisiSoyadDoldur("Otomasyon")
                .IletisimBilgisikaydet();
//        Gerçek kişi yönetimi ekranında yeni kaydı kontrol et

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Gelen maillerin evrak olarak sisteme dahil edilmesi")
    public void TC0394() throws InterruptedException {

        String birim = "OPTİİM BİRİM11";

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\mailt.msg")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur()
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .evrakBilgileriEkBilgiFiltreAc()
                .evrakEkTabViewEkle()
                .evrakBilgileriDosyaEkleme("C:\\Users\\Emre_Sencan\\Pictures\\test.xlsx")
                .kaydet();
        String evrakNo = gelenEvrakKayitPage.popUps();
        //   page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        //  page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
//        TODO  tabloda oluşturulan evrak bulunacak....
        teslimAlinmayiBekleyenlerPage
                .raporSec();
    }
}
