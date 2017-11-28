package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


public class PaylastiklarimPage extends MainPage {


    ElementsCollection tablePaylastiklarim = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasTab = $(By.xpath("//span[contains(@class, 'evrakPaylas')]/.."));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    SelenideElement btnTreeKapat = $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTreePanelKapat"));

    SelenideElement btnPaylasimdanGeriAl = $(By.xpath("//span[contains(@class, 'evrakGeriAl')]/.."));
    ElementsCollection tablePaylasimdanGeriAl = $$("div[id='mainPreviewForm:geriAlPaylasimDatatable'] tbody > tr[role='row']");


    ElementsCollection tabEvrakOnizleme = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] ul[role='tablist'] li");

    // Paylaş Tab elementleri

    SelenideElement btnPaylasimiDurdur = $(By.xpath("//span[contains(@class, 'evrakPaylasimDurdur')]/.."));
    SelenideElement btnPaylasimiDurdurEvet = $(By.id("mainInboxForm:paylasmaDurdurEvetButton"));


    // Evrak Notları elementleri
    SelenideElement btnEvratNotEkle = $("button[id$=':paylasimNotuEkleId']");
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));

    SelenideElement txtPaylasAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    //ElementsCollection tablePaylasilanlar = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] div[aria-hidden='false'] tbody > tr[role='row']");


    @Step("Paylaştıklarım sayfası aç")
    public PaylastiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Paylastiklarim);
        return this;
    }

    @Step("Satır seç")
    public PaylastiklarimPage satirSec(int satirIndex) {
        tablePaylastiklarim.get(satirIndex).click();
        return this;
    }

    @Step("Paylaş tabına tıkla")
    public PaylastiklarimPage paylasTabTikla() {
        btnPaylasTab.click();
        return this;
    }



    //     // Yasin ÖZGÜL / Yasin TELLİ / Veysel KIRAN

    // yeniler

    @Step("\"{paylasilanKullanici}\" evrakını seç ")
    public PaylastiklarimPage evrakSec(String paylasilanKullanici) {
        tablePaylastiklarim
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .get(0)
                .click();
        return this;
    }

    @Step("Evrak seçildi")
    public PaylastiklarimPage evrakSec(String[] paylasilanKullanicilar) {
        String _paylasilanKullanicilar = "";

        for (int i = 0; i < paylasilanKullanicilar.length; i++)
            _paylasilanKullanicilar += paylasilanKullanicilar[i] + " / ";

        _paylasilanKullanicilar = _paylasilanKullanicilar.substring(0, _paylasilanKullanicilar.length() - 3);

        tablePaylastiklarim
                .filterBy(Condition.text("Paylaşılanlar: " + _paylasilanKullanicilar))
                .get(0)
                .click();
        return this;
    }

    @Step("\"{tabAdi}\" tabını seç")
    public PaylastiklarimPage evrakOnizlemeTabSec(String tabAdi) {

        tabEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();;

        return this;
    }


    // Paylaş tab fonsiyonlar
    @Step("Paylaşımı durdur")
    public PaylastiklarimPage paylasimiDurdur() {
        btnPaylasimiDurdur.click();
        btnPaylasimiDurdurEvet.click();
        return this;
    }

    // Evrak notları fonksiyonları
    @Step("Evrak ekleme butonu aktif olmalı mı? : {aktifOlmali} ")
    public PaylastiklarimPage evrakNotEklemeButonuAktifOlmali(boolean aktifOlmali) {
        if(aktifOlmali == true)
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "false"));
        else
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "true"));
        return this;
    }


    @Step("Paylaşımdan geri al tabına tıklandı.")
    public PaylastiklarimPage paylasimdanGeriAlTabSec() {
        btnPaylasimdanGeriAl.click();
        return this;
    }

    @Step("\"{0}\" kullanıcısını paylaşımdan geri al")
    public PaylastiklarimPage paylasimdanGeriAl(String[] paylasilanlar) {

        for(int i = 0; i < paylasilanlar.length; i ++){

            SelenideElement currentRow = tablePaylasimdanGeriAl
                    .filterBy(Condition.text(paylasilanlar[i]))
                    .get(0);

            $(By.xpath("//legend[text() = 'Paylaşımdan Geri Al']"))
                    .waitUntil(Condition.visible, 5);

            currentRow
                    .$("button")
                    .click();

            islemMesaji().basariliOlmali("İşlem başarılıdır!");

            currentRow
                    .$("td:nth-child(3) > div")
                    .shouldHave(Condition.text("Geri Alındı"));

            currentRow
                    .$("td:nth-child(4) > div")
                    .shouldNotBe(Condition.empty);

        }
        // mainPreviewForm:geriAlPaylasimDatatable:0:j_idt18448


        return this;
    }


    @Step("paylaş butonuna tıklandı. ")
    public PaylastiklarimPage paylas() {
        btnPaylas.click();
        return this;
    }


    @Step("Paylaşılacak kişi seç: \"{kisiAdi}\" ")
    public PaylastiklarimPage paylasKisiSec(String kisiAdi) {
        txtPaylasKisi.selectLov(kisiAdi);
        return this;
    }

    @Step("Paylaşma tabında açıklama girildi : \"{aciklama}\"")
    public PaylastiklarimPage paylasimAciklamaYaz(String aciklama) {
        txtPaylasAciklama.setValue(aciklama);
        return this;
    }

    @Step("paylaşılan kişileri temizle ")
    public PaylastiklarimPage paylasilanKisileriTemizle() {
        txtPaylasKisi.clearAllSelectedLov();
        return this;
    }








}
