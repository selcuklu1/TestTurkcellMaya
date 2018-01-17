package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.commands.Commands;

public class BelgenetFramework {

//    private static ComboLov comboLov = new ComboLov();
//    private static ComboBox comboBox = new ComboBox();

    public static void setUp() {

        //region ComboLov
        Commands.getInstance().add("selectLov", new ComboLov().new SelectLov());

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
        //endregion
    }
}
