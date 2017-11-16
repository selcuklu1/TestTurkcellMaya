package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariPostalanacakEvraklarPage extends BaseLibrary {

    SelenideElement txtBirim = $(By.id("mainInboxForm:birimInboxFilterLov:LovText"));
    SelenideElement btnListele = $(By.id("mainInboxForm:birimEvraklariListeleButton"));

    // Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGeldigiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt368_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt373_input"));

    public BirimEvraklariPostalanacakEvraklarPage birimDoldur(String text) {
        txtBirim.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage listele() {
        btnListele.click();
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage filtreSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage geldigiYer() {
        btnGeldigiYer.click();
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage baslangicTarihiDoldur(String text) {
        dateBaslangicTarihi.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklarPage bitisTarihiDoldur(String text) {
        dateBitisTarihi.setValue(text);
        return this;
    }
}