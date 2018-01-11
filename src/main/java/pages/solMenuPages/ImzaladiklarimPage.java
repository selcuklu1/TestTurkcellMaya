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
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


public class ImzaladiklarimPage extends MainPage {

    //SelenideElement tblImzaladiklarim = $(By.id("mainInboxForm:inboxDataTable_data"));
    SelenideElement tabEvrakGecmisi = $(By.xpath("//*[text()[contains(.,'Evrak Geçmişi')]]"));
    SelenideElement btnIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement tabEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");
    ElementsCollection tblImzalananEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row'] table");
    SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");
    SelenideElement btnGidecegiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    BelgenetElement txtGidecegiYer = comboLov(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));
    SelenideElement txtGidecegiYer2 = $(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));

    SelenideElement txtBaslangicTarihi = $x("//label[normalize-space(text())='Başlangıç Tarihi :']/../../following-sibling::td//input");
    SelenideElement txtBitisTarihi = $x("//label[normalize-space(text())='Bitiş Tarihi :']/../../following-sibling::td//input");
    ElementsCollection tblImzaladiklarim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnGeriAl = $x("//span[contains(@class, 'evrakGeriAl')]/..");
    SelenideElement txtGeriAlAciklama = $(By.id("mainPreviewForm:evrakGeriAlInputTextareaId"));
    SelenideElement btnGeriAlOnay = $x("//div[@class='form-buttons']//span[. = 'Geri Al']/..");

    SelenideElement filtrePanelHeader = $("div[id='mainInboxForm:inboxDataTable:filtersAccordion'] > h3");

    ElementsCollection evrakSecButonlar = $$("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td");


    @Step("Imzaladiklarim Sayfasini aç")
    public ImzaladiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim);
        String pageTitle = SolMenuData.IslemYaptiklarim.Imzaladiklarim.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Paylaş buton gelmediği görme")
    public ImzaladiklarimPage paylasButonGelmedigiGorme(String buton) {
        boolean t = evrakSecButonlar.filterBy(text(buton)).size() == 0;
        Assert.assertEquals(t, true);
        return this;
    }

    @Step("Evrak geldiği görülür")
    public ImzaladiklarimPage evrakGeldigiGorme(String toplantiNo, String konu, String toplantiTarih) {
        tableKararIzlemeEvraklar.filterBy(text(toplantiNo))
                .filterBy(text(konu)).filterBy(text(konu))
                .filterBy(text(toplantiTarih)).filterBy(Condition.visible);
        return this;
    }

    @Step("ImzaladiklarimIlkPostaSec")
    public ImzaladiklarimPage evrakSec() {
        btnIlkEvrak.click();
        return this;
    }

    @Step("Dokümanı bul ve seç")
    public ImzaladiklarimPage dokumaniSec(String text) {
        filter().findRowsWith(text(text))
                .shouldHaveSize(1)
                .first()
                .click();
        return this;
    }

    @Step("Evrak Geçmişi tab")
    public ImzaladiklarimPage evrakGecmisi() {

        tabEvrakGecmisi.shouldBe(visible).click();
        return this;

    }

    @Step("Evrak Geçmişi \"Evrak kurum içi otomatik postalandı\" tekst içermeli")
    public ImzaladiklarimPage evrakGecmisiWith(String text) {
        $("tbody[id$='hareketGecmisiDataTable_data']").shouldHave(text(text));
        return this;
    }

    @Step("")
    public String evrakIcerikKontroluveEvrakNoAl(String icerik) {
        int size = tblImzalananEvraklar.size();
        String evrakNo = "";
        boolean flag = false;

        for (int i = 0; i < size; i++) {
            $(By.id("mainInboxForm:inboxDataTable:" + i + ":detayGosterButton")).click();
            evrakNo = evrakDetayiEvrakNoAl();
            String icerikTxt = $("[id='inboxItemInfoForm:evrakBilgileriList_content'] tr:nth-child(13) tr textarea").text();
            if (icerik.equals(icerikTxt)) {
                flag = true;
                break;
            }
            $(By.xpath("//div[@id='windowItemInfoDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
            islemPenceresiKapatmaOnayiPopup("Kapat");

        }
        Assert.assertEquals(flag, true, "Evrak listelenmiştir");
        return evrakNo;
    }

    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }

    @Step("Gideceği yer seç: {gidecegiYer}")
    public ImzaladiklarimPage gidecegiYerSec(String gidecegiYer) {
        btnGidecegiYer.click();
        txtGidecegiYer2.setValue(gidecegiYer);

        SelenideElement currentList = $$("div[id='inboxFiltreDialogForm:gidecegiYerFilterLovId:gidecegiYerFilterLovIdlovDialogId']").last();
        currentList.waitUntil(Condition.visible, 5000);
        currentList
                .$$("span[class='lovItemTitle ']")
                .filterBy(text(gidecegiYer))
                .last()
                .click();

        return this;
    }

    @Step("Başlangıç Tarihi doldur: {baslangicTarihi}")
    public ImzaladiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        txtBaslangicTarihi.setValue(baslangicTarihi);
        return this;
    }

    @Step("Bitiş Tarihi doldur: {bitisTarihi}")
    public ImzaladiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        txtBitisTarihi.setValue(bitisTarihi);
        return this;
    }

    @Step("Evrak Seç")
    public ImzaladiklarimPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblImzaladiklarim
                    .filterBy(text("Konu: " + konu))
                    .filterBy(text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(text("Evrak Tarihi: " + evrakTarihi))
                    .filterBy(text(no))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                postaListesi.click();
                return this;
            }
        }
        Assert.fail("Evrak bulunamadı.");
        return this;

    }

    @Step("Geri al butonuna tıkla")
    public ImzaladiklarimPage geriAl() {
        btnGeriAl.click();
        return this;
    }

    @Step("Geri Al açıklaması doldur: {aciklama}")
    public ImzaladiklarimPage geriAlAciklamaDoldurVeOnayla(String aciklama) {
        txtGeriAlAciklama.setValue(aciklama);
        btnGeriAlOnay.click();
        return this;
    }

    @Step("Filtre panelini aç")
    public ImzaladiklarimPage filtrePanelAc() {
        filtrePanelHeader.click();
        return this;
    }
}
