package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.bouncycastle.jcajce.provider.asymmetric.elgamal.BCElGamalPrivateKey;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Posta Listesi" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class PostaListesiPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement txtPostaListesi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_input"));
    SelenideElement btnPostala = $(By.id("mainInboxForm:inboxDataTable:j_idt726"));
    SelenideElement tblIlkRow = $(By.xpath("//tbody[@id='mainInboxForm:inboxDataTable_data']/tr[@data-ri='0']"));
    BelgenetElement cmbGidisSekli = comboBox(By.id("mainPreviewForm:postaListesiPostaTipi_label"));
    BelgenetElement cmbGonderildigiYer = comboBox(By.id("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement txtGramaj = $(By.xpath("//*[@id='mainPreviewForm:eastLayout']//label[normalize-space(text())='Gramaj :']/../..//input"));//$(By.id("mainPreviewForm:j_idt2574"));
    SelenideElement btnHesapla = $(By.id("mainPreviewForm:j_idt2578"));
    SelenideElement txtIndirimOrani = $(By.id("mainPreviewForm:j_idt2582"));
    SelenideElement btnPostaDetayiPostola = $(By.id("mainPreviewForm:postalaButton"));
    SelenideElement lblPostaListesiAdi = $(By.xpath("//label[contains(text(), 'Posta Listesi Adı :')]"));
    SelenideElement lblBarkodNo = $(By.xpath("//label[contains(text(), 'Barkod No : ')]"));
    SelenideElement lblGonderildigiYer = $(By.xpath("//label[contains(text(), 'Gönderildiği Yer : ')]"));
    SelenideElement lblGonderildigiKurum = $(By.xpath("//label[contains(text(), 'Gönderildiği Kurum : ')]"));
    SelenideElement lblAdres = $(By.xpath("//label[contains(text(), 'Adres : ')]"));
    SelenideElement lblGidisSekli = $(By.xpath("//label[contains(text(), 'Gidiş Şekli :')]"));
//    SelenideElement lblGonderildigiYer2 = $(By.xpath("mainPreviewForm:postaListesiYurticiYurtdisi_label"));
    SelenideElement lblTutar = $(By.xpath("//label[contains(text(), 'Tutar : ')]"));
    SelenideElement txtTutar = $(By.id("mainPreviewForm:j_idt2585"));


    @Step("Posta Listesi Sayfasını aç")
    public PostaListesiPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.PostaListesi);
        return this;
    }

    @Step("Filtrele alanını aç")
    public PostaListesiPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Posta Listesi doldur")
    public PostaListesiPage postaListesiDoldur(String postaListesi) throws InterruptedException {
        txtPostaListesi.sendKeys(postaListesi);
        Thread.sleep(2000);
//        $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaListesiAdi_panel"))
//                .waitUntil(Condition.visible,1);
        txtPostaListesi.pressEnter();
        return this;
    }

    @Step("Tablodan ilk row seç")
    public PostaListesiPage tablodanIlkRowSec() {
        tblIlkRow.click();
        return this;
    }

    @Step("Posta Listesi Postala tıkla")
    public PostaListesiPage postaListesiPostala() {
        btnPostala.click();
        return this;
    }

    @Step("Gidis Sekli seç")
    public PostaListesiPage gidisSekliSec(String gidisSekli) {
        cmbGidisSekli.selectComboBox(gidisSekli);
        return this;
    }

    @Step("Gonderildiğgi Yer seç")
    public PostaListesiPage gonderildigiYerSec(String gonderildigiYer) {
        cmbGonderildigiYer.selectComboBox(gonderildigiYer);
        return this;
    }

    @Step("Gramaj doldur")
    public PostaListesiPage gramajDoldur(String gramaj) throws InterruptedException {
        txtGramaj.setValue(gramaj);
        return this;
    }

    @Step("Etiket hesapla Tıkla")
    public PostaListesiPage tutarHesapla() {
        btnHesapla.click();
        return this;
    }

    @Step("Indirim oranı doldur")
    public PostaListesiPage indirimOraniDoldur(String indirimOrani) {
        txtIndirimOrani.setValue(indirimOrani);
        return this;
    }

    @Step("Posta Detayı Postala tıkla")
    public PostaListesiPage postaDetayiPostala() {
        btnPostaDetayiPostola.click();
        return this;
    }

    @Step("Alanların Kontrolü")
    public PostaListesiPage alanKontrolu() {
        lblPostaListesiAdi.isDisplayed();
        lblBarkodNo.isDisplayed();
        lblGonderildigiYer.isDisplayed();
        lblGonderildigiKurum.isDisplayed();
        lblAdres.isDisplayed();
        lblGidisSekli.isDisplayed();
//        lblGonderildigiYer2.isDisplayed();
        lblTutar.isDisplayed();
        return this;
    }
    @Step("Tutar doldur")
    public PostaListesiPage tutarDoldur(String tutar){
        txtTutar.clear();
        txtTutar.sendKeys(tutar);
        return this;
    }
}

