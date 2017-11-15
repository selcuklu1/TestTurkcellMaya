package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;
import pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvrakKayitPage extends BaseLibrary {

    SelenideElement pageTitle = $(By.cssSelector("#baseLayoutCenter .ui-dialog-title"));

    // Evrak Bilgileri Sekmesinde bulunanlar
    SelenideElement txtEvrakBilgileriListKonuKodu = $(By.id("evrakBilgileriForm:evrakBilgileriList:1:konuKoduLov:LovText"));
    SelenideElement txtEvrakBilgileriListKonu = $(By.id("evrakBilgileriForm:evrakBilgileriList:4:konuTextArea"));
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $(By.id("evrakBilgileriForm:evrakBilgileriList:5:evrakTuruCombo"));
    SelenideElement cmbEvrakBilgileriListEvrakDili = $(By.id("evrakBilgileriForm:evrakBilgileriList:7:evrakDili"));
    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $(By.id("evrakBilgileriForm:evrakBilgileriList:8:evrakTarihi_input"));
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $(By.xpath("//*[@id='evrakBilgileriForm:evrakBilgileriList:9:guvenlikKodu']"));

    // SelenideElement cmbEvrakBilgileriListKisiKurum = $(By.id("evrakBilgileriForm:evrakBilgileriList:9:kisiKurum"));
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id^='evrakBilgileriForm:evrakBilgileriList'][id$='kisiKurum']");
    BelgenetElement txtEvrakBilgileriListGeldigiKurum = comboLov(By.id("evrakBilgileriForm:evrakBilgileriList:9:geldigiGercekKisiLov:LovText"));

    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSol = $(By.id("evrakBilgileriForm:evrakBilgileriList:11:evrakSayiTextAreaSol"));
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $(By.id("evrakBilgileriForm:evrakBilgileriList:11:evrakSayiTextAreaSag"));
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $(By.id("evrakBilgileriForm:evrakBilgileriList:12:evrakGelisTipi"));
    SelenideElement cmbEvrakBilgileriListIvedilik = $(By.id("evrakBilgileriForm:evrakBilgileriList:13:ivedilik"));
    SelenideElement txtEvrakBilgileriListMiat = $(By.id("evrakBilgileriForm:evrakBilgileriList:14:miatCalendar_input"));
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("evrakBilgileriForm:evrakBilgileriList:15:j_idt4318"));
    SelenideElement cmbEvrakBilgileriListOzelKategori = $(By.id("evrakBilgileriForm:evrakBilgileriList:17:j_idt4499"));
    SelenideElement dateTxtEvrakBilgileriListPostalanmaTarihi = $(By.id("evrakBilgileriForm:evrakBilgileriList:18:postalanmaTarihi_input"));

    // Evrak Ekleri sekmesinde bulunanlar
    // Dosya ekle alt sekmesinde bulunanlar
    SelenideElement btnEvrakEkTabViewEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaEkleButton"));
    SelenideElement btnEvrakEkTabViewTemizle = $(By.id("evrakBilgileriForm:evrakEkTabView:dosyaTemizleButton"));
    SelenideElement cmbEvrakEkTabViewGizlilikDerecesi = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:guvenlikKodu']"));
    SelenideElement txtEvrakEkTabViewEkMetni = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:dosyaAciklama']"));
    SelenideElement btvEvrakEkTabViewDosyaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:fileUploadButton_input"));

    //Fiziksel Ek Ekle alt sekmesinde bulunanlar
    SelenideElement txtEvrakEkTabViewAciklama = $(By.xpath("//*[@id='evrakBilgileriForm:evrakEkTabView:aciklamaTextArea']"));
    SelenideElement cmbEvrakEkTabViewGuvenlikKoduAciklama = $(By.id("evrakBilgileriForm:evrakEkTabView:guvenlikKoduAciklama"));
    SelenideElement btnEvrakEkTabViewAciklamaEkle = $(By.id("evrakBilgileriForm:evrakEkTabView:aciklamaEkleButton"));

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
    SelenideElement txtDagitimBilgileriBirim = $(By.id("evrakBilgileriForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement txtDagitimBilgileriKisi = $(By.id("evrakBilgileriForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtDagitimBilgileriKullaniciListesi = $(By.id("evrakBilgileriForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement btnDagitimBilgileriOnaylayacakKisi = $(By.id("evrakBilgileriForm:onaylayacakKisiLov:treeButton"));
    SelenideElement txtDagitimBilgileriAciklama = $(By.xpath("//*[@id='evrakBilgileriForm:havaleAciklama']"));
    SelenideElement btnDagitimBilgileriDosyaEkle = $(By.id("evrakBilgileriForm:fileUploadHavaleEk_input"));
    SelenideElement txtDagitimBilgileriIslemSuresi = $(By.id("evrakBilgileriForm:islemSuresiTarih_input"));
    SelenideElement chkDagitimBilgileriEvragiOnayliKapat = $(By.id("evrakBilgileriForm:j_idt5629_input"));
    SelenideElement btnDagitimBilgileriEvragiKapatacakKisi = $(By.id("evrakBilgileriForm:evrakiKapatacakKisiLov:treeButton"));//todo:Evrakı Onaylı Kapat secili olmadan çıkmıyor

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


    public GelenEvrakKayitPage evrakBilgileriListKonuKoduDoldur(String konuKodu) throws InterruptedException {
        txtEvrakBilgileriListKonuKodu.sendKeys(konuKodu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKonuDoldur(String konu) throws InterruptedException {
        txtEvrakBilgileriListKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakTuruSec(String evrakTuru) throws InterruptedException {
        cmbEvrakBilgileriListEvrakTuru.selectOption(evrakTuru);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakDiliSec(String evrakDili) throws InterruptedException {
        cmbEvrakBilgileriListEvrakDili.selectOption(evrakDili);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakTarihiDoldur(String evrakTarihi) throws InterruptedException {
        dateTxtEvrakBilgileriListEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListGizlilikDerecesiSec(String gizlilikDerecesi) throws InterruptedException {
        cmbEvrakBilgileriListGizlilikDerecesi.selectOptionByValue(gizlilikDerecesi);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListKisiKurumSec(String kisiKurum) {
        cmbEvrakBilgileriListKisiKurum.selectOptionByValue(kisiKurum);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListGeldigiKurumDoldur(String geldigiKurum) {

        txtEvrakBilgileriListGeldigiKurum.selectComboLov(geldigiKurum);
        //shouldHave(Condition.text(geregi));

        System.out.println("title: " + txtEvrakBilgileriListGeldigiKurum.lastSelectedLovTitleText());
        System.out.println("detail: " + txtEvrakBilgileriListGeldigiKurum.lastSelectedLovDetailText());

        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakSayiSolDoldur(String evrakSayiSol) throws InterruptedException {
        txtEvrakBilgileriListEvrakSayiTextAreaSol.sendKeys(evrakSayiSol);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakSayiSagDoldur(String evrakSayiSag) throws InterruptedException {
        txtEvrakBilgileriListEvrakSayiTextAreaSag.sendKeys(evrakSayiSag);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListEvrakGelisTipiSec(String evrakGelisTipi) throws InterruptedException {
        cmbEvrakBilgileriListEvrakGelisTipi.selectOption(evrakGelisTipi);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListIvedilikSec(String ivedilik) throws InterruptedException {
        cmbEvrakBilgileriListIvedilik.selectOption(ivedilik);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListMiatDoldur(String miat) throws InterruptedException {
        txtEvrakBilgileriListMiat.sendKeys(miat);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListAciklamaDoldur(String evrakBilgileriAciklama) throws InterruptedException {
        txtEvrakBilgileriListAciklama.sendKeys(evrakBilgileriAciklama);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListOzelKategoriSec(String ozelKategori) throws InterruptedException {
        cmbEvrakBilgileriListOzelKategori.selectOption(ozelKategori);
        return this;
    }

    public GelenEvrakKayitPage evrakBilgileriListPostalanmaTarihiDoldur(String postalanmaTarihi) throws InterruptedException {
        dateTxtEvrakBilgileriListPostalanmaTarihi.sendKeys(postalanmaTarihi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriBirimDoldur(String birim) throws InterruptedException {
        txtDagitimBilgileriBirim.sendKeys(birim);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKisiDoldur(String kisi) throws InterruptedException {
        txtDagitimBilgileriKisi.sendKeys(kisi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriKullaniciListesiDoldur(String kullaniciListesi) throws InterruptedException {
        txtDagitimBilgileriKullaniciListesi.sendKeys(kullaniciListesi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriOnaylayacakKisi() throws InterruptedException {
        btnDagitimBilgileriOnaylayacakKisi.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriAciklamaDoldur(String aciklama) throws InterruptedException {
        txtDagitimBilgileriAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriDosyaEkle() throws InterruptedException {
        btnDagitimBilgileriDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriIslemSuresiDoldur(String islemSuresi) throws InterruptedException {
        txtDagitimBilgileriIslemSuresi.sendKeys(islemSuresi);
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriEvragiKapatacakKisi() throws InterruptedException {
        btnDagitimBilgileriEvragiKapatacakKisi.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkle() throws InterruptedException {
        btnEvrakEkTabViewEkle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewTemizle() throws InterruptedException {
        btnEvrakEkTabViewTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewGizlilikDerecesiSec(String gizlilikDerecesi) throws InterruptedException {
        cmbEvrakEkTabViewGizlilikDerecesi.selectOption(gizlilikDerecesi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkMetniDoldur(String evrakEkTabViewEkMetni) throws InterruptedException {
        txtEvrakEkTabViewEkMetni.sendKeys(evrakEkTabViewEkMetni);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewDosyaEkle() throws InterruptedException {
        btvEvrakEkTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage dagitimBilgileriEvragiOnayliKapatSec(boolean check) throws InterruptedException {
        chkDagitimBilgileriEvragiOnayliKapat.setSelected(check);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewAciklamaDoldur(String aciklama) throws InterruptedException {
        txtEvrakEkTabViewAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewGuvenlikKoduAciklamaSec(String guvenlikKoduAciklama) throws InterruptedException {
        cmbEvrakEkTabViewGuvenlikKoduAciklama.selectOption(guvenlikKoduAciklama);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewAciklamaEkle() throws InterruptedException {
        btnEvrakEkTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkIslemleriEvrakTarihBasDoldur(String baslamaTarihi) throws InterruptedException {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewEkIslemleriEvrakTarihSonDoldur(String sonTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewEkIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewekIslemleriEvrakAramaAranacakYerSec(String aranacakYer) throws InterruptedException {
        cmbEvrakEkTabViewekIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewevrakAramaDoldur(String arama) throws InterruptedException {
        txtEvrakEkTabViewevrakArama.sendKeys(arama);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraEkEkleTarihBasDoldur(String baslamaTarihi) throws InterruptedException {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraEkEkleTarihSonDoldur(String sonTarih) throws InterruptedException {
        dateTxtEvrakEkTabViewArsivdenEvrakAraEkEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraKonuDoldur(String konu) throws InterruptedException {
        txtEvrakEkTabViewArsivdenEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewKullaniciDoldur(String kullanici) throws InterruptedException {
        txtEvrakEkTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage evrakEkTabViewArsivdenEvrakAraSayiDoldur(String evrakSayi) throws InterruptedException {
        txtEvrakEkTabViewArsivdenEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewDosyaAciklamaDoldur(String aciklama) throws InterruptedException {
        txtIlgiIslemleriTabViewDosyaAciklama.sendKeys(aciklama);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewDosyaEkle() throws InterruptedException {
        btnIlgiIslemleriTabViewDosyaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiDosyaTemizle() throws InterruptedException {
        btnIlgiIslemleriTabViewIlgiDosyaTemizle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewAciklamaEkle() throws InterruptedException {
        btnIlgiIslemleriTabViewAciklamaEkle.click();
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihBasDoldur(String baslamaTarihi) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakTarihSonDoldur(String sonTarih) throws InterruptedException {
        datetxtIlgiIslemleriTabViewIlgiIslemleriEvrakTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYerSec(String aranacakYer) throws InterruptedException {
        cmbIlgiIslemleriTabViewIlgiIslemleriEvrakAramaAranacakYer.selectOption(aranacakYer);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewEvrakAramaDoldur(String arama) throws InterruptedException {
        txtIlgiIslemleriTabViewEvrakArama.sendKeys(arama);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBasDoldur(String baslamaTarihi) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihBas.sendKeys(baslamaTarihi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSonDoldur(String sonTarih) throws InterruptedException {
        dateTxtIlgiIslemleriTabViewArsivdenEvrakAraIlgiEkleTarihSon.sendKeys(sonTarih);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenIlgiEvrakAraKonuDoldur(String konu) throws InterruptedException {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraKonu.sendKeys(konu);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewKullaniciDoldur(String kullanici) throws InterruptedException {
        txtIlgiIslemleriTabViewKullanici.sendKeys(kullanici);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewArsivdenIlgiEvrakAraSayiDoldur(String evrakSayi) throws InterruptedException {
        txtIlgiIslemleriTabViewArsivdenIlgiEvrakAraSayi.sendKeys(evrakSayi);
        return this;
    }

    public GelenEvrakKayitPage ilgiIslemleriTabViewAciklamaDoldur(String aciklama) throws InterruptedException {
        txtIlgiIslemleriTabViewAciklama.sendKeys(aciklama);
        return this;
    }
}
