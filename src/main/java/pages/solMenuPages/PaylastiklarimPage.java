package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;


public class PaylastiklarimPage extends MainPage {


    ElementsCollection tablePaylastiklarim = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");
    ElementsCollection evrakSecTablar = $$("[id='mainPreviewForm:evrakOnizlemeTab'] ul li a");
    SelenideElement btnPaylasTab = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    SelenideElement btnTreeKapat = $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTreePanelKapat"));

    SelenideElement btnPaylasimdanGeriAl = $(By.xpath("//span[contains(@class, 'evrakGeriAl')]/.."));
    ElementsCollection tablePaylasimdanGeriAl = $$("div[id='mainPreviewForm:geriAlPaylasimDatatable'] tbody > tr[role='row']");


    ElementsCollection tabEvrakOnizleme = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] ul[role='tablist'] li");

    // Paylaş Tab elementleri

    SelenideElement btnPaylasimiDurdur = $(By.xpath("//span[contains(@class, 'evrakPaylasimDurdur')]/.."));


    // Evrak Notları elementleri
    SelenideElement btnEvratNotEkle = $("button[id$=':paylasimNotuEkleId']");
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));

    SelenideElement txtPaylasAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    ElementsCollection tablePaylasilanlar = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] div[aria-hidden='false'] tbody > tr[role='row']");

    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    ElementsCollection tableEvrakNotlari = $$(By.xpath("//th[contains(., 'Evrak Notları')]/../../../tbody/tr"));

    @Step("Paylaştıklarım sayfası aç")
    public PaylastiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Paylastiklarim);
        return this;
    }

    @Step("Tablodan rapor seç")
    public PaylastiklarimPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Satır seç")
    public PaylastiklarimPage satirSec(int satirIndex) {
        tablePaylastiklarim.get(satirIndex).click();
        return this;
    }


    //     // Yasin ÖZGÜL / Yasin TELLİ / Veysel KIRAN

    // yeniler

    @Step("Paylaş tabına tıkla")
    public PaylastiklarimPage paylasTabTikla() {
        clickJs(btnPaylasTab);
        return this;
    }

    @Step("Evrak seç ")
    public PaylastiklarimPage evrakSec(String paylasilanKullanici) {
        tablePaylastiklarim
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .get(0)
                .click();
        return this;
    }

    @Step("Evrak seç ")
    public PaylastiklarimPage evrakSec(String konu, String paylasilmaTarihi) {
        tablePaylastiklarim
                .filterBy(Condition.text(konu))
                .filterBy(Condition.text(paylasilmaTarihi))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Notları")
    public PaylastiklarimPage evrakNotlariTabAc() {
        evrakSecTablar.get(3).click();
        return this;
    }

    @Step("Evrak Notları")
    public PaylastiklarimPage paylasilanlarTabAc() {
        evrakSecTablar.get(4).click();
        return this;
    }

    @Step("Otomatik onay akışı kontrol")
    public PaylastiklarimPage evrakNotlariAciklamaGorme(String aciklama) {

        $$("[id='mainPreviewForm:evrakOnizlemeTab'] table tbody tr")
                .filterBy(text(aciklama)).shouldHave(sizeGreaterThan(0)).get(0).click();
        return this;
    }

    @Step("Paylaşılanlar kullanıcı görme")
    public PaylastiklarimPage paylasilanlarKullaniciGorme(String kisi) {

        $$("[id='mainPreviewForm:evrakOnizlemeTab'] div[class='ui-datatable ui-widget'] table tbody tr")
                .filterBy(text(kisi)).shouldHave(sizeGreaterThan(0)).get(0).click();
        return this;
    }

    @Step("Evrak seç : {konu}")
    public PaylastiklarimPage evrakSecKonuyaGore(String konu) {
        tablePaylastiklarim
                .filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Evrak seç ")
    public PaylastiklarimPage evrakSec(String konu, String evrakNo, String paylasilanKullanici, String paylasilmaTarihi) {
        tablePaylastiklarim
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                .first()
                .click();
        return this;
    }

    @Step("Evrak seçildi")
    public PaylastiklarimPage evrakSec(String[] paylasilanKullanicilar, String konu, String evrakNo, String paylasilmaTarihi) {
        String _paylasilanKullanicilar = "";

        for (int i = 0; i < paylasilanKullanicilar.length; i++)
            _paylasilanKullanicilar += paylasilanKullanicilar[i] + " / ";

        _paylasilanKullanicilar = _paylasilanKullanicilar.substring(0, _paylasilanKullanicilar.length() - 3);

        tablePaylastiklarim
                .filterBy(Condition.text("Paylaşılanlar: " + _paylasilanKullanicilar))
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                .first()
                .click();
        return this;
    }

    @Step("Evrak seçildi")
    public String evrakPaylasimTarihiGetir(String[] paylasilanKullanicilar, String konu, String evrakNo, String paylasilmaTarihi) {
        String _paylasilanKullanicilar = "";

        for (int i = 0; i < paylasilanKullanicilar.length; i++)
            _paylasilanKullanicilar += paylasilanKullanicilar[i] + " / ";

        _paylasilanKullanicilar = _paylasilanKullanicilar.substring(0, _paylasilanKullanicilar.length() - 3);

        String pTarihi = tablePaylastiklarim
                .filterBy(Condition.text("Paylaşılanlar: " + _paylasilanKullanicilar))
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Paylaşılma Tarihi: " + paylasilmaTarihi))
                .first()
                .$(By.xpath(".//td[contains(., 'Paylaşılma Tarihi:')]"))
                .innerText();


        return pTarihi.substring(pTarihi.indexOf("Paylaşılma Tarihi:") + 19, pTarihi.indexOf("Paylaşılma Tarihi:") + 38);

    }

    @Step("\"{0}\" tabını seç")
    public PaylastiklarimPage evrakOnizlemeTabSec(String tabAdi) {

        tabEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();
        ;

        return this;
    }

    @Step("\"{0}\" tabını seç")
    public PaylastiklarimPage paylasilanlarTabSec(String tabAdi) {

        tabEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();
        ;

        return this;
    }

    // Paylaş tab fonsiyonlar
    @Step("Paylaşımı durdur")
    public PaylastiklarimPage paylasimiDurdur() {
        btnPaylasimiDurdur.click();
        $$("div[id='mainInboxForm:paylasmaDurdurulsunMuDialog'] button[id='mainInboxForm:paylasmaDurdurEvetButton']").last().click();
        return this;
    }

    // Evrak notları fonksiyonları
    @Step("Evrak ekleme butonu aktif olmalı mı? : \"{0}\" ")
    public PaylastiklarimPage evrakNotEklemeButonuAktifOlmali(boolean aktifOlmali) {
        if (aktifOlmali == true)
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "false"));
        else
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "true"));
        return this;
    }

    @Step("Paylaşımdan geri al tabına tıklandı. ")
    public PaylastiklarimPage paylasimdanGeriAlTabSec() {
        btnPaylasimdanGeriAl.click();
        return this;
    }

    @Step("\"{0}\" kullanıcısını paylaşımdan geri al")
    public PaylastiklarimPage paylasimdanGeriAl(String[] paylasilanlar) {

        for (int i = 0; i < paylasilanlar.length; i++) {

            SelenideElement currentRow = tablePaylasimdanGeriAl
                    .filterBy(Condition.text(paylasilanlar[i]))
                    .get(0);

            $(By.xpath("//legend[text() = 'Paylaşımdan Geri Al']"))
                    .waitUntil(Condition.visible, 5000);

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

    @Step("Paylaşılacak kişi seç: {0} ")
    public PaylastiklarimPage paylasKisiSec(String kisiAdi) {
        txtPaylasKisi.selectLov(kisiAdi);
        return this;
    }

    @Step("Paylaşılacak kişi seç: {0} ")
    public PaylastiklarimPage paylasKisiSec(String[] kisiler) {
        for (int i = 0; i < kisiler.length; i++)
            txtPaylasKisi.selectLov(kisiler[i]);
        return this;
    }

    @Step("Paylaşma tabında açıklama girildi : \"{0}\"")
    public PaylastiklarimPage paylasimAciklamaYaz(String aciklama) {
        txtPaylasAciklama.setValue(aciklama);
        return this;
    }

    @Step("Paylaş")
    public PaylastiklarimPage paylasPaylas() {
        btnPaylasPaylas.click();
        return this;
    }

    @Step("paylaşılan kişileri temizle ")
    public PaylastiklarimPage paylasilanKisileriTemizle() {
        txtPaylasKisi.clearAllSelectedLov();
        return this;
    }

    @Step("Açıklama kontrol")
    public PaylastiklarimPage evrakNotuKontrol(String ekleyen, String tarih, String aciklama) {
        tableEvrakNotlari
                .filterBy(Condition.text(ekleyen))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(aciklama))
                .get(0)
                .shouldBe(Condition.exist);
        return this;
    }

    @Step("Açıklama kontrol")
    public PaylastiklarimPage paylasilanKontrol(String kullanici, String birim, String paylasimDurumu, String geriAlinmaTarihi) {
        tablePaylasilanlar
                .filterBy(Condition.text(kullanici))
                .filterBy(Condition.text(birim))
                .filterBy(Condition.text(paylasimDurumu))
                .filterBy(Condition.text(geriAlinmaTarihi))
                .get(0)
                .shouldBe(Condition.exist);
        return this;
    }

    @Step("Açıklama kontrol")
    public PaylastiklarimPage paylasilanKontrolTumKullanıcılıar(String[] kullanici, String paylasimDurumu) {
        for (int i = 0; i < kullanici.length; i++)
            tablePaylasilanlar
                    .filterBy(Condition.text(kullanici[i]))
                    .filterBy(Condition.text(paylasimDurumu))
                    .shouldHaveSize(1);
        return this;
    }

    @Step("Açıklama kontrol")
    public String paylasilmaTarihiGetir(String konu, String evrakNo, String paylasilanKullanici) {

        String pTarihi = tablePaylastiklarim
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .get(0)
                .$(By.xpath(".//td[contains(., 'Paylaşılma Tarihi:')]"))
                .innerText();

        return pTarihi.substring(pTarihi.indexOf("Paylaşılma Tarihi:") + 19, pTarihi.indexOf("Paylaşılma Tarihi:") + 38);
    }


}
