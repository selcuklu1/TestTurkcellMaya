package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class IslemYaptiklarimHavaleEttiklerimPage extends BaseLibrary {

    //SelenideElement pageTitle = $(By.cssSelector("#window1Dialog .ui-dialog-title"));
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim  =$(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt3011_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnKurumDisindanGelenBelge = $(By.id("mainInboxForm:inboxDataTable:0:j_idt765"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));


    @Step("Filtrele alanını aç")
    public IslemYaptiklarimHavaleEttiklerimPage filtreleAc (){
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public IslemYaptiklarimHavaleEttiklerimPage filtreleSec (String value){
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public IslemYaptiklarimHavaleEttiklerimPage sayfadaAraDoldur (String value){
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public IslemYaptiklarimHavaleEttiklerimPage baslangicTarihiDoldur (String tarih){
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi doldur")
    public IslemYaptiklarimHavaleEttiklerimPage bitisTarihiDoldur (String tarih){
        dateTxtBitisTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public IslemYaptiklarimHavaleEttiklerimPage raporSec (){
        tblRapor.click();
        return this;
    }

    @Step("Havale yap butonana bas")
    public IslemYaptiklarimHavaleEttiklerimPage havaleYap(){
        btnHavaleYap.click();
        return this;
    }
}
