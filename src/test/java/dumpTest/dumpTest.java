package dumpTest;

import com.codeborne.selenide.*;
import common.BaseTest;
import data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.*;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.EvrakHavaleKurallariYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KlasorYonetimiPage;
import pages.ustMenuPages.PulYonetimiPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


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
    public void caps() throws Exception {
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
    public void sorgulamaVefiltremeDeneme() throws Exception {
        login();

        new UstMenu().ustMenu("Form Şablon Yönetimi");
        $(Selectors.byText("Form Şablon Yönetimi")).shouldBe(visible);
        new SorgulamaVeFiltreleme($("#formSablonYonetimiListingForm"))
                .filtrelemeAlaniDoldur("Form Adı", "123");



        new EvrakHavaleKurallariYonetimiPage().openPage();
        new SorgulamaVeFiltreleme($("#havaleKuralYonetimiListingForm"))
                .filtrelemedeButonaTikla("Ara");
                /*.searchTable()
                    .findRowsInAllPages(1, exactText("TC-2069_1674108259301"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .first();
        e.$(".update-icon").click();*/



       /* new KlasorYonetimiPage().openPage();
        new SorgulamaVeFiltreleme($("#klasorYonetimiListingForm"))
            .filtrelemeCombolovAlaniDoldur("Birim", "Optiim")
                .filtrelemedeButonaTikla("Ara");*/

        new KlasorYonetimiPage().openPage();
        SelenideElement filter = $("#klasorYonetimiListingForm\\:filterPanel").shouldBe(visible);
//        Filtreleme f = new Filtreleme(filter);
//       new Filtreleme($x("//*[@id='klasorYonetimiListingForm:filterPanel']"))
       new Filtreleme($("#klasorYonetimiListingForm\\:filterPanel"))
//        f.filtrelemeCombolovAlaniDoldur("Birim", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
//                f.filtrelemeAlaniDoldur("Klasör Kodu", "123")
//                .filtrelemedeButonaTikla("Ara")
       ;

        SelenideElement b =  new SearchTable($("#klasorYonetimiListingForm\\:klasorTreeTable"))
                    .findRowsByText( "123")
                    .shouldHave(CollectionCondition.sizeGreaterThan(0))
                    .first();
        SelenideElement r = b.$x("button[span[contains(@class,'update-icon')]]");
        System.out.println(r.isDisplayed());
    }

    @Test(description = "", enabled = true)
    public void findFileInPath() throws Exception {

        Selenide.open("http://fiyat.mercedes-benz.com.tr/a-serisi");

//        RemoteWebDriver driver = (RemoteWebDriver) ((HasCapabilities)((EventFiringWebDriver) WebDriverRunner.getWebDriver()).getWrappedDriver());


        String href = $("a[class='btn downloadpdf']").attr("href");
       /* $("a[class='btn downloadpdf']").click();

        getDocPath();
        login();*/


//        if (capabilities.getBrowserName().equals("firefox")){
//            FirefoxProfile profile = new FirefoxProfile();
           /* FirefoxOptions options =  new FirefoxOptions();
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", getDownoladPath());*/

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
    public void solMenuTest() throws Exception {
        new MainPage().solMenu(SolMenuData.KurulIslemleri.KararIzleme);
    }

    @Test
    public void testName() throws Exception {
        new EvrakOlusturPage().openPage().bilgilerTabiAc();

        BelgenetElement el = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"))
                .selectLov("optiim");

        ElementsCollection col = el.titleItems();
        int q = col.size();

        String a1 = comboLov("input[id$='konuKoduLov:LovText']").lastSelectedLov().text();
        String a = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"))
                .selectLov("optiim").lastSelectedLov().text();
        String b = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText")).selectLov("optiim")
                .lastSelectedLov().text();
//String c = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText")).selectLov("optiim")
//        .

        System.out.println("!!!!!!-" + comboLov("input[id$='konuKoduLov:LovText']").lastSelectedLov());
//                .selectLov("010.01").lastSelectedLovTitle();

        $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:onayAkisiEkle")).click();
        comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovText"))
                .type("Optiim TEST").titleItems().filterBy(text("Optiim TEST3")).get(0).click();
//                .selectLov("Optiim TEST3");

        new EvrakOlusturPage().openPage().bilgilerTabiAc()
                .konuKoduSec("010.01");
        new EvrakOlusturPage().openPage().bilgilerTabiAc();

//        boolean b = comboLov("input[id$='konuKoduLov:LovText']").type("111111111").isEmpty();
        int i = comboLov("input[id$='konuKoduLov:LovText']").type("010.01")
                .titleItems().filterBy(text("Kanunlar")).size();


//        new UstMenu().ustMenu("Evrak İşlemleri", "Evrak Oluştur");
        new PulYonetimiPage().openPage();


//        $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).click();
        SelenideElement element = $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"));
//        element.sendKeys("\n");
        element.click();
//        element.pressEnter();
//        $(By.id("topMenuForm2:ust:0:ustMenuEleman")).pressEnter();
//        $(By.id("pulYonetimiEditorForm:savePulButton")).sendKeys("\n");
////        Dimension size = element.getSize();
//        Actions actions = new Actions(WebDriverRunner.getWebDriver());
//
//        actions.moveToElement(element).click(element).build().perform();
////        actions.moveToElement(element, size.getWidth() - 1, size.getHeight() - 1)
////                .click().build().perform();
////        clickOnInvisibleElement($(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")));
//
//
//
////        new Actions(WebDriverRunner.getWebDriver())
////                .moveToElement($(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"))
////                        , $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).getSize().getWidth() - 1
////                        , $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton")).getSize().getHeight() - 1)
////                .click().build().perform();
//
//
//        //        group.$(By.partialLinkText(menuText)).click();
////
////        System.out.println("Text: " + $("button[id='topMenuForm:userMenuButton_button']").text());
////        System.out.println("InnerText: " + $("button[id='topMenuForm:userMenuButton_button']").innerText());
////        takeScreenshot();*/
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
}
