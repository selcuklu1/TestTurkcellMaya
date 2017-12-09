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

public class TebliglerPage extends MainPage {

    ElementsCollection tableTebligler = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    SelenideElement menuTebligler = $(By.xpath("//a[contains(., 'Tebliğler')]"));

    SelenideElement btnTebligSil = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:inboxIslemlerToolbar']//span[contains(@class, 'document-messageDelete')]"));

    SelenideElement btnTebellugEt = $(By.xpath("//span[contains(@class, 'tebellugEt')]/.."));

    @Step("Tebliğler sayfasını aç")
    public TebliglerPage openPage() {
        solMenu(SolMenuData.Bildirimler.Tebligler);
        return this;
    }

    @Step("Evrak seç.")
    public TebliglerPage evrakSec(String konu, String birim, String tebligEden, String evrakTipi, String tebligAciklama) {

        tableTebligler
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Birim: " + birim))
                .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                .filterBy(Condition.text(tebligAciklama))
                .first()
                .click();

        return this;
    }

    @Step("Evrak tiki seç.")
    public TebliglerPage evrakTikSec(String konu, String birim, String tebligEden, String evrakTipi, String tebligAciklama, boolean secim) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableTebligler
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Birim: " + birim))
                .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                .filterBy(Condition.text(tebligAciklama))
                .first();

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (secim == true) {
            if (isSelected == false)
                currentRowCheckBox.click();
        } else {
            if (isSelected == true)
                currentRowCheckBox.click();
        }


        return this;
    }

    @Step("Tebliğler menüsü kırmızı mı?")
    public TebliglerPage tebliglerMenuKirmiziKontrolu() {
        menuTebligler.shouldHave(Condition.cssClass("ui-menuitem-unread"));
        return this;
    }


    @Step("İçerik göster butonuna tıkla.")
    public TebliglerPage icerikGoster(String konu, String birim, String tebligEden, String evrakTipi, String aciklama) {
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

    @Step("Sil butonuna tıkla.")
    public TebliglerPage tebligSil() {
        btnTebligSil.click();
        return this;
    }

    @Step("Tebliğ sil butonuna tıkla.")
    public TebliglerPage tebligSil(String konu, String birim, String tebligEden, String evrakTipi, String aciklama) {
        tableTebligler
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Birim: " + birim))
                .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                .filterBy(Condition.text(aciklama))
                .first()
                .find(By.xpath(".//span[contains(@class, 'delete-icon')]"))
                .click();
        return this;
    }

    SelenideElement btnTebellugEvet = $(By.id("mainInboxForm:tebellugEtEvetButton"));
    SelenideElement btnTebellugHayir = $(By.id("mainInboxForm:tebellugEtHayirButton"));

    @Step("Tebelliğ Et butonuna tıkla.")
    public TebliglerPage tebellugEt(boolean onay){
        btnTebellugEt.click();

        if(onay == true)
            btnTebellugEvet.click();
        else
            btnTebellugHayir.click();

        return this;
    }



    @Step("Evrak kontrol")
    public TebliglerPage evrakKontrol(String konu, String birim, String tebligEden, String evrakTipi, String aciklama, boolean shouldBeExist) {
        if(shouldBeExist == true){
            tableTebligler
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Birim: " + birim))
                    .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                    .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                    .filterBy(Condition.text(aciklama))
                    .first()
                    .shouldBe(Condition.visible);
        } else {

            tableTebligler
                    .filterBy(Condition.text("Konu: " + konu))
                    .filterBy(Condition.text("Birim: " + birim))
                    .filterBy(Condition.text("Tebliğ Eden: " + tebligEden))
                    .filterBy(Condition.text("Evrak Tipi: " + evrakTipi))
                    .filterBy(Condition.text(aciklama))
                    .first()
                    .shouldNotBe(Condition.visible);
        }
        return this;
    }


}

