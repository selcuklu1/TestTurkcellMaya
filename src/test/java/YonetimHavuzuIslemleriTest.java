import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Belgenet1Epic examples")

public class YonetimHavuzuIslemleriTest extends BaseTest {

    BasePage page = new BasePage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC0002() {

        /*


        page.ustMenuAc("Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .yonetimHavuzuEkle()
                .yonetimHavuzuAdiDoldur("Testdeneme1122153")
                .kullananBirimiEkle()
                .kullananBirimSec("OPTİİM BİRİM")
                .kullananBirimKaydet()
                .kullaniciEkle()
                .kullaniciSec("Optiim TEST1")
                .kullaniciKaydet()
                .yonetimHavuzuKaydet();




         */

        page.ustMenuAc("Evrak Oluştur");
        page.evrakOlusturPage()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim OPTİİM", "Paraflama")
                .onayAkisiKullaniciSec("Optiim TEST1")
                .onayAkisiKullaniciTipiSec("Optiim TEST1", "İmzalama")
                .onayAkisiKullan();




    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0001")
    public void TC0001() {

        page.ustMenuAc("Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .ara();



    }
}
