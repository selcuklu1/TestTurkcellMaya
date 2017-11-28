package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class TuzelKisiYonetimiPage extends MainPage {

    //<editor-fold desc="Elements">
    //Sorgulama ve filtreleme alanı
    SelenideElement btnAra = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:searchTuzelKisiButton"));
    SelenideElement btnDuzenle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));
    SelenideElement btnTuzelKisiEkle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:addNewTuzelKisiButton"));
    SelenideElement filtreSorgulamaPanel = $(By.id("tuzelKisiYonetimiListingForm:filterPanel"));
    SelenideElement txtFiltreVergiSGKNo = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:tuzelKisiVergiKimlikNoFilterInput"));
    SelenideElement txtFiltreAd = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:tuzelKisiAdFilterInput"));
    SelenideElement txtFiltreDurum = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnIslemOnayiHayir = $(By.id("baseConfirmationDialog:baseConfirmationDialogCancelButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));


    //Tüzel kişi ekleme alanı
    SelenideElement cmbTuzelKisiTipi = $(By.id("tuzelKisiYonetimiEditorForm:tuzelKisiTipi"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("tuzelKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement txtVergiNo = $(By.id("tuzelKisiYonetimiEditorForm:vergiNoInput"));
    SelenideElement txtTuzelKisiAd = $(By.id("tuzelKisiYonetimiEditorForm:adInput"));
    SelenideElement txtTuzelKisiKisaAd = $(By.id("tuzelKisiYonetimiEditorForm:kisaAdInput"));
    SelenideElement btnTuzelKisiKaydet = $(By.id("tuzelKisiYonetimiEditorForm:saveTuzeKisiButton"));
    SelenideElement btnPopupEvet = $(By.id("duplicateTuzelKisiKayitEvet"));

    //İletişim Bilgileri
    SelenideElement btnYeniIletisimEkle = $(By.id("tuzelKisiYonetimiEditorForm:iletisimBilgileriDataTable:addNewIletisimBilgisiButton"));

    //Yeni İletişim Bilgisi
    SelenideElement txtIletisimBilgisiAdres = $(By.id("tuzelKisiBilgileriEditorForm:adresInput"));
    BelgenetElement txtIletisimBilgisiIl = comboLov(By.id("tuzelKisiBilgileriEditorForm:lovIl:LovText"));
    BelgenetElement txtIletisimBilgisiIlce = comboLov(By.id("tuzelKisiBilgileriEditorForm:lovIlce:LovText"));
    BelgenetElement txtIletisimBilgisiUlke = comboLov(By.id("tuzelKisiBilgileriEditorForm:lovUlke:LovText"));
    SelenideElement txtIletisimBilgisiEPosta = $(By.id("tuzelKisiBilgileriEditorForm:ePostaInput"));
    SelenideElement btnIletisimBilgisiKaydet = $(By.id("tuzelKisiBilgileriEditorForm:saveIletisimBilgisiButton"));
    SelenideElement btnIletisimBilgisiIptal = $(By.id("tuzelKisiBilgileriEditorForm:cancelSaveIletisimBilgisiButton"));
    SelenideElement txtIletisimBilgisiMobilTelNo = $(By.id("tuzelKisiBilgileriEditorForm:mobilInput"));
    SelenideElement txtIletisimBilgisiTelefonNo = $(By.id("tuzelKisiBilgileriEditorForm:telefonInput"));
    SelenideElement txtIletisimBilgisiIsTelefonNo = $(By.id("tuzelKisiBilgileriEditorForm:telefonIsInput"));
    SelenideElement txtIletisimBilgisiFaxs1 = $(By.id("tuzelKisiBilgileriEditorForm:fax1Input"));
    SelenideElement txtIletisimBilgisiFaxs2 = $(By.id("tuzelKisiBilgileriEditorForm:fax2Input"));
    SelenideElement txtIletisimBilgisiWebAdres = $(By.id("tuzelKisiBilgileriEditorForm:webAdresiInput"));
    SelenideElement btnUlkeDelete = $("    button[id^='tuzelKisiBilgileriEditorForm:lovUlke:j_idt'] span[class$='delete-icon']");

    SelenideElement btnKepAdresBilgileriEkle = $(By.id("tuzelKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement txtPopupKepAdresi = $(By.id("tuzelKisiKepAdresEditorForm:tuzelKisiKepAdresInputTextId"));
    SelenideElement cmbPopupKepHizmetSaglayicisi = $(By.id("tuzelKisiKepAdresEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("tuzelKisiKepAdresEditorForm:saveKepAdresiButton"));
    SelenideElement btnGuncelle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));

    //Tablo
    SelenideElement tblTuzelKisiDataTable = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable"));
    SelenideElement btnTuzelKisiAktifYap = $(By.cssSelector("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='aktifEtTuzelKisi']"));
    SelenideElement btnTuzelKisiPasifYap = $(By.cssSelector("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='pasifEtTuzelKisi']"));
    SelenideElement tblKayitBulunamadi = $(By.xpath("//*[@id=\"tuzelKisiYonetimiListingForm:tuzelKisiDataTable_data\"]/tr/td"));

    //</editor-fold>

    public TuzelKisiYonetimiPage openPage() {
        ustMenu("Tüzel Kişi Yönetimi");
        return this;
    }

    public TuzelKisiYonetimiPage filtreVergiNoDoldur(String vergiNo) {
        txtFiltreVergiSGKNo.setValue(vergiNo);
        return this;
    }

    public TuzelKisiYonetimiPage filtreAdDoldur(String ad) {
        txtFiltreAd.setValue(ad);
        return this;
    }

    public String vergiNoCek() {
        String vergiNo = txtVergiNo.getValue();
        return vergiNo;
    }

    public TuzelKisiYonetimiPage guncelle() {
        btnGuncelle.click();
        return this;
    }

    @Step("Filtrede durum seç")
    public TuzelKisiYonetimiPage filtreDurumSec(String secim) {
        txtFiltreDurum.selectOptionByValue(secim);
        return this;
    }

    public TuzelKisiYonetimiPage yeniTuzelKisiEkle() {
        btnTuzelKisiEkle.click();
        return this;
    }

    public TuzelKisiYonetimiPage popupKaydet() {
        btnPopupKaydet.click();
        return this;
    }

    public TuzelKisiYonetimiPage kepHizmetSaglayicisiSec(String value) {
        cmbPopupKepHizmetSaglayicisi.selectOption(value);
        return this;
    }

    public TuzelKisiYonetimiPage popupKepAdresiDoldur(String text) {
        txtPopupKepAdresi.setValue(text);
        return this;
    }

    public TuzelKisiYonetimiPage kepAdresBilgileriEkle() {
        btnKepAdresBilgileriEkle.click();
        return this;
    }

    public TuzelKisiYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public TuzelKisiYonetimiPage duzenleGonder() {
        btnDuzenle.click();
        return this;
    }

    public TuzelKisiYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    public TuzelKisiYonetimiPage tuzelKisiTipiSec(String secim) {
        cmbTuzelKisiTipi.selectOptionByValue(secim);
        return this;
    }

    public TuzelKisiYonetimiPage vergiNoDoldur(String vergiNo) {
        txtVergiNo.setValue(vergiNo);
        return this;
    }

    public TuzelKisiYonetimiPage adDoldur(String ad) {
        txtTuzelKisiAd.setValue(ad);
        return this;
    }

    public TuzelKisiYonetimiPage kisaAdDoldur(String kisaAd) {
        txtTuzelKisiKisaAd.setValue(kisaAd);
        return this;
    }

    public TuzelKisiYonetimiPage yeniIletisimEkle() {
        btnYeniIletisimEkle.click();
        return this;
    }

    public TuzelKisiYonetimiPage mobilTelNoDoldur(String mobilTelNo) {
        txtIletisimBilgisiMobilTelNo.setValue(mobilTelNo);
        return this;
    }

    public TuzelKisiYonetimiPage telNoDoldur(String telNo) {
        txtIletisimBilgisiTelefonNo.setValue(telNo);
        return this;
    }

    public TuzelKisiYonetimiPage faks1NoDoldur(String faks1) {
        txtIletisimBilgisiFaxs1.setValue(faks1);
        return this;
    }

    public TuzelKisiYonetimiPage faks2NoDoldur(String faks2) {
        txtIletisimBilgisiFaxs2.setValue(faks2);
        return this;
    }

    public TuzelKisiYonetimiPage adresDoldur(String adres) {
        txtIletisimBilgisiAdres.setValue(adres);
        return this;
    }

    public TuzelKisiYonetimiPage ulkeSec(String ulke) {
        btnUlkeDelete.click();
        txtIletisimBilgisiUlke.selectLov(ulke);
        return this;
    }

    public TuzelKisiYonetimiPage ilSec(String il) {
        txtIletisimBilgisiIl.selectLov(il);
        return this;
    }

    public TuzelKisiYonetimiPage ilceSec(String ilce) {
        txtIletisimBilgisiIlce.selectLov(ilce);
        return this;
    }

    public TuzelKisiYonetimiPage ePostaDoldur(String ePosta) {
        txtIletisimBilgisiEPosta.setValue(ePosta);
        return this;
    }

    public TuzelKisiYonetimiPage webAdresDoldur(String webAdres) {
        txtIletisimBilgisiWebAdres.setValue(webAdres);
        return this;
    }

    public TuzelKisiYonetimiPage iletisimBilgisiKaydet() {
        btnIletisimBilgisiKaydet.click();
        return this;
    }

    public TuzelKisiYonetimiPage iletisimBilgisiIptalEt() {
        btnIletisimBilgisiIptal.click();
        return this;
    }

    public TuzelKisiYonetimiPage tuzelKisiKaydet() {
        btnTuzelKisiKaydet.click();
        if (btnPopupEvet.isDisplayed()) {
            btnPopupEvet.click();
        }
        return this;
    }

    @Step("Filtre sorgulama paneli aç")
    public TuzelKisiYonetimiPage filtreSorgulamaPaneliAc() {

        filtreSorgulamaPanel.click();
        txtFiltreVergiSGKNo.clear();
        txtFiltreAd.clear();
        return this;
    }

    @Step("Tüzel kişi kayıt kontrolu")
    public TuzelKisiYonetimiPage aktifKisiKayitKontrolu(String vergiNo, String ad, String kisaAd) {


        boolean statusVergiNo = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 1, vergiNo).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 2, ad).isDisplayed();
        boolean statusKisaAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 3, kisaAd).isDisplayed();

        Assert.assertEquals(statusVergiNo, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusKisaAd, true);

        return this;
    }

    @Step("Pasif kayit kontrolu")
    public TuzelKisiYonetimiPage pasifKisiKayitKontrolu(String vergiNo, String ad, String kisaAd) {

        boolean statusTCNO = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 1, vergiNo).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 2, ad).isDisplayed();
        boolean statusSoyad = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 3, kisaAd).isDisplayed();

        Assert.assertEquals(statusTCNO, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusSoyad, true);

        return this;
    }
    @Step("Aktif Tüzel kişi kayıt kontrolu")
    public TuzelKisiYonetimiPage aktiflerTumListeKayitKontrolu() {

        boolean status = findElementOnTableAllPages(btnTuzelKisiAktifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Pasif Tüzel kişi kayıt kontrolu")
    public TuzelKisiYonetimiPage pasiflerTumListeKayitKontrolu() {

        boolean status = findElementOnTableAllPages(btnTuzelKisiPasifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Tüzel Kişi Aktif Yap")
    public TuzelKisiYonetimiPage tuzelKisiAktifYap() {
        btnTuzelKisiAktifYap.click();
        return this;
    }

    @Step("Tüzel Kişi Pasif Yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifYap() {
        btnTuzelKisiPasifYap.click();
        return this;
    }

    @Step("Popup: İşlem Onayı")
    public TuzelKisiYonetimiPage islemOnayi(String secim) {

        if (secim == "Evet") {
            btnIslemOnayiEvet.click();
        } else {
            btnIslemOnayiHayir.click();
        }
        return this;
    }

    @Step("Tüzel Kişi Aktif Yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifIseAktifYap() {

        if (btnTuzelKisiAktifYap.isDisplayed()) {
            btnTuzelKisiAktifYap.click();
            btnIslemOnayiEvet.click();
        }
        return this;
    }

    @Step("Tüzel Kişi Aktif Yap")
    public TuzelKisiYonetimiPage tuzelKisiAktifIsePasifYap() {

        if (btnTuzelKisiPasifYap.isDisplayed()) {
            btnTuzelKisiPasifYap.click();
            btnIslemOnayiEvet.click();
        }
        return this;
    }

    @Step("Kayıt bulunamadı kontrolu")
    public TuzelKisiYonetimiPage kayitBulunamadiKontrolu() {
        Assert.assertEquals(tblKayitBulunamadi.getText().contains("Kayıt Bulunamamıştır"), true);
        return this;
    }
}

