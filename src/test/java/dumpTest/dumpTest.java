package dumpTest;

import com.codeborne.selenide.*;
import common.BaseTest;
import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.*;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.EvrakHavaleKurallariYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KlasorYonetimiPage;
import pages.ustMenuPages.PulYonetimiPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


public class dumpTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    public void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        executeJavaScript(script, element);
    }


    @BeforeMethod
    public void caps() throws Exception {
       /* DesiredCapabilities caps;
        if (Configuration.browser.equals("chrome")){
            caps = DesiredCapabilities.chrome();
            caps.setPlatform(Platform.MAC);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory",  TestData.docDownloadPathLinux);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
        }*/

       Configuration.timeout = 5000;
       Configuration.collectionsTimeout = 5000;
    }

    @Test(description = "", enabled = true)
    public void sorgulamaVefiltremeDeneme() throws Exception {
        login();


    }

    @Test(description = "", enabled = true)
    public void findFileInPath() throws Exception {

        Selenide.open("http://fiyat.mercedes-benz.com.tr/a-serisi");

//        RemoteWebDriver driver = (RemoteWebDriver) ((HasCapabilities)((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver());


        String href = $("a[class='btn downloadpdf']").attr("href");
       /* $("a[class='btn downloadpdf']").click();

        getDocPath();
        login();*/


//        if (capabilities.getBrowserName().equals("firefox")){
//            FirefoxProfile profile = new FirefoxProfile();
           /* FirefoxOptions options =  new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", getDownoladPath());*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser.download.folderList", 2);
        capabilities.setCapability("browser.download.dir", TestData.docDownloadPathLinux);

        Capabilities caps = ((HasCapabilities) ((EventFiringWebDriver) WebDriverRunner.getWebDriver())
                .getWrappedDriver())
                .getCapabilities().merge(capabilities);
            /*FirefoxDriver driver = (FirefoxDriver) ((HasCapabilities)((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver());
            driver = new FirefoxDriver(options);*/

//            WebDriverRunner.setWebDriver(d);
//        }

        /*profile['browser.download.folderList'] = 2 # custom location
        profile['browser.download.dir'] = download_directory
        profile['browser.helperApps.neverAsk.saveToDisk'] = "text/csv,application/pdf"*/


//        File file = new File(filePath);
//        Path path = file.toPath();
//        Files.find(path, 1, (dir, basicFileAttributes) -> path.toFile().getName().matches(".*." + ext));
    }

    @Test
    public void solMenuTest() throws Exception {
        new MainPage().solMenu(SolMenuData.KurulIslemleri.KararIzleme);
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
