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
        FirefoxProfile profile = new FirefoxProfile();
        /*profile.setPreference("browser.download.folderList", 0);
        //capabilities.setCapability("browser.download.dir", TestData.docDownloadPathWindows);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/excel");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-excel");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msexcel");
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);*/
        //return new FirefoxDriver(capabilities);
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 1);
        options.addPreference("browser.helperApps.alwaysAsk.force", false);
        options.addPreference("browser.helperApps.neverAsk.openFile", "true");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "true");
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/excel");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-excel");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msexcel");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        options.merge(capabilities);
        options.setProfile(profile);
        return new FirefoxDriver(options);

        //application/vnd.ms-excel
        //text/xml; charset=UTF-8
    }

    public WebDriver createDriver1(DesiredCapabilities capabilities) {

        //<editor-fold desc="ProdilesIni can be used to set "default" or pre created profile">
//        ProfilesIni profilesIni = new ProfilesIni();
//        FirefoxProfile profile = profilesIni.getProfile("default");
        //</editor-fold>
//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.19.0");
//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.18.0");
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        //options.setProfile(profile);
        //.setCapability("browser.name", "firefox");
        options.setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", TestData.docDownloadPathWindows);
        options.setCapability("browserName", "firefox");
        options.merge(capabilities);
//        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"false");
//        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

        //capabilities.setBrowserName("drivers.Firefox");

        return new FirefoxDriver(options);
    }

    public FirefoxOptions firePlatform() {
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

    public FirefoxOptions fire() {
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
