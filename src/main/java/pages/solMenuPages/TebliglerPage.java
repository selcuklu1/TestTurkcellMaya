package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;
import sun.jvm.hotspot.utilities.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TebliglerPage extends MainPage {

    ElementsCollection tableTebligler = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnTebligHatirlatTab = $(By.xpath("//span[contains(@class, 'tebligHatirlat')]/.."));

    SelenideElement chkOkunmamisTebligleriHatirlat = $(By.id("mainPreviewForm:okunmamisTebligleriHatirlatCheckbox"));
    SelenideElement chkOkunmusTebellugEdilmemisTebligleriHatirlat = $(By.id("mainPreviewForm:tebellugEdilmemisTebligleriHatirlatCheckbox"));
    SelenideElement btnTebligHatirlat = $(By.id("mainPreviewForm:tebligHatirlatButton_id"));

    SelenideElement menuTebligler = $(By.xpath("//a[contains(., 'Tebliğler')]"));

    @Step("Tebliğler sayfasını aç")
    public TebliglerPage openPage(){
        solMenu(SolMenuData.Bildirimler.Tebligler);
        return this;
    }

    @Step("Evrak seç.")
    public TebliglerPage evrakSec(String konu, String birim, String tebligEden, String tebligAciklama) {

        tableTebligler
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Birim: " + birim))
                .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                .filterBy(Condition.text(tebligAciklama))
                .first()
                .click();

        return this;
    }

    @Step("Tebliğler menüsü kırmızı mı?")
    public TebliglerPage tebliglerMenuKirmiziKontrolu(){
        menuTebligler.shouldHave(Condition.cssClass("ui-menuitem-unread"));
        return this;
    }


    @Step("İçerik göster butonuna tıkla.")
    public TebliglerPage icerikGoster(String konu, String birim, String tebligEden, String evrakTipi, String aciklama){
        tableTebligler
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Birim: " + birim))
                .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                .filterBy(Condition.text(aciklama))
                .first()
                .find(By.xpath(".//span[contains(@class, 'document-detail')]"))
                .click();
        return this;
    }


}

