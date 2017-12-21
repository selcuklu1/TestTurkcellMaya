package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


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

    @Step("Benimle Paylaşılanlar sayfasını aç")
    public BenimlePaylasilanlarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.BenimlePaylasilanlar);
        return this;
    }

    @Step("Durdurulmuş paylaşımlar checkbox seç")
    public BenimlePaylasilanlarPage durdurulmusPaylasimlarSec() {
        if(chkDurdurulmusPaylasimlar.isDisplayed() == false)
            divFiltrelePanel.click();

        chkDurdurulmusPaylasimlar.click();
        return this;
    }

    @Step("Filtrele butonuna tıklandı ")
    public BenimlePaylasilanlarPage filtrele() {
        btnFiltrele.click();
        return this;
    }

    @Step("Benimle paylaşılanlar tablosundan \"{0}\" seçildi")
    public BenimlePaylasilanlarPage evrakSec(String paylasan) {
        tableBenimlePaylasilanlar
                .filterBy(Condition.text("Paylaşan: " + paylasan))
                .get(0)
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

    @Step("\"{0}\" tabını seç")
    public BenimlePaylasilanlarPage evrakOnizlemeTabSec(String tabAdi) {
        tabsEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();;
        return this;
    }

    // Evrak notları fonksiyonları
    @Step("Evrak ekleme butonu aktif olmalı mı? : \"{0}\" ")
    public BenimlePaylasilanlarPage evrakNotEklemeButonuAktifOlmali(boolean aktifOlmali) {
        if(aktifOlmali == true)
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "false"));
        else
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "true"));
        return this;
    }

    @Step("Paylaşan: \"{paylasan}\" listede olmalımı?: \"{shouldBeExist}\"")
    public BenimlePaylasilanlarPage paylasilanlarKontrol(String paylasan, String konu, String paylasilmaTarihi, Boolean shouldBeExist) {

        if(shouldBeExist == true){
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

    @Step("evrak notu eklendi: \"{0}\" ")
    public BenimlePaylasilanlarPage evrakNotuGirVeKaydet(String evrakNotu) {
        txtEvrakNotu.setValue(evrakNotu);
        btnEvrakNotuKaydet.click();
        return this;
    }

    @Step("Evrak notu ekleme panelinde iptal butonuna tıklandı.")
    public BenimlePaylasilanlarPage evrakNotuPanelIptalButonuTikla() {
        btnEvrakNotuPanelIptal.click();
        return this;
    }

    ElementsCollection tableEvrakNotlari = $$(By.xpath("//th[contains(., 'Evrak Notları')]/../../../tbody/tr"));

    @Step("Açıklama kontrol")
    public BenimlePaylasilanlarPage evrakNotuKontrol(String ekleyen, String tarih, String aciklama) {
        tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .get(0)
                .shouldBe(Condition.exist);
        return this;
    }








}
