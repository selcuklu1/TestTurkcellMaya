package pages.pageComponents;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
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

    @Step("İşlem mesaj kontolü")
    public IslemMesajlari beklenenMesajTipi(MessageTitle messageTitleText) {
        messageTitle.shouldBe(visible);
        takeScreenshot();
        messageTitle.shouldHave(exactText(messageTitleText.value()));
//        Assert.assertEquals(getMessageTitle(), messageTitle.value());
        waitDisappear();
        return this;
    }

    @Step("İşlem mesaj kontolü")
    public IslemMesajlari beklenenMesaj(String message) {
        messageBody.shouldBe(visible);
        takeScreenshot();
        messageBody.shouldHave(text(message));
//        Assert.assertEquals(getMessageBody(), message);
        waitDisappear();
        return this;
    }

    @Step("Başarılı mesajı gelmeli")
    public void basariliOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), BASARILI.value());
        messageTitle.shouldHave(exactText(BASARILI.value()));
        if (expectedMessage.length > 0){
            String text;
            takeScreenshot();
            while (messageBody.text().isEmpty()){
                /*text = messageBody.text();
                if (!text.isEmpty())
                    break;*/
                sleep(100);
            }
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }

//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
        waitDisappear();
    }

    @Step("Uyarı mesajı gelmeli")
    public void uyariOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), UYARI.value());
        messageTitle.shouldHave(text((UYARI.value())));
        if (expectedMessage.length > 0){
            takeScreenshot();
            while (messageBody.text().isEmpty()){}
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }
//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
        waitDisappear();
    }

    @Step("Dikkat mesajı gelmeli")
    public void dikkatOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), DIKKAT.value());
        messageTitle.shouldHave(exactText(DIKKAT.value()));

        if (expectedMessage.length > 0){
            takeScreenshot();
            while (messageBody.text().isEmpty()){}
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }

//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
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
//        setDoNotWaitLoading(true);
        String text = $(".lobibox-notify-title").text();
//        setDoNotWaitLoading(false);
        return text;
    }

    public String getMessageBody() {
//        setDoNotWaitLoading(true);
        String text = $(".lobibox-notify-msg").text();
//        setDoNotWaitLoading(false);
        return text;
    }

    public void waitDisappear() {
        try {
            WebDriverRunner.getWebDriver().findElement(By.className("lobibox-close")).click();
        } catch (Exception ignored) {
        }

//        if (closeMessagePopup.exists())
//            closeMessagePopup.click();
    }

}



