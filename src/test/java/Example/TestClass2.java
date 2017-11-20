package Example;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.PulYonetimiPage;

import static pages.pageComponents.IslemMesajlari.MessageTitle.*;
import static pages.pageData.SolMenuData.Bildirimler;

@Epic("Belgenet1Epic examples")
public class TestClass2 extends BaseTest {
/*
    MainPage page = new MainPage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC9999: Yeni Pul Oluştur Testi")
    public void yeniPulYonetimi() {
        page.ustMenu("Pul Yönetimi");

        new PulYonetimiPage()
                .yeniPulEkle()
                .postaTipiSec("KEP")
                .gramajiDoldur("12")
                .tutariDoldur("10")
                .yurtDisiSec(true)
                .indirimOraniDoldur("10")
                .kaydet();
       page.islemMesaji().beklenenMesajTipi(BASARILI);
    }

    public void TC2yeniPulYonetimi() {
        page.ustMenu("Pul Yönetimi");

        new PulYonetimiPage()
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
    }*/
}
