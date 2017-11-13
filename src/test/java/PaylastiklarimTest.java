import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageData.MesajTipi;
import pageData.SolMenuData;

import static pageData.SolMenuData.*;

@Epic("Belgenet1Epic examples")
public class PaylastiklarimTest extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Pull Yönetimi TC2215")
    public void TC2215() {
        page.solMenu(IslemYaptiklarim.Paylastiklarim);
        page.paylastiklarimPage()
                .satirSec(0)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasTabTikla()
                .kisiSec("Optiim TEST1")
                .kisiSec("Optiim TEST2")
                .kisiSec("Optiim TEST3")
                .aciklamaYaz("optiim test 1 aciklama")
                .paylas()
                .paylasimKontrol(new String[]{"Optiim TEST1", "Optiim TEST2", "Optiim TEST3"});
        page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);
    }

    @Test(enabled = true, description = "TC1")
    public void TC1() {
        System.out.println("Sezai");
        System.out.println("Ilyas");
        System.out.println("Sezai4");
        System.out.println("Emre");
    }

}
