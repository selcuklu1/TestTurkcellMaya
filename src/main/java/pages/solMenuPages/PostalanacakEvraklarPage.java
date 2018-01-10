package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

public class PostalanacakEvraklarPage extends MainPage {

    SelenideElement txtBirim = $(By.id("mainInboxForm:birimInboxFilterLov:LovText"));
    SelenideElement btnListele = $(By.id("mainInboxForm:birimEvraklariListeleButton"));

    // Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGeldigiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt368_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt373_input"));

    SelenideElement tblEvrakSec = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnEvrakPostala = $("button .postala");
    SelenideElement btnEvrakPostalaPostala = $(By.id("mainPreviewForm:postalaButton_id"));
    SelenideElement tblSec = $(By.xpath("//tbody[@id='mainInboxForm:inboxDataTable_data']/tr[@data-ri='3']"));
    SelenideElement btnPostaDetayi3 = $(By.id("mainInboxForm:inboxDataTable:3:postaDagitimGosterButton"));
    SelenideElement btnGonderilenYerDetay = $(By.id("mainPreviewForm:dataTableId:0:j_idt20289"));
    BelgenetElement cmbGidisSekli = comboBox(By.xpath("//*[normalize-space(text())='Gidiş Şekli :']/ancestor::td[@role='gridcell'][1]//label[contains(@id,'_label')]"));
    SelenideElement txtGramaj = $("[id^='mainPreviewForm:dataTableId'][id$='postaGramaj']");
    SelenideElement btnTamam = $(By.id("mainPreviewForm:tutarDialogButtonId"));
    SelenideElement txtTutar = $("[id$='postaTutarId']");
    SelenideElement btnKaydet = $(By.id("mainPreviewForm:dagitimPlaniDetayKaydetViewDialog"));
    SelenideElement btnHesapla = $x("//button[span[text()='Hesapla']]");
    SelenideElement epostaTxt = $x("//label[normalize-space(text())='e-posta :']/ancestor::tr[1]//input");
    SelenideElement epostaAciklama = $x("//*[normalize-space(text())='Açıklama :']/ancestor::td[@role='gridcell'][1]//textarea");
    SelenideElement postalanacakEvrakYazdir = $x("//button[span[text()='Yazdır']]");
    SelenideElement postalanacakEvrakOrijinalYazdir = $x("//button[span[text()='Orjinalini Yazdır']]");
    SelenideElement btnPopupYazdir = $(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogYazdir"));
    SelenideElement btnPopupOrjYazdir = $(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogOrjYazdir"));
    SelenideElement btnPopupHesaplaTamam = $(By.id("mainPreviewForm:tutarDialogButtonId"));
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    ElementsCollection btnEvrakDetayKapat = $$("[id='windowItemInfoDialog'] a[class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all'] span[class='ui-icon ui-icon-closethick']");
    SelenideElement btnPostala = $(By.id("mainPreviewForm:postalaButton_id"));
    SelenideElement btnDialogEvet = $(By.id("mainPreviewForm:postalaDogrulaDialogForm:evetButton_id"));
    SelenideElement btnIcerikGoster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnDagitimYerDetay = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[2]/td[2]/div/button");
    SelenideElement tabIcerikIlgileri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:2:cmdbutton"));
    SelenideElement tabIcerikEkleri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:1:cmdbutton"));
    SelenideElement tabIcerikKapat = $x("//*[@id='windowItemInfoDialog']/div[1]/a[1]/span");
    SelenideElement tabIcerikKapatmaOnay = $(By.id("kapatButton"));
    SelenideElement birimPostaKodu = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[3]/td[4]/div/div/div//table/tbody/tr[1]/td[2]/input");
    SelenideElement birimPostaAciklama = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[3]/td[4]/div/div/div//table/tbody/tr[2]/td[2]/textarea");
    SelenideElement tuzelKisiPostaKodu = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[4]/td[4]/div/div/div//table/tbody/tr[1]/td[2]/input");
    SelenideElement tuzelKisiPostaAciklama = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[4]/td[4]/div/div/div//table/tbody/tr[2]/td[2]/textarea");
    SelenideElement ilkPostaPostaKodu = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[1]/td[4]/div/div/div//table/tbody/tr[1]/td[2]/input");
    SelenideElement ilkPostaAciklama = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[1]/td[4]/div/div/div//table/tbody/tr[2]/td[2]/textarea");
    SelenideElement btnEtiketYazdir = $x("//*[@id='mainPreviewForm:dataTableId_data']/tr[1]/td[5]/div//table/tbody/tr[3]/td/button");
    SelenideElement btnEtiketPopupKapat = $x("//*[@id='mainPreviewForm:showAppletContainer']/div/div[1]/a/span");
    SelenideElement btnDagitimYerDetayKapat = $x("//*[@id='mainPreviewForm:dagitimPlaniDetayViewDialog']/div[1]/a/span");
    SelenideElement lblIndirimOncesiTutari = $("[id='mainPreviewForm:tutarDialogId'] table:nth-child(1) tbody td:nth-child(2) label");
    SelenideElement lblIndirimOrani = $("[id='mainPreviewForm:tutarDialogId'] table:nth-child(2) tbody td:nth-child(2) label");
    SelenideElement lblTutar = $("[id='mainPreviewForm:tutarDialogId'] table:nth-child(3) tbody td:nth-child(2) label");
    SelenideElement popUP = $(By.id("mainPreviewForm:tutarDialogId"));

    @Step("Postalanacak Evraklar sayfası aç")
    public PostalanacakEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar);
        String pageTitle = SolMenuData.BirimEvraklari.PostalanacakEvraklar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrak seçilir")
    public PostalanacakEvraklarPage evrakSec(String konu, String yer, String tarih) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).get(0).click();
        return this;
    }

    @Step("Evrak içerik göster")
    public PostalanacakEvraklarPage evrakSecIcerikGoster(String konu, String yer, String tarih) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak içerik göster : \"{konu}\" ")
    public PostalanacakEvraklarPage evrakSecKonuyaGoreIcerikGoster(String konu) {
        tblEvraklar.filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    public PostalanacakEvraklarPage evrakSecIcerikKapat(boolean secim) {
        btnEvrakDetayKapat.get(0).click();
        if (secim == true) {
            $(By.id("kapatButton")).pressEnter();
        } else {
            $(By.id("iptalButton")).pressEnter();
        }
        return this;
    }

    @Step("Evrak Postala")
    public PostalanacakEvraklarPage evrakPostala() {
        btnEvrakPostala.click();
        return this;
    }

    @Step("Postala")
    public PostalanacakEvraklarPage evrakPostalaPostala(boolean secim) {
        btnEvrakPostalaPostala.pressEnter();
        if (secim = true) {
            $(By.id("mainPreviewForm:postalaDogrulaDialogForm:evetButton_id")).pressEnter();
        } else {
            $(By.id("mainPreviewForm:postalaDogrulaDialogForm:hayirButton_id")).pressEnter();
        }
        return this;
    }

    @Step("Evrak Seçilir")
    public PostalanacakEvraklarPage evrakSec() {
        tblEvrakSec.click();
        return this;
    }

    @Step("Birim Doldur")
    public PostalanacakEvraklarPage birimDoldur(String text) throws InterruptedException {
        txtBirim.setValue(text);
        return this;
    }

    @Step("Listele")
    public PostalanacakEvraklarPage listele() throws InterruptedException {
        btnListele.click();
        return this;
    }

    @Step("Filtrele Seç")
    public PostalanacakEvraklarPage filtreSec(String value) throws InterruptedException {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada ara doldur")
    public PostalanacakEvraklarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }

    @Step("Geldiği yer tıkla")
    public PostalanacakEvraklarPage geldigiYer() throws InterruptedException {
        btnGeldigiYer.click();
        return this;
    }

    @Step("Başlangıç tarihi doldur")
    public PostalanacakEvraklarPage baslangicTarihiDoldur(String text) throws InterruptedException {
        dateBaslangicTarihi.setValue(text);
        return this;
    }

    @Step("Bitiş tarihi doldur")
    public PostalanacakEvraklarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }

    @Step("tablodan seç")
    public PostalanacakEvraklarPage tablodanSec() {
        tblSec.click();
        return this;
    }

    @Step("Posta Detayı tıkla")
    public PostalanacakEvraklarPage postaDetayi() {
        btnPostaDetayi3.click();
        return this;
    }

    @Step("Gönderilen Yer Detay tıkla")
    public PostalanacakEvraklarPage gonderilenYerDetay() {
        btnGonderilenYerDetay.click();
        return this;
    }

    @Step("Gidis Sekli \"{gidisSekli}\" seçilir")
    public PostalanacakEvraklarPage gidisSekli(String gidisSekli) {
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }

    @Step("Gramaj alnına \"{gramaj}\" girilir.")
    public PostalanacakEvraklarPage gramajDoldur(String gramaj) throws InterruptedException {
        setValueJS(txtGramaj, gramaj);
        return this;
    }

    @Step("Hesapla tıkla")
    public PostalanacakEvraklarPage hesapla() throws InterruptedException {
        btnHesapla.click();
//        Thread.sleep(1000);
        btnPopupHesaplaTamam.click();
        return this;
    }

    @Step("Hesapla tıkla")
    public PostalanacakEvraklarPage evrakOnzilemeHesapla() throws InterruptedException {
        btnHesapla.click();
        return this;
    }

    @Step("Popup Tamam tıkla")
    public PostalanacakEvraklarPage popUpTamam() {
        btnTamam.click();
        return this;
    }

    @Step("Tutar alanına \"{tutar}\" girilir")
    public PostalanacakEvraklarPage tutarDoldur(String tutar) {
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Postalanacak Evrak Yazdır")
    public PostalanacakEvraklarPage postalanacakEvrakYaz() {

        postalanacakEvrakYazdir.click();
        return this;

    }

    public PostalanacakEvraklarPage postalanacakEvrakOrjYaz() throws InterruptedException {

        postalanacakEvrakOrijinalYazdir.click();
        btnPopupOrjYazdir.click();
        Thread.sleep(2000);
        Selenide.switchTo();

        popupPostaYazdirmaKapat();
        return this;
    }

    public PostalanacakEvraklarPage postalacanakEposta(String eposta) {

        epostaTxt.setValue(eposta);
        return this;
    }

    public PostalanacakEvraklarPage postalamaAciklama(String text) {

        epostaAciklama.setValue(text);
        return this;
    }

    public PostalanacakEvraklarPage popupPostaYazdirmaKapat() throws InterruptedException {

        $(By.id("postaDetayYazdirForm:dlgPostaDetayYazdir")).shouldBe(Condition.visible);
        $x("//div[@id='postaDetayYazdirForm:dlgPostaDetayYazdir']//a[span[@class='ui-icon ui-icon-closethick']]").click();

        return this;
    }

    public PostalanacakEvraklarPage popupPostalanacakEvrakYazdir() throws InterruptedException {
        btnPopupYazdir.click();
        Thread.sleep(2000);
        Selenide.switchTo();

        return this;
    }

    public PostalanacakEvraklarPage postala() {

        btnPostala.click();
        return this;
    }

    public PostalanacakEvraklarPage dialogpostalaEvet() {

        btnDialogEvet.click();
        return this;
    }

    public PostalanacakEvraklarPage dagitimDetay() {
        btnDagitimYerDetay.click();
        return this;
    }

    public PostalanacakEvraklarPage icerikGoster() {
        btnIcerikGoster.click();
        return this;
    }

    public PostalanacakEvraklarPage icerikIlgileriTab() {
        tabIcerikIlgileri.click();
        return this;
    }

    public PostalanacakEvraklarPage icerikEkleriTab() {
        tabIcerikEkleri.click();
        return this;
    }

    public PostalanacakEvraklarPage icerikPencereKapatma() {
        tabIcerikKapat.click();
        tabIcerikKapatmaOnay.click();
        return this;
    }

    public PostalanacakEvraklarPage tuzelKisiPostaKod(String txt) {
        tuzelKisiPostaKodu.setValue(txt);
        return this;

    }

    public PostalanacakEvraklarPage tuzelKisiPostaAciklama(String txt) {

        tuzelKisiPostaAciklama.setValue(txt);
        return this;
    }

    public PostalanacakEvraklarPage birimPostaKod(String txt) {
        birimPostaKodu.setValue(txt);
        return this;

    }

    public PostalanacakEvraklarPage birimPostaAciklama(String txt) {

        birimPostaAciklama.setValue(txt);
        return this;
    }

    public PostalanacakEvraklarPage ilkPostaPostaKod(String txt) {
        ilkPostaPostaKodu.setValue(txt);
        return this;
    }

    public PostalanacakEvraklarPage ilkPostaAciklama(String txt) {

        ilkPostaAciklama.setValue(txt);
        return this;
    }

    public PostalanacakEvraklarPage etiketYazdir() throws InterruptedException {
        btnEtiketYazdir.click();
        Thread.sleep(1000);
        return this;
    }

    public PostalanacakEvraklarPage etiketYazdirPopupKapat() {
        btnEtiketPopupKapat.click();
        return this;
    }

    public PostalanacakEvraklarPage dagitimDetayKapat() {

        btnDagitimYerDetayKapat.click();
        return this;
    }

    @Step("PopUp İndirim Öncesi tutar alaninda \"{indirimOncesiTutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostalanacakEvraklarPage popUpIndirimOncesiTutarKontrol(String indirimOncesiTutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblIndirimOncesiTutari.shouldHave(Condition.text(indirimOncesiTutar + " TL"));
        else
            lblIndirimOncesiTutari.shouldNotHave(Condition.text(indirimOncesiTutar + " TL"));
        return this;
    }

    @Step("PopUp İndirim Oranı alaninda \"{indirimOrani}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostalanacakEvraklarPage popUpIndirimOraniKontrol(String indirimOrani, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblIndirimOrani.shouldHave(Condition.text(indirimOrani));
        else
            lblIndirimOrani.shouldNotHave(Condition.text(indirimOrani));
        return this;
    }

    @Step("PopUp Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostalanacakEvraklarPage popUpTutarKontrol(String tutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblTutar.shouldHave(Condition.text(tutar));
        else
            lblTutar.shouldNotHave(Condition.text(tutar));
        return this;
    }

    @Step("Popup kontrolü")
    public PostalanacakEvraklarPage popUpKontrol() {
        popUP.shouldBe(Condition.visible);
        return this;
    }

    @Step("Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostalanacakEvraklarPage tutarAlaniKontrolu(String tutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtTutar.getValue().equals(Condition.text(tutar));
        else
            txtTutar.shouldNotBe(Condition.text(tutar));
        return this;
    }
}
