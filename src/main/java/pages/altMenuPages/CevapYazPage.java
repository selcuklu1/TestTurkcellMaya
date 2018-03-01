package pages.altMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageComponents.tabs.EditorTab;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class CevapYazPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'Cevap Yaz' and @class = 'ui-dialog-title']"));
    //Bilgileri sekmesinde bulunanlar
    SelenideElement cmbEvrakTuru = $(By.id("windowCevapEvrakForm:evrakBilgileriList:1:evrakTuruCombo"));
    SelenideElement cmbEmirTalimat = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:2:emirTalimatPanelGrid']/tbody/tr/td[3]/select"));
    SelenideElement cmbEvrakDili = $(By.id("windowCevapEvrakForm:evrakBilgileriList:3:evrakDili"));
    SelenideElement txtKonuKodu = $(By.id("windowCevapEvrakForm:evrakBilgileriList:4:konuKoduLov:LovText"));
    SelenideElement txtKonu = $("textarea[id^='windowCevapEvrakForm:evrakBilgileriList:'][id$=':konuTextArea']");
    SelenideElement cmbArsivKategorisi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:7:j_idt35880"));
    SelenideElement cmbGizlilikDerecesi = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:9:guvenlikKodu']"));
    SelenideElement cmbIvedilik = $(By.id("windowCevapEvrakForm:evrakBilgileriList:10:ivedilik"));
    SelenideElement txtMiat = $(By.id("windowCevapEvrakForm:evrakBilgileriList:11:miatCalendar_input"));
    SelenideElement cmbGeregiSecimTipi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:12:j_idt35921"));
    BelgenetElement comboLovGeregi = comboLov("[id$='geregiLov:LovText']");
    SelenideElement cmbBilgiSecimTipi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:13:j_idt35912"));
    SelenideElement txtBilgi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:13:bilgiLov:LovText"));
    SelenideElement chkDagitimiEkYap = $(By.id("windowCevapEvrakForm:evrakBilgileriList:16:dagitimEkYapCheckBoxId_input"));
    SelenideElement btnKaydetVeOnayaSun = $(By.cssSelector("[id^='windowCevapEvrakForm:j_idt'] [id$='uiRepeat:2:panelGrid'] span[class$='kaydetHavaleEt']"));
    SelenideElement btnImzala = $(By.cssSelector("[id^='windowCevapEvrakForm:j_idt'] [id$='uiRepeat:2:panelGrid'] span[class$='imzala']"));
    BelgenetElement cmbKaldiralacakKlasorler = comboLov("input[id$='eklenecekKlasorlerLov:LovText']");
    BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
    SelenideElement btnImzalaEkraniKapat = $("[id='evrakImzalaDialog'] [class$='closethick']");
    //Ekleri Sekmesi
    //Dosya Ekle alt sekmesi
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:dosyaAciklama']"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement btnEvrakEkTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("windowCevapEvrakForm:evrakEkTabView:dosyaTemizleButton"));
    //Fiziksel Ek Ekle alt sekmesi
    SelenideElement txtEvrakEkTabViewFizikselEkMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:aciklamaTextArea']"));
    SelenideElement cmbEvrakEkTabViewGuvenlikKoduAciklama = $(By.id("windowCevapEvrakForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEvrakEkTabViewFizikselEkEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:aciklamaEkleButton"));
    //Sistemde Kayıtlı Evrak Ekle
    SelenideElement dateTxtEvrakEkTabViewEvrakBaslamaTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewEvrakSonTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbEvrakEkTabViewEvrakinAranacagiYer = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement btnEvrakEkTabViewDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:dokumanAraButton']"));
    SelenideElement txtEvrakEkTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:evrakAramaText']"));
    //Arşivde Kayıtlı Evrak Ekle alt sekmesi
    SelenideElement dateTxtEvrakEkTabViewArsivdeKayitliEvrakBaslamaTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewArsivdeKayitliEvrakSonTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
    SelenideElement txtEvrakEkTabViewKonu = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
    SelenideElement txtEvrakEkTabViewKullanici = $(By.id("windowCevapEvrakForm:evrakEkTabView:kisiyeLov_id:LovText"));
    SelenideElement txtEvrakEkTabViewEvrakSayisi = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));
    SelenideElement btnEvrakEkTabViewArsivdeKayitliDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraButtonId']"));
    //İlgileri sekmesi
    //Dosya Ekle alt sekmesi
    SelenideElement txtIlgiIslemleriTabViewIlgiMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:dosyaAciklama']"));
    SelenideElement btnIlgiIslemleriTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:fileUploadButtonA_input"));
    SelenideElement btnIlgiIslemleriTabViewEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:dosyaEkleButton"));
    SelenideElement btnIlgiIslemleriTabViewTemizle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiDosyaTemizleButton"));
    //Metin Ekle alt sekmesi
    SelenideElement txtIlgiIslemleriTabViewMetinEkleIlgiMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:aciklamaTextArea']"));
    SelenideElement btnIlgiIslemleriTabViewAciklamaEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));
    //Sistemde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBasId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
    SelenideElement dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSonId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerId = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement txtIlgiIslemleriTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:evrakAramaText']"));
    SelenideElement btnIlgiIslemleriTabViewDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:dokumanAraButton']"));
    //Arşivde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihBasId_input"));
    SelenideElement dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihSonId_input"));
    SelenideElement txtIlgiIslemleriTabViewKonu = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraKonuInputTextId"));
    SelenideElement txtIlgiIslemleriTabViewKullanici = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlgiIslemleriTabViewEvrakSayi = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
    SelenideElement btnIlgiIslemleriTabViewArsivdenIlgiEvrakAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraButtonId']"));
    //İlişkili Evraklar sekmesi
