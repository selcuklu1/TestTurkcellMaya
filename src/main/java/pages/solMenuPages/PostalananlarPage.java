package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
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

    SelenideElement btnIcDısEvrakIkonu = $("[id^='mainInboxForm:inboxDataTable:0:j_idt'] [class$='document-typeIcDisBelgeNiteliksiz']");


    //ElementsCollection  tblPostalananlartbl =  $$("tbody[id='mainInboxForm:inboxDataTable_data']");
    //Hüseyin
    ElementsCollection tablePostalananlar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement btnPostaDetayi = $x("//span[text() = 'Posta Detayı']/../../..//button");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement tblPostalananYerler = $x("//*[@id='mainPreviewForm:postalananDataGrid']");


    SelenideElement btnIcerikPostaDetayiTuzelKisiGuncelle = $("[id='inboxItemInfoForm:postalananDataGrid'] span[class='ui-button-icon-left ui-icon update-icon']");
    SelenideElement btnGuncelle = $("[id='mainPreviewForm:postalananDataGrid'] span[class='ui-button-icon-left ui-icon update-icon']");
    SelenideElement btnTuzelKisiGuncelle = $("[id='mainPreviewForm:postaGuncelleDialog'] button[id^='mainPreviewForm']");
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
    SelenideElement popupEvrakYazdirma = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogYazdir']");
    SelenideElement tuzelKisiGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[4]/td[8]/div/button[1]");
    //
    SelenideElement kullaniciGuncelle = $x("//*[@id='mainPreviewForm:postalananDataGrid']/tbody/tr/td/div/table/tbody/tr[5]/td[8]/div/button[1]");
    SelenideElement btnIcerikPDTebligatTarihGnc = $x("//*[@id='inboxItemInfoForm:tebligatMazbatasiTarihiId_input']");
    SelenideElement btnIcerikPDPostaAciklama = $x("//*[@id='inboxItemInfoForm:postaGuncellePanel']/tbody/tr[3]/td[3]/textarea");
    SelenideElement btnIcerikPDPostaKodGnc = $x("//*[@id='inboxItemInfoForm:postaGuncellePanel']/tbody/tr[2]/td[3]/input");
    SelenideElement btnIcerkPDGuncellemeKaydet = $x("//*[@id='inboxItemInfoForm:postaGuncelleDialog']/div[2]/div/div/button");
    SelenideElement icerikEvrakSayisi = $x("//*[@id='inboxItemInfoForm:evrakDetayPanelGrid']/tbody/tr[3]/td[3]/label");
    SelenideElement btnEtiketPopupKapat = $x("//*[@id='mainPreviewForm:showAppletContainer']/div/div[1]/a/span");
    //  SelenideElement btnDagitimYerDetayKapat = $x("//*[@id='mainPreviewForm:dagitimPlaniDetayViewDialog']/div[1]/a/span");
    SelenideElement btnEvrakEkleri = $(By.xpath("//a[text() = 'Evrak Ekleri']"));
    SelenideElement btnEvrakEyazismaPaket = $("//a[text() = 'E-Yazışma Paketi']");
    ElementsCollection tblEvrakDetaylariUstVeriler = $$("tbody[id='postaDetayYazdirForm:dtPostaEvrakUstVeri_data'] tr[data-ri]");


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

    @Step("Postalanan Evrak içi Evrak Ekleri seçimi ve kontrol")
    public PostalananlarPage btnEvrakEkleri() {
        btnEvrakEkleri.click();
        return this;
    }

    @Step("Postalanan Evrak içi Evrak Ekleri kontrolü : \"{ek}\" ")
    public PostalananlarPage evrakEkleriKontrol (String ek) {
        ElementsCollection tblEvrakEkleri = $$("tbody[id^='mainPreviewForm:'][id$=':ekListesiOnizlemeDataTable_data'] tr[data-ri]");
        tblEvrakEkleri
                .filterBy(Condition.text(ek))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Posta Detayi Postalanan Evrak Posta Detayi kontrolü.")
    public PostalananlarPage postaDetayiPostalananYerlerKontrolu (String gonderilenYer,String postaKodu,String aciklama) {
        ElementsCollection tblPostaDetayiPostalananYerler= $$("tbody[id^='mainPreviewForm:'][id$='_data'] tr[data-ri]");
        tblPostaDetayiPostalananYerler
                .filterBy(Condition.text(gonderilenYer))
                .filterBy(Condition.text(postaKodu))
                .filterBy(Condition.text(aciklama))
                .shouldHaveSize(1);
        return this;
    }
    @Step("Posta Detayi Postalanan Evrak Posta Detayi Yazdır butonu tıklanır.")
    public PostalananlarPage postaDetayiPostalananYerlerYazdir(String gonderilenYer,String postaKodu,String aciklama) {
        ElementsCollection tblPostaDetayiPostalananYerler= $$("tbody[id^='mainPreviewForm:'][id$='_data'] tr[data-ri]");
        tblPostaDetayiPostalananYerler
                .filterBy(Condition.text(gonderilenYer))
                .filterBy(Condition.text(postaKodu))
                .filterBy(Condition.text(aciklama))
                .first()
                .$(By.xpath("//span[text()='Yazdır']/../../button[2]")).click();
        return this;
    }

    @Step("Evrak Detayları pop up Üst Veriler Yazdır tıklanır")
    public PostalananlarPage evrakDetaylariUstVerilerYazdirButonTikla(String konu) {
        tblEvrakDetaylariUstVeriler
                .filterBy(text(konu))
                .first()
                .$("button").click();
        return this;
    }

    @Step("Pdf kontrolü Yapılır.")
    public PostalananlarPage pdfKontrol() {
        switchTo().window(1);

        sleep(3000);
        takeScreenshot();

        closeNewWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Postalanan Evrak içi E-yazışma paketi içerik kontrol")
    public PostalananlarPage btnEyazismaPaket() {
        btnEvrakEyazismaPaket.click();
        return this;
    }

    @Step("Evrak seçilir")
    public PostalananlarPage evrakSec(String konu, String yer, String tarih) {
        $$("[id^='mainInboxForm:inboxDataTable_data'] > tr").filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).get(0).click();
        return this;
    }
    @Step("Evrak seçilir")
    public PostalananlarPage evrakSec(String konu) {
        $$("[id^='mainInboxForm:inboxDataTable_data'] > tr").filterBy(Condition.text(konu)).get(0).click();
        return this;
    }

    @Step("Evrak Önizleme \"{btnText}\" buton kontrolü.")
    public PostalananlarPage evrakOnizlemeButonKontrol(String btnText) {
        SelenideElement btnEvrakOnizleme = $(By.xpath("//span[text()='" + btnText + "']/../../..//button"));
        Assert.assertEquals(btnEvrakOnizleme.isDisplayed(),true);
        return this;
    }
    @Step("Evrak Önizleme Evrak Ek kontrolü.")
    public PostalananlarPage evrakOnizlemeEvrakEkKontrolu() {
        SelenideElement btnEvrakEk = $(By.xpath("//div[@class='textLayer']"));
        Assert.assertEquals(btnEvrakEk.isDisplayed(),true);
        return this;
    }

    @Step("Evrak içerik göster")
    public PostalananlarPage evrakSecIcerikGoster(String konu, String yer, String tarih) {
        $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']").filterBy(Condition.text(konu))
                .get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Sağ üstte Posta Detayı ikonunun geldiği görülür.")
    public PostalananlarPage postaDetayGeldigiGorme(){
        boolean durum = $$("[class='ui-button-icon-left ui-icon postala']").size()==1;
        Assert.assertEquals(durum,true);
        takeScreenshot();
        return this;
    }

    @Step("Filtrele Seç")
    public PostalananlarPage filtreSec(String text) {
        cmbFiltre.selectOption(text);
        return this;
    }

    @Step("Postalanan yerler tablosu içerik ve kontrolleri")
    public PostalananlarPage postalananyerlerKontrol() {
        String kontrl = tblPostalananYerler.getAttribute("innerText");
        System.out.println(kontrl);
        return this;
    }

    @Step("Gönderilen yerlerin listelendiği görülür")
    public PostalananlarPage gonderilenyerlerKontrol () {
        boolean durum = $$("[id$='postalananDataGrid']").size()>0;
        Assert.assertEquals(durum,true);
        takeScreenshot();
        return this;
    }

    @Step("Tüzel kişi ve kullanıcı satırının açıklama alanının dolu ve doğru geldiği görülür.")
    public PostalananlarPage tuzelKisiVeAciklamaAlanlarDoluGeldigiGorme(String kisi,String aciklama){
        boolean durum = $$("[id$='postalananDataGrid']").size()==1;
        boolean durum2 = $$("[id$='postalananDataGrid']").filterBy(Condition.text(kisi)).size()==1;
        Assert.assertEquals(durum,durum2);
        return this;
    }

    @Step("Postalanan Evrak Orjinalini Yazdır")
    public PostalananlarPage evrakOrjinaliniYazdir() {
        SelenideElement postalananEvrakOrjYazdir = $x("//button[span[text()='Yazdır']]");
        postalananEvrakOrjYazdir.click();
        return this;
    }

    @Step("Sayfada ara doldur")
    public PostalananlarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    @Step("Dağıtım planı Yazdır")
    public PostalananlarPage dagitimPlanYazdir() {
        postalananEvrakYazdir.click();
        return this;
    }

    @Step("Yazdır popup içinde Üstyazı ve Ekleri yazdir kontrolü")
    public PostalananlarPage yazdirpopupYazdirButonktrl() {
        SelenideElement evrakEkleri = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakEk_data']");
        evrakEkleri.exists();
        SelenideElement evrakYazdirButonktrl = $x("//span[text()='Yazdır']");
        evrakYazdirButonktrl.exists();

        return this;
    }

    @Step("Ekleri Yazdırma butonu tıklama")
    public PostalananlarPage btnEkleriPopupiciYazdir() {
        SelenideElement evrakYazdirButonktrl = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakEk_data']/tr[1]/td[7]/div/button");
        evrakYazdirButonktrl.click();
        return this;
    }

    @Step("Ekleri Yazdirma butonu , PDF'leri açma ve kontröl")
    public PostalananlarPage eklerYazdirPopupbtn() {
        SelenideElement evrakYazdirButonktrl = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakEk_data']/tr[1]/td[7]/div/button");
        evrakYazdirButonktrl.click();
        switchTo().window(1);
        SelenideElement ickKtrl = $x("//*[@id='plugin']");
        ickKtrl.exists();
        closeNewWindow();
        switchTo().window(0);
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

    @Step("Etiket Bastir Popup Kapat")
    public PostalananlarPage btnPopupEtiketBastirKapat() {
        btnEtiketPopupKapat.click();
        return this;
    }

    @Step("Evrak'ın \"{konu}\" adlı konu ile geldiği görünür. Geldiği yer:\"{gidecegiYer}\" Evrak tarihi:\"{evrakTarihi}\"")
    public PostalananlarPage evrakGeldigiGorme(String konu, String gidecegiYer, String evrakTarihi) {
        boolean durum = $$("[id='mainInboxForm:inboxDataTable_data'] > tr").filterBy(Condition.text(konu)).size()==1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }
    @Step("Evrakların listelendiği görülür.")
    public PostalananlarPage tabloEvrakGeldigiGorme() {
        tablePostalananlar.filterBy(Condition.text("Konu:"))
                .filterBy(Condition.text("Gideceği Yer:"))
                .filterBy(Condition.text("Evrak Tarihi:"));

        takeScreenshot();
        return this;
    }


    @Step("Evrak seç")
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
    @Step("Postalanan Evrak Detayları , Sayisi ve kontrolü")
    public String evSay() {
        return $x("//tbody/tr[3]/td[3]/label").getAttribute("outerText");
    }

    @Step("Postalanan Evrak Icerik icindeki Evrak Sayisi")
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

    @Step("\"{Konu}\" Kontrol değerlerine göre postaları filtrele, tarih ve no kontrol, İç süret ve Dış süret Filtrelenen postanın Icerik Goster butonuna tıkla ")
    public PostalananlarPage btnFiltrenenPostaIcerikGoster(String Konu) throws InterruptedException {
        filter().findRowsWith(Condition.text(Konu)).first().click();
        String idAtr;
        idAtr = filter().findRowsWith(Condition.text(Konu)).first().getAttribute("data-ri");
        System.out.println(idAtr);
        String IcerikId = "mainInboxForm:inboxDataTable:" + idAtr + ":detayGosterButton";
        SelenideElement filteredIcerikGoster = $(By.id(IcerikId));
        String TarihId = "//*[@id='mainInboxForm:inboxDataTable:" + idAtr + ":evrakTable']/tbody/tr[1]/td[3]";
        SelenideElement filteredTarihId = $x(TarihId);
        filteredTarihId.getAttribute("innerText");
        SelenideElement icSuret = $x("//a[text() = 'İç Suret']");
        icSuret.exists();
        SelenideElement disSuret = $x("//a[text() = 'Dış Suret']");
        disSuret.exists();
        filteredIcerikGoster.click();
        Thread.sleep(1000);
        return this;
    }

    @Step("İç ve Dış Suret - PDF kontrol")
    public PostalananlarPage icDisSuretKtrl() {
        SelenideElement icSuret = $x("//a[text() = 'İç Suret']");
        SelenideElement disSuret = $x("//a[text() = 'Dış Suret']");
        SelenideElement Pdfktrl = $x("//a[text() = 'EK-1    TS2235_PDF']");
        icSuret.exists();
        disSuret.exists();
        Pdfktrl.exists();
        return this;

    }

    @Step("Icerik içinde Ilgileri Tabına tıklama ve kontrol (kontrol için 500ms delay bulunmakta)")
    public PostalananlarPage btnIcerikIlgileriTab() throws InterruptedException {
        btnIlgileriIcerik.click();
        Thread.sleep(500);
        return this;
    }

    @Step("Icerik içinde Ekleri Tabına tıklama ve kontrolü")
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

    @Step("Filtereden Postaladıklarım checkini işaretle")
    public PostalananlarPage btnFiltrePostaladiklarim() {

        btnRadioPostaladiklarim.click();
        return this;
    }

    @Step("Filtreden başlangıç tarihi girişi")
    public PostalananlarPage btnFiltreBaslangicTarihi(String date) {
        btnFiltreBaslangicTarihi.setValue(date);
        return this;
    }

    @Step("Icerik Posta Detay Tuzel Kisi Tebligat Tarih kontrol ve Guncelle \"{date}\"")
    public PostalananlarPage btnIcerikPDTuzelKisiTebTarGnc(String date) {
        btnIcerikPDTebligatTarihGnc.setValue(date);
        return this;
    }

    @Step("Filtre sekmesini aç")
    public PostalananlarPage btnFiltreSpan() {
        btnFiltreSpan.click();
        return this;
    }

    @Step("Icerik içindeki posta detayi butonu ,Postalanan yerler kontrülü ve iç sayfa tüzel kisi guncelleme")
    public PostalananlarPage btnIcerikPostaDetayTuzelKisiGnc() {
        btnIcerikPostaDetayiTuzelKisiGuncelle.click();
        return this;
    }

    @Step("Icerik içindeki posta detayi butonu iç sayfa tuzel kisi posta kodu kontrol ve guncelleme \"{postaKodu}\"")
    public PostalananlarPage btnIcerikPosDetTuzKisPosKodGnc(String postaKodu) {
        btnIcerikPDPostaKodGnc.setValue(postaKodu);
        return this;

    }

    @Step("Icerik içindeki posta detayi buton tuzel kisi posta açıklama kontrol ve güncelle \"{aciklama}\"")
    public PostalananlarPage btnIcerikPDTuzelKisiPosAcikGnc(String aciklama) {
        btnIcerikPDPostaAciklama.setValue(aciklama);
        return this;

    }

    @Step("Icerik Posta Detay Pop up Guncelleme kaydet")
    public PostalananlarPage btnIcerikPDPopupKaydet() {
        btnIcerkPDGuncellemeKaydet.click();
        return this;
    }

    @Step("Tuzel Kisi Guncelle")
    public PostalananlarPage btnTuzelKisiGuncelle() {
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

    @Step("Evrak Yazdır Popup içi Üst Veri Pdf yazdırma, kırmızı alan içerik kontrolü")
    public PostalananlarPage popupYazpdfkontrolveKapatma() {
        popupEvrakYazdirma.click();
        switchTo().window(1);
        closeNewWindow();
        switchTo().window(0);
        SelenideElement ustyazi = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakUstVeri_data']/tr/td[2]/div");
        String pdf = ustyazi.getAttribute("innerText");
        System.out.println(pdf);

        return this;

    }

    @Step("Evrak yazdırma popup kapatma")
    public PostalananlarPage popupkapatma() {
        SelenideElement popupkapat = $x("//*[@id='postaDetayYazdirForm:dlgPostaDetayYazdir']/div[1]/a/span");
        popupkapat.click();
        return this;
    }

    @Step("Etiket Bastir ve kontrol")
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

    @Step("Tek imzacısının doğru olarak geldiği görülür")
    public PostalananlarPage tekImzaciKontrol(String imzaci) {

        ElementsCollection trParafImzaAkisListesi = $$("[id='mainInboxForm:imzaListesiDataTable_data'] tr");

        trParafImzaAkisListesi
                .filterBy(text(imzaci))
                .filterBy(text("İmza"))
                .get(0)
                .shouldBe(exist);
        return this;
    }

    @Step("Sağda evrak ekleri, ilgi bilgileri, evrak geçmişi, evrak notları tablarının geldiği kontrolu")
    public PostalananlarPage sagTabKontrol() {

        SelenideElement tavEvrakEkleri = $(By.xpath("//a[text()='Evrak Ekleri']"));
        SelenideElement tavEvrakGeçmişi = $(By.xpath("//a[text()='Evrak Geçmişi']"));
        SelenideElement tavEvrakNotlari = $(By.xpath("//a[text()='Evrak Notları']"));

        Assert.assertEquals(tavEvrakEkleri.isDisplayed(), true, "Evrak Ekleri");
        Assert.assertEquals(tavEvrakGeçmişi.isDisplayed(), true, "Evrak Geçmişi");
        Assert.assertEquals(tavEvrakNotlari.isDisplayed(), true, "Evrak Notları");

        return this;
    }

    @Step("İç-dış evrak ikonu kontrolu")
    public PostalananlarPage icDisEvrakIkonuKontrolu() {

       Assert.assertEquals(btnIcDısEvrakIkonu.isDisplayed(), true);
        return this;
    }

    @Step("Evrak birim postacı ile login ")
    public PostalananlarPage birimLogin(String user, String pass) {
        System.out.println(user);
        System.out.println(pass);
        return this;
        }

        @Step("Postalanan evrak içinde Posta arama \"{}\" ")
    public PostalananlarPage t2076PostaArama (String konu) {

        return this;
        }

    @Step("Postalananlar Evraklar listesinde evrakın listelenmediği kontrolu")
    public PostalananlarPage konuyaGoreEvrakGelmemeKontrolu(String konu) {

        boolean durum = tblEvraklar
                .filterBy(Condition.text(konu))
                .size() == 0;

        Assert.assertEquals(durum, true);

        return this;
    }
}
