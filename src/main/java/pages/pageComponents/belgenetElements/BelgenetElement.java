package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
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
    BelgenetElement selectLov(String value);

    BelgenetElement clearLastSelectedLov();

    BelgenetElement clearAllSelectedLov();

    BelgenetElement lastSelectedLovTitle();

    BelgenetElement lastSelectedLovDetail();

    String lastSelectedLovTitleText();

    String lastSelectedLovDetailText();

    Boolean isLovSelected();

    Boolean isLovValueSelectable(String value);

    /**
     * Click treeButton on comboLov
     * @return
     */
    BelgenetElement openTree();

    /**
     *
     * @return
     */
//    BelgenetElement clearLov();

    /**
     * Type text to comboLov input
     * @param text
     * @return
     */
    BelgenetElement type(String text);

    /**
     * "Sonuç bulunamamıştır" kontrolü, type ya da openTree sonrası kullanılır
     * @return
     */
    Boolean isEmpty();

    /**
     * selectable title list
     * @return
     */
    ElementsCollection titleItems();

    /**
     * selectable detail list
     * @return
     */
    ElementsCollection detailItems();

    //endregion

    //region ComboBox
    BelgenetElement selectComboBox(String text, boolean... js);

    List<String> getComboBoxValues();
    //BelgenetElement getComboBoxHtmlList();
    //endregion
}

