package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
//import pages.pageComponents.belgenetElements.ComboLov.*;

public class BelgenetFramework {

    private static ComboLov comboLov = new ComboLov();
    private static ComboBox comboBox = new ComboBox();

    public static void setUp() {
        //region ComboLov
        Commands.getInstance().add("selectComboLov", comboLov.new SelectLov());
        Commands.getInstance().add("clearLastSelectedLov", comboLov.new ClearLastSelectedLov());
        Commands.getInstance().add("clearAllSelectedLov", comboLov.new ClearAllSelectedLov());
        Commands.getInstance().add("lastSelectedLovTitle", comboLov.new LastSelectedLovTitle());
        Commands.getInstance().add("lastSelectedLovDetail", comboLov.new LastSelectedLovDetail());
        Commands.getInstance().add("lastSelectedLovTitleText", comboLov.new LastSelectedLovTitleText());
        Commands.getInstance().add("lastSelectedLovDetailText", comboLov.new LastSelectedLovDetailText());
        Commands.getInstance().add("isLovSelected", comboLov.new IsLovSelected());
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
}
