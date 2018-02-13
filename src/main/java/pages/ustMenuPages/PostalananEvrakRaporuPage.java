package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

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
    SelenideElement btnPostayalanAltBirim = $x("//*[@id='postalananEvrakRaporuForm:altBirimDahilId']");
    SelenideElement btnPostaSahibiAltBirim = $x("//*[@id='postalananEvrakRaporuForm:altBirimSahibiDahilId']");
    SelenideElement fromEvrakRapor = $x("//*[@id='postalananEvrakRaporuForm']");
    BelgenetElement cmbEvrakSahibi = comboLov("input[id$='postalananEvrakRaporuForm:sahibiBirimLov_id:LovText']");
    BelgenetElement cmbPostalananyer = comboLov("input[id$='postalananEvrakRaporuForm:postalananYerLov:LovText']");
    SelenideElement cmbPostaSekli = $x("//*[@id='postalananEvrakRaporuForm:postaSekliId']");
    SelenideElement cmbPostaTipi = $x("//*[@id='postalananEvrakRaporuForm:postaTipiMenu']");
    SelenideElement txtPostaAciklama = $x("//*[@id='postalananEvrakRaporuForm:aciklamaText']");
    BelgenetElement cmbPostalayanAdi = comboLov("input[id$='postalananEvrakRaporuForm:postalayanKullaniciLov:LovText']");
    SelenideElement btnPostaladiklarimcheck = $x("//*[@id='postalananEvrakRaporuForm:postaladiklarimCheckbox']");

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

    @Step("Posta açıklama alanının güncel hali ile geldiği görülür")
    public PostalananEvrakRaporuPage sonucKarsilastirma() {
        tblSorgulamaSonuc.get(0);
        String innertext = tblSorgulamaSonuc.get(0).getAttribute("innerText");
        System.out.println(innertext);
        return this;
    }
    @Step("Ağırlık ve Tutar kontrolü")
    public PostalananEvrakRaporuPage agirliktutarktrl () {
        SelenideElement tblagirlik = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[1]/td[11]/div");
        SelenideElement tbltutar = $x("//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[1]/td[12]/div");
        tblagirlik.getText();
        tbltutar.getText();
        return this;
    }
    @Step("Sorgulama sonucu gelen postalanan evrak sayısı kadar Postalanan yer - gecmiş detay - etiket buton kontrolü")
    public PostalananEvrakRaporuPage evrakSayiGecmisDetaybtnktrl () {
        String SchildElementCount;
        SchildElementCount = sorguTablosu.getAttribute("childElementCount");
        int childElementCount = Integer.parseInt(SchildElementCount);
        System.out.println(childElementCount);

        for (int i = 0; i < childElementCount; i++) {

            //*[@id="postalananEvrakRaporuForm:postalananEvrakDataTable:0:evrakGecmisiId"]/span[1]
            String paramEvrakGecmisiID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGecmisiId']/span[1]";
            String paramEvrakDetayID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGosterButton']/span[1]";
            String paramEvrakEtiketBastir = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[" + String.valueOf(i + 1) + "]/td[16]/div/button";

            SelenideElement EvrakGecmisiID = $x(paramEvrakGecmisiID);
            SelenideElement EvrakDetayID = $x(paramEvrakDetayID);
            SelenideElement EvrakEtiketBastir = $x(paramEvrakEtiketBastir);

        }
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
    @Step("Icerik Evrak No kontrol")
    public PostalananEvrakRaporuPage chkEvrakNobtn() {
        SelenideElement EvrkNoBtn = $x("//*[@id='windowReadOnlyForm:evrakBilgileriList:0:evrakNoPanelGrid']/tbody/tr/td[3]/div");
        EvrkNoBtn.exists();
        return this;
    }

    @Step("Evrak İçerik Kapat")
    public PostalananEvrakRaporuPage evrakIcerikKapat() {
        btnEvrakIcerikKapat.click();
        btnEvrakDialogKapat.click();
        return this;
    }

    @Step("Posta açıklama alanı doldur\"{postaAciklama}\"")
    public PostalananEvrakRaporuPage txtPostaAciklama(String postaAciklama) {
        txtPostaAciklama.setValue(postaAciklama);
        return this;
    }
    @Step("Posta açıklama alanı temizle")
    public PostalananEvrakRaporuPage clearPostaAciklamaAlani () {
        txtPostaAciklama.clear();
        return this;
    }

    @Step("Postalayan adi doldur\"{postalayanAdi}\"")
    public PostalananEvrakRaporuPage cmbPostalayanadi(String postalayanAdi) {
        cmbPostalayanAdi.selectLov(postalayanAdi);
        return this;
    }
    @Step("Postalayan adi alanını temizle")
    public PostalananEvrakRaporuPage cmbClearPostalayanAdi () {
        cmbPostalayanAdi.clearAllSelectedItems();
        return this;
    }

    @Step("Postaladiklarim checkbox")
    public PostalananEvrakRaporuPage chkboxPostaladiklarim() {
        btnPostaladiklarimcheck.click();
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

    @Step("Dosyanın indirilme kontrolü")
    public PostalananEvrakRaporuPage ktrlDosyaindirme () {
        String downloadpath =  getDownloadPath();
        System.out.println(downloadpath);
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
    public PostalananEvrakRaporuPage btnPostalayanAltBirim() {
        btnPostayalanAltBirim.click();
        return this;
    }

    @Step("Posta Sahibi Alt birimi dahil et")
    public PostalananEvrakRaporuPage btnPostaSahibiAltbirim() {
        btnPostaSahibiAltBirim.click();
        return this;
    }

    @Step("Postalanan Evrak rapor sayfası form tıklama")
    public PostalananEvrakRaporuPage evrakRaporForm() {
        fromEvrakRapor.click();
        return this;
    }
    @Step("Rapor al işlemi başarılıdır kontrülü")
    public PostalananEvrakRaporuPage raporalbasarilidir () {
        islemMesaji().isBasarili();
        return this;
    }

    @Step("Evrak Sahibi Seçimi \"{evrakSahibi}\" ")
    public PostalananEvrakRaporuPage cmbEvrakSahibi(String evrakSahibi) {
        cmbEvrakSahibi.selectLov(evrakSahibi);
        return this;
    }

    @Step("Evrak Sahibi \"{evrakSahibi}\" ile sorgulama ve kontrol ")
    public PostalananEvrakRaporuPage evrakSahibiKontrol(String evrakSahibi) {
        String SchildElementCount;
        SchildElementCount = sorguTablosu.getAttribute("childElementCount");
        int childElemCount = Integer.parseInt(SchildElementCount);
         int i = 1;
            String paramEvrakSahibi = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[" + String.valueOf(1) + "]/td[7]/div";
            SelenideElement evSahibiColumn = $x(paramEvrakSahibi);
            String evraksahibicol = evSahibiColumn.getAttribute("innerText");
            Assert.assertEquals(evrakSahibi, evraksahibicol);

        return this;
    }
     @Step("Evrak Sahibi Seçimi Temizleme")
     public PostalananEvrakRaporuPage cmbClearEvrakSahibi () {
        cmbEvrakSahibi.clearAllSelectedItems();
        return this;
     }

    @Step("Postalanan yer \"{postalananYer}\"seçimi")
    public PostalananEvrakRaporuPage cmbPostalananYerSecimi(String postalananYer) {
        cmbPostalananyer.selectLov(postalananYer);
        return this;
    }
    @Step("Postalanan yer seçimini temizleme")
    public PostalananEvrakRaporuPage cmbPostalananYerSecimiTemizle () {
        cmbPostalananyer.clearAllSelectedItems();
        return this;
    }

    @Step("Postalanan yer \"{postalananyer}\" ile sorgulama ve kontrol ")
    public PostalananEvrakRaporuPage postalananyerKontrol(String postalananyer) {
        String SchildElementCount;
        SchildElementCount = sorguTablosu.getAttribute("childElementCount");
        int childElemCount = Integer.parseInt(SchildElementCount);
        for (int i = 0; i < childElemCount; i++) {
            String parampostalananyer = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[" + String.valueOf(i + 1) + "]/td[6]/div";
            SelenideElement postalananyerColumn = $x(parampostalananyer);
            String postalananyercol = postalananyerColumn.getAttribute("innerText");
            postalananyercol = postalananyercol.replaceAll("\\s+","");

            Assert.assertEquals(postalananyercol ,postalananyer);

        }
        return this;
    }

    @Step("Posta sekli seçimi \"{postaSekli}\" ")
    public PostalananEvrakRaporuPage cmbpostaSeklisecimi(String postaSekli) {
        cmbPostaSekli.selectOption(postaSekli);
        return this;
    }

    @Step("Posta tipi seçimi \"{postaTipi}\" ")
    public PostalananEvrakRaporuPage cmbPostaTipisec(String postaTipi) {
        cmbPostaTipi.selectOption(postaTipi);
        return this;

    }

    @Step("Etiket İçerik tarih ve yer kontrol")
    public PostalananEvrakRaporuPage txtktrlEtiketIcerik () {

        SelenideElement txtktrl = $x("//*[@id='postalananEvrakRaporuForm:etiketMetinID']");
        txtktrl.exists();
        txtktrl.getText();
        System.out.println(txtktrl.getText());
        return this;
    }

    /*
    Kod Dinamik olarak gelen sonuç sayısına ve tipine göre locator değişimi ve kontrolünü yapar
     */
    @Step("Sorgulama sonucu gelen sonuçların hepsinin evrak geçmiş, detay ve etiket bastır buton ve ekranlarının tek tek kontrolü")
    public PostalananEvrakRaporuPage ekranSorgulananSonucKontrol() throws InterruptedException {
        String SchildElementCount;
        SchildElementCount = sorguTablosu.getAttribute("childElementCount");
        int childElementCount = Integer.parseInt(SchildElementCount);
        System.out.println(childElementCount);
        int i = 0;
        String paramEvrakGecmisiID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGecmisiId']/span[1]";
        String paramEvrakDetayID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGosterButton']/span[1]";
        String paramEvrakEtiketBastir = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[" + String.valueOf(i + 1) + "]/td[16]/div/button";

        SelenideElement EvrakGecmisiID = $x(paramEvrakGecmisiID);
        SelenideElement EvrakDetayID = $x(paramEvrakDetayID);
        SelenideElement EvrakEtiketBastir = $x(paramEvrakEtiketBastir);

        EvrakGecmisiID.click();
        Thread.sleep(1000);
        evrakGecmisiKapat();
        Thread.sleep(1000);
        EvrakDetayID.click();
        Thread.sleep(1000);
        evrakIcerikKapat();
        Thread.sleep(1000);
        EvrakEtiketBastir.click();
        Thread.sleep(1000);
        etiketBastirPopupKapat();
       /* for (int i = 0; i < childElementCount; i++) {

            //*[@id="postalananEvrakRaporuForm:postalananEvrakDataTable:0:evrakGecmisiId"]/span[1]
            String paramEvrakGecmisiID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGecmisiId']/span[1]";
            String paramEvrakDetayID = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable:" + String.valueOf(i) + ":evrakGosterButton']/span[1]";
            String paramEvrakEtiketBastir = "//*[@id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']/tr[" + String.valueOf(i + 1) + "]/td[16]/div/button";

            SelenideElement EvrakGecmisiID = $x(paramEvrakGecmisiID);
            SelenideElement EvrakDetayID = $x(paramEvrakDetayID);
            SelenideElement EvrakEtiketBastir = $x(paramEvrakEtiketBastir);

            EvrakGecmisiID.click();
            Thread.sleep(1000);
            evrakGecmisiKapat();
            Thread.sleep(1000);
            EvrakDetayID.click();
            Thread.sleep(1000);
            evrakIcerikKapat();
            Thread.sleep(1000);
            EvrakEtiketBastir.click();
            Thread.sleep(1000);
            etiketBastirPopupKapat();
        }*/
        return this;
    }

    @Step("Excel ve Tablo karşılaştırma")
    public PostalananEvrakRaporuPage excelTabloKars() {

        
    }
}
