package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.*;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "BirimHavaleEdilenlerPage" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class BirimHavaleEdilenlerPage extends MainPage {


    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement txtSayfadaAra = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt389_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt394_input"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tblKaydedilenGelenEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");

    @Step("Birim Havale Edilenler sayfası aç")
    public BirimHavaleEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimHavaleEdilenler);
        return this;
    }

    @Step("Filtrele alanını aç")
    public BirimHavaleEdilenlerPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public BirimHavaleEdilenlerPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public BirimHavaleEdilenlerPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public BirimHavaleEdilenlerPage baslangiçTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi doldur")
    public BirimHavaleEdilenlerPage bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        dateTxtBitisTarihi.pressEnter();
        return this;
    }

    @Step("Tablodan rapor seç")
    public BirimHavaleEdilenlerPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public BirimHavaleEdilenlerPage evrakNoIleTabloKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Tabloda evrak no ile evrak seçme. \"{evrakNo}\" ")
    public BirimHavaleEdilenlerPage evrakNoIleTablodanEvrakSecme(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak önizleme evrak kontrolü : \"{pdfText}\" ")
    public BirimHavaleEdilenlerPage evrakOnizlemeEklenenUstYaziKontrolu(String pdfText) {
        String text="";
        switchTo().frame(1);
        text =  $(By.xpath("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[4]")).getText();
        text.equals(pdfText);
        switchTo().parentFrame();
        return this;
    }


}
