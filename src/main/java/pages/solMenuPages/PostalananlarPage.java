package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PostalananlarPage extends BaseLibrary {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement chkKepIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:keplePostalananlarCheckbox_input"));
    SelenideElement chkMedasIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:medaslaPostalananlarCheckbox_input"));
    SelenideElement chkPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox_input"));
    @Step("Filtrele Seç")
    public PostalananlarPage filtreSec(String text) throws InterruptedException {
        cmbFiltre.selectOption(text);
        return this;
    }
    @Step("Sayfada ara doldur")
    public PostalananlarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }
    @Step("Başlangıç tarihi doldur")
    public PostalananlarPage baslangicTarihiDoldur(String text) throws InterruptedException {
        dateBaslangicTarihi.setValue(text);
        return this;
    }
    @Step("Bitiş tarihi doldur")
    public PostalananlarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }

    @Step("Kep ile PostalananlarPage seç")
    public PostalananlarPage kepIlePostalananlarSec(boolean text) throws InterruptedException {
        chkKepIlePostalananlar.setSelected(text);
        return this;
    }

    @Step("Medas ile PostalananlarPage seç")
    public PostalananlarPage medasIlePostalananlarSec(boolean text) throws InterruptedException {
        chkMedasIlePostalananlar.setSelected(text);
        return this;
    }
    @Step("Postaladıklarım seç")
    public PostalananlarPage postaladiklarimSec(boolean text) throws InterruptedException {
        chkPostaladiklarim.setSelected(text);
        return this;
    }

}
