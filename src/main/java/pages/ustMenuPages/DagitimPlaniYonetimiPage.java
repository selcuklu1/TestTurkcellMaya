package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.SearchTable;
import pages.pageComponents.TuzelKisiEkleDialog;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
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

    public By deleteButtonLocator = By.xpath("descendant::button[span[contains(@class,'delete-icon')]]");
    public By updteButtonLocator = By.xpath("descendant::button[span[contains(@class,'update-icon')]]");
    public By copyButtonLocator = By.cssSelector("button[id$='dagitimPlaniKopyala_id']");
    public By toPassiveButtonLocator = By.xpath("descendant::button[span[contains(@class,'to-passive-status-icon')]]");
    public By toActiveButtonLocator = By.xpath("descendant::button[span[contains(@class,'to-active-status-icon')]]");
    //public SearchFiltreleme searchFiltreleme = new SearchFiltreleme(page);
    public SearchTable sorgulamaDataTable = new SearchTable($(By.id("dagitimPlaniListingForm:dagitimPlaniDataTable")));
    public SearchTable dagitimPlaniDataTable = new SearchTable($(By.id("dagitimPlaniEditorForm:dagitimPlaniDataTable")));
    //region Sorgulama ve Filtreleme
    SelenideElement form = $("#dagitimPlaniListingForm");
    SelenideElement filterPanel = $(By.id("dagitimPlaniListingForm:filterPanel"));
    SelenideElement sorgulamaVeFiltrelemeTab = $("#dagitimPlaniListingForm h3[role=tab]");
    SelenideElement adInput = $(By.id("dagitimPlaniListingForm:filterPanel:dagitimPlaniFiltreAd_id"));
    SelenideElement durumSelect = $(By.id("dagitimPlaniListingForm:filterPanel:durumSelectBox"));
    SelenideElement araButton = $(By.id("dagitimPlaniListingForm:filterPanel:dagitimPlaniAra_id"));
    SelenideElement yeniButton = $(By.id("dagitimPlaniListingForm:dagitimPlaniDataTable:dagitimPlaniYeniKayit_id"));
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
    SelenideElement tuzelKisiAramaDetaylari = $(By.id("dagitimPlaniEditorForm:dagitimPlaniTuzelKisiEkle_id"));

    @Step("Sorgulama ve Filtrelemeyi genişlet")
    public DagitimPlaniYonetimiPage sorgulamayiGenislet(boolean... genislet) {
        SelenideElement element = form.$("h3[role=tab]").shouldBe(visible);

        if (!element.attr("aria-expanded").equalsIgnoreCase(String.valueOf(genislet.length > 0 ? genislet[0] : "true")))
            element.find("a").click();
        return this;
    }
    //endregion

    @Step("Sorgulama ve Filtrelemede \"Ad\" alan bulunur")
    public SelenideElement getSorgulamadaAd() {
        return adInput;
    }

    @Step("Sorgulama ve Filtrelemede \"Ad\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage sorgulamadaAdGir(String text) {
        sorgulamayiGenislet();
        getSorgulamadaAd().setValue(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alan bulunur")
    public SelenideElement getSorgulamadaDurum() {
        return durumSelect;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage sorgulamadaDurumSec(String text) {
        sorgulamayiGenislet();
        getSorgulamadaDurum().selectOption(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" buton bulunur")
    public SelenideElement getAraButton() {
        return araButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" butona tıklanır")
    public DagitimPlaniYonetimiPage ara() {
        sorgulamayiGenislet();
        getAraButton().click();
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" buton bulunur")
    public SelenideElement getYeniButton() {
        return yeniButton;
    }

    @Step("Sorgulama ve Filtrelemede \"Yeni\" butona tıklanır")
    public DagitimPlaniYonetimiPage yeni() {
        getYeniButton().pressEnter();
        return this;
    }

    @Step("Guncelle")
    public DagitimPlaniYonetimiPage guncelle() {
        sorgulamaDataTable.getFoundRow().$(updteButtonLocator).click();
        return this;
    }

    @Step("Kopyala")
    public DagitimPlaniYonetimiPage kopyala() {
        sorgulamaDataTable.getFoundRow().$(copyButtonLocator).click();
        return this;
    }

    public boolean aktifMi(){
        return sorgulamaDataTable.getFoundRow().$(toPassiveButtonLocator).exists();
    }

    @Step("Aktif olmalı")
    public DagitimPlaniYonetimiPage aktifKotrolu(){
        //sorgulamaDataTable.getFoundRow().$(toPassiveButtonLocator).shouldBe(visible);
        Assert.assertTrue(aktifMi(), "Bulunan kayıt aktif olmalı (toPassiveButton bulunmlı)");
        return this;
    }

    @Step("Pasif Yap")
    public DagitimPlaniYonetimiPage pasifYap() {
        sorgulamaDataTable.getFoundRow().$(toPassiveButtonLocator).click();
        return this;
    }

    @Step("Aktif Yap")
    public DagitimPlaniYonetimiPage aktifYap() {
        sorgulamaDataTable.getFoundRow().$(toActiveButtonLocator).click();
        return this;
    }

    @Step("Dağıtım Planı Kaydet/Güncelle title bulunur")
    public SelenideElement getKaydetGuncelleHeader() {
        return editorFormHeader;
    }

    //Dağıtım Planı Kaydet/Güncellede
    @Step("\"Adı\" alan bulunur")
    public SelenideElement getAdi() {
        return adiInput;
    }

    @Step("\"Adı\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage adiGir(String text) {
        getAdi().setValue(text);
        return this;
    }

    @Step("\"Açıklama\" alan bulunur")
    public SelenideElement getAciklama() {
        return aciklamaTextarea;
    }

    @Step("\"Açıklama\" alana \"{text}\" girilir")
    public DagitimPlaniYonetimiPage aciklamaGir(String text) {
        getAciklama().setValue(text);
        return this;
    }

    @Step("\"Kullanıldığı Birim\" alan bulunur")
    public BelgenetElement getKullanildigiBirim() {
        return kullanildigiBirimCombolov;
    }

    @Step("\"Kullanıldığı Birim\" alanda \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage kullanildigiBirimSec(String... text) {
        getKullanildigiBirim().selectExactLov(text);
        return this;
    }

    @Step("\"Kullanıldığı Birim\" alanı temizle")
    public DagitimPlaniYonetimiPage kullanildigiBirimTemizle() {
        getKullanildigiBirim().clearAllSelectedItems();
        return this;
    }

    //@Step("Dağıtım Planı Kaydet/Güncellede \"Alt Birimler Görsün\" alan bulunur")
    public SelenideElement getAltBirimlerGorsun() {
        return altBirimlerGorsunInput;
    }

    @Step("\"Alt Birimler Görsün\" alanı \"{enable}\" yapilir")
    public DagitimPlaniYonetimiPage altBirimlerGorsunSec(boolean enable) {
        if (enable != getAltBirimlerGorsun().toWebElement().isSelected())
            clickJs(getAltBirimlerGorsun().toWebElement());
        return this;
    }

    @Step("\"Dağıtım Elemanları\"'da tipi alan bulunur")
    public SelenideElement getDagitimElemanlariSelect() {
        return dagitimElemanlariSelect;
    }

    @Step("\"Dağıtım Elemanları\" tipi \"{text}\" seçilir")
    public DagitimPlaniYonetimiPage dagitimElemanlariTipiSec(String text) {
        getDagitimElemanlariSelect().selectOption(text);
        return this;
    }

    @Step("\"Dağıtım Elemanları\" Lov alan bulunur")
    public BelgenetElement getDagitimElemanlariCombolov() {
        return dagitimElemanlariCombolov;
    }

    @Step("\"Dağıtım Elemanları\"da \"{dagitimElemani}\" seçilir")
    public DagitimPlaniYonetimiPage dagitimElemanlariSec(String... dagitimElemani) {
        getDagitimElemanlariCombolov().selectLov(dagitimElemani);
        /*getSecilenDagitimElemaniSilButton(dagitimElemani, "bulunur").shouldBe(visible);
        getSecilenDagitimElemaniGuncelleButton(dagitimElemani, "bulunur").shouldBe(visible);*/
        return this;
    }

    @Step("Tüzel Kişi arama detayları")
    public TuzelKisiEkleDialog tuzelKisiAramaDetaylari(){
        tuzelKisiAramaDetaylari.click();
        return new TuzelKisiEkleDialog();
    }

    @Step("Dağıtım Planı \"Ekle\" buton bulunur")
    public SelenideElement getEkleButton() {
        return ekleButton;
    }

    @Step("Dağıtım Planı \"Ekle\" butona tıklanır")
    public DagitimPlaniYonetimiPage ekle(Condition... checkInListConditions) {
        getEkleButton().pressEnter();
        if (checkInListConditions.length > 0)
            dagitimPlaniDataTable.findRows(checkInListConditions).shouldHaveSize(1);
        return this;
    }

    @Step("Dağıtım Planı \"Kaydet\" buton bulunur")
    public SelenideElement getKaydetButton() {
        return kaydetButton;
    }

    @Step("Dağıtım Planı \"Kaydet\" butona tıklanır")
    public DagitimPlaniYonetimiPage kaydet() {
        getKaydetButton().pressEnter();
        return this;
    }

    public SearchTable getDagitimPlaniListesi() {
        return dagitimPlaniDataTable;
    }

    @Step("Dağıtım Plani listesinde aranır")
    public DagitimPlaniYonetimiPage dagitimPlaniListesindeAra(Condition... conditions) {
        dagitimPlaniDataTable.findRows(conditions);
        return this;
    }

    @Step("Kayır silinir")
    public DagitimPlaniYonetimiPage sil(Condition... silenecekKayit) {
        dagitimPlaniDataTable.getFoundRow().$(deleteButtonLocator).shouldBe(visible).pressEnter();
        dagitimPlaniDataTable.findRows(silenecekKayit).shouldHaveSize(0);
        return this;
    }

    @Step("Sil buton bulunur")
    public SelenideElement getSilButton() {
        return dagitimPlaniDataTable.getFoundRow().$(deleteButtonLocator);
    }

    @Step("Guncelle buton bulunur")
    public SelenideElement getGuncelleButton() {
        return dagitimPlaniDataTable.getFoundRow().$(updteButtonLocator);
    }

    @Step("Guncelle buton tıklanır")
    public DagitimHitapDuzenle guncelleTikla() {
        getGuncelleButton().shouldHave(visible).pressEnter();
        return new DagitimHitapDuzenle(form.$x("ancestor::div[contains(@id,'window') and contains(@id,'Dialog')]"));
    }


    /*@Step("Listeden Çıkart buton bulunur")
    public SelenideElement getDagitimElemanlariCombolovListedenCikartButton(int... index) {
        return dagitimElemanlariCombolov.getSelectedItems().get(index.length > 0 ? index[0] : 0).$(deleteButtonLocator);
    }

    @Step("Dağıtım Hitap Düzenleme buton bulunur")
    public SelenideElement getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton(int... index) {
        return dagitimElemanlariCombolov.getSelectedItems().get(index.length > 0 ? index[0] : 0).$(updteButtonLocator);
    }*/

    //Seçilen Dağıtım Elemenda Guncelle ve Sil buttonlari
    public SelenideElement getDagitimHitapDuzenlemeSilButton(String dagitimPlani){
        return dagitimElemanlariCombolov.getSelectedItems().filterBy(text(dagitimPlani)).first().$(deleteButtonLocator);
    }

    @Step("\"{dagitimPlani}\" dağıtım elemanında sil butonu {stepDescription}")
    public SelenideElement getDagitimHitapDuzenlemeSilButton(String dagitimPlani, String stepDescription){
        return getDagitimHitapDuzenlemeSilButton(dagitimPlani);
    }

    public SelenideElement getDagitimHitapDuzenlemeGuncelleButton(String dagitimPlani){
        return dagitimElemanlariCombolov.getSelectedItems().filterBy(text(dagitimPlani)).first().$(updteButtonLocator);
    }

    @Step("\"{dagitimPlani}\" dağıtım elemanında update butonu {stepDescription}")
    public SelenideElement getDagitimHitapDuzenlemeGuncelleButton(String dagitimPlani, String stepDescription){
        return getDagitimHitapDuzenlemeGuncelleButton(dagitimPlani);
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
        return new UstMenuPageHeader(form);
    }

    @Step("\"{adi}\" dağıtım planı bul ve güncelle butona tıkla")
    public DagitimPlaniYonetimiPage bulVeGuncelleTikla(String adi) {
        sorgulamadaDurumSec("Sadece Aktifler")
                .sorgulamadaAdGir(adi)
                .ara()
                .sorgulamaDataTable.findRows(text(adi)).shouldHave(sizeGreaterThan(0));
        guncelle()
                .getAdi().shouldHave(exactValue(adi));
        return this;
    }


    @Step("\"{dagitimElemanlariTipi}\" dağıtım elemanı eklenir")
    public DagitimPlaniYonetimiPage dagitimElemanlariEkle(String dagitimElemanlariTipi, String... dagitimElemanlari) {

        dagitimElemanlariTipiSec(dagitimElemanlariTipi);
        dagitimElemanlariSec(dagitimElemanlari);

        getDagitimHitapDuzenlemeSilButton(dagitimElemanlari[0], "bulunur").shouldBe(visible);
        getDagitimHitapDuzenlemeGuncelleButton(dagitimElemanlari[0],"bulunur").shouldBe(visible);
        ekle();
        dagitimPlaniDataTable.findRows(text(dagitimElemanlariTipi), text(dagitimElemanlari[0])).shouldHaveSize(1);
        getSilButton().shouldBe(visible);
        getGuncelleButton().shouldBe(visible);
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve adres seçilir")
    public DagitimPlaniYonetimiPage dagitiminElemaniEkleVeAdresSec(String dagitimElemanlariTipi, String dagitimElemanlari, String adres, String evraktaGorunecekHitap) {
        dagitimElemanlariEkle(dagitimElemanlariTipi, dagitimElemanlari);
        DagitimHitapDuzenle dagitimHitapDuzenle = guncelleTikla();
        dagitimHitapDuzenle.adresHitaptaGorunsunSec(true).adresGirilir(adres, evraktaGorunecekHitap).kaydet();;
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve özel hitap seçilir")
    public DagitimPlaniYonetimiPage dagitiminElemaniEkleVeOzelHitapSec(String dagitimElemanlariTipi, String dagitimElemanlari, String evraktaGorunecekHitap) {
        dagitimElemanlariEkle(dagitimElemanlariTipi, dagitimElemanlari);
        DagitimHitapDuzenle dagitimHitapDuzenle = guncelleTikla();
        dagitimHitapDuzenle.ozelHitapSec(true).ozelHitapGirilir(evraktaGorunecekHitap).kaydet();
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve ek güncellir")
    public DagitimPlaniYonetimiPage dagitiminElemaniEkleVeEkGuncelle(String dagitimElemanlariTipi, String dagitimElemanlari, String ek, String evraktaGorunecekHitap) {
        dagitimElemanlariEkle(dagitimElemanlariTipi, dagitimElemanlari);
        DagitimHitapDuzenle dagitimHitapDuzenle = guncelleTikla();
        dagitimHitapDuzenle.ekGuncelle(dagitimElemanlari, ek, evraktaGorunecekHitap).kaydet();
        return this;
    }

    @Step("Dağıtım Planı oluştur")
    public DagitimPlaniYonetimiPage dagitimPlaniOlustur(String planAdi, String aciklama, String kullanildigiBirim, boolean altBirimlerGorsun, String dagitimElemanlariTipi, String dagitimElemanlari) {
        yeni();
        adiGir(planAdi);
        aciklamaGir(aciklama);
        kullanildigiBirimSec(kullanildigiBirim);
        altBirimlerGorsunSec(altBirimlerGorsun);
        dagitimElemanlariEkle(dagitimElemanlariTipi, dagitimElemanlari);
        kaydet().islemMesaji().basariliOlmali();
        return this;
    }

    @Step("Dağıtım Planı oluştur")
    public DagitimPlaniYonetimiPage dagitimPlaniOlustur(String planAdi, String aciklama, String kullanildigiBirim, boolean altBirimlerGorsun, Map<String,String> dagitimElemanlari) {
        yeni();
        adiGir(planAdi);
        aciklamaGir(aciklama);
        kullanildigiBirimSec(kullanildigiBirim);
        altBirimlerGorsunSec(altBirimlerGorsun);
        dagitimElemanlari.forEach((k,v)-> dagitimElemanlariEkle(k, v));
        kaydet().islemMesaji().basariliOlmali();
        return this;
    }

    @Step("Dağıtım Planı oluştur")
    public DagitimPlaniYonetimiPage dagitimPlaniOlustur(String planAdi, String aciklama, String kullanildigiBirim, boolean altBirimlerGorsun, String[][] dagitimElemanlari) {
        yeni();
        adiGir(planAdi);
        aciklamaGir(aciklama);
        kullanildigiBirimSec(kullanildigiBirim);
        altBirimlerGorsunSec(altBirimlerGorsun);
        for (String[] d:dagitimElemanlari) {
            dagitimElemanlariEkle(d[0],d[1], d[2]);
        }
        kaydet().islemMesaji().basariliOlmali();
        return this;
    }
}
