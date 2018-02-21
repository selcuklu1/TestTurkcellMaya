package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ImzaBekleyenlerPageKapatmaIslemleri extends MainPage {
    ElementsCollection tblImzaBekleyenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");// span[class='ui-chkbox-icon']");
    ElementsCollection tblImzaBekleyenler = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnKapatmaImzala = $x("//span[text()= 'Kapatma İmzala']/../../..//button");
    SelenideElement btnKapatmayiIptalEt = $(By.id("mainPreviewForm:kapatmayiIptalEtButton"));
    SelenideElement btnKapatmayiIptalEtEvet = $(By.id("mainPreviewForm:kapatmayiIptalEvetButton_id"));

    @Step("Kapatma işlemleri altında İmza bekleyenler sayfası aç")
    public ImzaBekleyenlerPageKapatmaIslemleri openPage() {
        solMenu2(SolMenuData.KapatmaIslemleri.ImzaBekleyenler);
        return this;
    }
    public ImzaBekleyenlerPageKapatmaIslemleri evrakSec(String toplantiNo, String konu) {
        int i = 0;
        while (i < 100) {
            sleep(i);
            i++;
        }
        tblImzaBekleyenEvraklar.
                filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu))
                .first().click();
        return this;
    }

    @Step("Evrak Seç")
    public ImzaBekleyenlerPageKapatmaIslemleri evrakSec(String konu, String gidecegiYer, String gonderen, String evrakNo) {
        tblImzaBekleyenler
                .filterBy(text(konu))
                .filterBy(text(gidecegiYer))
                .filterBy(text(gonderen))
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Seç")
    public ImzaBekleyenlerPageKapatmaIslemleri evrakSec(String konu, String gidecegiYer, String gonderen) {
        tblImzaBekleyenler
                .filterBy(text(konu))
                .filterBy(text(gidecegiYer))
                .filterBy(text(gonderen))
                .first()
                .click();
        return this;
    }

    @Step("{konu} konulu evrak evrak listesinde olmalı mı? : {evrakOlmali}.")
    public ImzaBekleyenlerPageKapatmaIslemleri evrakKontrol(String konu, boolean evrakOlmali){
        if(evrakOlmali == true){
            tblImzaBekleyenEvraklar
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldBe(visible);
        }
        else {
            tblImzaBekleyenEvraklar
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldNotBe(visible);
        }
        return this;
    }

    @Step("Kapatma İmzala butonuna tıkla")
    public ImzaBekleyenlerPageKapatmaIslemleri kapatmaImzala(){
        btnKapatmaImzala.click();
        return this;
    }

    @Step("Kapatmayı İptal Et butonuna tıkla")
    public ImzaBekleyenlerPageKapatmaIslemleri kapatmayIptalEt(){
        btnKapatmayiIptalEt.click();
        btnKapatmayiIptalEtEvet.click();
        return this;
    }
}
