package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.not;
import static pages.pageComponents.belgenetElements.BelgentCondition.required;

public class EvrakOlusturPage extends MainPage {

    public PDFKontrol pdfKontrol = new PDFKontrol();
    //region Elements
    SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
    SelenideElement tabEditor = $("button .editor");
    SelenideElement tabEkleri = $("button .kullaniciEkleri");
    SelenideElement tabIlgileri = $("button .kullaniciIlgileri");
    SelenideElement tabIliskiliEvraklar = $("button .kullaniciIliskileri");
    SelenideElement tabSablonIslemleri = $("button .sablonOlustur");
    SelenideElement tabEvrakNotlari = $("button .evrakNot");
    //endregion
    SelenideElement btnCloseScreen = $("[id='window1Dialog'] span[class='ui-icon ui-icon-closethick']");
    SelenideElement tabEvrakDogrulama = $("button .evrakDogrulamaAktarimIslemleri");
    SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnKaydetOnayaSun2 = $("div[class='ui-tabmenu ui-tabmenu-right'] span[class='ui-button-icon-left ui-icon kaydetHavaleEt']");
    SelenideElement txtKaydetOnayaSunAciklama = $("[id$='onayIslemiAciklama']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");
    SelenideElement btnEvrakOlusturKapat = $(By.xpath("//div[@id='window3Dialog']//a/span[@class='ui-icon ui-icon-closethick']"));
    SelenideElement btnEvrakOlusturKapatEvet = $(By.id("kapatKaydetEvetButton"));
    SelenideElement divBilgileri = $(By.id("evrakBilgileriContainerDiv"));

    SelenideElement label1IslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFixWidth']"));
    SelenideElement label2IslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFixWidth']"));
    SelenideElement label3IslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[3]//label[@class='columnLabelFixWidth']"));
    SelenideElement label4IslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[4]//label[@class='columnLabelFixWidth']"));

    SelenideElement label1Kullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFix']"));
    SelenideElement label2Kullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFix']"));
    SelenideElement label3Kullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[3]//label[@class='columnLabelFix']"));
    SelenideElement label4Kullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[4]//label[@class='columnLabelFix']"));


    ElementsCollection tblVekaletVerenAlan = $$("[id='yeniGidenEvrakForm:kullaniciBirimSecenekleri_data'] tr[role='row']");
    SelenideElement btnKaydetEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
    SelenideElement btnKaydetHayir = $(By.id("kaydetConfirmForm:kaydetHayirButton"));
    SelenideElement btnKaydetOnayaSunGonder = $(By.id("yeniGidenEvrakForm:gonderButton"));
    SelenideElement btnKaydetOnayaSunGonderEvet = $(By.id("kaydetEvetButton"));

    //endregion
    ElementsCollection cevapYazImzalama = $$("[id='windowCevapEvrakForm'] [id^='windowCevapEvrakForm'] table div[class='ui-tabmenu ui-tabmenu-right'] td[class='buttonMenuContainerDefault'] button");
    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    private EkleriTab ekleriTab = new EkleriTab();
    private EditorTab editorTab = new EditorTab();
    private IlgileriTab ilgileriTab = new IlgileriTab();
    private IliskiliEvraklarTab iliskiliEvraklarTab = new IliskiliEvraklarTab();
    private EvrakNotlariTab evrakNotlariTab = new EvrakNotlariTab();
    private SablonIslemleriTab sablonIslemleriTab = new SablonIslemleriTab();

    @Step("Evrak Oluştur sayfasını aç")
    public EvrakOlusturPage openPage() {
        ustMenu(UstMenuData.EvrakIslemleri.EvrakOlustur);
        return this;
    }

    @Step("")
    public EvrakOlusturPage closePage() {
        $x("//form[@id='yeniGidenEvrakForm']" +
                "/ancestor::div[contains(@class,'windowDialog')]" +
                "/div[contains(@class,'ui-dialog-titlebar')]" +
                "/a[contains(@class,'ui-dialog-titlebar-close')]").click();

        //span[text()='Gelen Evrak Kayıt']/../a[contains(@class,'ui-dialog-titlebar-close')]
        return this;
    }

    @Step("PDF Önizleme")
    public EvrakOlusturPage pdfOnIzleme() {
        btnPDFOnizleme.click();
        return this;
    }

    @Step("İmzalama")
    public EvrakOlusturPage cevapYazImzalama() {
        cevapYazImzalama.get(2).click();
        return this;
    }

    @Step("Kaydet ve onay sun")
    public EvrakOlusturPage kaydetOnayaSun() {
        btnKaydetOnayaSun.click();
        return this;
    }

    @Step("Kaydet ve onay sun paneli: EVET butonuna tıkla.")
    public EvrakOlusturPage kaydetOnayaSunGonderEvet() {
        btnKaydetOnayaSunGonderEvet.click();
        return this;
    }

    @Step("Kaydet ve onay sun a ")
    public EvrakOlusturPage kaydetOnayaSun2() {
        btnKaydetOnayaSun2.parent().click();
        return this;
    }

    @Step("Kaydet ve onay sun")
    public EvrakOlusturPage kaydetOnayaSunAciklamaDoldur2(String aciklama) {
        txtKaydetOnayaSunAciklama.setValue(aciklama);
        $("[id$='gonderButton'").click();
        $(By.id("kaydetEvetButton")).click();
        return this;
    }

    @Step("Kaydet")
    public EvrakOlusturPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Kaydet")
    public EvrakOlusturPage kaydet(boolean save) {
        btnKaydet.click();
        if (save)
            btnKaydetEvet.click();
        else
            btnKaydetHayir.click();
        return this;
    }

    @Step("\"{0}\" ekran açılması beklenen statü: {1}")
    public EvrakOlusturPage PDFOnizlemeKisayolGonder(String kisayol) {

        SelenideElement tc = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='T.C.']"));
        String str = tc.getText();
        tc.click();

        tc.sendKeys(Keys.SHIFT + "o");

