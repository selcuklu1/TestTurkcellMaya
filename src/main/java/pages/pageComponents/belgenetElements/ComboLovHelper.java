package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.ElementFinder;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ComboLovHelper extends BaseLibrary {

    private static String id;

    private static String lovText;

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

    private static void setLocators(SelenideElement proxy) {
        if (proxy.attr("id").contains("LovText"))
            id = "[id*='" + proxy.attr("id").split("LovText")[0] + "']";
        else if (proxy.attr("id").contains("LovSecilen"))
            id = "[id*='" + proxy.attr("id").split("LovSecilen")[0] + "']";
        else
            throw new RuntimeException("comboLov id alınamadı.");

        lovText = id + "[id$='LovText']";

        treeButton = id + "[id*='treeButton']";

        lovItemTitle = " .lovItemTitle";
        lovItemDetail = " .lovItemDetail";

        lovTree = id + "[id$='lovTree']";
        lovTreeList = lovTree + " li";
        lovTreeListSelectableItems = lovTreeList + " span[class*='ui-tree-selectable-node']";
        lovTreeListSelectableItemsTitle = lovTreeListSelectableItems + lovItemTitle;
        lovTreeListSelectableItemsDetail = lovTreeListSelectableItems + lovItemDetail;

        lovSecilen = id + "[id*='LovSecilen']";
        lovSecilenItemTitle = lovSecilen + lovItemTitle;
        lovSecilenItemDetail = lovSecilen + lovItemDetail;
        lovInputTextleriTemizle = lovSecilen + " button[onclick*='lovInputTextleriTemizle']";

        lovTreePanelKapat = id + "[id*='lovTreePanelKapat']";
    }

    static BelgenetElement clearLastSelectedLov(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection temizleButonlari = $$(lovInputTextleriTemizle).filter(visible);
        int count = temizleButonlari.size();
        if (count > 0)
            temizleButonlari.get(count - 1).click();

        $$(lovInputTextleriTemizle).filter(visible).shouldHaveSize(count - 1);

        return (BelgenetElement) proxy;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    public static BelgenetElement clearAllSelectedLov(SelenideElement proxy) {
        setLocators(proxy);
        int count = $$(lovInputTextleriTemizle).size();

        for (int i = count - 1; i >= 0; i--)
            $$(lovInputTextleriTemizle).get(i).click();

        long t = Configuration.timeout;
        Configuration.timeout = 0;
        //! singleType lov için temizle butonu DOM da kaldığı için visible kontrolü kıllanıldı
        for (SelenideElement item : $$(lovInputTextleriTemizle))
            item.shouldBe(not(visible));

        Configuration.timeout = t;

        return (BelgenetElement) proxy;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    static BelgenetElement lastSelectedLovTitle(SelenideElement proxy) {
        setLocators(proxy);
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

    static BelgenetElement lastSelectedLovDetail(SelenideElement proxy) {
        setLocators(proxy);

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

    static String getLastSelectedLovValue(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemTitle).filter(visible);
        return title.get(title.size() - 1).text()
                + "\n" + title.get(title.size() - 1).text();
    }

    static String lastSelectedLovTitleText(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemTitle).filter(visible);
        return title.get(title.size() - 1).text().trim();
    }

    static String lastSelectedLovDetailText(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemDetail).filter(visible);
        return title.get(title.size() - 1).text().trim();
    }

    static Boolean isLovSelected(SelenideElement proxy) {
        setLocators(proxy);
        return $(lovSecilen).is(visible);
    }


    //region selectLov metodları
    private static void selectSingleType(String value) {
//        String title, detail;

        if (!WebDriverRunner.getWebDriver().findElement(By.cssSelector(lovText)).isDisplayed())
            $(lovInputTextleriTemizle).shouldBe(visible).click();

        $(lovText).shouldBe(visible);

        if (!$(lovText).isEnabled())
            $(treeButton).click();
        else
            $(lovText).setValue(value);

        ElementsCollection items = $$(lovTreeListSelectableItemsTitle);
        $$(lovTreeListSelectableItemsTitle).shouldHave(sizeGreaterThan(0));
        $$(lovTreeListSelectableItemsTitle).get(0).shouldBe(visible);

        if ($$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).size() > 0)
            $$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value))
                    .get($$(lovTreeListSelectableItemsTitle).filterBy(textCaseSensitive(value)).size() - 1).click();
        else if ($$(lovTreeListSelectableItemsTitle).filterBy(text(value)).size() > 0)
            $$(lovTreeListSelectableItemsTitle).filterBy(text(value))
                    .get($$(lovTreeListSelectableItemsTitle).filterBy(text(value)).size() - 1).click();
        else if ($$(lovTreeListSelectableItemsDetail).filterBy(text(value)).size() > 0)
            $$(lovTreeListSelectableItemsDetail).filterBy(text(value))
                    .get($$(lovTreeListSelectableItemsDetail).filterBy(text(value)).size() - 1).click();
        else
            $$(lovTreeListSelectableItemsTitle).get(0).click();

        $(lovSecilenItemTitle).shouldBe(visible);

        Allure.addAttachment("Seçilen değerleri:", $(lovSecilenItemTitle).text()
                + "\n" + $(lovSecilenItemDetail).text());
    }

    private static void selectMultiType(String value) {
        long defaultTimeout;
        boolean isSelected = false;
        SelenideElement willBeSelected = null;

        defaultTimeout = Configuration.timeout;
        Configuration.timeout = 0;
        List<String> selectedDetails = $$(lovSecilenItemDetail).texts();
        Configuration.timeout = defaultTimeout;

        $(lovText).setValue(value);

        List<SelenideElement> selectList = $$(lovTreeListSelectableItemsDetail);

        for (SelenideElement item : selectList) {
            item.shouldBe(visible);
            if (!selectedDetails.contains(item.text())) {
                isSelected = true;
                item.click();
                break;
            }

        }
        if (!isSelected)
            throw new RuntimeException("\"" + value + "\" değeri seçilemedi. Alan: " + lovText);

        if ($(lovTreePanelKapat).is(visible))
            $(lovTreePanelKapat).click();

        Assert.assertEquals(selectedDetails.size() + 1, $$(lovSecilenItemTitle).size(), "Bir seçenek eklenmesi bekleniyor");


        try {
            Allure.addAttachment("Seçilen değerleri:", $$(lovSecilenItemTitle).get(selectedDetails.size()).text()
                    + "\n" + $$(lovSecilenItemDetail).get(selectedDetails.size()).text());
        } catch (Exception e) {
        }
    }

    public static BelgenetElement selectLov(SelenideElement proxy, String value) {

        setLocators(proxy);

        executeJavaScript("arguments[0].scrollIntoView();", proxy);
//        proxy.sendKeys(Keys.SHIFT);

        WebElement element = WebDriverRunner.getWebDriver()
                .findElement(By.cssSelector(lovText));

        boolean isMultiType = element.getAttribute("class").contains("lovMultipleType");

        if (isMultiType)
            selectMultiType(value);
        else
            selectSingleType(value);

        if (isMultiType)
            return (BelgenetElement) proxy;
        else
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilen), 0);
    }

    public static boolean isLovValueSelectable(SelenideElement proxy, String value) {

        setLocators(proxy);

        boolean selectable = false;

        executeJavaScript("arguments[0].scrollIntoView();", proxy);

        $(lovText).shouldBe(visible);

        $(lovText).setValue(value);

        $(lovTree).shouldBe(visible);

        if ($$(lovTreeListSelectableItemsTitle).size() == 0)
            selectable = false;
        else if ($$(lovTreeListSelectableItemsTitle).size() == 1)
            selectable = true;
        else
            selectable = $$(lovTreeListSelectableItemsTitle).filterBy(exactText(value)).size() > 0;

        try {
            Allure.addAttachment("Seçilebilir mi?", "");
        } catch (Exception e) {
        }

        if ($(lovTreePanelKapat).is(visible))
            $(lovTreePanelKapat).click();

        return selectable;
    }
    //endregion


}
