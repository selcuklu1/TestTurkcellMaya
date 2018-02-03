package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MesajlarPage extends MainPage {

    ElementsCollection tableMesajlar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement lblKonu = $(By.xpath("//label[contains(text(),'Konu') and @class='columnLabelFixWidth']/../../td[3]"));
    SelenideElement lblTarih = $(By.xpath("//label[contains(text(),'Tarih') and @class='columnLabelFixWidth']/../../td[3]"));
    SelenideElement lblMesaj = $(By.xpath("//label[contains(text(),'Mesaj') and @class='columnLabelFixWidth']/../../td[3]"));

    @Step("Mesajlar sayfasını aç")
    public MesajlarPage openPage() {
        solMenu(SolMenuData.Bildirimler.Mesajlar);
        return this;
    }

    @Step("Mesaj Seç")
    public MesajlarPage mesajSec(String kimden, String konu, String mesaj) {

        tableMesajlar
                .filterBy(Condition.text(kimden))
                .filterBy(Condition.text(konu))
                .filterBy(Condition.text(mesaj))
                .first()
                .click();
        return this;
    }

    @Step("Mesaj kontrol")
    public MesajlarPage mesajKontrol(String konu, String tarih, String mesajAciklama) {

        lblKonu.shouldHave(Condition.text(konu));
        lblTarih.shouldHave(Condition.text(tarih));
        lblMesaj.shouldHave(Condition.text(mesajAciklama));

        return this;
    }

}

