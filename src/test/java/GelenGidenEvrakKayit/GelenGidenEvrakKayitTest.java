package GelenGidenEvrakKayit;

import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.BirimEvraklariKaydedilenGelenEvraklar;
import pageData.SolMenuData;

public class GelenGidenEvrakKayitTest extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void TC0321 () throws InterruptedException {

        page.ustMenuAc("Gelen Evrak Kayıt");
        page.gelenEvrakKayitPage()
                .evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf")
                .evrakBilgileriListKonuKoduDoldur("010.01")
                .evrakBilgileriListEvrakTuruSec("D")
                .evrakBilgileriListEvrakDiliSec("917")
                .evrakBilgileriListEvrakTarihiDoldur("16.11.2017")
                .evrakBilgileriListGizlilikDerecesiSec("N")
                .evrakBilgileriListKisiKurumSec("D")
                .evrakBilgileriListGeldigiKurumDoldur("Esk Kurum 071216 2")
                .evrakBilgileriListEvrakSayiSagDoldur("12365")
                .evrakBilgileriListEvrakGelisTipiSec("P")
                .evrakBilgileriListIvedilikSec("N")
                //Excel eklenecek
                .evrakBilgileriEkBilgiFiltreAc()
                .evrakBilgileriEkBilgiFizikselEkEkle()
                .evrakEkTabViewFizikselEkMetniDoldur("test otomasyon")
                .evrakFizikselEkTabViewAciklamaEkle()
                .kaydet();
                //popup ta evet tıklanacak
page.solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
page.kaydedilenGelenEvraklar()
        .raporSec();

    }
}
