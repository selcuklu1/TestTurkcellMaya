package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-11-20
 * Proje: Türksat Functional Test Automation
 * Class: "Gerçek Kişi Yönetimi " konulu metotları içerir
 * Yazan: Sezai Çelik
 ****************************************************/
public class GercekKisiYonetimPage extends MainPage {

    //<editor-fold desc="Elements">
    //Sorgulama ve Filtreleme
    SelenideElement btnGercekKisiEkle = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable:addNewGercekKisiButton"));
    SelenideElement txtFiltreTCKimlikNo = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiTcKimlikNoFilterInput"));
    SelenideElement txtFiltreAd = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiAdFilterInput"));
    SelenideElement txtFiltreSoyad = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiSoyadFilterInput"));
    SelenideElement cmbFiltreDurum = $(By.id("gercekKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement filtreSorgulamaPanel = $(By.id("gercekKisiYonetimiListingForm:filterPanel"));
    SelenideElement btnAra = $(By.id("gercekKisiYonetimiListingForm:filterPanel:searchGercekKisiButton"));
    SelenideElement btnGercekKisiGuncelle = $("[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable'][id$='updateGercekKisiButton']");
    SelenideElement btnGercekKisiPasiYap = $("[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable'][id$='pasifEtGercekKisi']");
    SelenideElement btnGercekKisiAktifYap = $("[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable'][id$='aktifEtGercekKisi']");

    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
    SelenideElement btnIslemOnayiHayir = $(By.id("baseConfirmationDialog:baseConfirmationDialogCancelButton"));

    //Gerçek Kişi Ekleme
    SelenideElement txtTCKimlikNo = $(By.id("gercekKisiYonetimiEditorForm:tcKimlikNoInput"));
    SelenideElement txtOnEk = $(By.id("gercekKisiYonetimiEditorForm:onEkInput"));
    SelenideElement txtUnvan = $(By.id("gercekKisiYonetimiEditorForm:unvanId"));
    SelenideElement txtAd = $(By.id("gercekKisiYonetimiEditorForm:adInput"));
    SelenideElement txtSoyad = $(By.id("gercekKisiYonetimiEditorForm:soyadInput"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("gercekKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement btnKepAdresBilgileriEkle = $(By.id("gercekKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("gercekKisiYonetimiEditorForm:saveGercekKisiButton"));
    SelenideElement btnIletisimBilgileriEkle = $(By.id("gercekKisiYonetimiEditorForm:iletisimBilgileriDataTable:addNewIletisimBilgisiButton"));
    SelenideElement btnEvetPopup = $(By.id("duplicateGercekKisiKayitEvet"));

    //İletişim Bilgileri
    SelenideElement btnUpdateIletisimBilgisi = $(By.cssSelector("[id^='gercekKisiYonetimiEditorForm:iletisimBilgileriDataTable'][id$='updateIletisimBilgisiButton']"));

    //Kişi ekleme zorunlu alanlar
    SelenideElement txtAdError = $(By.cssSelector("input[id='gercekKisiYonetimiEditorForm:adInput'][class*='ui-state-error']"));
    SelenideElement txtSoyadError = $(By.cssSelector("input[id='gercekKisiYonetimiEditorForm:soyadInput'][class*='ui-state-error']"));

    //Yeni İletişim Bilgisi
    SelenideElement txtIletisimBilgisiAdres = $(By.id("gercekKisiBilgileriEditorForm:adresInput"));
    BelgenetElement txtIletisimBilgisiIl = comboLov(By.id("gercekKisiBilgileriEditorForm:lovIl:LovText"));
    BelgenetElement txtIletisimBilgisiIlce = comboLov(By.id("gercekKisiBilgileriEditorForm:lovIlce:LovText"));
    BelgenetElement txtIletisimBilgisiUlke = comboLov(By.id("gercekKisiBilgileriEditorForm:lovUlke:LovText"));
    SelenideElement txtIletisimBilgisiEPosta = $(By.id("gercekKisiBilgileriEditorForm:ePostaInput"));
    SelenideElement btnIletisimBilgisiKaydet = $(By.id("gercekKisiBilgileriEditorForm:saveIletisimBilgisiButton"));
    SelenideElement btnIletisimBilgisiIptal = $(By.id("gercekKisiBilgileriEditorForm:cancelSaveIletisimBilgisiButton"));
    SelenideElement txtIletisimBilgisiMobilTelNo = $(By.id("gercekKisiBilgileriEditorForm:mobilInput"));
    SelenideElement txtIletisimBilgisiTelefonNo = $(By.id("gercekKisiBilgileriEditorForm:telefonInput"));
    SelenideElement txtIletisimBilgisiIsTelefonNo = $(By.id("gercekKisiBilgileriEditorForm:telefonIsInput"));
    SelenideElement txtIletisimBilgisiFaxs1 = $(By.id("gercekKisiBilgileriEditorForm:fax1Input"));
    SelenideElement txtIletisimBilgisiFaxs2 = $(By.id("gercekKisiBilgileriEditorForm:fax2Input"));
    SelenideElement txtIletisimBilgisiWebAdres = $(By.id("gercekKisiBilgileriEditorForm:webAdresiInput"));


    //Kep Adres Bilgileri Yeni kayit
    SelenideElement btnKepAdresiKaydet = $(By.id("gercekKisiKepAdresUpdateDialogForm:saveKepAdresiButton"));
    SelenideElement txtKepAdresi = $(By.id("gercekKisiKepAdresUpdateDialogForm:gercekKisiKepAdresUpdateDialogId"));
    SelenideElement cmbKepHizmetSaglayici = $(By.id("gercekKisiKepAdresUpdateDialogForm:kephs"));

    //Tablo
    SelenideElement tblGercekKisiDataTable = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable"));
    SelenideElement tblTc = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[1]"));
    SelenideElement tblAd = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[2]"));
    SelenideElement tblSoyad = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[3]"));
    SelenideElement tblKayitBulunamadi = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td"));
    SelenideElement tblData0 = $(By.id("tbody[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable_data'] tr[data-ri$='0']"));
    SelenideElement tblPasif = $(By.id("div[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable'] td[class$='center-aligned passive-cell']"));

    //</editor-fold>


    public GercekKisiYonetimPage openPage() {
        ustMenu("Gerçek Kişi Yönetimi");
        return this;
    }


    @Step("Gerçek Kişi Güncelle")
    public GercekKisiYonetimPage gercekKisiGuncelle() {
        btnGercekKisiGuncelle.click();
        return this;
    }

    @Step("Gerçek Kişi Pasif Yap")
    public GercekKisiYonetimPage gercekKisiPasifYap() {
        btnGercekKisiPasiYap.click();
        return this;
    }


    @Step("Yeni gerçek kişi ekle")
    public GercekKisiYonetimPage yeniGercekKisiEkle() {
        btnGercekKisiEkle.click();
        return this;
    }

    //    @Step("Kaydet")
    public GercekKisiYonetimPage kaydet() {
        clickJs(btnKaydet);
        //  btnKaydet.click();
        if (btnEvetPopup.isDisplayed()) {
            btnEvetPopup.click();
        }
        return this;
    }

    //Double click ile tıklanıyor.
    @Step("Kaydet")
    public GercekKisiYonetimPage kaydet2() {
        btnKaydet.click();
        btnKaydet.click();
        return this;
    }


    @Step("Popup: İşlem Onayı")
    public GercekKisiYonetimPage islemOnayi(String secim) {

        if (secim == "Evet") {
            btnIslemOnayiEvet.click();
        } else {
            btnIslemOnayiHayir.click();
        }
        return this;
    }

    @Step("Kep adres bilgileri ekle")
    public GercekKisiYonetimPage kepAdresBilgileriEkle() {
        btnKepAdresBilgileriEkle.click();
        return this;
    }

    @Step("Kep adresi kullanıyor seç")
    public GercekKisiYonetimPage kepAdresiKullaniyor(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    @Step("Soyad doldur")
    public GercekKisiYonetimPage soyadDoldur(String text) {
        txtSoyad.setValue(text);
        return this;
    }

    @Step("Ad doldur")
    public GercekKisiYonetimPage adDoldur(String text) {
        txtAd.setValue(text);
        return this;
    }

    @Step("Ünvan doldur")
    public GercekKisiYonetimPage unvanDoldur(String text) {
        txtUnvan.setValue(text);
        return this;
    }

    @Step("Ön ek doldur")
    public GercekKisiYonetimPage onEkDoldur(String text) {
        txtOnEk.setValue(text);
        return this;
    }

    @Step("TC doldur")
    public GercekKisiYonetimPage tcKimlikNoDoldur(String text) {
        txtTCKimlikNo.setValue(text);
        return this;
    }

    @Step("Ara")
    public GercekKisiYonetimPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Filtrede durum seç")
    public GercekKisiYonetimPage filtreDurumSec(String value) {
        cmbFiltreDurum.selectOptionByValue(value);
        return this;
    }

    @Step("Filtrede soyad doldur")
    public GercekKisiYonetimPage filtreSoyadDoldur(String soyad) {
        txtFiltreSoyad.setValue(soyad);
        return this;
    }

    @Step("Filtrede ad doldur")
    public GercekKisiYonetimPage filtreAdDoldur(String ad) {
        txtFiltreAd.setValue(ad);
        return this;
    }

    @Step("Filtrede TC kimlik no doldur")
    public GercekKisiYonetimPage filtreTCKimlikNoDoldur(String text) {
        txtFiltreTCKimlikNo.setValue(text);
        return this;
    }

    @Step("İletişim bilgileri ekle")
    public GercekKisiYonetimPage iletisimBilgileriEkle() {
        btnIletisimBilgileriEkle.click();
        return this;
    }

    @Step("Mobil Tel. No doldur")
    public GercekKisiYonetimPage iletisimBilgisiMobilTelNoDoldur(String mobilTelNo) {
        txtIletisimBilgisiMobilTelNo.setValue(mobilTelNo);
        return this;
    }

    @Step("Telefon No doldur")
    public GercekKisiYonetimPage iletisimBilgisiTelefonNoDoldur(String telNo) {
        txtIletisimBilgisiTelefonNo.setValue(telNo);
        return this;
    }

    @Step("İş Telefon No doldur")
    public GercekKisiYonetimPage iletisimBilgisiIsTelefonNoDoldur(String isTelNo) {
        txtIletisimBilgisiIsTelefonNo.setValue(isTelNo);
        return this;
    }

    @Step("Faks1 No doldur")
    public GercekKisiYonetimPage iletisimBilgisiFaxs1Doldur(String faxs1) {
        txtIletisimBilgisiFaxs1.setValue(faxs1);
        return this;
    }

    @Step("Faks2 No doldur")
    public GercekKisiYonetimPage iletisimBilgisiFaxs2Doldur(String faxs2) {
        txtIletisimBilgisiFaxs2.setValue(faxs2);
        return this;
    }

    @Step("Adres doldur")
    public GercekKisiYonetimPage iletisimBilgisiAdresDoldur(String adres) {
        txtIletisimBilgisiAdres.setValue(adres);
        return this;
    }

    @Step("İl doldur")
    public GercekKisiYonetimPage iletisimBilgisiIlDoldur(String il) {
        txtIletisimBilgisiIl.selectLov(il);
        return this;
    }

    @Step("İlçe doldur")
    public GercekKisiYonetimPage iletisimBilgisiIlceDoldur(String ilce) {
        txtIletisimBilgisiIlce.selectLov(ilce);
        return this;
    }

    @Step("Ülke doldur")
    public GercekKisiYonetimPage iletisimBilgisiUlkeDoldur(String ulke) {
        txtIletisimBilgisiUlke.selectLov(ulke);
        return this;
    }

    @Step("Eposta doldur")
    public GercekKisiYonetimPage iletisimBilgisiEpostaDoldur(String eposta) {
        txtIletisimBilgisiEPosta.setValue(eposta);
        return this;
    }

    @Step("Web Adresi doldur")
    public GercekKisiYonetimPage iletisimBilgisiWebAdresiDoldur(String webAdres) {
        txtIletisimBilgisiWebAdres.setValue(webAdres);
        return this;
    }

    @Step("İletişim bilgisi güncelle")
    public GercekKisiYonetimPage iletisimBilgisiGüncelle() {
        btnUpdateIletisimBilgisi.click();
        return this;
    }

    @Step("İletişim bilgisi kaydet")
    public GercekKisiYonetimPage iletisimBilgisiKaydet() {
        btnIletisimBilgisiKaydet.click();
        return this;
    }

    @Step("İletişim bilgisi iptal et")
    public GercekKisiYonetimPage iletisimBilgisiIptalEt() {
        btnIletisimBilgisiIptal.click();
        return this;
    }

    @Step("Kep adresi kaydet")
    public GercekKisiYonetimPage kepAdresiKaydet() {
        btnKepAdresiKaydet.click();
        return this;
    }

    @Step("Kep adresi doldur")
    public GercekKisiYonetimPage kepAdresiDoldur(String kepAdres) {
        txtKepAdresi.sendKeys(kepAdres);
        return this;
    }

    @Step("Kep hizmet sağlayıcı seç")
    public GercekKisiYonetimPage kepHizmetSaglayiciSec(String kepHizmetSaglayici) {
        cmbKepHizmetSaglayici.selectOptionByValue(kepHizmetSaglayici);
        return this;
    }

    @Step("Filtre sorgulama paneli aç")
    public GercekKisiYonetimPage filtreSorgulamaPaneliAc() {
        filtreSorgulamaPanel.click();
        txtFiltreTCKimlikNo.clear();
        txtFiltreAd.clear();
        txtFiltreSoyad.clear();
        return this;
    }

    @Step("Aktif Kayit kontrolu")
    public GercekKisiYonetimPage kayitKontrolu(String tcNO, String ad, String soyad) {
        //Assert.assertEquals(tblTc.getText().equals(tcNO), true);
        //Assert.assertEquals(tbleAd.getText().equals(ad), true);
        //Assert.assertEquals(tbleSoyad.getText().equals(soyad), true);

        boolean statusTCNO = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 1, tcNO).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 2, ad).isDisplayed();
        boolean statusSoyad = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 3, soyad).isDisplayed();

        Assert.assertEquals(statusTCNO, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusSoyad, true);
        return this;
    }

    @Step("TC kimlik no kontrolu")
    public GercekKisiYonetimPage tcNoKontrolu(String tcNO) {
        Assert.assertEquals(tblTc.getText().contains(tcNO), true);
        return this;
    }

    @Step("Kayıt bulunamadı kontrolu")
    public GercekKisiYonetimPage kayitBulunamadiKontrolu() {
        Assert.assertEquals(tblKayitBulunamadi.getText().contains("Kayıt Bulunamamıştır"), true);
        return this;
    }

    @Step("Pasifler kayıt kontrolu")
    public GercekKisiYonetimPage pasiflerKayitKontrolu() {
        Assert.assertEquals(btnGercekKisiAktifYap.isDisplayed(), true);
        return this;
    }

    @Step("TC kimlik no alma")
    public String getTbleTCNO() {
        String getTC = tblTc.getText();
        return getTC;
    }

    @Step("Zorunlu ad, soyad alan kontrolu")
    public GercekKisiYonetimPage zorunluAdSoyadAlanKontrolu() {
        Assert.assertEquals(txtAdError.exists(), true);
        Assert.assertEquals(txtSoyadError.exists(), true);
        return this;
    }

    @Step("Pasif kayit kontrolu")
    public GercekKisiYonetimPage pasiflerKayitKontrolu(String tcNO, String ad, String soyad) {
        boolean statusTCNO = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 1, tcNO).isDisplayed();
        boolean statusAd = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 2, ad).isDisplayed();
        boolean statusSoyad = findElementOnTableByColumnInputInAllPages(tblGercekKisiDataTable, 3, soyad).isDisplayed();

        Assert.assertEquals(statusTCNO, true);
        Assert.assertEquals(statusAd, true);
        Assert.assertEquals(statusSoyad, true);
        return this;
    }

    public GercekKisiYonetimPage aktifYap() {

        if (btnGercekKisiAktifYap.isDisplayed()) {
            btnGercekKisiAktifYap.click();
            btnIslemOnayiEvet.click();
        }
        return this;
    }
}
