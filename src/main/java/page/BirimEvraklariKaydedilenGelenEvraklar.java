package page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariKaydedilenGelenEvraklar {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kaydettiklerimCheckbox"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));

    @Step("Filtrele alanını aç")
    public BirimEvraklariKaydedilenGelenEvraklar filtreleAc (){
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public BirimEvraklariKaydedilenGelenEvraklar filtreleSec (String value){
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public BirimEvraklariKaydedilenGelenEvraklar sayfadaAraDoldur (String value){
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public BirimEvraklariKaydedilenGelenEvraklar TarihDoldur (String tarih){
        dateTxtTarih.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public BirimEvraklariKaydedilenGelenEvraklar raporSec (){
        tblRapor.click();
        return this;
    }
}
