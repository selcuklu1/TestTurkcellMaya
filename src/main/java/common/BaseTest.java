package common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import data.User;
import io.qameta.allure.Step;
import listeners.SettingsListener;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.io.File;
import java.util.Locale;

import static data.TestData.belgenetURL;


//BrowserPerTest.class
@Listeners({SettingsListener.class})
public class BaseTest extends BaseLibrary {

    @BeforeSuite
    public void beforeSuite() {

        //killProcess();
    }

    @BeforeClass(alwaysRun = true)
    public void driverSetUp() {

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        //region SetUp BelgenetFramework for BelgenetElements usage
        BelgenetFramework.setUp();
        //endregion

//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver");
        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;
//        Configuration.browser = "chrome";
//         Configuration.browser = "drivers.Firefox";
//        Configuration.browser = "marionette";
//        Configuration.browser = "gecko";

        //Configuration.remote = "http://192.168.1.3:6570/wd/hub";
        //Configuration.remote = "http://10.101.20.153:4444/wd/hub";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout =40000;
        Configuration.timeout = 40000;
//        Configuration.clickViaJs = true;
//        Configuration.holdBrowserOpen = true;
//        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 100;
//        Configuration.closeBrowserTimeoutMs = 34000;
//        Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";
        //endregion

//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

    }

    @AfterMethod
    public void tearDown() throws Exception {
//        Selenide.close();
//        try {
//            Selenide.clearBrowserLocalStorage();
//            Selenide.clearBrowserCookies();
//        } catch (Exception e) {
//        }
    }

    @Step("Login")
    public void login(User user) {
        new LoginPage().login(user.getUsername(), user.getPassword());
    }

    @Step("Login")
    public void login() {
        new LoginPage().login();
    }

    @Step("Login")
    public void login(String username, String password) {
        new LoginPage().login(username, password);
    }

    @Step("Logout")
    public void logout() {
        new MainPage().logout();
    }

}
