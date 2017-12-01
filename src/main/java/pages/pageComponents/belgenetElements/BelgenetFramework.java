package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
//import pages.pageComponents.belgenetElements.ComboLov.*;

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
        Commands.getInstance().add("selectedDetails", comboLov.new SelectedDetails());

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

    public static SelenideElement $inAnyFrame(By locator) {
        switchToFrameOfElement(locator);
        return ElementFinder.wrap(BelgenetElement.class, null, locator, 0);
    }

    public static SelenideElement $inAnyFrame(String selector) {
        switchToFrameOfElement(By.cssSelector(selector));
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(selector), 0);
    }

    /**
     * First search in main iframe, then first level child iframes.
     * Stay in founded iframe, to return to main iframe use switchTo().defaultContent().
     * @param locator
     */
    private static SelenideElement switchToFrameOfElement(By locator){
        switchTo().defaultContent();
        if ($(locator).exists())
            return $(locator);

        ElementsCollection iframes = $$(By.tagName("iframe"));
        for (SelenideElement iframe:iframes) {
            switchTo().frame(iframe);
            if ($(locator).exists())
                break;
        }

        return $(locator);
    }


}
