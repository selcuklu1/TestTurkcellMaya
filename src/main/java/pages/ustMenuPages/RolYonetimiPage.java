package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;


public class RolYonetimiPage extends MainPage {


    // Sorgulama ve Filtreleme
    SelenideElement txtAdfilterinput = $x("//*[@id='rolYonetimiListingForm:filterPanel:adFilterInput']");
    BelgenetElement cmbDurumSecimi = comboBox("select[id$='rolYonetimiListingForm:filterPanel:durumSelectBox']");
    SelenideElement btnSorgulamaFiltrelemeArama = $x("//*[@id='rolYonetimiListingForm:filterPanel:searchEntitiesButton']");

    //Rol Listesi
    SelenideElement btnYeniRolEkleme = $x("//*[@id='rolYonetimiListingForm:rolDataTable:addNewRolButton']");
    ElementsCollection tblRolListesi = $$("tbody[id='rolYonetimiListingForm:rolDataTable_data'] tr[data-ri]");


    //Rol ekleme sayfası
    SelenideElement txtRolAd = $x("//*[@id='rolYonetimiEditorForm:rolAdiInput']");
    SelenideElement txtRolKısaAd = $x("//*[@id='rolYonetimiEditorForm:rolKisaAdiInput']");
    SelenideElement txtRolEtiket = $x("//*[@id='rolYonetimiEditorForm:etiketInput']");
    SelenideElement txtRolDegerKod = $x("//*[@id='rolYonetimiEditorForm:degerKodInput']");
    SelenideElement txtRolAciklama = $x("//*[@id='rolYonetimiEditorForm:aciklamaInput']");
    SelenideElement txtRolYetkiOnceligi = $x("//*[@id='rolYonetimiEditorForm:siraInput']");
    SelenideElement btnRolKaydetme = $x("//*[@id='rolYonetimiEditorForm:saveRolButton']");
    SelenideElement btnRolIptal = $x("//*[@id='rolYonetimiEditorForm:cancelSaveRolButton']");

    //Listedeki butonlar
    String DataRow;
    String strGuncelle = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":updateRolButton']";
    String strRolKopyala = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":duplicateRolButton']";
    String strRolAksiyon = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":rolAksiyonlariButton']";
    String strRolPasifYap = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":changeRolStatusButton']";

    //Rol Aksiyon sayfası
    SelenideElement btnYeniAksiyonEkle = $x("//*[@id='rolYonetimiEditorForm:rolAksiyonDataTable:newRolAksiyonButton']");
    SelenideElement dlgRolAksiyonUpdate = $x("//*[@id='rolAksiyonUpdateDialog']");
    SelenideElement txtAdSorgulama = $x("//*[@id='rolYonetimiAksiyonFiltrelemeForm:filterPanel:adFilterInput']");
    SelenideElement btnDialogAksiyonArama = $x("//*[@id='rolYonetimiAksiyonFiltrelemeForm:filterPanel:searchEntitiesButton']");
    ElementsCollection tblEklenecekAksiyonList = $$("tbody[id='rolAksiyonEditorForm:eklenecekAksiyonList_data'] tr[data-ri]");
    SelenideElement btnDialogAksiyonEkle = $x("//*[@id='rolAksiyonEditorForm:addActionButton']");
    SelenideElement txtAksiyonListesiAd = $("[id^='rolYonetimiEditorForm:rolAksiyonDataTable:'][id$='filter']");
    ElementsCollection tblAksiyonListesi = $$("tbody[id='rolYonetimiEditorForm:rolAksiyonDataTable_data'] tr[data-ri]");

    //Yeni aksiyon ilişkilendirme
    SelenideElement tabSorgulamaveFiltreleme = $x("//div[@id='rolYonetimiAksiyonFiltrelemeForm:filterPanel']//a[text()='Sorgulama ve Filtreleme']");
    SelenideElement tabRolYonetimiSorgulamaveFiltreleme = $x("//div[@id='rolYonetimiListingForm:filterPanel']//a[text()='Sorgulama ve Filtreleme']");


    @Step("Rol Yönetimi Sayfasını aç")
    public RolYonetimiPage openPage() {

        ustMenu(UstMenuData.KullaniciIslemleri.RolYonetimi);
        return this;
    }

