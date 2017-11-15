package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;
import pageComponents.belgenetElements.BelgenetElement;

import java.beans.IntrospectionException;

import static com.codeborne.selenide.Selenide.$;
import static pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GercekKisiYonetimPage extends BaseLibrary {

    SelenideElement btnGercekKisiEkle = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable:addNewGercekKisiButton"));
    SelenideElement txtFiltreTCKimlikNo = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiTcKimlikNoFilterInput"));
    SelenideElement txtFiltreAd = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiAdFilterInput"));
    SelenideElement txtFiltreSoyad = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiSoyadFilterInput"));
    SelenideElement cmbFiltreDurum = $(By.id("gercekKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnAra = $(By.id("gercekKisiYonetimiListingForm:filterPanel:searchGercekKisiButton"));
    SelenideElement txtTCKimlikNo = $(By.id("gercekKisiYonetimiEditorForm:tcKimlikNoInput"));
    SelenideElement txtOnEk = $(By.id("gercekKisiYonetimiEditorForm:onEkInput"));
    SelenideElement txtUnvan = $(By.id("gercekKisiYonetimiEditorForm:unvanId"));
    SelenideElement txtAd = $(By.id("gercekKisiYonetimiEditorForm:adInput"));
    SelenideElement txtSoyad = $(By.id("gercekKisiYonetimiEditorForm:soyadInput"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("gercekKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement btnKepAdresBilgiler = $(By.id("gercekKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("gercekKisiYonetimiEditorForm:saveGercekKisiButton"));
    SelenideElement btnIletisimBilgileriEkle = $(By.id("gercekKisiYonetimiEditorForm:iletisimBilgileriDataTable:addNewIletisimBilgisiButton"));

  //Yeni İletişim Bilgisi
    SelenideElement txtIletisimBilgisiAdres = $(By.id("gercekKisiBilgileriEditorForm:adresInput"));
   // SelenideElement txtIletisimBilgisiIl= $(By.id("gercekKisiBilgileriEditorForm:lovIl:lovInputPanel"));
    BelgenetElement txtIletisimBilgisiIl = comboBox("gercekKisiBilgileriEditorForm:lovIl:lovInputPanel");
    SelenideElement txtIletisimBilgisiIlce= $(By.id("gercekKisiBilgileriEditorForm:lovIlce:lovInputPanel"));
    SelenideElement txtIletisimBilgisiEPosta= $(By.id("gercekKisiBilgileriEditorForm:ePostaInput"));
    SelenideElement btnIletisimBilgisiKaydet= $(By.id("gercekKisiBilgileriEditorForm:saveIletisimBilgisiButton"));


    public GercekKisiYonetimPage yeniGercekKisiEkle() {
        btnGercekKisiEkle.click();
        return this;
    }

    public GercekKisiYonetimPage kaydet() {
        btnKaydet.click();
        return this;
    }

    public GercekKisiYonetimPage kepAdresBilgiler() {
        btnKepAdresBilgiler.click();
        return this;
    }

    public GercekKisiYonetimPage kepAdresiKullaniyor(boolean secim){
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public GercekKisiYonetimPage soyadDoldur(String text) {
        txtSoyad.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage adDoldur(String text){
        txtAd.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage unvanDoldur(String text){
        txtUnvan.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage onEkDoldur(String text) {
        txtOnEk.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage tcKimlikNoDoldur(String text) {
        txtTCKimlikNo.setValue(text);
        createMernisTCNO();
        return this;
    }

    public GercekKisiYonetimPage ara() {
        btnAra.click();
        return this;
    }

    public GercekKisiYonetimPage filtreDurumSec(String value) {
        cmbFiltreDurum.selectOption(value);
        return this;
    }

    public GercekKisiYonetimPage filtreSoyadDoldur(String text){
        txtFiltreSoyad.setValue(text);
        return  this;
    }

    public GercekKisiYonetimPage filtreAdDoldur(String text){
        txtFiltreAd.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage filtreTCKimlikNoDoldur(String text) {
        txtFiltreTCKimlikNo.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgileriEkle() {
        btnIletisimBilgileriEkle.click();
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiAdresDoldur(String adres) {
        txtIletisimBilgisiAdres.sendKeys(adres);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiIlDoldur(String il) {
        //txtIletisimBilgisiIl.selectComboLov(il);
        //comboText(txtIletisimBilgisiIl);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiIlceDoldur(String ilce) {
        txtIletisimBilgisiIlce.sendKeys(ilce);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiEpostaDoldur(String eposta) {
        txtIletisimBilgisiIlce.sendKeys(eposta);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiKaydet() {
        btnIletisimBilgisiKaydet.click();
        return this;
    }
}
