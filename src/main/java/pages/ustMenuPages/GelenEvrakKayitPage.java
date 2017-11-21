package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvrakKayitPage extends BaseLibrary {

    SelenideElement pageTitle = $(By.cssSelector("#baseLayoutCenter .ui-dialog-title"));

    // Evrak Bilgileri Sekmesinde bulunanlar
    SelenideElement btnUstYaziEkle = $(By.xpath("//input[@id='evrakBilgileriForm:ustYaziForm:ustYaziUpload_input']"));
    SelenideElement txtEvrakBilgileriListKonuKodu = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='evrakDili']");
    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");


    // SelenideElement cmbEvrakBilgileriListKisiKurum = $(By.id("evrakBilgileriForm:evrakBilgileriList:9:kisiKurum"));
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    BelgenetElement txtEvrakBilgileriListGeldigiKurum = comboLov(By.id("evrakBilgileriForm:evrakBilgileriList:9:geldigiGercekKisiLov:LovText"));

    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSol = $("[id$='evrakSayiTextAreaSol'");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $(By.id("evrakBilgileriForm:evrakBilgileriList:14:miatCalendar_input"));
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("evrakBilgileriForm:evrakBilgileriList:15:j_idt4318"));
    SelenideElement cmbEvrakBilgileriListOzelKategori = $(By.id("evrakBilgileriForm:evrakBilgileriList:17:j_idt4499"));
    SelenideElement dateTxtEvrakBilgileriListPostalanmaTarihi = $(By.id("evrakBilgileriForm:evrakBilgileriList:18:postalanmaTarihi_input"));
    BelgenetElement comboKonuKodu = comboLov("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
    BelgenetElement comnoGeldigiKurum = comboLov("[id$='geldigiKurumLov:LovText']");

    // Evrak Ekleri sekmesinde bulunanlar
    // Dosya ekle alt sekmesinde bulunanlar

    SelenideElement btnEvrakEkleri = $(By.id("evrakBilgileriForm:evrakEkleriListesiPanel_toggler"));
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaTemizleButton"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaAciklama"));
    SelenideElement btvEvrakEkTabViewDosyaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:fileUploadButton_input"));

    //Fiziksel Ek Ekle alt sekmesinde bulunanlar
    SelenideElement btnFizikselEkEkle = $("a[href='#evrakBilgileriForm:evrakEkTabView:aciklamaEkleTab']");
    SelenideElement txtEvrakFizikselEkTabViewEkMetni = $(By.id("evrakBilgileriForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement cmbEvrakEkTabViewGuvenlikKoduAciklama = $(By.id("evrakBilgileriForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEvrakFizikselEkTabViewAciklamaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:aciklamaEkleButton"));

    //Sistemde Kayitli Evrak Ekle alt sekmesinde bulunanlar
    SelenideElement dateTxtEvrakEkTabViewEkIslemleriEvrakTarihBas = $(By.id("evrakBilgileriForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewEkIslemleriEvrakTarihSon = $(By.id("evrakBilgileriForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbEvrakEkTabViewekIslemleriEvrakAramaAranacakYer = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement txtEvrakEkTabViewevrakArama = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:evrakAramaText']"));

    //Arşivde Kayıtlı Evrak Ekle alt sekmesinde bulunanlar
    SelenideElement dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihBas = $(By.id("evrakBilgileriForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihSon = $(By.id("evrakBilgileriForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
    SelenideElement txtEvrakEkTabViewArsivdenEvrakAraKonu = $(By.id("evrakBilgileriForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
    SelenideElement txtEvrakEkTabViewKullanici = $(By.id("evrakBilgileriForm:evrakEkTabView:kisiyeLov_id:LovText"));
    SelenideElement txtEvrakEkTabViewArsivdenEvrakAraSayi = $(By.id("evrakBilgileriForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));

    // Havale işlemleri sekmesinde bulunanlar
    SelenideElement txtDagitimBilgileriBirim = $(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement txtDagitimBilgileriKisi = $(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtDagitimBilgileriKullaniciListesi = $(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement btnDagitimBilgileriOnaylayacakKisi = $(By.id("evrakBilgileriForm:onaylayacakKisiLov:treeButton"));
    SelenideElement txtDagitimBilgileriAciklama = $(By.id("evrakBilgileriForm:havaleAciklama"));
    SelenideElement btnDagitimBilgileriDosyaEkle = $(By.id("evrakBilgileriForm:fileUploadHavaleEk_input"));
    SelenideElement txtDagitimBilgileriIslemSuresi = $(By.id("evrakBilgileriForm:islemSuresiTarih_input"));
    SelenideElement chkDagitimBilgileriEvragiOnayliKapat = $(By.id("evrakBilgileriForm:j_idt5629_input"));
    SelenideElement btnDagitimBilgileriEvragiKapatacakKisi = $(By.id("evrakBilgileriForm:evrakiKapatacakKisiLov:treeButton"));//todo:Evrakı Onaylı Kapat secili olmadan çıkmıyor
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    BelgenetElement cmbDagitimBilgileriKisi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement cmbDagitimBilgileriKullaniciListesi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));

    //İlgi Bilgileri sekmesinde bulunanlar
    //Dosya Ekle alt sekmesinde bulunanlar
    SelenideElement txtIlgiIslemleriTabViewDosyaAciklama = $(By.xpath("//*[@id='evrakBilgileriForm:ilgiIslemleriTabView:dosyaAciklama']"));
    SelenideElement btnIlgiIslemleriTabViewDosyaEkle = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:dosyaEkleButton"));
    SelenideElement btnIlgiIslemleriTabViewIlgiDosyaTemizle = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:ilgiDosyaTemizleButton"));

    //Metin Ekle alt sekmesinde bulunanlar
    SelenideElement txtIlgiIslemleriTabViewAciklama = $(By.xpath("//*[@id='evrakBilgileriForm:ilgiIslemleriTabView:aciklamaTextArea']"));
    SelenideElement btnIlgiIslemleriTabViewAciklamaEkle = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:aciklamaEkleButton"));

    //Sistemde Kayitli Evrak Ekle alt sekmesinde bulunanlar
    SelenideElement dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBas = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
    SelenideElement datetxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSon = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYer = $(By.xpath("//*[@id='evrakBilgileriForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement txtIlgiIslemleriTabViewEvrakArama = $(By.xpath("//*[@id='evrakBilgileriForm:ilgiIslemleriTabView:evrakAramaText']"));
    //Arşivde Kayıtlı Evrak Ekle
    SelenideElement dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBas = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihBasId_input"));
    SelenideElement dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSon = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihSonId_input"));
    SelenideElement txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraKonu = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraKonuInputTextId"));
    SelenideElement txtIlgiIslemleriTabViewKullanici = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraSayi = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));

    SelenideElement btnKaydet = $("[id='buttonPanelForm:kaydetButton']");
    SelenideElement popUphavaleYeriSecmediniz = $("[id='havaleYeriSecmedinizConfirmDialog'");
    SelenideElement btnHavaleYeriSecmedinizEvet = $("[id='evetButtonBos']");
    SelenideElement ustYaziveHavaleYeriYokpopUp = $("[id='ustYaziveHavaleYeriYokConfirmDialog']");
    SelenideElement ustYaziYokEvet = $("[id='evetDugmesi']");
    SelenideElement ustYaziYokpopUp = $("[id='ustYaziYokConfirmDialog']");
    SelenideElement popUpEvet = $("[id='evetDugmesiUstYaziHavaleYer']");
    SelenideElement mukerrerPopUpEvet = $("[id='evetButtonBenzerKaydet']");
    SelenideElement mukerrerPopUp = $("[id='benzerEvrakKayitConfirmDialog']");
    SelenideElement basariliPopUpKapat = $("[id='evrakKaydetBasariliDialogForm:vazgecButton']");
    SelenideElement basariliPopUp = $("[id='evrakKaydetBasariliDialog']");


    SelenideElement btnGeldigiKisiEkle = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='gercekKisiEkle']");
    SelenideElement txtTCKN = $(By.id("gercekKisiHizliKayitDialogForm:tcKimlikNoInput"));
    SelenideElement btnTCKNAra = $(By.id("gercekKisiHizliKayitDialogForm:kpsTcKimlikNoSorgulaButtonHizliKayit"));
    SelenideElement btnKaydetIletisimBilgisi = $(By.id("gercekKisiHizliKayitDialogForm:saveGercekKisiHizliKayitButton"));
    SelenideElement txtAd = $(By.id("tgercekKisiHizliKayitDialogForm:adInputG"));
    SelenideElement txtSoyad = $(By.id("gercekKisiHizliKayitDialogForm:soyadInput"));
    SelenideElement mesaj = $("[#evrakKaydetBasariliDialog .ui-dialog-content]");

    public GelenEvrakKayitPage openPage() {
        new UstMenu().ustMenu("Gelen Evrak Kayıt");
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriEkBilgiFizikselEkEkle() throws InterruptedException {
        btnFizikselEkEkle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriEkBilgiFiltreAc() throws InterruptedException {
        btnEvrakEkleri.click();
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriUstYaziEkle(String path) throws InterruptedException {
        ustYaziUploadFile(path);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKonuKoduDoldur(String konuKodu) throws InterruptedException {
        comboKonuKodu.selectComboLov(konuKodu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKonuDoldur(String konu) {
        txtEvrakBilgileriListKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakTuruSec(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.selectOptionByValue(evrakTuru);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakDiliSec(String evrakDili) {
        cmbEvrakBilgileriListEvrakDili.selectOptionByValue(evrakDili);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakTarihiDoldur(String evrakTarihi) {
        dateTxtEvrakBilgileriListEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOptionByValue(gizlilikDerecesi);
        return this;
    }

    @Step("Kişi kurum seç")
    public GelenEvrakKayitPage evrakBilgileriListKisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
        return this;
    }


    @Step("Kişi kurum doldur")
    public GelenEvrakKayitPage evrakBilgileriListGeldigiKurumDoldur(String geldigiKurum) {

        txtEvrakBilgileriListGeldigiKurum.selectComboLov(geldigiKurum);
        //shouldHave(Condition.text(geregi));

        System.out.println("title: " + txtEvrakBilgileriListGeldigiKurum.lastSelectedLovTitleText());
        System.out.println("detail: " + txtEvrakBilgileriListGeldigiKurum.lastSelectedLovDetailText());
        return this;
    }

    //Lütfen metodları commentlemeyelim. Başka testler kullanıyor olabilir.
    public GelenEvrakKayitPage evrakBilgileriListGeldigiKurumDoldurLovText(String geldigiKurum) throws InterruptedException {
        comnoGeldigiKurum.selectComboLov(geldigiKurum);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakSayiSolDoldur(String evrakSayiSol) {
        txtEvrakBilgileriListEvrakSayiTextAreaSol.sendKeys(evrakSayiSol);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakSayiSagDoldur(String evrakSayiSag) {
        txtEvrakBilgileriListEvrakSayiTextAreaSag.sendKeys(evrakSayiSag);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakGelisTipiSec(String evrakGelisTipi) {
        cmbEvrakBilgileriListEvrakGelisTipi.selectOptionByValue(evrakGelisTipi);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListIvedilikSec(String ivedilik) {
        cmbEvrakBilgileriListIvedilik.selectOptionByValue(ivedilik);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListMiatDoldur(String miat) {
        txtEvrakBilgileriListMiat.sendKeys(miat);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListAciklamaDoldur(String evrakBilgileriAciklama) {
        txtEvrakBilgileriListAciklama.sendKeys(evrakBilgileriAciklama);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListOzelKategoriSec(String ozelKategori) {
        cmbEvrakBilgileriListOzelKategori.selectOption(ozelKategori);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListPostalanmaTarihiDoldur(String postalanmaTarihi) {
        dateTxtEvrakBilgileriListPostalanmaTarihi.sendKeys(postalanmaTarihi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriBirimDoldur(String birim) {
//        txtDagitimBilgileriBirim.sendKeys(birim);
        cmbHavaleIslemleriBirim.selectComboLov(birim);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKisiDoldur(String kisi) {
        txtDagitimBilgileriKisi.sendKeys(kisi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKullaniciListesiDoldur(String kullaniciListesi) {
//        txtDagitimBilgileriKullaniciListesi.sendKeys(kullaniciListesi);
        cmbDagitimBilgileriKullaniciListesi.selectComboLov(kullaniciListesi);
        return this;

    }

    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisi() {
        btnDagitimBilgileriOnaylayacakKisi.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriAciklamaDoldur(String aciklama) {
        txtDagitimBilgileriAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriDosyaEkle() {
        btnDagitimBilgileriDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriIslemSuresiDoldur(String islemSuresi) {
        txtDagitimBilgileriIslemSuresi.sendKeys(islemSuresi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriEvragiKapatacakKisi() {
        btnDagitimBilgileriEvragiKapatacakKisi.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkle() {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewTemizle() {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvrakKayitPage evrakFizikselEkTabViewEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewFizikselEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewDosyaEkle() {
        btvEvrakEkTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriEvragiOnayliKapatSec(boolean check) {
        chkDagitimBilgileriEvragiOnayliKapat.setSelected(check);
        return this;
    }

//    public GelenEvrakKayitPage evrakEkTabViewAciklamaDoldur(String aciklama)  {
//        txtEvrakEkTabViewAciklama.sendKeys(aciklama);
//        return this;
//    }

    public GelenEvrakKayitPage evrakEkTabViewGuvenlikKoduAciklamaSec(String guvenlikKoduAciklama) {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(guvenlikKoduAciklama);
        return this;
    }

    public GelenEvrakKayitPage evrakFizikselEkTabViewAciklamaEkle() {
        btnEvrakFizikselEkTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkIslemleriEvrakTarihBasDoldur(String baslamaTarihi) {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkIslemleriEvrakTarihSonDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewekIslemleriEvrakAramaAranacakYerSec(String aranacakYer) {
        cmbEvrakEkTabViewekIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewevrakAramaDoldur(String arama) {
        txtEvrakEkTabViewevrakArama.sendKeys(arama);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraEkEkleTarihBasDoldur(String baslamaTarihi) {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraEkEkleTarihSonDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraKonuDoldur(String konu) {
        txtEvrakEkTabViewArsivdenEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewKullaniciDoldur(String kullanici) {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraSayiDoldur(String evrakSayi) {
        txtEvrakEkTabViewArsivdenEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewDosyaAciklamaDoldur(String aciklama) {
        txtIlgiIslemleriTabViewDosyaAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewDosyaEkle() {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiDosyaTemizle() {
        btnIlgiIslemleriTabViewIlgiDosyaTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewAciklamaEkle() {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihBasDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihSonDoldur(String sonTarih) {
        datetxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerSec(String aranacakYer) {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewEvrakAramaDoldur(String arama) {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(arama);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBasDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSonDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenIlgiEvrakAraKonuDoldur(String konu) {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewKullaniciDoldur(String kullanici) {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenIlgiEvrakAraSayiDoldur(String evrakSayi) {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewAciklamaDoldur(String aciklama) {
        txtIlgiIslemleriTabViewAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage kaydet() {
        btnKaydet.click();
        return this;
    }

    public String popUps() {
//        popUp.shouldHave(Condition.visible);  pop up kontrolu
        String text;

        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            popUpEvet.click();
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            btnHavaleYeriSecmedinizEvet.click();
        }
        if (ustYaziYokpopUp.isDisplayed()) {
            ustYaziYokEvet.click();
        }
        if (mukerrerPopUp.isDisplayed()) {
            mukerrerPopUpEvet.click();
        }
        basariliPopUp.shouldBe(Condition.visible);
            String x = WebDriverRunner.getWebDriver()
                    .findElement(By.id("evrakKaydetBasariliDialog")).getText();
            Pattern y = Pattern.compile("\\d+");
            Matcher m = y.matcher(x);
            m.find();
            String evrakNo= m.group();
            System.out.println(evrakNo);
            basariliPopUpKapat.click();

        return evrakNo;
    }

    @Step("Geldiği Kişiyi ekle")
    public GelenEvrakKayitPage evrakBilgileriGeldigiKisiEkle() {
        executeJavaScript("arguments[0].click();",
                btnGeldigiKisiEkle);
        return this;
    }

    @Step("TC kimlik No ekle")
    public GelenEvrakKayitPage IletisimBilgisiTCKNEkle(String TCKN) {
//        String mernisNo = createMernisTCNO();
        txtTCKN.clear();
        txtTCKN.sendKeys(TCKN);
        return this;
    }

    @Step("TC kimlik No ara")
    public GelenEvrakKayitPage IletisimBilgisiTCKNAra() {
        executeJavaScript("arguments[0].click();",
                btnTCKNAra);
        return this;
    }

    @Step("Ad doldur")
    public GelenEvrakKayitPage IletisimBilgisiAdDoldur(String ad) {
        txtAd.sendKeys(ad);
        return this;
    }

    @Step("Soyad doldur")
    public GelenEvrakKayitPage IletisimBilgisiSoyadDoldur(String soyad) {
        txtSoyad.sendKeys(soyad);
        return this;
    }

    @Step("Kaydet")
    public GelenEvrakKayitPage IletisimBilgisikaydet() {
        btnKaydetIletisimBilgisi.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme")
    public GelenEvrakKayitPage evrakBilgileriDosyaEkleme(String pathToFile) {
        WebDriverRunner.getWebDriver()
                .findElement(By.xpath("//input[@id='evrakBilgileriForm:evrakEkTabView:fileUploadButton_input']"))
                .sendKeys(pathToFile);
        return this;
    }

    @Step("EkBilgiler dosya ekleme açıklama alanı doldur")
    public GelenEvrakKayitPage evrakBilgileriDosyaEklemeAciklamaDoldur(String aciklama) {
        txtEvrakEkTabViewEkMetni.sendKeys(aciklama);
        return this;
    }
}
