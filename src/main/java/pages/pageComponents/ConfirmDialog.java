package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 24.12.2017
 * Açıklama:
 */
public class ConfirmDialog extends MainPage {

    //"//div[contains(@class,'ui-confirm-dialog') and contains(@id,'Confirm')]"
    //$$("div[class~='ui-confirm-dialog'][class~='ui-dialog'][id*=Confirm]")

    String dialogsLocator = "div.ui-confirm-dialog.ui-dialog,div.ui-dialog.confirmDialog";
    //By dialogsLocator = By.cssSelector("div.ui-dialog");

    private ElementsCollection dialogs() {
        //return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog']");
        return $$(dialogsLocator);
    }

    public SelenideElement getDialog() {
        return dialogs().filterBy(visible).first();
    }

    @Step("Onay dialog başlığı")
    public SelenideElement dialogTitle() {
        return $$(dialogsLocator + " span.ui-dialog-title")
                .filterBy(visible).first();
    }

    @Step("Onay dialog messaji")
    public SelenideElement dialogMessage() {
        return $$(dialogsLocator + " .ui-dialog-content p")
                .filterBy(visible).first();
    }

    public ElementsCollection getConfirmButtons() {
        return $$x("//div[contains(@class,'ui-confirm-dialog')]//button")
                .filterBy(visible);
    }

    @Step("Onay dialog butonu")
    public SelenideElement getConfirmButton(String name) {
        /*return $$x("//div[contains(@class,'ui-confirm-dialog')]//button[span[text()='" + name + "']]")
                .filterBy(visible).shouldHave(CollectionCondition.sizeGreaterThan(0)).last();*/
        return getDialog().$x("descendant::button[span[text()='" + name + "']]");
    }

    @Step("Evet butona tikla")
    public ConfirmDialog confirmEvetTikla() {
        getConfirmButton("Evet").shouldBe(visible).pressEnter();
        getConfirmButton("Evet").should(disappear);
        return this;
    }

    @Step("Hayır butona tikla")
    public ConfirmDialog confirmHayirTikla() {
        getConfirmButton("Hayır").shouldBe(visible).pressEnter();
        getConfirmButton("Hayır").should(disappear);
        return this;
    }

    @Step("Onay dialog butonu")
    public ConfirmDialog buttonClick(String name) {
        getDialog().$x("descendant::button[span[text()='" + name + "']]").click();
        /*$$x("//div[contains(@class,'ui-confirm-dialog')]//button[span[text()='" + name + "']]")
                .filterBy(visible).shouldHave(CollectionCondition.sizeGreaterThan(0)).last().click();*/
        return this;
    }

    @Step("Onya mesaj kontrol edilir")
    public ConfirmDialog onayMesajKontrolu(Condition... conditions) {
        for (Condition condition : conditions) {
            getDialog().shouldHave(condition);
        }
        return this;
    }

}
