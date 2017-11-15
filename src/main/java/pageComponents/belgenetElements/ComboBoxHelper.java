package pageComponents.belgenetElements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ComboBoxHelper extends BaseLibrary {

    public static void selectComboBox(SelenideElement proxy, String text, boolean js){
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
                        By.xpath(panelXpath + " //li[text()[contains(normalize-space(),'"+ text +"')]]"));

        executeJavaScript("arguments[0].click()", li);
//        List<WebElement> ul = WebDriverRunner.getWebDriver().findElements(liLocator);
//
//        WebElement optionToSelect = null;
//        for (WebElement li: ul) {
//            if (li.getAttribute("innerText").trim().equalsIgnoreCase(text)) {
//                optionToSelect = li;
//                break;
//            }
//        }
//        if (optionToSelect.equals(null)){}
    }

    private static void clickLikeUser(String text) {
        $(btnTrigger).click();
        $$(liLocator)
                .filterBy(Condition.exactText(text))
                .get(0).doubleClick();
    }

    public static By getComboBoxHtmlList(SelenideElement proxy){
        setLocators(proxy);
        $(btnTrigger).click();
        return ulLocator;
    }

    public static List<String> getComboBoxValues(SelenideElement proxy){
        setLocators(proxy);

        List<String> values = new ArrayList<String>();
        List<WebElement> list = WebDriverRunner.getWebDriver().findElements(liLocator);
        for (WebElement e:list) {
            values.add(e.getAttribute("innerText").trim());
        }

        return values;
    }

    private static String id;
    private static String panelXpath;
    private static By label;
    private static By btnTrigger;
    private static By liLocator;
    private static By ulLocator;

    public static void setLocators(SelenideElement proxy){
        //region Get id without _label. This id is parent Div element id
        id = proxy.attr("id");
        id = id.substring(0,id.lastIndexOf("_label"));
        //endregion

        label = By.id(id + "_lable");
        btnTrigger = By.cssSelector("[id='"+ id + "']  div[class*='ui-selectonemenu-trigger']");
        panelXpath = "//*[@id='" + id + "_panel']";
        liLocator = By.cssSelector("[id='"+ id + "_panel'] li");
        ulLocator = By.cssSelector("[id='"+ id + "_panel'] ul");
    }

}
