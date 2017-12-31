package pages.pageComponents;

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

    public ElementsCollection dialogs() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog'][id*=Confirm]");
    }

    @Step("Onay dialog başlığı")
    public SelenideElement dialogTitle() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog'][id*=Confirm] span.ui-dialog-title")
                .filterBy(visible).first();
    }

    @Step("Onay dialog messaji")
    public SelenideElement dialogMessage() {
        return $$("div[class~='ui-confirm-dialog'][class~='ui-dialog'][id*=Confirm] .ui-dialog-content p")
                .filterBy(visible).first();
    }

    public ElementsCollection buttons() {
        return $$x("//div[contains(@class,'ui-confirm-dialog') and contains(@id,'Confirm')]//button")
                .filterBy(visible);
    }

    @Step("Onay dialog butonu")
    public SelenideElement button(String name) {
        return $$x("//div[contains(@class,'ui-confirm-dialog') and contains(@id,'Confirm')]//button[span[text()='" + name + "']]")
                .filterBy(visible).first();
    }
}
