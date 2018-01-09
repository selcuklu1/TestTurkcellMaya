package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;

public class TopluPostaladiklarimPage extends MainPage {

    SelenideElement filtrePanelHeader = $("div[id='mainInboxForm:inboxDataTable:filtersAccordion'] > h3");
    SelenideElement txtPostaListesiAdi = $("//label[normalize-space(text())='Posta Listesi Adı :']/ancestor::tr[@class='ui-widget-content']//input");
    SelenideElement txtBarkodNo = $("//label[normalize-space(text())='Barkod No :']/ancestor::tr[@class='ui-widget-content']//input");
    SelenideElement txtEvrakSayisi = $("//label[normalize-space(text())='Evrak Sayısı :']/ancestor::tr[@class='ui-widget-content']//input");
    SelenideElement txtPostaTarihi = $("//label[normalize-space(text())='Posta Tarihi :']/ancestor::tr[@class='ui-widget-content']//input");
    ElementsCollection tblPostaladiklarim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnFiltrele = $("mainInboxForm:inboxDataTable:filtersAccordion:topluPostaladiklarimFiltreButton");
    SelenideElement btnTemizle = $("mainInboxForm:inboxDataTable:filtersAccordion:topluPostaladiklarimFiltreButton");
    ElementsCollection tblEvrakListesi = $$("tbody[id='mainPreviewForm:dataTableId_data'] > tr[role='row']");
    ElementsCollection tblPostaListesi = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    SelenideElement popUpEvrakDetayi = $(By.xpath("//span[text()='Evrak Detayları']"));
    ElementsCollection tblEvrakDetayi = $$("[id='mainPreviewForm:dtEvrakUstVeri_data'] tr[role='row']");
    SelenideElement txtTutar = $(By.xpath("//label[normalize-space(text())='Tutar :']/../following-sibling::td//input"));
    SelenideElement btnGuncelle = $(By.id("mainPreviewForm:guncelleButton"));
    BelgenetElement cmbGonderildigiYer = comboBox(By.id("mainPreviewForm:tpbeGidecegiYerSelectOneMenuId_label"));
    BelgenetElement cmbGonderildigiYer2 = comboBox(By.id("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement txtAdres = $(By.id("mainPreviewForm:gidecegiAdresId"));
    BelgenetElement cmbGidisSekli = comboBox(By.id("mainPreviewForm:postaListesiPostaTipi_label"));
    SelenideElement txtIndirimOrani = $x("//label[normalize-space(text())='İndirim Oranı :']/../following-sibling::td//input");
    SelenideElement txtGramaj = $(By.xpath("//*[@id='mainPreviewForm:eastLayout']//label[normalize-space(text())='Gramaj :']/../..//input"));
    SelenideElement btnHesapla = $x("//span[. = 'Tutar Hesapla']/..");
    SelenideElement lblIndirimOncesiTutar = $x("//table[@id='mainPreviewForm:indirimOncesiTutarId']//label[contains(., 'TL')]");

    @Step("Toplu Postaladıklarım sayfasını aç")
    public TopluPostaladiklarimPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TopluPostaladiklarim);
        return this;
    }

    @Step("Filtre Panel aç")
    public TopluPostaladiklarimPage filtrePaneliAc() {
        filtrePanelHeader.click();
        return this;
    }

    @Step("Posta listesi adı doldur: {postaListesiAdi}")
    public TopluPostaladiklarimPage postaListesiAdiDoldur(String postaListesiAdi) {
        txtPostaListesiAdi.setValue(postaListesiAdi);
        return this;
    }

    @Step("Barkod No doldur: {barkodNo}")
    public TopluPostaladiklarimPage barkodNoDoldur(String barkodNo) {
        txtBarkodNo.setValue(barkodNo);
        return this;
    }

    @Step("Evrak Sayısı doldur: {evrakSayisi}")
    public TopluPostaladiklarimPage evrakSayisiDoldur(String evrakSayisi) {
        txtEvrakSayisi.setValue(evrakSayisi);
        return this;
    }

    @Step("Posta Tarihi doldur: {postaTarihi}")
    public TopluPostaladiklarimPage postaTarihiDoldur(String postaTarihi) {
        txtPostaTarihi.setValue(postaTarihi);
        return this;
    }

