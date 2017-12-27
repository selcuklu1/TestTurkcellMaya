package pages.pageComponents;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class UstMenuPageHeader extends MainPage {

//    SelenideElement pageWindow;
    @FindBy(xpath = "//ancestor::div[contains(@class,'windowDialog')]")
    public SelenideElement pageWindow;

    @FindBy(css = "div[class~='ui-dialog-titlebar'] a[class~='ui-dialog-titlebar-close']")
    public SelenideElement closePageButton;

    @FindBy(css = "div[class~='ui-dialog-titlebar'] > span[class='ui-dialog-title']")
    public SelenideElement pageTitle;




    /*public SelenideElement getPageWindow() {
        return pageWindow;
    }*/

    public SelenideElement getPageCloseButton() {
        return closePageButton;
    }

    public SelenideElement getPageTitle() {
        return pageTitle;
    }

    public void close(){
        closePageButton.click();
    }

    @Step("İmzala butonu ara")
    public SelenideElement imzalaButton(){
        return $x("//*[text()='İmzala']/ancestor::tbody[1]//button");
    }

    @Step("İmzala butona tıkla")
    public UstMenuPageHeader imzalaButonaTikla(){
        imzalaButton().click();
        return this;
    }

    @Step("s-İmzla radio butonu ara")
    public SelenideElement sImzalaRadio(){
        return $("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }

    @Step("s-İmzla seç")
    public UstMenuPageHeader sImzalaRadioSec(){
        sImzalaRadio().shouldBe(visible).click();
        return this;
    }

    @Step("İmzala")
    public void imzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        for (int i = 0; i < Configuration.timeout/1000; i++) {
            sleep(1000);
            if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)){
                $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                break;
            }
            else{
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
    }

    @Step("Parafla")
    public UstMenuPageHeader parafla(){
        $("button span[class~=parafla]").click();
        sImzalaRadioSec();
        for (int i = 0; i < Configuration.timeout/1000; i++) {
            sleep(1000);
            if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)){
                $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                break;
            }
            else{
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
        return this;
    }
}
