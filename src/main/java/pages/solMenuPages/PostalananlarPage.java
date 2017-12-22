package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;
import java.lang.String;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class PostalananlarPage extends MainPage {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement dateBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement chkKepIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:keplePostalananlarCheckbox_input"));
    SelenideElement chkMedasIlePostalananlar = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:medaslaPostalananlarCheckbox_input"));
    SelenideElement chkPostaladiklarim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:postaladiklarimCheckbox_input"));
    SelenideElement tblEvrakSec = $(By.id("mainInboxForm:inboxDataTable:1:evrakTable"));
    SelenideElement  tblPostalananlartbl =  $(By.id("mainInboxForm:inboxDataTable_data"));

    SelenideElement tblEvrakDetayPanel = $(By.id("mainPreviewForm:evrakDetayPanelGrid"));
    //ElementsCollection  tblPostalananlartbl =  $$("tbody[id='mainInboxForm:inboxDataTable_data']");
    //Hüseyin
    ElementsCollection tablePostalananlar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPostaDetayi = $x("//span[text() = 'Posta Detayı']/../../..//button");

    SelenideElement btnGuncelle = $(By.id("mainPreviewForm:j_idt18728:0:j_idt18764"));
    SelenideElement txtPosta = $(By.id("mainPreviewForm:j_idt18803"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:j_idt18806"));
    SelenideElement btnKaydet = $(By.id("mainPreviewForm:j_idt18809"));
    SelenideElement btnTarihGuncelle = $(By.id("mainPreviewForm:tebligatMazbatasiTarihiId_input"));
    ElementsCollection dlgPostaGuncelle = $$("table[id='mainPreviewForm:postaGuncellePanel']");
    @Step("Postalananlar sayfası aç")
    public PostalananlarPage openPage() throws  InterruptedException {
        solMenu(SolMenuData.BirimEvraklari.Postalananlar);
        Thread.sleep(1500);
        return this;
    }

    @Step("Filtrele Seç")
    public PostalananlarPage filtreSec(String text) throws InterruptedException {
        cmbFiltre.selectOption(text);
        return this;
    }
    @Step("Sayfada ara doldur")
    public PostalananlarPage sayfadaAraDoldur(String text) throws InterruptedException {
        txtSayfadaAra.setValue(text);
        return this;
    }
    @Step("Başlangıç tarihi doldur")
    public PostalananlarPage baslangicTarihiDoldur(String text) throws InterruptedException {
        dateBaslangicTarihi.setValue(text);
        return this;
    }
    @Step("Bitiş tarihi doldur")
    public PostalananlarPage bitisTarihiDoldur(String text) throws InterruptedException {
        dateBitisTarihi.setValue(text);
        return this;
    }

    @Step("Kep ile PostalananlarPage seç")
    public PostalananlarPage kepIlePostalananlarSec(boolean text) throws InterruptedException {
        chkKepIlePostalananlar.setSelected(text);
        return this;
    }

    @Step("Medas ile PostalananlarPage seç")
    public PostalananlarPage medasIlePostalananlarSec(boolean text) throws InterruptedException {
        chkMedasIlePostalananlar.setSelected(text);
        return this;
    }
    @Step("Postaladıklarım seç")
    public PostalananlarPage postaladiklarimSec() throws InterruptedException {
        tblEvrakSec.click();
        return this;
    }

    @Step("Evrak seç.")
    public PostalananlarPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        tablePostalananlar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .filterBy(Condition.text("Evrak Tarihi: " + evrakTarihi))
                .filterBy(Condition.text(no))
                .first()
                .click();

        return this;
    }

    @Step("Posta Detayı na tıkla")
    public PostalananlarPage postaDetayiTikla(){
        btnPostaDetayi.click();
        return this;
    }

    @Step("Tablodan Evrak Seçimi")
    public PostalananlarPage tablodanSecim() throws InterruptedException {

        solMenu(SolMenuData.BirimEvraklari.Postalananlar);

        Thread.sleep(2000);
        tablePostalananlar.first().click();

        return this;
    }

    @Step("GonderimGuncelleme")
    public PostalananlarPage btnGuncelle() {

        btnGuncelle.click();
        return this;
    }

    @Step("Tarih Guncelle")
    public PostalananlarPage btnTarihGuncelle(String text) {
        btnTarihGuncelle.click();
        btnTarihGuncelle.setValue(text);

    return this;
    }

    @Step("Posta Kodu Güncelle")
    public PostalananlarPage btnPostakoduGuncelle(String txt) {
        txtPosta.setValue(txt);
return this;
    }
    @Step("Açıklama Güncelle")
    public PostalananlarPage txtAciklama (String txt) {
        txtAciklama.setValue(txt);

        return this;

    }

    @Step("Guncelleme Kaydet")
    public PostalananlarPage btnKaydet () {
        btnKaydet.click();

        return this;
    }

    @Step("Evrak Detay Panel Sayıyı çekme")
    public PostalananlarPage detayEvrakSayisi () {

        String txt =  $x("//tbody/tr[3]/td[3]/label").getAttribute("outerText");

        System.out.println(txt);
        return this;
    }


    /**
     * @return
     */
   public String evSay() {

        return $x("//tbody/tr[3]/td[3]/label").getAttribute("outerText");
   }
}
