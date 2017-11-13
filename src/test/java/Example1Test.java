import common.BasePage;
import common.BaseTest;
import org.testng.annotations.Test;

public class Example1Test extends BaseTest {

    BasePage page = new BasePage();

    @Test
    public void TC100() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
        page.evrakOlusturPage().konuKoduDoldur("010.10");
    }

    @Test
    public void TC101() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
    }

    @Test
    public void TC102() {
        page.loginPage().login();
        page.ustMenuAc("Evrak Oluştur");
    }
}