//Dosya Ekle alt sekmesi
    SelenideElement txtIlisikIslemleriTabViewIlisikMetni = $(By.id("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:dosyaAciklama']"));
    SelenideElement btnIlisikIslemleriTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:fileUploadButtonA_input"));
    SelenideElement btnIlisikIslemleriTabViewEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:ilisikEkleButton"));
    SelenideElement btnIlisikIslemleriTabViewTemizle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:ilisikTemizleButton"));
    //Sistemde Kayıtlı Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihBasId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihBasId_input"));
    SelenideElement dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihSonId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihSonId_input"));
    SelenideElement cmbIlisikIslemleriTabViewEvrakınAranacagiYer = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakAramaAranacakYerId']"));
    SelenideElement txtIlisikIslemleriTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:evrakAramaText']"));
    SelenideElement btnIlisikIslemleriTabViewDokumanAra = $(By.id("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:dokumanAraButton']"));
    //Tercume Ekle alt sekmesi
    SelenideElement txtIlisikIslemleriTabViewTercumeAciklama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:tercumeAciklama']"));
    SelenideElement btnIlisikIslemleriTabViewTercumeDosyaEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:fileUploadButtonB_input"));
    SelenideElement btnIlisikIslemleriTabViewTercumeEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:tercumeEkleButton"));
    SelenideElement btnIlisikIslemleriTabViewTercumeTemizle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:tercumeTemizleButton"));
    //Arsivde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihBasId_input"));
    SelenideElement dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihSonId_input"));
    SelenideElement txtIlisikIslemleriTabViewKonu = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraKonuInputTextId"));
    SelenideElement txtIlisikIslemleriTabViewKullanici = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlisikIslemleriTabViewEvrakSayi = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraSayiInputTextId"));
    SelenideElement btnIlisikIslemleriTabViewArsivdenIlisikEvrakAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraButtonId']"));
    //Editor
    SelenideElement btnEditor = $("[id^='windowCevapEvrakForm:j_idt'] [id$='uiRepeat:1:cmdbutton'] [class$='editor']");
    SelenideElement btnBilgiler = $("[id^='windowCevapEvrakForm:j_idt'] [id$='uiRepeat:0:cmdbutton'] [class$='kullaniciBilgileri']");
    SelenideElement editorIlgiKismi = $(By.id("windowCevapEvrakForm:ilgiOutPanel"));
    BelgenetElement cmbGeregi = comboLov("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    BelgenetElement cmbGeregiDataTable = comboLov("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='geregiLov:LovSecilenTable_data']");
    BelgenetElement cmbKonuKodu = comboLov("[id^='windowCevapEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
    SelenideElement txtOnayIslemiAciklama = $(By.id("windowCevapEvrakForm:onayIslemiAciklama"));
    SelenideElement btnGonder = $(By.id("windowCevapEvrakForm:gonderButton"));
    SelenideElement btnEvetPopup = $(By.cssSelector("div[class*='ui-confirm-dialog'] button[id='kaydetEvetButton']"));
    SelenideElement btnHayirPopup = $(By.cssSelector("div[class*='ui-confirm-dialog'] button[id='kaydetHayirButton']"));
    SelenideElement btnKaydet = $x("//span[contains(@class, 'kaydet')]/..");
    SelenideElement btnEvrakKopyala = $("[class='ui-button-icon-left ui-icon evrakKopyala']");
    ElementsCollection btnEvrakKopyalaEvet = $$("[id='evrakCopyConfirmForm'] button");
    SelenideElement btnEvrakKopyalaUyariEvet = $(By.id("evrakCopyConfirmForm:copyEvrakEvetButton"));
    private SelenideElement page = $("#windowCevapEvrakDialog");

    public EditorTab editorTab() {
        return new EditorTab(page);
    }


    @Step("Sayfayı kapat")
    public CevapYazPage closePage(boolean save) {
        pageHeader().closePage();
        if (save)
            confirmDialog().confirmEvetTikla();
        else
            confirmDialog().confirmHayirTikla();
        return this;
    }

    public SelenideElement getPage() {
        return page;
    }

    public EvrakPageButtons pageButtons() {
        return new EvrakPageButtons(page);
    }

    public BilgilerTab bilgileriTab() {
        return new BilgilerTab(page);
//        return bilgilerTab;
    }

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(page);
    }


    /* @Step(tabName + " tabı açılır")
     public EditorTab openTab(boolean... clickIfOpen) {
         SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");
         if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
             tab.$("button").click();

         page.$("[id$=allPanels_content]").shouldBe(visible);
         page.$$("span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
 //        page.$$("#DOnayDivToolbar span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
         return this;
     }
 */
    @Step("Sayfa açıldı mı kontrolü")
    public CevapYazPage sayfaAcilmali() {
        pageTitle.shouldBe(visible);
        return this;
    }

    public CevapYazPage evrakTuruSec(String evrakTuru) {
        cmbEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    public CevapYazPage emirTalimatSec(String emirTalimat) {
        cmbEmirTalimat.selectOption(emirTalimat);
        return this;
    }

    public CevapYazPage evrakDiliSec(String evrakDili) {
        cmbEvrakDili.selectOption(evrakDili);
        return this;
    }

    public CevapYazPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.sendKeys(konuKodu);
        return this;
    }

    public CevapYazPage konuDoldur(String konu) {
        txtKonu.setValue(konu);
        return this;
    }

    public CevapYazPage kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler) {
        cmbKaldiralacakKlasorler.selectLov(kaldirilacakKlasorler);
        return this;
    }

    public CevapYazPage arsivKategorisiSec(String arsivKategorisi) {
        cmbArsivKategorisi.selectOption(arsivKategorisi);
        return this;
    }

    public CevapYazPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public CevapYazPage ivedilikSec(String ivedilik) {
        cmbIvedilik.selectOption(ivedilik);
        return this;
    }

    public CevapYazPage miatDoldur(String miat) {
        txtMiat.sendKeys(miat);
        return this;
    }

    public CevapYazPage geregiSecimTipiSec(String geregiSecimTipi) {
        cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
        return this;
    }

    public CevapYazPage geregiDoldur(String geregi) {
        comboLovGeregi.selectLov(geregi);
        return this;
    }

    public CevapYazPage bilgiSecimTipiSec(String bilgiSecimTipi) {
        cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
        return this;
    }

    public CevapYazPage bilgiDoldur(String bilgi) {
        txtBilgi.sendKeys(bilgi);
        return this;
    }
    @Step("Onay akışı alanını {onayAkisi} doldur")
    public CevapYazPage onayAkisiDoldur(String onayAkisi) {
        cmbOnayAkisi.selectLov(onayAkisi);
        return this;
    }

    public CevapYazPage dagitimiEkYapSec(boolean dagitimiEkYap) {
        chkDagitimiEkYap.setSelected(dagitimiEkYap);
        return this;
    }
    @Step("Ek metni alanını doldur: {ekMetin}")
    public CevapYazPage evrakEkTabViewEkMetniDoldur(String ekMetin) {
        txtEvrakEkTabViewEkMetni.sendKeys(ekMetin);
        return this;
    }

    public CevapYazPage evrakEkTabViewGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Dosya eklenir")
    public CevapYazPage evrakEkTabViewDosyaEkle(String filePath) {
        uploadFile(btnEvrakEkTabViewDosyaEkle,filePath);
        return this;
    }

    @Step("Ekle tıklanır")
    public CevapYazPage evrakEkTabViewEkle() {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    public CevapYazPage evrakEkTabViewTemizle() {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public CevapYazPage evrakEkTabViewFizikselEkMetniDoldur(String fizikselEkMetin) {
        txtEvrakEkTabViewFizikselEkMetni.sendKeys(fizikselEkMetin);
        return this;
    }

    public CevapYazPage evrakEkTabViewGuvenlikKoduAciklamaSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(gizlilikDerecesi);
        return this;
    }

    public CevapYazPage evrakEkTabViewFizikselEkEkle() {
        btnEvrakEkTabViewFizikselEkEkle.click();
        return this;
    }

    public CevapYazPage evrakEkTabViewEvrakBaslamaTarihDoldur(String baslamaTarih) {
        dateTxtEvrakEkTabViewEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public CevapYazPage evrakEkTabViewEvrakSonTarihDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage evrakEkTabViewEvrakinAranacagiYerSec(String evraginAranacagiYer) {
        cmbEvrakEkTabViewEvrakinAranacagiYer.selectOption(evraginAranacagiYer);
        return this;
    }

    public CevapYazPage evrakEkTabViewDokumanAra() {
        btnEvrakEkTabViewDokumanAra.click();
        return this;
    }

    public CevapYazPage evrakEkTabViewEvrakAramaDoldur(String evrakArama) {
        txtEvrakEkTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public CevapYazPage evrakEkTabViewArsivdeKayitliEvrakBaslamaTarihDoldur(String baslamaTarih) {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public CevapYazPage evrakEkTabViewArsivdeKayitliEvrakSonTarihDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage evrakEkTabViewKonuDoldur(String konu) {
        txtEvrakEkTabViewKonu.sendKeys(konu);
        return this;
    }

    public CevapYazPage evrakEkTabViewKullaniciDoldur(String kullanici) {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public CevapYazPage evrakEkTabViewEvrakSayisiDoldur(String evrakSayi) {
        txtEvrakEkTabViewEvrakSayisi.sendKeys(evrakSayi);
        return this;
    }

    public CevapYazPage evrakEkTabViewArsivdeKayitliDokumanAra() {
        btnEvrakEkTabViewArsivdeKayitliDokumanAra.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewIlgiMetniDoldur(String ilgiMetni) {
        txtIlgiIslemleriTabViewIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewDosyaEkle() {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewEkle() {
        btnIlgiIslemleriTabViewEkle.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewTemizle() {
        btnIlgiIslemleriTabViewTemizle.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewMetinEkleIlgiMetniDoldur(String ilgiMetni) {
        txtIlgiIslemleriTabViewMetinEkleIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewAciklamaEkle() {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihSonIdDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerIdSec(String aranacakYer) {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerId.selectOption(aranacakYer);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewEvrakAramaDoldur(String evrakArama) {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewDokumanAra() {
        btnIlgiIslemleriTabViewDokumanAra.click();
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasIdDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasId.sendKeys(baslamaTarihi);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonIdDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewKonuDoldur(String konu) {
        txtIlgiIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewKullaniciDoldur(String kullanici) {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewEvrakSayiDoldur(String evrakSayi) {
        txtIlgiIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public CevapYazPage ilgiIslemleriTabViewArsivdenIlgiEvrakAra() {
        btnIlgiIslemleriTabViewArsivdenIlgiEvrakAra.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewIlisikMetniDoldur(String ilisikMetni) {
        txtIlisikIslemleriTabViewIlisikMetni.sendKeys(ilisikMetni);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewDosyaEkle() {
        btnIlisikIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewEkle() {
        btnIlisikIslemleriTabViewEkle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewTemizle() {
        btnIlisikIslemleriTabViewTemizle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihSonIdDoldur(String sonTarih) {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewEvrakınAranacagiYerSec(String evrakınAranacagiYer) {
        cmbIlisikIslemleriTabViewEvrakınAranacagiYer.selectOption(evrakınAranacagiYer);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewEvrakAramaDoldur(String evrakArama) {
        txtIlisikIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewDokumanAra() {
        btnIlisikIslemleriTabViewDokumanAra.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewTercumeAciklamaDoldur(String tercumeAciklama) {
        txtIlisikIslemleriTabViewTercumeAciklama.sendKeys(tercumeAciklama);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewTercumeDosyaEkle() {
        btnIlisikIslemleriTabViewTercumeDosyaEkle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewTercumeEkle() {
        btnIlisikIslemleriTabViewTercumeEkle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewTercumeTemizle() {
        btnIlisikIslemleriTabViewTercumeTemizle.click();
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonIdDoldur(String sonTarih) {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewKonuDoldur(String konu) {
        txtIlisikIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewKullaniciDoldur(String kullanici) {
        txtIlisikIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewEvrakSayiDoldur(String evrakSayi) {
        txtIlisikIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public CevapYazPage ilisikIslemleriTabViewArsivdenIlisikEvrakAra() {
        btnIlisikIslemleriTabViewArsivdenIlisikEvrakAra.click();
        return this;
    }

    public CevapYazPage editorTabAc() {
        btnEditor.click();
        return this;
    }

    public CevapYazPage bilgilerTabAc() {
        btnBilgiler.click();
        return this;
    }

    @Step("Kaydet ve Onaya sun")
    public CevapYazPage kaydetVeOnayaSun() {
        btnKaydetVeOnayaSun.click();
        return this;
    }

    @Step("İmzala")
    public CevapYazPage imzalama() {
        btnImzala.click();
        return this;
    }


    @Step("İmzalama Ekranı kapat")
    public CevapYazPage imzalamaEkraniKapat() {
        btnImzalaEkraniKapat.shouldBe(visible);
        btnImzalaEkraniKapat.click();
        return this;
    }

    @Step("Kaydet ve Onaya sun")
    public CevapYazPage onayIslemiAciklamaDoldur(String aciklama) {
        txtOnayIslemiAciklama.shouldBe(Condition.visible);
        txtOnayIslemiAciklama.setValue(aciklama);
        return this;
    }

    @Step("Gönder")
    public CevapYazPage gonder() {
        btnGonder.click();
        return this;
    }

    @Step("Kişinin geregi alanında görüntülenme kontrolu")
    public CevapYazPage geregiKontrolu(String adSoyad) {

/*      System.out.println("Gelen geregi:     " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("Beklenen geregi:  " + adSoyad);
        Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);*/
        cmbGeregi.shouldBe(visible);
        cmbGeregi.getSelectedTitles().last().shouldHave(text(adSoyad));
        return this;
    }

    @Step("Konu kodu alanında görüntülenme kontrolu")
    public CevapYazPage konuAlaniKontrolu(String konuKodu) {

        /*System.out.println("Gelen konuKodu:     " + cmbKonuKodu.lastSelectedLovTitleText());
        System.out.println("Beklenen konuKodu:  " + konu);*/
        /*Assert.assertEquals(cmbKonuKodu.lastSelectedLovTitleText().contains(konu), true);*/
        cmbKonuKodu.getSelectedTitles().last().shouldHave(text(konuKodu));
        return this;
    }

    @Step("Editörde ilgi satırının, seçilen evrakın tarih ve sayısı ile geldiği kontrolu")
    public CevapYazPage editorSayiTarihKontrolu(String sayi, String tarih) {

        //Tc373 Tüzelkişi'nin 24.12.2017 tarihli ve 1367405182-1012 sayılı yazısı.
        String getIlgi = editorIlgiKismi.shouldHave(Condition.visible).getText();

        System.out.println("Gelen Ilgi Alanı:   " + getIlgi);
        System.out.println("Girilen Ilgi Alanı: " + sayi + " " + tarih);
        Assert.assertEquals(getIlgi.contains(sayi), true);
        Assert.assertEquals(getIlgi.contains(tarih), true);
        return this;
    }


    @Step("Popup İşlem Kayıt Uyarı:  \"{secim}\"")
    public CevapYazPage evrakKayitUyariPopup(String secim) {

        switch (secim) {
            case "Evet":
                btnEvetPopup.click();
                break;
            case "Hayır":
                btnHayirPopup.click();
                break;
        }
        return this;

    }

    @Step("Kaydet butonuna tıkla")
    public CevapYazPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Evrak Kopyalanır")
    public CevapYazPage evrakKopyala(){
        btnEvrakKopyala.click();
        return this;
    }

    @Step("Evet tıklanır")
    public CevapYazPage evrakKopyalaEvet(){
        btnEvrakKopyalaEvet.filterBy(Condition.text("Evet")).first().click();
        return this;
    }

    @Step("Evrak kopyalama uyarısının geldiği görülür.")
    public CevapYazPage evrakKopyalaUyariGeldigiGorme(){
        boolean durum = $("[id='evrakCopyConfirmForm']").shouldBe(visible).exists()==true;
        Assert.assertEquals(durum,true);
        return this;
    }

    @Step("Evet tıklanır")
    public CevapYazPage evrakKopyalaUyariEvet(){
        btnEvrakKopyalaUyariEvet.click();
        return this;
    }

    @Step("Kayıt Popup alanında Evet butonuna tıkla.")
    public CevapYazPage evrakKayitPopupEvet() {
        $$(By.id("kaydetConfirmForm:kaydetEvetButton")).last().click();
        return this;
    }
}
