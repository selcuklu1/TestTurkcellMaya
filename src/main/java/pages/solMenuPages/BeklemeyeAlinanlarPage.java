package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BeklemeyeAlinanlarPage extends MainPage {

    //TODO: Listede datalar gelmiyor.
    SelenideElement cmbFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    ElementsCollection tblEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
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
    SelenideElement btnEvrakKopyala = $("[class='ui-button-icon-left ui-icon evrakKopyala']");

    SelenideElement evrakKopyalamaSorusu = $("[onload] div:nth-child(106) .ui-dialog-content");

    SelenideElement btnEvrakKopyalaEvet = $(By.id("evrakCopyConfirmForm:copyEvrakEvetButton"));

    @Step("Beklemeye Alınanlar sayfası aç")
    public BeklemeyeAlinanlarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.BeklemeyeAlinanlar);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.BeklemeyeAlinanlar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrak seçilir")
    public BeklemeyeAlinanlarPage evrakSecKonuyagore(String konuKodu){
        tblEvraklar.filterBy(Condition.text(konuKodu)).first().click();
        return this;
    }

    @Step("Evrak İçerik Göster")
    public BeklemeyeAlinanlarPage evrakSecKonuyaGoreIcerikGoster(String konuKodu){
        tblEvraklar.filterBy(Condition.text(konuKodu)).first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }


    @Step("Evrak kopyala tıklanır")
    public BeklemeyeAlinanlarPage evrakKopyala(){
        btnEvrakKopyala.click();
        return this;
    }

    @Step("Evrak kopyala ikonunun geldiği görülür")
    public BeklemeyeAlinanlarPage evrakKopyalaGeldigiGorme(){
        Assert.assertEquals(btnEvrakKopyala.isDisplayed(),true);
        return this;
    }

    @Step("Evrakı kopyalamak istediğinize emin misiniz? sorusunun geldiği görülür.")
    public BeklemeyeAlinanlarPage evrakKopyalamaSorusununGeldiginiGorme(){
        Assert.assertEquals(evrakKopyalamaSorusu.isDisplayed(),true);
        
        return this;
    }
    @Step("Evrakı kopyalamak istediğinize emin misiniz? sorusunun geldiği görülür.")
    public BeklemeyeAlinanlarPage evrakKopyalamaSorusununGeldiginiGormeUyariMesaj(String uyariMesaji){
        boolean durum = $$("[class='ui-dialog-content ui-widget-content'] p").filterBy(Condition.text(uyariMesaji)).size()>0;
        Assert.assertEquals(durum,true);

        return this;
    }
    @Step("Evet tıklanır")
    public BeklemeyeAlinanlarPage evrakKopyalaEvet(){
        btnEvrakKopyalaEvet.click();
        return this;
    }

    @Step("Acordion aç")
    public BeklemeyeAlinanlarPage acordion() throws InterruptedException {
        acordion.click();
        return this;
    }

    @Step("Sol menu aç")
    public BeklemeyeAlinanlarPage solMenu() throws InterruptedException {
        solmenubeklemeyealinanlar.click();
        return this;
    }

    @Step("Filtrele seç")
    public BeklemeyeAlinanlarPage filtreleSec(String value) throws InterruptedException {
        cmbFiltrele.selectOption(value);
        return this;
    }

    @Step("Sayfada ara doldur")
    public BeklemeyeAlinanlarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }

    @Step("Gideceği yer seçiniz")
    public BeklemeyeAlinanlarPage gidecegiYerSeciniz() throws InterruptedException {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    @Step("Gideceği yer tree doldur")
    public BeklemeyeAlinanlarPage gidecegiYerTreeDoldur(String text) throws InterruptedException {
        btnGidecegiYerTree.setValue(text);
        return this;
    }

    @Step("Başlangıç tarihi doldur")
    public BeklemeyeAlinanlarPage baslangicTarihDoldur(String text) throws InterruptedException {
        dateBaslangicTarih.setValue(text);
        return this;
    }

    @Step("Bitiş tarihi doldur")
    public BeklemeyeAlinanlarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }

    @Step("Evrak Göster")
    public BeklemeyeAlinanlarPage evrakGoster() {
        btnEvrakGoster.click();
        return this;
    }

}

