package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class PostalananlarPage extends MainPage {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement chkKepIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:keplePostalananlarCheckbox_input"));
    SelenideElement chkMedasIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:medaslaPostalananlarCheckbox_input"));
    SelenideElement chkPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox_input"));

    //Hüseyin
    ElementsCollection tablePostalananlar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPostaDetayi = $x("//span[text() = 'Posta Detayı']/../../..//button");

    @Step("Postalananlar sayfası aç")
    public PostalananlarPage openPage(){
        solMenu(SolMenuData.BirimEvraklari.Postalananlar);
        return this;
    }

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

    @Step("Evrak seç.")
    public PostalananlarPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tablePostalananlar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text(no))
                .first()
                .click();

        return this;
    }

    @Step("Posta Detayı na tıkla")
    public PostalananlarPage postaDetayiTikla(){
        btnPostaDetayi.click();
        return this;
    }



}
