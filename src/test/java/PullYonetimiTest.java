import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageData.MesajTipi;
import pageData.SolMenuData;

import static pageData.MesajTipi.*;
import static pageData.SolMenuData.*;

@Epic("Belgenet1Epic examples")
public class PullYonetimiTest extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC9999: Yeni Pul Oluştur Testi")
    public void TC2215() {
        page.ustMenuAc("Pul Yönetimi");

        page.pulYonetimiPage()
                .yeniPulEkle()
                .postaTipiSec("KEP")
                .gramajiDoldur("12")
                .tutariDoldur("10")
                .yurtDisiSec(true)
                .indirimOraniDoldur("10")
                .kaydet();
        page.islemMesaji().beklenenMesajTipi(BASARILI);
    }

    @Test(description = "Sol Memu açılması")
    public void solMenuTest() {
        page.solMenu(Bildirimler.Mesajlar);
    }
}
