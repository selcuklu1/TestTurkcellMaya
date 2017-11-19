package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GercekKisiYonetimPage extends BaseLibrary {

    SelenideElement btnGercekKisiEkle = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable:addNewGercekKisiButton"));
    SelenideElement txtFiltreTCKimlikNo = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiTcKimlikNoFilterInput"));
    SelenideElement txtFiltreAd = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiAdFilterInput"));
    SelenideElement txtFiltreSoyad = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiSoyadFilterInput"));
    SelenideElement cmbFiltreDurum = $(By.id("gercekKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement filtreSorgulamaPanel = $(By.id("gercekKisiYonetimiListingForm:filterPanel"));
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
    SelenideElement btnEvetPopup = $(By.id("duplicateGercekKisiKayitEvet"));

    //Kişi ekleme zorunlu alanlar
    SelenideElement txtAdError = $(By.cssSelector("input[id='gercekKisiYonetimiEditorForm:adInput'][class*='ui-state-error']"));
    SelenideElement txtSoyadError = $(By.cssSelector("input[id='gercekKisiYonetimiEditorForm:soyadInput'][class*='ui-state-error']"));

    //Yeni İletişim Bilgisi
    SelenideElement txtIletisimBilgisiAdres = $(By.id("gercekKisiBilgileriEditorForm:adresInput"));
    // SelenideElement txtIletisimBilgisiIl= $(By.id("gercekKisiBilgileriEditorForm:lovIl:lovInputPanel"));
    BelgenetElement txtIletisimBilgisiIl = comboLov(By.id("gercekKisiBilgileriEditorForm:lovIl:LovText"));
    BelgenetElement txtIletisimBilgisiIlce = comboLov(By.id("gercekKisiBilgileriEditorForm:lovIlce:LovText"));
    BelgenetElement txtIletisimBilgisiUlke = comboLov(By.id("gercekKisiBilgileriEditorForm:lovUlke:LovText"));

    SelenideElement txtIletisimBilgisiEPosta = $(By.id("gercekKisiBilgileriEditorForm:ePostaInput"));
    SelenideElement btnIletisimBilgisiKaydet = $(By.id("gercekKisiBilgileriEditorForm:saveIletisimBilgisiButton"));

    //Table
    SelenideElement tableGercekKisiData = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable_data"));
    SelenideElement tbleTc = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[1]"));
    SelenideElement tbleAd = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[2]"));
    SelenideElement tbleSoyad = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td[3]"));
    SelenideElement tableKayitBulunamadi = $(By.xpath("//*[@id=\"gercekKisiYonetimiListingForm:gercekKisiDataTable_data\"]/tr/td"));
    SelenideElement tbleData0 = $(By.id("tbody[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable_data'] tr[data-ri$='0']"));
    SelenideElement tblePasif = $(By.id("div[id^='gercekKisiYonetimiListingForm:gercekKisiDataTable'] td[class$='center-aligned passive-cell']"));
    SelenideElement btnAktif = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable:0:aktifEtGercekKisi"));
    SelenideElement tbleGercekKisiDataTable = $(By.id("gercekKisiYonetimiListingForm:gercekKisiDataTable"));

    @Step("Yeni gerçek kişi ekle")
    public GercekKisiYonetimPage yeniGercekKisiEkle() {
        btnGercekKisiEkle.click();
        return this;
    }

    @Step("Kaydet")
    public GercekKisiYonetimPage kaydet() {
        btnKaydet.click();
        if (btnEvetPopup.isDisplayed()) {
            btnEvetPopup.click();
        }
        return this;
    }

    public GercekKisiYonetimPage kepAdresBilgiler() {
        btnKepAdresBilgiler.click();
        return this;
    }

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
        createMernisTCNO();
        return this;
    }

    public GercekKisiYonetimPage ara() {
        btnAra.click();
        return this;
    }

    public GercekKisiYonetimPage filtreDurumSec(String value) {
        cmbFiltreDurum.selectOptionByValue(value);
        return this;
    }

    public GercekKisiYonetimPage filtreSoyadDoldur(String text) {
        txtFiltreSoyad.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage filtreAdDoldur(String text) {
        txtFiltreAd.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage filtreTCKimlikNoDoldur(String text) {
        txtFiltreTCKimlikNo.setValue(text);
        return this;
    }

    @Step("İletişim bilgileri ekle")
    public GercekKisiYonetimPage iletisimBilgileriEkle() {
        btnIletisimBilgileriEkle.click();
        return this;
    }

    @Step("Adres doldur")
    public GercekKisiYonetimPage iletisimBilgisiAdresDoldur(String adres) {
        txtIletisimBilgisiAdres.sendKeys(adres);
        return this;
    }

    @Step("İl doldur")
    public GercekKisiYonetimPage iletisimBilgisiIlDoldur(String il) {
        txtIletisimBilgisiIl.selectComboLov(il);
        return this;
    }

    @Step("İlçe doldur")
    public GercekKisiYonetimPage iletisimBilgisiIlceDoldur(String ilce) {
        txtIletisimBilgisiIlce.selectComboLov(ilce);
        return this;
    }

    public GercekKisiYonetimPage iletisimBilgisiUlkeDoldur(String ulke) {
        txtIletisimBilgisiUlke.selectComboLov(ulke);
        return this;
    }

    @Step("Eposta doldur")
    public GercekKisiYonetimPage iletisimBilgisiEpostaDoldur(String eposta) {
        txtIletisimBilgisiEPosta.setValue(eposta);
        return this;
    }

    @Step("İletişim bilgisi kaydet")
    public GercekKisiYonetimPage iletisimBilgisiKaydet() {
        btnIletisimBilgisiKaydet.click();
        return this;
    }

    public GercekKisiYonetimPage filtreSorgulamaPaneliAc() {
        filtreSorgulamaPanel.click();
        txtFiltreTCKimlikNo.clear();
        txtFiltreAd.clear();
        txtFiltreSoyad.clear();
        return this;
    }

    @Step("Kayit kontrolu başarılı")
    public GercekKisiYonetimPage kayitKontrolu(String tcNO, String ad, String soyad)  {
        Assert.assertEquals(tbleTc.getText().equals(tcNO), true);
        Assert.assertEquals(tbleAd.getText().equals(ad), true);
        Assert.assertEquals(tbleSoyad.getText().equals(soyad), true);

        //  boolean status = findElementOnTableByColumnInputInAllPages(tbleGercekKisiDataTable, 3, soyad).isDisplayed();
        //Assert.assertEquals(status, true);
        return this;
    }

    @Step("TcNo kontrolu başarılı")
    public GercekKisiYonetimPage tcNoKontrolu(String tcNO) {
        Assert.assertEquals(tbleTc.getText().contains(tcNO), true);
        return this;
    }

    @Step("Kayıt bulunamadı kontrolu başarılı")
    public GercekKisiYonetimPage kayitBulunamadiKontrolu() {
        Assert.assertEquals(tableKayitBulunamadi.getText().contains("Kayıt Bulunamamıştır"), true);
        return this;
    }

    public GercekKisiYonetimPage pasiflerKayitKontrolu() {
        Assert.assertEquals(btnAktif.isDisplayed(), true);
        return this;
    }

    public String getTbleTCNO() {
        String getTC = tbleTc.getText();
        return getTC;
    }

    public GercekKisiYonetimPage zorunluAdSoyadAlanKontrolu() {
        Assert.assertEquals(txtAdError.exists(), true);
        Assert.assertEquals(txtSoyadError.exists(), true);
        return this;
    }
}
