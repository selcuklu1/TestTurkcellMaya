package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;


public class RolYonetimiPage extends MainPage {


    // Sorgulama ve Filtreleme
    SelenideElement txtAdfilterinput = $x("//*[@id='rolYonetimiListingForm:filterPanel:adFilterInput']");
    BelgenetElement cmbDurumSecimi = comboBox("select[id$='rolYonetimiListingForm:filterPanel:durumSelectBox']");
    SelenideElement btnSorgulamaFiltrelemeArama = $x("//*[@id='rolYonetimiListingForm:filterPanel:searchEntitiesButton']");

    //Rol Listesi
    SelenideElement btnYeniRolEkleme = $x("//*[@id='rolYonetimiListingForm:rolDataTable:addNewRolButton']");
    ElementsCollection tblRolListesi = $$("[@id='rolYonetimiListingForm:rolDataTable_data'] tr[data-ri]");



    //Rol ekleme sayfası
    SelenideElement txtRolAd = $x("//*[@id='rolYonetimiEditorForm:rolAdiInput']");
    SelenideElement txtRolKısaAd = $x("//*[@id='rolYonetimiEditorForm:rolKisaAdiInput']");
    SelenideElement txtRolEtiket = $x("//*[@id='rolYonetimiEditorForm:etiketInput']");
    SelenideElement txtRolDegerKod = $x("//*[@id='rolYonetimiEditorForm:degerKodInput']");
    SelenideElement txtRolAciklama = $x("//*[@id='rolYonetimiEditorForm:aciklamaInput']");
    SelenideElement txtRolYetkiOnceligi = $x("//*[@id='rolYonetimiEditorForm:siraInput']");
    SelenideElement btnRolKaydetme = $x("//*[@id='rolYonetimiEditorForm:saveRolButton']");
    SelenideElement btnRolIptal = $x("//*[@id='rolYonetimiEditorForm:cancelSaveRolButton']");

    //Listedeki butonlar
    String DataRow;
    String strGuncelle = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":updateRolButton']";
    String strRolKopyala = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":duplicateRolButton']";
    String strRolAksiyon = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":rolAksiyonlariButton']";
    String strRolPasifYap = "//*[@id='rolYonetimiListingForm:rolDataTable:" + DataRow + ":changeRolStatusButton']";

    //Rol Aksiyon sayfası
    SelenideElement btnYeniAksiyonEkle = $x("//*[@id='rolYonetimiEditorForm:rolAksiyonDataTable:newRolAksiyonButton']");
    SelenideElement dlgRolAksiyonUpdate = $x("//*[@id='rolAksiyonUpdateDialog']");
    SelenideElement txtAdSorgulama = $x("//*[@id='rolYonetimiAksiyonFiltrelemeForm:filterPanel:adFilterInput']");
    SelenideElement btnDialogAksiyonArama = $x("//*[@id='rolYonetimiAksiyonFiltrelemeForm:filterPanel:searchEntitiesButton']");
    ElementsCollection tblEklenecekAksiyonList = $$("[@id='rolAksiyonEditorForm:eklenecekAksiyonList_data'] tr[data-ri]");
    SelenideElement btnDialogAksiyonEkle = $x("//*[@id='rolAksiyonEditorForm:addActionButton']");



    @Step("Rol Yönetimi Sayfasını aç")
    public RolYonetimiPage openPage() {

        ustMenu(UstMenuData.KullaniciIslemleri.RolYonetimi);
        return this;
    }

    @Step("Aranacak Rol adı doldurma : \"{RolAd}\" ")
    public RolYonetimiPage txtRolAdArama(String RolAd) {
        txtAdfilterinput.setValue(RolAd);
        return this;
    }

    @Step("Aranacak durum seçimi : \"{durum}\"")
    public RolYonetimiPage cmbDurumSecim(String durum) {
        cmbDurumSecimi.selectComboBox(durum);
        return this;
    }

