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

    public BirimYonetimiPage sagUstLogoGenislik(String text) throws InterruptedException{
        txtSagUstLogoGenislik.setValue(text);
        return this;
    }

    public BirimYonetimiPage solUstGenislikDoldur(String text) throws InterruptedException{
        txtSolUstGenislik.setValue(text);
        return this;
    }

    public BirimYonetimiPage sagUstLogoBoyDoldur(String text) throws InterruptedException{
        txtSagUstLogoBoy.setValue(text);
        return this;
    }

    public BirimYonetimiPage solUstLogoBoyDoldur(String text) throws InterruptedException{
        txtSolUstLogoBoy.setValue(text);
        return this;
    }

    public BirimYonetimiPage altLogoyuDegistir() throws InterruptedException{
        btnAltLogoyuDegistir.click();
        return this;
    }

    public BirimYonetimiPage sagUstLogoEkleGonder() throws InterruptedException{
        btnSagUstLogoEkle.click();
        return this;
    }

    public BirimYonetimiPage solUstLogoEkle() throws InterruptedException{
        btnSolUstLogoEkle.click();
        return this;
    }

    public BirimYonetimiPage yetkiDevriVarSec(Boolean secim) throws InterruptedException{
        chkYetkiDevriVar.setSelected(secim);
        return this;
    }

    public  BirimYonetimiPage kepAdresiKullaniyorSec(boolean secim) throws InterruptedException{
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public  BirimYonetimiPage aciklamaDoldur(String text) throws InterruptedException{
        txtAciklama.setValue(text);
        return  this;
    }

    public BirimYonetimiPage olurMetniDoldur(String text) throws InterruptedException {
        txtOlurMetni.setValue(text);
        return  this;
    }

    public BirimYonetimiPage genelEvrakSec(boolean secim) throws InterruptedException{
        chkGenelEvrak.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage arsivBirimiSec(String value) throws InterruptedException{
        chkArsivBirimi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage BelgenetKullaniyorMuSec(String value) throws InterruptedException{
        cmbBelgenetKullaniyorMu.selectOption(value);
        return this;
    }


    public BirimYonetimiPage postaSekliSec(String value) throws InterruptedException{
        cmbPostaSekli.selectOption(value);
        return  this;
    }

    public  BirimYonetimiPage medasPostaBirimiDoldur(String text) throws InterruptedException{
        txtMedasPostaBirimi.setValue(text);
        return this;
    }

    public BirimYonetimiPage fizikiArsivBirimiDoldur(String text) throws InterruptedException{
        treeFizikiArsivBirimi.setValue(text);
        return this;
    }

    public BirimYonetimiPage treeBagliBirimDoldur(String text) throws InterruptedException{
        treeBagliBirim.setValue(text);
        return  this;
    }

    public BirimYonetimiPage birimBagTuruSec(String text) throws InterruptedException{
        cmbBirimBagTuru.selectOption(text);
        return this;
    }

    public BirimYonetimiPage gidenEvraklariNumaratoruDoldur(String text) throws InterruptedException{
        txtGidenEvrakNumaratoru.setValue(text);
        return  this;

    }

    public  BirimYonetimiPage gelenEvraklariNumaratoruDoldur(String text) throws InterruptedException{
        txtGelenEvrakNumaratoru.setValue(text);
        return this;
    }


    public BirimYonetimiPage birimTipiSec(String value) throws  InterruptedException{
        cmbBirimTipi.selectOption(value);
        return this;
    }

    public   BirimYonetimiPage IdariKimlikKoduDoldur(String text) throws InterruptedException{
        txtIdariKimlikKodu.setValue(text);
        return  this;
    }

    public BirimYonetimiPage antetTipiSec(String value) throws InterruptedException{
        cmbAntetTipi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage karargahKisaltmasiDoldur(String text) throws InterruptedException{
        txtKarargahKisaltmasi.setValue(text);
        return  this;
    }

    public BirimYonetimiPage mesajAdresiDoldur(String text) throws InterruptedException{
        txtMesajAdresi.setValue(text);
        return this;
    }

    public BirimYonetimiPage ozelHitap(Boolean secim) throws InterruptedException{
        chkOzelHitap.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage kisaAdiDoldur(String text) throws InterruptedException{
        txtKisaAdi.setValue(text);
        return  this;
    }

    public BirimYonetimiPage adDoldur(String text) throws InterruptedException{
        txtAd.setValue(text);
        return this;
    }

    public BirimYonetimiPage disBirim(Boolean secim) throws  InterruptedException{
        chkDisBirim.setSelected(secim);
        return this;
    }

    public BirimYonetimiPage gorunurlukTipiSec(String value) throws InterruptedException{
        cmbGorunurlukTipi.selectOption(value);
        return this;
    }

    public BirimYonetimiPage btnArti() throws InterruptedException{
        btnArti.click();
        return  this;
    }

    public BirimYonetimiPage  durumSec(String value) throws  InterruptedException{
        cmbDurum.selectOption(value);
        return this;

    }

    public BirimYonetimiPage birimTuruSec(String value) throws InterruptedException{
        cmbBirimTuru.selectOption(value);
        return  this;
    }

    public BirimYonetimiPage duzenle() throws InterruptedException{
        btnDuzenle.click();
        return this;
    }

    public BirimYonetimiPage araGonder() throws InterruptedException{
        btnAra.click();
        return  this;
    }

    public BirimYonetimiPage birimDoldur(String text) throws InterruptedException{
        txtBirim.setValue(text);
        return  this;
    }
}
