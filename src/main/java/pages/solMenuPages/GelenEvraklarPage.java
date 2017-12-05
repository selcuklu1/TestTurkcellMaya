package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GelenEvraklarPage extends MainPage {

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement tblEvraklar = $("table[id='mainInboxForm:inboxDataTable:0:evrakTable'] tr:nth-child(3)");
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
    SelenideElement btnTabHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));

    //Havale Yap Alt Yapı
    SelenideElement btnHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));
    SelenideElement treeHavaleYapBirim = $(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovTexts"));
    SelenideElement treeHavaleYapKisi = $(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement treeHavaleYapKullaniciListesi = $(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement treeHavaleYapOnaylanacakKisi = comboLov("[id^='mainPreviewForm:onaylayacakKisiLov:LovText']");
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
    SelenideElement btnPaylas = $(By.xpath("//button/span[contains(@class, 'evrakPaylas')]"));
    SelenideElement txtPaylasKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    BelgenetElement txtPaylasilanKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasIcPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    SelenideElement evrakSec = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));


    public GelenEvraklarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.GelenEvraklar);
        return this;
    }

    @Step("Evrak seç")
    public GelenEvraklarPage evrakSec() {
        evrakSec.click();
        return this;
    }

    public GelenEvraklarPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi, String evrakTarihi, String no) {
        tableEvraklar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Geldiği Yer: " + geldigiYer))
                .filterBy(Condition.text("Kayıt Tarihi / Sayı: " + kayitTarihiSayi))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text("No: " + no))
                .get(0)
                .click();
        return this;
    }

    @Step("Havale yap")
    public GelenEvraklarPage tabHavaleYap() {
        btnTabHavaleYap.click();
        return this;
    }

    public GelenEvraklarPage iadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEtDosyaEkle() {
        btnIadeEtDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage iadeEtNotInputDoldur(String text) {
        txtIadeEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtTebligEt() {
        btnTebligEtTebligEt.click();
        return this;
    }

    public GelenEvraklarPage tebligEtNotInputDoldur(String text) {
        txtTebligEtNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtKullaniciListesiDoldur(String text) {
        txtTebligEtKullaniciListesi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage tebligEtKisiInputDoldur(String text) {
        txtTebligEtKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapHavaleOnayinaGonder() {
        btnHavaleYapHavaleOnayinaGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapGonder() {
        btnHavaleYapGonder.click();
        return this;
    }

    public GelenEvraklarPage havaleYapEvrakOnayliKapatChecked(Boolean secim) {
        chkHavaleYapEvrakOnayliKapat.setSelected(secim);
        return this;
    }

    public GelenEvraklarPage havaleYapIslemSuresiDoldur(String text) {
        txtHavaleYapIslemSuresi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapDosyaEkle() {
        btnHavaleYapDosyaEkle.click();
        return this;
    }

    public GelenEvraklarPage havaleYapAciklamaDoldur(String text) {
        txtHavaleYapAciklama.sendKeys(text);
        return this;
    }

    @Step("Havale onaylanacak kisi doldur")
    public GelenEvraklarPage havaleYapOnaylanacakKisiTreeDoldur(String text) {
        treeHavaleYapOnaylanacakKisi.selectLov(text);
        System.out.println("Başarı Bu selectlı geçmiştir");
        return this;
    }

    public GelenEvraklarPage havaleYapKullaniciListesiTreeDoldur(String text) {
        treeHavaleYapKullaniciListesi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapKisiTreeDoldur(String text) {
        treeHavaleYapKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage havaleYapBirimTreeDoldur(String text) {
        treeHavaleYapBirim.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasIcPaylas() {
        btnPaylasIcPaylas.click();
        return this;
    }

    public GelenEvraklarPage paylasanAciklamaDoldur(String text) {
        txtPaylasanAciklama.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiDoldur(String text) {
        txtPaylasKisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage paylasKisiSec(String kisi) {
        txtPaylasilanKisi.selectLov(kisi);
        return this;
    }

    public GelenEvraklarPage filtreleSec(String value) {
        cmbFiltrele.selectOption(value);
        return this;
    }

    public GelenEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage gidecegiYerSecinizk() {
        btnGidecegiYerSeciniz.click();
        return this;
    }

    public GelenEvraklarPage gidecegiYerTreeDoldur(String text) {
        treeGidecegiYer.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage baslangicTarihDoldur(String text) {
        dateTxtBaslangicTarih.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage bitisTarihDoldur(String text) {
        dateTxtBitisTarihi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage topluSecim() {
        btnTopluSecim.click();
        return this;
    }

    public GelenEvraklarPage topluHavale() {
        btnTopluHavale.click();
        return this;
    }

    public GelenEvraklarPage topluKlasorKaldir() {
        btnTopluKlasorKaldir.click();
        return this;
    }

    public GelenEvraklarPage raporAl() {
        btnRaporAl.click();
        return this;
    }

    public GelenEvraklarPage evrakGoster() {
        btnEvrakGoster.click();
        return this;
    }

    public GelenEvraklarPage havaleYap() {
        btnHavaleYap.click();
        return this;
    }

    public GelenEvraklarPage tebligEt() {
        btnTebligEt.click();
        return this;
    }

    public GelenEvraklarPage iadeEt() {
        btnIadeEt.click();
        return this;
    }

    public GelenEvraklarPage cevapYaz() {
        btnCevapYaz.click();
        return this;
    }

    public GelenEvraklarPage evrakKapat() {
        btnEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKisiselKlasorlerSec(boolean secilen) {
        chkEvrakKapatKisiselKlasorler.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatEvrakKapat() {
        btnEvrakKapatEvrakKapat.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaOnayinaSun() {
        btnEvrakKapatKapatmaOnayinaSun.click();
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(String text) {
        txtEvrakKapatOnayAkisi.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatOnayAkisiDoldur(Boolean secilen) {
        txtEvrakKapatOnayAkisi.setSelected(secilen);
        return this;
    }

    public GelenEvraklarPage evrakKapatNotDoldur(String text) {
        txtEvrakKapatNot.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatKaldirilacakKlasorlerDoldur(String text) {
        txtEvrakKapatKaldirilacakKlasorler.sendKeys(text);
        return this;
    }

    public GelenEvraklarPage evrakKapatKapatmaTipiSec(String value) {
        cmbEvrakKapatKapatmaTipi.selectOption(value);
        return this;
    }

    public GelenEvraklarPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("")
    public String tablodanEvrakNoAl(int adet) {
        String text="";
        SelenideElement tblEvraklar = $("table[id='mainInboxForm:inboxDataTable:"+0+":evrakTable'] tr:nth-child(3)");
        for (int i =0; i<adet; i++){
            text = $("table[id='mainInboxForm:inboxDataTable:"+i+":evrakTable'] tr:nth-child(3)").getText();
        }
//        String text = tblEvraklar.getText();
        System.out.println(text);
        String arr1[] = text.split("/");
        String evrakNo = getIntegerInText(arr1[2]);
        return evrakNo;
    }

}