    @Step("Rol Yönetimi Sayfası kontrolu.")
    public RolYonetimiPage rolYonetimiSayfaKontrolu() {

        Assert.assertEquals($x("//div[@id='window1Dialog']//span[text()='Rol Yönetimi']").isDisplayed(), true, "Sayfa açıldı.");
        return this;
    }

    @Step("Aranacak Rol adı doldurma : \"{RolAd}\" ")
    public RolYonetimiPage txtRolAdArama(String RolAd) {
        txtAdfilterinput.setValue(RolAd);
        return this;
    }

    @Step("Aranacak durum seçimi : \"{durum}\"")
    public RolYonetimiPage cmbDurumSecim(String durum) {
        cmbDurumSecimi.selectComboBox(durum);
        return this;
    }

    @Step("Arama butonuna tıkla")
    public RolYonetimiPage btnRolArama() {
        btnSorgulamaFiltrelemeArama.click();
        return this;
    }

    @Step("Yeni Rol Ekleme butonu")
    public RolYonetimiPage btnYeniRolekle() {
        btnYeniRolEkleme.click();
        return this;
    }

    @Step("Yeni Rol ad doldurma : \"{Rolad}\"")
    public RolYonetimiPage txtYeniRolAd(String Rolad) {
        txtRolAd.setValue(Rolad);
        return this;
    }

    @Step("Yeni Rol Kısa Ad doldurma : \"{RolKısaad}\"")
    public RolYonetimiPage txtYeniRolKısaAd(String RolKısaad) {
        txtRolKısaAd.setValue(RolKısaad);
        return this;
    }

    @Step("Yeni Rol Etiket doldurma : \"{etiket}\"")
    public RolYonetimiPage txtYeniRolEtiket(String etiket) {
        txtRolEtiket.setValue(etiket);
        return this;
    }

    @Step("Yeni Rol Deger kod doldurma :\"{degerkod}\"")
    public RolYonetimiPage txtRolDegerKod(String degerkod) {
        txtRolDegerKod.setValue(degerkod);
        return this;
    }

    @Step("Yeni Rol Açıklama doldurma : \"{aciklama}\"")
    public RolYonetimiPage txtRolAciklama(String aciklama) {
        txtRolAciklama.setValue(aciklama);
        return this;
    }

    @Step("Yeni Rol Yetki Onceligi doldurma : \"{oncelik}\"")
    public RolYonetimiPage txtRolYetkiOnceligi(int oncelik) {
        txtRolYetkiOnceligi.setValue(String.valueOf(oncelik));
        return this;
    }

    @Step("Yeni Rol Kaydetme ")
    public RolYonetimiPage btnYeniRolKaydetme() {
        btnRolKaydetme.click();
        return this;
    }

    @Step("Yeni Rol Kaydetme iptal")
    public RolYonetimiPage btnYeniRolIptal() {
        btnRolIptal.click();
        return this;
    }

    @Step("Arama sonuç tablosundan seçim {ad}")
    public RolYonetimiPage tblRolListeSecim(String ad) {
        tblRolListesi.filter(Condition.text(ad)).first().click();
        return this;

    }


    @Step("Arama sonuç tablosunda seçilen sonuç aksiyon butonu tıklama")
    public RolYonetimiPage tblRolListeSecimAksiyonButonu(String ad) {
        tblRolListesi.filter(Condition.text(ad)).first().$("button[id$='rolAksiyonlariButton']")
                .click();
        return this;
    }

    @Step("Aksiyon Listesi ekrana gelir.")
    public RolYonetimiPage aksiyonListesiKontrol() {
        Assert.assertEquals($x("//label[normalize-space(text())='Aksiyon Listesi']").isDisplayed(), true, "Aksiyon Listesi görüntülendi");
        return this;
    }


    @Step("Yeni Aksiyon Ekle butonu")
    public RolYonetimiPage btnYeniAksiyonEkle() {
        btnYeniAksiyonEkle.click();
        return this;
    }

