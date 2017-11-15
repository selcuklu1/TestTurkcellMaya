package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariPostalananlarPage extends BaseLibrary {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement chkKepIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:keplePostalananlarCheckbox_input"));
    SelenideElement chkMedasIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:medaslaPostalananlarCheckbox_input"));
    SelenideElement chkPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox_input"));

    public BirimEvraklariPostalananlarPage filtreSec(String text) {
        cmbFiltre.selectOption(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage baslangicTarihiDoldur(String text) {
        dateBaslangicTarihi.setValue(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage bitisTarihiDoldur(String text) {
        dateBitisTarihi.setValue(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage kepIlePostalananlarSec(boolean text) {
        chkKepIlePostalananlar.setSelected(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage medasIlePostalananlarSec(boolean text) {
        chkMedasIlePostalananlar.setSelected(text);
        return this;
    }

    public BirimEvraklariPostalananlarPage postaladiklarimSec(boolean text) {
        chkPostaladiklarim.setSelected(text);
        return this;
    }

}
