package common;

import com.codeborne.selenide.Configuration;
import data.TestData;
import listeners.SettingsListener;
import org.testng.annotations.BeforeClass;
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
<<<<<<< HEAD
        //        Configuration.baseUrl = "http://94.55.114.18:8889/edys-web/sistemeGiris.xhtml";
        Configuration.baseUrl = TestData.belgenetURL;
=======
        Configuration.baseUrl = belgenetURL;
>>>>>>> 7cfb106ba52eaee62cf21b80aad145309ff65fd9
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
