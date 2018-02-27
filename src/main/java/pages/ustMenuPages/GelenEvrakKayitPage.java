package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class GelenEvrakKayitPage extends MainPage {

    public SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSol = $("[id$='evrakSayiTextAreaSol']");
    //region Elements
    SelenideElement pageTitle = $(By.cssSelector("#baseLayoutCenter .ui-dialog-title"));
    // Evrak Bilgileri Sekmesinde bulunanlar
    SelenideElement btnUstYaziEkle = $(By.xpath("//span[text()='Üst Yazı Ekle']/../../label"));
    BelgenetElement txtEvrakBilgileriListKonuKodu = comboLov("[id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");
    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    //SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    //BelgenetElement cmbEvrakBilgileriListGeldigiKisi = comboLov(By.id("evrakBilgileriForm:evrakBilgileriList:9:geldigiGercekKisiLov:LovText"));
    BelgenetElement cmbGeldigiGercekKisi = comboLov("[id$='geldigiGercekKisiLov:LovText']");
    BelgenetElement cmbGeldigiKisi = comboLov("[id$='geldigiKisiLov:LovText']");
    BelgenetElement cmbGeldigiTuzelKisi = comboLov("[id$='geldigiTuzelKisiLov:LovText']");
    By cmbGeldiğiGercekKisiBy = By.cssSelector("[id$='geldigiGercekKisiLov:LovText']");
    By cmbGeldiğiTuzelKisiBy = By.cssSelector("[id$='geldigiTuzelKisiLov:LovText']");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $("[id$=miatCalendar_input]");
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("evrakBilgileriForm:evrakBilgileriList:15:j_idt4318"));
    SelenideElement cmbEvrakBilgileriListOzelKategori = $(By.id("evrakBilgileriForm:evrakBilgileriList:17:j_idt4499"));
    SelenideElement dateTxtEvrakBilgileriListPostalanmaTarihi = $(By.id("evrakBilgileriForm:evrakBilgileriList:18:postalanmaTarihi_input"));
    BelgenetElement comboKonuKodu = comboLov("[id$='konuKoduLov:LovText']");
    BelgenetElement comboGeldigiKurum = comboLov("[id$='geldigiKurumLov:LovText']");
    BelgenetElement cmbGeldigiBirim = comboLov("[id$='geldigiBirimLov:LovText']");
    By cmbGeldigiBirimBy = By.cssSelector("[id$='geldigiBirimLov:LovText']");

    SelenideElement txtKonuKoduTemizle = $("[id='evrakBilgileriForm:evrakBilgileriList:1:konuKoduLov:LovSecilen'] button");
    // Evrak Ekleri sekmesinde bulunanlar
    // Dosya ekle alt sekmesinde bulunanlar

    SelenideElement btnEvrakEkleri = $(By.id("evrakBilgileriForm:evrakEkleriListesiPanel_toggler"));
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaTemizleButton"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaAciklama"));
    SelenideElement btvEvrakEkTabViewDosyaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:fileUploadButton_input"));
    //    ElementsCollection tblDosyaEkle = $$("div[id$='evrakBilgileriForm:ekListesiDataTable'] tr[role=row]");
    ElementsCollection tblDosyaEkle = $$("div[id$='ekListesiDataTable'] tr[data-ri]");

    //Fiziksel Ek Ekle alt sekmesinde bulunanlar
    SelenideElement btnFizikselEkEkle = $("a[href='#evrakBilgileriForm:evrakEkTabView:aciklamaEkleTab']");
    SelenideElement txtEvrakFizikselEkTabViewEkMetni = $(By.id("evrakBilgileriForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement cmbEvrakEkTabViewGuvenlikKoduAciklama = $(By.id("evrakBilgileriForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEvrakFizikselEkTabViewAciklamaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:aciklamaEkleButton"));

    //Sistemde Kayitli Evrak Ekle alt sekmesinde bulunanlar
    SelenideElement btnSistemdeKayitliEvrakEkle = $("a[href='#evrakBilgileriForm:evrakEkTabView:sistemdeKayitliEvragiEkleTab']");
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
    ElementsCollection chkOtomatikHavale = $$("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class='ui-chkbox ui-widget'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    SelenideElement cmbPopupOtomatikHavale = $("[id$='havaleKuralSelect']");
    SelenideElement txtDagitimBilgileriBirim = $(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    BelgenetElement txtDagitimBilgileriKisiComboLov = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtDagitimBilgileriKisi = $(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtDagitimBilgileriKullaniciListesi = $(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement btnDagitimBilgileriOnaylayacakKisi = $(By.id("evrakBilgileriForm:onaylayacakKisiLov:treeButton"));
    SelenideElement txtDagitimBilgileriAciklama = $(By.id("evrakBilgileriForm:havaleAciklama"));
    SelenideElement btnDagitimBilgileriDosyaEkle = $(By.id("evrakBilgileriForm:fileUploadHavaleEk_input"));
    SelenideElement txtDagitimBilgileriIslemSuresi = $(By.id("evrakBilgileriForm:islemSuresiTarih_input"));
    SelenideElement chkDagitimBilgileriEvragiOnayliKapat = $(By.id("evrakBilgileriForm:j_idt5629_input"));
    SelenideElement btnDagitimBilgileriEvragiKapatacakKisi = $(By.id("evrakBilgileriForm:evrakiKapatacakKisiLov:treeButton"));//todo:Evrakı Onaylı Kapat secili olmadan çıkmıyor
    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));

    SelenideElement birimSeç = $("select[id^='evrakBilgileriForm:dagitimBilgileriBirimLov:LovSecilenTable']");
    BelgenetElement cmbHavaleIslemleriOnaylayacakKisi = comboLov(By.id("evrakBilgileriForm:onaylayacakKisiLov:LovText"));

    BelgenetElement cmbDagitimBilgileriKisi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement cmbDagitimBilgileriKullaniciListesi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection tblVekaletVerenAlan = $$("[id='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleIcin_data'] tr[role='row']");
    ElementsCollection tblVekaletAlanVeren = $$("tbody[id='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleOnaylayacakIcin_data'] tr[data-ri]");

    //İlgi Bilgileri sekmesinde bulunanlar
    //Dosya Ekle alt sekmesinde bulunanlar
    SelenideElement btnIlgiBilgileri = $(By.id("evrakBilgileriForm:ilgiBilgileriPanel_toggler"));
    SelenideElement ilgiBilgileriDosyaPath = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:fileUploadButtonB_input"));
    SelenideElement txtIlgiBilgiEkMetni = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:dosyaAciklama"));
    SelenideElement btnilgiBilgileriTabViewEkle = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:dosyaEkleButton"));
    ElementsCollection tblIlgiliBilgilerDosyaEkle = $$("div[id$='ilgiListesiDataTable'] tr[data-ri]");

    SelenideElement btnilgiBilgileriSistemdeKayitliEvrakEkle = $("a[href='#evrakBilgileriForm:ilgiIslemleriTabView:sistemdeKayitliEvragiEkleTab']");
    SelenideElement txtIlgiBilgileriEvrakArama = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:evrakAramaText"));
    SelenideElement ilgiBilgileriDokumanAraButton = $(By.id("evrakBilgileriForm:ilgiIslemleriTabView:dokumanAraButton"));
    SelenideElement ilgiBilgileriEkEkleButton1 = $("button[id^='evrakBilgileriForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable:0']");

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
    SelenideElement popUphavaleYeriSecmediniz = $(By.id("havaleYeriSecmedinizConfirmDialog"));
    SelenideElement popUphavaleOnayGonderilsinmi = $(By.id("havaleOnayinaGonderilsinmiConfirmDialog"));
    SelenideElement btnUstYaziveHavaleYeriSecmedinizEvet = $("[id='evetButtonBos']");
    SelenideElement btnUstYaziveHavaleYeriSecmedinizHayır = $(By.id("hayirButtonBos"));
    SelenideElement btnHavaleYeriSecmedinizHayır = $(By.id("hayirDugmesiUstYaziHavaleYer"));
    SelenideElement btnHavaleYeriSecmedinizEvet = $(By.id("evetButtonBos"));

    SelenideElement ustYaziveHavaleYeriYokpopUp = $("[id='ustYaziveHavaleYeriYokConfirmDialog']");
    SelenideElement ustYaziYokEvet = $("[id='evetDugmesi']");
    SelenideElement ustYaziYokpopUp = $("[id='ustYaziYokConfirmDialog'][class$='ui-overlay-visible']");
    SelenideElement popUpEvet = $("[id='evetDugmesiUstYaziHavaleYer']");
    SelenideElement popUpEvet2 = $("[id='evetButtonGonderilsinMi']");

    SelenideElement mukerrerPopUpEvet = $("[id='evetButtonBenzerKaydet']");
    SelenideElement mukerrerPopUp = $("[id='benzerEvrakKayitConfirmDialog'][class$='ui-overlay-visible']");
    SelenideElement btnUstYaziDegistirmeHayır = $(By.id("evrakBilgileriForm:ustyaziDegistirmeButton"));
    SelenideElement btnUstYaziDegistirEvet = $(By.id("evrakBilgileriForm:ustyaziDegistirButton"));
    SelenideElement popUpUstYaziDegistirilme = $(By.id("evrakBilgileriForm:ustyaziDegistirisilMiDialog"));
    SelenideElement basariliPopUpKapat = $("[id='evrakKaydetBasariliDialogForm:vazgecButton']");
    SelenideElement basariliPopUp = $("[id='evrakKaydetBasariliDialog'][class$='ui-overlay-visible']");

    ElementsCollection visibleEvrakBasarili = $$("[id='evrakKaydetBasariliDialog']");

    SelenideElement btnGeldigiKisiEkle = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='gercekKisiEkle']");
    SelenideElement txtTCKN = $(By.id("gercekKisiHizliKayitDialogForm:tcKimlikNoInput"));
    SelenideElement btnTCKNAra = $(By.id("gercekKisiHizliKayitDialogForm:kpsTcKimlikNoSorgulaButtonHizliKayit"));
    SelenideElement btnKaydetIletisimBilgisi = $(By.id("gercekKisiHizliKayitDialogForm:saveGercekKisiHizliKayitButton"));
    //    SelenideElement txtAd = $(By.id("tgercekKisiHizliKayitDialogForm:adInputG"));
    SelenideElement txtAd = $(By.id("gercekKisiHizliKayitDialogForm:adInputG"));

    SelenideElement txtSoyad = $(By.id("gercekKisiHizliKayitDialogForm:soyadInput"));
    SelenideElement mesaj = $("[#evrakKaydetBasariliDialog .ui-dialog-content]");

    SelenideElement lblDosyaAdi = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaAdi"));
    SelenideElement lblEklenenPdfUstYazi = $("[id='evrakBilgileriForm:eklendiYazisi'] label");
    SelenideElement lblEklenenMailUstYazi = $(By.xpath("//table[@id='evrakBilgileriForm:ustYaziAdim']/tbody/tr/td[3]/label"));

    SelenideElement btnBirim = $(By.id("evrakBilgileriForm:j_idt4283"));

    //Dosya ekleme path
//    By dosyaPath = By.xpath("//input[@id='evrakBilgileriForm:evrakEkTabView:fileUploadButton_input']");
    SelenideElement dosyaPath = $(By.xpath("//input[@id='evrakBilgileriForm:evrakEkTabView:fileUploadButton_input']"));
    SelenideElement ustYazi = $(By.xpath("//input[@class='ustYaziUploadClass']"));

//    Evrak Detayı sayfası objeleri

    SelenideElement btnevrakDetayiEvrakEkleri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:1:cmdbutton"));
    SelenideElement btnEvrakDetayiFizikselEkEkle = $("a[href='#inboxItemInfoForm:evrakEkTabView:aciklamaEkleTab']");
    SelenideElement txtEvrakDetayiAciklama = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement btnEvrakDetayiEkle = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaEkleButton"));
    SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
    SelenideElement txtEvrakDetayiEvrakNo = $(By.xpath("//table[@id='inboxItemInfoForm:evrakBilgileriList:0:evrakNoPanelGrid']/tbody/tr/td[3]"));
    SelenideElement popUpPdfDegisiklik = $(By.xpath("//div[@id='inboxItemInfoForm:ustyaziDegistirisilMiDialog']"));
    SelenideElement btnEvrakDetayiPdfDegisiklikKabul = $(By.id("inboxItemInfoForm:ustyaziDegistirButton"));
    SelenideElement btnEvrakDetayiKaydetUyarisi = $(By.id("kaydetConfirmForm:kaydetEvetButton"));

    //    SelenideElement btnTaramaHavuzundanEkle = $(By.id("evrakBilgileriForm:uploadFromTarananEvrakHavuzuGelenEvrak"));
    SelenideElement btnTarayicidanEkle = $(By.id("evrakBilgileriForm:dogrudanTaramaBaslat"));
    SelenideElement btnTaramaArayuzundenEkle = $(By.id("evrakBilgileriForm:taramaArayuzundenEkle"));
    SelenideElement btnTaramaServisindenEkle = $(By.id("evrakBilgileriForm:taramaServisEkle"));
    SelenideElement lblUstyaziGoster = $(By.id("evrakBilgileriForm:ustYaziGosterButton"));
    SelenideElement lblUstyaziGizle = $(By.id("evrakBilgileriForm:ustYaziGizleButton"));


    // Havale İşlemleri
    BelgenetElement txtHavaleIslemleriKisi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleIslemleriKullaniciListesi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtHavaleIslemleriBirim = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    //endregion
    SelenideElement btnSecilenGeldigiKurumKaldir = $("div[id$='geldigiKurumLov:LovSecilen'] button");

    //Tarama Havuzundan Ekle
    SelenideElement btnTaramaHavuzundanEkle = $x("//span[text()='Tarama Havuzundan Ekle']/../../button");
    SelenideElement chkTaramaHavuzuIlkEvrak = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:selectionId"));
    SelenideElement cmbTaramaHavuzuIlkEvrakTur = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:tarananTuruId"));
    SelenideElement btnTaramaHavuzuTamam = $(By.id("taramaHavuzuFormId:taramaHavuzuTamamButton"));


    //otomatik havale checkboxı
    SelenideElement otomatikHavaleCheckbox = $("[id='evrakBilgileriForm:havalePanel_content'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
    //BirimKontrol
    SelenideElement birimKontrol = $(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    //Kişi kontrolü
    SelenideElement kisiKontrol = $(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    //kullanıcı listesi kontrlu
    SelenideElement kullanıcıListeKontrol = $(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    //Açıklama alanı kontrolü
    SelenideElement aciklamaKontrol = $(By.id("evrakBilgileriForm:havaleAciklama"));
    //Dosya ekle kontrolü
    SelenideElement dosyaEkleKontrol = $(By.id("evrakBilgileriForm:fileUploadHavaleEk"));
    //işlem süresi alanını kontrolü
    SelenideElement islemSureKontrol = $(By.id("evrakBilgileriForm:islemSuresiTarih_input"));

    SelenideElement ekEkleButton1 = $(By.id("evrakBilgileriForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable:0:ekEkleButton1"));
    SelenideElement dokumanAraButton = $(By.id("evrakBilgileriForm:evrakEkTabView:dokumanAraButton"));
    SelenideElement islemKapat = $(By.id("kapatButton"));

    //Evrak Havale Islemleri Form
    SelenideElement btnHavaleIslemleri = $(By.id("evrakBilgileriForm:havalePanel_toggler"));

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    @Step("Gelen Evrak Kayıt sayfasını aç")
    public GelenEvrakKayitPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.GelenEvrakKayit);
        return this;
    }

    @Step("Otomatik havale seçilir")
    public GelenEvrakKayitPage otomatikHavaleSec() {
        if (chkOtomatikHavale.size() == 1) {
            chkOtomatikHavale.get(0).click();
            takeScreenshot();
        } else {
            $("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").click();
            //$("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class='ui-chkbox ui-widget'] input[type='checkbox']").setSelected(false);
            sleep(1000);
            takeScreenshot();
            chkOtomatikHavale.get(0).click();
        }
        return this;
    }

    @Step("Otomatik havale seç")
    public GelenEvrakKayitPage otomatikHavaleSec2() {
        $("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active']").click();
        clickJs($("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class='ui-chkbox-box ui-widget ui-corner-all ui-state-default"));
        return this;
    }

    @Step("Otomatik havale seçilir")
    public GelenEvrakKayitPage otomatikHavaleSec2(boolean durum) {
        clickJs($("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class^='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
        sleep(3000);
        clickJs($("[id='evrakBilgileriForm:havaleDagitimLovPanel'] [class^='ui-chkbox-box ui-widget ui-corner-all ui-state-default']"));
        return this;
    }

    @Step("Otomatik havalenin geldiği görünür :{otomatikHavale}")
    public GelenEvrakKayitPage otomatikHavaleGeldigiGorme(String otomatikHavale) {
        ElementsCollection lblOtomoatikHavale = $$("[id='evrakBilgileriForm:havalePanel'] label[class='columnLabelFixSmallWidth']");
        boolean durum = lblOtomoatikHavale.filterBy(Condition.text(otomatikHavale)).size() == 1;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Açılan popup da Otomatik havale seçilir: \"{otomatikHavale}\" ")
    public GelenEvrakKayitPage popupOtomatikHavaleSec(String otomatikHavale) {
        sleep(3000);
        cmbPopupOtomatikHavale.selectOption(otomatikHavale);
        takeScreenshot();
        $("[class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-shadow havaleKuralSecimiDialog ui-draggable ui-overlay-visible'] [class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" seç")
    public GelenEvrakKayitPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("Hava İşlemleri Kişi alanında \"{kisi}\" seçmeye dene")
    public GelenEvrakKayitPage havaleIslemleriKisiSecmeyeDene(String kisi) {
        txtHavaleIslemleriKisi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kişi alanını doldur")
    public GelenEvrakKayitPage havaleIslemleriKisiDoldur(String kisi, String birim) {
        txtHavaleIslemleriKisi.type(kisi).getDetailItems().filterBy(text(birim)).first().click();
        return this;
    }

    @Step("Kullanıcı Listesi alnında \"{kisi}\" seçmeye dene")
    public GelenEvrakKayitPage havaleIslemleriKullaniciListesiSecmeyeDene(String kisi) {
        txtHavaleIslemleriKullaniciListesi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
    /*    txtHavaleIslemleriKullaniciListesi.selectLov(kisi);
        txtHavaleIslemleriKullaniciListesi.selectLov(kisi);*/
        return this;
    }

    @Step("Kullanıcı Listesi alnında \"{kisi}\" seç")
    public GelenEvrakKayitPage havaleIslemleriKullaniciListesiDoldur(String kisi, String detay) {
        //txtHavaleIslemleriKullaniciListesi.selectLov(kisi);
        txtHavaleIslemleriKullaniciListesi.type(kisi).getDetailItems().filterBy(text(detay)).first().click();
        return this;
    }

    @Step("Fiziksel Ek ekle")
    public GelenEvrakKayitPage ekBilgiFizikselEkEkle() {
        clickJs(btnFizikselEkEkle);
        return this;
    }

    @Step("Sistemde Kayitli Evrakekle")
    public GelenEvrakKayitPage sistemdeKayitliEvrakEkle() {
        clickJs(btnSistemdeKayitliEvrakEkle);
        return this;
    }

    @Step("Ilgi Bilgileri Sistemde Kayitli Evrakekle")
    public GelenEvrakKayitPage ilgiBilgileriSistemdeKayitliEvrakEkle() {
        clickJs(btnilgiBilgileriSistemdeKayitliEvrakEkle);
        return this;
    }

    @Step("Evrak Ekleri Evrak Filtreleme")
    public GelenEvrakKayitPage ekBilgiFiltreAc() {
//        btnEvrakEkleri.click();
        clickJs(btnEvrakEkleri);
        return this;
    }

    @Step("Ilgi Bilgileri Evrak Filtreleme")
    public GelenEvrakKayitPage ilgiliBilgiFiltreAc() {
        clickJs(btnIlgiBilgileri);
        return this;
    }

    @Step("Üst yazi \"{path}\" ekle")
    public GelenEvrakKayitPage evrakBilgileriUstYaziEkle(String path) {
        uploadFile(ustYazi, path);
        return this;
    }

    @Step("Üst yazi \"{path}\" ekle")
    public GelenEvrakKayitPage ustYaziEkle(String path) {
        ustYazi.uploadFile(new File(path));
        return this;
    }

    @Step("Konu Kodu alanında \"{konuKodu}\" seç")
    public GelenEvrakKayitPage konuKoduDoldur(String konuKodu) {
        comboKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Konu alanına \"{konu}\" girilir")
    public GelenEvrakKayitPage konuDoldur(String konu) {
        $("[id$='konuTextArea']").setValue(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKonuDoldur(String konu) {
        txtEvrakBilgileriListKonu.sendKeys(konu);
        return this;
    }

    @Step("Evrak Türü alanında \"{evrakTuru}\" seç")
    public GelenEvrakKayitPage evrakTuruSec(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    @Step("Evrak Dili alanında \"{evrakDili}\" seç")
    public GelenEvrakKayitPage evrakDiliSec(String evrakDili) {
        cmbEvrakBilgileriListEvrakDili.selectOption(evrakDili);
        return this;
    }

    @Step("Evrak Tarihi alanını \"{evrakTarihi}\" doldur")
    public GelenEvrakKayitPage evrakTarihiDoldur(String evrakTarihi) {
        dateTxtEvrakBilgileriListEvrakTarihi.clear();
        dateTxtEvrakBilgileriListEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    @Step("Gizlilik Derecesi alanında \"{gizlilikDerecesi}\" seç")
    public GelenEvrakKayitPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Kişi kurum tipi alanında \"{kisiKurum}\" seç")
    public GelenEvrakKayitPage kisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOption(kisiKurum);
        return this;
    }

    @Step("Kişi kurum tipi alanında \"{kisiKurum}\" seç")
    public GelenEvrakKayitPage kisiKurumSecByText(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOption(kisiKurum);
        return this;
    }

    @Step("Geldiği gerçek kişi alanı doldur: {geldigiKisi} | {description} ")
    public GelenEvrakKayitPage geldigiGercekKisiDoldur(String geldigiKisi, String description) {

        cmbGeldigiGercekKisi.selectLov(geldigiKisi);

        /*System.out.println("title: " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeldigiGercekKisi.lastSelectedLovDetailText());*/

        return this;
    }

    @Step("Geldiği kullanıcı alanı doldur: {kullanici} | {description} ")
    public GelenEvrakKayitPage geldigiKullaniciDoldur(String kullanici, String description) {

        cmbGeldigiKisi.selectLov(kullanici);

        /*System.out.println("title: " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeldigiGercekKisi.lastSelectedLovDetailText());*/

        return this;
    }

    @Step("Seçilen gereği gerçek kişi sil")
    public GelenEvrakKayitPage secilenGeregiGercekKisiSil() {
        cmbGeldigiGercekKisi.clearLastSelectedItem();
        return this;
    }

    @Step("Seçilen gereği tüzel kişi sil")
    public GelenEvrakKayitPage secilenGeregiTuzelKisiSil() {
        cmbGeldigiTuzelKisi.clearLastSelectedItem();
        return this;
    }

    @Step("Geldiği tüzel kişi {description} doldur: | {geldigiTuzelKisi}")
    public GelenEvrakKayitPage geldigiTuzelKisiDoldur(String geldigiTuzelKisi, String description) {

        cmbGeldigiTuzelKisi.selectLov(geldigiTuzelKisi);

        /*System.out.println("title: " + cmbGeldigiTuzelKisi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeldigiTuzelKisi.lastSelectedLovDetailText());*/

        return this;
    }

    @Step("Gerçek kişinin geldiği alanında \" {kisi}\" görüntülenmeme kontrolu")
    public GelenEvrakKayitPage geldigiGercekKisiGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeldiğiGercekKisiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    @Step("Tüzel kişinin geldiği alanında \" {kisi}\" görüntülenmeme kontrolu")
    public GelenEvrakKayitPage geldigiTuzelKisiGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeldiğiTuzelKisiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    @Step("Tüzel kişinin geldiği alanında \" {kisi}\" görüntülenme kontrolu")
    public GelenEvrakKayitPage geldigiTuzelKisiGoruntulenmeKontrolu(String kisi) {

        cmbGeldigiTuzelKisi.selectLov(kisi);

        cmbGeldigiTuzelKisi.getSelectedItems().last().shouldHave(text(kisi));

        /*String t = cmbGeldigiTuzelKisi.getSelectedItems().last().text();
        String d = cmbGeldigiTuzelKisi.getSelectedDetails().last().text();
        if (t.contains(kisi)) {
            *//*System.out.println("Gelen title:     " + cmbGeldigiTuzelKisi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + kisi);*//*
         *//*Assert.assertEquals(cmbGeldigiTuzelKisi.lastSelectedLovTitleText().contains(kisi), true);*//*
            cmbGeldigiTuzelKisi.getSelectedItems().last().shouldHave(text(kisi));
        } else if (d.contains(kisi)) {
            *//*System.out.println("Gelen title:     " + cmbGeldigiTuzelKisi.lastSelectedLovDetailText());
            System.out.println("Beklenen title:  " + kisi);*//*
         *//*Assert.assertEquals(cmbGeldigiTuzelKisi.lastSelectedLovDetailText().contains(kisi), true);*//*
            cmbGeldigiTuzelKisi.getSelectedDetails().last().shouldHave(text(kisi));
        }*/

        return this;
    }

    @Step("Geldiği kişi alanında görüntülenmediği kontrolu")
    public GelenEvrakKayitPage geldigiKurumDegerGoruntulemeKontrolu(String kurumAdi, Boolean shoudlBeExist) {
        Assert.assertEquals(comboGeldigiKurum.isLovValueSelectable(kurumAdi), shoudlBeExist);
        return this;
    }

    @Step("Geldiği kişi alanında görüntülenme kontrolu")
    public GelenEvrakKayitPage gercekKisiGoruntulenmeKontrolu(String tckn, String adSoyad) {

        cmbGeldigiGercekKisi.selectLov(tckn);
        /*System.out.println("Gelen title:     " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);*/
        cmbGeldigiGercekKisi.getSelectedTitles().last().shouldHave(text(adSoyad));
//        Assert.assertEquals(cmbGeldigiGercekKisi.lastSelectedLovTitleText().contains(adSoyad), true);

        return this;
    }

    @Step("Geldiği kurum alanında \"{geldigiKurum}\" seçilir ")
    public GelenEvrakKayitPage geldigiKurumDoldurLovText(String geldigiKurum) {
        if (btnSecilenGeldigiKurumKaldir.isDisplayed())
            btnSecilenGeldigiKurumKaldir.click();
        comboGeldigiKurum
                .type(geldigiKurum)
                .getTitleItems()
                .first()
                .click();
        return this;
    }

    @Step("Evrak Sayı değer kontrolü: {evrakSayi} olmalı.")
    public GelenEvrakKayitPage solEvrakSayiKontrol(String evrakSayi) {
        txtEvrakBilgileriListEvrakSayiTextAreaSol.shouldHave(value(evrakSayi));
        return this;
    }

    @Step("Geldiği kurum alanında \"{geldigiKurum}\" seçilir ")
    public GelenEvrakKayitPage geldigiKurumDoldurLovText2(String geldigiKurum) {
        comboGeldigiKurum.selectLov(geldigiKurum);
        return this;
    }

    @Step("Evrak sayısı sol tarafına \"{evrakSayiSol}\" girilir ")
    public GelenEvrakKayitPage evrakSayiSolDoldur(String evrakSayiSol) {
        txtEvrakBilgileriListEvrakSayiTextAreaSol.sendKeys(evrakSayiSol);
        return this;
    }

    @Step("Evrak sayısı sağ tarafı doldurulur ")
    public GelenEvrakKayitPage evrakSayiSagDoldur() {
        String evrakSayiSag = createRandomNumber(5);
        txtEvrakBilgileriListEvrakSayiTextAreaSag.clear();
        txtEvrakBilgileriListEvrakSayiTextAreaSag.sendKeys(evrakSayiSag);
        return this;
    }

    @Step("Evrak sayısı sağ tarafına \"{sayi}\" girilir ")
    public GelenEvrakKayitPage evrakSayiSagDoldur(String sayi) {
        txtEvrakBilgileriListEvrakSayiTextAreaSag.clear();
        txtEvrakBilgileriListEvrakSayiTextAreaSag.sendKeys(sayi);
        return this;
    }

    @Step("Evrak Geliş Tipi alanında \"{evrakGelisTipi}\" seçilir ")
    public GelenEvrakKayitPage evrakGelisTipiSec(String evrakGelisTipi) {
        cmbEvrakBilgileriListEvrakGelisTipi.selectOption(evrakGelisTipi);
        return this;
    }

    @Step("İvedilik alanında \"{ivedilik}\" seçilir ")
    public GelenEvrakKayitPage ivedilikSec(String ivedilik) {
        cmbEvrakBilgileriListIvedilik.selectOption(ivedilik);
        return this;
    }

    @Step("Miat alanı \"{miat}\" doldurulur ")
    public GelenEvrakKayitPage miatDoldur(String miat) {
        txtEvrakBilgileriListMiat.setValue(miat);
        return this;
    }

    @Step("Evrak Bilgileri Açıklama alanına \"{evrakBilgileriAciklama}\" girilir")
    public GelenEvrakKayitPage evrakBilgileriAciklamaDoldur(String evrakBilgileriAciklama) {
        txtEvrakBilgileriListAciklama.sendKeys(evrakBilgileriAciklama);
        return this;
    }

    @Step("Özel Kategori alanıda \"{ozelKategori}\" seçilir")
    public GelenEvrakKayitPage ozelKategoriSec(String ozelKategori) {
        cmbEvrakBilgileriListOzelKategori.selectOption(ozelKategori);
        return this;
    }

    @Step("Postalama Tarihi alanına \"{postalamaTarihi}\" girilir")
    public GelenEvrakKayitPage postalanmaTarihiDoldur(String postalanmaTarihi) {
        dateTxtEvrakBilgileriListPostalanmaTarihi.sendKeys(postalanmaTarihi);
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
        cmbHavaleIslemleriBirim.type(birim).getDetailItems()
                .filterBy(Condition.exactText(details)).first().click();
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{opsiyon}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriBirimOpsiyon(String opsiyon) {
//        birimSeç.selectOptionByValue(opsiyon);
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        if (opsiyon.equals(gerek))
            birimSeç.selectOptionByValue("G");
        else if (opsiyon.equals(bilgi))
            birimSeç.selectOptionByValue("B");
        else if (opsiyon.equals(koordinasyon))
            birimSeç.selectOptionByValue("S");
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayanWithDetails(String onaylayan, String details) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan, details);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayanKisiSec(String onaylayan) {
        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında \"{onaylayan}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayanKisiSecWithTitle(String onaylayan, String title) {
//        cmbHavaleIslemleriOnaylayacakKisi.selectLov(onaylayan+title);
//        cmbHavaleIslemleriOnaylayacakKisi.openTreePanel();

        cmbHavaleIslemleriOnaylayacakKisi.openTreePanel().getTitleItems()
                .filterBy(Condition.exactText(onaylayan + title)).first().click();


//        cmbHavaleIslemleriOnaylayacakKisi
//                .type(onaylayan).getTitleItems()
//                .filterBy(Condition.exactText(onaylayan+title)).first().click();
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında panel kontrolü.")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisiPanel() {
        cmbHavaleIslemleriOnaylayacakKisi.openTreePanel();
        waitForLoadingJS(WebDriverRunner.getWebDriver(), 15);
        takeScreenshot();
        cmbHavaleIslemleriOnaylayacakKisi.closeTreePanel();
        return this;
    }

    @Step("Lütfen seçim yapınız... popup'ı geldiği görülür.")
    public GelenEvrakKayitPage popUpKullaniciSecimKontrulu() {
        SelenideElement popUp = $(By.xpath("//span[text()='Lütfen seçim yapınız...']"));
        popUp.isDisplayed();
        return this;
    }

    @Step("Lütfen seçim yapınız... popup'ında \"{kullanici}\" seçilir.")
    public GelenEvrakKayitPage popUpKullaniciSecimi(String kullanici) {
        tblVekaletAlanVeren
                .filterBy(Condition.text(kullanici))
                .first()
                .$("[id^='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleOnaylayacakIcin'] button").click();
        return this;
    }


    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanında kullanici kontrolü. \"{kullanici}\"")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisiKontrolü(String kullanici, String title) {
//        List<String> text = cmbHavaleIslemleriOnaylayacakKisi.openTreePanel()
//                .getSelectableItems()
//                .filterBy(Condition.text(kullanici))
//                .texts();

        String text = cmbHavaleIslemleriOnaylayacakKisi.openTreePanel().getTitleItems()
                .filterBy(Condition.exactText(kullanici + title)).first().text();
        cmbHavaleIslemleriOnaylayacakKisi.closeTreePanel();
        System.out.println(text);
        Allure.addAttachment("Kullanıcı kontrolü : ", text);
        return this;
    }

    @Step("Dağıtım Bilgileri Onaylayacak Kisi alanı kontrolü.")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisiAlaniKontrolü(String vekaletAlan, String title, String vekaletVeren) {
        List<String> text = cmbHavaleIslemleriOnaylayacakKisi.getSelectedItems().texts();
        System.out.println(text);

        text.get(0).contains(vekaletAlan);
        text.get(0).contains(title);
        text.get(0).contains(vekaletVeren);

        Allure.addAttachment("Onaylayacak kişi : ", "Onaylayacak Kisi alanına \n" + text.get(0) + " geldiği görülür.");
        return this;
    }

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriBirimDoldur2(String birim) {
//        txtDagitimBilgileriBirim.sendKeys(birim);
        cmbHavaleIslemleriBirim.selectLov(birim);
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Dağıtım Bilgileri Kişi alanında \"{kisi}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriKisiDoldur(String kisi) {
        txtDagitimBilgileriKisi.sendKeys(kisi);
        return this;
    }

    @Step("Dağıtım Bilgileri Kişi alanında \"{kisi}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriKisiSec(String kisi) {
        txtDagitimBilgileriKisiComboLov.selectLov(kisi);
        return this;
    }

    @Step("Dağıtım Bilgileri Kişi alanında \"{kisi}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriKisiSec(String kisi, String title) {
        if ($(By.xpath("//table[@id='evrakBilgileriForm:kisiLovContainer']//span[text()='Birim']")).isDisplayed())
            $(By.xpath("//table[@id='evrakBilgileriForm:kisiLovContainer']//span[text()='Birim']")).click();

        txtDagitimBilgileriKisiComboLov.type(kisi).getTitleItems()
                .filterBy(Condition.exactText(kisi + title)).first().click();
        txtDagitimBilgileriKisiComboLov.closeTreePanel();
        return this;
    }

    @Step("Dağıtım Bilgileri Kişi alanında \"{kisi}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriKisiSec2(String kisi) {
        txtDagitimBilgileriKisiComboLov.type(kisi).getSelectableItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Dağıtım Bilgileri Kullanıcı Listesi alanında \"{kullaniciListesi}\" seçilir")
    public GelenEvrakKayitPage dagitimBilgileriKullaniciListesiDoldur(String kullaniciListesi) {
//        txtDagitimBilgileriKullaniciListesi.sendKeys(kullaniciListesi);
        cmbDagitimBilgileriKullaniciListesi.selectLov(kullaniciListesi);
        return this;

    }

    @Step("Dağıtım Bilgileri Onaylayacak Kişi eklenir")
    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisi() {
        btnDagitimBilgileriOnaylayacakKisi.click();
        return this;
    }

    @Step("Dağıtım Bilgileri açıklama alanına \"{aciklama}\" girilir")
    public GelenEvrakKayitPage dagitimBilgileriAciklamaDoldur(String aciklama) {
        txtDagitimBilgileriAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Dağıtım Bilgileri Dosya Ekle")
    public GelenEvrakKayitPage dagitimBilgileriDosyaEkle() {
        btnDagitimBilgileriDosyaEkle.click();
        return this;
    }

    @Step("Dağıtım Bilgileri İşlem Süresi alanına \"{islemSuresi}\" girilir")
    public GelenEvrakKayitPage dagitimBilgileriIslemSuresiDoldur(String islemSuresi) {
        txtDagitimBilgileriIslemSuresi.sendKeys(islemSuresi);
        return this;
    }

    @Step("Dağıtım Bilgileri Evrakı Kapatacak Kisi seçilir")
    public GelenEvrakKayitPage dagitimBilgileriEvragiKapatacakKisi() {
        btnDagitimBilgileriEvragiKapatacakKisi.click();
        return this;
    }

    @Step("Evrak Tab View Ekle")
    public GelenEvrakKayitPage evrakEkTabViewEkle() {
        clickJs(btnEvrakEkTabViewEkle);
        return this;
    }

    @Step("Ilgi Bilgileri Tab View Ekle")
    public GelenEvrakKayitPage ilgiBilgileriTabViewEkle() {
        clickJs(btnilgiBilgileriTabViewEkle);
        return this;
    }

    @Step("Evrak Tab View Temizle")
    public GelenEvrakKayitPage evrakEkTabViewTemizle() {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    @Step("Evrak Ek Tabı Gizlilik Derecesi alanında \"{gizlilikDerecesi}\" seçilir")
    public GelenEvrakKayitPage evrakEkTabGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    @Step("Fiziksel Ek Tabı Ek Metin alanına \"{evrakEkTabViewEkMetni}\" girilir")
    public GelenEvrakKayitPage fizikselEkTabEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    @Step("Evrak Ek Tabı Ek Metin alanına \"{evrakEkTabViewEkMetni}\" girilir")
    public GelenEvrakKayitPage evrakEkTabFizikselEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    @Step("Evrak Ek Tabı Dosya Ekle")
    public GelenEvrakKayitPage evrakEkTabDosyaEkle() {
        btvEvrakEkTabViewDosyaEkle.click();
        return this;
    }

    @Step("Dağıtım Bilgileri Evrakı Onaylı Kapat seçilir")
    public GelenEvrakKayitPage dagitimBilgileriEvragiOnayliKapatSec(boolean check) {
        chkDagitimBilgileriEvragiOnayliKapat.setSelected(check);
        return this;
    }

//    public GelenEvrakKayitPage evrakEkTabViewAciklamaDoldur(String aciklama)  {
//        txtEvrakEkTabViewAciklama.sendKeys(aciklama);
//        return this;
//    }

    @Step("Evrak Ek Tabı Güvenlik Kodu alanında \"{guvenlikKoduAciklama}\" seçilir")
    public GelenEvrakKayitPage evrakEkTabGuvenlikKoduAciklamaSec(String guvenlikKoduAciklama) {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(guvenlikKoduAciklama);
        return this;
    }

    @Step("Fiziksel Ek Tabı Açıklama Ekle")
    public GelenEvrakKayitPage fizikselEkTabViewAciklamaEkle() {
        clickJs(btnEvrakFizikselEkTabViewAciklamaEkle);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabEkIslemleriEvrakTarihBasDoldur(String baslamaTarihi) {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabEkIslemleriEvrakTarihSonDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabIslemleriEvrakAramaAranacakYerSec(String aranacakYer) {
        cmbEvrakEkTabViewekIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    @Step("Evrak Arama")
    public GelenEvrakKayitPage evrakEkTabEvrakAramaDoldur(String arama) {
        txtEvrakEkTabViewevrakArama.sendKeys(arama);
        return this;
    }

    @Step("Ilgi Bilgileri Sistemde Evrak Arama")
    public GelenEvrakKayitPage ilgiBilgileriSistemdeKayitliEvrakEkleArama(String arama) {
        txtIlgiBilgileriEvrakArama.sendKeys(arama);
        return this;
    }

    @Step("Döküman Arama")
    public GelenEvrakKayitPage dokumanAraButton() {
        dokumanAraButton.click();
        return this;
    }

    @Step("Ilgi Bilgileri Sistemde Ara Button Tıkla")
    public GelenEvrakKayitPage ilgiBilgileridokumanAraButton() {
        ilgiBilgileriDokumanAraButton.click();
        return this;
    }

    @Step("Ek Ekle Button Tıkla")
    public GelenEvrakKayitPage ekEkleButton1() {
        ekEkleButton1.click();
        return this;
    }

    @Step("Ilgi Bilgileri Sistemde Ilgi Ekle Button Tıklama")
    public GelenEvrakKayitPage ilgiBilgileriEkEkleButton1() {
        ilgiBilgileriEkEkleButton1.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabArsivdenEvrakAraEkEkleTarihBasDoldur(String baslamaTarihi) {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabArsivdenEvrakAraEkEkleTarihSonDoldur(String sonTarih) {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabArsivdenEvrakAraKonuDoldur(String konu) {
        txtEvrakEkTabViewArsivdenEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabKullaniciDoldur(String kullanici) {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabArsivdenEvrakAraSayiDoldur(String evrakSayi) {
        txtEvrakEkTabViewArsivdenEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabDosyaAciklamaDoldur(String aciklama) {
        txtIlgiIslemleriTabViewDosyaAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Ilgi Işlemleri Dosya Ekle")
    public GelenEvrakKayitPage ilgiIslemleriTabDosyaEkle() {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabIlgiDosyaTemizle() {
        btnIlgiIslemleriTabViewIlgiDosyaTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabAciklamaEkle() {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabIlgiIslemleriEvrakTarihBasDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabIlgiIslemleriEvrakTarihSonDoldur(String sonTarih) {
        datetxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabIlgiIslemleriEvrakAramaAranacakYerSec(String aranacakYer) {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabEvrakAramaDoldur(String arama) {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(arama);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabArsivdenEvrakAraIlgiEkleTarihBasDoldur(String baslamaTarihi) {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabArsivdenEvrakAraIlgiEkleTarihSonDoldur(String sonTarih) {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabArsivdenIlgiEvrakAraKonuDoldur(String konu) {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabKullaniciDoldur(String kullanici) {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabArsivdenIlgiEvrakAraSayiDoldur(String evrakSayi) {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabAciklamaDoldur(String aciklama) {
        txtIlgiIslemleriTabViewAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Kaydet butonu")
    public GelenEvrakKayitPage kaydet() {
        btnKaydet.pressEnter();
        return this;
    }

    @Step("Yeni Kayıt tıklanır")
    public GelenEvrakKayitPage yeniKayitButton() {
        $("[id$='yeniKayitButton']").pressEnter();
        return this;
    }

    @Step("Evet butonu")
    public GelenEvrakKayitPage evetDugmesi() {
        $("[id='evetDugmesi']").pressEnter();
        return this;
    }

    @Step("Benzer Kayıt tıklanır")
    public GelenEvrakKayitPage benzerKayit() {
        if ($(("[id$='benzerKayitButton']")).shouldBe(visible).exists() == true) {
            $("[id$='benzerKayitButton']").pressEnter();
        } else {
        }
        return this;
    }

    @Step("Benzer Kayıt tıklanır")
    public GelenEvrakKayitPage benzerKaydet() {
        if ($$(("[id='evetButtonBenzerKaydet']")).size() == 1) {
            $("[id='evetButtonBenzerKaydet']").pressEnter();
        } else {
        }
        return this;
    }

    @Step("PopUp kontrolleri")
    public String popUps() {
//        popUp.shouldHave(Condition.visible);  pop up kontrolu

        String text;
        Selenide.sleep(5000);

        if (ustYaziYokpopUp.isDisplayed()) {
            clickJs(ustYaziYokEvet);
            Allure.addAttachment("Üst Yazı Seçmediniz PopUp'ı", "Üst Yazı gelmemiştir PopUp'ı kapatılır.");
        }
        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            clickJs(popUpEvet);
            Allure.addAttachment("Üst Yazı ve Havale Yeri yok PopUp'ı", "Üst Yazı ve Havale Yeri yok PopUp'ı kapatılır.");
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            clickJs(btnHavaleYeriSecmedinizEvet);
            Allure.addAttachment("Havale Yeri Seçmediniz PopUp'ı", mesaj2);
        }
        if (mukerrerPopUp.isDisplayed()) {
            clickJs(mukerrerPopUpEvet);
            Allure.addAttachment("Mükerrer İşlem PopUp'ı", "Mükerrer İşlem PopUp'ı kapatılır.");
        }
        basariliPopUp.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        basariliPopUp.getText().contains(mesaj4);
        Allure.addAttachment("İşlem başarılı PopUp'ı", mesaj4);
        String evrakNo = getNumberFromText(By.id("evrakKaydetBasariliDialog"));
        clickJs(basariliPopUpKapat);
        return evrakNo;
    }

    @Step("PopUp kontrolleri")
    public GelenEvrakKayitPage popUps(boolean evrakNotAlma) {
//        popUp.shouldHave(Condition.visible);  pop up kontrolu

        String text;
        Selenide.sleep(5000);

        if (ustYaziYokpopUp.isDisplayed()) {
            clickJs(ustYaziYokEvet);
            Allure.addAttachment("Üst Yazı Seçmediniz PopUp'ı", "Üst Yazı gelmemiştir PopUp'ı kapatılır.");
        }
        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            clickJs(popUpEvet);
            Allure.addAttachment("Üst Yazı ve Havale Yeri yok PopUp'ı", "Üst Yazı ve Havale Yeri yok PopUp'ı kapatılır.");
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            clickJs(btnHavaleYeriSecmedinizEvet);
            Allure.addAttachment("Havale Yeri Seçmediniz PopUp'ı", mesaj2);
        }
        if (mukerrerPopUp.isDisplayed()) {
            clickJs(mukerrerPopUpEvet);
            Allure.addAttachment("Mükerrer İşlem PopUp'ı", "Mükerrer İşlem PopUp'ı kapatılır.");
        }
        basariliPopUp.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        basariliPopUp.getText().contains(mesaj4);
        Allure.addAttachment("İşlem başarılı PopUp'ı", mesaj4);
        clickJs(basariliPopUpKapat);
        return this;
    }

    @Step("PopUp kontrolleri v2")
    public String popUpsv2() {
        Selenide.sleep(5000);

        if (ustYaziYokpopUp.isDisplayed()) {
            clickJs(ustYaziYokEvet);
            Allure.addAttachment("Üst Yazı Seçmediniz PopUp'ı", "Üst Yazı gelmemiştir PopUp'ı kapatılır.");
        }
        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            clickJs(popUpEvet);
            Allure.addAttachment("Üst Yazı ve Havale Yeri yok PopUp'ı", "Üst Yazı ve Havale Yeri yok PopUp'ı kapatılır.");
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            clickJs(btnHavaleYeriSecmedinizEvet);
            Allure.addAttachment("Havale Yeri Seçmediniz PopUp'ı", mesaj2);
        }

        if (popUphavaleOnayGonderilsinmi.isDisplayed()) {
            String mesaj3 = "Evrakı havale onayina göndermek istiyor musunuz?";
            popUphavaleOnayGonderilsinmi.getText().equals(mesaj3);
            clickJs(popUphavaleOnayGonderilsinmi);
            Allure.addAttachment("Havale Onay Gonderilsin mi PopUp'ı", mesaj3);
        }
        if (mukerrerPopUp.isDisplayed()) {
            clickJs(mukerrerPopUpEvet);
            Allure.addAttachment("Mükerrer İşlem PopUp'ı", "Mükerrer İşlem PopUp'ı kapatılır.");
        }
        basariliPopUp.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        basariliPopUp.getText().contains(mesaj4);
        Allure.addAttachment("İşlem başarılı PopUp'ı", mesaj4);

        SelenideElement vEvrakBasarili = visibleEvrakBasarili.filterBy(Condition.visible).get(0);
        String evrakNo = getNumberFromText(vEvrakBasarili.getText());
        clickJs(basariliPopUpKapat);

        return evrakNo;
    }

    @Step("Geldiği Kişiyi ekle")
    public GelenEvrakKayitPage geldigiKisiEkle() {
        executeJavaScript("arguments[0].click();",
                btnGeldigiKisiEkle);
        return this;
    }

    @Step("TC kimlik No ekle : \"{mernisNo}\" ")
    public GelenEvrakKayitPage iletisimBilgisiTCKNEkle(String mernisNo) {
        txtTCKN.clear();
        txtTCKN.sendKeys(mernisNo);
        return this;
    }

    @Step("TC kimlik No ara")
    public GelenEvrakKayitPage iletisimBilgisiTCKNAra() {
        executeJavaScript("arguments[0].click();",
                btnTCKNAra);
        return this;
    }

    @Step("Ad doldur : \"{ad}\" ")
    public GelenEvrakKayitPage iletisimBilgisiAdDoldur(String ad) {
        txtAd.setValue(ad);
        return this;
    }

    @Step("Soyad doldur : \"{soyad}\" ")
    public GelenEvrakKayitPage iletisimBilgisiSoyadDoldur(String soyad) {
        txtSoyad.setValue(soyad);
        return this;
    }

    @Step("Kaydet tıkla")
    public GelenEvrakKayitPage iletisimBilgisikaydet() {
        btnKaydetIletisimBilgisi.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleme(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
//        WebDriverRunner.getWebDriver()
//                .findElement(dosyaPath)
//                .sendKeys(pathToFile);
        return this;
    }

    @Step("Ilgi Bilgileri Dosya Ekleme : \"{pathToFile}\" ")
    public GelenEvrakKayitPage ilgiBilgileriDosyaEkleme(String pathToFile) throws InterruptedException {
        uploadFile(ilgiBilgileriDosyaPath, pathToFile);
        Thread.sleep(4000);
        return this;
    }

    @Step("Ek Bilgiler dosya ekleme açıklama alanı doldur : \"{aciklama}\" ")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleEkMetinDoldur(String aciklama) {
        txtEvrakEkTabViewEkMetni.sendKeys(aciklama);
        return this;
    }

    @Step("Ilgi Bilgiler dosya ekleme açıklama alanı doldur : \"{aciklama}\" ")
    public GelenEvrakKayitPage ilgiBilgileriDosyaEkleEkMetinDoldur(String aciklama) {
        txtIlgiBilgiEkMetni.sendKeys(aciklama);
        return this;
    }

    @Step("EkBilgiler dosya ekleme excel adi kontrol : \"{excelAdi}\" ")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleDosyaAdiKontrol(String excelAdi) {
        $(byText(excelAdi)).shouldBe(Condition.visible);
//        String text = lblDosyaAdi.text();
//        System.out.println(text);
//        Assert.assertEquals(text, excelAdi);
        return this;
    }

    @Step("Ilgi Bilgiler dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public GelenEvrakKayitPage ilgiBilgileriDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }

    @Step("PDF Ust Yazi adi kontrol : \"{ustYaziAdi}\" ")
    public GelenEvrakKayitPage ustYaziPdfAdiKontrol(String ustYaziAdi) {
        lblEklenenPdfUstYazi.shouldHave(text(ustYaziAdi));
       /* String text = lblEklenenPdfUstYazi.text();
        System.out.println(text);
        Assert.assertEquals(text.contains(ustYaziAdi), true);*/
        return this;
    }

    @Step("PDF text alır ")
    public String onIzlemePdfText() {
        String text = "";
        switchTo().frame(3);
        sleep(1000);
        text = $x("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[1]").getText();
        System.out.println(text);
        switchTo().parentFrame();
        return text;
    }

    @Step("Mail Ust Yazi adi kontrol : \"{ustYaziAdi}\" ")
    public GelenEvrakKayitPage ustYaziMailAdiKontrol(String ustYaziAdi) {
//        String text = lblEklenenMailUstYazi.text();
        lblEklenenMailUstYazi.shouldBe(Condition.text(ustYaziAdi));
//        System.out.println(text);
//        Assert.assertEquals(text.contains(ustYaziAdi), true);
        return this;
    }


    @Step("Birim butonu tıkla")
    public GelenEvrakKayitPage havaleIslemleriBirim() {
        btnBirim.click();
        return this;
    }

    @Step("Havale İşlemleri Birim alanında \"{birim}\" seç")
    public GelenEvrakKayitPage havaleIslemleriBirimDoldur(String birim) {
        txtHavaleIslemleriBirim.selectLov(birim);
        return this;
    }

    @Step("Alanların güncellenebilirlik kontrolü")
    public GelenEvrakKayitPage evrakDetaylariAlanGuncellenebilirlikKontrolü() {
        txtEvrakBilgileriListKonuKodu.shouldBe(Condition.enabled);
        txtEvrakBilgileriListKonu.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakTuru.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakDili.shouldBe(Condition.enabled);
        dateTxtEvrakBilgileriListEvrakTarihi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListGizlilikDerecesi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListKisiKurum.shouldBe(Condition.enabled);
        txtEvrakBilgileriListEvrakSayiTextAreaSag.shouldBe(Condition.enabled);
        comboGeldigiKurum.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakGelisTipi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListIvedilik.shouldBe(Condition.enabled);
        txtEvrakBilgileriListMiat.shouldBe(Condition.enabled);

//        Allure.addAttachment(txtEvrakBilgileriListKonuKodu.getSelectedTitles().toString(), "Ekran kontolü ok");
        Allure.addAttachment(txtEvrakBilgileriListKonu.text(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListEvrakTuru.getText(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListEvrakDili.getText(), "Ekran kontolü ok");
        Allure.addAttachment(dateTxtEvrakBilgileriListEvrakTarihi.getValue(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListGizlilikDerecesi.getText(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListKisiKurum.getText(), "Ekran kontolü ok");
        Allure.addAttachment(txtEvrakBilgileriListEvrakSayiTextAreaSag.text(), "Ekran kontolü ok");
//        Allure.addAttachment(comboGeldigiKurum.getSelectedText(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListEvrakGelisTipi.getText(), "Ekran kontolü ok");
        Allure.addAttachment(cmbEvrakBilgileriListIvedilik.text(), "Ekran kontolü ok");
        Allure.addAttachment(txtEvrakBilgileriListMiat.getValue(), "Ekran kontolü ok");
        return this;
    }

    @Step("Evrak Detayi Ekleri tabı tıkla")
    public GelenEvrakKayitPage evrakDetayiEkleriTab() {
        btnevrakDetayiEvrakEkleri.click();
        return this;
    }

    @Step("Evrak Detayi Fizilsel Ek Ekle tabı tıkla")
    public GelenEvrakKayitPage evrakDetayiFizikselEkEkleTab() {
        btnEvrakDetayiFizikselEkEkle.click();
        return this;
    }

    @Step("Evrak Detayi Aciklama alanına \"{aciklama}\" girilir")
    public GelenEvrakKayitPage evrakDetayiAciklamaDoldur(String aciklama) {
        txtEvrakDetayiAciklama.sendKeys(aciklama);
        SelenideElement btnEvrakDetayiEkle = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaEkleButton"));
        SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
        return this;
    }

    @Step("Evrak Detayi Ekle tıkla")
    public GelenEvrakKayitPage evrakDetayiEkle() {
        btnEvrakDetayiEkle.click();
        SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
        return this;
    }

    @Step("Evrak Detayi Kaydet tıkla")
    public GelenEvrakKayitPage evrakDetayiKaydet() {
        btnEvrakDetayiKaydet.click();
        return this;
    }

    @Step("Evrak Detayi EvrakNo text al")
    public String evrakDetayiEvrakNoTextAl() {
        return txtEvrakDetayiEvrakNo.text();
    }

    @Step("Guncelleme Kontrolleri")
    public GelenEvrakKayitPage guncellenenAlanKontrolleri(String evrakTarihi, String evrakTuru, String gizlilikDerecesi) {
        String txtEvrakTarihi = dateTxtEvrakBilgileriListEvrakTarihi.getValue();
        String txtEvrakTuru = cmbEvrakBilgileriListEvrakTuru.getSelectedValue();
        String txtGizlilikDerecesi = cmbEvrakBilgileriListGizlilikDerecesi.getSelectedValue();

        Assert.assertEquals(txtEvrakTarihi, evrakTarihi);
        Assert.assertEquals(txtEvrakTuru, evrakTuru);
        Assert.assertEquals(txtGizlilikDerecesi, gizlilikDerecesi);

        return this;
    }

    @Step("Evrak Detayi PdfDegisiklik PopUp close")
    public GelenEvrakKayitPage evrakDetayiPdfDegisiklikpopUpClose() {
//        popUpPdfDegisiklik.shouldBe(Condition.visible);
        if (popUpPdfDegisiklik.isDisplayed()) {
            btnEvrakDetayiPdfDegisiklikKabul.click();
        }
        return this;
    }

    @Step("Evrak Detayi Kaydet PopUp Close")
    public GelenEvrakKayitPage evrakDetayiKaydetPopUpClose() {
        btnEvrakDetayiKaydetUyarisi.shouldBe(Condition.visible);
        btnEvrakDetayiKaydetUyarisi.click();
        return this;
    }

    @Step("Panel kapat")
    public GelenEvrakKayitPage panelKapat(Boolean kaydet) {
        SelenideElement closeButton = $(By.xpath("//span[@class='ui-dialog-title' and text()='Gelen Evrak Kayıt']/..//span[@class='ui-icon ui-icon-closethick']"));
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", closeButton);
        closeButton.click();

        if (kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }

    @Step("Üst yazı değiştirilsim mi popUp kontrolü. \"{secim}\" ")
    public GelenEvrakKayitPage ustYaziDegistirilmisPopUpKontrol(boolean secim) {
        if (popUpUstYaziDegistirilme.isDisplayed()) {
            if (secim)
                clickJs(btnUstYaziDegistirEvet);
            else
                clickJs(btnUstYaziDegistirmeHayır);
        }
        return this;
    }

    @Step("Dosya Ekle Tabı tabloda \"{dosyaAdi}\" kontrolü")
    public GelenEvrakKayitPage dosyaEkleTabTabloKontrolu(String dosyaAdi) {
//        boolean status = findElementOnTableByColumnInputInAllPages(tblDosyaEkle,columIndex,dosyaAdi).isDisplayed();
        tblDosyaEkle
                .filterBy(Condition.text(dosyaAdi)).shouldHaveSize(1);
//        Assert.assertEquals(status, true);
        return this;
    }

    @Step("Ilgi Bilgileri Dosya Ekle Tabı tabloda \"{dosyaAdi}\" kontrolü")
    public GelenEvrakKayitPage ilgiBilgileridosyaEkleTabloKontrolu(String dosyaAdi) {
        boolean durum = tblIlgiliBilgilerDosyaEkle
                .filterBy(Condition.text(dosyaAdi)).size() > 0;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Evrak Ekleri ekle butonana tıkla")
    public GelenEvrakKayitPage evrakEkleriDosyaEkle() {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    @Step("Ekrandaki alan kontrolleri")
    public GelenEvrakKayitPage alanKontrolleri() {

        btnUstYaziEkle.isDisplayed();
        lblUstyaziGoster.isDisplayed();
        lblUstyaziGizle.isDisplayed();
        btnTaramaHavuzundanEkle.isDisplayed();
        btnTarayicidanEkle.isDisplayed();
        btnTaramaArayuzundenEkle.isDisplayed();
        btnTaramaServisindenEkle.isDisplayed();

        Allure.addAttachment("Ekran Kontrolü", "üst yazı ekle butonunun\n" +
                "üst yazı göster\n" +
                "üst yazı gizle seçeneklerinin\n" +
                "tarama havuzundan ekle\n" +
                "tarayıcıdan ekle\n" +
                "tarama arayüzünden ekle\n" +
                "tarama servisinden ekle butonlarının geldiği görülür.\n");
        takeScreenshot();
//        Allure.addAttachment(btnUstYaziEkle.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(lblUstyaziGoster.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(lblUstyaziGizle.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnTaramaHavuzundanEkle.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnTarayicidanEkle.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnTaramaArayuzundenEkle.text(), "Ekran Kontrolü ok");
//        Allure.addAttachment(btnTaramaServisindenEkle.text(), "Ekran Kontrolü ok");

        return this;
    }

    @Step("Evrak turu alanında \"{evrakTuru}\" kontrolü")
    public GelenEvrakKayitPage evrakTuruKontrol(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.shouldHave(Condition.text(evrakTuru));
//        Assert.assertEquals(cmbEvrakBilgileriListEvrakTuru.getText(), evrakTuru);
        return this;
    }

    @Step("Evrak Sayısı sol alan kontrolü : \"{solAlan}\" ")
    public GelenEvrakKayitPage evrakSayisiSolAlanKontrolu(String solAlan) {
        Assert.assertEquals(txtEvrakBilgileriListEvrakSayiTextAreaSol.getValue(), solAlan);
        return this;
    }

    @Step("Konu kodu sil")
    public GelenEvrakKayitPage konuKoduSil() {
        comboKonuKodu.clearLastSelectedItem();
        return this;
    }

    @Step("Konu kodu temizle")
    public GelenEvrakKayitPage konuKoduTemizle() {
        txtKonuKoduTemizle.click();
        sleep(2000);
        return this;
    }

    @Step("Evrak Tarihi sil")
    public GelenEvrakKayitPage evrakTarihiSil() {
        dateTxtEvrakBilgileriListEvrakTarihi.clear();
        return this;
    }

    @Step("Evrak Sayısı Sağ alan sil")
    public GelenEvrakKayitPage evrakSayiSagSil() {
        txtEvrakBilgileriListEvrakSayiTextAreaSag.clear();
        return this;
    }

    @Step("Havale yeri seçmediniz Popup kontrolü")
    public GelenEvrakKayitPage popUpKontrol(String secim) {
        String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";

        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            if (secim.equals("Hayır"))
                clickJs(btnUstYaziveHavaleYeriSecmedinizHayır);
        } else
            clickJs(btnUstYaziveHavaleYeriSecmedinizEvet);
        return this;
    }

    @Step("Evrak üst yazı ve havale yeri seçmediniz Popup kontrolü")
    public GelenEvrakKayitPage popUpKontrol2(String secim) {
        SelenideElement btnHavaleYeriSecmedinizHayır = $(By.id("hayirDugmesiUstYaziHavaleYer"));
        SelenideElement btnHavaleYeriSecmedinizEvet = $(By.id("evetDugmesiUstYaziHavaleYer"));
        String mesaj2 = "Evrak üst yazı ve havale yeri seçmediniz. Evrak kaydedildiğinde havale işlemine devam edecektir.İşleme devam etmek istiyor musunuz?";
        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            if (secim.equals("Hayır"))
                clickJs(btnHavaleYeriSecmedinizHayır);
        } else
            clickJs(btnHavaleYeriSecmedinizEvet);
        return this;
    }

    @Step("Ust yazi gizle")
    public GelenEvrakKayitPage ustYaziGizle() {
        lblUstyaziGizle.click();
        takeScreenshot();
        return this;
    }

    @Step("Ust yazi gözter")
    public GelenEvrakKayitPage ustYaziGoster() {
        lblUstyaziGoster.click();
        takeScreenshot();
        return this;
    }

    @Step("Vekalet alan Ve Veren tablosu vekalet alan \"{isim}\" seç")
    public GelenEvrakKayitPage vekeletAlanVerenTabloVekaletAlanveyaVerenSec(String isim) {
        tblVekaletVerenAlan
                .filterBy(Condition.text(isim)).first()
                .$("[id='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleIcin_data'] td:nth-child(4) button").click();
        return this;
    }

    @Step("Tarama Havuzundan Ekle sayfası açılır")
    public GelenEvrakKayitPage taramaHavuzundanEkleSayfasiAc() {
        btnTaramaHavuzundanEkle.click();
        return this;
    }

    @Step("Tarama Havuzu ilk kayıt seçilir")
    public GelenEvrakKayitPage taramaHavuzuIlkKayitSec() {
        chkTaramaHavuzuIlkEvrak.click();
        return this;
    }

    @Step("Tarama Havuzu ilk kayıt Tür \"{tur}\" seçilir ")
    public GelenEvrakKayitPage taramaHavuzuIlkKayitTurSec(String tur) {
        cmbTaramaHavuzuIlkEvrakTur.selectOption(tur);
        return this;
    }

    @Step("Tarama Havuzu Tamam butonu ")
    public GelenEvrakKayitPage taramaHavuzuTamam() {
        clickJs(btnTaramaHavuzuTamam);
        return this;
    }

    @Step("Gelen Evrak Kayıt sayfasında Kaydet butonuna tıkladıktan sonra beliren panelde Evet butonuna tıkla.")
    public GelenEvrakKayitPage gelenEvrakKayitKaydetEvet() {
        popUpEvet.click();
        return this;
    }

    @Step("Gelen Evrak Kayıt sayfasında Kaydet butonuna tıkladıktan sonra beliren panelde Evet butonuna tıkla.")
    public GelenEvrakKayitPage gelenEvrakKayitKaydetEvet2() {
        popUpEvet2.click();
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public GelenEvrakKayitPage havaleAlanKontrolleri() {
        String text = "";
        if (otomatikHavaleCheckbox.isDisplayed()) {
            text += "Otomatik Havale Checkbox,";
            Assert.assertEquals(otomatikHavaleCheckbox.isDisplayed(), true, "Otomatik Havale Checkbox Görüntülendi");
            Allure.addAttachment("Otomatik Havale Checkbox Görüntülendi", "");
        }
        if (birimKontrol.isDisplayed()) {
            text += "Birim Kontrol,";
            Assert.assertEquals(birimKontrol.isDisplayed(), true, "Birim Alanı Görüntülendi");
            Allure.addAttachment("Birim Alanı Görüntülendi", "");
        }
        if (kisiKontrol.isDisplayed()) {
            text += "Kisi Kontrol, ";
            Assert.assertEquals(kisiKontrol.isDisplayed(), true, "Kisi Alanı Görüntülendi");
            Allure.addAttachment("Kisi Alanı Görüntülendi", "");
        }
        if (kullanıcıListeKontrol.isDisplayed()) {
            text += "Kullanıcı Liste,";
            Assert.assertEquals(kullanıcıListeKontrol.isDisplayed(), true, "Kullanıcı Listesi Alanı Görüntülendi");
            Allure.addAttachment("Kullanıcı Listesi Alanı Görüntülendi", "");
        }
        if (aciklamaKontrol.isDisplayed()) {
            text += "Aciklama,";
            Assert.assertEquals(aciklamaKontrol.isDisplayed(), true, "Aciklama Alanı Görüntülendi");
            Allure.addAttachment("Aciklama Alanı Görüntülendi", "");
        }
        if (dosyaEkleKontrol.isDisplayed()) {
            text += "Dosya Ekle,";
            Assert.assertEquals(dosyaEkleKontrol.isDisplayed(), true, "Dosya Kontrol Alanı Görüntülendi");
            Allure.addAttachment("Dosya Ekle Alanı Görüntülendi", "");
        }
        if (islemSureKontrol.isDisplayed()) {
            text += "İslem Sure alanları gösterilmektedir.";
            Assert.assertEquals(islemSureKontrol.isDisplayed(), true, "İşlem Sure Alanı Görüntülendi");
            Allure.addAttachment("Işlem Süre Alanı Görüntülendi", "");
        }
        Allure.addAttachment("Alan Kontrolleri : ", text);
        takeScreenshot();
        return this;
    }

    @Step("Evrak Kapatma")
    public GelenEvrakKayitPage evrakKapatma() {
        ElementsCollection tr = $$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']").filterBy(text("Gelen Evrak Kayıt"));
        tr.get(0).$("[href]").click();
        islemKapat.click();
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public GelenEvrakKayitPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public GelenEvrakKayitPage evrakNoIleTabloKontrolu(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        return this;
    }


    @Step("Geldiği birim alanında \"{geldigiBirim}\" seç ")
    public GelenEvrakKayitPage geldigiBirimDoldur(String geldigiBirim) {
        cmbGeldigiBirim.selectLov(geldigiBirim);
        return this;
    }

    @Step("Evrak no alanının, birim kaydederken girilen idari birim kimlik no ile dolduğu görülür")
    public GelenEvrakKayitPage evrakSayisiKontrolu(String idariBirimKimlikKodu) {
        Assert.assertEquals(txtEvrakBilgileriListEvrakSayiTextAreaSol.getValue().contains(idariBirimKimlikKodu), true, "Evrak Sayı - İdari Kimlik No");
        return this;
    }

    @Step("Gereği alanında Birimin geldiği ve seçilemediği kontrolu - {description} : {birim}")
    public GelenEvrakKayitPage geregiAlanindaBiriminGeldigiVeSecilemedigiKontrolu(String birim, String description) {

        int gorunurSecilemezBirimSize = comboLov(cmbGeldigiBirimBy).type(birim).getSelectableItems().size();
        Assert.assertEquals(gorunurSecilemezBirimSize == 0, true, "Birimin geldiği ve seçilemediği görülür: " + birim);
        comboLov(cmbGeldigiBirimBy).closeTreePanel();
        System.out.println("Birimin geldiği ve seçilemediği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilemediği görülür: " + birim, "");

        return this;
    }

    @Step("Gereği alanında Birimin geldiği ve seçilebildiği kontrolu - {description} : {birim}")
    public GelenEvrakKayitPage geregiAlanindaBiriminGeldigiVeSecilebildigiKontrolu(String birim, String description) {

        cmbGeldigiBirim.selectLov(birim);

        System.out.println("Birimin geldiği ve seçilebildiği görülür: " + birim);
        Allure.addAttachment("Birimin geldiği ve seçilebildiği görülür: " + birim, "");

        return this;
    }


    @Step("Teslim Alınmayı Bekleyenler evrak düşürmektedir.")
    public void gelenEvrakKayitBirimHavaleEt(String konu,String kurum,String birim) {
        String konuKodu = "Diğer";
        String evrakSayiSag = createRandomText(15);

        //1.Teslim Alınmayı Bekleyenler evrak düşürmektedir.
                openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(getSysDate())
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
    }

    @Step("Gelen Evraklar sayfasına evrak düşürmektedir.")
    public void gelenEvrakKayitKullaniciHavaleEt(String konu,String kurum,String kullanici){

        String konuKodu = "Diğer";
        String evrakTarihi = getSysDateForKis();
        String evrakSayiSag = createRandomNumber(8);

        openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kullanici)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
    }
}
