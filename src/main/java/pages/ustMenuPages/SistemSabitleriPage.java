package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.Filtreler;
import pages.pageComponents.SearchFiltreleme;
import pages.pageComponents.UstMenuPageHeader;
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
    SelenideElement filterTab = filter.$("h3");
    SelenideElement propertyList = $("#constantPropertiesListingForm\\:constantPropertiesList");
    SelenideElement propertyDatatable = $("id[$='constantPropertiesDataTable']");

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(page);
    }

    public SistemSabitleriPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.SistemSabitleri);
        pageHeader().getPageTitle().shouldHave(text(UstMenuData.YonetimSayfalari.SistemSabitleri.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public void closePage(boolean save) {
        pageHeader().closePage();
        if (save)
            confirmDialog().confirmEvetTikla();
        else
            confirmDialog().confirmHayirTikla();
    }

    SearchFiltreleme searchFiltreleme = new SearchFiltreleme(filter);
    @Step("Sorgulama ve Filtreleme")
    public SearchFiltreleme sorgulamaVeFiltreleme(){
        return searchFiltreleme;
    }

    @Step("")
    public ElementsCollection aramaSonucuBul(String deger){
        return propertyList.$$("div").filterBy(text(deger));
    }


    /*@Step("\"Sorgulama ve Filtreleme\"'i genişlet")
    public SistemSabitleriPage sorgulamaVeFiltrelemeAc() {
        if (filter.$("h3").attr("aria-expanded").equalsIgnoreCase("false"))
            filter.$("h3").find("a").click();
        return this;
    }*/
}
