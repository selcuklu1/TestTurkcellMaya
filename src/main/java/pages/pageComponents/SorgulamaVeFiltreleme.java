package pages.pageComponents;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebElementsCollectionWrapper;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.isTableNavButtonDisabled;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 3.01.2018
 * Açıklama:
 */
public class SorgulamaVeFiltreleme extends BaseLibrary {
    private SelenideElement window;

    /**
     * Ekranın ana form ya da div
     * @param window
     */
    public SorgulamaVeFiltreleme(SelenideElement window) {
        this.window = window;
    }

    //region Filter
    /**
     * Filter alanların oluduğu panel
     * @return
     */
    public SelenideElement getFilterPanel(){
        SelenideElement filterPanel = window.$("div[id$='filterPanel']");
        filterPanel.shouldBe(visible);
        return filterPanel;
    }

    public SelenideElement getSorgulamaVeFiltreleme(){
        return getFilterPanel().$("h3");
    }

    @Step("\"Sorgulama ve Filtreleme\"yi genişlet")
    public SorgulamaVeFiltreleme sorgulamaVeFiltrelemeyiGenislet() {
        SelenideElement element = getSorgulamaVeFiltreleme();
        element.shouldHave(text("Sorgulama ve Filtreleme"));
        if (element.attr("aria-expanded").equalsIgnoreCase("false"))
            element.find("a").click();
        return this;
    }

    @Step("Get label")
    public SelenideElement getLabel(String text){
        SelenideElement label = getFilterPanel().$x("//label[normalize-space(.)='"+ text +"']");
        label.shouldBe(visible);
        return label;
    }

    public SelenideElement getFilterElement(String label){
        SelenideElement filterElement = null;

        //Onay Akış Yönetimi ve Form Şablon Yönetimi sayfada yapı farklı
        if (getFilterPanel().find("span[class='filterElement']").exists())
            return getFilterPanel().$x("//span[@class='filterElement' and label[normalize-space(.)='" + label + "']]");


        log.info("Sorgulama ve Filtreleme: element with span[@class='filterElement'] not found");

        SelenideElement labelElement = getLabel(label);
        //----------------
        //Onay Akışı Yönetimi gibi sayfalar
        //<tr class="ui-widget-content" role="row">
        SelenideElement parentElement = labelElement.parent();
        if (parentElement.getTagName().equals("tr") && parentElement.has(cssClass("ui-widget-content")) && parentElement.has(attribute("role","row")))
            return parentElement;

        //----------------
        //Form Şablon Yönetimi gibi sayfalar
        //tbody parent olacak
        parentElement = labelElement.$x("//ancestor::tbody[1]");
        return parentElement;
    }

    @Step("Get input element with label")
    public SelenideElement getFilterInput(String label){
        SelenideElement parentElement = getFilterElement(label);
        return parentElement.find("input");
    }

    @Step("Get select element with label")
    public SelenideElement getFilterSelect(String label){
        SelenideElement selectElement = getFilterElement(label).$("select");
        return selectElement;
    }

    @Step("Get button elements of labeled field")
    public ElementsCollection getFilterFieldButtons(String label){
        ElementsCollection elements = getFilterElement(label).$$("button");
        return elements;
    }

    @Step("\"Sorgulama ve Filtreleme\"de \"{name}\" alanı doldur")
    public SorgulamaVeFiltreleme filtrelemeAlaniDoldur(String name, CharSequence... keysToSend){
        sorgulamaVeFiltrelemeyiGenislet();
        SelenideElement field = getFilterInput(name);
        for (CharSequence keys:keysToSend) {
            field.sendKeys(keys);
        }
        return this;
    }

    @Step("\"Sorgulama ve Filtreleme\"de \"{name}\" alanı doldur")
    public SorgulamaVeFiltreleme filtrelemeCombolovAlaniDoldur(String name, String value){
        sorgulamaVeFiltrelemeyiGenislet();
        SelenideElement parent = getFilterElement(name);
        comboLov(By.xpath(parent.getSearchCriteria().split("By.xpath:")[1].trim() + "//input"))
            .selectLov(value);
        return this;
    }

