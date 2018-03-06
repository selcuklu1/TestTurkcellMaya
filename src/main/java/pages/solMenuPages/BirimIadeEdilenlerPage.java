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
 * Class: "BirimIadeEdilenlerPage" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class BirimIadeEdilenlerPage extends MainPage {

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

    public BirimIadeEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimeIadeEdilenler);
        return this;
    }

    @Step("Evrak numara ile evrak seçilir : \"{evrakNo}\" ")
    public BirimIadeEdilenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Teslim Al button kontrolü ")
    public BirimIadeEdilenlerPage evrakTeslimAlButtonKontrol() {
        teslimAlButton.should(visible);
        takeScreenshot();
        return this;
    }

    @Step("Evrak no ile teslim al ve Evet seçeneğinin tıklanması ve Evrakı teslim almak istediğinize emin misiniz? uyarı kontrolü")
    public BirimIadeEdilenlerPage evrakSecNoTeslimAl(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak tıklanır ve listelendiği görülür {konu}")
    public BirimIadeEdilenlerPage evrakSec(String konu) {
        tblEvraklar.filterBy(text(konu)).get(0).click();
        return this;
    }

    @Step("Teslim Al ve Havale Et")
    public BirimIadeEdilenlerPage teslimAlVeHavaleEt() {
        $("[class='ui-button-icon-left ui-icon teslimAlHavale']").click();
        return this;
    }

    @Step("Onaylayacak Kişi doldur: {onaylanacakKisi} - {birim}")
    public BirimIadeEdilenlerPage onaylanacakKisiDoldur(String onaylanacakKisi, String birim) {
        txtOnaylanacakKisi.selectLov(onaylanacakKisi, birim);
        return this;
    }

    @Step("")
    public BirimIadeEdilenlerPage havaleOnayinaGonder() {
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }

    @Step("Önizleme ekranından Teslim Al butonuna basılır ve Evrakı teslim almak istediğinize emin misiniz? uyarısı Evet ile onaylanır")
    public BirimIadeEdilenlerPage evrakOnizlemeTeslimAl() {
        onizlemeTeslimAl.click();
        $(By.id("teslimAlEvetButton")).click();
        return this;
    }

    @Step("Onizleme ekranı açılır\n")
    public BirimIadeEdilenlerPage ekranOnizlemeKontrol() {
        Assert.assertEquals(tabHavale.isDisplayed(), true, "Onizleme sayfası");
        Allure.addAttachment("Onizleme sayfası", "açılmaktadır");
        return this;
    }

    @Step("Evrak Sec Toplu ve Teslim Al")
    public BirimIadeEdilenlerPage evrakSecToplu(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAl.get(0).click();

        return this;
    }

    @Step("Toplu Evrak Sec 3 tane ve Teslim Al Havale Et")
    public BirimIadeEdilenlerPage evrakSecToplu2(String konu1, String konu2, String konu3, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu3)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAlHavaleEt.get(0).click();

        return this;
    }

    @Step("Evrak Teslim Al Havale Et")
    public BirimIadeEdilenlerPage evrakTeslimAlHavaletEt() {
        teslimAlveHavaleEt.click();
        return this;
    }


    @Step("Birime Iade Edilenler sayfasında evrakın listede olmadığının kontrolu")
    public BirimIadeEdilenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false, "Evrak Bulunamamıştır");
        return this;
    }

    @Step("Birime Iade Edilenler sayfasında evrakın listede geldiği görünür")
    public BirimIadeEdilenlerPage evrakNoGeldigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, true, "Evrak Bulunamamıştır");
        return this;
    }

    @Step("Evrak no ile icerikten teslim al")
    public BirimIadeEdilenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak Detay ekranı açılır\n")
    public BirimIadeEdilenlerPage ekranIcerikKontrol() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

    @Step("Evrak Havale ekranı açılır\n")
    public BirimIadeEdilenlerPage ekranHavaleKontrol() {
        Assert.assertEquals(tabEvrakDetayi.isDisplayed(), true, "Evrak Detay sayfası");
        Allure.addAttachment("Evrak Detay sayfası", "açılmaktadır");
        return this;
    }

    @Step("İçerikten Evrak Teslim Al ve Havale Et")
    public BirimIadeEdilenlerPage içeriktenEvrakTeslimAlHavaleEt() {
        içeriktenEvrakTeslimAlHavaleEt.click();
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public BirimIadeEdilenlerPage dagitimBilgileriBirimDoldur2(String birim) {
        cmbHavaleIslemleriBirim.selectLov(birim);
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }
    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenIcerikBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenIcerikBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemlerinde Dosya Ekle")
    public BirimIadeEdilenlerPage dosyaEkle() {
        dosyaEkleKontrol.click();
        return this;
    }

    @Step("Havale İşlemlerinde Evrak Önizlemede Dosya Ekle")
    public BirimIadeEdilenlerPage onizlemeDosyaEkle() {
        onizlemeDosyaEkleKontrol.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public BirimIadeEdilenlerPage havaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Evrak Ekleri Evrak Önizleme Dosya Ekleme : \"{pathToFile}\" ")
    public BirimIadeEdilenlerPage onizlemeHavaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(onizlemeDosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public BirimIadeEdilenlerPage havaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Havale Evrak Önizleme dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public BirimIadeEdilenlerPage onizlemeHavaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Teslim Al Gönder")
    public BirimIadeEdilenlerPage teslimAlGonder() {
        teslimalgonder.click();
        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public BirimIadeEdilenlerPage evrakOnizlemeKontrol() {
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Evrak Havale Kontrolu")
    public BirimIadeEdilenlerPage evrakHavaleKontrol() {
        if (evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Havale Ekranı", "açılmıştır");
        return this;
    }

    @Step("Evrak Önizleme Ekranından Teslim Al ve Havale Yap")
    public BirimIadeEdilenlerPage onizlemeTeslimAlveHavaleYap() {
        onizlemeTeslimAlveHavaleYap.click();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public BirimIadeEdilenlerPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{kisi}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenOnizlemeKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeKisi.isDisplayed(), true, "Kisi Kontrolü");
        Allure.addAttachment("Kişi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kisi alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenKisiOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenKisiOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Dağıtım Bilgileri Kisi alanında \"{opsiyon}\" seçilir")
    public BirimIadeEdilenlerPage havaleIslemleriOnizlemeKisiOpsiyonSec(String opsiyon) {
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
    public BirimIadeEdilenlerPage havaleIslemleriKisiDetails(String kisi, String details) {
        txtHavaleIslemleriKisi.selectLov(kisi, details);
        return this;
    }

    @Step("Havale İşlemleri Kişi Listesi alanında \"{kisi}\" seç")
    public BirimIadeEdilenlerPage havaleKisiListesi(String kisiliste) {
        txtHavaleIslemleriKisiListesi.openTreePanel();
        txtHavaleIslemleriKisiListesi.closeTreePanel();
        txtHavaleIslemleriKisiListesi.selectLov(kisiliste);
        return this;
    }

    @Step("Havale İşlemleri Kişi Listesi alanında eklenen \"{kisi}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenOnizlemeKisiListesiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeKisiListesi.isDisplayed(), true, "Kisi Listesi Kontrolü");
        Allure.addAttachment("Kişi Listesi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kisi Listesi alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenKisiListesiOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenKisiListesiOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public BirimIadeEdilenlerPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Havale İşlemleri Onaylayan alanında eklenen \"{kisi}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenOnizlemeOnaylayanKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenOnizlemeOnaylayan.isDisplayed(), true, "Onaylayan Kontrolü");
        Allure.addAttachment("Onaylayan Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public BirimIadeEdilenlerPage havaleAlanKontrolleri() {

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
    public BirimIadeEdilenlerPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
//        havaleIslemleriBirim.type(birim).getDetailItems()
//                .filterBy(Condition.exactText(details)).first().click();
//        havaleIslemleriBirim.closeTreePanel();
        havaleIslemleriBirim.selectLov(birim, details);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenOnizlemeBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenOnizlemeBirim.isDisplayed(), true, "Birim Kontrolü");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{opsiyon}\" kontrolü")
    public BirimIadeEdilenlerPage eklenenBirimOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(eklenenBirimOnizlemeOpsiyon.getSelectedText().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Birim Toplu Havale Alanında Teslim Al Gonder")
    public BirimIadeEdilenlerPage birimTopluTeslimAlGonder() {
        birimTopluTeslimAlGonder.click();
        return this;
    }

    @Step("Birim Toplu Havale Alanında Teslim Al Gonder")
    public BirimIadeEdilenlerPage birimTeslimAlGonder() {
        birimTeslimAlGonder.click();
        return this;
    }


}
