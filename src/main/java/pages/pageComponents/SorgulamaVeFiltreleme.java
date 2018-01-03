package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementsCollection;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

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
        getFilterPanel().$x("//button[.='"+ name +"']").click();
        return this;
    }
    //endregion


    public SelenideElement getFirstPageButton() {
        return window.$("span[class~='ui-paginator-first']");
    }

    public SelenideElement getPrevPageButton() {
        return window.$("span[class~='ui-paginator-prev']");
    }

    public SelenideElement getNextPageButton() {
        return window.$("span[class~='ui-paginator-next']");
    }

    public SelenideElement getLastPageButton() {
        return window.$("span[class~='ui-paginator-last']");
    }

    public SelenideElement dataTable(){
        return window.$("[id$='DataTable_data']");
    }

    private ElementsCollection getDataTableRows() {
        return dataTable().$$("tr[data-ri][role=row]");
    }

    private SelenideElement getColumn(SelenideElement row, int columnIndex){
        return row.$("td[role=gridcell]");
    }

    @Step("Arama tablosunda satırı ara")
    public SelenideElement findRow(int columnIndex, Condition condition){
        ElementsCollection columns = dataTable().$$("tr[data-ri][role=row] td[role=gridcell]:nth-child("+ columnIndex +")");
        if (columns.size() == 0)
            throw new NotFoundException("Kayıt Bulunamamıştır");

        columns.shouldHave(sizeGreaterThan(0));
        SelenideElement row = columns.filterBy(condition).shouldHave(sizeGreaterThan(0)).first();
        return row;
    }

    private SelenideElement getRowWith(Condition condition) {
        while (true) {
            dataTable().shouldBe(visible);

            ElementsCollection rows = getSearchRows();
            rows.shouldHave(sizeGreaterThan(0));

            for (SelenideElement row : rows) {
                row.shouldBe(visible);
                ElementsCollection filtered = row.$$("[class='searchText']").filterBy(condition);
                if (filtered.size() > 0)
                    return row;
            }

            if (getNextPageButton().has(cssClass("ui-state-disabled")))
                throw new NotFoundException("Satır bulunamadı: filterBy: " + condition.toString());

            getNextPageButton().click();
        }
    }

    @Step("Arama tablosundan satırları al")
    public ElementsCollection getSearchRows() {
        dataTable().shouldBe(visible);
        Allure.addAttachment("Satır sayısı:" + String.valueOf(dataTable().$$("tr[data-ri][role='row']").size()),
                (dataTable().$$("tr[data-ri][role='row']").size() > 0) ? dataTable().$$("tr[data-ri][role='row']").texts().toString() : "");
        takeScreenshot();
        return dataTable().$$("tr[data-ri][role='row']");
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

            if (filtered.size() > 0 || getNextPageButton().has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }

            getNextPageButton().click();
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
            if (filtered.size() > 0 || getNextPageButton().has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
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
            if (filtered.size() > 0 || getNextPageButton().has(cssClass("ui-state-disabled"))) {
                Allure.addAttachment("Filtereye göre bulunan satırlar", filtered.texts().toString());
                return filtered;
            }
            getNextPageButton().click();
        }
    }

    /*@Step("Satırı ara")
    private ElementsCollection findRows(Condition condition){
*//*        ElementsCollection columns = dataTable().$$("tr[data-ri][role=row] td[role=gridcell]");
        if (columns.size() == 0 && dataTable().find(Selectors.byText("Kayıt Bulunamamıştır")).exists()){
            return columns;
        }
        columns.shouldHave(sizeGreaterThan(0));
        ElementsCollection filtered = columns.filterBy(condition);

        for (SelenideElement row:filtered) {
            SelenideElement r = row.$("ancestor::tr[data-ri][role=row]");
            if (!rows.contains(r))
                rows.add(r);
        }
        ElementsCollection collection = new ElementsCollection(rows);

        return columns.filterBy(condition);*//*
    }*/
}
