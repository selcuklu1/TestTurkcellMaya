package pages.ustMenuPages;

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

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

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
    SelenideElement strPopupAktifEtmeMesaji = $("[id='baseConfirmationDialog:form'] [class='content']");

    //Tüzel kişi ekleme alanı
    SelenideElement cmbTuzelKisiTipi = $(By.id("tuzelKisiYonetimiEditorForm:tuzelKisiTipi"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("tuzelKisiYonetimiEditorForm:kepAdresiKullanimCheckbox"));
    SelenideElement txtVergiNo = $(By.id("tuzelKisiYonetimiEditorForm:vergiNoInput"));
    SelenideElement txtTuzelKisiAd = $(By.id("tuzelKisiYonetimiEditorForm:adInput"));
    SelenideElement txtTuzelKisiKisaAd = $(By.id("tuzelKisiYonetimiEditorForm:kisaAdInput"));
    SelenideElement btnTuzelKisiKaydet = $(By.id("tuzelKisiYonetimiEditorForm:saveTuzeKisiButton"));
    SelenideElement btnPopupEvet = $(By.id("duplicateTuzelKisiKayitEvet"));

    //İletişim Bilgileri
    SelenideElement txtTVLogo = $(By.id("tuzelKisiYonetimiEditorForm:tvLogoInput"));
    SelenideElement txtRadyoLogo = $(By.id("tuzelKisiYonetimiEditorForm:radyoLogoInput"));
    SelenideElement cmbKarasalTV = $(By.id("tuzelKisiYonetimiEditorForm:karasalTvId"));
    SelenideElement cmbKarasalRadyo = $(By.id("tuzelKisiYonetimiEditorForm:karasalRadyoId"));
    SelenideElement cmbKabloTV = $(By.id("tuzelKisiYonetimiEditorForm:kabloTvId"));
    SelenideElement cmbKabloRadyo = $(By.id("tuzelKisiYonetimiEditorForm:kabloRadyoId"));
    SelenideElement chkKarasalTVYayinda = $(By.id("tuzelKisiYonetimiEditorForm:karasalTvYayindaId"));
    SelenideElement chkKarasalRadyoYayinda = $(By.id("tuzelKisiYonetimiEditorForm:karasalRadyoYayindaId"));
    SelenideElement chkUyduTVYayinda = $(By.id("tuzelKisiYonetimiEditorForm:uyduTvYayindaId"));
    SelenideElement chkUyduRadyoYayinda = $(By.id("tuzelKisiYonetimiEditorForm:uyduRadyoYayindaId"));
    SelenideElement chkKabloTVYayinda = $(By.id("tuzelKisiYonetimiEditorForm:kabloTvYayindaId"));
    SelenideElement chkKabloRadyoYayinda = $(By.id("tuzelKisiYonetimiEditorForm:kabloRadyoYayindaId"));
    SelenideElement chkIstegeBagliTV = $(By.id("tuzelKisiYonetimiEditorForm:istegeBagliTvId"));
    SelenideElement chkUyduTV = $(By.id("tuzelKisiYonetimiEditorForm:uyduTvId"));
    SelenideElement chkUyduRadyo = $(By.id("tuzelKisiYonetimiEditorForm:uyduRadyoId"));
    SelenideElement chkIstegeBagliRadyo = $(By.id("tuzelKisiYonetimiEditorForm:istegeBagliRadyoId"));
    SelenideElement chkPlatformIsletmecisi = $(By.id("tuzelKisiYonetimiEditorForm:platformIsletmecisiId"));
    SelenideElement chkAltyapiIsletmecisi = $(By.id("tuzelKisiYonetimiEditorForm:altyapiIsletmecisiId"));
    SelenideElement chkLisansIptal = $(By.id("tuzelKisiYonetimiEditorForm:lisansIptalId"));


    //Medya şirketi
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
    ElementsCollection tblKisiler = $$("[id='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'] tr[class^='ui-widget-content']");
    //</editor-fold>

    @Step("Tüzel Kişi Yönetimi sayfasını aç")
    public TuzelKisiYonetimiPage openPage() {
        ustMenu(UstMenuData.TeskilatKisiTanimlari.TuzelKisiYonetimi);
        $("#tuzelKisiYonetimiListingForm").shouldBe(visible);
        return this;
    }

    @Step("Filtrede vergi no doldur")
    public TuzelKisiYonetimiPage filtreVergiNoDoldur(String vergiNo) {
        txtFiltreVergiSGKNo.setValue(vergiNo);
        return this;
    }

    @Step("Filtrede ad doldur")
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

    @Step("Tüzel kişi güncelle")
    public TuzelKisiYonetimiPage tuzelKisiSecGuncele(String kullanici) {
        tblKisiler.filterBy(Condition.text(kullanici)).get(0)
                .$$("[id^='tuzelKisiYonetimiListingForm:tuzelKisiDataTable'][id$='updateTuzelKisiButton']").get(0).click();
        return this;
    }

    @Step("Filtrede durum seç")
    public TuzelKisiYonetimiPage filtreDurumSec(String filtreDurumu) {
        txtFiltreDurum.selectOptionByValue(filtreDurumu);
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

    @Step("İletişim bilgileri güncelle")
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
        btnKepAdresBilgileriEkle.pressEnter();
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

    @Step("Tüzel Kişi kayıtlarının tabloda listelendiği görülür")
    public TuzelKisiYonetimiPage tuzelKisilerinListelendigiGorme() {
        boolean durum = $$(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable_data")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Eklenen yeni kayıt listede görüntülenir: {kep}")
    public TuzelKisiYonetimiPage kepAdresBilgileriKayitListedeGeldigiGorulur(String kep) {
        boolean durum = $("[id='gercekKisiYonetimiEditorForm:kepBilgileriDataTable']").shouldBe(Condition.text(kep)).shouldBe(Condition.visible).exists() == true;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Tüzel kişi tipi seç")
    public TuzelKisiYonetimiPage tuzelKisiTipiSec(String tuzelKisiTipi) {
        cmbTuzelKisiTipi.selectOption(tuzelKisiTipi);
        return this;
    }

    @Step("Vergi no doldur")
    public TuzelKisiYonetimiPage vergiNoDoldur(String vergiNo) {
        txtVergiNo.setValue(vergiNo);
        return this;
    }

    @Step("Ad doldur")
    public TuzelKisiYonetimiPage adDoldur(String ad) {
        txtTuzelKisiAd.setValue(ad);
        return this;
    }

    @Step("Kısa ad doldur")
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

    @Step("Aktif tüzel kişi tüm liste kayıt kontrolu")
    public TuzelKisiYonetimiPage aktiflerTumListeKayitKontrolu() throws InterruptedException {

        String formTuzelKisiYonetimi = "tuzelKisiYonetimiListingForm";
        btnTuzelKisiPasifYap.shouldBe(visible);

        boolean status = findElementOnTableAllPages(formTuzelKisiYonetimi, btnTuzelKisiAktifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Pasif tüzel kişi tüm liste kayıt kontrolu")
    public TuzelKisiYonetimiPage pasiflerTumListeKayitKontrolu() throws InterruptedException {

        String formTuzelKisiYonetimi = "tuzelKisiYonetimiListingForm";

        btnTuzelKisiAktifYap.shouldBe(visible);
        boolean status = findElementOnTableAllPages(formTuzelKisiYonetimi, btnTuzelKisiPasifYap);
        Assert.assertEquals(status, false);
        return this;
    }

    @Step("Tüzel kişi aktif yap")
    public TuzelKisiYonetimiPage tuzelKisiAktifYap() {
        btnTuzelKisiAktifYap.shouldBe(visible);
        btnTuzelKisiAktifYap.click();
        return this;
    }

    @Step("Tüzel kişi pasif yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifYap() {
        btnTuzelKisiPasifYap.shouldBe(visible);
        btnTuzelKisiPasifYap.click();
        return this;
    }

    @Step("Tüzel kişi pasif ise aktif yap")
    public TuzelKisiYonetimiPage tuzelKisiPasifIseAktifYap() {

        btnTuzelisiGuncelle.shouldBe(visible);

        if (btnTuzelKisiAktifYap.isDisplayed()) {
            btnTuzelKisiAktifYap.click();
            btnIslemOnayiEvet.click();
            Allure.addAttachment("Tüzel kişi pasif olduğu için aktif yapıldı.", "");
        }
        return this;
    }

    @Step("Tüzel kişi aktif ise pasif yap")
    public TuzelKisiYonetimiPage tuzelKisiAktifIsePasifYap() {

        btnTuzelisiGuncelle.shouldBe(visible);

        if (btnTuzelKisiPasifYap.isDisplayed()) {
            btnTuzelKisiPasifYap.click();
            btnIslemOnayiEvet.click();
            Allure.addAttachment("Tüzel kişi aktif olduğu için pasif yapıldı.", "");

        }
        return this;
    }

    @Step("Kayıt bulunamadı kontrolu")
    public TuzelKisiYonetimiPage kayitBulunamadiKontrolu() {
        Assert.assertEquals(tblKayitBulunamadi.getText().contains("Kayıt Bulunamamıştır"), true);
        return this;
    }

    @Step("Kep Adresi Kullaniyor ve Kep Adres Bilgileri alan kontrolleri")
    public TuzelKisiYonetimiPage kepAdresiAlanKontrolu() {

        Assert.assertEquals(chkKepAdresiKullaniyor.isDisplayed(), true);
        Assert.assertEquals(btnKepAdresBilgileriEkle.isDisplayed(), true);

        return this;
    }


    @Step("Gelen popup mesaji kontrolu: {popupAktifEtmeMesaji}")
    public TuzelKisiYonetimiPage popupTuzelKisiAktifEtmeKontrolu(String popupAktifEtmeMesaji) {

        strPopupAktifEtmeMesaji.shouldBe(visible);

        String getPopupMessage = strPopupAktifEtmeMesaji.text();

        Assert.assertEquals(getPopupMessage.contains(popupAktifEtmeMesaji), true);
        return this;
    }

    @Step("Medya Şirketi alan kontrolleri")
    public TuzelKisiYonetimiPage medyaSirketiAlanKontrolleri() {

        Assert.assertEquals(txtTVLogo.isDisplayed(), true);
        Allure.addAttachment("TV Logo alan kontrolu başaralı.", "");

        Assert.assertEquals(txtRadyoLogo.isDisplayed(), true);
        Allure.addAttachment("Radyo Logo alan kontrolu başaralı.", "");

        Assert.assertEquals(cmbKarasalTV.isDisplayed(), true);
        Allure.addAttachment("Karasal TV alan kontrolu başaralı.", "");

        Assert.assertEquals(cmbKarasalRadyo.isDisplayed(), true);
        Allure.addAttachment("Karasal Radyo alan kontrolu başaralı.", "");

        Assert.assertEquals(cmbKabloTV.isDisplayed(), true);
        Allure.addAttachment("Kablo TV alan kontrolu başaralı.", "");

        Assert.assertEquals(cmbKabloRadyo.isDisplayed(), true);
        Allure.addAttachment("Kablo Radyo alan kontrolu başaralı.", "");

        Assert.assertEquals(chkKarasalTVYayinda.isDisplayed(), true);
        Allure.addAttachment("Karasal TV Yayında alan kontrolu başaralı.", "");

        Assert.assertEquals(chkKarasalRadyoYayinda.isDisplayed(), true);
        Allure.addAttachment("Karasal Radyo Yayında alan kontrolu başaralı.", "");

        Assert.assertEquals(chkUyduTVYayinda.isDisplayed(), true);
        Allure.addAttachment("Uydu Tv Yayında alan kontrolu başaralı.", "");

        Assert.assertEquals(chkUyduRadyoYayinda.isDisplayed(), true);
        Allure.addAttachment("Uydu Radyo Yayında alan kontrolu başaralı.", "");

        Assert.assertEquals(chkKabloTVYayinda.isDisplayed(), true);
        Allure.addAttachment("Kablo Tv Yayınd alan kontrolu başaralı.", "");

        Assert.assertEquals(chkKabloRadyoYayinda.isDisplayed(), true);
        Allure.addAttachment("Kablo Radyo Yayında alan kontrolu başaralı.", "");

        Assert.assertEquals(chkIstegeBagliTV.isDisplayed(), true);
        Allure.addAttachment("İsteğe Bağlı Tv alan kontrolu başaralı.", "");

        Assert.assertEquals(chkUyduTV.isDisplayed(), true);
        Allure.addAttachment("Uydu Tv alan kontrolu başaralı.", "");

        Assert.assertEquals(chkUyduRadyo.isDisplayed(), true);
        Allure.addAttachment("Uydu Radyo alan kontrolu başaralı.", "");

        Assert.assertEquals(chkIstegeBagliTV.isDisplayed(), true);
        Allure.addAttachment("İsteğe Bağlı TV alan kontrolu başaralı.", "");

        Assert.assertEquals(chkIstegeBagliRadyo.isDisplayed(), true);
        Allure.addAttachment("İsteğe Bağlı Radyo alan kontrolu başaralı.", "");

        Assert.assertEquals(chkPlatformIsletmecisi.isDisplayed(), true);
        Allure.addAttachment("Platform İşletmecisi alan kontrolu başaralı.", "");

        Assert.assertEquals(chkAltyapiIsletmecisi.isDisplayed(), true);
        Allure.addAttachment("Altyapı İşletmecisi alan kontrolu başaralı.", "");

        Assert.assertEquals(chkLisansIptal.isDisplayed(), true);
        Allure.addAttachment("Lisans İptal alan kontrolu başaralı.", "");
        return this;

    }

    @Step("Karasal TV seç")
    public TuzelKisiYonetimiPage karasalTVSec(String karasalTV) {
        cmbKarasalTV.selectOption(karasalTV);
        return this;
    }

    @Step("Karasal Radyo seç")
    public TuzelKisiYonetimiPage karasalRadyoSec(String karasalRadyo) {
        cmbKarasalRadyo.selectOption(karasalRadyo);
        return this;
    }

    @Step("Karasal TV Yayında seç")
    public TuzelKisiYonetimiPage karasalTVYayindaSec(boolean secim) {
        chkKarasalTVYayinda.setSelected(secim);
        return this;
    }

    @Step("Kablo TV seç")
    public TuzelKisiYonetimiPage kabloTVSec(String kabloTV) {
        cmbKabloTV.selectOption(kabloTV);
        return this;
    }

    @Step("Kablo TV Yayında seç")
    public TuzelKisiYonetimiPage kabloTVYayindaSec(boolean secim) {
        chkKabloTVYayinda.setSelected(secim);
        return this;
    }

    @Step("İsteğe Bağlı TV seç")
    public TuzelKisiYonetimiPage istegeBagliTvSec(boolean secim) {
        chkIstegeBagliTV.setSelected(secim);
        return this;
    }

    @Step("Platform İşletmecisi seç")
    public TuzelKisiYonetimiPage platformIsletmecisiSe(boolean secim) {
        chkPlatformIsletmecisi.setSelected(secim);
        return this;
    }

    @Step("Medya şirketi tipinde tüzel kişi ekleme")
    public List<String> medyaSirketiTuzelKisiEkleme() {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = "TuzelMedya" + getSysDate();
        String tuzelKisiTipi = "MEDYA ŞİRKETİ";
        String adres = "Gültepe Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İst";
        String ilce = "Kağ";
        String eposta = kisaAd + "medyasirketi@turksat.com.tr";
        String webAdres = "www." + kisaAd + "medyasirketi.com";
        String telNo = "5391111111";
        String faksNo = "2121111111";
        String basariMesaji = "İşlem başarılıdır!";

        openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec(tuzelKisiTipi)
                .medyaSirketiAlanKontrolleri()
                .vergiNoDoldur(vergiNo)
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)

                .karasalTVSec("T1")
                .karasalTVYayindaSec(true)
                .kabloTVSec("TEK")
                .kabloTVYayindaSec(true)
                .istegeBagliTvSec(true)
                .platformIsletmecisiSe(true)

                .yeniIletisimEkle()

                .mobilTelNoDoldur(telNo)
                .telNoDoldur(telNo)
                .faks1NoDoldur(faksNo)
                .faks2NoDoldur(faksNo)
                .adresDoldur(adres)
                .ulkeSec(ulke)

                .ilSec(il)
                .ilceSec(ilce)
                .ePostaDoldur(eposta)
                .webAdresDoldur(webAdres)
                .iletisimBilgisiKaydet()

                .tuzelKisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        return Arrays.asList(ad,kisaAd,vergiNo);
    }
}

