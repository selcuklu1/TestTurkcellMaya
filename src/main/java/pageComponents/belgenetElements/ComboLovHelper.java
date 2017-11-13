package pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ComboLovHelper extends BaseLibrary{

    private static String baseCssLocator;
    private static String lovTextBy;
    private static String lovSecilenBy;
    private static String lovSecilenItemTitleBy;
    private static String lovSecilenItemDetailBy;

    static SelenideElement lovText;
    static SelenideElement treeButton;
    static SelenideElement lovTree;
    static ElementsCollection lovTreeList;
    static ElementsCollection lovTreeListSelectableItems;
    static SelenideElement lovSecilen;
    static SelenideElement lovSecilenItemTitle;
    static SelenideElement lovSecilenItemDetail;
    static SelenideElement lovInputTextleriTemizle;
    static SelenideElement lovTreePanelKapat;

    private static void setLocators(SelenideElement proxy) {

        if (proxy.attr("id").contains("LovText"))
            baseCssLocator = "[id='" + proxy.attr("id").split("LovText")[0];
        else
            baseCssLocator = "[id='" + proxy.attr("id").split("LovSecilen")[0];

        lovTextBy = baseCssLocator + "LovText']";
        lovSecilenBy = baseCssLocator + "LovSecilen']";
        lovSecilenItemTitleBy = baseCssLocator + "LovSecilen'] .lovItemTitle";
        lovSecilenItemDetailBy = baseCssLocator + "LovSecilen'] .lovItemDetail";

        lovText = $(lovTextBy);
        treeButton = $(baseCssLocator + "treeButton']");
        lovTree = $(baseCssLocator + "lovTree']");
        lovTreeList = $$(baseCssLocator + "lovTree'] li");
        lovTreeListSelectableItems = $$(baseCssLocator + "lovTree'] li span[class*='ui-tree-selectable-node']");
        lovSecilen = $(lovSecilenBy);
        lovSecilenItemTitle = $(lovSecilenItemTitleBy);
        lovSecilenItemDetail = $(lovSecilenItemDetailBy);
        lovInputTextleriTemizle = $(baseCssLocator + "LovSecilen'] button[onclick*='lovInputTextleriTemizle']");
        lovTreePanelKapat = $(baseCssLocator + "lovTreePanelKapat']");

    }

    public static BelgenetElement clearComboLov(SelenideElement proxy) {
        setLocators(proxy);

        if (lovInputTextleriTemizle.exists())
            lovInputTextleriTemizle.click();

        lovText.shouldBe(visible);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovTextBy), 0);
    }

    public static BelgenetElement selectLov(SelenideElement proxy, String value) {

        setLocators(proxy);

        if (lovInputTextleriTemizle.is(visible)) lovInputTextleriTemizle.click();
        lovText.shouldBe(visible);

        lovText.setValue(value);

        lovTreeListSelectableItems.shouldHave(sizeGreaterThan(0)).get(0).click();

        lovText.shouldNotBe(visible);
        lovSecilen.shouldBe(visible);
        if (lovTreePanelKapat.is(visible)) lovTreePanelKapat.click();

        Allure.addAttachment("Seçil değerleri:", lovSecilenItemTitle.text()
                + "\n" + lovSecilenItemDetail.text());

        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilenBy), 0);
    }

    public static BelgenetElement selectedLovTitle(SelenideElement proxy) {
        setLocators(proxy);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilenItemTitleBy), 0);
    }

    public static BelgenetElement selectedLovDetail(SelenideElement proxy) {
        setLocators(proxy);
        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilenItemDetailBy), 0);
    }

    public static String getSelectedLovValue(SelenideElement proxy) {
        setLocators(proxy);
        lovSecilen.shouldBe(visible);
        return lovSecilenItemTitle.text()
                + "\n" + lovSecilenItemDetail.text();
    }

    public static String getSelectedLovTitle(SelenideElement proxy) {
        setLocators(proxy);
        return lovSecilenItemTitle.shouldBe(visible).text().trim();
    }

    public static String getSelectedLovDetail(SelenideElement proxy) {
        setLocators(proxy);
        return lovSecilenItemDetail.shouldBe(visible).text().trim();
    }

    public static Boolean isSelectedLov(SelenideElement proxy) {
        setLocators(proxy);
        return lovSecilen.is(visible);
    }
}
