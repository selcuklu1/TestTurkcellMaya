package pages.pageComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.IslemMesajlari.MessageTitle.*;

public class IslemMesajlari extends BaseLibrary {
    //yeni env objeleri
    //div class="lobibox-notify lobibox-notify-success animated-fast fadeInDown notify-mini"
    //                          lobibox-notify-warning
    //span lobibox-close
    //div class=lobibox-notify-title
    //div class=lobibox-notify-msg
    //div class=lobibox-close

    /*private SelenideElement messageTitle = $(".lobibox-notify-title");
    private SelenideElement messageBody = $(".lobibox-notify-msg");
    private SelenideElement closeMessagePopup = $(".lobibox-close");*/

    private By messageLocator = By.cssSelector(".lobibox-notify");
    private By bodyLocator = By.cssSelector(".lobibox-notify-body");
    private By titleLocator = By.cssSelector(".lobibox-notify-title");
    private By msgLocator = By.cssSelector(".lobibox-notify-msg");
    private By closeButtonLocator = By.cssSelector(".lobibox-close");
    List<String> messages;


    ///////////////////////////////////////////////////////////////////////////
    // v.2.0
    ///////////////////////////////////////////////////////////////////////////

    public ElementsCollection getMessageTitles() {
        return $$(titleLocator);
    }

    public ElementsCollection getMessageBodies() {
        return $$(msgLocator);
    }

    //    @Step("Messaj bulunmalı")
    private SelenideElement getMessageBody() {
        //return $$(messageLocator).shouldHave(sizeGreaterThan(0)).filterBy(visible).first();
        return $(messageLocator).shouldBe(visible);
    }

    private List<String> getMessageBodyList() {
        return $$(messageLocator).shouldHave(sizeGreaterThan(0)).filterBy(visible).texts();
    }

    //    @Step("Messaj başlığı kontrolü")
    private void checkTitle(SelenideElement element, String messageType) {
        String titleText = element.text();
        Assert.assertTrue(titleText.contains(messageType)
                , "\nAlınan mesaj başlığı : " + titleText + "\nBeklenen: " + messageType + "\n");
        System.out.println("İşlem Mesajı başlık: " + titleText);
    }

    //    @Step("Messaj teksti kontrolü")
    private void checkMessageText(SelenideElement element, String expectedMessage) {
        String text = element.text();
        for (int i = 0; i < 500; i++) {
            if (!text.isEmpty()) {
                System.out.println("İşlem Mesajı teksti: " + text);
                Assert.assertTrue(text.contains(expectedMessage)
                        , "\nAlınan mesaj: " + text + "\nBeklenen: " + expectedMessage + "\n");
                break;
            }
            sleep(10);
            text = element.text();
        }
    }

    private boolean checkMessageTextBoolean(SelenideElement element, String expectedMessage) {
        for (int i = 0; i < 500; i++) {
            if (!element.text().isEmpty())
                break;
            sleep(10);
        }
        return element.text().contains(expectedMessage);
    }

    @Step("Messaj kontrolü")
    private void checkMessage(String messageTitle, String... expectedMessage) {
        SelenideElement message = getMessageBody();
        System.out.println(message.text());
        takeScreenshot();
        checkTitle(message.$(titleLocator), messageTitle);
        if (expectedMessage.length > 0)
            checkMessageText(message.$(msgLocator), expectedMessage[0]);

        closeMessage();
    }

    @Step("Başarılı mesajı gelmeli")
    public IslemMesajlari basariliOlmali(String... expectedMessage) {
        checkMessage(BASARILI.value(), expectedMessage);
        return this;
    }

    @Step("Uyarı mesajı gelmeli")
    public IslemMesajlari uyariOlmali(String... expectedMessage) {
        checkMessage(UYARI.value(), expectedMessage);
        return this;
    }

    @Step("Dikkat mesajı gelmeli")
    public IslemMesajlari dikkatOlmali(String... expectedMessage) {
        checkMessage(DIKKAT.value(), expectedMessage);
        return this;
    }

    @Step("İşlem mesaj tipi kontolü")
    public void beklenenMesajTipi(MessageTitle messageTitleText) {
        checkTitle(getMessageBody().$(titleLocator), messageTitleText.value());
    }

    @Step("İşlem mesaj kontolü")
    public void beklenenMesaj(String message) {
        checkMessage(getMessageBody().$(titleLocator).text(), message);
    }

    public boolean isBasarili() {
        //Not working?! boolean b = getMessageBody().$(titleLocator).has(exactText(BASARILI.value()));
        //takeScreenshot();
        SelenideElement m = getMessageBody().shouldBe(visible);
        System.out.println("İşlem Mesajı teksti: " + m.text());
        return m.$(titleLocator).text().equals(BASARILI.value());
    }

    public boolean isBasarili(String... expectedMessage) {
        SelenideElement message = getMessageBody();

        System.out.println(message.text());
        takeScreenshot();

        if (expectedMessage.length == 0)
            return message.$(titleLocator).text().equals(BASARILI.value());
        else
            return message.$(titleLocator).text().equals(BASARILI.value())
                    && checkMessageTextBoolean(message.$(msgLocator), expectedMessage[0]);
    }

    public boolean isUyari() {
        /*boolean b = $(titleLocator).shouldBe(visible).has(exactText(UYARI.value()));
        takeScreenshot();*/
        SelenideElement m = getMessageBody().shouldBe(visible);
        System.out.println("İşlem Mesajı teksti: " + m.text());
        return m.$(titleLocator).text().equals(UYARI.value());
    }

    public boolean isUyari(String... expectedMessage) {
        SelenideElement message = getMessageBody();

        System.out.println(message.text());
        takeScreenshot();

        if (expectedMessage.length == 0)
            return message.$(titleLocator).text().equals(UYARI.value());
        else
            return message.$(titleLocator).text().equals(UYARI.value())
                    && checkMessageTextBoolean(message.$(msgLocator), expectedMessage[0]);
    }

    public boolean isDikkat() {
        /*boolean b = $(titleLocator).shouldBe(visible).has(exactText(DIKKAT.value()));
        takeScreenshot();*/
        SelenideElement m = getMessageBody().shouldBe(visible);
        System.out.println("İşlem Mesajı teksti: " + m.text());
        return m.$(titleLocator).text().equals(DIKKAT.value());
    }

    public boolean isDikkat(String... expectedMessage) {
        SelenideElement message = getMessageBody();

        System.out.println(message.text());
        takeScreenshot();

        if (expectedMessage.length == 0)
            return message.$(titleLocator).text().equals(DIKKAT.value());
        else
            return message.$(titleLocator).text().equals(DIKKAT.value())
                    && checkMessageTextBoolean(message.$(msgLocator), expectedMessage[0]);
    }


    public void closeMessage() {
        try {
            WebDriverRunner.getWebDriver().findElement(closeButtonLocator).click();
        } catch (Exception ignored) {
        }
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

        BASARILI("İşlem başarılıdır"),
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



