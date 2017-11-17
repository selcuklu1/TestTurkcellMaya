package page.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class PulYonetimiPage extends BaseLibrary {

    SelenideElement btnAddNewPul = $(By.id("pulYonetimiListingForm:pulDataTable:addNewPulButton"));
    SelenideElement cmbPostaTipi = $(By.id("pulYonetimiEditorForm:postaTipiSelectbox"));
    SelenideElement txtGramaj = $(By.id("pulYonetimiEditorForm:agirlikInput"));
    SelenideElement txtTutar = $(By.id("pulYonetimiEditorForm:tutarInput"));
    SelenideElement chkYurtDisi = $(By.id("pulYonetimiEditorForm:yurtDisiCheckbox_input"));
    SelenideElement txtIndirimOrani = $(By.id("pulYonetimiEditorForm:numInput"));
    SelenideElement btnSavePul = $(By.id("pulYonetimiEditorForm:savePulButton"));

    @Step("Yeni Pul ekle")
    public PulYonetimiPage yeniPulEkle() {
        btnAddNewPul.click();
        return this;
    }

    @Step("Posta tipi seç")
    public PulYonetimiPage postaTipiSec(String value) {
        cmbPostaTipi.selectOption(value);
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
}
