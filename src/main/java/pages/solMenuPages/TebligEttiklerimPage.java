package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class TebligEttiklerimPage extends MainPage {

    ElementsCollection tableTebligEttiklerim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnTebligHatirlatTab = $(By.xpath("//span[contains(@class, 'tebligHatirlat')]/.."));

    SelenideElement chkOkunmamisTebligleriHatirlat = $(By.id("mainPreviewForm:okunmamisTebligleriHatirlatCheckbox"));
    SelenideElement chkOkunmusTebellugEdilmemisTebligleriHatirlat = $(By.id("mainPreviewForm:tebellugEdilmemisTebligleriHatirlatCheckbox"));
    SelenideElement btnTebligHatirlat = $(By.id("mainPreviewForm:tebligHatirlatButton_id"));

    SelenideElement txtTebligHatirlatNot = $("div[id='mainPreviewForm:evrakOnizlemeTab'] textarea");


    @Step("Tebliğler sayfasını aç")
    public TebligEttiklerimPage openPage(){
        solMenu(SolMenuData.IslemYaptiklarim.TebligEttiklerim);
        return this;
    }

    @Step("Evrak seç.")
    public TebligEttiklerimPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tableTebligEttiklerim
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text("No: " + no))
                .first()
                .click();

        return this;
    }

    // document-detail

    @Step("İçerik göster butonuna tıkla.")
    public TebligEttiklerimPage icreikGoster(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tableTebligEttiklerim
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text("No: " + no))
                .first()
                .find(By.xpath(".//span[contains(@class, 'document-detail')]"))
                .click();

        return this;
    }

    @Step("Tebliğ Hatırlat butonuna tıkla.")
    public TebligEttiklerimPage tebligHatirlatTabTikla() {
        btnTebligHatirlatTab.click();
        return this;
    }

    @Step("Okunmamış Tebliğleri Hatırlat tik tıkla")
    public TebligEttiklerimPage okunmamisTebligleriHatirlat(boolean secim) {

        Boolean isSelected = false;
        if (chkOkunmamisTebligleriHatirlat.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if(secim == true){
            if(isSelected == false)
                chkOkunmamisTebligleriHatirlat.click();
        } else {
            if(isSelected == true)
                chkOkunmamisTebligleriHatirlat.click();
        }

        return this;
    }

    @Step("Okunmuş Tebellüğ Edilmemiş Tebliğleri Hatırlat")
    public TebligEttiklerimPage okunmusTebellugEdilmemisTebligleriHatirlat(boolean secim) {

        Boolean isSelected = false;
        if (chkOkunmusTebellugEdilmemisTebligleriHatirlat.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if(secim == true){
            if(isSelected == false)
                chkOkunmusTebellugEdilmemisTebligleriHatirlat.click();
        } else {
            if(isSelected == true)
                chkOkunmusTebellugEdilmemisTebligleriHatirlat.click();
        }

        return this;
    }


    @Step("Tebliğ Hatırlat Notu gir.")
    public TebligEttiklerimPage tebligHatirlatNotuGir(String not){
        txtTebligHatirlatNot.setValue(not);
        return this;
    }

    @Step("Tebliğ Hatırlat butonuna tıkla.")
    public TebligEttiklerimPage tebligHatirlat(){
        btnTebligHatirlat.click();
        return this;
    }

}

