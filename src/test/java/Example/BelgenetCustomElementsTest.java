package Example;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FiltrelerPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageData.SolMenuData.IslemBekleyenEvraklar;

//import pages.pageComponents.belgenetElements.BelgenetElement;


public class BelgenetCustomElementsTest extends BaseTest {

    BasePage page = new BasePage();

    @BeforeClass
    public void setUpFramework() {
        page.loginPage().login();
    }

    public class TestPage{
        BelgenetElement combo = comboLov(By.id("birimYonetimiFilterForm:accordionPanel:birimLov:LovText"));

        public TestPage selectCombo(){
            combo.selectComboLov("opt");
            return this;
        }

        public boolean isSelected(){
            return combo.isLovSelected();
        }
    }

    @Test(groups = {"FrameworkTest"}, enabled = true)
    public void comboBoxTest() {
        page.solMenu(IslemBekleyenEvraklar.GelenEvraklar);
        new FiltrelerPage().filtrelerAc();

        By cmbFiltreleLocator = By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label");

        comboBox(cmbFiltreleLocator)
                .selectComboBox("SÜRELİ EVRAKLAR")
                .shouldHave(text("SÜRELİ EVRAKLAR"));

        BelgenetElement cmbFiltre = comboBox(cmbFiltreleLocator);
        cmbFiltre.selectComboBox("TÜMÜ", false).shouldHave(text("TÜMÜ"));
        Assert.assertEquals(cmbFiltre.getComboBoxValues().contains("SÜRELİ EVRAKLAR"), true);



//        BelgenetElement element = comboLov("[id='mainInboxForm:inboxDataTable:filtersAccordion'] h3 a");
//        pages.ustMenuAc("Birim Yönetimi");
//        new TestPage().selectCombo();
//        comboLov(By.id("birimYonetimiFilterForm:accordionPanel:birimLov:LovText")).selectComboLov("OPT")
//                .shouldHave(text("OPTİİM"));
//
//        System.out.println("selected: " + new TestPage().isSelected());

//        pages.ustMenuAc("Evrak Oluştur");
//
//        String locator = "[id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText']";
//
//
//        BelgenetElement c = comboLov(locator).selectComboLov("010.10");
//        System.out.println("lastSelectedLovTitleText: " + c.lastSelectedLovTitleText());
//        c.lastSelectedLovDetail().shouldHave(text("Mevzuat İşleri"));
//        System.out.println("shouldHave: " + 1);
//        BelgenetElement e = comboLov(locator).selectComboLov("010.10");
//        System.out.println("visible: " + e.is(visible));
//        System.out.println("id: " + e.attr("id"));

//        System.out.println("id: " + comboLov(locator).selectComboLov("010.10").attr("id"));
//        BelgenetElement elem = comboLov(locator).selectComboLov("010.10");
//        System.out.println("id: " + elem.attr("id"));
//        elem.shouldHave(Condition.text("010.10"));
//        comboLov(locator).selectComboLov("010.10");
//                shouldHave(Condition.text("010.10"));

//        System.out.println("title: " + comboLov(locator).lastSelectedLovTitleText());
//        System.out.println("detail: " + comboLov(locator).lastSelectedLovDetailText());
    }

    @Test(groups = {"FrameworkTest"}, enabled = true)
    public void comboLovTest() {
        //-----------------------------------
        page.ustMenu("Evrak Oluştur");
        String locator = "[id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText']";
        comboLov(locator).selectComboLov("010.10")
                .shouldHave(Condition.text("010.10"));

        System.out.println("title: " + comboLov(locator).lastSelectedLovTitleText());
        System.out.println("detail: " + comboLov(locator).lastSelectedLovDetailText());

        //-----------------------------------
        page.ustMenu("Birim Yönetimi");
        BelgenetElement cmlBirim = comboLov("[id='birimYonetimiFilterForm:accordionPanel:birimLov:LovText");

        cmlBirim.selectComboLov("opt")
                .shouldHave(text("optiim"));
        System.out.println("1. Birim seçili mi?: " + cmlBirim.isLovSelected());
        cmlBirim.clearLastSelectedLov();
        System.out.println("1. Birim seçili mi?: " + cmlBirim.isLovSelected());

        System.out.println("\n================\n");

        cmlBirim.selectComboLov("opt");
        System.out.println("2. Birim seçili mi?: " + cmlBirim.isLovSelected());
        cmlBirim.lastSelectedLovTitle().shouldHave(text("OPT"));
        if (cmlBirim.lastSelectedLovTitleText().equalsIgnoreCase("OPTİİM BİRİM"))
            cmlBirim.selectComboLov("");

        cmlBirim.selectComboLov("opt");
        cmlBirim.lastSelectedLovTitle().shouldHave(exactText("OPTİİM BİRİM"));
        System.out.println("2. Birim seçili mi?: " + cmlBirim.isLovSelected());
        //-----------------------------------

        //PageObject kullanım.
        new TestPage().selectCombo().isSelected();

    }
}
