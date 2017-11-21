package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PulYonetimiPage extends MainPage {

    SelenideElement btnAddNewPul = $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"));
    SelenideElement cmbPostaTipi = $(By.id("pulYonetimiListingForm:filterPanel:postaTipiSelectbox"));
    SelenideElement cmbGonderimTipi = $(By.id("pulYonetimiListingForm:filterPanel:gonderimTipiSelectBox"));
    SelenideElement cmbPulEklemePostaTipi = $(By.id("pulYonetimiEditorForm:postaTipiSelectbox"));
    SelenideElement txtGramaj = $(By.id("pulYonetimiEditorForm:agirlikInput"));
    SelenideElement txtTutar = $(By.id("pulYonetimiEditorForm:tutarInput"));
    SelenideElement chkYurtDisi = $(By.id("pulYonetimiEditorForm:yurtDisiCheckbox_input"));
    SelenideElement txtIndirimOrani = $(By.id("pulYonetimiEditorForm:numInput"));
    SelenideElement btnSavePul = $(By.id("pulYonetimiEditorForm:savePulButton"));
    SelenideElement btnAra = $(By.id("pulYonetimiListingForm:filterPanel:searchPulButton"));
    SelenideElement tblPulYonetimi = $(By.id("pulYonetimiListingForm:pulDataTable_data"));
    ElementsCollection tblPulYonetimi2 = $$("tbody[id$='pulYonetimiListingForm:pulDataTable_data'] tr[role=row] div[class=searchText]");


    public PulYonetimiPage openPage() {
        new UstMenu().ustMenu("Pul Yönetimi");
        return this;
    }

    @Step("Yeni Pul ekle")
    public PulYonetimiPage yeniPulEkle() {
        btnAddNewPul.click();
        return this;
    }

    @Step("Posta tipi seç")
    public PulYonetimiPage postaTipiSec(String value) {
        cmbPostaTipi.selectOptionByValue(value);
        return this;
    }

    @Step("Gönderim tipi seç")
    public PulYonetimiPage gonderimTipiSec(String value) {
        cmbGonderimTipi.selectOptionByValue(value);
        return this;
    }


    @Step("Posta tipi seç")
    public PulYonetimiPage pulEklemePostaTipiSec(String value) {
        cmbPulEklemePostaTipi.selectOptionByValue(value);
        return this;
    }

    @Step("Gramajı doldur")
    public PulYonetimiPage gramajiDoldur(String gramaj) {
        txtGramaj.setValue(gramaj);
        return this;
    }

    @Step("Tutarı doldur")
    public PulYonetimiPage tutariDoldur(String tutar) {
        txtTutar.setValue(tutar);
        return this;
    }

    @Step("Yurt dışı checkbox değeri seç")
    public PulYonetimiPage yurtDisiSec(boolean secili) {
        chkYurtDisi.setSelected(secili);
        return this;
    }

    public PulYonetimiPage indirimOraniDoldur(String indirimOrani) {
        txtIndirimOrani.setValue(indirimOrani);
        return this;
    }

    @Step("Kaydet")
    public PulYonetimiPage kaydet() {
        btnSavePul.click();
        return this;
    }

    @Step("Ara")
    public PulYonetimiPage ara() {
        btnAra.click();
        return this;
    }

//    @Step("Tabloda evrak no kontrolu")
//    public PulYonetimiPage tabloKontrolu(String evrakNo)
//    {
//
//        findElementOnTableByColumnInputInAllPages(tblPulYonetimi,0,evrakNo);
//        return  this;
//    }
}
