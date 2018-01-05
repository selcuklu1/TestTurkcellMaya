package common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import data.User;
import io.qameta.allure.Step;
import listeners.SettingsListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.util.Locale;

import static data.TestData.belgenetURL;

//BrowserPerTest.class
@Listeners({SettingsListener.class})//, BrowserPerTest.class})
public class BaseTest extends BaseLibrary {


    @BeforeClass(alwaysRun = true)
    public void driverSetUp() {

        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        BelgenetFramework.setUp();

        //Configuration.remote = "http://10.101.20.151:4444/wd/hub";
        //Configuration.remote = "http://localhost:4444/wd/hub";

        Configuration.baseUrl = (System.getProperty("URL") == null) ? belgenetURL : System.getProperty("URL");
        Configuration.browser = (System.getProperty("browser") == null) ? "chrome" : System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("node");
        Configuration.remote = System.getProperty("hub");


        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        Configuration.collectionsTimeout = 30 * 1000;
        Configuration.timeout = 30 * 1000;
        setWaitForLoading(20);
        //Configuration.clickViaJs = true;
//        Configuration.holdBrowserOpen = true;
        //Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 100;
        //Configuration.closeBrowserTimeoutMs = 34000;
        //Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";
        //endregion

        // System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
//      getBrowserName();

        log.info("remote: " + Configuration.remote);
        log.info("browser: " + Configuration.browser);
        log.info("url: " + Configuration.baseUrl);
        log.info("Doc path: " + getDocPath());
        log.info("Download path: " + getDownoladPath());
        log.info("Selenide/Selenium driver has been set up.");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult testResult) {
        int SUCCESS = 1;
        int FAILURE = 2;
        int SKIP = 3;
        int SUCCESS_PERCENTAGE_FAILURE = 4;
        int STARTED= 16;
        String result = "unknown";
        switch (testResult.getStatus()){
            case 1 : result = "SUCCESS"; break;
            case 2 : result = "FAILURE"; break;
            case 3 : result = "SKIP"; break;
            case 4 : result = "SUCCESS_PERCENTAGE_FAILURE"; break;
            case 16 : result = "STARTED"; break;
        }

        if (testResult.getStatus() == ITestResult.FAILURE)
            takeScreenshot();

        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test " + result + ": " + testResult.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }

    /*@AfterMethod(alwaysRun = true)
    public void afterMethod() {
        *//*try {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
        } catch (Exception e) {
            log.info("Error clearBrowserLocalStorage and clearBrowserCookies: " + e.getMessage());
        }*//*
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
//        Selenide.close();
//        log.info("Browser has been closed.");
    }*/

    @Step("Login")
    public void login(User user) {
        LoginPage loginPage = new LoginPage().login(user.getUsername(), user.getPassword());
        if (!user.getBirimAdi().isEmpty() && user.getBirimAdi() != null)
            loginPage.birimSec(Condition.text(user.getBirimAdi()));
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
