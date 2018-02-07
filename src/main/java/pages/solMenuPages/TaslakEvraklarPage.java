package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TaslakEvraklarPage extends MainPage {

    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement btnTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));
    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));

    SelenideElement btnImzala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));

    //Sil Button alt div
    SelenideElement btnSil = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));
    SelenideElement txtSilAciklama = $("[id='mainPreviewForm:evrakSilPanelGrid'] td:nth-child(3) textarea");
    SelenideElement btnSilSil = $(By.xpath("//span[text()='Sil']/../../button"));
    SelenideElement btnPopSilEvet = $(By.id("mainPreviewForm:evrakSilEvetButton"));

    SelenideElement btnEvrakKopyala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));

    //Paylaş Button alt div

    // mainPreviewForm:paylasButtonId
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    SelenideElement btnEvrakNotlariTabYeni = $(By.id("mainPreviewForm:j_idt11557:kisiselNotEkleDataTableId:kisiselNotEkleId"));
    SelenideElement txtEvrakNotlariModalAcikalama = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotAciklamaid"));
    SelenideElement cmbEvrakNotlariModalNotTipi = $(By.id("evrakKisiselNotDialogFormId:evrakNotTipi_input"));
    SelenideElement btnEvrakNotlariModalKaydet = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotKaydet"));
    SelenideElement btnEvrakNotlariModalIptal = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotIptal"));

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    ElementsCollection tabEvrakOnizleme = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] ul[role='tablist'] li");
    SelenideElement btnPaylasTab = $(By.xpath("//span[contains(@class, 'evrakPaylas')]/.."));

    SelenideElement btnPaylasBirim = $("div[id='mainPreviewForm:paylasTumuBoolean']");
    ElementsCollection tblHareketGecmisi = $$("tbody[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    SelenideElement btnRaporAlExcel = $("[id$='hareketGecmisiDataTable:evrakGecmisiExport']");

    @Step("Taslak Evraklar sayfası aç")
    public TaslakEvraklarPage openPage() {

        solMenu(SolMenuData.IslemBekleyenEvraklar.TaslakEvraklar);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.TaslakEvraklar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrak notları popup iptal")
    public TaslakEvraklarPage evrakNotlariModalIptalGonder() {
        btnEvrakNotlariModalIptal.click();
        return this;
    }

    @Step("Evrak notları popup kaydet")
    public TaslakEvraklarPage evrakNotlariModalKaydetGonder() {
        btnEvrakNotlariModalKaydet.click();
        return this;
    }

    public TaslakEvraklarPage evrakNotlariModalNotTipiSec(String value) {
        cmbEvrakNotlariModalNotTipi.selectOption(value);
        return this;
    }

    public TaslakEvraklarPage evrakNotlariModalAcikalamDoldur(String text) {
        txtEvrakNotlariModalAcikalama.setValue(text);
        return this;
    }

    public TaslakEvraklarPage evrakNotlariTabYeniButtonClick() {
        btnEvrakNotlariTabYeni.click();
        return this;
    }

    public TaslakEvraklarPage imzalaGonder() {
        btnImzala.click();
        return this;
    }

    public TaslakEvraklarPage evrakGosterGonder() {
        btnEvrakGoster.click();
        return this;
    }

    public TaslakEvraklarPage raporAlGonder() {
        btnRaporAl.click();
        return this;
    }

    public TaslakEvraklarPage topluSecimGonder() {
        btnTopluSecim.click();
        return this;
    }

    public TaslakEvraklarPage tarihDoldur(String text) {
        dateTarih.setValue(text);
        return this;
    }

    public TaslakEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    @Step("Sil butonuna basılır.")
    public TaslakEvraklarPage silSilGonder() {
        btnSilSil.click();
        return this;
    }

    @Step("Sil Onayı popupı kapatılır")
    public TaslakEvraklarPage silmeOnayıPopUpEvet() {
        btnPopSilEvet.click();
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton tıklanır.")
    public TaslakEvraklarPage evrakOnizlemeButonTikla(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        btnEvrakOnizleme.click();
        return this;
    }

    @Step("Not alanı doldurulur. \"{text}\" ")
    public TaslakEvraklarPage silAciklamaInputDolduur(String text) {
        txtSilAciklama.setValue(text);
        return this;
    }

    public TaslakEvraklarPage silButtonGonder() {
        btnSil.click();
        return this;
    }

    public TaslakEvraklarPage evrakKopyalaGonder() {
        btnEvrakKopyala.click();
        return this;
    }

    @Step("Paylaş butonuna tıkla.")
    public TaslakEvraklarPage paylasPaylasGonder() {
        btnPaylasPaylas.click();
        return this;
    }

    @Step("Açıklama alanını doldur.: {text}")
    public TaslakEvraklarPage paylasanAciklamaDoldur(String text) {
        txtPaylasanAciklama.setValue(text);
        return this;
    }

    @Step("Kişi alanını doldur.")
    public TaslakEvraklarPage paylasKisiDoldur(String kisi) {
        txtPaylasKisi.selectLov(kisi);
        return this;
    }

    @Step("Kişi alanını boşalt.")
    public TaslakEvraklarPage paylasKisiBosalt(String kisi) {
        txtPaylasKisi.selectLov(kisi);
        return this;
    }

    public TaslakEvraklarPage paylasKisiDoldur(String[] kisiler) {
        for (int i = 0; i < kisiler.length; i++) {
            txtPaylasKisi.selectLov(kisiler[i]);
        }
        return this;
    }

    public TaslakEvraklarPage paylasGonder() {
        btnPaylas.click();
        return this;
    }


    @Step("Evrak seç.")
    public TaslakEvraklarPage evrakSec(String konu, String gidecegiYer, String tarihSaat) {
        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text("Gideceği Yer: " + gidecegiYer))
                .filterBy(text(tarihSaat))
                .get(0)
                .click();
        return this;
    }

    @Step("Evrak seç : {konu}")
    public TaslakEvraklarPage evrakSecKonuyaGore(String konu) {
        tableEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Hareket Geçmişi açıklama kontrolü :\n \"{text}\" ")
    public TaslakEvraklarPage tabloKontol(String text) {
        tblHareketGecmisi
                .filterBy(Condition.text(text))
                .shouldHaveSize(1);
        return this;
    }


    @Step("Rapor al Excel")
    public TaslakEvraklarPage raporAl(String remoteDownloadPath) {
        deleteSpecificFile("Rapor_");

        sleep(3000);

        btnRaporAlExcel.click();
        islemMesaji().basariliOlmali();
        waitForLoadingJS(WebDriverRunner.getWebDriver(), 180);
        sleep(3000);
        searchDownloadedFileWithName(remoteDownloadPath, "Rapor_.xls");

        return this;
    }

    @Step("Evrak Geçmişi tıklanır.")
    public TaslakEvraklarPage evrakGecmisiTikla() {
   $(By.xpath("//a[text()='Evrak Geçmişi']")).click();
        return this;
    }
    @Step("Evrak Önizleme Ekranı ve evrak eki açıldığı görülür.")
    public TaslakEvraklarPage evrakOnizlemeveEkiKontrolu(String icerik) {
        Assert.assertEquals($(By.xpath("//div[text()='Evrak Önizleme']")).isDisplayed(), true);
//        Assert.assertEquals($(By.xpath("//div[text()='" + icerik + "']")).isDisplayed(), true);
        return this;
    }

    @Step("Paylaş tabına tıkla")
    public TaslakEvraklarPage paylasTabTikla() {
        btnPaylasTab.click();
        return this;
    }

    @Step("Birim butonuna tıkla")
    public TaslakEvraklarPage paylasBirimTikla() {
        btnPaylasBirim.click();
        return this;
    }

    @Step("Evrak kontrolu : \"{konu}\" ")
    public TaslakEvraklarPage evrakKontrolu(String konu) {

        tableEvraklar
                .filterBy(Condition.text("Konu: " + konu)).shouldHaveSize(1);
        return this;
    }

    @Step("Evrak kontrolu : \"{konu}\" , \"{shouldBeExist}\" ")
    public TaslakEvraklarPage evrakKontrolu(String konu,boolean shouldBeExist) {
        if(shouldBeExist) {
            tableEvraklar
                    .filterBy(Condition.text("Konu: " + konu)).shouldHaveSize(1);
        }
        else {
            tableEvraklar
                    .filterBy(Condition.text("Konu: " + konu)).shouldHaveSize(0);
        }
        return this;
    }

    @Step("Gelen Evraklar sayfasında evrakın geldiği kontrolu ve seçme")
    public TaslakEvraklarPage konuyaGoreEvrakIcerikGoster(String konu) {

        tableEvraklar
                .filterBy(text("Konu: " + konu))
                .first()
                .$("[id^='mainInboxForm:inboxDataTable'] [id$='detayGosterButton']").click();

        $(By.id("mainPreviewForm:eastLayout")).waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Kişi alanını boşalt.")
    public TaslakEvraklarPage paylasilanKisileriTemizle() {
        txtPaylasKisi.clearAllSelectedItems();
        return this;
    }


}
