package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageComponents.belgenetElements.Belgenet.$inFrame;
import static pages.pageComponents.belgenetElements.BelgentCondition.toolboxButtonOn;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.12.2017
 * Açıklama:
 */
public class TextEditor extends MainPage {

    private final SelenideElement container;

    public TextEditor() {
        this.container = $("html");
    }

    public TextEditor(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement editor() {
        SelenideElement frame = container.$(".cke_wysiwyg_frame");
        new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000, Configuration.pollingInterval)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

        SelenideElement editor = $(".cke_editable");
        return editor;
    }

    public String getText() {
        String text = editor().text();
        switchTo().defaultContent();
        return text;
    }

    @Step("Editörde kontrolleri yap")
    public TextEditor editorShouldHave(Condition... conditions) {
        SelenideElement editor = editor();
        for (Condition condition : conditions) {
            editor.shouldHave(condition);
        }
        switchTo().defaultContent();
        return this;
    }

    public String getInnerText() {
        String text = editor().innerText();
        switchTo().defaultContent();
        return text;
    }

    @Step("Editore tıkla")
    public TextEditor click() {
        SelenideElement editor = editor();
        editor.shouldBe(visible, enabled);
        editor.click();
        switchTo().defaultContent();
        return this;
    }

    @Step("Editore yaz")
    public TextEditor type(CharSequence... keysToSend) {
        SelenideElement editor = editor();
        editor.shouldBe(visible, enabled);
        editor.sendKeys(keysToSend);
        switchTo().defaultContent();
        return this;
    }

    @Step("\"{butonIsmi}\" toolbar butonun etkin durumu değiştir: \"{etkinDurumu}\" yap")
    public TextEditor toolbarButton(String butonIsmi, boolean etkinDurumu) {

        SelenideElement button = container.$x("descendant::a[span[contains(@class,'cke_button_label') and normalize-space(text())='" + butonIsmi + "']]");
        button.shouldBe(visible, enabled);

        if (button.is(toolboxButtonOn) != etkinDurumu)
            button.click();

        return this;
    }

    @Step("\"{name}\" toolbar alanda \"{value}\" seç")
    public TextEditor toolbarCombo(String name, String value) {
        SelenideElement combo = container.$x("descendant::span[span[contains(@class,'cke_combo_label') and normalize-space(text())='" + name + "']]/a/span[@class='cke_combo_open']");
        combo.shouldBe(visible, enabled);
        clickJs(combo);

        By iframeLocator = By.cssSelector("iframe[class='cke_panel_frame']");
        SelenideElement element = $inFrame(".cke_panel_block a[title='" + value + "']", iframeLocator);
        clickJs(element);
        switchTo().defaultContent();
        return this;
    }


/*
    private SelenideElement editor() {
//        By frame = By.cssSelector("div[id^='cke'][id$='contents'] iframe");
        By frame = By.className("cke_wysiwyg_frame");

//      $inFrame("body[class*='cke_contents_ltr']");
//        return $inFrame("body[class~='cke_contents_ltr']", frame);
//        return $inFrame(By.tagName("body"), frame);

        new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000, Configuration.pollingInterval)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

        SelenideElement editor = $(".cke_editable");
//                $inFrame(".cke_editable", frame);
        return editor;
//        return $inFrame("body[class='cke_editable cke_editable_themed cke_contents_ltr']", frame);
//        return $inFrame(".cke_contents_ltr", frame);
    }

    public String getText() {
        String text = editor().text();
        switchTo().defaultContent();
        return text;
    }

    @Step("Editör tekst kontrolü")
    public TextEditor editorShouldHave(Condition... condition) {
        SelenideElement editor = editor();
        for (Condition con : condition) {
            editor.shouldHave(con);
        }
        switchTo().defaultContent();
        return this;
    }

    public String getInnerText() {
        String text = editor().innerText();
        switchTo().defaultContent();
        return text;
    }

    @Step("Editore tıkla")
    public TextEditor click() {
//        Selenide.sleep(1000);
        SelenideElement editor = editor();
        editor.shouldBe(visible);
        editor.click();
////        WebDriverRunner.getWebDriver().findElement(By.cssSelector("body[class='cke_editable']")).sendKeys(keysToSend);
//        WebDriverRunner.getWebDriver().findElement(By.tagName("body")).sendKeys(keysToSend);
        switchTo().defaultContent();
//        WebDriverRunner.getWebDriver().switchTo().parentFrame();
        return this;
    }

    @Step("Editore tekst yaz")
    public TextEditor type(CharSequence... keysToSend) {
//        Selenide.sleep(1000);


        SelenideElement editor = editor();
        editor.shouldBe(visible);
        editor.sendKeys(keysToSend);
////        WebDriverRunner.getWebDriver().findElement(By.cssSelector("body[class='cke_editable']")).sendKeys(keysToSend);
//        WebDriverRunner.getWebDriver().findElement(By.tagName("body")).sendKeys(keysToSend);
        switchTo().defaultContent();
//        WebDriverRunner.getWebDriver().switchTo().parentFrame();
        return this;
    }

    @Step("\"{name}\" toolbar butonun etkinleştir \"{value}\" yap")
    public TextEditor toolbarButton(String name, boolean value) {
//        Selenide.sleep(2000);
        SelenideElement button = $$x("//a[span[contains(@class,'cke_button_label') and normalize-space(text())='" + name + "']]")
                .shouldHave(sizeGreaterThan(0))
                .filterBy(visible)
                .shouldHaveSize(1)
                .first()
                .shouldBe(visible)
                .shouldBe(enabled);
//        System.out.println(button.is(exist));
//        System.out.println(button.is(visible));
//        System.out.println(button.is(disabled));
//        System.out.println(button.is(enabled));

        *//*new WebDriverWait(WebDriverRunner.getWebDriver(),Configuration.timeout/1000, 500)
                .until(ExpectedConditions.elementToBeClickable(button));*//*
        button.shouldBe(visible);
        if (button.is(toolboxButtonOn) != value) {
//            Selenide.sleep(1000);
//            clickJs(button);
            button.click();
//            System.out.println("Clicked");
        }

        return this;
    }

    @Step("\"{name}\" toolbar alanda \"{value}\" seç")
    public TextEditor toolbarCombo(String name, String value) {
//        Selenide.sleep(1000);
        SelenideElement combo = $$x("//span[span[contains(@class,'cke_combo_label') and normalize-space(text())='" + name + "']]/a/span[@class='cke_combo_open']")
                .shouldHave(sizeGreaterThan(0))
                .filterBy(visible)
                .shouldHaveSize(1)
                .first()
                .shouldBe(visible)
                .shouldBe(enabled);

        clickJs(combo);
//        combo.shouldBe(visible).click();

        By iframeLocator = By.cssSelector("iframe[class='cke_panel_frame']");
        SelenideElement element = $inFrame(".cke_panel_block a[title='" + value + "']", iframeLocator);
//                .shouldBe(visible)
//        element.click();
        clickJs(element);



*//*        By iframeLocator = By.cssSelector("iframe[class='cke_panel_frame']");
        $(iframeLocator).shouldBe(visible);
        switchTo().frame(WebDriverRunner.getWebDriver().findElement(iframeLocator));
//        $inFrame(By.cssSelector(".cke_panel_block a[title='" + value + "']"), iframeLocator);//.click();

        $(By.cssSelector(".cke_panel_block a[title='" + value + "']")).click();*//*

        switchTo().defaultContent();
//        WebDriverRunner.getWebDriver().switchTo().parentFrame();
        return this;
    }

    @Step("")
    public TextEditor sablonlar() {

        return this;
    }*/

}
