import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Test;

public class TestClass3 extends BaseTest {

    BasePage page = new BasePage();

    @Test
    public void testName() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
        page.evrakOlusturPage().konuKoduDoldur("010.10");
    }

    @Test
    public void testName2() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
    }

    @Test
    public void testName3() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
    }
}
