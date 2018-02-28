package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TeslimAlinmayiBekleyenlerPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection btnTeslimAlVeKapat = $$("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] table td[class='buttonMenuContainerDefault']");
    SelenideElement btnTeslimAlVeKapat2 = $("[class='ui-button-icon-left ui-icon teslimAlHavale']");
    BelgenetElement txtTeslimAlVeHavaleEtBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    BelgenetElement txtTeslimAlVeHavaleEtKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtTeslimAlVeHavaleEtKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtEvrakDetayTeslimAlVeHavaleEtBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));
    BelgenetElement txtEvrakDetayTeslimAlVeHavaleEtKisi = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement btnTeslimAlVeHavaleEtTeslimAlVeGonder = $("[id$='btnTeslimAlGonder']");
    SelenideElement btnEvrakDetayTeslimAlVeHavaleEtTeslimAlVeGonder = $(By.id("inboxItemInfoForm:btnTeslimAlGonder"));
    BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    BelgenetElement txtKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement btnTeslimAlVeKapatTeslimAlVeKapat = $("[id='mainPreviewForm:evrakOnizlemeTab'] div[class='form-buttons kapatButtonDirekt'] button");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection btnTeslimAl = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-delivery']");

    //Önizleme
    SelenideElement formEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    SelenideElement tabEvrakEkleri = $(By.xpath("//*[contains(text(),'Evrak Ekleri')]"));
    SelenideElement tabIlgiBilgileri = $(By.xpath("//*[contains(text(),'İlgi Bilgileri')]"));
    SelenideElement tabIlisikBilgileri = $(By.xpath("//*[contains(text(),'İlişik Bilgileri')]"));

    SelenideElement accordionEvrakEkleri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionEvrakEkleriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionEvrakEkleri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionEvrakEkleriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(3)");

    SelenideElement accordionIlgiBilgileri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionIlgiBilgileriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionIlgiBilgileri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionIlgiBilgileriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(3)");

    ElementsCollection tblOnIzlemeEkler = $$("[id*='ekListesiOnizlemeDataTable'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlgiBilgileri = $$("[id*='ilgiListesiDataTable_data'] > tr[role='row']");

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement evrakTeslimAl = $("[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:5:cmdbutton']");

    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement teslimAlGönder = $("[id='mainPreviewForm:btnTeslimAlGonder']");
    ElementsCollection birimDegistirme = $$("a[id^='leftMenuForm:edysMenuItem'] span[class='ui-menuitem-text']");

    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement btnOnizlemeIadeEt = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] span[class$='iadeEt']");
    ElementsCollection lblIadeEdilecekKullanici = $$("table[id='mainPreviewForm:iadeBilgileriPanelGrid'] label");
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    SelenideElement txtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnOnizlemeIadeEtDosyaEkle = $(By.id("mainPreviewForm:fileUploadIadeEk"));
    SelenideElement dosyaPathIade = $(By.xpath("//input[@id='mainPreviewForm:fileUploadIadeEk_input']"));
    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));
    SelenideElement evrakHavaleKontrol = $(By.id("mainPreviewForm:onizlemePanel"));


    ElementsCollection ilgiBilgileriEkleriKontrol = $$("div[id$='ilgiListesiDataTable'] tr[data-ri]");
    ElementsCollection teslimEvrakEkleri = $$("a[href^='#mainPreviewForm']");
    ElementsCollection teslimEvrakEkleriKontrol = $$("div[id$='ekListesiOnizlemeDataTable'] tr[data-ri]");

    BelgenetElement txtSecilenlerOnaylayacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));

    ElementsCollection tblKaydedilenGelenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    SelenideElement tabEvrakDetayi = $("[id='inboxItemInfoForm']");
    SelenideElement btnTeslimAlPopup=$(By.id("teslimAlEvetButton"));

    public TeslimAlinmayiBekleyenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinmayiBekleyenler);
