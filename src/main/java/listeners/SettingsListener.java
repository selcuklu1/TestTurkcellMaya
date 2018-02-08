package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;


public class SettingsListener extends ExitCodeListener {

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        registerDriverEvenListener();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Started: " + result.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        /*if (!WebDriverRunner.getWebDriver().toString().contains("(null)"))
            takeScreenshotOnFail();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Failed: " + result.getName());
        System.out.println("///////////////////////////////////////////////////////");*/
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        super.onConfigurationFailure(itr);
        /*if (!WebDriverRunner.getWebDriver().toString().contains("(null)"))
            takeScreenshotOnFail();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Configuration Failed: " + itr.getName());
        System.out.println("///////////////////////////////////////////////////////");*/
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshotOnFail() {
        byte[] bytes = new byte[]{};
        try {
            bytes = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            System.out.println("Error takeScreenshot:" + e.getMessage());
        }
        return bytes;
    }

    public void registerDriverEvenListener() {
        //<editor-fold desc="Register DriverEventListener to implement loading pages wait">
        WebDriverRunner.setWebDriver((new EventFiringWebDriver(WebDriverRunner.getWebDriver()).register(new DriverEventListener())));
        //</editor-fold>

    }
}
