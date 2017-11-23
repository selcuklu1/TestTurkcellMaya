package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KurumYonetimiPage extends MainPage {

    SelenideElement txtKurum = $(By.id("kurumYonetimiListingForm:filterPanel:kurumFilterLov:LovText"));
    SelenideElement btnAra = $(By.id("kurumYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement cmbDurum = $(By.id("kurumYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement txtIdariBirimKimlikKodu = $(By.id("kurumYonetimiEditorForm:kurumEKodInput"));
    SelenideElement chkKaysisteYerAlmiyor = $(By.id("kurumYonetimiEditorForm:kaysisteVarMiCheckbox_input"));
    SelenideElement txtKurumAdi = $(By.id("kurumYonetimiEditorForm:kurumAdiInput"));
    SelenideElement chkPaketKullanim = $(By.id("kurumYonetimiEditorForm:paketKullanimCheckbox_input"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("kurumYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement chkOzelHitap = $(By.id("kurumYonetimiEditorForm:ozelHitapExistSelBoolean_input"));
    SelenideElement btnKaydet = $(By.id("kurumYonetimiEditorForm:saveKurumButton"));
    SelenideElement btnKepAdresBilgileriArti = $(By.id("kurumYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement btnGuncelle = $(By.id("kurumYonetimiListingForm:kurumTreeTable:1_0:updateKurumButton"));
    SelenideElement txtPopupKepAdresi = $(By.id("kurumKepAdresBilgiEditorForm:kurumKepAdresBilgiInputTextId"));
    SelenideElement cmbPopupKepHizmetSaglayicisi = $(By.id("kurumKepAdresBilgiEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("kurumKepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnAltMenuAc = $("[id$='kurumYonetimiListingForm:kurumTreeTable_node_1'] span");
    BelgenetElement txtKurumCombolov = comboLov(By.id("kurumYonetimiListingForm:filterPanel:kurumFilterLov:LovText"));



    // Hüseyin

    ElementsCollection tableKurumListesi = $$("div[id='kurumYonetimiListingForm:kurumTreeTable'] tbody > tr[role='row']");
    SelenideElement btnKurumGuncelle = $("button[id^='kurumYonetimiListingForm:kurumTreeTable:'][id$=':updateKurumButton']");
    By btnGuncelleSelector = By.cssSelector("button[id^='kurumYonetimiListingForm:kurumTreeTable:'][id$=':updateKurumButton']");
    SelenideElement divSecilenUstKurum = $(By.id("kurumYonetimiEditorForm:ustKurumLov:LovSecilen"));
    SelenideElement btnSecilenKurumListedenCikar = $("div[id='kurumYonetimiEditorForm:ustKurumLov:LovSecilen'] button[id*='kurumYonetimiEditorForm:ustKurumLov']");
    BelgenetElement txtUstKurum = comboLov(By.id("kurumYonetimiEditorForm:ustKurumLov:LovText"));
    SelenideElement btnIletisimGuncelle = $("button[id^='kurumYonetimiEditorForm:iletisimBilgileriDataTable:'][id$=':updateIletisimBilgisiButton']");

    // İletişim bilgilerii elementleri

    SelenideElement txtMobilTelNo = $(By.id("kurumBilgileriEditorForm:mobilInput"));
    SelenideElement txtTelefonNo = $(By.id("kurumBilgileriEditorForm:telefonInput"));
    SelenideElement txtIsTelefonNo = $(By.id("kurumBilgileriEditorForm:telefonIsInput"));
    SelenideElement txtFaxNumarasi1 = $(By.id("kurumBilgileriEditorForm:fax1Input"));
    SelenideElement txtFaxNumarasi2 = $(By.id("kurumBilgileriEditorForm:fax2Input"));
    SelenideElement txtAdres = $(By.id("kurumBilgileriEditorForm:adresInput"));
    BelgenetElement txtUlke = comboLov(By.id("kurumBilgileriEditorForm:lovUlke:LovText"));
    BelgenetElement txtIl = comboLov(By.id("kurumBilgileriEditorForm:lovIl:LovText"));
    SelenideElement txtIlce = $(By.id("kurumBilgileriEditorForm:lovIlce:LovText"));
    SelenideElement txtEPosta = $(By.id("kurumBilgileriEditorForm:ePostaInput"));
    SelenideElement txtWebAdresi = $(By.id("kurumBilgileriEditorForm:webAdresiInput"));





    public KurumYonetimiPage openPage() {
        ustMenu("Kurum Yönetimi");
        return this;
    }
    @Step("TC kimlik no alma")
    public String idariBirimKimlikKoduCek() {
        String getIdariBirimKodu = txtIdariBirimKimlikKodu.getValue();
        return getIdariBirimKodu;
    }

    public  KurumYonetimiPage popupKaydet() throws InterruptedException{
        btnPopupKaydet.click();
        return this;
    }
    public KurumYonetimiPage popupKepHizmetSaglayicisiSec(String value) throws InterruptedException{
        cmbPopupKepHizmetSaglayicisi.selectOption(value);
        return this;
    }

    public KurumYonetimiPage popupKepAdresiDoldur(String text) throws InterruptedException{
        txtPopupKepAdresi.setValue(text);
        return this;
    }

    public KurumYonetimiPage guncelle() throws InterruptedException{
        btnAltMenuAc.click();
        btnGuncelle.click();
        return this;
    }
    public KurumYonetimiPage kepAdresBilgileriArti() {
        btnKepAdresBilgileriArti.click();
        return this;
    }

    public KurumYonetimiPage kaydetGonder() {
        btnKaydet.click();
        return this;
    }

    public KurumYonetimiPage ozelHitapSec(boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage paketKullanimSec(boolean secim) {
        chkPaketKullanim.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage kurumAdiDoldur(String text) {
        txtKurumAdi.setValue(text);
        return this;
    }

    public KurumYonetimiPage kaysisteYerAlmiyorSec(boolean secim) {
        chkKaysisteYerAlmiyor.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage idariBirimKimlikKoduDoldur(String text) {
        txtIdariBirimKimlikKodu.setValue(text);
        return this;
    }

    public KurumYonetimiPage durumSec(String value) {
        cmbDurum.selectOption(value);
        return this;
    }

    public KurumYonetimiPage ara(){
        btnAra.click();
        return this;
    }

    public KurumYonetimiPage kurumDoldur(String text) {
        txtKurumCombolov.selectComboLov(text);
        return this;
    }



    // Hüseyin Methods

    public KurumYonetimiPage kurumGuncelle(String kurumAdi){
        tableKurumListesi
                .filterBy(Condition.text(kurumAdi))
                .get(0)
                .$(btnGuncelleSelector)
                .click();
        return this;
    }

    public KurumYonetimiPage ustKurumSec(String ustKurum){
        if(divSecilenUstKurum.exists())
            btnSecilenKurumListedenCikar.exists();
        txtUstKurum.selectComboLov(ustKurum);
        return this;
    }

    public KurumYonetimiPage iletisimGuncelle(){
        btnIletisimGuncelle.click();
        return this;
    }

    public KurumYonetimiPage mobilTelNoDoldur(String mobilTelNo){
        txtMobilTelNo.setValue(mobilTelNo);
        return this;
    }

    public KurumYonetimiPage telefonNoDoldur(String telefonNo){
        txtMobilTelNo.setValue(telefonNo);
        return this;
    }

    public KurumYonetimiPage isTelefonNoDoldur(String isTelefonNo){
        txtMobilTelNo.setValue(isTelefonNo);
        return this;
    }

    public KurumYonetimiPage faxNumarasi1Doldur(String faxNumarasi1){
        txtMobilTelNo.setValue(faxNumarasi1);
        return this;
    }

    public KurumYonetimiPage faxNumarasi2Doldur(String faxNumarasi2){
        txtMobilTelNo.setValue(faxNumarasi2);
        return this;
    }


    /*


    SelenideElement txtMobilTelNo = $(By.id("kurumBilgileriEditorForm:mobilInput"));
    SelenideElement txtTelefonNo = $(By.id("kurumBilgileriEditorForm:telefonInput"));
    SelenideElement txtIsTelefonNo = $(By.id("kurumBilgileriEditorForm:telefonIsInput"));
    SelenideElement txtFaxNumarasi1 = $(By.id("kurumBilgileriEditorForm:fax1Input"));
    SelenideElement txtFaxNumarasi2 = $(By.id("kurumBilgileriEditorForm:fax2Input"));
    SelenideElement txtAdres = $(By.id("kurumBilgileriEditorForm:adresInput"));
    BelgenetElement txtUlke = comboLov(By.id("kurumBilgileriEditorForm:lovUlke:LovText"));
    BelgenetElement txtIl = comboLov(By.id("kurumBilgileriEditorForm:lovIl:LovText"));
    SelenideElement txtIlce = $(By.id("kurumBilgileriEditorForm:lovIlce:LovText"));
    SelenideElement txtEPosta = $(By.id("kurumBilgileriEditorForm:ePostaInput"));
    SelenideElement txtWebAdresi = $(By.id("kurumBilgileriEditorForm:webAdresiInput"));



     */








}

