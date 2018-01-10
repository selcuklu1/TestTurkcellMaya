package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollectionWrapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static pages.pageComponents.belgenetElements.BelgentCondition.isTableNavButtonDisabled;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 4.01.2018
 * Açıklama:
 */
public class SearchTable {

    private final SelenideElement parentElement;

    public SearchTable(SelenideElement parentElement) {
        this.parentElement = parentElement;
    }

    @Step
    public SelenideElement getTableHeader() {
        return parentElement.$("th.ui-datatable-header");
    }

    @Step
    public SelenideElement getTableLabel() {
        return getTableHeader().$("label");
    }

    @Step
    public SelenideElement getAddNewButton() {
        return parentElement.$x("descendant::button[span[contains(@class,'add-icon')]]");
    }

    @Step
    public SearchTable yeniKayitEkle() {
        getAddNewButton().shouldBe(visible, enabled).click();
        return this;
    }

//    [id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] th.ui-datatable-header label

    @Step("İlk sayfaya git butonu")
    public SelenideElement getFirstPageButton() {
        return parentElement.$("span[class~='ui-paginator-first']");
    }

    @Step("Önceki sayfaya git butonu")
    public SelenideElement getPrevPageButton() {
        return parentElement.$("span[class~='ui-paginator-prev']");
    }

    @Step("Sonraki sayfaya git butonu")
    public SelenideElement getNextPageButton() {
        return parentElement.$("span[class~='ui-paginator-next']");
    }

    @Step("Son sayfaya git butonu")
    public SelenideElement getLastPageButton() {
        return parentElement.$("span[class~='ui-paginator-last']");
    }

    public SelenideElement getDataTable() {
//        return parentElement.$("[id$='SearchTable']");
//        ,[id$='SearchTable'] table[role=grid],table[role=treegrid]
        /*SelenideElement table = parentElement.find("[id$='SearchTable']");
        if (table.isDisplayed())
            return table;

        return parentElement.$("div[id$=TreeTable]");*/
        return parentElement;
    }

    private SelenideElement getDataTableData() {
        return parentElement.$("[id$='DataTable_data']");
    }

    private ElementsCollection getDataTableRows() {
        return parentElement.$$("tr[role=row]");
    }

    private SelenideElement getColumn(SelenideElement row, int columnIndex) {
        return row.$("td[role=gridcell]");
    }

    public boolean isRowsExist() {
        ElementsCollection columns = parentElement.$$("tr[role=row] td[role=gridcell]");
        if (columns.size() == 0 || parentElement.find(Selectors.byText("Kayıt Bulunamamıştır")).exists())
            return false;

        return false;
    }

    public ElementsCollection getRows() {
        parentElement.shouldBe(visible);
        /*Allure.addAttachment("Satır sayısı:" + String.valueOf(searchTable.$$("tr[role='row']").size()),
                (searchTable.$$("tr[role='row']").size() > 0) ? getDataTableData().$$("tr[role='row']").texts().toString() : "");
        takeScreenshot();*/
        return parentElement.$$("tr[role='row']");
    }

    @Step("Kolonun index")
    public int getColumnIndex(String columnName) {

        ElementsCollection columnheaders = parentElement.$$("[id$='SearchTable'] th[role='columnheader']");
        columnheaders.filterBy(exactText(columnName)).shouldHave(sizeGreaterThan(0));
        int i = 1;
        for (SelenideElement header : columnheaders) {
            if (header.has(exactText(columnName)))
                break;
            else
                i++;
        }
        Assert.assertTrue(i <= columnheaders.size(), "\"" + columnName + "\" isimli kolon bulunmalı");
        Allure.addAttachment("Kolon index", String.valueOf(i));
        return i;
    }

    /**
     * @param index start with 1
     * @return
     */
    @Step("Kolonun ismi")
    public String getColumnName(int index) {
        String name = parentElement.$$("th[role='columnheader']")
                .shouldHave(sizeGreaterThanOrEqual(index))
                .get(index - 1).text().trim();
        Allure.addAttachment("Kolon ismi", name);
        return name;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    private ElementsCollection getFilteredRows(Condition... conditions) {
        ArrayList<WebElement> resulrRows = new ArrayList<>();
        ElementsCollection rows = parentElement.$$("tr[role=row]");
        for (SelenideElement row : rows) {
            ElementsCollection columns = row.$$("td[role=gridcell]");
            int i = conditions.length;
            for (SelenideElement column : columns)
                for (Condition condition : conditions)
                    if (column.has(condition))
                        i--;

            if (i <= 0)
                resulrRows.add(row);
        }

        return new ElementsCollection(new WebElementsCollectionWrapper(resulrRows));
    }

    private ElementsCollection getFilteredRows(int columnIndex, Condition... conditions) {
        ArrayList<WebElement> rows = new ArrayList<>();
        ElementsCollection columns = parentElement.$$("tr[role=row] td[role=gridcell]:nth-child(" + columnIndex + ")");
        for (Condition condition : conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@role='row'][1]")); //@data-ri and
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    private ElementsCollection getFilteredRows(String columnName, Condition... conditions) {
        ArrayList<WebElement> rows = new ArrayList<>();
        int columnIndex = getColumnIndex(columnName);
        ElementsCollection columns = parentElement.$$("tr[role=row] td[role=gridcell]:nth-child(" + columnIndex + ")");
        for (Condition condition : conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@data-ri and @role='row'][1]"));
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda kolonlara göre satırları ara")
    public ElementsCollection findRows(Condition... conditions) {
        ElementsCollection rows = getFilteredRows(conditions);
        Allure.addAttachment("Filtereye göre bulunan satırlar", rows.texts().toString());
        return rows;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda kolon tekste göre satırları ara")
    public ElementsCollection findRows(int columnIndex, Condition... conditions) {
        String columnName = getColumnName(columnIndex);
        return getFilteredRows(columnIndex, conditions);
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda kolon tekste göre satırları ara")
    public ElementsCollection findRows(String columnName, Condition... conditions) {
        int columnIndex = getColumnIndex(columnName);
        return getFilteredRows(columnIndex, conditions);
    }

    ElementsCollection getFilteredRows(String... texts) {
        ElementsCollection filtered = getRows();
        for (String text : texts)
            filtered = filtered.filterBy(text(text));
        return filtered;
    }

    @Step("Tabloda tekste göre satırları ara")
    public ElementsCollection findRowsByText(String... texts) {
        ElementsCollection filtered = getFilteredRows(texts);
        Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
        return filtered;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param texts
     * @return
     */
    @Step("Tabloda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPagesByText(String... texts) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(texts);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(int columnIndex, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnIndex, conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     * aaabb           ccc;ddd         222
     * aaa           ccc;ddd;fff      1111
     * aaa              ddd           1111
     * <p>
     * getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     * getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     * getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Tabloda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(String columnName, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnName, conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }


}
