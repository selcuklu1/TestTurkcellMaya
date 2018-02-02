package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface BelgenetElement extends SelenideElement {

    //region ComboLov

    BelgenetElement comboLov(String selector);

    BelgenetElement comboLov(By locator);

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement clearLastSelectedItem();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement clearAllSelectedItems();

    /**
     * Search by text exact words case insensitive
     * comboLov().selectLov("Optiim TEST, "Optiim Birim", "Danışman")
     * select first filtered
     * if empty first will be selected
     *
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement selectLov(String... text);

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    Boolean isLovSelected();

    /**
     * Click treeButton on comboLov
     *
     * @return
     */
    BelgenetElement openTreePanel();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement closeTreePanel();

    /**
     * Type text to comboLov input
     *
     * @param text
     * @return
     */
    BelgenetElement type(String text);

    /**
     * "Sonuç bulunamamıştır" kontrolü, type ya da openTreePanel sonrası kullanılır
     *
     * @return
     */
    Boolean isEmpty();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    Boolean isLovValueSelectable(String value);

    /**
     * selectable title list
     *
     * @return
     */
    ElementsCollection getSelectableItems();

    /**
     * selectable title list
     *
     * @return
     */
    ElementsCollection getTitleItems();

    /**
     * selectable detail list
     *
     * @return
     */
    ElementsCollection getDetailItems();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    ElementsCollection getSelectedTitles();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    ElementsCollection getSelectedDetails();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    ElementsCollection getSelectedItems();

    //endregion

    //region ComboBox
    BelgenetElement comboBox(String selector);

    BelgenetElement comboBox(By locator);

    BelgenetElement selectComboBox(String text, boolean... js);

    ElementsCollection getComboBoxValues();
    //BelgenetElement getComboBoxHtmlList();
    //endregion


    //region Selenide $ to BelgenetElements

    /**
     * ATTENTION! This method doesn't start any search yet!
     * Same as {@link #find(String)}
     *
     * @see com.codeborne.selenide.commands.Find
     */
    BelgenetElement $(String cssSelector);

    /**
     * ATTENTION! This method doesn't start any search yet!
     * Same as {@link #find(String, int)}
     *
     * @see com.codeborne.selenide.commands.Find
     */
    BelgenetElement $(String cssSelector, int index);

    /**
     * ATTENTION! This method doesn't start any search yet!
     * Same as {@link #find(String)}
     *
     * @see com.codeborne.selenide.commands.Find
     */
    BelgenetElement $(By selector);

    /**
     * ATTENTION! This method doesn't start any search yet!
     * Same as {@link #find(String, int)}
     *
     * @see com.codeborne.selenide.commands.Find
     */
    BelgenetElement $(By selector, int index);

    /**
     * <p>Locates the first matching element inside given element using xpath locator</p>
     * ATTENTION! This method doesn't start any search yet!
     * <p>Short form of {@code webElement.findElement(By.xpath(xpathLocator))}</p>
     *
     * @see com.codeborne.selenide.commands.FindByXpath
     */
    BelgenetElement $x(String xpath);

    /**
     * <p>Locates the Nth matching element inside given element using xpath locator</p>
     * ATTENTION! This method doesn't start any search yet!
     *
     * @see com.codeborne.selenide.commands.FindByXpath
     */
    BelgenetElement $x(String xpath, int index);


    /**
     * <p>
     * Short form of {@code webDriver.findElements(thisElement, By.cssSelector(cssSelector))}
     * </p>
     * ATTENTION! This method doesn't start any search yet!
     * <p>
     * For example, {@code $("#multirowTable").findAll("tr.active").shouldHave(size(2));}
     * </p>
     *
     * @return list of elements inside given element matching given CSS selector
     * @see com.codeborne.selenide.commands.FindAll
     */
    ElementsCollection findAll(String cssSelector);

    /**
     * <p>
     * Short form of {@code webDriver.findElements(thisElement, selector)}
     * </p>
     * ATTENTION! This method doesn't start any search yet!
     * <p>
     * For example, {@code $("#multirowTable").findAll(By.className("active")).shouldHave(size(2));}
     * </p>
     *
     * @return list of elements inside given element matching given criteria
     * @see com.codeborne.selenide.commands.FindAll
     */
    ElementsCollection findAll(By selector);

    /**
     * ATTENTION! This method doesn't start any search yet!
     * Same as {@link #findAll(String)}
     */
    ElementsCollection $$(String cssSelector);

    /**
     * Same as {@link #findAll(By)}
     */
    ElementsCollection $$(By selector);

    /**
     * <p>
     * Short form of {@code webDriver.findElements(thisElement, By.xpath(xpath))}
     * </p>
     * ATTENTION! This method doesn't start any search yet!
     * <p>
     * For example, {@code $("#multirowTable").$$x("./input").shouldHave(size(2));}
     * </p>
     *
     * @return list of elements inside given element matching given xpath locator
     * @see com.codeborne.selenide.commands.FindAllByXpath
     */
    ElementsCollection $$x(String xpath);

    static BelgenetElement $(WebElement parentElement, String cssLocator) {
        return ElementFinder.wrap(BelgenetElement.class, parentElement, By.cssSelector(cssLocator), 0);

    }
    //endregion
}

