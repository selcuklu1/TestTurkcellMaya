package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
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
    SelenideElement txtEvrakBilgileriListMiat = $(By.id("evrakBilgileriForm:evrakBilgileriList:14:miatCalendar_input"));
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
    SelenideElement chkOtomatikHavale =$(By.id("evrakBilgileriForm:j_idt11601_input"));
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
//    SelenideElement txtAd = $(By.id("tgercekKisiHizliKayitDialogForm:adInputG"));
    SelenideElement txtAd = $(By.xpath("//table[@id='gercekKisiHizliKayitDialogForm:j_idt4251']/tbody/tr[4]/td[1]/input"));

    SelenideElement txtSoyad = $(By.xpath("//table[@id='gercekKisiHizliKayitDialogForm:j_idt4251']/tbody/tr[4]/td[2]/input"));
    SelenideElement mesaj = $("[#evrakKaydetBasariliDialog .ui-dialog-content]");

    SelenideElement lblDosyaAdi = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaAdi"));
    SelenideElement lblEklenenUstYazi = $(By.id("evrakBilgileriForm:eklendiYazisi"));
    SelenideElement btnBirim = $(By.id("evrakBilgileriForm:j_idt4283"));

    //Dosya ekleme path
    By dosyaPath = By.xpath("//input[@id='evrakBilgileriForm:evrakEkTabView:fileUploadButton_input']");
    SelenideElement ustYazi = $(By.xpath("//input[@class='ustYaziUploadClass']"));

