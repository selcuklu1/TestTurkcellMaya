package pages.pageComponents;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageComponents.belgenetElements.BelgenetFramework.$inFrame;
import static pages.pageComponents.belgenetElements.BelgentCondition.isToolboxButtonOn;

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

    public SelenideElement editor() {
        By frame = By.cssSelector("div[id^='cke'][id$='contents'] iframe");

//      $inFrame("body[class*='cke_contents_ltr']");
        return $inFrame("body[class*='cke_contents_ltr']", frame);
    }

    @Step("Editore tekst yaz")
    public TextEditor type(CharSequence... keysToSend) {
        editor().sendKeys(keysToSend);
        switchTo().defaultContent();
        return this;
    }

    @Step("\"{name}\" toolbar butonun etkin durumu \"{value}\" yap")
    public TextEditor toolbarButton(String name, boolean value) {
        SelenideElement button = $$x("//a/span[contains(@class,'cke_button_label') and normalize-space(text())='" + name + "']/..")
                .filterBy(visible).shouldHaveSize(1).first();

        if (button.is(isToolboxButtonOn) == value)
            button.click();

        return this;
    }

    @Step("\"{name}\" toolbar alanda \"{value}\" seç")
    public TextEditor toolbarCombo(String name, String value) {
        SelenideElement combo = $$x("//span[contains(@class,'cke_combo_label') and normalize-space(text())='" + name + "']/../a")
                .filterBy(visible).shouldHaveSize(1).first();

        combo.click();

        By iframeLocator = By.className("cke_panel_frame");
        $inFrame(By.cssSelector(".cke_panel_block a[title='" + value + "']"), iframeLocator).click();

        switchTo().defaultContent();
        return this;
    }

}