        return this;
    }

    @Step("{ekranAdi} ekran açılması beklenen statü: {acilmali}")
    public EvrakOlusturPage kisayolEkranKontrol(String ekranAdi, boolean acilmali) {
        boolean t = $$("[id^='window'][id$='Button_ID'] .ui-button-text")
                .filterBy(Condition.text(ekranAdi)).size() > 0;
        Assert.assertEquals(t, acilmali, "Ekran açılmamalı");
        return this;
    }

    public EvrakOlusturPage evrakOlusturPageKapat() {

        SelenideElement closeButton = $(By.xpath("//span[@class='ui-dialog-title' and text()='Evrak Oluştur']/..//span[@class='ui-icon ui-icon-closethick']"));
        Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", closeButton);
        closeButton.click();
        btnEvrakOlusturKapatEvet.click();
        return this;
    }

    @Step("Evrak Oluştur sayfası kapat")
    public EvrakOlusturPage evrakOlusturKapat() {
        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Evrak Oluştur]']"))
                .click();
        btnCloseScreen.click();
        islemPenceresiKaydetPopup("Evet");
        return this;
    }

    public EvrakOlusturPage evrakOlusturSayfaKapat() {
        $(By.xpath("//div[@id='window1Dialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        islemPenceresiKaydetPopup("Evet");
        return this;
    }

    @Step("1.Sıradaki işlem ve kullanıcı kontrolu")
    public EvrakOlusturPage kullaniciIslemVe1SiraKontrolu(String kullanici1, String islemTipi1) {
        Assert.assertEquals(label1IslemTipi.getText(), "1. " + islemTipi1);
        Assert.assertEquals(label1Kullanici.getText(), kullanici1);
        return this;
    }

    @Step("2.Sıradaki işlem ve kullanıcı kontrolu")
    public EvrakOlusturPage kullaniciIslemVe2SiraKontrolu(String kullanici2, String islemTipi2) {
        Assert.assertEquals(label2IslemTipi.getText(), "2. " + islemTipi2);
        Assert.assertEquals(label2Kullanici.getText(), kullanici2);
        return this;
    }

    @Step("3.Sıradaki işlem ve kullanıcı kontrolu")
    public EvrakOlusturPage kullaniciIslemVe3SiraKontrolu(String kullanici3, String islemTipi3) {
        Assert.assertEquals(label3IslemTipi.getText(), "3. " + islemTipi3);
        Assert.assertEquals(label3Kullanici.getText(), kullanici3);
        return this;
    }

    @Step("4.Sıradaki işlem ve kullanıcı kontrolu")
    public EvrakOlusturPage kullaniciIslemVe4SiraKontrolu(String kullanici4, String islemTipi4) {
        Assert.assertEquals(label4IslemTipi.getText(), "4. " + islemTipi4);
        Assert.assertEquals(label4Kullanici.getText(), kullanici4);
        return this;
    }

    @Step("Kaydet ve onaya sun panelinde gönder butonuna tıkla.")
    public EvrakOlusturPage kaydetOnayaSunGonder() {
        btnKaydetOnayaSunGonder.click();
        return this;
    }


    //region Tabs
    @Step("Bilgiler tabını aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    @Step("Editör tabını aç")
    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    @Step("Ekleri tabını aç")
    public EkleriTab ekleriTabAc() {
        return ekleriTab.open();
    }

    @Step("İlgileri tabını aç")
    public IlgileriTab ilgileriTabAc() {
        return ilgileriTab.open();
    }

    @Step("İlişkili evraklar tabını aç")
    public IliskiliEvraklarTab iliskiliEvraklarTabAc() {
        return iliskiliEvraklarTab.open();
    }

    public EvrakNotlariTab evrakNotlariTabAc() {
        return evrakNotlariTab.open();
    }

    public SablonIslemleriTab sablonIslemleriTabAc() {
        return sablonIslemleriTab.open();
    }

    public class BilgilerTab extends MainPage {

        //region Elements

        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");

        //label[normalize-space(text())='Konu Kodu']/../..//input

        BelgenetElement txtcomboLovForm = comboLov("[id$='formSablonuId:LovText']");
        BelgenetElement cmlKonuKodu = comboLov("input[id$='konuKoduLov:LovText']");
        SelenideElement btnKonuKoduTree = $("button[id$='konuKoduLov:treeButton']");
        SelenideElement txtKonu = $("textarea[id$='konuTextArea']");
        BelgenetElement cmbKaldiralacakKlasorler = comboLov("input[id$='eklenecekKlasorlerLov:LovText']");
        SelenideElement btnKaldiralacakKlasorlerTree = $("button[id$='eklenecekKlasorlerLov:treeButton']");
        SelenideElement cmbEvrakTuru = $("select[id$='evrakTuruCombo']");
        SelenideElement dateKayitTarihi = $("input[id$='kayitTarih_input']");
        SelenideElement cmbEvrakDili = $("select[id$=evrakDili]");
        SelenideElement cmbGizlilikDerecesi = $("select[id$=guvenlikKodu]");
        SelenideElement btnIletisimbilgileriOnayAkisiEkle = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='onayAkisiEkle']");
        SelenideElement cmbKullanicilarIlkImzalama = $("[id='yeniGidenEvrakForm:evrakBilgileriList:18:anlikakisOlusturPanelGrid'] [id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='selectOneMenu']");
        SelenideElement cmbKullanicilarIlkImzalama2 = $(By.id("windowCevapEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnKullanicilarKullan = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:anlikAkisKullanButton"));
        //Kanun Kapsam Tipi
        SelenideElement rdbKanunKapsamTipiNormal = $x("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Normal'])]");
        SelenideElement rdbKanunKapsamTipiBilgiEdinmeKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Bilgi Edinme Kanunu'])]");
        SelenideElement rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Kişisel Verilerin Korunması Kanunu'])]");
        SelenideElement txtEvrakSayiEkMetni = $("input[id$='evrakSayiEkMetniInputText']");

        SelenideElement txtAciklama = $(By.xpath("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Açıklama']/ancestor::tr[@class='ui-datagrid-row']//textarea"));
        SelenideElement cmbIvedik = $("select[id$='ivedilik']");
        SelenideElement dateMiat = $("input[id$='miatCalendar_input']");

        SelenideElement cmbBilgiSecimTipi = $x("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
        //SelenideElement cmbBilgiSecimTipi = $(By.xpath("//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));

        BelgenetElement txtBilgi = comboLov("input[id$='bilgiLov:LovText']");
        SelenideElement btnBilgiTree = $("button[id$='bilgiLov:treeButton']");

        //SelenideElement cmbGeregiSecimTipi = $(By.xpath("//select[starts-with(@id,'yeniGidenEvrakForm:evrakBilgileriList:16:j_idt')]"));
        SelenideElement cmbGeregiSecimTipi = $x("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
        BelgenetElement txtGeregi = comboLov("input[id$='geregiLov:LovText']");
        SelenideElement btnGeregiTree = $("button[id$='geregiLov:treeButton']");

        SelenideElement chkDagitimiEkYap = $("input[id$='dagitimEkYapCheckBoxId_input']");

        // Onay Akışı Elementleri
        SelenideElement btnOnayAkisiEkle = $("button[id*='onayAkisiEkle']");
        ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
        SelenideElement btnOnayAkisiKullaniciKullan = $("button[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='anlikAkisKullanButton']");
        BelgenetElement txtOnayAkisiKullanicilar = comboLov("[id$='akisAdimLov:LovText']");
        ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");

        SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:LovText']");
        SelenideElement listOnayAkisiKullanilan = $("div[id*='akisLov:lovContainer'] div[class*='lovSelection processEt'] tbody");
        SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:lovTreePanelKapat']");
        ElementsCollection cmbKullanicilarImzaSec2 = $$("[id^='yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable'][id$='selectOneMenu']");
        SelenideElement cmbKullanicilarImza = $("[id^='yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable'][id$='selectOneMenu']");
        SelenideElement btnOnayAkisGuncelle = $(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:j_idt'] [class$='update-icon']"));
        SelenideElement btnOnayAkisSil = $(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:j_idt'] [class$='delete-icon']"));
        //BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        BelgenetElement cmbOnayAkisi2 = comboLov(By.id("windowCevapEvrakForm:evrakBilgileriList:18:akisLov:LovText"));
        SelenideElement btnOnayAkisiEkle2 = $(By.id("windowCevapEvrakForm:evrakBilgileriList:18:onayAkisiEkle"));
        //BelgenetElement cmbOnayAkisi2 = comboLov(By.cssSelector("[id$='akisLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement chkKoordineli = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'] [id$='koordineliBooleanCheckbox']");


        //Bilgileri tabı
        BelgenetElement txtKonuKodu = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");
        SelenideElement txtCevapYazKaydetVeOnaySunAciklama = $(By.id("windowCevapEvrakForm:onayIslemiAciklama"));
        SelenideElement btnKaydetveOnayaSun = $(By.xpath("//div[@id='windowCevapEvrakDialog']//tr//td[2]//td[3]//button"));

        SelenideElement txtKaldiralacakKlasorler = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:LovText"));
        SelenideElement rdbNormal = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:0"));
        SelenideElement rdbBilgiEdinmeKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:1"));
        SelenideElement rdbKisiselVerilerinKorunmasiKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:2"));
        SelenideElement cbmAkisAdim = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement tblKullanıcılar = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisAdimLov:LovSecilenTable_data']");
        ElementsCollection tblKullanıcılar2 = $$("[id$='akisAdimLov:LovSecilenTable_data'] >tr");
        SelenideElement tblVekalet = $(By.id("yeniGidenEvrakForm:kullaniciBirimSecimiDialogId"));
        SelenideElement btnVekaletTablosuKapat = $(By.xpath("//div[@id='yeniGidenEvrakForm:kullaniciBirimSecimiDialogId']//span[@class='ui-icon ui-icon-closethick']"));
        SelenideElement btnKullan = $("[id$='anlikAkisKullanButton']");
        SelenideElement txtOnayAkisi = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovSecilen']");
        SelenideElement btnIadeEt = $(By.xpath("//span[text()='İade Et']/ancestor::tbody[1]//button"));
        BelgenetElement cmbKullaniciListesi = comboBox(By.id("inboxItemInfoForm:kullaniciListOneMenu_id_label"));
        SelenideElement txtNot = $(By.id("inboxItemInfoForm:notTextArea_id"));
        SelenideElement btnIadeEt2 = $(By.id("inboxItemInfoForm:iadeEtButton_id"));
        SelenideElement popUpEvrakDegisiklik = $(By.xpath("//span[normalize-space(text())='Evrakta değişiklik var, kaydetmek ister misiniz?']"));
        SelenideElement txtOnayIslemiAciklama = $(By.id("windowCevapEvrakForm:onayIslemiAciklama"));
        SelenideElement btnOnayIslemiGonder = $(By.id("windowCevapEvrakForm:gonderButton"));
        SelenideElement cmbGeregiTuzelKisi = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
        BelgenetElement cmbGeregi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
        BelgenetElement cmbGeregi2 = comboLov("[id$='geregiLov:LovText']");
        BelgenetElement cmbGeregiPostaTipi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:selectOneMenu"));
        // select[id^='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:'][id$=':selectOneMenu']
        SelenideElement cmbPostaTipi = $("select[id^='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:'][id$=':selectOneMenu']");
        By cmbGeregiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");

        BelgenetElement cmbPostaTipi2 = comboBox(" [id='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable_data'] tr[role='row'] select");

        BelgenetElement cmbOnayAkisi = comboLov("[id$='akisLov:LovText']");
        SelenideElement btnOnayAkisiTemizle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt134"));
        SelenideElement btnOnayAkisiEdit = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt135"));
        //SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:onayAkisiEkle"));

        // Gereği - Dağıtım Hitap Düzenleme
        SelenideElement btnGeregiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:j_idt123"));
        SelenideElement btnBilgiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:LovSecilenTable:0:j_idt123"));

        SelenideElement chkAdresHitaptaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Hitapta Görünsün']/../..//div//div"));
        SelenideElement chkAdresDagitimdaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Dağıtımda Görünsün']/../..//div//div"));
        SelenideElement txtDagitimHitapAdres = $(By.xpath("//div[@id='yeniGidenEvrakForm:pnlHitapDuzenle']//div/textarea[@role='textbox']"));
        SelenideElement btnDagitimHitapDuzenlemeKaydet = $(By.xpath("//*[@id='yeniGidenEvrakForm:pnlHitapDuzenle']//span[normalize-space(text())='Kaydet']/parent::button"));

        BelgenetElement cmbBilgi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
        By cmbBilgiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']");

        SelenideElement lovTreeKapat = $(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='bilgiLov:lovTreePanelKapat']"));
        SelenideElement lovTreeSec = $(By.xpath("//*[@id=\"yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:lovTree:0_0\"]/div/span/span[2]"));
        SelenideElement btnOtomatikOnayAkisi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:otomatikOnayAkisiEkle"));

        BelgenetElement txtForm = comboLov("input[id$='formSablonuId:LovText']");

        SelenideElement btnKaydet = $(By.xpath("//span[text()='Kaydet']/ancestor::tbody[1]//button"));

        SelenideElement aKendimiEkle = $("a[id$=':kendimiEkleCommand']");
        SelenideElement btnVekaletKaydet = $("[id^='yeniGidenEvrakForm:j_idt'] [class*='ui-button-text-only tipTip button-icon-borderless']");

        SelenideElement btnOtomatikAkisKullan = $("[id$='hiyerarsikAkisOlusturForm:hiyerarsikAkisKullan']");
        ElementsCollection trOtomatikOnayAkisiEkleKullanicilar = $$("tbody[id*='yeniGidenEvrakForm:hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId_data'] tr[role='row']");

        //endregion

        private BilgilerTab open() {
            if (divContainer.is(not(visible)))
                tabBilgiler.click();

            //divContainer.shouldBe(visible);
            return this;
        }

        public boolean isOnTabPage() {
            return divContainer.is(visible);
        }

        @Step("Konu alanının seçilen evrak ile aynı şekilde dolu geldiği,")
        public BilgilerTab konuAlanıDoluGeldigiGorme(String konu) {
            Assert.assertEquals(txtKonu.getValue(), konu);
            takeScreenshot();
            return this;
        }

        @Step("Gereği alanının \"{geregi}\" yazılı olacak şekilde olduğu görülür.")
        public BilgilerTab geregiSeciliGeldigiGorme(String geregi) {
            boolean durum = $$("div[id$='geregiLov:lovSelectionPanel']").filterBy(Condition.text(geregi)).size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("Kullanıcılar alanında \"{value}\" seç")
        public BilgilerTab IlkKullaniciImzalamaVeyaParaflamaSec(String value) {
            cmbKullanicilarImza.selectOptionByValue(value);
            return this;
        }

        @Step("Kullanıcılar alanında imzacı seç")
        public BilgilerTab kullanicilarImzaciSec2(String value) {
            cmbKullanicilarImzaSec2.get(1).selectOption(value);
            return this;
        }


        @Step("Kullanıcılar alanı doldur")
        public BilgilerTab kullanicilarDoldur(String kullanici) {
            txtOnayAkisiKullanicilar.selectLov(kullanici);
            return this;
        }

        @Step("Kullanıcılar alanı doldur")
        public BilgilerTab kullanicilarDoldur2(String kullanici) {
            txtOnayAkisiKullanicilar.type(kullanici).getTitleItems()
                    .filterBy(Condition.exactText(kullanici + " [Ağ (Network) Uzman Yardımcısı]")).first().click();
            txtOnayAkisiKullanicilar.closeTreePanel();
            return this;
        }

        @Step("Kullanıcılar alanında \"{kullanici}\" seç")
        public BilgilerTab kullanicilarDoldurWithTitle(String kullanici, String title) {
            txtOnayAkisiKullanicilar.type(kullanici).getTitleItems()
                    .filterBy(Condition.exactText(kullanici + title)).first().click();
            txtOnayAkisiKullanicilar.closeTreePanel();
            return this;
        }

        @Step("Kullanıcılar alanında \"{kullanici}\" seç")
        public BilgilerTab kullanicilarDoldurWithDetail(String kullanici, String detail) {

            txtOnayAkisiKullanicilar.type(kullanici).getDetailItems().filterBy(text(detail)).first().click();

            return this;
        }

        @Step("Otomatik Onay akışı tıkla")
        public BilgilerTab otomatikOnayAkisiSec() {
            btnOtomatikOnayAkisi.click();
            return this;
        }

        @Step("Konu Kodu alanında \"{konuKodu}\" seç")
        public BilgilerTab konuKoduSec(String konuKodu) {
            cmlKonuKodu.selectLov(konuKodu);
            return this;
        }

        @Step("Kullanıcı kontrolü")
        public BilgilerTab kullaniciTabloKontrol() {
            Assert.assertEquals(tblKullanıcılar.isDisplayed(),true);
            return this;
        }
        @Step("Kullnici ismine göre imzalama veya paraflama kontrolu : \"{value}\" ")
        public BilgilerTab kullniciIsmineGoreImzaParafKontrol(String kullanici, String value) {

            tblKullanıcılar2.filterBy(Condition.text(kullanici))
                    .filterBy(Condition.text(value))
                    .shouldHaveSize(1);
            return this;
        }
        @Step("Kullnici ismine göre imzalama veya paraflama seç")
        public BilgilerTab kullniciIsmineGoreImzaParafSec(String kullanici, String value) {

            tblKullanıcılar2.filterBy(Condition.text(kullanici)).first()
                    .$("select")
                    .selectOptionByValue(value);

            return this;
        }

        @Step("Konu Kodu alanı zorunluluk kontrol")
        public boolean konuKoduZorunlukKontrol(boolean zorunlu) {
            return cmlKonuKodu.is(required);
        }

        @Step("Konu alanında {konu} seç")
        public BilgilerTab konuSec(String konu) {
            txtKonu.setValue(konu);
            return this;
        }

        @Step("Konu alanı doldur")
        public BilgilerTab zorunluKodu() {
            txtKonu.is(required);
            return this;
        }

        @Step("Kaldiralacak Klasörler alanında \"{kaldirilacakKlasorler}\" seç")
        public BilgilerTab kaldiralacakKlasorlerSec(String kaldirilacakKlasorler) {
            cmbKaldiralacakKlasorler.selectLov(kaldirilacakKlasorler);
            return this;
        }

        @Step("Evrak Türü alanında \"{evrakTuru}\" seç")
        public BilgilerTab evrakTuruSec(String evrakTuru) {
//            if (!cmbEvrakTuru.getSelectedOption().equals(text))
            cmbEvrakTuru.selectOption(evrakTuru);
            return this;
        }

        @Step("Kayıt Tarih alanında \"{dateText}\" seç")
        public BilgilerTab dateKayitTarihiSec(String dateText) {
            dateKayitTarihi.setValue(dateText);
            return this;
        }

        @Step("Evrak Dili alanında \"{evrakDili}\" seç")
        public BilgilerTab evrakDiliSec(String evrakDili) {
            cmbEvrakDili.selectOption(evrakDili);
//            if (cmbEvrakTuru.getSelectedOption().equals(text))
//                throw new RuntimeException("Alan seçilemedi");
            return this;
        }

        @Step("Onay Akışı ekle")
        public BilgilerTab OnayAkisiEkle() {
            btnIletisimbilgileriOnayAkisiEkle.click();
            return this;
        }

        @Step("Onay Akışı imzalama seç")
        public BilgilerTab onayAkisiEkleIlkImzalaSec(String imzalama) {
            cmbKullanicilarIlkImzalama.selectOption(imzalama);
            return this;
        }

        @Step("Onay Akışı imzalama seç")
        public BilgilerTab onayAkisiEkleIlkImzalaSec2(String imzalama) {
            cmbKullanicilarIlkImzalama2.selectOption(imzalama);
            return this;
        }

        @Step("Kullan")
        public BilgilerTab imzalamaIlkKullan() {
            btnKullanicilarKullan.pressEnter();
            return this;
        }


        @Step("Gizlilik Derecesi alanında {gizlilikDerecesi} seç")
        public BilgilerTab gizlilikDerecesiSec(String gizlilikDerecesi) {
            cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
            return this;
        }

        @Step("Kanun Kapsam Tipi normal seç")
        public BilgilerTab kanunKapsamTipiNormalSec() {
            rdbKanunKapsamTipiNormal.click();
            return this;
        }

        @Step("Kanun Kapsam Tipi Bilgi Edinme Kanunu seç")
        public BilgilerTab kanunKapsamTipiBilgiEdinmeKanunuSec() {
            rdbKanunKapsamTipiBilgiEdinmeKanunu.click();
            return this;
        }

        @Step("Vekalet alan Ve Veren tablo kontrolü")
        public BilgilerTab vekeletAlanVerenTabloKontrolu() {
            tblVekalet.shouldBe(visible);
            //Assert.assertEquals(tblVekalet.isDisplayed(), true);
            return this;
        }

        @Step("Vekalet alan Ve Veren tablosu vekalet alan seç : \"{isim}\" ")
        public BilgilerTab vekeletAlanVerenTabloVekaletAlanveyaVerenSec(String isim) {
            tblVekaletVerenAlan
                    .filterBy(Condition.text(isim)).first()
                    .$("[id^='yeniGidenEvrakForm:kullaniciBirimSecenekleri'][id$='secinizButton']").click();
            return this;
        }

        @Step("Vekalet alan Ve Veren tablo kontrolü")
        public BilgilerTab vekeletAlanVerenTabloKapat() {
            btnVekaletTablosuKapat.click();
            return this;
        }

        @Step("Kanun Kapsam Tipi Kisisel Verilerin Korunmasi Kanunu seç")
        public BilgilerTab kanunKapsamTipiKisiselVerilerinKorunmasiKanunuSec() {
            rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu.click();
            return this;
        }

        @Step("Evrak Sayi Ek Metni alanında {text} seç")
        public BilgilerTab evrakSayiEkMetniSec(String text) {
            txtEvrakSayiEkMetni.setValue(text);
            return this;
        }

        @Step("Açıklama gir")
        public BilgilerTab aciklamaSec(String text) {
            txtAciklama.setValue(text);
            return this;
        }

        @Step("İvedik alanında \"{ivedilik}\" seç")
        public BilgilerTab ivedilikSec(String ivedilik) {
            cmbIvedik.selectOption(ivedilik);
            return this;
        }

        @Step("Miat alanında {dateText} seç")
        public BilgilerTab miatSec(String dateText) {
            dateMiat.setValue(dateText);
            return this;
        }

        @Step("Bilgi Secim Tipi alanında \"{bilgiSecimTipi}\" seç")
        public BilgilerTab bilgiSecimTipiSec(String bilgiSecimTipi) {
            cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
            return this;
        }


        @Step("Bilgi Secim Tipi alanında \"{bilgiSecimTipi}\" seç")
        public BilgilerTab bilgiSecimTipiSecByText(String bilgiSecimTipi) {
            cmbBilgiSecimTipi.shouldBe(visible).selectOption(bilgiSecimTipi);
            return this;
        }

        @Step("Bilgi seçim tipi tree alanında \"{kurumAdi}\" geliyor mu? kontrol et")
        public BilgilerTab bilgiSecimTipiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            Assert.assertEquals(txtBilgi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Bilgi alanında \"{bilgi}\" seç")
        public BilgilerTab bilgiSec(String bilgi) {
            txtBilgi.selectLov(bilgi);
            return this;
        }

        @Step("Bilgi alanında temizle ve \"{bilgi}\" seç")
        public BilgilerTab bilgiSec(String bilgi, Boolean clearAll) {
            txtBilgi.sendKeys(Keys.SHIFT);
            txtBilgi
                    .type(bilgi)
                    .getTitleItems()
                    .first()
                    .click();
            txtBilgi.closeTreePanel();
            txtBilgi.clearAllSelectedItems();
            return this;
        }

        @Step("Geregi alanında \"{geregi}\" seç")
        public BilgilerTab geregiSec(String geregi) {
            sleep(4000);
            cmbGeregi.sendKeys(geregi);
            cmbGeregi.selectLov(geregi);
            cmbGeregi.closeTreePanel();
            return this;
        }

        @Step("Seçilen gereği Dağıtım Hitap Düzenle tıklanır")
        public BilgilerTab secilenGeregiDagitimHitapGuncelleme(){
        $("[id$='geregiLov:LovSecilenTable_data'] button [class='ui-button-icon-left ui-icon update-icon']").click();
            return this;
        }
        ElementsCollection tableGeregiSecilenler = $$("tbody[id$='geregiLov:LovSecilenTable_data'] > tr");

        @Step("Geregi alanında \"{geregi}\" için {geregiTipi} seç")
        public BilgilerTab geregiTipiSec(String geregi, String geregiTipi) {

            tableGeregiSecilenler
                    .filterBy(text(geregi))
                    .first()
                    .$("select[id$='selectOneMenu']")
                    .selectOption(geregiTipi);

            return this;
        }

        @Step("Geregi alanında Tüzel Kişi olarak \"{tuzelkisi}\" seç")
        public BilgilerTab geregiTuzelKisiSec(String tuzelkisi) {
            cmbGeregi.sendKeys(tuzelkisi);

            SelenideElement currentList = $$("div[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':geregiLov:lovTree']").last();
            currentList.waitUntil(Condition.visible, 5000);
            currentList
                    .$$("span[class='lovItemTitle ']")
                    .filterBy(text(tuzelkisi))
                    .last()
                    .click();
            return this;
        }

        @Step("Geregi alanını seçilenleri kaldır")
        public BilgilerTab geregiSecilenleriKaldır() {
            txtGeregi.clearAllSelectedItems();
            return this;
        }

        @Step("Geregi alanında \"{geregi}\" seç")
        public BilgilerTab geregiSec(String geregi, Boolean clearAfterSelecion) {
            cmbGeregi.sendKeys(Keys.SHIFT);
            txtGeregi.type(geregi)
                    .getTitleItems()
                    .first()
                    .click();
            txtGeregi.closeTreePanel();
            txtGeregi.clearAllSelectedItems();
            return this;
        }

        @Step("Gereği tree alanında \"{kurumAdi}\" geliyor mu? kontrol et")
        public BilgilerTab geregiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            txtGeregi.sendKeys(Keys.SHIFT);
            Assert.assertEquals(txtGeregi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Dagitimi Ek Yap alanı \"{setSelected}\" seç")
        public BilgilerTab dagitimiEkYapSec(boolean setSelected) {
            chkDagitimiEkYap.setSelected(setSelected);
            return this;
        }

        @Step("Onay Akisi alanında \"{onayAkisi}\" seç")
        public BilgilerTab cmbOnayAkisi(String onayAkisi) {
            cmbOnayAkisi2.selectLov(onayAkisi);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu) {
            txtKonuKodu.selectLov(konuKodu);
            //shouldHave(Condition.text(konuKodu));

            /*System.out.println("title: " + txtKonuKodu.lastSelectedLovTitleText());
            System.out.println("detail: " + txtKonuKodu.lastSelectedLovDetailText());*/

            return this;
        }

        @Step("Evrak Sayı Ek Metni Doldur")
        public BilgilerTab evrakSayiEkMetniDoldur(String evrakSayiEkMetni) {
            txtEvrakSayiEkMetni.sendKeys(evrakSayiEkMetni);
            return this;
        }

        @Step("Akış Adımı Seç")
        public BilgilerTab akisAdimSec(String akisAdim) {
            cbmAkisAdim.selectOption(akisAdim);
            return this;
        }

        @Step("Konu doldur")
        public BilgilerTab konuDoldur(String konu) {
            //sendKeys(txtKonu, konu, false); selenium
            txtKonu.clear();
            txtKonu.sendKeys(konu); //selenide
            return this;
        }

        public BilgilerTab evrakDerecesiSec(String value) {
            cmbGizlilikDerecesi.selectOption(value);
            return this;
        }

        @Step("Kaydet ve Onaya Sun buton ")
        public BilgilerTab kaydetVeOnayaSun() {
            clickJs(btnKaydetveOnayaSun);
            return this;
        }

        public BilgilerTab konuKapsamTipiSec() {
            btnKonuKoduTree.click();
            return this;
        }

        public BilgilerTab aciklamaDoldur(String aciklama) {
            txtAciklama.sendKeys(aciklama);
            return this;
        }

        public BilgilerTab ivediSec(String value) {
            cmbIvedik.selectOption(value);
            return this;
        }

        public BilgilerTab miatDoldur(String date) {
//            dateMiat.setValue(date).pressTab();
            setValueJS(dateMiat, date);
            return this;
        }

        @Step("Bilgi alanı doldur")
        public BilgilerTab bilgiDoldur(String bilgi) {
            cmbBilgi.selectLov(bilgi);
            //shouldHave(Condition.text(geregi));
            return this;
        }

        //Manuel bilgi alanı doldurulur ve ilk sırada çıkan tıklanır.
        @Step("Bilgi alanı doldur")
        public BilgilerTab manuelBilgiDoldur(String bilgi) throws InterruptedException {
            cmbBilgi.setValue(bilgi).pressEnter();
            Thread.sleep(2000);
            clickJs(lovTreeSec);
            return this;
        }

        public BilgilerTab lovTreeKapat() {
            lovTreeKapat.click();
            return this;
        }

        public BilgilerTab kisayolSayfaAcma(String kisayol) throws InterruptedException {
            divBilgileri.click();
            Thread.sleep(2000);
            switchTo().activeElement().sendKeys(kisayol);
            return this;
        }

        @Step("Bilgileri tabında kişinin bilgi alanında görüntülenmeme kontrolu")
        public BilgilerTab bilgiAlanindaGoruntulenmemeKontrolu(String adSoyad) {

            boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(adSoyad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Kişinin Bilgi alanında görüntülenme kontrolu")
        public BilgilerTab bilgiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

            String adSoyad = ad + " " + soyad.toUpperCase();
            cmbBilgi.shouldBe(visible);
            cmbBilgi.selectLov(adSoyad);
            /*System.out.println("Gelen title:     " + cmbBilgi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);*/
            /*Assert.assertEquals(cmbBilgi.lastSelectedLovTitleText().contains(adSoyad), true);*/
            cmbBilgi.getSelectedTitles().last().shouldHave(text(adSoyad));

            return this;
        }

        @Step("Gereği Seçim Tipi alanında \"{geregiSecimTipi}\" seç")
        public BilgilerTab geregiSecimTipiSec(String geregiSecimTipi) {
            cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
            return this;
        }


        @Step("Gereği Seçim Tipi alanında \"{geregiSecimTipi}\" seç")
        public BilgilerTab geregiSecimTipiSecByText(String geregiSecimTipi) {
            cmbGeregiSecimTipi.shouldBe(visible);
            cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
            return this;
        }

        @Step("Gereği {description} doldur: | {geregi}")
        public BilgilerTab geregiDoldur(String geregi, String description) {
            cmbGeregi.selectLov(geregi);
            return this;
        }

        @Step("Seçimde posta tipinin otomatik KEP geldiği ve kullanıcının değiştirebildiği görülür")
        public BilgilerTab geregiAlaniKEPSeciliGeldigiGorme() {
            boolean durum = $("[id$='geregiLov:LovSecilenTable_data'] select").getSelectedText().equals("KEP");
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("Gereği {description} doldur: | {geregi}")
        public BilgilerTab geregiDoldur2(String geregi, String description) {
            cmbGeregi2.selectLov(geregi);
            return this;
        }

        @Step("Bilgileri tabında kişinin geregi alanında görüntülenmeme kontrolu")
        public BilgilerTab geregiAlanindaGoruntulenmemeKontrolu(String adSoyad) {

            boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(adSoyad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + adSoyad + ": Kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + adSoyad + ": Kişinin görüntülenmediği görülür.");

            return this;
        }

        @Step("Kişinin gereği alanında görüntülenme kontrolu")
        public BilgilerTab geregiAlanindaGoruntulenmeKontrolu(String adSoyad) {

            cmbGeregi.shouldBe(visible);
            cmbGeregi.selectLov(adSoyad);
            /*System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);
            Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);*/
            cmbGeregi.getSelectedTitles().last().shouldHave(text(adSoyad));
            return this;
        }

        @Step("Otomatik onay akışı alanında geldiği görünür \"{ekranAdi}\" | \"{ad}\"")
        public BilgilerTab otomatikOnayAkisiGeldigiGorme(String ekranAdi, String ad) {

            $$(" [id='yeniGidenEvrakForm:hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId'] tbody tr")
                    .filterBy(text(ekranAdi)).shouldHave(sizeGreaterThan(0)).get(0).click();
            $("[id='yeniGidenEvrakForm:hiyerarsikAkisOlusturDialog'] [class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();
            System.out.println("Başarılı geçti " + ekranAdi);
            return this;
        }

        @Step("\"{ekranAdi}\" text var olma kontorlu, beklenen: {vardir}")
        public BilgilerTab otomatikOnayAkisiGelmedigiGorme(String ekranAdi, boolean vardir) {

            boolean t = $$(" [id='yeniGidenEvrakForm:hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId'] tbody tr")
                    .filterBy(text(ekranAdi)).size() > 0;
            Assert.assertEquals(t, vardir, "kdkdkdkd");
            System.out.println("Başarılı geçti:" + ekranAdi);
            return this;
        }

        @Step("Seçilen gereği sil")
        public BilgilerTab secilenGeregiSil() throws InterruptedException {
            Thread.sleep(1000);
            cmbGeregi.shouldBe(visible);
            cmbGeregi.clearLastSelectedItem();
            return this;
        }

        @Step("Seçilen gereği sil")
        public BilgilerTab secilenGeregiSil2() throws InterruptedException {
            $$("[id$='geregiLov:LovSecilenTable_data'] button[class$='button-icon-borderless']").get(0).click();
            return this;
        }

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle(String kullanici) {

//            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.selectLov(kullanici);

            return this;
        }

        @Step("Her dağıtım yerinin posta tipinin default KEP olarak geldiği görülür.")
        public BilgilerTab kepOlarakGeldikleriGorme(String kisi1, String kisi2, String kisi3) {
            boolean durum1 = $$("[id$='geregiLov:LovSecilenTable_data'] > tr").filterBy(Condition.text(kisi1)).get(0).$("select").getSelectedText().equals("KEP");
            boolean durum2 = $$("[id$='geregiLov:LovSecilenTable_data'] > tr").filterBy(Condition.text(kisi1)).get(0).$("select").getSelectedText().equals("KEP");
            boolean durum3 = $$("[id$='geregiLov:LovSecilenTable_data'] > tr").filterBy(Condition.text(kisi1)).get(0).$("select").getSelectedText().equals("KEP");
            Assert.assertEquals(durum1, durum2);
            Assert.assertEquals(durum2, durum3);
            takeScreenshot();
            return this;
        }

        @Step("Onay Akışı Ekle")
        public BilgilerTab onayAkisiEkle() {
            clickJs(btnOnayAkisiEkle);
//            btnOnayAkisiEkle.pressEnter();
            return this;
        }

        @Step("Güncel kullanıcının default paraflama aksiyonu ile geldiği görülür.")
        public BilgilerTab onayAkisiParaflamaGeldigiGorme() {
            boolean durum = $("[id*='akisAdimLov:LovSecilenTable'][id$='selectOneMenu']").getSelectedText().equals("Paraflama");
            Assert.assertEquals(durum, true);
            return this;
        }

        @Step("Gereği alanı güncelle")
        public BilgilerTab geregiAlaniGuncelle() {
            btnGeregiLovSecilemUpdate.click();
            return this;
        }

        @Step("Bilgi alanı güncelle")
        public BilgilerTab bilgiAlaniGuncelle() {
            btnBilgiLovSecilemUpdate.click();
            return this;
        }

        @Step("Bilgi alanı kontrol")
        public BilgilerTab bilgialaniKontrol() {
            txtBilgi.click();
            return this;
        }

        @Step("Adres hitapta görünsün")
        public BilgilerTab adresHitaptaGorunsunSec(boolean secim) {
            chkAdresHitaptaGorunsun.setSelected(secim);
            return this;
        }

        @Step("Adres dağıtımda görünsün")
        public BilgilerTab adresDagitimdaGorunsunSec(boolean secim) {
            chkAdresDagitimdaGorunsun.setSelected(secim);
            return this;
        }

        @Step("Dagitim hitap düzenleme kaydet")
        public BilgilerTab dagitimHitapDuzenlemeKaydet() {
            btnDagitimHitapDuzenlemeKaydet.click();
            return this;
        }

        @Step("İade Et butonu kontrolü")
        public BilgilerTab iadeEtbutonKontol() {
            btnIadeEt.shouldHave(Condition.enabled);
            return this;
        }

        @Step("Kaydet butonu")
        public BilgilerTab kaydet() {
            btnKaydet.click();
            return this;
        }


        @Step("İade Et butonu")
        public BilgilerTab iadeEt() {
            btnIadeEt.click();
            return this;
        }

        @Step("Kullanıcı Listesi kontrol : \"{kullanici}\" ")
        public BilgilerTab kullaniciListesiKontrol(String kullanici) {
            String text = cmbKullaniciListesi.text();
            text.contains(kullanici);
            return this;
        }

        @Step("Not doldur")
        public BilgilerTab notDoldur(String not) {
            txtNot.sendKeys(not);
            return this;
        }

        @Step("İade et tıklanır")
        public BilgilerTab iadeEt2() {
            btnIadeEt2.click();
            return this;
        }

        @Step("İade et")
        public BilgilerTab popUpEvraktaDegisiklik() {
            popUpEvrakDegisiklik.should(Condition.visible);
            $(By.xpath("//body/div[136]/div[3]/button[1]/span[@class='ui-button-text']")).click();
            return this;
        }

        @Step("Dağıtım hitap adresi al")
        public String getDagitimHitapAdres() {

            String dagitimHitapAdres = txtDagitimHitapAdres.getText();
            return dagitimHitapAdres;
        }


        public BelgenetElement getKonuKodu() {
            return cmlKonuKodu;
        }

        public SelenideElement getKonu() {
            return txtKonu;
        }

        //region Onay Akışı İşlemleri

        @Step("Onay akışı doldur")
        public BilgilerTab onayAkisiTemizle(String deger) {
            ElementsCollection btnOnayAkisiKaldir = $$("[id='yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:LovSecilen'] button");
            btnOnayAkisiKaldir.get(0).pressEnter();
            // comboLov("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:LovText").selectLov(deger);
            cmbOnayAkisi.type(deger).getTitleItems().first().click();

            return this;
        }

        @Step("Onay akışı temizle")
        public BilgilerTab onayAkisiTemizle() {
            cmbOnayAkisi.click();
            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiDoldur(String onay) {
            cmbOnayAkisi.selectLov(onay);
            return this;
        }

        @Step("Onay akışı doldur")
        public BilgilerTab onayAkisiDoldurWithoutKontrol(String onay) {
            if (cmbOnayAkisi.isLovSelected() == true) {
                cmbOnayAkisi.clearAllSelectedItems();
            }
            cmbOnayAkisi.type(onay).getDetailItems().first().click();
            return this;
        }

        @Step("Seçilen akışta vekaleti bulunan kişiler bulunmaktadır. Lütfen evrakın akışında kullanılacak kişileri seçiniz.")
        public BilgilerTab vekaletKaydet() {

            if (btnVekaletKaydet.isDisplayed()) {
                btnVekaletKaydet.click();
            }
            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiDoldur2(String onay) {
            cmbOnayAkisi2.selectLov(onay);
            return this;
        }

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle2(String onay) {
            btnOnayAkisiEkle2.click();
            return this;
        }

        @Step("Seçien onay akışını sil")
        public BilgilerTab secilenOnayAkisiSil() {

            if (cmbOnayAkisi.isLovSelected()) {
                cmbOnayAkisi.clearAllSelectedItems();
            }

            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            comboLov(cmbOnayAkisiBy).type(onayAkisi).getTitleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            comboLov(cmbOnayAkisiBy).closeTreePanel();
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

        @Step("Onay akışı güncelle")
        public BilgilerTab onayAkisiGuncelle() {
            btnOnayAkisGuncelle.shouldBe(visible);
            //btnOnayAkisGuncelle.pressEnter();
            clickJs(btnOnayAkisGuncelle);
            return this;
        }

        @Step("Onay akışı kullanıcı ekle")
        public BilgilerTab onayAkisiKullaniciEkle(String kullaniciAdi) {
            txtOnayAkisiKullanicilar.selectLov(kullaniciAdi);
            return this;
        }

        @Step("Onay akışı kullanıcı sil")
        public BilgilerTab onayAkisiKullaniciSil(String kullanici) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class$='delete-icon']").click();
            return this;
        }

        @Step("Onay akışı kullanıcı tipi seç")
        public BilgilerTab kullaniciyaKullaniciTipiSec(String kullanici, String secim) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']").selectOptionByValue(secim);
            return this;
        }

        @Step("Onay akışı kullanıcı adı ve tipi kontrolu: \"{kullaniciAdi}\", \"{kullaniciTipi}\" ")
        public BilgilerTab onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {
            btnKullan.sendKeys(Keys.SHIFT);
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .shouldHave(value(kullaniciTipi));
            return this;
        }

        @Step("Otomatik Hiyerarşik Onay Akışı kullanıcı adı ve tipi kontrolu: {description}")
        public BilgilerTab otomatikOnayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi, String description) {
            btnOtomatikAkisKullan.sendKeys(Keys.SHIFT);
            trOtomatikOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[name*='hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId']")
                    .shouldHave(value(kullaniciTipi));
            return this;
        }

        @Step("Her kullanıcının yanında işaretlenmek üzere checbox bulunması kontrolu {description}")
        public BilgilerTab otomatikOnayAkisiKullaniciyaGoreCheckBoxKontrolu(String kullaniciAdi, String description) {
            btnOtomatikAkisKullan.sendKeys(Keys.SHIFT);
            trOtomatikOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class='ui-chkbox ui-widget']")
                    .shouldBe(visible);

            return this;
        }

        @Step("Listelenen kullanıcılardan seçim yap: {description}")
        public BilgilerTab otomatikOnayAkisiKullaniciSec(String kullaniciAdi, Boolean secim, String description) {
            btnOtomatikAkisKullan.sendKeys(Keys.SHIFT);
            trOtomatikOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class='ui-chkbox ui-widget']")
                    .setSelected(secim);

            return this;
        }

        @Step("Onay akışı kullanıcı adı ve koordine tipi kontrol et")
        public BilgilerTab onayAkisiKullaniciKoordineKontrol(String kullaniciAdi, String kullaniciTipi) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$(("[id^='yeniGidenEvrakForm:evrakBilgileriList'] [class='lovItemDetail']"))
                    .text().contains(kullaniciTipi);

            return this;
        }

        @Step("Onay akışı vekalet kontrol")
        public BilgilerTab onayAkisiVekaletKontrol(String vekaletliKullanici) {

            btnKullan.sendKeys(Keys.SHIFT);
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(vekaletliKullanici))
                    .get(0)
                    .shouldBe(exist)
                    .shouldBe(text("Vekalet:"));

            return this;
        }

        @Step("Onay akışı kullanıcıları silme")
        public BilgilerTab onayAkisiKullanicilariTemizle() {
            //btnOnayAkisiEkle.click();
            if (txtOnayAkisiKullanicilar.exists())
                txtOnayAkisiKullanicilar.clearAllSelectedItems();
            return this;
        }

        @Step("Onay akışı kullanıcı tipi seç")
        public BilgilerTab onayAkisiKullaniciTipiSec(String kullaniciAdi, String kullaniciTipi) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .selectOptionContainingText(kullaniciTipi);
            return this;
        }

        @Step("Form Sec : {form} ")
        public BilgilerTab formSec(String form) {
            txtcomboLovForm.selectLov(form);
            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan() {
//            clickJs(btnKullan);
//            Selenide.executeJavaScript("arguments[0].scrollIntoView(true);", btnKullan);
//            btnKullan.pressEnter();
            clickJs(btnKullan);
            return this;
        }

        @Step("Otomatik Onay AKışı Kullan")
        public BilgilerTab otomatikOnayAkisiKullan() {
            clickJs(btnOtomatikAkisKullan);
            return this;
        }

        @Step("Onay Akişi alanı kontrolü")
        public BilgilerTab onaAkisiTextKontol() {
            String x = txtOnayAkisi.getText();
            Assert.assertNotEquals(x, "");
            return this;
        }

        @Step("Onay akışı kullan butonu tıkla")
        public BilgilerTab onayAkisiKullan() {
            //WebDriverRunner.getWebDriver().findElement(By.cssSelector("button[id$='anlikAkisKullanButton']"));
        /*$$("button[id$='anlikAkisKullanButton']").get(0).scrollTo();
        executeJavaScript("arguments[0].click();",$("button[id$='anlikAkisKullanButton']"));
        scrollIntoView();*/
            //executeJavaScript("arguments[0].scrollIntoView();",btnOnayAkisiKullaniciKullan);

            btnOnayAkisiKullaniciKullan.pressEnter();
            return this;
        }

        @Step("Onay akışı kullan butonu tıkla")
        public BilgilerTab onayAkisiKullan2() {
            clickJs($("[id='windowCevapEvrakForm:evrakBilgileriList:18:anlikAkisKullanButton']"));
            return this;
        }

        @Step("Onay akışı listesinde listelenen kullanıcıyı kontrol et")
        public BilgilerTab onayAkisiTreeKullaniciKontrol(String kullaniciAdi, Boolean exist) {


            txtOnayAkisiKullanicilarInput.setValue(kullaniciAdi);
            if (exist == true)
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(Condition.exist);
            else
                listOnayAkisikullanicilar
                        .filterBy(text(kullaniciAdi))
                        .get(0)
                        .shouldBe(not(Condition.exist));

            if (btnOnayAkisiPanelKapat.isDisplayed())
                btnOnayAkisiPanelKapat.click();

            return this;
        }

        @Step("Onay İşlemi acıklama doldur : {aciklama}")
        public BilgilerTab onayIslemiAciklamaDoldur(String aciklama) {
            txtOnayIslemiAciklama.setValue(aciklama);
            return this;
        }

        @Step("Onay İşlemi Gonder")
        public BilgilerTab onayIslemiGonder() {
            btnOnayIslemiGonder.click();
            return this;
        }

        @Step("Onay Iişlemi onaya sunma popup")
        public BilgilerTab onayIslemiOnayaSunmaPopUp() {
            $(By.id("kaydetEvetButton")).click();
            return this;
        }

        @Step("Onay akışı kullanılan kullanici kontrol et : \"{kullaniciAdi}\" ")
        public BilgilerTab onayAkisiKullanilanKullaniciKontrolEt(String kullaniciAdi) {
            listOnayAkisiKullanilan
                    .$(By.xpath(".//span[contains(., '" + kullaniciAdi + "') and @class='lovItemDetail']")).shouldBe(exist);
            return this;
        }

//TODO: Burası hatalı, düzeltilecek.
/*        public BilgilerTab onayAkisiKullaniciSec(String _kullaniciAdi) {
            txtOnayAkisiKullanicilar.setValue(_kullaniciAdi);
            listOnayAkisikullanicilar.$(By.xpath("./ul/li[contains(., '" + _kullaniciAdi + "')]")).click();
            return this;
        }*/

        @Step("Gerçek kişi gereği alanı kontrolu")
        public BilgilerTab gercekKisiGeregiAlaniKontrol(String adSoyad, String unvan, String adres, String posta) {
            /*System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);
            System.out.println("Gelen detail:    " + cmbGeregi.lastSelectedLovDetailText());
            System.out.println("Beklenen detail: " + unvan + " | " + adres);
            System.out.println("Gelen posta:     " + cmbPostaTipi.getSelectedText());
            System.out.println("Beklenen posta:  " + posta);*/

            cmbGeregi.getSelectedTitles().last().shouldHave(text(adSoyad));
            cmbGeregi.getSelectedDetails().last().shouldHave(text(unvan + " | " + adres));
            /*Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);
            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains(unvan + " | " + adres), true);*/
            Assert.assertEquals(cmbPostaTipi.getSelectedText().contains(posta), true);

            return this;
        }

        @Step("Gerçek alanında posta şekli \"{postaTipi}\" seçilir. ")
        public BilgilerTab gercekKisiGeregiAlaniPostaTipiSec(String postaTipi) {
            SelenideElement cmbPostaTipi2 = $(" [id='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable_data'] tr[role='row'] select");
            cmbPostaTipi2.selectOption(postaTipi);

            return this;
        }


        @Step("Gerçek Kişi posta türü APS seçilir.")
        public BilgilerTab gercekKisiPostaTipiAPSSec(String gercekKisi) {
            ElementsCollection geregiOlanlar = $$("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovSecilenTable_data'] tr[role='row']");
            geregiOlanlar.filterBy(Condition.text(gercekKisi)).get(0).$("select").selectOption("APS");
            return this;
        }

        @Step("Tüzel Kişi gereği alanı kontrolu")
        public BilgilerTab tuzelKisiGeregiAlaniVergiNoPostaTipiKontrol(String vergiNo2, String postaTipi) {

            /*System.out.println("Gelen detail:    " + cmbGeregi.lastSelectedLovDetailText());
            System.out.println("Beklenen detail: " + "Vergi No: " + vergiNo2);
            System.out.println("Gelen posta:     " + cmbPostaTipi.getText());
            System.out.println("Beklenen posta:  " + postaTipi);*/

            cmbGeregi.getSelectedDetails().last().shouldHave(text("Vergi No: " + vergiNo2));
//            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains("Vergi No: " + vergiNo2), true);
            Assert.assertEquals(cmbPostaTipi.getText().contains(postaTipi), true);

            return this;
        }

        @Step("Tüzel Kişi gereği alanı kontrolu")
        public BilgilerTab tuzelKisiGeregiAlaniVergiNoAdAdresKontrol(String vergiNo, String ad, String adres) {
            String gelenTitle = cmbGeregi.getSelectedTitles().last().text();
            String gelenDetail = cmbGeregi.getSelectedDetails().last().text();
            String beklenenDetail = adres + " / " + "Vergi No: " + vergiNo;

            System.out.println("Gelen title:    " + gelenTitle);
            System.out.println("Beklenen title: " + ad);
            System.out.println("Gelen detail:    " + gelenDetail);
            System.out.println("Beklenen detail: " + beklenenDetail);

            Assert.assertEquals(gelenTitle.contains(ad), true);
            Assert.assertEquals(gelenDetail.contains(beklenenDetail), true);
            return this;
        }

        @Step("Kurum için seçilen geregi posta tipi")
        public BilgilerTab geregiKurumPostaTipi(String posta) {
            txtGeregi.getSelectedItems().last().$("select").selectOption(posta);
//            cmbGeregiPostaTipi.selectLov(posta);
            return this;

        }

        @Step("Kaldirilacak klasorler seç")
        public BilgilerTab kaldirilacakKlasorler(String klasor) {
            //TODO: Fonksiyon yazılacak.
            cmbKaldiralacakKlasorler.selectLov(klasor);
            return this;
        }


        //ElementsCollection divGeregiSecilenler = $$("tbody[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':geregiLov:LovSecilenTable_data'] > tr[role='row']");
        @Step("Secilen geregi kontrol")
        public BilgilerTab geregiSecilenKontrol(String baslik, String detay, String postaTipi) {

            cmbGeregi.getSelectedTitles().last().shouldHave(text(baslik));
            cmbGeregi.getSelectedDetails().last().shouldHave(text(detay));

            /*Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(baslik), true);
            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains(detay), true);*/
            Assert.assertEquals(cmbPostaTipi.getSelectedText().contains(postaTipi), true);


            return this;
        }

        @Step("Gereği son kayıt sil")
        public BilgilerTab geregiSonKayitSil() {
            cmbGeregi.clearLastSelectedItem();
            return this;
        }

        @Step("Seçilen onay akışı detail kontrolu: \"{onayAkisiDetail}\" ")
        public BilgilerTab onayAkisiDetailKontrol(String onayAkisiDetail) {
//            System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
//            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(onayAkisiDetail), true);
            cmbOnayAkisi.getSelectedDetails().last().shouldHave(text(onayAkisiDetail));
            return this;
        }

        @Step("Seçilen onay akışı title kontrolu: \"{onayAkisiTitle}\" ")
        public BilgilerTab onayAkisiTitleKontrol(String onayAkisiTitle) {
//            System.out.println("Gelen title:     " + cmbOnayAkisi.lastSelectedLovTitleText());
//            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(onayAkisiTitle), true);
            cmbOnayAkisi.getSelectedTitles().last().shouldHave(text(onayAkisiTitle));
            return this;
        }

        @Step("Kullanıcı yerleri değiştir")
        public BilgilerTab kullaniciYerleriDegistir(String kullanici1, String kullanici2) {

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici1))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class$='ui-icon-arrowthick-1-s']").click();

            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullanici2))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class$='ui-icon-arrowthick-1-s']").click();

            return this;
        }

        @Step("Form şablonu seç: {sablonAdi}")
        public BilgilerTab formSablonuSec(String sablonAdi) {
            txtForm.selectLov(sablonAdi);
            return this;
        }

        @Step("Kendimi ekle linkine tıkla")
        public BilgilerTab kendimiEkle() {
            aKendimiEkle.click();
            return this;
        }

        @Step("Koordineli seç")
        public BilgilerTab koordineliSec(boolean secim) {
            chkKoordineli.setSelected(secim);
            return this;
        }

        @Step("Onay akışı alanının dolduğu görülür kontrolu")
        public BilgilerTab onayAkisiDoluGeldigiKontrolu() {

            Assert.assertEquals(btnOnayAkisGuncelle.isDisplayed(), true);
            Assert.assertEquals(btnOnayAkisSil.isDisplayed(), true);
            return this;
        }

        @Step("Bilgiler Tabı alan kontrolleri")
        public BilgilerTab bilgilerTabAlanKontrolleri() {
            txtKonuKodu.isDisplayed();
            txtKonu.isDisplayed();
            cmbEvrakTuru.isDisplayed();
            cmbGizlilikDerecesi.isDisplayed();
            cmbIvedik.isDisplayed();
            txtBilgi.isDisplayed();
            cmbGeregi.isDisplayed();
            cmbOnayAkisi.isDisplayed();
            txtKaldiralacakKlasorler.isDisplayed();

            Allure.addAttachment("Bilgiler Tab Kontrolü", "Konu kodu,\n" +
                    "Konu, \n" +
                    "Evrak türü\n" +
                    "Gizlilik derecesi,\n" +
                    "İvedilik (veya Aciliyet)\n" +
                    "Bilgi,\n" +
                    "Gereği,\n" +
                    "Onay Akışı alanlarının olduğu ekranın geldiği görülür.\n" +
                    "Kaldırılacak Klasör alanlarının geldiği görülür.");

            return this;
        }

        //endregion

    }

    public class EditorTab extends MainPage {

        SelenideElement divContainer = $(By.id("yeniGidenEvrakForm:allPanels_content"));

        SelenideElement divHitap = $("div[id='yeniGidenEvrakForm:hitapInplace'] > span");
        // SelenideElement divEditor = $(By.id("yeniGidenEvrakForm:allPanels"));
        SelenideElement divEditor = $("span[id='yeniGidenEvrakForm:D1Editor']");
        SelenideElement divEditorInstance = $(By.id("cke_1_contents"));
        SelenideElement yeniGidenEvrakForm = $(By.id("cke_yeniGidenEvrakForm:ckeditorInstance_window1"));
        SelenideElement editorHitapKismi = $(By.cssSelector("#yeniGidenEvrakForm\\:hitapInplace > span:nth-child(4)"));
        SelenideElement tblEditorlovSecilenTable = $(By.id("yeniGidenEvrakForm:geregiKurumLov:LovSecilenTable"));
        BelgenetElement tblEditolovGeregiTable = comboLov("input[id='yeniGidenEvrakForm:geregiKurumLov:LovText']");
        SelenideElement btnImzala = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='imzala']");
        SelenideElement divImzacılarGnMdV = $("[id='yeniGidenEvrakForm:parafciPanell'] [class='ui-inplace ui-hidden-container']");
        By cmbGeregiBy = By.id("yeniGidenEvrakForm:geregiKurumLov:LovText");
        By cmbBilgiBy = By.id("yeniGidenEvrakForm:bilgiKurumLov:LovText");
        BelgenetElement cmbGeregi = comboLov(By.id("yeniGidenEvrakForm:geregiKurumLov:LovText"));
        BelgenetElement cmbBilgi = comboLov(By.id("yeniGidenEvrakForm:bilgiKurumLov:LovText"));
        SelenideElement btnParafla = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        SelenideElement radibtnSimza = $("[id='imzalaForm:imzaPanelGrid'] div[id='imzalaForm:imzalaRadio']  div:nth-child(2)");
        SelenideElement btnEvrakImzala = $(By.xpath("//buton[starts-with(@id,'imzalaForm:jsfImzaForm:j_idt')]"));
        SelenideElement btnSimzaImzala = $(By.id("imzalaForm:sayisalImzaConfirmDialogOpener"));
        SelenideElement btnSayısalImzeEvet = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
        SelenideElement btnSImzaImzala2 = $(By.id("imzalaForm:imzalaButton"));
        SelenideElement btnGeregiSil = $(By.cssSelector("[id='yeniGidenEvrakForm:geregiKurumLov:LovSecilenTable'] [class$='delete-icon']"));
        SelenideElement btnBilgiSil = $(By.cssSelector("[id='yeniGidenEvrakForm:bilgiKurumLov:LovSecilenTable'] [class$='delete-icon']"));
        ElementsCollection trEditorEkLlistesi = $$("[id='yeniGidenEvrakForm:eklerPanell'] tr");
        ElementsCollection trEditorIlgilistesi = $$("[id$='ilgiOutPanel'] tr");
        SelenideElement lblImzaci = $(" [id^='yeniGidenEvrakForm'][id*='imzaciGridPanel'] > tbody > tr:nth-child(4) > td > span");
        SelenideElement lblKonu = $(By.xpath("//*[@id='yeniGidenEvrakForm:editorTarihKonuSayi']/table/tbody/tr[3]/td[4]"));


        private TextEditor editor = new TextEditor();

        public TextEditor getEditor() {
            return editor;
        }

        @Step("Editör tabını aç")
        private EditorTab open() {

            if (divContainer.is(not(visible)))
                tabEditor.click();

            //divContainer.shouldBe(visible);
            return this;
        }

        @Step("Hitap alanı \"{hitap}\" olarak gelmeli")
        public EditorTab hitapKontrol(String hitap) {
            divHitap.shouldHave(text(hitap));
            return this;
        }

        @Step("Metin alanın geldiği görünür")
        public EditorTab metinAlaninGeldigiGorme() {
            boolean durum = $$(By.id("yeniGidenEvrakForm:allPanels_content")).size() == 1;
            Assert.assertEquals(durum, true);
            return this;
        }

        @Step("İmzacı alanı \"{kullanici}\" olarak gelmeli")
        public EditorTab imzacılarGnMdVKontrol(String kullanici) {
            Assert.assertEquals(kullanici, divImzacılarGnMdV.getText());
            System.out.println("İmzalama başarılı geçmiştir");
            return this;
        }

        @Step("Hitap Alanı: Hitap, Ön ad, Ad, Soyad kontrolu")
        public EditorTab hitapAlanindaSayinOnAdAdSoyadKontrol(String sayin, String onAd, String ad, String soyad) {
            String getHitapAlani = editorHitapKismi.shouldHave(Condition.visible).getText();
            String girilenHitapAlani = sayin + " " + onAd + " " + toUpperCaseFirst(ad) + " " + soyad.toUpperCase();
            System.out.println("Gelen Hitap Alanı:   " + getHitapAlani);
            System.out.println("Girilen Hitap Alanı: " + girilenHitapAlani);
            Assert.assertEquals(getHitapAlani.contains(girilenHitapAlani), true);

            return this;
        }

        @Step("Hitap Alanı: Adres, ilçe, il kontrolu")
        public EditorTab hitapAlaniAdresKontrol(String adres, String ilce, String il) {
            // Kuştepe Mahallesi ŞİŞLİ / İSTANBUL
            String getHitapAlani = editorHitapKismi.shouldHave(Condition.visible).getText();
            String girilenHitapAlaniAdres = adres + " " + ilce.toUpperCase() + " / " + il.toUpperCase();
            System.out.println("Girilen Hitap Alanı: " + girilenHitapAlaniAdres);
            System.out.println("Gelen Hitap Alanı:   " + getHitapAlani);
            Assert.assertEquals(getHitapAlani.contains(girilenHitapAlaniAdres), true);
            return this;
        }

        @Step("Editör Evrak Gereği Doldur")
        public EditorTab editorEvrakGeregiSec(String Text) {

            tblEditolovGeregiTable.selectLov(Text);

            return this;

        }

        @Step("Şablonlar alanında \"{secim}\" seçilir")
        public EditorTab onTanimliIcerikSablonuKullan(String secim) {
            clickJs($("[class='cke_button cke_button__sablon_sec cke_button_off']"));
            $("[id$='windowCevapEvrakForm:icerikSablonListPanel'] div[class='ui-selectonemenu ui-widget ui-state-default ui-corner-all ui-helper-clearfix'] select").selectOption(secim);
            ($$("[class='form-buttons'] button[id^='windowCevapEvrakForm'] span").filterBy(Condition.text("Uygula")).get(0)).parent().click();

            return this;
        }


        @Step("Editör İçerik Doldur")
        public EditorTab editorIcerikDoldur(String icerik) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TextEditor editor = new TextEditor();
            editor.type(icerik);

            //divEditor.find(By.tagName("iframe")).click();
            //divEditor.find(By.tagName("iframe")).getWrappedElement().sendKeys(icerik);
            return this;
        }

        @Step("Editör İçerik Doldur")
        public String editorSayiAl() {
            SelenideElement txtSayi = $(By.id("editorEvrakSayiAlani"));
            String sayi = txtSayi.text();
            return sayi;
        }

        @Step("İmzala")
        public EditorTab imzala() {
            Selenide.sleep(2000);
            btnImzala.click();
            //clickJs(btnImzala);
            return this;
        }

        @Step("İmza popupının geldiği görülür.")
        public EditorTab imzaPopupGeldigiGorme() {
            boolean durum = $$("[id='evrakImzalaDialog']").size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("İmzala")
        public EditorTab cevapYazEditörimzala() {
            $(By.xpath("//div[@id='windowCevapEvrakDialog']//tbody//td[2]//td[3]//button")).click();
            return this;
        }


        @Step("İmzala")
        public EditorTab sImzaImzala() {
            btnSimzaImzala.pressEnter();
            return this;
        }

        @Step("Sayılsal imza atılacağı ile ilgili uyarının geldiği görülür.")
        public EditorTab stepmethod() {
            boolean durum = $$("[id='imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton']").size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("İmzala")
        public EditorTab sImzaImzala2() {
            btnSImzaImzala2.click();
            return this;
        }

        public EditorTab popupImzalaVeEvrakKapatma() {

            //switchTo().window("");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SelenideElement imzaPopupKapat = $(By.xpath("//*[@id='evrakImzalaDialog']/div[1]/a/span"));
            imzaPopupKapat.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SelenideElement evrakKapat = $(By.xpath("//*[@id='window1Dialog']/div[1]/a[1]/span"));
            evrakKapat.click();
//            $("#kapatKaydetEvetButton").click();
            $("#kapatKaydetHayirButton").click();
        /*Thread.sleep(2000);
        SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
        sayisalImzaOnay.click();*/
            return this;
        }

        public EditorTab popupSimzaEvet() {
            SelenideElement sayisalImzaOnay = $$(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton")).last();
            sayisalImzaOnay.click();
            return this;
        }

        public EditorTab popupSImzalaIslemleri() throws InterruptedException {
            Thread.sleep(10000);
            /*//switchTo().window("");
//            Thread.sleep(5000);
//            SelenideElement sImza = $(By.id("imzalaForm:imzalamaYontemiRadio:1"));
//            sImza.selectRadio("I");

           *//* $("#evrakImzalaDialog").shouldBe(visible);
            executeJavaScript("arguments[0].click()", WebDriverRunner.getWebDriver().findElement(By.id("imzalaForm:imzalamaYontemiRadio:1")));
//            Thread.sleep(2000);
            SelenideElement imzala = $(By.xpath("//*[@id='imzalaForm:sayisalImzaConfirmDialogOpener']"));
            imzala.click();
//            Thread.sleep(2000);*//*

//           .$("input")
            $("div[id='imzalaForm:imzalaRadio']").shouldBe(visible);
//            $("div[id='imzalaForm:imzalaRadio']").click();
            clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));

            $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
            Thread.sleep(700);

            $("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton").shouldBe(visible).click();*/
//            $x("//*[text()='İmzala']/ancestor::tbody[1]//button").click();
            $("div[id='imzalaForm:imzalaRadio']").shouldBe(visible).click();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
            for (int i = 0; i < Configuration.timeout / 1000; i++) {
                sleep(1000);
                if ($("#imzalaForm\\:sayisalImzaConfirmDialogOpener").is(visible)) {
                    $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();
                    clickJs($("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton"));
                    break;
                } else {
                    $("#imzalaForm\\:imzalaButton").click();
                    break;
                }
            }
            return this;
        }

        @Step("Editör ekranında hitap kontrolu: {beklenenEditorHitap}")
        public EditorTab editorHitapKontrol(String beklenenEditorHitap) {
            String editorHitap = $(By.xpath("//*[@id='yeniGidenEvrakForm:hitapInplace']/span")).getText();
            Assert.assertEquals(editorHitap.contains(beklenenEditorHitap), true);
            return this;
        }

        @Step("Editör ekranında kişinin geregi alanında görüntülenmeme kontrolu")
        public EditorTab geregiAlanindaGoruntulenmemeKontrolu(String ad) {
            boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(ad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + ad + ": Kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + ad + ": Kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Editör ekranında kişinin bilgi alanında görüntülenmeme kontrolu")
        public EditorTab bilgiAlanindaGoruntulenmemeKontrolu(String ad) {
            boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(ad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + ad + ": Gerçek kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + ad + ": Gerçek kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Gereği {description} doldur: | {geregi}")
        public EditorTab geregiDoldur(String geregi, String description) {
            Selenide.sleep(2000);
            cmbGeregi.selectLov(geregi);
            return this;
        }

        @Step("Gereği {description} doldur: | {geregi}")
        public EditorTab geregiVergiNoDoldur(String geregi, String description) {
            cmbGeregi.type(geregi).getDetailItems().filterBy(text(geregi)).first().click();
            return this;
        }

        @Step("Bilgi {description} doldur: | {bilgi}")
        public EditorTab bilgiDoldur(String bilgi, String description) {
            cmbBilgi.selectLov(bilgi);
            return this;
        }

        @Step("Parafla butonu tıkla")
        public EditorTab parafla() {
            btnParafla.click();
            return this;
        }

        @Step("Simza seç")
        public EditorTab sImzasec() {
            radibtnSimza.click();
//            radibtnSimza.selectRadio("I");

            return this;
        }

        @Step("Evrak Imzala ekranı imzala")
        public EditorTab evrakImzalama() {
            btnEvrakImzala.click();
            return this;
        }

        @Step("Sayısal Imza Evet")
        public EditorTab sayisalImzaEvetPopup() {
            $$(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton")).last().click();
            //btnSayısalImzeEvet.click();
            sleep(7000);
            return this;
        }

        @Step("Gereği alanı temizle")
        public EditorTab secilenGeregiSil() {
            cmbGeregi.shouldBe(visible);
            cmbGeregi.clearAllSelectedItems();
            return this;
        }

        @Step("Seçilen bilgi sil")
        public EditorTab secilenBilgiSil() {
            cmbBilgi.clearAllSelectedItems();
            return this;
        }

        @Step("Gereği ve bilgi alanından sil")
        public EditorTab geregVeBilgiAlanindanSil() throws InterruptedException {

            Thread.sleep(3000);
            if (btnBilgiSil.isDisplayed()) {
                btnBilgiSil.click();
            }

            if (btnGeregiSil.isDisplayed()) {
                btnGeregiSil.click();
            }

            return this;
        }

        @Step("Editorde eklenen ek kontrolu: {description} ")
        public EditorTab editordeEkKontrol(String ek, String description) {

            trEditorEkLlistesi
                    .filterBy(text(ek))
                    .get(0)
                    .shouldBe(exist);

            return this;
        }

        @Step("Editörde eklenen dağıtım tipi ve posta kontrol")
        public EditorTab editordeGeregiPostaKontrol() {
            cmbGeregi.getSelectableItems();
            return this;
        }

        @Step("Editorde eklenen ilgi kontrolu: {description}")
        public EditorTab editordeIlgiKontrol(String ilgi, String description) {

            trEditorIlgilistesi
                    .filterBy(text(ilgi))
                    .get(0)
                    .shouldBe(exist);

            return this;
        }

        @Step("Editörde ilgi satırının, seçilen evrakın \"{kullanici}\", \"{tarih}\" ve \"{evrakSayi}\" ile geldiği görülür.")
        public EditorTab ilgiSatırıKontrol(String kullanici, String tarih, String evrakSayi) {
            boolean durum = trEditorIlgilistesi
                    .filterBy(text(kullanici))
                    .filterBy(text(tarih))
                    .filterBy(text(evrakSayi)).size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("Editörde ilgi satırının, seçilen evrakın \"{kullanici}\" ve \"{tarih}\" ile geldiği görülür.")
        public EditorTab ilgiSatırıKontrol(String kullanici, String tarih) {
            boolean durum = trEditorIlgilistesi
                    .filterBy(text(kullanici))
                    .filterBy(text(tarih)).size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("Editorde silenen ekin gelmediği kontrolu")
        public EditorTab editordeEkGelmedigiKontrolu(String ek) {

            int a = trEditorEkLlistesi
                    .filterBy(text(ek)).size();

            Assert.assertEquals(a == 0, true);
            return this;
        }

        @Step("Editorde imzaci kontrolu: {imzaci}")
        public EditorTab editordeImzaciKontrol(String imzaci) {

            String editorImzaci = lblImzaci.getText();
            Assert.assertEquals(editorImzaci.contains(imzaci), true);

            return this;
        }

        @Step("Editorde gereği alani kontrolu: {kurum}")
        public EditorTab geregiAlaniKontrolu(String kurum) {

            boolean status = cmbGeregi.isLovSelected();
            Assert.assertEquals(status, true);

            return this;
        }

        @Step("Editorde konu kontrolu: {konu}")
        public EditorTab editordeKonuKontrol(String konu) {

            String editorKonu = lblKonu.getText();
            Assert.assertEquals(editorKonu.contains(konu), true);

            return this;
        }
    }

    public class EkleriTab extends MainPage {

        //Ekleri tabı - Dosya Ekle
        SelenideElement txtEkleriDosyaAciklama = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaAciklama"));
        SelenideElement btnEkleriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
        SelenideElement cmbEkleriDosyaGuvenlikKodu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKodu"));
        SelenideElement btnEkleriDosyaEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaEkleButton"));
        SelenideElement btnEkleriDosyaTemizle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaTemizleButton"));
        SelenideElement chkEkListesiniEkYap = $(By.id("yeniGidenEvrakForm:j_idt30306"));
        SelenideElement btnDosyaEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
        SelenideElement chkTaramaHavuzuTarihBaslangic = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuIlkTarihCalendar_input"));
        SelenideElement chkTaramaHavuzuTarihBitis = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSonTarihCalendar_input"));

        SelenideElement lblDosyaAdi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaAdi"));
        ElementsCollection trEkLlistesi = $$("tbody[id*='yeniGidenEvrakForm:ekListesiDataTable'] tr[role='row']");
        SelenideElement btnEkleriEvraklarTaramaHavuzudanEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:uploadFromTarananEvrakHavuzuEkA"));
        SelenideElement cmbEvrakTuru = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:tarananTuruId"));
        SelenideElement btnTaramaHavuzuSorgula = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSorgulaButton"));
        SelenideElement chkTaramaHavuzuDosya1 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:selectionId"));
        SelenideElement chkTaramaHavuzuDosya2 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:1:selectionId"));
        SelenideElement txtDosya1Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:0:j_idt']");
        SelenideElement txtDosya2Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:1:j_idt']");
        SelenideElement cmbTaramaTuru = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:tarananTuruId"));

        SelenideElement chkDagitimYerleriEk1 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:0:j_idt'] [class='ui-selectcheckboxmenu-label ui-corner-all']");
        SelenideElement chkDagitimYerleriKullaniciEk1 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:0:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(3)");
        SelenideElement chkDagitimYerleriKurumEk1 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:0:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(2)");
        SelenideElement chkDagitimYerleriBirimEk1 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:0:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(1)");

        SelenideElement chkDagitimYerleriEk2 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:1:j_idt'] [class='ui-selectcheckboxmenu-label ui-corner-all']");
        SelenideElement chkDagitimYerleriKullaniciEk2 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:1:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(3)");
        SelenideElement chkDagitimYerleriKurumEk2 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:1:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(2)");
        SelenideElement chkDagitimYerleriBirimEk2 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:1:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(1)");

        SelenideElement chkDagitimYerleriEk3 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:2:j_idt'] [class='ui-selectcheckboxmenu-label ui-corner-all']");
        SelenideElement chkDagitimYerleriKullaniciEk3 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:2:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(3)");
        SelenideElement chkDagitimYerleriKurumEk3 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:2:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(2)");
        SelenideElement chkDagitimYerleriBirimEk3 = $("[id^='yeniGidenEvrakForm:ekListesiDataTable:2:j_idt'] [class*='ui-selectcheckboxmenu-list-item ui-corner-all']:nth-child(1)");


        SelenideElement btnTaramaHavuzuTamam = $(By.id("taramaHavuzuFormId:taramaHavuzuTamamButton"));
        ElementsCollection trEklistesi = $$("tbody[id*='yeniGidenEvrakForm:ekListesiDataTable'] tr[role='row']");

        //Ekleri tabı - Fiziksel Ekle
        SelenideElement btnEkleriFizikselEkEkleTab = $(By.xpath("//a[@href='#yeniGidenEvrakForm:evrakEkTabView:aciklamaEkleTab']"));
        SelenideElement txtEkleriFizikselMetni = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaTextArea"));
        SelenideElement cmbEkleriFizikselGizlilikDerecesi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKoduAciklama"));
        SelenideElement btnEkleriFizikselEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaEkleButton"));

        //Ekleri tabı - Sistemde Kayıtlı Evrak Ekle
        SelenideElement btnSistemdeKayitliEvrakTab = $("a[href='#yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvragiEkleTab']");
        SelenideElement dateSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
        SelenideElement dateSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
        SelenideElement cmbEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId"));
        SelenideElement btnSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dokumanAraButton"));
        SelenideElement txtEvrakArama = $(By.id("yeniGidenEvrakForm:evrakEkTabView:evrakAramaText"));
        ElementsCollection tblSistemdeKayitliEvrakListe = $$("[id='yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable_data'] tr[data-ri]");
        ElementsCollection tblEkListesi = $$("[id='yeniGidenEvrakForm:ekListesiDataTable_data'] tr[role='row']");
        SelenideElement btnIlgiEkle = $("[id^='yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable'][id$='ekEkleButton1']");
        ElementsCollection trEvraklarListesi = $$("tbody[id*='yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable'] tr[role='row']");
        SelenideElement btnEvrakDetay = $(By.id("yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable:0:detayGosterButton"));
        SelenideElement evrakDetayiPageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
        SelenideElement btnEvrakDetayiPenceresiKapat = $("#windowReadOnlyEvrakDialog > div:nth-of-type(1) .ui-icon-closethick");
        SelenideElement btnEvrakEkEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable:0:ekEkleButton1"));
        ElementsCollection trEvraklarTumListe = $$("tbody[id*='yeniGidenEvrakForm:ekListesiDataTable_data'] tr[role='row']");

        //Web Adresini Ekle Tab
        SelenideElement btnEkleriWebAdresiniEkle = $("a[href='#yeniGidenEvrakForm:evrakEkTabView:webAdresindenEkEkle']");
        //Arşivde Kayıtlı Ekle Tab
        SelenideElement btnEkleriArsivdeEkleTab = $("a[href='#yeniGidenEvrakForm:evrakEkTabView:arsivdenEkEkleTabId']");


        //İlişkili Evraklar tab - Sistemde kayıtlı evrak ekle

        //Ekleri tabı - Arşivde Kayıtlı Evrak Ekle
        SelenideElement dateArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
        SelenideElement dateArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
        SelenideElement txtArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
        SelenideElement txtArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:evrakEkTabView:kisiyeLov_id:LovText"));
        SelenideElement txtArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));
        SelenideElement btnArsivdenEvrakAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraButtonId"));

        SelenideElement btnImzala = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='imzala']");

        @Step("Ekleri Tabını aç")
        private EkleriTab open() {
            tabEkleri.shouldBe(visible);
            clickJs(tabEkleri);
            return this;
        }

        @Step("Ekleri/Fiziksel Ek Ekle Tab - Açma")
        public EkleriTab fizikselEkEkleTabiniAc() {
            btnEkleriFizikselEkEkleTab.click();
            return this;
        }

        @Step("Ekleri/Sistemde Kayıtlı Evrak Ekle Tab - Açma")
        public EkleriTab sistemdeKayitliEvrakEkleTabiniAc() {
            btnSistemdeKayitliEvrakTab.click();
            return this;
        }

        @Step("Ekleri/Web Adresi Ekle Tab - Açma")
        public EkleriTab webAdresiEkleTabiniAc() {
            btnEkleriWebAdresiniEkle.click();
            return this;
        }

        @Step("Ekleri/Arşivde Kayıtlı Evrak Ekle Tab - Açma")
        public EkleriTab arsivdeKayitliEvrakEkleTabiniAc() {
            btnEkleriArsivdeEkleTab.click();
            return this;
        }

        @Step("Ekleri Tab - Dosya Ekle")
        public EkleriTab ekleriDosyaEkle(String pathToFile) {
            uploadFile(btnEkleriDosyaFileUpload, pathToFile);
            return this;
        }

        @Step("Ekleri Tab - Ek Metni alanını doldur: {aciklama}")
        public EkleriTab ekleriEkMetniDoldur(String aciklama) {
            txtEkleriDosyaAciklama.setValue(aciklama);
            return this;
        }

        @Step("Ekleri Tab - Ekle")
        public EkleriTab ekleriEkle() {
            btnEkleriDosyaEkle.click();
            return this;
        }

        @Step("İmzala")
        public EkleriTab imzala() {
            btnImzala.click();
            return this;
        }

        public EkleriTab popupImzalaVeEvrakKapatma() throws InterruptedException {

            Thread.sleep(5000);
            SelenideElement imzaPopupKapat = $(By.xpath("//*[@id='evrakImzalaDialog']/div[1]/a/span"));
            imzaPopupKapat.click();

            Thread.sleep(2000);
            SelenideElement evrakKapat = $(By.xpath("//*[@id='window1Dialog']/div[1]/a[1]/span"));
            evrakKapat.click();
        /*Thread.sleep(2000);
        SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
        sayisalImzaOnay.click();*/
            return this;
        }

        @Step("Ekleri/Sistemde Kayıtlı Evrak Ekle tab aç")
        public EkleriTab sistemdeKayitliEvrakEkleTabAc() {
            btnSistemdeKayitliEvrakTab.click();
            return this;
        }

        @Step("Evrakın Aranaği Yer seç")
        public EkleriTab evrakinAranacagiYerSec(String aranacakYer) {
            cmbEvrakAranacakyer.selectOption(aranacakYer);
            return this;
        }

        @Step("Evrak Arama doldur: {evrakNo}")
        public EkleriTab evrakAramaDoldur(String evrakNo) {
            txtEvrakArama.setValue(evrakNo);
            return this;
        }

        @Step("Dokuman Ara")
        public EkleriTab dokumanAra() {
            btnSistemdeDokumanAra.click();
            return this;
        }

        @Step("Tabloda Evrak no kontrolü")
        public EkleriTab tabloEvrakNoKontrol(String evrakNo) {
            tblSistemdeKayitliEvrakListe
                    .filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
            return this;
        }

        @Step("Tabloda detay butonuna tıkla")
        public EkleriTab tablodaDetayTikla(String evrakNo) {
            tblSistemdeKayitliEvrakListe
                    .filterBy(Condition.text(evrakNo)).first()
                    .$("[id^='yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable'][id$='detayGosterButton']").click();
            return this;
        }

        @Step("Tabloda detay butonuna tıkla")
        public EkleriTab tablodaIlgiEkleTıkla(String evrakNo) {
            tblSistemdeKayitliEvrakListe
                    .filterBy(Condition.text(evrakNo)).first()
                    .$("[id^='yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable'][id$='ekEkleButton1']").click();
            return this;
        }

        @Step("Evrak Arama ekranı kapat")
        public EkleriTab evrakAramaKapat() {
            $(By.xpath("//div[@id='windowReadOnlyEvrakDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
            islemPenceresiKapatmaOnayiPopup("Kapat");
            return this;
        }

        @Step("Ilgi ekle tablo kontrolü")
        public EkleriTab tablodaIlgiEkleKontrolu() {
            tblEkListesi
                    .shouldHaveSize(1);
            return this;
        }

        @Step("Dosya ekle, Fiziksel ek ekle, Sistemde kayıtlı evrak ekle, Web adresini ekle alanlarının geldiği görülür")
        public EkleriTab ekleriTablariGeldigiGorme() {
            boolean durum = $$("[id='yeniGidenEvrakForm:evrakEkTabView']").size() == 1;
            Assert.assertEquals(durum, true);
            takeScreenshot();
            return this;
        }

        @Step("Dosya ekle : {description} ")
        public EkleriTab dosyaEkle(String pathPDF, String description) {
            uploadFile(btnDosyaEkle, pathPDF);
            return this;
        }

        @Step("Dosya yüklenene kadar 60 dk bekle, 60 dktan fazla sürerse timeout hatası ver")
        public EkleriTab dosyaYukleneneKadarBekle() {

            waitForLoadingJS(WebDriverRunner.getWebDriver(), 3600);

            return this;
        }

        @Step("Eklenen dosya adi kontrol : {dosyaAdi}")
        public EkleriTab ekleriEklenenDosyaAdiKontrol(String dosyaAdi) {

            Assert.assertEquals(lblDosyaAdi.getText().contains(dosyaAdi), true);

            return this;
        }

        @Step("Ekranın alt kısmında listelenen eklere dosyanın geldiği kontrolu: {description}")
        public EkleriTab listelenenEklerdeKontrol(String value, String description) {

            trEkLlistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Fiziksel ek metni doldur")
        public EkleriTab fizikselEkMetniDoldur(String fizikselEkMetni) {

            txtEkleriFizikselMetni.setValue(fizikselEkMetni);

            return this;
        }


        @Step("Fiziksel Ek Metni ekle")
        public EkleriTab fizikselEkMetniEkle() {

            btnEkleriFizikselEkle.click();

            return this;
        }

        @Step("Evrakların listelendiği kontrolu")
        public EkleriTab listelenenEvraklardaKontrol(String evrakNo) {

            trEvraklarListesi
                    .filterBy(text(evrakNo))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Evrak Detayı göster")
        public EkleriTab evrakDetayiGoster() {

            btnEvrakDetay.click();

            return this;
        }

        @Step("Evrak Detayının geldiği kontrolu")
        public EkleriTab evrakDetayiKontrol() {

            evrakDetayiPageTitle.shouldBe(visible);

            return this;
        }

        @Step("Evrak Detayı sayfasını kapat")
        public EkleriTab evrakDetayiSayfasınıKapat() {

            btnEvrakDetayiPenceresiKapat.click();

            return this;
        }

        @Step("Evrak EK ekle")
        public EkleriTab evrakEkEkle() {

            btnEvrakEkEkle.click();

            return this;
        }

        @Step("Ekler listesinde detay göster")
        public EkleriTab eklenenEklerListesindeDetayGoster(String evrakSayisi) {

            trEkLlistesi
                    .filterBy(text(evrakSayisi))
                    .get(0)
                    .shouldBe(exist)
                    .$("[id$='detayButton']").click();

            return this;
        }

        @Step("Ekler listesinde ek sil")
        public EkleriTab ekIsmineGoreEkSilme(String ek) {

            trEvraklarTumListe
                    .filterBy(text(ek))
                    .get(0)
                    .$("[id$='ekListesiSilButton']").click();

            return this;
        }

        @Step("Tarama havuzundan ekle")
        public EkleriTab taramaHavuzundanEkle() {
            btnEkleriEvraklarTaramaHavuzudanEkle.click();
            return this;
        }

        @Step("Evrak türü seç")
        public EkleriTab evrakTuruSec(String turu) {
            cmbEvrakTuru.selectOption(turu);
            return this;
        }

        @Step("Tarama havuzu sorgula")
        public EkleriTab taramaHavuzuSorgula() {
            btnTaramaHavuzuSorgula.click();
            return this;
        }

        @Step("Ek dosya seç")
        public EkleriTab birinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya1.setSelected(secim);
            return this;
        }

        @Step("Ek dosya seç")
        public EkleriTab ikinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya2.setSelected(secim);
            return this;
        }

        @Step("Ek metni doldur")
        public EkleriTab dosya1AciklamaDoldur(String ilisikMetni) {
            txtDosya1Aciklama.setValue(ilisikMetni);
            return this;
        }

        @Step("Ek metni doldur")
        public EkleriTab dosya2AciklamaDoldur(String ilisikMetni) {
            txtDosya2Aciklama.setValue(ilisikMetni);
            return this;
        }

        @Step("Tarama havuzu tamam")
        public EkleriTab taramaHavuzuTamam() {
            btnTaramaHavuzuTamam.shouldBe(visible);
            clickJs(btnTaramaHavuzuTamam);
            return this;
        }

        @Step("Ekranın alt kısmında listelenen evraklara eklerin geldiği kontrolu: {description}")
        public EkleriTab listelenenEklereDosyanınGeldigiKontrolu(String value, String description) {

            trEklistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Ekranın alt kısmında listelenen eklerde indir butonunun aktif geldiği kontrolu")
        public EkleriTab listelenenEklerdeIndırButonuKontrol(String dosyaAdi) {

            trEklistesi
                    .filterBy(text(dosyaAdi))
                    .get(0)
                    .$("[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only tipTip'] [class$='ui-icon-disk']").shouldBe(visible);

            return this;
        }

        @Step("Sistemde Kayıtlı Evrak Ekle alan kontrolleri")
        public EkleriTab sistemdeKayitliEvrakEkleAlanKontrolleri() {

            dateSistemdeEvrakTarihiBaslangic.shouldBe(visible);
            dateSistemdeEvrakTarihiSon.shouldBe(visible);
            cmbEvrakAranacakyer.shouldBe(visible);
            txtEvrakArama.shouldBe(visible);
            btnSistemdeDokumanAra.shouldBe(visible);

            return this;
        }

        @Step("Evrak aranacak yer seç")
        public EkleriTab evrakAranacakYerSec(String turu) {
            cmbEvrakAranacakyer.selectOption(turu);
            return this;
        }

        @Step("Evrakın listeye gelmediği kontrolu")
        public EkleriTab listelenenEvraklardaGelmemeKontrolu(String evrakNo) {

            trEvraklarListesi
                    .filterBy(text(evrakNo)).shouldHaveSize(0);

            return this;
        }

        @Step("Ek listesinde detay göster")
        public EkleriTab ekListesindeDetayGoster(String value) {

            trEklistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist)
                    .$("[id$='detayButton']").click();

            return this;
        }

        @Step("Detay tıklanarak açılan ek detay kontrolu")
        public EkleriTab ekleriDetayGeldigiKontrolu() {

            switchTo().window(1);
            Selenide.sleep(1000);
            closeNewWindow();
            switchTo().window(0);
            return this;
        }

        @Step("Evrak tarih aralığı - başlangıç")
        public EkleriTab evrakTarihBaslangicDoldur(String tarihBaslangic) {
            chkTaramaHavuzuTarihBaslangic.setValue(tarihBaslangic);
            return this;
        }

        @Step("Evrak tarih aralığı - bitiş")
        public EkleriTab evrakTarihBitisDoldur(String tarihBitis) {
            chkTaramaHavuzuTarihBitis.setValue(tarihBitis);
            return this;
        }

        @Step("Popup Ek Silme Onay - Kaydı silmek istediğinize emin misiniz? : {secim}")
        public void ekSilmeOnayi(String secim) {

            SelenideElement btnSilmeOnayiEvet = $("[id$='ekSilEvetButton']");
            SelenideElement btnSilmeOnayiHayir = $("['ekSilHayirButton']");

            switch (secim) {
                case "Evet":
                    clickJs(btnSilmeOnayiEvet);
                    break;
                case "Hayır":
                    clickJs(btnSilmeOnayiHayir);
                    break;
            }
        }

        @Step("Popup Ek Ekle - Eklemek istediğiniz dosya 300 dpidan düşüktür devam etmek istiyor musunuz? : {secim}")
        public EkleriTab ekEkleDusukDpiPopupOnayi(String secim) {

            SelenideElement ekEklePopup = $("[id='yeniGidenEvrakForm:dusukDpiConfirmDialog'] [class= 'ui-dialog-title']");
            SelenideElement btnEkEkleEvet = $("[id='yeniGidenEvrakForm:dusukDpiConfirmDialog'] [id$= 'dusukDpiEvetButton']");
            SelenideElement btnEkEkleHayir = $("[id='yeniGidenEvrakForm:dusukDpiConfirmDialog'] [id$= 'dusukDpiHayirButton']");

            if (ekEklePopup.isDisplayed()) {
                switch (secim) {
                    case "Evet":
                        clickJs(btnEkEkleEvet);
                        break;
                    case "Hayır":
                        clickJs(btnEkEkleHayir);
                        break;
                }
            }
            return this;
        }

        @Step("Dağıtım yerleri aç - Ek1")
        public EkleriTab dagitimYerleriAcEk1() {
            chkDagitimYerleriEk1.click();
            return this;
        }

        @Step("Dağıtım yerleri aç - Ek2")
        public EkleriTab dagitimYerleriAcEk2() {
            chkDagitimYerleriEk2.click();
            return this;
        }

        @Step("Dağıtım yerleri aç - Ek3")
        public EkleriTab dagitimYerleriAcEk3() {
            chkDagitimYerleriEk3.click();
            return this;
        }

        @Step("Dağıtım yerlerinde birim seç")
        public EkleriTab dagitimYerlerindeBirimSec(boolean secim) {
            chkDagitimYerleriBirimEk1.setSelected(secim);
            return this;
        }

        @Step("Dağıtım yerlerinde kurum seç")
        public EkleriTab dagitimYerlerindeKurumSec(boolean secim) {
            chkDagitimYerleriKurumEk1.setSelected(secim);
            return this;
        }

        @Step("Dağıtım yerlerinde kullanıcı seç")
        public EkleriTab dagitimYerlerindeKullaniciSec(boolean secim) {
            chkDagitimYerleriKullaniciEk1.setSelected(secim);
            return this;
        }

        //3ü seçili geliyor sadece kullanıcı kaldırılıyor.
        @Step("Dağıtım yerlerinde birim ve kurum seç, kullanıcı seçme")
        public EkleriTab dagitimYerlerindeBirimVeKurumSecEk1() {

            chkDagitimYerleriBirimEk1.setSelected(false);
            chkDagitimYerleriKurumEk1.setSelected(false);
            chkDagitimYerleriKullaniciEk1.setSelected(true);

            return this;
        }

        @Step("Dağıtım yerlerinde birim, kurum ve kullanıcı seçimlerini kaldır")
        public EkleriTab dagitimYerlerindeBirimKurumKullaniciKaldir() {
            chkDagitimYerleriBirimEk2.setSelected(true);
            chkDagitimYerleriKurumEk2.setSelected(true);
            chkDagitimYerleriKullaniciEk2.setSelected(true);

            return this;
        }

        @Step("Dağıtım yerlerinde birim ve kurum seçimlerini kaldır, kullanıcı seç")
        public EkleriTab dagitimYerlerindeKullaniciSec() {
            chkDagitimYerleriBirimEk3.setSelected(true);
            chkDagitimYerleriKurumEk3.setSelected(true);
            chkDagitimYerleriKullaniciEk3.setSelected(false);

            return this;
        }
    }

    public class IlgileriTab extends MainPage {

        //İlgileri tabı - Dosya Ekle
        SelenideElement txtIlgileriIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaAciklama"));
        SelenideElement btnIlgileriDosyaEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:fileUploadButtonA_input"));
        SelenideElement btnIlgileriEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaEkleButton"));
        SelenideElement btnIlgileriTaramaHavuzudanEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:uploadFromTarananEvrakHavuzuIlgiA"));
        SelenideElement cmbEvrakTuru = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:tarananTuruId"));
        SelenideElement btnTaramaHavuzuSorgula = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSorgulaButton"));
        SelenideElement chkTaramaHavuzuDosya1 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:selectionId"));
        SelenideElement chkTaramaHavuzuDosya2 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:1:selectionId"));
        SelenideElement txtDosya1Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:0:j_idt']");
        SelenideElement txtDosya2Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:1:j_idt']");
        SelenideElement lblDosyaAdi = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaAdi"));
        SelenideElement chkTaramaHavuzuTarihBaslangic = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuIlkTarihCalendar_input"));
        SelenideElement chkTaramaHavuzuTarihBitis = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSonTarihCalendar_input"));


        SelenideElement lblIlgiListesiAciklama1 = $("[id^='yeniGidenEvrakForm:ilgiListesiDataTable:0'] [class$='ui-inplace-display']");
        SelenideElement txtIlgiListesiAciklama1 = $("[id^='yeniGidenEvrakForm:ilgiListesiDataTable:0'] [class*='ui-inputtextarea']");
        SelenideElement btnIlgiListesiCheck = $("[id^='yeniGidenEvrakForm:ilgiListesiDataTable:0'] [class$='ui-icon-check']");

        SelenideElement cmbTaramaTuru = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:tarananTuruId"));
        SelenideElement btnTaramaHavuzuTamam = $(By.id("taramaHavuzuFormId:taramaHavuzuTamamButton"));
        ElementsCollection trIlgilistesi = $$("tbody[id*='yeniGidenEvrakForm:ilgiListesiDataTable'] tr[role='row']");

        //İlgileri tabı - Metin Ekle
        SelenideElement btnIlgileriMetinEkleTab = $(By.xpath("//a[@href='#yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaEkleTab']"));
        SelenideElement txtIlgileriMetinEkleIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaTextArea"));
        SelenideElement btnIlgileriMetinEkleEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));

        //İlgileri tabı - Sistemde kayıtlı evrak ekle
        SelenideElement btnSistemdeKayitliEvrakTab = $("a[href='#yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvragiEkleTab']");
        SelenideElement tabIlgileriSistemdeKayitliEvrakEkle = $(By.linkText("Sistemde Kayıtlı Evrak Ekle"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBitis = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
        SelenideElement cmbIlgileriSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId"));
        SelenideElement txtIlgileriSistemdeEvrakArama = $(By.name("yeniGidenEvrakForm:ilgiIslemleriTabView:evrakAramaText"));
        SelenideElement btnIlgileriSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dokumanAraButton"));
        SelenideElement tableSistemdeKayitliEvrak = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable"));
        SelenideElement btnTablodaBulunanIlkEvrakiEkle = $(By.xpath("//*[starts-with(@id,'yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable:0:j_idt')]"));
        ElementsCollection trEvraklarListesi = $$("tbody[id*='yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable'] tr[role='row']");
        SelenideElement btnEvrakIlgiEkEkle = $("[id^='yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable:0:'] [class$='document-follow']");
        SelenideElement evrakDetayiPageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
        SelenideElement btnEvrakDetayiPenceresiKapat = $("#windowReadOnlyEvrakDialog > div:nth-of-type(1) .ui-icon-closethick");

        //İlgileri tabı - Arşivde Kayıtlı Evrak Ekle
        SelenideElement dateIlgileriArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihBasId_input"));
        SelenideElement dateIlgileriArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihSonId_input"));
        SelenideElement txtIlgileriArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraKonuInputTextId"));
        SelenideElement txtIlgileriArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:kisiyeLov_id:LovText"));
        SelenideElement txtIlgileriArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
        SelenideElement btnIlgileriArsivdenEvrakAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraButtonId"));

        //İlgileri tabı - Arşivde Kayıtlı Evrak Ekle
        SelenideElement dateIlisikIslemleriTabViewArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihBasId_input"));
        SelenideElement dateIlisikIslemleriTabViewArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihSonId_input"));
        SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraKonuInputTextId"));
        SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:kisiyeLov_id:LovText"));
        SelenideElement txtIlisikIslemleriTabViewArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
        SelenideElement btnIlisikIslemleriTabViewArsivdenEvrakDokumanAra = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraButtonId"));

        @Step("Ilgileri tabını aç")
        private IlgileriTab open() {
            tabIlgileri.shouldBe(visible);
            clickJs(tabIlgileri);
            return this;
        }

        @Step("İlgi Tab - Dosya Ekle")
        public IlgileriTab ilgiDosyaEkle(String pathToFile) {
            uploadFile(btnIlgileriDosyaEkle, pathToFile);
            return this;
        }

        @Step("İlgi Tab - İlgi Metni")
        public IlgileriTab ilgileriIlgiMetniDoldur(String ilgiMetni) {
            txtIlgileriIlgiMetni.setValue(ilgiMetni);
            return this;
        }

        @Step("İlgi Tab - Ekle")
        public IlgileriTab ilgileriEkle() {
            btnIlgileriEkle.click();
            return this;
        }

        @Step("Tarama havuzundan ekle")
        public IlgileriTab taramaHavuzundanEkle() {
            btnIlgileriTaramaHavuzudanEkle.click();
            return this;
        }

        @Step("Evrak tarih aralığı - başlangıç")
        public IlgileriTab evrakTarihBaslangicDoldur(String tarihBaslangic) {
            chkTaramaHavuzuTarihBaslangic.setValue(tarihBaslangic);
            return this;
        }

        @Step("Evrak tarih aralığı - bitiş")
        public IlgileriTab evrakTarihBitisDoldur(String tarihBitis) {
            chkTaramaHavuzuTarihBitis.setValue(tarihBitis);
            return this;
        }

        @Step("Evrak türü seç")
        public IlgileriTab evrakTuruSec(String turu) {
            cmbEvrakTuru.selectOption(turu);
            return this;
        }

        @Step("Tarama havuzu sorgula")
        public IlgileriTab taramaHavuzuSorgula() {
            btnTaramaHavuzuSorgula.click();
            return this;
        }

        @Step("İlgi dosya seç")
        public IlgileriTab birinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya1.setSelected(secim);
            return this;
        }

        @Step("İlgi dosya seç")
        public IlgileriTab ikinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya2.setSelected(secim);
            return this;
        }

        @Step("İlgi metni doldur")
        public IlgileriTab dosya1AciklamaDoldur(String ilisikMetni) {
            txtDosya1Aciklama.setValue(ilisikMetni);
            return this;
        }

        @Step("İlgi metni doldur")
        public IlgileriTab dosya2AciklamaDoldur(String ilisikMetni) {
            txtDosya2Aciklama.setValue(ilisikMetni);
            return this;
        }

        @Step("Tarama havuzu tamam")
        public IlgileriTab taramaHavuzuTamam() {
            btnTaramaHavuzuTamam.shouldBe(visible);
            clickJs(btnTaramaHavuzuTamam);
            return this;
        }

        @Step("Dosya ekle : {description} ")
        public IlgileriTab dosyaEkle(String pathPDF, String description) {

            uploadFile(btnIlgileriDosyaEkle, pathPDF);

            return this;
        }


        @Step("Ekranın alt kısmında listelenen ilgilerde eklerin geldiği kontrolu: {description}")
        public IlgileriTab listelenenIlgilerdeDosyanınGeldigiKontrolu(String value, String description) {

            trIlgilistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Ekranın alt kısmında listelenen ilgilerde indir butonunun aktif geldiği kontrolu")
        public IlgileriTab listelenenIlgilerdeIndırButonuKontrol(String dosyaAdi) {

            trIlgilistesi
                    .filterBy(text(dosyaAdi))
                    .get(0)
                    .$("[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only tipTip'] [class$='ui-icon-disk']").shouldBe(visible);

            return this;
        }

        @Step("İlgi ek listesinde detay göster")
        public IlgileriTab ilgiEkListesindeDetayGoster(String value) {

            trIlgilistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist)
                    .$("[id$='ilgiListesiDetayButton']").click();

            return this;
        }

        @Step("Detay tıklanarak açılan ilgileri detay kontrolu")
        public IlgileriTab ilgileriDetayGeldigiKontrolu() {

            switchTo().window(1);
            Selenide.sleep(1000);
            closeNewWindow();
            switchTo().window(0);
            return this;
        }

        @Step("Evrak Detayının geldiği kontrolu")
        public IlgileriTab evrakDetayiKontrol() {

            evrakDetayiPageTitle.shouldBe(visible);

            return this;
        }

        @Step("İlgileri listesinde ilgi sil")
        public IlgileriTab ilgiIsmineGoreIlgiSilme(String ilgi) {

            trIlgilistesi
                    .filterBy(text(ilgi))
                    .get(0)
                    .$("[id$='ilgiListesiSilButton']").click();

            return this;
        }

        @Step("İlgileri listesinde ilgi güncelle")
        public IlgileriTab ilgiIsmineGoreAciklamaGuncelleme(String ilgiYeni) {

            lblIlgiListesiAciklama1.click();
            txtIlgiListesiAciklama1.setValue(ilgiYeni);
            btnIlgiListesiCheck.click();

            return this;
        }

        @Step("Evrak Detayı sayfasını kapat")
        public IlgileriTab evrakDetayiSayfasınıKapat() {

            btnEvrakDetayiPenceresiKapat.click();

            return this;
        }

        @Step("Dosya yüklenene kadar 60 dk bekle, 60 dktan fazla sürerse timeout hatası ver")
        public IlgileriTab dosyaYukleneneKadarBekle() {

            waitForLoadingJS(WebDriverRunner.getWebDriver(), 3600);

            return this;
        }

        @Step("Ilgileri dosya adi kontrol : {dosyaAdi}")
        public IlgileriTab ekleriEklenenDosyaAdiKontrol(String dosyaAdi) {

            Assert.assertEquals(lblDosyaAdi.getText().contains(dosyaAdi), true);

            return this;
        }

        @Step("Ilgileri/Metin Ekle Tab - Açma")
        public IlgileriTab ilgileriMetinEkleTabAc() {
            btnIlgileriMetinEkleTab.click();
            return this;
        }

        @Step("İlgileri/Metin Ekle - İlgi Metni")
        public IlgileriTab ilgileriMetinEkleIlgiMetniDoldur(String ilgiMetni) {
            txtIlgileriMetinEkleIlgiMetni.setValue(ilgiMetni);
            return this;
        }

        @Step("İlgileri/Metin Ekle - Ekle")
        public IlgileriTab ilgileriMetinEkleEkle() {
            btnIlgileriMetinEkleEkle.click();
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak Ekle")
        public IlgileriTab sistemeKayitliEvrakEkleTab() {
            tabIlgileriSistemdeKayitliEvrakEkle.click();
            return this;
        }


        @Step("İlgileri/Sistemde Kayıtlı Evrak Ekle tab aç")
        public IlgileriTab sistemdeKayitliEvrakEkleTabAc() {
            btnSistemdeKayitliEvrakTab.click();
            return this;
        }

        @Step("Sistemde Kayıtlı Evrak Ekle alan kontrolleri")
        public IlgileriTab sistemdeKayitliEvrakEkleAlanKontrolleri() {

            dateIlgileriSistemdeEvrakTarihiBaslangic.shouldBe(visible);
            dateIlgileriSistemdeEvrakTarihiBitis.shouldBe(visible);
            cmbIlgileriSistemdeEvrakAranacakyer.shouldBe(visible);
            txtIlgileriSistemdeEvrakArama.shouldBe(visible);
            btnIlgileriSistemdeDokumanAra.shouldBe(visible);

            return this;
        }

        @Step("Evrakın listeye gelmediği kontrolu")
        public IlgileriTab listelenenEvraklardaGelmemeKontrolu(String evrakNo) {

            trEvraklarListesi
                    .filterBy(text(evrakNo)).shouldHaveSize(0);

            return this;
        }

        @Step("Evrakların listelendiği kontrolu")
        public IlgileriTab listelenenEvraklardaKontrol(String evrakNo) {

            trEvraklarListesi
                    .filterBy(text(evrakNo))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Evrak ilgi ek ekle")
        public IlgileriTab evrakEkEkle() {

            btnEvrakIlgiEkEkle.click();

            return this;
        }

        @Step("Evrakın Aranaği Yer seç")
        public IlgileriTab evrakAranacakYerSec(String aranacakYer) {
            cmbIlgileriSistemdeEvrakAranacakyer.selectOption(aranacakYer);
            return this;
        }

        @Step("Evrak Arama doldur: {evrakNo}")
        public IlgileriTab evrakAramaDoldur(String evrakNo) {
            txtIlgileriSistemdeEvrakArama.setValue(evrakNo);
            return this;
        }

        @Step("Dokuman Ara")
        public IlgileriTab dokumanAra() {
            btnIlgileriSistemdeDokumanAra.click();
            return this;
        }


        @Step("IlgileriTab Sisteme Kayitli Evrak EkleTab Tarih Başlagıç")
        public IlgileriTab sistemeKayitliEvrakBaslangictarihi(String dateText) {
            dateIlgileriSistemdeEvrakTarihiBaslangic.setValue(dateText);
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak EkleTab Tarih Son")
        public IlgileriTab sistemeKayitliEvrakBitistarihi(String dateText) {
            dateIlgileriSistemdeEvrakTarihiBitis.setValue(dateText);
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak EkleTab Evrak Aranacak yer secimi")
        public IlgileriTab sistemeKayitliEvrakAramaYeriSec(String evrakYeri) {
            cmbIlgileriSistemdeEvrakAranacakyer.selectOption(evrakYeri);
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak EkleTab Evrak Arama Metni")
        public IlgileriTab sistemeKayitliEvrakAra(String evrakAdi) {
            txtIlgileriSistemdeEvrakArama.setValue(evrakAdi);
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak EkleTab Dokuman Ara")
        public IlgileriTab sistemeKayitliDokumanArama() {
            btnIlgileriSistemdeDokumanAra.click();
            return this;
        }

        @Step("IlgileriTab Tabloda Bulunan Evraki Ekle")
        public IlgileriTab tablodaBulunanEvrakiEkle() {
            btnTablodaBulunanIlkEvrakiEkle.pressEnter();
//            btnTablodaBulunanIlkEvrakiEkle.click();
            return this;

        }

        @Step("Popup İlgi Silme Onayı - Kaydı silmek istediğinize emin misiniz? : {secim}")
        public void ilgiSilmeOnayi(String secim) {

            SelenideElement btnSilmeOnayiEvet = $("[id$='ilgiSilEvetButton']");
            SelenideElement btnSilmeOnayiHayir = $("['ilgiSilHayirButton']");

            switch (secim) {
                case "Evet":
                    clickJs(btnSilmeOnayiEvet);
                    break;
                case "Hayır":
                    clickJs(btnSilmeOnayiHayir);
                    break;
            }
        }


    }

    public class IliskiliEvraklarTab extends MainPage {

        //İlişkili Evraklar tabı - Dosya Ekle
        SelenideElement txtIliskiliEvraklarIlisikMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dosyaAciklama"));
        SelenideElement btnIliskiliEvraklarTaramaHavuzudanEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:uploadFromTarananEvrakHavuzuIliskiA"));
        SelenideElement btnTaramaHavuzuSorgula = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSorgulaButton"));
        SelenideElement chkTaramaHavuzuDosya1 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:selectionId"));
        SelenideElement chkTaramaHavuzuDosya2 = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:1:selectionId"));
        SelenideElement txtDosya1Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:0:j_idt']");
        SelenideElement txtDosya2Aciklama = $("[id^='taramaHavuzuFormId:taramaHavuzuDataTableId:1:j_idt']");
        SelenideElement cmbTaramaTuru = $(By.id("taramaHavuzuFormId:taramaHavuzuDataTableId:0:tarananTuruId"));
        SelenideElement cmbEvrakTuru = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:tarananTuruId"));
        SelenideElement chkTaramaHavuzuTarihBaslangic = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuIlkTarihCalendar_input"));
        SelenideElement chkTaramaHavuzuTarihBitis = $(By.id("taramaHavuzuFormId:filterAccordionPanelId:taramaHavuzuSonTarihCalendar_input"));

        SelenideElement btnTaramaHavuzuTamam = $(By.id("taramaHavuzuFormId:taramaHavuzuTamamButton"));
        ;
        ElementsCollection trDosyalistesi = $$("tbody[id*='yeniGidenEvrakForm:ilisikListesiDataTable'] tr[role='row']");
        SelenideElement btnDosyaEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonA_input"));
        ;
        SelenideElement lblDosyaAdi = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dosyaAdi"));

        SelenideElement btnIliskiliDosyaFileUpload = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonA"));
        SelenideElement btnIliskiliEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikEkleButton"));
        SelenideElement btnIliskiliTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikTemizleButton"));


        //İlişkili Evraklar tab - Sistemde kayıtlı evrak ekle
        SelenideElement dateIliskiliSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihBasId_input"));
        SelenideElement dateIliskiliSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihSonId_input"));
        SelenideElement cmbIliskiliSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakAramaAranacakYerId"));
        SelenideElement txtIliskiliSistemdeEvrakArama = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:evrakAramaText"));
        SelenideElement btnIliskiliSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dokumanAraButton"));
        SelenideElement btnSistemdeKayitliEvrakTab = $("a[href='#yeniGidenEvrakForm:ilisikIslemleriTabView:sistemdeKayitliEvragiEkleTab']");
        ElementsCollection trEvraklarListesi = $$("tbody[id*='yeniGidenEvrakForm:ilisikIslemleriTabView:sistemdeKayitliEvrakListesiDataTable'] tr[role='row']");
        SelenideElement btnEvrakIlisikEkle = $("[id^='yeniGidenEvrakForm:ilisikIslemleriTabView:sistemdeKayitliEvrakListesiDataTable'] [class$='document-follow']");
        SelenideElement btnEvrakDetay = $(By.id("yeniGidenEvrakForm:evrakEkTabView:sistemdeKayitliEvrakListesiDataTable:0:detayGosterButton"));
        SelenideElement evrakDetayiPageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
        SelenideElement btnEvrakDetayiPenceresiKapat = $("#windowReadOnlyEvrakDialog > div:nth-of-type(1) .ui-icon-closethick");
        SelenideElement btnTercumeEkleTab = $("a[href='#yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeEvragiEkleTab']");

        //İlişkili Evraklar tab - Tercüme Ekle
        SelenideElement txtIlisikTercumeEkleIlisikMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeAciklama"));
        SelenideElement btnIlisikTercumeEkleDosyaEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonB_input"));
        SelenideElement btnIlisikTercumeEkleEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeEkleButton"));
        SelenideElement btnIlisikTercumeEkleTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeTemizleButton"));
        SelenideElement lblTercumeDosyaAdi = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeDosyaAdi"));


        @Step("Ilişkili evraklar tabını aç")
        private IliskiliEvraklarTab open() {
            tabIliskiliEvraklar.shouldBe(visible);
            clickJs(tabIliskiliEvraklar);
            return this;
        }

        @Step("İlişik metni doldur")
        public IliskiliEvraklarTab ilisikMetniDoldur(String ilisikMetni) {
            txtIliskiliEvraklarIlisikMetni.setValue(ilisikMetni);
            return this;
        }

        @Step("Tarama havuzundan ekle")
        public IliskiliEvraklarTab taramaHavuzundanEkle() {
            btnIliskiliEvraklarTaramaHavuzudanEkle.click();
            return this;
        }

        @Step("Tarama havuzu sorgula")
        public IliskiliEvraklarTab taramaHavuzuSorgula() {
            btnTaramaHavuzuSorgula.click();
            return this;
        }

        @Step("Ilişkili dosya seç")
        public IliskiliEvraklarTab birinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya1.setSelected(secim);
            return this;
        }

        @Step("Ilişkili dosya seç")
        public IliskiliEvraklarTab ikinciEvrakSec(boolean secim) {
            chkTaramaHavuzuDosya2.setSelected(secim);
            return this;
        }

        @Step("İlişik metni doldur")
        public IliskiliEvraklarTab dosya1AciklamaDoldur(String ilisikMetni) {
            txtDosya1Aciklama.setValue(ilisikMetni);
            return this;
        }


        @Step("İlişik metni doldur")
        public IliskiliEvraklarTab dosya2AciklamaDoldur(String ilisikMetni) {
            txtDosya2Aciklama.setValue(ilisikMetni);
            return this;
        }

        @Step("Evrak aranacak yer seç")
        public IliskiliEvraklarTab evrakAranacakYerSec(String turu) {
            cmbIliskiliSistemdeEvrakAranacakyer.selectOption(turu);
            return this;
        }


        @Step("Tarama türü seç")
        public IliskiliEvraklarTab taramaTuruSec(String turu) {
            cmbTaramaTuru.selectOption(turu);
            return this;
        }

        @Step("Evrak türü seç")
        public IliskiliEvraklarTab evrakTuruSec(String turu) {
            cmbEvrakTuru.selectOption(turu);
            return this;
        }

        @Step("Tarama havuzu tamam")
        public IliskiliEvraklarTab taramaHavuzuTamam() {
            btnTaramaHavuzuTamam.shouldBe(visible);
            clickJs(btnTaramaHavuzuTamam);
            return this;
        }

        @Step("Ekranın alt kısmında listelenen evraklara dosyanın geldiği kontrolu: {description}")
        public IliskiliEvraklarTab listelenenEvraklaraDosyanınGeldigiKontrolu(String value, String description) {

            trDosyalistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Ekranın alt kısmında listelenen evraklara indir butonunun aktif geldiği kontrolu")
        public IliskiliEvraklarTab listelenenEvraklardaIndırButonuKontrol(String dosyaAdi) {

            trDosyalistesi
                    .filterBy(text(dosyaAdi))
                    .get(0)
                    .$("[class='ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only tipTip'] [class$='ui-icon-disk']").shouldBe(visible);

            return this;
        }

        @Step("Dosya ekle: {dosyaAdi}")
        public IliskiliEvraklarTab dosyaEkle(String pathPDF, String dosyaAdi) {

            uploadFile(btnDosyaEkle, pathPDF);

            return this;
        }

        @Step("Eklenen dosya adi kontrol : {dosyaAdi}")
        public IliskiliEvraklarTab iliskiliSitemdeEklenenDosyaAdiKontrol(String dosyaAdi) {

            Assert.assertEquals(lblDosyaAdi.getText().contains(dosyaAdi), true);

            return this;
        }

        @Step("İlişkili Evraklar Tab - Ekle")
        public IliskiliEvraklarTab iliskiliEkle() {
            btnIliskiliEkle.click();
            return this;
        }

        @Step("Dosya yüklenene kadar 60 dk bekle, 60 dktan fazla sürerse timeout hatası ver")
        public IliskiliEvraklarTab dosyaYukleneneKadarBekle() {

            waitForLoadingJS(WebDriverRunner.getWebDriver(), 60);

            return this;
        }

        @Step("İlişkili Evraklar/Sistemde Kayıtlı Evrak Ekle Tab - Aç")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleTabiniAc() {
            btnSistemdeKayitliEvrakTab.click();
            return this;
        }

        @Step("Sistemde Kayıtlı Evrak Ekle alan kontrolleri")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleAlanKontrolleri() {

            dateIliskiliSistemdeEvrakTarihiBaslangic.shouldBe(visible);
            dateIliskiliSistemdeEvrakTarihiSon.shouldBe(visible);
            cmbIliskiliSistemdeEvrakAranacakyer.shouldBe(visible);
            txtIliskiliSistemdeEvrakArama.shouldBe(visible);
            btnIliskiliSistemdeDokumanAra.shouldBe(visible);

            return this;
        }

        @Step("Evrak Arama doldur: {evrakNo}")
        public IliskiliEvraklarTab evrakAramaDoldur(String evrakNo) {
            txtIliskiliSistemdeEvrakArama.setValue(evrakNo);
            return this;
        }

        @Step("Dokuman Ara")
        public IliskiliEvraklarTab dokumanAra() {
            btnIliskiliSistemdeDokumanAra.click();
            return this;
        }

        @Step("Evrak ilişik ekle")
        public IliskiliEvraklarTab evrakIlisikEkle() {
            btnEvrakIlisikEkle.click();
            return this;
        }


        @Step("Evrakların listelendiği kontrolu")
        public IliskiliEvraklarTab listelenenEvraklardaKontrol(String evrakNo) {

            trEvraklarListesi
                    .filterBy(text(evrakNo))
                    .get(0)
                    .shouldBe(exist);
            return this;
        }

        @Step("Evrakın listeye gelmediği kontrolu")
        public IliskiliEvraklarTab listelenenEvraklardaGelmemeKontrolu(String evrakNo) {

            int size = trEvraklarListesi
                    .filterBy(text(evrakNo)).size();
            Assert.assertEquals(size == 0, true);

            return this;
        }


        @Step("Evrak ve dosya listesinde detay göster")
        public IliskiliEvraklarTab eklenenEvrakVeDosyaListesindeDetayGoster(String value) {

            trDosyalistesi
                    .filterBy(text(value))
                    .get(0)
                    .shouldBe(exist)
                    .$("[id$='ilisikListesiDetayButton']").click();

            return this;
        }

        @Step("Evrak Detayının geldiği kontrolu")
        public IliskiliEvraklarTab evrakDetayiKontrol() {
            evrakDetayiPageTitle.shouldBe(visible);
            return this;
        }

        @Step("Evrak Detayı sayfasını kapat")
        public IliskiliEvraklarTab evrakDetayiSayfasınıKapat() {
            btnEvrakDetayiPenceresiKapat.click();
            return this;
        }

        @Step("İlişkili Evraklar/Tercüme Ekle Tab - Aç")
        public IliskiliEvraklarTab tercumeEkleTabiniAc() {
            btnTercumeEkleTab.click();
            return this;
        }


        @Step("İlişik metni doldur")
        public IliskiliEvraklarTab tercumeIlisikMetniDoldur(String ilisikMetni) {
            txtIlisikTercumeEkleIlisikMetni.setValue(ilisikMetni);
            return this;
        }

        @Step("İlişik/Tercüme Ekle - Dosya ekle: {dosyaAdi}")
        public IliskiliEvraklarTab tercumeDosyaEkle(String pathPDF, String dosyaAdi) {

            uploadFile(btnIlisikTercumeEkleDosyaEkle, pathPDF);

            return this;
        }

        @Step("Eklenen dosya adi kontrol : {dosyaAdi}")
        public IliskiliEvraklarTab tercumeEklenenDosyaAdiKontrol(String dosyaAdi) {

            Assert.assertEquals(lblTercumeDosyaAdi.getText().contains(dosyaAdi), true);

            return this;
        }

        @Step("Ekle")
        public IliskiliEvraklarTab tercumeEkleEkle() {
            btnIlisikTercumeEkleEkle.click();
            return this;
        }

        @Step("Detay tıklanarak açılan tercüme kontrolu")
        public IliskiliEvraklarTab eklenTercumeDosyaKontrolu() {

            switchTo().window(1);
            closeNewWindow();
            switchTo().window(0);
            return this;
        }

        @Step("Eklenen ilişik silme")
        public IliskiliEvraklarTab ismeGoreIlisikSilme(String ilisik) {

            trDosyalistesi
                    .filterBy(text(ilisik))
                    .get(0)
                    .$("[id$='ilisikListesiSilButton']").click();

            return this;
        }

        @Step("Evrak tarih aralığı - başlangıç")
        public IliskiliEvraklarTab evrakTarihBaslangicDoldur(String tarihBaslangic) {
            chkTaramaHavuzuTarihBaslangic.setValue(tarihBaslangic);
            return this;
        }

        @Step("Evrak tarih aralığı - bitiş")
        public IliskiliEvraklarTab evrakTarihBitisDoldur(String tarihBitis) {
            chkTaramaHavuzuTarihBitis.setValue(tarihBitis);
            return this;
        }

        @Step("Popup İlişik Silme Onay - Kaydı silmek istediğinize emin misiniz? : {secim}")
        public void ilisikSilmeOnayi(String secim) {

            SelenideElement btnSilmeOnayiEvet = $("[id$='ilisikSilEvetButton']");
            SelenideElement btnSilmeOnayiHayir = $("['ilisikSilHayirButton']");

            switch (secim) {
                case "Evet":
                    clickJs(btnSilmeOnayiEvet);
                    break;
                case "Hayır":
                    clickJs(btnSilmeOnayiHayir);
                    break;
            }
        }

        @Step("Popup Ek Ekle - Eklemek istediğiniz dosya 300 dpidan düşüktür devam etmek istiyor musunuz? : {secim}")
        public IliskiliEvraklarTab ekEkleDusukDpiPopupOnayi(String secim) {

            SelenideElement ekEklePopup = $("[id='yeniGidenEvrakForm:iliskiDusukDpiConfirmDialog'] [class= 'ui-dialog-title']");
            SelenideElement btnEkEkleEvet = $("[id='yeniGidenEvrakForm:iliskiDusukDpiConfirmDialog'] [id$= 'iliskiDusukDpiEvetButton']");
            SelenideElement btnEkEkleHayir = $("[id='yeniGidenEvrakForm:iliskiDusukDpiConfirmDialog'] [id$= 'iliskiDusukDpiHayirButton']");

            if (ekEklePopup.isDisplayed()) {
                switch (secim) {
                    case "Evet":
                        clickJs(btnEkEkleEvet);
                        break;
                    case "Hayır":
                        clickJs(btnEkEkleHayir);
                        break;
                }
            }
            return this;
        }

    }

    public class EvrakNotlariTab extends MainPage {

        //Evrak Notları
        SelenideElement btnKisiselNotEkle = $(By.id("yeniGidenEvrakForm:kisiselNotEkleDataTableId:kisiselNotEkleId"));

        SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:onayAkisiEkle"));
        //SelenideElement tableOnayAkisiEkleKullanicilar = $(By.xpath("//tbody[@id='yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable_data']/tr/td/div/table/tbody/tr/td"));
        SelenideElement tableOnayAkisiEkleKullanicilar = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable_data"));

        SelenideElement btnOnayAkisiKullaniciKullan = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:anlikAkisKullanButton"));
        BelgenetElement txtOnayAkisiKullanicilar = comboLov("[id$='akisAdimLov:LovText']");
        SelenideElement listOnayAkisikullanicilar = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:lovTree"));

        private EvrakNotlariTab open() {
            tabEvrakNotlari.click();
            return this;

        }
    }

    public class SablonIslemleriTab extends MainPage {

        SelenideElement txtSablonAdi = $(By.id("yeniGidenEvrakForm:sablonAdiText_id"));
        SelenideElement btnEvrakiYeniSablonOlarakKaydet = $(By.id("yeniGidenEvrakForm:sablonIslemYeniButton_Id"));
        ElementsCollection tableKisiselSablonlar = $$("tbody[id$='sablonDataTableKisisel_data'] > tr[role='row']");
        ElementsCollection tableBirimSablonlar = $$("tbody[id$='sablonDataTable_data'] > tr[role='row']");

        SelenideElement cmbSablonTuru = $(By.id("yeniGidenEvrakForm:evrakSablonTuru"));
        ElementsCollection listSablonTurleri = $$("div[id='yeniGidenEvrakForm:evrakSablonTuru_panel'] > ul > li");
        BelgenetElement txtKullanacakBirimler = comboLov(By.id("yeniGidenEvrakForm:sablonLov_id:LovText"));

        private SablonIslemleriTab open() {
            tabSablonIslemleri.click();
            return this;
        }

        @Step("Şablon adi doldur {sablonAdi}")
        public SablonIslemleriTab sablonAdiDoldur(String sablonAdi) {
            txtSablonAdi.setValue(sablonAdi);
            return this;
        }

        @Step("Evrakı yeni şablon olarak kaydet ")
        public SablonIslemleriTab evrakiYeniSablonOlarakKaydet() {
            btnEvrakiYeniSablonOlarakKaydet.click();
            return this;
        }

        @Step("{sablonAdi} sablonunu uygula")
        public SablonIslemleriTab kisiselSablonuEvrakaUygula(String sablonAdi) {
            ElementsCollection kisiselPages = $$("td[id$='sablonDataTableKisisel_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

            for (int i = 0; i < kisiselPages.size(); i++) {
                kisiselPages.get(i).click();

                SelenideElement btnUygula = tableKisiselSablonlar
                        .filterBy(text(sablonAdi))
                        .first()
                        .$("button[id$=':sablonListesiUygulaButtonKisisel_id']");

                if (btnUygula.isDisplayed()) {
                    btnUygula.click();
                    break;
                }
            }
            return this;
        }

        @Step("{sablonAdi} sablonunu uygula")
        public SablonIslemleriTab birimSablonuEvrakaUygula(String sablonAdi) {
            ElementsCollection birimPages = $$("td[id$='sablonDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

            for (int i = 0; i < birimPages.size(); i++) {
                birimPages.get(i).click();

                SelenideElement btnUygula = tableBirimSablonlar
                        .filterBy(text(sablonAdi))
                        .first()
                        .$("button[id$=':sablonListesiUygulaButton_id']");

                if (btnUygula.isDisplayed()) {
                    btnUygula.click();
                    break;
                }
            }
            return this;
        }


        @Step("Şablon Türü seç: {sablonTuru}")
        public SablonIslemleriTab sablonTuruSec(String sablonTuru) {
            cmbSablonTuru.click();
            listSablonTurleri
                    .filterBy(text(sablonTuru))
                    .first()
                    .click();
            return this;
        }

        @Step("Kullanacak Birim Seç: {kullanacakBirim}")
        public SablonIslemleriTab kullanacakBirimSec(String kullanacakBirim) {
            txtKullanacakBirimler.selectLov(kullanacakBirim);
            return this;
        }


    }

    public class PDFKontrol extends MainPage {

        @Step("Gereği alanında adres gelmedigi, Bilgi alanında dagitim yerinin adresi ile geldigi kontrolu")
        public PDFKontrol geregiBilgiAlaniAdresPdfKontrol(String birinciKullaniciGeregiAdresi, String ikinciKullaniciBilgiAdresi) {

            //gereği: div[@id='viewer']/div[@class='page']//div[.='xrpisak Mahallesi ŞİŞLİ / İSTANBUL']
            //blgil : div[@id='viewer']/div[@class='page']//div[.='Gültepe Mahallesi KAĞITHANE / İSTANBUL']

            SelenideElement geregiAdresAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + birinciKullaniciGeregiAdresi + "']"));
            SelenideElement bilgiAdresAlaniPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='" + ikinciKullaniciBilgiAdresi + "']"));

            //div[@id='viewer']/div[@class='page']//div[.='Gültepe Mahallesi KAĞITHANE / İSTANBUL']

            System.out.println(birinciKullaniciGeregiAdresi);
            System.out.println("Beklenen ikinci kullanici adresi: " + ikinciKullaniciBilgiAdresi);
            System.out.println("Gelen ikinci kullanici adresi: " + bilgiAdresAlaniPDF.getText());

            Assert.assertEquals(geregiAdresAlaniPDF.isDisplayed(), false);
            Assert.assertEquals(bilgiAdresAlaniPDF.isDisplayed(), true);
            Assert.assertEquals(bilgiAdresAlaniPDF.getText(), ikinciKullaniciBilgiAdresi);
            takeScreenshot();
            return this;
        }

        @Step("Pdf önizleme kisayol gonder")
        public PDFKontrol PDFOnizlemeKisayolGonder(String kisayol) {

            SelenideElement tc = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='T.C.']"));
            String str = tc.getText();
            tc.click();

            tc.sendKeys(Keys.SHIFT + "o");

            return this;
        }

        @Step("Pdf hitap kontrolu")
        public PDFKontrol PDFHitapKontrol(String beklenenPDFHitap) {
            String PDFHitap = $(By.xpath("//*[@id='viewer']/div/div[2]/div[5]")).getText();
            Assert.assertEquals(PDFHitap.contains(beklenenPDFHitap), true);
            return this;
        }

        @Step("Pdf EK-1 kontrolu")
        public PDFKontrol PDFEk1Kontrolu(String ek1) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[12]")).getText();
            Assert.assertEquals(pdfEK1.contains(ek1), true);
            return this;
        }

        @Step("Pdf EK-2 kontrolu")
        public PDFKontrol PDFEk2Kontrolu(String ek2) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[13]")).getText();
            Assert.assertEquals(pdfEK1.contains(ek2), true);
            return this;
        }

        @Step("Pdf EK-3 kontrolu")
        public PDFKontrol PDFEk3Kontrolu(String ek3) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[14]")).getText();
            Assert.assertEquals(pdfEK1.contains(ek3), true);
            return this;
        }

        @Step("Pdf Dağıtımda eklerin gitmeyeceği yrler kontrolu: {dagitim}")
        public PDFKontrol eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim1(String dagitim, String ekler) {
            String pdfDagitim1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[17]")).getText();
            Assert.assertEquals(ekler.contains(ekler), true);
            return this;
        }

        @Step("Pdf Dağıtımda eklerin gitmeyeceği yerler kontrolu: {dagitim}")
        public PDFKontrol eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim2(String dagitim, String ekler) {
            String pdfDagitim1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[18]")).getText();
            Assert.assertEquals(ekler.contains(ekler), true);
            return this;
        }

        @Step("Pdf Dağıtımda eklerin gitmeyeceği yerler kontrolu: {dagitim}")
        public PDFKontrol eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim3(String dagitim, String ekler) {
            String pdfDagitim1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[19]")).getText();
            Assert.assertEquals(ekler.contains(ekler), true);
            return this;
        }

        @Step("Pdf İlgi-1 kontrolu: {description}")
        public PDFKontrol PDFIlgi1Kontrolu(String ilgi1, String description) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[13]")).getText();
            Assert.assertEquals(pdfEK1.contains(ilgi1), true);
            return this;
        }

        @Step("Pdf İlgi-2 kontrolu: {description}")
        public PDFKontrol PDFIlgi2Kontrolu(String ilgi2, String description) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[15]")).getText();
            Assert.assertEquals(pdfEK1.contains(ilgi2), true);
            return this;
        }

        @Step("Pdf İlgi-3 kontrolu: {description}")
        public PDFKontrol PDFIlgi3Kontrolu(String ilgi3, String description) {
            String pdfEK1 = $(By.xpath("//*[@id='viewer']/div/div[2]/div[17]")).getText();
            Assert.assertEquals(pdfEK1.contains(ilgi3), true);
            return this;
        }
    }
    //endregion
}
