package listeners;

import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.sql.Timestamp;

public class DriverEventListener extends BaseLibrary implements WebDriverEventListener {

    private static boolean log = true;

    public void beforeAlertAccept(WebDriver driver) {

    }

    public void afterAlertAccept(WebDriver driver) {

    }

    public void afterAlertDismiss(WebDriver driver) {

    }

    public void beforeAlertDismiss(WebDriver driver) {

    }

    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    public void afterNavigateTo(String url, WebDriver driver) {

    }

    public void beforeNavigateBack(WebDriver driver) {

    }

    public void afterNavigateBack(WebDriver driver) {

    }

    public void beforeNavigateForward(WebDriver driver) {

    }

    public void afterNavigateForward(WebDriver driver) {

    }

    public void beforeNavigateRefresh(WebDriver driver) {

    }

    public void afterNavigateRefresh(WebDriver driver) {

    }


    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        boolean loading = true;
        if (by != null)
            if (by.equals(By.cssSelector("div[id*='bekleyiniz'][style*='visibility: visible']")))
                loading = false;
//        if (by.equals(By.className("loading")))
//                loading = false;


        if (loading)
            waitForLoading(driver);

        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " Looking for element: " + by.toString());
        }
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " Found element: " + by.toString());
        }
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Will click on element.. " + element.toString());

        /**
         * Focus on element: Belgenete özel
         * Visible fakat ekranda görünmeyen olan buronlar için.
         * executeScript("arguments[0].scrollIntoView();", element) bazı yerlerde beklenmedik
         * sonuçları verdiği için sendKeys kullanıldı. Test edilecek..!
         */

        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " Before click: " + element.toString());
        }

//        try {
//            element.sendKeys("\n");
//        } catch (Exception ignored) { }
        /*try {
            element.sendKeys(Keys.SHIFT);
        } catch (Exception ignored) { }*/
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Clicked on element.. " + element.toString());
//        waitForLoading(driver);
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " After click: " + element.toString());
        }
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    public void beforeScript(String script, WebDriver driver) {

    }

    public void afterScript(String script, WebDriver driver) {

    }

    public void onException(Throwable throwable, WebDriver driver) {

    }
}
