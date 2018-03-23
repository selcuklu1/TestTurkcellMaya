package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Filtreler extends BaseLibrary {

    SelenideElement filtre = $("[id='mainInboxForm:inboxDataTable:filtersAccordion']");
    SelenideElement filtersGrid = $("table[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid']");
    SelenideElement searchTable = $("#mainInboxForm\\:inboxDataTable_data");
    SelenideElement firstPageButton = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-first']");
    SelenideElement prevPageButton = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-prev']");
    SelenideElement nextPageButton = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-next']");
    SelenideElement lastPageButton = $("#mainInboxForm\\:inboxDataTable span[class~='ui-paginator-last']");

    By icerikGoster = By.cssSelector("button[id$='detayGosterButton']");


    @Step("Filtrelerde alanı doldur")
    public Filtreler filtrelerdeAlaniDoldur(String name, CharSequence... keysToSend) {
        SelenideElement field = filtersGrid.$x("//label[contains(.,'" + name + "')]/ancestor::td[1]/following-sibling::td[1]//input");
        field.shouldBe(visible);
//        field.sendKeys(keysToSend);
        for (CharSequence keys : keysToSend) {
            field.sendKeys(keys);
        }
        return this;
    }

    @Step("Filtrelerde alanin butonu tıkla")
    public Filtreler filtrelerdeAlaninButonuTikla(String fieldName, int index) {
        filtersGrid.$$x("//label[contains(.,'" + fieldName + "')]/ancestor::td[1]/following-sibling::td[1]//button")
                .shouldHave(sizeGreaterThan(0))
                .get(index)
                .click();
        return this;
    }

    @Step("Filtrelerde butonu tıkla")
    public Filtreler filtrelerButonuTikla(String buttonName) {
        filtre.$x("//button[.='" + buttonName + "']").click();
        return this;
    }

    @Step("İçetik göster")
    public By icerikGoster() {
        return icerikGoster;
    }

    @Step("\"Filtreler\"i genişlet")
    public Filtreler filtrelerAc() {
        if (filtre.$("h3").attr("aria-expanded").equalsIgnoreCase("false"))
            filtre.$("h3").find("a").click();
        return this;
    }

    private SelenideElement getRowWith(Condition condition) {
        while (true) {
            searchTable.shouldBe(visible);

            ElementsCollection rows = getSearchRows();
            rows.shouldHave(sizeGreaterThan(0));

            for (SelenideElement row : rows) {
                row.shouldBe(visible);
                ElementsCollection filtered = row.$$("[class='searchText']").filterBy(condition);
                if (filtered.size() > 0)
                    return row;
            }

            if (nextPageButton.has(cssClass("ui-state-disabled")))
                throw new NotFoundException("Satır bulunamadı: filterBy: " + condition.toString());

            nextPageButton.click();
        }
    }

    @Step("Arama tablosundan satırları al")
    public ElementsCollection getSearchRows() {
        searchTable.shouldBe(visible);
        Allure.addAttachment("Satır sayısı:" + String.valueOf(searchTable.$$("tr[data-ri][role='row']").size()),
                (searchTable.$$("tr[data-ri][role='row']").size() > 0) ? searchTable.$$("tr[data-ri][role='row']").texts().toString() : "");
        takeScreenshot();
        return searchTable.$$("tr[data-ri][role='row']");
    }

    /**
     * Find rows by Selenide.Condition in all pages
     *
     * @param condition
     * @return
     */
    @Step("Arama tablosunda satırı bul")
    public ElementsCollection findRowsWith(Condition condition) {
        while (true) {
//            System.out.println("===================");
//            System.out.println(searchTable.innerHtml());
//            System.out.println("===================");
//            rows.shouldHave(sizeGreaterThan(0));
            ElementsCollection filtered = getSearchRows().filterBy(condition);

            if (filtered.size() > 0 || nextPageButton.has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }

            nextPageButton.click();
        }
    }

    /**
     * Find rows by Selenide.Condition in all pages
     *
     * @param condition
     * @return
     */
    @Step("Arama tablosunda satırı bul")
    public ElementsCollection findRowsWith(Condition condition, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getSearchRows().filterBy(condition);
            if (filtered.size() > 0) {
                for (Condition con : conditions) {
                    filtered = filtered.filterBy(con);
                }
            }
            if (filtered.size() > 0 || nextPageButton.has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            nextPageButton.click();
        }
    }

    /**
     * Find rows by Selenide.Condition in all pages
     *
     * @param condition
     * @return
     */
    @Step("Arama tablosunda satırı bul")
    public ElementsCollection findRowsWithToday(Condition condition, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getSearchRows().filterBy(text(getSysDateForKis()));
            if (filtered.size() == 0)
                return filtered;

            filtered = filtered.filterBy(condition);
            if (filtered.size() > 0) {
                for (Condition con : conditions) {
                    filtered = filtered.filterBy(con);
                }
            }
            if (filtered.size() > 0 || nextPageButton.has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            nextPageButton.click();
        }
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
        return executeJavaScript(
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
//        SelenideElement p = element.parent();
//        while (!p.getTagName().equalsIgnoreCase(parentTag))
//            p = p.parent();
//        return p;
    }


//    private String getTitle() {
//        return pageTilte.text().trim();
//    }


}
