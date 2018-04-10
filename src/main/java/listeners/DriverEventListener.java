package listeners;


import com.codeborne.selenide.Configuration;
import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.sql.Timestamp;
import java.util.Arrays;

public class DriverEventListener extends BaseLibrary implements WebDriverEventListener {

    private static boolean log = false;

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

        By loadingLocator = By.cssSelector("div[style*='display: block;'] .loading");
        long timeout = Configuration.timeout / 1000;

        /*private By messageLocator = By.cssSelector(".lobibox-notify");
        private By bodyLocator = By.cssSelector(".lobibox-notify-body");
        private By titleLocator = By.cssSelector(".lobibox-notify-title");
        private By msgLocator = By.cssSelector(".lobibox-notify-msg");
        private By closeButtonLocator = By.cssSelector(".lobibox-close");*/

        /*(by.equals(By.className("lobibox-notify"))
                || by.equals(By.cssSelector(".lobibox-notify"))
                ||*/

        if (by.toString().contains("lobibox")
                || (element != null && element.toString().contains("lobibox")))
            return;


        waitForLoadingJS(driver);
        /*//İşlem Mesajları için loading kaybolması beklememeli.
        if (by.equals(By.cssSelector(".lobibox-notify-title")) || by.equals(By.cssSelector(".lobibox-notify-msg")))
                return;*/

        /*final String[] readyState = new String[1];
        //JS readyStates: loading, interactive, complete
        new WebDriverWait(driver, timeout, 10)
                .until(Boolean -> {
                    readyState[0] = ((JavascriptExecutor) driver).executeScript("return document.readyState").toString();
                    return readyState[0].equals("interactive") || readyState[0].equals("complete");
                });
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    readyState:  " + readyState[0]);
        }*/

        /*//Loading aramalarda beklememeli.
        if (by.equals(loadingLocator))
            return;

        //"div[id*='bekleyiniz'][style*='visibility: visible']"
        new WebDriverWait(driver, timeout, 50).until(
                invisibilityOfAllElements(driver.findElements(loadingLocator)));
//            until(invisibilityOfElementLocated(By.cssSelector("div[style*='display: block;'] .loading")));

        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    looking for element: " + by.toString());
        }

        }*/

    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    found element: " + by.toString());
        }
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        //waitForLoadingJS(driver);
        //new WebDriverWait(driver, Configuration.timeout / 1000).until(elementToBeClickable(element));

        // Selenide.sleep(1000);
        /**
         * Focus on element: Belgenete özel
         * Visible fakat ekranda görünmeyen olan buronlar için.
         * executeScript("arguments[0].scrollIntoView();", element) bazı yerlerde beklenmedik
         * sonuçları verdiği için sendKeys kullanıldı. Test edilecek..!
         */
        /*try {
            element.sendKeys("\n");
        } catch (Exception ignored) { }
        try {
            element.sendKeys(Keys.SHIFT);
        } catch (Exception ignored) { }*/
        /*Actions action = new Actions(driver);
        action.moveToElement(element, element.getLocation().x/2, element.getLocation().y/2)
                .perform();*/
        // System.out.println("Element location: " + element.getLocation().x + "-" + element.getLocation().y);

        /*if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    Before click: " + element.toString());
        }*/
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    clicked: " + element.toString());
        }
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        waitForLoadingJS(driver);
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    Change value: " + Arrays.toString(keysToSend) + "    element: " + element.toString());
        }
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    value chaged: " + element.toString());
        }
    }

    public void beforeScript(String script, WebDriver driver) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    before script: " + script);
        }
    }

    public void afterScript(String script, WebDriver driver) {
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + "    after script: " + script);
        }
    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver driver) {
        /*System.out.println("///////////////////////////////////////////////////////////////////////////");
        System.out.println("Case Error: " + throwable.getMessage());
        System.out.println("///////////////////////////////////////////////////////////////////////////");
        takeScreenshot(driver);*/
    }


}
