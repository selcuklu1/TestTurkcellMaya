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

import static com.codeborne.selenide.Condition.*;
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
        switchTo().frame(container.$(".cke_wysiwyg_frame"));
        /*new WebDriverWait(WebDriverRunner.getWebDriver(), Configuration.timeout / 1000, Configuration.pollingInterval)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));*/
        //SelenideElement editor = $(".cke_editable");
        return $(".cke_editable");
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
        //sleep(3000);
        SelenideElement editor = editor();
        editor.shouldHave(attribute("contenteditable", "true"));
        editor.shouldBe(visible, enabled);

        //container.$$(".cke_button").filterBy()

        editor.sendKeys(keysToSend);
        switchTo().defaultContent();
        return this;
    }

    @Step("Editore içerik silinir.")
    public TextEditor clear() {
        //sleep(3000);
        SelenideElement editor = editor();
        editor.shouldHave(attribute("contenteditable", "true"));
        editor.shouldBe(visible, enabled);
        editor.clear();
        switchTo().defaultContent();
        return this;
    }

    @Step("\"{butonIsmi}\" toolbar butonun etkin durumu değiştir: \"{etkinDurumu}\" yap")
    public TextEditor toolbarButton(String butonIsmi, boolean etkinDurumu) {
        //class="cke_button_disabled" aria-disabled="true"
        SelenideElement button = container.$x("descendant::a[span[contains(@class,'cke_button_label') and normalize-space(text())='" + butonIsmi + "']]");
        button.shouldBe(visible, enabled);
        button.shouldNotHave(cssClass("cke_button_disabled"));
        if (button.is(toolboxButtonOn) != etkinDurumu)
            button.click();

        return this;
    }

    @Step("\"{name}\" toolbar alanda \"{value}\" seç")
    public TextEditor toolbarCombo(String name, String value) {
        SelenideElement combo = container
                .$x("descendant::span[span[contains(@class,'cke_combo_label') and normalize-space(text())='" + name + "']]")
                .shouldNotHave(cssClass("cke_combo_disabled"));

        clickJs(combo.$("a span.cke_combo_open").shouldBe(visible, enabled));

        By iframeLocator = By.cssSelector("iframe[class='cke_panel_frame']");
        SelenideElement element = $inFrame(".cke_panel_block a[title='" + value + "']", iframeLocator);
        clickJs(element);
        switchTo().defaultContent();
        return this;
    }
}
