package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TebellugEttiklerimPage extends MainPage {

    ElementsCollection tableTebellugEttiklerim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");


    @Step("Tebellüğ Ettiklerim sayfasını aç")
    public TebellugEttiklerimPage openPage(){
        solMenu(SolMenuData.IslemYaptiklarim.TebellugEttiklerim);
        return this;
    }

    @Step("Evrak seç.")
    public TebellugEttiklerimPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tableTebellugEttiklerim
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
    public TebellugEttiklerimPage icreikGoster(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tableTebellugEttiklerim
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text("No: " + no))
                .first()
                .find(By.xpath(".//span[contains(@class, 'document-detail')]"))
                .click();

        return this;
    }

}

