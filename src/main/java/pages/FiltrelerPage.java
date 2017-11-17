package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class FiltrelerPage extends BaseLibrary {


    SelenideElement pageTilte = $(".ui-inbox-header-title");

    String filtreMainDivId = "mainInboxForm:inboxDataTable:filtersAccordion";
    SelenideElement filtre = $("[id='" + filtreMainDivId + "'] h3");
    SelenideElement filtersGrid = $(By.id(filtreMainDivId + ":filtersGrid"));
    ElementsCollection filterList = $$("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] label");

    SelenideElement sonucTablosu = $(By.id("mainInboxForm:inboxDataTable_data"));

    @Step("\"Filtreler\"i genişlet")
    public void filtrelerAc() {
        if (filtre.attr("aria-expanded").equalsIgnoreCase("false")) filtre.find("a").click();
    }


//    Function HtmlGetParentObject(ByVal obj, ByVal parentTagName)
//    Dim parent : Set parent = obj.parentElement
//    If Not parent is Nothing Then
//    If StrComp(parent.tagName, parentTagName, vbTextCompare) <> 0 Then
//    Set parent = HtmlGetParentObject(parent, parentTagName)
//    End If
//    End If
//    Set HtmlGetParentObject = parent
//    End Function

    private SelenideElement getParentElement(SelenideElement element, String parentTag) {
        SelenideElement p = executeJavaScript(
                "function getParentElement(element, parentTag) {" +
                        "p = element.parentNode;\n" +
                        "    if(p.tagName === null) return null;" +
                        "    if(p.tagName === parentTag) return p.tagName;" +
                        "    getParentElement(p, parentTage)" +
                        "}" +
                        "getParentElement(argument[0], argument[1])" +
                        "" +
                        "", element, parentTag
        );
        return p;
//        SelenideElement p = element.parent();
//        while (!p.getTagName().equalsIgnoreCase(parentTag))
//            p = p.parent();
//        return p;
    }

    private void m(String elementLabel) {
        filtrelerAc();
        SelenideElement element = filterList.filter(Condition.text(elementLabel)).get(0).parent();
        SelenideElement parentElement = getParentElement(element, "TD");
    }

    public String getTitle() {
        return pageTilte.text().trim();
    }

}
