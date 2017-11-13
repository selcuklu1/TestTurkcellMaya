import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Belgenet1Epic examples")

public class YonetimHavuzuIslemleri extends BaseTest {

    BasePage page = new BasePage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC02() {
        page.ustMenuAc("Kullanıcı İşlemleri","Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .YonetimHavuzuEkle()
                .YonetimHavuzuAdiDoldur("Yeni Optiim Havuzu2")
                .KullananBirimiEkle()
                .KullananBirimSec("OPTİİM BİRİM")
                .KullananBirimKaydet()
                .KullaniciEkle()
                .KullaniciSec("Optiim OPTİİM")
                .KullaniciKaydet()
                .YonetimHavuzuKaydet();
    }


}
