package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.UstMenuData;

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
    ElementsCollection tblPulYonetimi2 = $$("tbody[id$='pulYonetimiListingForm:pulDataTable_data'] tr[role=row]");
    SelenideElement lblPostaTipi = $(By.xpath("//form[@id='pulYonetimiEditorForm']//tr[1]//label"));
    SelenideElement lblGramaj = $(By.xpath("//form[@id='pulYonetimiEditorForm']//tr[2]//label"));
    SelenideElement lblTutar = $(By.xpath("//form[@id='pulYonetimiEditorForm']//tr[3]//label"));
    SelenideElement lblIndirimOrani = $(By.xpath("//form[@id='pulYonetimiEditorForm']//tr[5]//label"));


    public PulYonetimiPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.PulYonetimi);
        return this;
    }

    @Step("Yeni Pul ekle")
        public PulYonetimiPage yeniPulEkle() {
//        btnAddNewPul.click();
            clickJs(btnAddNewPul);
            return this;
    }
    @Step("Alan Kontrolleri")
    public PulYonetimiPage alanKontrolleri() {

        lblPostaTipi.isDisplayed();
        lblGramaj.isDisplayed();
        lblTutar.isDisplayed();
        lblIndirimOrani.isDisplayed();
        chkYurtDisi.isDisplayed();

        Allure.addAttachment("label",lblPostaTipi.text());
        Allure.addAttachment("label",lblGramaj.text());
        Allure.addAttachment("label",lblTutar.text());
        Allure.addAttachment("label",lblIndirimOrani.text());
        Allure.addAttachment("Yurt Dişi checkBox","Ok");
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

    @Step("İndirim oranı girilir")
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

    @Step("Tabloda evrak no kontrolu")
    public PulYonetimiPage tabloKontrolu(String gonderimSekli) {
        int j;
        int i = tblPulYonetimi2
                .filterBy(Condition.text(gonderimSekli)).size();

        for (j = 0; j < i; j++)
            tblPulYonetimi2
                    .filterBy(Condition.text(gonderimSekli))
                    .get(j)
                    .shouldBe(Condition.exist);
        return this;
    }
}
