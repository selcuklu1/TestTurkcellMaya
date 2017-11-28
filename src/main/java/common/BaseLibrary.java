package common;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class BaseLibrary {

    protected static String winHandleBefore=null;

    //<editor-fold desc="Allure screenshooter">
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
    //</editor-fold>

    //<editor-fold desc="Page loading methods">

    /**
     * Waiting to JS and jQuery ready state and object with class "loading" to disappear.
     * Used in DriverInvokeListener beforeFindBy method
     *
     * @param
     */
    public void waitForJS() {
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
    }

    public void waitForJSreadyState() {
        Wait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return executeJavaScript("return document.readyState").equals("complete");
            }
        });
    }

    public void waitForLoadingToDisappear(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        try {
            new WebDriverWait(driver, Configuration.timeout / 1000, 200).
                    until(invisibilityOfElementLocated(By.className("loading")));
//            System.out.println("Loading: Ok");
        } catch (Exception e) {
        }
        driver.manage().timeouts().implicitlyWait(Configuration.timeout, TimeUnit.MILLISECONDS);
    }

    public void waitForLoading(WebDriver driver) {
        waitForJS();
        waitForLoadingToDisappear(driver);
    }
    //</editor-fold>

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

    //Üstyazı dosyasını ekler
    public void ustYaziUploadFile(String pathToFile) {
        try {
            $(By.xpath("//input[@class='ustYaziUploadClass']")).sendKeys(pathToFile);
//            LogPASS("Dosya Yuklendi.");
        } catch (Exception e) {
//            logger.error("Error in attaching file.s : " + e);
//            LogFAIL("Error in attaching file.s : " + e);
            System.out.println("Error in attaching file.s  : " + e);

            throw new RuntimeException(e);
        }
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
    public void deleteFile(String pathToFile) {
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

        WebElement element = null;
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
    protected WebElement findElementOnTableByColumnInput(SelenideElement byTable, int columnIndex, String columnInput) {
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

    public String getIntegerInText(By by) {
        String x = WebDriverRunner.getWebDriver().findElement(by).getText();
        Pattern y = Pattern.compile("\\d+");
        Matcher m = y.matcher(x);
        m.find();
        String number = m.group();
        System.out.println(number);

        return number;
    }

    public String windowHandleBefore() throws InterruptedException {
        // Store the current window handle
        winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
        return winHandleBefore;
    }

    public void switchToNewWindow() throws InterruptedException {
        Thread.sleep(6000);
        // Perform the click operation that opens new window
        // Switch to new window opened
        for (String winHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            WebDriverRunner.getWebDriver().switchTo().window(winHandle);
        }
    }

    public void switchToDefaultWindow() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverRunner.getWebDriver().close();
        // driver.switchTo().defaultContent();
        WebDriverRunner.getWebDriver().switchTo().window(winHandleBefore);
    }


    public String cssSE(String element, String attribute, String startsWith, String endsWith) {

        if (element != "" || element == null) {

            return "["+attribute+"^='']["+attribute+"$='']";

        } else {

            return element+"["+attribute+"^='']["+attribute+"$='']";

        }

    }

    @Step("[\"{0}\"] alanının değeri [\"{0}\"] olmalı.")
    public void alanDegeriKontrolEt(SelenideElement element, String value, boolean shouldHaveValue, boolean exactText){
        if(shouldHaveValue == true){
            if (exactText == true)
                element.shouldHave(Condition.exactValue(value));
            else
                element.shouldHave(Condition.value(value));

        }
        else
        {
            if (exactText == true)
                element.shouldNotHave(Condition.exactValue(value));
            else
                element.shouldNotHave(Condition.value(value));

        }
    }

    public boolean findElementOnTableAllPages(SelenideElement element) {
        SelenideElement next = $(("[class='ui-paginator-next ui-state-default ui-corner-all']"));

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
}
