package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.ElementFinder;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ComboLovHelper extends BaseLibrary {

    static SelenideElement element;

    private static String id;

    private static String lovText;

    private static boolean multiType;

    private static String treeButton;
    private static String lovTree;
    private static String lovTreeList;
    private static String lovTreeListSelectableItems;
    private static String lovTreeListSelectableItemsTitle;
    private static String lovTreeListSelectableItemsDetail;

    private static String lovItemTitle;
    private static String lovItemDetail;

    private static String lovSecilen;
    private static String lovSecilenItemTitle;
    private static String lovSecilenItemDetail;
    private static String lovInputTextleriTemizle;

    private static String lovTreePanelKapat;

    public static void setLocators(SelenideElement proxy) {

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
        lovInputTextleriTemizle = lovSecilen + " button[onclick*='lovInputTextleriTemizle']";

        lovTreePanelKapat = id + "[id*='lovTreePanelKapat']";
    }

    static BelgenetElement clearLastSelectedLov() {
        ElementsCollection temizleButonlari = $$(lovInputTextleriTemizle).filter(visible);
        int count = temizleButonlari.size();
        if (count > 0)
            temizleButonlari.get(count - 1).click();

        $$(lovInputTextleriTemizle).filter(visible).shouldHaveSize(count - 1);

        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    public static BelgenetElement clearAllSelectedLov() {
        int count = $$(lovInputTextleriTemizle).size();

        for (int i = count - 1; i >= 0; i--)
            $$(lovInputTextleriTemizle).get(i).click();

//        long t = Configuration.timeout;
//        Configuration.timeout = 0;
        //! singleType lov için temizle butonu DOM da kaldığı için visible kontrolü kıllanıldı
        for (SelenideElement item : $$(lovInputTextleriTemizle))
            item.shouldBe(not(visible));
//        Configuration.timeout = t;

        return (BelgenetElement) element;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    static BelgenetElement lastSelectedLov() {

        SelenideElement e;
        if (multiType)
            e = $(lovSecilen + " > tr[role='row']:last-child");
        else
            e = $(lovSecilen);

        String locator = e.getWrappedElement().toString();
        locator = locator.split("css selector:")[1].trim();
        locator = locator.substring(0, locator.length()-1);

//        locator = multiType ? locator + ":nth-child("+ $$(l).size() +")" : lovSecilen;
//        return locator;
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(locator), 0);
    }

    static BelgenetElement lastSelectedLovTitle() {
        String xpath = "";

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
        }
    }

    static BelgenetElement lastSelectedLovDetail() {
        String xpath = "";

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
        }
    }

    static String getLastSelectedLovValue() {
        return lastSelectedLovTitleText() + "\n" + lastSelectedLovDetailText();
    }

    static String lastSelectedLovTitleText() {
        return $$(lovSecilenItemTitle).last().shouldBe(visible).text();
    }

    static String lastSelectedLovDetailText() {
        return $$(lovSecilenItemDetail).last().shouldBe(visible).text();
    }

    static Boolean isLovSelected() {
        return $(lovSecilen).is(visible);
    }

    //region selectLov metodları
    public static BelgenetElement selectLov(String value) {

        executeJavaScript("arguments[0].scrollIntoView();", element);
//        element.sendKeys(Keys.SHIFT);

//        WebElement element = WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText));

//        boolean multiType = element.getAttribute("class").contains("lovMultipleType");

        if (multiType)
            selectMultiType(value);
        else
            selectSingleType(value);

        if (multiType)
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
        else
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilen), 0);
    }

    public static boolean isLovValueSelectable(String value) {

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
        } catch (Exception ignored) { }

        closeLovTreePanel();

        return selectable;
    }

    private static void openListItems(String value){
        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);
    }

    private static void selectSingle(String value){
        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();

        openListItems(value);

        ElementsCollection titleItems = $$(lovTree).last().$$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        ElementsCollection detailItems = $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);
        titleItems.get(0).shouldBe(visible);
