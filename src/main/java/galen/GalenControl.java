package galen;

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

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

            waitForLoadingJS(WebDriverRunner.getWebDriver());

            new GalenPageDump(testName).dumpPage(WebDriverRunner.getWebDriver(),
                    pageSpecPath + testName + "/" + testName + "_objects.spec",
                    dumpFolderPath);

            maximazeBrowser();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Galen dumpPage error: " + e.getMessage());
        }
    }

    @Step("\"{testName}\" görsel kontrol")
    public void galenLayoutControl(String testName) throws IOException {
        Allure.addAttachment("Layout report link", "file:///Users/ilyas/WorkspaceJava/Git/BelgenetFTA/galenReports/TS0577/report.html");

        Dimension browserSize = new Dimension(1440, 900);
        WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);

        // Create a layoutReport object
        // checkLayout function checks the layout and returns a LayoutReport
        // object
        LayoutReport layoutReport = Galen.checkLayout(WebDriverRunner.getWebDriver()
                , pageSpecPath + testName + "/" + testName + "_controls.spec",
                Arrays.asList());

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

    public void galenLayoutControl2(String testName) throws IOException {

        Dimension browserSize = new Dimension(1280, 800);
        WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);

        // Create a layoutReport object
        // checkLayout function checks the layout and returns a LayoutReport
        // object
        LayoutReport layoutReport = Galen.checkLayout(WebDriverRunner.getWebDriver()
                , pageSpecPath + testName + "/" + testName + ".spec",
                Arrays.asList());

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

    public void setTextValuesToGalenSpec(String testName, Map<String, String> params){
        String filePath = pageSpecPath + testName + "/" + testName + "_temp.spec";
        String specContent = getFileContent(filePath);
        for (Map.Entry<String,String> entry : params.entrySet()){
            specContent = specContent.replace("${" + entry.getKey() + "}", entry.getValue());
        }
            /*System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        params.forEach((name,value)-> specContent.replace("${" + name + "}", value));*/

        writeContentToFile(pageSpecPath + testName + "/"+ testName + "_objects.spec", specContent);
    }

    public void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getFileContent(String filePath) {
        File file = new File(filePath);
        String content = "";
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
                content = content + line + System.lineSeparator();
                line = reader.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return content;
    }

    public void writeContentToFile_o(String filePath, String content) {
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(filePath);
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void writeContentToFile(String filePath, String content) {
        //File file = new File(filePath);
        FileWriter writer = null;
        try
        {
            writer = new FileWriter(filePath);
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
