package common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import listeners.SettingsListener;
import org.openqa.selenium.Dimension;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pageComponents.belgenetElements.BelgenetFramework;

import java.util.Locale;

import static data.TestData.*;

@Listeners({SettingsListener.class})
public class BaseTest {

    @BeforeClass
    public void setUp() {
        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(turkishLocal);

        //region SetUp BelgenetFramework for BelgenetElements usage
        BelgenetFramework.setUp();
        //endregion

        //region Selenide Driver Configuration
        Configuration.baseUrl = belgenetURL;
        Configuration.browser = "drivers.Firefox"; //
        //"org.openqa.selenium.Firefox.FirefoxDriver";
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.collectionsTimeout = 20000;
        Configuration.timeout = 20000;
        Configuration.holdBrowserOpen = true;
//        Configuration.startMaximized = true;
//        Configuration.headless = true;
//        Configuration.browserSize = "1024x768";
        //endregion
    }

}
