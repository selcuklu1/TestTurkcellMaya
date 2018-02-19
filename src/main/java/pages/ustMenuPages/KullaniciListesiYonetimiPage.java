package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.Belgenet;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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
    SelenideElement cmbDurum = $(By.id("kullaniciGrubuListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnAra = $(By.id("kullaniciGrubuListingForm:filterPanel:kullaniciGrubuArama_id"));
    ElementsCollection tblKullaniciListesi = $$("tbody[id='kullaniciGrubuListingForm:kullaniciGrubuDataTable_data'] tr[data-ri]");
    SelenideElement btnIslemOnayıEvet = $(By.id("baseConfirmationDialog:confirmButton"));

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

    @Step("Durum alanında \"{durum}\" seçilir.")
    public KullaniciListesiYonetimiPage durumSec(String durum) {
        cmbDurum.selectOption(durum);
        return this;
    }

    @Step("Ara butonuna tıklanır.")
    public KullaniciListesiYonetimiPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Kullanici Listesi tablosu kontrolü.")
    public KullaniciListesiYonetimiPage kullaniciListesiTabloKontrolu() {

        String text = cmbDurum.getText();

        if (tblKullaniciListesi.size()>0)
            Allure.addAttachment("Tablo Kontrolü : ",text+ " Kayıtlar listelenmiştir.");

        else
            Allure.addAttachment("Tablo Kontrolü : ", "Kayıt bulunamamıştır.");
        return this;
    }

    @Step("Kullanici Listesi tablosu kontrolu : \"{kullaniciAdi}\", \"{shouldBeExist}\" ")
    public KullaniciListesiYonetimiPage kullaniciListesiTablosuKullaniciAdiKontrolu(String kullaniciAdi, boolean shouldBeExist) {
        if (shouldBeExist) {
            searchTable().searchInAllPages(true).findRows(text(kullaniciAdi)).shouldHave(CollectionCondition.sizeGreaterThan(0));
//            tblKullaniciListesi
//                    .filterBy(Condition.text(kullaniciAdi))
//                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        } else {
            searchTable().searchInAllPages(true).findRows(text(kullaniciAdi)).shouldHaveSize(0);
//            tblKullaniciListesi
//                    .filterBy(Condition.text(kullaniciAdi))
//                    .shouldHaveSize(0);
        }
        return this;
    }

    @Step("Pasif Yap butonu tıklanır.")
    public KullaniciListesiYonetimiPage pasifYap(String kullaniciAdi) {
        tblKullaniciListesi
                .filterBy(text(kullaniciAdi))
                .first()
                .$("[id$=':kullaniciGrubuAktif_id']").click();
        return this;
    }

    @Step("İşlem Onayı popUpı \"{butonText}\" butonuna basılarak kapatılır.")
    public KullaniciListesiYonetimiPage islemOnayiPopUpEvetHayır(String butonText) {
        SelenideElement btnKapat = $(By.xpath("//div[@id='baseConfirmationDialog:dialog']//span[text()='" + butonText + "']//..//..//button"));
        btnKapat.click();
        return this;
    }
}
