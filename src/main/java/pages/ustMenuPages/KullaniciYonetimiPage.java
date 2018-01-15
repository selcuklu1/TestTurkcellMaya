package pages.ustMenuPages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.util.Random;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KullaniciYonetimiPage extends MainPage {

    SelenideElement pageTitle = $(By.cssSelector("#window1Dialog .ui-dialog-title"));
    SelenideElement gorevCombobox = $(By.id("kullaniciYonetimiListingForm:filterPanel:filterGorevAutocomplete"));
    BelgenetElement cmlBirim = comboLov(By.id("kullaniciYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement txtTCKimlikNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:tcKimlikNoFilter"));
    SelenideElement txtAd = $(By.id("kullaniciYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement txtSoyad = $(By.id("kullaniciYonetimiListingForm:filterPanel:soyadFilterInput"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciAdiFilterInput"));
    SelenideElement txtEmail = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciEmailFilterInput"));
    SelenideElement txtSicilNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:sicilInput"));
    SelenideElement cmbKullaniciTuru = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciTuruSelectBox"));
    SelenideElement btnAra = $(By.id("kullaniciYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement cmbDurum = $(By.id("kullaniciYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement chkGorevSuresiDolanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:gorevSuresiDolanlarCheckbox"));
    SelenideElement chkBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:birimiOlmayanlarCheckbox"));
    SelenideElement chkAltBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox"));
    SelenideElement tblKolonBirim = $(By.xpath("//*[@id='kullaniciYonetimiListingForm:kullaniciDataTable:j_idt10843']/div"));
    SelenideElement btnKullaniciListesiGuncelle2 = $("[id^='kullaniciYonetimiListingForm:kullaniciDataTable'][id$='updateKullaniciButton']");
    SelenideElement tblGorevliOlduguBirimler = $(By.id("kullaniciYonetimiEditorForm:kullaniciBirimDataTable"));
    SelenideElement btnGorevliOlduguBirimlerGuncelle2 = $("[id^='kullaniciYonetimiEditorForm:kullaniciBirimDataTable][id$='updateKullaniciBirimButton']");
    SelenideElement cmbKullaniciBirimAtamaGizlilikDerecesi = $(By.id("kullaniciBirimEditorForm:kullaniciGuvenlikKoduSelect"));
    SelenideElement btnKullaniciBirimAtamaKaydet = $(By.id("kullaniciBirimEditorForm:saveKullaniciBirimButton"));


    // Kullanıcı Listesi
    SelenideElement btnKullaniciListesiGuncelle = $("[id$='kullaniciYonetimiListingForm:kullaniciDataTable_data'] tr [id$='kullaniciYonetimiListingForm:kullaniciDataTable:0:updateKullaniciButton']");

    // Görevli Olduğu Birimler

//    SelenideElement btnGorevliOlduguBirimlerGuncelle = $("[id$='updateKullaniciBirimButton']");

    SelenideElement btnGorevliOlduguBirimlerGuncelle = $(By.id("kullaniciYonetimiEditorForm:kullaniciBirimDataTable:0:updateKullaniciBirimButton"));


    //Görevli Olduğu Birimler alanı güncelle popup
    SelenideElement cmbPopupKullaniciBirimAtamaBagTipi = $(By.id("kullaniciBirimEditorForm:kullaniciBagTipiSelect"));
    SelenideElement btnPopupBirimAtamaKaydet = $("[id='kullaniciBirimEditorForm:saveKullaniciBirimButton']");
    SelenideElement popUpKullaniciBirimAtama = $(By.id("j_idt5626"));//j_idt4444

    //Kullanıcı Güncelleme
    SelenideElement btnKullaniciGuncelleKaydet = $(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));
    SelenideElement txtUnvan = $(By.id("kullaniciYonetimiEditorForm:unvanAutoComplete_input"));
    SelenideElement txtEkranAdi = $(By.id("kullaniciYonetimiEditorForm:ekranAdiInput"));

    ElementsCollection tblKullaniciBirim = $$("[id='kullaniciYonetimiEditorForm:kullaniciBirimDataTable_data'] tr[role=row][data-ri]");


    //
    SelenideElement kaydet = $(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));


    // Hüseyin TÜMER
    SelenideElement btnKullaniciEkle = $(By.id("kullaniciYonetimiListingForm:kullaniciDataTable:addNewKullaniciButton"));
    SelenideElement txtTcKimlikNo1 = $(By.id("kullaniciYonetimiEditorForm:tcKimlikNoInput"));
    SelenideElement txtSicilNo1 = $(By.id("kullaniciYonetimiEditorForm:sicilNoInput"));
    SelenideElement txtAd1 = $(By.id("kullaniciYonetimiEditorForm:ilkAdInput"));
    SelenideElement txtSoyad1 = $(By.id("kullaniciYonetimiEditorForm:soyadInput"));
    SelenideElement txtKullaniciAdi1 = $(By.id("kullaniciYonetimiEditorForm:kullaniciAdiInput"));
    SelenideElement txtEmail1 = $(By.id("kullaniciYonetimiEditorForm:emailInput"));
    SelenideElement txtSifre = $(By.id("kullaniciYonetimiEditorForm:sifre"));
    SelenideElement txtSifreTekrar = $(By.id("kullaniciYonetimiEditorForm:sifreTekrar"));
    SelenideElement btnGorevliOlduguBirimEkle = $(By.id("kullaniciYonetimiEditorForm:kullaniciBirimDataTable:addNewKullaniciBirimLinkButton"));
    BelgenetElement txtBirim = comboLov(By.id("kullaniciBirimEditorForm:kullaniciBirimIliskiBirimLov:LovText"));
    SelenideElement btnRolEkle = $(By.id("kullaniciYonetimiEditorForm:kullaniciRolBirimDataTable:addNewRolLinkButton"));
    SelenideElement txtGorev = $(By.id("kullaniciBirimEditorForm:unvanAutoComplete_input"));
    BelgenetElement txtRol = comboLov(By.id("kullaniciRolEkleEditorForm:rolEkleLovRolList:LovText"));
    SelenideElement btnRolKaydet = $(By.id("kullaniciRolEkleEditorForm:saveKullaniciRolEkleButton"));

    @Step("Birim alanında \"{0}\" sec")
    public KullaniciYonetimiPage birimSec(String text) {
        cmlBirim.selectLov(text);
        return this;
    }

    public String ekranAdiCek() {
        String ekranAdi = txtEkranAdi.getValue();
        return ekranAdi;
    }

    public KullaniciYonetimiPage kaydet() {
        kaydet.click();
        return this;
    }

    @Step("Kullanıcı birim atama kaydet")
    public KullaniciYonetimiPage popupKullaniciBirimAtamaKaydet() {
        btnPopupBirimAtamaKaydet.click();
        return this;
    }

    @Step("Kullanıcı Yönetim sayfasını aç")
    public KullaniciYonetimiPage openPage() {
        ustMenu(UstMenuData.KullaniciIslemleri.KullaniciYonetimi);
        return this;
    }

    @Step("Kullanıcı Birim atama bağ tipi seçilir {value} | {bag}")
    public KullaniciYonetimiPage popupKullaniciBirimAtamaBagTipiSec(String bagTipi, String bag) {
        cmbPopupKullaniciBirimAtamaBagTipi.selectOption(bagTipi);
        return this;
    }

    @Step("Görevli olduğu birimleri güncelle")
    public KullaniciYonetimiPage gorevliOlduguBirimlerGuncelle() {
        clickJs(btnGorevliOlduguBirimlerGuncelle);
        return this;
    }

    @Step("\"{birim}\" adlı birimi güncelle")
    public KullaniciYonetimiPage gorevliOlduguBirimlerGuncelle(String birim) {
        clickJs(btnGorevliOlduguBirimlerGuncelle);
        return this;
    }

    @Step("\"{kullanici}\" Seçilen kullanıcıyı güncelle tıkla")
    public KullaniciYonetimiPage kullaniciListesiGuncelle() {
        clickJs(btnKullaniciListesiGuncelle);
        return this;
    }

    @Step("\"{kullanici}\" adlı seçilen kullanıcıdaki güncelle tıklanır")
    public KullaniciYonetimiPage kullaniciListesiGuncelle(String kullanici) {
        kullanici = $$("[id='kullaniciYonetimiListingForm:kullaniciDataTable_data'] tr[data-ri='0'] div").get(1).getText();
        clickJs(btnKullaniciListesiGuncelle);
        return this;
    }

    public KullaniciYonetimiPage gorevAlaniDoldur(String value) {
        //TODO
        return this;
    }

    public KullaniciYonetimiPage TCKimlikNoDoldur(String value) {
        txtTCKimlikNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage adDoldur(String value) {
        txtAd.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage soyadDoldur(String value) {
        txtSoyad.sendKeys(value);
        return this;
    }

    @Step("Kullanici alanı \"{kullaniciAdi}\" doldurulur")
    public KullaniciYonetimiPage kullaniciAdiDoldur(String kullaniciAdi) {
        txtKullaniciAdi.sendKeys(kullaniciAdi);
        return this;
    }

    public KullaniciYonetimiPage emailDoldur(String value) {
        txtEmail.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage sicilNoDoldur(String value) {
        txtSicilNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage kullaniciTuruSec(String value) {
        cmbKullaniciTuru.selectOptionByValue(value);
        //selectCombobox(cmbKullaniciTuru,value);
        return this;
    }

    public KullaniciYonetimiPage durumSec(String value) {
        cmbDurum.selectOptionByValue(value);
        return this;
    }

    public KullaniciYonetimiPage gorevSuresiDolanlarSec(boolean value) {
        chkGorevSuresiDolanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage birimiOlmayanlarSec(boolean value) {
        chkBirimiOlmayanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage altBirimlerDahilSec(boolean value) {
        chkAltBirimiOlmayanlar.setSelected(value);
        return this;
    }

    @Step("Ara tıklanır. Kullanıcıların TCKN, ad soyad ve birim bilgileri ile listelendiği görülür.")
    public KullaniciYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    public String adCek(){
       String ad = $$("[id='kullaniciYonetimiListingForm:kullaniciDataTable_data'] tr[data-ri='0'] div").get(1).getText();
        return ad;
    }

    public String birimAdCek(){
       String ad = $$("[id='kullaniciYonetimiEditorForm:kullaniciBirimDataTable_data'] span").get(0).getText();
        return ad;
    }

    @Step("Birim Kontrolü")
    public KullaniciYonetimiPage tabloBirimKontrol() {
        tblKolonBirim.exists();
        return this;
    }

    @Step("Güncelle butonu tıklanır")
    public KullaniciYonetimiPage kullaniciListesiGuncelleButonuTikla() {
//        clickJs(btnKullaniciListesiGuncelle);
//        btnKullaniciListesiGuncelle.exists();
        btnKullaniciListesiGuncelle2.click();
        return this;
    }

    @Step("Gorevli oldugu birimler kontrolü")
    public KullaniciYonetimiPage gorevliOlduguBirimlerKontol() {
        tblGorevliOlduguBirimler.shouldBe(visible);
        tblGorevliOlduguBirimler.$("label").shouldHave(text("Görevli Olduğu Birimler"));
        tblKullaniciBirim.shouldHave(sizeGreaterThan(0));
        tblGorevliOlduguBirimler.exists();
        return this;
    }

    //    @Step("Görevli olduğu birim guncelle")
//    public KullaniciYonetimiPage gorevliOlduguBirimGuncelle2 () {
//        clickJs(btnGorevliOlduguBirimlerGuncelle);
//        btnGorevliOlduguBirimlerGuncelle2.click();
//        return this;
//    }
    @Step("Gizlilik derecesi \"{gizlilikDerecesi}\" seçilir")
    public KullaniciYonetimiPage kullaniciBirimAtamaGizlilikDerecesiSec(String gizlilikDerecesi) {
//        if (cmbKullaniciBirimAtamaGizlilikDerecesi.isDisplayed())
        cmbKullaniciBirimAtamaGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Kullanıcı Birim Atama Kaydet")
    public KullaniciYonetimiPage kullaniciBirimAtamaKaydetTikla() {
        btnKullaniciBirimAtamaKaydet.click();
        return this;
    }

    @Step("Görevli olduğu birim guncelleme")
    public KullaniciYonetimiPage gorevliOlduguBirimGuncelle() {
        String title = cmlBirim.getSelectedTitles().last().text();

        tblKullaniciBirim.filterBy(text(title)).shouldHaveSize(1)
                .first().$("[id$='updateKullaniciBirimButton']").click();
        return this;
    }

    @Step("Kullanıcı güncelleme alanında kaydet")
    public KullaniciYonetimiPage kullaniciGuncellemeKaydet() {
        btnKullaniciGuncelleKaydet.click();
        return this;
    }

    @Step("Kullanici Birim Atama Gizlilik Derecesi seçilen değer kontrolu")
    public KullaniciYonetimiPage kullaniciBirimAtamaGizlilikDerecesiDeğerKontrolu(String value) {
        cmbKullaniciBirimAtamaGizlilikDerecesi.shouldHave(text(value));
        return this;
    }

    @Step("")
    public KullaniciYonetimiPage kullaniciGuncellemeUnvanGuncelle(String unvan) {
        if (txtUnvan.is(Condition.empty)) {
            txtUnvan.sendKeys(unvan);
            txtUnvan.pressEnter();
        }
        return this;
    }

    @Step("Kullanıcı Oluştur")
    public String kullaniciOlustur(String gorevliOlduguBirim, String gorev) {

        String kullaniciAdi = "Kullanici" + (new Random().nextInt((900000 - 100000) + 1) + 100000);
        String sicilNo = "" + (new Random().nextInt((900000 - 100000) + 1) + 100000);
        btnKullaniciEkle.click();
        txtTcKimlikNo1.setValue(createMernisTCKN());
        txtSicilNo1.setValue(sicilNo);
        txtAd1.setValue(kullaniciAdi);
        txtSoyad1.setValue(kullaniciAdi);
        txtKullaniciAdi1.setValue(kullaniciAdi);
        txtEmail1.setValue(kullaniciAdi + "@turksat.com.tr");
        txtSifre.setValue("123");
        txtSifreTekrar.setValue("123");
        txtUnvan.setValue(gorev);
        Selenide.sleep(2000);
        txtUnvan.pressEnter();
        btnGorevliOlduguBirimEkle.click();
        txtBirim.selectLov(gorevliOlduguBirim);
        txtGorev.setValue(gorev);
        Selenide.sleep(2000);
        txtGorev.pressEnter();
        btnKullaniciBirimAtamaKaydet.click();
        btnRolEkle.click();
        txtRol.selectLov("ENTERPRİSE");
        txtRol.selectLov("STANDART KULLANICI (EVRAK KAPATMA HARİÇ)");
        btnRolKaydet.click();
        String ekranAdi = txtEkranAdi.getValue();
        btnKullaniciGuncelleKaydet.click();
        return ekranAdi;
    }

    @Step("Kullanıcı Oluştur")
    public String kullaniciOlustur(String kullaniciAdi, String sifre, String gorevliOlduguBirim, String gorev) {

        String sicilNo = "" + (new Random().nextInt((900000 - 100000) + 1) + 100000);
        btnKullaniciEkle.click();
        txtTcKimlikNo1.setValue(createMernisTCKN());
        txtSicilNo1.setValue(sicilNo);
        txtAd1.setValue(kullaniciAdi);
        txtSoyad1.setValue(kullaniciAdi);
        txtKullaniciAdi1.setValue(kullaniciAdi);
        txtEmail1.setValue(kullaniciAdi + "@turksat.com.tr");
        txtSifre.setValue(sifre);
        txtSifreTekrar.setValue(sifre);
        txtUnvan.setValue(gorev);
        Selenide.sleep(2000);
        txtUnvan.pressEnter();
        btnGorevliOlduguBirimEkle.click();
        txtBirim.selectLov(gorevliOlduguBirim);
        txtGorev.setValue(gorev);
        Selenide.sleep(2000);
        txtGorev.pressEnter();
        btnKullaniciBirimAtamaKaydet.click();
        btnRolEkle.click();
        txtRol.selectLov("ENTERPRİSE");
        txtRol.selectLov("STANDART KULLANICI (EVRAK KAPATMA HARİÇ)");
        btnRolKaydet.click();
        String ekranAdi = txtEkranAdi.getValue();
        btnKullaniciGuncelleKaydet.click();
        return ekranAdi;
    }
}
