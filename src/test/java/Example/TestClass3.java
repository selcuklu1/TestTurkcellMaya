package Example;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Selenide.$;

public class TestClass3 extends BaseTest {
/*
    MainPage page = new MainPage();

    @Test
    public void testName() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
        new EvrakOlusturPage().konuKoduDoldur("010.10");
    }

    @Test
    public void testName2() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
    }

    @Test
    public void testName3() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
    }


    @Test
    public void ustYaziFileUpload() {
        page.loginPage().login();
        page.ustMenu("Gelen Evrak Kayıt");
        new GelenEvrakKayitPage().ustYaziUploadFile("C:\\TestAutomation\\BelgenetFTA\\testpdf.pdf");
    }*/

    @Test()
    public void pdfKisayolKontrol() throws InterruptedException {

        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        evrakOlusturPage
                .openPage()
                .openTab("Editör");

        SelenideElement editor = $(By.id("yeniGidenEvrakForm:allPanels"));
        Thread.sleep(5000);
        editor.click();
        editor.sendKeys(Keys.SHIFT+"o");

    }

}
