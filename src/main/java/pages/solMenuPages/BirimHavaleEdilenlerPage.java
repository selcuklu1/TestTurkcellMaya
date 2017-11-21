package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "BirimHavaleEdilenlerPage" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class BirimHavaleEdilenlerPage extends MainPage {


    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement txtSayfadaAra = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt389_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt394_input"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement tblKaydedilenGelenEvraklar =$(By.id("mainInboxForm:inboxDataTable_data"));

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
    @Step("Tabloda evrak no kontrolü")
    public BirimHavaleEdilenlerPage tabloKontrolu(String evrakNo)
    {
        int row = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role=row] div[class=searchText]").filterBy(Condition.text(evrakNo)).size();
        System.out.println(row);
        Assert.assertEquals(row,1);
        //log başarılı
        return  this;
    }
}
