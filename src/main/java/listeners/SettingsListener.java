package listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.TestNG;

public class SettingsListener extends TestNG.ExitCodeListener {

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        registerDriverEvenListener();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test: " + result.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        takeScreenshotOnFail();
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        super.onConfigurationFailure(itr);
        takeScreenshotOnFail();
    }

    @Attachment(value = "Fail screenshot", type = "image/png")
    public byte[] takeScreenshotOnFail() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void registerDriverEvenListener() {
        //<editor-fold desc="Register DriverEventListener to implement loading pages wait">
        WebDriverRunner.setWebDriver((new EventFiringWebDriver(WebDriverRunner.getWebDriver()).register(new DriverEventListener())));
        //</editor-fold>

    }
}
