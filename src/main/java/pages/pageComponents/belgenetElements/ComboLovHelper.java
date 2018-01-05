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
    private String lovSecilenItemTitle;
    private String lovSecilenItemDetail;
    private String lovSecilenTemizleButton;
    private String lovInputTextleriTemizle;

    private String lovTreePanelKapat;

    public void setLocators(SelenideElement proxy) {

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

    @Step("Clear last selected items")
    BelgenetElement clearLastSelectedItem() {
        SelenideElement b = $$(lovInputTextleriTemizle).last().shouldBe(visible);
        int count = $$(lovInputTextleriTemizle).size();
        b.click();
        if (b.is(visible))
            // $$(lovInputTextleriTemizle).last().click();   Firefox browserda aşağı inmeme sorunundan dolayı commentlendi.
            clickJs($$(lovInputTextleriTemizle).last());

        $$(lovInputTextleriTemizle).filter(visible).shouldHaveSize(count - 1);

        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    @Step("Clear all selected items")
    public BelgenetElement clearAllSelectedItems() {

        int count = getSelectedItems().size();
        for (int i = 0; i < count; i++) {
            getSelectedItems().last().$(lovSecilenTemizleButton).pressEnter();
            getSelectedItems().shouldHaveSize(count - i - 1);
        }


        /*int count = $$(lovInputTextleriTemizle).size();
        Allure.addAttachment("Items count", String.valueOf(count));

        SelenideElement b = $$(lovInputTextleriTemizle).last().shouldBe(visible);

        while ($$(lovInputTextleriTemizle).size() > 0) {
            SelenideElement e = $$(lovInputTextleriTemizle).first();
            e.pressEnter();
            count--;
           *//* if (e.is())
                e.click();*//*
            e.shouldBe(not(visible));
            $$(lovInputTextleriTemizle).shouldHaveSize(count);
        }*/

        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    By getLastSelectedItem() {

        SelenideElement e;
        if (multiType)
            e = $(lovSecilen + " > tr[role='row']:last-child");
        else
            e = $(lovSecilen);

        String locator = e.getWrappedElement().toString();
        locator = locator.split("css selector:")[1].trim();
        locator = locator.substring(0, locator.length() - 1);

//        locator = multiType ? locator + ":nth-child("+ $$(l).size() +")" : lovSecilen;
//        return locator;
        return By.cssSelector(locator);
    }

    //Depricated
    @Step("Get last selected title")
    SelenideElement lastSelectedLovTitle() {
        ElementsCollection selectedItems = getSelectedTitles();
        selectedItems = selectedItems.filterBy(visible);
        selectedItems.shouldHave(sizeGreaterThan(0));
        return selectedItems.last();

        /*String xpath = "";

        int count = $$(lovSecilenItemTitle).size();
        if (count == 0)
            throw new RuntimeException("lastSelectedLovTitle bulunamadı");
        if ($$(lovSecilenItemTitle).filter(visible).size() == 0)
            throw new RuntimeException("lastSelectedLovTitle bulunamadı");

        SelenideElement title = $$(lovSecilenItemTitle).filter(visible).get(count - 1);
        if (!title.attr("id").isEmpty())
            return ElementFinder.wrap(BelgenetElement.class, null, By.id(title.attr("id")), 0);
        else {

            SelenideElement parent = title.parent();
            while (parent.attr("id") == "" && !parent.equals(null)) {
                parent = parent.parent();
            }
            xpath = parent.attr("id");
            if (xpath.isEmpty()) throw new RuntimeException("lastSelectedLovTitle'in xpath! bulunamadı");

            xpath = "//*[@id='" + xpath + "']//*[@class='lovItemTitle']";
            return ElementFinder.wrap(BelgenetElement.class, null, By.xpath(xpath), 0);
        }*/
    }

    //Depricated
    @Step("Get last selected detail")
    SelenideElement lastSelectedLovDetail() {
        ElementsCollection selectedItems = getSelectedDetails();
        selectedItems = selectedItems.filterBy(visible);
        selectedItems.shouldHave(sizeGreaterThan(0));

        return selectedItems.last();
        /*String xpath = "";

        int count = $$(lovSecilenItemDetail).size();
        if (count == 0)
            throw new RuntimeException("lovSecilenItemDetail bulunamadı");
        if ($$(lovSecilenItemDetail).filter(visible).size() == 0)
            throw new RuntimeException("lovSecilenItemDetail bulunamadı");

        SelenideElement title = $$(lovSecilenItemDetail).filter(visible).get(count - 1);
        if (!title.attr("id").isEmpty())
            return ElementFinder.wrap(BelgenetElement.class, null, By.id(title.attr("id")), 0);
        else {

            SelenideElement parent = title.parent();
            while (parent.attr("id") == "" && !parent.equals(null)) {
                parent = parent.parent();
            }
            xpath = parent.attr("id");
            if (xpath.isEmpty()) throw new RuntimeException("lovSecilenItemDetail'in xpath! bulunamadı");

            xpath = "//*[@id='" + xpath + "']//*[@class='lovItemDetail']";
            return ElementFinder.wrap(BelgenetElement.class, null, By.xpath(xpath), 0);
        }*/
    }

    @Step("Get all selected items")
    ElementsCollection getSelectedItems() {
        ElementsCollection rows;
        if (multiType)
            rows = $$(lovSecilen + " > tr[role='row']");
        else
            rows = $$(lovSecilen);
        rows = rows.filter(visible);

        System.out.println("size " + rows.size());
        Allure.addAttachment("Items count", String.valueOf(rows.size()));
        if (rows.size() > 0)
            Allure.addAttachment("Values", rows.texts().toString());
        return rows;
    }

    @Step("Get selected titles")
    ElementsCollection getSelectedTitles() {
        List<String> texts = null;
        if ($$(lovSecilenItemTitle).size()>0)
            texts = $$(lovSecilenItemTitle).texts();
        Allure.addAttachment("Value", texts.toString());
        return $$(lovSecilenItemTitle);
    }

    @Step("Get selected details")
    ElementsCollection getSelectedDetails() {
        List<String> texts = null;
        if ($$(lovSecilenItemDetail).size() > 0)
            texts = $$(lovSecilenItemDetail).texts();
        Allure.addAttachment("Value", texts.toString());
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

    @Step("Is field selected")
    Boolean isLovSelected() {
        boolean isselected = $(lovSecilen).is(visible);
        Allure.addAttachment("Value", String.valueOf(isselected));
        return isselected;
    }

    //region selectLov metodları
    @Step("Select")
    public By selectLov(String value) {

        //executeJavaScript("arguments[0].scrollIntoView();", element);
        try {
            if (element.isDisplayed())
                element.getWrappedElement().sendKeys(Keys.SHIFT);
        } catch (Exception ignored) {
        }

//        WebElement element = WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText));

//        boolean multiType = element.getAttribute("class").contains("lovMultipleType");

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

    @Step("Is selectable")
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
            if (isSelected) break;
        }
        if (!isSelected)
            throw new RuntimeException("\"" + value + "\" değeri seçilemedi. Alan: " + lovText);

        closeTreePanel();

        Assert.assertTrue(isSelected, "Bir değer seçilemedi");
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
            $$(lovTreePanelKapat).last().click();
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

    @Step("Type text in field")
    public BelgenetElement type(String text) {
        $(lovText).setValue(text);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovTree), 0);
    }

    @Step("Is empty")
    public boolean isEmpty() {
        boolean isempty = $$(lovTreeList).get(0).is(have(text("Sonuç bulunamamıştır")));
        Allure.addAttachment("Value", String.valueOf(isempty));
        return isempty;
    }

    @Step("Get title items")
    public ElementsCollection getTitleItems() {

        String locator = "li span[class*='ui-tree-selectable-node'] " + lovItemTitle;
        $$(lovTreeList).get(0).shouldBe(visible);
        $$(lovTree).last().shouldBe(visible);
        return $$(lovTree).last().$$(locator);
//        $$(lovTreeList).get(0).shouldBe(visible);
//        return  $$(lovTree).last().$$(lovTreeListSelectableItemsTitle);
    }

    @Step("Get detail items")
    public ElementsCollection getDetailItems() {
        String locator = "li span[class*='ui-tree-selectable-node'] " + lovItemDetail;
        $$(lovTreeList).get(0).shouldBe(visible);
        $$(lovTree).last().shouldBe(visible);
        return $$(lovTree).last().$$(locator);
//        $$(lovTreeList).get(0).shouldBe(visible);
//        return  $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);
    }

}
