package pages.pageComponents;

import com.codeborne.selenide.*;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.Wait;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static pages.pageComponents.IslemMesajlari.MessageTitle.*;

public class IslemMesajlari extends BaseLibrary {

    //WebDriverRunner.getWebDriver() = driver
    //yeni env objeleri
    //div class="lobibox-notify lobibox-notify-success animated-fast fadeInDown notify-mini"
    //                          lobibox-notify-warning
    //span lobibox-close
    //div class=lobibox-notify-title
    //div class=lobibox-notify-msg
    //div class=lobibox-close

    //http://94.55.114.18:8889/edys-web/mainInbox.xhtml
    private SelenideElement messageTitle = $(".lobibox-notify-title");
    private SelenideElement messageBody = $(".lobibox-notify-msg");
    private SelenideElement closeMessagePopup = $(".lobibox-close");

    //http://www.belgenet.com.tr:8282/edys-web/mainInbox.xhtml
    // private SelenideElement messageTitle = $(".ui-growl-message  > .ui-growl-title");
    // private SelenideElement messageBody = $(".ui-growl-message p");

    public enum MessageTitle {

        BASARILI("İşlem Başarılıdır"),
        UYARI("Uyarı"),
        DIKKAT("Dikkat");

        private String value;

        MessageTitle(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public enum MessageBody {

        BASARILI("İşlem Başarılıdır"),
        UYARI("Uyarı"),
        DIKKAT("Dikkat");

        private String value;

        MessageBody(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    @Step("Beklenen mesaj tipi \"{messageTitle.value()}\"")
    public IslemMesajlari beklenenMesajTipi(MessageTitle messageTitle) {
        Assert.assertEquals(getMessageTitle(), messageTitle.value());
        return this;
    }

    @Step("Beklenen mesaj \"{messageBody.value()}\"")
    public IslemMesajlari beklenenMesaj(String message) {
        Assert.assertEquals(getMessageBody(), message);
        return this;
    }

    @Step("Başarılı mesajı gelmeli")
    public void basariliOlmali(String actualMessage) {
        Assert.assertEquals(getMessageTitle(), BASARILI.value());
        Assert.assertEquals(actualMessage, getMessageBody());
        System.out.println("Gelen Başarı Mesajı: " + getMessageBody());
        waitDisappear();
    }

    @Step("Uyarı mesajı gelmeli")
    public void uyariOlmali(String actualMessage) {
        Assert.assertEquals(getMessageTitle(), UYARI.value());
        Assert.assertEquals(actualMessage, getMessageBody());
        System.out.println("Gelen Uyarı Mesajı: " + getMessageBody());
        waitDisappear();
    }

    @Step("Dikkat mesajı gelmeli")
    public void dikkatOlmali(String actualMessage) {
        Assert.assertEquals(getMessageTitle(), DIKKAT.value());
        Assert.assertEquals(actualMessage, getMessageBody());
        System.out.println("Gelen Dikkat Mesajı: " + getMessageBody());
        waitDisappear();
    }

    public boolean isBasarili() {
        return getMessageTitle().equalsIgnoreCase(BASARILI.value());
    }

    public boolean isUyari() {
        return getMessageTitle().equalsIgnoreCase(UYARI.value());
    }

    public boolean isDikkat() {
        return getMessageTitle().equalsIgnoreCase(DIKKAT.value());
    }

    public String getMessageTitle() {
        return messageTitle.should(visible).text();
    }

    public String getMessageBody() {
        messageBody.waitUntil(visible, Configuration.timeout, 100);
        return messageBody.text();
    }

    public void waitDisappear() {

        closeMessagePopup.click();
    }

}



