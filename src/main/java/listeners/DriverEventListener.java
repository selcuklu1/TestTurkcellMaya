package listeners;

import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class DriverEventListener extends BaseLibrary implements WebDriverEventListener {

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
        waitForLoading(driver);
        System.out.println("Looking for element: " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Will click on element.. " + element.toString());

        /**
         * Focus on element: Belgenete özel
         * Visible fakat ekranda görünmeyen olan buronlar için.
         * executeScript("arguments[0].scrollIntoView();", element) bazı yerlerde beklenmedik
         * sonuçları verdiği için sendKeys kullanıldı. Test edilecek..!
         */
        element.sendKeys(Keys.SHIFT); //element.sendKeys("\n");
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
//        System.out.println("Clicked on element.. " + element.toString());
//        waitForLoading(driver);
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
