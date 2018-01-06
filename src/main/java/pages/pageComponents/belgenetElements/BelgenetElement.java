package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

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
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement selectLov(String value);

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    Boolean isLovSelected();

    /**
     * Click treeButton on comboLov
     * @return
     */
    BelgenetElement openTreePanel();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    BelgenetElement closeTreePanel();

    /**
     * Type text to comboLov input
     * @param text
     * @return
     */
    BelgenetElement type(String text);

    /**
     * "Sonuç bulunamamıştır" kontrolü, type ya da openTreePanel sonrası kullanılır
     * @return
     */
    Boolean isEmpty();

    /**
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     */
    Boolean isLovValueSelectable(String value);

    /**
     * selectable title list
     * @return
     */
    ElementsCollection getTitleItems();

    /**
     * selectable detail list
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
    BelgenetElement selectComboBox(String text, boolean... js);

    ElementsCollection getComboBoxValues();
    //BelgenetElement getComboBoxHtmlList();
    //endregion



/*
    //region ComboLov

    *//**
     * Seçili ise temizler ve seçer. Mutlti select ise daha önce seçilmiş ise fail verir.
     *
     * @param value
     * @return
     * @see pages.pageComponents.belgenetElements.ComboLovHelper
     *//*
    BelgenetElement selectLov(String value);

    BelgenetElement clearLastSelectedLov();

    BelgenetElement clearAllSelectedLov();

    BelgenetElement getLastSelectedItemTitle();

    BelgenetElement getLastSelectedItemDetail();

    String lastSelectedLovTitleText();

    String lastSelectedLovDetailText();

    Boolean isLovSelected();

    Boolean isLovValueSelectable(String value);

    *//**
     * Click treeButton on comboLov
     *
     * @return
     *//*
    BelgenetElement openTree();

    *//**
     *
     * @return
     *//*
//    BelgenetElement clearLov();

    *//**
     * Type text to comboLov input
     *
     * @param text
     * @return
     *//*
    BelgenetElement type(String text);

    *//**
     * "Sonuç bulunamamıştır" kontrolü, type ya da openTree sonrası kullanılır
     *
     * @return
     *//*
    Boolean isEmpty();

    *//**
     * selectable title list
     *
     * @return
     *//*
    ElementsCollection titleItems();

    *//**
     * selectable detail list
     *
     * @return
     *//*
    ElementsCollection detailItems();

    BelgenetElement lastSelectedLov();

    BelgenetElement closeLovTreePanel();

    ElementsCollection selectedTitles();

    ElementsCollection selectedDetails();

    ElementsCollection allSelectedLov();

    //endregion

    //region ComboBox
    BelgenetElement selectComboBox(String text, boolean... js);

    ElementsCollection getComboBoxValues();
    //BelgenetElement getComboBoxHtmlList();
    //endregion

    */

}

