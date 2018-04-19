package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.Click;
import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.commands.SetSelected;
import com.codeborne.selenide.ex.InvalidStateException;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BelgenetFramework {

//    private static ComboLov comboLov = new ComboLov();
//    private static ComboBox comboBox = new ComboBox();

    public static void setUp() {

        //region ComboLov
        Commands.getInstance().add("selectLov", new ComboLov().new SelectLov());

        Commands.getInstance().add("selectExactLov", new ComboLov().new SelectExactLov());

        Commands.getInstance().add("clearLastSelectedItem", new ComboLov().new ClearLastSelectedItem());

        Commands.getInstance().add("clearAllSelectedItems", new ComboLov().new ClearAllSelectedItems());

        Commands.getInstance().add("isLovSelected", new ComboLov().new IsLovSelected());

        Commands.getInstance().add("isLovValueSelectable", new ComboLov().new IsLovValueSelectable());

        Commands.getInstance().add("getSelectableItems", new ComboLov().new GetSelectableItems());

        Commands.getInstance().add("getSelectedItems", new ComboLov().new GetSelectedItems());

        Commands.getInstance().add("getSelectedTitles", new ComboLov().new GetSelectedTitles());

        Commands.getInstance().add("getSelectedDetails", new ComboLov().new GetSelectedDetails());

        Commands.getInstance().add("openTreePanel", new ComboLov().new OpenTreePanel());

        Commands.getInstance().add("closeTreePanel", new ComboLov().new CloseTreePanel());

        Commands.getInstance().add("type", new ComboLov().new Type());

        Commands.getInstance().add("isEmpty", new ComboLov().new IsEmpty());

        Commands.getInstance().add("getTitleItems", new ComboLov().new GetTitleItems());

        Commands.getInstance().add("getDetailItems", new ComboLov().new GetDetailItems());

        //endregion

        // region ComboBox
        Commands.getInstance().add("selectComboBox", new ComboBox().new SelectComboBox());
        Commands.getInstance().add("getComboBoxValues", new ComboBox().new GetComboBoxValues());
        Commands.getInstance().add("getComboBoxHtmlList", new ComboBox().new GetComboBoxList());
        Commands.getInstance().add("openPanel", new ComboBox().new OpenPanel());
        Commands.getInstance().add("closePanel", new ComboBox().new ClosePanel());
        //endregion

        //Belgenet checkbox için geliştirildi
        Commands.getInstance().add("setSelected", new SetSelected(){
            Click click = new Click();

            @Override
            public WebElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) {
                boolean selected = (Boolean) args[0];
                WebElement element = locator.getWebElement();
                if (!element.isDisplayed()) {
                    throw new InvalidStateException("Cannot change invisible element");
                }
                String tag = element.getTagName();
                String belgenetCheckboxClass = "ui-chkbox-box";
                if (!tag.equals("option")) {
                    if (tag.equals("input")) {
                        String type = element.getAttribute("type");
                        if (!type.equals("checkbox") && !type.equals("radio")) {
                            throw new InvalidStateException("Only use setSelected on checkbox/option/radio");
                        }
                    } else if (!element.getAttribute("class").contains(belgenetCheckboxClass)){
                        if (!element.findElement(By.className(belgenetCheckboxClass)).isDisplayed())
                            throw new InvalidStateException("Only use setSelected on checkbox/option/radio");
                        element = element.findElement(By.className(belgenetCheckboxClass));
                    } else {
                        throw new InvalidStateException("Only use setSelected on checkbox/option/radio");
                    }
                }

                if (element.getAttribute("readonly") != null || element.getAttribute("disabled") != null)
                    throw new InvalidStateException("Cannot change value of readonly/disabled element");
                if (element.isSelected() != selected && element.getAttribute("class").contains("ui-state-active")!=selected) {
                    click.execute(proxy, locator, NO_ARGS);
                }

                return proxy;
            }
        });

    }
}
