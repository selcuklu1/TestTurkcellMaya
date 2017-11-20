package Example;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;

import static pages.pageData.SolMenuData.IslemBekleyenEvraklar;

@Feature("InParallel")
public class TestClass1 extends BaseTest {
/*
    MainPage page = new MainPage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Description("Evrak Oluştur JS ile aç")
    @Test(enabled = true)
    public void birimHavaleEdilenler() {
        page.solMenu(IslemBekleyenEvraklar.GelenEvraklar);
    }

    *//*@Test(enabled = true)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Evrak Oluştur aç")
    public void evrakOlustur() {
        pages.ustMenuAc("Evrak Oluştur");
    }

    @Test(enabled = true)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Yeni Pul Yönetimi Fail Testi")
    public void yeniPulYonetimi() {
        pages.ustMenuAc("Pul Yönetimi");

        pages.pulYonetimiPage()
                .yeniPulEkle()
                .postaTipiSec("KEP")
                .gramajiDoldur("1")
                .yurtDisiSec(true)
                .kaydet();
        pages.islemMesaji().beklenenMesajTipi(MessageTitle.UYARI);
    }

    @Test
    @Description("Konu Kodu seç")
    public void konuKoduDoldur() {
        pages.ustMenuAc("Evrak Oluştur");
        pages.evrakOlusturPage().konuKoduDoldur("010.10");
    }*/
}
