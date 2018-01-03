package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.SorgulamaVeFiltreleme;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
public class EvrakHavaleKurallariYonetimiPage extends MainPage {

    SelenideElement window = $("#havaleKuralYonetimiListingForm");

    SelenideElement btnAra = $("[id='havaleKuralYonetimiListingForm:filterPanel:searchEntitiesButton']");
    SelenideElement btnSil = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:0:deleteHavaleKuralButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
    BelgenetElement txtBirim = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement cmbDurum = $(By.id("havaleKuralYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement cmbGeldigiYerTipi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerTipiSelect"));
    SelenideElement chkAltBirimDahil = $(By.id("havaleKuralYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox_input"));
    BelgenetElement txtKullanici = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerKullaniciLov:LovText"));
    SelenideElement btnYeni = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:addNewHavaleKuralButton"));
    ElementsCollection cmbEvrakTuru = $$("[class='ui-selectcheckboxmenu-label-container']");
    ElementsCollection cmbEvrakTuruGenelge = $$("[class='ui-selectcheckboxmenu-items ui-selectcheckboxmenu-list ui-widget-content ui-widget ui-corner-all ui-helper-reset'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    SelenideElement txtKuralAdi = $(By.id("havaleKuralYonetimiEditorForm:kuralAdi"));
    SelenideElement btnKuralinTanimliOlduguBirimlerYeni = $(By.id("havaleKuralYonetimiEditorForm:kullaniciBirimDataTable:addNewKullaniciBirimLinkButton"));
    BelgenetElement txtBirimEkleBirim = comboLov(By.id("hkKullaniciBirimEditorForm:havaleKuralKullaniciBirimIliskiBirimLov:LovText"));
    SelenideElement btnBirimEkleEkle = $(By.id("hkKullaniciBirimEditorForm:saveKullaniciBirimButton"));
    SelenideElement btnKuralEklemeKaydet = $(By.id("havaleKuralYonetimiEditorForm:saveHavaleKuralButton"));
    SelenideElement sonTable = $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_paginator_top'] [class='ui-paginator-last ui-state-default ui-corner-all']");
    SelenideElement txtFiltreleKuralAdi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:adFilterInput"));
    //TODO Kime havale edilicek
    BelgenetElement txtKimeHavaleEdilecekKisi = comboLov(By.id("havaleKuralYonetimiEditorForm:havaleKullaniciLov:LovText"));
    //TODO


    @Step("")
    public SorgulamaVeFiltreleme sorgulamaVeFiltreleme(){
        return new SorgulamaVeFiltreleme(window);
    }


    @Step("Kural ekleme kaydet")
    public EvrakHavaleKurallariYonetimiPage kuralEklemeKaydet() {
        btnKuralEklemeKaydet.click();
        return this;
    }

    @Step("Birim ekle")
    public EvrakHavaleKurallariYonetimiPage birimEkleEkle() {
        clickJs(btnBirimEkleEkle);
        sleep(5000);
        return this;
    }

    @Step("Birim doldur")
    public EvrakHavaleKurallariYonetimiPage birimEkleBirimDoldur(String birim) {
        txtBirimEkleBirim.selectLov(birim);
        return this;
    }

    @Step("Filtrele alanındaki kural adı alanını doldur {kuralAdi} | {alan}")
    public EvrakHavaleKurallariYonetimiPage filtreleKuralAdiDoldur(String kuralAdi, String alan){
        txtFiltreleKuralAdi.setValue(kuralAdi);
        return this;
    }

    @Step("Kuralın tanımlı olduğu birimler")
    public EvrakHavaleKurallariYonetimiPage kuralinTanimliOlduguBirimlerYeni() {
        btnKuralinTanimliOlduguBirimlerYeni.click();
        return this;
    }

    @Step("Kural adı doldur")
    public EvrakHavaleKurallariYonetimiPage kuralAdıDoldur(String kuralAdi) {
        txtKuralAdi.setValue(kuralAdi);
        return this;
    }

    @Step("Evrak türü seç")
    public EvrakHavaleKurallariYonetimiPage evrakTuruSec() {
        cmbEvrakTuru.get(0).click();
        cmbEvrakTuruGenelge.get(2).click();
        cmbEvrakTuru.get(0).click();
        return this;
    }

    @Step("Yeni kural oluştur")
    public EvrakHavaleKurallariYonetimiPage yeniKural() {
        btnYeni.click();
        return this;
    }

    @Step("Tablo değer kontrolu")
    public EvrakHavaleKurallariYonetimiPage tabloKontrol(String deger) {
        $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_data']").shouldHave(Condition.text(deger));
        return this;
    }

    @Step("Alt birim dahil seçilir")
    public EvrakHavaleKurallariYonetimiPage altBirimDahilSec(boolean secim) {
        chkAltBirimDahil.setSelected(secim);
        return this;
    }

    @Step("Geldiği yer tipi seçiniz")
    public EvrakHavaleKurallariYonetimiPage geldigiYerTipiSec(String value) {
        cmbGeldigiYerTipi.selectOptionByValue(value);
        return this;
    }

    @Step("Durum Seçiniz")
    public EvrakHavaleKurallariYonetimiPage durumSec(String value) {
        cmbDurum.selectOptionByValue(value);
        return this;
    }

    @Step("Kural adı doldur")
    public EvrakHavaleKurallariYonetimiPage kuralAdiDoldur(String kuralAdi) {
        txtKuralAdi.setValue(kuralAdi);
        return this;
    }

    @Step("Kişi doldur")
    public EvrakHavaleKurallariYonetimiPage kimeHavaleEdilecekKisiDoldur(String kisi, String birim) {
        txtKimeHavaleEdilecekKisi.type(kisi).detailItems().filterBy(Condition.text(birim)).first().click();
        return this;
    }

    @Step("Birim alanı doldurulur")
    public EvrakHavaleKurallariYonetimiPage birimDoldur(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }

    @Step("Sil")
    public EvrakHavaleKurallariYonetimiPage sil() {
        btnSil.click();
        return this;
    }

    @Step("Sil")
    public EvrakHavaleKurallariYonetimiPage sil(String konu) {
       // sonTable.click();
        ElementsCollection evrak2 = $$("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable'] table tbody tr");
        evrak2.filterBy(Condition.text(konu)).get(0).$("[id^='havaleKuralYonetimiListingForm:havaleKuralDataTable'][id$='deleteHavaleKuralButton']").click();
        return this;
    }

    @Step("İslem onayı evet")
    public EvrakHavaleKurallariYonetimiPage islemOnayiEvet() {
        btnIslemOnayiEvet.click();
        return this;
    }

    @Step("Ara")
    public EvrakHavaleKurallariYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Kullanıcı doldur")
    public EvrakHavaleKurallariYonetimiPage kullaniciDoldur(String kullanici) {
        txtKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Evrak havale kuralları yonetimi sayfası açılır")
    public EvrakHavaleKurallariYonetimiPage openPage() {
        ustMenu("Evrak Havale Kuralları Yönetimi");
        return this;
    }


}
