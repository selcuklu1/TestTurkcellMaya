package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class TeslimAlinanlarPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));

    @Step("Teslim Alınanlar sayfası aç")
    public TeslimAlinanlarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinanlar);
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinanlarPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinanlarPage evrakSec(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no)).get(0).click();
        return this;
    }

    @Step("Filtrele alanını aç")
    public TeslimAlinanlarPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public TeslimAlinanlarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public TeslimAlinanlarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public TeslimAlinanlarPage tarihiDoldur(String tarih, Keys... keys) {
        dateTxtTarih.sendKeys(tarih);
        for (Keys k:keys) {
            dateTxtTarih.sendKeys(keys);
        }
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinanlarPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Kisi doldur")
    public TeslimAlinanlarPage havaleYapKisiDoldur(String kisi){
        txtHavaleYapKisi.selectLov(kisi);
        return this;
    }

    @Step("Kisi alanında \"{kisi}\" seçmeye dene")
    public TeslimAlinanlarPage havaleYapKisiSecmeyeDene(String kisi) {
        txtHavaleYapKisi.type(kisi).titleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public TeslimAlinanlarPage havaleYapKullaniciListesiDoldur(String kullaniciListesi){
        //txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesi seçmeye dene")
    public TeslimAlinanlarPage havaleYapKullaniciListesiSecmeyeDene(String kullaniciListesi) {
        txtHavaleYapKullaniciListesi.type(kullaniciListesi).titleItems().filterBy(text(kullaniciListesi)).first().click();
        return this;
    }

    @Step("Havale yap butonana bas")
    public TeslimAlinanlarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }
}
