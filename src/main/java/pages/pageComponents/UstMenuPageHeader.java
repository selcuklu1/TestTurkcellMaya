package pages.pageComponents;

import com.codeborne.selenide.SelenideElement;
import pages.MainPage;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class UstMenuPageHeader extends MainPage {

    /*//SelenideElement pageWindow;
    @FindBy(xpath = "//ancestor::div[contains(@class,'windowDialog')]")
    public SelenideElement pageWindow;

    @FindBy(css = "div[class~='ui-dialog-titlebar'] a[class~='ui-dialog-titlebar-close']")
    public SelenideElement closePageButton;

    @FindBy(css = "div[class~='ui-dialog-titlebar'] > span[class='ui-dialog-title']")
    public SelenideElement pageTitle;*/
    //span[id^=window] > div[id^=window][id$=Dialog]
    //div[id^=window][id$=Dialog] a.ui-dialog-titlebar-close
    //div[id^=window][id$=Dialog]>div.ui-dialog-titlebar>a.ui-dialog-titlebar-close
    //div[id^=window][id$=Dialog]>div.ui-dialog-titlebar>.ui-dialog-title
    //div[starts-with(@id,'window') and contains(@id,'Dialog')]/div[contains(@class,'ui-dialog-titlebar')]//span[contains(@class,'ui-dialog-title')]
    //div[starts-with(@id,'window') and contains(@id,'Dialog') and div[contains(@class,'ui-dialog-titlebar') and span[text()='Evrak Oluştur']]]
    //div[starts-with(@id,'window') and div[contains(@class,'ui-dialog-titlebar') and span[text()='Evrak Oluştur']]]
    //div[starts-with(@id,'window') and div[span[text()='Evrak Oluştur']]]

    private SelenideElement container;

    public UstMenuPageHeader(SelenideElement container) {
        this.container = container;
    }

    /*public UstMenuPageHeader() {
        container = $("html");
    }*/

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    public SelenideElement getPageWindow() {
        return getContainer().$x("ancestor::div[starts-with(@id,'window') and contains(@class,'windowDialog')]");
    }

    public SelenideElement getPageCloseButton() {
        return getPageWindow().$("div[class~='ui-dialog-titlebar'] a[class~='ui-dialog-titlebar-close']");
    }

    public SelenideElement getPageTitle() {
        return getPageWindow().$("div[class~='ui-dialog-titlebar'] > span[class='ui-dialog-title']");
    }

    public void closePage() {
        //getPageCloseButton().click();
        clickJs(getPageCloseButton());
    }

    /*@Step("İmzala butonu ara")
    public SelenideElement imzalaButton() {
        return $x("//*[text()='İmzala']/ancestor::tbody[1]//button");
    }

    @Step("İmzala butona tıkla")
    public UstMenuPageHeader imzalaButonaTikla() {
        imzalaButton().click();
        return this;
    }

    @Step("s-İmzla radio butonu ara")
    public SelenideElement sImzalaRadio() {
        return $("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }

    @Step("s-İmzla seç")
    public UstMenuPageHeader sImzalaRadioSec() {
        sImzalaRadio().shouldBe(visible).click();
        return this;
    }

    @Step("İmzala")
    public void imzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
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
    }

    @Step("Parafla")
    public UstMenuPageHeader parafla() {
        $("button span[class~=parafla]").click();
        sImzalaRadioSec();
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
    }*/
}
