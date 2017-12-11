package pages.pageComponents;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.$inFrame;
import static pages.pageComponents.belgenetElements.BelgentCondition.toolboxButtonOn;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.12.2017
 * Açıklama:
 */
public class TextEditor extends ElementsContainer {

    /*private SelenideElement editor;

    private void setEditor() {
        By frame = By.cssSelector("div[id^='cke'][id$='contents'] iframe");

        this.editor = $inFrame("body[class*='cke_contents_ltr']", frame);
//      $inFrame("body[class*='cke_contents_ltr']");
    }

    public TextEditor editor() {
        setEditor();
        return this;
    }*/

    private SelenideElement editor() {
//        By frame = By.cssSelector("div[id^='cke'][id$='contents'] iframe");
        By frame = By.className("cke_wysiwyg_frame");

//      $inFrame("body[class*='cke_contents_ltr']");
//        return $inFrame("body[class~='cke_contents_ltr']", frame);
//        return $inFrame(By.tagName("body"), frame);
//        return $inFrame(".cke_editable", frame);
        return $inFrame(".cke_editable", frame);
//        return $inFrame(".cke_contents_ltr", frame);
    }

    public String getText() {
        String text = editor().text();
        switchTo().defaultContent();
        return text;
    }

    public String getInnerText() {
        String text = editor().innerText();
        switchTo().defaultContent();
        return text;
    }

    @Step("Editore tekst yaz")
    public TextEditor type(CharSequence... keysToSend) {
        editor().sendKeys(keysToSend);
////        WebDriverRunner.getWebDriver().findElement(By.cssSelector("body[class='cke_editable']")).sendKeys(keysToSend);
//        WebDriverRunner.getWebDriver().findElement(By.tagName("body")).sendKeys(keysToSend);
        switchTo().defaultContent();
//        WebDriverRunner.getWebDriver().switchTo().parentFrame();
        return this;
    }

    @Step("\"{name}\" toolbar butonun etkin durumu \"{value}\" yap")
    public TextEditor toolbarButton(String name, boolean value) {
        SelenideElement button =
                $$x("//a/span[contains(@class,'cke_button_label') and normalize-space(text())='" + name + "']/..")
                        .shouldHave(sizeGreaterThan(0)).filterBy(visible).first();
//        System.out.println($$x("//a/span[contains(@class,'cke_button_label') and normalize-space(text())='" + name + "']/..").size());

        if (button.is(toolboxButtonOn) != value) {
            button.click();
        }

        return this;
    }

    @Step("\"{name}\" toolbar alanda \"{value}\" seç")
    public TextEditor toolbarCombo(String name, String value) {
        SelenideElement combo = $$x("//span[contains(@class,'cke_combo_label') and normalize-space(text())='" + name + "']/../a")
                .filterBy(visible).shouldHaveSize(1).first();

        combo.click();

        By iframeLocator = By.cssSelector("iframe[class='cke_panel_frame']");
        $(iframeLocator).shouldBe(visible);
        switchTo().frame(WebDriverRunner.getWebDriver().findElement(iframeLocator));
//        $inFrame(By.cssSelector(".cke_panel_block a[title='" + value + "']"), iframeLocator);//.click();

        $(By.cssSelector(".cke_panel_block a[title='" + value + "']")).click();

//        switchTo().defaultContent();
        WebDriverRunner.getWebDriver().switchTo().parentFrame();
        return this;
    }

    @Step("")
    public TextEditor sablonlar() {

        return this;
    }
}
