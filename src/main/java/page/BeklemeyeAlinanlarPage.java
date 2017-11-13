package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BeklemeyeAlinanlarPage extends BaseLibrary {

    //TODO: Listede datalar gelmiyor.
    SelenideElement cmbFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGidecegiYerSeciniz = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    SelenideElement btnGidecegiYerTree = $(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));
    SelenideElement dateBaslangicTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));
    //Beklemeye Alınanlar Page
    SelenideElement solmenubeklemeyealinanlar = $(By.id("leftMenuForm:edysMenuItem_-566551367"));
    //Filtreler Acordion
    SelenideElement acordion = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion"));

    public BeklemeyeAlinanlarPage acordionclick() throws InterruptedException {
        acordion.click();
        return this;
    }


    public BeklemeyeAlinanlarPage solMenuGonder() throws InterruptedException {
        solmenubeklemeyealinanlar.click();
        return this;
    }

    public BeklemeyeAlinanlarPage filtreleSec(String value) throws InterruptedException {
        cmbFiltrele.selectOption(value);
        return this;
    }

    public BeklemeyeAlinanlarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public BeklemeyeAlinanlarPage gidecegiYerSecinizGonder() throws InterruptedException {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public BeklemeyeAlinanlarPage gidecegiYerTreeDoldur(String text) throws InterruptedException {
        btnGidecegiYerTree.setValue(text);
        return this;
    }

    public BeklemeyeAlinanlarPage baslangicTarihDoldur(String text) throws InterruptedException {
        dateBaslangicTarih.setValue(text);
        return this;
    }

    public BeklemeyeAlinanlarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }

    public BeklemeyeAlinanlarPage evrakGosterGonder() throws InterruptedException {
        btnEvrakGoster.click();
        return this;
    }

    //TODO: Listede datalar gelmiyor.

}

