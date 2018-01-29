package pages.pageComponents;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebElementsCollectionWrapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgentCondition.isTableNavButtonDisabled;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 4.01.2018
 * Açıklama:
 */
public class SearchTable {

    private static String rowXpathLocator = "tr[@role='row']";
    private static String rowCssLocator = "tbody > tr[role=row]";
    private static String columnCssLocator = "td[role=gridcell]";


    //region Class Initialization
    private final SelenideElement parentElement;

    public SearchTable(SelenideElement parentElement) {
        this.parentElement = parentElement;
    }

    public SearchTable(By parentElementLocator) {
        this.parentElement = $(parentElementLocator);
    }

    public SearchTable(String parentElementCssLocator) {
        this.parentElement = $(By.cssSelector(parentElementCssLocator));
    }
    //endregion

    @Step
    public SelenideElement getTableHeader() {
        return parentElement.$("thead");
    }

    @Step
    public SelenideElement getTableLabel() {
        return getTableHeader().$("label");
    }

    @Step("İlk sayfaya git butonu bulunur")
    public SelenideElement getFirstPageButton() {
        return parentElement.$("span[class~='ui-paginator-first']");
    }

    @Step("Önceki sayfaya git butonu bulunur")
    public SelenideElement getPrevPageButton() {
        return parentElement.$("span[class~='ui-paginator-prev']");
    }

    @Step("Sonraki sayfaya git butonu bulunur")
    public SelenideElement getNextPageButton() {
        return parentElement.$("span[class~='ui-paginator-next']");
    }

    @Step("Son sayfaya git butonu bulunur")
    public SelenideElement getLastPageButton() {
        return parentElement.$("span[class~='ui-paginator-last']");
    }

    public ElementsCollection getColumnHeaders() {
        //ElementsCollection columnheaders = parentElement.$$("[id$='SearchTable'] th[role='columnheader']");
        ElementsCollection columnheaders = parentElement.$$("thead th[role='columnheader']");
        if (columnheaders.size() == 0)
            columnheaders = parentElement.$$("thead th");
        return columnheaders;
    }

    @Step("Kolonlar bulunur")
    public SearchTable columnHeaderControl(Condition... conditions){
        for (Condition condition:conditions) {
            getColumnHeaders().filterBy(condition).shouldHave(sizeGreaterThan(0));
        }
        return this;
    }

