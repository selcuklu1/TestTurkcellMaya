package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-11-23
 * Proje: Türksat Functional Test Automation
 * Class: "Tüzel Kişi Yönetimi" konulu metotları içerir
 * Yazan: Sezai Çelik
 ****************************************************/

public class TuzelKisiYonetimiPage extends MainPage {

    //<editor-fold desc="Elements">
    //Sorgulama ve filtreleme alanı
    SelenideElement btnAra = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:searchTuzelKisiButton"));
    SelenideElement btnDuzenle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));
    SelenideElement btnTuzelKisiEkle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:addNewTuzelKisiButton"));
    SelenideElement filtreSorgulamaPanel = $(By.cssSelector("[id='tuzelKisiYonetimiListingForm'] [id='tuzelKisiYonetimiListingForm:filterPanel']"));
    SelenideElement txtFiltreVergiSGKNo = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:tuzelKisiVergiKimlikNoFilterInput"));
    SelenideElement txtFiltreAd = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:tuzelKisiAdFilterInput"));
    SelenideElement txtFiltreDurum = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnIslemOnayiHayir = $(By.id("baseConfirmationDialog:baseConfirmationDialogCancelButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));


    //Tüzel kişi ekleme alanı
    SelenideElement cmbTuzelKisiTipi = $(By.id("tuzelKisiYonetimiEditorForm:tuzelKisiTipi"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("tuzelKisiYonetimiEditorForm:kepAdresiKullanimCheckbox"));
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
    SelenideElement txtKepAdresi = $(By.id("tuzelKisiKepAdresEditorForm:tuzelKisiKepAdresInputTextId"));
    SelenideElement cmbPopupKepHizmetSaglayicisi = $(By.id("tuzelKisiKepAdresEditorForm:kephs"));
    SelenideElement btnKepAdresKaydet = $(By.id("tuzelKisiKepAdresEditorForm:saveKepAdresiButton"));
    SelenideElement btnKepAdresIptalEt = $(By.id("tuzelKisiKepAdresEditorForm:cancelSaveIletisimBilgisiButton"));
    SelenideElement btnIletisimBilgileriGuncelle = $(By.cssSelector("[id^='tuzelKisiYonetimiEditorForm:iletisimBilgileriDataTable'][id$='updateIletisimBilgisiButton']"));

    //Tablo
    SelenideElement tblTuzelKisiDataTable = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable"));
    SelenideElement btnTuzelKisiAktifYap = $(By.cssSelector("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='aktifEtTuzelKisi']"));
    SelenideElement btnTuzelKisiPasifYap = $(By.cssSelector("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='pasifEtTuzelKisi']"));
    SelenideElement tblKayitBulunamadi = $(By.xpath("//*[@id=\"tuzelKisiYonetimiListingForm:tuzelKisiDataTable_data\"]/tr/td"));
    SelenideElement btnTuzelisiGuncelle = $("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='updateTuzelKisiButton']");

    //</editor-fold>

    @Step("Tüzel Kişi Yönetimi sayfasını aç")
    public TuzelKisiYonetimiPage openPage() {
        ustMenu("Tüzel Kişi Yönetimi");
        $("#tuzelKisiYonetimiListingForm").shouldBe(visible);
        return this;
    }

    @Step("Filtrede Vergi No doldur")
    public TuzelKisiYonetimiPage filtreVergiNoDoldur(String vergiNo) {
        txtFiltreVergiSGKNo.setValue(vergiNo);
        return this;
    }

    @Step("Filtrede Ad doldur")
    public TuzelKisiYonetimiPage filtreAdDoldur(String ad) {
        txtFiltreAd.setValue(ad);
        return this;
    }

    public String vergiNoCek() {
        String vergiNo = txtVergiNo.getValue();
        return vergiNo;
    }

    @Step("Tüzel kişi güncelle")
    public TuzelKisiYonetimiPage tuzelKisiGuncelle() {
        btnTuzelisiGuncelle.click();
        return this;
    }

    @Step("Filtrede durum seç")
    public TuzelKisiYonetimiPage filtreDurumSec(String secim) {
        txtFiltreDurum.selectOptionByValue(secim);
        return this;
    }

    @Step("Yeni tüzel kişi ekle")
    public TuzelKisiYonetimiPage yeniTuzelKisiEkle() {
        btnTuzelKisiEkle.click();
        return this;
    }

    @Step("Kep adresi kaydet")
    public TuzelKisiYonetimiPage kepAdresiKaydet() {
        btnKepAdresKaydet.click();
        return this;
    }

    @Step("Kep adresi iptal et")
    public TuzelKisiYonetimiPage kepAdresiIptalet() {
        btnKepAdresIptalEt.click();
        return this;
    }

    @Step("İletişim Bilgileri güncelle")
    public TuzelKisiYonetimiPage iletisimBilgileriGuncelle() {
        btnIletisimBilgileriGuncelle.click();
        return this;
    }


    @Step("Kep hizmet sağlayıcısı seç")
    public TuzelKisiYonetimiPage kepHizmetSaglayicisiSec(String value) {
        cmbPopupKepHizmetSaglayicisi.selectOption(value);
        return this;
    }

    @Step("Kep adresi doldur")
    public TuzelKisiYonetimiPage kepAdresiDoldur(String text) {
        txtKepAdresi.setValue(text);
        return this;
    }

    @Step("Kep adres bilgileri ekle")
    public TuzelKisiYonetimiPage kepAdresBilgileriEkle() {
        btnKepAdresBilgileriEkle.click();
        return this;
    }

    @Step("Kep adresi kullanılıyor seç")
    public TuzelKisiYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    @Step("Düzenle")
    public TuzelKisiYonetimiPage duzenle() {
        btnDuzenle.click();
        return this;
    }

    @Step("Ara")
    public TuzelKisiYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Tüzel kişi tipi seç")
    public TuzelKisiYonetimiPage tuzelKisiTipiSec(String secim) {
        cmbTuzelKisiTipi.selectOption(secim);
        return this;
    }

    @Step("Vergi No doldur")
    public TuzelKisiYonetimiPage vergiNoDoldur(String vergiNo) {
        txtVergiNo.setValue(vergiNo);
        return this;
    }

    @Step("Ad doldur")
    public TuzelKisiYonetimiPage adDoldur(String ad) {
        txtTuzelKisiAd.setValue(ad);
        return this;
    }

    @Step("Kısa Ad doldur")
    public TuzelKisiYonetimiPage kisaAdDoldur(String kisaAd) {
        txtTuzelKisiKisaAd.setValue(kisaAd);
        return this;
    }

    @Step("Yeni iletişim ekle")
    public TuzelKisiYonetimiPage yeniIletisimEkle() {
        btnYeniIletisimEkle.click();
        return this;
    }

    @Step("Mobil tel no doldur")
    public TuzelKisiYonetimiPage mobilTelNoDoldur(String mobilTelNo) {
        txtIletisimBilgisiMobilTelNo.setValue(mobilTelNo);
        return this;
    }

    @Step("Tel no doldur")
    public TuzelKisiYonetimiPage telNoDoldur(String telNo) {
        txtIletisimBilgisiTelefonNo.setValue(telNo);
        return this;
    }

    @Step("Faks1 doldur")
    public TuzelKisiYonetimiPage faks1NoDoldur(String faks1) {
        txtIletisimBilgisiFaxs1.setValue(faks1);
        return this;
    }

    @Step("Faks2 doldur")
    public TuzelKisiYonetimiPage faks2NoDoldur(String faks2) {
        txtIletisimBilgisiFaxs2.setValue(faks2);
        return this;
    }

    @Step("Adres doldur")
    public TuzelKisiYonetimiPage adresDoldur(String adres) {
        txtIletisimBilgisiAdres.setValue(adres);
        return this;
    }

    @Step("Ükle seç")
    public TuzelKisiYonetimiPage ulkeSec(String ulke) {

        if (btnUlkeDelete.isDisplayed() == false) {
            txtIletisimBilgisiUlke.selectLov(ulke);
        }
        
        return this;
    }

    @Step("İl seç")
    public TuzelKisiYonetimiPage ilSec(String il) {
        txtIletisimBilgisiIl.selectLov(il);
        return this;
    }

    @Step("İlçe seç")
    public TuzelKisiYonetimiPage ilceSec(String ilce) {
        txtIletisimBilgisiIlce.selectLov(ilce);
        return this;
    }

    @Step("Eposta doldur")
    public TuzelKisiYonetimiPage ePostaDoldur(String ePosta) {
        txtIletisimBilgisiEPosta.setValue(ePosta);
        return this;
    }

    @Step("Web adres doldur")
    public TuzelKisiYonetimiPage webAdresDoldur(String webAdres) {
        txtIletisimBilgisiWebAdres.setValue(webAdres);
        return this;
    }

    @Step("İletişim bilgisi kaydet")
    public TuzelKisiYonetimiPage iletisimBilgisiKaydet() {
        btnIletisimBilgisiKaydet.click();
        return this;
    }

    @Step("İletişim bilgisi iptal et")
    public TuzelKisiYonetimiPage iletisimBilgisiIptalEt() {
        btnIletisimBilgisiIptal.click();
        return this;
    }

    @Step("Tüzel kişi kaydet")
    public TuzelKisiYonetimiPage tuzelKisiKaydet() {
        btnTuzelKisiKaydet.shouldBe(visible);
        clickJs(btnTuzelKisiKaydet);
        if (btnPopupEvet.isDisplayed()) {
            btnPopupEvet.click();
        }
        return this;
    }

    @Step("Filtre sorgulama paneli aç")
    public TuzelKisiYonetimiPage filtreSorgulamaPaneliAc() {

        filtreSorgulamaPanel.shouldBe(visible);
        filtreSorgulamaPanel.click();
        txtFiltreVergiSGKNo.clear();
        txtFiltreAd.clear();
        return this;
    }

    @Step("Aktif tüzel kişi kayıt kontrolu")
    public TuzelKisiYonetimiPage aktifTuzelKisiKayitKontrolu(String vergiNo, String ad, String kisaAd) {

        btnTuzelisiGuncelle.shouldBe(visible);

        boolean statusVergiNo = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 1, vergiNo).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 2, ad).isDisplayed();
        boolean statusKisaAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 3, kisaAd).isDisplayed();

        Assert.assertEquals(statusVergiNo, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusKisaAd, true);

        return this;
    }

    @Step("Pasif tüzel kişi kayit kontrolu")
    public TuzelKisiYonetimiPage pasifTuzelKisiKayitKontrolu(String vergiNo, String ad, String kisaAd) {

        tblTuzelKisiDataTable.shouldBe(visible);
        boolean statusTCNO = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 1, vergiNo).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 2, ad).isDisplayed();
        boolean statusSoyad = findElementOnTableByColumnInputInAllPages(tblTuzelKisiDataTable, 3, kisaAd).isDisplayed();

        Assert.assertEquals(statusTCNO, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusSoyad, true);

        return this;
    }

    @Step("Aktif Tüzel kişi tüm liste kayıt kontrolu")
    public TuzelKisiYonetimiPage aktiflerTumListeKayitKontrolu() {

        btnTuzelKisiPasifYap.shouldBe(visible);

        boolean status = findElementOnTableAllPages(btnTuzelKisiAktifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Pasif Tüzel kişi tüm liste kayıt kontrolu")
    public TuzelKisiYonetimiPage pasiflerTumListeKayitKontrolu() {

        btnTuzelKisiAktifYap.shouldBe(visible);
        boolean status = findElementOnTableAllPages(btnTuzelKisiPasifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Tüzel Kişi Aktif Yap")
    public TuzelKisiYonetimiPage tuzelKisiAktifYap() {
        btnTuzelKisiAktifYap.shouldBe(visible);
        btnTuzelKisiAktifYap.click();
        return this;
    }

    @Step("Tüzel Kişi Pasif Yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifYap() {
        btnTuzelKisiPasifYap.shouldBe(visible);
        btnTuzelKisiPasifYap.click();
        return this;
    }

    @Step("Tüzel Kişi Aktif Yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifIseAktifYap() {

        if (btnTuzelKisiAktifYap.isDisplayed()) {
            btnTuzelKisiAktifYap.click();
            btnIslemOnayiEvet.click();
            Allure.addAttachment("Tüzel kişi pasif olduğu için aktif yapıldı.", "");
        }
        return this;
    }

    @Step("Tüzel Kişi Pasif Yap")
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

