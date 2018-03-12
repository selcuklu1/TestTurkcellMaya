package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/****************************************************
 * Proje: Türksat Functional Test Automation
 * Class: "BirimeIadeEdilenlerPage" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class BirimeIadeEdilenlerPage extends MainPage {

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement teslimAlButton = $("[id$='teslimAlButton']");
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement onizlemeTeslimAl = $("[id='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']");
    ElementsCollection btnTeslimAl = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-delivery']");
    ElementsCollection btnTeslimAlHavaleEt = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-delivery-publish']");

    SelenideElement teslimAlveHavaleEt = $("[id='mainInboxForm:inboxDataTable:0:teslimAlVeHavaleEtButton']");
    BelgenetElement txtOnaylanacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    SelenideElement içeriktenEvrakTeslimAlHavaleEt = $("[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:5:cmdbutton']");
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement dosyaEkleKontrol = $(By.id("inboxItemInfoForm:fileUploadTeslimAlHavaleEk"));
    SelenideElement onizlemeDosyaEkleKontrol = $(By.id("mainPreviewForm:fileUploadTeslimAlHavaleEk"));
    SelenideElement txtEklenenIcerikBirim = $("div[id^='inboxItemInfoForm:dagitimBilgileriBirimLov:LovSecilenTable']");
    SelenideElement txtEklenenOnizlemeKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable']");
    SelenideElement txtEklenenOnizlemeBirim = $("div[id^='mainPreviewForm:dagitimBilgileriBirimLov:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenOnizlemeKisiListesi = $("div[id^='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:j_idt']");
    SelenideElement eklenenKisiOnizlemeOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:selectOneMenu']");
    SelenideElement eklenenBirimOnizlemeOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriBirimLov:LovSecilenTable:0:selectOneMenu']");
    SelenideElement eklenenKisiListesiOnizlemeOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:selectOneMenu']");

    SelenideElement dosyaPath = $(By.xpath("//input[@id='inboxItemInfoForm:fileUploadTeslimAlHavaleEk_input']"));
    SelenideElement onizlemeDosyaPath = $(By.xpath("//input[@id='mainPreviewForm:fileUploadTeslimAlHavaleEk_input']"));

    SelenideElement teslimalgonder = $(By.id("inboxItemInfoForm:btnTeslimAlGonder"));

    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));
    SelenideElement onizlemeTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));

    BelgenetElement txtHavaleIslemleriKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement cmbHavaleIslemleriOnaylayacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    SelenideElement txtEklenenOnizlemeOnaylayan = $("div[id^='mainPreviewForm:onaylayacakKisiLov:lovSelectionPanel']");

    BelgenetElement txtHavaleIslemleriKisiListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection havaleOnayinaGonder = $$("[id^='mainPreviewForm:j_idt']");

    //Kontroller
    SelenideElement onaylayacakKisi = $("[id='mainPreviewForm:onaylayacakKisiLov:LovText']");
    SelenideElement birimKontrol = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement kisiKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement kullanıcıListeKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement aciklamaKontrol = $(By.id("mainPreviewForm:aciklamaInputText"));

    SelenideElement dosyaEkleKontrol2 = $(By.id("mainPreviewForm:fileUploadTeslimAlHavaleEk"));
//    SelenideElement islemSureKontrol = $(By.id("mainPreviewForm:islemSuresiTarih_input"));

    BelgenetElement havaleIslemleriBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));

    SelenideElement birimTopluTeslimAlGonder = $(By.id("mainPreviewForm:btnTopluTeslimAlGonder"));
    SelenideElement birimTeslimAlGonder = $(By.id("mainPreviewForm:btnTeslimAlGonder"));
    SelenideElement tabHavale = $("[id='mainPreviewForm:evrakOnizlemeTab']");
    SelenideElement tabEvrakDetayi = $("[id='inboxItemInfoForm']");
    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlVeHavaleEt = $("[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] [class$='teslimAlHavale']");
    BelgenetElement cmbBirimeHavale = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    By cmbBirimeHavaleBy = By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText");

    @Step("Birime İade Edilenler sayfası aç")
    public BirimeIadeEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimeIadeEdilenler);
        return this;
    }

    @Step("Evrak numara ile evrak seçilir : \"{evrakNo}\" ")
    public BirimeIadeEdilenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Teslim Al button kontrolü ")
    public BirimeIadeEdilenlerPage evrakTeslimAlButtonKontrol() {
        teslimAlButton.should(visible);
        takeScreenshot();
        return this;
    }

    @Step("Evrak no ile teslim al ve Evet seçeneğinin tıklanması ve Evrakı teslim almak istediğinize emin misiniz? uyarı kontrolü")
    public BirimeIadeEdilenlerPage evrakSecNoTeslimAl(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak tıklanır ve listelendiği görülür {konu}")
    public BirimeIadeEdilenlerPage evrakSec(String konu) {
        tblEvraklar.filterBy(text(konu)).get(0).click();
        return this;
    }

    @Step("Evrak Üzerinde Iade Et Button kontrolu")
    public BirimeIadeEdilenlerPage konuyaGoreEvrakIadeEtKontrolu(String konu) {
        boolean durum = tblEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[class$='document-typeIade']").isDisplayed();


        Assert.assertEquals(durum, true, "Iade Et Button kontrolü:");
        Allure.addAttachment("Iade Et Button Kontrolü", "");
        return this;
    }

    @Step("Teslim Al ve Havale Et")
    public BirimeIadeEdilenlerPage teslimAlVeHavaleEt() {
        $("[class='ui-button-icon-left ui-icon teslimAlHavale']").click();
        return this;
    }

    @Step("Onaylayacak Kişi doldur: {onaylanacakKisi} - {birim}")
    public BirimeIadeEdilenlerPage onaylanacakKisiDoldur(String onaylanacakKisi, String birim) {
        txtOnaylanacakKisi.selectLov(onaylanacakKisi, birim);
        return this;
    }

    @Step("")
    public BirimeIadeEdilenlerPage havaleOnayinaGonder() {
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }

    @Step("Önizleme ekranından Teslim Al butonuna basılır ve Evrakı teslim almak istediğinize emin misiniz? uyarısı Evet ile onaylanır")
    public BirimeIadeEdilenlerPage evrakOnizlemeTeslimAl() {
        onizlemeTeslimAl.click();
        $(By.id("teslimAlEvetButton")).click();
        return this;
    }

    @Step("Onizleme ekranı açılır\n")
    public BirimeIadeEdilenlerPage ekranOnizlemeKontrol() {
        Assert.assertEquals(tabHavale.isDisplayed(), true, "Onizleme sayfası");
        Allure.addAttachment("Onizleme sayfası", "açılmaktadır");
        return this;
    }

    @Step("Evrak Sec Toplu ve Teslim Al")
    public BirimeIadeEdilenlerPage evrakSecToplu(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAl.get(0).click();

        return this;
    }

    @Step("Toplu Evrak Sec 3 tane ve Teslim Al Havale Et")
    public BirimeIadeEdilenlerPage evrakSecToplu2(String konu1, String konu2, String konu3, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu3)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAlHavaleEt.get(0).click();

        return this;
    }

    @Step("Evrak Teslim Al Havale Et")
    public BirimeIadeEdilenlerPage evrakTeslimAlHavaletEt() {
        teslimAlveHavaleEt.click();
        return this;
    }


    @Step("Birime Iade Edilenler sayfasında evrakın listede olmadığının kontrolu")
    public BirimeIadeEdilenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false, "Evrak Bulunamamıştır");
        return this;
    }

    @Step("Birime Iade Edilenler sayfasında evrakın listede geldiği görünür")
    public BirimeIadeEdilenlerPage evrakNoGeldigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, true, "Evrak Bulunamamıştır");
        return this;
    }

    @Step("Evrak no ile icerikten teslim al")
    public BirimeIadeEdilenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak Detay ekranı açılır\n")
    public BirimeIadeEdilenlerPage ekranIcerikKontrol() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

    @Step("Evrak Havale ekranı açılır\n")
    public BirimeIadeEdilenlerPage ekranHavaleKontrol() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

    @Step("İçerikten Evrak Teslim Al ve Havale Et")
    public BirimeIadeEdilenlerPage içeriktenEvrakTeslimAlHavaleEt() {
        içeriktenEvrakTeslimAlHavaleEt.click();
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public BirimeIadeEdilenlerPage dagitimBilgileriBirimDoldur2(String birim) {
        cmbHavaleIslemleriBirim.selectLov(birim);
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }
    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenIcerikBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenIcerikBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemlerinde Dosya Ekle")
    public BirimeIadeEdilenlerPage dosyaEkle() {
        dosyaEkleKontrol.click();
        return this;
    }

    @Step("Havale İşlemlerinde Evrak Önizlemede Dosya Ekle")
    public BirimeIadeEdilenlerPage onizlemeDosyaEkle() {
        onizlemeDosyaEkleKontrol.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public BirimeIadeEdilenlerPage havaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Evrak Ekleri Evrak Önizleme Dosya Ekleme : \"{pathToFile}\" ")
    public BirimeIadeEdilenlerPage onizlemeHavaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(onizlemeDosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public BirimeIadeEdilenlerPage havaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Havale Evrak Önizleme dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public BirimeIadeEdilenlerPage onizlemeHavaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Teslim Al Gönder")
    public BirimeIadeEdilenlerPage teslimAlGonder() {
        teslimalgonder.click();
        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public BirimeIadeEdilenlerPage evrakOnizlemeKontrol() {
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Evrak Havale Kontrolu")
    public BirimeIadeEdilenlerPage evrakHavaleKontrol() {
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Havale Ekranı", "açılmıştır");
        return this;
    }

    @Step("Evrak Önizleme Ekranından Teslim Al ve Havale Yap")
    public BirimeIadeEdilenlerPage onizlemeTeslimAlveHavaleYap() {
        onizlemeTeslimAlveHavaleYap.click();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public BirimeIadeEdilenlerPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{kisi}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenOnizlemeKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeKisi.isDisplayed(), true, "Kisi Kontrolü");
        Allure.addAttachment("Kişi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kisi alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenKisiOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenKisiOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Dağıtım Bilgileri Kisi alanında \"{opsiyon}\" seçilir")
    public BirimeIadeEdilenlerPage havaleIslemleriOnizlemeKisiOpsiyonSec(String opsiyon) {
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        if (opsiyon.equals(gerek))
            eklenenKisiOnizlemeOpsiyon.selectOptionByValue("G");
        else if (opsiyon.equals(bilgi))
            eklenenKisiOnizlemeOpsiyon.selectOptionByValue("B");
        else if (opsiyon.equals(koordinasyon))
            eklenenKisiOnizlemeOpsiyon.selectOptionByValue("K");

        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public BirimeIadeEdilenlerPage havaleIslemleriKisiDetails(String kisi, String details) {
        txtHavaleIslemleriKisi.selectLov(kisi, details);
        return this;
    }

    @Step("Havale İşlemleri Kişi Listesi alanında \"{kisi}\" seç")
    public BirimeIadeEdilenlerPage havaleKisiListesi(String kisiliste) {
        txtHavaleIslemleriKisiListesi.openTreePanel();
        txtHavaleIslemleriKisiListesi.closeTreePanel();
        txtHavaleIslemleriKisiListesi.selectLov(kisiliste);
        return this;
    }

    @Step("Havale İşlemleri Kişi Listesi alanında eklenen \"{kisi}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenOnizlemeKisiListesiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeKisiListesi.isDisplayed(), true, "Kisi Listesi Kontrolü");
        Allure.addAttachment("Kişi Listesi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kisi Listesi alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenKisiListesiOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenKisiListesiOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public BirimeIadeEdilenlerPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Havale İşlemleri Onaylayan alanında eklenen \"{kisi}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenOnizlemeOnaylayanKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeOnaylayan.isDisplayed(), true, "Onaylayan Kontrolü");
        Allure.addAttachment("Onaylayan Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public BirimeIadeEdilenlerPage havaleAlanKontrolleri() {

        Assert.assertEquals(onaylayacakKisi.isDisplayed(), true, "Onaylayacak Kişi Alanı ");
        Allure.addAttachment("Onaylayacak Kisi Alanı Görüntülendi : ", "");

        Assert.assertEquals(birimKontrol.isDisplayed(),true,"Birim Alanı ");
        Allure.addAttachment("Birim Kontrol Alanı Görüntülendi : ","");

        Assert.assertEquals(kisiKontrol.isDisplayed(), true, "Kisi Alanı ");
        Allure.addAttachment("Kisi Alanı Görüntülendi : ", "");

        Assert.assertEquals(kullanıcıListeKontrol.isDisplayed(), true, "Kullanıcı Liste Alanı ");
        Allure.addAttachment("Kullanıcı Liste Alanı Görüntülendi : ", "");

        Assert.assertEquals(aciklamaKontrol.isDisplayed(), true, "Aciklama Alanı ");
        Allure.addAttachment("Aciklama Alanı Görüntülendi : ", "");

        Assert.assertEquals(dosyaEkleKontrol2.isDisplayed(), true, "Dosya Ekle Alanı ");
        Allure.addAttachment("Dosya Ekle Alanı Görüntülendi : ", "");

//        if(islemSureKontrol.isDisplayed()) {
//            text += "İslem Sure alanları gösterilmektedir.";
//        }
        takeScreenshot();
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public BirimeIadeEdilenlerPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
//        havaleIslemleriBirim.type(birim).getDetailItems()
//                .filterBy(Condition.exactText(details)).first().click();
//        havaleIslemleriBirim.closeTreePanel();
        havaleIslemleriBirim.selectLov(birim, details);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenOnizlemeBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenOnizlemeBirim.isDisplayed(), true, "Birim Kontrolü");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimeIadeEdilenlerPage eklenenBirimOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenBirimOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Birim Toplu Havale Alanında Teslim Al Gonder")
    public BirimeIadeEdilenlerPage birimTopluTeslimAlGonder() {
        birimTopluTeslimAlGonder.click();
        return this;
    }

    @Step("Birim Toplu Havale Alanında Teslim Al Gonder")
    public BirimeIadeEdilenlerPage birimTeslimAlGonder() {
        birimTeslimAlGonder.click();
        return this;
    }

    @Step("Evrak seç")
    public BirimeIadeEdilenlerPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Havale yap")
    public BirimeIadeEdilenlerPage havaleYap() {
        btnTeslimAlVeHavaleEt.click();
        return this;
    }

    @Step("Birime havale alanında \"{birim}\" seçilir")
    public BirimeIadeEdilenlerPage birimeHavaleDoldur(String birim) {
        cmbBirimeHavale.selectLov(birim);
        Allure.addAttachment("Birimin Sonuçlarda görüntülendiği görülür", "");
        return this;
    }

    @Step("Birime havale alanında girilen \"{description}\" 'ın görüntülenmeme kontrolu: {birim}")
    public BirimeIadeEdilenlerPage birimeHavaleAlanindaGoruntulenmemeKontrolu(String birim, String description) {

        boolean selectable = comboLov(cmbBirimeHavaleBy).isLovValueSelectable(birim);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür.");
        Allure.addAttachment("MyCombolov alanında " + birim + ": Birimin görüntülenmediği görülür.", "");
        return this;
    }
}
