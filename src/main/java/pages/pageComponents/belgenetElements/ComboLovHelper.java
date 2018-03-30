package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.ElementFinder;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ComboLovHelper extends BaseLibrary {

    SelenideElement element;

    private String id;

    private String lovText;

    private boolean multiType;

    private String treeButton;
    private String lovTree;
    private String lovTreeList;
    private String lovTreeListSelectableItems;
    private String lovTreeListSelectableItemsTitle;
    private String lovTreeListSelectableItemsDetail;

    private String lovItemTitle;
    private String lovItemDetail;

    private String lovSecilen;
    private String lovSelectedItems;
    private String lovSecilenItemTitle;
    private String lovSecilenItemDetail;
    private String lovSecilenTemizleButton;
    private String lovInputTextleriTemizle;

    private String lovTreePanelKapat;

    void setLocators(SelenideElement proxy) {

        element = proxy;

        if (element.attr("id").contains("LovText"))
            id = "[id*='" + element.attr("id").split("LovText")[0] + "']";
        else if (element.attr("id").contains("LovSecilen"))
            id = "[id*='" + element.attr("id").split("LovSecilen")[0] + "']";
        else if (element.attr("id").contains("lovTree"))
            id = "[id*='" + element.attr("id").split("lovTree")[0] + "']";
        else
            throw new RuntimeException("comboLov id alınamadı.");

        multiType = element.getAttribute("class").contains("lovMultipleType");

        lovText = id + "[id$='LovText']";

        treeButton = id + "[id*='treeButton']";

        lovItemTitle = " .lovItemTitle";
        lovItemDetail = " .lovItemDetail";

        lovTree = id + "[id$='lovTree']";
        lovTreeList = lovTree + " li";
        lovTreeListSelectableItems = lovTreeList + " span[class*='ui-tree-selectable-node']";
        lovTreeListSelectableItemsTitle = lovTreeListSelectableItems + lovItemTitle;
        lovTreeListSelectableItemsDetail = lovTreeListSelectableItems + lovItemDetail;
//*[@id='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:j_idt112']/ancestor::tr[@role='row']

        lovSecilen = id + (multiType ? "[id$='LovSecilenTable_data']" : "[id$='LovSecilen']");
//        lovSecilen = id + "[id*='LovSecilen']";
//        LovSecilenTable_data = id + "[id$='LovSecilenTable_data']";
        lovSelectedItems = lovSecilen + " > tr[role='row']";
        lovSecilenItemTitle = lovSecilen + lovItemTitle;
        lovSecilenItemDetail = lovSecilen + lovItemDetail;
        lovSecilenTemizleButton = "button[onclick*='lovInputTextleriTemizle']";
        lovInputTextleriTemizle = lovSecilen + " " + lovSecilenTemizleButton;

        lovTreePanelKapat = id + "[id*='lovTreePanelKapat']";
    }

    @Step("Clear last selected items")
    BelgenetElement clearSelectedItem(int index) {
        int count = $$(lovInputTextleriTemizle).size();

        SelenideElement b = $$(lovInputTextleriTemizle).get(index).shouldBe(visible);
//        b.click();
        b.pressEnter();
        if (b.is(visible))
            // $$(lovInputTextleriTemizle).last().click();   Firefox browserda aşağı inmeme sorunundan dolayı commentlendi.
            clickJs($$(lovInputTextleriTemizle).last());

        $$(lovInputTextleriTemizle).filter(visible).shouldHaveSize(count - 1);

        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    @Step("Clear last selected item")
    BelgenetElement clearLastSelectedItem() {
        ElementsCollection items = $$(lovSelectedItems).filterBy(visible);
        int size = items.size();
        if (size == 0) {
            Allure.addAttachment("No item to clear", String.valueOf(size));
            return (BelgenetElement) element;
        }

        SelenideElement lastItem = items.last();
        Allure.addAttachment("Remove last selected item", lastItem.text());
        clickJs(lastItem.$(lovSecilenTemizleButton));

        //lastItem.shouldNotBe(exist);
        $$(lovSelectedItems).filterBy(visible).shouldHaveSize(size - 1);
        return (BelgenetElement) element;
    }

    @Step("Clear all selected items")
    BelgenetElement clearAllSelectedItems() {

        int count = getSelectedItems().size();
        for (int i = 0; i < count; i++) {
            clickJs(getSelectedItems().last().$(lovSecilenTemizleButton));
            //getSelectedItems().last().$(lovSecilenTemizleButton).pressEnter();
            getSelectedItems().shouldHaveSize(count - i - 1);
        }
        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    //return type belgenetelement olsun
    By getLastSelectedItem() {

        SelenideElement e;
        if (multiType)
            e = $(lovSecilen + " > tr[role='row']:last-child");
        else
            e = $(lovSecilen);

        String locator = e.getSearchCriteria();
        Allure.addAttachment("item", $(locator).text());
        return By.cssSelector(locator);
    }

    @Step("Get last selected title")
    @Deprecated
    SelenideElement getLastSelectedItemTitle() {
        ElementsCollection selectedItems = getSelectedTitles();
        selectedItems = selectedItems.filterBy(visible);
        selectedItems.shouldHave(sizeGreaterThan(0));
        Allure.addAttachment("item", selectedItems.last().text());
        return selectedItems.last();
    }

    @Step("Get last selected detail")
    @Deprecated
    SelenideElement getLastSelectedItemDetail() {
        ElementsCollection selectedItems = getSelectedDetails();
        selectedItems = selectedItems.filterBy(visible);
        selectedItems.shouldHave(sizeGreaterThan(0));
        Allure.addAttachment("item", selectedItems.last().text());
        return selectedItems.last();
    }

    @Step("Get all selected items")
    ElementsCollection getSelectedItems() {
        ElementsCollection rows;
        if (multiType)
            rows = $$(lovSecilen + " > tr[role='row']");
        else
            rows = $$(lovSecilen);
        rows = rows.filter(visible);

        //System.out.println("Combolov selected items size " + rows.size());
        Allure.addAttachment("Items count", String.valueOf(rows.size()));
        if (rows.size() > 0)
            Allure.addAttachment("Values", rows.texts().toString());
        return rows;
    }

    @Step("Get selected titles")
    ElementsCollection getSelectedTitles() {
        List<String> texts = null;
        if ($$(lovSecilenItemTitle).size() > 0)
            texts = $$(lovSecilenItemTitle).texts();
        Allure.addAttachment("Value", texts != null ? texts.toString() : "");
        return $$(lovSecilenItemTitle);
    }

    @Step("Get selected details")
    ElementsCollection getSelectedDetails() {
        List<String> texts = null;
        if ($$(lovSecilenItemDetail).size() > 0)
            texts = $$(lovSecilenItemDetail).texts();
        Allure.addAttachment("Value", texts != null ? texts.toString() : "");
        return $$(lovSecilenItemDetail);
    }

    @Step("Get last selected text")
    String getLastSelectedValue() {
        return getLastSelectedTitleText() + "\n" + getLastSelectedDetailText();
    }

    @Step("Get last selected detail text")
    String getLastSelectedTitleText() {
        $$(lovSecilenItemTitle).shouldHave(sizeGreaterThan(0));
        String text = $$(lovSecilenItemTitle).last().shouldBe(visible).text();
        Allure.addAttachment("Value", text);
        return text;
    }

    @Step("Get last selected detail text")
    String getLastSelectedDetailText() {
        $$(lovSecilenItemDetail).shouldHave(sizeGreaterThan(0));
        String text = $$(lovSecilenItemDetail).last().shouldBe(visible).text();
        Allure.addAttachment("Value", text);
        return text;
    }

    @Step("Is field selected?")
    Boolean isLovSelected() {
        boolean isselected = $(lovSecilen).is(visible);
        Allure.addAttachment("Value", String.valueOf(isselected));
        return isselected;
    }

    //region selectLov metodları
    @Step("Select")
    @Deprecated
    public By selectLov_o(String value) {

        //executeJavaScript("arguments[0].scrollIntoView();", element);
        try {
            if (element.isDisplayed())
                element.getWrappedElement().sendKeys(Keys.SHIFT);
        } catch (Exception ignored) {
        }

        if (multiType)
            selectMultiType(value);
        else
            selectSingleType(value);

//        return By.cssSelector(lovText);

       /* if (multiType)
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
        else
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilen), 0);*/
        if (multiType)
            return By.cssSelector(lovText);
        else
            return By.cssSelector(lovSecilen);
    }

    @Step("Is item selectable?")
    public boolean isLovValueSelectable(String value) {

/*        WebElement weblovText = WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText));
//        executeJavaScript("arguments[0].scrollIntoView();", weblovText);
//
//        if (weblovText.isDisplayed())
//            $(lovText).setValue(value);
//        else
//            $(treeButton).click();*/

        boolean selectable = false;

        executeJavaScript("arguments[0].scrollIntoView();", element);

        if ($(lovText).is(not(visible)))
            $(lovInputTextleriTemizle).shouldBe(visible).click();

        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);

        $(lovText).shouldBe(visible);

        SelenideElement tree = $$(lovTree).last();
        tree.shouldBe(visible);
        tree.$$(lovTreeList).shouldHave(sizeGreaterThan(0));
        tree.$$(lovTreeList).get(0).shouldBe(visible);

        selectable = !$$(lovTreeList).get(0).is(have(text("Sonuç bulunamamıştır")));

        try {
            Allure.addAttachment("Seçilebilir mi?", "");
        } catch (Exception ignored) {
        }

        closeTreePanel();

        return selectable;
    }

    @Step("Open list items")
    private void openListItems(String value) {
        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);
    }

    private void selectSingle(String value) {
        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();

        openListItems(value);

        ElementsCollection titleItems = $$(lovTree).last().$$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        ElementsCollection detailItems = $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);
        titleItems.get(0).shouldBe(visible);
//        getTitleItems.details.filterBy(exactText()).

    }

    @Step("Single select type")
    @Deprecated
    private void selectSingleType(String value) {
//        String title, detail;

        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();

        $(lovText).shouldBe(visible);

        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);

        SelenideElement tree = $$(lovTree).last();
        tree.shouldBe(visible);
        ElementsCollection collection = tree.$$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        collection.last().shouldBe(visible);

        /*ElementsCollection filteredCollection = collection.filterBy(textCaseSensitive(value));
        if (filteredCollection.size() > 0) {
            filteredCollection.get(0).shouldBe(visible).click();
            $(lovSecilenItemTitle).shouldBe(visible);
            Allure.addAttachment("Seçilen değerleri:", $(lovSecilenItemTitle).text()
                    + "\n" + $(lovSecilenItemDetail).text());
            return;
        }*/

        ElementsCollection filteredCollection = collection.filterBy(text(value));
        if (filteredCollection.size() > 0)
            filteredCollection.get(0).click();
        else
            collection.get(0).click();

        /*tree.$$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        tree.$$(lovTreeListSelectableItemsTitle).get(0).shouldBe(visible);

        if (tree.$$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).size() > 0)
            tree.$$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).first().click();
        else if (tree.$$(lovTreeListSelectableItemsTitle).filterBy(text(value)).size() > 0)
            tree.$$(lovTreeListSelectableItemsTitle).filterBy(text(value)).first().click();
        else
            tree.$$(lovTreeListSelectableItemsTitle).get(0).click();*/

        $(lovSecilenItemTitle).shouldBe(visible);

        try {
            Allure.addAttachment("Seçilen değerleri:", $(lovSecilenItemTitle).text()
                    + "\n" + $(lovSecilenItemDetail).text());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Multi select type")
    @Deprecated
    private void selectMultiType(String value) {
        long defaultTimeout;
        boolean isSelected = false;
        SelenideElement willBeSelected = null;

        List<String> selectedTitles = $$(lovSecilenItemTitle).texts();
        List<String> selectedDetails = $$(lovSecilenItemDetail).texts();

        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);

        $$(lovTree).last().shouldBe(visible);
        ElementsCollection selectTitleList = $$(lovTree).last().$$(lovTreeListSelectableItemsTitle);
        ElementsCollection selectDetailList = $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);

        for (int i = 0; i < selectTitleList.size(); i++) {
            SelenideElement title = selectTitleList.get(i).shouldBe(visible);
            SelenideElement detail = selectDetailList.get(i).shouldBe(visible);
            if (!selectedTitles.contains(title.text()) || !selectedDetails.contains(detail.text())) {
                isSelected = true;
                title.click();
                break;
            }
        }
        if (!isSelected)
            throw new RuntimeException("\"" + value + "\" değeri seçilemedi. Alan: " + lovText);

        closeTreePanel();

        //Assert.assertTrue(isSelected, "Bir değer seçilemedi");
