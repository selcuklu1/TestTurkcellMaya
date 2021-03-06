package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/****************************************************
 * Tarih: 2018-01-26
 * Proje: Türksat Functional Test Automation
 * Class: "BirimHavaleOnayınaGelenler" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class HavaleOnayınaGelenlerPage extends MainPage {
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnHavaleOnay = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']");
    SelenideElement btnEvrakGoster = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton']");

    SelenideElement btnHavaleOnayiOnayla = $(By.id("mainPreviewForm:onaylaButton_id"));
    SelenideElement icerikHavaleOnay = $("button[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton']");
    SelenideElement notAlanıDoldur = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement icerikNotAlanıDoldur = $(By.id("inboxItemInfoForm:notTextArea_id"));
    SelenideElement onayıReddet = $(By.id("mainPreviewForm:reddetButton_id"));
    SelenideElement btnIcerikOnayıReddet = $(By.id("inboxItemInfoForm:reddetButton_id"));
    SelenideElement btnIcerikOnayla = $(By.id("inboxItemInfoForm:onaylaButton_id"));
    SelenideElement onizlemeOnayla = $(By.id("mainPreviewForm:onaylaButton_id"));
    BelgenetElement txtHavaleOnayiBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov_id:LovText"));
    BelgenetElement txtHavaleOnayiKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovText"));

    SelenideElement txtBirimKontrol = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov_id:LovText"));
    SelenideElement txtKisiKontrol = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovText"));
    SelenideElement txtNotKontrol = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnOnaylaKontrol = $(By.id("mainPreviewForm:onaylaButton_id"));
    SelenideElement btnReddetKontrol = $(By.id("mainPreviewForm:reddetButton_id"));

    //      SelenideElement onayıReddetEvet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
//      SelenideElement onayıReddetEvet = $("button[id='inboxItemInfoForm:reddetEvetButton_id']");
//      SelenideElement btnHavaleOnayReddet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
    ElementsCollection btnHavaleOnayReddet = $$("[id$='mainPreviewForm:reddetEvetButton_id']");
    ElementsCollection btnIcerikHavaleOnayReddet = $$("[id$='inboxItemInfoForm:reddetEvetButton_id']");
    ElementsCollection btnHavaleOnayEvet = $$("[id$='mainPreviewForm:evetButton_id']");
    ElementsCollection btnOnayla = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-accept']");

    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov_id:LovText"));

    ElementsCollection dagitimOnaylaEvet = $$("[id='inboxItemInfoForm:evetButton_id']");

    SelenideElement birimSeç = $("select[id='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu']");
    SelenideElement txtEklenenKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenBirim = $("div[id^='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenBirimOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu']");

    SelenideElement lblSayfa = $("div[id='inboxItemInfo']");
    SelenideElement lblOnizlemeSayfa = $("div[id='mainPreviewForm:onizlemePanel']");
    //Dagitim Kontrolleri
    SelenideElement dagitimBirimKontrolu = $("div[id^='inboxItemInfoForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:j_idt']");
    SelenideElement dagitimBirimOpsiyon = $("select[id^='inboxItemInfoForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu']");

    SelenideElement dagitimKisiKontrolu = $("div[id^='inboxItemInfoForm:dagitimBilgileriKullaniciLov_id:LovSecilenTable:0:j_idt']");
    @Step("Birim Havale Onayına Gelenler sayfası aç")
    public HavaleOnayınaGelenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayinaGelenler);
        return this;
    }


    @Step("Evrak Içeriği ekranı açılır\n")
    public HavaleOnayınaGelenlerPage icerikEkranKontrol() {
        Assert.assertEquals(lblSayfa.isDisplayed(),true,"Evrak Içeriği Ekranı Kontrolü");
        Allure.addAttachment("Evrak Içeriği Ekranı Kontrolü","");
        return this;
    }

    @Step("Evrak Onizleme ekranı açılır\n")
    public HavaleOnayınaGelenlerPage onizlemeEkranKontrol() {
        Assert.assertEquals(lblOnizlemeSayfa.isDisplayed(),true,"Evrak Onizleme Ekranı Kontrolü");
        Allure.addAttachment("Evrak Onizleme Ekranı Kontrolü","");
        return this;
    }


    @Step("Havale Alan Kontrolleri")
    public HavaleOnayınaGelenlerPage havaleAlanKontrolleri() {
        Assert.assertEquals(txtBirimKontrol.isDisplayed(),true,"Birim Kontrolü");
        Allure.addAttachment("Birim Kontrolü Alanı","gelmektedir.");

        Assert.assertEquals(txtKisiKontrol.isDisplayed(),true,"Kisi Kontrolü");
        Allure.addAttachment("Kisi Kontrolü Alanı","gelmektedir.");

        Assert.assertEquals(txtNotKontrol.isDisplayed(),true,"Not Kontrolü");
        Allure.addAttachment("Not Kontrolü Alanı","gelmektedir.");

        Assert.assertEquals(btnOnaylaKontrol.isDisplayed(),true,"Onayla Kontrolü");
        Allure.addAttachment("Onayla Kontrolü","gelmektedir.");

        Assert.assertEquals(btnReddetKontrol.isDisplayed(),true,"Reddet Kontrolü");
        Allure.addAttachment("Reddet Kontrolü","gelmektedir.");
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public HavaleOnayınaGelenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrakın ekranın sağında önizlemede geldiği görülür.")
    public HavaleOnayınaGelenlerPage evrakOnizlemeGeldigiGorme(boolean gorulen){
        boolean durum = $(By.id("mainPreviewForm:evrakOnizlemeTab")).isDisplayed();
        Assert.assertEquals(durum,gorulen);
        return this;
    }
    
    @Step("Evrak no ile evrağın gelmediği görülür: \"{evrakNo}\" ")
    public HavaleOnayınaGelenlerPage evrakNoIleEvrakGelmedigiGorme(String evrakNo) {
        boolean durum = tblEvraklar
                .filterBy(Condition.text(evrakNo)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Havale Onayı")
    public HavaleOnayınaGelenlerPage havaleOnayi() {
        $("[class='ui-button-icon-left ui-icon havaleOnay']").click();
        return this;
    }

    @Step("Onayla ve onayı reddet seçeneklerinin geldiği görülür")
    public HavaleOnayınaGelenlerPage havaleOnayiOnaylaOnayiReddetGeldigiGorme(){
        boolean durum = $(By.id("inboxItemInfoForm:onaylaButton_id")).isDisplayed();
        boolean durum1 = $(By.id("inboxItemInfoForm:reddetButton_id")).isDisplayed();
        Assert.assertEquals(durum,true);
        Assert.assertEquals(durum1,true);
        takeScreenshot();
        return this;
    }

    @Step("Birim alanını doldur: {birim}")
    public HavaleOnayınaGelenlerPage havaleOnayiBirimDoldur(String birim) {
        txtHavaleOnayiBirim.selectLov(birim);
        return this;
    }

    @Step("Birim alanında seçileni Bilgi için gönder")
    public HavaleOnayınaGelenlerPage havaleOnayinaBirimGeregiIcinBilgiIcinSec() {
        $(By.id("mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu")).selectOption("BİLGİ İÇİN GÖNDER");
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{opsiyon}\" seçilir")
    public HavaleOnayınaGelenlerPage dagitimBilgileriBirimOpsiyon(String opsiyon) {
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        if (opsiyon.equals(gerek))
            birimSeç.selectOptionByValue("G");
        else if (opsiyon.equals(bilgi))
            birimSeç.selectOptionByValue("B");
        else if (opsiyon.equals(koordinasyon))
            birimSeç.selectOptionByValue("K");

        return this;
    }

    @Step("Kişi alanını doldur: {kisi}")
    public HavaleOnayınaGelenlerPage havaleOnayiKisiDoldur(String kisi, String birim) {
        txtHavaleOnayiKisi.selectLov(kisi, birim);
        return this;
    }

    @Step("Evrak Sec Evrak Onayla")
    public HavaleOnayınaGelenlerPage evrakSecEvrakOnayla(String konu1, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        btnOnayla.get(0).click();

        return this;
    }

    @Step("Evrak no ile seç ve içerik göster")
    public HavaleOnayınaGelenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage havaleOnay() {
        btnHavaleOnay.click();
        return this;
    }

    @Step("Havale Onay İkon Kontrolu tıkla")
    public HavaleOnayınaGelenlerPage havaleOnayIkonKontrolu() {
        Assert.assertEquals(btnHavaleOnay.isDisplayed(), true, "Havale Onay Ikon Kontrolu");
        Allure.addAttachment("Havale Onay Ikon Kontrolu", "");
        return this;
    }

    @Step("Havale Evrak Goster Buton Kontrolu")
    public HavaleOnayınaGelenlerPage evrakGosterButonKontrolu() {
        Assert.assertEquals(btnEvrakGoster.isDisplayed(), true, "Havale Evrak Goster Buton Kontrolu");
        Allure.addAttachment("Havale Evrak Goster Buton Kontrolu", "");
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında  \"{kisi}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenKisi.isDisplayed(), true, "Kisi Kontrolü");
        Allure.addAttachment("Kisi Kontrolü:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenBirimKontrolu(String birim) {
        Assert.assertEquals(dagitimBirimKontrolu.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{birim}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenOnizlemeBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{opsiyon}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenBirimOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(dagitimBirimOpsiyon.getSelectedOption().text().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Havale İşlemleri Birim alanında eklenen \"{opsiyon}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenBirimOnizlemeOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(birimSeç.getSelectedOption().text().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

//    SelenideElement txtEklenenBirimOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:selectOneMenu']");
    @Step("Dağıtım Bilgileri Birim alanında \"{opsiyon}\" seçilir")
    public HavaleOnayınaGelenlerPage havaleIslemleriBirimOpsiyonSec(String opsiyon) {
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        if (opsiyon.equals(gerek))
            dagitimBirimOpsiyon.selectOptionByValue("G");
        else if (opsiyon.equals(bilgi))
            dagitimBirimOpsiyon.selectOptionByValue("B");
        else if (opsiyon.equals(koordinasyon))
            dagitimBirimOpsiyon.selectOptionByValue("K");

        return this;
    }

    @Step("Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage havaleOnayiOnayla() {
        btnHavaleOnayiOnayla.pressEnter();
        return this;
    }

    @Step("Onayla butonunu tıkla")
    public HavaleOnayınaGelenlerPage icerikGosterHavaleOnayiOnayla(){
        $(By.id("inboxItemInfoForm:onaylaButton_id")).pressEnter();
        return this;
    }

    @Step("Havaleyi onaylamak üzeresiniz. Kabul ediyor musunuz? Evet / Hayır uyarısını geldiği görülür.")
    public HavaleOnayınaGelenlerPage havaleyiOnaylamakUzersinizUyariGeldigiGorme() {
        boolean durum = $$("[id$='evetButton_id']").size() > 0;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Evet tıklanır")
    public HavaleOnayınaGelenlerPage havaleyiOnaylamakUzeresinizEvet() {
        $(By.id("mainPreviewForm:evetButton_id")).pressEnter();
        return this;
    }

    @Step("Evet tıklanır")
    public HavaleOnayınaGelenlerPage icerikHavaleyiOnaylamakUzeresinizEvet() {
        $(By.id("inboxItemInfoForm:evetButton_id")).pressEnter();
        return this;
    }


    @Step("Evrak İçerikten Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage icerikHavaleOnay() {
        icerikHavaleOnay.click();
        return this;
    }

    @Step("Evrak İçerikten Havale Onay butonu kontrolü")
    public HavaleOnayınaGelenlerPage icerikHavaleOnayButonKontrolu() {
        Assert.assertEquals(icerikHavaleOnay.isDisplayed(),true,"Havale Onay Buton Kontrolü");
        Allure.addAttachment("Havale Onay Buton Kontrolü" , "");
        return this;
    }

    @Step("Dağıtım Için Gönderilecek Birim Kontrolü")
    public HavaleOnayınaGelenlerPage dagitimIcinGonderileceklerBirimKontrolu(String birim) {
        Assert.assertEquals(dagitimBirimKontrolu.getText().contains(birim),true,"Dağıtım Için Gönderilecek Birim Kontrolü");
        Allure.addAttachment("Dağıtım Için Gönderilecek Birim Kontrolü" , birim);
        return this;
    }

    @Step("Dağıtım Için Gönderilecek Kisi Kontrolü")
    public HavaleOnayınaGelenlerPage dagitimIcinGonderileceklerKisiKontrolu(String kisi) {
        Assert.assertEquals(dagitimKisiKontrolu.getText().contains(kisi),true,"Dağıtım Için Gönderilecek Kisi Kontrolü");
        Allure.addAttachment("Dağıtım Için Gönderilecek Kisi Kontrolü" , kisi);
        return this;
    }


    @Step("Not Alanını Doldur")
    public HavaleOnayınaGelenlerPage notAlanınıDoldur(String not) {
        notAlanıDoldur.setValue(not);
        return this;
    }

    @Step("Not Alanını Doldur")
    public HavaleOnayınaGelenlerPage icerikNotAlanınıDoldur(String not) {
        icerikNotAlanıDoldur.setValue(not);
        return this;
    }

    @Step("Havale Onay Reddet")
    public HavaleOnayınaGelenlerPage onayıReddet() {
        onayıReddet.click();
        return this;
    }

    @Step("Havale Onay Reddet")
    public HavaleOnayınaGelenlerPage icerikOnayıReddet() {
        btnIcerikOnayıReddet.click();
        return this;
    }

    @Step("Havale Onay Reddet Buton Kontrolü")
    public HavaleOnayınaGelenlerPage icerikOnayıReddetButonKontrolu() {
        Assert.assertEquals(btnIcerikOnayıReddet.isDisplayed(),true,"Havale Onay Reddet Buton Kontrolü");
        Allure.addAttachment("Havale Onay Reddet Buton Kontrolü", "");
        return this;
    }

    @Step("Havale Onayla Buton Kontrolü")
    public HavaleOnayınaGelenlerPage icerikOnaylaButonKontrolu() {
        Assert.assertEquals(btnIcerikOnayla.isDisplayed(),true,"Havale Onayla Buton Kontrolü");
        Allure.addAttachment("Havale Onayla Buton Kontrolü", "");
        return this;
    }

    @Step("Onizleme Havale Onay")
    public HavaleOnayınaGelenlerPage onizlemeOnayla() {
        onizlemeOnayla.click();
        return this;
    }

    // TODO: Her dönen butonu click yapma
    // 2 tane Evet buttonu dönüyor ve aralarında fark yok
    @Step("Havale Onay Reddet Evet")
    public HavaleOnayınaGelenlerPage onayıReddetEvet() {
        btnHavaleOnayReddet.last().click();
        return this;
    }

    // TODO: Her dönen butonu click yapma
    // 2 tane Evet buttonu dönüyor ve aralarında fark yok
    @Step("Havale Onay Reddet Evet ve Havale onayini reddetmek üzeresiniz. Kabul ediyor musunuz? kontrolu")
    public HavaleOnayınaGelenlerPage icerikOnayıReddetEvet() {
        btnIcerikHavaleOnayReddet.last().click();
        return this;
    }

    // TODO: Her dönen butonu click yapma
    // 2 tane Evet buttonu dönüyor ve aralarında fark yok
    @Step("Havale Onay Reddet Evet")
    public HavaleOnayınaGelenlerPage onayıOnaylaEvet() {
        btnHavaleOnayEvet.last().click();
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public HavaleOnayınaGelenlerPage dagitimBilgileriBirimDoldur2(String birim) {
        cmbHavaleIslemleriBirim.selectLov(birim);
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Dagitim Onayla")
    public HavaleOnayınaGelenlerPage dagitimOnayla() {
        btnIcerikOnayla.click();
        return this;
    }


    @Step("Dagitim Onayla Evet : Havaleyi onaylamak üzeresiniz. Kabul ediyor musunuz?")
    public HavaleOnayınaGelenlerPage dagitimOnaylaEvet() {
        dagitimOnaylaEvet.last().click();
        return this;
    }
}
