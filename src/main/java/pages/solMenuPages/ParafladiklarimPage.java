package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageData.SolMenuData.IslemYaptiklarim;

public class ParafladiklarimPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt3011_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtBaslangicTarihi = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] tr:nth-child(2) td:nth-child(4) tr input");
    SelenideElement dateTxtBitisTarihi = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] tr:nth-child(3) td:nth-child(2) tr input");
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement btnGidecegiYerTree = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    SelenideElement btnKurumDisindanGelenBelge = $(By.id(""));
    SelenideElement btnIcerikGöster = $(By.id(""));
    SelenideElement btnTamEkranGöster = $(By.id(""));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id(""));
    SelenideElement btnIconPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    SelenideElement btnIcerik = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));

    @Step("Parafladıklarım sayfası aç")
    public ParafladiklarimPage openPage() {
        solMenu(IslemYaptiklarim.Parafladiklarim);
        String pageTitle = IslemYaptiklarim.Parafladiklarim.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Açıklama doldur")
    public ParafladiklarimPage paylasAciklamaDoldur(String aciklama) {
        txtPaylasAciklama.setValue(aciklama);
        return this;
    }

    @Step("Kişi doldur")
    public ParafladiklarimPage paylasKisiDoldur(String kisi) {
        txtPaylasKisi.selectLov(kisi);
        return this;
    }

    @Step("Paylaş")
    public ParafladiklarimPage paylasPaylas() {
        btnPaylasPaylas.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafladiklarimPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Filtrele alanını aç")
    public ParafladiklarimPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public ParafladiklarimPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public ParafladiklarimPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public ParafladiklarimPage baslangicTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi alanına \"{tarih}\" doldur")
    public ParafladiklarimPage bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafladiklarimPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tablodan konuya göre rapor seç")
    public ParafladiklarimPage konuyaGoreRaporSec(String konu) {
        $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']")
                .filterBy(Condition.text(konu)).first()
                .$("[id$='detayGosterButton']");
        return this;
    }

    @Step("Paylaş ikonuna  bas")
    public ParafladiklarimPage havaleYap() {
        btnIconPaylas.click();
        return this;
    }

    @Step("Kişi Seç")
    public ParafladiklarimPage kişiSec(String kisi) {
        txtKisi.sendKeys(kisi);
        return this;
    }

    @Step("Açıklama doldur")
    public ParafladiklarimPage aciklamaDoldur(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Paylaş butonuna bas")
    public ParafladiklarimPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("İçerik ilk kayıt")
    public ParafladiklarimPage icerikIlkKayıt() {
        btnIcerik.click();
        return this;
    }

    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }
}
