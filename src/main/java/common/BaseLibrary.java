package common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;


public class BaseLibrary {

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
                return (Boolean) executeJavaScript("return document.readyState").equals("complete");
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





    public static String clearTurkishChars(String str) {
        String ret = str;
        char[] turkishChars = new char[] {0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[] {'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }



    public void uploadFile(String pathToFile) {
        try {
            $(By.xpath("//input[@class='dz-hidden-input']")).sendKeys(pathToFile);
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

    public String randomNumber(int length) {
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

    public String createRandomText(int textSize) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < textSize; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();

        return output;
    }

    public String getSysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
        String sysDate = dtf.format(now);

        return sysDate;
    }

    public String getSysDateForKis() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); // 2016/11/16 12:08:43
        String sysDate = dtf.format(now);

        return sysDate;
    }

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
}
