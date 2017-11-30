package common;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
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
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.baseUrl = belgenetURL;
<<<<<<< HEAD
       // Configuration.browser = "drivers.Firefox";
=======
        //Configuration.browser = "drivers.Firefox";
>>>>>>> 978364c0d6a202db360d56c8a2999abaf142682b
        //Configuration.browser = "marionette";
        //Configuration.remote = "http://0.0.0.0:32768/wd/hub";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 30000;
        Configuration.timeout = 30000;
        Configuration.holdBrowserOpen = true;
//        Configuration.headless = true;
        Configuration.startMaximized = true;
//        Configuration.browserSize = "1024x600";
        //endregion

        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    }


    public void login() {
        new LoginPage().login();
    }

    public void login(String username, String password) {
        new LoginPage().login(username, password);
    }
    @Step("Çıkış yap")
    public void logout() {
        new MainPage().logout();
    }

}
