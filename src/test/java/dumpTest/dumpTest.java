package dumpTest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.TestData;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.EvrakOlusturPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static java.util.regex.Pattern.DOTALL;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;


public class dumpTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    public void clickOnInvisibleElement(SelenideElement element) {

        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        executeJavaScript(script, element);
    }


    @BeforeMethod
    public void caps() {
       /* DesiredCapabilities caps;
        if (Configuration.browser.equals("chrome")){
            caps = DesiredCapabilities.chrome();
            caps.setPlatform(Platform.MAC);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory",  TestData.docDownloadPathLinux);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            WebDriverRunner.setWebDriver(new ChromeDriver(options));
        }*/

        Configuration.timeout = 5000;
        Configuration.collectionsTimeout = 5000;
    }

    @Test(description = "", enabled = true)
    public void sorgulamaVefiltremeDeneme() {
        login();


    }

    @Test(description = "", enabled = true)
    public void findFileInPath() {

        Selenide.open("http://fiyat.mercedes-benz.com.tr/a-serisi");

//        RemoteWebDriver driver = (RemoteWebDriver) ((HasCapabilities)((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver());


        String href = $("a[class='btn downloadpdf']").attr("href");
       /* $("a[class='btn downloadpdf']").click();

        getUploadPath();
        login();*/


//        if (capabilities.getBrowserName().equals("firefox")){
//            FirefoxProfile profile = new FirefoxProfile();
           /* FirefoxOptions options =  new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", getDownloadPath());*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browser.download.folderList", 2);
        capabilities.setCapability("browser.download.dir", TestData.docDownloadPathLinux);

        Capabilities caps = ((HasCapabilities) ((EventFiringWebDriver) WebDriverRunner.getWebDriver())
                .getWrappedDriver())
                .getCapabilities().merge(capabilities);
            /*FirefoxDriver driver = (FirefoxDriver) ((HasCapabilities)((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver());
            driver = new FirefoxDriver(options);*/

//            WebDriverRunner.setWebDriver(d);
//        }

        /*profile['browser.download.folderList'] = 2 # custom location
        profile['browser.download.dir'] = download_directory
        profile['browser.helperApps.neverAsk.saveToDisk'] = "text/csv,application/pdf"*/


//        File file = new File(filePath);
//        Path path = file.toPath();
//        Files.find(path, 1, (dir, basicFileAttributes) -> path.toFile().getName().matches(".*." + ext));
    }

    @Test
    public void solMenuTest() {
        new MainPage().solMenu(SolMenuData.KurulIslemleri.KararIzleme);
    }

    @Test(description = "", enabled = true)
    public void selectLovTexts() throws Exception {
        login();
        OlurYazisiOlusturPage olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        boolean b = comboLov("input[id$='konuKoduLov:LovText']").selectLov("010","Kanunlar").isLovSelected();
        comboLov("input[id$='eklenecekKlasorlerLov:LovText']").selectLov();


        $("button[id$=onayAkisiEkle]").click();

        comboLov("input[id$='akisAdimLov:LovText']")
                .selectLov("Zübeyde TEKİN", "Ankara İl Müdürü", "AD MÜDÜRLÜĞÜ", "YGD");

        comboLov("input[id$='akisAdimLov:LovText']")
                .selectLov("Optiim TEST2");//, "Ağ (Network) Uzman Yardımcısı", "Optiim Birim", "YGD");

        comboLov("input[id$='akisAdimLov:LovText']")
                .selectLov("Optiim TEST");//,"Optiim TEST [Ağ (Network) Uzman Yardımcısı]", "Optiim Birim", "YGD");



        comboLov("input[id$='eklenecekKlasorlerLov:LovText']").selectLov();

    }

    @Test(enabled = false, dataProvider = "zorunluAlanlar")
    public void test1(String fieldName, Object field) {
//        if (field instanceof BelgenetElement) {
//            BelgenetElement dog = (BelgenetElement) field;
//        }
//        else{
        SelenideElement dog = (SelenideElement) field;
//        }


//        System.out.println("!!!!!!!!-" + field.toString());
        dog.shouldBe(BelgentCondition.required);

    }

    @DataProvider
    public Object[][] zorunluAlanlar1() {
        return new Object[][]{
//                {"Konu Kodu", tabBilgiler.getCmlKonuKodu()}
//                , {"Konu", tabBilgiler.getTxtKonu()}
        };
    }



    @Test(description = "", enabled = true)
    public void aaa() throws Exception {

        String source1="Optiim TEST2 [Danışman]\n" +
                "Optiim Alt Birim1\n" +
                "Optiim Birim";
        String source2="Optiim TEST2 [Ağ (Network) Uzman Yardımcısı]\n" +
                "Optiim Birim\n" +
                "YGD";
        String source3="Optiim TEST [Ağ (Network) Uzman Yardımcısı]\n" +
                "Optiim Birim\n" +
                "YGD";
        String subterm_1 = "Optiim TEST";

        System.out.println(isContain(source1,subterm_1));
        System.out.println(isContain(source2,subterm_1));
        System.out.println(isContain(source3,subterm_1));

    }

    private boolean isContain(String source, String subItem){
        String pattern = "\\b"+subItem+"\\b";
        Pattern p=Pattern.compile(pattern);
        Matcher m=p.matcher(source);
        return m.find();
    }


    public boolean matches(String text, String regex) {
        return Pattern.compile(".*" + regex + ".*", DOTALL).matcher(text).matches();
    }

    public boolean contains(String text, String subtext) {
        return reduceSpaces(text.toLowerCase()).contains(reduceSpaces(subtext.toLowerCase()));
    }

    public boolean containsCaseSensitive(String text, String subtext) {
        return reduceSpaces(text).contains(reduceSpaces(subtext));
    }

    public boolean equals(String text, String subtext) {
        return reduceSpaces(text).equalsIgnoreCase(reduceSpaces(subtext.toLowerCase()));
    }

    public boolean equalsCaseSensitive(String text, String subtext) {
        return reduceSpaces(text).equals(reduceSpaces(subtext));
    }

    String reduceSpaces(String text) {
        return text.replaceAll("[\\[\\]\\s\\n\\r\u00a0]+", " ").trim();
    }
}
