package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GelenEvraklarCevapYazPage extends BaseLibrary {

    //Bilgileri sekmesinde bulunanlar
    SelenideElement cmbEvrakTuru = $(By.id("windowCevapEvrakForm:evrakBilgileriList:1:evrakTuruCombo"));
    SelenideElement cmbEmirTalimat = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:2:emirTalimatPanelGrid']/tbody/tr/td[3]/select"));
    SelenideElement cmbEvrakDili = $(By.id("windowCevapEvrakForm:evrakBilgileriList:3:evrakDili"));
    SelenideElement txtKonuKodu = $(By.id("windowCevapEvrakForm:evrakBilgileriList:4:konuKoduLov:LovText"));
    SelenideElement txtKonu = $(By.id("windowCevapEvrakForm:evrakBilgileriList:5:konuTextArea"));
    SelenideElement txtKaldirilacakKlasorler = $(By.id("windowCevapEvrakForm:evrakBilgileriList:6:eklenecekKlasorlerLov:LovText"));
    SelenideElement cmbArsivKategorisi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:7:j_idt35880"));
    SelenideElement cmbGizlilikDerecesi = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakBilgileriList:9:guvenlikKodu']"));
    SelenideElement cmbIvedilik = $(By.id("windowCevapEvrakForm:evrakBilgileriList:10:ivedilik"));
    SelenideElement txtMiat = $(By.id("windowCevapEvrakForm:evrakBilgileriList:11:miatCalendar_input"));
    SelenideElement cmbGeregiSecimTipi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:12:j_idt35921"));
    SelenideElement txtGeregi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:12:geregiLov:LovText"));
    SelenideElement cmbBilgiSecimTipi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:13:j_idt35912"));
    SelenideElement txtBilgi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:13:bilgiLov:LovText"));
    SelenideElement txtOnayAkisi = $(By.id("windowCevapEvrakForm:evrakBilgileriList:14:akisLov:LovText"));
    SelenideElement chkDagitimiEkYap = $(By.id("windowCevapEvrakForm:evrakBilgileriList:16:dagitimEkYapCheckBoxId_input"));

    //Ekleri Sekmesi
    //Dosya Ekle alt sekmesi
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:dosyaAciklama']"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement btnEvrakEkTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:fileUploadButtonA_input"));
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("windowCevapEvrakForm:evrakEkTabView:dosyaTemizleButton"));

    //Fiziksel Ek Ekle alt sekmesi
    SelenideElement txtEvrakEkTabViewFizikselEkMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:aciklamaTextArea']"));
    SelenideElement cmbEvrakEkTabViewGuvenlikKoduAciklama = $(By.id("windowCevapEvrakForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEvrakEkTabViewFizikselEkEkle = $(By.id("windowCevapEvrakForm:evrakEkTabView:aciklamaEkleButton"));


    //Sistemde Kayıtlı Evrak Ekle
    SelenideElement dateTxtEvrakEkTabViewEvrakBaslamaTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewEvrakSonTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbEvrakEkTabViewEvrakinAranacagiYer = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:ekIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement btnEvrakEkTabViewDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:dokumanAraButton']"));
    SelenideElement txtEvrakEkTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:evrakAramaText']"));

    //Arşivde Kayıtlı Evrak Ekle alt sekmesi
    SelenideElement dateTxtEvrakEkTabViewArsivdeKayitliEvrakBaslamaTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihBasId_input"));
    SelenideElement dateTxtEvrakEkTabViewArsivdeKayitliEvrakSonTarih = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraEkEkleTarihSonId_input"));
    SelenideElement txtEvrakEkTabViewKonu = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraKonuInputTextId"));
    SelenideElement txtEvrakEkTabViewKullanici = $(By.id("windowCevapEvrakForm:evrakEkTabView:kisiyeLov_id:LovText"));
    SelenideElement txtEvrakEkTabViewEvrakSayisi = $(By.id("windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraSayiInputTextId"));
    SelenideElement btnEvrakEkTabViewArsivdeKayitliDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:evrakEkTabView:arsivdenEvrakAraButtonId']"));
    //İlgileri sekmesi
    //Dosya Ekle alt sekmesi
    SelenideElement txtIlgiIslemleriTabViewIlgiMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:dosyaAciklama']"));
    SelenideElement btnIlgiIslemleriTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:fileUploadButtonA_input"));
    SelenideElement btnIlgiIslemleriTabViewEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:dosyaEkleButton"));
    SelenideElement btnIlgiIslemleriTabViewTemizle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiDosyaTemizleButton"));

    //Metin Ekle alt sekmesi
    SelenideElement txtIlgiIslemleriTabViewMetinEkleIlgiMetni = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:aciklamaTextArea']"));
    SelenideElement btnIlgiIslemleriTabViewAciklamaEkle = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:aciklamaEkleButton"));

    //Sistemde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBasId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihBasId_input"));
    SelenideElement dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSonId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakTarihSonId_input"));
    SelenideElement cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerId = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:ilgiIslemleriEvrakAramaAranacakYerId']"));
    SelenideElement txtIlgiIslemleriTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:evrakAramaText']"));
    SelenideElement btnIlgiIslemleriTabViewDokumanAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:dokumanAraButton']"));

    //Arşivde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihBasId_input"));
    SelenideElement dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonId = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenEvrakAraIlgiEkleTarihSonId_input"));
    SelenideElement txtIlgiIslemleriTabViewKonu = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraKonuInputTextId"));
    SelenideElement txtIlgiIslemleriTabViewKullanici = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlgiIslemleriTabViewEvrakSayi = $(By.id("windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraSayiInputTextId"));
    SelenideElement btnIlgiIslemleriTabViewArsivdenIlgiEvrakAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilgiIslemleriTabView:arsivdenIlgiEvrakAraButtonId']"));

    //İlişkili Evraklar sekmesi
//Dosya Ekle alt sekmesi
    SelenideElement txtIlisikIslemleriTabViewIlisikMetni = $(By.id("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:dosyaAciklama']"));
    SelenideElement btnIlisikIslemleriTabViewDosyaEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:fileUploadButtonA_input"));
    SelenideElement btnIlisikIslemleriTabViewEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:ilisikEkleButton"));
    SelenideElement btnIlisikIslemleriTabViewTemizle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:ilisikTemizleButton"));

    //Sistemde Kayıtlı Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihBasId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihBasId_input"));
    SelenideElement dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihSonId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakTarihSonId_input"));
    SelenideElement cmbIlisikIslemleriTabViewEvrakınAranacagiYer = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:iliskiliEvrakAramaAranacakYerId']"));
    SelenideElement txtIlisikIslemleriTabViewEvrakArama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:evrakAramaText']"));
    SelenideElement btnIlisikIslemleriTabViewDokumanAra = $(By.id("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:dokumanAraButton']"));

    //Tercume Ekle alt sekmesi
    SelenideElement txtIlisikIslemleriTabViewTercumeAciklama = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:tercumeAciklama']"));
    SelenideElement btnIlisikIslemleriTabViewTercumeDosyaEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:fileUploadButtonB_input"));
    SelenideElement btnIlisikIslemleriTabViewTercumeEkle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:tercumeEkleButton"));
    SelenideElement btnIlisikIslemleriTabViewTercumeTemizle = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:tercumeTemizleButton"));

    //Arsivde Kayitli Evrak Ekle alt sekmesi
    SelenideElement dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihBasId_input"));
    SelenideElement dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonId = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenEvrakAraIlisikEkleTarihSonId_input"));
    SelenideElement txtIlisikIslemleriTabViewKonu = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraKonuInputTextId"));
    SelenideElement txtIlisikIslemleriTabViewKullanici = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:kisiyeLov_id:LovText"));
    SelenideElement txtIlisikIslemleriTabViewEvrakSayi = $(By.id("windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraSayiInputTextId"));
    SelenideElement btnIlisikIslemleriTabViewArsivdenIlisikEvrakAra = $(By.xpath("//*[@id='windowCevapEvrakForm:ilisikIslemleriTabView:arsivdenIlisikEvrakAraButtonId']"));


    public GelenEvraklarCevapYazPage evrakTuruSec(String evrakTuru) throws InterruptedException {
        cmbEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    public GelenEvraklarCevapYazPage emirTalimatSec(String emirTalimat) throws InterruptedException {
        cmbEmirTalimat.selectOption(emirTalimat);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakDiliSec(String evrakDili) throws InterruptedException {
        cmbEvrakDili.selectOption(evrakDili);
        return this;
    }

    public GelenEvraklarCevapYazPage konuKoduDoldur(String konuKodu) throws InterruptedException {
        txtKonuKodu.sendKeys(konuKodu);
        return this;
    }

    public GelenEvraklarCevapYazPage konuDoldur(String konu) throws InterruptedException {
        txtKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage kaldirilacakKlasorlerDoldur(String kaldirilacakKlasorler) throws InterruptedException {
        txtKaldirilacakKlasorler.sendKeys(kaldirilacakKlasorler);
        return this;
    }

    public GelenEvraklarCevapYazPage arsivKategorisiSec(String arsivKategorisi) throws InterruptedException {
        cmbArsivKategorisi.selectOption(arsivKategorisi);
        return this;
    }

    public GelenEvraklarCevapYazPage gizlilikDerecesiSec(String gizlilikDerecesi) throws InterruptedException {
        cmbGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage ivedilikSec(String ivedilik) throws InterruptedException {
        cmbIvedilik.selectOption(ivedilik);
        return this;
    }

    public GelenEvraklarCevapYazPage miatDoldur(String miat) throws InterruptedException {
        txtMiat.sendKeys(miat);
        return this;
    }

    public GelenEvraklarCevapYazPage geregiSecimTipiSec(String geregiSecimTipi) throws InterruptedException {
        cmbGeregiSecimTipi.selectOption(geregiSecimTipi);
        return this;
    }

    public GelenEvraklarCevapYazPage geregiDoldur(String geregi) throws InterruptedException {
        txtGeregi.sendKeys(geregi);
        return this;
    }

    public GelenEvraklarCevapYazPage bilgiSecimTipiSec(String bilgiSecimTipi) throws InterruptedException {
        cmbBilgiSecimTipi.selectOption(bilgiSecimTipi);
        return this;
    }

    public GelenEvraklarCevapYazPage bilgiDoldur(String bilgi) throws InterruptedException {
        txtBilgi.sendKeys(bilgi);
        return this;
    }

    public GelenEvraklarCevapYazPage onayAkisiDoldur(String onayAkisi) throws InterruptedException {
        txtOnayAkisi.sendKeys(onayAkisi);
        return this;
    }

    public GelenEvraklarCevapYazPage dagitimiEkYapSec(boolean dagitimiEkYap) throws InterruptedException {
        chkDagitimiEkYap.setSelected(dagitimiEkYap);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEkMetniDoldur(String ekMetin) throws InterruptedException {
        txtEvrakEkTabViewEkMetni.sendKeys(ekMetin);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewGizlilikDerecesiSec(String gizlilikDerecesi) throws InterruptedException {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewDosyaEkle() throws InterruptedException {
        btnEvrakEkTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEkle() throws InterruptedException {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewTemizle() throws InterruptedException {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewFizikselEkMetniDoldur(String fizikselEkMetin) throws InterruptedException {
        txtEvrakEkTabViewFizikselEkMetni.sendKeys(fizikselEkMetin);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewGuvenlikKoduAciklamaSec(String gizlilikDerecesi) throws InterruptedException {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewFizikselEkEkle() throws InterruptedException {
        btnEvrakEkTabViewFizikselEkEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakBaslamaTarihDoldur(String baslamaTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakSonTarihDoldur(String sonTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakinAranacagiYerSec(String evraginAranacagiYer) throws InterruptedException {
        cmbEvrakEkTabViewEvrakinAranacagiYer.selectOption(evraginAranacagiYer);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewDokumanAra() throws InterruptedException {
        btnEvrakEkTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakAramaDoldur(String evrakArama) throws InterruptedException {
        txtEvrakEkTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliEvrakBaslamaTarihDoldur(String baslamaTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakBaslamaTarih.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliEvrakSonTarihDoldur(String sonTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewArsivdeKayitliEvrakSonTarih.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewKonuDoldur(String konu) throws InterruptedException {
        txtEvrakEkTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewKullaniciDoldur(String kullanici) throws InterruptedException {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewEvrakSayisiDoldur(String evrakSayi) throws InterruptedException {
        txtEvrakEkTabViewEvrakSayisi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage evrakEkTabViewArsivdeKayitliDokumanAra() throws InterruptedException {
        btnEvrakEkTabViewArsivdeKayitliDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiMetniDoldur(String ilgiMetni) throws InterruptedException {
        txtIlgiIslemleriTabViewIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewDosyaEkle() throws InterruptedException {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEkle() throws InterruptedException {
        btnIlgiIslemleriTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewTemizle() throws InterruptedException {
        btnIlgiIslemleriTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewMetinEkleIlgiMetniDoldur(String ilgiMetni) throws InterruptedException {
        txtIlgiIslemleriTabViewMetinEkleIlgiMetni.sendKeys(ilgiMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewAciklamaEkle() throws InterruptedException {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihBasIdDoldur(String baslamaTarih) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihSonIdDoldur(String sonTarih) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerIdSec(String aranacakYer) throws InterruptedException {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerId.selectOption(aranacakYer);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEvrakAramaDoldur(String evrakArama) throws InterruptedException {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewDokumanAra() throws InterruptedException {
        btnIlgiIslemleriTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasIdDoldur(String baslamaTarihi) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihBasId.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonIdDoldur(String sonTarih) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewarsivdenEvrakAraIlgiEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewKonuDoldur(String konu) throws InterruptedException {
        txtIlgiIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewKullaniciDoldur(String kullanici) throws InterruptedException {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewEvrakSayiDoldur(String evrakSayi) throws InterruptedException {
        txtIlgiIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilgiIslemleriTabViewArsivdenIlgiEvrakAra() throws InterruptedException {
        btnIlgiIslemleriTabViewArsivdenIlgiEvrakAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIlisikMetniDoldur(String ilisikMetni) throws InterruptedException {
        txtIlisikIslemleriTabViewIlisikMetni.sendKeys(ilisikMetni);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewDosyaEkle() throws InterruptedException {
        btnIlisikIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEkle() throws InterruptedException {
        btnIlisikIslemleriTabViewEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTemizle() throws InterruptedException {
        btnIlisikIslemleriTabViewTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihBasIdDoldur(String baslamaTarih) throws InterruptedException {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewIliskiliEvrakTarihSonIdDoldur(String sonTarih) throws InterruptedException {
        dateTxtIlisikIslemleriTabViewIliskiliEvrakTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakınAranacagiYerSec(String evrakınAranacagiYer) throws InterruptedException {
        cmbIlisikIslemleriTabViewEvrakınAranacagiYer.selectOption(evrakınAranacagiYer);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakAramaDoldur(String evrakArama) throws InterruptedException {
        txtIlisikIslemleriTabViewEvrakArama.sendKeys(evrakArama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewDokumanAra() throws InterruptedException {
        btnIlisikIslemleriTabViewDokumanAra.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeAciklamaDoldur(String tercumeAciklama) throws InterruptedException {
        txtIlisikIslemleriTabViewTercumeAciklama.sendKeys(tercumeAciklama);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeDosyaEkle() throws InterruptedException {
        btnIlisikIslemleriTabViewTercumeDosyaEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeEkle() throws InterruptedException {
        btnIlisikIslemleriTabViewTercumeEkle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewTercumeTemizle() throws InterruptedException {
        btnIlisikIslemleriTabViewTercumeTemizle.click();
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasIdDoldur(String baslamaTarih) throws InterruptedException {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihBasId.sendKeys(baslamaTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonIdDoldur(String sonTarih) throws InterruptedException {
        dateTxtIlisikIslemleriTabViewArsivdenEvrakAraIlisikEkleTarihSonId.sendKeys(sonTarih);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewKonuDoldur(String konu) throws InterruptedException {
        txtIlisikIslemleriTabViewKonu.sendKeys(konu);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewKullaniciDoldur(String kullanici) throws InterruptedException {
        txtIlisikIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewEvrakSayiDoldur(String evrakSayi) throws InterruptedException {
        txtIlisikIslemleriTabViewEvrakSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvraklarCevapYazPage ilisikIslemleriTabViewArsivdenIlisikEvrakAra() throws InterruptedException {
        btnIlisikIslemleriTabViewArsivdenIlisikEvrakAra.click();
        return this;
    }
}
