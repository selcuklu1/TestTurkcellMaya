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
    SelenideElement btnHavaleOnayiOnayla = $(By.id("mainPreviewForm:onaylaButton_id"));
    SelenideElement icerikHavaleOnay = $("button[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton']");
    SelenideElement notAlanıDoldur = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement icerikNotAlanıDoldur = $(By.id("inboxItemInfoForm:notTextArea_id"));
    SelenideElement onayıReddet = $(By.id("mainPreviewForm:reddetButton_id"));
    SelenideElement icerikOnayıReddet = $(By.id("inboxItemInfoForm:reddetButton_id"));
    SelenideElement onizlemeOnayla = $(By.id("mainPreviewForm:onaylaButton_id"));
    BelgenetElement txtHavaleOnayiBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov_id:LovText"));
    BelgenetElement txtHavaleOnayiKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovText"));

    //      SelenideElement onayıReddetEvet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
//      SelenideElement onayıReddetEvet = $("button[id='inboxItemInfoForm:reddetEvetButton_id']");
//      SelenideElement btnHavaleOnayReddet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
    ElementsCollection btnHavaleOnayReddet = $$("[id$='mainPreviewForm:reddetEvetButton_id']");
    ElementsCollection btnIcerikHavaleOnayReddet = $$("[id$='inboxItemInfoForm:reddetEvetButton_id']");
    ElementsCollection btnHavaleOnayEvet = $$("[id$='mainPreviewForm:evetButton_id']");
    ElementsCollection btnOnayla = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-accept']");

    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov_id:LovText"));

    SelenideElement dagitimOnayla = $(By.id("inboxItemInfoForm:onaylaButton_id"));
    ElementsCollection dagitimOnaylaEvet = $$("[id='inboxItemInfoForm:evetButton_id']");

    SelenideElement birimSeç = $("select[id='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu']");
    SelenideElement txtEklenenKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenBirim = $("div[id^='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:j_idt']");
    SelenideElement txtEklenenBirimOpsiyon = $("select[id='mainPreviewForm:dagitimBilgileriBirimLov_id:LovSecilenTable:0:selectOneMenu']");


    @Step("Birim Havale Onayına Gelenler sayfası aç")
    public HavaleOnayınaGelenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayinaGelenler);
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

    @Step("Evrak Sec Checkbox ile")
    public HavaleOnayınaGelenlerPage evrakSecCheckBox(String konu1, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        btnOnayla.get(0).click();

        return this;
    }

    @Step("Evrak no ile teslim al")
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

    @Step("Havale İşlemleri Kişi alanında eklenen \"{kisi}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenKisi.isDisplayed(), true, "Kisi Eklendi");
        Allure.addAttachment("Kisi Eklendi:", kisi);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{birim}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenBirimKontrolu(String birim) {
        Assert.assertEquals(txtEklenenBirim.isDisplayed(), true, "Birim Eklendi");
        Allure.addAttachment("Birim Eklendi:", birim);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{opsiyon}\" kontrolü")
    public HavaleOnayınaGelenlerPage eklenenBirimOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(txtEklenenBirimOpsiyon.getSelectedOption().text().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage havaleOnayiOnayla() {
        btnHavaleOnayiOnayla.pressEnter();
        return this;
    }

    @Step("Havaleyi onaylamak üzeresiniz. Kabul ediyor musunuz? Evet / Hayır uyarısını geldiği görülür.")
    public HavaleOnayınaGelenlerPage havaleyiOnaylamakUzersinizUyariGeldigiGorme() {
        boolean durum = $$(By.id("mainPreviewForm:evetButton_id")).size() > 0;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }


    @Step("Evet tıklanır")
    public HavaleOnayınaGelenlerPage havaleyiOnaylamakUzeresinizEvet() {
        $(By.id("mainPreviewForm:evetButton_id")).pressEnter();
        return this;
    }

    @Step("Evrak İçerikten Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage icerikHavaleOnay() {
        icerikHavaleOnay.click();
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
        icerikOnayıReddet.click();
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
    @Step("Havale Onay Reddet Evet")
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
        dagitimOnayla.click();
        return this;
    }


    @Step("Dagitim Onayla Evet")
    public HavaleOnayınaGelenlerPage dagitimOnaylaEvet() {
        System.out.println("dagitimonaylasize" + dagitimOnaylaEvet.size());
        dagitimOnaylaEvet.last().click();
        return this;
    }


}