    @Step("\"Sorgulama ve Filtreleme\"de \"{fieldName}\"'ın butonu tıkla")
    public SorgulamaVeFiltreleme filtrelemedeAlaninButonuTikla(String fieldName, int index){
        sorgulamaVeFiltrelemeyiGenislet();
        ElementsCollection filterElement = getFilterFieldButtons(fieldName);
        filterElement.shouldHave(sizeGreaterThan(0));
        filterElement.get(index).click();
        return this;
    }

    @Step("\"Sorgulama ve Filtreleme\"de \"{name}\"'butona tıkla")
    public SorgulamaVeFiltreleme filtrelemedeButonaTikla(String name){
        sorgulamaVeFiltrelemeyiGenislet();
        SelenideElement filterPanel = getFilterPanel();
        SelenideElement button = filterPanel.$$("button").filterBy(visible).filterBy(text(name)).last();
        button.click();
//        filterPanel.$x("//button[.='"+ name +"']").shouldBe(visible, enabled).click();
        return this;
    }
    //endregion

    @Step("İlk sayfaya git butonu")
    public SelenideElement getFirstPageButton() {
        return window.$("span[class~='ui-paginator-first']");
    }

    @Step("Önceki sayfaya git butonu")
    public SelenideElement getPrevPageButton() {
        return window.$("span[class~='ui-paginator-prev']");
    }

    @Step("Sonraki sayfaya git butonu")
    public SelenideElement getNextPageButton() {
        return window.$("span[class~='ui-paginator-next']");
    }

    @Step("Son sayfaya git butonu")
    public SelenideElement getLastPageButton() {
        return window.$("span[class~='ui-paginator-last']");
    }

    public SelenideElement getDataTable(){
//        return window.$("[id$='DataTable']");
//        ,[id$='DataTable'] table[role=grid],table[role=treegrid]

        SelenideElement table = window.find("[id$='DataTable']");
        if (table.isDisplayed())
            return table;

        return window.$("div[id$=TreeTable]");
    }

//    [data-ri]

    private SelenideElement getDataTableData(){
        return window.$("[id$='DataTable_data']");
    }

    private ElementsCollection getDataTableRows() {
        return getDataTable().$$("tr[role=row]");
    }

    private SelenideElement getColumn(SelenideElement row, int columnIndex){
        return row.$("td[role=gridcell]");
    }

    public boolean isRowsExist(){
        SelenideElement dataTable = getDataTable();
        ElementsCollection columns = dataTable.$$("tr[role=row] td[role=gridcell]");
        if (columns.size() == 0 || dataTable.find(Selectors.byText("Kayıt Bulunamamıştır")).exists())
            return false;

        return false;
    }

    public ElementsCollection getRows() {
        SelenideElement dataTable = getDataTable();
        dataTable.shouldBe(visible);
        /*Allure.addAttachment("Satır sayısı:" + String.valueOf(dataTable.$$("tr[role='row']").size()),
                (dataTable.$$("tr[role='row']").size() > 0) ? getDataTableData().$$("tr[role='row']").texts().toString() : "");
        takeScreenshot();*/
        return dataTable.$$("tr[role='row']");
    }

    @Step("Kolonun index")
    public int getColumnIndex(String columnName){
        SelenideElement dataTable = getDataTable();
        ElementsCollection columnheaders = dataTable.$$("[id$='DataTable'] th[role='columnheader']");
        columnheaders.filterBy(exactText(columnName)).shouldHave(sizeGreaterThan(0));
        int i = 1;
        for (SelenideElement header:columnheaders) {
            if (header.has(exactText(columnName)))
                break;
            else
                i++;
        }
        Assert.assertTrue(i<= columnheaders.size(), "\""+columnName+"\" isimli kolon bulunmalı");
        Allure.addAttachment("Kolon index", String.valueOf(i));
        return i;
    }

