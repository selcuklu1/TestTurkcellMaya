package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import static pages.pageData.UstMenuData.YonetimSayfalari;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.02.2018
 * Açıklama:
 */
public class DagitimPlaniYonetimiPage extends MainPage {

    public final YonetimSayfalari pageTitle = YonetimSayfalari.DagitimPlaniYonetimi;

    //region Sorgulama ve Filtreleme
    SelenideElement listingForm = $("#dagitimPlaniListingForm");
    SelenideElement filterPanel = $(By.id("dagitimPlaniListingForm:filterPanel"));
    SelenideElement sorgulamaVeFiltrelemeTab = $("#dagitimPlaniListingForm h3[role=tab]");
    SelenideElement adInput = $(By.id("dagitimPlaniListingForm:filterPanel:dagitimPlaniFiltreAd_id"));
    SelenideElement durumSelect = $(By.id("dagitimPlaniListingForm:filterPanel:durumSelectBox"));
    SelenideElement araButton = $(By.id("dagitimPlaniListingForm:filterPanel:dagitimPlaniAra_id"));
    SelenideElement yeniButton = $(By.id("dagitimPlaniListingForm:dagitimPlaniDataTable:dagitimPlaniYeniKayit_id"));
    //public SearchFiltreleme searchFiltreleme = new SearchFiltreleme(page);
    public SearchTable sorgulamaDataTable = new SearchTable($(By.id("dagitimPlaniListingForm:dagitimPlaniDataTable")));

    @Step("Sorgulama ve Filtrelemeyi genişlet")
    public DagitimPlaniYonetimiPage sorgulamayiGenislet(boolean... genislet) {
        SelenideElement element = listingForm.$("h3[role=tab]");
        if (!element.attr("aria-expanded").equalsIgnoreCase(String.valueOf(genislet.length>0?genislet[0]:"true")))
            element.find("a").click();
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ad\" alan bulunur")
    public SelenideElement getSorgulamadaAd(){
        return adInput;
    }

    @Step("Sorgulama ve Filtrelemede \"Ad\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage sorgulamadaAdGir(String text){
        getSorgulamadaAd().setValue(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alan bulunur")
    public SelenideElement getSorgulamadaDurum(){
        return durumSelect;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage sorgulamadaDurumSec(String text){
        getSorgulamadaDurum().selectOption(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" buton bulunur")
    public SelenideElement getAraButton(){
        return araButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" butona tıklanır")
    public DagitimPlaniYonetimiPage ara(){
        getAraButton().click();
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" buton bulunur")
    public SelenideElement getYeniButton(){
        return araButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" butona tıklanır")
    public DagitimPlaniYonetimiPage yeni(){
        getYeniButton().click();
        return this;
    }
    //endregion

    //region Dağıtım Planı Kaydet/Güncelle
    SelenideElement editorForm = $("#dagitimPlaniEditorForm");
    SelenideElement editorFormHeader = $(By.id("dagitimPlaniEditorForm:dagitimPlaniEditorPanel_header"));
    SelenideElement adiInput = $(By.id("dagitimPlaniEditorForm:dagitimPlaniAd_id"));
    SelenideElement aciklamaTextarea = $(By.id("dagitimPlaniEditorForm:dagitimPlaniAciklama_id"));
    BelgenetElement kullanildigiBirimCombolov = comboLov(By.id("dagitimPlaniEditorForm:birimLov_id:LovText"));
    SelenideElement altBirimlerGorsunInput = $(By.id("dagitimPlaniEditorForm:dagitimPlaniAltBirim_id_input"));
    SelenideElement dagitimElemanlariSection = $("fieldset#dagitimPlaniEditorForm\\:fldStDagitimPlanElemsOutput");
    SelenideElement dagitimElemanlariSelect = $("fieldset#dagitimPlaniEditorForm\\:fldStDagitimPlanElemsOutput select");
    BelgenetElement dagitimElemanlariCombolov = comboLov(By.id("dagitimPlaniEditorForm:dagitimPlaniBirimLov:LovText"));
    SelenideElement ekleButton = $(By.id("dagitimPlaniEditorForm:dagitimPlaniEkleButton"));
    SelenideElement kaydetButton = $(By.id("dagitimPlaniEditorForm:dagitimPlaniKaydet_id"));

    public SearchTable kaydetGuncelleDataTable = new SearchTable($(By.id("dagitimPlaniEditorForm:dagitimPlaniDataTable")));
    
    @Step("Dağıtım Planı Kaydet/Güncelle title bulunur")
    public SelenideElement getKaydetGuncelleHeader(){
        return editorFormHeader;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Adı\" alan bulunur")
    public SelenideElement getAdi(){
        return adiInput;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Adı\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage adiGir(String text){
        getAdi().setValue(text);
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Açıklama\" alan bulunur")
    public SelenideElement getAciklama(){
        return aciklamaTextarea;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Açıklama\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage aciklamaGir(String text){
        getAciklama().setValue(text);
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Kullanıldığı Birim\" alan bulunur")
    public BelgenetElement getKullanildigiBirim(){
        return kullanildigiBirimCombolov;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Kullanıldığı Birim\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage kullanildigiBirimSec(String text){
        getKullanildigiBirim().selectLov(text);
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Alt Birimler Görsün\" alan bulunur")
    public SelenideElement getAltBirimlerGorsun(){
        return altBirimlerGorsunInput;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Alt Birimler Görsün\" alanı \"{enable}\" yapilir")
    public DagitimPlaniYonetimiPage altBirimlerGorsunSec(boolean enable){
        if (enable != getAltBirimlerGorsun().toWebElement().isSelected())
            clickJs(getAltBirimlerGorsun().toWebElement());
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Dağıtım Elemanları\"'da select alan bulunur")
    public SelenideElement getDagitimElemanlariSelect(){
        return dagitimElemanlariSelect;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Dağıtım Elemanları\"'da select alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage dagitimElemanlariSelecSec(String text){
        getDagitimElemanlariSelect().selectOption(text);
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Dağıtım Elemanları\"'da Lov alan bulunur")
    public BelgenetElement getDagitimElemanlariCombolov(){
        return dagitimElemanlariCombolov;
    }

    @Step("Dağıtım Planı Kaydet/Güncellede \"Dağıtım Elemanları\"'da Lov alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage dagitimElemanlariCombolovSec(String text){
        getDagitimElemanlariCombolov().selectLov(text);
        return this;
    }

    @Step("Dağıtım Planı \"Ekle\" buton bulunur")
    public SelenideElement getEkleButton(){
        return ekleButton;
    }

    @Step("Dağıtım Planı \"Ekle\" butona tıklanır")
    public DagitimPlaniYonetimiPage ekle(){
        getEkleButton().pressEnter();
        return this;
    }

    @Step("Dağıtım Planı \"Kaydet\" buton bulunur")
    public SelenideElement getKaydetButton(){
        return kaydetButton;
    }

    @Step("Dağıtım Planı \"Kaydet\" butona tıklanır")
    public DagitimPlaniYonetimiPage kaydet(){
        getKaydetButton().pressEnter();
        return this;
    }

    //endregion
    


    public DagitimPlaniYonetimiPage openPage() {
        ustMenu(pageTitle);
        pageHeader().getPageTitle().shouldHave(text(pageTitle.getName()));
        return this;
    }

    @Step("Sayfayı kapat")
    public DagitimPlaniYonetimiPage closePage() {
        pageHeader().closePage();
        confirmDialog().confirmEvetTikla();
        return this;
    }

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(listingForm);
    }


}
