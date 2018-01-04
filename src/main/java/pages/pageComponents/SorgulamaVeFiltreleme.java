package pages.pageComponents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
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
        SelenideElement label = getFilterPanel().$x("descendant::label[normalize-space(.)='"+ text +"']");
        label.shouldBe(visible);
        return label;
    }

    public SelenideElement getFilterElement(String label){
        SelenideElement filterElement = null;

        //Onay Akış Yönetimi ve Form Şablon Yönetimi sayfada yapı farklı
        if (getFilterPanel().find("span[class='filterElement']").exists())
            return getFilterPanel().$x("descendant::span[@class='filterElement' and label[normalize-space(.)='" + label + "']]");


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
        parentElement = labelElement.$x("ancestor::tbody[1]");
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
        /*SelenideElement button = filterPanel.$$("button").filterBy(visible).filterBy(text(name)).last();
        button.click();*/
        filterPanel.$x("descendant::button[.='"+ name +"']").shouldBe(visible, enabled).click();
        return this;
    }
    //endregion

    @Step("")
    public SearchTable searchTable(){
        return new SearchTable(window);
    }
}
