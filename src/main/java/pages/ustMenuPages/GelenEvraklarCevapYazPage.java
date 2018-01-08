package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvraklarCevapYazPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'Cevap Yaz' and @class = 'ui-dialog-title']"));

    //Bilgileri sekmesinde bulunanlar
    SelenideElement cmbEvrakTuru = $(By.id("windowCevapEvrakForm:evrakBilgileriList:1:evrakTuruCombo"));
    SelenideElement cmbEmirTalimat = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:2:emirTalimatPanelGrid']/tbody/tr/td[3]/select"));
    SelenideElement cmbEvrakDili = $(By.id("windowCevapEvrakForm:evrakBilgileriList:3:evrakDili"));
    SelenideElement txtKonuKodu = $(By.id("windowCevapEvrakForm:evrakBilgileriList:4:konuKoduLov:LovText"));
    SelenideElement txtKonu = $(By.id("windowCevapEvrakForm:evrakBilgileriList:5:konuTextArea"));
    SelenideElement cmbArsivKategorisi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:7:j_idt35880"));
    SelenideElement cmbGizlilikDerecesi = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:9:guvenlikKodu']"));
    SelenideElement cmbIvedilik = $(By.id("windowCevapEvrakForm:evrakBilgileriList:10:ivedilik"));
    SelenideElement txtMiat = $(By.id("windowCevapEvrakForm:evrakBilgileriList:11:miatCalendar_input"));
    SelenideElement cmbGeregiSecimTipi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:12:j_idt35921"));
    SelenideElement txtGeregi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:12:geregiLov:LovText"));
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

    @Step("Sayfa açıldı mı kontrolü")
    public GelenEvraklarCevapYazPage sayfaAcilmali() {
        pageTitle.shouldBe(visible);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakTuruSec(String evrakTuru) {
        cmbEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    public GelenEvraklarCevapYazPage emirTalimatSec(String emirTalimat) {
        cmbEmirTalimat.selectOption(emirTalimat);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakDiliSec(String evrakDili) {
        cmbEvrakDili.selectOption(evrakDili);
        return this;
    }

    public GelenEvraklarCevapYazPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.sendKeys(konuKodu);
        return this;
    }

    public GelenEvraklarCevapYazPage konuDoldur(String konu) {
        txtKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler) {
        cmbKaldiralacakKlasorler.selectLov(kaldirilacakKlasorler);
        return this;
    }

    public GelenEvraklarCevapYazPage arsivKategorisiSec(String arsivKategorisi) {
        cmbArsivKategorisi.selectOption(arsivKategorisi);
        return this;
    }

    public GelenEvraklarCevapYazPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage ivedilikSec(String ivedilik) {
        cmbIvedilik.selectOption(ivedilik);
        return this;
    }

    public GelenEvraklarCevapYazPage miatDoldur(String miat) {
        txtMiat.sendKeys(miat);
        return this;
    }

    public GelenEvraklarCevapYazPage geregiSecimTipiSec(String geregiSecimTipi) {
        cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
        return this;
    }

    public GelenEvraklarCevapYazPage geregiDoldur(String geregi) {
        txtGeregi.sendKeys(geregi);
        return this;
    }

    public GelenEvraklarCevapYazPage bilgiSecimTipiSec(String bilgiSecimTipi) {
        cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
        return this;
    }

    public GelenEvraklarCevapYazPage bilgiDoldur(String bilgi) {
        txtBilgi.sendKeys(bilgi);
        return this;
    }

    public GelenEvraklarCevapYazPage onayAkisiDoldur(String onayAkisi) {
        cmbOnayAkisi.selectLov(onayAkisi);
        return this;
    }

    public GelenEvraklarCevapYazPage dagitimiEkYapSec(boolean dagitimiEkYap) {
        chkDagitimiEkYap.setSelected(dagitimiEkYap);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEkMetniDoldur(String ekMetin) {
        txtEvrakEkTabViewEkMetni.sendKeys(ekMetin);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewDosyaEkle() {
        btnEvrakEkTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEkle() {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewTemizle() {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewFizikselEkMetniDoldur(String fizikselEkMetin) {
        txtEvrakEkTabViewFizikselEkMetni.sendKeys(fizikselEkMetin);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewGuvenlikKoduAciklamaSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewFizikselEkEkle() {
        btnEvrakEkTabViewFizikselEkEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakBaslamaTarihDoldur(String baslamaTarih) {
        dateTxtEvrakEkTabViewEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakSonTarihDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakinAranacagiYerSec(String evraginAranacagiYer) {
        cmbEvrakEkTabViewEvrakinAranacagiYer.selectOption(evraginAranacagiYer);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewDokumanAra() {
        btnEvrakEkTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakAramaDoldur(String evrakArama) {
        txtEvrakEkTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliEvrakBaslamaTarihDoldur(String baslamaTarih) {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliEvrakSonTarihDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewKonuDoldur(String konu) {
        txtEvrakEkTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewKullaniciDoldur(String kullanici) {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakSayisiDoldur(String evrakSayi) {
        txtEvrakEkTabViewEvrakSayisi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliDokumanAra() {
        btnEvrakEkTabViewArsivdeKayitliDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiMetniDoldur(String ilgiMetni) {
        txtIlgiIslemleriTabViewIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewDosyaEkle() {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEkle() {
        btnIlgiIslemleriTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewTemizle() {
        btnIlgiIslemleriTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewMetinEkleIlgiMetniDoldur(String ilgiMetni) {
        txtIlgiIslemleriTabViewMetinEkleIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewAciklamaEkle() {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihSonIdDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerIdSec(String aranacakYer) {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerId.selectOption(aranacakYer);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEvrakAramaDoldur(String evrakArama) {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewDokumanAra() {
        btnIlgiIslemleriTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasIdDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasId.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonIdDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewKonuDoldur(String konu) {
        txtIlgiIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewKullaniciDoldur(String kullanici) {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEvrakSayiDoldur(String evrakSayi) {
        txtIlgiIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewArsivdenIlgiEvrakAra() {
        btnIlgiIslemleriTabViewArsivdenIlgiEvrakAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIlisikMetniDoldur(String ilisikMetni) {
        txtIlisikIslemleriTabViewIlisikMetni.sendKeys(ilisikMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewDosyaEkle() {
        btnIlisikIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEkle() {
        btnIlisikIslemleriTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTemizle() {
        btnIlisikIslemleriTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihSonIdDoldur(String sonTarih) {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakınAranacagiYerSec(String evrakınAranacagiYer) {
        cmbIlisikIslemleriTabViewEvrakınAranacagiYer.selectOption(evrakınAranacagiYer);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakAramaDoldur(String evrakArama) {
        txtIlisikIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewDokumanAra() {
        btnIlisikIslemleriTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeAciklamaDoldur(String tercumeAciklama) {
        txtIlisikIslemleriTabViewTercumeAciklama.sendKeys(tercumeAciklama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeDosyaEkle() {
        btnIlisikIslemleriTabViewTercumeDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeEkle() {
        btnIlisikIslemleriTabViewTercumeEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeTemizle() {
        btnIlisikIslemleriTabViewTercumeTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasIdDoldur(String baslamaTarih) {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonIdDoldur(String sonTarih) {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewKonuDoldur(String konu) {
        txtIlisikIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewKullaniciDoldur(String kullanici) {
        txtIlisikIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakSayiDoldur(String evrakSayi) {
        txtIlisikIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenIlisikEvrakAra() {
        btnIlisikIslemleriTabViewArsivdenIlisikEvrakAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage editorTabAc() {
        btnEditor.click();
        return this;
    }

    public GelenEvraklarCevapYazPage bilgilerTabAc() {
        btnBilgiler.click();
        return this;
    }

    @Step("Kaydet ve Onaya sun")
    public GelenEvraklarCevapYazPage kaydetVeOnayaSun() {
        btnKaydetVeOnayaSun.click();
        return this;
    }

    @Step("İmzala")
    public GelenEvraklarCevapYazPage imzalama() {
        btnImzala.click();
        return this;
    }


    @Step("İmzalama Ekranı kapat")
    public GelenEvraklarCevapYazPage imzalamaEkraniKapat() {
        btnImzalaEkraniKapat.shouldBe(visible);
        btnImzalaEkraniKapat.click();
        return this;
    }

    @Step("Kaydet ve Onaya sun")
    public GelenEvraklarCevapYazPage onayIslemiAciklamaDoldur(String aciklama) {
        txtOnayIslemiAciklama.shouldBe(Condition.visible);
        txtOnayIslemiAciklama.setValue(aciklama);
        return this;
    }

    @Step("Gönder")
    public GelenEvraklarCevapYazPage gonder() {
        btnGonder.click();
        return this;
    }

    @Step("Kişinin geregi alanında görüntülenme kontrolu")
    public GelenEvraklarCevapYazPage geregiKontrolu(String adSoyad) {

/*      System.out.println("Gelen geregi:     " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("Beklenen geregi:  " + adSoyad);
        Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);*/
        cmbGeregi.shouldBe(visible);
        cmbGeregi.getSelectedTitles().last().shouldHave(text(adSoyad));
        return this;
    }

    @Step("Konu kodu alanında görüntülenme kontrolu")
    public GelenEvraklarCevapYazPage konuAlaniKontrolu(String konuKodu) {

        /*System.out.println("Gelen konuKodu:     " + cmbKonuKodu.lastSelectedLovTitleText());
        System.out.println("Beklenen konuKodu:  " + konu);*/
        /*Assert.assertEquals(cmbKonuKodu.lastSelectedLovTitleText().contains(konu), true);*/
        cmbKonuKodu.getSelectedTitles().last().shouldHave(text(konuKodu));
        return this;
    }

    @Step("Editörde ilgi satırının, seçilen evrakın tarih ve sayısı ile geldiği kontrolu")
    public GelenEvraklarCevapYazPage editorSayiTarihKontrolu(String sayi, String tarih) {

        //Tc373 Tüzelkişi'nin 24.12.2017 tarihli ve 1367405182-1012 sayılı yazısı.
        String getIlgi = editorIlgiKismi.shouldHave(Condition.visible).getText();

        System.out.println("Gelen Ilgi Alanı:   " + getIlgi);
        System.out.println("Girilen Ilgi Alanı: " + sayi + " " + tarih);
        Assert.assertEquals(getIlgi.contains(sayi), true);
        Assert.assertEquals(getIlgi.contains(tarih), true);
        return this;
    }


    @Step("Popup İşlem Kayıt Uyarı:  \"{secim}\"")
    public GelenEvraklarCevapYazPage evrakKayitUyariPopup(String secim) {

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
}
