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
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.io.*;
import java.util.*;

import static com.codeborne.selenide.Selenide.sleep;

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
        Locale defaultLocal = Locale.getDefault();
//        Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(new Locale("en", "TR"));
        try {
            /*Dimension browserSize = new Dimension(1440, 900);
            WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);
            WebDriverRunner.getWebDriver().manage().window().setPosition(new Point(0,0));*/
            //waitForLoadingJS(WebDriverRunner.getWebDriver());
            sleep(10000);

            new GalenPageDump(testName).dumpPage(WebDriverRunner.getWebDriver(),
                    pageSpecPath + testName + "/" + testName + "_objects.gspec",
                    dumpFolderPath);

            maximazeBrowser();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Galen dumpPage error: " + e.getMessage());
        }finally {
            Locale.setDefault(defaultLocal);
        }
    }

    @Step("\"{testName}\" görsel kontrol")
    public void galenLayoutControl(String testName) throws IOException {

        Locale defaultLocal = Locale.getDefault();
        //Locale turkishLocal = new Locale("tr", "TR");
        Locale.setDefault(new Locale("en", "TR"));


        Allure.addAttachment("Layout report link", "galenReports/TS0577/report.html");

       /* Dimension browserSize = new Dimension(1440, 900);
        WebDriverRunner.getWebDriver().manage().window().setSize(browserSize);
        WebDriverRunner.getWebDriver().manage().window().setPosition(new Point(0,0));*/
        sleep(10000);
        //waitForLoadingJS(WebDriverRunner.getWebDriver());
        // Create a layoutReport object
        // checkLayout function checks the layout and returns a LayoutReport
        // object
        LayoutReport layoutReport = Galen.checkLayout(WebDriverRunner.getWebDriver()
                , pageSpecPath + testName + "/" + testName + "_controls.gspec", Arrays.asList());
        //Collections.emptyList());
//

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

        Locale.setDefault(defaultLocal);
        // If layoutReport has errors Assert Fail
        Locale.setDefault(defaultLocal);
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
                , pageSpecPath + testName + "/" + testName + ".gspec",
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

        Locale.setDefault(new Locale("en", "TR"));

        String filePath = pageSpecPath + testName + "/" + testName + "_temp.gspec";
        System.out.println("===================================================");
        System.out.println("GALEN SPECS");
        String specContent = getFileContent(filePath);
        System.out.println("===================================================");
        System.out.println("CONTENT");
        System.out.println(specContent);
        System.out.println("===================================================");

        for (Map.Entry<String,String> entry : params.entrySet()){
            System.out.println("VALUE: " + entry.getValue());
            specContent = specContent.replace("${" + entry.getKey() + "}", entry.getValue());
        }
            /*System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        params.forEach((name,value)-> specContent.replace("${" + name + "}", value));*/

        writeContentToFile(pageSpecPath + testName + "/"+ testName + "_objects.gspec", specContent);
    }

    public void setTextValuesToGalenSpec(String testName, String[][] params){

        Locale.setDefault(new Locale("en", "TR"));

        String filePath = pageSpecPath + testName + "/" + testName + "_temp.gspec";
        System.out.println("===================================================");
        System.out.println("GALEN SPECS");
        String specContent = getFileContent(filePath);
        System.out.println("===================================================");
        System.out.println("CONTENT");
        System.out.println(specContent);
        System.out.println("===================================================");

        for (String[] p : params){
            System.out.println("VALUE: " + p[1]);
            specContent = specContent.replace("${" + p[0] + "}", p[1]);
        }
            /*System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        params.forEach((name,value)-> specContent.replace("${" + name + "}", value));*/

            writeContentToFile(pageSpecPath + testName + "/"+ testName + "_objects.gspec", specContent);

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

        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            out.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }

        /*FileWriter writer = null;
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
        }*/
    }

}
