import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GelenGidenEvrakKayit extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Üstyazı ek ve ilgi eklenerek gelen evrak kaydı")
    public void ustyaziEkveIlgiEklenerekGelenEvrakKaydi() throws InterruptedException {

        page.ustMenuAc("Gelen Evrak Kayıt");
        page.gelenEvrakKayitPage()
                //.evrakBilgileriUstYaziEkle("C:/Users/Emre_Sencan/Pictures/pdf2.pdf") üst yazı ekle butonu tıklanacak
                .evrakBilgileriListKonuKoduDoldur("010.01")
                .evrakBilgileriListEvrakTuruSec("D")
                .evrakBilgileriListEvrakDiliSec("917")
                .evrakBilgileriListEvrakTarihiDoldur("16.11.2017")
                .evrakBilgileriListGizlilikDerecesiSec("N")
                .evrakBilgileriListKisiKurumSec("D")
                .evrakBilgileriListGeldigiKurumDoldur("Esk Kurum 071216 2")
                .evrakBilgileriListEvrakSayiSagDoldur("12365")
                .evrakBilgileriListEvrakGelisTipiSec("P")
                .evrakBilgileriListIvedilikSec("N");
                //Excel eklenecek
    }
}
