import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Belgenet1Epic examples")

public class YonetimHavuzuIslemleri extends BaseTest {

    BasePage page = new BasePage();

    @BeforeClass
    public void setUp() {
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC01() {
        page.loginPage().login();
        page.ustMenuAc("Kullanıcı İşlemleri","Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .YonetimHavuzuEkle()
                .YonetimHavuzuAdiDoldur("Yeni OPTiiM Havuzu")
                .KullananBirimiEkle()
                .KullananBirimSec("OPTİİM BİRİM")
                .KullananBirimKaydet()
                .KullaniciEkle()
                .KullaniciSec("Optiim OPTİİM")
                .KullaniciKaydet()
                .YonetimHavuzuKaydet();
    }


}
