package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
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
    SelenideElement btnKaydet = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydet']");
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");

    SelenideElement divBilgileri = $(By.id("evrakBilgileriContainerDiv"));

    //endregion

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

    public EvrakOlusturPage PDFOnizlemeKisayolGonder(String kisayol) throws InterruptedException {

        SelenideElement tc = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='T.C.']"));
        String str = tc.getText();
        tc.click();

        tc.sendKeys(Keys.SHIFT + "o");

        return this;
    }

    @Step("\"{0}\" ekran açılması beklenen statü: {1}")
    public EvrakOlusturPage kisayolEkranKontrol(String ekranAdi, boolean acilmali) {
        boolean t = $$("[id^='window'][id$='Button_ID'] .ui-button-text")
                .filterBy(Condition.text(ekranAdi)).size() > 0;
        Assert.assertEquals(t, acilmali, "Ekran açılmamamlı");
        return this;
    }

    public EvrakOlusturPage kisayolSayfaAcma(String kisayol) throws InterruptedException {
        divBilgileri.click();
        Thread.sleep(2000);
        switchTo().activeElement().sendKeys(kisayol);
        return this;
    }

    //region Tabs
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

        //Kanun Kapsam Tipi
        SelenideElement rdbKanunKapsamTipiNormal = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Normal'])]");
        SelenideElement rdbKanunKapsamTipiBilgiEdinmeKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Bilgi Edinme Kanunu'])]");
        SelenideElement rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu = $("//input[contains(@id,'kanunKapsamTipiRadio') and (../label[contains(@for,'kanunKapsamTipiRadio') and normalize-space(text())='Kişisel Verilerin Korunması Kanunu'])]");
        SelenideElement txtEvrakSayiEkMetni = $("input[id$='evrakSayiEkMetniInputText']");

        SelenideElement txtAciklama = $(By.xpath("//form[@id='yeniGidenEvrakForm']//label[normalize-space(text())='Açıklama']/ancestor::tr[@class='ui-datagrid-row']//textarea"));
        SelenideElement cmbIvedik = $("select[id$='ivedilik']");
        SelenideElement dateMiat = $("input[id$='miatCalendar_input']");

        SelenideElement cmbBilgiSecimTipi = $(By.xpath("//label[normalize-space(text())='Bilgi Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
        BelgenetElement txtBilgi = comboLov("input[id$='bilgiLov:LovText']");
        SelenideElement btnBilgiTree = $("button[id$='bilgiLov:treeButton']");

        SelenideElement cmbGeregiSecimTipi = $(By.xpath("//label[normalize-space(text())='Gereği Seçim Tipi']/ancestor::tr[@class='ui-datagrid-row']//select"));
        BelgenetElement txtGeregi = comboLov("input[id$='geregiLov:LovText']");
        SelenideElement btnGeregiTree = $("button[id$='geregiLov:treeButton']");

        SelenideElement chkDagitimiEkYap = $("input[id$='dagitimEkYapCheckBoxId_input']");
        BelgenetElement cmbOnayAkisi = comboLov("input[id$='akisLov:LovText']");

        // Onay Akışı Elementleri
        SelenideElement btnOnayAkisiEkle = $("button[id*='onayAkisiEkle']");
        ElementsCollection trOnayAkisiEkleKullanicilar = $$("tbody[id*='akisAdimLov:LovSecilenTable_data'] tr[role='row']");
        SelenideElement btnOnayAkisiKullaniciKullan = $("button[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$='anlikAkisKullanButton']");
        BelgenetElement txtOnayAkisiKullanicilar = comboLov("[id$='akisAdimLov:LovText']");
        ElementsCollection listOnayAkisikullanicilar = $$("div[id*='akisAdimLov:lovTree'] ul li");

        SelenideElement txtOnayAkisiKullanicilarInput = $("input[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:LovText']");
        SelenideElement listOnayAkisiKullanilan = $("div[id*='akisLov:lovContainer'] div[class*='lovSelection processEt'] tbody");
        SelenideElement btnOnayAkisiPanelKapat = $("button[id^='yeniGidenEvrakForm:evrakBilgileriList:'][id$=':akisAdimLov:lovTreePanelKapat']");

        //Bilgileri tabı
        BelgenetElement txtKonuKodu = comboLov(By.id("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='konuKoduLov:LovText']"));

        SelenideElement txtKaldiralacakKlasorler = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:LovText"));
        SelenideElement rdbNormal = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:0"));
        SelenideElement rdbBilgiEdinmeKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:1"));
        SelenideElement rdbKisiselVerilerinKorunmasiKanunu = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:10:kanunKapsamTipiRadio:2"));
        SelenideElement cbmAkisAdim = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable:0:selectOneMenu"));

        BelgenetElement cmbGeregi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovText"));
        SelenideElement cmbPostaTipi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:selectOneMenu"));
        By cmbGeregiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='geregiLov:LovText']");

       // BelgenetElement txtOnayAkisi = comboLov("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement btnOnayAkisiTemizle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt134"));
        SelenideElement btnOnayAkisiEdit = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:akisLov:j_idt135"));
        //SelenideElement btnOnayAkisiEkle = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:17:onayAkisiEkle"));

        // Gereği - Dağıtım Hitap Düzenleme
        SelenideElement btnGeregiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:16:geregiLov:LovSecilenTable:0:j_idt123"));
        SelenideElement btnBilgiLovSecilemUpdate = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:15:bilgiLov:LovSecilenTable:0:j_idt123"));

        SelenideElement chkAdresHitaptaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Hitapta Görünsün']/../..//input"));
        SelenideElement chkAdresDagitimdaGorunsun = $(By.xpath("//label[normalize-space(text())='Adres Dağıtımda Görünsün']/../..//input"));
        SelenideElement txtDagitimHitapAdres = $(By.xpath("//div[@id='yeniGidenEvrakForm:pnlHitapDuzenle']/table[4]//div/textarea[@role='textbox']"));
        SelenideElement btnDagitimHitapDuzenlemeKaydet = $(By.xpath("//*[@id='yeniGidenEvrakForm:pnlHitapDuzenle']//span[normalize-space(text())='Kaydet']/parent::button"));

        BelgenetElement cmbBilgi = comboLov(By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']"));
        By cmbBilgiBy = By.cssSelector("[id^='yeniGidenEvrakForm:evrakBilgileriList'][id$='bilgiLov:LovText']");

        SelenideElement btnOtomatikOnayAkisi = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:otomatikOnayAkisiEkle"));

        SelenideElement cmbKullanicilarImza = $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisAdimLov:LovSecilenTable:1:selectOneMenu"));

        //endregion

        private BilgilerTab open() {
            if (divContainer.is(not(visible)))
                tabBilgiler.click();

            divContainer.shouldBe(visible);
            return this;
        }

        public boolean isOnTabPage() {
            return divContainer.is(visible);
        }

        @Step("Kullanıcılar alanında imzacı seç")
        public BilgilerTab kullanicilarImzaciSec(String value){
            cmbKullanicilarImza.selectOption(value);
            return this;
        }
        
        public BilgilerTab kullanicilarDoldur(String kullanici){
            txtOnayAkisiKullanicilar.selectLov(kullanici);
            return this;
        }

        @Step("Konu Kodu alanında \"{0}\" seç")
        public BilgilerTab otomatikOnayAkisi() {
            btnOtomatikOnayAkisi.click();
            return this;
        }

        @Step("Konu Kodu alanında \"{0}\" seç")
        public BilgilerTab konuKoduSec(String value) {
            cmlKonuKodu.selectLov(value);
            return this;
        }

        @Step("Konu Kodu alanı zorunluluk kontrol")
        public boolean konuKoduZorunlukKontrol(boolean zorunlu) {
            return cmlKonuKodu.is(required);
        }

        @Step("Konu alanında \"{0}\" seç")
        public BilgilerTab konuSec(String value) {
            txtKonu.setValue(value);
            return this;
        }

        @Step("Konu alanı \"{0}\" ile doldur")
        public BilgilerTab zorunluKodu() {
            txtKonu.is(required);
            return this;
        }

        @Step("Kaldiralacak Klasörler alanında \"{0}\" seç")
        public BilgilerTab kaldiralacakKlasorlerSec(String value) {
            cmbKaldiralacakKlasorler.selectLov(value);
            return this;
        }

        @Step("Evrak Türü alanında \"{0}\" seç")
        public BilgilerTab evrakTuruSec(String text) {
//            if (!cmbEvrakTuru.getSelectedOption().equals(text))
            cmbEvrakTuru.selectOption(text);
            return this;
        }

        @Step("Kayıt Tarih alanında \"{0}\" seç")
        public BilgilerTab dateKayitTarihiSec(String dateText) {
            dateKayitTarihi.setValue(dateText);
            return this;
        }

        @Step("Evrak Dili alanında \"{0}\" seç")
        public BilgilerTab evrakDiliSec(String text) {
            cmbEvrakDili.selectOption(text);
//            if (cmbEvrakTuru.getSelectedOption().equals(text))
//                throw new RuntimeException("Alan seçilemedi");
            return this;
        }

        @Step("Gizlilik Derecesi alanında \"{0}\" seç")
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

        @Step("Kanun Kapsam Tipi Kisisel Verilerin Korunmasi Kanunu seç")
        public BilgilerTab kanunKapsamTipiKisiselVerilerinKorunmasiKanunuSec() {
            rdbKanunKapsamTipiKisiselVerilerinKorunmasiKanunu.click();
            return this;
        }

        @Step("Evrak Sayi Ek Metni alanında \"{0}\" seç")
        public BilgilerTab evrakSayiEkMetniSec(String text) {
            txtEvrakSayiEkMetni.setValue(text);
            return this;
        }

        @Step("Açıklama gir")
        public BilgilerTab aciklamaSec(String text) {
            txtAciklama.setValue(text);
            return this;
        }

        @Step("İvedik alanında \"{0}\" seç")
        public BilgilerTab ivedikSec(String text) {
            cmbIvedik.selectOption(text);
            return this;
        }

        @Step("Miat alanında \"{0}\" seç")
        public BilgilerTab miatSec(String dateText) {
            dateMiat.setValue(dateText);
            return this;
        }

        @Step("Bilgi Secim Tipi alanında \"{0}\" seç")
        public BilgilerTab bilgiSecimTipiSec(String text) {
            cmbBilgiSecimTipi.selectOptionByValue(text);
            return this;
        }

        @Step("Bilgi seçim tipi tree alanında \"{0}\" geliyor mu? kontrol et")
        public BilgilerTab bilgiSecimTipiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            org.testng.Assert.assertEquals(txtBilgi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Bilgi alanında \"{0}\" seç")
        public BilgilerTab bilgiSec(String text) {
            txtBilgi.selectLov(text);
            return this;
        }

        @Step("Geregi Secim Tipi alanında \"{0}\" seç")
        public BilgilerTab geregiSecimTipi(String text) {
            cmbGeregiSecimTipi.selectOptionContainingText(text);
            return this;
        }

        @Step("Geregi alanında \"{0}\" seç")
        public BilgilerTab geregiSec(String text) {
            txtGeregi.selectLov(text);
            return this;
        }

        @Step("Gereği tree alanında \"{0}\" geliyor mu? kontrol et")
        public BilgilerTab geregiTreeKontrolEt(String kurumAdi, Boolean shouldBeSelectable) {
            org.testng.Assert.assertEquals(txtGeregi.isLovValueSelectable(kurumAdi), shouldBeSelectable);
            return this;
        }

        @Step("Dagitimi Ek Yap alanı \"{0}\" seç")
        public BilgilerTab dagitimiEkYapSec(boolean setSelected) {
            chkDagitimiEkYap.setSelected(setSelected);
            return this;
        }

        @Step("Onay Akisi alanında \"{0}\" seç")
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

        @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
        public BilgilerTab bilgiAlanindaGoruntulenmemeKontrolu(String ad, String soyad) {

            String adSoyad = ad + " " + soyad;
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

        @Step("Gereği seçim tipi seç")
        public BilgilerTab geregiSecimTipiSec(String value) {
            cmbGeregiSecimTipi.selectOptionByValue(value);
            return this;
        }

        @Step("Gereği doldur")
        public BilgilerTab geregiDoldur(String geregi) {
            cmbGeregi.selectLov(geregi);
            return this;
        }


        @Step("Kişinin Geregi alanında görüntülenmediği kontrolu")
        public BilgilerTab geregiAlanindaGoruntulenmemeKontrolu(String ad, String soyad) {

            String adSoyad = ad + " " + soyad;
            boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(adSoyad);
            Assert.assertEquals(selectable, false, "MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür");
            System.out.println("MyCombolov alanında " + adSoyad + ": Gerçek kişinin görüntülenmediği görülür.");

            return this;
        }

        @Step("Kişinin Geregi alanında görüntülenme kontrolu")
        public BilgilerTab geregiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

            String adSoyad = ad + " " + soyad;
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

        @Step("\"{0}\" text var olma kontorlu, beklenen: {1}")
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

        @Step("Onay akışı adı doldur")
        public BilgilerTab onayAkisiDoldur(boolean onayAkisi) throws InterruptedException {
            //TODO: Fonksiyonu yazılacak.
            return this;
        }

        BelgenetElement txtOnayAkisi = comboLov(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:LovText"));

        @Step("")
        public BilgilerTab onayAkisiTemizle(String deger){
        $(By.id("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:j_idt134")).click();
       // comboLov("yeniGidenEvrakForm:evrakBilgileriList:18:akisLov:LovText").selectLov(deger);
            txtOnayAkisi.type(deger).titleItems().first().click();

            return this;
        }

        @Step("Onay akışı doldur")
        public BilgilerTab onayAkisDoldur(String onay){
            txtOnayAkisi.selectLov(onay);
            return this;
        }

        @Step("Onay akışı kullanıcı ekle")
        public BilgilerTab onayAkisiKullaniciEkle(String kullaniciAdi) {
            btnOnayAkisiEkle.click();
            txtOnayAkisiKullanicilar.selectLov(kullaniciAdi);
            return this;
        }

        @Step("Onay akışı kullanıcı adı ve tipi kontrol et")
        public BilgilerTab onayAkisiKullaniciKontrol(String kullaniciAdi, String kullaniciTipi) {
            trOnayAkisiEkleKullanicilar
                    .filterBy(text(kullaniciAdi))
                    .get(0)
                    .shouldBe(exist)
                    .$("select[id*='selectOneMenu']")
                    .shouldHave(value(kullaniciTipi));
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

        @Step("gerçek Kişi gereği alanı kontrolu başarılı")
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

        @Step("Tüzel Kişi gereği alanı kontrolu başarılı")
        public BilgilerTab tuzelKisiGeregiAlaniKontrol(String vergiNo2, String postaTipi) {

            System.out.println("Gelen detail:    " + cmbGeregi.lastSelectedLovDetailText());
            System.out.println("Beklenen detail: " + "Vergi No: " + vergiNo2);
            System.out.println("Gelen posta:     " + cmbPostaTipi.getSelectedValue());
            System.out.println("Beklenen posta:  " + postaTipi);

            Assert.assertEquals(cmbGeregi.lastSelectedLovDetailText().contains("Vergi No: " + vergiNo2), true);
            Assert.assertEquals(cmbPostaTipi.getSelectedValue().contains(postaTipi), true);

            return this;
        }


        public BilgilerTab kaldirilacakKlasorler(String klasor) {
            //TODO: Fonksiyon yazılacak.
            return this;
        }

        //endregion

    }

    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class EditorTab extends MainPage {

        SelenideElement divHitap = $("div[id='yeniGidenEvrakForm:hitapInplace'] > span");
        SelenideElement divEditor = $(By.id("yeniGidenEvrakForm:allPanels"));
        SelenideElement yeniGidenEvrakForm = $(By.id("cke_yeniGidenEvrakForm:ckeditorInstance_window1"));
        SelenideElement editorHitapKismi = $(By.cssSelector("#yeniGidenEvrakForm\\:hitapInplace > span:nth-child(4)"));
        SelenideElement tblEditorlovSecilenTable = $(By.id("yeniGidenEvrakForm:geregiKurumLov:LovSecilenTable"));
        SelenideElement btnImzala = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='imzala']");
        SelenideElement divImzacılarGnMdV = $("[id='yeniGidenEvrakForm:parafciPanell'] [class='ui-inplace ui-hidden-container']");

        private EditorTab open() {
            tabEditor.click();
            return this;

        }

        @Step("Hitap alanı \"{0}\" olarak gelmeli")
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

        @Step("Editör İçerik Doldur")
        public EditorTab editorIcerikDoldur(String icerik) throws InterruptedException {
            Thread.sleep(5000);
            divEditor.click();
            divEditor.sendKeys(icerik);
            return this;
        }

        @Step("İmzala")
        public EditorTab imzala() {
            btnImzala.click();
            return this;
        }

        public EditorTab popupImzalaVeEvrakKapatma() throws InterruptedException {

            //switchTo().window("");
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

        public EditorTab popupSImzalaIslemleri() throws InterruptedException {

            //switchTo().window("");
            Thread.sleep(5000);
            SelenideElement sImza = $(By.id("imzalaForm:imzalamaYontemiRadio:1"));
            sImza.selectRadio("I");
            Thread.sleep(2000);
            SelenideElement imzala = $(By.xpath("//*[@id='imzalaForm:sayisalImzaConfirmDialogOpener']"));
            imzala.click();
            Thread.sleep(2000);
            SelenideElement sayisalImzaOnay = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
            sayisalImzaOnay.click();
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
            try {
                btnEkleriDosyaFileUpload.sendKeys(pathToFile);
//            LogPASS("Dosya Yuklendi.");
            } catch (Exception e) {
//            logger.error("Error in attaching file.s : " + e);
//            LogFAIL("Error in attaching file.s : " + e);
                System.out.println("Error in attaching file.s  : " + e);
                throw new RuntimeException(e);
            }
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

            //switchTo().window("");
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
        SelenideElement dateIlgileriSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiSon = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
        SelenideElement cmbIlgileriSistemdeEvrakAranacakyer = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId"));
        SelenideElement txtIlgileriSistemdeEvrakArama = $(By.name("yeniGidenEvrakForm:ilgiIslemleriTabView:evrakAramaText"));
        SelenideElement btnIlgileriSistemdeDokumanAra = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:dokumanAraButton"));

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


    }
    //endregion


}
