package pages.solMenuPages;

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
    SelenideElement içeriktenEvrakTeslimAlHavaleEt = $("[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:5:cmdbutton']");
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement dosyaEkleKontrol = $(By.id("inboxItemInfoForm:fileUploadTeslimAlHavaleEk"));
    SelenideElement onizlemeDosyaEkleKontrol = $(By.id("mainPreviewForm:fileUploadTeslimAlHavaleEk"));

    SelenideElement dosyaPath = $(By.xpath("//input[@id='inboxItemInfoForm:fileUploadTeslimAlHavaleEk_input']"));
    SelenideElement onizlemeDosyaPath = $(By.xpath("//input[@id='mainPreviewForm:fileUploadTeslimAlHavaleEk_input']"));

    SelenideElement teslimalgonder = $(By.id("inboxItemInfoForm:btnTeslimAlGonder"));

    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));
    SelenideElement onizlemeTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));

    BelgenetElement txtHavaleIslemleriKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement cmbHavaleIslemleriOnaylayacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));

    ElementsCollection havaleOnayinaGonder = $$("[id^='mainPreviewForm:j_idt']");

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

    @Step("Teslim Al button kontrolü")
    public BirimIadeEdilenlerPage evrakTeslimAlButtonKontrol() {
        teslimAlButton.should(visible);
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

    @Step("Önizleme ekranından Teslim Al butonuna basılır ve Evrakı teslim almak istediğinize emin misiniz? uyarısı Evet ile onaylanır")
    public BirimIadeEdilenlerPage evrakOnizlemeTeslimAl()
    {
        onizlemeTeslimAl.click();
         $(By.id("teslimAlEvetButton")).click();
         return this;
    }

    @Step("Evrak Sec Checkbox ile ve Teslim Al")
    public BirimIadeEdilenlerPage evrakSecCheckBox(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAl.get(0).click();

        return this;
    }


    @Step("Birime Iade Edilenler sayfasında evrakın listede olmadığının kontrolu")
    public BirimIadeEdilenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Evrak no ile icerikten teslim al")
    public BirimIadeEdilenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
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
        if(evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
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

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public BirimIadeEdilenlerPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Evrak Önizleme Havale Onayına Gönder")
    public BirimIadeEdilenlerPage havaleOnayinaGonder() {
        havaleOnayinaGonder.filterBy(Condition.text("Havale Onayına Gönder")).last().click();
        return this;
    }

}
