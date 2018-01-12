package common;

import com.codeborne.selenide.*;
import data.TestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class BaseLibrary extends ElementsContainer {


    protected static final Logger log = Logger.getLogger(BaseLibrary.class.getName());
    protected static String winHandleBefore = null;
    private static String docPath = null;
    private static String browserName = null;
    private String downoladPath = null;
    private long waitForLoading = 20;
    private int doWaitLoading = 0;
    private boolean doNotWaitLoading = false;

    public static void killProcess() {

        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("taskkill /f /im " + "chrome.exe");
            rt.exec("taskkill /f /im " + "chromedriver.exe");
            rt.exec("taskkill /f /im " + "conhost.exe");
            rt.exec("taskkill /f /im " + "firefox.exe");
            rt.exec("taskkill /f /im " + "geckodriver.exe");
            rt.exec("taskkill /f /im " + "iexplore.exe");
            rt.exec("taskkill /f /im " + "iedriver.server");
            rt.exec("taskkill /f /im " + "iedriver.server64");
            //rt.exec("taskkill /f /im " + "WerFault");
            //rt.exec("taskkill /f /im " + "AcroRd32");
            //rt.exec("taskkill /f /im " + "Excel");
        } catch (IOException e) {
            System.out.println("Processler Kill Edilememdi!!!");
        }
    }
    //</editor-fold>

    //<editor-fold desc="Page loading methods">

    /**
     * Türkçe harfleri inglizce harflere dönüştürüyor
     *
     * @param str
     * @return
     */
    public static String clearTurkishChars(String str) {
        String ret = str;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }

    public static String getPCUsername() {
        String userName = System.getProperty("user.name");
        return userName;
    }

    public void clearCookies() {
        try {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
        } catch (Exception e) {
            log.info("Error clearBrowserLocalStorage and clearBrowserCookies: " + e.getMessage());
        }
    }

    //<editor-fold desc="Allure screenshooter">
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        byte[] bytes = new byte[]{};
        try {
            bytes = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            System.out.println("Error takeScreenshot:" + e.getMessage());
        }
        return bytes;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
        byte[] bytes = new byte[]{};
        try {
            bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            System.out.println("Error takeScreenshot:" + e.getMessage());
        }
        return bytes;
    }

    /**
     * Waiting to JS and jQuery ready state and object with class "loading" to disappear.
     * Used in DriverInvokeListener beforeFindBy method
     *
     * @param
     */
    private void waitForJS() {
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000, 50).
                    until((ExpectedCondition<Boolean>) driver -> {
                        String readyState = executeJavaScript("return document.readyState");
//                        System.out.println("Internal ready state:" + readyState);
//                        return readyState.equals("complete") || readyState.equals("interactive");
                        return !readyState.equals("loading");

                    });
//            System.out.println("Loading: Ok");
        } catch (Exception e) {
//            System.out.println("Loading window error: " + e.getMessage());
        }
        /*try {
            Wait().until(ExpectedConditions.and(
                    (ExpectedCondition<Boolean>) driver -> {
                        try {
                            return (Boolean) executeJavaScript("return document.readyState").equals("complete");
                        } catch (Exception e) {
                            return true;
                        }
                    },
                    (ExpectedCondition<Boolean>) driver -> {
                        try {
                            return (Boolean) executeJavaScript("return jQuery.active == 0");
                        } catch (Exception e) {
                            return true;
                        }
                    }
            ));
        } catch (Exception e) {
            System.out.println("WaitForJS error: " + e.getMessage());
        }*/
    }

    private void waitForJSreadyState() {
        Wait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return executeJavaScript("return document.readyState").equals("complete");
            }
        });
    }

    private void waitForLoadingToDisappear(WebDriver driver) {
//        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

        //Thread.sleep(3000);
        try {

            /*List<WebElement> loading = driver.findElements(By.className("loading"));
            System.out.println("Count:" + loading.size());
            int i = 0;
            for (WebElement e:loading) {
                System.out.println(i + ": innerHtml-" + executeJavaScript("return arguments[0].outerHTML", e));
                System.out.println(i + ": isDisplayed-" + e.isDisplayed() + "   isEnabled-" + e.isEnabled());
            }*/

//            System.out.println("Count:" + driver.findElements(By.className("loading")).size());
            System.out.println("Count:" + driver.findElements(By.cssSelector("div[style*='display: block;'] .loading")).size());
            new WebDriverWait(driver, 10, 50).
                    until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(
                            By.className("loading"))));
