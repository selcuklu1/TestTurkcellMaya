package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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
    SelenideElement chkKepAdresiKullaniyor = $(By.id("kurumYonetimiEditorForm:kepAdresiKullanimCheckbox"));
    SelenideElement chkOzelHitap = $(By.id("kurumYonetimiEditorForm:ozelHitapExistSelBoolean_input"));
    //SelenideElement btnKaydet = $(By.id("kurumYonetimiEditorForm:saveKurumButton"));
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
    SelenideElement btnKurumKaydet = $(By.id("kurumYonetimiEditorForm:saveKurumButton"));

    // İletişim bilgileri elementleri
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
    SelenideElement btnIletisimBilgisiKaydet = $(By.id("kurumBilgileriEditorForm:saveIletisimBilgisiButton"));

    // Kep Adresi elementleri
    ElementsCollection tableKepAdresleri = $$("tbody[id='kurumYonetimiEditorForm:kepBilgileriDataTable_data'] tr[role='row']");
    By btnKepAdresiGuncelleSelector = By.cssSelector("button[id^='kurumYonetimiEditorForm:kepBilgileriDataTable:'][id$=':updateKepAdresiButton']");
    SelenideElement txtKepAdresi = $(By.id("kurumKepAdresBilgiEditorForm:kurumKepAdresBilgiInputTextId"));
    SelenideElement cmbKepHizmetSaglayici = $(By.id("kurumKepAdresBilgiEditorForm:kephs"));
    SelenideElement btnKepAdresiBilgileriKaydet = $(By.id("kurumKepAdresBilgiEditorForm:saveKepAdresiButton"));
    SelenideElement btnKurumHiyerarşisiniGuncelle = $("button[id^='kurumYonetimiListingForm:kurumTreeTable:'][id$=':applyChangesButton']");

    SelenideElement filtrePanel = $(By.id("kurumYonetimiListingForm:filterPanel"));

    @Step("Kurum Yönetimi sayfası aç")
    public KurumYonetimiPage openPage() {
        ustMenu("Kurum Yönetimi");
        return this;
    }
    @Step("TC kimlik no alma")
    public String idariBirimKimlikKoduCek() {
        String getIdariBirimKodu = txtIdariBirimKimlikKodu.getValue();
        return getIdariBirimKodu;
    }
    @Step("Kaydet")
    public  KurumYonetimiPage popupKaydet() throws InterruptedException{
        btnPopupKaydet.click();
        return this;
    }
    @Step("Kep hizmet sağlayıcısı seç")
    public KurumYonetimiPage popupKepHizmetSaglayicisiSec(String value) throws InterruptedException{
        cmbPopupKepHizmetSaglayicisi.selectOption(value);
        return this;
    }
    @Step("Kep adresi doldur")
    public KurumYonetimiPage popupKepAdresiDoldur(String text) throws InterruptedException{
        txtPopupKepAdresi.setValue(text);
        return this;
    }
    @Step("Güncelle ")
    public KurumYonetimiPage guncelle() throws InterruptedException{
        btnAltMenuAc.click();
        btnGuncelle.click();
        return this;
    }
    @Step("Kep Adres Bilgileri ekle")
    public KurumYonetimiPage kepAdresBilgileriArti() {
        btnKepAdresBilgileriArti.click();
        return this;
    }


    @Step("Kaydet")
    public KurumYonetimiPage kaydet() {
        btnKurumKaydet.click();
        return this;
    }

    @Step("Özel hitap seç")
    public KurumYonetimiPage ozelHitapSec(boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }
    @Step("Kep adresi kullanıyor seç")
    public KurumYonetimiPage kepAdresiKullaniyorSec(boolean secim) {

        Boolean isSelected = false;
        if (chkKepAdresiKullaniyor.$(By.xpath("./div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if(secim == true){
            if(isSelected == false)
                chkKepAdresiKullaniyor.click();
        } else {
            if(isSelected == true)
                chkKepAdresiKullaniyor.click();
        }


        return this;
    }

    @Step("Paket kullanım seç")
    public KurumYonetimiPage paketKullanimSec(boolean secim) {
        chkPaketKullanim.setSelected(secim);
        return this;
    }

    @Step("Kurum adı doldur")
    public KurumYonetimiPage kurumAdiDoldur(String text) {
        txtKurumAdi.setValue(text);
        return this;
    }

    @Step("Kaysiste Yer Almıyor Seç")
    public KurumYonetimiPage kaysisteYerAlmiyorSec(boolean secim) {
        chkKaysisteYerAlmiyor.setSelected(secim);
        return this;
    }

    @Step("İdari Birim Kimlik Kodu doldur")
    public KurumYonetimiPage idariBirimKimlikKoduDoldur(String text) {
        txtIdariBirimKimlikKodu.setValue(text);
        return this;
    }

    @Step("Durum seç")
    public KurumYonetimiPage durumSec(String value) {
        cmbDurum.selectOption(value);
        return this;
    }
    @Step("Ara")
    public KurumYonetimiPage ara(){
        btnAra.click();
        return this;
    }

    @Step("Sorgulama panelinde kurum doldur.")
    public KurumYonetimiPage sorgulaKurumDoldur(String kurumAdi) {
        if (!txtKurumCombolov.isDisplayed())
            filtrePanel.click();
        txtKurumCombolov.selectLov(kurumAdi);
        return this;
    }

    @Step("Sorgulama panelinde kurum doldur.")
    public KurumYonetimiPage kurumDoldur(String text) {
        txtKurumCombolov.selectLov(text);
        return this;
    }


    // Hüseyin Methods
    @Step("{0} kurumunu güncelle")
    public KurumYonetimiPage kurumGuncelle(String kurumAdi){
        tableKurumListesi
                .filterBy(Condition.text(kurumAdi))
                .get(0)
                .$(btnGuncelleSelector)
                .click();
        return this;
    }

    @Step("{0} kurumunu seç")
    public KurumYonetimiPage ustKurumSec(String ustKurum){
        if(divSecilenUstKurum.exists())
            btnSecilenKurumListedenCikar.exists();
        txtUstKurum.selectLov(ustKurum);
        return this;
    }

    @Step("İletişim bilgisi güncelle")
    public KurumYonetimiPage iletisimGuncelle(){
        btnIletisimGuncelle.click();
        return this;
    }

    @Step("Mobil telefon no doldur")
    public KurumYonetimiPage mobilTelNoDoldur(String mobilTelNo){
        txtMobilTelNo.setValue(mobilTelNo);
        return this;
    }

    @Step("Telefon No doldur")
    public KurumYonetimiPage telefonNoDoldur(String telefonNo){
        txtTelefonNo.setValue(telefonNo);
        return this;
    }

    @Step("İş Telefon No doldur")
    public KurumYonetimiPage isTelefonNoDoldur(String isTelefonNo){
        txtIsTelefonNo.setValue(isTelefonNo);
        return this;
    }

    @Step("Fax Numarası 1 doldur")
    public KurumYonetimiPage faxNumarasi1Doldur(String faxNumarasi1){
        txtFaxNumarasi1.setValue(faxNumarasi1);
        return this;
    }

    @Step("Fax Numarası 2 doldur")
    public KurumYonetimiPage faxNumarasi2Doldur(String faxNumarasi2){
        txtFaxNumarasi2.setValue(faxNumarasi2);
        return this;
    }

    @Step("Adres doldur")
    public KurumYonetimiPage adresDoldur(String adres){
        txtAdres.setValue(adres);
        return this;
    }

    @Step("Ülke doldur")
    public KurumYonetimiPage ulkeDoldur(String ulke){
        txtUlke.selectLov(ulke);
        return this;
    }

    @Step("İl doldur")
    public KurumYonetimiPage ilDoldur(String il){
        txtIl.selectLov(il);
        return this;
    }

    @Step("İlçe doldur")
    public KurumYonetimiPage ilceDoldur(String ilce){
        txtIlce.setValue(ilce);
        return this;
    }

    @Step("E Posta doldur")
    public KurumYonetimiPage ePostaDoldur(String eposta){
        txtEPosta.setValue(eposta);
        return this;
    }

    @Step("Web Adresi doldur")
    public KurumYonetimiPage webAdresiDoldur(String webAdresi){
        txtWebAdresi.setValue(webAdresi);
        return this;
    }

    @Step("İletişim bilgisi kaydet")
    public KurumYonetimiPage iletisimBilgisiKaydet(){
        btnIletisimBilgisiKaydet.click();
        return this;
    }

    @Step("Kep adresi güncelle")
    public KurumYonetimiPage kepAdresiGuncelle(String kepAdresi, Integer kepIndex) {

        if(kepIndex == null){
            tableKepAdresleri
                    .filterBy(Condition.text(kepAdresi))
                    .get(0)
                    .$(btnKepAdresiGuncelleSelector)
                    .click();
        } else {
            tableKepAdresleri
                    .filterBy(Condition.text(kepAdresi))
                    .get(kepIndex)
                    .$(btnKepAdresiGuncelleSelector)
                    .click();
        }

        return this;
    }

    @Step("Kep adresi doldur")
    public KurumYonetimiPage kepAdresiDoldur(String kepAdresi) {
        txtKepAdresi.setValue(kepAdresi);
        return this;
    }

    @Step("Kep hizmet sağlayıcısı seç")
    public KurumYonetimiPage kepHizmetSaglayiciSec(String kepHizmetSaglayici) {
        cmbKepHizmetSaglayici.selectOption(kepHizmetSaglayici);
        return this;
    }

    @Step("Kep adresi bilgileri kaydet")
    public KurumYonetimiPage kepAdresiBilgileriKaydet() {
        btnKepAdresiBilgileriKaydet.click();
        return this;
    }

    @Step("Description")
    public KurumYonetimiPage kepAdresiKontrol(String kepAdresi, Integer kepIndex, Boolean shouldBeExist) {

        if(shouldBeExist){

            if(kepIndex == null)
            {
                tableKepAdresleri
                        .filterBy(Condition.text(kepAdresi))
                        .get(0)
                        .shouldBe(Condition.exist);
            } else {
                tableKepAdresleri
                        .filterBy(Condition.text(kepAdresi))
                        .get(kepIndex)
                        .shouldBe(Condition.exist);
            }

        } else {
            if(kepIndex == null)
            {
                tableKepAdresleri
                        .filterBy(Condition.text(kepAdresi))
                        .get(0)
                        .shouldNotBe(Condition.exist);
            } else {
                tableKepAdresleri
                        .filterBy(Condition.text(kepAdresi))
                        .get(kepIndex)
                        .shouldNotBe(Condition.exist);
            }
        }

        return this;
    }

    @Step("Kurum hiyerarşisini güncelle butonuna tıklandı")
    public KurumYonetimiPage kurumHiyerarsisiniGuncelle() {
        btnKurumHiyerarşisiniGuncelle.click();
        $("bekleyinizStatusDialog").waitUntil(Condition.not(Condition.visible), 60000);
        return this;
    }

    @Step("Kurum panelinde kaydet butonuna tıklandı.")
    public KurumYonetimiPage kurumKaydet() {
        //btnKurumKaydet.doubleClick();
        clickJs(btnKurumKaydet);
        return this;
    }

    @Step("kurum kontrol et")
    public KurumYonetimiPage kurumKontrolEt(String kurumAdi, Boolean shouldBeExist) {

        if(shouldBeExist == true){
            tableKurumListesi
                    .filterBy(Condition.text(kurumAdi))
                    .get(0)
                    .shouldBe(Condition.exist);
        } else {
            tableKurumListesi
                    .filterBy(Condition.text(kurumAdi))
                    .get(0)
                    .shouldNotBe(Condition.exist);
        }

        return this;
    }

    @Step("Panel kapat")
    public KurumYonetimiPage panelKapat(){


        //btnEvrakOlusturKapat.click();

        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Kurum Yönetimi]']"))
                .contextClick();
        $(By.id("kapatButton")).click();

        return this;
    }

}

