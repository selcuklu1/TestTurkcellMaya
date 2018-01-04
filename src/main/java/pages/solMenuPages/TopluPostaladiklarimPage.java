package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

