package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import javafx.animation.Interpolatable;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BirimYonetimiPage extends BaseLibrary {
    SelenideElement txtBirim = $(By.id("birimYonetimiFilterForm:accordionPanel:birimLov:LovText"));
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

    public BirimYonetimiPage sagUstLogoGenislik(String text) {
        txtSagUstLogoGenislik.setValue(text);
        return this;
    }

    public BirimYonetimiPage solUstGenislikDoldur(String text) {
        txtSolUstGenislik.setValue(text);
        return this;
    }

    public BirimYonetimiPage sagUstLogoBoyDoldur(String text) {
        txtSagUstLogoBoy.setValue(text);
        return this;
    }

    public BirimYonetimiPage solUstLogoBoyDoldur(String text) {
        txtSolUstLogoBoy.setValue(text);
        return this;
    }

    public BirimYonetimiPage altLogoyuDegistir() {
        btnAltLogoyuDegistir.click();
        return this;
    }

    public BirimYonetimiPage sagUstLogoEkleGonder() {
        btnSagUstLogoEkle.click();
        return this;
    }

    public BirimYonetimiPage solUstLogoEkle() {
        btnSolUstLogoEkle.click();
        return this;
    }

    public BirimYonetimiPage yetkiDevriVarSec(Boolean secim) {
        chkYetkiDevriVar.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage aciklamaDoldur(String text) {
        txtAciklama.setValue(text);
        return this;
    }

    public BirimYonetimiPage olurMetniDoldur(String text) {
        txtOlurMetni.setValue(text);
        return this;
    }

    public BirimYonetimiPage genelEvrakSec(boolean secim) {
        chkGenelEvrak.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage arsivBirimiSec(String value) {
        chkArsivBirimi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage BelgenetKullaniyorMuSec(String value) {
        cmbBelgenetKullaniyorMu.selectOption(value);
        return this;
    }


    public BirimYonetimiPage postaSekliSec(String value) {
        cmbPostaSekli.selectOption(value);
        return this;
    }

    public BirimYonetimiPage medasPostaBirimiDoldur(String text) {
        txtMedasPostaBirimi.setValue(text);
        return this;
    }

    public BirimYonetimiPage fizikiArsivBirimiDoldur(String text) {
        treeFizikiArsivBirimi.setValue(text);
        return this;
    }

    public BirimYonetimiPage treeBagliBirimDoldur(String text) {
        treeBagliBirim.setValue(text);
        return this;
    }

    public BirimYonetimiPage birimBagTuruSec(String text) {
        cmbBirimBagTuru.selectOption(text);
        return this;
    }

    public BirimYonetimiPage gidenEvraklariNumaratoruDoldur(String text) {
        txtGidenEvrakNumaratoru.setValue(text);
        return this;

    }

    public BirimYonetimiPage gelenEvraklariNumaratoruDoldur(String text) {
        txtGelenEvrakNumaratoru.setValue(text);
        return this;
    }


    public BirimYonetimiPage birimTipiSec(String value) {
        cmbBirimTipi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage IdariKimlikKoduDoldur(String text) {
        txtIdariKimlikKodu.setValue(text);
        return this;
    }

    public BirimYonetimiPage antetTipiSec(String value) {
        cmbAntetTipi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage karargahKisaltmasiDoldur(String text) {
        txtKarargahKisaltmasi.setValue(text);
        return this;
    }

    public BirimYonetimiPage mesajAdresiDoldur(String text) {
        txtMesajAdresi.setValue(text);
        return this;
    }

    public BirimYonetimiPage ozelHitap(Boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage kisaAdiDoldur(String text) {
        txtKisaAdi.setValue(text);
        return this;
    }

    public BirimYonetimiPage adDoldur(String text) {
        txtAd.setValue(text);
        return this;
    }

    public BirimYonetimiPage disBirim(Boolean secim) {
        chkDisBirim.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage gorunurlukTipiSec(String value) {
        cmbGorunurlukTipi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage btnArti() {
        btnArti.click();
        return this;
    }

    public BirimYonetimiPage durumSec(String value) {
        cmbDurum.selectOption(value);
        return this;

    }

    public BirimYonetimiPage birimTuruSec(String value) {
        cmbBirimTuru.selectOption(value);
        return this;
    }

    public BirimYonetimiPage duzenle() {
        btnDuzenle.click();
        return this;
    }

    public BirimYonetimiPage araGonder() {
        btnAra.click();
        return this;
    }

    public BirimYonetimiPage birimDoldur(String text) {
        txtBirim.setValue(text);
        return this;
    }
}
