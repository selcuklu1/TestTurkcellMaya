package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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
    SelenideElement txtKisaAdi = $(By.id("birimYonetimiEditorForm:kisaAdInput"));
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
    SelenideElement btnYeniKepAdresBilgileriEkle = $(By.id("birimYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));

    SelenideElement txtPopupKepAdresi = $(By.id("kepAdresBilgiEditorForm:kepAdresiInputTextId"));
    SelenideElement cmbPopupHizmetSaglayicisi = $(By.id("kepAdresBilgiEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("kepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("birimYonetimiEditorForm:saveBirimButton"));

    @Step("Birim Yönetimi sayfası aç")
    public BirimYonetimiPage openPage() {
        ustMenu("Birim Yönetimi");
        return this;
    }

    @Step("Kaydet")
    public BirimYonetimiPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Popup kaydet")
    public BirimYonetimiPage popupKaydet() {
        btnPopupKaydet.click();
        return this;
    }

    @Step("Popup hizmet sağlayıcısı seç")
    public BirimYonetimiPage popupHizmetSaglayicisiSec(String value) {
        cmbPopupHizmetSaglayicisi.selectOption(value);
        return this;
    }

    @Step("Popup kep adresi doldur")
    public BirimYonetimiPage popupKepAdresiDoldur(String kepAdresi) {
        txtPopupKepAdresi.setValue(kepAdresi);
        return this;
    }

    @Step("Kep adresi bilgileri ekle")
    public BirimYonetimiPage yeniKepAdresBilgileriEkle() {
      btnYeniKepAdresBilgileriEkle.pressEnter();
        return this;
    }

    @Step("Güncelle")
    public BirimYonetimiPage tableDuzenle() {
        btnTableDuzenle.click();
        return this;
    }

    @Step("Sağ üst logo genişlik doldur")
    public BirimYonetimiPage sagUstLogoGenislik(String genislik) {
        txtSagUstLogoGenislik.setValue(genislik);
        return this;
    }

    @Step("Sol üst genişlik doldur")
    public BirimYonetimiPage solUstGenislikDoldur(String genislik) {
        txtSolUstGenislik.setValue(genislik);
        return this;
    }

    @Step("Sağ üst logo boy doldur")
    public BirimYonetimiPage sagUstLogoBoyDoldur(String boy) {
        txtSagUstLogoBoy.setValue(boy);
        return this;
    }

    @Step("Sol üst logo boy doldur")
    public BirimYonetimiPage solUstLogoBoyDoldur(String boy) {
        txtSolUstLogoBoy.setValue(boy);
        return this;
    }

    @Step("Alt logoyu değiştir")
    public BirimYonetimiPage altLogoDegistir() {
        btnAltLogoyuDegistir.click();
        return this;
    }

    @Step("Sağ ust logo ekle tıkla")
    public BirimYonetimiPage sagUstLogoEkleGonder() {
        btnSagUstLogoEkle.click();
        return this;
    }

    @Step("Sol ust Logo ekle tıkla")
    public BirimYonetimiPage solUstLogoEkle() {
        btnSolUstLogoEkle.click();
        return this;
    }

    @Step("Yetki devri var seç")
    public BirimYonetimiPage yetkiDevriVarSec(Boolean secim) {
        chkYetkiDevriVar.setSelected(secim);
        return this;
    }

    @Step("Kep adresi kullaniyor seç")
    public BirimYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    @Step("Açıklama doldur")
    public BirimYonetimiPage aciklamaDoldur(String text) {
        txtAciklama.setValue(text);
        return this;
    }

    @Step("Olur metni doldur")
    public BirimYonetimiPage olurMetniDoldur(String text) {
        txtOlurMetni.setValue(text);
        return this;
    }

    @Step("Genel evrak seç")
    public BirimYonetimiPage genelEvrakSec(boolean secim) {
        chkGenelEvrak.setSelected(secim);
        return this;
    }

    @Step("Arşiv birimi seç")
    public BirimYonetimiPage arsivBirimiSec(String value) {
        chkArsivBirimi.selectOption(value);
        return this;
    }

    @Step("Belgenet kullanılıtor mu seç")
    public BirimYonetimiPage BelgenetKullaniyorMuSec(String value) {
        cmbBelgenetKullaniyorMu.selectOption(value);
        return this;
    }

    @Step("Posta şekli seç")
    public BirimYonetimiPage postaSekliSec(String value) {
        cmbPostaSekli.selectOption(value);
        return this;
    }

    @Step("Yetki devri var seç")
    public BirimYonetimiPage medasPostaBirimiDoldur(String text) {
        txtMedasPostaBirimi.setValue(text);
        return this;
    }

    @Step("Fiziki arşiv birimi doldur")
    public BirimYonetimiPage fizikiArsivBirimiDoldur(String text) {
        treeFizikiArsivBirimi.setValue(text);
        return this;
    }

    @Step("Tree bağlı birimi doldur")
    public BirimYonetimiPage treeBagliBirimDoldur(String text) {
        treeBagliBirim.setValue(text);
        return this;
    }

    @Step("Birim bağ türü seç")
    public BirimYonetimiPage birimBagTuruSec(String text) {
        cmbBirimBagTuru.selectOption(text);
        return this;
    }

    @Step("Giden evrakları numaratoru doldur")
    public BirimYonetimiPage gidenEvraklariNumaratoruDoldur(String text) {
        txtGidenEvrakNumaratoru.setValue(text);
        return this;
    }

    @Step("Gelen evraklar numaratoru doldur")
    public BirimYonetimiPage gelenEvraklariNumaratoruDoldur(String text) {
        txtGelenEvrakNumaratoru.setValue(text);
        return this;
    }

    @Step("Birimi tipi seç")
    public BirimYonetimiPage birimTipiSec(String value) throws InterruptedException {
        cmbBirimTipi.selectOption(value);
        return this;
    }

    @Step("İdari kimlik doldur")
    public BirimYonetimiPage idariKimlikKoduDoldur(String text) {
        txtIdariKimlikKodu.setValue(text);
        return this;
    }

    @Step("Antet tipi seç")
    public BirimYonetimiPage antetTipiSec(String value) {
        cmbAntetTipi.selectOption(value);
        return this;
    }

    @Step("Karargah kısaltması doldur")
    public BirimYonetimiPage karargahKisaltmasiDoldur(String text) {
        txtKarargahKisaltmasi.setValue(text);
        return this;
    }

    @Step("Mesaj adresi doldur")
    public BirimYonetimiPage mesajAdresiDoldur(String text) {
        txtMesajAdresi.setValue(text);
        return this;
    }

    @Step("Özel hitap seç")
    public BirimYonetimiPage ozelHitap(Boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }

    @Step("Kısa adı doldur")
    public BirimYonetimiPage kisaAdiDoldur(String kisaAd) {
        txtKisaAdi.setValue(kisaAd);
        return this;
    }

    @Step("Ad doldur")
    public BirimYonetimiPage adDoldur(String ad) {
        txtAd.setValue(ad);
        return this;
    }

    @Step("Dış birim seç")
    public BirimYonetimiPage disBirimSec(Boolean secim) {
        chkDisBirim.setSelected(secim);
        return this;
    }

    @Step("Görünürlük tipi seç")
    public BirimYonetimiPage gorunurlukTipiSec(String secim) {
        cmbGorunurlukTipi.selectOption(secim);
        return this;
    }

    @Step("Ekle")
    public BirimYonetimiPage ekle() {
        btnArti.click();
        return this;
    }

    @Step("Durum seç")
    public BirimYonetimiPage durumSec(String secim) {
        cmbDurum.selectOption(secim);
        return this;
    }

    @Step("Birim türü seç")
    public BirimYonetimiPage birimTuruSec(String secim) {
        cmbBirimTuru.selectOption(secim);
        return this;
    }

    @Step("Düzenle")
    public BirimYonetimiPage duzenle() {
        btnDuzenle.click();
        return this;
    }

    @Step("Ara")
    public BirimYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Birim doldur")
    public BirimYonetimiPage birimDoldur(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }
}
