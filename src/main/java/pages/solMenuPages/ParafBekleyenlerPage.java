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
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;


public class ParafBekleyenlerPage extends MainPage {

    ElementsCollection tblParafBekleyenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasTab = $(By.xpath("//span[contains(@class, 'evrakPaylas')]/.."));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:7:cmdbutton"));
    SelenideElement btnTreeKapat = $(By.id("mainPreviewForm:evrakPaylasKisiLov:lovTreePanelKapat"));
    SelenideElement btnParafla = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td:nth-child(5) button");

    SelenideElement btnPaylasimdanGeriAl = $(By.xpath("//span[contains(@class, 'evrakGeriAl')]/.."));
    ElementsCollection tablePaylasimdanGeriAl = $$("div[id='mainPreviewForm:geriAlPaylasimDatatable'] tbody > tr[role='row']");


    ElementsCollection tabEvrakOnizleme = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] ul[role='tablist'] li");

    // Paylaş Tab elementleri

    SelenideElement btnPaylasimiDurdur = $(By.xpath("//span[contains(@class, 'evrakPaylasimDurdur')]/.."));
    SelenideElement btnPaylasimiDurdurEvet = $(By.id("mainInboxForm:paylasmaDurdurEvetButton"));


    // Evrak Notları elementleri
    SelenideElement btnEvratNotEkle = $("button[id$=':paylasimNotuEkleId']");
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    SelenideElement txtPaylasAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    ElementsCollection tablePaylasilanlar = $$("div[id='mainPreviewForm:evrakOnizlemeTab'] div[aria-hidden='false'] tbody > tr[role='row']");
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    BelgenetElement txtTakipListesiKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat = $("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");
    ElementsCollection tableEvrakNotlari = $$(By.xpath("//th[contains(., 'Evrak Notları')]/../../../tbody/tr"));

    //Önizleme
    SelenideElement tabEvrakEkleri = $(By.xpath("//*[contains(text(),'Evrak Ekleri')]"));
    SelenideElement tabIlgiBilgileri = $(By.xpath("//*[contains(text(),'İlgi Bilgileri')]"));
    SelenideElement tabIlisikBilgileri = $(By.xpath("//*[contains(text(),'İlişik Bilgileri')]"));
    SelenideElement accordionEvrakEkleri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnl:0'] [class='onizlemeFrame']");
    SelenideElement accordionIlgiBilgileri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnlI:0'] [class='onizlemeFrame']");
    SelenideElement accordionIlisikBilgileri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnlIlisik:0'] [class='onizlemeFrame']");

    SelenideElement evrakOnIzleme = $("[id^='mainPreviewForm:j_idt'] [class='ui-tabs-panel ui-widget-content ui-corner-bottom']");
    SelenideElement btnEvrakSil = $("[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] [class$='evrakSil']");
    SelenideElement btnEvrakOnizlemdeSil = $("[id^='mainPreviewForm:j_idt'] [class$='ui-button-text']");
    SelenideElement txtEvrakOnizlemdeSilNotu = $("[id^='mainPreviewForm:j_idt'][class^='ui-inputfield ui-inputtextarea']");



    @Step("Paraf Bekleyenler sayfası aç")
    public ParafBekleyenlerPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.ParafBekleyenler);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.ParafBekleyenler.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Kullancılar doldur")
    public ParafBekleyenlerPage takipListesiKullanicilarDoldur(String kullanicilar) {
        txtTakipListesiKullanicilar.type(kullanicilar).getTitleItems().filterBy(Condition.text(kullanicilar)).first().click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafBekleyenlerPage gizlilikRaporSecTakipListesi(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.$$("button[id^='mainInboxForm:inboxDataTable:']").get(0).click();
        return this;
    }

    public ParafBekleyenlerPage takipListeKapat() {
        btnTakipListesiKapat.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafBekleyenlerPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Satır seç")
    public ParafBekleyenlerPage satirSec(int satirIndex) {
        tblParafBekleyenEvraklar.get(satirIndex).click();
        return this;
    }


    //     // Yasin ÖZGÜL / Yasin TELLİ / Veysel KIRAN

    // yeniler

    @Step("Paylaş tabına tıkla")
    public ParafBekleyenlerPage paylasTabTikla() {
        btnPaylasTab.click();
        return this;
    }

    @Step("Evrak seç ")
    public ParafBekleyenlerPage evrakSec(String paylasilanKullanici) {
        tblParafBekleyenEvraklar
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .get(0)
                .click();
        return this;
    }

    @Step("Evrak seç ")
    public ParafBekleyenlerPage evrakSec(String konu, String gidecegiYer, String gonderen, String tarih) {
        tblParafBekleyenEvraklar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Gönderen: " + gonderen))
                .filterBy(Condition.text(tarih))
                .first()
                .click();
        return this;
    }

    @Step("Evrak seç ")
    public ParafBekleyenlerPage konuyaGoreEvrakSec(String konu) {
        tblParafBekleyenEvraklar
                .filterBy(Condition.text(konu)).first()
                .click();
        return this;
    }

    @Step("Evrak seçildi")
    public ParafBekleyenlerPage evrakSec(String[] paylasilanKullanicilar) {
        String _paylasilanKullanicilar = "";

        for (int i = 0; i < paylasilanKullanicilar.length; i++)
            _paylasilanKullanicilar += paylasilanKullanicilar[i] + " / ";

        _paylasilanKullanicilar = _paylasilanKullanicilar.substring(0, _paylasilanKullanicilar.length() - 3);

        tblParafBekleyenEvraklar
                .filterBy(Condition.text("Paylaşılanlar: " + _paylasilanKullanicilar))
                .get(0)
                .click();
        return this;
    }

    @Step("\"{tabAdi}\" tabını seç")
    public ParafBekleyenlerPage evrakOnizlemeTabSec(String tabAdi) {

        tabEvrakOnizleme
                .filterBy(Condition.text(tabAdi))
                .get(0)
                .click();

        return this;
    }

    // Paylaş tab fonsiyonlar
    @Step("Paylaşımı durdur")
    public ParafBekleyenlerPage paylasimiDurdur() {
        btnPaylasimiDurdur.click();
        btnPaylasimiDurdurEvet.click();
        return this;
    }

    // Evrak notları fonksiyonları
    @Step("Evrak ekleme butonu aktif olmalı mı? : \"{0}\" ")
    public ParafBekleyenlerPage evrakNotEklemeButonuAktifOlmali(boolean aktifOlmali) {
        if (aktifOlmali == true)
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "false"));
        else
            btnEvratNotEkle.shouldHave(Condition.attribute("aria-disabled", "true"));
        return this;
    }

    @Step("Paylaşımdan geri al tabına tıklandı. ")
    public ParafBekleyenlerPage paylasimdanGeriAlTabSec() {
        btnPaylasimdanGeriAl.click();
        return this;
    }

    @Step("\"{0}\" kullanıcısını paylaşımdan geri al")
    public ParafBekleyenlerPage paylasimdanGeriAl(String[] paylasilanlar) {

        for (int i = 0; i < paylasilanlar.length; i++) {

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
    public ParafBekleyenlerPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("Paylaş")
    public ParafBekleyenlerPage paylasPaylas() {
        btnPaylasPaylas.click();
        return this;
    }

    @Step("Paylaşılacak kişi seç: {0} ")
    public ParafBekleyenlerPage paylasKisiSec(String kisiAdi) {
        txtPaylasKisi.selectLov(kisiAdi);
        return this;
    }

    @Step("Paylaşılacak kişi seç: {0} ")
    public ParafBekleyenlerPage paylasKisiSec(String[] kisiler) {
        for (int i = 0; i < kisiler.length; i++)
            txtPaylasKisi.selectLov(kisiler[i]);
        return this;
    }

    @Step("Paylaşma tabında açıklama girildi : \"{0}\"")
    public ParafBekleyenlerPage paylasimAciklamaYaz(String aciklama) {
        txtPaylasAciklama.setValue(aciklama);
        return this;
    }

    @Step("paylaşılan kişileri temizle ")
    public ParafBekleyenlerPage paylasilanKisileriTemizle() {
        txtPaylasKisi.clearAllSelectedItems();
        return this;
    }

    @Step("Açıklama kontrol")
    public ParafBekleyenlerPage evrakNotuKontrol(String ekleyen, String tarih, String aciklama, Boolean shouldBeExist) {
        if (shouldBeExist == true) {
            tableEvrakNotlari
                    .filterBy(Condition.text(ekleyen))
                    .filterBy(Condition.text(tarih))
                    .filterBy(Condition.text(aciklama))
                    .get(0)
                    .shouldBe(Condition.exist);
        } else {

            tableEvrakNotlari
                    .filterBy(Condition.text(ekleyen))
                    .filterBy(Condition.text(tarih))
                    .filterBy(Condition.text(aciklama))
                    .get(0)
                    .shouldNotBe(Condition.exist);
        }

        return this;
    }

    @Step("Açıklama kontrol")
    public ParafBekleyenlerPage paylasilanKontrol(String kullanici, String birim, String paylasimDurumu, String geriAlinmaTarihi) {
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
    public String paylasilmaTarihiGetir(String konu, String evrakNo, String paylasilanKullanici) {

        String pTarihi = tblParafBekleyenEvraklar
                .filterBy(Condition.text("Evrak No: " + evrakNo))
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Paylaşılanlar: " + paylasilanKullanici))
                .get(0)
                .$(By.xpath(".//td[contains(., 'Paylaşılma Tarihi:')]"))
                .innerText();

        return pTarihi.substring(pTarihi.indexOf("Paylaşılma Tarihi:") + 19, pTarihi.indexOf("Paylaşılma Tarihi:") + 38);
    }

    @Step("Parafla")
    public ParafBekleyenlerPage parafla() {
        btnParafla.click();
        return this;
    }

    @Step("Paraf Bekleyenler listesinde evrak kontrolu")
    public ParafBekleyenlerPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblParafBekleyenEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Parafladıklarım listesinden evrak önizlemede aç")
    public ParafBekleyenlerPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblParafBekleyenEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Parafladıklarım listesinden evrak detay göster")
    public ParafBekleyenlerPage konuyaGoreEvrakDetayGoster(String konu) {

        tblParafBekleyenEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='detayGosterButton']").click();

        return this;
    }

    @Step("Evrak Ek/İlgi/İlişikler tablarının geldiği kontrolu")
    public ParafBekleyenlerPage tabKontrolleri() {

        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);
        Assert.assertEquals(tabIlisikBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Paraf Bekleyenler/Evrak Ekleri tabını aç")
    public ParafBekleyenlerPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Paraf Bekleyenler/İlgi Bilgileri tabını aç")
    public ParafBekleyenlerPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }

    @Step("Paraf Bekleyenler/İlişik Bilgileri tabını aç")
    public ParafBekleyenlerPage tabIlisikBilgileriAc() {
        tabIlisikBilgileri.click();
        return this;
    }

    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public ParafBekleyenlerPage evrakEkleriAccordionKontrol() {
        Assert.assertEquals(accordionEvrakEkleri.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlgi Bilgileri Accordion kontrolu")
    public ParafBekleyenlerPage ilgiBilgileriAccordionKontrol() {
        Assert.assertEquals(accordionIlgiBilgileri.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlişik Bilgileri Accordion kontrolu")
    public ParafBekleyenlerPage ilisikBilgileriAccordionKontrol() {
        Assert.assertEquals(accordionIlisikBilgileri.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme ekranı kontrolu")
    public ParafBekleyenlerPage evrakOnizlemeKontrol() {

        Assert.assertEquals(evrakOnIzleme.isDisplayed(), true);

        return this;
    }


    @Step("Sil butonu kontrolu")
    public ParafBekleyenlerPage silButonuKontrolu() {

        Assert.assertEquals(btnEvrakSil.isDisplayed(), true);

        return this;
    }

    @Step("Sil")
    public ParafBekleyenlerPage sil() {
        btnEvrakSil.click();
        return this;
    }

    @Step("Evrak Önizlemde Sil")
    public ParafBekleyenlerPage evrakOnizlemedeSil() {
        btnEvrakOnizlemdeSil.click();
        return this;
    }

    @Step("Evrak Önizlemde Silme Notu Doldur")
    public ParafBekleyenlerPage evrakSilNotuDoldur(String not) {
        txtEvrakOnizlemdeSilNotu.setValue(not);
        return this;
    }


}
