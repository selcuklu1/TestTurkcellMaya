package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariTeslimAlinmayiBekleyenlerPage extends BaseLibrary {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));


    @Step("Filtrele alanını aç")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage filtreleAc (){
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage filtreleSec (String value){
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage sayfadaAraDoldur (String value){
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage TarihiDoldur (String tarih){
        dateTxtTarih.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage raporSec (){
        tblRapor.click();
        return this;
    }

    @Step("Telim Al ve Havale Yap butonana bas")
    public BirimEvraklariTeslimAlinmayiBekleyenlerPage havaleYap(){
        btnTeslimAlveHavaleYap.click();
        return this;
    }
}
