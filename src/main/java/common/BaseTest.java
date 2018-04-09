package common;

import com.codeborne.selenide.*;
import data.TestData;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import listeners.DriverEventListener;
import listeners.ResultListener;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$;
import static data.TestData.belgenetURL;
import static io.qameta.allure.util.ResultsUtils.firstNonEmpty;

//BrowserPerTest.class
@Listeners({ResultListener.class
        //, MethodInterceptor.class
})
//@Listeners({RerunFailedTests.class})
public class BaseTest extends BaseLibrary {

    //Seconds
    static final int timeout = 30;
    static final int loadingTimeout = 30;

    public Locale turkishLocal;

    @BeforeSuite(alwaysRun = true)
    public void driverSetUp() {
        String sysProperties = "";
        sysProperties += "Setup started";
        sysProperties += "\nfile.encoding: " + String.format("file.encoding: %s", System.getProperty("file.encoding"));
        sysProperties += "\ndefault charset=" + Charset.defaultCharset();
        sysProperties += "\njava.specification.version" + System.getProperty("java.specification.version");
        sysProperties += "\njava.runtime.version" + System.getProperty("java.runtime.version");
        sysProperties += "\nlocale default:" + Locale.getDefault();

        turkishLocal = new Locale("tr", "TR");
        if (!Locale.getDefault().equals(turkishLocal)) Locale.setDefault(turkishLocal);
        sysProperties += "\nlocale: " + Locale.getDefault();


        BelgenetFramework.setUp();
        WebDriverRunner.addListener(new DriverEventListener());

        //Configuration.remote = "http://10.101.20.151:4444/wd/hub";
        //Configuration.remote = "http://localhost:4444/wd/hub";

        Configuration.baseUrl = (System.getProperty("URL") == null) ? belgenetURL : System.getProperty("URL");
        Configuration.browser = (System.getProperty("browser") == null) ? "chrome" : System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("node");
        Configuration.driverManagerEnabled = false;
        Configuration.remote = System.getProperty("hub");
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = Configuration.remote == null;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = timeout * 1000;
        Configuration.holdBrowserOpen = Configuration.remote == null;
        Configuration.timeout = timeout * 1000;
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.collectionsPollingInterval = 100;

        //Configuration.headless = false;
        //Configuration.clickViaJs = true;
        //Configuration.closeBrowserTimeoutMs = 34000;
        //Configuration.openBrowserTimeoutMs = 34000;
        //Configuration.browserSize = "1024x600";
        //endregion
        setWaitForLoading(loadingTimeout);

        /*if (Configuration.browser.equalsIgnoreCase("firefox")){
            String neverAsk = "application/msword," +
                    "application/vnd.ms-excel," +
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document," +
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet," +
                    "application/pdf";
            FirefoxOptions options = new FirefoxOptions()
                    .addPreference("browser.helperApps.alwaysAsk.force", false)
                    .addPreference("browser.download.manager.showWhenStarting", false)
                    .addPreference("browser.helperApps.neverAsk.saveToDisk", neverAsk);
            //Configuration.browserCapabilities = DesiredCapabilities.firefox();
            Configuration.browserCapabilities = new DesiredCapabilities();
            Configuration.browserCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
            //Configuration.browserCapabilities.merge(options);
            //Configuration.browserCapabilities.setCapability("browser.helperApps.alwaysAsk.force", false);
            //Configuration.browserCapabilities.setCapability("browser.helperApps.neverAsk.saveToDisk", neverAsk);
        }*/

        //System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        sysProperties += "\nremote: " + Configuration.remote;
        sysProperties += "\nbrowser: " + Configuration.browser;
        sysProperties += "\nbrowser.version: " + Configuration.browserVersion;
        sysProperties += "\nurl: " + Configuration.baseUrl;

        /*System.out.println("Upload path: " + getUploadPath());
        System.out.println("Download path: " + getDownloadPath());
        System.out.println("Selenide/Selenium driver has been set up.");*/
        log.info(sysProperties);
        //AllureEnvironmentUtils.create();
    }

    @BeforeSuite(enabled = true)
    public void beforeSuite(ITestContext context) {
        if (System.getProperty("buildName") != null && !System.getProperty("buildName").isEmpty())
            context.getSuite().getXmlSuite().setName(System.getProperty("buildName"));
        else
            context.getSuite().getXmlSuite().setName("Suite");

        ((TestRunner) context).getTest().setName("Tests");
    }

