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

//    private static ComboLov comboLov = new ComboLov();
//    private static ComboBox comboBox = new ComboBox();

    public static void setUp() {
        //region ComboLov
        Commands.getInstance().add("selectLov", new ComboLov().new SelectLov());
        Commands.getInstance().add("clearLastSelectedLov", new ComboLov().new ClearLastSelectedLov());
        Commands.getInstance().add("clearAllSelectedLov", new ComboLov().new ClearAllSelectedLov());
        Commands.getInstance().add("lastSelectedLovTitle", new ComboLov().new LastSelectedLovTitle());
        Commands.getInstance().add("lastSelectedLovDetail", new ComboLov().new LastSelectedLovDetail());
        Commands.getInstance().add("lastSelectedLovTitleText", new ComboLov().new LastSelectedLovTitleText());
        Commands.getInstance().add("lastSelectedLovDetailText", new ComboLov().new LastSelectedLovDetailText());
        Commands.getInstance().add("isLovSelected", new ComboLov().new IsLovSelected());
        Commands.getInstance().add("isLovValueSelectable", new ComboLov().new IsLovValueSelectable());
        Commands.getInstance().add("lastSelectedLov", new ComboLov().new LastSelectedLov());
        Commands.getInstance().add("closeLovTreePanel", new ComboLov().new CloseLovTreePanel());

        Commands.getInstance().add("allSelectedLov", new ComboLov().new AllSelectedLov());
        Commands.getInstance().add("selectedTitles", new ComboLov().new SelectedTitles());
        Commands.getInstance().add("selectDetails", new ComboLov().new SelectedDetails());

        Commands.getInstance().add("openTree", new ComboLov().new OpenTree());
//        Commands.getInstance().add("clearLov", comboLov.new ClearLov());
        Commands.getInstance().add("type", new ComboLov().new Type());
        Commands.getInstance().add("isEmpty", new ComboLov().new IsEmpty());
        Commands.getInstance().add("titleItems", new ComboLov().new TitleItems());
        Commands.getInstance().add("detailItems", new ComboLov().new DetailItems());

        //endregion
        // region ComboBox
        Commands.getInstance().add("selectComboBox", new ComboBox().new SelectComboBox());
        Commands.getInstance().add("getComboBoxValues", new ComboBox().new GetComboBoxValues());
        Commands.getInstance().add("getComboBoxHtmlList", new ComboBox().new GetComboBoxList());
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
