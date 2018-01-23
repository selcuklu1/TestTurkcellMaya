package common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import listeners.DriverEventListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Locale;

import static data.TestData.belgenetURL;
import static io.qameta.allure.util.ResultsUtils.firstNonEmpty;

//BrowserPerTest.class
//@Listeners({SettingsListener.class})
//@Listeners({RerunFailedTests.class})
public class BaseTest extends BaseLibrary {

    //Seconds
    static final int timeout = 30;
    static final int loadingTimeout = 30;

    @BeforeClass(alwaysRun = true)
    public void driverSetUp() {
        System.out.println("file.encoding: " + String.format("file.encoding: %s", System.getProperty("file.encoding")));
        System.out.println("default charset=" + Charset.defaultCharset());
        System.out.println("java.specification.version" + System.getProperty("java.specification.version"));
        System.out.println("java.runtime.version" + System.getProperty("java.runtime.version"));
        System.out.println("locale default: " + Locale.getDefault());
        Locale turkishLocal = new Locale("tr", "TR");
        if (!Locale.getDefault().equals(turkishLocal))
            Locale.setDefault(turkishLocal);
        System.out.println("locale: " + Locale.getDefault());

        BelgenetFramework.setUp();
        WebDriverRunner.addListener(new DriverEventListener());

        //Configuration.remote = "http://localhost:4444/wd/hub";
        //Configuration.remote = "http://10.101.20.151:4444/wd/hub";

        Configuration.baseUrl = (System.getProperty("URL") == null) ? belgenetURL : System.getProperty("URL");
        Configuration.browser = (System.getProperty("browser") == null) ? "chrome" : System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("node");
        Configuration.remote = System.getProperty("hub");
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = timeout * 1000;
        Configuration.timeout = timeout * 1000;
        setWaitForLoading(loadingTimeout);
        //Configuration.clickViaJs = true;
        //Configuration.holdBrowserOpen = true;
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

        /*System.out.println("remote: " + Configuration.remote);
        System.out.println("browser: " + Configuration.browser);
        System.out.println("url: " + Configuration.baseUrl);
        System.out.println("Upload path: " + getUploadPath());
        System.out.println("Download path: " + getDownloadPath());
        System.out.println("Selenide/Selenium driver has been set up.");*/

        AllureEnvironmentUtils.create();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method test) {

        String testName = firstNonEmpty(
                test.getDeclaredAnnotation(org.testng.annotations.Test.class).description(),
                test.getName())
                .orElse("Unknown");

        final String desc = test.getDeclaredAnnotation(org.testng.annotations.Test.class).toString();
        Allure.addAttachment("Annotations", desc);

        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("TEST: " + testName);
        System.out.println("");
        System.out.println("STATUS: Started");
        System.out.println("");
        System.out.println("TEST ANNOTATIONS: " + test.getDeclaredAnnotation(org.testng.annotations.Test.class).toString());
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        int SUCCESS = 1;
        int FAILURE = 2;
        int SKIP = 3;
        int SUCCESS_PERCENTAGE_FAILURE = 4;
        int STARTED = 16;
        String result = "unknown";
        switch (testResult.getStatus()) {
            case 1:
                result = "SUCCESS";
                break;
            case 2:
                result = "FAILURE";
                break;
            case 3:
                result = "SKIP";
                break;
            case 4:
                result = "SUCCESS_PERCENTAGE_FAILURE";
                break;
            case 16:
                result = "STARTED";
                break;
        }

        if (testResult.getStatus() == ITestResult.FAILURE)
            takeScreenshot();

        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("TEST: " + testResult.getMethod().getMethodName());
        System.out.println("");
        System.out.println("STATUS: " + result);
        System.out.println("");
        System.out.println("DESCRIPTION: "+ testResult.getMethod().getDescription());
        if (testResult.getThrowable()!=null) {
            System.out.println("");
            System.out.println("ERROR: " + testResult.getThrowable().getMessage());
        }
        //        System.out.println("Test Annotations: " + testResult.getMethod().getMethod().getDeclaredAnnotation(org.testng.annotations.Test.class).toString());
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");

        //Parallelde hatası vermemesi WebDriverRunner.closeWebDriver() eklendi.
        //login da WebDriverRunner.clearBrowserCache(); eklendi
        //Selenide.close();
        //WebDriverRunner.getAndCheckWebDriver().quit();
        WebDriverRunner.closeWebDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Selenide.close();
        log.info("Browser has been closed.");
    }

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