    @Step("Arama butonuna tıkla")
    public RolYonetimiPage btnRolArama () {
        btnSorgulamaFiltrelemeArama.click();
        return this;
    }

    @Step("Yeni Rol Ekleme butonu")
    public RolYonetimiPage btnYeniRolekle () {
        btnYeniRolEkleme.click();
        return this;
    }
    @Step("Yeni Rol ad doldurma : \"{Rolad}\"")
    public RolYonetimiPage txtYeniRolAd (String Rolad) {
        txtRolAd.setValue(Rolad);
        return this;
    }

    @Step("Yeni Rol Kısa Ad doldurma : \"{RolKısaad}\"")
    public RolYonetimiPage txtYeniRolKısaAd (String RolKısaad) {
        txtRolKısaAd.setValue(RolKısaad);
        return this;
    }

    @Step("Yeni Rol Etiket doldurma : \"{etiket}\"")
    public RolYonetimiPage txtYeniRolEtiket (String etiket) {
        txtRolEtiket.setValue(etiket);
        return this;
    }

    @Step("Yeni Rol Deger kod doldurma :\"{degerkod}\"")
    public RolYonetimiPage txtRolDegerKod (String degerkod) {
        txtRolDegerKod.setValue(degerkod);
        return this;
    }

    @Step("Yeni Rol Açıklama doldurma : \"{aciklama}\"")
    public RolYonetimiPage txtRolAciklama (String aciklama) {
        txtRolAciklama.setValue(aciklama);
        return this;
    }

    @Step("Yeni Rol Yetki Onceligi doldurma : \"{oncelik}\"")
    public RolYonetimiPage txtRolYetkiOnceligi (int oncelik) {
        txtRolYetkiOnceligi.setValue(String.valueOf(oncelik));
        return this;
    }

    @Step("Yeni Rol Kaydetme ")
    public RolYonetimiPage btnYeniRolKaydetme () {
        btnRolKaydetme.click();
        return this;
    }

    @Step("Yeni Rol Kaydetme iptal")
    public RolYonetimiPage btnYeniRolIptal() {
        btnRolIptal.click();
        return this;
    }

    @Step("Arama sonuç tablosundan seçim {secrolad}")
    public RolYonetimiPage tblRolListeSecim (String secrolad) {
    tblRolListesi.filter(Condition.text("secrolad")).first().click();
    return this;

    }

    @Step("Arama sonuç tablosunda seçilen sonuç aksiyon butonu tıklama")
    public RolYonetimiPage tblRolListeSecimAksiyonButonu () {
        String datari = tblRolListesi.filter(Condition.text("secrolad")).first().getAttribute("data-ri");
        DataRow = datari;
        SelenideElement TablodanRolAksiyon = $x(strRolAksiyon);
        TablodanRolAksiyon.click();
        return this;
    }

    @Step("Yeni Aksiyon Ekle butonu")
    public RolYonetimiPage btnYeniAksiyonEkle() {
        btnYeniAksiyonEkle.click();
        return this;
    }

    @Step("Dialog içi arama Aksiyon ad : \"{aksiyonAd}\" doldur")
    public RolYonetimiPage txtDialogAksiyonad (String aksiyonAd) {
        txtAdSorgulama.setValue(aksiyonAd);
        return this;
    }
    @Step("Dialog içi arama butonu tıklama")
    public RolYonetimiPage btnDialogAksiyonAra () {
        btnDialogAksiyonArama.click();
        return this;
    }
    @Step("Dialog Aksiyon sonuç listesinden seçme")
    public RolYonetimiPage btnDialogselectAction () {
        tblEklenecekAksiyonList.first().click();
        return this;
    }
    @Step("Dialog Aksiyon Ekle butonu")
    public RolYonetimiPage btnDialogAksiyonEkle () {
        btnDialogAksiyonEkle.click();
        return this;
    }







}
