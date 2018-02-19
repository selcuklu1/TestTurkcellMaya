package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.Belgenet;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;


/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KullaniciListesiYonetimiPage extends MainPage {

    SelenideElement tabSorgulamaVeFiltreleme = $x("//a[text()='Sorgulama ve Filtreleme']//..//..//h3");
    SelenideElement lblKullaniciListeleri = $x("//label[normalize-space(text())='Kullanıcı Listeleri']");
    SelenideElement btnYeniEkle = $(By.id("kullaniciGrubuListingForm:kullaniciGrubuDataTable:kullaniciGrubuYeniKayit_id"));
    SelenideElement lblKullaniciListesiEkleme = $x("//span[normalize-space(text())='Kullanıcı Listesi Ekleme']");
    SelenideElement txtAdi = $(By.id("kullaniciGrubuEditorForm:kullaniciGrubuAd_id"));
    SelenideElement txtAciklama = $(By.id("kullaniciGrubuEditorForm:kullaniciGrubuAciklama_id"));
    BelgenetElement txtAitOlduguBirim = comboLov(By.id("kullaniciGrubuEditorForm:kullaniciGrubuBirimLov_id:LovText"));
    BelgenetElement txtKullanicilar = comboLov(By.id("kullaniciGrubuEditorForm:kullaniciGrubuKullaniciLov_id:LovText"));
    SelenideElement btnKaydet = $(By.id("kullaniciGrubuEditorForm:kullaniciGrubuKaydet_id"));
    SelenideElement btnIptal = $(By.id("kullaniciGrubuEditorForm:kullaniciGrubuKaydetIptal_id"));


    @Step("Kullanıcı Listesi Yönetimi sayfası açılır.")
    public KullaniciListesiYonetimiPage openPage() {
        ustMenu(UstMenuData.KullaniciIslemleri.KullaniciListesiYonetimi);
        return this;
    }

    @Step("Kullanıcı Listesi Yönetimi sayfası alan kontrolü yapılır.")
    public KullaniciListesiYonetimiPage ekranAlanKontrolu() {
        Assert.assertEquals(tabSorgulamaVeFiltreleme.isDisplayed(), true, "Sorgu ve Filtrele tabı gözüküyor.");
        Assert.assertEquals(lblKullaniciListeleri.isDisplayed(), true, "Kullanıcı Listeleri alanı gözüküyor.");

        Allure.addAttachment("Kullanıcı Listesi Yönetimi : ", "Kullanıcı Listesi Yönetimi ekranının açıldığı görülür.\n" +
                "Sorgulama ve Filtreleme\n" +
                "ve\n" +
                "Kullanıcı Listeleri alanları görüntülenir");
        takeScreenshot();
        return this;
    }

    @Step("Yeni Ekle (+) butonu tıklanır.")
    public KullaniciListesiYonetimiPage yeniEkle() {
        btnYeniEkle.click();
        return this;
    }

    @Step("Kullanıcı Listesi Ekleme alan kontrolleri yapılır.")
    public KullaniciListesiYonetimiPage kullaniciListesiEklemeAlanKontrolleri() {
        Assert.assertEquals(lblKullaniciListesiEkleme.isDisplayed(), true, "Kullanıcı Lİstesi Ekleme alanı görülür.");
        Assert.assertEquals(txtAdi.isDisplayed(), true, "Adı alanı görülür.");
        Assert.assertEquals(txtAciklama.isDisplayed(), true, "Açıklama alanı görülür.");
        Assert.assertEquals(txtAitOlduguBirim.isDisplayed(), true, "Ait Olduğu alanı görülür.");
        Assert.assertEquals(txtKullanicilar.isDisplayed(), true, "Kullanıclar alanı görülür.");

        Allure.addAttachment("Kullanıcı Listesi Ekeleme : ", "Kullanıcı Listesi Ekleme ekranının açıldığı görülür.\n" +
                "Adı\n" +
                "Açıklama\n" +
                "Ait Olduğu Birim\n" +
                "Kullanıcılar alanları gelir.");

        takeScreenshot();
        return this;
    }

    @Step("Adı alanına \"{ad}\" girilir.")
    public KullaniciListesiYonetimiPage adDoldur(String ad) {
        txtAdi.sendKeys(ad);
        return this;
    }

    @Step("Açıklama alanına \"{aciklama}\" girilir.")
    public KullaniciListesiYonetimiPage aciklamaDoldur(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Ait Olduğu Birim alanında \"{birim}\" seçilir.")
    public KullaniciListesiYonetimiPage birimSec(String birim) {
        txtAitOlduguBirim.selectLov(birim);
        return this;
    }

    @Step("Kullanicilar alanında \"{kullanici}\" seçilir.")
    public KullaniciListesiYonetimiPage kullanicilarSec(String kullanici) {
        txtKullanicilar.selectLov(kullanici);
        return this;
    }

    @Step("Kaydet butonuna tıklanır.")
    public KullaniciListesiYonetimiPage kaydet() {
        btnKaydet.click();
        return this;
    }

}
