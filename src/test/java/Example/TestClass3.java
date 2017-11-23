package Example;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.util.logging.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TestClass3 extends BaseTest {

    private static final Logger log = Logger.getLogger(TestClass3.class.getName());

    @BeforeMethod
    public void loginBeforeTests() {
        login();
    }
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
        editor.sendKeys(Keys.SHIFT + "o");

    }

    @Test()
    public void goToNewWindow() throws InterruptedException {

        SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
        //SelenideElement btnPDFOnizleme = $(By.id("yeniGidenEvrakForm:rightTab:uiRepeat:0:cmdbutton"));
        SelenideElement title = $(By.xpath("/html/body/div[1]/div[2]/div[4]/div/div/div[2]/div[5]"));


        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        evrakOlusturPage
                .openPage()
                .openTab("Editör");

        //  Thread.sleep(5000);

        // Store the current window handle
        String winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
        Thread.sleep(4000);
        btnPDFOnizleme.click();
        Thread.sleep(5000);
        // Perform the click operation that opens new window
        // Switch to new window opened
        for (String winHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            WebDriverRunner.getWebDriver().switchTo().window(winHandle);
        }
        Thread.sleep(5000);
        String getTitle = title.shouldHave(visible).getText();
        System.out.println(getTitle);

        WebDriverRunner.getWebDriver().close();
        // driver.switchTo().defaultContent();
        WebDriverRunner.getWebDriver().switchTo().window(winHandleBefore);

        log.info("New window");
    }

}

