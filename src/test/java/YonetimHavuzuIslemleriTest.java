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
    public void TC02() {

        page.ustMenuAc("Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .yonetimHavuzuEkle()
                .yonetimHavuzuAdiDoldur("Testdeneme1")
                .kullananBirimiEkle()
                .kullananBirimSec("OPTİİM BİRİM")
                .kullananBirimKaydet()
                .kullaniciEkle()
                .kullaniciSec("Optiim TEST1")
                .kullaniciKaydet()
                .yonetimHavuzuKaydet();
    }


}
