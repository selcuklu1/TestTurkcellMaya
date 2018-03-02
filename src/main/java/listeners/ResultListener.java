package listeners;

import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener2;

//TestNG.ExitCodeListener {
public class ResultListener extends BaseLibrary implements IResultListener2 {


    /*@Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        //registerDriverEvenListener();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Started: " + result.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        if (!WebDriverRunner.getWebDriver().toString().contains("(null)"))
            takeScreenshotOnFail();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Failed: " + result.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        super.onConfigurationFailure(itr);
        if (!WebDriverRunner.getWebDriver().toString().contains("(null)"))
            takeScreenshotOnFail();
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Configuration Failed: " + itr.getName());
        System.out.println("///////////////////////////////////////////////////////");
    }*/

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

    @Override
    public void beforeConfiguration(ITestResult tr) {

    }

    @Override
    public void onConfigurationSuccess(ITestResult itr) {

    }

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Configuration Failed: " + itr.getTestName() + "    " + itr.getName());
        System.out.println("///////////////////////////////////////////////////////");

        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Test Configuration Failed: " + itr.getMethod().getMethodName());
        System.out.println("Test Class: " + itr.getInstanceName());
        System.out.println("");
        System.out.println("STATUS: failed");
        System.out.println("");
        if (itr.getThrowable() != null) {
            System.out.println("");
            System.out.println("ERROR: " + itr.getThrowable().getMessage());
        }
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////");

        if (WebDriverRunner.hasWebDriverStarted())
            takeScreenshot();
    }

    @Override
    public void onConfigurationSkip(ITestResult itr) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    @Attachment(value = "Page screenshot", type = "image/png")
    public void onTestFailure(ITestResult result) {
            try {
                ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
            }
            catch (Exception e){
                System.out.println("Take Screenshot error: " + e);
            }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
