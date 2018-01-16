package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KaydedilenGidenEvraklarPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement ekran = $("div[id='mainInboxForm:inboxDataTable'] label");
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt672_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt2855_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $("[id$='kaydettiklerimCheckbox']");
    SelenideElement btnIcerikGöster = $("[id$='detayGosterButton']");
    SelenideElement btnTamEkranGöster = $("[id$='tamEkranModuButton']");
    ElementsCollection tblRapor = $$("tbody[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");


    @Step("Kaydedilen giden evraklar sayfası aç")
    public KaydedilenGidenEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KaydedilenGidenEvraklar);
        return this;
    }

    @Step("Filtrele alanını aç")
    public KaydedilenGidenEvraklarPage filtreleAc() {
        if (ekran.isDisplayed())
            f.click();
        return this;
    }

    @Step("Filtere seç")
    public KaydedilenGidenEvraklarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public KaydedilenGidenEvraklarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public KaydedilenGidenEvraklarPage tarihDoldur(String tarih) {
        dateTxtTarih.sendKeys(tarih);
        dateTxtTarih.pressEnter();
        return this;
    }

    @Step("Tabloda evrak no kontrolu : \"{evrakNo}\" ")
    public KaydedilenGidenEvraklarPage tabloKontrolu(String evrakNo) {
        int row = tblRapor.filterBy(Condition.text(evrakNo)).size();
        System.out.println(row);
        Assert.assertEquals(row, 1);
        return this;
    }

    @Step("Tabloda evrak no kontrolu : \"{konu}\" ")
    public KaydedilenGidenEvraklarPage tabloKontrolKonuyaGore(String konu) {
        tblRapor.filterBy(Condition.text(konu))
                .shouldHaveSize(1);
        return this;
    }


}
