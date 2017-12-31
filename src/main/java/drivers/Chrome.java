package drivers;

import com.codeborne.selenide.WebDriverProvider;
import common.BaseTest;
import data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 31.12.2017
 * Açıklama:
 */
public class Chrome extends BaseTest implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
//        System.setProperty("webdriver.gecko.driver","/Users/ilyas/Documents/WebDrivers/geckodriver-v0.19.0");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", TestData.docDownloadPathLinux);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        return new ChromeDriver(options);
    }
}