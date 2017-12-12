package common;

import com.codeborne.selenide.Configuration;
import listeners.SettingsListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static data.TestData.belgenetURL;


@Listeners({SettingsListener.class})
public class BaseTest extends BaseLibrary {

    @BeforeClass
    public void driverSetUp() {

//        killProcess();

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        //region SetUp BelgenetFramework for BelgenetElements usage
        BelgenetFramework.setUp();
        //endregion

        //region Selenide Driver Configuration
//        Configuration.remote = "http://0.0.0.0:32769/wd/hub";
        Configuration.baseUrl = belgenetURL;
        Configuration.browser = "chrome";
//        Configuration.browser = "drivers.Firefox";
//        Configuration.browser = "marionette";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 30000;
        Configuration.timeout = 30000;
//        Configuration.holdBrowserOpen = true;
//        Configuration.headless = false;
//        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 100;
//        Configuration.closeBrowserTimeoutMs = 34000;
//        Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";
        //endregion

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

    }

    @AfterMethod
    public void tearDown() {
        try {
            clearBrowserLocalStorage();
            clearBrowserCookies();
        } catch (Exception ignored) {
        }
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
