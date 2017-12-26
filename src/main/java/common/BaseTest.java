package common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.User;
import io.qameta.allure.Step;
import listeners.SettingsListener;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.util.Locale;

import static data.TestData.belgenetURL;

//BrowserPerTest.class
@Listeners({SettingsListener.class})
public class BaseTest extends BaseLibrary {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        //killProcess();
    }

    @BeforeClass(alwaysRun = true)
    public void driverSetUp() {

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        BelgenetFramework.setUp();

        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;
        Configuration.browser = "chrome";
       //Configuration.browser = "firefox";

        // Configuration.remote = "http://10.101.20.151:4444/wd/hub";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 40000;
        Configuration.timeout = 40000;
        //Configuration.clickViaJs = true;
        //Configuration.holdBrowserOpen = true;
        //Configuration.headless = false;

        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 100;
        //Configuration.closeBrowserTimeoutMs = 34000;
        //Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";

        Configuration.baseUrl = belgenetURL;
        //endregion

        // System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        setDocPath();
        getBrowserName();

        log.info("Selenide/Selenium driver has been set up.");

    }

    @AfterMethod (alwaysRun = true)
    public void afterMethod() {
        Selenide.close();
        log.info("Driver has been quit.");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Selenide.close();
        log.info("Browser has been closed.");
    }

    public void clearCookies() throws Exception {
       // Selenide.close();
        try {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
        } catch (Exception e) {
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        //killProcess();
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
