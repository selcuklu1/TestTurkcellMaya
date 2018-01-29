package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.*;

public class PostalananEvrakRaporuPage extends MainPage {

    SelenideElement txtEvrakSayisi = $(By.id("postalananEvrakRaporuForm:evrakSayiTextId"));
    SelenideElement btnPostaBaslangicTarihi = $(By.id("postalananEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("postalananEvrakRaporuForm:sorgulaButton"));
    ElementsCollection tblSorgulamaSonuc = $$("tbody[id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']");
    SelenideElement sorguTablosu = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']");
    SelenideElement btnIlkEvrakGecmisi = $(By.id("postalananEvrakRaporuForm:postalananEvrakDataTable:0:evrakGecmisiId"));
    SelenideElement btnEvrakGecmisiKapat = $x("//*[@id='postalananEvrakRaporHareketGecmisiForm:postalananEvrakRaporhareketGecmisiDataTableDialogId']/div[1]/a/span");
    SelenideElement btnEvrakGoster = $(By.id("postalananEvrakRaporuForm:postalananEvrakDataTable:0:evrakGosterButton"));
    SelenideElement btnEvrakIcerikKapat = $x("//*[@id='windowReadOnlyEvrakDialog']/div[1]/a[1]/span");
    SelenideElement btnEvrakDialogKapat = $(By.id("kapatButton"));
    SelenideElement btnExcel = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable']/table/thead/tr[1]/th/button[5]");
    SelenideElement btnEtiketBastir = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[1]/td[16]/div/button");
    SelenideElement btnPdfBastir = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable']/table/thead/tr[1]/th/button[3]");
    SelenideElement btnEtiketYazdir = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable']/table/thead/tr[1]/th/button");
    SelenideElement btnPopupKapat = $x("//*[@id='postalananEvrakRaporuForm:showAppletContainer']/div/div[1]/a");
    SelenideElement etiketBastirPopupIlkSatir = $x("//*[@id='etiketBastirDaialog']/div[2]/form/center/table/tbody/tr[1]/td/input");
    SelenideElement etiketBastirPopupIlkKolon = $x("//*[@id='etiketBastirDaialog']/div[2]/form/center/table/tbody/tr[2]/td/input");
    SelenideElement etiketBastirPopupTamam = $x("//*[@id='etiketBastirDaialog']/div[2]/form/center/table/tbody/tr[3]/td/button");
    SelenideElement btnPostayalanAltBirim = $x("//*[@id='postalananEvrakRaporuForm:altBirimDahilId']/div[2]/span");
    SelenideElement btnPostaSahibiAltBirim = $x("//*[@id='postalananEvrakRaporuForm:altBirimSahibiDahilId']/div[2]/span");
    @Step("Postalanan Evrak Raporu sayfasını aç")
    public PostalananEvrakRaporuPage openPage() {

        ustMenu(UstMenuData.Raporlar.PostalananEvrakRaporu);
        return this;
    }

    @Step("Evrak Sayı numarası girişi")
    public PostalananEvrakRaporuPage evrakSayisi(String eSayi) {
        txtEvrakSayisi.setValue(eSayi);
        return this;
    }

    @Step("Posta Arama Başlangıç Tarihi girişi")
    public PostalananEvrakRaporuPage postaAramaBaslangicTarihi(String date) {
        btnPostaBaslangicTarihi.setValue(date);
        return this;
    }

    @Step("Posta Sorgulama butonuna tıkla")
    public PostalananEvrakRaporuPage postaSorgulama() {
        btnSorgula.click();
        return this;
    }

    @Step("Çıkan sonuçları kontrol et")
    public PostalananEvrakRaporuPage sonucKarsilastirma() {
        tblSorgulamaSonuc.get(0);
        return this;
    }

    @Step("Evrak Geçmişi butonuna tıkla")
    public PostalananEvrakRaporuPage ilkEvrakGecmisi() {
        btnIlkEvrakGecmisi.click();
        return this;
    }

    @Step("Evrak Geçmişini kapat")
    public PostalananEvrakRaporuPage evrakGecmisiKapat() {
        btnEvrakGecmisiKapat.click();
        return this;
    }

    @Step("Evrak İçerik Gösterimi")
    public PostalananEvrakRaporuPage evrakIcerikGoster() {
        btnEvrakGoster.click();
        return this;
    }

    @Step("Evrak İçerik Kapat")
    public PostalananEvrakRaporuPage evrakIcerikKapat() {
        btnEvrakIcerikKapat.click();
        btnEvrakDialogKapat.click();
        return this;
    }

    @Step("Etiket Bastir")
    public PostalananEvrakRaporuPage etiketBastir() {
        btnEtiketBastir.click();
        return this;
    }

    @Step("Etiket Bastir Popup Kapatma")
    public PostalananEvrakRaporuPage etiketBastirPopupKapat() {
        btnPopupKapat.click();
        return this;
    }

    @Step("Excele Tıkla ve indir")
    public PostalananEvrakRaporuPage btnExcel() {
        //btnExcel.click();
        clickJs(btnExcel);
        return this;
    }

    @Step("Pdfe Tıkla ve indir")
    public PostalananEvrakRaporuPage btnPdf() {
        clickJs(btnPdfBastir);
        return this;
    }

    @Step("Etiket butonuna tıkla")
    public PostalananEvrakRaporuPage btnEtiket() {
        btnEtiketYazdir.click();
        return this;
    }

    @Step("Etiket Bastirma Popup Satir ve Kolon girişi")
    public PostalananEvrakRaporuPage popupEtiketBastirma(String satir, String kolon) {
        etiketBastirPopupIlkSatir.setValue(satir);
        etiketBastirPopupIlkKolon.setValue(kolon);
        etiketBastirPopupTamam.click();
        return this;
    }

    @Step("Postalayan Alt birimi dahil et")
    public PostalananEvrakRaporuPage btnPostalayanAltBirim () {
        btnPostayalanAltBirim.click();
        return this;
    }
    @Step("Posta Sahibi Alt birimi dahil et")
    public PostalananEvrakRaporuPage btnPostaSahibiAltbirim () {
        btnPostaSahibiAltBirim.click();
        return this;
    }

    @Step("Sorgulama sonucu gelen sonuçların evrak geçmiş, detay ve etiket bastır ekranlarının tek tek kontrolü")
    public PostalananEvrakRaporuPage ekranSorgulananSonucKontrol() {
        String SchildElementCount;
        SchildElementCount = sorguTablosu.getAttribute("childElementCount");
        int childElementCount = Integer.parseInt(SchildElementCount);
        for (int i = 0 ; i < childElementCount ; i++) {
            //*[@id="postalananEvrakRaporuForm:postalananEvrakDataTable:0:evrakGecmisiId"]/span[1]
            String paramEvrakGecmisiID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:"+String.valueOf(i)+":evrakGecmisiId']/span[1]";
            String paramEvrakDetayID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:"+String.valueOf(i)+":evrakGosterButton']/span[1]";

        }
        return this;
    }
}
