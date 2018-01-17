package pages.pageComponents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
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

    private SelenideElement getButton(String text){
        return getContainer().$x("descendant::*[text()='" + text + "']/ancestor::tbody[1]//button");
    }

    @Step("s-İmzla radio butonu bul")
    public SelenideElement getSImzalaRadio() {
        return getImzalaForm().$("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }

    @Step("s-İmzla seç")
    public EvrakPageButtons sImzalaRadioSec() {
        getSImzalaRadio().shouldBe(visible).click();
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
            } else if ($("#imzalaForm\\:imzalaButton").is(visible)) {
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
        return this;
    }

    //region İmzala
    @Step("İmzala butonu bul")
    public SelenideElement getImzalaButton() {
        return getButton("İmzala");
    }

    public SelenideElement getImzalaForm(){
        return $("#imzalaForm");
    }

    @Step("İmzala butona tıkla")
    public EvrakPageButtons imzalaButonaTikla() {
        getImzalaButton().click();
        return this;
    }

    @Step("İmzala")
    public EvrakPageButtons evrakImzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
        evrakImzaOnay();
        return this;
    }
    //endregion

    //region Parafla
    @Step("Parafla butonu bul")
    public SelenideElement getParaflaButton() {
        return getButton("Parafla");
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
    //endregion

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

    //region Kaydet ve Onaya Sun
    @Step("Kaydet ve Onaya Sun butonu bul")
    public SelenideElement getEvrakKaydetVeOnayaSun(){
        return getButton("Kaydet ve Onaya Sun'");
    }

    @Step("Kaydet ve Onaya Sun")
    public EvrakPageButtons evrakKaydetVeOnayaSunTikla(){
        getEvrakKaydetVeOnayaSun().click();
        return this;
    }
    //endregion

    //region Gönder
    @Step("Gönder butonu bul")
    public SelenideElement getGonder(){
        return getContainer().$("button[id$='gonderButton']");
    }

    @Step("Gönder butona tıkla")
    public EvrakPageButtons gonderTikla(){
        getGonder().click();
        return this;
    }
    //endregion

    //region Postala
    @Step("Postala butonu bul")
    public SelenideElement getEvrakPostala(){
        return getButton("Parafla");
    }

    @Step("Postala butona tıkla")
    public EvrakPageButtons postalaTikla(){
        getEvrakPostala().click();
        return this;
    }

    @Step("Postala")
    private EvrakPageButtons evrakPostala(){
        postalaTikla();
        getContainer().$x("descendant::button[.='Postala']").click();
        confirmDialog().confirmEvetTikla();
        return this;
    }
    //endregion


    @Step("PDF Önizleme butonu bul")
    public SelenideElement getPdfOnizleme(){
        return getButton("PDF Önizleme");
    }

    @Step("PDF Önizleme butonu tikla")
    public EvrakPageButtons pdfOnizlemeTikla(){
        getPdfOnizleme().click();
        return this;
    }

}
