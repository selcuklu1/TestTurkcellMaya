package page.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ParafBekleyenlerPage extends BaseLibrary {

    //TODO: Listede datalar gelmiyor.

    private SelenideElement cmbFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    private SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    private SelenideElement btnGidecegiYerSeciniz = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    private SelenideElement btnGidecegiYerTree = $(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));
    private SelenideElement txtBaslangicTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    private SelenideElement txtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));

    public ParafBekleyenlerPage filtreleSelectBoxSec(String value) {
        //selectCombobox(filtreleSelectBox , value);
        cmbFiltrele.selectOption(value);
        return this;
    }

    public ParafBekleyenlerPage sayfadanAraInputDoldur(String text) {
        //sendKeys(sayfadaAraInput, text ,false);
        txtSayfadaAra.sendKeys(text);
        return this;
    }

    public ParafBekleyenlerPage gidecegiYerSecinizButonClick() {
        //click(gidecegiYerSecinizButon);
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public ParafBekleyenlerPage gidecegiyerTreeDoldur(String text) {
        //sendKeys(gidecegiYerTree , text, false);
        btnGidecegiYerTree.sendKeys(text);
        return this;
    }

    public ParafBekleyenlerPage baslangicTarihInputDoldur(String text) {
        //sendKeys(baslangicTarihInput, text, false);
        txtBaslangicTarih.sendKeys(text);
        return this;
    }


    public ParafBekleyenlerPage bitisTarihInputDoldur(String text) {
        //sendKeys(bitisTarihiInput,text,false);
        txtBitisTarihi.sendKeys(text);
        return this;
    }

    //TODO: Listede datalar gelmiyor.


}