//            System.out.println("Loading: Ok");
        } catch (Exception e) {
//            System.out.println("Loading window error: " + e.getMessage());
        }
//        driver.manage().timeouts().implicitlyWait(Configuration.timeout, TimeUnit.MILLISECONDS);
    }

    private void waitForLoadingToDisappear1(WebDriver driver) {
        try {

            //div[starts-with(@id,"bekleyiniz") and contains(@style, "display")]
            //div[id*='bekleyiniz'][style*='visibility: visible']
            new WebDriverWait(driver, Configuration.timeout / 1000, 50).
                    until(invisibilityOfElementLocated(By.cssSelector("div[id*='bekleyiniz'][style*='visibility: visible']")));

//            new WebDriverWait(driver, Configuration.timeout / 1000, 50).
//                    until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("loading"))));
            //      new WebDriverWait(driver, Configuration.timeout / 1000, 50).
            //            until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("loading"))));
            //
            //                 new WebDriverWait(driver, Configuration.timeout / 1000, 50).
//                        until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.className("loading"))));

//            System.out.println("Loading: Ok");
        } catch (Exception e) {
//            System.out.println("Loading window error: " + e.getMessage());
        }
    }

    private void setDoNotWaitLoading(boolean doNotWaitLoading) {
        this.doNotWaitLoading = doNotWaitLoading;
    }

    private void waitForLoading(WebDriver driver) throws InterruptedException {
        if (doNotWaitLoading)
            return;
        //waitForJS();
        waitForLoadingToDisappear(driver);
    }
    //</editor-fold>

    private long getWaitForLoading() {
        return waitForLoading;
    }

    public void setWaitForLoading(long seconds) {
        this.waitForLoading = waitForLoading;
    }

    public void waitForLoadingJS(WebDriver driver, long timeoutSec) {
        new WebDriverWait(driver, timeoutSec, 10).until(driver1 ->
        {
            JavascriptExecutor js = (JavascriptExecutor) driver1;
            boolean isJsFinished = false;
            try {
                isJsFinished = (boolean) js.executeScript("return (document.readyState == \"complete\" || document.readyState == \"interactive\")");
            } catch (Exception e) {
                isJsFinished = true;
                System.out.println("Load: isJsFinished error: " + e.getMessage());
            }

            //            boolean isAjaxFinished = (boolean) ((JavascriptExecutor) driver1).
//                    executeScript("return jQuery.active == 0");

            boolean isAjaxFinished = false;
            try {
                isAjaxFinished = (boolean) js.executeScript("var result = true; try { result = (typeof jQuery != 'undefined') ? jQuery.active == 0 : true } catch (e) {}; return result;");
            } catch (Exception e) {
                isAjaxFinished = true;
                System.out.println("Load: isAjaxFinished error: " + e.getMessage());
            }

            boolean isLoaderHidden = false;
            try {
                isLoaderHidden = (boolean) js.executeScript("return document.querySelectorAll('div[id*=\"bekleyiniz\"][style*=\"visibility: visible\"]').length == 0");
//                    executeScript("return $('.loading').is(':visible') == false");
            } catch (Exception e) {
                isLoaderHidden = true;
                System.out.println("Load: isLoaderHidden error: " + e.getMessage());
            }

            return isJsFinished && isLoaderHidden && isAjaxFinished;
        });
    }

    public void waitForLoadingJS(WebDriver driver) {
//        long timeout = Configuration.timeout / 1000;
        long timeout = getWaitForLoading();
        waitForLoadingJS(driver, timeout);
    }

    public void maximazeBrowser() {
        try {
            if (Configuration.browserSize != null) {
                try {
                    String[] size = Configuration.browserSize.split("x");
                    int width = Integer.parseInt(size[0]);
                    int height = Integer.parseInt(size[1]);
                    Dimension browserSize = new Dimension(width, height);
                    WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);
                    System.out.println("custom maximize()");
                } catch (NumberFormatException e) {
                    WebDriverRunner.getWebDriver().manage().window().maximize();
                    System.out.println("manage().window().maximize()");
                }
            } else {
                WebDriverRunner.getWebDriver().manage().window().maximize();
                System.out.println("manage().window().maximize()");
            }
        } catch (Exception e) {
            System.out.println("SettingsListener maximize:" + e.getMessage());
        }
    }

    /**
     * Alan setValue, sendKeys doğru çalışmıyor ise bu metodu kullanılır.
     *
     * @param element
     * @param value
     */
    public void setValueJS(SelenideElement element, String value) {
        executeJavaScript("arguments[0].value = arguments[1]", element, value);
    }

    /**
     * get date from text in format 31.12.2017
     *
     * @param text
     * @return
     */
    public String getDateFromText(String text) {
        String result = "";
        String regex = "[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            result = matcher.group();

        return result;
    }

    /**
     * get time from text in format 18:59:01
     *
     * @param text
     * @return
     */
    public String getTimeFromText(String text) {
        String result = "";
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
            result = matcher.group();

        return result;
    }

    /**
     * JavaSctipt ile click yapılır
     *
     * @param element
     */
    public void clickJs(SelenideElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

    /**
     * JavaSctipt ile click yapılır
     *
     * @param element
     */
    public void clickJs(WebElement element) {
        executeJavaScript("arguments[0].click();", element);
    }

//    private String closeAlertAndGetItsText() {
//        try {
//            Alert alert = driver.switchTo().alert();
//            String alertText = alert.getText();
//            if (acceptNextAlert) {
//                alert.accept();
//            } else {
//                alert.dismiss();
//            }
//            return alertText;
//        } finally {
//            acceptNextAlert = true;
//        }
//    }

    //Dosya ekler
    public void uploadFile(SelenideElement element, String pathToFile) {
        try {
            element.sendKeys(pathToFile);
            log.info("Dosya yüklendi.");
        } catch (Exception e) {
            log.info("Error in attaching file.s : " + e);
            throw new RuntimeException(e);
        }
    }

    //Random numara üretir.
    public String createRandomNumber(int length) {
        Random r = new Random();
        List<Integer> digits = new ArrayList<Integer>();
        String number = "";

        for (int i = 0; i < length - 1; i++) {
            digits.add(i);
        }

        for (int i = length - 1; i > 0; i--) {
            int randomDigit = r.nextInt(i);
            number += digits.get(randomDigit);
            digits.remove(randomDigit);
        }

        number = "1" + number;

        return number;
    }

    //Random text üretir.
    public String createRandomText(int textSize) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < textSize; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();

        return output;
    }

    //yyyyMMddHHmmss formatına göre sysdate alır.
    public String getSysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
        String sysDate = dtf.format(now);

        return sysDate;
    }

    //dd.MM.yyyy formatına göre sysdate alır.
    public String getSysDateForKis() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
        String sysDate = dtf.format(now);

        return sysDate;
    }

    //dd.MM.yyyy formatına göre / koyarak sysdate alır.
    public String getSysDateForKis2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
        String sysDate = dtf.format(now);

        return sysDate;
    }

    //Bugün tarihinden sonraki bir yıl sonrayı alır.
    public String getAfterSysYear() {
        String untildate = getSysDateForKis();// can take any date in current
        // format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        String sysAfterYear = dateFormat.format(cal.getTime());
        return sysAfterYear;

    }

    //Günün tarihinden sonraki bir tarihi alır.
    public String getAfterSysDate(int i) throws ParseException {
        String untildate = getSysDateForKis();// can take any date in current
        // format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormat.parse(untildate));
        cal.add(Calendar.DATE, i);
        String convertedDate = dateFormat.format(cal.getTime());
        System.out.println("Gunun tarihinden 10 gun sonrasi: " + convertedDate);

        return convertedDate;

    }

    // sistem yılını alır
    public String getSysYear() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String sysYear = dtf.format(now);

        return sysYear;
    }

    // sistem ayını alır
    public String getSysMonth() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String sysMonth = dtf.format(now);

        return sysMonth;
    }

    //Dosyanın bilgisayara inip inmediğini kontrol eder.
    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return flag = true;
        }

        return flag;
    }

    //Bilgisayara indirilen dosyaları siler.
    public boolean deleteFile(String pathToFile) {
        try {
            File file = new File(pathToFile);

            if (file.delete()) {
                System.out.println(file.getName() + " dosyasi silindi.");
//                LogPASS(file.getName() + " dosyasi silindi.");
            } else {
                System.out.println("Dosya silme islemi basarisiz.");
                //logger.error("Error : Dosya silme islemi basarisiz.");
//                LogFAIL("Error : Dosya silme islemi basarisiz. ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Random tc yaratır mernis sorgusundan geçecek şekilde.
    public String createMernisTCKN() {
        Vector<Integer> array = new Vector<Integer>();
        Random randomGenerator = new Random();
        array.add(new Integer(1 + randomGenerator.nextInt(9)));

        for (int i = 1; i < 9; i++) array.add(randomGenerator.nextInt(10));

        int t1 = 0;
        for (int i = 0; i < 9; i += 2) t1 += array.elementAt(i);

        int t2 = 0;
        for (int i = 1; i < 8; i += 2) t2 += array.elementAt(i);

        int x = ((t1 * 7) - t2) % 10;
        array.add(new Integer(x));

        x = 0;
        for (int i = 0; i < 10; i++) x += array.elementAt(i);

        x = x % 10;
        array.add(new Integer(x));

        String res = "";
        for (int i = 0; i < 11; i++) res = res + Integer.toString(array.elementAt(i));

        System.out.println("Olusturulan TC Kimlik No:" + res);

        return res;
    }

    //Textin ilk harfini büyük yapar.
    public String toUpperCaseFirst(String text) {
        char ilkHarf = Character.toUpperCase(text.charAt(0));
        text = ilkHarf + text.substring(1);
        return text;
    }

    //Texti split edip : 'dan sonrasını alır.
    public String splitString(String str) {
        String[] parts = str.split(": ");// "004: 034556"
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // 034556

        return part2;
    }

    /* columnInput ile gönderilen değer, columnIndex ile belirtilen sütunda
       aratılır. columnInput olan satırın elementini döndürür. columnInput araması tüm sayfalarda yapılır.*/
    protected WebElement findElementOnTableByColumnInputInAllPages(SelenideElement byTable, int columnIndex, String columnInput) {
        SelenideElement next = $(("[class='ui-paginator-next ui-state-default ui-corner-all']"));
        // SelenideElement nextDisable = $(("[class*='ui-state-disabled']"));

        SelenideElement element = null;
        while (element == null) {
            element = findElementOnTableByColumnInput(byTable, columnIndex, columnInput);
            if (element == null) {
                if (next.isDisplayed() == false) {
                    System.out.println("Element tablodaki hiç bir sayfada bulunamadı.");
                    return null; // Element hiç bir sayfada bulunamazsa null döner.
                }
                next.click();
            }
        }
        System.out.println("Tabloda element bulundu.");
        return element;
    }

    /*  columnInput ile gönderilen değer, columnIndex ile belirtilen sütunda
       aratılır. columnInput olan satırın elementini döndürür. */
    protected WebElement findElementOnTableByColumnInputO(SelenideElement byTable, int columnIndex, String columnInput) {
        WebElement table = $(byTable).$(By.tagName("tbody"));
        int rowCount = 0;

        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        rowCount = allRows.size();
        WebElement elem = null;
        for (WebElement row : allRows) {
            elem = row.findElements(By.tagName("td")).get(columnIndex - 1);
            if (elem.getText().equals(columnInput)) {
                return elem;
            }
        }
        return null;
    }

    protected SelenideElement findElementOnTableByColumnInput(SelenideElement byTable, int columnIndex, String columnInput) {
        int rowCount = 0;

        ElementsCollection allRows = $(byTable).$(By.tagName("tbody")).$$(By.tagName("tr"));
        rowCount = allRows.size();
        if (rowCount == 0)
            return null;

        SelenideElement elem = null;
        for (SelenideElement row : allRows) {
            elem = row.$$(By.tagName("td")).get(columnIndex - 1).shouldBe(visible);
            if (elem.text().equals(columnInput)) {
                return elem;
            }
        }
        return null;
    }

    public String getIntegerInText(By by) {
        String x = WebDriverRunner.getWebDriver().findElement(by).getText();
        Pattern y = Pattern.compile("\\d+");
        Matcher m = y.matcher(x);
        m.find();
        String number = m.group();
        System.out.println(number);

        return number;
    }

    public String getIntegerInText(String text) {
        Pattern y = Pattern.compile("\\d+");
        Matcher m = y.matcher(text);
        m.find();
        String number = m.group();
        System.out.println(number);
        return number;
    }

    // Store the current window handle
    public String windowHandleBefore() {
        winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
        return winHandleBefore;
    }

    // Perform the click operation that opens new window
    // Switch to new window opened
    public void switchToNewWindow() throws InterruptedException {
        Thread.sleep(6000);
        for (String winHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            WebDriverRunner.getWebDriver().switchTo().window(winHandle);
        }
    }

    // Switch to default window
    public void switchToDefaultWindow() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverRunner.getWebDriver().close();
        // driver.switchTo().defaultContent();
        WebDriverRunner.getWebDriver().switchTo().window(winHandleBefore);
    }

    public void closeNewWindow() {
        WebDriverRunner.getWebDriver().close();
    }

    //region Neden burada?
    public String cssSE(String element, String attribute, String startsWith, String endsWith) {

        if (element != "" || element == null) {
            return "[" + attribute + "^=''][" + attribute + "$='']";
        } else {
            return element + "[" + attribute + "^=''][" + attribute + "$='']";
        }

    }

    @Step("[\"{0}\"] alanının değeri [\"{0}\"] olmalı.")
    public void alanDegeriKontrolEt(SelenideElement element, String value, boolean shouldHaveValue, boolean exactText) {
        if (shouldHaveValue == true) {
            if (exactText == true)
                element.shouldHave(exactValue(value));
            else {
                String _value = element.getValue();
                Assert.assertEquals(_value.contains(value), true);
            }
        } else {
            if (exactText == true)
                element.shouldNotHave(exactValue(value));

            else {
                String _value = element.getValue();
                Assert.assertEquals(_value.contains(value), false);
            }
        }
    }

    public boolean findElementOnTableAllPages(String form, SelenideElement element) {

        SelenideElement next = $(("[id='" + form + "'] [class='ui-paginator-next ui-state-default ui-corner-all']"));

        boolean status = false;
        while (status == false) {
            status = element.isDisplayed();
            if (status == false) {
                if (next.isDisplayed() == false) {
                    System.out.println("Element hiç bir sayfada bulunamadı.");
                    return status;
                }
                next.click();
            }
        }
        System.out.println("Element bulundu.");
        return status;
    }

    //Bilgisayarda uzantısını verdiğiniz klasordeki dosyalardan gönderdiğiniz ismi içinde içeriyorsa o dosyayı siler.
    public boolean deleteFile(String path, String fileName) throws IOException {

        boolean flag = false;
        File directory = new File(path);
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteDirectory(files[i]);
                        flag = true;
                    } else {
                        if (files[i].getName().toString().contains(fileName)) {
                            files[i].delete();
                            System.out.println("dosya silindi");
                            flag = true;
                        } else
                            System.out.println("Klasörde istenilen isimde dosya bulunamadı.");
                    }
                }
            } else
                System.out.println("Klasör boş.");
        }
        return flag;
    }

    // İşlem penceresi kapatma onay - popup
    @Step("Popup : İşlem penceresi kapatma onayi: \"{secim}\" ")
    public void islemPenceresiKapatmaOnayiPopup(String secim) {

        SelenideElement btnKapat = $(By.id("kapatButton"));
        SelenideElement btnIptal = $(By.id("kapatButton"));
        SelenideElement islemPenceresiKapatmaPopup = $(By.id("closeWindowConfirm"));

        if (islemPenceresiKapatmaPopup.isDisplayed()) {
            switch (secim) {
                case "Kapat":
                    btnKapat.click();
                    break;
                case "İptal":
                    btnIptal.click();
                    break;
            }
        }

    }

    // İşlem penceresi kapatma onay - popup
    @Step("Popup : İşlem penceresi kaydet: {secim}")
    public void islemPenceresiKaydetPopup(String secim) {

        SelenideElement islemKaydetPopup = $(By.id("saveOnCloseWindowConfirm"));
        SelenideElement btnEvet = $(By.id("kapatKaydetEvetButton"));
        SelenideElement btnHayir = $(By.id("kapatKaydetHayirButton"));
        SelenideElement btnIptal = $(By.id("kapatKaydetIptalButton"));

        switch (secim) {
            case "Evet":
                btnEvet.click();
                break;
            case "Hayır":
                btnHayir.click();
                break;
            case "İptal":
                btnIptal.click();
                break;
        }
    }

    //endregion

    @Step("Popup İşlem Onayı: {secim}")
    public void islemOnayi(String secim) {

        SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
        SelenideElement btnIslemOnayiHayir = $(By.id("baseConfirmationDialog:baseConfirmationDialogCancelButton"));
        btnIslemOnayiEvet.shouldBe(visible);

        switch (secim) {
            case "Evet":
                clickJs(btnIslemOnayiEvet);
                break;
            case "Hayır":
                clickJs(btnIslemOnayiHayir);
                break;
        }
    }


    @Step("Popup Silme Onayı: {secim}")
    public void silmeOnayi(String secim) {

        SelenideElement btnSilmeOnayiEvet = $("[id$='ekSilEvetButton']");
        SelenideElement btnSilmeOnayiHayir = $("['ekSilHayirButton']");

        switch (secim) {
            case "Evet":
                clickJs(btnSilmeOnayiEvet);
                break;
            case "Hayır":
                clickJs(btnSilmeOnayiHayir);
                break;
        }
    }

    //Dosyanın bilgisayara inip inmediğini kontrol eder.
    public boolean searchDownloadedFileWithName(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();
        Pattern y = Pattern.compile("[^0-9]");
        String s = null;
        SoftAssert sa = new SoftAssert();

        for (int i = 0; i < dir_contents.length; i++) {
            String file = dir_contents[i].getName().toString();
            s = "";
            Matcher m = y.matcher(file);
            while (m.find()) {
                s = s + m.group();
            }
//            sa.assertEquals(s,fileName,"Klasör "+ dir_contents[i].getName().toString() +"indirilmiştir.");
//            sa.assertNotEquals(s,fileName,"İstenilen dosya indirilmemiştir.");
//            assert s.equals(fileName) : "Klasör "+ dir_contents[i].getName().toString() + "indirilmiştir.";
//            assert s.equalsIgnoreCase(fileName) : "İstenilen dosya indirilmemiştir.";

            if (s.contains(fileName)) {
                System.out.println("dosya indirilmiştir.");
                Allure.addAttachment(dir_contents[i].getName().toString(), "raporu indirilmiştir");
                flag = true;
                break;
            } else
                Allure.addAttachment("Rapor Sonucu", "İstenilen dosya indirilememiştir.");
        }
        return flag;
    }


    //region Capabilities
    public String getOS() {
        Capabilities caps = getCapabilities();
        String platformName = caps.getCapability("platformName").toString();
        System.out.println("Operation System: " + platformName);
        return platformName;
    }

    public String getBrowserName() {
        String browserName = getCapabilities().getBrowserName();
        System.out.println("Browser Name : " + browserName);
        return browserName;
    }

    @Deprecated
    private String setDocPath() {

        // Get Browser name and version.
        Capabilities caps = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();
        // String browserName = caps.getBrowserName();
        // String browserVersion = caps.getVersion();
        Platform operationSystem = caps.getPlatform();
        System.out.println("Operation System: " + operationSystem.name());

        if (operationSystem.is(Platform.WINDOWS)) {
            docPath = "C:\\TestAutomation\\BelgenetFTA\\documents\\";
        } else if (operationSystem.is(Platform.XP)) {
            docPath = "C:\\TestAutomation\\BelgenetFTA\\documents\\";
        } else if (operationSystem.is(Platform.LINUX)) {
            //TODO: Linux pathi verilecek
            docPath = System.getProperty("user.name") + "/BelgenetFTA/documents";
        } else if (operationSystem.is(Platform.MAC)) {
            //TODO: Mac pathi verilecek
            docPath = System.getProperty("user.name") + "/BelgenetFTA/documents";
        }
        System.out.println("File path: " + docPath);
        return docPath;
    }

    public String getDocPath() {
        Capabilities caps = getCapabilities();
        Platform operationSystem = caps.getPlatform();

        String path = "";
        if (operationSystem.is(Platform.WINDOWS) || operationSystem.is(Platform.XP))
            path = TestData.docPathWindows;
        else
            path = TestData.docPathLinux;

        log.info("Documents path: " + path);
        return path;
    }

    public String getDownloadPath() {
        Capabilities caps = getCapabilities();
        Platform operationSystem = caps.getPlatform();
        String path = "";
        if (operationSystem.is(Platform.WINDOWS) || operationSystem.is(Platform.XP))
            path = TestData.docDownloadPathWindows;
        else
            path = TestData.docDownloadPathLinux;

        log.info("Downloads path: " + path);
        return path;
    }

    /**
     * Get Capabilities of EventFiringWebDriver
     */
    public Capabilities getCapabilities() {
        //This 'if' handle case when LoggingWebDriver.driver is instanceof EventFiringWebDriver .
        //In this case RemoteWebDriver is field within EventFiringWebDriver while EventFiringWebDriver don't support getting Capabilities
        WebDriver driver = WebDriverRunner.getWebDriver();

        if (driver instanceof EventFiringWebDriver
                && ((EventFiringWebDriver) driver).getWrappedDriver() instanceof HasCapabilities) {
            return ((HasCapabilities) ((EventFiringWebDriver) driver)
                    .getWrappedDriver()).getCapabilities();
        } else if (driver instanceof HasCapabilities) {
            return ((HasCapabilities) driver).getCapabilities();
        }
        throw new UnsupportedOperationException(
                "Underlying driver instance does not support capabilities");
    }

    public RemoteWebDriver getDriverAsRemoteWebDriver() {
        //This 'if' handle case when LoggingWebDriver.driver is instanceof EventFiringWebDriver .
        //In this case RemoteWebDriver is field within EventFiringWebDriver while EventFiringWebDriver don't support getting Capabilities
        WebDriver driver = WebDriverRunner.getWebDriver();

        if (driver instanceof EventFiringWebDriver
                && ((EventFiringWebDriver) driver).getWrappedDriver() instanceof HasCapabilities) {
            return (RemoteWebDriver) ((EventFiringWebDriver) driver)
                    .getWrappedDriver();
        } else if (driver instanceof HasCapabilities) {
            return (RemoteWebDriver) driver;
        }
        throw new UnsupportedOperationException(
                "Underlying driver instance does not support capabilities");
    }

    public int getRandomNumber(int startIndex, int endIndex) {
        return (new Random().nextInt((endIndex - startIndex) + 1) + startIndex);
    }

    //endregion


}
