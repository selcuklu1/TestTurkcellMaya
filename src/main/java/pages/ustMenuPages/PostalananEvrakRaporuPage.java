package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class PostalananEvrakRaporuPage extends MainPage {

    SelenideElement txtEvrakSayisi = $( By.id("postalananEvrakRaporuForm:evrakSayiTextId"));
    SelenideElement btnPostaBaslangicTarihi = $(By.id("postalananEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("postalananEvrakRaporuForm:sorgulaButton"));
    ElementsCollection tblSorgulamaSonuc = $$("tbody[id='postalananEvrakRaporuForm:postalananEvrakDataTable_data']");
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


    public PostalananEvrakRaporuPage openPage() {

        ustMenu("Postalanan Evrak Raporu");
        return this;
}

public PostalananEvrakRaporuPage evrakSayisi (String txt) {
    txtEvrakSayisi.setValue(txt);
    return this;
}

public PostalananEvrakRaporuPage postaAramaBaslangicTarihi (String txt) {

    btnPostaBaslangicTarihi.setValue(txt);
    return this;
}

public PostalananEvrakRaporuPage postaSorgulama() {

    btnSorgula.click();
    return this;

}

public PostalananEvrakRaporuPage sonucKarsilastirma () {

    tblSorgulamaSonuc.get(0);

    return this;
}

public PostalananEvrakRaporuPage ilkEvrakGecmisi() {
    btnIlkEvrakGecmisi.click();
    return this;
}
public PostalananEvrakRaporuPage evrakGecmisiKapat() {
        btnEvrakGecmisiKapat.click();
        return this;
}
public PostalananEvrakRaporuPage evrakIcerikGoster() {
        btnEvrakGoster.click();
        return this;
}

public PostalananEvrakRaporuPage evrakIcerikKapat() {
         btnEvrakIcerikKapat.click();
         btnEvrakDialogKapat.click();
         return this;
}
public PostalananEvrakRaporuPage etiketBastir() {
         btnEtiketBastir.click();
         return this;
}
public PostalananEvrakRaporuPage etiketBastirPopupKapat() {

    btnPopupKapat.click();
    return this;
}
public PostalananEvrakRaporuPage btnExcel () {
         //btnExcel.click();
    clickJs(btnExcel);
         return this;
}
public PostalananEvrakRaporuPage btnPdf() {
    clickJs(btnPdfBastir);
    return this;
}
public PostalananEvrakRaporuPage btnEtiket() {
    btnEtiketYazdir.click();
    return this;
}

public PostalananEvrakRaporuPage popupEtiketBastirma (String satir , String kolon) {
        etiketBastirPopupIlkSatir.setValue(satir);
        etiketBastirPopupIlkKolon.setValue(kolon);
        etiketBastirPopupTamam.click();
        return this;
}
}
