package common;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import listeners.SettingsListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetFramework;

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

        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;
        Configuration.browser = "chrome";
        // Configuration.browser = "drivers.Firefox";
        //Configuration.browser = "marionette";


        Configuration.remote = "http://192.168.1.3:6570/wd/hub";
        //Configuration.remote = "http://10.101.20.153:4444/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 30000;
        Configuration.timeout = 30000;
        Configuration.holdBrowserOpen = true;
        Configuration.headless = false;
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

/*        try {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
        } catch (Exception e) {
        }*/
    }

    public class User {

        private String username;
        private String password;
        private String name;
        private String birimAdi;
        private String gorev;

        public User(String username, String password, String name, String birimAdi, String gorev) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.birimAdi = birimAdi;
            this.gorev = gorev;
        }

        public User(String username, String password, String name, String birimAdi) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.birimAdi = birimAdi;
        }

        public User(String username, String password, String name) {
            this.username = username;
            this.password = password;
            this.name = name;
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getBirimAdi() {
            return birimAdi;
        }

        public String getGorev() {
            return gorev;
        }
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