    @Step("Kolon index'i aranır")
    public int getColumnIndex(String columnName) {
        ElementsCollection columnheaders = getColumnHeaders();
        columnheaders.filterBy(exactText(columnName)).shouldHave(sizeGreaterThan(0));
        int i = 0;
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
     * @param index start with 0
     * @return
     */
    @Step("Kolonun ismi alınır")
    public String getColumnName(int index) {
        String name = getColumnHeaders().shouldHave(sizeGreaterThanOrEqual(index))
                .get(index).text().trim();
        Allure.addAttachment("Kolon ismi", name);
        return name;
    }

    //region Buttons. On development...

    //Güncelle button .update-icon  button[id*='update']
    @Step("Güncelle butona tıkla")
    public SearchTable guncelleTikla() {
        foundRow.$("button[id*='update']").shouldBe(visible, enabled).click();
        return this;
    }
    //Havale Kural Listesi - Kopyala button .formSablonKopyala    button[id*='copy']
    @Step("Kopyala butona tıkla")
    public SearchTable kopyalaTikla() {
        foundRow.$("button[id*='copy']").shouldBe(visible, enabled).click();
        return this;
    }
    //Aktif Yap     button .to-active-status-icon
    @Step("Aktif Yap butona tıkla")
    public SearchTable aktifYapTikla() {
        foundRow.$("button .to-active-status-icon").shouldBe(visible, enabled).click();
        return this;
    }
    //Passif Yap button .to-passive-status-icon     button[id*='Pasif']
    @Step("Passif Yap butona tıkla")
    public SearchTable pasifYapTikla() {
        foundRow.$("button[id*='Pasif']").shouldBe(visible, enabled).click();
        return this;
    }
    //Sil button .delete-icon  button[id*='delete']
    @Step("Sil butona tıkla")
    public SearchTable silTikla() {
        foundRow.$("button[id*='delete']").shouldBe(visible, enabled).click();
        return this;
    }
    //Doküman ekle button .document-follow
    @Step("Doküman Ekle tıkla")
    public SearchTable dokumanEkleTikla() {
        foundRow.$("button .document-follow").shouldBe(visible, enabled).click();
        return this;
    }
    //Eposta Seçenekleri button .email-icon
    //button .user-block-icon   User! Bloke Et
    //Rapor Al  button .document-getReport

    @Step
    public SelenideElement getYeniKayitEkleButton() {
//        return parentElement.$x("descendant::button[span[contains(@class,'add-icon')]]");
        return parentElement.$("button .add-icon");
    }

    @Step("Yeni Kayıt Ekle butona tıkla")
    public SearchTable yeniKayitEkleTikla() {
        getYeniKayitEkleButton().shouldBe(visible, enabled).click();
        return this;
    }


    //endregion


    private SelenideElement foundRow;
    private ElementsCollection foundRows;
    private String columnName;
    private int columnIndex = -1;
    private boolean searchInAllPages = false;
    private boolean searchStartFromLast = false;


    public SearchTable foundRow() {
        /*if (foundRow == null)
            if (foundRows == null)
                throw new RuntimeException("Bulunan satır yok");
            else
                foundRow = foundRows.first();*/

        return this;
    }

    public SearchTable foundRows() {
        if (foundRows == null)
            throw new RuntimeException("Bulunan satırlar yok");
        return this;
    }

    public SelenideElement getFoundRow() {
        return foundRow;
    }

    public ElementsCollection getFoundRows() {
        return foundRows;
    }

    public boolean isRowsExist() {
        return !parentElement.find(Selectors.byText("Kayıt Bulunamamıştır")).exists()
                && parentElement.$$(rowCssLocator + " " + columnCssLocator).size() != 0;
    }

    @Step("Arama ayarı: tüm sayfalarda aranır")
    public SearchTable searchInAllPages(boolean searchInAllPages) {
        this.searchInAllPages = searchInAllPages;
        return this;
    }

    @Step("Arama ayarı: son sayfadan başlanır")
    public SearchTable searchStartFromLastPage(boolean searchStartFromLast){
        this.searchStartFromLast = searchStartFromLast;
        return this;
    }

    @Step("Arama ayarı: kolon ismine göre aranır")
    public SearchTable searchByColumnName(String columnName){
        this.columnName = columnName;
        columnIndex = getColumnIndex(columnName);
        return this;
    }

    @Step("Arama ayarı: kolon index'a göre aranır")
    public SearchTable searchByColumnIndex(int columnIndex){
        this.columnIndex = columnIndex;
        columnName = getColumnName(columnIndex);
        return this;
    }

    @Step("Tablodaki satırlar aranır")
    public SearchTable findRows(Condition... conditions) {
        foundRow = null;
        foundRows = null;
        String rowCssLocator = this.rowCssLocator;

        boolean searchByColumn = (columnIndex > -1);

        ArrayList<WebElement> rows = new ArrayList<>();

        //[data-ri] varsa o ana row.
        if(parentElement.$$(rowCssLocator + "[data-ri]").size() > 0)
            rowCssLocator = rowCssLocator + "[data-ri]";

        ElementsCollection collection = searchByColumn ? parentElement.$$(rowCssLocator + " " + columnCssLocator + ":nth-child(" + columnIndex + ")")
        :parentElement.$$(rowCssLocator);

        SelenideElement pageNavigationButton = searchStartFromLast ? getLastPageButton() : getNextPageButton();

        if (searchStartFromLast && pageNavigationButton.exists() && pageNavigationButton.is(not(isTableNavButtonDisabled)))
            pageNavigationButton.click();

        while (true) {
            for (Condition condition : conditions)
                collection = collection.filterBy(condition);

            if (collection.size() > 0 || !searchInAllPages || pageNavigationButton.is(isTableNavButtonDisabled))
                break;

            pageNavigationButton.click();
        }

        //If search by column get column rows
        if (searchByColumn) {
            for (SelenideElement column : collection)
                rows.add(column.$x("ancestor::"+ rowXpathLocator +"[1]"));
            collection = new ElementsCollection(new WebElementsCollectionWrapper(rows));
        }

        Allure.addAttachment("Filtrelere göre bulunan satırlar", collection.texts().toString());
        foundRows = collection;
        foundRow = collection.first();
        return this;
    }

    public SearchTable useFoundRow(int index){
        foundRow = foundRows.get(index);
        return this;
    }

    public SearchTable useFirstFoundRow(){
        /*if (foundRows == null)
            throw new NotFoundException("Satırlar bulunamadı");*/
        foundRow = foundRows.first();
        return this;
    }

    public SearchTable useLastFoundRow(){
        foundRow = foundRows.last();
        return this;
    }

    @Step("Satırdaki kolonlar bulunur")
    public ElementsCollection getColumnValues() {
        return foundRow.$$(columnCssLocator);
    }

    @Step("{columnIndex} kolon bulunur")
    public SelenideElement getColumnValue(int columnIndex) {
        //return foundRows.get((rowIndex.length > 0)? rowIndex[0]:0).$$("td[role=gridcell]").get(columnIndex);
        return foundRow.$$(columnCssLocator).get(columnIndex);
    }

    @Step("{columnName} isimli kolon bulunur")
    public SelenideElement getColumnValue(String columnName) {
        if (columnIndex == -1)
            columnIndex = getColumnIndex(columnName);
        //return foundRows.get((rowIndex.length > 0)? rowIndex[0]:0).$$("td[role=gridcell]").get(columnIndex);
        return foundRow.$$(columnCssLocator).get(columnIndex);
    }

    public ElementsCollection rowsToElementsCollection(){
        return foundRows;
    }

    public SelenideElement rowToSelenideElement(){
        return foundRow;
    }

    public SearchTable should(Condition... condition){
        foundRow.should(condition);
        return this;
    }

    public SearchTable shouldHave(Condition... condition){
        foundRow.shouldHave(condition);
        return this;
    }

    public SearchTable shouldBe(Condition... condition){
        foundRow.shouldBe(condition);
        return this;
    }

    public SearchTable shouldNot(Condition... condition){
        foundRow.shouldNot(condition);
        return this;
    }

    public SearchTable shouldNotHave(Condition... condition){
        foundRow.shouldNotHave(condition);
        return this;
    }

    public SearchTable shouldNotBe(Condition... condition){
        foundRow.shouldNotBe(condition);
        return this;
    }

    public SearchTable shouldHaveSize(int expectedSize) {
        foundRows.shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    public SearchTable shouldBe(CollectionCondition... conditions) {
        foundRows.shouldBe(conditions);
        return this;
    }

    public SearchTable shouldHave(CollectionCondition... conditions) {
        foundRows.shouldHave(conditions);
        return this;
    }


/*
    @Step("Satırları ara")
    public SearchTable findRows(Condition... conditions) {
        foundRows = getFilteredRows(conditions);
        Allure.addAttachment("Filtereye göre bulunan satırlar", foundRows.texts().toString());
        return this;
    }

    @Step("Satırları ara")
    public SearchTable findRows(int columnIndex, Condition... conditions) {
        this.columnIndex = columnIndex;
        columnName = getColumnName(columnIndex);
        foundRows = getFilteredRows(columnIndex, conditions);
        return this;
    }

    @Step("Satırları ara")
    public SearchTable findRows(String columnName, Condition... conditions) {
        columnIndex = getColumnIndex(columnName);
        this.columnName = columnName;
        foundRows = getFilteredRows(columnIndex, conditions);
        return this;
    }

    @Step("Tüm sayfalarda satırları ara")
    public SearchTable findRowsInAllPages(Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    @Step("Tüm sayfalarda satırları ara")
    public SearchTable findRowsInAllPages(int columnIndex, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnIndex, conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    @Step("Tüm sayfalarda satırları ara")
    public SearchTable findRowsInAllPages(String columnName, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnName, conditions);
            if (filtered.size() > 0 || getNextPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    @Step("Tüm sayfalarda satırları ara")
    public SearchTable findRowsInAllPagesByTextFromLast(String... texts) {
        if (getLastPageButton().is(not(isTableNavButtonDisabled)))
            getLastPageButton().click();

        while (true) {
            ElementsCollection filtered = getFilteredRows(texts);
            if (filtered.size() > 0 || getPrevPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getPrevPageButton().click();
        }
    }*/

    //region Find Row


   /*

    @Step("Tüm satırları bul")
    public ElementsCollection getRows() {
//        parentElement.shouldBe(visible);
        *//*Allure.addAttachment("Satır sayısı:" + String.valueOf(searchTable.$$("tr[role='row']").size()),
                (searchTable.$$("tr[role='row']").size() > 0) ? getDataTableData().$$("tr[role='row']").texts().toString() : "");
        takeScreenshot();*//*
        return parentElement.$$("tbody>tr[role='row']");
    }

    private ElementsCollection getFilteredRows(Condition... conditions) {
        ElementsCollection rows = getRows();
        for (Condition condition:conditions)
            rows = rows.filterBy(condition);

        return rows;
    }

    *//**
     * Row'da tüm condition'lar sağlanmalı. Yavaştır!!!!
     * Örnek:
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
     *//*
    private ElementsCollection getFilteredRowsFilterByColumns(Condition... conditions) {
        ArrayList<WebElement> resulrRows = new ArrayList<>();
        ElementsCollection rows = getRows();
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
        ElementsCollection columns = parentElement.$$("tbody tr[role=row] td[role=gridcell]:nth-child(" + columnIndex + ")");
        for (Condition condition : conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@role='row'][1]")); //@data-ri and
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    private ElementsCollection getFilteredRows(String columnName, Condition... conditions) {
        ArrayList<WebElement> rows = new ArrayList<>();
        int columnIndex = getColumnIndex(columnName);
        ElementsCollection columns = parentElement.$$("tbody tr[role=row] td[role=gridcell]:nth-child(" + columnIndex + ")");
        for (Condition condition : conditions)
            columns = columns.filterBy(condition);

        for (SelenideElement column : columns)
            rows.add(column.$x("ancestor::tr[@data-ri and @role='row'][1]"));
        return new ElementsCollection(new WebElementsCollectionWrapper(rows));
    }

    private ElementsCollection getFilteredRows(String... texts) {
        ElementsCollection filtered = getRows();
        for (String text : texts)
            filtered = filtered.filterBy(text(text));
        return filtered;
    }

    //region Find Rows
    *//**
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
     *//*
    @Step("Satırları ara")
    public ElementsCollection findRows(Condition... conditions) {
        ElementsCollection rows = getFilteredRows(conditions);
        Allure.addAttachment("Filtereye göre bulunan satırlar", rows.texts().toString());
        return rows;
    }

    *//**
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
     *//*
    @Step("Belirli kolon tekste göre satırları ara")
    public ElementsCollection findRows(int columnIndex, Condition... conditions) {
        String columnName = getColumnName(columnIndex);
        return getFilteredRows(columnIndex, conditions);
    }

    *//**
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
     *//*
    @Step("Belirli kolon tekste göre satırları ara")
    public ElementsCollection findRows(String columnName, Condition... conditions) {
        int columnIndex = getColumnIndex(columnName);
        return getFilteredRows(columnIndex, conditions);
    }

    @Step("Satırları ara")
    public ElementsCollection findRowsByText(String... texts) {
        ElementsCollection filtered = getFilteredRows(texts);
        Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
        return filtered;
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırları ara")
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

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırları ara")
    public ElementsCollection findRowsInAllPagesByTextFromLast(String... texts) {
        if (getLastPageButton().is(not(isTableNavButtonDisabled)))
            getLastPageButton().click();

        while (true) {
            ElementsCollection filtered = getFilteredRows(texts);
            if (filtered.size() > 0 || getPrevPageButton().is(isTableNavButtonDisabled)) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getPrevPageButton().click();
        }
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırları ara")
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

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırları ara")
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

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırları ara")
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
    //endregion

    //region Find Row
    *//**
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
     *//*
    @Step("Satırı ara")
    public SelenideElement findRow(Condition... conditions) {
        SelenideElement row = getFilteredRows(conditions).shouldHave(sizeGreaterThan(0)).first();
        Allure.addAttachment("Filtereye göre bulunan satır", row.text());
        return row;
    }

    *//**
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
     *//*
    @Step("Belirli kolon tekste göre satırı ara")
    public SelenideElement findRow(int columnIndex, Condition... conditions) {
        String columnName = getColumnName(columnIndex);
        return getFilteredRows(columnIndex, conditions).shouldHave(sizeGreaterThan(0)).first();
    }

    *//**
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
     *//*
    @Step("Belirli kolon tekste göre satırı ara")
    public SelenideElement findRow(String columnName, Condition... conditions) {
        int columnIndex = getColumnIndex(columnName);
        return getFilteredRows(columnIndex, conditions).shouldHave(sizeGreaterThan(0)).first();
    }

    @Step("Satırı ara")
    public SelenideElement findRowByText(String... texts) {
        SelenideElement row = getFilteredRows(texts).shouldHave(sizeGreaterThan(0)).first();
        Allure.addAttachment("Filtereye göre bulunan satırlar", row.text());
        return row;
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırı ara")
    public SelenideElement findRowInAllPagesByText(String... texts) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(texts);
            if (filtered.size() > 0) {
                SelenideElement row = filtered.first();
                Allure.addAttachment("Filtereye göre bulunan satırlar", row.text());
                return row;
            }
            if (getNextPageButton().is(not(isTableNavButtonDisabled)))
                break;

            getNextPageButton().click();
        }
        throw new NotFoundException("Satır bulunamadı:" + texts.toString());
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırı ara")
    public SelenideElement findRowInAllPages(Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(conditions);
            if (filtered.size() > 0) {
                SelenideElement row = filtered.first();
                Allure.addAttachment("Filtereye göre bulunan satırlar", row.text());
                return row;
            }
            if (getNextPageButton().is(not(isTableNavButtonDisabled)))
                break;

            getNextPageButton().click();
        }
        throw new NotFoundException("Satır bulunamadı:" + conditions.toString());
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırı ara")
    public SelenideElement findRowInAllPages(int columnIndex, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnIndex, conditions);
            if (filtered.size() > 0) {
                SelenideElement row = filtered.first();
                Allure.addAttachment("Filtereye göre bulunan satırlar", row.text());
                return row;
            }
            if (getNextPageButton().is(not(isTableNavButtonDisabled)))
                break;

            getNextPageButton().click();
        }
        throw new NotFoundException("Satır bulunamadı:" + conditions.toString());
    }

    *//**
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
     *//*
    @Step("Tüm sayfalarda satırı ara")
    public SelenideElement findRowInAllPages(String columnName, Condition... conditions) {
        while (true) {
            ElementsCollection filtered = getFilteredRows(columnName, conditions);
            if (filtered.size() > 0) {
                SelenideElement row = filtered.first();
                Allure.addAttachment("Filtereye göre bulunan satırlar", row.text());
                return row;
            }
            if (getNextPageButton().is(not(isTableNavButtonDisabled)))
                break;

            getNextPageButton().click();
        }
        throw new NotFoundException("Satır bulunamadı:" + conditions.toString());
    }
    //endregion

    @Step("")
    public ElementsCollection getColumnValues(SelenideElement row) {
        return row.$$("td[role=gridcell]");
    }

    @Step("")
    public SelenideElement getColumnValue(SelenideElement row, int columnIndex) {
        return row.$$("td[role=gridcell]").get(columnIndex);
    }

    @Step("")
    public SelenideElement getColumnValue(SelenideElement row, String columnName) {
        int index = getColumnIndex(columnName);
        return row.$$("td[role=gridcell]").get(index - 1);
    }
*/
}
