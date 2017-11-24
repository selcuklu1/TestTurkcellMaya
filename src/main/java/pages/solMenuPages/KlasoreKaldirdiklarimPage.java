package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;

public class KlasoreKaldirdiklarimPage extends MainPage {

    //Filtreler sekmesi
    private SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    private SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    private SelenideElement txtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    private SelenideElement txtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    private SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));

    private SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));
    private SelenideElement btnEvrakKopyala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));

    @Step("Klasöre kaldırdıklarım sayfası aç")
    public KlasoreKaldirdiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Klasorekaldirdiklarim);
        return this;
    }

    @Step("Filtrele")
    public KlasoreKaldirdiklarimPage filtreSec(String filtre) {
        //selectCombobox(filtreSelectbox, filtre);
        cmbFiltre.selectOption(filtre);
        return this;
    }
    @Step("Sayfada ara")
    public KlasoreKaldirdiklarimPage sayfadaAraDoldur(String sayfadaAra) {
        //sendKeys(sayfadaAraInput, sayfadaAra, false);
        txtSayfadaAra.sendKeys(sayfadaAra);
        return this;
    }
    @Step("Başlangıç tarih doldur")
    public KlasoreKaldirdiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        //sendKeys(baslangicTarihiInput, baslangicTarihi, false);
        txtBaslangicTarihi.sendKeys(baslangicTarihi);
        return this;
    }
    @Step("Bitiş tarihi doldur")
    public KlasoreKaldirdiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        //sendKeys(bitisTarihiInput, bitisTarihi, false);
        txtBitisTarihi.sendKeys(bitisTarihi);
        return this;
    }
    @Step("Rapor al")
    public KlasoreKaldirdiklarimPage raporAl() {
        //click(raporAlButton);
        btnRaporAl.click();
        return this;
    }
    @Step("Evrak goster")
    public KlasoreKaldirdiklarimPage evrakGoster() {
        //click(evrakGosterButton);
        btnEvrakGoster.click();
        return this;
    }
    @Step("Evrak kopyala")
    public KlasoreKaldirdiklarimPage evrakKopyala() {
        //click(evrakKopyalaButton);
        btnEvrakKopyala.click();
        return this;
    }

}
