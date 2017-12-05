package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;

public class PostalanacakEvraklarPage extends MainPage {

    SelenideElement txtBirim = $(By.id("mainInboxForm:birimInboxFilterLov:LovText"));
    SelenideElement btnListele = $(By.id("mainInboxForm:birimEvraklariListeleButton"));

    // Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement btnGeldigiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:geldigiYerFilterOpenDialogButton"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt368_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt373_input"));

    SelenideElement tblEvrakSec = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnEvrakPostala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement tblSec =$(By.xpath("//tbody[@id='mainInboxForm:inboxDataTable_data']/tr[@data-ri='3']"));
    SelenideElement btnPostaDetayi3 = $(By.id("mainInboxForm:inboxDataTable:3:postaDagitimGosterButton"));
    SelenideElement btnGonderilenYerDetay = $(By.id("mainPreviewForm:dataTableId:0:j_idt20289"));
    BelgenetElement cmbGidisSekli = comboBox(By.id("mainPreviewForm:dataTableId:0:j_idt19199_label"));
    SelenideElement txtGramaj = $(By.id("mainPreviewForm:dataTableId:0:postaGramaj"));
    SelenideElement btnTamam = $(By.id("mainPreviewForm:tutarDialogButtonId"));
    SelenideElement txtTutar = $(By.id("mainPreviewForm:dataTableId:0:dpPostaTutarId"));
    SelenideElement btnKaydet = $(By.id("mainPreviewForm:dagitimPlaniDetayKaydetViewDialog"));
    SelenideElement btnHesapla = $(By.id("mainPreviewForm:dataTableId:0:j_idt19237"));
    SelenideElement epostaTxt = $(By.id("mainPreviewForm:dataTableId:0:j_idt19224"));
    SelenideElement epostaAciklama = $(By.id("mainPreviewForm:dataTableId:0:j_idt19228"));
    SelenideElement PostalanacakEvrakYazdir = $(By.id("mainPreviewForm:dataTableId:0:j_idt19266"));
    SelenideElement PostalanacakEvrakOrijinalYazdir = $(By.id("mainPreviewForm:dataTableId:0:j_idt19267"));
    SelenideElement btnPopupYazdir = $(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogYazdir"));
    SelenideElement btnPopupOrjYazdir = $(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri:0:evrakDetayiViewDialogOrjYazdir"));
    SelenideElement btnPopupHesaplaTamam = $(By.id("mainPreviewForm:tutarDialogButtonId"));

    @Step("Postalanacak Evraklar sayfası aç")
    public PostalanacakEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar);
        return this;
    }
    @Step("Evrak Postala")
    public PostalanacakEvraklarPage evrakPostala() {
        btnEvrakPostala.click();
        return this;
    }
    @Step("Evrak Seçilir")
    public PostalanacakEvraklarPage evrakSec() {
        tblEvrakSec.click();
        return this;
    }

    @Step("Birim Doldur")
    public PostalanacakEvraklarPage birimDoldur(String text) throws InterruptedException {
        txtBirim.setValue(text);
        return this;
    }
    @Step("Listele")
    public PostalanacakEvraklarPage listele() throws InterruptedException {
        btnListele.click();
        return this;
    }
    @Step("Filtrele Seç")
    public PostalanacakEvraklarPage filtreSec(String value) throws InterruptedException {
        cmbFiltre.selectOption(value);
        return this;
    }
    @Step("Sayfada ara doldur")
    public PostalanacakEvraklarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }
    @Step("Geldiği yer tıkla")
    public PostalanacakEvraklarPage geldigiYer() throws InterruptedException {
        btnGeldigiYer.click();
        return this;
    }
    @Step("Başlangıç tarihi doldur")
    public PostalanacakEvraklarPage baslangicTarihiDoldur(String text) throws InterruptedException {
        dateBaslangicTarihi.setValue(text);
        return this;
    }
    @Step("Bitiş tarihi doldur")
    public PostalanacakEvraklarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }
    @Step("tablodan seç")
    public PostalanacakEvraklarPage tablodanSec(){
        tblSec.click();
        return this;
    }
    @Step("Posta Detayı tıkla")
    public PostalanacakEvraklarPage postaDetayi(){
        btnPostaDetayi3.click();
        return this;
    }
    @Step("Gönderilen Yer Detay tıkla")
    public PostalanacakEvraklarPage gonderilenYerDetay(){
        btnGonderilenYerDetay.click();
        return this;
    }
    @Step("Gidis Sekli Seç")
    public PostalanacakEvraklarPage gidisSekli(String gidisSekli){
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }
    @Step("Gramaj doldur")
    public PostalanacakEvraklarPage gramajDoldur(String gramaj){
        txtGramaj.sendKeys(gramaj);
        return this;
    }
    @Step("Hesapla tıkla")
    public PostalanacakEvraklarPage hesapla() throws InterruptedException {
        btnHesapla.click();
        Thread.sleep(1000);
        btnPopupHesaplaTamam.click();
        return this;
    }
    @Step("Tamam tıkla")
    public PostalanacakEvraklarPage tamam(){
        btnTamam.click();
        return this;
    }
    @Step("Tutar doldur")
    public PostalanacakEvraklarPage tutarDoldur(String tutar){
        txtTutar.sendKeys(tutar);
        return this;
    }

    @Step("Postalanacak Evrak Yazdır")
    public PostalanacakEvraklarPage postalanacakEvrakYaz () {

        PostalanacakEvrakYazdir.click();
        return this;

    }

    public PostalanacakEvraklarPage postalanacakEvrakOrjYaz() throws InterruptedException {

        PostalanacakEvrakOrijinalYazdir.click();
        Thread.sleep(1000);
        btnPopupOrjYazdir.click();
        SelenideElement popupOrj = $(By.id("postaDetayYazdirForm:dlgPostaDetayYazdir"));
        popupOrj.findElement(By.className("ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all")).click();
        return this;
    }

    public PostalanacakEvraklarPage PostalacanakEposta (String eposta) {

        epostaTxt.setValue(eposta);
        return this;
    }

    public PostalanacakEvraklarPage PostalamaAciklama (String text) {

        epostaAciklama.setValue(text);
        return this;
    }

    public PostalanacakEvraklarPage PopupPostaYazdirmaKapat() throws InterruptedException {
        Thread.sleep(2000);

        SelenideElement popupEvrakYazdir = $(By.id("postaDetayYazdirForm:dlgPostaDetayYazdir"));

        popupEvrakYazdir.getWrappedElement().isDisplayed();

        return this;
    }

    public PostalanacakEvraklarPage PopupPostalanacakEvrakYazdir() throws InterruptedException {
        Thread.sleep(5000);
        btnPopupYazdir.click();
        Thread.sleep(2000);
        return this;
    }
}
