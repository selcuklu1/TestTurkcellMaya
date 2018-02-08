package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureUtils;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

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
    SelenideElement cmbDagitimSekli = $("[id^='mainPreviewForm:dataTableId:0:j_idt'] [class*='ui-selectonemenu'] Select");
    SelenideElement btnIcerikEvrakGoster = $x("//*[@id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton']");
    SelenideElement btnComboEvrakGidisSekli = $x("//*[@id='inboxItemInfoForm:dataTableId_data']/tr[1]/td[3]/div/div/div/table/tbody/tr[3]/td/div/div[1]/select");
    SelenideElement txtEvrakKonuKontrol = $x("//*[@id='mainPreviewForm:evrakDetayPanelGrid']/tbody/tr[1]/td[3]/label");
    SelenideElement tblPostalanacakYerler = $x("//*[@id='mainPreviewForm:dataTableId_data']");
    SelenideElement slctYurtIciyurtdisi = $x("//*[@id='mainPreviewForm:dataTableId:0:yurtIciDisiId']");

    SelenideElement btnIcerikPostaKod = $x("//*[@id='inboxItemInfoForm:dataTableId']/table/tbody/tr/td[4]/div/div/div/table/tbody/tr[1]/td[2]/input");
    SelenideElement btnIcerikPostaAciklama = $x("//*[@id='inboxItemInfoForm:dataTableId']/table/tbody/tr/td[4]/div/div/div/table/tbody/tr[2]/td[2]/textarea");
    SelenideElement btnIcerikPostaYazdir = $x("//*[@id='inboxItemInfoForm:dataTableId_data']/tr/td[5]/div/table/tbody/tr[1]/td/button");
    SelenideElement btnIcerikEtiketBastir = $x("//*[@id='inboxItemInfoForm:dataTableId_data']/tr/td[5]/div/table/tbody/tr[3]/td/button");
    SelenideElement btnIcerikPopupYazdir = $x("//*[@id='postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogYazdir']");
    SelenideElement btnIcerikPopupKapat = $x("//*[@id='postaDetayYazdirForm:dlgPostaDetayYazdir']/div[1]/a/span");
    SelenideElement btnIcerikEtiketAciklama = $x("//*[@id='inboxItemInfoForm:etiketMetinIDPostIslm']");
    SelenideElement btnEtiketpopupkapat = $x("//*[@id='inboxItemInfoForm:showAppletContainer']/div/div[1]/a/span");
    SelenideElement btnIcerikPostalama = $x("//*[@id='inboxItemInfoForm:postalaButton_id']");
    SelenideElement btnIcerikPostalamaEvet = $x("//*[@id='inboxItemInfoForm:postalaDogrulaDialogForm:evetButton_id']");
    SelenideElement btnFizikselEkBulunmaktadirIkon = $x("//span[@class='ui-button-icon-left ui-icon document-typeFizikselEk']");
    SelenideElement txtEvrakinFizikselEkivardir = $x("//*[contains(text(),'Evrakın fiziksel eki vardır, göndermeyi unutmayınız!')]");
    SelenideElement btnIcerikEvrakPostala = $x("//*[@id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton']");
    //Önizleme

    SelenideElement formEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    SelenideElement tabEvrakEkleri = $(By.xpath("//*[contains(text(),'Evrak Ekleri')]"));
    SelenideElement tabIlgiBilgileri = $(By.xpath("//*[contains(text(),'İlgi Bilgileri')]"));
    SelenideElement tabIlisikBilgileri = $(By.xpath("//*[contains(text(),'İlişik Bilgileri')]"));

    SelenideElement accordionEvrakEkleri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionEvrakEkleriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionEvrakEkleri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionEvrakEkleriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(3)");

    SelenideElement accordionIlgiBilgileri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionIlgiBilgileriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionIlgiBilgileri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionIlgiBilgileriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(3)");

    ElementsCollection tblOnIzlemeEkler = $$("[id*='ekListesiOnizlemeDataTable'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlgiBilgileri = $$("[id*='ilgiListesiDataTable_data'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlisikBilgileri = $$("[id*='ilisikListesiDataTable_data'] > tr[role='row']");

    @Step("Postalanacak Evraklar sayfası aç")
    public PostalanacakEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar);
        String pageTitle = SolMenuData.BirimEvraklari.PostalanacakEvraklar.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrakın fiziksel Eki vardır, göndermeyi unutmayınız yazısı kontrolü ve Tarih-sayı kontrolü")
    public PostalanacakEvraklarPage KntrlEvrakFizikselEkYaziSayTar() {
        txtEvrakinFizikselEkivardir.exists();
        return this;
    }

    @Step("Evrak seçilir")
    public PostalanacakEvraklarPage evrakSec(String konu, String yer, String tarih) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).get(0).click();
        return this;
    }

    @Step("Fiziksel Ek ikon kontrolü bulunmaktadır")
    public PostalanacakEvraklarPage btnFizikselEkIkonKontrol() {
        btnFizikselEkBulunmaktadirIkon.exists();
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

    @Step("Evrak içerik içindeki Evrak Postala butonu")
    public PostalanacakEvraklarPage btnEvrakIcerikEvrakPostala() {
        btnIcerikEvrakPostala.click();
        return this;
    }

    @Step("Evrak içerik Evrak Göster butonu")
    public PostalanacakEvraklarPage btnIcerikEvrakGoster() {
        btnIcerikEvrakGoster.click();
        return this;
    }

    @Step("Dağıtım Gidis Şekli seçimi \"{postaSekli}\" ")
    public PostalanacakEvraklarPage btnDagitimGidisSekli(String postaSekli) {
        String selectedTxt = btnComboEvrakGidisSekli.getSelectedText();
        System.out.println(selectedTxt);
        btnComboEvrakGidisSekli.selectOptionContainingText(postaSekli);
        return this;

    }

    @Step("Yurt içi yurt dışı seçimi \"{yurticdis}\" ")
    public PostalanacakEvraklarPage cmbYurticidisi(String yurt) {
        slctYurtIciyurtdisi.selectOption(yurt);
        return this;
    }

    @Step("Evrak içerik göster : \"{konu}\" ")
    public PostalanacakEvraklarPage evrakSecKonuyaGoreIcerikGoster(String konu) {
        tblEvraklar.filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Secim ve Icerik Kapatma")
    public PostalanacakEvraklarPage evrakSecIcerikKapat(boolean secim) {
        btnEvrakDetayKapat.get(0).click();
        if (secim == true) {
            $(By.id("kapatButton")).pressEnter();
        } else {
            $(By.id("iptalButton")).pressEnter();
        }
        return this;
    }

    @Step("Evrak Detayları kontrol")
    public PostalanacakEvraklarPage txtevrakDetayKontrol() {
        txtEvrakKonuKontrol.getSelectedText();
        return this;
    }

    @Step("Evrak Detay Postalanan yerler kontrolü - Tablodan kontröl")
    public PostalanacakEvraklarPage tblDetayPostalananYerlerKontrol() {
        tblPostalanacakYerler.getAttribute("childElementCount");
        String kntrltxt = tblPostalanacakYerler.getAttribute("innerText");
        if (kntrltxt == null) {
            System.out.println("Postalanacak Yerler Boş gelmiştir");
        }
        return this;
    }

    @Step("Evrak Postala")
    public PostalanacakEvraklarPage evrakPostala() {
        btnEvrakPostala.click();
        return this;
    }

    @Step("")
    public PostalanacakEvraklarPage alanKontrolleri(String konu,String[] title,String[] gonderildigYerler) {
        SelenideElement evrakDetaylariKonu = $("table[id='mainPreviewForm:evrakDetayPanelGrid'] tr:nth-child(1) td:nth-child(3) label");
        SelenideElement evrakDetaylariTarih = $("table[id='mainPreviewForm:evrakDetayPanelGrid'] tr:nth-child(4) td:nth-child(3) label");
        SelenideElement evrakDetaylariGonderen = $("table[id='mainPreviewForm:evrakDetayPanelGrid'] tr:nth-child(2) td:nth-child(3) label");

        ElementsCollection tblPostalanacakYerler = $$("tbody[id='mainPreviewForm:dataTableId_data'] tr[data-ri]");

        Assert.assertEquals(evrakDetaylariKonu.text().contains(konu),true);
        Assert.assertEquals(evrakDetaylariTarih.text().equals(getSysDateForKis()),true);
        Assert.assertEquals(evrakDetaylariGonderen.text().equals("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"),true);

        Allure.addAttachment("Evrak Detayları", "Evrak Detayları alanı kontrol edilmiştir.");

        for (int i =0 ; i<tblPostalanacakYerler.size();i++){

            tblPostalanacakYerler
                    .filterBy(Condition.text(title[i]))
                    .filterBy(Condition.text(gonderildigYerler[i]))
                    .shouldHaveSize(1);
            Allure.addAttachment("Postalanacak Yerler - Gönderim Şekli", title[i]+" - "+gonderildigYerler[i]);

        }







        return this;
    }

    @Step("Posta tiplerinin doğru olarak listelendiği görülür")
    public PostalanacakEvraklarPage postaTipleriListelendigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:dataTableId_data")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("{alan} - {kisi} : {gidisSekli} gönderici ve alıcı kep adreslerinin geldiği görülür.")
    public PostalanacakEvraklarPage postalanacakYerlerAlanGoreSecimGeldigiGorme(String alan, String kisi, String gidisSekli) {
        boolean durum = $$("[id='mainPreviewForm:dataTableId_data'] > tr[role='row']").filterBy(Condition.text(kisi))
                .filterBy(Condition.text(gidisSekli)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("\"{tuzelKisi}\" adlı tüzel kişiyi dağıtım planını \"{secilen}\" seçilir")
    public PostalanacakEvraklarPage dagitimSekliDegistirSec(String tuzelKisi, String secilen, String secim) {
        $$("[id='mainPreviewForm:dataTableId_data'] > tr").filterBy(Condition.text(tuzelKisi))
                .get(0).$$("[class='ui-helper-hidden-accessible']").get(0).parent().click();
        $$("[id*='mainPreviewForm:dataTableId:" + secim + "'][id$='panel'] ul li").filterBy(Condition.text(secilen)).get(0).click();
        return this;
    }

    @Step("")
    public PostalanacakEvraklarPage tuzelKisiPostaKoduVeAciklamaDoldur(String tuzelKisi, String postaKodu, String aciklama) {
        $$("[id='mainPreviewForm:dataTableId_data'] > tr").filterBy(Condition.text(tuzelKisi))
                .get(0).$$("input[type='text']").get(0).setValue(postaKodu);

        $$("[id='mainPreviewForm:dataTableId_data'] > tr").filterBy(Condition.text(tuzelKisi))
                .get(0).$$("textarea").get(0).setValue(aciklama);
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

    @Step("Postala")
    public PostalanacakEvraklarPage evrakPostalaPostala() {
        btnEvrakPostalaPostala.pressEnter();
        return this;
    }

    @Step("Belge elektronik imzalı değil! Evrakı postalamak üzeresiniz. Devam etmek istiyor musunuz? uyarısının geldiği görülür.")
    public PostalanacakEvraklarPage belgeElektronikImzaliDegilUyariGeldigiGorme() {
        boolean durum = $$("[id^='mainPreviewForm:postalaDogrulaDialogForm']").filterBy(Condition.visible).size() == 0;
        Assert.assertEquals(durum, false);
        takeScreenshot();
        return this;
    }

    @Step("Evet seçilir")
    public PostalanacakEvraklarPage belgeElektronikImzaliDegilUyariEvet() {
        $("[id$='evetButton_id']").click();
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

    @Step("Postalancak Evrak Orjinal Yazdır")
    public PostalanacakEvraklarPage postalanacakEvrakOrjYaz() throws InterruptedException {

        postalanacakEvrakOrijinalYazdir.click();
        btnPopupOrjYazdir.click();
        Thread.sleep(2000);

        return this;
    }

    @Step("PDF - Evrak sayısı - Yazışma Kuralları kontrol")
    public PostalanacakEvraklarPage pdfEvrakYazismaKuralkontrol() {
        switchTo().window(1);
        SelenideElement pdftab = $x("//*[@id='plugin']");
        String ktrl = pdftab.getValue();
        System.out.println(ktrl);
        return this;
    }

    @Step("Postalanacak Evrak Orjinal Yazdırma popup Kapatma")
    public PostalanacakEvraklarPage popupEvrOrjYazKapat() throws InterruptedException {

        closeNewWindow();
        switchTo().window(0);
        popupPostaYazdirmaKapat();
        return this;
    }

    @Step("Postalancak Eposta")
    public PostalanacakEvraklarPage postalacanakEposta(String eposta) {

        epostaTxt.setValue(eposta);
        return this;
    }

    @Step("Postalancak Postalama Açıklama")
    public PostalanacakEvraklarPage postalamaAciklama(String text) {

        epostaAciklama.setValue(text);
        return this;
    }

    @Step("Postalancak popup Posta yazdırma kapat")
    public PostalanacakEvraklarPage popupPostaYazdirmaKapat() throws InterruptedException {

        $(By.id("postaDetayYazdirForm:dlgPostaDetayYazdir")).shouldBe(Condition.visible);
        $x("//div[@id='postaDetayYazdirForm:dlgPostaDetayYazdir']//a[span[@class='ui-icon ui-icon-closethick']]").click();

        return this;
    }

    @Step("Postalanacan Evrak orjinal popup Kapatma")
    public PostalanacakEvraklarPage popupOrjYazPostaKapatma() {
        SelenideElement btnkp = $x("//*[@id='postaDetayYazdirForm:dlgPostaDetayYazdir']/div[1]/a/span");
        return this;
    }

    @Step("Popup postalanacak Evrak Yazdır")
    public PostalanacakEvraklarPage popupPostalanacakEvrakYazdir() throws InterruptedException {
        btnPopupYazdir.click();
        Thread.sleep(2000);

        return this;
    }

    @Step("Postalanacak evrak Postala")
    public PostalanacakEvraklarPage postala() {

        btnPostala.click();
        return this;
    }

    //    @Step("")
    public PostalanacakEvraklarPage popUpEvet() {
        $(By.id("mainPreviewForm:postalaDogrulaDialogForm:evetButton_id")).click();
        return this;
    }

    @Step("Postalanacak Evrak Postalama popup Evet seçmiş")
    public PostalanacakEvraklarPage dialogpostalaEvet() {

        btnDialogEvet.click();
        return this;
    }

    @Step("Postalanacak Evrak Dağitim Detayi")
    public PostalanacakEvraklarPage dagitimDetay() {
        btnDagitimYerDetay.click();
        return this;
    }

    @Step("Postalanacak Evrak içerik Göster")
    public PostalanacakEvraklarPage icerikGoster() {
        btnIcerikGoster.click();
        return this;
    }

    @Step("Postalanacak Evrak içerik ilgileri tab")
    public PostalanacakEvraklarPage icerikIlgileriTab() {
        tabIcerikIlgileri.click();
        return this;
    }

    @Step("Postalanacak Evrak içerik Ekleri tab")
    public PostalanacakEvraklarPage icerikEkleriTab() {
        tabIcerikEkleri.click();
        return this;
    }

    @Step("Postalanacak Evrak içerik detay pencere kapatma")
    public PostalanacakEvraklarPage icerikPencereKapatma() {
        tabIcerikKapat.click();
        tabIcerikKapatmaOnay.click();
        return this;
    }

    @Step("Postalanacak Evrak tüzel kişi posta kodu")
    public PostalanacakEvraklarPage tuzelKisiPostaKod(String postakod) {
        tuzelKisiPostaKodu.setValue(postakod);
        return this;

    }

    @Step("Postalanacak Evrak tüzel kişi posta açıklama")
    public PostalanacakEvraklarPage tuzelKisiPostaAciklama(String aciklama) {

        tuzelKisiPostaAciklama.setValue(aciklama);
        return this;
    }

    @Step("Postalanacak Evrak birim posta kodu")
    public PostalanacakEvraklarPage birimPostaKod(String postakod) {
        birimPostaKodu.setValue(postakod);
        return this;

    }

    public PostalanacakEvraklarPage birimPostaAciklama(String aciklama) {
        birimPostaAciklama.setValue(aciklama);
        return this;
    }

    @Step("Postalanacak Evrak kullanıcı posta kodu")
    public PostalanacakEvraklarPage ilkPostaPostaKod(String postakod) {
        ilkPostaPostaKodu.setValue(postakod);
        return this;
    }

    @Step("Postalanacak Evrak kullanıcı posta açıklama")
    public PostalanacakEvraklarPage ilkPostaAciklama(String aciklama) {

        ilkPostaAciklama.setValue(aciklama);
        return this;
    }

    @Step("Postalanacak Evrak etiket yazdir")
    public PostalanacakEvraklarPage etiketYazdir() throws InterruptedException {
        btnEtiketYazdir.click();
        Thread.sleep(1000);
        return this;
    }

    @Step("Postalanacak Evrak etiket yazdir popup kapat")
    public PostalanacakEvraklarPage etiketYazdirPopupKapat() {
        btnEtiketPopupKapat.click();
        return this;
    }

    @Step("Postalanacak Evrak dağıtım detay kapat")
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

    @Step("Postalanacak Evraklar listesinde evrak kontrolu")
    public PostalanacakEvraklarPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Postalanacak Evraklar listesinde evrak kontrolü:  \"{konu}\" ")
    public PostalanacakEvraklarPage konuyaGoreEvrakKontroluAllPages(String konu) {
        searchTable().searchInAllPages(true).findRows(text(konu)).getFoundRow().shouldBe(exist);
        return this;
    }

    @Step("Postalanacak Evraklar listesinden evrak önizlemede aç")
    public PostalanacakEvraklarPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Evrak Ek/İlgi tablarının geldiği kontrolu")
    public PostalanacakEvraklarPage tabKontrolleri() {

        formEvrakOnizleme.shouldBe(exist);
        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Postalanacak Evraklar/Evrak Ekleri tabını aç")
    public PostalanacakEvraklarPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Postalanacak Evraklar/İlgi Bilgieri tabını aç")
    public PostalanacakEvraklarPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }

    @Step("Dağıtım yeri seç")
    public PostalanacakEvraklarPage dagitimSecTbl1(String posta) {
        cmbDagitimSekli.selectOption(posta);
        return this;
    }

    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public PostalanacakEvraklarPage evrakEkleriAccordionKontrol() {

        accordionEvrakEkleriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen1.isDisplayed(), true);
        accordionEvrakEkleri1.click();
        Selenide.sleep(1000);
        accordionEvrakEkleri2.click();
        accordionEvrakEkleriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlgi Bilgileri Accordion kontrolu")
    public PostalanacakEvraklarPage ilgiBilgileriAccordionKontrol() {

        accordionIlgiBilgileriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen1.isDisplayed(), true);
        accordionIlgiBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlgiBilgileri2.click();
        accordionIlgiBilgileriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizlemede ekleri detay butonu kontrolu")
    public PostalanacakEvraklarPage evrakEklerindeDetayButonuKontrol(String ek1, String ek2) {

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek1))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek2))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ilgi bilgileri detay butonu kontrolu")
    public PostalanacakEvraklarPage ilgiBilgilerindeDetayButonuKontrol(String ilgiSayisi1, String ilgiSayisi2) {

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi1))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi2))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        return this;
    }

    @Step("Icerik Posta Kod alani")
    public PostalanacakEvraklarPage inputIcerikPstakod(String postaKod) {
        btnIcerikPostaKod.setValue(postaKod);
        return this;
    }

    @Step("Icerik Posta Aciklama alani")
    public PostalanacakEvraklarPage inputIcerikPostaAciklama(String postaAciklama) {
        btnIcerikPostaAciklama.setValue(postaAciklama);
        return this;
    }

    @Step("Icerik Posta Yazdir butonu")
    public PostalanacakEvraklarPage btnIcerikPostaYazdir() {
        btnIcerikPostaYazdir.click();
        return this;
    }

    @Step("Icerik Posta Yazdır popup Yazdir butonu")
    public PostalanacakEvraklarPage btnPopupPostaYazdirma() {
        btnIcerikPopupYazdir.click();
        return this;
    }

    @Step("Icerik Posta Yazdır popup kapat")
    public PostalanacakEvraklarPage btnYazdirPopupKapat() {
        btnIcerikPopupKapat.click();
        return this;
    }

    @Step("Icerik Etiket Bastir butonu")
    public PostalanacakEvraklarPage btnIcerikEtiketBastir() {
        btnIcerikEtiketBastir.click();
        return this;
    }

    @Step("Icerik Etiket popup Aciklama kontrol")
    public PostalanacakEvraklarPage txtPopupEtiketAciklama() {
        String etiketAciklama = btnIcerikEtiketAciklama.getAttribute("innerText");
        System.out.println(etiketAciklama);
        return this;
    }

    @Step("Icerik Etiket popup Kapatma")
    public PostalanacakEvraklarPage btnEtiketpopupkapat() {
        btnEtiketpopupkapat.click();
        return this;
    }

    @Step("Icerik Evrak Postalama butonu")
    public PostalanacakEvraklarPage btnIcerikEvrakPostalama() {
        btnIcerikPostalama.click();
        return this;
    }

    @Step("Icerik Evrak Postalama Evet butonu")
    public PostalanacakEvraklarPage btnIcerikPostalamaEvet() {
        btnIcerikPostalamaEvet.click();
        return this;
    }

    @Step("Secilen konu başlığı filtrele ve seçilen posta içerik göster")
    public PostalanacakEvraklarPage btnFiltrenenPostaIcerikGoster(String Konu) throws InterruptedException {
        filter().findRowsWith(Condition.text(Konu)).first().click();
        String idAtr;
        idAtr = filter().findRowsWith(Condition.text(Konu)).first().getAttribute("data-ri");
        System.out.println(idAtr);
        String IcerikId = "mainInboxForm:inboxDataTable:" + idAtr + ":detayGosterButton";
        SelenideElement filteredIcerikGoster = $(By.id(IcerikId));
        filteredIcerikGoster.click();
        Thread.sleep(1000);
        return this;
    }

    BelgenetElement txtKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection tblTakipListesi = $$("tbody[id='evrakTakibimeEkleDialogForm:takipListLov:LovSecilenTable_data'] > tr[role='row']");
    SelenideElement btnTakipListesiKapat = $("div[id='evrakTakibimeEkleDialogForm:takipDialog'] span[class*='ui-icon-closethick']");

    @Step("{konu} konulu evrak üzerinde Takip Listesi butonuna tıkla.")
    public PostalanacakEvraklarPage takipListesiAc(String konu) {
        tblEvraklar
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class,'ui-button-icon-left ui-icon document-addFollow')]/..")
                .click();
        return this;
    }

    @Step("Takip Listesinde {adiSoyadi} kullanıcısının ve {birim} birim bilgisinin olduğu görülür.")
    public PostalanacakEvraklarPage takipListesiKontrol(String adiSoyadi, String birim) {
        tblTakipListesi.filterBy(text(adiSoyadi)).filterBy(text(birim)).first().shouldBe(visible);
        return this;
    }

    @Step("Takip Listesi ekranında bulunan (X) \"Sayfayı Kapatma\" butonuna basılır. Takip listesi ekranın kapatıldığı görülür.")
    public PostalanacakEvraklarPage takipListesiKapat() {
        btnTakipListesiKapat.click();
        txtKullaniciListesi.shouldNotBe(visible);
        return this;
    }
}
