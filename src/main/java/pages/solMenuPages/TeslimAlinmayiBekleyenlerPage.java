package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
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
    BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    BelgenetElement txtKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement btnTeslimAlVeKapatTeslimAlVeKapat = $("[id='mainPreviewForm:evrakOnizlemeTab'] div[class='form-buttons kapatButtonDirekt'] button");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));

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

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec() {
        tblIlkEvrak.click();
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

    @Step("Evrak önizleme evrak kontrolü : \"{pdfText}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakOnizlemeEklenenUstYaziKontrolu(String pdfText) {
        String text = "";
        switchTo().frame(1);
        text = $(By.xpath("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[4]")).getText();
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
    public TeslimAlinmayiBekleyenlerPage evrakSecTeslimAl(String konu, String yer, String tarih, String no, boolean secim){
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

    @Step("Telim Al ve Havale Yap butonana bas")
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

}
