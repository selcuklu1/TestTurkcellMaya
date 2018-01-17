package pages.pageComponents;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 24.12.2017
 * Açıklama:
 */
public class ConfirmDialog extends BaseLibrary {

    //"//div[contains(@class,'ui-confirm-dialog') and contains(@id,'Confirm')]"
    //$$("div[class~='ui-confirm-dialog'][class~='ui-dialog'][id*=Confirm]")

    public ElementsCollection dialogs() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog']");
    }

    @Step("Onay dialog başlığı")
    public SelenideElement dialogTitle() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog'] span.ui-dialog-title")
                .filterBy(visible).first();
    }

    @Step("Onay dialog messaji")
    public SelenideElement dialogMessage() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog'] .ui-dialog-content p")
                .filterBy(visible).first();
    }

    public ElementsCollection getConfirmButtons() {
        return $$x("//div[contains(@class,'ui-confirm-dialog')]//button")
                .filterBy(visible);
    }

    @Step("Onay dialog butonu")
    public SelenideElement getConfirmButton(String name) {
        return $$x("//div[contains(@class,'ui-confirm-dialog')]//button[span[text()='" + name + "']]")
                .filterBy(visible).shouldHave(CollectionCondition.sizeGreaterThan(0)).last();
    }

    @Step("Evet butona tikla")
    public ConfirmDialog confirmEvetTikla(){
        getConfirmButton("Evet").shouldBe(visible).click();
        return this;
    }

    @Step("Hayır butona tikla")
    public ConfirmDialog confirmHayirTikla(){
        getConfirmButton("Hayır").shouldBe(visible).click();
        return this;
    }


}
