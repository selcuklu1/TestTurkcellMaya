package listeners;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Timestamp;
import java.util.function.Function;

import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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

        /*Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver arg0) {
                WebElement element = arg0.findElement(By.id("dynamicColor"));
                String color = element.getCssValue("color");
                System.out.println("The button text has color :" + color);
                if (color.equals("rgba(255, 255, 0, 1)")) {
                    return true;
                }
                return false;
            }
        };*/
//        System.out.println("BeforFind   " + by.toString());


        if (by.equals(By.cssSelector("div[style*='display: block;'] .loading")))
            return;

        if (by.equals(By.cssSelector(".lobibox-notify-title"))
                || by.equals(By.cssSelector(".lobibox-notify-msg")))
                return;

        new WebDriverWait(driver, 5, 50)
                .until(boalen -> {
                    JavascriptExecutor js = ((JavascriptExecutor) driver);
                    String readyState = js.executeScript("return document.readyState").toString();
//                        System.out.println("Internal ready state:" + readyState);
                    return readyState.equals("complete")
                            || readyState.equals("interactive");
//                    return !readyState.equals("loading");
                });

        new WebDriverWait(driver, 10, 50).
//            until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(
            until(invisibilityOfElementLocated(By.cssSelector("div[style*='display: block;'] .loading")));
    /*    boolean loading = true;
        if (by != null) {
            if (
                    by.equals(By.cssSelector("div[id*='bekleyiniz'][style*='visibility: visible']")) ||
                            by.equals(By.className("loading")) ||
                            by.equals(By.cssSelector(".lobibox-notify-title")) ||
                            by.equals(By.cssSelector(".lobibox-notify-msg"))
                    ){
                loading = false;

                *//*if (by.equals(by.equals(By.cssSelector(".lobibox-notify-title"))
                        || by.equals(By.cssSelector(".lobibox-notify-msg"))))
                    System.out.println(by.toString());*//*
            }
        }
        else {
            if (by.equals(By.cssSelector("div[id*='bekleyiniz'][style*='visibility: visible']")) ||
                    by.equals(By.className("loading")) ||
                    element.toString().contains(".lobibox-notify-title")
                    || element.toString().contains(".lobibox-notify-msg")) {
                loading = false;
            }
        }

        System.out.println(loading + "          "+ by.toString());
        if (loading)
            waitForLoading(driver);
*/
        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " Looking for element: " + by.toString());
        }
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//        System.out.println("AfterFind   " + by.toString());


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
//        System.out.println("BeforClick   " + element.toString());
//        new WebDriverWait(driver, 10, 50).
//            until(ExpectedConditions.invisibilityOfAllElements(driver.findElements
//                    (By.cssSelector("div[style*='display: block;'] .loading"))));
//        until(invisibilityOfElementLocated(By.cssSelector("div[style*='display: block;'] .loading")));


        new WebDriverWait(driver, Configuration.timeout/1000).until(elementToBeClickable(element));

//        Actions action = new Actions(driver);
//        action.moveToElement(element, element.getLocation().x/2, element.getLocation().y/2)
//                .perform();

        // System.out.println("Element location: " + element.getLocation().x + "-" + element.getLocation().y);
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

//        new WebDriverWait(driver, 10, 50).
//                until(ExpectedConditions.invisibilityOfAllElements(driver.findElements
//                        (By.cssSelector("div[style*='display: block;'] .loading"))));

        if (log) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println(timestamp + " After click: " + element.toString());
        }
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//        new WebDriverWait(driver, 10, 50).
//                until(ExpectedConditions.invisibilityOfAllElements(driver.findElements
//                        (By.cssSelector("div[style*='display: block;'] .loading"))));

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
