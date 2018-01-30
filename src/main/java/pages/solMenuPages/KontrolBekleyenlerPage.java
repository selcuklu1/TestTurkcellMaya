package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KontrolBekleyenlerPage extends MainPage {

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement bntKontrolEt = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td[class='buttonMenuContainerDefault'] span[class='ui-button-icon-left ui-icon kontrol']");
    SelenideElement txtKontrolEtNot = $("[id$='paraflaPanelGrid'] textarea");
    SelenideElement btnKontrolEtGonder = $("[id$='imzalaButton']");

    @Step("Kontrol bekleyenler sayfası aç")
    public KontrolBekleyenlerPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.KontrolBekleyenler);
        return this;
    }

    @Step("Evrak seç")
    public KontrolBekleyenlerPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi) {
        tableEvraklar
                .filterBy(text(konu))
                .filterBy(text(geldigiYer))
                .filterBy(text(kayitTarihiSayi))
                .get(0).click();
        return this;
    }

    @Step("Kontrol et not alanını \"{not}\" doldur")
    public KontrolBekleyenlerPage kontrolEtNotDoldur(String not) {
        txtKontrolEtNot.setValue(not);
        return this;
    }

    @Step("Gönder tıklanır")
    public KontrolBekleyenlerPage kontrolEtGonder() {
        btnKontrolEtGonder.click();
        return this;
    }

    @Step("Kontrol et")
    public KontrolBekleyenlerPage kontrolEt() {
        bntKontrolEt.parent().click();
        return this;
    }

}
