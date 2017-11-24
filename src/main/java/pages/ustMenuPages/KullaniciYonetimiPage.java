package pages.ustMenuPages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

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
    SelenideElement btnGorevliOlduguBirimlerGuncelle =$(By.id("kullaniciYonetimiEditorForm:kullaniciBirimDataTable:0:updateKullaniciBirimButton"));

    //Görevli Olduğu Birimler alanı güncelle popup
    SelenideElement cmbPopupKullaniciBirimAtamaBagTipi = $(By.id("kullaniciBirimEditorForm:kullaniciBagTipiSelect"));
    SelenideElement btnPopupBirimAtamaKaydet = $("[id='kullaniciBirimEditorForm:saveKullaniciBirimButton']");
    SelenideElement popUpKullaniciBirimAtama = $(By.id("j_idt4444"));

    //Kullanıcı Güncelleme
    SelenideElement btnKullaniciGuncelleKaydet = $(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));
    SelenideElement txtUnvan = $(By.id("kullaniciYonetimiEditorForm:unvanAutoComplete_input"));
    SelenideElement txtEkranAdi = $(By.id("kullaniciYonetimiEditorForm:ekranAdiInput"));


    //
    SelenideElement kaydet =$(By.id("kullaniciYonetimiEditorForm:saveKullaniciButton"));

    @Step("Birim alanında \"{0}\" sec")
    public KullaniciYonetimiPage birimSec(String text) {
        cmlBirim.selectComboLov(text);
        return this;
    }

    public String ekranAdiCek(){
        String ekranAdi = txtEkranAdi.getValue();
        return ekranAdi;
    }

    public KullaniciYonetimiPage kaydet(){
        kaydet.click();
        return this;
    }

    public  KullaniciYonetimiPage popupKullaniciBirimAtamaKaydet() {
        btnPopupBirimAtamaKaydet.click();
        return this;
    }


    public KullaniciYonetimiPage openPage() {
        new UstMenu().ustMenu("Kullanıcı Yönetim");
        return this;
    }

    public KullaniciYonetimiPage popupKullaniciBirimAtamaBagTipiSec(String value) {
        cmbPopupKullaniciBirimAtamaBagTipi.selectOption(value);
        return this;
    }

    public KullaniciYonetimiPage gorevliOlduguBirimlerGuncelle() {
        btnGorevliOlduguBirimlerGuncelle.click();
        return this;
    }


    public KullaniciYonetimiPage kullaniciListesiGuncelle() {
        btnKullaniciListesiGuncelle.click();
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
        cmbKullaniciBirimAtamaGizlilikDerecesi.selectOptionByValue(gizlilikDerecesi);
        return this;
    }

    @Step("Kullanıcı Birim Atama Kaydet")
    public KullaniciYonetimiPage kullaniciBirimAtamaKaydetTikla() {
        if(popUpKullaniciBirimAtama.isDisplayed()) {
            btnKullaniciBirimAtamaKaydet.click();
        }
        return this;
    }

    @Step("Görevli olduğu birim guncelleme")
    public KullaniciYonetimiPage gorevliOlduguBirimGuncelle() {

        String title = cmlBirim.lastSelectedLovTitleText();
        $$("[id='kullaniciYonetimiEditorForm:kullaniciBirimDataTable_data'] tr[role=row]")
                .filterBy(Condition.text(title)).shouldHaveSize(1)
                .first()
                .$("[id$='updateKullaniciBirimButton']").click();
        return this;
    }

    @Step("Kullanıcı guncelleme kaydet")
    public KullaniciYonetimiPage kullaniciGuncellemeKaydet() {
        if(txtUnvan.is(Condition.empty)) {
            txtUnvan.sendKeys("BT İş Analist / Yazılımcı");
            txtUnvan.pressEnter();
        }

        btnKullaniciGuncelleKaydet.click();
        return this;
    }
    @Step("Kullanici Birim Atama Gizlilik Derecesi Kontrolu")
    public KullaniciYonetimiPage kullaniciBirimAtamaGizlilikDerecesiKontrolu(){
        cmbKullaniciBirimAtamaGizlilikDerecesi.shouldBe(Condition.text("Tasnif Dışı"));
        return this;
    }

}
