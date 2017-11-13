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
public class Paylastiklarim extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void paylastiklarimSec() {
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

    @Test()
    public void TC1() {
        System.out.println("Sezai");
        System.out.println("Ilyas");
        System.out.println("Sezai2");
        System.out.println("Sezai3");
        System.out.println("Sezai4");
        System.out.println("Sezai5");
        toplama(3,4);
        toplama(3,5);
    }

    private void toplama(int i, int i1) {
        int toplam = i + i1;
    }

    public void aaa(){
        int i= 0;
        int x = 5;
    }
}