    @BeforeMethod(alwaysRun = true, enabled = true)
    public void beforeMethod(ITestContext context, Method test) {
        String testResults = "";
        /*if (test.getDeclaringClass().isAnnotationPresent(io.qameta.allure.Feature.class))
            ((TestRunner) context).getTest().setName(test.getDeclaringClass().getAnnotation(io.qameta.allure.Feature.class).value());
        else
            ((TestRunner) context).getTest().setName(test.getDeclaringClass().getSimpleName());
        */
        String testName = firstNonEmpty(
                test.getDeclaredAnnotation(org.testng.annotations.Test.class).description(),
                test.getName())
                .orElse("Unknown");

        final String desc = test.getDeclaredAnnotation(org.testng.annotations.Test.class).toString();
        Allure.addAttachment("Annotations", desc);
        testResults += "\n///////////////////////////////////////////////////////" + "\n";
        //System.out.println("Total Test Classes: " + ((TestRunner) context).getTestClasses().size());
        testResults += "\nTotal Tests: " + context.getAllTestMethods().length;
        testResults += "\nPassed Tests: " + context.getPassedTests().size();
        testResults += "\nFailed Tests: " + context.getFailedTests().size();
        testResults += "\nSkipped Tests: " + context.getSkippedTests().size();
        testResults += "\nLeft Tests: " + Integer.valueOf(context.getAllTestMethods().length - (context.getPassedTests().size() + context.getFailedTests().size() + context.getSkippedTests().size())).toString() + "\n";
        testResults += "\n///////////////////////////////////////////////////////";
        testResults += "\nTEST CLASS: " + test.getDeclaringClass().getSimpleName() + "\n";
        testResults += "\nTEST: " + testName + "\n";
        testResults += "\nSTATUS: Started: " + "\n";
        testResults += "\nTEST ANNOTATIONS: " + test.getDeclaredAnnotation(org.testng.annotations.Test.class).toString();
        testResults += "\n///////////////////////////////////////////////////////";
        testResults += "\n///////////////////////////////////////////////////////";
        log.info(testResults);

    }

