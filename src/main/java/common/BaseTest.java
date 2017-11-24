package common;

import com.codeborne.selenide.Configuration;
import listeners.SettingsListener;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.util.Locale;

import static data.TestData.belgenetURL;


@Listeners({SettingsListener.class})
public class BaseTest extends BaseLibrary {

    @BeforeClass
    public void driverSetUp() {

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);
        
        //region SetUp BelgenetFramework for BelgenetElements usage
        BelgenetFramework.setUp();
        //endregion

        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;

        //Configuration.browser = "drivers.Firefox";

        //org.openqa.selenium.chrome.FirefoxDriver;

//        Configuration.remote = "http://0.0.0.0:32768/wd/hub";
//        Configuration.browser = "chrome";

        //Configuration.browser = "marionette";

        Configuration.browser = "chrome";



        //"org.openqa.selenium.Firefox.FirefoxDriver";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 20000;
        Configuration.timeout = 20000;


//        Configuration.startMaximized = true;
//        Configuration.headless = true;
//        Configuration.browserSize = "1024x600";
        //endregion


        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");


        Configuration.holdBrowserOpen = true;

        Configuration.startMaximized = true;
        // Configuration.headless = true;
        //Configuration.browserSize = "1024x600";
        //endregion

    }

    public void login() {
        new LoginPage().login();
    }

    public void login(String username, String password) {
        new LoginPage().login(username, password);
    }

    public void logout() {
        new MainPage().logout();
    }

}
