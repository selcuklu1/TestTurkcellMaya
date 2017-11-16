package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class IslemYaptiklarimKlasoreKaldirdiklarimPage extends BaseLibrary {

    //Filtreler sekmesi
    private SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    private SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    private SelenideElement txtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    private SelenideElement txtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    private SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));

    private SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));
    private SelenideElement btnEvrakKopyala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));


    //public IslemYaptiklarimKlasoreKaldirdiklarimPage(WebDriver driver) {super(driver);}

    public IslemYaptiklarimKlasoreKaldirdiklarimPage filtreSec(String filtre) {
        //selectCombobox(filtreSelectbox, filtre);
        cmbFiltre.selectOption(filtre);
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage sayfadaAraDoldur(String sayfadaAra) {
        //sendKeys(sayfadaAraInput, sayfadaAra, false);
        txtSayfadaAra.sendKeys(sayfadaAra);
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        //sendKeys(baslangicTarihiInput, baslangicTarihi, false);
        txtBaslangicTarihi.sendKeys(baslangicTarihi);
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        //sendKeys(bitisTarihiInput, bitisTarihi, false);
        txtBitisTarihi.sendKeys(bitisTarihi);
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage raporAl() {
        //click(raporAlButton);
        btnRaporAl.click();
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage evrakGoster() {
        //click(evrakGosterButton);
        btnEvrakGoster.click();
        return this;
    }

    public IslemYaptiklarimKlasoreKaldirdiklarimPage evrakKopyala() {
        //click(evrakKopyalaButton);
        btnEvrakKopyala.click();
        return this;
    }

}
