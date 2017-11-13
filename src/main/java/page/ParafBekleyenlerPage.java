package page;

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

    public ParafBekleyenlerPage filtreleSelectBoxSec(String value) throws InterruptedException {
        //selectCombobox(filtreleSelectBox , value);
        cmbFiltrele.selectOption(value);
        return this;
    }

    public ParafBekleyenlerPage sayfadanAraInputDoldur(String text) throws InterruptedException {
        //sendKeys(sayfadaAraInput, text ,false);
        txtSayfadaAra.sendKeys(text);
        return this;
    }

    public ParafBekleyenlerPage gidecegiYerSecinizButonClick() throws InterruptedException {
        //click(gidecegiYerSecinizButon);
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public ParafBekleyenlerPage gidecegiyerTreeDoldur(String text) throws InterruptedException {
        //sendKeys(gidecegiYerTree , text, false);
        btnGidecegiYerTree.sendKeys(text);
        return this;
    }

    public ParafBekleyenlerPage baslangicTarihInputDoldur(String text) throws InterruptedException {
        //sendKeys(baslangicTarihInput, text, false);
        txtBaslangicTarih.sendKeys(text);
        return this;
    }


    public ParafBekleyenlerPage bitisTarihInputDoldur(String text) throws InterruptedException {
        //sendKeys(bitisTarihiInput,text,false);
        txtBitisTarihi.sendKeys(text);
        return this;
    }

    //TODO: Listede datalar gelmiyor.


}
