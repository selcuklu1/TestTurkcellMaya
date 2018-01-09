package Example;//import com.galenframework.api.Galen;
//import com.galenframework.api.GalenPageDump;
//import com.galenframework.reports.GalenTestInfo;
//import com.galenframework.reports.HtmlReportBuilder;
//import com.galenframework.reports.model.LayoutReport;

import com.codeborne.selenide.WebDriverRunner;
import com.galenframework.api.Galen;
import com.galenframework.api.GalenPageDump;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import common.BaseTest;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GalenExampleTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod(Method method) {

        /*// Go to swtestacademy.com
        driver.get("http://www.swtestacademy.com/");
        driver.manage().window().setSize(new Dimension(1200, 800));*/
    }

    @Test
    public void homePageLayoutTest(Method method) throws IOException {

        galenGenerateDump("homePageLayoutTest");
        galenLayoutControl("homePageLayoutTest");

    }

    /**
     *
     * @param testName
     * @see "/src/test/resources/testName/" path must be exist with
     *      "testName".gspec, dump path will be generated in
     *      "/src/test/resources/testName/dump" path
     */
    public void galenGenerateDump(String testName) {
        try {
            new GalenPageDump(testName).dumpPage(WebDriverRunner.getWebDriver(),
                    "src/test/resources/specs/" + testName + "/" + testName + ".gspec",
                    "src/test/resources/specs/" + testName + "/dump");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void galenLayoutControl(String testName) throws IOException {

        // Create a layoutReport object
        // checkLayout function checks the layout and returns a LayoutReport
        // object
        LayoutReport layoutReport = Galen.checkLayout(WebDriverRunner.getWebDriver(), "src/test/resources/specs/homepage.gspec",
                Arrays.asList("desktop"));

        // Create a tests list
        List<GalenTestInfo> galenTests = new LinkedList<GalenTestInfo>();

        // Create a GalenTestInfo object
        GalenTestInfo galenTest = GalenTestInfo.fromString("testName layout");

        // Get layoutReport and assign to test object
        galenTest.getReport().layout(layoutReport, "check testName layout");

        // Add test object to the tests list
        galenTests.add(galenTest);

        // Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();

        // Create a report under /target folder based on tests list
        htmlReportBuilder.build(galenTests,
                System.getProperty("user.dir") + "/test-output/GalenReports/" + testName + "/");

        // If layoutReport has errors Assert Fail
        if (layoutReport.errors() > 0) {
            //ExtentTestManager.getTest().log(LogStatus.FAIL, "Galen Layout test failed.");
            Allure.addAttachment("Galen Layout test failed", String.valueOf(layoutReport.errors()));
            System.out.println("Galen Layout test failed.");
            Assert.fail("Layout test failed");
        }
    }
}
