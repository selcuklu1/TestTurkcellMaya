package pages.galen;

import com.codeborne.selenide.WebDriverRunner;
import com.galenframework.api.Galen;
import com.galenframework.api.GalenPageDump;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 8.01.2018
 * Açıklama:
 */
public class GalenControl extends BaseLibrary {

    private String pageSpecPath = "src/test/resources/specs/";
    private String reportFolderPath = "galenReports/";
    private String dumpFolderPath = "galenDumps/";


    /**
     * @param testName
     * @see "/src/test/resources/testName/" path must be exist with
     * "testName".gspec, dump path will be generated in
     * "/src/test/resources/testName/dump" path
     */
    public void galenGenerateDump(String testName) {
        try {
            Dimension browserSize = new Dimension(1280, 800);
            WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);

            new GalenPageDump(testName).dumpPage(WebDriverRunner.getWebDriver(),
                    pageSpecPath + testName + "/" + testName + ".gspec",
                    dumpFolderPath);

            maximazeBrowser();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Galen dumpPage error: " + e.getMessage());
        }
    }

    @Step("\"{testName}\" görsel kontrol")
    public void galenLayoutControl(String testName) throws IOException {

        Dimension browserSize = new Dimension(1280, 800);
        WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);

        // Create a layoutReport object
        // checkLayout function checks the layout and returns a LayoutReport
        // object
        LayoutReport layoutReport = Galen.checkLayout(WebDriverRunner.getWebDriver()
                , pageSpecPath + testName + "/" + testName + ".gspec",
                Arrays.asList("desktop"));

        // Create a tests list
        List<GalenTestInfo> galenTests = new LinkedList<GalenTestInfo>();

        // Create a GalenTestInfo object
        GalenTestInfo galenTest = GalenTestInfo.fromString(testName + " layout");

        // Get layoutReport and assign to test object
        galenTest.getReport().layout(layoutReport, "Check " + testName + " layout");

        // Add test object to the tests list
        galenTests.add(galenTest);

        // Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();

        // Create a report under /target folder based on tests list
        htmlReportBuilder.build(galenTests, "galenReports/" + testName + "/");

        // If layoutReport has errors Assert Fail
        if (layoutReport.errors() > 0) {
            //ExtentTestManager.getTest().log(LogStatus.FAIL, "Galen Layout test failed.");
            Allure.addAttachment("Galen Layout test failed", String.valueOf(layoutReport.errors()));
            System.out.println("Galen Layout test failed.");
            Assert.fail("Layout test failed");
        }

        maximazeBrowser();
    }

}
