package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BirimEvraklariTeslimAlinanlarPage extends BaseLibrary {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));


    @Step("Filtrele alanını aç")
    public BirimEvraklariTeslimAlinanlarPage filtreleAc (){
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public BirimEvraklariTeslimAlinanlarPage filtreleSec (String value){
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public BirimEvraklariTeslimAlinanlarPage sayfadaAraDoldur (String value){
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public BirimEvraklariTeslimAlinanlarPage TarihiDoldur (String tarih){
        dateTxtTarih.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public BirimEvraklariTeslimAlinanlarPage raporSec (){
        tblRapor.click();
        return this;
    }

    @Step("Havale yap butonana bas")
    public BirimEvraklariTeslimAlinanlarPage havaleYap(){
        btnHavaleYap.click();
        return this;
    }
}