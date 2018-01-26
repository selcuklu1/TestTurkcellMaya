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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PostalananlarPage extends MainPage {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement chkKepIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:keplePostalananlarCheckbox_input"));
    SelenideElement chkMedasIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:medaslaPostalananlarCheckbox_input"));
    SelenideElement chkPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox_input"));
    SelenideElement tblEvrakSec = $(By.id("mainInboxForm:inboxDataTable:1:evrakTable"));
    SelenideElement tblPostalananlartbl = $(By.id("mainInboxForm:inboxDataTable_data"));
    SelenideElement btnRadioPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox"));
    SelenideElement tblEvrakDetayPanel = $(By.id("mainPreviewForm:evrakDetayPanelGrid"));
    //ElementsCollection  tblPostalananlartbl =  $$("tbody[id='mainInboxForm:inboxDataTable_data']");
    //Hüseyin
    ElementsCollection tablePostalananlar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPostaDetayi = $x("//span[text() = 'Posta Detayı']/../../..//button");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    SelenideElement btnIcerikPostaDetayiTuzelKisiGuncelle = $x("//*[@id='inboxItemInfoForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[4]/td[8]/div/button[1]");
    SelenideElement btnGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[2]/td[8]/div/button[1]");
    SelenideElement btnTuzelKisiGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[4]/td[8]/div/button[1]");
    SelenideElement txtPosta = $x("//*[@id=\'mainPreviewForm:postaGuncellePanel\']/tbody/tr[2]/td[3]/input");
    SelenideElement txtAciklama = $x("//*[@id=\'mainPreviewForm:postaGuncellePanel\']/tbody/tr[3]/td[3]/textarea");
    SelenideElement btnKaydet = $x("//span[text() = 'Kaydet']");
    SelenideElement btnTarihGuncelle = $(By.id("mainPreviewForm:tebligatMazbatasiTarihiId_input"));
    ElementsCollection dlgPostaGuncelle = $$("table[id='mainPreviewForm:postaGuncellePanel']");
    SelenideElement btnKurdele = $(By.id("mainInboxForm:inboxDataTable:0:btnImzasiz"));
    SelenideElement btnImzaciPopupKapat = $x("//*[@id='mainInboxForm:imzaListesiDialog']/div[1]/a/span");
    SelenideElement btnTamEkran = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement btnTamEkranKapat = $x("//*[@id='fullScreenDialog']/div[1]/a/span");
    SelenideElement btnIcerikGoster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnIlgileriIcerik = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:2:cmdbutton"));
    SelenideElement btnEkleriIcerik = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:1:cmdbutton"));
    SelenideElement btnIcerikPencereKapat = $x("//*[@id='windowItemInfoDialog']/div[1]/a[1]/span");
    SelenideElement btnKapatmaOnayı = $(By.id("kapatButton"));
    SelenideElement btnFiltreBaslangicTarihi = $x("//*[@id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid']/tbody/tr[2]/td[2]/table/tbody/tr/td[1]/span/input");
    SelenideElement btnFiltreSpan = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion"));
    SelenideElement ImzaDialog = $(By.id("mainInboxForm:imzaListesiDialog"));
    SelenideElement postalananEvrakYazdir = $x("//button[span[text()='Yazdır']]");
    SelenideElement postalananEvrakEtiketYazdir = $x("//button[span[text()='Etiket Bastır']]");
    SelenideElement tabIcerikIlgileri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:2:cmdbutton"));
    SelenideElement tabIcerikEkleri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:1:cmdbutton"));
    SelenideElement tabIcerikKapat = $x("//*[@id='windowItemInfoDialog']/div[1]/a[1]/span");
    SelenideElement tabIcerikKapatmaOnay = $(By.id("kapatButton"));
    SelenideElement btnIcerikPostaDetayi = $x("//*[@id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton']/span[1]");
    //
    SelenideElement tuzelKisiGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[4]/td[8]/div/button[1]");
    //
    SelenideElement kullaniciGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[5]/td[8]/div/button[1]");
    SelenideElement btnIcerikPDTebligatTarihGnc = $x("//*[@id='inboxItemInfoForm:tebligatMazbatasiTarihiId_input']");
    SelenideElement btnIcerikPDPostaAciklama = $x("//*[@id='inboxItemInfoForm:postaGuncellePanel']/tbody/tr[3]/td[3]/textarea");
    SelenideElement btnIcerikPDPostaKodGnc = $x("//*[@id='inboxItemInfoForm:postaGuncellePanel']/tbody/tr[2]/td[3]/input");
    SelenideElement btnIcerkPDGuncellemeKaydet = $x("//*[@id='inboxItemInfoForm:postaGuncelleDialog']/div[2]/div/div/button");
    SelenideElement icerikEvrakSayisi = $x("//*[@id='inboxItemInfoForm:evrakDetayPanelGrid']/tbody/tr[3]/td[3]/label");
    //SelenideElement btnEtiketPopupKapat = $x("//*[@id='mainPreviewForm:showAppletContainer']/div/div[1]/a/span");
    //  SelenideElement btnDagitimYerDetayKapat = $x("//*[@id='mainPreviewForm:dagitimPlaniDetayViewDialog']/div[1]/a/span");


    @Step("Postalananlar sayfası aç")
    public PostalananlarPage openPage() throws InterruptedException {
        solMenu(SolMenuData.BirimEvraklari.Postalananlar);
        String pageTitle = SolMenuData.BirimEvraklari.Postalananlar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        Thread.sleep(1500);
        return this;
    }

    @Step("Evrak seçilir")
    public PostalananlarPage evrakSec(String konu, String yer, String tarih) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).get(0).click();
        return this;
    }

    @Step("Evrak içerik göster")
    public PostalananlarPage evrakSecIcerikGoster(String konu, String yer, String tarih) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Filtrele Seç")
    public PostalananlarPage filtreSec(String text) {
        cmbFiltre.selectOption(text);
        return this;
    }

    @Step("Sayfada ara doldur")
    public PostalananlarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    @Step("Başlangıç tarihi doldur")
    public PostalananlarPage baslangicTarihiDoldur(String text) {
        dateBaslangicTarihi.setValue(text);
        return this;
    }
    @Step("Icerik Detay Posta Detay Butonu")
    public PostalananlarPage icerikDetayPostaDetayi() {
        btnIcerikPostaDetayi.click();
        return this;
    }

    @Step("Bitiş tarihi doldur")
    public PostalananlarPage bitisTarihiDoldur(String text) {
        dateBitisTarihi.setValue(text);
        return this;
    }

    @Step("Kep ile PostalananlarPage seç")
    public PostalananlarPage kepIlePostalananlarSec(boolean text) {
        chkKepIlePostalananlar.setSelected(text);
        return this;
    }

    @Step("Medas ile PostalananlarPage seç")
    public PostalananlarPage medasIlePostalananlarSec(boolean text) {
        chkMedasIlePostalananlar.setSelected(text);
        return this;
    }

    @Step("Postaladıklarım seç")
    public PostalananlarPage postaladiklarimSec() {
        tblEvrakSec.click();
        return this;
    }

    @Step("Evrak'ın \"{konu}\" adlı konu ile geldiği görünür. Geldiği yer:\"{gidecegiYer}\" Evrak tarihi:\"{evrakTarihi}\"")
    public PostalananlarPage evrakGeldigiGorme(String konu, String gidecegiYer, String evrakTarihi){
        boolean durum = tablePostalananlar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(gidecegiYer))
                .filterBy(Condition.text(evrakTarihi)).get(0).shouldBe(visible).exists()==true;
        Assert.assertEquals(durum,true);
        takeScreenshot();
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
    public PostalananlarPage postaDetayiTikla() {
        btnPostaDetayi.click();
        return this;
    }

    @Step("Tablodan Evrak Seçimi")
    public PostalananlarPage tablodanSecim() throws InterruptedException {

        solMenu(SolMenuData.BirimEvraklari.Postalananlar);

        Thread.sleep(2000);
        tablePostalananlar.first().click();

        return this;
    }

    @Step("GonderimGuncelleme")
    public PostalananlarPage btnGuncelle() {

        btnGuncelle.click();
        return this;
    }

    @Step("Tarih Guncelle")
    public PostalananlarPage btnTarihGuncelle(String text) {
        btnTarihGuncelle.click();
        btnTarihGuncelle.setValue(text);

        return this;
    }

    @Step("Posta Kodu Güncelle")
    public PostalananlarPage btnPostakoduGuncelle(String txt) {
        txtPosta.setValue(txt);
        return this;
    }

    @Step("Açıklama Güncelle")
    public PostalananlarPage txtAciklama(String txt) {
        txtAciklama.setValue(txt);

        return this;

    }

    @Step("Guncelleme Kaydet")
    public PostalananlarPage btnKaydet() {
        btnKaydet.click();

        return this;
    }

    @Step("Evrak Detay Panel Sayıyı çekme")
    public PostalananlarPage detayEvrakSayisi() {

        String txt = $x("//tbody/tr[3]/td[3]/label").getAttribute("outerText");

        System.out.println(txt);
        return this;
    }


    /**
     * @return
     */
        @Step("Postalanan Evrak Sayisi")
        public String evSay() {
        return $x("//tbody/tr[3]/td[3]/label").getAttribute("outerText");
    }

    @Step ("Postalanan Evrak Icerik icindeki Evrak Sayisi")
    public String icerikEvrakSay() {
            return $x("//*[@id='inboxItemInfoForm:evrakDetayPanelGrid']/tbody/tr[3]/td[3]/label").getAttribute("innerText");
    }
    @Step("Kurdele Butonuna Tıkla")
    public PostalananlarPage btnKurdele() {

        btnKurdele.click();
        return this;
    }

    @Step("Imza Popup kapat")
    public PostalananlarPage btnImzaciPopupKapat() {
            btnImzaciPopupKapat.exists();
            btnImzaciPopupKapat.scrollTo();
        clickJs(btnImzaciPopupKapat);
        return this;
    }

    @Step("Tam Ekran görünüme tıkla")
    public PostalananlarPage btnTamEkran() {
        btnTamEkran.click();
        return this;
    }

    @Step("Tam Ekran görünümü kapat")
    public PostalananlarPage btnTamEkranKapat() {
        btnTamEkranKapat.click();
        return this;
    }

    @Step("Icerik Goster butonuna tıkla")
    public PostalananlarPage btnIcerikGoster() throws InterruptedException {
        btnIcerikGoster.click();
        Thread.sleep(1000);
        return this;
    }
    @Step("Filtrelenen postanın Icerik Goster butonuna tıkla")
    public PostalananlarPage btnFiltrenenPostaIcerikGoster(String Konu) throws InterruptedException {
        filter().findRowsWith(Condition.text(Konu)).first().click();
        String idAtr ;
        idAtr = filter().findRowsWith(Condition.text(Konu)).first().getAttribute("data-ri");
        System.out.println(idAtr);
        String IcerikId = "mainInboxForm:inboxDataTable:" + idAtr + ":detayGosterButton";
        SelenideElement filteredIcerikGoster = $(By.id(IcerikId));
        filteredIcerikGoster.click();
        Thread.sleep(1000);
        return this;
    }

    @Step("Icerik içinde Ilgileri Tabına tıklama")
    public PostalananlarPage btnIcerikIlgileriTab() throws InterruptedException {
        btnIlgileriIcerik.click();
        Thread.sleep(500);
        return this;
    }

    @Step("Icerik içinde Ekleri Tabına tıklama")
    public PostalananlarPage btnIcerikEkleriTab() {
        btnEkleriIcerik.click();
        return this;
    }

    @Step("Icerik Detay Kapatma")
    public PostalananlarPage btnIcerikDetayKapat() {
        btnIcerikPencereKapat.click();
        btnKapatmaOnayı.click();
        return this;
    }

    @Step("Filtereden Postaladıklarımı işaretle")
    public PostalananlarPage btnFiltrePostaladiklarim() {

        btnRadioPostaladiklarim.click();
        return this;
    }

    @Step("Filtreden başlangıç tarihi girişi")
    public PostalananlarPage btnFiltreBaslangicTarihi(String date) {

        btnFiltreBaslangicTarihi.setValue(date);
        return this;
    }

    @Step("Icerik Posta Detay Tuzel Kisi Tebligat Tarih Guncelle")
    public PostalananlarPage btnIcerikPDTuzelKisiTebTarGnc (String date) {
            btnIcerikPDTebligatTarihGnc.setValue(date);
            return this;
    }

    @Step("Filtre sekmesini aç")
    public PostalananlarPage btnFiltreSpan() {
        btnFiltreSpan.click();
        return this;
    }
    @Step("Icerik içindeki posta detayi butonu iç sayfa tuzek kisi guncelleme")
    public PostalananlarPage btnIcerikPostaDetayTuzelKisiGnc () {
            btnIcerikPostaDetayiTuzelKisiGuncelle.click();
            return this;
    }
    @Step("Icerik içindeki posta detayi butonu iç sayfa tuzel kisi posta kodu guncelleme")
    public PostalananlarPage btnIcerikPosDetTuzKisPosKodGnc (String postaKodu) {
           btnIcerikPDPostaKodGnc.setValue(postaKodu);
           return this;

    }
    @Step("Icerik içindeki posta detayi buton tuzel kisi posta açıklama güncelle")
    public PostalananlarPage btnIcerikPDTuzelKisiPosAcikGnc (String aciklama) {
            btnIcerikPDPostaAciklama.setValue(aciklama);
            return this;

    }
    @Step("Icerik Posta Detay Pop up Guncelleme kaydet")
    public PostalananlarPage btnIcerikPDPopupKaydet () {
        btnIcerkPDGuncellemeKaydet.click();
        return this;
    }
    @Step("Tuzel Kisi Guncelle")
    public PostalananlarPage btnTuzelKisiGuncelle () {
        btnTuzelKisiGuncelle.click();
        return this;

    }
    @Step("Imza Dialog Ekranını göster")
    public PostalananlarPage mngImzaDialog() {

        ImzaDialog.click();
        ImzaDialog.scrollTo();
        return this;
    }

    @Step("Evrak Yazdır")
    public PostalananlarPage evrakYazdir() {
        postalananEvrakYazdir.click();
        return this;
    }

    @Step("Etiket Bastir")
    public PostalananlarPage etiketBastir() {

        postalananEvrakEtiketYazdir.click();
        return this;
    }

    @Step("Tuzel Kisi Posta Kodu Guncelle")
    public PostalananlarPage tuzelKisiPostaKodu() {

        tuzelKisiGuncelle.click();
        return this;
    }
    @Step("Kullanıcı Guncelle")
    public PostalananlarPage kullaniciGuncelle() {

        kullaniciGuncelle.click();
        return this;
    }


}
