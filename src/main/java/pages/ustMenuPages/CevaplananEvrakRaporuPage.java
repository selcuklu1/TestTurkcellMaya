package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "CevaplananEvrakRaporuPage" konulu senaryoları içerir
 * Yazan: Sezai Çelik
 ****************************************************/
public class CevaplananEvrakRaporuPage extends MainPage {

    BelgenetElement cmbBirim = comboLov(By.id("cevaplananEvrakRaporuForm:birimLovId:LovText"));
    SelenideElement chkAltBirimEvraklariDahil = $(By.id("cevaplananEvrakRaporuForm:altBirimKlasorId"));
    BelgenetElement cmbKonuKodu = comboLov(By.id("cevaplananEvrakRaporuForm:konuKoduLov:LovText"));
    SelenideElement dateIlkTarih = $(By.id("cevaplananEvrakRaporuForm:ilkTarihCalendar_input"));
    SelenideElement dateSonTarih = $(By.id("cevaplananEvrakRaporuForm:sonTarihCalendar_input"));
    SelenideElement btnSorgula = $(By.id("cevaplananEvrakRaporuForm:sorgulaButton"));
    SelenideElement btnTemizle = $(By.id("cevaplananEvrakRaporuForm:temizleButton"));
    SelenideElement tblCevaplananEvrakFormDataTable = $(By.id("cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab"));
    SelenideElement btnEvrakGecmisi = $(By.id("cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab:0:evrakGecmisiId"));
    SelenideElement btnEvrakDetayi = $(By.id("cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab:0:evrakGosterButton"));
    SelenideElement btnSayfayiRaporla = $(By.xpath("//div[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab']/table[@role='grid']//th[@class='ui-datatable-header ui-widget-header']/button[2]"));
    SelenideElement btnTumSayfayiRaporla = $(By.xpath("//div[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab']/table[@role='grid']//th[@class='ui-datatable-header ui-widget-header']/button[1]"));

    @Step("Cevaplanan Evrak Raporu sayfasını aç")
    public CevaplananEvrakRaporuPage openPage() {
        ustMenu("Cevaplanan Evrak Raporu");
        $("#cevaplananEvrakRaporuForm").shouldBe(visible);
        return this;
    }

    @Step("Birim alani doldur")
    public CevaplananEvrakRaporuPage birimDoldur(String birim) {
        cmbBirim.selectLov(birim);
        return this;
    }

    @Step("Alt Birim Evraklari Dahil alani seç")
    public CevaplananEvrakRaporuPage altBirimEvraklariDahilSec(Boolean secim) {
        chkAltBirimEvraklariDahil.setSelected(secim);
        return this;
    }

    @Step("Konu Kodu alanında {konuKodu} doldur")
    public CevaplananEvrakRaporuPage konuKoduDoldur(String konuKodu) {
        cmbKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("İlk Tarih alanında {tarih} doldur")
    public CevaplananEvrakRaporuPage ilkTarihDoldur(String tarih) {
        dateIlkTarih.setValue(tarih);
        return this;
    }

    @Step("Son Tarih alanında {tarih} doldur")
    public CevaplananEvrakRaporuPage sonTarihDoldur(String tarih) {
        dateSonTarih.setValue(tarih);
        return this;
    }

    @Step("Sorgula")
    public CevaplananEvrakRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Sayfayi Raporla")
    public CevaplananEvrakRaporuPage sayfayiRaporla() {
        btnSayfayiRaporla.shouldBe(visible);
        clickJs(btnSayfayiRaporla);
        return this;
    }

    @Step("Tüm Sayfayi Raporla")
    public CevaplananEvrakRaporuPage tumSayfayiRaporla() {
        btnTumSayfayiRaporla.shouldBe(visible);
        clickJs(btnTumSayfayiRaporla);
        return this;
    }

    @Step("Temizle")
    public CevaplananEvrakRaporuPage temizle() {
        btnTemizle.click();
        return this;
    }

    @Step("Cevaplanan evrak kayit kontrolu")
    public CevaplananEvrakRaporuPage cevaplananEvrakKayitKontrolu(String evrakTarihi, String evrakKayitTarihi, String evrakSayisi, String konu, String cevaplananEvrakKonuKodu, String cevaplananEvrakKonu, String cevaplananEvrakSayisi, String cevaplananEvrakTarihi) {


        tblCevaplananEvrakFormDataTable.shouldBe(visible);

        boolean statusEvrakTarihi = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 1, evrakTarihi).isDisplayed();
        boolean statusEvrakKayitTarihi = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 2, evrakKayitTarihi).isDisplayed();
        boolean statusEvrakSayisi = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 3, evrakSayisi).isDisplayed();
        boolean statuskonu = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 4, konu).isDisplayed();
        boolean statusCevaplananEvrakKonuKodu = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 5, cevaplananEvrakKonuKodu).isDisplayed();
        boolean statusCevaplananEvrakKonu = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 6, cevaplananEvrakKonu).isDisplayed();
        boolean statusCevaplananEvrakSayisi = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 7, cevaplananEvrakSayisi).isDisplayed();
        boolean statusCevaplananEvrakTarihi = findElementOnTableByColumnInputInAllPages(tblCevaplananEvrakFormDataTable, 11, cevaplananEvrakTarihi).isDisplayed();

        Assert.assertEquals(statusEvrakTarihi, true);
        Assert.assertEquals(statusEvrakKayitTarihi, true);
        Assert.assertEquals(statusEvrakSayisi, true);
        Assert.assertEquals(statuskonu, true);
        Assert.assertEquals(statusCevaplananEvrakKonuKodu, true);
        Assert.assertEquals(statusCevaplananEvrakKonu, true);
        Assert.assertEquals(statusCevaplananEvrakSayisi, true);
        Assert.assertEquals(statusCevaplananEvrakTarihi, true);

        return this;
    }

    @Step("Cevaplanan evrak detay, geçmiş kayit kontrolu")
    public CevaplananEvrakRaporuPage cevaplananEvrakGecmisiVeDetayKontrolu() {

        Assert.assertEquals(btnEvrakGecmisi.isDisplayed(), true);
        Assert.assertEquals(btnEvrakDetayi.isDisplayed(), true);

        return this;
    }

    @Step("Temizle sonrası kayit gelmeme kontrolu")
    public CevaplananEvrakRaporuPage temizleSonrasiKontrol() {

        Assert.assertEquals(btnEvrakGecmisi.isDisplayed(), false);
        Assert.assertEquals(btnEvrakDetayi.isDisplayed(), false);
        return this;
    }

    @Step("Excell indirilme kontrolu")
    public CevaplananEvrakRaporuPage excellIndirilmeKontrolu(String filePath, String fileName) throws IOException, InterruptedException {

        boolean status = searchDownloadedFileWithName(filePath, fileName);
        Assert.assertEquals(status, true);
        return this;
    }
}
