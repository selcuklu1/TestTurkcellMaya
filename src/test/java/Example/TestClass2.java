package Example;

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
public class TestClass2 extends BaseTest {

    BasePage page = new BasePage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC9999: Yeni Pul Oluştur Testi")
    public void yeniPulYonetimi() {
        page.ustMenuAc("Pul Yönetimi");

        page.pulYonetimiPage()
                .yeniPulEkle()
                .postaTipiSec("KEP")
                .gramajiDoldur("12")
                .tutariDoldur("10")
                .yurtDisiSec(true)
                .kaydet();
        page.islemMesaji().beklenenMesajTipi(BASARILI);
    }

    public void TC2yeniPulYonetimi() {
        page.ustMenuAc("Pul Yönetimi");

        page.pulYonetimiPage()
                .yeniPulEkle()
                .postaTipiSec("KEP")
                .gramajiDoldur("12")
                .tutariDoldur("10")
                .yurtDisiSec(true)
                .kaydet();
        page.islemMesaji().beklenenMesajTipi(BASARILI);
    }

    @Test(description = "Sol Memu açılması")
    public void solMenuTest() {
        page.solMenu(Bildirimler.Mesajlar);
    }
}
