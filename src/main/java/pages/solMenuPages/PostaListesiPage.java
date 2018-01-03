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

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

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
    //    SelenideElement lblGonderildigiYer2 = $(By.xpath("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement lblTutar = $(By.xpath("//label[contains(text(), 'Tutar : ')]"));
    SelenideElement popUpEvrakDetayi = $(By.xpath("//span[text()='Evrak Detayları']"));
    //SelenideElement txtTutar = $(By.id("mainPreviewForm:j_idt2585"));
    BelgenetElement txtGonderildigiKurum = comboLov("mainPreviewForm:tpbeGonderildigiGercekKisiLovId:LovSecilen");

    // Hüseyin

    SelenideElement divFiltrePanelBaslik = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion"));
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPostaListesiDropDown = $("span[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi'] > button");
    ElementsCollection listPostaListesi = $$("div[id='mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel'] > ul > li");

    SelenideElement txtPostaListesiAdi = $x("//label[normalize-space(text())='Posta Listesi Adı :']/../following-sibling::td//textarea");
    SelenideElement btnEtiketBastir = $x("//span[text() = 'Etiket Bastır']/../../button");
    ElementsCollection tblEvrakDetayi = $$("[id='mainPreviewForm:dtEvrakUstVeri_data'] tr[role='row']");
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

    @Step("Posta Listesi kontrolü : \"{postaListesi}\" ")
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

    @Step("Gidis Sekli \"{gidisSekli}\" seç")
    public PostaListesiPage gidisSekliSec(String gidisSekli) {
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public PostaListesiPage gonderildigiYerSec(String gonderildigiYer) {
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("Gramaj alanını doldur : \"{gramaj}\" ")
    public PostaListesiPage gramajDoldur(String gramaj) {
        setValueJS(txtGramaj, gramaj);
        return this;
    }

    @Step("Etiket hesapla Tıkla")
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
//        lblGonderildigiYer2.isDisplayed();
        lblTutar.isDisplayed();
        return this;
    }

    @Step("Posta Listesi Adı alanı kontrolü. \"{postaListesiAdi}\" ")
    public PostaListesiPage postaListesiAdiKontrolu(String postaListesiAdi) {
        SelenideElement txtPostaListesiAdi = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) td:nth-child(2)");
        txtPostaListesiAdi.shouldBe(Condition.text(postaListesiAdi));
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

        tableEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .click();

        return this;
    }


    @Step("Evrak önizleme kontrolü")
    public PostaListesiPage evrakOnizlemeKontrolu() {
        $(By.id("mainPreviewForm:eastLayout")).shouldBe(Condition.visible);
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
            divGonderildigiKurm.shouldNotHave(Condition.text(kurum));
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
    public PostaListesiPage indirimOncesiTutarKontrol(String indirimOncesiTutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblIndirimOncesiTutar.shouldHave(Condition.text(indirimOncesiTutar + " TL"));
        else
            lblIndirimOncesiTutar.shouldNotHave(Condition.text(indirimOncesiTutar + " TL"));
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


    @Step("Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public PostaListesiPage tutarKontrol(String tutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtTutar.shouldHave(Condition.value(tutar));
        else
            txtTutar.shouldNotHave(Condition.value(tutar));
        return this;
    }

    @Step("Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
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
        btnEtiketBastir.click();
        txtEtiketBastir.waitUntil(Condition.visible, 5000);
        return this;
    }

    @Step("Etiket bastır ekranında Gideceği Yer ve Adres kontrolü")
    public PostaListesiPage etiketBastirEkraniKontrolü(String adres, String konu) {
        txtEtiketBastir.text().contains(konu);
        txtEtiketBastir.text().contains(adres);
        return this;
    }

    @Step("Etiket bastır ekranı kapama")
    public PostaListesiPage etiketBastirEkraniKapat() {
        SelenideElement btnEtiketBastırEkraniKapat = $(By.xpath("//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']//a/span[@class='ui-icon ui-icon-closethick']"));
        btnEtiketBastırEkraniKapat.click();
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public PostaListesiPage evrakListesiYazdir(String[] konu ) {
        int size = tableEvrakListesi.size();
        for (int i = 0; i < size; i++) {

            tableEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır();
            pdfKontrol
                    .geregiBilgiAlaniAdresPdfKontrol(konu[0]);

        }
        return this;
    }

    @Step("Etiket bastır ekranı kapama")
    public PostaListesiPage evrakDetayiPopUpKontrolü() {
        popUpEvrakDetayi.shouldBe(Condition.visible);
        return this;
    }
    @Step("Etiket bastır ekranı kapama")
    public PostaListesiPage evrakDetayiYazdır() {
        tblEvrakDetayi.first()
                .$("[id$='evrakDetayiViewDialogYazdir']").click();
        return this;
    }

    public class PDFKontrol extends MainPage {

        public PDFKontrol geregiBilgiAlaniAdresPdfKontrol(String konu){

            switchTo().window(1);
//            SelenideElement geregiAdresAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + sayi + "']"));
            SelenideElement bilgiAdresAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + konu + "']"));

//            System.out.println(sayi);
            System.out.println("Beklenen konu: " + konu);
            System.out.println("Gelen konu: " + bilgiAdresAlaniPDF.getText());
            return this;
        }

    }
}

