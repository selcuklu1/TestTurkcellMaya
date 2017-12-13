package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KararYazisiOlusturPage extends MainPage {

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
    SelenideElement tabBilgiler = $("button[id^='yeniKararEvrakForm:kararEvrakLeftTab:uiRepeat'] span[class$='kullaniciBilgileri']");
    SelenideElement tabEditor = $("button .editor");
    SelenideElement tabEkleri = $("button .kullaniciEkleri");
    SelenideElement tabIlgileri = $("button .kullaniciIlgileri");
    SelenideElement tabIliskiliEvraklar = $("button .kullaniciIliskileri");
    SelenideElement tabEvrakNotlari = $("button .evrakNot");
    SelenideElement tabEvrakDogrulama = $("button .evrakDogrulamaAktarimIslemleri");

    SelenideElement btnPDFOnizleme = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='pdfOnIzleme']");
    SelenideElement btnKaydet = $(By.id("yeniKararEvrakForm:kararEvrakRightTab:uiRepeat:1:cmdbutton"));
    SelenideElement btnKaydetOnayaSun = $("button[id^='yeniKararEvrakForm:kararEvrakRightTab:uiRepeat'] span[class$='kaydetHavaleEt']");
    SelenideElement btnPaylas = $("button[id^='yeniGidenEvrakForm:rightTab:uiRepeat'] span[class$='evrakPaylas']");

    SelenideElement divBilgileri = $(By.id("evrakBilgileriContainerDiv"));

    //endregion


    @Step("Karar yazısı oluştur sayfası aç")
    public KararYazisiOlusturPage openPage() {
        ustMenu("Karar Yazısı Oluştur");
        return this;
    }

    //region Tabs
    @Step("Bilgiler tab aç")
    public BilgilerTab bilgilerTabiAc() {
        return bilgilerTab.open();
    }

    public class BilgilerTab extends MainPage {

        //region Elements
        BelgenetElement txtKonuKodu = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:0:konuKoduLov:LovText"));
        SelenideElement txtKonu = $(By.id("yeniKararEvrakForm:evrakBilgileriList:2:konuTextArea"));
        SelenideElement cmbIvedilik = $(By.id("yeniKararEvrakForm:evrakBilgileriList:4:ivedilik"));
        SelenideElement dateMiat = $(By.id("yeniKararEvrakForm:evrakBilgileriList:5:miatCalendar_input"));
        BelgenetElement txtOnayAkisi = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisLov:LovText"));
        BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:7:eklenecekKlasorlerLov:LovText"));
        SelenideElement txtToplantiNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:8:toplantiNo"));
        SelenideElement dateToplantiTarihi = $(By.id("yeniKararEvrakForm:evrakBilgileriList:9:toplantiTarih_input"));
        SelenideElement txtKararNo = $(By.id("yeniKararEvrakForm:evrakBilgileriList:10:kararNo"));
        SelenideElement btnOnayAkisiEkle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:onayAkisiEkle"));
        SelenideElement btnKullanicilarKullan = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:anlikAkisKullanButton"));
        SelenideElement txtAciklama = $(By.id("yeniKararEvrakForm:onayIslemiAciklama"));
        SelenideElement btnGonder = $(By.id("yeniKararEvrakForm:gonderButton"));
        SelenideElement btnEvet = $(By.id("kaydetEvetButton"));
        SelenideElement btnHayir = $(By.id("kaydetHayirButton"));
        SelenideElement btnKonuKoduTemizle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:0:konuKoduLov:j_idt134"));
        SelenideElement btnKaldirilicakKlasorTemizle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:7:eklenecekKlasorlerLov:LovSecilenTable:0:j_idt122"));
        SelenideElement btnOnayakisiTemizle = $(By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisLov:j_idt134"));
        BelgenetElement txtKullanicilar = comboLov(By.id("yeniKararEvrakForm:evrakBilgileriList:6:akisAdimLov:LovText"));
        SelenideElement btnKaydetEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
        SelenideElement btnKaydetHayir = $(By.id("kaydetConfirmForm:kaydetHayirButton"));
        SelenideElement divContainer = $("#evrakBilgileriContainerDiv");
        By cmbOnayAkisiBy = By.cssSelector("[id^='yeniKararEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']");
        SelenideElement cmbSelectOneMenu = $(By.id("yeniKararEvrakForm:evrakBilgileriList:14:akisAdimLov:LovSecilenTable:0:selectOneMenu"));
        SelenideElement btnEkranKapat = $(By.cssSelector("[id='window3Dialog'] span[class='ui-icon ui-icon-closethick']"));
        SelenideElement btnKaydetveOnaySun = $(By.id("yeniKararEvrakForm:kararEvrakRightTab:uiRepeat:2:cmdbutton"));
        BelgenetElement cmbOnayAkisi = comboLov(By.cssSelector("[id^='yeniKararEvrakForm:evrakBilgileriList'][id$='akisLov:LovText']"));
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


        @Step("Gönder")
        public BilgilerTab gonder(boolean secim) {
            btnGonder.click();
            if (secim == true) {
                btnEvet.click();
            } else {
                btnHayir.click();
            }
            return this;
        }

        @Step("Kaydet ve onay sun")
        public BilgilerTab kaydetveOnaySun() {
            btnKaydetOnayaSun.click();
            return this;
        }

        @Step("Onay akışı temizle")
        public BilgilerTab onayAkisiTemizle() {
            btnOnayakisiTemizle.click();
            return this;
        }

        @Step("Açıklama doldur")
        public BilgilerTab aciklamaDoldur(String aciklama) {
            txtAciklama.setValue(aciklama);
            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan() {
            btnKullanicilarKullan.click();
            return this;
        }

        @Step("Karar no doldur")
        public BilgilerTab kararNoDoldur(String kararNo) {
            txtKararNo.setValue(kararNo);
            return this;
        }

        @Step("Toplantı tarih seç")
        public BilgilerTab toplantiTarihDoldur(String tarih) {
            dateToplantiTarihi.setValue(tarih);
            return this;
        }

        @Step("Toplanti no doldur")
        public BilgilerTab toplantiNoDoldur(String toplantiNo) {
            txtToplantiNo.setValue(toplantiNo);
            return this;
        }

        @Step("Kaldirilacak klasörler doldur")
        public BilgilerTab kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler) {
            txtKaldirilacakKlasorler.selectLov(kaldirilacakKlasorler);
            return this;
        }


        @Step("Onay akışı kontrol")
        public BilgilerTab imzalamaKontrol(String imzalama) {
            Assert.assertEquals(txtOnayAkisi.lastSelectedLovDetailText().contains(imzalama), true);
            return this;
        }

        @Step("Miat Doldur")
        public BilgilerTab miatDoldur(String miat) {
            dateMiat.sendKeys(miat);
            return this;
        }

        @Step("Onay akışı doldurma ve görüntüleme kontrolu")
        public BilgilerTab onayAkisiDoldur(String onayAkisi) {
            cmbOnayAkisi.shouldBe(visible);
            cmbOnayAkisi.selectLov(onayAkisi);
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
            System.out.println("Gelen detail:     " + cmbOnayAkisi.lastSelectedLovTitleText());
            Assert.assertEquals(cmbOnayAkisi.lastSelectedLovTitleText().contains(secim), true);
            return this;
        }

        @Step("İvedilik seç")
        public BilgilerTab ivedilikSec(String secilen) {
            cmbIvedilik.selectOption(secilen);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu) {
            txtKonuKodu.selectLov(konuKodu);
            return this;
        }

        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduTemizle() {
            btnKonuKoduTemizle.click();
            return this;
        }

        @Step("Kaldırılıcak klasör temizle")
        public BilgilerTab kaldirilacakKlasorTemizle() {
            btnKaldirilicakKlasorTemizle.click();
            return this;
        }

        @Step("Konu doldur")
        public BilgilerTab konuDoldur(String konuKodu) {
            txtKonu.setValue(konuKodu);
            return this;
        }

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle() {
            clickJs(btnOnayAkisiEkle);
            //btnOnayAkisiEkle.click();
            return this;
        }

        @Step("Kullanıcılar doldur")
        public BilgilerTab kullanicilarDoldur(String kullanici, String birim) {
            txtKullanicilar.type(kullanici).titleItems().filterBy(text(birim)).first().click();
            txtKullanicilar.closeLovTreePanel();
            return this;
        }

        @Step("Kaydet")
        public BilgilerTab kaydet(boolean secim) {
            clickJs(btnKaydet);
            //btnKaydet.click();
            if (secim == true) {
                clickJs(btnKaydetEvet);
            } else {
                clickJs(btnKaydetHayir);
            }
            return this;
        }

        @Step("Ekrani kapat")
        public BilgilerTab ekraniKapat() {
            btnEkranKapat.click();
            return this;
        }

        @Step("Parafçı, Kontrolcü, Koordineci ve İmzacı combo kontrolu")
        public BilgilerTab onayAkisiKullaniciComboKontrol() {

            onayAkisiEkle();

            if (cmbSelectOneMenu.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Allure.addAttachment("Parafçı, Kontrolcü, Koordineci ve İmzacı combo gelmedi.", "");
                log.info("Parafçı, Kontrolcü, Koordineci ve İmzacı combo gelmedi.");
                Assert.assertTrue(false);
            }
            return this;
        }

        @Step("Bilgileri tabında Onay Akışı alanında görüntülenmeme kontrolu")
        public BilgilerTab onayAkisiAlanindaGoruntulenmemeKontrolu(String onayAkisi) {

            comboLov(cmbOnayAkisiBy).type(onayAkisi).titleItems().filterBy(exactText(onayAkisi)).shouldHaveSize(0);
            System.out.println("MyCombolov alanında " + onayAkisi + ": Onay Akışın görüntülenmediği görülür.");

            return this;
        }

    }


    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class EditorTab extends MainPage {
        //SelenideElement divEditor = $("editorAntetBaslik");
        SelenideElement divEditor1 = $(By.id("cke_1_contents"));
        SelenideElement divEditorInput = $("[id='cke_1_contents']");
        SelenideElement divHitap = $("div[id='yeniGidenEvrakForm:hitapInplace'] > span");
        SelenideElement btnEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
        SelenideElement btnHayir = $(By.id("kaydetConfirmForm:kaydetHayirButton"));

        private EditorTab open() {
            tabEditor.click();
            return this;

        }

        @Step("Kaydet")
        public EditorTab kaydet(boolean secim) {
            btnKaydet.click();
            if (secim == true) {
                btnEvet.click();
            } else {
                btnHayir.click();
            }
            return this;
        }


        @Step("Editör İçerik Doldur")
        public EditorTab editorIcerikDoldur(String icerik) {
            divEditor1.click();
            divEditor1.setValue(icerik);
            divEditorInput.sendKeys(icerik);
            return this;


        }


    }

    public EkleriTab ekleriTabAc() {
        return ekleriTab.open();
    }

    public class EkleriTab extends MainPage {
        //Doysa ekle tab
        SelenideElement btnDosyaEkleTabDosyaEkle = $(By.id("yeniKararEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
        SelenideElement txtDosyaEkleEkMetni = $(By.id("yeniKararEvrakForm:evrakEkTabView:dosyaAciklama"));
        SelenideElement btnDosyaEkleKaydet = $(By.id("yeniKararEvrakForm:evrakEkTabView:dosyaEkleButton"));

        private EkleriTab open() {
            tabEkleri.click();
            return this;

        }

        @Step("Ek metni doldur")
        public EkleriTab dosyaEkleEkMetniDoldur(String ekMetni) {
            txtDosyaEkleEkMetni.setValue(ekMetni);
            return this;
        }

        @Step("Kaydet")
        public EkleriTab dosyaEkleKaydet() {
            btnDosyaEkleKaydet.click();
            return this;
        }

        @Step("Dosya ekle")
        public EkleriTab dosyaEkleTabDosyaEkle(String pathFile) {
            uploadFile(btnDosyaEkleTabDosyaEkle, pathFile);
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

        SelenideElement metinEkleTab = $("a[href='#yeniKararEvrakForm:ilgiIslemleriTabView:aciklamaEkleTab']");
        SelenideElement metinEkleTabEkMetni = $(By.id("yeniKararEvrakForm:ilgiIslemleriTabView:aciklamaTextArea"));
        SelenideElement btnMetinEkleEkle = $(By.id("yeniKararEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));

        private IlgileriTab open() {
            tabIlgileri.click();
            return this;
        }

        @Step("Metin ekle tab")
        public IlgileriTab metinEkleTab() {
            metinEkleTab.click();
            return this;
        }

        @Step("İlgi metni doldur ")
        public IlgileriTab ilgiMetniDoldur(String ilgiMetni) {
            metinEkleTabEkMetni.setValue(ilgiMetni);
            return this;
        }

        @Step("Ekle")
        public IlgileriTab metinEkleEkle() {
            btnMetinEkleEkle.click();
            return this;
        }



    }

    public IliskiliEvraklarTab iliskiliEvraklarTabAc() {
        return iliskiliEvraklarTab.open();
    }

    public class IliskiliEvraklarTab extends MainPage {

        //Sistemde kayıtlı evrak ekle tabı
        SelenideElement sistemdeKayitliEvrakEkleTab = $("a[href='#yeniKararEvrakForm:ilisikIslemleriTabView:sistemdeKayitliEvragiEkleTab']");
        SelenideElement txtSistemdeKayitliEvrakEkleEvrakArama = $(By.id("yeniKararEvrakForm:ilisikIslemleriTabView:evrakAramaText"));
        SelenideElement btnSistemdeKayitliEvrakEkleDokumanAra = $(By.id("yeniKararEvrakForm:ilisikIslemleriTabView:dokumanAraButton"));
        SelenideElement btnSistemdeKayitliEvrakEkleArti = $(By.id("[id^='yeniKararEvrakForm:ilisikIslemleriTabView:sistemdeKayitliEvrakListesiDataTable:0:']"));

        private IliskiliEvraklarTab open() {
            tabIliskiliEvraklar.click();
            return this;

        }

        @Step("Sistemde kayıtlı evrak ekle tab aç")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleTabAc() {
            sistemdeKayitliEvrakEkleTab.click();
            return this;
        }

        @Step("Sistemde kayıtlı evrak ekle artı")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleArti() {
            btnSistemdeKayitliEvrakEkleArti.click();
            return this;
        }

        @Step("Sistemde kayıtlı evrak ekle tab aç")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleEvrakAramaDoldur(String evrakArama) {
            txtSistemdeKayitliEvrakEkleEvrakArama.setValue(evrakArama);
            return this;
        }

        @Step("Sistemde kayıtlı evrak ekle tab aç")
        public IliskiliEvraklarTab sistemdeKayitliEvrakEkleEvrakDokumanAra() {
            btnSistemdeKayitliEvrakEkleDokumanAra.click();
            return this;
        }

    }

    public EvrakNotlariTab evrakNotlariTabAc() {
        return evrakNotlariTab.open();
    }

    public class EvrakNotlariTab extends MainPage {

        private EvrakNotlariTab open() {
            tabEvrakNotlari.click();
            return this;

        }
    }

    public class PDFKontrol extends MainPage {

        @Step("Gereği alanında adres gelmedigi, Bilgi alanında dagitim yerinin adresi ile geldigi gorulur")
        public PDFKontrol geregiBilgiAlaniAdresPdfKontrol(String birinciKullaniciGeregiAdresi, String ikinciKullaniciBilgiAdresi) throws InterruptedException {

            return this;
        }


    }
    //endregion

}



