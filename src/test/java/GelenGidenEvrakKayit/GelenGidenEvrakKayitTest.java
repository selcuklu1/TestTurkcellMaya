package GelenGidenEvrakKayit;


import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import page.birimEvralklari.KaydedilenGelenEvraklarPage;
import page.birimEvralklari.TeslimAlinmayiBekleyenlerPage;
import page.gelenEvrak.GelenEvrakKayitPage;
import pageComponents.BasePage;
import pageData.SolMenuData;

public class GelenGidenEvrakKayitTest extends BaseTest {

    BasePage page;
    LoginPage loginPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklar;

    String konuKodu = "010.01";
    String evrakTuru = "R";
    String evrakDili = "917";
    String evrakTarihi = "16.11.2017";
    String gizlilikDerecesi = "N";
    String kisiKurum = "D";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakSayiSag = "12365";
    String evrakGelisTipi = "P";
    String ivedilik= "N";
    String ekMetni = "test otomasyon";

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        loginPage.login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321() throws InterruptedException {

        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
//                .evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldur(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                //Excel eklenecek
                .evrakBilgileriEkBilgiFiltreAc()
                .evrakBilgileriEkBilgiFizikselEkEkle()
                .evrakEkTabViewFizikselEkMetniDoldur(ekMetni)
                .evrakFizikselEkTabViewAciklamaEkle()
                .kaydet()
                .popUps();
//        page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);

        kaydedilenGelenEvraklar.raporSec();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Gelen evrak kayıt ekranından havale")
    public void TC0328 () throws InterruptedException{
        String birim = "OPTİİM BİRİM11";
        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
//                .evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldur(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur(evrakSayiSag)
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur(birim)
                .kaydet()
                .popUps();
//        page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
        page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);

        new TeslimAlinmayiBekleyenlerPage().raporSec();
    }
}