//        titleItems.details.filterBy(exactText()).

    }

    private static void selectSingleType(String value) {
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
        tree.$$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        tree.$$(lovTreeListSelectableItemsTitle).get(0).shouldBe(visible);

        if (tree.$$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).size() > 0)
            tree.$$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).first().click();
        else if (tree.$$(lovTreeListSelectableItemsTitle).filterBy(text(value)).size() > 0)
            tree.$$(lovTreeListSelectableItemsTitle).filterBy(text(value)).first().click();
        else
            tree.$$(lovTreeListSelectableItemsTitle).get(0).click();

        $(lovSecilenItemTitle).shouldBe(visible);

        Allure.addAttachment("Seçilen değerleri:", $(lovSecilenItemTitle).text()
                + "\n" + $(lovSecilenItemDetail).text());
    }

    private static void selectMultiType(String value) {
        long defaultTimeout;
        boolean isSelected = false;
        SelenideElement willBeSelected = null;

//        defaultTimeout = Configuration.timeout;
//        Configuration.timeout = 0;
        List<String> selectedTitles = $$(lovSecilenItemTitle).texts();
        List<String> selectedDetails = $$(lovSecilenItemDetail).texts();
//        Configuration.timeout = defaultTimeout;

        $(lovText).setValue(value);

        $$(lovTree).last().shouldBe(visible);
        ElementsCollection selectTitleList = $$(lovTree).last().$$(lovTreeListSelectableItemsTitle);
        ElementsCollection selectDetailList = $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);

//        if (selectTitleList.filterBy(exactTextCaseSensitive(value)).size() > 0)
//            selectTitleList = selectTitleList.filterBy(exactTextCaseSensitive(value));

        for (int i = 0; i < selectTitleList.size(); i++) {
            SelenideElement title = selectTitleList.get(i).shouldBe(visible);
            SelenideElement detail = selectDetailList.get(i).shouldBe(visible);
             if (!selectedTitles.contains(title.text()) || !selectedDetails.contains(detail.text())){
                isSelected = true;
                title.click();
                break;
            }
//            for (int j = 0; j < selectedTitles.size(); j++) {
//                if (!selectedTitles.get(j).equalsIgnoreCase(title.text()) ||
//                        !selectedDetails.get(j).equalsIgnoreCase(detail.text()))
//                {
////&& (title.text().equals(value) || detail.text().contains(value)))
//                    isSelected = true;
//                    title.click();
//                    break;
//                }
//            }

            if (isSelected) break;

            /*if (!selectedTitles.contains(title.text()) || !selectedDetails.contains(detail.text())){
                isSelected = true;
                title.click();
                break;
            }*/
//            if (!selectedDetails.contains(item.text())) {
//                isSelected = true;
//                item.click();
//                break;
//            }

        }

        /*for (SelenideElement item : selectTitleList) {
            item.shouldBe(visible);

            if (!selectedDetails.contains(item.text())) {
                isSelected = true;
                item.click();
                break;
            }

        }*/
        if (!isSelected)
            throw new RuntimeException("\"" + value + "\" değeri seçilemedi. Alan: " + lovText);

        closeLovTreePanel();

        Assert.assertEquals(selectedTitles.size() + 1, $$(lovSecilenItemTitle).size(), "Bir seçenek eklenmesi bekleniyor");

        try {
            Allure.addAttachment("Seçilen değerleri:", $$(lovSecilenItemTitle).get(selectedDetails.size()).text()
                    + "\n" + $$(lovSecilenItemDetail).get(selectedDetails.size()).text());
        } catch (Exception e) {
        }
    }

    private static void closeLovTreePanel() {
        if ($$(lovTreePanelKapat).last().is(visible))
            $$(lovTreePanelKapat).last().click();
    }
    //endregion


    public static BelgenetElement openTree(){
        $(treeButton).click();
        return (BelgenetElement) $$(lovTree).last().$(lovTree);
    }

    private static BelgenetElement clearLov(){
        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();
        return (BelgenetElement) $(lovText);
    }

    public static BelgenetElement type(String text){
        $(lovText).setValue(text);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovTree), 0);
    }

    public static boolean isEmpty(){
        return $$(lovTreeList).get(0).is(have(text("Sonuç bulunamamıştır")));
    }


    public static ElementsCollection titleItems(){

        String locator = "li  span[class*='ui-tree-selectable-node'] " + lovItemTitle;
        $$(lovTreeList).get(0).shouldBe(visible);
        return $$(lovTree).last().$$(locator);
//        $$(lovTreeList).get(0).shouldBe(visible);
//        return  $$(lovTree).last().$$(lovTreeListSelectableItemsTitle);
    }

    public static ElementsCollection detailItems(){
        String locator = "li  span[class*='ui-tree-selectable-node'] " + lovItemDetail;
        $$(lovTreeList).get(0).shouldBe(visible);
        return $$(lovTree).last().$$(locator);
//        $$(lovTreeList).get(0).shouldBe(visible);
//        return  $$(lovTree).last().$$(lovTreeListSelectableItemsDetail);
    }


}
