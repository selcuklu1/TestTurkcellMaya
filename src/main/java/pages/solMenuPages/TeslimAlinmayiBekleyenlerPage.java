package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class TeslimAlinmayiBekleyenlerPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));
    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlVeKapat = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    BelgenetElement txtKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement btnTeslimAlVeKapatTeslimAlVeKapat = $("[id='mainPreviewForm:evrakKapatFieldsetId'] button[id^='mainPreviewForm:j_']");

    public TeslimAlinmayiBekleyenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinmayiBekleyenler);
//        ustMenu("Teslim Alınmayı Bekleyenler");
        return this;
    }

    @Step("Kladırılıcak klasörler doldur")
    public TeslimAlinmayiBekleyenlerPage kaldirilacakKlasorlerDoldur(String kaldirilicakKlasor) {
        txtKaldirilacakKlasorler.selectLov(kaldirilicakKlasor);
        return this;
    }

    @Step("Konu kodu doldur")
    public TeslimAlinmayiBekleyenlerPage konuKoduDoldur(String konuKodu){
        txtKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlveKapatTeslimAlVeKapat(){
        btnTeslimAlVeKapatTeslimAlVeKapat.click();
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeKapat() {
        btnTeslimAlVeKapat.click();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Filtrele alanını aç")
    public TeslimAlinmayiBekleyenlerPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public TeslimAlinmayiBekleyenlerPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public TeslimAlinmayiBekleyenlerPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public TeslimAlinmayiBekleyenlerPage tarihiDoldur(String tarih) {
        dateTxtTarih.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinmayiBekleyenlerPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Telim Al ve Havale Yap butonana bas")
    public TeslimAlinmayiBekleyenlerPage havaleYap() {
        btnTeslimAlveHavaleYap.click();
        return this;
    }
    @Step("Tabloda evrak no kontrolü")
    public TeslimAlinmayiBekleyenlerPage tabloKontrolu(String evrakNo)
    {
        int row = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role=row] div[class=searchText]").filterBy(Condition.text(evrakNo)).size();
        System.out.println(row);
        Assert.assertEquals(row,1);
        //log başarılı
        return this;
    }

}
