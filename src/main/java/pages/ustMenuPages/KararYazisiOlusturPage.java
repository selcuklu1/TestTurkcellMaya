package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.not;

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
    SelenideElement tabBilgiler = $("button .kullaniciBilgileri");
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
    public KararYazisiOlusturPage openPage(){
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
        public BilgilerTab gonder(boolean secim){
            btnGonder.click();
            if (secim == true){
                btnEvet.click();
            }
            else{
                btnHayir.click();
            }
            return this;
        }

        @Step("Kaydet ve onay sun")
        public BilgilerTab kaydetveOnaySun(){
            btnKaydetOnayaSun.click();
            return this;
        }
        @Step("Onay akışı temizle")
        public BilgilerTab onayAkisiTemizle(){
            btnOnayakisiTemizle.click();
            return this;
        }
        @Step("Açıklama doldur")
        public BilgilerTab aciklamaDoldur(String aciklama){
            txtAciklama.setValue(aciklama);
            return this;
        }

        @Step("Kullan")
        public BilgilerTab kullan(){
            btnKullanicilarKullan.click();
            return this;
        }

        @Step("Karar no doldur")
        public BilgilerTab kararNoDoldur(String kararNo){
            txtKararNo.setValue(kararNo);
            return this;
        }

        @Step("Toplantı tarih seç")
        public BilgilerTab toplantiTarihDoldur(String tarih){
            dateToplantiTarihi.setValue(tarih);
            return this;
        }

        @Step("Toplanti no doldur")
        public BilgilerTab toplantiNoDoldur(String toplantiNo){
            txtToplantiNo.setValue(toplantiNo);
            return this;
        }

        @Step("Kaldirilacak klasörler doldur")
        public BilgilerTab kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler){
            txtKaldirilacakKlasorler.selectLov(kaldirilacakKlasorler);
            return this;
        }

        @Step("Onay akışı doldur")
        public BilgilerTab onayAkisiDoldur(String onayAkisi){
            txtOnayAkisi.selectLov(onayAkisi);
            return this;
        }


        @Step("Onay akışı kontrol")
        public BilgilerTab imzalamaKontrol(String imzalama){
            Assert.assertEquals(txtOnayAkisi.lastSelectedLovDetailText().contains(imzalama), true);
            return this;
        }

        @Step("Miat Doldur")
        public BilgilerTab miatDoldur(String miat){
            dateMiat.sendKeys(miat);
            return this;
        }

        @Step("İvedilik seç")
        public BilgilerTab ivedilikSec(String secilen){
            cmbIvedilik.selectOption(secilen);
            return this;
        }
        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduDoldur(String konuKodu){
            txtKonuKodu.selectLov(konuKodu);
            return this;
        }
        @Step("Konu kodu doldur")
        public BilgilerTab konuKoduTemizle(){
            btnKonuKoduTemizle.click();
            return this;
        }
        @Step("Kaldırılıcak klasör temizle")
        public BilgilerTab kaldirilacakKlasorTemizle(){
            btnKaldirilicakKlasorTemizle.click();
            return this;
        }
        @Step("Konu doldur")
        public BilgilerTab konuDoldur(String konuKodu){
            txtKonu.setValue(konuKodu);
            return this;
        }

        @Step("Onay akışı ekle")
        public BilgilerTab onayAkisiEkle(){
            clickJs(btnOnayAkisiEkle);
            //btnOnayAkisiEkle.click();
            return this;
        }

        @Step("Kullanıcılar doldur")
        public BilgilerTab kullanicilarDoldur(String kullanici, String birim){
            txtKullanicilar.type(kullanici).titleItems().filterBy(text(birim)).first().click();
            txtKullanicilar.closeLovTreePanel();
            return this;
        }

        @Step("Kaydet")
        public BilgilerTab kaydet(boolean secim){
            clickJs(btnKaydet);
            //btnKaydet.click();
            if (secim == true){
               clickJs(btnKaydetEvet);
            }
            else{
                clickJs(btnKaydetHayir);
            }
            return this;
        }

    }

    public EditorTab editorTabAc() {
        return editorTab.open();
    }

    public class EditorTab extends MainPage {
        //SelenideElement divEditor = $("editorAntetBaslik");
        SelenideElement divEditor1 = $(By.id("cke_1_contents"));
        SelenideElement divEditorInput = $("cke_1_contents");
        SelenideElement divHitap = $("div[id='yeniGidenEvrakForm:hitapInplace'] > span");
        SelenideElement btnEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
        SelenideElement btnHayir = $(By.id("kaydetConfirmForm:kaydetHayirButton"));

        private EditorTab open() {
            tabEditor.click();
            return this;

        }

        @Step("Kaydet")
        public EditorTab kaydet(boolean secim){
            btnKaydet.click();
            if (secim == true){
                btnEvet.click();
            }
            else{
                btnHayir.click();
            }
            return this;
        }



        @Step("Editör İçerik Doldur")
        public EditorTab editorIcerikDoldur(String icerik){
            divEditor1.click();
            divEditor1.sendKeys(icerik);
          //  divEditor.sendKeys(icerik);
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
        SelenideElement tabIliskiliSistemdeKayitliEvrakEkle = $(By.xpath("a//[@href='#yeniGidenEvrakForm:ilgiIslemleriTabView:sistemdeKayitliEvragiEkleTab']"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBaslangic = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
        SelenideElement dateIlgileriSistemdeEvrakTarihiBitis = $(By.id("yeniGidenEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
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
