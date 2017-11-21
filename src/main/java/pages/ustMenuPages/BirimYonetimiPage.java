package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimYonetimiPage extends MainPage {

    SelenideElement btnAra = $(By.id("birimYonetimiFilterForm:accordionPanel:searchEntitiesButton"));
    SelenideElement btnDuzenle = $(By.cssSelector("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
    ElementsCollection paylastiklarimList = $$("[id='birimYonetimiListingForm:birimTreeTable'] > button[role='row']");
    SelenideElement cmbBirimTuru = $(By.id("birimYonetimiFilterForm:accordionPanel:birimTuruSelectBox"));
    SelenideElement cmbDurum = $(By.id("birimYonetimiFilterForm:accordionPanel:durumSelectBox"));
    SelenideElement btnArti = $(By.id("birimYonetimiListingForm:birimTreeTable:0:addNewBirimButton"));
    SelenideElement cmbGorunurlukTipi = $(By.id("birimYonetimiEditorForm:gorunurlukTipiSelect"));
    SelenideElement chkDisBirim = $(By.id("birimYonetimiEditorForm:disBirimCheckbox_input"));
    SelenideElement txtAd = $(By.id("birimYonetimiEditorForm:adInput"));
    SelenideElement txtKisaAdi =  $(By.id("birimYonetimiEditorForm:kisaAdInput"));
    SelenideElement chkOzelHitap = $(By.id("birimYonetimiEditorForm:ozelHitapExistSelBoolean_input"));
    SelenideElement txtMesajAdresi = $(By.id("birimYonetimiEditorForm:maggKoduInput"));
    SelenideElement txtKarargahKisaltmasi = $(By.id("birimYonetimiEditorForm:karargahKisaltmasiInput"));
    SelenideElement cmbAntetTipi = $(By.id("birimYonetimiEditorForm:antetTipiSelect_input"));
    SelenideElement txtIdariKimlikKodu = $(By.id("birimYonetimiEditorForm:kurumKimlikKoduInput"));
    SelenideElement cmbBirimTipi = $(By.id("birimYonetimiEditorForm:birimTipiAutoComplete_input"));
    SelenideElement txtGelenEvrakNumaratoru = $(By.id("birimYonetimiEditorForm:gelenEvrakNumaratorAutoComplete_input"));
    SelenideElement txtGidenEvrakNumaratoru = $(By.id("birimYonetimiEditorForm:gidenEvrakNumaratorAutoComplete_input"));
    SelenideElement cmbBirimBagTuru = $(By.id("birimYonetimiEditorForm:birimBagTuruSelect"));
    SelenideElement treeBagliBirim = $(By.id("birimYonetimiEditorForm:bagliBirimLov:LovText"));
    SelenideElement treeFizikiArsivBirimi = $(By.id("birimYonetimiEditorForm:fizikiArsivBirimiLov:LovText"));
    SelenideElement txtMedasPostaBirimi = $(By.id("birimYonetimiEditorForm:medasPostaBirimiLov:LovText"));
    SelenideElement cmbPostaSekli = $(By.id("birimYonetimiEditorForm:postaSekli"));
    SelenideElement cmbBelgenetKullaniyorMu = $(By.id("birimYonetimiEditorForm:belge"));
    SelenideElement chkArsivBirimi = $(By.id("birimYonetimiEditorForm:arsivBirimiCheckbox_input"));
    SelenideElement chkGenelEvrak = $(By.id("birimYonetimiEditorForm:genelEvrakCheckbox_input"));
    SelenideElement txtOlurMetni = $(By.id("birimYonetimiEditorForm:olurMetniText"));
    SelenideElement txtAciklama = $(By.id("birimYonetimiEditorForm:aciklamaMetniText"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("birimYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement chkYetkiDevriVar = $(By.id("birimYonetimiEditorForm:yetkiDevriCheckbox_input"));
    SelenideElement btnSolUstLogoEkle = $(By.id("birimYonetimiEditorForm:solUstMenuLogoEkle"));
    SelenideElement btnSagUstLogoEkle = $(By.id("birimYonetimiEditorForm:sagUstMenuLogoEkle"));
    SelenideElement btnAltLogoyuDegistir = $(By.id("birimYonetimiEditorForm:altMenuLogoEkle"));
    SelenideElement txtSolUstLogoBoy = $(By.id("birimYonetimiEditorForm:j_idt11983"));
    SelenideElement txtSagUstLogoBoy = $(By.id("birimYonetimiEditorForm:j_idt11986"));
    SelenideElement txtSolUstGenislik = $(By.id("birimYonetimiEditorForm:j_idt11990"));
    SelenideElement txtSagUstLogoGenislik = $(By.id("birimYonetimiEditorForm:j_idt11993"));
    BelgenetElement txtBirim = comboLov("[id$='birimLov:LovText']");
    SelenideElement btnTableDuzenle = $(By.id("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
    SelenideElement btnKepAdresBilgileriArti = $(By.id("birimYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));

    SelenideElement txtPopupKepAdresi= $(By.id("kepAdresBilgiEditorForm:kepAdresiInputTextId"));
    SelenideElement cmbPopupHizmetSaglayicisi = $(By.id("kepAdresBilgiEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("kepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("birimYonetimiEditorForm:saveBirimButton"));

    public BirimYonetimiPage openPage() {
        ustMenu("Birim Yönetimi");
        return this;
    }

    @Step("Kaydet tıkla")
    public BirimYonetimiPage kaydet() throws InterruptedException{
        btnKaydet.click();
        return this;
    }
    @Step("Popup kaydet tıkla")
    public BirimYonetimiPage popupKaydet() throws InterruptedException{
        btnPopupKaydet.click();
        return this;
    }
    @Step("Popup hizmet sağlayıcısı seç")
    public BirimYonetimiPage popupHizmetSaglayicisiSec(String value) throws InterruptedException{
        cmbPopupHizmetSaglayicisi.selectOption(value);
        return this;
    }
    @Step("Popup kep adresi doldur")
    public BirimYonetimiPage popupKepAdresiDoldur(String text) throws InterruptedException{
        txtPopupKepAdresi.setValue(text);
        return this;
    }
    @Step("Kep adresi bilgileri artı tıkla")
    public BirimYonetimiPage kepAdresBilgileriArti()throws InterruptedException{
        btnKepAdresBilgileriArti.click();
        return this;
    }
    @Step("Tablodaki düzenle buttona tıkla")
    public BirimYonetimiPage tableDuzenle() throws InterruptedException {
        btnTableDuzenle.click();
        return this;
    }
    @Step("Sağ ust Logo genişlik doldur")
    public BirimYonetimiPage sagUstLogoGenislik(String text) throws InterruptedException{
        txtSagUstLogoGenislik.setValue(text);
        return this;
    }
    @Step("Sol ust genişlik doldur")
    public BirimYonetimiPage solUstGenislikDoldur(String text) throws InterruptedException{
        txtSolUstGenislik.setValue(text);
        return this;
    }
    @Step("Sağ ust Logo boy doldur")
    public BirimYonetimiPage sagUstLogoBoyDoldur(String text) throws InterruptedException{
        txtSagUstLogoBoy.setValue(text);
        return this;
    }
    @Step("Sol ust logo boy doldur")
    public BirimYonetimiPage solUstLogoBoyDoldur(String text) throws InterruptedException{
        txtSolUstLogoBoy.setValue(text);
        return this;
    }
    @Step("Alt logoyu değiştir tıkla")
    public BirimYonetimiPage altLogoyuDegistir() throws InterruptedException{
        btnAltLogoyuDegistir.click();
        return this;
    }
    @Step("Sağ ust logo ekle tıkla")
    public BirimYonetimiPage sagUstLogoEkleGonder() throws InterruptedException{
        btnSagUstLogoEkle.click();
        return this;
    }
    @Step("Sol ust Logo ekle tıkla")
    public BirimYonetimiPage solUstLogoEkle() throws InterruptedException{
        btnSolUstLogoEkle.click();
        return this;
    }
    @Step("Yetki devri var seç")
    public BirimYonetimiPage yetkiDevriVarSec(Boolean secim) throws InterruptedException{
        chkYetkiDevriVar.setSelected(secim);
        return this;
    }
    @Step("Kep adresi kullaniyor seç")
    public BirimYonetimiPage kepAdresiKullaniyorSec(boolean secim) throws InterruptedException {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }
    @Step("Açıklama doldur")
    public BirimYonetimiPage aciklamaDoldur(String text) throws InterruptedException {
        txtAciklama.setValue(text);
        return  this;
    }
    @Step("Olur metni doldur")
    public BirimYonetimiPage olurMetniDoldur(String text) throws InterruptedException {
        txtOlurMetni.setValue(text);
        return  this;
    }
    @Step("Genel evrak seç")
    public BirimYonetimiPage genelEvrakSec(boolean secim) throws InterruptedException{
        chkGenelEvrak.setSelected(secim);
        return this;
    }
    @Step("Arşiv birimi seç")
    public BirimYonetimiPage arsivBirimiSec(String value) throws InterruptedException{
        chkArsivBirimi.selectOption(value);
        return this;
    }
    @Step("Belgenet kullanılıtor mu seç")
    public BirimYonetimiPage BelgenetKullaniyorMuSec(String value) throws InterruptedException{
        cmbBelgenetKullaniyorMu.selectOption(value);
        return this;
    }

    @Step("Posta şekli seç")
    public BirimYonetimiPage postaSekliSec(String value) throws InterruptedException{
        cmbPostaSekli.selectOption(value);
        return  this;
    }
    @Step("Yetki devri var seç")
    public BirimYonetimiPage medasPostaBirimiDoldur(String text) throws InterruptedException {
        txtMedasPostaBirimi.setValue(text);
        return this;
    }
    @Step("Fiziki arşiv birimi doldur")
    public BirimYonetimiPage fizikiArsivBirimiDoldur(String text) throws InterruptedException{
        treeFizikiArsivBirimi.setValue(text);
        return this;
    }
    @Step("Tree bağlı birimi doldur")
    public BirimYonetimiPage treeBagliBirimDoldur(String text) throws InterruptedException{
        treeBagliBirim.setValue(text);
        return  this;
    }
    @Step("Birim bağ türü seç")
    public BirimYonetimiPage birimBagTuruSec(String text) throws InterruptedException{
        cmbBirimBagTuru.selectOption(text);
        return this;
    }
    @Step("Giden evrakları numaratoru doldur")
    public BirimYonetimiPage gidenEvraklariNumaratoruDoldur(String text) throws InterruptedException{
        txtGidenEvrakNumaratoru.setValue(text);
        return  this;
    }
    @Step("Gelen evraklar numaratoru doldur")
    public BirimYonetimiPage gelenEvraklariNumaratoruDoldur(String text) throws InterruptedException {
        txtGelenEvrakNumaratoru.setValue(text);
        return this;
    }
    @Step("Birimi tipi seç")
    public BirimYonetimiPage birimTipiSec(String value) throws  InterruptedException{
        cmbBirimTipi.selectOption(value);
        return this;
    }
    @Step("İdari kimlik doldur")
    public BirimYonetimiPage IdariKimlikKoduDoldur(String text) throws InterruptedException {
        txtIdariKimlikKodu.setValue(text);
        return  this;
    }
    @Step("Antet tipi seç")
    public BirimYonetimiPage antetTipiSec(String value) throws InterruptedException{
        cmbAntetTipi.selectOption(value);
        return this;
    }
    @Step("Karargah kısaltması doldur")
    public BirimYonetimiPage karargahKisaltmasiDoldur(String text) throws InterruptedException{
        txtKarargahKisaltmasi.setValue(text);
        return  this;
    }
    @Step("Mesaj adresi doldur")
    public BirimYonetimiPage mesajAdresiDoldur(String text) throws InterruptedException{
        txtMesajAdresi.setValue(text);
        return this;
    }
    @Step("Özel hitap seç")
    public BirimYonetimiPage ozelHitap(Boolean secim) throws InterruptedException{
        chkOzelHitap.setSelected(secim);
        return this;
    }
    @Step("Kısa adı doldur")
    public BirimYonetimiPage kisaAdiDoldur(String text) throws InterruptedException{
        txtKisaAdi.setValue(text);
        return  this;
    }
    @Step("Ad doldur")
    public BirimYonetimiPage adDoldur(String text) throws InterruptedException{
        txtAd.setValue(text);
        return this;
    }
    @Step("Dış birim seç")
    public BirimYonetimiPage disBirim(Boolean secim) throws  InterruptedException{
        chkDisBirim.setSelected(secim);
        return this;
    }
    @Step("Görünürlük tipi seç")
    public BirimYonetimiPage gorunurlukTipiSec(String value) throws InterruptedException{
        cmbGorunurlukTipi.selectOption(value);
        return this;
    }
    @Step("Yeni buttona tıkla")
    public BirimYonetimiPage btnArti() throws InterruptedException{
        btnArti.click();
        return  this;
    }
    @Step("Durum seç")
    public BirimYonetimiPage durumSec(String value) throws InterruptedException {
        cmbDurum.selectOption(value);
        return this;

    }
    @Step("Birim türü seçildi")
    public BirimYonetimiPage birimTuruSec(String value) throws InterruptedException{
        cmbBirimTuru.selectOption(value);
        return  this;
    }
    @Step("Düzenle tıkla")
    public BirimYonetimiPage duzenle() throws InterruptedException{
        btnDuzenle.click();
        return this;
    }
    @Step("Ara tıkla")
    public BirimYonetimiPage ara() throws InterruptedException{
        btnAra.click();
        return  this;
    }

    @Step("Birim Doldur")
    public BirimYonetimiPage birimDoldur(String text) throws InterruptedException{
        txtBirim.selectComboLov(text);
        return  this;
    }
}
