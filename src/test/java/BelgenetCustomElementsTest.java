
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import common.BasePage;
import common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import pageComponents.belgenetElements.BelgenetElement;
import pageComponents.belgenetElements.BelgenetElement;
import pageComponents.belgenetElements.BelgenetFramework;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pageData.SolMenuData.*;


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
            return combo.isComboLovSelected();
        }
    }

    @Test(groups = {"FrameworkTest"}, enabled = true)
    public void comboBoxTest() {
        page.solMenu(IslemBekleyenEvraklar.GelenEvraklar);
        page.mainPage().filtrelerAc();

        By cmbFiltreleLocator = By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label");

        comboBox(cmbFiltreleLocator)
                .selectComboBox("SÜRELİ EVRAKLAR")
                .shouldHave(text("SÜRELİ EVRAKLAR"));

        BelgenetElement cmbFiltre = comboBox(cmbFiltreleLocator);
        cmbFiltre.selectComboBox("TÜMÜ", false).shouldHave(text("TÜMÜ"));
        Assert.assertEquals(cmbFiltre.getComboBoxValues().contains("SÜRELİ EVRAKLAR"), true);



//        BelgenetElement element = comboLov("[id='mainInboxForm:inboxDataTable:filtersAccordion'] h3 a");
//        page.ustMenuAc("Birim Yönetimi");
//        new TestPage().selectCombo();
//        comboLov(By.id("birimYonetimiFilterForm:accordionPanel:birimLov:LovText")).selectComboLov("OPT")
//                .shouldHave(text("OPTİİM"));
//
//        System.out.println("selected: " + new TestPage().isSelected());

//        page.ustMenuAc("Evrak Oluştur");
//
//        String locator = "[id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText']";
//
//
//        BelgenetElement c = comboLov(locator).selectComboLov("010.10");
//        System.out.println("getComboLovTitleText: " + c.getComboLovTitleText());
//        c.getComboLovDetail().shouldHave(text("Mevzuat İşleri"));
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

//        System.out.println("title: " + comboLov(locator).getComboLovTitleText());
//        System.out.println("detail: " + comboLov(locator).getComboLovDetailText());
    }

    @Test(groups = {"FrameworkTest"}, enabled = true)
    public void comboLovTest() {
        //-----------------------------------
        page.ustMenuAc("Evrak Oluştur");
        String locator = "[id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:LovText']";
        comboLov(locator).selectComboLov("010.10")
                .shouldHave(Condition.text("010.10"));

        System.out.println("title: " + comboLov(locator).getComboLovTitleText());
        System.out.println("detail: " + comboLov(locator).getComboLovDetailText());

        //-----------------------------------
        page.ustMenuAc("Birim Yönetimi");
        BelgenetElement cmlBirim = comboLov("[id='birimYonetimiFilterForm:accordionPanel:birimLov:LovText");

        cmlBirim.selectComboLov("opt")
                .shouldHave(text("optiim"));
        System.out.println("1. Birim seçili mi?: " + cmlBirim.isComboLovSelected());
        cmlBirim.clearComboLov();
        System.out.println("1. Birim seçili mi?: " + cmlBirim.isComboLovSelected());

        System.out.println("\n================\n");

        cmlBirim.selectComboLov("opt");
        System.out.println("2. Birim seçili mi?: " + cmlBirim.isComboLovSelected());
        cmlBirim.getComboLovTitle().shouldHave(text("OPT"));
        if (cmlBirim.getComboLovTitleText().equalsIgnoreCase("OPTİİM BİRİM"))
            cmlBirim.selectComboLov("");

        cmlBirim.selectComboLov("opt");
        cmlBirim.getComboLovTitle().shouldHave(exactText("OPTİİM BİRİM"));
        System.out.println("2. Birim seçili mi?: " + cmlBirim.isComboLovSelected());
        //-----------------------------------

        //PageObject kullanım.
        new TestPage().selectCombo().isSelected();

    }
}
