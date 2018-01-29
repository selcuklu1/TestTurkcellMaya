package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class ComboBoxHelper extends BaseLibrary {

    private String panelXpath;
    private By btnTrigger;
    private By liLocator;
    private By ulLocator;

    void selectComboBox(SelenideElement proxy, String text, boolean js) {
        setLocators(proxy);

//        if (proxy.text().equalsIgnoreCase(text))
//            return;

        if (js)
            jsClick(text);
        else
            click(text);
    }

    private void jsClick(String text) {
        WebElement li = WebDriverRunner.getWebDriver()
                .findElement(
                        By.xpath(panelXpath + " //li[text()[contains(normalize-space(),'" + text + "')]]"));

        executeJavaScript("arguments[0].click()", li);
    }

    //ul.ui-selectonemenu-items

    private void click(String text) {
        $(btnTrigger).click();
        $$(liLocator)
                .filterBy(exactText(text))
                .get(0).doubleClick();
    }

    By getComboBoxHtmlList(SelenideElement proxy) {
        setLocators(proxy);
        $(btnTrigger).click();
        return ulLocator;
    }

    ElementsCollection getComboBoxValues(SelenideElement proxy) {
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

    private void setLocators(SelenideElement proxy) {
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
