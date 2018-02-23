package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class BirimYonetimiPage extends MainPage {

    SelenideElement btnAra = $(By.id("birimYonetimiFilterForm:accordionPanel:searchEntitiesButton"));
    SelenideElement btnDuzenle = $(By.cssSelector("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
    ElementsCollection paylastiklarimList = $$("[id='birimYonetimiListingForm:birimTreeTable'] > button[role='row']");
    SelenideElement cmbBirimTuru = $(By.id("birimYonetimiFilterForm:accordionPanel:birimTuruSelectBox"));
    SelenideElement btnBirimTurumDropDownButton = $("span[id='birimYonetimiEditorForm:birimTipiAutoComplete'] > button");
    SelenideElement cmbDurum = $(By.id("birimYonetimiFilterForm:accordionPanel:durumSelectBox"));
    SelenideElement btnArti = $("[id^='birimYonetimiListingForm:birimTreeTable'] [id$='addNewBirimButton']");
    SelenideElement cmbGorunurlukTipi = $(By.id("birimYonetimiEditorForm:gorunurlukTipiSelect"));
    SelenideElement chkDisBirim = $(By.id("birimYonetimiEditorForm:disBirimCheckbox_input"));
    SelenideElement txtAd = $(By.id("birimYonetimiEditorForm:adInput"));
    SelenideElement txtKisaAdi = $(By.id("birimYonetimiEditorForm:kisaAdInput"));
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
    SelenideElement chkSDPnaGoreBirimKlasorleriniOlustur = $(By.id("birimYonetimiEditorForm:birimStandartDosyaPlaninaGore_input"));
    SelenideElement chkYetkiDevriVar = $(By.id("birimYonetimiEditorForm:yetkiDevriCheckbox_input"));
    SelenideElement btnSolUstLogoEkle = $(By.id("birimYonetimiEditorForm:solUstMenuLogoEkle"));
    SelenideElement btnSagUstLogoEkle = $(By.id("birimYonetimiEditorForm:sagUstMenuLogoEkle"));
    SelenideElement btnAltLogoyuDegistir = $(By.id("birimYonetimiEditorForm:altMenuLogoEkle"));

    SelenideElement txtSolUstLogoBoy = $(By.xpath("//label[normalize-space(text())='Sol Üst Logo Boy']"));
    SelenideElement txtSagUstLogoBoy = $(By.xpath("//label[normalize-space(text())='Sağ Üst Logo Boy']"));
    SelenideElement txtSolUstGenislik = $(By.xpath("//label[normalize-space(text())='Sol Üst Logo Genişlik']"));
    SelenideElement txtSagUstLogoGenislik = $(By.xpath("//label[normalize-space(text())='Sağ Üst Logo Genişlik']"));

    BelgenetElement txtBirim = comboLov("[id$='birimLov:LovText']");
    SelenideElement btnAktiflerIlkGuncelle = $(By.id("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
    SelenideElement btnPasiflerIlkGuncelle = $(By.id("birimYonetimiListingForm:pasifBirimlerDataTable:0:updateBirimButton"));
    SelenideElement btnYeniKepAdresBilgileriEkle = $(By.id("birimYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));

    SelenideElement txtPopupKepAdresi = $(By.id("kepAdresBilgiEditorForm:kepAdresiInputTextId"));
    SelenideElement cmbPopupHizmetSaglayicisi = $(By.id("kepAdresBilgiEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("kepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("birimYonetimiEditorForm:saveBirimButton"));

    BelgenetElement cmbPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:postaBirimiLov:LovText"));
    BelgenetElement cmbKepPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:kepPostaBirimiLov:LovText"));
    SelenideElement txtAntentBilgisi = $(By.id("birimYonetimiEditorForm:antetBilgisiInput"));
    ElementsCollection tblBirimYonetimiListesi = $$("[id='birimYonetimiListingForm:birimTreeTable'] > table > tbody > tr");// span[class='ui-chkbox-icon']");
    SelenideElement btnPasifYap = $("[id$='updateBirimStatusButton'] [class$='to-passive-status-icon']");
    SelenideElement btnAktifYap = $("[id$='updateBirimStatusButton'] [class$='to-active-status-icon']");
    SelenideElement chkDisBirimBos = $("[id='birimYonetimiEditorForm:disBirimCheckbox'] [class$='ui-state-default']");
    SelenideElement chkDisBirimDolu = $("[id='birimYonetimiEditorForm:disBirimCheckbox'] [class$='ui-state-disabled']");
    SelenideElement filtreSorgulamaPanel = $("[id='birimYonetimiFilterForm'] [id='birimYonetimiFilterForm:accordionPanel']");


    // Hüseyin TÜMER

    SelenideElement btnBirimEkle = $(By.id("birimYonetimiListingForm:birimTreeTable:addNewBirimButton"));
    SelenideElement txtAntetBilgisi = $(By.id("birimYonetimiEditorForm:antetBilgisiInput"));
    BelgenetElement txtPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:postaBirimiLov:LovText"));
    BelgenetElement txtKepPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:kepPostaBirimiLov:LovText"));
    SelenideElement btnBirimAmiriEkle = $(By.id("birimYonetimiEditorForm:birimKullaniciDataTable:addNewBirimKullaniciLinkButton"));
    BelgenetElement txtKullanici = comboLov(By.id("birimAmiriEditorForm:birimAmiriLov:LovText"));
    SelenideElement txtGorev = $(By.id("birimAmiriEditorForm:gorevAutoComplete_input"));
    SelenideElement cmbGizlilikDerecesi = $(By.id("birimAmiriEditorForm:birimGuvenlikKoduSelect"));
    SelenideElement btnBirimAmiriKaydet = $(By.id("birimAmiriEditorForm:saveBirimKullaniciIliskiButton"));
    BelgenetElement txtUstBirim = comboLov(By.id("birimYonetimiEditorForm:ustBirimLov:LovText"));
    SelenideElement btnBirimKaydet = $(By.id("birimYonetimiEditorForm:saveBirimButton"));

    @Step("Birim Yönetimi sayfası aç")
    public BirimYonetimiPage openPage() {
        ustMenu(UstMenuData.TeskilatKisiTanimlari.BirimYonetimi);
        return this;
    }

    @Step("Kaydet")
    public BirimYonetimiPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Popup kaydet")
    public BirimYonetimiPage popupKaydet() {
        btnPopupKaydet.click();
        return this;
    }

    @Step("Popup hizmet sağlayıcısı seç")
    public BirimYonetimiPage popupHizmetSaglayicisiSec(String value) {
        cmbPopupHizmetSaglayicisi.selectOption(value);
        return this;
    }

    @Step("Popup kep adresi doldur")
    public BirimYonetimiPage popupKepAdresiDoldur(String kepAdresi) {
        txtPopupKepAdresi.setValue(kepAdresi);
        return this;
    }

    @Step("Kep adresi bilgileri ekle")
    public BirimYonetimiPage yeniKepAdresBilgileriEkle() {
        btnYeniKepAdresBilgileriEkle.pressEnter();
        return this;
    }

    @Step("Aktiflerde ilk birimin güncelle butonu tıklanır")
    public BirimYonetimiPage aktiflerIlkBirimGuncelle() {
        btnAktiflerIlkGuncelle.click();
        return this;
    }

    @Step("Pasiflerde ilk birimin güncelle butonu tıklanır")
    public BirimYonetimiPage pasiflerIlkBirimGuncelle() {
        btnPasiflerIlkGuncelle.click();
        return this;
    }

    @Step("Sağ alanda Birim Güncelleme ekranı geldiği görülür")
    public BirimYonetimiPage sagAlandaGuncellemeEkranGeldigiGorme() {
        boolean durum = $$(By.id("birimYonetimiEditorForm:birimYonetimiEditorPanel_header")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Sağ üst logo genişlik doldur")
    public BirimYonetimiPage sagUstLogoGenislik(String genislik) {
        txtSagUstLogoGenislik.setValue(genislik);
        return this;
    }

    @Step("Sol üst genişlik doldur")
    public BirimYonetimiPage solUstGenislikDoldur(String genislik) {
        txtSolUstGenislik.setValue(genislik);
        return this;
    }

    @Step("Sağ üst logo boy doldur")
    public BirimYonetimiPage sagUstLogoBoyDoldur(String boy) {
        txtSagUstLogoBoy.setValue(boy);
        return this;
    }

    @Step("Sol üst logo boy doldur")
    public BirimYonetimiPage solUstLogoBoyDoldur(String boy) {
        txtSolUstLogoBoy.setValue(boy);
        return this;
    }

    @Step("Alt logoyu değiştir")
    public BirimYonetimiPage altLogoDegistir() {
        btnAltLogoyuDegistir.click();
        return this;
    }

    @Step("Sağ ust logo ekle tıkla")
    public BirimYonetimiPage sagUstLogoEkleGonder() {
        btnSagUstLogoEkle.click();
        return this;
    }

    @Step("Sol ust Logo ekle tıkla")
    public BirimYonetimiPage solUstLogoEkle() {
        btnSolUstLogoEkle.click();
        return this;
    }

    @Step("Yetki devri var seç")
    public BirimYonetimiPage yetkiDevriVarSec(Boolean secim) {
        chkYetkiDevriVar.setSelected(secim);
        return this;
    }

    @Step("Kep adresi kullaniyor seç")
    public BirimYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    @Step("Açıklama doldur")
    public BirimYonetimiPage aciklamaDoldur(String text) {
        txtAciklama.setValue(text);
        return this;
    }

    @Step("Olur metni doldur")
    public BirimYonetimiPage olurMetniDoldur(String text) {
        txtOlurMetni.setValue(text);
        return this;
    }

    @Step("Genel evrak seç")
    public BirimYonetimiPage genelEvrakSec(boolean secim) {
        chkGenelEvrak.setSelected(secim);
        return this;
    }

    @Step("Arşiv birimi seç")
    public BirimYonetimiPage arsivBirimiSec(String value) {
        chkArsivBirimi.selectOption(value);
        return this;
    }

    @Step("Belgenet kullanılıtor mu seç")
    public BirimYonetimiPage BelgenetKullaniyorMuSec(String value) {
        cmbBelgenetKullaniyorMu.selectOption(value);
        return this;
    }


    @Step("Posta şekli seç")
    public BirimYonetimiPage postaSekliSec(String postaSekli) {
        cmbPostaSekli.selectOption(postaSekli);
        return this;
    }

    @Step("Yetki devri var seç")
    public BirimYonetimiPage medasPostaBirimiDoldur(String text) {
        txtMedasPostaBirimi.setValue(text);
        return this;
    }

    @Step("Fiziki arşiv birimi doldur")
    public BirimYonetimiPage fizikiArsivBirimiDoldur(String text) {
        treeFizikiArsivBirimi.setValue(text);
        return this;
    }

    @Step("Tree bağlı birimi doldur")
    public BirimYonetimiPage treeBagliBirimDoldur(String text) {
        treeBagliBirim.setValue(text);
        return this;
    }

    @Step("Birim bağ türü seç")
    public BirimYonetimiPage birimBagTuruSec(String birimBagTuru) {
        cmbBirimBagTuru.selectOption(birimBagTuru);
        return this;
    }

    @Step("Giden evrakları numaratoru doldur")
    public BirimYonetimiPage gidenEvraklariNumaratoruDoldur(String gidenEvrakNumaratoru) {
        txtGidenEvrakNumaratoru.setValue(gidenEvrakNumaratoru);
        Selenide.sleep(3000);
        txtGidenEvrakNumaratoru.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Gelen evraklar numaratoru doldur")
    public BirimYonetimiPage gelenEvraklariNumaratoruDoldur(String gelenEvrakNumaratoru) {
        txtGelenEvrakNumaratoru.setValue(gelenEvrakNumaratoru);
        Selenide.sleep(3000);
        txtGelenEvrakNumaratoru.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Birimi tipi seç")
    public BirimYonetimiPage birimTipiSec(String birimTipi) {
        cmbBirimTipi.setValue(birimTipi);
        Selenide.sleep(3000);
        cmbBirimTipi.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("İdari kimlik doldur")
    public BirimYonetimiPage idariKimlikKoduDoldur(String idariBirimKimlikKodu) {
        txtIdariKimlikKodu.setValue(idariBirimKimlikKodu);
        return this;
    }

    @Step("Antet tipi seç")
    public BirimYonetimiPage antetTipiSec(String antentTipi) {
        cmbAntetTipi.selectOption(antentTipi);
        return this;
    }

    @Step("Karargah kısaltması doldur")
    public BirimYonetimiPage karargahKisaltmasiDoldur(String text) {
        txtKarargahKisaltmasi.setValue(text);
        return this;
    }

    @Step("Mesaj adresi doldur")
    public BirimYonetimiPage mesajAdresiDoldur(String text) {
        txtMesajAdresi.setValue(text);
        return this;
    }

    @Step("Özel hitap seç")
    public BirimYonetimiPage ozelHitap(Boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }

    @Step("Kısa adı doldur")
    public BirimYonetimiPage kisaAdiDoldur(String kisaAd) {
        txtKisaAdi.setValue(kisaAd);
        return this;
    }

    @Step("Ad doldur")
    public BirimYonetimiPage adDoldur(String ad) {
        txtAd.setValue(ad);
        return this;
    }

    @Step("Dış birim seç")
    public BirimYonetimiPage disBirimSec(Boolean secim) {
        chkDisBirim.setSelected(secim);
        return this;
    }

    @Step("Görünürlük tipi seç")
    public BirimYonetimiPage gorunurlukTipiSec(String secim) {
        cmbGorunurlukTipi.selectOption(secim);
        return this;
    }

    @Step("Ekle")
    public BirimYonetimiPage ekle() {
        btnArti.click();
        return this;
    }

    @Step("Durum seç")
    public BirimYonetimiPage durumSec(String secim) {
        cmbDurum.selectOption(secim);
        return this;
    }

    @Step("Birim türü seç")
    public BirimYonetimiPage birimTuruSec(String secim) {
        cmbBirimTuru.selectOption(secim);
        return this;
    }

    @Step("Düzenle")
    public BirimYonetimiPage duzenle() {
        btnDuzenle.click();
        return this;
    }

    @Step("Ara")
    public BirimYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Birim doldur")
    public BirimYonetimiPage birimFiltreDoldur(String birim) {
        txtBirim.selectLov(birim);
        return this;
    }

    @Step("Birim oluştur")
    public String birimOlustur(String ustBirim) {

        String idariKimlikKodu = "1" + (new Random().nextInt((900000 - 100000) + 1) + 100000);
        String yeniBirimAdi = "Birim" + idariKimlikKodu;
        String postaBirimi = ustBirim;
        String birimAmiri = "Huser TUMER";
        String gorev = "Ağ (Network) Uzman Yardımcısı";

        btnBirimEkle.click();
        txtAd.setValue(yeniBirimAdi);
        txtAntetBilgisi.setValue(yeniBirimAdi);
        txtIdariKimlikKodu.setValue(idariKimlikKodu);
        cmbBirimTipi.setValue("Genel Müdürlüğü");
        Selenide.sleep(3000);
        cmbBirimTipi.sendKeys(Keys.ENTER);
        txtUstBirim.selectLov(postaBirimi);
        txtPostaBirimi.selectLov(postaBirimi);
        txtKepPostaBirimi.selectLov(postaBirimi);
        btnBirimAmiriEkle.click();
        txtKullanici.selectLov(birimAmiri);
        txtGorev.setValue(gorev);
        Selenide.sleep(3000);
        txtGorev.sendKeys(Keys.ENTER);
        cmbGizlilikDerecesi.selectOption("Hizmete Özel");
        btnBirimAmiriKaydet.click();
        btnBirimKaydet.click();
        return yeniBirimAdi;
    }

    @Step("Üst Birim seç")
    public BirimYonetimiPage ustBirimSec(String ustBirim, String ustBirimDetail) {
        txtUstBirim.selectLov(ustBirim, ustBirimDetail);
        return this;
    }

    @Step("Posta birimi seç")
    public BirimYonetimiPage postaBirimiSec(String postaBirim, String postaBirimDetail) {
        cmbPostaBirimi.selectLov(postaBirim, postaBirimDetail);
        return this;
    }

    @Step("Kep Posta birimi seç")
    public BirimYonetimiPage kepPostaBirimiSec(String kepPostaBirim, String kepPostaBirimDetail) {
        cmbKepPostaBirimi.selectLov(kepPostaBirim, kepPostaBirimDetail);
        return this;
    }

    @Step("Belgenet kullanıyor mu seç: {secim}")
    public BirimYonetimiPage belgenetKullanıyormuSec(String secim) {
        cmbBelgenetKullaniyorMu.selectOption(secim);
        return this;
    }

    @Step("Antent bilgisi doldur")
    public BirimYonetimiPage antetBilgisiDoldur(String antentBilgisi) {
        txtAntentBilgisi.setValue(antentBilgisi);
        return this;
    }

    @Step("Birim Yönetimi alan kontrolleri")
    public BirimYonetimiPage birimYonetimiAlanKontrolleri() {

        Assert.assertEquals(chkDisBirim.isDisplayed(), true, "Dış Birim");
        Allure.addAttachment("Dış Birim alanı kontrolu başarılı", "");

        Assert.assertEquals(chkOzelHitap.isDisplayed(), true, "Özel Hitap");
        Allure.addAttachment("Özel Hitap alanı kontrolu başarılı", "");

        //Upgrade sonrası burası çıkmıyor
/*      Assert.assertEquals(txtKarargahKisaltmasi.isDisplayed(), true, "Karargah Kısaltması");
        Allure.addAttachment("Karargah Kısaltması alanı kontrolu başarılı", "");*/

        Assert.assertEquals(treeBagliBirim.isDisplayed(), true, "Bağlı Birim");
        Allure.addAttachment("Bağlı Birim alanı kontrolu başarılı", "");

        Assert.assertEquals(treeFizikiArsivBirimi.isDisplayed(), true, "Fizik Arşiv Birimi");
        Allure.addAttachment("Fizik Arşiv Birimi alanı kontrolu başarılı", "");

        Assert.assertEquals(chkArsivBirimi.isDisplayed(), true, "Arşiv Birimi");
        Allure.addAttachment("Arşiv Birimi alanı kontrolu başarılı", "");

        Assert.assertEquals(chkGenelEvrak.isDisplayed(), true, "Genel Evrak");
        Allure.addAttachment("Genel Evrak alanı kontrolu başarılı", "");

        Assert.assertEquals(txtOlurMetni.isDisplayed(), true, "Olur Metni");
        Allure.addAttachment("Olur Metni alanı kontrolu başarılı", "");

        Assert.assertEquals(txtAciklama.isDisplayed(), true, "Açıklama");
        Allure.addAttachment("Açıklama alanı kontrolu başarılı", "");

        Assert.assertEquals(chkKepAdresiKullaniyor.isDisplayed(), true, "Kep Adresi Kullanılıyor");
        Allure.addAttachment("Kep Adresi Kullanılıyor alanı kontrolu başarılı", "");

        Assert.assertEquals(chkSDPnaGoreBirimKlasorleriniOlustur.isDisplayed(), true, "SDPna Göre Birim Klasörlerini Oluştur");
        Allure.addAttachment("SDPna Göre Birim Klasörlerini Oluştur alanı kontrolu başarılı", "");

        Assert.assertEquals(chkYetkiDevriVar.isDisplayed(), true, "Yetki Devri Var");
        Allure.addAttachment("Yetki Devri Var alanı kontrolu başarılı", "");

        Assert.assertEquals(txtSagUstLogoBoy.isDisplayed(), true, "Sağ Üst Logo Boy");
        Allure.addAttachment("Sağ Üst Logo Boy alanı kontrolu başarılı", "");

        Assert.assertEquals(txtSolUstLogoBoy.isDisplayed(), true, "Sol Üst Logo Boy");
        Allure.addAttachment("Sol Üst Logo Boy alanı kontrolu başarılı", "");

        Assert.assertEquals(btnSolUstLogoEkle.isDisplayed(), true, "Sol Üst Logo Ekle");
        Allure.addAttachment("Sol Üst Logo Ekle alanı kontrolu başarılı", "");

        Assert.assertEquals(btnSagUstLogoEkle.isDisplayed(), true, "Sağ Üst Logo Ekle");
        Allure.addAttachment("Sağ Üst Logo Ekle alanı kontrolu başarılı", "");

        Assert.assertEquals(txtSagUstLogoGenislik.isDisplayed(), true, "Sağ Üst Logo Genişlik");
        Allure.addAttachment("Sağ Üst Logo Genişlik alanı kontrolu başarılı", "");

        Assert.assertEquals(txtSolUstGenislik.isDisplayed(), true, "Sol Üst Logo Genişlik");
        Allure.addAttachment("Sol Üst Logo Genişlik alanı kontrolu başarılı", "");

        Assert.assertEquals(btnBirimAmiriEkle.isDisplayed(), true, "Birim Amiri");
        Allure.addAttachment("Birim Amiri alanı kontrolu başarılı", "");

        Assert.assertEquals(btnYeniKepAdresBilgileriEkle.isDisplayed(), true, "Kep Adres Bilgiler");
        Allure.addAttachment("Kep Adres Bilgileri alanı kontrolu başarılı", "");

        takeScreenshot();

        return this;
    }

    @Step("Eklenen birim sonuç tablosunda listelenir")
    public BirimYonetimiPage birimKontrolu(String birimAdi) {

        tblBirimYonetimiListesi
                .filterBy(Condition.text(birimAdi))
                .shouldHaveSize(1);

        return this;
    }

    @Step("Pasif yap butonunun aktif olarak geldiği kontrolu")
    public BirimYonetimiPage pasifYapButonuKontrolu() {

        Assert.assertEquals(btnPasifYap.isDisplayed(), true, "Pasif Yap kontrolu");
        Allure.addAttachment("Pasif Yap butonu kontrolu başarılı", "");

        return this;
    }

    @Step("Aktif yap butonunun aktif olarak geldiği")
    public BirimYonetimiPage aktifYapButonuKontrolu() {

        Assert.assertEquals(btnAktifYap.isDisplayed(), true, "Aktif Yap kontrolu");
        Allure.addAttachment("Pasif Yap butonu kontrolu başarılı", "");

        return this;
    }

    @Step("Dış birim check boxının boş olduğu kontrolu")
    public BirimYonetimiPage disBirimChkBoxBosOlduguKontrolu() {

        Assert.assertEquals(chkDisBirimBos.isDisplayed(), true, "Dış birim check boxının boş olduğu kontrolu");
        Allure.addAttachment("Dış birim check boxının boş olduğu kontrolu", "");

        return this;
    }

    @Step(" Dış birim check boxının işaretli olduğu kontrolu")
    public BirimYonetimiPage disBirimChkBoxDoluOlduguKontrolu() {

        Assert.assertEquals(chkDisBirimDolu.isDisplayed(), true, "Dış birim check boxının işaretli olduğu kontrolu");
        Allure.addAttachment("Dış birim check boxının işaretli olduğu kontrolu", "");

        return this;
    }

    @Step("Filtre sorgulama paneli aç")
    public BirimYonetimiPage filtreSorgulamaPaneliAc() {

        filtreSorgulamaPanel.shouldBe(visible).click();
        return this;
    }

    @Step("Yeni Birim Kayıt")
    public List<String> yeniBirimKayit() {

        String sistemTarihi = getSysDate();
        String birimAdi = "Birim_" + sistemTarihi;
        String birimKisaAdi = "birim_" + sistemTarihi;
        String idariBirimKimlikKodu = sistemTarihi;
        String birim = "Optiim Birim";
        String birimDetail = "YGD";
        String birimTipi = "Genel Müdürlüğü";
        String gelenEvrakNumaratoru = "Türksat AŞ_numarator - Gelen Evrak";
        String gidenEvrakNumaratoru = "Türksat AŞ_numarator - Giden Evrak";
        String basariMesaji = "İşlem başarılıdır!";

        openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()
                .gorunurlukTipiSec("Görünür")
                .adDoldur(birimAdi)
                .kisaAdiDoldur(birimKisaAdi)
                .antetTipiSec("Normal")
                .antetBilgisiDoldur(birimAdi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustBirimSec(birim, birimDetail)
                .birimTipiSec(birimTipi)
                .gelenEvraklariNumaratoruDoldur(gelenEvrakNumaratoru)
                .gidenEvraklariNumaratoruDoldur(gidenEvrakNumaratoru)
                .birimBagTuruSec("Bağlı Kuruluş")
                .postaBirimiSec(birim, birimDetail)
                .kepPostaBirimiSec(birim, birimDetail)
                .postaSekliSec("Otomatik")
                .belgenetKullanıyormuSec("Evet")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        return Arrays.asList(birimAdi,birimKisaAdi,idariBirimKimlikKodu);
    }
}
