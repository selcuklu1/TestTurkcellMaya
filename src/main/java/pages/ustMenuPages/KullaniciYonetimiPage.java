package pages.ustMenuPages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.util.Random;
import java.util.Vector;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

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

    ElementsCollection tblKullaniciBirim = $$("[id='kullaniciYonetimiEditorForm:kullaniciBirimDataTable_data'] tr[role=row]");


    //
    SelenideElement kaydet = $(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));


    // Hüseyin TÜMER
    SelenideElement btnKullaniciEkle = $(By.id("kullaniciYonetimiListingForm:kullaniciDataTable:addNewKullaniciButton"));


    @Step("Birim alanında \"{0}\" sec")
    public KullaniciYonetimiPage birimSec(String text) {
        cmlBirim.selectLov(text);
        return this;
    }

    @Step("Ekran adı çekilir")
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

    @Step("Kullanıcı Yönetim sayfası aç")
    public KullaniciYonetimiPage openPage() {
        ustMenu("Kullanıcı Yönetim");
        return this;
    }

    @Step("Kullanıcı Birim atama bağ tipi seç")
    public KullaniciYonetimiPage popupKullaniciBirimAtamaBagTipiSec(String value) {
        cmbPopupKullaniciBirimAtamaBagTipi.selectOption(value);
        return this;
    }

    @Step("Görevli olduğu birimler güncelle")
    public KullaniciYonetimiPage gorevliOlduguBirimlerGuncelle(){
        clickJs(btnGorevliOlduguBirimlerGuncelle);
        return this;
    }

    @Step("Seçilen kullanıcı güncelle tıkla")
    public KullaniciYonetimiPage kullaniciListesiGuncelle(){
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

    public KullaniciYonetimiPage kullaniciAdiDoldur(String value) {
        txtKullaniciAdi.sendKeys(value);
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
    @Step("Ara")
    public KullaniciYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Birim Kontrol")
    public KullaniciYonetimiPage tabloBirimKontrol() {
        tblKolonBirim.exists();
        return this;
    }

    @Step("Güncelle butonu")
    public KullaniciYonetimiPage kullaniciListesiGuncelleButonuTikla() {
//        clickJs(btnKullaniciListesiGuncelle);
//        btnKullaniciListesiGuncelle.exists();
        btnKullaniciListesiGuncelle2.click();
        return this;
    }

    @Step("Gorevli oldugu birimler")
    public KullaniciYonetimiPage gorevliOlduguBirimlerKontol() {
        tblGorevliOlduguBirimler.exists();
        return this;
    }

    //    @Step("Görevli olduğu birim guncelle")
//    public KullaniciYonetimiPage gorevliOlduguBirimGuncelle2 () {
//        clickJs(btnGorevliOlduguBirimlerGuncelle);
//        btnGorevliOlduguBirimlerGuncelle2.click();
//        return this;
//    }
    @Step("Gizlilik derecesi seç")
    public KullaniciYonetimiPage kullaniciBirimAtamaGizlilikDerecesiSec(String gizlilikDerecesi) {
        if (cmbKullaniciBirimAtamaGizlilikDerecesi.isDisplayed())
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

        String title = cmlBirim.lastSelectedLovTitleText();
        tblKullaniciBirim
                .filterBy(Condition.text(title)).shouldHaveSize(1)
                .first()
                .$("[id$='updateKullaniciBirimButton']").click();
        return this;
    }

    @Step("Kullanıcı guncelleme kaydet")
    public KullaniciYonetimiPage kullaniciGuncellemeKaydet() {
        btnKullaniciGuncelleKaydet.click();
        return this;
    }

    @Step("Kullanici Birim Atama Gizlilik Derecesi Kontrolu")
    public KullaniciYonetimiPage kullaniciBirimAtamaGizlilikDerecesiKontrolu() {
        cmbKullaniciBirimAtamaGizlilikDerecesi.shouldBe(Condition.text("Tasnif Dışı"));
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

    @Step("Kullanıcı Oluştur")
    public String kullaniciOlustur(String gorevliOlduguBirim, String gorev){

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
    public String kullaniciOlustur(String kullaniciAdi, String sifre, String gorevliOlduguBirim, String gorev){

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
