package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BelgenetFramework {

    private static ComboLov comboLov = new ComboLov();
    private static ComboBox comboBox = new ComboBox();

    public static void setUp() {
        //region ComboLov
        Commands.getInstance().add("selectLov", comboLov.new SelectLov());
        Commands.getInstance().add("clearLastSelectedLov", comboLov.new ClearLastSelectedLov());
        Commands.getInstance().add("clearAllSelectedLov", comboLov.new ClearAllSelectedLov());
        Commands.getInstance().add("lastSelectedLovTitle", comboLov.new LastSelectedLovTitle());
        Commands.getInstance().add("lastSelectedLovDetail", comboLov.new LastSelectedLovDetail());
        Commands.getInstance().add("lastSelectedLovTitleText", comboLov.new LastSelectedLovTitleText());
        Commands.getInstance().add("lastSelectedLovDetailText", comboLov.new LastSelectedLovDetailText());
        Commands.getInstance().add("isLovSelected", comboLov.new IsLovSelected());
        Commands.getInstance().add("isLovValueSelectable", comboLov.new IsLovValueSelectable());
        Commands.getInstance().add("lastSelectedLov", comboLov.new LastSelectedLov());
        Commands.getInstance().add("closeLovTreePanel", comboLov.new CloseLovTreePanel());

        Commands.getInstance().add("allSelectedLov", comboLov.new AllSelectedLov());
        Commands.getInstance().add("selectedTitles", comboLov.new SelectedTitles());
        Commands.getInstance().add("selectDetails", comboLov.new SelectedDetails());

        Commands.getInstance().add("openTree", comboLov.new OpenTree());
//        Commands.getInstance().add("clearLov", comboLov.new ClearLov());
        Commands.getInstance().add("type", comboLov.new Type());
        Commands.getInstance().add("isEmpty", comboLov.new IsEmpty());
        Commands.getInstance().add("titleItems", comboLov.new TitleItems());
        Commands.getInstance().add("detailItems", comboLov.new DetailItems());

        //endregion
        // region ComboBox
        Commands.getInstance().add("selectComboBox", comboBox.new SelectComboBox());
        Commands.getInstance().add("getComboBoxValues", comboBox.new GetComboBoxValues());
        Commands.getInstance().add("getComboBoxHtmlList", comboBox.new GetComboBoxList());
        //endregion

    }

    /**
     * Replacement for standard Selenide `$` method.
     *
     * @param selector CSS selector
     * @return BelgenetElement - an "advanced" version of `SelenideElement` with more custom methods
     */
    public static BelgenetElement comboLov(String selector) {
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(selector), 0);
    }

    public static BelgenetElement comboLov(By locator) {
        return ElementFinder.wrap(BelgenetElement.class, null, locator, 0);
    }

    public static BelgenetElement comboBox(String selector) {
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(selector), 0);
    }

    public static BelgenetElement comboBox(By locator) {
        return ElementFinder.wrap(BelgenetElement.class, null, locator, 0);
    }


    /**
     * First search in main iframe, then first level child iframes(optiona iframe locator).
     * Stay in founded iframe, to return to main iframe use switchTo().defaultContent().
     *
     * @param locator
     * @param iframeLocator
     * @return
     */
    public static SelenideElement $inFrame(By locator, By... iframeLocator) {

        switchToFrameOfElement(locator, iframeLocator);

        return ElementFinder.wrap(BelgenetElement.class, null, locator, 0);
    }

    /**
     * First search in main iframe, then first level child iframes(optiona iframe locator).
     * Stay in founded iframe, to return to main iframe use switchTo().defaultContent().
     *
     * @param cssSelector
     * @param iframeLocator
     * @return
     */
    public static SelenideElement $inFrame(String cssSelector, By... iframeLocator) {

        switchToFrameOfElement(By.cssSelector(cssSelector), iframeLocator);

        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(cssSelector), 0);
    }

    private static void switchToFrameOfElement(By elementLocator, By... iframeLocator) {

       /* switchTo().defaultContent();
        if ($(elementLocator).exists())
            return;// $(elementLocator);*/

        By f = iframeLocator.length > 0 ? iframeLocator[0] : By.tagName("iframe");
        ElementsCollection iframes = $$(f).filterBy(visible);

        for (SelenideElement iframe : iframes) {
//            iframe.shouldBe(visible);
            switchTo().frame(iframe);
            if ($(elementLocator).exists())// && $(locator).is(visible))
                return;

            switchTo().defaultContent();
        }

//        return;// $(elementLocator);
    }
}
