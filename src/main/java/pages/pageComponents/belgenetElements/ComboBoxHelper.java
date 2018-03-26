package pages.pageComponents.belgenetElements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class ComboBoxHelper extends BaseLibrary {

    private String panelXpath;
    private By label;
    private By btnTrigger;
    private By liLocator;
    private By ulLocator;

    void setLocators(SelenideElement proxy) {
        //Get id without _label. This id is parent Div element id
        String id = proxy.attr("id");
        if (id.contains("_label"))
            id = id.substring(0, id.lastIndexOf("_label"));

        label = By.id(id + "_label");

        btnTrigger = proxy.attr("class").contains("ui-selectonemenu")
                ? By.cssSelector("[id='" + id + "'] .ui-selectonemenu-trigger")
                : By.cssSelector("[id='" + id + "'] .ui-selectcheckboxmenu-trigger");
       /* if (proxy.has(cssClass("ui-selectonemenu-trigger")))
            btnTrigger = By.cssSelector("[id='" + id + "'] .ui-selectonemenu-trigger");
        else
            btnTrigger = By.cssSelector("[id='" + id + "'] .ui-selectcheckboxmenu-trigger");*/

        panelXpath = "//*[@id='" + id + "_panel']";
        liLocator = By.cssSelector("[id='" + id + "_panel'] li");
        ulLocator = By.cssSelector("[id='" + id + "_panel'] ul");
    }

    void openPanel(){
        if($x(panelXpath).is(not(visible)))
            $(btnTrigger).click();
    }

    void closePanel(){
        if($x(panelXpath).is(visible))
            $(btnTrigger).click();
    }

    void selectComboBox(SelenideElement proxy, String text, boolean js) {
        setLocators(proxy);

//        if (proxy.text().equalsIgnoreCase(text))
//            return;
        /*if ($(By.xpath(panelXpath + " //li[.//*[contains(normalize-space(),'" + text + "')]]")).exists() && !js) {
            clickJs($(By.xpath(panelXpath + " //li[.//*[contains(normalize-space(),'" + text + "')]]")).toWebElement());
        }*/

        if ($$(liLocator).filterBy(text(text)).size() > 0 && !js){
            clickJs($$(liLocator).filterBy(text(text)).first().toWebElement());
        }
        else {
            openPanel();
            $$(liLocator).filterBy(exactText(text)).get(0).scrollIntoView(true).click();
        }

        //$(label).shouldHave(text(text));
    }

    private void jsClick(String text) {
        WebElement li = WebDriverRunner.getWebDriver().findElement(By.xpath(panelXpath + " //li[.//*[contains(normalize-space(),'" + text + "')]]"));
        executeJavaScript("arguments[0].click()", li);
    }
    //ul.ui-selectonemenu-items
    private void click(String text) {
        if($x(panelXpath).is(not(visible)))
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
}
