package pageComponents.belgenetElements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.ElementFinder;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ComboLovHelper extends BaseLibrary{

    private static String id;

    private static String lovText;

    private static String treeButton;
    private static String lovTree;
    private static String lovTreeList;
    private static String lovTreeListSelectableItems;

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
        lovTree = id + "[id$='lovTree']";
        lovTreeList = id + "[id$='lovTree'] li";
        lovTreeListSelectableItems = id + "[id*='lovTree'] li span[class*='ui-tree-selectable-node']";

        lovSecilen = id + "[id*='LovSecilen']";
        lovSecilenItemTitle = lovSecilen + " .lovItemTitle";
        lovSecilenItemDetail = lovSecilen + " .lovItemDetail";
        lovInputTextleriTemizle = lovSecilen + " button[onclick*='lovInputTextleriTemizle']";

        lovTreePanelKapat = id + "[id*='lovTreePanelKapat']";
    }

    public static BelgenetElement clearLastSelectedLov(SelenideElement proxy) {
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

        for (int i = count - 1; i >= 0; i--) {
            $$(lovInputTextleriTemizle).get(i).click();
//            $$(lovInputTextleriTemizle).shouldHaveSize(i);
        }

        long t = Configuration.timeout;
        Configuration.timeout = 0;
        $(lovInputTextleriTemizle).shouldNotBe(exist);
        Configuration.timeout = t;

        return (BelgenetElement) proxy;
//        return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
    }

    public static BelgenetElement selectLov(SelenideElement proxy, String value) {
        setLocators(proxy);

        if (!$(lovText).is(visible) && $$(lovInputTextleriTemizle).size() == 1) {
            $$(lovInputTextleriTemizle).get(0).click();
            $(lovText).shouldBe(visible);
        }

        int count = $$(lovSecilenItemTitle).filter(visible).size();
        $(lovText).setValue(value);
        $$(lovTreeListSelectableItems).shouldHave(sizeGreaterThan(0)).get(0).click();
        $$(lovSecilenItemTitle).filter(visible).shouldHaveSize(count + 1);

        if ($(lovTreePanelKapat).is(visible)) $(lovTreePanelKapat).click();

        Allure.addAttachment("Seçil değerleri:", $$(lovSecilenItemTitle).get(count).text()
                + "\n" + $$(lovSecilenItemDetail).get(count).text());

        if ($(lovText).is(visible))
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovText), 0);
        else
            return ElementFinder.wrap(BelgenetElement.class, null, By.cssSelector(lovSecilen), 0);

    }

    public static BelgenetElement lastSelectedLovTitle(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection titles = $$(lovSecilenItemTitle).filter(visible);
        String xpath = titles.get(titles.size() - 1).parent().attr("id");
        xpath = "//*[@id='" + xpath + "']//*[@class='lovItemTitle']";
        return ElementFinder.wrap(BelgenetElement.class, null, By.xpath(xpath), 0);
    }

    public static BelgenetElement lastSelectedLovDetail(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection details = $$(lovSecilenItemDetail).filter(visible);
        String xpath = details.get(details.size() - 1).parent().attr("id");
        xpath = "//*[@id='" + xpath + "']//*[@class='lovItemDetail']";

        return ElementFinder.wrap(BelgenetElement.class, null, By.xpath(xpath), 0);
    }

    public static String getLastSelectedLovValue(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemTitle).filter(visible);
        return title.get(title.size() - 1).text()
                + "\n" + title.get(title.size() - 1).text();
    }

    public static String lastSelectedLovTitleText(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemTitle).filter(visible);
        return title.get(title.size() - 1).text().trim();
    }

    public static String lastSelectedLovDetailText(SelenideElement proxy) {
        setLocators(proxy);

        ElementsCollection title = $$(lovSecilenItemDetail).filter(visible);
        return title.get(title.size() - 1).text().trim();
    }

    public static Boolean isLovSelected(SelenideElement proxy) {
        setLocators(proxy);
        return $(lovSecilen).is(visible);
    }
}