//        ustMenu("Teslim Alınmayı Bekleyenler");
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinmayiBekleyenlerPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(text(konu), text(yer), text(tarih), text(no))
//                .filterBy(text(yer))
//                .filterBy(text(tarih))
//                .filterBy(text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        //evrak.click();
        return this;
    }

    @Step("Kladırılıcak klasörler doldur")
    public TeslimAlinmayiBekleyenlerPage kaldirilacakKlasorlerDoldur(String kaldirilicakKlasor) {
        txtKaldirilacakKlasorler.selectLov(kaldirilicakKlasor);
        return this;
    }

    @Step("Konu kodu doldur")
    public TeslimAlinmayiBekleyenlerPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlveKapatTeslimAlVeKapat() {
        btnTeslimAlVeKapatTeslimAlVeKapat.click();
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeKapat() {
        btnTeslimAlVeKapat.filterBy(Condition.text("Teslim Al ve Kapat")).get(0).$("button").click();
        return this;
    }

    @Step("Birim alanını doldur: {birimAd} - {birim} ")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeHavaleEtBirimDoldur(String birimAd, String birim) {
        txtTeslimAlVeHavaleEtBirim.type(birimAd).getDetailItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Birim alanını doldur: {birimAd} - {birim} ")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeHavaleEtKullaniciListesiDoldur(String kullaniciListesi, String birim) {
        txtHavaleYapKullaniciListesi.type(kullaniciListesi).getTitleItems().filterBy(Condition.text(kullaniciListesi)).first().click();
        txtHavaleYapKullaniciListesi.type(kullaniciListesi).getTitleItems().filterBy(Condition.text(kullaniciListesi)).first().click();
        return this;
    }

    @Step("Teslim Al ve Havale ekranında Kullanıcı Listesi alanında \"{kullaniciListesi}\" seçilir.")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeHavaleEtKullaniciListesiDoldur(String kullaniciListesi) {
        BelgenetElement  txtHavaleYapKullaniciListesi=comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Gereği için gönder ifadesi koordinasyon için gönder olarak değiştir")
    public TeslimAlinmayiBekleyenlerPage kullaniciListesiGeregiIcinGonderKordinasyonIcinGonderDegistir() {
        $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:selectOneMenu']").selectOption("KOORDİNASYON İÇİN GÖNDER");
        return this;
    }

    @Step("")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeGonder() {
        $(By.id("mainPreviewForm:btnTeslimAlGonder")).click();
        return this;
    }

    @Step("Kişi alanını doldur: {kisiAd} - {birim} ")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeHavaleEtKisiDoldur(String kisiAd, String birim) {
        txtTeslimAlVeHavaleEtKisi.type(kisiAd).getDetailItems().filterBy(Condition.text(birim)).first().click();
        txtTeslimAlVeHavaleEtKisi.type(kisiAd).getSelectableItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Gereği için gönder ifadesi bilgi için gönder olarak değiştir")
    public TeslimAlinmayiBekleyenlerPage kisiGeregiIcinGonderBilgiIcinGonderDegistir() {
        $("[id='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable_data'] select").selectOption("BİLGİ İÇİN GÖNDER");
        return this;
    }

    @Step("Birim alanını doldur: {birimAd} - {birim} ")
    public TeslimAlinmayiBekleyenlerPage evrakDetayTeslimAlVeHavaleEtBirimDoldur(String birimAd, String birim) {
        txtEvrakDetayTeslimAlVeHavaleEtBirim.type(birimAd).getDetailItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Birim alanını doldur: {birimAd} - {birim} ")
    public TeslimAlinmayiBekleyenlerPage evrakDetayTeslimAlVeHavaleEtKisiDoldur(String birimAd, String birim) {
        txtEvrakDetayTeslimAlVeHavaleEtKisi.type(birimAd).getDetailItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Gereği için gönder ifadesi bilgi için gönder olarak değiştir")
    public TeslimAlinmayiBekleyenlerPage evrakDetayTeslimAlVeHavaleEtSecilenBirimBilgiSec() {
        $(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovSecilenTable:0:selectOneMenu")).selectOption("BİLGİ İÇİN GÖNDER");
        return this;
    }

    @Step("Teslim Al Gönder tıklanır")
    public TeslimAlinmayiBekleyenlerPage teslimAlveHavaleEtTeslimAlGonder() {
        btnTeslimAlVeHavaleEtTeslimAlVeGonder.click();
        return this;
    }

    @Step("Teslim Al Gönder tıklanır")
    public TeslimAlinmayiBekleyenlerPage evrakDetayTeslimAlveHavaleEtTeslimAlGonder() {
        btnEvrakDetayTeslimAlVeHavaleEtTeslimAlVeGonder.pressEnter();
        return this;
    }


    @Step("Kapatma tipi, Konu kodu, Kaldırılacak klasörler, Not ve Onay akışı alanlarının geldiği görülür.")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeKapatAlanGeldigiGorme() {
        boolean durum = $$("[id='mainPreviewForm:evrakKapatPanelGrid']").size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Evrak geldiği görünür")
    public TeslimAlinmayiBekleyenlerPage evrakGeldigiGorunur(String konuKodu, String tarih, String geldigiYer) {
        boolean durum = tblEvraklar
                .filterBy(Condition.text(konuKodu))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(geldigiYer)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak geldiği görünür")
    public TeslimAlinmayiBekleyenlerPage evrakGeldigiGorunur(String konuKodu) {
        boolean durum = tblEvraklar
                .filterBy(Condition.text(konuKodu)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Teslim Al ve Havale Et")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeHavaleEt() {
        btnTeslimAlVeKapat2.click();
        return this;
    }

    @Step("Seçilenleri teslim al ve havale et")
    public TeslimAlinmayiBekleyenlerPage secilenTeslimAlVeHavaleEt() {
        $("button[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only tipTip button-icon-borderless'] [class='ui-button-icon-left ui-icon document-delivery-publish']").click();
        return this;
    }

    @Step("Onaylayacak Kişi alanını doldur: {onaylayacakKisi}")
    public TeslimAlinmayiBekleyenlerPage secilenOnaylayacakKisiDoldur(String onaylayacakKisi) {
        txtSecilenlerOnaylayacakKisi.selectLov(onaylayacakKisi);
        return this;
    }

    @Step("")
    public TeslimAlinmayiBekleyenlerPage secilenHavaleOnayinaGonder() {
        $$("[id='mainPreviewForm:eastLayout'] span button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak no ile evrak içerik göster seçilir : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakNoIleEvrakIcerikGosterSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak no ile evrak Checkbox seçilir : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakNoIleEvrakCheckboxSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo)).get(0).$$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").first().click();
        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakOnizlemeKontrol() {
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Havale Ekran Kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakHavaleEkranKontrol() {
        if (evrakHavaleKontrol.isDisplayed())
            Allure.addAttachment("Evrak Havale Ekranı", "açılmıştır");
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evrak Ekleri Tıklama")
    public TeslimAlinmayiBekleyenlerPage teslimEvrakEkleri(String select) {
        teslimEvrakEkleri.filterBy(Condition.text(select)).get(0).click();
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Ilgi Bilgileri Kontrol")
    public TeslimAlinmayiBekleyenlerPage teslimIlgiBilgileriEvrakEkleriKontrol(String ek1, String ek2) {

        boolean durum1 = ilgiBilgileriEkleriKontrol.filterBy(Condition.text(ek1)).size() > 0;
        boolean durum2 = ilgiBilgileriEkleriKontrol.filterBy(Condition.text(ek2)).size() > 0;
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evrak Ekleri Kontrol")
    public TeslimAlinmayiBekleyenlerPage teslimEvrakEkleriKontrol(String ek1, String ek2, String ek3) {
        teslimEvrakEkleriKontrol.filterBy(Condition.text(ek1)).shouldHaveSize(1);
        teslimEvrakEkleriKontrol.filterBy(Condition.text(ek2)).shouldHaveSize(1);
        teslimEvrakEkleriKontrol.filterBy(Condition.text(ek3)).shouldHaveSize(1);
        return this;
    }


    @Step("Teslim Alinmayi Bekleyenler sayfasında evrakın listeye düşmediği kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        Allure.addAttachment(konu, "Nolu Evrak Listelenmemiştir.");
        return this;
    }

    @Step("Evrak önizleme evrak kontrolü : \"{pdfText}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakOnizlemeEklenenUstYaziKontrolu(String pdfText) {
        String text = "";
        switchTo().frame(1);
        sleep(1000);
        text = $(By.xpath("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[1]")).getText();
        text.equals(pdfText);
        switchTo().parentFrame();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).click();
        return this;
    }

    @Step("Evrak içerik göster")
    public TeslimAlinmayiBekleyenlerPage evrakSecIcerikGoster(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak teslim al")
    public TeslimAlinmayiBekleyenlerPage evrakSecTeslimAl(String konu, String yer, String tarih, String no, boolean secim) {
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak no ile teslim al Uyarı: Evrakı teslim almak istediğinize emin misiniz?\n Evet seçeneği seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSecNoTeslimAl(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak no ile teslim al Ikon Kontrolü")
    public TeslimAlinmayiBekleyenlerPage teslimAlIkonKontrol(String konu) {
        boolean durum = tblEvraklar.filterBy(text(konu)).get(0).$$("[class='ui-button-icon-left ui-icon document-delivery']").size()==1;
        Assert.assertEquals(durum,true,"Ikon Kontrolü");
        Allure.addAttachment("Teslim Al Ikonu:" + konu,"bulunmaktadır.");
        return this;
    }


    @Step("Evrak no ile içerik göster")
    public TeslimAlinmayiBekleyenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak Detay ekranı açılır\n")
    public TeslimAlinmayiBekleyenlerPage ekranKontrolEvrakDetayi() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }


    @Step("İçerikten Evrak teslim al")
    public TeslimAlinmayiBekleyenlerPage içeriktenEvrakTeslimAl() {
        evrakTeslimAl.click();
        return this;
    }

    @Step("İçerikten Evrak teslim Alma : Evrakı teslim almak istediğinize emin misiniz? uyarı kontrolü")
    public TeslimAlinmayiBekleyenlerPage içeriktenEvrakEvet() {
        $(By.id("teslimAlEvetButton")).click();
        return this;
    }


    @Step("Evrak Sec Toplu ve Teslim Al")
    public TeslimAlinmayiBekleyenlerPage evrakSecToplu(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        takeScreenshot();
        btnTeslimAl.get(0).click();
        return this;
    }

    @Step("Evrak geçmişi alanına tıklanır")
    public TeslimAlinmayiBekleyenlerPage secilenEvrakEvrakGecmisi() {
        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol")
    public TeslimAlinmayiBekleyenlerPage evrakGecmisi(String teslimAlinan, String islemSureci) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;

    }

    @Step("Filtrele alanını aç")
    public TeslimAlinmayiBekleyenlerPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public TeslimAlinmayiBekleyenlerPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public TeslimAlinmayiBekleyenlerPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public TeslimAlinmayiBekleyenlerPage tarihiDoldur(String tarih, Keys... keys) {
        dateTxtTarih.sendKeys(tarih);
        for (Keys k : keys) {
            dateTxtTarih.sendKeys(keys);
        }
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinmayiBekleyenlerPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Telim Al ve Havale Yap")
    public TeslimAlinmayiBekleyenlerPage havaleYap() {
        btnTeslimAlveHavaleYap.click();
        return this;
    }

    @Step("Kisi doldur")
    public TeslimAlinmayiBekleyenlerPage havaleYapKisiDoldur(String kisi) {
        txtHavaleYapKisi.selectLov(kisi);
        return this;
    }

    @Step("Kişi alanında \"{kisi}\" seçmeye dene")
    public TeslimAlinmayiBekleyenlerPage havaleYapKisiKisiSecmeyeDene(String kisi) {
        txtHavaleYapKisi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public TeslimAlinmayiBekleyenlerPage havaleYapKullaniciListesiDoldur(String kullaniciListesi) {
        //txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesinde \"{kisi}\" seçmeye dene")
    public TeslimAlinmayiBekleyenlerPage havaleYapKullaniciListesiSecmeyeDene(String kisi) {
        txtHavaleYapKullaniciListesi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage tabloKontrolu(String evrakNo) {
        $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[data-ri]")
                .filterBy(text(evrakNo))
                .shouldHaveSize(1);
        //log başarılı
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler listesinde evrak kontrolü:  \"{konu}\" ")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakKontroluAllPages(String konu) {
        searchTable().searchInAllPages(true).findRows(text(konu)).getFoundRow().shouldBe(exist);
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evraklar listesinde evrak kontrolu")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evraklar listesinden evrak önizlemede aç")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evraklar listesinden evrak önizlemede aç")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreIcerikGoster(String konu) {

        tblEvraklar
                .filterBy(Condition.text(konu))
                .first()
                .$("[id$='detayGosterButton']").click();

        return this;
    }


    @Step("\"{text}\" butonu tıklanır.")
    public TeslimAlinmayiBekleyenlerPage btnTikla(String text) {
        SelenideElement btn = $(By.xpath("descendant::*[text()='" + text + "']/ancestor::tbody[1]//button"));
        btn.click();
        return this;
    }

    @Step("Evrak Teslim Al popupı kapatılır. ")
    public TeslimAlinmayiBekleyenlerPage evrakTeslimAlPopUpEvet(){
        btnTeslimAlPopup.click();
        return this;
    }


    @Step("Evrak Ek/İlgi tablarının geldiği kontrolu")
    public TeslimAlinmayiBekleyenlerPage tabKontrolleri() {

        formEvrakOnizleme.shouldBe(exist);
        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Postalanacak Evraklar/Evrak Ekleri tabını aç")
    public TeslimAlinmayiBekleyenlerPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Postalanacak Evraklar/İlgi Bilgieri tabını aç")
    public TeslimAlinmayiBekleyenlerPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }


    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakEkleriAccordionKontrol() {

        accordionEvrakEkleriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen1.isDisplayed(), true);
        accordionEvrakEkleri1.click();
        Selenide.sleep(1000);
        accordionEvrakEkleri2.click();
        accordionEvrakEkleriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlgi Bilgileri Accordion kontrolu")
    public TeslimAlinmayiBekleyenlerPage ilgiBilgieriAccordionKontrol() {

        accordionIlgiBilgileriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen1.isDisplayed(), true);
        accordionIlgiBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlgiBilgileri2.click();
        accordionIlgiBilgileriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizlemede detay butonu kontrolu")
    public TeslimAlinmayiBekleyenlerPage detayButonKontrol(String ekSayisi) {

        tblOnIzlemeEkler
                .filterBy(Condition.text(ekSayisi))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ekleri detay butonu kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakEklerindeDetayButonuKontrol(String ek1, String ek2) {

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek1))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek2))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ilgi bilgileri detay butonu kontrolu")
    public TeslimAlinmayiBekleyenlerPage ilgiBilgilerindeDetayButonuKontrol(String ilgiSayisi1, String ilgiSayisi2) {

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi1))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi2))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public TeslimAlinmayiBekleyenlerPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
        cmbHavaleIslemleriBirim.type(birim).getDetailItems()
                .filterBy(Condition.exactText(details)).first().click();
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Birim Degisikligi Sonrası Teslim Al Gönder butonuna tıklama")
    public TeslimAlinmayiBekleyenlerPage teslimAlGonder() {
        teslimAlGönder.click();
        return this;
    }

    @Step("Kullanıcının Yeni Birimi \"{digerDetails}\" seçilir")
    public TeslimAlinmayiBekleyenlerPage birimDegistirme(String digerDetails) {
        birimDegistirme.filterBy(Condition.text(digerDetails)).get(0).click();
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi")
    public TeslimAlinmayiBekleyenlerPage iadeEt() {
        btnIadeEt.click();
        return this;
    }

    @Step("Iade Et buton kontrolü")
    public TeslimAlinmayiBekleyenlerPage onizlemeIadeEtKontrol() {
        Assert.assertEquals(btnOnizlemeIadeEt.isDisplayed(),true,"Iade et buton kontrolü");
        Allure.addAttachment("Iade et buton kontrolü","");
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi")
    public TeslimAlinmayiBekleyenlerPage onizlemeIadeEt() {
        btnOnizlemeIadeEt.click();
        return this;
    }

    @Step("Iade Edilecek Kullanıcı Kontrolü")
    public TeslimAlinmayiBekleyenlerPage onizlemeIadeEdilecekKullaniciKontrolu(String kisi) {
        boolean durum = lblIadeEdilecekKullanici.filterBy(Condition.text(kisi)).size() == 1;
        Assert.assertEquals(durum,true,"Iade Edilecek Kullanıcı Kontrolü");
        Allure.addAttachment("Iade Edilecek Kullanıcı Kontrolü","");
        return this;
    }

    @Step("Not alanını doldur: {not}")
    public TeslimAlinmayiBekleyenlerPage iadeEtNotDoldur(String not) {
        txtNot.setValue(not);
        return this;
    }

    public TeslimAlinmayiBekleyenlerPage onizlemeIadeEtDosyaEkle() {
        btnOnizlemeIadeEtDosyaEkle.click();
        return this;
    }

    @Step("Dosya Ekleme : \"{pathToFile}\" ")
    public TeslimAlinmayiBekleyenlerPage onizlemeIadeDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPathIade, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public TeslimAlinmayiBekleyenlerPage onizlemeIadeDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi ve Iade Et Tıklanması")
    public TeslimAlinmayiBekleyenlerPage iadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }

    @Step("Tabloda evrak kontrolü : \"{konu}\"  \"{geldigiKurum}\" \"{evrakTarihi}\" \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakAlanKontrolleri(String konu, String geldigiKurum, String evrakTarihi, String evrakNo) {
        System.out.println("evrakNo:" + konu + " geldigiKurum" + geldigiKurum + " evrakTarihi" + evrakTarihi + " evrakkayitno" + evrakNo);
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(konu))
                .filterBy(Condition.text(geldigiKurum))
                .filterBy(Condition.text(evrakTarihi))
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        Allure.addAttachment("Konu", konu);
        Allure.addAttachment("EvrakTarihi", evrakTarihi);
        Allure.addAttachment("GeldigiKurum", geldigiKurum);
        Allure.addAttachment("EvrakNo", evrakNo);
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evrak Ekleri Kontrol : {description}")
    public TeslimAlinmayiBekleyenlerPage teslimEvrakEkleriKontrol(String ek, String description) {

        teslimEvrakEkleriKontrol
                .filterBy(Condition.text(ek))
                .shouldHaveSize(1);

        return this;
    }

}