    @Step("Filtrele butonuna tıkla.")
    public TopluPostaladiklarimPage filtrele() {
        btnFiltrele.click();
        return this;
    }

    @Step("Temizle butonuna tıkla")
    public TopluPostaladiklarimPage temizle() {
        btnTemizle.click();
        return this;
    }

    @Step("Posta Listesi seç")
    public TopluPostaladiklarimPage postaListesiSec(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari) {


        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblPostaladiklarim
                    .filterBy(text("Posta Listesi Adı: " + postaListesiAdi))
                    .filterBy(text("Posta Kodu: " + postaKodu))
                    .filterBy(text("Posta Tarihi: " + postaTarihi))
                    .filterBy(text("Posta Gramajı: " + postaGramaji))
                    .filterBy(text("PTT Tutarı: " + pttTutari + " TL"))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                postaListesi.click();
                break;
            }


        }
        return this;
    }

    @Step("Posta Listesi seç")
    public TopluPostaladiklarimPage postaListesiKontrol(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari, boolean shouldBeExist) {


        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        boolean elementFound = false;

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblPostaladiklarim
                    .filterBy(text("Posta Listesi Adı: " + postaListesiAdi))
                    .filterBy(text("Posta Kodu: " + postaKodu))
                    .filterBy(text("Posta Tarihi: " + postaTarihi))
                    .filterBy(text("Posta Gramajı: " + postaGramaji))
                    .filterBy(text("PTT Tutarı: " + pttTutari + " TL"))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                elementFound = true;
            }

        }

        Assert.assertEquals(elementFound, shouldBeExist);

        return this;
    }

    @Step("Toplu Postaladıklarım Posta Listesi Kontrolü")
    public TopluPostaladiklarimPage topluPostaladiklarimPostaListesiKontrol(String postaListesiAdi, String postaKodu, String postaTarihi, String postaGramaji, String pttTutari, boolean shouldBeExist) {
        tblPostaListesi
                .filterBy(Condition.text(postaListesiAdi))
                .filterBy(Condition.text(postaKodu))
                .filterBy(Condition.text(postaTarihi))
                .filterBy(Condition.text(postaGramaji))
                .filterBy(Condition.text(pttTutari))
                .first();
        return this;
    }

    @Step("Toplu Postaladıklarım tablosundan \"{konu}\" konulu evrak seçilir")
    public TopluPostaladiklarimPage topluPostaladiklarimEvrakSec(String konu) {
        tblPostaListesi
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public TopluPostaladiklarimPage evrakListesiYazdir(String[] konu) {
        int size = tblEvrakListesi.size();
        for (int i = 0; i < size; i++) {

            tblEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);
            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("Evrak Listesi tablosunda Yazdır butonu tıklanır.")
    public TopluPostaladiklarimPage evrakListesiOrjinaliYazdir(String[] konu) {
        int size = tblEvrakListesi.size();
        for (int i = 0; i < size; i++) {
            tblEvrakListesi
                    .get(i)
                    .$x("//span[text() = 'Orjinalini Yazdır']/../../button").click();
            evrakDetayiPopUpKontrolü();
            evrakDetayiYazdır(konu[i]);
            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            $(By.xpath("//div[@id='mainPreviewForm:evrakDetayiViewDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        }
        return this;
    }

    @Step("Evrak Detayı popup kontrolü")
    public TopluPostaladiklarimPage evrakDetayiPopUpKontrolü() {
        popUpEvrakDetayi.shouldBe(Condition.visible);
        return this;
    }

    @Step("Evrak Detayı Yazdır butonu")
    public TopluPostaladiklarimPage evrakDetayiYazdır(String konu) {
        tblEvrakDetayi.filterBy(Condition.text(konu))
                .first()
                .$("[id$='evrakDetayiViewDialogYazdir']").click();
        return this;
    }

    @Step("Tutar alanına \"{tutar}\" girilir")
    public TopluPostaladiklarimPage tutarGuncelle(String tutar) {
        txtTutar.clear();
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Güncelle butonuna tıklanır")
    public TopluPostaladiklarimPage guncelle() {
        btnGuncelle.click();
        return this;
    }

    @Step("Posta Listesi Adı alanı değiştirilir. \"{postaListesiAdi}\" ")
    public TopluPostaladiklarimPage postaListesiAdiDegistirme(String postaListesiAdi) {
        SelenideElement txtPostaListesiAdi = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) textarea");
        txtPostaListesiAdi.clear();
        txtPostaListesiAdi.sendKeys(postaListesiAdi);
        return this;
    }

    @Step("Barkod No girilir. \"{barkodNo}\" ")
    public TopluPostaladiklarimPage postaListesiBarkodNoDoldur(String barkodNo) {
        SelenideElement txtBarkoNo = $("[id='mainPreviewForm:eastLayout'] table tr:nth-child(1) input");
        txtBarkoNo.clear();
        txtBarkoNo.sendKeys(barkodNo);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public TopluPostaladiklarimPage gonderildigiYerSec(String gonderildigiYer) {
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("Adres alanında \"{adres}\" girilir.")
    public TopluPostaladiklarimPage adresDoldur(String adres) {
        txtAdres.clear();
        txtAdres.sendKeys(adres);
        return this;
    }

    @Step("Gidis Sekli \"{gidisSekli}\" seç")
    public TopluPostaladiklarimPage gidisSekliSec(String gidisSekli) {
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }

    @Step("Gonderildiği Yeri \"{gonderildigiYer}\" seç")
    public TopluPostaladiklarimPage gonderildigiYerSec2(String gonderildigiYer) {
        cmbGonderildigiYer2.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("İndirim oranı girilir")
    public TopluPostaladiklarimPage indirimOraniDoldur(String indirimOrani) {
        txtIndirimOrani.setValue(indirimOrani);
        return this;
    }

    @Step("Gramaj alanını doldur : \"{gramaj}\" ")
    public TopluPostaladiklarimPage gramajDoldur(String gramaj) {
        setValueJS(txtGramaj, gramaj);
        return this;
    }

    @Step("Etiket hesapla Tıkla")
    public TopluPostaladiklarimPage tutarHesapla() {
        clickJs(btnHesapla);
        return this;
    }

    @Step("İndirim Öncesi tutar alaninda \"{indirimOncesiTutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage indirimOncesiTutarKontrol(String indirimOncesiTutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            lblIndirimOncesiTutar.shouldHave(Condition.text(indirimOncesiTutar + " TL"));
        else
            lblIndirimOncesiTutar.shouldNotHave(Condition.text(indirimOncesiTutar + " TL"));
        return this;
    }
    @Step("Tutar alaninda \"{indirimOrani}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage indirimOraniKontrol(String indirimOrani, boolean shouldBeEquals) {
        txtIndirimOrani.text();
        if (shouldBeEquals == true)
            txtIndirimOrani.shouldHave(Condition.value(indirimOrani));
        else
            txtIndirimOrani.shouldNotHave(Condition.value(indirimOrani));
        return this;
    }

    @Step("Tutar alaninda \"{tutar}\" değeri olmalı mı? : \"{shouldBeEquals}\" ")
    public TopluPostaladiklarimPage tutarKontrol(String tutar, boolean shouldBeEquals) {
        if (shouldBeEquals == true)
            txtTutar.shouldHave(Condition.value(tutar));
        else
            txtTutar.shouldNotHave(Condition.value(tutar));
        return this;
    }

    @Step("Evrak listesinden evrak sil")
    public TopluPostaladiklarimPage evrakSil(String gonderildigiYer, String evrakKonusu, String evrakSayisi) {
        tblEvrakListesi
                .filterBy(text(gonderildigiYer))
                .filterBy(text("Konu: " + evrakKonusu))
                .filterBy(text("Sayı: " + evrakSayisi))
                .first()
                .$("button[id$='silButton']")
                .click();

        return this;
    }

    @Step("Evrak Kontrol Et")
    public TopluPostaladiklarimPage evrakKontrol(String gonderildigiYer, String evrakKonusu, String evrakSayisi) {

        tblEvrakListesi
                .filterBy(text(gonderildigiYer))
                .filterBy(text(evrakKonusu))
                .filterBy(text(evrakSayisi))
                .first()
                .$("button[id$='silButton']")
                .shouldBe(visible);

        return this;
    }

    @Step("İlk posta listesi adını getir.")
    public String postaListesiAdiGetir(int index) {
        String postaListesiAdi = tblPostaladiklarim
                .get(0)
                .$$("td")
                .filterBy(text("Posta Listesi Adı:"))
                .get(0)
                .getText();

        return postaListesiAdi;
    }
}

