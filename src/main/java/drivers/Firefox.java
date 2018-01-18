package drivers;

import com.codeborne.selenide.WebDriverProvider;
import common.BaseTest;
import data.TestData;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class Firefox extends BaseTest implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {

        //<editor-fold desc="ProdilesIni can be used to set "default" or pre created profile">
//        ProfilesIni profilesIni = new ProfilesIni();
//        FirefoxProfile profile = profilesIni.getProfile("default");
        //</editor-fold>

//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.19.0");
//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.18.0");
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(profile)
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", TestData.docDownloadPathWindows);


//        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"false");
//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

        return new FirefoxDriver(options);
    }

    public FirefoxOptions firePlatform(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.WINDOWS);

        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(profile)
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", TestData.docDownloadPathLinux);
        options.merge(capabilities);
        return options;
    }

    public FirefoxOptions fire(){
        //<editor-fold desc="ProdilesIni can be used to set "default" or pre created profile">
//        ProfilesIni profilesIni = new ProfilesIni();
//        FirefoxProfile profile = profilesIni.getProfile("default");
        //</editor-fold>

//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.19.0");
//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.18.0");
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions()
                .setProfile(profile)
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        //options.addPreference("browser.download.dir", TestData.docDownloadPathLinux);


//        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"false");
//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        return options;
    }
}