//    Evrak Detayı sayfası objeleri

    SelenideElement btnevrakDetayiEvrakEkleri = $(By.id("inboxItemInfoForm:dialogTabMenuLeft:uiRepeat:1:cmdbutton"));
    SelenideElement btnEvrakDetayiFizikselEkEkle = $("a[href='#inboxItemInfoForm:evrakEkTabView:aciklamaEkleTab']");
    SelenideElement txtEvrakDetayiAciklama = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaTextArea"));
    SelenideElement btnEvrakDetayiEkle = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaEkleButton"));
    SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
    SelenideElement txtEvrakDetayiEvrakNo = $(By.id("inboxItemInfoForm:evrakBilgileriList:0:j_idt4632"));
    SelenideElement popUpPdfDegisiklik = $(By.xpath("//div[@id='inboxItemInfoForm:ustyaziDegistirisilMiDialog']"));
    SelenideElement btnEvrakDetayiPdfDegisiklikKabul = $(By.id("inboxItemInfoForm:ustyaziDegistirButton"));
    SelenideElement btnEvrakDetayiKaydetUyarisi = $(By.id("kaydetConfirmForm:kaydetEvetButton"));


    //endregion


    public GelenEvrakKayitPage otomatikHavaleSec(boolean secim){
        chkOtomatikHavale.setSelected(secim);
        return this;
    }

    public GelenEvrakKayitPage openPage() {
        new UstMenu().ustMenu("Gelen Evrak Kayıt");
        return this;
    }

    public GelenEvrakKayitPage ekBilgiFizikselEkEkle() throws InterruptedException {
        btnFizikselEkEkle.click();
        return this;
    }


    public GelenEvrakKayitPage ekBilgiFiltreAc() throws InterruptedException {
        btnEvrakEkleri.click();
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

    @Step("Kişi kurum seç")
    public GelenEvrakKayitPage kisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
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


    @Step("Geldiği kişi alanında görüntülenmediği kontrolu")
    public GelenEvrakKayitPage geldigiKurumDegerGoruntulemeKontrolu(String kurumAdi, Boolean shoudlBeExist) {
        Assert.assertEquals(comboGeldigiKurum.isLovValueSelectable(kurumAdi), shoudlBeExist);
        return this;
    }

    @Step("Geldiği kişi alanında görüntülenme kontrolu")
    public GelenEvrakKayitPage gercekKisiGoruntulenmeKontrolu(String tckn, String ad, String soyad) {

        String adSoyad = ad + " " + soyad;
        cmbGeldigiGercekKisi.selectLov(tckn);
        System.out.println("Gelen title:     " + cmbGeldigiGercekKisi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);

        Assert.assertEquals(cmbGeldigiGercekKisi.lastSelectedLovTitleText().contains(adSoyad), true);

        return this;
    }

    SelenideElement btnSecilenGeldigiKurumKaldir = $("div[id$='geldigiKurumLov:LovSecilen'] button");
    public GelenEvrakKayitPage geldigiKurumDoldurLovText(String geldigiKurum) {
        if(btnSecilenGeldigiKurumKaldir.isDisplayed())
            btnSecilenGeldigiKurumKaldir.click();
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
        txtEvrakBilgileriListMiat.sendKeys(miat);
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
        ElementsCollection col = cmbHavaleIslemleriBirim.type(birim).titleItems();
        col.filterBy(Condition.exactText(birim)).first().click();
        cmbHavaleIslemleriBirim.type(birim).titleItems()
                .filterBy(Condition.exactText(birim)).get(0).click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKisiDoldur(String kisi) {
        txtDagitimBilgileriKisi.sendKeys(kisi);
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
        btnEvrakEkTabViewEkle.click();
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
    public GelenEvrakKayitPage evrakEkleriDosyaEkleme(String pathToFile) {
        WebDriverRunner.getWebDriver()
                .findElement(dosyaPath)
                .sendKeys(pathToFile);
        return this;
    }

    @Step("Ek Bilgiler dosya ekleme açıklama alanı doldur")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleEkMetinDoldur(String aciklama) {
        txtEvrakEkTabViewEkMetni.sendKeys(aciklama);
        return this;
    }

    @Step("EkBilgiler dosya ekleme excel adi kontrol")
    public GelenEvrakKayitPage evrakEkleriDosyaEkleDosyaAdiKontrol(String excelAdi) {
        String text = lblDosyaAdi.text();
        System.out.println(text);
        Assert.assertEquals(text, excelAdi);
        return this;
    }

    @Step("Ust Yazi adi kontrol")
    public GelenEvrakKayitPage UstYaziAdiKontrol(String ustYaziAdi) {
        String text = lblEklenenUstYazi.text();
        System.out.println(text);
        Assert.assertEquals(text.contains(ustYaziAdi), true);
        return this;
    }

    @Step("Birim butonu")
    public GelenEvrakKayitPage havaleIslemleriBirim() {
        btnBirim.click();
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
    public GelenEvrakKayitPage evrakDetayiFizikselEkEkleTab(){
        btnEvrakDetayiFizikselEkEkle.click();
        return this;
    }
    @Step("Evrak Detayi Aciklama doldur")
    public GelenEvrakKayitPage evrakDetayiAciklamaDoldur(String aciklama){
        txtEvrakDetayiAciklama.sendKeys(aciklama);
        SelenideElement btnEvrakDetayiEkle = $(By.id("inboxItemInfoForm:evrakEkTabView:aciklamaEkleButton"));
        SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
        return this;
    }
    @Step("Evrak Detayi Ekle tıkla")
    public GelenEvrakKayitPage evrakDetayiEkle(){
       btnEvrakDetayiEkle.click();
        SelenideElement btnEvrakDetayiKaydet = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
        return this;
    }
    @Step("Evrak Detayi Kaydet tıkla")
    public GelenEvrakKayitPage evrakDetayiKaydet(){
        btnEvrakDetayiKaydet.click();
        return this;
    }
    @Step("Evrak Detayi EvrakNo text al")
    public String evrakDetayiEvrakNoTextAl(){
        return  txtEvrakDetayiEvrakNo.text();
    }
    @Step("Guncelleme Kontrolleri")
    public GelenEvrakKayitPage guncellenenAlanKontrolleri(String evrakTarihi,String evrakTuru,String gizlilikDerecesi){
        String txtEvrakTarihi = dateTxtEvrakBilgileriListEvrakTarihi.getValue();
        String txtEvrakTuru = cmbEvrakBilgileriListEvrakTuru.getSelectedValue();
        String txtGizlilikDerecesi = cmbEvrakBilgileriListGizlilikDerecesi.getSelectedValue();

        Assert.assertEquals(txtEvrakTarihi,evrakTarihi);
        Assert.assertEquals(txtEvrakTuru,evrakTuru);
        Assert.assertEquals(txtGizlilikDerecesi,gizlilikDerecesi);

        return this;
    }
    @Step("PopUp close")
    public GelenEvrakKayitPage evrakDetayiPdfDegisiklikpopUpClose(){
        popUpPdfDegisiklik.shouldBe(Condition.visible);
        btnEvrakDetayiPdfDegisiklikKabul.click();
        return this;
    }
    @Step("Evrak Detayi Kaydet PopUp Close")
    public GelenEvrakKayitPage evrakDetayiKaydetPopUpClose(){
        btnEvrakDetayiKaydetUyarisi.shouldBe(Condition.visible);
        btnEvrakDetayiKaydetUyarisi.click();
        return this;
    }

    @Step("Panel kapat")
    public GelenEvrakKayitPage panelKapat(Boolean kaydet){
        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Gelen Evrak Kayıt]']"))
                .contextClick();

        if(kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }


}
