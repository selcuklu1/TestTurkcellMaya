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
        Configuration.baseUrl = "http://10.101.20.153:8889/edys-web/sistemeGiris.xhtml";
        //Configuration.browser = "chrome";
        //Configuration.browser = "drivers.Firefox";
        //Configuration.browser = "marionette";

        Configuration.remote = "http://10.101.20.153:4444/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 30000;
        Configuration.timeout = 30000;
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;
        Configuration.startMaximized = true;
        //Configuration.browserSize = "1024x600";
        //endregion

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

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
