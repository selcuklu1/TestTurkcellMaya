package pageComponents.belgenetElements;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

public interface BelgenetElement extends SelenideElement {

    //region ComboLov
    BelgenetElement selectComboLov(String value);

    BelgenetElement clearLastSelectedLov();

    BelgenetElement clearAllSelectedLov();

    BelgenetElement lastSelectedLovTitle();

    BelgenetElement lastSelectedLovDetail();

    String lastSelectedLovTitleText();

    String lastSelectedLovDetailText();

    Boolean isLovSelected();
    //endregion

    //region ComboBox
    BelgenetElement selectComboBox(String text, boolean... js);
    List<String> getComboBoxValues();
//    BelgenetElement getComboBoxHtmlList();
    //endregion
}