    /**
     *
     * @param index start with 1
     * @return
     */
    @Step("Kolonun ismi")
    public String getColumnName(int index){
        String name = getDataTable().$$("th[role='columnheader']")
                .shouldHave(sizeGreaterThanOrEqual(index))
                .get(index - 1).text().trim();
        Allure.addAttachment("Kolon ismi", name);
        return name;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    private ElementsCollection getFilteredRows(Condition... conditions){
        SelenideElement dataTable = getDataTable();
        ArrayList<WebElement> resulrRows = new ArrayList<>();
        ElementsCollection rows = dataTable.$$("tr[role=row]");
        for (SelenideElement row:rows) {
            ElementsCollection columns = row.$$("td[role=gridcell]");
            int i = conditions.length;
            for (SelenideElement column:columns)
                for (Condition condition:conditions)
                    if (column.has(condition))
                        i--;

            if (i <= 0)
                resulrRows.add(row);
        }

        return new ElementsCollection(new WebElementsCollectionWrapper(resulrRows));
    }

    private ElementsCollection getFilteredRows(int columnIndex, Condition... conditions){
        SelenideElement dataTable = getDataTable();
        ArrayList<WebElement> rows = new ArrayList<>();
        ElementsCollection columns = dataTable.$$("tr[role=row] td[role=gridcell]:nth-child("+ columnIndex +")");
        for (Condition condition:conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@data-ri and @role='row'][1]"));
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    private ElementsCollection getFilteredRows(String columnName, Condition... conditions){
        SelenideElement dataTable = getDataTable();
        ArrayList<WebElement> rows = new ArrayList<>();
        int columnIndex = getColumnIndex(columnName);
        ElementsCollection columns = dataTable.$$("tr[role=row] td[role=gridcell]:nth-child("+ columnIndex +")");
        for (Condition condition:conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@data-ri and @role='row'][1]"));
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda kolonlara göre satırları ara")
    public ElementsCollection findRows(Condition... conditions){
        ElementsCollection rows = getFilteredRows(conditions);
        Allure.addAttachment("Filtereye göre bulunan satırlar", rows.texts().toString());
        return rows;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda kolon tekste göre satırları ara")
    public ElementsCollection findRows(int columnIndex, Condition... conditions){
        String columnName = getColumnName(columnIndex);
        return getFilteredRows(columnIndex, conditions);
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda kolon tekste göre satırları ara")
    public ElementsCollection findRows(String columnName, Condition... conditions){
        int columnIndex = getColumnIndex(columnName);
        return getFilteredRows(columnIndex, conditions);
    }

    private ElementsCollection getFilteredRows(String... texts){
        ElementsCollection filtered = getRows();
        for (String text : texts)
            filtered = filtered.filterBy(text(text));
        return filtered;
    }

    @Step("Arama tablosunda tekste göre satırları ara")
    public ElementsCollection findRowsByText(String... texts){
        ElementsCollection filtered = getFilteredRows(texts);
        Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
        return filtered;
    }

    /**
     * Row'da tüm condition'lar sağlanmalı. Örnek:
     * |    kolon1   |      kolon2    |   kolon3    |
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param texts
     * @return
     */
    @Step("Arama tablosunda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPagesByText(String... texts){
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
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(Condition... conditions){
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
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(int columnIndex, Condition... conditions){
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
     *      aaabb           ccc;ddd         222
     *      aaa           ccc;ddd;fff      1111
     *      aaa              ddd           1111
     *
     *   getFilteredRows(exactText(aaa), text(fff)) ikinci satırı bulur
     *   getFilteredRows(text(aaa), exactText(222)) birinci satırı bulur
     *   getFilteredRows(exactText(aaa), exactText(ddd), exactText(1111)) üçüncü satırı bulur
     *
     * @param conditions
     * @return
     */
    @Step("Arama tablosunda tüm sayfalarda ara")
    public ElementsCollection findRowsInAllPages(String columnName, Condition... conditions){
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