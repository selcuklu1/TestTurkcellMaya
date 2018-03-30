package pages.pageComponents;

import com.codeborne.selenide.*;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 11.01.2018
 * Açıklama:
 */
public class EvrakPageButtons extends MainPage {

    private SelenideElement container;

    public EvrakPageButtons() {
        container = $("html");
    }

    public EvrakPageButtons(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    private SelenideElement getButton(String text) {
        return getContainer().$x("descendant::*[text()='" + text + "']/ancestor::tbody[1]//button");
    }


    @Step("s-İmza radio butonu bul")
    public SelenideElement getSImzalaRadio() {
        return getEvrakImzalaDialog().$("#imzalaForm\\:imzalaRadio .ui-radiobutton-box");
    }

    @Step("s-İmza seç")
    public EvrakPageButtons sImzalaRadioSec() {
/*        if (islemMesaji().getMessageTitles().size() > 0)
            if (islemMesaji().isUyari("Servise ulaşılamıyor!"))
                islemMesaji().closeMessage();
            else
                throw new RuntimeException("İşlem Mesajı");*/
        getSImzalaRadio().waitUntil(visible, Configuration.timeout + 20000).click();
        //getSImzalaRadio().shouldBe(visible).click();
        //clickJs(getEvrakImzalaDialog().find("#imzalaForm\\:imzalaRadio input"));
        return this;
    }

    @Step("İmza onayı ver")
    public EvrakPageButtons evrakImzaOnay() {
        for (int i = 0; i < Configuration.timeout / 1000; i++) {
            sleep(1000);
            if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)) {
                $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                break;
            } else if ($("#imzalaForm\\:imzalaButton").is(visible)) {
                $("#imzalaForm\\:imzalaButton").click();
                break;
            }
        }
        System.out.println("evrakImzaOnay exit");
        return this;
    }

    //region İmzala
    @Step("İmzala butonu bul")
    public SelenideElement getImzalaButton() {
        return getButton("İmzala");
    }

    @Step("Evrak İmzala pencerisi bulunur")
    public SelenideElement getEvrakImzalaDialog() {
        return $("#evrakImzalaDialog");
    }

    @Step("Evrak İmzala pencerisi kapat")
    public EvrakPageButtons closeEvrakImzalaDialog() {
        getEvrakImzalaDialog().$("a.ui-dialog-titlebar-close").click();
        return this;
    }

    public SelenideElement getImzalaForm() {
        return $("#imzalaForm");
    }

    @Step("İmzala butona tıkla")
    public EvrakPageButtons imzalaButonaTikla() {
        getImzalaButton().shouldBe(visible).pressEnter();
        return this;
    }

    @Step("İmzala")
    public EvrakPageButtons evrakImzala() {
        imzalaButonaTikla();
        sImzalaRadioSec();
        /*for (int i = 0; i < 5; i++) {
            if (islemMesaji().getMessageTitles().size() > 0)
                takeScreenshot();
            if (islemMesaji().isUyari("Servise ulaşılamıyor!"))
                islemMesaji().closeMessage();
            else
                break;
            sleep(1000);
            //throw new RuntimeException("İşlem Mesajı");
        }*/
        evrakImzaOnay();
        return this;
    }

    @Step("Güncellenen evrağı imzalama denemesi. Dialog kontrolü - \"Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir\"")
    public EvrakPageButtons evrakGuncellenenEvraginImzalaTiklaDialogKontrol() {
        imzalaButonaTikla();
        Selenide.prompt( "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.",null);
        return this;
    }

    //endregion

    //region Parafla
    @Step("Parafla butonu bul")
    public SelenideElement getParaflaButton() {
        return getButton("Parafla");
    }

    @Step("Koordine Parafla butonu bul")
    public SelenideElement getKoordineParaflaButton() {
        return getButton("Koordine Parafla");
    }

    @Step("Parafla butona tıkla")
    public EvrakPageButtons paraflaButonaTikla() {
        getParaflaButton().click();
        return this;
    }

    @Step("Koordine Parafla butona tıkla")
    public EvrakPageButtons koordineParaflaButonaTikla() {
        getKoordineParaflaButton().click();
        return this;
    }

    @Step("Parafla")
    public EvrakPageButtons evrakParafla() {
        paraflaButonaTikla();
        sImzalaRadioSec();
        evrakImzaOnay();
        return this;
    }

    @Step("Koordine Parafla")
    public EvrakPageButtons evrakKoordineParafla() {
        koordineParaflaButonaTikla();
        sImzalaRadioSec();
        evrakImzaOnay();
        return this;
    }

    //endregion

    //region İade Et
    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt() {
        getButton("İade Et").click();
        //$("#inboxItemInfoForm\\:iadeEtButton_id").click();
        container.$("button[id$='iadeEtButton_id'").click();
        return this;
    }

    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt(String iadeNotu) {
        getButton("İade Et").click();
        //getContainer().$x("descendant::td[@class='buttonMenuContainerDefault' and descendant::span[.='İade Et']]//button").click();
        //getContainer().$("button .iadeEt").click();
        container.$("textarea[id$='notTextArea_id'").setValue(iadeNotu);
        container.$("button[id$='iadeEtButton_id'").click();
        return this;
    }

    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt(User user, String iadeNotu) {
        getButton("İade Et").click();
        comboBox(By.id("mainPreviewForm:kullaniciListOneMenu_id")).selectComboBox(user.getFullname(), true);
        container.$("textarea[id$='notTextArea_id'").setValue(iadeNotu);
        container.$("button[id$='iadeEtButton_id'").click();
        return this;
    }

    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt(String userText, String iadeNotu) {
        getButton("İade Et").click();
        comboBox(By.id("mainPreviewForm:kullaniciListOneMenu_id")).selectComboBox(userText);
        container.$("textarea[id$='notTextArea_id'").setValue(iadeNotu);
        container.$("button[id$='iadeEtButton_id'").click();
        return this;
    }

    @Step("Iade et")
    public EvrakPageButtons evrakIadeEt(String userText, String uploadFile, String iadeNotu) {
        getButton("İade Et").click();
        comboBox(By.id("mainPreviewForm:kullaniciListOneMenu_id")).selectComboBox(userText);
        $(By.id("mainPreviewForm:fileUploadIadeEk_input")).uploadFile(new File(uploadFile));
        container.$("textarea[id$='notTextArea_id'").setValue(iadeNotu);
        container.$("button[id$='iadeEtButton_id'").click();
        return this;
    }

    //endregion

    @Step("Kaydet")
    public EvrakPageButtons evrakKaydet() {
        getContainer().$("button .kaydet").click();
        $("#kaydetConfirmForm\\:kaydetEvetButton").click();
        return this;
    }

    @Step("Kaydet")
    public EvrakPageButtons evrakKaydetMesajKontrollu() {
        getContainer().$("button .kaydet").click();
        confirmDialog().onayMesajKontrolu(text("Evrakı kaydetmek istediğinize emin misiniz?"))
                .confirmEvetTikla();
        //$("#kaydetConfirmForm\\:kaydetEvetButton").click();
        return this;
    }

    //region Kaydet ve Onaya Sun
    @Step("Kaydet ve Onaya Sun butonu bul")
    public SelenideElement getEvrakKaydetVeOnayaSun() {
        return getButton("Kaydet ve Onaya Sun");
    }

    @Step("Kaydet ve Onaya Sun")
    public EvrakPageButtons evrakKaydetVeOnayaSunTikla() {
        getEvrakKaydetVeOnayaSun().shouldBe(visible).click();
        return this;
    }
    //endregion

    //region Gönder
    @Step("Gönder butonu bul")
    public SelenideElement getGonder() {
        return getContainer().$("button[id$='gonderButton']");
    }

    @Step("Gönder butona tıkla")
    public EvrakPageButtons gonderTikla() {
        getGonder().click();
        return this;
    }
    //endregion

    //region Postala
    @Step("Postala butonu bul")
    public SelenideElement getEvrakPostala() {
        return getButton("Parafla");
    }

    @Step("Postala butona tıkla")
    public EvrakPageButtons postalaTikla() {
        getEvrakPostala().click();
        return this;
    }

    @Step("Postala")
    private EvrakPageButtons evrakPostala() {
        postalaTikla();
        getContainer().$x("descendant::button[.='Postala']").click();
        confirmDialog().confirmEvetTikla();
        return this;
    }
    //endregion


    @Step("PDF Önizleme butonu bul")
    public SelenideElement getPdfOnizleme() {
        return getButton("PDF Önizleme");
    }

    @Step("PDF Önizleme butonu tikla")
    public EvrakPageButtons pdfOnizlemeTikla() {
        getPdfOnizleme().click();
        return this;
    }

    SelenideElement btnIcerikDegistiIptal = $(By.id("ilkIzBirakacakKullanicidanSonraGuncellenenEvrakIslemForm:evrakSecmeliDegistiVazgecButton"));
    @Step("Evrak Içerik değişti ve sonrasında gelen uyarı ekranında, Iptal butonu tıklanır")
    public MainPage icerikDegistiIptal() {
        btnIcerikDegistiIptal.click();
        return this;
    }


    SelenideElement dialogIcerikDegistiUyarıKontrol = $("div[id='ilkIzBirakacakKullanicidanSonraGuncellenenEvrakIslemDialog']");
    @Step("Onay Ekranı: \"{uyari}\"  \"{secenek1}\"  \"{secenek2}\" ")
    public MainPage icerikDegistiUyarıKontrol(String uyari,String secenek1,String secenek2) {
        Assert.assertEquals(dialogIcerikDegistiUyarıKontrol.getText().contains(uyari), true, uyari);
        Allure.addAttachment(uyari, "");

        Assert.assertEquals(dialogIcerikDegistiUyarıKontrol.getText().contains(secenek1), true, secenek1);
        Allure.addAttachment(secenek1, "");

        Assert.assertEquals(dialogIcerikDegistiUyarıKontrol.getText().contains(secenek2), true, secenek2);
        Allure.addAttachment(secenek2, "");
        return this;
    }


    ElementsCollection radioEvrakIcerikDegistiImzalaveDevamEt = $$("table[id='ilkIzBirakacakKullanicidanSonraGuncellenenEvrakIslemForm:secenekTipi'] tr");
    @Step("Evrak Içerik değişti ve sonrasında gelen uyarı ekranında, İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)")
    public MainPage evrakIcerikDegistiImzalaveDevamEt() {
        radioEvrakIcerikDegistiImzalaveDevamEt.filterBy(Condition.text("İmzala ve devam et")).get(0).click();
        return this;
    }

    ElementsCollection radioEvrakIcerikDegistiIadeEt = $$("table[id='ilkIzBirakacakKullanicidanSonraGuncellenenEvrakIslemForm:secenekTipi'] tr");
    @Step("Evrak Içerik değişti Iade Et")
    public MainPage evrakIcerikDegistiIadeEt() {
        radioEvrakIcerikDegistiIadeEt.filterBy(Condition.text("İade Et")).get(0).click();
        return this;
    }

    SelenideElement btnEvrakIcerikDegistiKaydet = $("button[id$='evrakSecmeliDegistiKaydetButton']");
    @Step("Evrak Içerik değişti ve Kaydet)")
    public MainPage evrakSecmeliDegistiKaydet() {
        btnEvrakIcerikDegistiKaydet.click();
        return this;
    }

    SelenideElement btnKullaniciyaIadeEt = $("button[id$='iadeEtButton_id']");
    @Step("Kullanıcıya Iade Et butonu tıklanır)")
    public MainPage kullaniciyaIadeEt() {
        btnKullaniciyaIadeEt.click();
        takeScreenshot();
        return this;
    }

    SelenideElement btnEvrakIcerikDegistiKaydetEvet = $("button[id^='inboxItemInfoForm:j_idt'][onclick*='iadeKaydetEtDogrulaDialog']");
    @Step("Evrakta değişiklik var, kaydetmek ister misiniz?)")
    public MainPage evrakIcerikDegistiKaydetEvet() {
        btnEvrakIcerikDegistiKaydetEvet.click();
        return this;
    }

    SelenideElement btnEvrakIcerikDegistiEvet = $("button[id$='evrakDegistiKaydetButton']");
    @Step("Evrak Içerik değişti ve Kaydet")
    public MainPage evrakSecmeliDegistiEvet() {
        btnEvrakIcerikDegistiEvet.click();
        return this;
    }

    SelenideElement btnImzalanamaz = $("span[class='ui-button-icon-left ui-icon imzalanamaz']");
    @Step("İmzalama butonun üzerine Üçgen içerisinde Ünlem(!)")
    public MainPage imzalanamazButtonKontrol() {
        Assert.assertEquals(btnImzalanamaz.isDisplayed(),true,"İmzalama butonun üzerine Üçgen içerisinde Ünlem(!) ");
        Allure.addAttachment("İmzalama butonun üzerine Üçgen içerisinde Ünlem(!) ","");
        takeScreenshot();
        return this;
    }

    SelenideElement btnParaflanamaz = $("span[class='ui-button-icon-left ui-icon paraflanamaz']");
    @Step("Paraflama butonun üzerine Üçgen içerisinde Ünlem(!)")
    public MainPage paraflanamazButtonKontrol() {
        Assert.assertEquals(btnParaflanamaz.isDisplayed(),true,"Paraflama butonun üzerine Üçgen içerisinde Ünlem(!) ");
        Allure.addAttachment("Paraflama butonun üzerine Üçgen içerisinde Ünlem(!) ","");
        takeScreenshot();
        return this;
    }



    SelenideElement txtEvrakImzalaUyari = $("div[id='imzalaForm:imzaPanel_header']");
    @Step("Evrak Içerik değişti ve Kaydet)")
    public MainPage evrakImzalaUyariKontrol(String uyari) {
        Assert.assertEquals(txtEvrakImzalaUyari.getText().contains(uyari), true, "Evrak İmza Uyari Kontrol");
        Allure.addAttachment("Evrak İmza Uyari Kontrol", " gelmektedir.");
        return this;
    }

    @Step("Iade Et Butonu Bul")
    public SelenideElement getIadeEt() {
        return getButton("İade Et");
    }

    @Step("Iade Et buton tıklama")
    public EvrakPageButtons iadeEt() {
        getIadeEt().click();
        return this;
    }

    @Step("Parafcı Kontrol: {user}")
    public EvrakPageButtons parafciKontrol(String user) {
        comboBox(By.id("inboxItemInfoForm:kullaniciListOneMenu_id")).selectComboBox(user);
        return this;
    }

    @Step("Kontrol Eden Kontrol: {user}")
    public EvrakPageButtons kontrolEdenKontrol(String user) {
        comboBox(By.id("inboxItemInfoForm:kullaniciListOneMenu_id")).selectComboBox(user);
        return this;
    }

    @Step("Dosya ekle")
    public EvrakPageButtons dosyaEkle(String path,String dosyaAdi) throws InterruptedException{
        havaleDosyaEkle(path);
        havaleDosyaEkleDosyaAdiKontrol(dosyaAdi);
        return this;
    }
    SelenideElement dosyaPath = $(By.xpath("//input[@id='inboxItemInfoForm:fileUploadIadeEk_input']"));
    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public EvrakPageButtons havaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public EvrakPageButtons havaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Not alanını doldur: {not}")
    public EvrakPageButtons notAlaniDoldur(String not) {
        container.$("textarea[id$='notTextArea_id']").setValue(not);
        return this;
    }

    @Step("Geri Al butonu bul")
    public SelenideElement getGeriAl() {
        return getButton("Geri Al");
    }

    @Step("Evrak Önizleme Geri Al")
    public EvrakPageButtons geriAl() {
        getGeriAl().click();
        return this;
    }

    @Step("Geri Al notu doldur")
    public EvrakPageButtons geriAlNotDoldur(String not){
        container.$("[id$='evrakGeriAlInputTextareaId']").setValue(not);
        return this;
    }
    
    @Step("Geri Al")
    public EvrakPageButtons geriAlGeriAl(){
        container.$x("descendant::button[.='Geri Al']").pressEnter();
        return this;
    }


    @Step("Teslim Al ve Havale Et butonu aranır")
    public SelenideElement getTeslimAlveHavaleEt() {
        return getButton("Teslim Al ve Havale Et");
    }

    @Step("Teslim Al ve Havale Et")
    public EvrakPageButtons teslimAlveHavaleEt() {
        getTeslimAlveHavaleEt().click();
        return this;
    }

    @Step("Evrak Göster butonu tıklanır")
    public EvrakPageButtons evrakGoster() {
        getButton("Evrak Göster").click();
        return this;
    }

    @Step("Kontrol Et")
    public EvrakPageButtons kontrolEt() {
        getButton("Kontrol Et").click();
        return this;
    }

}
