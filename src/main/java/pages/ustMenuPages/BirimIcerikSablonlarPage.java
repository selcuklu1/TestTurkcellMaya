package pages.ustMenuPages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimIcerikSablonlarPage extends BaseLibrary {

    private SelenideElement txtSablonAdi = $(By.id("birimSablonForm:sablonAdiText_id"));
    private BelgenetElement lovKullanilacakBirimler = comboLov(By.id("birimSablonForm:sablonLov_id:LovText"));
    private SelenideElement selEvrakTipi = $x("//label[normalize-space(text())='Evrak Tipi']/ancestor::tr[last()]//select[starts-with(@id,'birimSablonForm')]");
                                            //$("#birimSablonForm select:first-child")
                                            private SelenideElement btnYeniSablonOlustur = $(By.id("birimSablonForm:sablonIslemYeniButton_Id"));
    private SelenideElement btnKaydet = $(By.id("birimSablonForm:sablonIslemKaydetButton_id"));
    private SelenideElement btnSil = $(By.id("birimSablonForm:sablonIslemSilButton_id"));
    private SelenideElement btnEvrakOnizleme = $(By.id("birimSablonForm:sablonOnizlemeButton_id"));


    private SelenideElement tblBirimSablonlari = $("[id^='birimSablonForm'][id$='sablonDataTable']");
    //Detay butonları row sayısına eşit olmalı
    private ElementsCollection rowsBirimSablonlari = $$("[id$='sablonDataTable'] tbody tr[role='row']");
    private ElementsCollection btnDetayInEachRow = $$("[id$='sablonListesiDetayButton_id']");

    private TextEditor editor = new TextEditor();

    public SelenideElement getTxtSablonAdi() {
        return txtSablonAdi;
    }

    public BelgenetElement getLovKullanilacakBirimler() {
        return lovKullanilacakBirimler;
    }

    public SelenideElement getSelEvrakTipi() {
        return selEvrakTipi;
    }

    public SelenideElement getBtnYeniSablonOlustur() {
        return btnYeniSablonOlustur;
    }

    public SelenideElement getBtnKaydet() {
        return btnKaydet;
    }

    public SelenideElement getBtnSil() {
        return btnSil;
    }

    public SelenideElement getBtnEvrakOnizleme() {
        return btnEvrakOnizleme;
    }

    public SelenideElement getTblBirimSablonlari() {
        return tblBirimSablonlari;
    }

    public ElementsCollection getRowsBirimSablonlari() {
        return rowsBirimSablonlari;
    }

    public ElementsCollection getBtnDetayInEachRow() {
        return btnDetayInEachRow;
    }

    public TextEditor getEditor() {
        return editor;
    }

//    public SelenideElement editor = $(By.id("cke_birimSablonForm:birimIcerikCkEditor"));

    public BirimIcerikSablonlarPage openPage() {
        new UstMenu().ustMenu("Birim İçerik Şablonları");
        return this;
    }

    public void yeniSablonOlustur(){
        btnYeniSablonOlustur.click();

        lovKullanilacakBirimler.type("optiim birim").titleItems()
                .filterBy(Condition.exactText("optiim birim"))
                .first()
                .click();

        lovKullanilacakBirimler.closeLovTreePanel();
        comboLov(By.id("birimSablonForm:sablonLov_id:LovText")).closeLovTreePanel();

        lovKullanilacakBirimler.lastSelectedLov().$(By.tagName("select")).selectOption("ALT BİRİMLER GÖRSÜN");

        getEditor()
                .type("Test").type(Keys.ENTER)
                .type("hhghghg").type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim");
    }

}
