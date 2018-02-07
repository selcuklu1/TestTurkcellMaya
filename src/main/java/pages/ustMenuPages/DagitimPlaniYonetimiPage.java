package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import static pages.pageData.UstMenuData.YonetimSayfalari;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.02.2018
 * Açıklama:
 */
public class DagitimPlaniYonetimiPage extends MainPage {

    public final YonetimSayfalari pageTitle = YonetimSayfalari.DagitimPlaniYonetimi;

    public By deleteButtonLocator = By.xpath("descendant::button[span[contains(@class,'delete-icon')]]");
    public By updteButtonLocator = By.xpath("descendant::button[span[contains(@class,'update-icon')]]");
    public By copyButtonLocator = By.cssSelector("button[id$='dagitimPlaniKopyala_id']");
    public By toPassiveButtonLocator = By.xpath("descendant::button[span[contains(@class,'to-passive-status-icon')]]");
    public By toActiveButtonLocator = By.xpath("descendant::button[span[contains(@class,'to-active-status-icon')]]");

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
        sorgulamayiGenislet();
        getSorgulamadaAd().setValue(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alan bulunur")
    public SelenideElement getSorgulamadaDurum(){
        return durumSelect;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage sorgulamadaDurumSec(String text){
        sorgulamayiGenislet();
        getSorgulamadaDurum().selectOption(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" buton bulunur")
    public SelenideElement getAraButton(){
        return araButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" butona tıklanır")
    public DagitimPlaniYonetimiPage ara(){
        sorgulamayiGenislet();
        getAraButton().click();
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" buton bulunur")
    public SelenideElement getYeniButton(){
        return yeniButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" butona tıklanır")
    public DagitimPlaniYonetimiPage yeni(){
        getYeniButton().pressEnter();
        return this;
    }

    @Step("Guncelle butona tıklanır")
    public DagitimPlaniYonetimiPage sorgulamaDataTableGuncelleButonaTikla() {
        sorgulamaDataTable.getFoundRow().$(updteButtonLocator).click();
        return this;
    }

    @Step("Kopyala butona tıklanır")
    public DagitimPlaniYonetimiPage sorgulamaDataTableKopyalaButonaTikla() {
        sorgulamaDataTable.getFoundRow().$(copyButtonLocator).click();
        return this;
    }

    @Step("Pasif Yap butona tıklanır")
    public DagitimPlaniYonetimiPage sorgulamaDataTablePasifYapButonaTikla() {
        sorgulamaDataTable.getFoundRow().$(toPassiveButtonLocator).click();
        return this;
    }

    @Step("Aktif Yap butona tıklanır")
    public DagitimPlaniYonetimiPage sorgulamaDataTableAktifYapButonaTikla() {
        sorgulamaDataTable.getFoundRow().$(toActiveButtonLocator).click();
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
    //dagitimElemanlariSelect değere göre dagitimElemanlariCombolov id değişiyor
    BelgenetElement dagitimElemanlariCombolov = comboLov("fieldset#dagitimPlaniEditorForm\\:fldStDagitimPlanElemsOutput [id$='LovText']");
    SelenideElement ekleButton = $(By.id("dagitimPlaniEditorForm:dagitimPlaniEkleButton"));
    SelenideElement kaydetButton = $(By.id("dagitimPlaniEditorForm:dagitimPlaniKaydet_id"));

    public SearchTable kaydetGuncelleDataTable = new SearchTable($(By.id("dagitimPlaniEditorForm:dagitimPlaniDataTable")));
    
    @Step("Dağıtım Planı Kaydet/Güncelle title bulunur")
    public SelenideElement getKaydetGuncelleHeader(){
        return editorFormHeader;
    }

    //Dağıtım Planı Kaydet/Güncellede
    @Step("\"Adı\" alan bulunur")
    public SelenideElement getAdi(){
        return adiInput;
    }

    @Step("\"Adı\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage adiGir(String text){
        getAdi().setValue(text);
        return this;
    }

    @Step("\"Açıklama\" alan bulunur")
    public SelenideElement getAciklama(){
        return aciklamaTextarea;
    }

    @Step("\"Açıklama\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage aciklamaGir(String text){
        getAciklama().setValue(text);
        return this;
    }

    @Step("\"Kullanıldığı Birim\" alan bulunur")
    public BelgenetElement getKullanildigiBirim(){
        return kullanildigiBirimCombolov;
    }

    @Step("\"Kullanıldığı Birim\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage kullanildigiBirimSec(String... text){
        getKullanildigiBirim().selectLov(text);
        return this;
    }

    @Step("\"Kullanıldığı Birim\" alanı temizle")
    public DagitimPlaniYonetimiPage kullanildigiBirimTemizle(){
        getKullanildigiBirim().clearAllSelectedItems();
        return this;
    }

    //@Step("Dağıtım Planı Kaydet/Güncellede \"Alt Birimler Görsün\" alan bulunur")
    public SelenideElement getAltBirimlerGorsun(){
        return altBirimlerGorsunInput;
    }

    @Step("\"Alt Birimler Görsün\" alanı \"{enable}\" yapilir")
    public DagitimPlaniYonetimiPage altBirimlerGorsunSec(boolean enable){
        if (enable != getAltBirimlerGorsun().toWebElement().isSelected())
            clickJs(getAltBirimlerGorsun().toWebElement());
        return this;
    }

    @Step("\"Dağıtım Elemanları\"'da tipi alan bulunur")
    public SelenideElement getDagitimElemanlariSelect(){
        return dagitimElemanlariSelect;
    }

    @Step("\"Dağıtım Elemanları\" tipi \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage dagitimElemanlariSelecSec(String text){
        getDagitimElemanlariSelect().selectOption(text);
        return this;
    }

    @Step("\"Dağıtım Elemanları\" Lov alan bulunur")
    public BelgenetElement getDagitimElemanlariCombolov(){
        return dagitimElemanlariCombolov;
    }

    @Step("\"Dağıtım Elemanları\"da \"{text}\" seçilir")
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

    /*@Step("Sil buton")
    public By getSilButton() {
        return deleteButtonLocator;
    }

    @Step("Guncelle buton")
    public By getGuncelleButton() {
        return updteButtonLocator;
    }

    @Step("Listeden Çıkart buton")
    public By getListedenCikartButton() {
        return deleteButtonLocator;
    }

    @Step("Dağıtım Hitap Düzenleme buton")
    public By getDagitimHitapDuzenlemeButton() {
        return updteButtonLocator;
    }*/

    @Step("Sil buton bulunur")
    public SelenideElement getSilButton() {
        return kaydetGuncelleDataTable.getFoundRow().$(deleteButtonLocator);
    }

    @Step("Guncelle buton bulunur")
    public SelenideElement getGuncelleButton() {
        return kaydetGuncelleDataTable.getFoundRow().$(updteButtonLocator);
    }

    @Step("Guncelle buton tıklanır")
    public DagitimHitapDuzenle guncelleTikla() {
        getGuncelleButton().shouldHave(visible).pressEnter();
        return new DagitimHitapDuzenle(listingForm.$x("ancestor::div[contains(@id,'window') and contains(@id,'Dialog')]"));
    }

    @Step("Listeden Çıkart buton bulunur")
    public SelenideElement getDagitimElemanlariCombolovListedenCikartButton(int... index) {
        return dagitimElemanlariCombolov.getSelectedItems().get(index.length>0?index[0]:0).$(deleteButtonLocator);
    }

    @Step("Dağıtım Hitap Düzenleme buton bulunur")
    public SelenideElement getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton(int... index) {
        return dagitimElemanlariCombolov.getSelectedItems().get(index.length>0?index[0]:0).$(updteButtonLocator);
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

    @Step("\"{adi}\" dağıtım planı bul ve güncelle butona tıkla")
    public DagitimPlaniYonetimiPage araVeGuncelleTiklanir(String adi){
        sorgulamadaDurumSec("Sadece Aktifler")
                .sorgulamadaAdGir(adi)
                .ara()
                .sorgulamaDataTable.findRows(text(adi)).shouldHave(sizeGreaterThan(0));
        sorgulamaDataTableGuncelleButonaTikla()
            .getAdi().shouldHave(exactValue(adi));
        return this;
    }

}
