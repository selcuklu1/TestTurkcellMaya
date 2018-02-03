package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.*;


public class BenimlePaylasilanlarPage extends MainPage {

    SelenideElement chkDurdurulmusPaylasimlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:benimlePaylasilanlarDurdurulmusCheckbox"));
    SelenideElement btnFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:benimlePaylasilanlarFiltrele"));
    ElementsCollection tableBenimlePaylasilanlar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    ElementsCollection tabsEvrakOnizleme = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] ul[role='tablist'] li");

    // Evrak Notları elementleri
    SelenideElement btnEvratNotEkle = $("button[id$=':paylasimNotuEkleId']");
    SelenideElement divFiltrelePanel = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion"));
    SelenideElement txtEvrakNotu = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotAciklamaid"));
    SelenideElement btnEvrakNotuPanelIptal = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotIptal"));
    SelenideElement btnEvrakNotuKaydet = $(By.id("evrakKisiselNotDialogFormId:paylasimNotKaydet"));
    ElementsCollection birimSec = $$("[id='birimlerimMenusuContainer'] li a span");
    ElementsCollection tableEvrakNotlari = $$(By.xpath("//th[contains(., 'Evrak Notları')]/../../../tbody/tr"));

    @Step("Benimle Paylaşılanlar sayfasını aç")
    public BenimlePaylasilanlarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.BenimlePaylasilanlar);
        return this;
    }

    public BenimlePaylasilanlarPage birimSec(String birim) {
        birimSec.filterBy(Condition.text(birim)).get(0).click();
        sleep(3000);
        return this;
    }

    @Step("Durdurulmuş paylaşımlar checkbox seç")
    public BenimlePaylasilanlarPage durdurulmusPaylasimlarSec() {
        if (chkDurdurulmusPaylasimlar.isDisplayed() == false)
            divFiltrelePanel.click();

        chkDurdurulmusPaylasimlar.click();
        return this;
    }

    @Step("Filtrele butonuna tıklandı ")
    public BenimlePaylasilanlarPage filtrele() {
        btnFiltrele.click();
        return this;
    }

    @Step("Benimle paylaşılanlar tablosundan evrak seçildi")
    public BenimlePaylasilanlarPage evrakSec(String paylasan) {
        tableBenimlePaylasilanlar
                .filterBy(Condition.text("Paylaşan: " + paylasan))
                .get(0)
                .click();
        return this;
    }

    @Step("Evrak seç : {konu}")
    public BenimlePaylasilanlarPage evrakSecKonuyaGore(String konu) {
        tableBenimlePaylasilanlar
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Benimle paylaşılanlar tablosundan evrak seçildi")
    public BenimlePaylasilanlarPage evrakSec(String paylasan, String paylasilmaTarihi, String konu, String evrakNo) {
        tableBenimlePaylasilanlar
                .filterBy(Condition.text("Paylaşan: " + paylasan))
                .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .get(0)
                .click();
        return this;
    }

    @Step("Benimle paylaşılanlar tablosundan evrak seçildi")
    public BenimlePaylasilanlarPage evrakSec(String paylasan, String paylasilmaTarihi, String konu) {
        tableBenimlePaylasilanlar
                .filterBy(Condition.text("Paylaşan: " + paylasan))
                .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                .filterBy(Condition.text("Konu: " + konu))
                .get(0)
                .click();
        return this;
    }

    @Step("\"{0}\" tabını seç")
    public BenimlePaylasilanlarPage evrakOnizlemeTabSec(String tabAdi) {
        tabsEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();
        ;
        return this;
    }

    // Evrak notları fonksiyonları
    @Step("Evrak ekleme butonu aktif olmalı mı? : \"{aktifOlmali}\" ")
    public BenimlePaylasilanlarPage evrakNotEklemeButonuAktifOlmali(boolean aktifOlmali) {
        if (aktifOlmali == true)
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "false"));
        else
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "true"));
        return this;
    }

    @Step("Paylaşan: \"{paylasan}\" listede olmalımı?: \"{shouldBeExist}\"")
    public BenimlePaylasilanlarPage paylasilanlarKontrol(String paylasan, String konu, String paylasilmaTarihi, Boolean shouldBeExist) {

        if (shouldBeExist == true) {
            tableBenimlePaylasilanlar
                    .filterBy(Condition.text("Paylaşan: " + paylasan))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                    .first()
                    .shouldBe(Condition.exist);
        } else {
            tableBenimlePaylasilanlar
                    .filterBy(Condition.text("Paylaşan: " + paylasan))
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                    .first()
                    .shouldNotBe(Condition.exist);
        }

        return this;
    }

    @Step("Evraka not ekle butonuna tiklandi.")
    public BenimlePaylasilanlarPage evrakNotuEkle() {
        btnEvratNotEkle.click();
        return this;
    }

    @Step("evrak notu eklendi: \"{evrakNotu}\" ")
    public BenimlePaylasilanlarPage evrakNotuGirVeKaydet(String evrakNotu) {
        txtEvrakNotu.setValue(evrakNotu);
        btnEvrakNotuKaydet.click();
        return this;
    }

    @Step("evrak notu eklendi: \"{evrakNotu}\" ")
    public BenimlePaylasilanlarPage evrakNotuGirVeSil(String evrakNotu) {
        txtEvrakNotu.setValue(evrakNotu);
        txtEvrakNotu.clear();
        return this;
    }

    @Step("Evrak notu ekleme panelinde iptal butonuna tıklandı.")
    public BenimlePaylasilanlarPage evrakNotuPanelIptalButonuTikla() {
        btnEvrakNotuPanelIptal.click();
        return this;
    }

    @Step("Evrak Notu kontrolü.")
    public BenimlePaylasilanlarPage evrakNotuKontrol(String ekleyen, String tarih, String aciklama) {
        boolean durum = tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .first()
                .shouldBe(Condition.visible).exists();
        Assert.assertEquals(durum, true);
        return this;
    }
    // span[class*='delete-icon']

    @Step("Evrak notu silinememeli")
    public BenimlePaylasilanlarPage evrakNotuSilinmemeliKontrolet(String ekleyen, String tarih, String aciklama) {

        tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .first()
                .$("span[class*='delete-icon']")
                .shouldNotBe(Condition.visible);

        return this;
    }

    @Step("Evrak notu sil ve kontrol et. Evrak notu silinmeli.")
    public BenimlePaylasilanlarPage evrakNotuSil(String ekleyen, String tarih, String aciklama) {

        tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .first()
                .$("span[class*='delete-icon']")
                .click();

        tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .first()
                .$("span[class*='delete-icon']")
                .shouldNotBe(Condition.visible);

        return this;
    }

    @Step("Evrak Listede olmalı mı? : {shouldBeVisible}")
    public BenimlePaylasilanlarPage evrakKontrol(String paylasan, String paylasilmaTarihi, String konu, boolean shouldBeVisible) {
        if(shouldBeVisible == true) {

            tableBenimlePaylasilanlar
                    .filterBy(Condition.text("Paylaşan: " + paylasan))
                    .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                    .filterBy(Condition.text("Konu: " + konu))
                    .first()
                    .shouldBe(Condition.visible);

        } else {

            tableBenimlePaylasilanlar
                    .filterBy(Condition.text("Paylaşan: " + paylasan))
                    .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                    .filterBy(Condition.text("Konu: " + konu))
                    .first()
                    .shouldNotBe(Condition.visible);

        }


        return this;
    }


}
