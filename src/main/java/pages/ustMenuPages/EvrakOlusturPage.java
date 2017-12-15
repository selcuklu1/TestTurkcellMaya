package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.not;
import static pages.pageComponents.belgenetElements.BelgentCondition.required;

///////////////////////////////////////////////////////////////////////////
// Ilyas Bayraktar
///////////////////////////////////////////////////////////////////////////
public class EvrakOlusturPage extends MainPage {

    //region Tabs local variables
    private BilgilerTab bilgilerTab = new BilgilerTab();
    private EkleriTab ekleriTab = new EkleriTab();
    private EditorTab editorTab = new EditorTab();
    private IlgileriTab ilgileriTab = new IlgileriTab();
    private IliskiliEvraklarTab iliskiliEvraklarTab = new IliskiliEvraklarTab();
    private EvrakNotlariTab evrakNotlariTab = new EvrakNotlariTab();
    public PDFKontrol pdfKontrol = new PDFKontrol();
    //endregion


    //region Elements
    SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
    SelenideElement tabEditor = $("button .editor");
    SelenideElement tabEkleri = $("button .kullaniciEkleri");
    SelenideElement tabIlgileri = $("button .kullaniciIlgileri");
    SelenideElement tabIliskiliEvraklar = $("button .kullaniciIliskileri");
    SelenideElement tabSablonIslemleri = $("button .sablonOlustur");
    SelenideElement tabEvrakNotlari = $("button .evrakNot");
    SelenideElement tabEvrakDogrulama = $("button .evrakDogrulamaAktarimIslemleri");

    SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class*='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");
    SelenideElement btnEvrakOlusturKapat = $(By.xpath("//div[@id='window3Dialog']//a/span[@class='ui-icon ui-icon-closethick']"));
    SelenideElement btbEvrakOlusturKapatEvet = $(By.id("kapatKaydetEvetButton"));
    SelenideElement divBilgileri = $(By.id("evrakBilgileriContainerDiv"));
    SelenideElement labelIlkIslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFixWidth']"));
    SelenideElement labelIkinciIslemTipi = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFixWidth']"));
    SelenideElement labelIlkKullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[1]//label[@class='columnLabelFix']"));
    SelenideElement labelIkinciKullanici = $(By.xpath("//form[@id='yeniGidenEvrakForm']/table[2]//label[@class='columnLabelFix']"));

    ElementsCollection tblVekaletVerenAlan =$$("[id='yeniGidenEvrakForm:kullaniciBirimSecenekleri_data'] tr[role='row']");
    //endregion
    @Step("Evrak Oluştur sayfası aç")
    public EvrakOlusturPage openPage() {
        new UstMenu().ustMenu("Evrak Oluştur");
        $("#yeniGidenEvrakForm").shouldBe(visible);
        return this;
    }


    @Step("PDF Önizleme")
    public EvrakOlusturPage pdfOnIzleme() {
        btnPDFOnizleme.click();
        return this;
    }

    @Step("PDF Önizleme")
    public EvrakOlusturPage kaydetOnayaSun() {
        btnKaydetOnayaSun.click();
        return this;
    }

    @Step("Kaydet")
    public EvrakOlusturPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("\"{0}\" ekran açılması beklenen statü: {1}")
    public EvrakOlusturPage PDFOnizlemeKisayolGonder(String kisayol) throws InterruptedException {

        SelenideElement tc = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='T.C.']"));
        String str = tc.getText();
        tc.click();

        tc.sendKeys(Keys.SHIFT + "o");

