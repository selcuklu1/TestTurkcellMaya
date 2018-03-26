package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class BirimYonetimiPage extends MainPage {

    SelenideElement btnAra = $(By.id("birimYonetimiFilterForm:accordionPanel:searchEntitiesButton"));
//    SelenideElement btnDuzenle = $(By.cssSelector("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
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
    BelgenetElement cmbAntetTipi = comboBox(By.id("birimYonetimiEditorForm:antetTipiSelect"));
    SelenideElement cmbAntetTipi2 = $("select[id='birimYonetimiEditorForm:antetTipiSelect_input']");
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
    SelenideElement chkKepAdresiKullaniyor = $(By.id("birimYonetimiEditorForm:kepAdresiKullanimCheckbox"));
    SelenideElement chkSDPnaGoreBirimKlasorleriniOlustur = $(By.id("birimYonetimiEditorForm:birimStandartDosyaPlaninaGore_input"));
    SelenideElement chkYetkiDevriVar = $(By.id("birimYonetimiEditorForm:yetkiDevriCheckbox_input"));
    SelenideElement btnSolUstLogoEkle = $(By.id("birimYonetimiEditorForm:solUstMenuLogoEkle"));
    SelenideElement btnSagUstLogoEkle = $(By.id("birimYonetimiEditorForm:sagUstMenuLogoEkle"));
    SelenideElement btnAltLogoyuDegistir = $(By.id("birimYonetimiEditorForm:altMenuLogoEkle"));
    SelenideElement btnYeniAltBirimEkle = $("[id^='birimYonetimiListingForm:birimTreeTable'] [id$='addNewAltBirimButton']");
    SelenideElement btnAltBirimAcma = $("[id='birimYonetimiListingForm:birimTreeTable_node_0'] [class='ui-treetable-toggler ui-icon ui-icon-triangle-1-e']");

    SelenideElement txtSolUstLogoBoy = $(By.xpath("//label[normalize-space(text())='Sol Üst Logo Boy']"));
    SelenideElement txtSagUstLogoBoy = $(By.xpath("//label[normalize-space(text())='Sağ Üst Logo Boy']"));
    SelenideElement txtSolUstGenislik = $(By.xpath("//label[normalize-space(text())='Sol Üst Logo Genişlik']"));
    SelenideElement txtSagUstLogoGenislik = $(By.xpath("//label[normalize-space(text())='Sağ Üst Logo Genişlik']"));
    SelenideElement chkbirimingeldigi = $(By.id("birimYonetimiListingForm:birimTreeTable_node_0"));

    BelgenetElement txtBirim = comboLov("[id$='birimLov:LovText']");
    BelgenetElement txtAltAntetİletisim = comboLov("[id$='iletisimBilgileriSatirlar_header']");
    BelgenetElement txtİletisimBilgileri1 = comboLov("[id$='iletisimBilgisiSatir1Text']");
    BelgenetElement txtİletisimBilgileri2 = comboLov("[id$='iletisimBilgisiSatir2Text']");
    BelgenetElement txtİletisimBilgileri3 = comboLov("[id$='iletisimBilgisiSatir3Text']");
    SelenideElement btnAktiflerIlkGuncelle = $(By.id("birimYonetimiListingForm:birimTreeTable:0:updateBirimButton"));
    SelenideElement btnPasiflerIlkGuncelle = $(By.id("birimYonetimiListingForm:pasifBirimlerDataTable:0:updateBirimButton"));
    SelenideElement btnYeniKepAdresBilgileriEkle = $(By.id("birimYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));

    SelenideElement txtiletisimBilgisi1= $(By.id("birimIletisimBilgileriSatirlarEditorForm:iletisimBilgisiSatir1Text"));
    SelenideElement txtiletisimBilgisi2= $(By.id("birimIletisimBilgileriSatirlarEditorForm:iletisimBilgisiSatir2Text"));
    SelenideElement txtiletisimBilgisi3= $(By.id("birimIletisimBilgileriSatirlarEditorForm:iletisimBilgisiSatir3Text"));
    SelenideElement btnIletisimKaydet = $(By.id("birimIletisimBilgileriSatirlarEditorForm:saveIletisimBilgisiBirimButton"));
    SelenideElement btnIletisimGuncelle = $(By.id("birimYonetimiEditorForm:iletisimBilgileriDataTable:0:updateIletisimBilgisiButton"));
    SelenideElement txtMobilTel= $(By.id("birimIletisimBilgileriEditorForm:mobilInputBirim"));
    SelenideElement txtTelNo= $(By.id("birimIletisimBilgileriEditorForm:telefonInputBirim"));
    SelenideElement txtİsTelefonNo= $(By.id("birimIletisimBilgileriEditorForm:telefonIsInputBirim"));
    SelenideElement txtFaksNo1= $(By.id("birimIletisimBilgileriEditorForm:fax1InputBirim"));
    SelenideElement txtFaksNo2= $(By.id("birimIletisimBilgileriEditorForm:fax2InputBirim"));
    SelenideElement txtAdres= $(By.id("birimIletisimBilgileriEditorForm:adresInputBirim"));
    BelgenetElement txtİlSec = comboLov(By.id("birimIletisimBilgileriEditorForm:lovIl:LovText"));
    BelgenetElement txtUlkeSec = comboLov(By.id("birimIletisimBilgileriEditorForm:lovUlke:LovSecilen"));
    BelgenetElement txtİlceSec = comboLov(By.id("birimIletisimBilgileriEditorForm:lovIlce:LovText"));
    SelenideElement txtEposta= $(By.id("birimIletisimBilgileriEditorForm:ePostaInputBirim"));
    SelenideElement txtWebAdresi= $(By.id("birimIletisimBilgileriEditorForm:webAdresiInputBirim"));
    SelenideElement btnIletisimbilgileriKaydet = $(By.id("birimIletisimBilgileriEditorForm:saveIletisimBilgisiBirimButton"));


    SelenideElement txtPopupKepAdresi = $(By.id("kepAdresBilgiEditorForm:kepAdresiInputTextId"));
    SelenideElement cmbPopupHizmetSaglayicisi = $(By.id("kepAdresBilgiEditorForm:kephs"));
    SelenideElement btnPopupKepAdresBilgileriKaydet = $(By.id("kepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("birimYonetimiEditorForm:saveBirimButton"));

    BelgenetElement cmbPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:postaBirimiLov:LovText"));
    BelgenetElement cmbKepPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:kepPostaBirimiLov:LovText"));
    SelenideElement txtAntentBilgisi = $(By.id("birimYonetimiEditorForm:antetBilgisiInput"));
    ElementsCollection tblBirimYonetimiListesi = $$("[id='birimYonetimiListingForm:birimTreeTable'] > table > tbody > tr");// span[class='ui-chkbox-icon']");
    ElementsCollection tblPasifBirimListesi = $$("[id='birimYonetimiListingForm:pasifBirimlerDataTable'] > table > tbody > tr");// span[class='ui-chkbox-icon']");
    SelenideElement islemOnayiAciklama = $(By.id("baseConfirmationDialog:explanationInput"));
    SelenideElement onayDialogu = $(By.id("baseConfirmationDialog:dialog"));
    ElementsCollection tblAktifBirimListesi = $$("[id='birimYonetimiListingForm:birimTreeTable'] > table > tbody");// span[class='ui-chkbox-icon']");
    SelenideElement btnPasifYap = $("[id$='updateBirimStatusButton'] [class$='to-passive-status-icon']");
    SelenideElement btnAktifYap = $("[id$='updateBirimStatusButton'] [class$='to-active-status-icon']");
    SelenideElement chkDisBirimBos = $("[id='birimYonetimiEditorForm:disBirimCheckbox'] [class$='ui-state-default']");
    SelenideElement chkDisBirimDolu = $("[id='birimYonetimiEditorForm:disBirimCheckbox'] [class$='ui-state-disabled']");
    SelenideElement filtreSorgulamaPanel = $("[id='birimYonetimiFilterForm'] [id='birimYonetimiFilterForm:accordionPanel']");
    SelenideElement popupIslemOnaySorusu = $("[id='baseConfirmationDialog:form'] [class='content']");


    SelenideElement txtPopupIslemOnayAciklama = $(By.id("baseConfirmationDialog:explanationInput"));
    SelenideElement txtPopupIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
    SelenideElement txtPopupIslemOnayiHayir = $(By.id("baseConfirmationDialog:baseConfirmationDialogCancelButton"));

    SelenideElement dteBirimAmiriAtamaBaslangicTarihi = $(By.id("birimAmiriEditorForm:gorevBaslangicTarihiCalendar"));
    SelenideElement dteBirimAmiriAtamaBitisTarihi = $(By.id("birimAmiriEditorForm:gorevBitisTarihiCalendar"));

    SelenideElement dteBirimAmiriAtamaBaslangicTarihiInput = $(By.id("birimAmiriEditorForm:gorevBaslangicTarihiCalendar_input"));
    SelenideElement dteBirimAmiriAtamaBitisTarihiInput = $(By.id("birimAmiriEditorForm:gorevBitisTarihiCalendar_input"));

    SelenideElement cmbBirimAmiriAtamaBagTipi = $(By.id("birimAmiriEditorForm:birimBagTipiSelect"));
    ElementsCollection tblKepAdresBilgileriListesi = $$("[id='birimYonetimiEditorForm:kepBilgileriDataTable'] > table > tbody > tr");// span[class='ui-chkbox-icon']");
    SelenideElement btnBirimGuncelle = $("[id^='birimYonetimiListingForm:birimTreeTable'][id$='updateBirimButton']");
    SelenideElement btnBirimAktifYap = $(By.cssSelector("[id^='birimYonetimiListingForm:birimTreeTable'] [class$='to-active-status-icon']"));
    SelenideElement btnBirimPasifYap = $(By.cssSelector("[id^='birimYonetimiListingForm:birimTreeTable'] [class$='to-passive-status-icon']"));
    SelenideElement tblPasifKayitlarBulunamadi = $(By.xpath("//*[@id=\"birimYonetimiListingForm:pasifBirimlerDataTable_data\"]/tr/td"));
    SelenideElement btnGuncelle = $(By.id("birimYonetimiEditorForm:updateIletisimBilgisiButton"));
    SelenideElement btnBirimdekiKullanicilar = $(By.cssSelector("[id^='birimYonetimiListingForm:birimTreeTable'] [id$='showBirimdekiKullanicilar']"));



    // Hüseyin TÜMER

    SelenideElement btnBirimEkle = $(By.id("birimYonetimiListingForm:birimTreeTable:addNewBirimButton"));
    SelenideElement txtAntetBilgisi = $(By.id("birimYonetimiEditorForm:antetBilgisiInput"));
    BelgenetElement txtPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:postaBirimiLov:LovText"));
    BelgenetElement txtKepPostaBirimi = comboLov(By.id("birimYonetimiEditorForm:kepPostaBirimiLov:LovText"));
    SelenideElement btnBirimAmiriEkle = $(By.id("birimYonetimiEditorForm:birimKullaniciDataTable:addNewBirimKullaniciLinkButton"));
    SelenideElement btnbirimdekikullanicilar = $(By.id("birimYonetimiListingForm:birimTreeTable:0:showBirimdekiKullanicilar"));
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
    public BirimYonetimiPage popupKepAdresBilgileriKaydet() {
        btnPopupKepAdresBilgileriKaydet.click();
        return this;
    }

    @Step("KEP hizmet sağlayıcısı seç")
    public BirimYonetimiPage popupKepHizmetSaglayicisiSec(String kepHizmetSaglayici) {
        cmbPopupHizmetSaglayicisi.selectOption(kepHizmetSaglayici);
        return this;
    }

    @Step("Popup kep adresi doldur")
    public BirimYonetimiPage popupKepAdresiDoldur(String kepAdresi) {
        txtPopupKepAdresi.setValue(kepAdresi);
        return this;
    }

    @Step("Kep adresi bilgileri ekle")
    public BirimYonetimiPage yeniKepAdresBilgileriEkle() {
        clickJs(btnYeniKepAdresBilgileriEkle);
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

        cmbAntetTipi.selectComboBox(antentTipi);
        /*System.out.println(cmbAntetTipi2.getSelectedOption());
        System.out.println(cmbAntetTipi2.getSelectedValue());

        //1. denemeselect utility ile deneme
        Select mySelect= new Select(cmbAntetTipi2);
//        mySelect.selectByVisibleText(antentTipi);
        mySelect.selectByIndex(1);

        //2. deneme value domda arayarak deneme
        $("select[id='birimYonetimiEditorForm:antetTipiSelect_input'] option[value='Y']").click();

        //3. denemeutility select olmadan deneme
//        cmbAntetTipi2.selectOptionByValue("Y");
//        cmbAntetTipi2.selectOption(antentTipi);*/

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

//    @Step("Düzenle")
//    public BirimYonetimiPage duzenle() {
//        btnDuzenle.click();
//        return this;
//    }

    @Step("Ara")
    public BirimYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Birimin sonuçlarda listelendiği görülür")
    public BirimYonetimiPage biriminListelendigiKontrolu() {

        Assert.assertEquals(chkbirimingeldigi.isDisplayed(), true, "Birimin Geldigi Görülür");
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

    @Step("Birim Yönetimi filtreleme alan kontrolleri")
    public BirimYonetimiPage birimYonetimiFiltrelemeAlanKontrolleri() {

        Assert.assertEquals(txtBirim.isDisplayed(), true, "Birim");
        Allure.addAttachment("Birim alanı kontrolu başarılı", "");

        Assert.assertEquals(cmbBirimTuru.isDisplayed(), true, "Birim Türü");
        Allure.addAttachment("Birim Türü alanı kontrolu başarılı", "");

        Assert.assertEquals(cmbDurum.isDisplayed(), true, "Durum");
        Allure.addAttachment("Durum alanı kontrolu başarılı", "");

        return this;
    }

    @Step("Eklenen birim sonuç tablosunda listelenir")
    public BirimYonetimiPage birimKayitKontrolu(String birimAdi) {

        tblBirimYonetimiListesi
                .filterBy(Condition.text(birimAdi))
                .shouldHaveSize(1);

        return this;
    }

    @Step("Birimin İletişim Bilgisi Kaydet Güncelle butonu tıklanır")
    public BirimYonetimiPage iletisimBilgisi(String birimAdi) {

        tblBirimYonetimiListesi
                .filterBy(Condition.text(birimAdi))
                .first()
                .$("[id$='updateBirimIletisimButton']")
                .click();

        return this;
    }

    @Step("Alt Antet İletişim Bilgileri Satırları ve İletişim Bilgileri alanları görülür")
    public BirimYonetimiPage birimYonetimiİletisimBilgisiAlanKontrolleri() {

        Assert.assertEquals(txtAltAntetİletisim.isDisplayed(), true, "Alt Antet İletişim Bilgileri Satırları");
        Allure.addAttachment("Alt Antet alanı kontrolu başarılı", "");

        Assert.assertEquals(txtİletisimBilgileri1.isDisplayed(), true, "İletişim Bilgileri Satır 1");
        Allure.addAttachment("İletisim bilgileri alanı kontrolu başarılı", "");

        Assert.assertEquals(txtİletisimBilgileri2.isDisplayed(), true, "İletişim Bilgileri Satır 2");
        Allure.addAttachment("İletisim bilgileri alanı kontrolu başarılı", "");

        Assert.assertEquals(txtİletisimBilgileri3.isDisplayed(), true, "İletişim Bilgileri Satır 3");
        Allure.addAttachment("İletisim bilgileri alanı kontrolu başarılı", "");
        return this;
    }

    @Step("Alt Antet İletişim Bilgileri Satırları alanında kalem (güncelle) butonu tıklanır")
    public BirimYonetimiPage guncelle() {
        btnGuncelle.click();
        return this;
    }
    @Step("Yeni İletişim Bilgisi sayfasının pop-up olarak açıldığı görülür")
    public BirimYonetimiPage iletisimPopupGeldigiGorme() {
        boolean durum = $$("[id$='birimKullaniciUpdateDialog']").size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("İletisim Bilgisi Güncelle Satır 1 Doldur ")
    public BirimYonetimiPage iletisimBilgisiSatır1Doldur(String text) {
        txtiletisimBilgisi1.setValue(text);
        return this;
    }
    @Step("İletisim Bilgisi Güncelle Satır 2 Doldur ")
    public BirimYonetimiPage iletisimBilgisiSatır2Doldur(String text) {
        txtiletisimBilgisi2.setValue(text);
        return this;
    }
    @Step("İletisim Bilgisi Güncelle Satır 3 Doldur ")
    public BirimYonetimiPage iletisimBilgisiSatır3Doldur(String text) {
        txtiletisimBilgisi3.setValue(text);
        return this;
    }

    @Step("İletisim Bilgileri Kaydet butonuna basılır.")
    public BirimYonetimiPage iletisimKaydet() {
        btnIletisimKaydet.click();
        return this;
    }

    @Step("İletişim Bilgileri alanından kalem (güncelle) butonu tıklanır")
    public BirimYonetimiPage iletisimGuncelle() {
        btnIletisimGuncelle.click();
        return this;
    }
    @Step("İletişim Bilgisi Güncelleme sayfasının pop-up olarak açıldığı görülür")
    public BirimYonetimiPage yeniİletisimPopupGeldigiGorme() {
        boolean durum = $$("[id$='birimSubEntityUpdateDialog']").size() == 1;
        takeScreenshot();
        return this;
    }
    @Step("Mobil Tel.No Doldur ")
    public BirimYonetimiPage mobilTel(String text) {
        txtMobilTel.setValue(text);
        return this;
    }
    @Step("Telefon No Doldur")
    public BirimYonetimiPage telefonNo(String text) {
        txtTelNo.setValue(text);
        return this;
    }
    @Step("İş Telefon No Doldur")
    public BirimYonetimiPage isTelefonNo(String text) {
        txtİsTelefonNo.setValue(text);
        return this;
    }
    @Step("Faks Numarası 1 Doldur")
    public BirimYonetimiPage faksNo1(String text) {
        txtFaksNo1.setValue(text);
        return this;
    }
    @Step("Faks Numarası 2 Doldur")
    public BirimYonetimiPage faksNo2(String text) {
        txtFaksNo2.setValue(text);
        return this;
    }
    @Step("Adres Doldur")
    public BirimYonetimiPage adres(String text) {
        txtAdres.setValue(text);
        return this;
    }
    @Step("Ülke doldur")
    public BirimYonetimiPage ulkeDoldur(String ulke) {
        txtUlkeSec.selectLov(ulke);
        return this;
    }
    @Step("İl doldur")
    public BirimYonetimiPage ilDoldur(String il) {
        txtİlSec.selectLov(il);
        return this;
    }
    @Step("İlce doldur")
    public BirimYonetimiPage ilceDoldur(String ilce) {
        txtİlceSec.selectLov(ilce);
        return this;
    }

    @Step("Eposta  Doldur")
    public BirimYonetimiPage eposta(String text) {
        txtEposta.setValue(text);
        return this;
    } @Step("Web Adresi  Doldur")
    public BirimYonetimiPage webAdresi(String text) {
        txtWebAdresi.setValue(text);
        return this;
    }
    @Step("İletişim Bilgileri Kaydet")
    public BirimYonetimiPage iletisimBilgileriKaydet() {
        btnIletisimbilgileriKaydet.click();
        return this;
    }
    @Step("Birimin pasif birimler sonuçlarından listelendiği görülür")
    public BirimYonetimiPage pasifBirimKayitKontrolu(String birimAdi) {

        tblPasifBirimListesi
                .filterBy(Condition.text(birimAdi))
                .shouldHaveSize(1);

        return this;
    }

    @Step("Pasif yapılan kaydın gelmediği görülür")
    public BirimYonetimiPage pasifKaydinAktifteGelmedigiKontrolu(String birimAdi) {

        tblAktifBirimListesi
                .filterBy(Condition.text(birimAdi))
                .shouldHaveSize(0);

        return this;
    }

    @Step("Birim pasif yap")
    public BirimYonetimiPage birimPasifYap(String birimAdi) {

        tblBirimYonetimiListesi
                .filterBy(Condition.text(birimAdi))
                .first()
                .$(By.cssSelector("[id$='updateBirimStatusButton'] [class$='to-passive-status-icon']"))
                .click();

        return this;
    }

    @Step("Onay dialogu kontrolu")
    public BirimYonetimiPage biriminOnaydialoguKontrolu() {

        Assert.assertEquals(onayDialogu.isDisplayed(), true, "Birimin Geldigi Görülür");
        return this;
    }

    @Step("Açıklama alanı doldurulur: {aciklama}")
    public BirimYonetimiPage islemOnayiAciklamaDoldur(String aciklama){
        islemOnayiAciklama.setValue(aciklama);
        return this;
    }

    @Step("Birim pasif yap")
    public BirimYonetimiPage birimAktifYap(String birimAdi) {

        tblPasifBirimListesi
                .filterBy(Condition.text(birimAdi))
                .first()
                .$(By.cssSelector("[id$='updateBirimStatusButton'] [class$='to-active-status-icon']"))
                .click();

        return this;
    }

    @Step("Birim güncelle")
    public BirimYonetimiPage birimGüncelle(String birimAdi) {

        tblBirimYonetimiListesi
                .filterBy(Condition.text(birimAdi))
                .first()
                .$("[id$='updateBirimButton']")
                .click();

        return this;
    }


    @Step("Birim adı (Ad) Güncelle ")
    public BirimYonetimiPage birimAdGuncelle(String text) {
        txtAd.setValue(text);
        return this;
    }

    @Step("İdari Birim Kimlik kodu Güncelle  ")
    public BirimYonetimiPage birimIdariKoduGuncelle(String text) {
        txtIdariKimlikKodu.setValue(text);
        return this;
    }

    @Step("Birim Yönetimi ekranına geri dönüldüğü görülür.")
    public BirimYonetimiPage birimYonetimiPageKontrol1() {
        boolean durum = $$(By.id("window1Dialog")).size() == 1;
        Assert.assertEquals(durum, true);
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

        return Arrays.asList(birimAdi, birimKisaAdi, idariBirimKimlikKodu);
    }

    @Step("\"Birimin ve bağlı alt birimlerin durumunu değiştirmek istediğinize emin misiniz? \" sorusunun sorulduğu İşlem Onayı dialog kontrolu")
    public BirimYonetimiPage islemOnayiPopupSorusu(String soru) {
        popupIslemOnaySorusu.shouldBe(visible);
        Assert.assertEquals(popupIslemOnaySorusu.text().contains(soru), true);
        return this;
    }

    @Step("İşlem Onayı - Açıklama doldur")
    public BirimYonetimiPage popupIslemOnayiAciklamaDoldur(String aciklama) {
        txtPopupIslemOnayAciklama.setValue(aciklama);
        return this;
    }

    @Step("İşlem Onayı - Evet")
    public BirimYonetimiPage popupIslemOnayiEvet() {
        txtPopupIslemOnayiEvet.click();
        return this;
    }

    @Step("Birimdeki Kullanıcılar butonuna Tıkla")
    public BirimYonetimiPage birimdekikullanıcılarbutonunatıkla() {
        btnbirimdekikullanicilar.click();
        return this;
    }

    @Step("İşlem Onayı - Hayır")
    public BirimYonetimiPage popupIslemOnayiHayir() {
        txtPopupIslemOnayiHayir.click();
        return this;
    }
    @Step("Birim Amiri ekle")
    public BirimYonetimiPage birimAmiriEkle() {
        clickJs(btnBirimAmiriEkle);
        return this;
    }

    @Step("Birim Amiri Atama - Kullanıcı doldur")
    public BirimYonetimiPage txtBirimAmiriAtamaKullaniciDoldur(String kullanici) {
        txtKullanici.selectLov(kullanici);
        return this;
    }

    @Step("Birim Amiri Atama - Kullanıcı doldur")
    public BirimYonetimiPage txtBirimAmiriAtamaGorevDoldur(String gorev) {
        txtGorev.setValue(gorev);
        Selenide.sleep(3000);
        txtGorev.sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Birim Amiri Atama - Başlangıç Tarihi doldur")
    public BirimYonetimiPage txtBirimAmiriAtamaBaslangicTarihiDoldur(String baslangicTarihi) {
        dteBirimAmiriAtamaBaslangicTarihiInput.setValue(baslangicTarihi);
        return this;
    }

    @Step("Birim Amiri Atama - Bitiş Tarihi doldur")
    public BirimYonetimiPage txtBirimAmiriAtamaBitiscTarihiDoldur(String bitisTarihi) {
        dteBirimAmiriAtamaBitisTarihiInput.setValue(bitisTarihi);
        return this;
    }

    @Step("Birim Amiri Atama - Başlangıç ve Bitiş Tarihi kontrolu")
    public BirimYonetimiPage birimAmiriAtamaBaslangicBitisTarihiKontrol() {

        Assert.assertEquals(dteBirimAmiriAtamaBaslangicTarihi.isDisplayed(), true, "Başlangıç Tarihi kontrolu");
        Allure.addAttachment("Başlangıç Tarihi kontrolu başarılı", "");

        Assert.assertEquals(dteBirimAmiriAtamaBitisTarihi.isDisplayed(), true, "Bitiş Tarihi kontrolu");
        Allure.addAttachment("Bitiş Tarihi kontrolu başarılı", "");

        return this;
    }

    @Step("Birim Amiri Atama - Gizlilik derecesi seç")
    public BirimYonetimiPage cmbBirimAmiriAtamaGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Birim Amiri Atama - Bağ Tipi seç")
    public BirimYonetimiPage cmbBirimAmiriAtamaBagTipiSec(String bagTipi) {
        cmbBirimAmiriAtamaBagTipi.selectOption(bagTipi);
        return this;
    }

    @Step("Birim Amiri Atama - Kaydet")
    public BirimYonetimiPage birimAmiriAtamaKaydet() {
        btnBirimAmiriKaydet.click();
        return this;
    }

    @Step("Yeni alt birim ekle")
    public BirimYonetimiPage yeniAltBirimEkle() {
        btnYeniAltBirimEkle.click();
        return this;
    }

    @Step("Üst Birim alanında \"{ustBirim}\" aranılan birimin set edilmiş olarak geldiği görülür")
    public BirimYonetimiPage birimSeciliGeldigiGorme(String ustBirim) {
        boolean durum = $$("div[id$='ustBirimLov:lovSelectionPanel']").filterBy(Condition.text(ustBirim)).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Alt birimleri açma")
    public BirimYonetimiPage altBirimleriAcma() {
        btnAltBirimAcma.click();
        return this;
    }

    @Step("Kep adresi varsayılan yap")
    public BirimYonetimiPage kepAdresiVarsayilanYap(String kepAdresi) {

        tblKepAdresBilgileriListesi
                .filterBy(Condition.text(kepAdresi))
                .first()
                .$("[id$='changeVarsayilanButton']")
                .scrollTo().pressEnter()
                .click();

        return this;
    }

    @Step("Kep Adres Bilgileri kontrolu")
    public BirimYonetimiPage kepAdresBilgileriKontrolu(String kepAdresi) {

        tblKepAdresBilgileriListesi
                .filterBy(Condition.text(kepAdresi))
                .shouldHaveSize(1);

        return this;
    }

    @Step("Kep Adresi Silme")
    public BirimYonetimiPage kepAdresBilgileriSil(String kepAdresi) {

        if(tblKepAdresBilgileriListesi.filterBy(Condition.text(kepAdresi)).size() == 1)
        {
            tblKepAdresBilgileriListesi
                    .filterBy(Condition.text(kepAdresi))
                    .first()
                    .$("[id$='deleteKepAdresiButton']")
                    .pressEnter();
            islemOnayi("Evet");
        }

        return this;
    }

    @Step("Birim aktif ise pasif yap")
    public BirimYonetimiPage birimAktifIsePasifYap() {

        btnBirimGuncelle.shouldBe(visible);

        if (btnBirimPasifYap.isDisplayed()) {
            btnBirimPasifYap.click();
            popupIslemOnayiAciklamaDoldur("Birim pasif yapılacak.");
            popupIslemOnayiEvet();
            islemMesaji().basariliOlmali("İşlem başarılıdır!");
            Allure.addAttachment("Birim aktif olduğu için pasif yapıldı.", "");
        }
        else
        {
            Allure.addAttachment("Birim pasif olduğu için işlem yapılmadı.", "");
        }

        return this;
    }

    @Step("Birim pasif ise aktif yap")
    public BirimYonetimiPage birimPasifIseAktifYap() {

        btnBirimGuncelle.shouldBe(visible);

        if (btnBirimAktifYap.isDisplayed()) {
            btnBirimAktifYap.click();
            popupIslemOnayiAciklamaDoldur("Birim aktif yapılacak.");
            popupIslemOnayiEvet();
            islemMesaji().basariliOlmali("İşlem başarılıdır!");
            Allure.addAttachment("Birim pasif olduğu için aktif yapıldı.", "");
        }
        else
        {
            Allure.addAttachment("Birim aktif olduğu için işlem yapılmadı.", "");
        }

        return this;
    }

    @Step("Data Resetleme")
    public BirimYonetimiPage kepAdresBilgileriDataResetleme(String kepAdresi1, String kepAdresi2) {

        kepAdresBilgileriSil(kepAdresi1);
        kepAdresBilgileriSil(kepAdresi2);

        return this;
    }

    @Step("Data Resetleme")
    public BirimYonetimiPage dataResetlemeBirimAktifIsePasifYap(String birimAdi) {

        Allure.addAttachment("Birim aktif ise pasif yapılacak", "");
        birimFiltreDoldur(birimAdi);
        durumSec("Tümü");
        ara();
        birimAktifIsePasifYap();
        return this;
    }

    @Step("Data Resetleme")
    public BirimYonetimiPage dataResetlemeBirimPasifIseAktifYap(String birimAdi) {

        Allure.addAttachment("Birim pasif ise aktif yapılacak", "");
        birimFiltreDoldur(birimAdi);
        durumSec("Tümü");
        ara();
        birimPasifIseAktifYap();
        return this;
    }

    @Step("Aktif yapılan kaydın gelmediği kontrolu")
    public BirimYonetimiPage aktifYapilanKaydinGelmedigiKontrolu() {
        Assert.assertEquals(tblPasifKayitlarBulunamadi.getText().contains("Kayıt Bulunamamıştır"), true);

        return this;
    }

    @Step("Birimdeki Kullanıcılar tıkla")
    public BirimYonetimiPage birimdekiKullanicilarTikla() {
        btnBirimdekiKullanicilar.click();
        return this;
    }
}