package Example;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 14.12.2017
 * Açıklama:
 */
public class Test1 {

    RemoteWebDriver driver;

    @Test
    public void testName() throws Exception {

        System.setProperty("webdriver.gecko.driver", "");

        String Node = "http://10.10.:4444/wd/hub";
        DesiredCapabilities cap = DesiredCapabilities.firefox();

        driver = new RemoteWebDriver(new URL(Node), cap);
         System.out.println("Alkan");
         System.out.println("Serkan");
//        driver = new FirefoxDriver();
        driver.get("https://www.google.com.tr/");
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}
