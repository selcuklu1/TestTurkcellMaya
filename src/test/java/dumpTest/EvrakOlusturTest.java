package dumpTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PulYonetimiPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class EvrakOlusturTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    @BeforeMethod
    public void setUp() throws Exception {
//        WebDriver driver = new FirefoxDriver();
//        driver.get("https://www.google.com/");
//        login();
//        evrakOlusturPage = new EvrakOlusturPage();
//        evrakOlusturPage.open().bilgilerTabiAc();
        login();
    }

    public void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        executeJavaScript(script, element);
    }

    @Test
    public void solMenuTest() throws Exception {
        new MainPage().solMenu(SolMenuData.KurulIslemleri.KararIzleme);
    }

    @Test
    public void testName() throws Exception {
        new EvrakOlusturPage().openPage().bilgilerTabiAc();

        BelgenetElement el = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"))
                .selectLov("optiim").lastSelectedLov();

        ElementsCollection col = el.titleItems();
        int q = col.size();

        String a1 =  comboLov("input[id$='konuKoduLov:LovText']").lastSelectedLov().text();
        String a = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"))
                .selectLov("optiim").lastSelectedLov().text();
        String b = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText")).selectLov("optiim")
                .lastSelectedLov().text();
//String c = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText")).selectLov("optiim")
//        .

        System.out.println("!!!!!!-" + comboLov("input[id$='konuKoduLov:LovText']").lastSelectedLov());
//                .selectLov("010.01").lastSelectedLovTitle();

        $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:onayAkisiEkle")).click();
        comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovText"))
                .type("Optiim TEST").titleItems().filterBy(text("Optiim TEST3")).get(0).click();
//                .selectLov("Optiim TEST3");

        new EvrakOlusturPage().openPage().bilgilerTabiAc()
                .konuKoduSec("010.01");
        new EvrakOlusturPage().openPage().bilgilerTabiAc();

//        boolean b = comboLov("input[id$='konuKoduLov:LovText']").type("111111111").isEmpty();
        int i = comboLov("input[id$='konuKoduLov:LovText']").type("010.01")
                .titleItems().filterBy(text("Kanunlar")).size();



//        new UstMenu().ustMenu("Evrak İşlemleri", "Evrak Oluştur");
        new PulYonetimiPage().openPage();


//        $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).click();
        SelenideElement element = $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"));
//        element.sendKeys("\n");
        element.click();
//        element.pressEnter();
//        $(By.id("topMenuForm2:ust:0:ustMenuEleman")).pressEnter();
//        $(By.id("pulYonetimiEditorForm:savePulButton")).sendKeys("\n");
////        Dimension size = element.getSize();
//        Actions actions = new Actions(WebDriverRunner.getWebDriver());
//
//        actions.moveToElement(element).click(element).build().perform();
////        actions.moveToElement(element, size.getWidth() - 1, size.getHeight() - 1)
////                .click().build().perform();
////        clickOnInvisibleElement($(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")));
//
//
//
////        new Actions(WebDriverRunner.getWebDriver())
////                .moveToElement($(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"))
////                        , $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).getSize().getWidth() - 1
////                        , $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).getSize().getHeight() - 1)
////                .click().build().perform();
//
//
//        //        group.$(By.partialLinkText(menuText)).click();
////
////        System.out.println("Text: " + $("button[id='topMenuForm:userMenuButton_button']").text());
////        System.out.println("InnerText: " + $("button[id='topMenuForm:userMenuButton_button']").innerText());
////        takeScreenshot();*/
    }

    @Test(enabled = false, dataProvider = "zorunluAlanlar")
    public void test1(String fieldName, Object field) {
//        if (field instanceof BelgenetElement) {
//            BelgenetElement dog = (BelgenetElement) field;
//        }
//        else{
        SelenideElement dog = (SelenideElement) field;
//        }


//        System.out.println("!!!!!!!!-" + field.toString());
        dog.shouldBe(BelgentCondition.required);

    }

    @DataProvider
    public Object[][] zorunluAlanlar1() {
        return new Object[][]{
//                {"Konu Kodu", tabBilgiler.getCmlKonuKodu()}
//                , {"Konu", tabBilgiler.getTxtKonu()}
        };
    }
}
