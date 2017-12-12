package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class ComboBoxHelper extends BaseLibrary {

    static void selectComboBox(SelenideElement proxy, String text, boolean js) {
        setLocators(proxy);

//        if (proxy.text().equalsIgnoreCase(text))
//            return;

        if (js)
            jsClick(text);
        else
            clickLikeUser(text);
    }

    private static void jsClick(String text) {
        WebElement li = WebDriverRunner.getWebDriver()
                .findElement(
                        By.xpath(panelXpath + " //li[text()[contains(normalize-space(),'" + text + "')]]"));

        executeJavaScript("arguments[0].click()", li);
    }

    private static void clickLikeUser(String text) {
        $(btnTrigger).click();
        $$(liLocator)
                .filterBy(exactText(text))
                .get(0).doubleClick();
    }

    static By getComboBoxHtmlList(SelenideElement proxy) {
        setLocators(proxy);
        $(btnTrigger).click();
        return ulLocator;
    }

    static ElementsCollection getComboBoxValues(SelenideElement proxy) {
        setLocators(proxy);
        if ($x(panelXpath).is(not(visible)))
            $(btnTrigger).click();
//        List<String> values = new ArrayList<String>();
//        List<WebElement> list = WebDriverRunner.getWebDriver().findElements(liLocator);
//        for (WebElement e : list) {
//            values.add(e.getAttribute("innerText").trim());
//        }

        return $$(liLocator);
    }

    private static String panelXpath;
    private static By btnTrigger;
    private static By liLocator;
    private static By ulLocator;

    private static void setLocators(SelenideElement proxy) {
        //region Get id without _label. This id is parent Div element id
        String id = proxy.attr("id");
        id = id.substring(0, id.lastIndexOf("_label"));
        //endregion

        By label = By.id(id + "_lable");
        btnTrigger = By.cssSelector("[id='" + id + "']  div[class*='ui-selectonemenu-trigger']");
        panelXpath = "//*[@id='" + id + "_panel']";
        liLocator = By.cssSelector("[id='" + id + "_panel'] li");
        ulLocator = By.cssSelector("[id='" + id + "_panel'] ul");
    }

}
