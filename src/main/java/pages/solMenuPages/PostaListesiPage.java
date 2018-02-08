package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import java.awt.*;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Posta Listesi" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class PostaListesiPage extends MainPage {

    public PDFKontrol pdfKontrol = new PDFKontrol();

    SelenideElement filterPanelHeader = $("div[id='mainInboxForm:inboxDataTable:filtersAccordion'] > h3");
    SelenideElement txtPostaListesi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_input"));
    SelenideElement btnPostala = $(By.id("mainInboxForm:inboxDataTable:j_idt726"));
    SelenideElement tblIlkRow = $(By.xpath("//tbody[@id='mainInboxForm:inboxDataTable_data']/tr[@data-ri='0']"));
    BelgenetElement cmbGidisSekli = comboBox(By.id("mainPreviewForm:postaListesiPostaTipi_label"));
    BelgenetElement cmbGonderildigiTuzelKisi = comboLov(By.id("mainPreviewForm:tpbeGonderildigiTuzelKisiLovId:LovSecilen"));
    BelgenetElement cmbGonderildigiKurum = comboLov(By.id("mainPreviewForm:tpbeGonderildigiKurumLovId:LovSecilen"));

    BelgenetElement cmbGonderildigiYer = comboBox(By.id("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement txtGramaj = $(By.xpath("//*[@id='mainPreviewForm:eastLayout']//label[normalize-space(text())='Gramaj :']/../..//input"));//$(By.id("mainPreviewForm:j_idt2574"));
    SelenideElement btnHesapla = $x("//span[. = 'Tutar Hesapla']/..");
    SelenideElement btnPostaDetayiPostola = $(By.id("mainPreviewForm:postalaButton"));
    SelenideElement lblPostaListesiAdi = $(By.xpath("//label[contains(text(), 'Posta Listesi Adı :')]"));
    SelenideElement lblBarkodNo = $(By.xpath("//label[contains(text(), 'Barkod No : ')]"));
    SelenideElement lblGonderildigiYer = $(By.xpath("//label[contains(text(), 'Gönderildiği Yer : ')]"));
    SelenideElement lblGonderildigiKurum = $(By.xpath("//label[contains(text(), 'Gönderildiği Kurum : ')]"));
    SelenideElement lblAdres = $(By.xpath("//label[contains(text(), 'Adres : ')]"));
    SelenideElement lblGidisSekli = $(By.xpath("//label[contains(text(), 'Gidiş Şekli :')]"));
    SelenideElement lblGonderildigiYer2 = $(By.xpath("//label[contains(text(), 'Gönderildiği Yer : ')]"));
    SelenideElement lblTutar = $(By.xpath("//label[contains(text(), 'Tutar\t : ')]"));
    SelenideElement lblGramaj = $(By.xpath("//label[contains(text(), 'Gramaj :')]"));
    SelenideElement popUpEvrakDetayi = $(By.xpath("//span[text()='Evrak Detayları']"));
    //SelenideElement txtTutar = $(By.id("mainPreviewForm:j_idt2585"));
    BelgenetElement txtGonderildigiKurum = comboLov("mainPreviewForm:tpbeGonderildigiGercekKisiLovId:LovSecilen");

    // Hüseyin

    SelenideElement divFiltrePanelBaslik = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion"));
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPostaListesiDropDown = $("span[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi'] > button");

    ElementsCollection listPostaListesi = $$("div[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel'] > ul > li");

    SelenideElement txtPostaListesiAdi = $x("//label[normalize-space(text())='Posta Listesi Adı :']/../following-sibling::td//textarea");

    SelenideElement txtBarkodNo = $x("//label[normalize-space(text())='Barkod No :']/../following-sibling::td//inpıt");
    SelenideElement btnEtiketBastir = $x("//span[text() = 'Etiket Bastır']/../../button");
    ElementsCollection tblEvrakDetayi = $$("[id='mainPreviewForm:dtEvrakUstVeri_data'] tr[data-ri]");
    SelenideElement divGonderildigiKurm = $("div[id='mainPreviewForm:tpbeGonderildigiKurumLovId:LovSecilen'] div[id^='mainPreviewForm:tpbeGonderildigiKurumLovId']");
    SelenideElement lblGonderildigiYerCombo = $("label[id='mainPreviewForm:tpbeGidecegiYerSelectOneMenuId_label']");
    SelenideElement txtAdres = $(By.id("mainPreviewForm:gidecegiAdresId"));
    SelenideElement lblGidisSekliCmb = $(By.id("mainPreviewForm:postaListesiPostaTipi_label"));
    SelenideElement lblGonderildigiYerCmb = $(By.id("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement lblIndirimOncesiTutar = $x("//table[@id='mainPreviewForm:indirimOncesiTutarId']//label[contains(., 'TL')]");
    SelenideElement txtTutar = $x("//label[normalize-space(text())='Tutar :']/../following-sibling::td//input");
    SelenideElement txtIndirimOrani = $x("//label[normalize-space(text())='İndirim Oranı :']/../following-sibling::td//input");
    SelenideElement txtEtiketBastir = $(By.id("mainPreviewForm:etiketMetinID"));
    SelenideElement txtIndirimOrani2 = $("[id='mainPreviewForm:indirimOraniId'] td:nth-child(2) input");
    ElementsCollection tableEvrakListesi = $$("tbody[id='mainPreviewForm:dataTableId_data'] > tr[role='row']");

    @Step("Posta Listesi Sayfasını aç")
    public PostaListesiPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.PostaListesi);
        $x("//label[normalize-space(text()) = 'Posta Listesi']").waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Filtrele alanını aç")
    public PostaListesiPage filtreleAc() {
        $("div[id='mainInboxForm:inboxDataTable:filtersAccordion']").click();
        return this;
    }

    @Step("Posta Listesi doldur : \"{postaListesi}\" ")
    public PostaListesiPage postaListesiDoldur(String postaListesi) {
        txtPostaListesi.setValue(postaListesi);
        Selenide.sleep(2000);
        txtPostaListesi.pressEnter();
        return this;
    }

    @Step("Posta Listesi doldur : \"{postaListesi}\" ")
    public String postaListesiIlkKayitAl() {
        SelenideElement panelAc = $(By.xpath("//span[@class='ui-button-icon-primary ui-icon ui-icon-triangle-1-s']/.."));
        panelAc.click();
        SelenideElement panel = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel'] li:nth-child(1)");
//        BelgenetElement txtPostaListesi = comboBox(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_input"));
        String postaListesi = panel.text();

        return postaListesi;
    }

    @Step("Posta Listesi inbox kontrolü")
    public PostaListesiPage postaListesiInboxKontrolu() {
        boolean statu = false;
        boolean x = txtPostaListesi.getValue().isEmpty();
        Assert.assertEquals(x, statu);
        Allure.addAttachment("Inbox kontrolü : ","Liste seçimi öncesi inbox boş olduğu görülür.");
        return this;
    }

    @Step("Posta Listesi kontrolü : \"{postaListesi}\", \"{shouldBeExist}\" ")
    public PostaListesiPage postaListesiKontrol(String postaListesi, boolean shouldBeExist) {
        btnPostaListesiDropDown.click();
        txtPostaListesi.setValue(postaListesi);
        Selenide.sleep(3000);
        if (shouldBeExist == true) {
            $("div[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel'] > ul").shouldBe(Condition.visible);
            //listPostaListesi.filterBy(Condition.exactText(postaListesi)).first().shouldBe(Condition.visible);
        } else {
            $("div[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel'] > ul").shouldNotBe(Condition.visible);

            //listPostaListesi.filterBy(Condition.exactText(postaListesi)).first().shouldNotBe(Condition.visible);
        }
        return this;
    }

    @Step("Tablodan ilk row seç")
    public PostaListesiPage tablodanIlkRowSec() {
        tblIlkRow.click();
        return this;
    }

    @Step("Posta Listesi Postala tıkla")
    public PostaListesiPage postaListesiPostala() {
        btnPostala.click();
        return this;
    }

    ElementsCollection listGidisSekli = $$("div[id='mainPreviewForm:postaListesiPostaTipi_panel'] > ul > li");

    @Step("Gidis Sekli \"{gidisSekli}\" seç")
    public PostaListesiPage gidisSekliSec(String gidisSekli) {


        lblGidisSekliCmb.click();

        SelenideElement currentItem = listGidisSekli
                .filterBy(Condition.exactText(gidisSekli))
                .first();

        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", currentItem);

        currentItem.click();



        //cmbGidisSekli.selectComboBox(gidisSekli, true);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public PostaListesiPage gonderildigiYerSec(String gonderildigiYer) {
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public PostaListesiPage gonderildigiTuzelKisiKontrolu(String gonderildigiYer, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            cmbGonderildigiTuzelKisi.getSelectedTitles().first().shouldBe(Condition.text(gonderildigiYer));
        else
            cmbGonderildigiTuzelKisi.getSelectedTitles().first().shouldNotHave(Condition.text(gonderildigiYer));
        return this;
    }


    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public PostaListesiPage gonderildigiKurumKontrolu(String gonderildigiYer, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            cmbGonderildigiKurum.getSelectedTitles().first().shouldBe(Condition.text(gonderildigiYer));
        else
            cmbGonderildigiKurum.getSelectedTitles().first().shouldNotHave(Condition.text(gonderildigiYer));
        return this;
    }

    @Step("Gonderildiği Kurum alanının geldiği görülür.")
    public PostaListesiPage gonderildigiKurumAlanKontrolu() {
            cmbGonderildigiKurum.shouldBe(Condition.visible);
        return this;
    }

    @Step("Gramaj alanını doldur : \"{gramaj}\" ")
    public PostaListesiPage gramajDoldur(String gramaj) {
        setValueJS(txtGramaj, gramaj);
        return this;
    }
    @Step("Gramaj alanını doldur : \"{gramaj}\",\"{shouldBe}\" ")
    public PostaListesiPage gramajDoldur(String gramaj,boolean shouldBe) {
        if(shouldBe)
            setValueJS(txtGramaj, gramaj);
        else
            txtGramaj.sendKeys(gramaj);

        return this;
    }

    @Step("Gramaj alanı numerik kontrolü ")
    public PostaListesiPage gramajNumerikKontrol() {
        Assert.assertEquals(StringUtils.isNumeric(txtGramaj.getValue()), true);
        return this;
    }

    @Step("Tutar Hesapla butonuna tıkla.")
    public PostaListesiPage tutarHesapla() {
        clickJs(btnHesapla);
        return this;
    }

    @Step("Indirim oranı alanını doldur : \"{indirimOrani}\" ")
    public PostaListesiPage indirimOraniDoldur(String indirimOrani) {
        txtIndirimOrani.clear();
        txtIndirimOrani.setValue(indirimOrani);
        return this;
    }

    @Step("Indirim oranı alanını doldur : \"{indirimOrani}\" ")
    public PostaListesiPage indirimOraniDoldur2(String indirimOrani) {
        txtIndirimOrani2.setValue(indirimOrani);
        txtIndirimOrani2.pressTab();
        return this;
    }

    @Step("Posta Detayı Postala tıkla")
    public PostaListesiPage postaDetayiPostala() {
        btnPostaDetayiPostola.click();
        return this;
    }

    @Step("Ekrandaki alanların kontrolü")
    public PostaListesiPage alanKontrolu() {
        lblPostaListesiAdi.isDisplayed();
        lblBarkodNo.isDisplayed();
        lblGonderildigiYer.isDisplayed();
        lblGonderildigiKurum.isDisplayed();
        lblAdres.isDisplayed();
        lblGidisSekli.isDisplayed();
        lblGonderildigiYer2.isDisplayed();
        lblTutar.isDisplayed();
        lblGramaj.isDisplayed();

        Allure.addAttachment("Ekran Kontrolü", "Posta listesi adı \n" +
                "Barkod no\n" +
                "Gönderildiği yer\n" +
                "Gönderildiği kurum\n" +
                "Adres\n" +
                "Gidiş şekli\n" +
                "Gönderildiği yer\n" +
                "Gramaj\n" +
                "Tutar alanlarının geldiği görülür.");

//        Allure.addAttachment("label", lblPostaListesiAdi.text());
//        Allure.addAttachment("label", lblBarkodNo.text());
//        Allure.addAttachment("label", lblGonderildigiYer.text());
//        Allure.addAttachment("label", lblGonderildigiKurum.text());
//        Allure.addAttachment("label", lblAdres.text());
//        Allure.addAttachment("label", lblGidisSekli.text());
//        Allure.addAttachment("label", lblGonderildigiYer2.text());
//        Allure.addAttachment("label", lblGramaj.text());
//        Allure.addAttachment("label", lblTutar.text());
        return this;
    }

    @Step("Posta Listesi Adı alanı kontrolü. \"{postaListesiAdi}\" ")
    public PostaListesiPage postaListesiAdiKontrolu(String postaListesiAdi) {
        SelenideElement txtPostaListesiAdi = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) textarea");
        txtPostaListesiAdi.shouldBe(Condition.text(postaListesiAdi));
        return this;
    }

    @Step("Barkod No girilir. \"{barkodNo}\" ")
    public PostaListesiPage postaListesiBarkodNoDoldur(String barkodNo) {
        SelenideElement txtBarkoNo = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) input");
        txtBarkoNo.clear();
        txtBarkoNo.sendKeys(barkodNo);
        return this;
    }


    @Step("Posta Listesi Adı alanı değiştirilir. \"{postaListesiAdi}\" ")
    public PostaListesiPage postaListesiAdiDegistirme(String postaListesiAdi) {
        SelenideElement txtPostaListesiAdi = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) textarea");
        txtPostaListesiAdi.clear();
        txtPostaListesiAdi.sendKeys(postaListesiAdi);
        return this;
    }

    @Step("Posta Listesi Adı alanı kontrolü. \"{postaListesiAdi}\" ")
    public PostaListesiPage postaDetayiGonderildigiYer(String gonderildigiYer) {
        SelenideElement cmbGonderildigiYer = $(By.id("mainPreviewForm:tpbeGidecegiYerSelectOneMenuId_input"));
        cmbGonderildigiYer.getSelectedText().equals(gonderildigiYer);
        return this;
    }

    @Step("Tutar alanı doldur : \"{tutar}\" ")
    public PostaListesiPage tutarDoldur(String tutar) {
        txtTutar.clear();
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Evrak seç.")
    public PostaListesiPage evrakSec(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi) {

        tableEvraklar
                .filterBy(Condition.text(kayitTarihiSayi))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                .filterBy(Condition.text("Posta Tipi: " + postTipi))
                .first()
                .click();

        return this;
    }

    @Step("Evrak seç.")
    public PostaListesiPage evrakSec(int index) {

        tableEvraklar
                .get(index)
                .click();

        return this;
    }

    @Step("Konuye göre evrak seç. \"{konu}\" ")
    public PostaListesiPage evrakSec(String konu) {

       SelenideElement tablo =  $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[data-ri]")
                .filterBy(Condition.text(konu))
                .first();
       tablo.click();
        return this;
    }


    @Step("Evrak önizleme kontrolü")
    public PostaListesiPage evrakOnizlemeKontrolu() {
        Assert.assertEquals($(By.xpath("//div[text()='Evrak Önizleme']")).is(Condition.visible),true);
        return this;
    }

    @Step("Evrak kontrolü")
    public PostaListesiPage evrakKontrol(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi, boolean shouldBeExist) {

        if (shouldBeExist == true) {

            tableEvraklar
                    .filterBy(Condition.text(kayitTarihiSayi))
                    .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                    .filterBy(Condition.text("Posta Tipi: " + postTipi))
                    .first()
                    .shouldBe(Condition.exist)
                    .shouldBe(Condition.visible);

        } else {

            tableEvraklar
                    .filterBy(Condition.text(kayitTarihiSayi))
                    .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                    .filterBy(Condition.text("Posta Tipi: " + postTipi))
                    .first()
                    .shouldNotBe(Condition.exist)
                    .shouldNotBe(Condition.visible);

        }
        return this;
    }

    @Step("Evrak Listesi kontrolü")
    public PostaListesiPage evrakListesiKontrol(String gonderilenYer, String evrakSayisiEvrakKonusu) {

        tableEvrakListesi
                .filterBy(Condition.text(gonderilenYer))
                .filterBy(Condition.text(evrakSayisiEvrakKonusu))
                .first()
                .shouldBe(Condition.exist)
                .shouldBe(Condition.visible);

        return this;
    }

    @Step("Posta listesinden çıkart.")
    public PostaListesiPage postaListesindenCikart(String kayitTarihiSayi, String gidecegiYer, String konu, String hazirlayanBirim, String postTipi) {
        //

        tableEvraklar
                .filterBy(Condition.text(kayitTarihiSayi))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Hazırlayan Birim: " + hazirlayanBirim))
                .filterBy(Condition.text("Posta Tipi: " + postTipi))
                .first()
                .$("button[id$='postaListesindenCikarButton']")
                .click();


        return this;
    }

    @Step("Posta listesinden çıkart. \"{konu}\" ")
    public PostaListesiPage konuyaGorePostaListesindenCikart(String konu) {
        //

        tableEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .$("button[id$='postaListesindenCikarButton']")
                .click();


        return this;
    }

    @Step("Posta listesinden çıkart.")
    public PostaListesiPage postaListesindenCikart(int index) {
        tableEvraklar
                .get(0)
                .click();
        return this;
    }

    @Step("Posta listesi adında \"{postaListesiAdi}\" değeri olmali mi? : \"{shouldBeEquals}\" ")
    public PostaListesiPage postaListesiAdiKontrol(String postaListesiAdi, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtPostaListesiAdi.shouldHave(Condition.value(postaListesiAdi));
        else
            txtPostaListesiAdi.shouldNotHave(Condition.value(postaListesiAdi));
        return this;
    }


    @Step("Gönderildiğü kurum alanında \"{kurum}\" değeri olmali mi? : \"{shouldBeEquals}\" ")
    public PostaListesiPage gonderildigiKurumKontro(String kurum, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            divGonderildigiKurm.shouldHave(Condition.text(kurum));
        else
            divGonderildigiKurm.shouldNotHave(Condition.text(kurum));
        return this;
    }


    @Step("Gönderildiği yer combosunda \"{gonderildigiYer}\" seçili olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage gonderildigiYerKontrol(String gonderildigiYer, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblGonderildigiYerCombo.shouldHave(Condition.text(gonderildigiYer));
        else
            lblGonderildigiYerCombo.shouldNotHave(Condition.text(gonderildigiYer));
        return this;
    }


    @Step("Adres alanında \"{adres}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage adresKontrol(String adres, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtAdres.shouldHave(Condition.text(adres));
        else
            txtAdres.shouldNotHave(Condition.text(adres));
        return this;
    }

    @Step("Adres alanında \"{adres}\" girilir.")
    public PostaListesiPage adresDoldur(String adres) {
        txtAdres.clear();
        txtAdres.sendKeys(adres);
        return this;
    }

    @Step("Gidiş şekli combosunda \"{gidisSekli}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage gidisSekliKontrol(String gidisSekli, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblGidisSekliCmb.shouldHave(Condition.text(gidisSekli));
        else
            lblGidisSekliCmb.shouldNotHave(Condition.text(gidisSekli));
        return this;
    }


    @Step("Gönderildiği yer combosunda \"{gonderildigiYer\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage yurticiYurtdisiKontrol(String gonderildigiYer, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblGonderildigiYerCmb.shouldHave(Condition.text(gonderildigiYer));
        else
            lblGonderildigiYerCmb.shouldNotHave(Condition.text(gonderildigiYer));
        return this;
    }


    @Step("İndirim Öncesi tutar alaninda \"{indirimOncesiTutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage indirimOncesiTutarKontrol(String indirimOncesiTutar) {
        if(lblIndirimOncesiTutar.getText().contains(indirimOncesiTutar))
            Assert.assertEquals(true, true);
        else
            Assert.assertEquals(true, false);
        return this;
    }

    @Step("{0}")
    public PostaListesiPage indirimOrani(){

        return this;
    }

    @Step("Gramaj alaninda \"{gramaj}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage gramajKontrol(String gramaj, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtGramaj.shouldHave(Condition.value(gramaj));
        else
            txtGramaj.shouldNotHave(Condition.value(gramaj));
        return this;
    }


    @Step("İndirim sonrası tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage tutarKontrol(String tutar) {

        if(txtTutar.getValue().contains(tutar))
            Assert.assertEquals(true, true);
        else
            Assert.assertEquals(true, false);

        return this;
    }


    public String tutarAl() {
        String tutar = txtTutar.getValue();
        return tutar;
    }

    @Step("İndirim Oranı alaninda \"{indirimOrani}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage indirimOraniKontrol(String indirimOrani, boolean shouldBeEquals) {
        txtIndirimOrani.text();
        if (shouldBeEquals == true)
            txtIndirimOrani.shouldHave(Condition.value(indirimOrani));
        else
            txtIndirimOrani.shouldNotHave(Condition.value(indirimOrani));
        return this;
    }

    @Step("Etiket bastır butonuna tıkla.")
    public PostaListesiPage etiketBastir() {
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", btnEtiketBastir);
        btnEtiketBastir.pressEnter();
        txtEtiketBastir.waitUntil(Condition.visible, 5000);
        Allure.addDescription("Etiket Bastır ekranı kontrolü.");
        return this;
    }

    @Step("Etiket bastır ekranında Gideceği Yer ve Adres kontrolü")
    public PostaListesiPage etiketBastirEkraniKontrolü(String adres, String konu) {
        Assert.assertEquals(txtEtiketBastir.text().contains(konu),true);
        Assert.assertEquals(txtEtiketBastir.text().contains(adres),true);
        return this;
    }

    @Step("Etiket bastır ekranı kapama")
    public PostaListesiPage etiketBastirEkraniKapat() {
        SelenideElement btnEtiketBastırEkraniKapat = $(By.xpath("//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']//a/span[@class='ui-icon ui-icon-closethick']"));
        btnEtiketBastırEkraniKapat.click();
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public PostaListesiPage evrakListesiYazdir(String[] konu) {
        int size = tableEvrakListesi.size();
//        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", tableEvrakListesi);
        for (int i = size - 1; i >= 0; i--) {
            tableEvrakListesi
                    .filterBy(Condition.text(konu[i]))
                    .first()
                    .$x("descendant::span[text() = 'Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);
            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();

//            pdfKontrol
//                    .geregiBilgiAlaniAdresPdfKontrol(konu[0]);

        }
        return this;
    }

    @Step("Evrak Detayı popup kontrolü")
    public PostaListesiPage evrakDetayiPopUpKontrolü() {
        popUpEvrakDetayi.shouldBe(Condition.visible);
        return this;
    }

    @Step("Evrak Detayı Yazdır butonu")
    public PostaListesiPage evrakDetayiYazdır(String konu) {
        tblEvrakDetayi.filterBy(Condition.text(konu))
                .first()
                .$("[id$='evrakDetayiViewDialogYazdir']").click();
        return this;
    }

    @Step("Evrak Detayı Yazdır butonu")
    public PostaListesiPage evrakDetayiOrjinaliYazdır(String konu) {

        tblEvrakDetayi.filterBy(Condition.text(konu))
                .first()
                .$("[id$='evrakDetayiViewDialogOrjYazdir']").click();
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public PostaListesiPage evrakListesiYazdirPdfKontrolu(String[] konu, String[] evrakNo, String[] icerik) throws AWTException, IOException {
        String remoteDownloadPath = getDownloadPath();
        int size = tableEvrakListesi.size();
        size = size - 1;
        String pdfName = "";
//        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", tableEvrakListesi);
        for (int i = size; i >= 0; i--) {

//            SearchTable searchTable =  TopluPostaladiklarimPage.searchTable();
//            searchTable.findRows(Condition.text(konu[i]))
//                    .getFoundRow()
//                    .$x("descendant::button[descendant::span[. = 'Yazdır']]").click();

            tableEvrakListesi
                    .filterBy(Condition.text(konu[i]))
                    .first()
                    .$x("descendant::button[descendant::span[. = 'Yazdır']]").pressEnter();

            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);

//            pdfName = pdfIndir();
            switchTo().window(1);
            String pdfPath = remoteDownloadPath + pdfName;
            sleep(3000);
            pdfKontrol
                    .PDFAlanKontrolleriFF(konu[i], evrakNo[i], icerik[i]);
//                    .PDFAlanKontrolleri(pdfPath, konu[i], evrakNo[i], icerik[i]);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("Evrak Listesi tablosunda Orjinalini Yazdır butonu tıklanır.")
    public PostaListesiPage evrakListesiOrjinaliYazdirPdfKontrolu(String[] konu, String[] evrakNo, String[] icerik) throws AWTException, IOException {
        String remoteDownloadPath = getDownloadPath();
//        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", tableEvrakListesi);
        int size = tableEvrakListesi.size();
        size = size - 1;
        for (int i = size; i >= 0; i--) {
//            Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", tableEvrakListesi);
//            SearchTable searchTable =  TopluPostaladiklarimPage.searchTable();
//            searchTable.findRows(Condition.text(konu[i]))
//                    .getFoundRow()
//                    .$x("descendant::button[descendant::span[. = 'Orjinalini Yazdır']]").pressEnter();


            tableEvrakListesi
                    .filterBy(Condition.text(konu[i]))
                    .first()
                    .$x("descendant::button[descendant::span[. = 'Orjinalini Yazdır']]").pressEnter();

            evrakDetayiPopUpKontrolü();
            evrakDetayiOrjinaliYazdır(konu[i]);
            switchTo().window(1);
            sleep(3000);
            pdfKontrol
                    .PDFAlanKontrolleriFF(konu[i], evrakNo[i], icerik[i]);
//                    .PDFAlanKontrolleri(pdfPath, konu[i], evrakNo[i], icerik[i]);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }


    public class PDFKontrol extends MainPage {

        @Step("PDF'teki alanların kontrolü")
        public PDFKontrol PDFAlanKontrolleriFF(String konu, String evrakNo, String icerik) throws IOException {

            SelenideElement konuAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + konu + "']"));
//            SelenideElement konuAlaniPDF = $("div[class='firefinder-match']");
//            SelenideElement evrakNoAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + evrakNo + "']"));
//            SelenideElement evrakNoAlaniPDF = $(".textLayer > div:nth-child(5)");
            SelenideElement evrakNoAlaniPDF = $x("//div[contains(.,'" + evrakNo + "')]");
            SelenideElement icerikAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + icerik + "']"));
            SelenideElement altAntetAdresAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='Ankara Üniversitesi Ankütek Teknopark E Blok Kat:1']"));
            SelenideElement altAntetTelefonAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='Tel: 0312 222 22 22']"));
            SelenideElement altAntetWebSitesiAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='Web: www.turksat.com.tr']"));

            String evraNoPDF = evrakNoAlaniPDF.getText();
//            String evraNoPDF = evrakNoAlaniPDF.getText();

            System.out.println("Beklenen Sayı : " + evrakNo);
            System.out.println("Gelen Sayı : " + evrakNoAlaniPDF.getText());
            System.out.println("Beklenen Konu : " + konu);
            System.out.println("Gelen Konu : " + konuAlaniPDF.getText());
            System.out.println("Beklenen İcerik : " + icerik);
            System.out.println("Gelen İcerik : " + icerikAlaniPDF.getText());
            System.out.println("Beklenen Alt Antet Adres : " + "Ankara Üniversitesi Ankütek Teknopark E Blok Kat:1");
            System.out.println("Gelen Alt Antet Adres : " + altAntetAdresAlaniPDF.getText());
            System.out.println("Beklenen Alt Antet Telefon : " + "Tel: 0312 222 22 22");
            System.out.println("Gelen Alt Antet Telefon : " + altAntetTelefonAlaniPDF.getText());
            System.out.println("Beklenen Alt Antet Web Sitesi : " + "Web: www.turksat.com.tr");
            System.out.println("Gelen Alt Antet Web Sitesi : " + altAntetWebSitesiAlaniPDF.getText());

            Assert.assertEquals(evrakNoAlaniPDF.getText().contains(evrakNo), true);
            Assert.assertEquals(konuAlaniPDF.getText(), konu);
            Assert.assertEquals(icerikAlaniPDF.getText(), icerik);
            Assert.assertEquals(altAntetAdresAlaniPDF.getText(), "Ankara Üniversitesi Ankütek Teknopark E Blok Kat:1");
            Assert.assertEquals(altAntetTelefonAlaniPDF.getText(), "Tel: 0312 222 22 22");
            Assert.assertEquals(altAntetWebSitesiAlaniPDF.getText(), "Web: www.turksat.com.tr");

            Allure.addAttachment("PDF Kontrolü konu : ", konuAlaniPDF.getText());
            Allure.addAttachment("PDF Kontrolü evrakNo : ", evrakNo);
            Allure.addAttachment("PDF Kontrolü içerik : ", icerikAlaniPDF.getText());
            Allure.addAttachment("PDF Kontrolü Altantet : ", altAntetAdresAlaniPDF.getText() + altAntetTelefonAlaniPDF.getText() + altAntetWebSitesiAlaniPDF.getText());

            takeScreenshot();

            return this;
        }
    }
}

