package pages.ustMenuPages;

import com.codeborne.selenide.*;
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
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
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
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class*='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnKaydetOnayaSun2 = $("div[class='ui-tabmenu ui-tabmenu-right'] span[class='ui-button-icon-left ui-icon kaydetHavaleEt']");
    SelenideElement txtKaydetOnayaSunAciklama = $(By.id("windowCevapEvrakForm:onayIslemiAciklama"));
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");
    SelenideElement btnEvrakOlusturKapat = $(By.xpath("//div[@id='window3Dialog']//a/span[@class='ui-icon ui-icon-closethick']"));
    SelenideElement btnEvrakOlusturKapatEvet = $(By.id("kapatKaydetEvetButton"));
    SelenideElement divBilgileri = $(By.id("evrakBilgileriContainerDiv"));
    SelenideElement labelIlkIslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFixWidth']"));
    SelenideElement labelIkinciIslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFixWidth']"));
    SelenideElement labelIlkKullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFix']"));
    SelenideElement labelIkinciKullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFix']"));
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
        $(By.id("windowCevapEvrakForm:gonderButton")).click();
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
        //btnEvrakOlusturKapat.click();

        $(By.xpath("//span[@class='ui-dialog-title' and text()='Evrak Oluştur']/..//span[@class='ui-icon ui-icon-closethick']")).click();
        btnEvrakOlusturKapatEvet.click();
