package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;

public class Parafladiklarim extends MainPage {

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
    SelenideElement btnIconPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));
    SelenideElement txtKisi =$(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    SelenideElement btnIcerik = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");

    @Step("Parafladıklarım sayfası aç")
    public Parafladiklarim openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Parafladiklarim);
        return this;
    }

    @Step("Filtrele alanını aç")
    public Parafladiklarim filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public Parafladiklarim filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public Parafladiklarim sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public Parafladiklarim baslangicTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi doldur")
    public Parafladiklarim bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public Parafladiklarim raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Paylaş ikonuna  bas")
    public Parafladiklarim havaleYap() {
        btnIconPaylas.click();
        return this;
    }

    @Step("Kişi Seç")
    public Parafladiklarim kişiSec(String kisi) {
        txtKisi.sendKeys(kisi);
        return this;
    }

    @Step("Açıklama doldur")
    public Parafladiklarim aciklamaDoldur(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Paylaş butonuna bas")
    public Parafladiklarim paylas() {
        btnPaylas.click();
        return this;
    }
    @Step("İçerik ilk kayıt")
    public Parafladiklarim icerikIlkKayıt() {
        btnIcerik.click();
        return this;
    }
    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }
}
