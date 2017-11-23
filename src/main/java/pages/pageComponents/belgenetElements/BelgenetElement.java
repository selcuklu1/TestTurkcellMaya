package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

public interface BelgenetElement extends SelenideElement {

    //region ComboLov

    /**
     * Seçili ise temizler ve seçer. Mutlti select ise daha önce seçilmiş ise fail verir.
     * @param value
     * @return
     * @link pages.pageComponents.belgenetElements.ComboLovHelper.selectLov
     */
    BelgenetElement selectComboLov(String value);

    BelgenetElement clearLastSelectedLov();

    BelgenetElement clearAllSelectedLov();

    BelgenetElement lastSelectedLovTitle();

    BelgenetElement lastSelectedLovDetail();

    String lastSelectedLovTitleText();

    String lastSelectedLovDetailText();

    Boolean isLovSelected();

    Boolean isLovValueSelectable(String value);
    //endregion

    //region ComboBox
    BelgenetElement selectComboBox(String text, boolean... js);

    List<String> getComboBoxValues();
    //BelgenetElement getComboBoxHtmlList();
    //endregion
}

