package pages.pageComponents;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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

    private By titleLocator = By.className("lobibox-notify-title");
    private By bodyLocator = By.className("lobibox-notify-msg");
    private By closeButtonLocator = By.className("lobibox-close");

    //http://www.belgenet.com.tr:8282/edys-web/mainInbox.xhtml
    // private SelenideElement messageTitle = $(".ui-growl-message  > .ui-growl-title");
    // private SelenideElement messageBody = $(".ui-growl-message p");


    ///////////////////////////////////////////////////////////////////////////
    // v.2.0
    ///////////////////////////////////////////////////////////////////////////

    public ElementsCollection getMessageTitles(){
        return $$(titleLocator);
    }

    public ElementsCollection getMessageBodies(){
        return $$(bodyLocator);
    }

    private void checkMessage(String messageType, String... expectedMessage){
        SelenideElement lastMessage = $$(titleLocator).shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
        takeScreenshot();
        lastMessage.shouldHave(exactText(messageType));

        if (expectedMessage.length > 0 && !expectedMessage[0].isEmpty()) {
            for (int i = 0; i < 500; i++) {
                lastMessage.shouldBe(exist, visible);
                if (!lastMessage.text().isEmpty()) {
                    lastMessage.shouldHave(text(expectedMessage[0]));
                    break;
                }
                sleep(10);
            }
        }
        closeMessage();
    }

    @Step("Başarılı mesajı gelmeli")
    public void basariliOlmali(String... expectedMessage) {
        checkMessage(BASARILI.value(), expectedMessage);
    }

    @Step("Uyarı mesajı gelmeli")
    public void uyariOlmali(String... expectedMessage) {
        checkMessage(UYARI.value(), expectedMessage);
    }

    @Step("Dikkat mesajı gelmeli")
    public void dikkatOlmali(String... expectedMessage) {
        checkMessage(DIKKAT.value(), expectedMessage);
    }

    @Step("İşlem mesaj kontolü")
    private IslemMesajlari beklenenMesajTipi(MessageTitle messageTitleText) {
        messageTitle.shouldBe(visible);
        takeScreenshot();
        messageTitle.shouldHave(exactText(messageTitleText.value()));
//        Assert.assertEquals(getMessageTitle(), messageTitle.value());
        closeMessage();
        return this;
    }

    @Step("İşlem mesaj kontolü")
    public void beklenenMesaj(String message) {
        SelenideElement lastMessage = $$(titleLocator).last().shouldBe(visible);
        takeScreenshot();
        lastMessage.waitUntil(text(message), 5000, 20);
        closeMessage();
    }

    public boolean isBasarili() {
        SelenideElement lastMessage = $$(titleLocator).last().shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(BASARILI.value()));
    }

    public boolean isUyari() {
        SelenideElement lastMessage = $$(titleLocator).last().shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(UYARI.value()));
    }

    public boolean isDikkat() {
        SelenideElement lastMessage = $$(titleLocator).last().shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(DIKKAT.value()));
    }

    public String getMessageTitle() {
//        setDoNotWaitLoading(true);
        String text = $(titleLocator).text();
//        setDoNotWaitLoading(false);
        return text;
    }

    public String getMessageBody() {
//        setDoNotWaitLoading(true);
        String text = $(bodyLocator).text();
//        setDoNotWaitLoading(false);
        return text;
    }

    public void closeMessage() {
        try {
            WebDriverRunner.getWebDriver().findElement(closeButtonLocator).click();
        } catch (Exception ignored) { }
    }

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




    ///////////////////////////////////////////////////////////////////////////
    // v.1.0
    ///////////////////////////////////////////////////////////////////////////
    /*@Step("İşlem mesaj kontolü")
    public IslemMesajlari beklenenMesajTipi(MessageTitle messageTitleText) {
        messageTitle.shouldBe(visible);
        takeScreenshot();
        messageTitle.shouldHave(exactText(messageTitleText.value()));
//        Assert.assertEquals(getMessageTitle(), messageTitle.value());
        closeMessage();
        return this;
    }

    @Step("İşlem mesaj kontolü")
    public IslemMesajlari beklenenMesaj(String message) {
        messageBody.shouldBe(visible);
        takeScreenshot();
        messageBody.shouldHave(text(message));
//        Assert.assertEquals(getMessageBody(), message);
        closeMessage();
        return this;
    }

    @Step("Başarılı mesajı gelmeli")
    public void basariliOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), BASARILI.value());
        takeScreenshot();
        messageTitle.shouldHave(exactText(BASARILI.value()));
        takeScreenshot();
        if (expectedMessage.length > 0) {
            while (messageBody.text().isEmpty()){}
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }

//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
        closeMessage();
    }

    @Step("Uyarı mesajı gelmeli")
    public void uyariOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), UYARI.value());
        takeScreenshot();
        messageTitle.shouldHave(text((UYARI.value())));
        takeScreenshot();
        if (expectedMessage.length > 0) {
            while (messageBody.text().isEmpty()){}
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }
//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
        closeMessage();
    }

    @Step("Dikkat mesajı gelmeli")
    public void dikkatOlmali(String... expectedMessage) {
//        Assert.assertEquals(getMessageTitle(), DIKKAT.value());
        takeScreenshot();
        messageTitle.shouldHave(exactText(DIKKAT.value()));
        takeScreenshot();
        if (expectedMessage.length > 0) {
            while (messageBody.text().isEmpty()){}
            takeScreenshot();
            messageBody.shouldHave(text(expectedMessage[0]));
        }

//            Assert.assertEquals(getMessageBody(), expectedMessage[0]);
        closeMessage();
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

    public void closeMessage() {
        try {
            WebDriverRunner.getWebDriver().findElement(By.className("lobibox-close")).click();
        } catch (Exception ignored) {
        }

//        if (closeMessagePopup.exists())
//            closeMessagePopup.click();
    }

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
*/
}



