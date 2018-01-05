package pages.pageComponents;

import com.codeborne.selenide.*;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
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

    private By messageLocator = By.cssSelector(".lobibox-notify");
    private By bodyLocator = By.cssSelector(".lobibox-notify-body");
    private By titleLocator = By.cssSelector(".lobibox-notify-title");
    private By msgLocator = By.cssSelector(".lobibox-notify-msg");
    private By closeButtonLocator = By.cssSelector(".lobibox-close");

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
        return $$(msgLocator);
    }

    @Step("Messaj bulunmalı")
    private SelenideElement getMessageBody() {
        SelenideElement body = $$(messageLocator).shouldHave(sizeGreaterThan(0)).filterBy(visible).last();
        takeScreenshot();
        return body;
    }

    @Step("Messaj başlığı kontrolü")
    private void checkTitle(SelenideElement messageBody, String messageType){
        String titleText = messageBody.$(titleLocator).text();
        System.out.println("İşlem Mesajı başlık: " + titleText);
        Assert.assertTrue(titleText.contains(messageType)
                , "\nAlınan mesaj başlığı : " + titleText + "\nBeklenen: " + messageType + "\n");
    }

    @Step("Messaj teksti kontrolü")
    private void checkMessage(SelenideElement messageBody, String expectedMessage){
        SelenideElement msg = messageBody.$(msgLocator);
        for (int i = 0; i < 500; i++) {
            msg.shouldBe(exist, visible);
            if (msg.is(not(empty))) {
                System.out.println("İşlem Mesajı teksti: " + msg.text());
                Assert.assertTrue(msg.text().contains(expectedMessage)
                        , "\nAlınan mesaj: " + msg.text() + "\nBeklenen: " + expectedMessage + "\n");
                break;
            }
            sleep(10);
        }
    }

//    @Step("İşlem mesajı kontrolü")
    private void checkMessage(String messageType, String... expectedMessage){
        SelenideElement message = getMessageBody();

        checkTitle(message, messageType);
        if (expectedMessage.length > 0)
            checkMessage(message, expectedMessage[0]);

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
    public void beklenenMesajTipi(MessageTitle messageTitleText) {
        checkTitle(getMessageBody(), messageTitleText.value());
    }

    @Step("İşlem mesaj kontolü")
    public void beklenenMesaj(String message) {
        checkMessage(getMessageBody(), message);
    }

    public boolean isBasarili() {
        SelenideElement lastMessage = getMessageBody().$(titleLocator).shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(BASARILI.value()));
    }

    public boolean isUyari() {
        SelenideElement lastMessage = getMessageBody().$(titleLocator).shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(UYARI.value()));
    }

    public boolean isDikkat() {
        SelenideElement lastMessage = getMessageBody().$(titleLocator).shouldBe(visible);
        takeScreenshot();
        return lastMessage.has(exactText(DIKKAT.value()));
    }

/*    public String getMessageTitle() {
//        setDoNotWaitLoading(true);
        String text = $(titleLocator).text();
//        setDoNotWaitLoading(false);
        return text;
    }

    public String getMessageBody() {
//        setDoNotWaitLoading(true);
        String text = $(msgLocator).text();
//        setDoNotWaitLoading(false);
        return text;
    }*/

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



