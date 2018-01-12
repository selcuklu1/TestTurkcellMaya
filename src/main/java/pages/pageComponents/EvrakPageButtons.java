package pages.pageComponents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 11.01.2018
 * Açıklama:
 */
public class EvrakPageButtons extends MainPage {

    private SelenideElement container;

    public EvrakPageButtons() {
        container = $("html");
    }

    public EvrakPageButtons(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    @Step("İmzala butonu bul")
    public SelenideElement getImzalaButton() {
        return getContainer().$x("descendant::*[text()='İmzala']/ancestor::tbody[1]//button");
    }
    @Step("İmzala butona tıkla")
    public EvrakPageButtons imzalaButonaTikla() {
        getImzalaButton().click();
        return this;
    }
    @Step("s-İmzla radio butonu bul")
    public SelenideElement getSImzalaRadio() {
        return $("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }
    @Step("s-İmzla seç")
    public EvrakPageButtons sImzalaRadioSec() {
        getSImzalaRadio().shouldBe(visible).click();
        return this;
    }
    @Step("İmzala")
    public EvrakPageButtons evrakImzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
        evrakImzaOnay();
        return this;
    }
    @Step("İmza onayı ver")
    public EvrakPageButtons evrakImzaOnay() {
        for (int i = 0; i < Configuration.timeout / 1000; i++) {
            sleep(1000);
            if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)) {
                $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                break;
            } else {
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
        return this;
    }

    @Step("Parafla butonu bul")
    public SelenideElement getParaflaButton() {
        return getContainer().$x("descendant::*[text()='Parafla']/ancestor::tbody[1]//button");
    }
    @Step("Parafla butona tıkla")
    public EvrakPageButtons paraflaButonaTikla() {
        getParaflaButton().click();
        return this;
    }
    @Step("Parafla")
    public EvrakPageButtons evrakParafla() {
        paraflaButonaTikla();
        sImzalaRadioSec();
        evrakImzaOnay();
        return this;
    }

    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt(String iadeNotu) {
        getContainer().$("button .iadeEt").click();
        $("#inboxItemInfoForm\\:notTextArea_id").setValue("İade notu");
        $("#inboxItemInfoForm\\:iadeEtButton_id").click();
        return this;
    }

    @Step("Kaydet")
    public EvrakPageButtons evrakKaydet() {
        getContainer().$("button .kaydet").click();
        $("#kaydetConfirmForm\\:kaydetEvetButton").click();
        return this;
    }
}