    @Step("Dialog içi arama Aksiyon ad : \"{aksiyonAd}\" doldur")
    public RolYonetimiPage txtDialogAksiyonad(String aksiyonAd) {
        txtAdSorgulama.setValue(aksiyonAd);
        return this;
    }

    @Step("Dialog içi arama butonu tıklama")
    public RolYonetimiPage btnDialogAksiyonAra() {
        btnDialogAksiyonArama.click();
        return this;
    }

    @Step("Dialog Aksiyon sonuç listesinden seçme")
    public RolYonetimiPage btnDialogselectAction(String ad) {

        boolean isSelected = false;

        SelenideElement currentRow = tblEklenecekAksiyonList
                .filterBy(Condition.text(ad))
                .first();

        if (tblEklenecekAksiyonList.filterBy(Condition.text(ad)).first().isDisplayed()) {
            SelenideElement chkBox = currentRow.$("div[class='ui-chkbox ui-widget']");

            if (chkBox.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
                isSelected = true;

            if (isSelected == false) {
                chkBox.click();
            }
            btnDialogAksiyonEkle();
            islemMesaji().isBasarili();
        }
        else {
            Allure.addAttachment("Tablo Seçim", "Tabloda seçilecek kayıt bulunamadı.");
            $x("//div[@id='rolAksiyonUpdateDialog']//span[@class='ui-icon ui-icon-closethick']").click();
        }
        return this;
    }

    @Step("Rol Listesi tablo kontrolü. \"{rolAdi}\" ")
    public RolYonetimiPage rolListesiKontrolu(String rolAdi) {
        tblRolListesi.filterBy(Condition.text(rolAdi)).shouldHave(CollectionCondition.sizeGreaterThan(0));
        return this;
    }

    @Step("Dialog Aksiyon Ekle butonu")
    public RolYonetimiPage btnDialogAksiyonEkle() {
        btnDialogAksiyonEkle.click();
        return this;
    }

    @Step("Yeni Aksiyon İliskilendirme ekranı Sorgulama ve Filtreleme tabı açılır.")
    public RolYonetimiPage yeniAksiyonİliskilendirmeSorgulamaveFiltrelemeTabAc() {
        tabSorgulamaveFiltreleme.click();
        return this;
    }

    @Step("Yeni Aksiyon İliskilendirme ekranı Sorgulama ve Filtreleme tabı açılır.")
    public RolYonetimiPage rolYonetimiSorgulamaveFiltrelemeTabAc() {
        SelenideElement tab = $x("//div[@id='rolYonetimiListingForm:filterPanel']//a[text()='Sorgulama ve Filtreleme']/../../h3");
        if (tab.getAttribute("aria-expanded").equals("false"))
            tabRolYonetimiSorgulamaveFiltreleme.click();
        return this;
    }

    @Step("Aksiyon Listesi Ad alanına \"{ad}\" yazılır.")
    public RolYonetimiPage aksiyonListesiAdDoldur(String ad) {
        ElementsCollection kisiselPages = $$("th[id='rolYonetimiEditorForm:rolAksiyonDataTable_paginator_top'] > span[class='ui-paginator-pages'] >  span");
        setValueJS(txtAksiyonListesiAd, ad);
        if (kisiselPages.size() > 1)
            kisiselPages.get(1).click();

        return this;
    }

    @Step("Aksiyon Listesi tablo kontrolu")
    public RolYonetimiPage aksiyonListesiKontol(String ad) {
        tblAksiyonListesi
                .filterBy(Condition.text(ad)).shouldHaveSize(1);
        return this;
    }

    @Step("Aksiyon Listesi tablosundan \"{ad}\" isimli rol silinir.")
    public RolYonetimiPage aksiyonListesiRolSil(String ad) {
        tblAksiyonListesi
                .filterBy(Condition.text(ad)).first()
                .$("button[id$='deleteRolAksiyonButton']").click();
        return this;
    }

    @Step("İşlem Onay Popupı kapatılır. \"{mesaj}\" ")
    public RolYonetimiPage islemOnayPopupKapat(String mesaj) {
        confirmDialog().onayMesajKontrolu(Condition.text(mesaj));
        confirmDialog().confirmEvetTikla();

        return this;
    }

}
