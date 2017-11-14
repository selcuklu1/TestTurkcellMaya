package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GelenEvraklarPage extends BaseLibrary {

    SelenideElement cmbFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGidecegiYerSeciniz = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement treeGidecegiYer = $(By.id("inboxFiltreDialogForm:geldigiYerFilterLovId:LovText"));
    SelenideElement dateTxtBaslangicTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement btnTopluHavale = $(By.id("mainInboxForm:inboxDataTable:j_idt664"));
    SelenideElement btnTopluKlasorKaldir = $(By.id("mainInboxForm:inboxDataTable:j_idt665"));
    SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));
    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));

    //Havale Yap Alt Yapı
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));
    SelenideElement treeHavaleYapBirim = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovTexts"));
    SelenideElement treeHavaleYapKisi = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement treeHavaleYapKullaniciListesi = $(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement treeHavaleYapOnaylanacakKisi = $(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));
    SelenideElement txtHavaleYapAciklama = $(By.id("mainPreviewForm:havaleAciklama"));
    SelenideElement btnHavaleYapDosyaEkle = $(By.id("mainPreviewForm:fileUploadHavaleEk_input"));
    SelenideElement txtHavaleYapIslemSuresi = $(By.id("mainPreviewForm:islemSuresiTarih_input"));
    SelenideElement chkHavaleYapEvrakOnayliKapat = $(By.id("mainPreviewForm:j_idt30591_input"));
    SelenideElement btnHavaleYapGonder = $(By.id("mainPreviewForm:j_idt30598"));
    SelenideElement btnHavaleYapHavaleOnayinaGonder = $(By.id("mainPreviewForm:j_idt30599"));

    // Tebiğ Et Buttonu altı div
    SelenideElement btnTebligEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));
    SelenideElement txtTebligEtKisi = $(By.id("mainPreviewForm:kullaniciLov_id:LovText"));
    SelenideElement txtTebligEtKullaniciListesi = $(By.id("mainPreviewForm:kullaniciGrubuLov_id:LovText"));
    SelenideElement txtTebligEtNot = $(By.id("mainPreviewForm:tebligNotu_id"));
    SelenideElement btnTebligEtTebligEt = $(By.id("mainPreviewForm:tebligEtButton_id"));

    // İade Et Buttonu altı div
    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));
    SelenideElement txtIadeEtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnIadeEtDosyaEkle = $(By.id("mainPreviewForm:fileUploadIadeEk_input"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    // Cevap Yaz Buttonu
    SelenideElement btnCevapYaz = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));

    //Evrak Kapat Buttonu div
    SelenideElement btnEvrakKapat = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    SelenideElement cmbEvrakKapatKapatmaTipi = $(By.id("mainPreviewForm:kapatmaTipiOneMenu_id"));
    SelenideElement txtEvrakKapatKaldirilacakKlasorler = $(By.id("mainPreviewForm:klasorLov_id:LovText"));
    SelenideElement txtEvrakKapatNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement txtEvrakKapatOnayAkisi = $(By.id("mainPreviewForm:akisLov_id:LovText"));
    SelenideElement btnEvrakKapatKapatmaOnayinaSun = $(By.id("mainPreviewForm:kapatmaOnayinaSunButtonDirektId"));
    SelenideElement btnEvrakKapatEvrakKapat = $(By.id("mainPreviewForm:j_idt30764"));
    SelenideElement chkEvrakKapatKisiselKlasorler = $(By.id("mainPreviewForm:kisiselKlasorlerimiGetirCheckboxId_input"));

    //Paylaş Button altı div
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    SelenideElement txtPaylasKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasIcPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    public GelenEvraklarPage iadeEtIadeEt() throws InterruptedException {
        btnIadeEtIadeEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEtDosyaEkle() throws InterruptedException {
        btnIadeEtDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage iadeEtNotInputDoldur(String text) throws InterruptedException {
        txtIadeEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtTebligEt() throws InterruptedException {
        btnTebligEtTebligEt.click();
        return this;
    }

    public GelenEvraklarPage tebligEtNotInputDoldur(String text) throws InterruptedException {
        txtTebligEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtKullaniciListesiDoldur(String text) throws InterruptedException {
        txtTebligEtKullaniciListesi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtKisiInputDoldur(String text) throws InterruptedException {
        txtTebligEtKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapHavaleOnayinaGonder() throws InterruptedException {
        btnHavaleYapHavaleOnayinaGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapGonder() throws InterruptedException {
        btnHavaleYapGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapEvrakOnayliKapatChecked(Boolean secim) throws InterruptedException {
        chkHavaleYapEvrakOnayliKapat.setSelected(secim);
        return this;
    }

    public GelenEvraklarPage havaleYapIslemSuresiDoldur(String text) throws InterruptedException {
        txtHavaleYapIslemSuresi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapDosyaEkle() throws InterruptedException {
        btnHavaleYapDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage havaleYapAciklamaDoldur(String text) throws InterruptedException {
        txtHavaleYapAciklama.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapOnaylanacakKisiTreeDoldur(String text) throws InterruptedException {
        treeHavaleYapOnaylanacakKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKullaniciListesiTreeDoldur(String text) throws InterruptedException {
        treeHavaleYapKullaniciListesi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKisiTreeDoldur(String text) throws InterruptedException {
        treeHavaleYapKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapBirimTreeDoldur(String text) throws InterruptedException {
        treeHavaleYapBirim.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasIcPaylas() throws InterruptedException {
        btnPaylasIcPaylas.click();
        return this;
    }

    public GelenEvraklarPage paylasanAciklamaDoldur(String text) throws InterruptedException {
        txtPaylasanAciklama.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiDoldur(String text) throws InterruptedException {
        txtPaylasKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage filtreleSec(String value) throws InterruptedException {
        cmbFiltrele.selectOption(value);
        return this;
    }

    public GelenEvraklarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage gidecegiYerSecinizk() throws InterruptedException {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public GelenEvraklarPage gidecegiYerTreeDoldur(String text) throws InterruptedException {
        treeGidecegiYer.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage baslangicTarihDoldur(String text) throws InterruptedException {
        dateTxtBaslangicTarih.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage bitisTarihDoldur(String text) throws InterruptedException {
        dateTxtBitisTarihi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage topluSecim() throws InterruptedException {
        btnTopluSecim.click();
        return this;
    }

    public GelenEvraklarPage topluHavale() throws InterruptedException {
        btnTopluHavale.click();
        return this;
    }

    public GelenEvraklarPage topluKlasorKaldir() throws InterruptedException {
        btnTopluKlasorKaldir.click();
        return this;
    }


    public GelenEvraklarPage raporAl() throws InterruptedException {
        btnRaporAl.click();
        return this;
    }

    public GelenEvraklarPage evrakGoster() throws InterruptedException {
        btnEvrakGoster.click();
        return this;
    }

    public GelenEvraklarPage havaleYap() throws InterruptedException {
        btnHavaleYap.click();
        return this;
    }

    public GelenEvraklarPage tebligEt() throws InterruptedException {
        btnTebligEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEt() throws InterruptedException {
        btnIadeEt.click();
        return this;
    }

    public GelenEvraklarPage cevapYaz() throws InterruptedException {
        btnCevapYaz.click();
        return this;
    }

    public GelenEvraklarPage evrakKapat() throws InterruptedException {
        btnEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKisiselKlasorlerSec(boolean secilen) throws InterruptedException {
        chkEvrakKapatKisiselKlasorler.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatEvrakKapat() throws InterruptedException {
        btnEvrakKapatEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaOnayinaSun() throws InterruptedException {
        btnEvrakKapatKapatmaOnayinaSun.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(String text) throws InterruptedException {
        txtEvrakKapatOnayAkisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(Boolean secilen) throws InterruptedException {
        txtEvrakKapatOnayAkisi.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatNotDoldur(String text) throws InterruptedException {
        txtEvrakKapatNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatKaldirilacakKlasorlerDoldur(String text) throws InterruptedException {
        txtEvrakKapatKaldirilacakKlasorler.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaTipiSec(String value) throws InterruptedException {
        cmbEvrakKapatKapatmaTipi.selectOption(value);
        return this;
    }

    public GelenEvraklarPage paylas() throws InterruptedException {
        btnPaylas.click();
        return this;
    }

}
