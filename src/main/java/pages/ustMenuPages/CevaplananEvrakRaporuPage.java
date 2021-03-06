package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 <<<<<<< HEAD
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 =======
 * Class: "CevaplananEvrakRaporuPage" konulu senaryoları içerir
 * Yazan: Sezai Çelik
 >>>>>>> 36645eaf85cda015b40d4bcf32eef5e0a62d4a37
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

    SelenideElement tblEvrakTarihi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[1]"));
    SelenideElement tblEvrakKayitTarihi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[2]"));
    SelenideElement tblEvrakSayisi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[3]"));
    SelenideElement tblKonu = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[4]"));
    SelenideElement tblCevaplananEvrakKonuKodu = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[5]"));
    SelenideElement tblCevaplananEvrakKonu = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[6]"));
    SelenideElement tblCevaplananEvrakSayisi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[7]"));
    SelenideElement tblCevaplananEvrakIlkParafTarihi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[8]"));
    SelenideElement tblCevaplananEvrakIlkParafKullanici = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[9]"));
    SelenideElement tblCevaplananEvrakIlkParafBirimi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[10]"));
    SelenideElement tblCevaplananEvrakTarihi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[11]"));
    SelenideElement tblEvrakGecmisi = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[12]"));
    SelenideElement tblDetay = $(By.xpath("//*[@id='cevaplananEvrakRaporuForm:cevaplananEvrakRaporOutputTab_data']/tr/td[13]"));


    @Step("Cevaplanan Evrak Raporu sayfasını aç")
    public CevaplananEvrakRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.CevaplananEvrakRaporu);
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

    @Step("Cevaplanan evrak alan kontrolleri")
    public CevaplananEvrakRaporuPage cevaplananEvrakAlanKontrolleri() {

        Assert.assertEquals(tblEvrakTarihi.isDisplayed(), true);
        Allure.addAttachment("Evrak Tarihi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblEvrakKayitTarihi.isDisplayed(), true);
        Allure.addAttachment("Evrak Kayıt Tarihi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblEvrakSayisi.isDisplayed(), true);
        Allure.addAttachment("Evrak Sayısı alan kontrolu başaralı.", "");

        Assert.assertEquals(tblKonu.isDisplayed(), true);
        Allure.addAttachment("Konu alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakKonuKodu.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak Konu Kodu alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakKonu.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak Konu alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakSayisi.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak Sayısı alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakIlkParafTarihi.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak İlk Paraf Tarihi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakIlkParafKullanici.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak İlk Paraf Kullanıcı alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakIlkParafBirimi.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak İlk Paraf Birimi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblCevaplananEvrakTarihi.isDisplayed(), true);
        Allure.addAttachment("Cevaplanan Evrak Tarihi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblEvrakGecmisi.isDisplayed(), true);
        Allure.addAttachment("Evrak Geçmişi alan kontrolu başaralı.", "");

        Assert.assertEquals(tblDetay.isDisplayed(), true);
        Allure.addAttachment("Detay alan kontrolu başaralı.", "");


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
