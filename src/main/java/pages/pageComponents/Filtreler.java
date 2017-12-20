package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Filtreler extends BaseLibrary {


    SelenideElement pageTilte = $(".ui-inbox-header-title");

    String filtreMainDivId = "mainInboxForm:inboxDataTable:filtersAccordion";
    SelenideElement filtre = $("[id='" + filtreMainDivId + "'] h3");
    SelenideElement filtersGrid = $(By.id(filtreMainDivId + ":filtersGrid"));
    ElementsCollection filterList = $$("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] label");

    By icerikGoster = By.cssSelector("button[id$='detayGosterButton']");
    SelenideElement sonucTablosu = $(By.id("mainInboxForm:inboxDataTable_data"));


    @Step("")
    public By icerikGoster() {
        return icerikGoster;
    }

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
        SelenideElement element = filterList.filter(text(elementLabel)).get(0).parent();
        SelenideElement parentElement = getParentElement(element, "TD");
    }

    public String getTitle() {
        return pageTilte.text().trim();
    }

    private SelenideElement getRowWith(Condition condition) {

        while (true) {
            SelenideElement table = $("#mainInboxForm\\:inboxDataTable_data").shouldBe(visible);
            //SelenideElement row = table.$x("//*[contains(text(),'" + text + "')]/ancestor::tr[@data-ri and @role='row']");
            SelenideElement nextPage = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-next']");
            ElementsCollection rows = table.$$("tr[data-ri][role='row']").shouldHave(sizeGreaterThan(0));

            for (SelenideElement row : rows) {
                row.shouldBe(visible);
                ElementsCollection filtered = row.$$("[class='searchText']").filterBy(condition);
                if (filtered.size() > 0)
                    return row;
            }
            if (nextPage.has(cssClass("ui-state-disabled")))
                throw new NotFoundException("Row bulunamadı: filterBy: " + condition.toString());

            nextPage.click();
        }
    }

    /**
     * Find rows by Selenide.Condition in all pages
     *
     * @param condition
     * @return
     */
    public ElementsCollection findRowsWith(Condition condition) {
        while (true) {
            SelenideElement table = $("#mainInboxForm\\:inboxDataTable_data");
//            table.shouldHave(visible);
            //SelenideElement row = table.$x("//*[contains(text(),'" + text + "')]/ancestor::tr[@data-ri and @role='row']");
            SelenideElement nextPage = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-next']");
            ElementsCollection rows = table.$$("tr[data-ri][role='row']");//.shouldHave(sizeGreaterThan(0));
            if (rows.size() == 0)
                return rows;
            nextPage.shouldHave(visible);
            rows.last().shouldBe(visible);
            System.out.println(condition.toString());

            ElementsCollection filtered = rows.filterBy(condition);

            if (filtered.size() > 0)
                return filtered;

            if (nextPage.has(cssClass("ui-state-disabled")))
                return filtered;

            nextPage.click();
        }
    }

}