    @AfterMethod(alwaysRun = true, enabled = true)
    public void afterMethod(ITestResult testResult) {
        String testResults = "";
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

        /*if (testResult.getStatus() == ITestResult.FAILURE)
            takeScreenshot();*/
        testResults += "///////////////////////////////////////////////////////";
        testResults += "///////////////////////////////////////////////////////";
        testResults += "\nTEST: " + testResult.getMethod().getMethodName() + "\n";
        testResults += "\nSTATUS: " + result + "\n";
        testResults += "\nDESCRIPTION: " + testResult.getMethod().getDescription() + "\n";
        if (testResult.getThrowable() != null) {
            testResults += "\nERROR: " + testResult.getThrowable().getMessage() + "\n";
        }
        //        System.out.println("Test Annotations: " + testResult.getMethod().getMethod().getDeclaredAnnotation(org.testng.annotations.Test.class).toString());
        testResults += "///////////////////////////////////////////////////////";
        testResults += "///////////////////////////////////////////////////////";

        //Parallelde hatası vermemesi WebDriverRunner.closeWebDriver() eklendi.
        //login da WebDriverRunner.clearBrowserCache(); eklendi
        //Selenide.close();
        //WebDriverRunner.getAndCheckWebDriver().quit();
        log.info(testResults);

        try {
            Selenide.close();
            //WebDriverRunner.getWebDriver().quit();
            //WebDriverRunner.closeWebDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        Selenide.close();
        log.info("Browser has been closed.");
    }


    /**
     * @param testName
     *
     * @return downloadPath
     */
    public String useFirefoxWindows151(String testName) {
        String neverAsk = "application/msword;" +
                "application/vnd.ms-excel;" +
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" +
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;" +
                "application/pdf";

        String downloadPath = TestData.docDownloadPathWindows + testName;
        try {

            //Capabilities caps = getCapabilities();
            //caps.merge(options);
            /*FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", downloadPath);*/
            //capabilities.setCapability("browser.download.dir", TestData.docDownloadPathWindows);
            FirefoxOptions options = new FirefoxOptions();
            //options.setProfile(profile);
            options.setAcceptInsecureCerts(true)
                    .addPreference("security.insecure_field_warning.contextual.enabled", false)
                    .addPreference("browser.download.folderList", 2)
                    .addPreference("browser.download.dir", downloadPath)
                    .addPreference("browser.helperApps.alwaysAsk.force", false)
                    .addPreference("browser.download.manager.showWhenStarting", false)
//                    .addPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel; text/xml;application/x-excel;application/x-msexcel;application/pdf");
                    .addPreference("browser.helperApps.neverAsk.saveToDisk", neverAsk);
            //options.addPreference("browser.helperApps.neverAsk.openFile", "true");
            //options.addPreference("browser.helperApps.neverAsk.saveToDisk", "true");
            options.setCapability(CapabilityType.BROWSER_VERSION, "151");

            /*DesiredCapabilities capabilities = DesiredCapabilities.firefox();
             *//*capabilities.setVersion("151");
            capabilities.setPlatform(Platform.WINDOWS);//
            options.merge(capabilities);*/
            //caps.merge(options);

            WebDriver driver = Configuration.remote == null ?
                    new EventFiringWebDriver(new FirefoxDriver(options)).register(new DriverEventListener())
                    : new EventFiringWebDriver(new RemoteWebDriver(new URL(Configuration.remote), options)).register(new DriverEventListener());

            WebDriverRunner.setWebDriver(driver);
            //WebDriverRunner.setWebDriver(new FirefoxDriver(options));
        } catch (Exception e) {
            throw new RuntimeException("Invalid 'remote' parameter: " + Configuration.remote, e);
        }
        return downloadPath;
    }

    public void useFirefox() {
        try {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability(CapabilityType.VERSION, Configuration.browserVersion);
            //firefoxOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY);
            //firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, "firefox");
            /*DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setVersion(Configuration.browserVersion);*/

            EventFiringWebDriver driver;
            if (Configuration.remote == null) {
                WebDriver firefox = new FirefoxDriver();
                driver = new EventFiringWebDriver(firefox).register(new DriverEventListener());
            } else {
                WebDriver firefox = new RemoteWebDriver(new URL(Configuration.remote), firefoxOptions);
                //firefox.get("https://www.google.com.tr/");
                driver = new EventFiringWebDriver(firefox).register(new DriverEventListener());
            }

            /*WebDriver driver = Configuration.remote == null ?
                    new EventFiringWebDriver(new FirefoxDriver()).register(new DriverEventListener())
                    : new EventFiringWebDriver(new RemoteWebDriver(new URL(Configuration.remote), capabilities)).register(new DriverEventListener());*/
            //: new EventFiringWebDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities)).register(new DriverEventListener());

            //System.setProperty("webdriver.chrome.driver", "C:\\drivers\\geckodriver.exe");
            /*WebDriver driver = System.getProperty("hub") == null ?
                    new FirefoxDriver()
                    : new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);*/
            /*WebDriver driver = System.getProperty("hub") == null ?
                    new FirefoxDriver()
                    : new RemoteWebDriver(firefoxOptions);*/
            //C:\drivers

            if (WebDriverRunner.hasWebDriverStarted())
                WebDriverRunner.getWebDriver().quit();

            WebDriverRunner.setWebDriver(driver);
            /*WebDriverRunner.setWebDriver(new FirefoxDriver(firefoxOptions));
            System.out.println(getCapabilities().getCapability(CapabilityType.BROWSER_VERSION));
            Configuration.remote = System.getProperty("hub");*/

        } catch (Exception e) {
            throw new RuntimeException(String.format("Error new RemoteWebDriver: %s error %s", Configuration.remote, e.getMessage()), e);
        }

        //System.out.println("Browser: " + getCapabilities().getBrowserName());
    }

    /**
     * @param testName
     *
     * @return downloadPath
     */
    public String useChromeWindows151(String testName) {
        String downloadPath = TestData.docDownloadPathWindows + testName;
        //downloadPath = System.getProperty("user.dir")+ File.separator + "target" + File.separator + testName;
        try {
            //Capabilities caps = getCapabilities();
            //caps.merge(options);
            /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setPlatform(Platform.WINDOWS);
            capabilities.setVersion("151");*/

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
            options.setCapability(CapabilityType.BROWSER_VERSION, "151");
            options.addArguments("disable-infobars");
            options.setAcceptInsecureCerts(true);
            /*WebDriver driver = Configuration.remote == null ?
                    new ChromeDriver(options)
                    : new RemoteWebDriver(new URL(Configuration.remote), options);*/
            WebDriver driver = Configuration.remote == null ?
                    new EventFiringWebDriver(new ChromeDriver(options)).register(new DriverEventListener())
                    : new EventFiringWebDriver(new RemoteWebDriver(new URL(Configuration.remote), options)).register(new DriverEventListener());

            WebDriverRunner.setWebDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException("Invalid 'remote' parameter: " + Configuration.remote, e);
        }
        return downloadPath;
    }


    @Step("Login")
    public void login(User user) {
        LoginPage loginPage = new LoginPage().login(user.getUsername(), user.getPassword());
        if (!user.getBirimAdi().isEmpty() && user.getBirimAdi() != null)
            loginPage.birimSec(Condition.text(user.getBirimAdi()));
    }

    ElementsCollection birimDegistirme = $$("a[id^='leftMenuForm:edysMenuItem'] span[class='ui-menuitem-text']");

    @Step("Kullanıcının Yeni Birimi \"{birim}\" seçilir")
    public void birimDegistirme(String birim) {
        birimDegistirme.filterBy(Condition.text(birim)).get(0).click();
//        waitForLoadingJS(WebDriverRunner.getWebDriver());
        Selenide.sleep(15000);
    }

    @Step("Test Numarası : {testid} {status} ")
    public void testStatus(String testid, String status) { }

    @Step("{name} : {description}")
    public void step(String name, String description) { }

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