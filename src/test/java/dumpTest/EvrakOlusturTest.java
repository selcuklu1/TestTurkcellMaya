package dumpTest;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EvrakOlusturPage;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.ustMenuPages.PulYonetimiPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class EvrakOlusturTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    @BeforeClass
    public void setUp() throws Exception {

//        login();
//        evrakOlusturPage = new EvrakOlusturPage();
//        evrakOlusturPage.open().bilgilerTabiAc();
    }

    public void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        executeJavaScript(script, element);
    }

    @Test
    public void testName() throws Exception {
        login();

        new EvrakOlusturPage().open().bilgilerTabiAc().konuKoduSec("010.01");


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
////        takeScreenshot();
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
