package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariPostalanacakEvraklar extends BaseLibrary {

    SelenideElement txtBirim = $(By.id("mainInboxForm:birimInboxFilterLov:LovText"));
    SelenideElement btnListele = $(By.id("mainInboxForm:birimEvraklariListeleButton"));

    // Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGeldigiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt368_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt373_input"));

    public BirimEvraklariPostalanacakEvraklar birimDoldur(String text) throws InterruptedException {
        txtBirim.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar listeleGonder() throws InterruptedException {
        btnListele.click();
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar filtreSec(String value) throws InterruptedException {
        cmbFiltre.selectOption(value);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar geldigiYerGonder() throws InterruptedException {
        btnGeldigiYer.click();
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar baslangicTarihiDoldur(String text) throws InterruptedException {
        dateBaslangicTarihi.setValue(text);
        return this;
    }

    public BirimEvraklariPostalanacakEvraklar bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }
}