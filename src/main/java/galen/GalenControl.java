package galen;

import com.galenframework.api.Galen;
import com.galenframework.api.GalenPageDump;
import com.galenframework.config.GalenProperty;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.speclang2.pagespec.SectionFilter;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Label;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.galenframework.config.GalenConfig.getConfig;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 8.01.2018
 * Açıklama:
 */
public class GalenControl {

    public static String specPath = "src/test/resources/galen";
    //public static String specFileName;
    public static String dumpFolderPath;
    public static String reportPath = getConfig().getStringProperty(GalenProperty.TEST_JAVA_REPORT_OUTPUTFOLDER);

    public GalenControl() {
        getConfig().setProperty(GalenProperty.GALEN_BROWSER_PAGELEMENT_AREAFINDER, "NATIVE");
        getConfig().setProperty(GalenProperty.GALEN_RANGE_APPROXIMATION, "5");
        getConfig().setProperty(GalenProperty.GALEN_BROWSER_VIEWPORT_ADJUSTSIZE, "true");
        getConfig().setProperty(GalenProperty.GALEN_LOG_LEVEL, "1");
    }

    public GalenControl generateDump(String testName, Map<String, Object> params, Dimension... pageDimension) {
        /*Dimension browserSize = new Dimension(1440, 900);
            WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);
            WebDriverRunner.getWebDriver().manage().window().setPosition(new Point(0,0));*/

        Dimension currentDimension = getWebDriver().manage().window().getSize();
        if (pageDimension.length > 0) {
            getWebDriver().manage().window().setSize(pageDimension[0]);
            getWebDriver().manage().window().setPosition(new Point(0, 0));
        }

        try {

            String path = specPath + "/" + testName;
            String specFileName = path + "/" + testName + ".gspec";
            /*specPath += "/" + testName;
            specFileName = specPath + "/" + testName + ".gspec";*/
            dumpFolderPath = path + "/dump";

            GalenPageDump galenPageDump = new GalenPageDump(testName);
            galenPageDump.setJsVariables(params).dumpPage(getWebDriver(), specFileName, dumpFolderPath);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pageDimension.length > 0)
                getWebDriver().manage().window().setSize(currentDimension);
        }
        return this;
    }

    @Step("\"{testName}\" görsel kontrol")
    public GalenControl layoutControl(String testName, Map<String, Object> params, Dimension... pageDimension) throws IOException {
        String path = specPath + "/" + testName;
        String specFileName = path + "/" + testName + ".gspec";

        Allure.addLabels(new Label().withName("Layout"));
        Allure.addAttachment("Layoun specs:", new FileInputStream(new File(specFileName)));

        Dimension currentDimension = getWebDriver().manage().window().getSize();
        if (pageDimension.length > 0) {
            getWebDriver().manage().window().setSize(pageDimension[0]);
            getWebDriver().manage().window().setPosition(new Point(0, 0));
        }

        //parsePageSpec
        List<String> list = new LinkedList<>();
        list.add("all");
        SectionFilter sectionFilter = new SectionFilter(list, new LinkedList<>());
        LayoutReport layoutReport = Galen.checkLayout(
                getWebDriver()
                , specFileName
                , sectionFilter
                , null
                , params);

        List<GalenTestInfo> galenTests = new LinkedList<>();

        // Create a GalenTestInfo object
        GalenTestInfo galenTest = GalenTestInfo.fromString(testName);

        // Get layoutReport and assign to test object
        galenTest.getReport().layout(layoutReport, "check " + testName + " layout");

        // Add test object to the tests list
        galenTests.add(galenTest);

        // Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();

        // Create a report under /target folder based on tests list
        htmlReportBuilder.build(galenTests, reportPath);

        Assert.assertTrue(layoutReport.errors() == 0, "Layout kontol: " + layoutReport.errors());

        if (pageDimension.length > 0)
            getWebDriver().manage().window().setSize(currentDimension);
        return this;
    }

}
