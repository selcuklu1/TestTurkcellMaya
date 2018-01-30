package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.SearchFiltreleme;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgentCondition;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 17.01.2018
 * Açıklama:
 */
public class SistemSabitleriPage extends MainPage {
    SelenideElement page = $("#constantPropertiesListingForm");
    SelenideElement filter = $("#constantPropertiesListingForm\\:accordionId");
    SelenideElement propertyList = $("#constantPropertiesListingForm\\:constantPropertiesList");
    SearchFiltreleme searchFiltreleme = new SearchFiltreleme(filter);

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(page);
    }

    public SistemSabitleriPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.SistemSabitleri);
        pageHeader().getPageTitle().shouldHave(text(UstMenuData.YonetimSayfalari.SistemSabitleri.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public void closePage() {
        pageHeader().closePage();
        confirmDialog().getConfirmButton("Kapat");
    }

    @Step("Sistem Sabitleri sayfayı bul")
    public SelenideElement getPage() {
        return page;
    }

    @Step("Sorgulama ve Filtreleme")
    public SearchFiltreleme sorgulamaVeFiltreleme() {
        return searchFiltreleme;
    }

    @Step("Tüm sekmeleri bul")
    public ElementsCollection getSistemSabitleriTabs() {
        return page.$$("tbody[id='constantPropertiesListingForm:constantPropertiesList_data']>tr");
    }

    @Step("{tabName} sekmeyi bul")
    public SelenideElement getSistemSabitleriTab(String tabName) {
        return page.$x("(descendant::h3[.='" + tabName + "'])[1]");
    }

    @Step("{tabName} sekmeyi aç")
    public SistemSabitleriPage openSistemSabitleriTab(String tabName) {
        SelenideElement tab = getSistemSabitleriTab(tabName);
        if (tab.attr("aria-expanded").equalsIgnoreCase("false"))
            tab.click();
        return this;
    }

    @Step("{tabName} sekmenin Sistem Sabitleri Listesini bul")
    public SearchTable getSistemSabitleriList(String tabName) {
        return new SearchTable(getSistemSabitleriTab(tabName).$x("ancestor::tr[@data-ri]//ancestor::tr[1]//div[contains(@id,'constantPropertiesDataTable')]"));
    }


    @Step("Arama sonucu bul")
    private ElementsCollection aramaSonucuBul(String propertyName) {
        ElementsCollection col = propertyList.$$("div").filterBy(BelgentCondition.innerText(propertyName));
        if (col.size() > 0 &&
                col.first().$x("ancestor::div/h3").attr("aria-expanded").equalsIgnoreCase("false"))
            col.first().$x("ancestor::div/h3").click();

        return propertyList.$$("div").filterBy(BelgentCondition.innerText(propertyName));
    }

    @Step("Arama sonucu bul")
    public SearchTable getPropertyTable(String propertyName) {
        SelenideElement table = aramaSonucuBul(propertyName).first().$x("ancestor::table[1]");
        return new SearchTable(table);
    }

    @Step("Arama sonucu bul")
    public SelenideElement getPropertyRow(String propertyName) {
        return aramaSonucuBul(propertyName).first().$x("ancestor::tr[@data-ri][1]");
    }


}