//div[@id='window1Dialog']//span[@class='ui-icon ui-icon-closethick']
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

    @Step("Kullanıcı işlem ve sıra kontrolu")
    public EvrakOlusturPage kullaniciIslemVeSiraKontrolu(String kullanici1, String islemTipi1, String kullanici2, String islemTipi2) {

        Assert.assertEquals(labelIlkIslemTipi.getText(), "1. " + islemTipi1);
        Assert.assertEquals(labelIkinciIslemTipi.getText(), "2. " + islemTipi2);
        Assert.assertEquals(labelIlkKullanici.getText(), kullanici1);
        Assert.assertEquals(labelIkinciKullanici.getText(), kullanici2);

        return this;
    }

    @Step("Kaydet ve onaya sun panelinde gönder butonuna tıkla.")
    public EvrakOlusturPage kaydetOnayaSunGonder(){
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

    public EkleriTab ekleriTabAc() {
        return ekleriTab.open();
    }

    public IlgileriTab ilgileriTabAc() {
        return ilgileriTab.open();
    }

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
        ElementsCollection cmbKullanicilarIlkImzalama = $$("[id='yeniGidenEvrakForm:evrakBilgileriList:18:anlikakisOlusturPanelGrid'] [id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='selectOneMenu']");
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
        //BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        BelgenetElement cmbOnayAkisi2 = comboLov(By.id("windowCevapEvrakForm:evrakBilgileriList:18:akisLov:LovText"));
        SelenideElement btnOnayAkisiEkle2 = $(By.id("windowCevapEvrakForm:evrakBilgileriList:18:onayAkisiEkle"));
        //BelgenetElement cmbOnayAkisi2 = comboLov(By.cssSelector("[id$='akisLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");

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
        SelenideElement btnIadeEt = $("[id='inboxItemInfoForm:dialogTabMenuRight:dialogTabMenuRight'] td:nth-child(7) button");
        BelgenetElement cmbKullaniciListesi = comboBox(By.id("inboxItemInfoForm:kullaniciListOneMenu_id_label"));
        SelenideElement txtNot = $(By.id("inboxItemInfoForm:notTextArea_id"));
        SelenideElement btnIadeEt2 = $(By.id("inboxItemInfoForm:iadeEtButton_id"));
        SelenideElement popUpEvrakDegisiklik = $(By.xpath("//span[normalize-space(text())='Evrakta değişiklik var, kaydetmek ister misiniz?']"));
        SelenideElement txtOnayIslemiAciklama = $(By.id("windowCevapEvrakForm:onayIslemiAciklama"));
        SelenideElement btnOnayIslemiGonder = $(By.id("windowCevapEvrakForm:gonderButton"));

        BelgenetElement cmbGeregi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
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
        SelenideElement aKendimiEkle = $("a[id$=':kendimiEkleCommand']");
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

        @Step("Otomatik onay akışı seç")
        public BilgilerTab otomatikOnayAkisi() {
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
            tblKullanıcılar.isDisplayed();
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
            cmbKullanicilarIlkImzalama.get(0).selectOption(imzalama);
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
            Assert.assertEquals(tblVekalet.isDisplayed(), true);
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
            txtBilgi.selectLov(bilgi);
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

        @Step("Geregi alanını seçilenleri kaldır")
        public BilgilerTab geregiSecilenleriKaldır() {
            txtGeregi.clearAllSelectedItems();
            return this;
        }

        @Step("Geregi alanında \"{geregi}\" seç")
        public BilgilerTab geregiSec(String geregi, Boolean clearAfterSelecion) {
            cmbGeregi.sendKeys(Keys.SHIFT);
            txtGeregi.selectLov(geregi);
            txtGeregi.clearLastSelectedItem();
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

        @Step("Otomatik onay akışı alanında geldiği görünür \"{ekranAdi}\" | \"{ekranAdi}\"")
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

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle(String kullanici) {

//            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.selectLov(kullanici);

            return this;
        }

        @Step("Onay Akışı Ekle")
        public BilgilerTab onayAkisiEkle() {
            clickJs(btnOnayAkisiEkle);
//            btnOnayAkisiEkle.pressEnter();
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

        @Step("İade Et butonu")
        public BilgilerTab iadeEt() {
            btnIadeEt.click();
            return this;
        }

        @Step("Kullanıcı Listesi kontrol")
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

        @Step("İade et")
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

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiDoldur(String onay) {
            cmbOnayAkisi.selectLov(onay);
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
                cmbOnayAkisi.clearLastSelectedItem();
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

        @Step("Onay akışı kullanıcı adı ve tipi kontrol et")
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
            btnKullan.pressEnter();
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
            txtOnayIslemiAciklama.sendKeys(aciklama);
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

        @Step("Kendimi ekle linkine tıkla.")
        public BilgilerTab kendimiEkle(){
            aKendimiEkle.click();
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

        @Step("İmzala")
        public EditorTab imzala() {
            btnImzala.click();
            return this;
        }

        @Step("İmzala")
        public EditorTab cevapYazEditörimzala() {
            $(By.xpath("//div[@id='windowCevapEvrakDialog']//tbody//td[2]//td[3]//button")).click();
            return this;
        }


        @Step("İmzala")
        public EditorTab sImzaImzala() {
            clickJs(btnSimzaImzala);
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
            SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
            sayisalImzaOnay.click();
            return this;
        }

        public EditorTab popupSImzalaIslemleri() {

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

        @Step("Gereği alani doldur")
        public EditorTab geregiDoldur(String text) {
            cmbGeregi.selectLov(text);
            return this;
        }

        @Step("Bilgi alani doldur")
        public EditorTab bilgiDoldur(String bilgi) {
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
            btnSayısalImzeEvet.click();
            return this;
        }

        @Step("Gereği alanı temizle")
        public EditorTab secilenGeregiSil() {
            cmbGeregi.clearLastSelectedItem();
            return this;
        }

        @Step("Seçilen bilgi sil")
        public EditorTab secilenBilgiSil() {
            cmbBilgi.clearLastSelectedItem();
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
    }

    public class EkleriTab extends MainPage {

        //Ekleri tabı - Dosya Ekle
        SelenideElement txtEkleriDosyaAciklama = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaAciklama"));
        SelenideElement btnEkleriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
        SelenideElement cmbEkleriDosyaGuvenlikKodu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKodu"));
        SelenideElement btnEkleriDosyaEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaEkleButton"));
        SelenideElement btnEkleriDosyaTemizle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaTemizleButton"));
        SelenideElement chkEkListesiniEkYap = $(By.id("yeniGidenEvrakForm:j_idt30306"));
        SelenideElement btnDosyaEkle = $(By.xpath("//input[@id='yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input']"));

        //Ekleri tabı - Fiziksel Ekle
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

        //Ekleri tabı - Arşivde Kayıtlı Evrak Ekle
        SelenideElement dateArsivdeEvrakAraTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
        SelenideElement dateArsivdeEvrakAraTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
        SelenideElement txtArsivdeEvrakAraKonu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
        SelenideElement txtArsivdeEvrakAraKullanici = $(By.id("yeniGidenEvrakForm:evrakEkTabView:kisiyeLov_id:LovText"));
        SelenideElement txtArsivdeEvrakAraSayi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));
        SelenideElement btnArsivdenEvrakAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:arsivdenEvrakAraButtonId"));

        SelenideElement btnImzala = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='imzala']");

        private EkleriTab open() {
            tabEkleri.click();
            return this;

        }

        @Step("Ekleri Tab - Dosya Ekle")
        public EkleriTab ekleriDosyaEkle(String pathToFile) {
            uploadFile(btnEkleriDosyaFileUpload, pathToFile);
            return this;
        }

        @Step("Ekleri Tab - Ek Metni")
        public EkleriTab ekleriEkMetniDoldur(String aciklama) {
            txtEkleriDosyaAciklama.sendKeys(aciklama);
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

        @Step("Sistemde Kayıtlı Evrak Ekle tab aç")
        public EkleriTab sistemdeKayitliEvrakEkleTabAc() {
            btnSistemdeKayitliEvrakTab.click();
            return this;
        }

        @Step("Evrakın Aranaği Yer seç")
        public EkleriTab evrakinAranacagiYerSec(String aranacakYer) {
            cmbEvrakAranacakyer.selectOption(aranacakYer);
            return this;
        }

        @Step("Evrak Arama doldur")
        public EkleriTab evrakAramaDoldur(String value) {
            txtEvrakArama.sendKeys(value);
            return this;
        }

        @Step("Dokuman Ara")
        public EkleriTab dokumanAra() {
            btnSistemdeDokumanAra.click();
            return this;
        }

        @Step("Tablodao Evrak no kontrolü")
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

        @Step("Dosya ekle {description} : {pathPDF}")
        public EkleriTab dosyaEkle(String pathPDF, String description) {

            uploadFile(btnDosyaEkle, pathPDF);

            return this;
        }

        public EkleriTab dosyaYukleneneKadarBekleme(int time, int count) {


            //waitForLoadingJS2(WebDriverRunner.getWebDriver());
            System.out.println("   Sezai ............................. ");
            /*            System.out.println("Count:" +  WebDriverRunner.getWebDriver().findElements(By.cssSelector("div[style*='display: block;'] .ui-progressbar-value")).size());

            boolean exists = WebDriverRunner.getWebDriver().findElements(By.cssSelector("div[style*='display: block;'] .ui-progressbar-value"))
                    .size() != 0;
            if (exists && count > 0) {
                count--;
                dosyaYukleneneKadarBekleme(500, count);
            }*/

            return this;
        }
    }

    public class IlgileriTab extends MainPage {

        //İlgileri tabı - Dosya Ekle
        SelenideElement txtIlgileriDosyaIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaAciklama"));
        SelenideElement btnIlgileriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:fileUploadButtonA_input"));
        SelenideElement btnIlgileriDosyaEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dosyaEkleButton"));

        //İlgileri tabı - Metin Ekle
        SelenideElement txtIlgileriMetinIlgiMetni = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaTextArea"));
        SelenideElement btnIlgileriMetinEkle = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));

        //İlgileri tabı - Sistemde kayıtlı evrak ekle
        SelenideElement tabIliskiliSistemdeKayitliEvrakEkle = $(By.linkText("Sistemde Kayıtlı Evrak Ekle"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBitis = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
        SelenideElement cmbIlgileriSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId"));
        SelenideElement txtIlgileriSistemdeEvrakArama = $(By.name("yeniGidenEvrakForm:ilgiIslemleriTabView:evrakAramaText"));
        SelenideElement btnIlgileriSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dokumanAraButton"));
        SelenideElement tableSistemdeKayitliEvrak = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable"));
        SelenideElement btnTablodaBulunanIlkEvrakiEkle = $(By.xpath("//*[starts-with(@id,'yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvrakListesiDataTable:0:j_idt')]"));

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

        private IlgileriTab open() {
            tabIlgileri.click();
            return this;
        }

        @Step("IlgileriTab Sisteme Kayitli Evrak Ekle")
        public IlgileriTab sistemeKayitliEvrakEkleTab() {
            tabIliskiliSistemdeKayitliEvrakEkle.click();
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
    }

    public class IliskiliEvraklarTab extends MainPage {

        //İlişkili Evraklar tabı - Dosya Ekle
        SelenideElement txtIliskiliDosyaIlgiMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dosyaAciklama"));
        SelenideElement btnIliskiliDosyaFileUpload = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonA"));
        SelenideElement btnIliskiliEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikEkleButton"));
        SelenideElement btnIliskiliTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:ilisikTemizleButton"));

        //İlişkili Evraklar tab - Sistemde kayıtlı evrak ekle
        SelenideElement dateIliskiliSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihBasId_input"));
        SelenideElement dateIliskiliSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihSonId_input"));
        SelenideElement cmbIliskiliSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:iliskiliEvrakAramaAranacakYerId"));
        SelenideElement txtIliskiliSistemdeEvrakArama = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:evrakAramaText"));
        SelenideElement btnIliskiliSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:dokumanAraButton"));

        //İlişkili Evraklar tab - Tercüme Ekle
        SelenideElement txtIlisikIslemleriTabViewIlisikMetni = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeAciklama"));
        SelenideElement btnIlisikIslemleriTabViewDosyaEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:fileUploadButtonB"));
        SelenideElement btnIlisikIslemleriTabViewEkle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeEkleButton"));
        SelenideElement btnIlisikIslemleriTabViewTemizle = $(By.id("yeniGidenEvrakForm:ilisikIslemleriTabView:tercumeTemizleButton"));

        private IliskiliEvraklarTab open() {
            tabIliskiliEvraklar.click();
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
    }
    //endregion


}