//        Assert.assertEquals($$(lovSecilenItemTitle).size(),getSelectedTitles.size() + 1, "Bir seçenek eklenmesi bekleniyor");

        try {
            if ($$(lovSecilen).size() > 0)
                Allure.addAttachment("Seçilen değerleri:", $$(lovSecilen).last().text());
//                Allure.addAttachment("Seçilen değerleri:", $$(lovSecilenItemTitle).get(selectedDetails.size()).text()
//                    + "\n" + $$(lovSecilenItemDetail).get(selectedDetails.size()).text());
        } catch (Exception ignored) {
        }
    }

    @Step("Close tree panel")
    public void closeTreePanel() {
        if ($$(lovTreePanelKapat).last().is(visible)) {
            //clickJs($$(lovTreePanelKapat).last());
            //$$(lovTreePanelKapat).last().click();
            $$(lovTreePanelKapat).last().pressEnter();
        }
    }
    //endregion

    @Step("Open tree panel")
    public BelgenetElement openTreePanel() {
        $(treeButton).shouldBe(visible).click();
//        $(lovTree).shouldBe(visible);
//        return (BelgenetElement) $$(lovTree).filterBy(visible).last();
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovTree), 0);
    }

    private BelgenetElement clearLov() {
        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    @Step("Type text")
    public BelgenetElement type(String text) {
        $(lovText).setValue(text);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovTree), 0);
    }

    @Step("\"Sonuç bulunamamıştır\" mı?")
    public boolean isEmpty() {
        boolean isempty = $$(lovTreeList).get(0).shouldBe(visible).is(have(text("Sonuç bulunamamıştır")));
        Allure.addAttachment("Value", String.valueOf(isempty));
        return isempty;
    }

    @Step("Get title items")
    public ElementsCollection getSelectableItems() {
        String locator = "li span[class*='ui-tree-selectable-node']";
        $$(lovTreeList).get(0).shouldBe(visible);
        $$(lovTree).last().shouldBe(visible);
        Allure.addAttachment("items", $$(lovTree).last().$$(locator).texts().toString());
        return $$(lovTree).last().$$(locator);
    }

    @Step("Get title items")
    public ElementsCollection getTitleItems() {
        String locator = "li span[class*='ui-tree-selectable-node'] " + lovItemTitle;
        $$(lovTreeList).get(0).shouldBe(visible);
        $$(lovTree).last().shouldBe(visible);
        Allure.addAttachment("items", $$(lovTree).last().$$(locator).texts().toString());
        return $$(lovTree).last().$$(locator);
    }

    @Step("Get detail items")
    public ElementsCollection getDetailItems() {
        String locator = "li span[class*='ui-tree-selectable-node'] " + lovItemDetail;
        $$(lovTreeList).get(0).shouldBe(visible);
        $$(lovTree).last().shouldBe(visible);
        Allure.addAttachment("items", $$(lovTree).last().$$(locator).texts().toString());
        return $$(lovTree).last().$$(locator);
    }

    @Step("Select Lov")
    public By selectLov(String... text) {
        String selectableItemsLocator = "li span[class*='ui-tree-selectable-node']";
        ElementsCollection collection;

        if (!$(lovText).isDisplayed() && $(lovInputTextleriTemizle).isDisplayed())
            $(lovInputTextleriTemizle).click();

        //try used for disabled field with openTree button
        try {
            if ($(lovText).isDisplayed()) $(lovText).getWrappedElement().sendKeys(Keys.SHIFT);
        } catch (Exception ignored) {
        }

        $(lovText).shouldBe(visible);

        if ($(lovText).isEnabled() && text.length > 0)
            $(lovText).setValue(text[0]);
        else
            $(treeButton).click();

        collection = $$(lovTree).shouldHave(sizeGreaterThan(0)).filterBy(visible).last().$$(selectableItemsLocator);
        //collection.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
        Allure.addAttachment("Selectable items " + collection.size(), collection.texts().toString());
        Allure.addAttachment("Filter texts " + text.length, Arrays.toString(text));

        //ElementsCollection collection1 = collection;
        for (String t : text) {
            collection = collection.filterBy(text(t));
            //regex vs türkçe karakterleri
            //collection1 = collection1.filterBy(matchText("(?i)(?u)(?m)\\b" + t.trim().replaceAll("[\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)‌​\\?\\*\\+\\.\\>]", "\\\\$0") + "\\b"));
        }
        /*if (collection1.size() == 0)
            for (String t : text)
                collection = collection.filterBy(text(t));
        else
            collection = collection1;*/

        Allure.addAttachment("Filtered items " + collection.size(), collection.texts().toString());
        Assert.assertTrue(collection.size() > 0, "Filtered selectable items should have size greater than 0");
        Allure.addAttachment("First item will be selected", collection.first().text());
        collection.first().click();

        closeTreePanel();

        SelenideElement selectedItem = multiType
                ? $$(lovSelectedItems).last().shouldBe(visible)
                : $$(lovSecilen).last().shouldBe(visible);
        for (String t : text) Assert.assertTrue(selectedItem.has(text(t)), "Selected item should have text: " + t);
        Allure.addAttachment("Selected item", $$(lovSecilen).last().text());

        return By.cssSelector(lovText);
    }

    @Step("Select Lov")
    public By selectExactLov(String... text) {
        String selectableItemsLocator = "li span[class*='ui-tree-selectable-node']";
        ElementsCollection collection;

        if (!$(lovText).isDisplayed() && $(lovInputTextleriTemizle).isDisplayed())
            $(lovInputTextleriTemizle).click();

        //try used for disabled field with openTree button
        try {
            if ($(lovText).isDisplayed()) $(lovText).getWrappedElement().sendKeys(Keys.SHIFT);
        } catch (Exception ignored) {
        }

        $(lovText).shouldBe(visible);

        if ($(lovText).isEnabled() && text.length > 0)
            $(lovText).setValue(text[0]);
        else
            $(treeButton).click();

        collection = $$(lovTree).shouldHave(sizeGreaterThan(0)).filterBy(visible).last().$$(selectableItemsLocator);
        //collection.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
        Allure.addAttachment("Selectable items " + collection.size(), collection.texts().toString());
        Allure.addAttachment("Filter texts " + text.length, Arrays.toString(text));

        //ElementsCollection collection1 = collection;
        for (String t : text) {
            //regex vs türkçe karakterleri
            collection = collection.filterBy(matchText("(?i)(?u)(?m)\\b" + t.trim().replaceAll("[<(\\[{\\\\^\\-=$!|\\]})‌​?*+.>]", "\\\\$0") + "\\b"));
            //collection = collection.filterBy(matchText("(?i)(?u)(?m)\\b" + t.trim().replaceAll("[\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)‌​\\?\\*\\+\\.\\>]", "\\\\$0") + "\\b"));
        }

        Allure.addAttachment("Filtered items " + collection.size(), collection.texts().toString());
        Assert.assertTrue(collection.size() > 0, "Filtered selectable items should have size greater than 0");
        Allure.addAttachment("First item will be selected", collection.first().text());
        collection.first().click();

        closeTreePanel();

        SelenideElement selectedItem = multiType
                ? $$(lovSelectedItems).last().shouldBe(visible)
                : $$(lovSecilen).last().shouldBe(visible);
        for (String t : text) Assert.assertTrue(selectedItem.has(text(t)), "Selected item should have text: " + t);
        Allure.addAttachment("Selected item", $$(lovSecilen).last().text());

        return By.cssSelector(lovText);
    }
}