        return this;
    }

    @Step("{0} ekran açılması beklenen statü: {1}")
    public EvrakOlusturPage kisayolEkranKontrol(String ekranAdi, boolean acilmali) {
        boolean t = $$("[id^='window'][id$='Button_ID'] .ui-button-text")
                .filterBy(Condition.text(ekranAdi)).size() > 0;
        Assert.assertEquals(t, acilmali, "Ekran açılmamalı");
        return this;
    }

    public EvrakOlusturPage evrakOlusturPageKapat() {
        //btnEvrakOlusturKapat.click();

        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Evrak Oluştur]']"))
                .contextClick();
        btbEvrakOlusturKapatEvet.click();

        return this;
    }

    public EvrakOlusturPage kullaniciIslemVeSiraKontrolu(String kullanici1, String islemTipi1, String kullanici2, String islemTipi2) {

        Assert.assertEquals(labelIlkIslemTipi.getText(), "1. " + islemTipi1);
        Assert.assertEquals(labelIkinciIslemTipi.getText(), "2. " + islemTipi2);
        Assert.assertEquals(labelIlkKullanici.getText(), kullanici1);
        Assert.assertEquals(labelIkinciKullanici.getText(), kullanici2);

        return this;
    }


    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    public class BilgilerTab extends MainPage {

        //region Elements

        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");

        //label[normalize-space(text())='Konu Kodu']/../..//input

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

        //Kanun Kapsam Tipi
        SelenideElement rdbKanunKapsamTipiNormal = $x("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Normal'])]");
        SelenideElement rdbKanunKapsamTipiBilgiEdinmeKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Bilgi Edinme Kanunu'])]");
        SelenideElement rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Kişisel Verilerin Korunması Kanunu'])]");
        SelenideElement txtEvrakSayiEkMetni = $("input[id$='evrakSayiEkMetniInputText']");

        SelenideElement txtAciklama = $(By.xpath("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Açıklama']/ancestor::tr[@class='ui-datagrid-row']//textarea"));
        SelenideElement cmbIvedik = $("select[id$='ivedilik']");
        SelenideElement dateMiat = $("input[id$='miatCalendar_input']");

        SelenideElement cmbBilgiSecimTipi = $x("//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
        //SelenideElement cmbBilgiSecimTipi = $(By.xpath("//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));

        BelgenetElement txtBilgi = comboLov("input[id$='bilgiLov:LovText']");
        SelenideElement btnBilgiTree = $("button[id$='bilgiLov:treeButton']");

        SelenideElement cmbGeregiSecimTipi = $(By.xpath("//select[starts-with(@id,'yeniGidenEvrakForm:evrakBilgileriList:16:j_idt')]"));
        //Bu çalışmıyor. SelenideElement cmbGeregiSecimTipi = $x("//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select");
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
        SelenideElement cmbKullanicilarImza = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='selectOneMenu']");
        SelenideElement btnOnayAkisGuncelle = $(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:j_idt'] [class$='update-icon']"));
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");

        //Bilgileri tabı
        BelgenetElement txtKonuKodu = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']");

        SelenideElement txtKaldiralacakKlasorler = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:LovText"));
        SelenideElement rdbNormal = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:0"));
        SelenideElement rdbBilgiEdinmeKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:1"));
        SelenideElement rdbKisiselVerilerinKorunmasiKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:2"));
        SelenideElement cbmAkisAdim = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement tblKullanıcılar = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisAdimLov:LovSecilenTable_data']");
        ElementsCollection tblKullanıcılar2 = $$("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisAdimLov:LovSecilenTable_data'] >tr");
        SelenideElement tblVekalet = $(By.id("yeniGidenEvrakForm:kullaniciBirimSecimiDialogId"));
        SelenideElement btnVekaletTablosuKapat = $(By.xpath("//div[@id='yeniGidenEvrakForm:kullaniciBirimSecimiDialogId']//span[@class='ui-icon ui-icon-closethick']"));
        SelenideElement btnKullan = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='anlikAkisKullanButton']");
        SelenideElement txtOnayAkisi = $("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovSecilen']");

        BelgenetElement cmbGeregi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");
        BelgenetElement cmbGeregiPostaTipi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:selectOneMenu"));
        // select[id^='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:'][id$=':selectOneMenu']
        SelenideElement cmbPostaTipi = $("select[id^='yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:'][id$=':selectOneMenu']");
        By cmbGeregiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");

        // BelgenetElement cmbOnayAkisi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
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

        BelgenetElement cmbBilgi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']"));
        By cmbBilgiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']");

        SelenideElement lovTreeKapat = $(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='bilgiLov:lovTreePanelKapat']"));
        SelenideElement lovTreeSec = $(By.xpath("//*[@id=\"yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:lovTree:0_0\"]/div/span/span[2]"));
        SelenideElement btnOtomatikOnayAkisi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:otomatikOnayAkisiEkle"));

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

        @Step("Kullanıcılar alanında imzacı seç")
        public BilgilerTab kullanicilarImzaciSec(String value) {
            cmbKullanicilarImza.selectOptionByValue(value);
            return this;
        }

        @Step("Kullanıcılar alanı doldur")
        public BilgilerTab kullanicilarDoldur(String kullanici) {
            txtOnayAkisiKullanicilar.selectLov(kullanici);
            return this;
        }

        @Step("Kullanıcılar alanı doldur")
        public BilgilerTab kullanicilarDoldur2(String kullanici) {
            txtOnayAkisiKullanicilar.type(kullanici).titleItems()
                    .filterBy(Condition.exactText(kullanici + " [Ağ (Network) Uzman Yardımcısı]")).first().click();
            txtOnayAkisiKullanicilar.closeLovTreePanel();
            return this;
        }

        @Step("Konu Kodu alanında {0} seç")
        public BilgilerTab otomatikOnayAkisi() {
            btnOtomatikOnayAkisi.click();
            return this;
        }

        @Step("Konu Kodu alanında {0} seç")
        public BilgilerTab konuKoduSec(String value) {
            cmlKonuKodu.selectLov(value);
            return this;
        }

        @Step("Kullanıcı kontrolü")
        public BilgilerTab kullaniciTabloKontrol() {
            tblKullanıcılar.isDisplayed();
            return this;
        }

        @Step("")
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

        @Step("Konu alanında {0} seç")
        public BilgilerTab konuSec(String value) {
            txtKonu.setValue(value);
            return this;
        }

        @Step("Konu alanı {0} ile doldur")
        public BilgilerTab zorunluKodu() {
            txtKonu.is(required);
            return this;
        }

        @Step("Kaldiralacak Klasörler alanında {0} seç")
        public BilgilerTab kaldiralacakKlasorlerSec(String value) {
            cmbKaldiralacakKlasorler.selectLov(value);
            return this;
        }

        @Step("Evrak Türü alanında {text} seç")
        public BilgilerTab evrakTuruSec(String text) {
//            if (!cmbEvrakTuru.getSelectedOption().equals(text))
            cmbEvrakTuru.selectOption(text);
            return this;
        }

        @Step("Kayıt Tarih alanında {0} seç")
        public BilgilerTab dateKayitTarihiSec(String dateText) {
            dateKayitTarihi.setValue(dateText);
            return this;
        }

        @Step("Evrak Dili alanında {0} seç")
        public BilgilerTab evrakDiliSec(String text) {
            cmbEvrakDili.selectOption(text);
//            if (cmbEvrakTuru.getSelectedOption().equals(text))
//                throw new RuntimeException("Alan seçilemedi");
            return this;
        }

        @Step("Onay Akışı ekle")
        public BilgilerTab OnayAkisiEkle() {
            btnIletisimbilgileriOnayAkisiEkle.click();
            return this;
        }


        @Step("Gizlilik Derecesi alanında {0} seç")
        public BilgilerTab gizlilikDerecesiSec(String text) {
            cmbGizlilikDerecesi.selectOption(text);
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

        @Step("Vekalet alan Ve Veren tablo vekalet alan seç")
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

        @Step("Evrak Sayi Ek Metni alanında {0} seç")
        public BilgilerTab evrakSayiEkMetniSec(String text) {
            txtEvrakSayiEkMetni.setValue(text);
            return this;
        }

        @Step("Açıklama gir")
        public BilgilerTab aciklamaSec(String text) {
            txtAciklama.setValue(text);
            return this;
        }

        @Step("İvedik alanında {0} seç")
        public BilgilerTab ivedikSec(String text) {
            cmbIvedik.selectOption(text);
            return this;
        }

        @Step("Miat alanında {0} seç")
        public BilgilerTab miatSec(String dateText) {
            dateMiat.setValue(dateText);
            return this;
        }

        @Step("Bilgi Secim Tipi alanında {0} seç")
        public BilgilerTab bilgiSecimTipiSec(String text) {
            cmbBilgiSecimTipi.selectOption(text);
            return this;
        }

        @Step("Bilgi seçim tipi tree alanında {0} geliyor mu? kontrol et")
        public BilgilerTab bilgiSecimTipiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            Assert.assertEquals(txtBilgi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Bilgi alanında {0} seç")
        public BilgilerTab bilgiSec(String text) {
            txtBilgi.selectLov(text);
            return this;
        }

        @Step("Bilgi alanında temizle ve {0} seç")
        public BilgilerTab bilgiSec(String text, Boolean clearAll) {
            txtBilgi.sendKeys(Keys.SHIFT);
            txtBilgi.selectLov(text);
            txtBilgi.clearAllSelectedLov();
            return this;
        }

        @Step("Geregi alanında {0} seç")
        public BilgilerTab geregiSec(String text) {
            txtGeregi.selectLov(text);
            txtGeregi.closeLovTreePanel();
            return this;
        }

        @Step("Geregi alanında {0} seç")
        public BilgilerTab geregiSec(String text, Boolean clearAfterSelecion) {
            cmbGeregi.sendKeys(Keys.SHIFT);
            txtGeregi.selectLov(text);
            txtGeregi.clearLastSelectedLov();
            return this;
        }

        @Step("Gereği tree alanında {0} geliyor mu? kontrol et")
        public BilgilerTab geregiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            txtGeregi.sendKeys(Keys.SHIFT);
            Assert.assertEquals(txtGeregi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Dagitimi Ek Yap alanı {0} seç")
        public BilgilerTab dagitimiEkYapSec(boolean setSelected) {
            chkDagitimiEkYap.setSelected(setSelected);
            return this;
        }

        @Step("Onay Akisi alanında {text} seç")
        public BilgilerTab cmbOnayAkisi(String text) {
            cmbOnayAkisi.selectLov(text);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu) {
            txtKonuKodu.selectLov(konuKodu);
            //shouldHave(Condition.text(konuKodu));

            System.out.println("title: " + txtKonuKodu.lastSelectedLovTitleText());
            System.out.println("detail: " + txtKonuKodu.lastSelectedLovDetailText());

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
            txtKonu.sendKeys(konu); //selenide
            return this;
        }

        public BilgilerTab evrakDerecesiSec(String value) {
            cmbGizlilikDerecesi.selectOption(value);
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
            dateMiat.sendKeys(date);
            return this;
        }

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

        @Step("Bilgileri tabında kişinin bilgi alanında görüntülenmediği kontrolu")
        public BilgilerTab bilgiAlanindaGoruntulenmemeKontrolu(String adSoyad) {

            boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(adSoyad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Kişinin Bilgi alanında görüntülenme kontrolu")
        public BilgilerTab bilgiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

            String adSoyad = ad + " " + soyad.toUpperCase();
            cmbBilgi.selectLov(adSoyad);
            System.out.println("Gelen title:     " + cmbBilgi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);
            Assert.assertEquals(cmbBilgi.lastSelectedLovTitleText().contains(adSoyad), true);

            return this;
        }

        @Step("Geregi Secim Tipi alanında {value} seç")
        public BilgilerTab geregiSecimTipiSec(String value) {
            cmbGeregiSecimTipi.selectOptionByValue(value);
            return this;
        }

        @Step("Geregi Secim Tipi alanında {value} seç")
        public BilgilerTab geregiSecimTipiSecByText(String value) {
            cmbGeregiSecimTipi.selectOption(value);
            return this;
        }

        @Step("Gereği doldur")
        public BilgilerTab geregiDoldur(String geregi) {
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

        @Step("Kişinin Geregi alanında görüntülenme kontrolu")
        public BilgilerTab geregiAlanindaGoruntulenmeKontrolu(String adSoyad) {

            cmbGeregi.selectLov(adSoyad);
            System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);
            Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);

            return this;
        }

        @Step("Otomatik onay akışı kontrol")
        public BilgilerTab otomatikOnayAkisiGeldigiGorme(String ekranAdi) {

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

        public BilgilerTab secilenGeregiSil() {
            cmbGeregi.clearLastSelectedLov();
            return this;
        }

        public BilgilerTab onayAkisiEkle(String kullanici) {

            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.selectLov(kullanici);

            return this;
        }

        @Step("Onay Akışı Ekle")
        public BilgilerTab onayAkisiEkle() {
            btnOnayAkisiEkle.click();
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


        public BilgilerTab adresHitaptaGorunsunSec(boolean secim) {
            chkAdresHitaptaGorunsun.setSelected(secim);
            return this;
        }

        public BilgilerTab adresDagitimdaGorunsunSec(boolean secim) {
            chkAdresDagitimdaGorunsun.setSelected(secim);
            return this;
        }

        public BilgilerTab dagitimHitapDuzenlemeKaydet() {
            btnDagitimHitapDuzenlemeKaydet.click();
            return this;
        }

        public String getDagitimHitapAdres() {

            return txtDagitimHitapAdres.getText();
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
            $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:j_idt134")).click();
            // comboLov("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:LovText").selectLov(deger);
            cmbOnayAkisi.type(deger).titleItems().first().click();

            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiDoldur(String onay) {
            cmbOnayAkisi.selectLov(onay);
            return this;
        }

        public BilgilerTab secilenOnayAkisiSil() {

            if (cmbOnayAkisi.isLovSelected()) {
                cmbOnayAkisi.clearLastSelectedLov();
            }

            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            comboLov(cmbOnayAkisiBy).type(onayAkisi).titleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

        @Step("Onay akışı güncelle")
        public BilgilerTab onayAkisiGuncelle() {
            btnOnayAkisGuncelle.click();
            return this;
        }

        @Step("Onay akışı kullanıcı ekle")
        public BilgilerTab onayAkisiKullaniciEkle(String kullaniciAdi) {
            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.selectLov(kullaniciAdi);
            return this;
        }

        public BilgilerTab onayAkisiKullaniciSil(String yeniKullanici) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(yeniKullanici))
                    .get(0)
                    .shouldBe(exist)
                    .$("[class$='delete-icon']").click();
            return this;
        }

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
            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.clearAllSelectedLov();
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

        @Step("Kullan butonu")
        public BilgilerTab kullan() {
            clickJs(btnKullan);
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
            btnOnayAkisiKullaniciKullan.click();
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

        @Step("Kullanılan onay akışı kontrol et")
        public BilgilerTab onayAkisiKullanilanKullanilanKontrolEt(String kullaniciAdi) {
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

        @Step("Gerçek Kişi gereği alanı kontrolu başarılı")
        public BilgilerTab gercekKisiGeregiAlaniKontrol(String adSoyad, String unvan, String adres, String posta) {
            System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
            System.out.println("Beklenen title:  " + adSoyad);
            System.out.println("Gelen detail:    " + cmbGeregi.lastSelectedLovDetailText());
            System.out.println("Beklenen detail: " + unvan + " | " + adres);
            System.out.println("Gelen posta:     " + cmbPostaTipi.getSelectedValue());
            System.out.println("Beklenen posta:  " + posta);

            Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);
            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains(unvan + " | " + adres), true);
            Assert.assertEquals(cmbPostaTipi.getSelectedValue().contains(posta), true);

            return this;
        }

        @Step("Tüzel Kişi gereği alanı kontrolu")
        public BilgilerTab tuzelKisiGeregiAlaniVergiNoPostaTipiKontrol(String vergiNo2, String postaTipi) {

            System.out.println("Gelen detail:    " + cmbGeregi.lastSelectedLovDetailText());
            System.out.println("Beklenen detail: " + "Vergi No: " + vergiNo2);
            System.out.println("Gelen posta:     " + cmbPostaTipi.getSelectedValue());
            System.out.println("Beklenen posta:  " + postaTipi);

            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains("Vergi No: " + vergiNo2), true);
            Assert.assertEquals(cmbPostaTipi.getSelectedValue().contains(postaTipi), true);

            return this;
        }

        @Step("Tüzel Kişi gereği alanı kontrolu")
        public BilgilerTab tuzelKisiGeregiAlaniVergiNoAdAdresKontrol(String vergiNo, String ad, String adres) {
            String gelenTitle = cmbGeregi.lastSelectedLovTitleText();
            String gelenDetail = cmbGeregi.lastSelectedLovDetailText();
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
            cmbGeregiPostaTipi.selectLov(posta);
            return this;

        }

        public BilgilerTab kaldirilacakKlasorler(String klasor) {
            //TODO: Fonksiyon yazılacak.
            cmbKaldiralacakKlasorler.selectLov(klasor);
            return this;
        }


        //ElementsCollection divGeregiSecilenler = $$("tbody[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':geregiLov:LovSecilenTable_data'] > tr[role='row']");
        public BilgilerTab geregiSecilenKontrol(String baslik, String detay, String postaTipi) {

            Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(baslik), true);
            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains(detay), true);
            Assert.assertEquals(cmbPostaTipi.getSelectedText().contains(postaTipi), true);


            return this;
        }

        public BilgilerTab geregiSonKayitSil() {
            cmbGeregi.clearLastSelectedLov();
            return this;
        }

        @Step("Seçilen onay akışı detail kontrolu: \"{secim}\" ")
        public BilgilerTab onayAkisiDetailKontrol(String secim) {
            System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovDetailText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovDetailText().contains(secim), true);
            return this;
        }

        @Step("Seçilen onay akışı title kontrolu: \"{secim}\" ")
        public BilgilerTab onayAkisiTitleKontrol(String secim) {
            System.out.println("Gelen title:     " + cmbOnayAkisi.lastSelectedLovTitleText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(secim), true);
            return this;
        }

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


        //endregion

    }

    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class EditorTab extends MainPage {

        SelenideElement divHitap = $("div[id='yeniGidenEvrakForm:hitapInplace'] > span");
        // SelenideElement divEditor = $(By.id("yeniGidenEvrakForm:allPanels"));
        SelenideElement divEditor = $("span[id='yeniGidenEvrakForm:D1Editor']");
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
        SelenideElement btnParafla = $(By.id("yeniGidenEvrakForm:rightTab:uiRepeat:2:cmdbutton"));
        SelenideElement radibtnSimza = $("[id='imzalaForm:imzaPanelGrid'] div[id='imzalaForm:imzalaRadio']  div:nth-child(2)");
        SelenideElement btnEvrakImzala = $(By.xpath("//buton[starts-with(@id,'imzalaForm:jsfImzaForm:j_idt')]"));
        SelenideElement btnSimzaImzala = $(By.id("imzalaForm:imzalaButton"));

        public TextEditor getEditor() {
            return editor;
        }

        private TextEditor editor = new TextEditor();


        private EditorTab open() {
            tabEditor.click();
            return this;

        }

        @Step("Hitap alanı {0} olarak gelmeli")
        public EditorTab hitapKontrol(String hitap) {
            divHitap.shouldHave(text(hitap));
            return this;
        }

        @Step("İmzacı alanı \"{kullanici}\" olarak gelmeli")
        public EditorTab imzacılarGnMdVKontrol(String kullanici) {
            divImzacılarGnMdV.shouldHave(text(kullanici));
            System.out.println("İmzalama başarılı geçmiştir");
            return this;
        }

        @Step("Hitap Alanı: Hitap, Unvan, Ad, Soyad kontrolu")
        public EditorTab hitapAlaniUnvanAdSoyadKontrol(String sayin, String unvan, String ad, String soyad) {
            String getHitapAlani = editorHitapKismi.shouldHave(Condition.visible).getText();
            String girilenHitapAlani = sayin + " " + unvan + " " + toUpperCaseFirst(ad) + " " + soyad.toUpperCase();
            System.out.println("Gelen Hitap Alanı:   " + getHitapAlani);
            System.out.println("Girilen Hitap Alanı: " + girilenHitapAlani);
            Assert.assertEquals(getHitapAlani.contains(girilenHitapAlani), true);

            return this;
        }

        @Step("Hitap Alanı: Adres, ilce, il kontrolu")
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
            divEditor.find(By.tagName("iframe")).click();
            divEditor.find(By.tagName("iframe")).getWrappedElement().sendKeys(icerik);
            return this;
        }

        @Step("İmzala")
        public EditorTab imzala() {
            btnImzala.click();
            return this;
        }
        @Step("İmzala")
        public EditorTab sImzaImzala() {
            clickJs(btnSimzaImzala);
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
        /*Thread.sleep(2000);
        SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
        sayisalImzaOnay.click();*/
            return this;
        }

        public EditorTab popupSImzalaIslemleri() {

            //switchTo().window("");
//            Thread.sleep(5000);
//            SelenideElement sImza = $(By.id("imzalaForm:imzalamaYontemiRadio:1"));
//            sImza.selectRadio("I");

            $("#evrakImzalaDialog").shouldBe(visible);
            executeJavaScript("arguments[0].click()", WebDriverRunner.getWebDriver().findElement(By.id("imzalaForm:imzalamaYontemiRadio:1")));
//            Thread.sleep(2000);
            SelenideElement imzala = $(By.xpath("//*[@id='imzalaForm:sayisalImzaConfirmDialogOpener']"));
            imzala.click();
//            Thread.sleep(2000);
            SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
            sayisalImzaOnay.click();
            return this;
        }

        public EditorTab editorHitapKontrol(String beklenenEditorHitap) {
            String editorHitap = $(By.xpath("//*[@id='yeniGidenEvrakForm:hitapInplace']/span")).getText();
            Assert.assertEquals(editorHitap.contains(beklenenEditorHitap), true);
            return this;
        }

        @Step("Editör ekranında kişinin geregi alanında görüntülenmediği kontrolu")
        public EditorTab geregiAlanindaGoruntulenmemeKontrolu(String ad) {
            boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(ad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + ad + ": Kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + ad + ": Kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Editör ekranında kişinin bilgi alanında görüntülenmediği kontrolu")
        public EditorTab bilgiAlanindaGoruntulenmemeKontrolu(String ad) {
            boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(ad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + ad + ": Gerçek kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + ad + ": Gerçek kişinin görüntülenmediği görülür.");
            return this;
        }

        @Step("Gereği alani doldur")
        public EditorTab geregiDoldur(String geregi) {
            cmbGeregi.selectLov(geregi);
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
    }

    public EkleriTab ekleriTabAc() {
        return ekleriTab.open();
    }

    public class EkleriTab extends MainPage {

        //Ekleri tabı - Dosya Ekle
        SelenideElement txtEkleriDosyaAciklama = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaAciklama"));
        SelenideElement btnEkleriDosyaFileUpload = $(By.id("yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
        SelenideElement cmbEkleriDosyaGuvenlikKodu = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKodu"));
        SelenideElement btnEkleriDosyaEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaEkleButton"));
        SelenideElement btnEkleriDosyaTemizle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dosyaTemizleButton"));
        SelenideElement chkEkListesiniEkYap = $(By.id("yeniGidenEvrakForm:j_idt30306"));

        //Ekleri tabı - Fiziksel Ekle
        SelenideElement txtEkleriFizikselMetni = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaTextArea"));
        SelenideElement cmbEkleriFizikselGizlilikDerecesi = $(By.id("yeniGidenEvrakForm:evrakEkTabView:guvenlikKoduAciklama"));
        SelenideElement btnEkleriFizikselEkle = $(By.id("yeniGidenEvrakForm:evrakEkTabView:aciklamaEkleButton"));

        //Ekleri tabı - Sistemde Kayıtlı Evrak Ekle
        SelenideElement dateSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
        SelenideElement dateSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
        SelenideElement cmbEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId"));
        SelenideElement btnSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:evrakEkTabView:dokumanAraButton"));

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

        @Step("Ekleri Tab - Açıklama")
        public EkleriTab ekleriDosyaAciklamaDoldur(String aciklama) throws InterruptedException {
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
    }

    public IlgileriTab ilgileriTabAc() {
        return ilgileriTab.open();
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
            btnTablodaBulunanIlkEvrakiEkle.click();
            return this;

        }
    }

    public IliskiliEvraklarTab iliskiliEvraklarTabAc() {
        return iliskiliEvraklarTab.open();
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

    public EvrakNotlariTab evrakNotlariTabAc() {
        return evrakNotlariTab.open();
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

    public class PDFKontrol extends MainPage {

        @Step("Gereği alanında adres gelmedigi, Bilgi alanında dagitim yerinin adresi ile geldigi gorulur")
        public PDFKontrol geregiBilgiAlaniAdresPdfKontrol(String birinciKullaniciGeregiAdresi, String ikinciKullaniciBilgiAdresi) throws InterruptedException {

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

        public PDFKontrol PDFOnizlemeKisayolGonder(String kisayol) throws InterruptedException {

            SelenideElement tc = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='T.C.']"));
            String str = tc.getText();
            tc.click();

            tc.sendKeys(Keys.SHIFT + "o");

            return this;
        }

        public PDFKontrol PDFHitapKontrol(String beklenenPDFHitap) {
            String PDFHitap = $(By.xpath("//*[@id='viewer']/div/div[2]/div[5]")).getText();
            Assert.assertEquals(PDFHitap.contains(beklenenPDFHitap), true);
            return this;
        }
    }
    //endregion


}
