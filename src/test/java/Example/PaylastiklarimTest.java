package Example;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.IslemMesajlari;
import pages.solMenuPages.PaylastiklarimPage;

import static pages.pageData.SolMenuData.IslemYaptiklarim;

@Epic("Belgenet1Epic examples")
public class PaylastiklarimTest extends BaseTest {
/*

    MainPage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new MainPage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void paylastiklarimSec() {
        page.solMenu(IslemYaptiklarim.Paylastiklarim);
        new PaylastiklarimPage()
                .satirSec(0)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasTabTikla()
                .kisiSec("Optiim TEST1")
                .kisiSec("Optiim TEST2")
                .kisiSec("Optiim TEST3")
                .aciklamaYaz("optiim test 1 aciklama")
                .paylas()
                .paylasimKontrol(new String[]{"Optiim TEST1", "Optiim TEST2", "Optiim TEST3"});
        page.islemMesaji().beklenenMesajTipi(IslemMesajlari.MessageTitle.BASARILI);
    }
*/

}
