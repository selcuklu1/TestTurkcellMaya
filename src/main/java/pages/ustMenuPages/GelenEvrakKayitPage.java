package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvrakKayitPage extends MainPage {

    //region Elements
    SelenideElement pageTitle = $(By.cssSelector("#baseLayoutCenter .ui-dialog-title"));

    // Evrak Bilgileri Sekmesinde bulunanlar
    SelenideElement btnUstYaziEkle = $(By.xpath("//input[@id='evrakBilgileriForm:ustYaziForm:ustYaziUpload_input']"));
    SelenideElement txtEvrakBilgileriListKonuKodu = $("[id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[[id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");

    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");


    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    //SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    //BelgenetElement cmbEvrakBilgileriListGeldigiKisi = comboLov(By.id("evrakBilgileriForm:evrakBilgileriList:9:geldigiGercekKisiLov:LovText"));
    BelgenetElement cmbGeldigiGercekKisi = comboLov("[id$='geldigiGercekKisiLov:LovText']");
    BelgenetElement cmbGeldigiTuzelKisi = comboLov("[id$='geldigiTuzelKisiLov:LovText']");
    By cmbGeldiğiGercekKisiBy = By.cssSelector("[id$='geldigiGercekKisiLov:LovText']");
    By cmbGeldiğiTuzelKisiBy = By.cssSelector("[id$='geldigiTuzelKisiLov:LovText']");


    public SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSol = $("[id$='evrakSayiTextAreaSol']");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $("[id$=miatCalendar_input]");
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("evrakBilgileriForm:evrakBilgileriList:15:j_idt4318"));
    SelenideElement cmbEvrakBilgileriListOzelKategori = $(By.id("evrakBilgileriForm:evrakBilgileriList:17:j_idt4499"));
    SelenideElement dateTxtEvrakBilgileriListPostalanmaTarihi = $(By.id("evrakBilgileriForm:evrakBilgileriList:18:postalanmaTarihi_input"));
    BelgenetElement comboKonuKodu = comboLov("[id$='konuKoduLov:LovText']");
    BelgenetElement comboGeldigiKurum = comboLov("[id$='geldigiKurumLov:LovText']");

    // Evrak Ekleri sekmesinde bulunanlar
    // Dosya ekle alt sekmesinde bulunanlar

    SelenideElement btnEvrakEkleri = $(By.id("evrakBilgileriForm:evrakEkleriListesiPanel_toggler"));
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaTemizleButton"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaAciklama"));
    SelenideElement btvEvrakEkTabViewDosyaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:fileUploadButton_input"));
    //    ElementsCollection tblDosyaEkle = $$("div[id$='evrakBilgileriForm:ekListesiDataTable'] tr[role=row]");
    ElementsCollection tblDosyaEkle = $$("div[id$='ekListesiDataTable'] tr[role=row]");

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
    SelenideElement chkOtomatikHavale = $(By.id("evrakBilgileriForm:j_idt11601_input"));
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
    BelgenetElement cmbDagitimBilgileriKisi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement cmbDagitimBilgileriKullaniciListesi = comboLov(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection tblVekaletVerenAlan =$$("[id='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleIcin_data'] tr[role='row']");


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
    SelenideElement popUphavaleYeriSecmediniz = $(By.id("havaleYeriSecmedinizConfirmDialog"));
    SelenideElement btnHavaleYeriSecmedinizEvet = $("[id='evetButtonBos']");
    SelenideElement btnHavaleYeriSecmedinizHayır = $(By.id("hayirButtonBos"));
    SelenideElement ustYaziveHavaleYeriYokpopUp = $("[id='ustYaziveHavaleYeriYokConfirmDialog']");
    SelenideElement ustYaziYokEvet = $("[id='evetDugmesi']");
    SelenideElement ustYaziYokpopUp = $("[id='ustYaziYokConfirmDialog']");
    SelenideElement popUpEvet = $("[id='evetDugmesiUstYaziHavaleYer']");
    SelenideElement mukerrerPopUpEvet = $("[id='evetButtonBenzerKaydet']");
    SelenideElement mukerrerPopUp = $("[id='benzerEvrakKayitConfirmDialog']");
    SelenideElement btnUstYaziDegistirmeHayır = $(By.id("evrakBilgileriForm:ustyaziDegistirmeButton"));
    SelenideElement popUpUstYaziDegistirilme = $(By.id("evrakBilgileriForm:ustyaziDegistirisilMiDialog"));
    SelenideElement basariliPopUpKapat = $("[id='evrakKaydetBasariliDialogForm:vazgecButton']");
    SelenideElement basariliPopUp = $("[id='evrakKaydetBasariliDialog']");

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

    SelenideElement btnTaramaHavuzundanEkle = $(By.id("evrakBilgileriForm:uploadFromTarananEvrakHavuzuGelenEvrak"));
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


    public GelenEvrakKayitPage otomatikHavaleSec(boolean secim) {
        chkOtomatikHavale.setSelected(secim);
        return this;
    }

    public GelenEvrakKayitPage openPage() {
        new UstMenu().ustMenu("Gelen Evrak Kayıt");
        return this;
    }

    @Step("Kişi alanını doldur")
    public GelenEvrakKayitPage havaleIslemleriKisiDoldur(String kisi) {
        txtHavaleIslemleriKisi.selectLov(kisi);
        return this;
    }

    @Step("")
    public GelenEvrakKayitPage havaleIslemleriKullaniciListesiDoldur(String kisi) {
        txtHavaleIslemleriKullaniciListesi.selectLov(kisi);
        txtHavaleIslemleriKullaniciListesi.selectLov(kisi);

        return this;
    }
    
    public GelenEvrakKayitPage ekBilgiFizikselEkEkle() throws InterruptedException {
        clickJs(btnFizikselEkEkle);
        return this;
    }

    public GelenEvrakKayitPage ekBilgiFiltreAc() throws InterruptedException {
//        btnEvrakEkleri.click();
        clickJs(btnEvrakEkleri);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriUstYaziEkle(String path) throws InterruptedException {
        uploadFile(ustYazi, path);
        //ustYaziUploadFile(path);
        return this;
    }

    public GelenEvrakKayitPage konuKoduDoldur(String konuKodu) throws InterruptedException {
        comboKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Konu doldur")
    public GelenEvrakKayitPage konuDoldur(String konu){
    $("[id$='konuTextArea']").setValue(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKonuDoldur(String konu) {
        txtEvrakBilgileriListKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakTuruSec(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.selectOptionByValue(evrakTuru);
        return this;
    }

    public GelenEvrakKayitPage evrakDiliSec(String evrakDili) {
        cmbEvrakBilgileriListEvrakDili.selectOptionByValue(evrakDili);
        return this;
    }

    public GelenEvrakKayitPage evrakTarihiDoldur(String evrakTarihi) {
        dateTxtEvrakBilgileriListEvrakTarihi.clear();
        dateTxtEvrakBilgileriListEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    public GelenEvrakKayitPage gizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOptionByValue(gizlilikDerecesi);
        return this;
    }

    @Step("Kişi kurum tipi alanında {kisiKurum} seç")
    public GelenEvrakKayitPage kisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
        return this;
    }

    @Step("Kişi kurum tipi alanında {kisiKurum} seç")
    public GelenEvrakKayitPage kisiKurumSecByText(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOption(kisiKurum);
        return this;
    }


    @Step("Geldiği Gerçek kişi doldur")
    public GelenEvrakKayitPage geldigiGercekKisiDoldur(String geldigiKisi) {

        cmbGeldigiGercekKisi.selectLov(geldigiKisi);

        System.out.println("title: " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeldigiGercekKisi.lastSelectedLovDetailText());

        return this;
    }

    public GelenEvrakKayitPage secilenGeregiGercekKisiSil() {
        cmbGeldigiGercekKisi.clearLastSelectedLov();
        return this;
    }

    public GelenEvrakKayitPage secilenGeregiTuzelKisiSil() {
        cmbGeldigiTuzelKisi.clearLastSelectedLov();
        return this;
    }

    @Step("Geldiği Tüzel kişi doldur")
    public GelenEvrakKayitPage geldigiTuzelKisiDoldur(String geldigiTuzelKisi) {

        cmbGeldigiTuzelKisi.selectLov(geldigiTuzelKisi);

        System.out.println("title: " + cmbGeldigiTuzelKisi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeldigiTuzelKisi.lastSelectedLovDetailText());

        return this;
    }

    @Step("Gerçek kişinin geldiği alanında görüntülenmeme kontrolu")
    public GelenEvrakKayitPage geldigiGercekKisiGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeldiğiGercekKisiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    @Step("Tüzel kişinin geldiği alanında görüntülenmeme kontrolu")
    public GelenEvrakKayitPage geldigiTuzelKisiGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeldiğiTuzelKisiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    @Step("Tüzel kişinin geldiği alanında görüntülenme kontrolu")
    public GelenEvrakKayitPage geldigiTuzelKisiGoruntulenmeKontrolu(String kisi) {

        cmbGeldigiTuzelKisi.selectLov(kisi);

        if (cmbGeldigiTuzelKisi.lastSelectedLovTitleText().contains(kisi)) {
            System.out.println("Gelen title:     " + cmbGeldigiTuzelKisi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + kisi);
            Assert.assertEquals(cmbGeldigiTuzelKisi.lastSelectedLovTitleText().contains(kisi), true);
        } else if (cmbGeldigiTuzelKisi.lastSelectedLovDetailText().contains(kisi)) {
            System.out.println("Gelen title:     " + cmbGeldigiTuzelKisi.lastSelectedLovDetailText());
            System.out.println("Beklenen title:  " + kisi);
            Assert.assertEquals(cmbGeldigiTuzelKisi.lastSelectedLovDetailText().contains(kisi), true);
        }

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
        System.out.println("Gelen title:     " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);

        Assert.assertEquals(cmbGeldigiGercekKisi.lastSelectedLovTitleText().contains(adSoyad), true);

        return this;
    }

    SelenideElement btnSecilenGeldigiKurumKaldir = $("div[id$='geldigiKurumLov:LovSecilen'] button");

    public GelenEvrakKayitPage geldigiKurumDoldurLovText(String geldigiKurum) {
        if (btnSecilenGeldigiKurumKaldir.isDisplayed())
            btnSecilenGeldigiKurumKaldir.click();
        comboGeldigiKurum.selectLov(geldigiKurum);
        return this;
    }

    public GelenEvrakKayitPage geldigiKurumDoldurLovText2(String geldigiKurum) {
        comboGeldigiKurum.clearLastSelectedLov();
        comboGeldigiKurum.selectLov(geldigiKurum);
        return this;
    }

    public GelenEvrakKayitPage evrakSayiSolDoldur(String evrakSayiSol) {
        txtEvrakBilgileriListEvrakSayiTextAreaSol.sendKeys(evrakSayiSol);
        return this;
    }

    public GelenEvrakKayitPage evrakSayiSagDoldur() {
        String evrakSayiSag = createRandomNumber(5);
        txtEvrakBilgileriListEvrakSayiTextAreaSag.clear();
        txtEvrakBilgileriListEvrakSayiTextAreaSag.sendKeys(evrakSayiSag);
        return this;
    }

    public GelenEvrakKayitPage evrakGelisTipiSec(String evrakGelisTipi) {
        cmbEvrakBilgileriListEvrakGelisTipi.selectOptionByValue(evrakGelisTipi);
        return this;
    }

    public GelenEvrakKayitPage ivedilikSec(String ivedilik) {
        cmbEvrakBilgileriListIvedilik.selectOptionByValue(ivedilik);
        return this;
    }

    public GelenEvrakKayitPage miatDoldur(String miat) {
        txtEvrakBilgileriListMiat.setValue(miat);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriAciklamaDoldur(String evrakBilgileriAciklama) {
        txtEvrakBilgileriListAciklama.sendKeys(evrakBilgileriAciklama);
        return this;
    }

    public GelenEvrakKayitPage ozelKategoriSec(String ozelKategori) {
        cmbEvrakBilgileriListOzelKategori.selectOption(ozelKategori);
        return this;
    }

    public GelenEvrakKayitPage postalanmaTarihiDoldur(String postalanmaTarihi) {
        dateTxtEvrakBilgileriListPostalanmaTarihi.sendKeys(postalanmaTarihi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriBirimDoldur(String birim) {
//        txtDagitimBilgileriBirim.sendKeys(birim);
        cmbHavaleIslemleriBirim.type(birim).titleItems()
                .filterBy(Condition.exactText(birim)).get(0).click();
        cmbHavaleIslemleriBirim.closeLovTreePanel();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKisiDoldur(String kisi) {
        txtDagitimBilgileriKisi.sendKeys(kisi);
        return this;
    }
    public GelenEvrakKayitPage dagitimBilgileriKisiSec(String kisi) {
        txtDagitimBilgileriKisiComboLov.selectLov(kisi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKullaniciListesiDoldur(String kullaniciListesi) {
//        txtDagitimBilgileriKullaniciListesi.sendKeys(kullaniciListesi);
        cmbDagitimBilgileriKullaniciListesi.selectLov(kullaniciListesi);
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
        clickJs(btnEvrakEkTabViewEkle);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewTemizle() {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabGizlilikDerecesiSec(String gizlilikDerecesi) {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvrakKayitPage fizikselEkTabEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabFizikselEkMetniDoldur(String evrakEkTabViewEkMetni) {
        txtEvrakFizikselEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabDosyaEkle() {
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

    public GelenEvrakKayitPage evrakEkTabGuvenlikKoduAciklamaSec(String guvenlikKoduAciklama) {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(guvenlikKoduAciklama);
        return this;
    }

    public GelenEvrakKayitPage fizikselEkTabViewAciklamaEkle() {
        btnEvrakFizikselEkTabViewAciklamaEkle.click();
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

    public GelenEvrakKayitPage evrakEkTabEvrakAramaDoldur(String arama) {
        txtEvrakEkTabViewevrakArama.sendKeys(arama);
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

    public GelenEvrakKayitPage kaydet() {
        btnKaydet.click();
        return this;
    }

    public GelenEvrakKayitPage yeniKayitButton(){
        $("[id='evrakKaydetBasariliDialogForm:yeniKayitButton']").pressEnter();
        return this;
    }
    
    public GelenEvrakKayitPage evetDugmesi(){
        $("[id='evetDugmesi']").pressEnter();
        return this;
    }

    public GelenEvrakKayitPage benzerKayit(){
        $(By.id("evetButtonBenzerKaydet")).pressEnter();
        return this;
    }

    public String popUps() throws InterruptedException {
//        popUp.shouldHave(Condition.visible);  pop up kontrolu
        String text;

        Thread.sleep(2000);

        if (ustYaziveHavaleYeriYokpopUp.isDisplayed()) {
            popUpEvet.click();
        }
        if (popUphavaleYeriSecmediniz.isDisplayed()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            btnHavaleYeriSecmedinizEvet.click();
        }
        if (ustYaziYokpopUp.isDisplayed()) {
            ustYaziYokEvet.click();
        }
        if (mukerrerPopUp.isDisplayed()) {
            mukerrerPopUpEvet.click();
        }
        basariliPopUp.shouldBe(Condition.visible);
        String mesaj4 = "Evrak başarıyla kaydedilmiştir.";
        basariliPopUp.getText().contains(mesaj4);

        String evrakNo = getIntegerInText(By.id("evrakKaydetBasariliDialog"));
        basariliPopUpKapat.click();

        return evrakNo;
    }

    @Step("Geldiği Kişiyi ekle")
    public GelenEvrakKayitPage geldigiKisiEkle() {
        executeJavaScript("arguments[0].click();",
                btnGeldigiKisiEkle);
        return this;
    }

    @Step("TC kimlik No ekle")
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

    @Step("Ad doldur")
    public GelenEvrakKayitPage iletisimBilgisiAdDoldur(String ad) {
        txtAd.setValue(ad);
        return this;
    }

    @Step("Soyad doldur")
    public GelenEvrakKayitPage iletisimBilgisiSoyadDoldur(String soyad) {
        txtSoyad.setValue(soyad);
        return this;
    }

    @Step("Kaydet")
    public GelenEvrakKayitPage iletisimBilgisikaydet() {
        btnKaydetIletisimBilgisi.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleme(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
//        WebDriverRunner.getWebDriver()
//                .findElement(dosyaPath)
//                .sendKeys(pathToFile);
        return this;
    }

    @Step("Ek Bilgiler dosya ekleme açıklama alanı doldur")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleEkMetinDoldur(String aciklama) {
        txtEvrakEkTabViewEkMetni.sendKeys(aciklama);
        return this;
    }

    @Step("EkBilgiler dosya ekleme excel adi kontrol")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleDosyaAdiKontrol(String excelAdi) {
        $(byText(excelAdi)).shouldBe(Condition.visible);
//        String text = lblDosyaAdi.text();
//        System.out.println(text);
//        Assert.assertEquals(text, excelAdi);
        return this;
    }

    @Step("PDF Ust Yazi adi kontrol")
    public GelenEvrakKayitPage ustYaziPdfAdiKontrol(String ustYaziAdi) throws InterruptedException {
        String text = lblEklenenPdfUstYazi.text();
        System.out.println(text);
        Assert.assertEquals(text.contains(ustYaziAdi), true);
        return this;
    }

    @Step("Mail Ust Yazi adi kontrol")
    public GelenEvrakKayitPage ustYaziMailAdiKontrol(String ustYaziAdi) throws InterruptedException {
//        String text = lblEklenenMailUstYazi.text();
        lblEklenenMailUstYazi.shouldBe(Condition.text(ustYaziAdi));
//        System.out.println(text);
//        Assert.assertEquals(text.contains(ustYaziAdi), true);
        return this;
    }


    @Step("Birim butonu")
    public GelenEvrakKayitPage havaleIslemleriBirim() {
        btnBirim.click();
        return this;
    }

    @Step("Birim doldur")
    public GelenEvrakKayitPage havaleIslemleriBirimDoldur(String birim) {
        txtHavaleIslemleriBirim.selectLov(birim);
        return this;
    }

    @Step("Alanların güncellenebilirlik kontrolü")
    public GelenEvrakKayitPage evrakDetaylariAlanGuncellenebilirlikKontrolü() {
        txtEvrakBilgileriListKonuKodu.shouldBe(Condition.enabled);
//        txtEvrakBilgileriListKonu.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakTuru.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakDili.shouldBe(Condition.enabled);
        dateTxtEvrakBilgileriListEvrakTarihi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListGizlilikDerecesi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListKisiKurum.shouldBe(Condition.enabled);
        txtEvrakBilgileriListEvrakSayiTextAreaSag.shouldBe(Condition.enabled);
//        cmbEvrakBilgileriListGeldigiKisi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListEvrakGelisTipi.shouldBe(Condition.enabled);
        cmbEvrakBilgileriListIvedilik.shouldBe(Condition.enabled);
//        txtEvrakBilgileriListMiat.shouldBe(Condition.enabled);
//        txtEvrakBilgileriListAciklama.shouldBe(Condition.enabled);
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

    @Step("Evrak Detayi Aciklama doldur")
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

    @Step("PopUp close")
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
        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Gelen Evrak Kayıt]']"))
                .contextClick();

        if (kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }

    @Step("Üst yazı değiştirilsim mi?")
    public GelenEvrakKayitPage ustYaziDegistirilmisPopUpKontrol() {
       popUpUstYaziDegistirilme.shouldBe(Condition.visible);
        clickJs(btnUstYaziDegistirmeHayır);
        return this;
    }

    @Step("")
    public GelenEvrakKayitPage dosyaEkleTabTabloKontrolu(String dosyaAdi) {
//        boolean status = findElementOnTableByColumnInputInAllPages(tblDosyaEkle,columIndex,dosyaAdi).isDisplayed();
        tblDosyaEkle
                .filterBy(Condition.text(dosyaAdi)).shouldHaveSize(1);
//        Assert.assertEquals(status, true);
        return this;
    }

    @Step("Evrak Ekleri ekle butonana tıkla")
    public GelenEvrakKayitPage evrakEkleriDosyaEkle() {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    @Step("Ekrandaki alan kontrolleri")
    public GelenEvrakKayitPage alanKontrolleri() {

        btnUstYaziEkle.shouldBe(Condition.exist);
        lblUstyaziGoster.shouldBe(Condition.exist);
        lblUstyaziGizle.shouldBe(Condition.exist);
        btnTaramaHavuzundanEkle.shouldBe(Condition.exist);
        btnTarayicidanEkle.shouldBe(Condition.exist);
        btnTaramaArayuzundenEkle.shouldBe(Condition.exist);
        btnTaramaServisindenEkle.shouldBe(Condition.exist);

        return this;
    }

    @Step("Evrak turu alan kontrolü")
    public GelenEvrakKayitPage evrakTuruKontrol(String evrakTuru) {
        cmbEvrakBilgileriListEvrakTuru.shouldHave(Condition.text(evrakTuru));
//        Assert.assertEquals(cmbEvrakBilgileriListEvrakTuru.getText(), evrakTuru);
        return this;
    }

    @Step("Evrak Sayısı sol alan kontrolü")
    public GelenEvrakKayitPage evrakSayisiSolAlanKontrolu(String solAlan) {
        Assert.assertEquals(txtEvrakBilgileriListEvrakSayiTextAreaSol.getValue(), solAlan);
        return this;
    }

    @Step("Konu kodu sil")
    public GelenEvrakKayitPage konuKoduSil() throws InterruptedException {
        comboKonuKodu.clearLastSelectedLov();
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

    @Step("Popup kontrol")
    public GelenEvrakKayitPage popUpKontrol() {
        if (popUphavaleYeriSecmediniz.exists()) {
            String mesaj2 = "Havale yeri seçmediniz. Evrak kaydedildiğinde Kaydedilen Gelen Evraklar kutusuna düşecektir. İşleme devam etmek istiyor musunuz?";
            popUphavaleYeriSecmediniz.getText().equals(mesaj2);
            clickJs(btnHavaleYeriSecmedinizHayır);
        }
        return this;
    }

    @Step("Ust yazi gizle")
    public GelenEvrakKayitPage ustYaziGizle() {
        lblUstyaziGizle.click();
        return this;
    }

    @Step("Ust yazi gözter")
    public GelenEvrakKayitPage ustYaziGoster() {
        lblUstyaziGoster.click();
        return this;
    }
    @Step("Vekalet alan Ve Veren tablo vekalet alan seç")
    public GelenEvrakKayitPage vekeletAlanVerenTabloVekaletAlanveyaVerenSec(String isim) {
        tblVekaletVerenAlan
                .filterBy(Condition.text(isim)).first()
                .$("[id='evrakBilgileriForm:kullaniciBirimSecenekleriHavaleIcin_data'] td:nth-child(4) button").click();
        return this;
    }
}
