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

import static data.TestData.belgenetURL;


@Listeners({SettingsListener.class})
public class BaseTest extends BaseLibrary {

    @BeforeClass
    public void driverSetUp() {
       // killProcess();

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        //region SetUp BelgenetFramework for BelgenetElements usage
        BelgenetFramework.setUp();
        //endregion

        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;
        Configuration.browser = "chrome";
        //Configuration.browser = "drivers.Firefox";
        //Configuration.browser = "marionette";

        //Configuration.remote = "http://10.101.20.153:4444/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 20000;
        Configuration.timeout = 20000;
        Configuration.holdBrowserOpen = true;
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 1000;
        Configuration.collectionsPollingInterval = 1000;
//        Configuration.closeBrowserTimeoutMs = 34000;
//        Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";
        //endregion

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

    }

    @AfterMethod
    public void tearDown() throws Exception {

//        Selenide.clearBrowserLocalStorage();
//        Selenide.clearBrowserCookies();

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
