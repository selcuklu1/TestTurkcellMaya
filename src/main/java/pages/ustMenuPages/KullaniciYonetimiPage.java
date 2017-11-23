package pages.ustMenuPages;


import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KullaniciYonetimiPage extends MainPage {

    SelenideElement pageTitle = $(By.cssSelector("#window1Dialog .ui-dialog-title"));
    SelenideElement gorevCombobox = $(By.id("kullaniciYonetimiListingForm:filterPanel:filterGorevAutocomplete"));
    BelgenetElement cmlBirim = comboLov(By.id("kullaniciYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement txtTCKimlikNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:tcKimlikNoFilter"));
    SelenideElement txtAd = $(By.id("kullaniciYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement txtSoyad = $(By.id("kullaniciYonetimiListingForm:filterPanel:soyadFilterInput"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciAdiFilterInput"));
    SelenideElement txtEmail = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciEmailFilterInput"));
    SelenideElement txtSicilNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:sicilInput"));
    SelenideElement cmbKullaniciTuru = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciTuruSelectBox"));
    SelenideElement btnAra = $(By.id("kullaniciYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement cmbDurum = $(By.id("kullaniciYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement chkGorevSuresiDolanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:gorevSuresiDolanlarCheckbox"));
    SelenideElement chkBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:birimiOlmayanlarCheckbox"));
    SelenideElement chkAltBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox"));

    // Kullanıcı Listesi
    SelenideElement btnKullaniciListesiGuncelle = $(By.id("kullaniciYonetimiListingForm:kullaniciDataTable:0:updateKullaniciButton"));

    // Görevli Olduğu Birimler
    SelenideElement btnGorevliOlduguBirimlerGuncelle =$(By.id("kullaniciYonetimiEditorForm:kullaniciBirimDataTable:0:updateKullaniciBirimButton"));

    //Görevli Olduğu Birimler alanı güncelle popup
    SelenideElement cmbPopupKullaniciBirimAtamaBagTipi = $(By.id("kullaniciBirimEditorForm:kullaniciBagTipiSelect"));
    SelenideElement btnPopupKullaniciBirimAtamaKaydet = $(By.id("kullaniciBirimEditorForm:saveKullaniciBirimButton"));

    //Kullanıcı Güncelleme
    SelenideElement btnKullaniciGuncellemeKaydet = $(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));


    @Step("Birim alanında \"{0}\" sec")
    public KullaniciYonetimiPage birimSec(String text) {
        cmlBirim.selectComboLov(text);
        return this;
    }

    public KullaniciYonetimiPage openPage()
    {
        new UstMenu().ustMenu("Kullanıcı Yönetim");
        return this;
    }

    public KullaniciYonetimiPage popupKullaniciBirimAtamaKaydet() {
        btnPopupKullaniciBirimAtamaKaydet.click();
        return this;
    }

    public KullaniciYonetimiPage popupKullaniciBirimAtamaBagTipiSec(String value){
        cmbPopupKullaniciBirimAtamaBagTipi.selectOption(value);
        return this;
    }

    public KullaniciYonetimiPage gorevliOlduguBirimlerGuncelle(){
        btnGorevliOlduguBirimlerGuncelle.click();
        return this;
    }


    public KullaniciYonetimiPage kullaniciListesiGuncelle(){
        btnKullaniciListesiGuncelle.click();
        return this;
    }

    public KullaniciYonetimiPage gorevAlaniDoldur(String value) {
        //TODO
        return this;
    }

    public KullaniciYonetimiPage TCKimlikNoDoldur(String value) {
        txtTCKimlikNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage adDoldur(String value) {
        txtAd.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage soyadDoldur(String value) {
        txtSoyad.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage kullaniciAdiDoldur(String value) {
        txtKullaniciAdi.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage emailDoldur(String value) {
        txtEmail.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage sicilNoDoldur(String value) {
        txtSicilNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage kullaniciTuruSec(String value) {
        cmbKullaniciTuru.selectOptionByValue(value);
        //selectCombobox(cmbKullaniciTuru,value);
        return this;
    }

    public KullaniciYonetimiPage durumSec(String value) {
        cmbDurum.selectOptionByValue(value);
        return this;
    }

    public KullaniciYonetimiPage gorevSuresiDolanlarSec(boolean value) {
        chkGorevSuresiDolanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage birimiOlmayanlarSec(boolean value) {
        chkBirimiOlmayanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage altBirimlerDahilSec(boolean value) {
        chkAltBirimiOlmayanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage ara() {
        btnAra.click();
        return this;
    }

}
