package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TopluEvrakOnizleme extends MainPage {
    SelenideElement tabTopluHavale = $("[id='mainPreviewForm:topluHavaleOnizlemeTab']");
    SelenideElement txtBirim = $("[id='mainPreviewForm:dagitimBilgileriBirimLov:LovText']");
    //    SelenideElement txtKisi = $("[id='mainPreviewForm:dagitimBilgileriKullaniciLov:LovText']");
    SelenideElement txtAciklama = $("[id='mainPreviewForm:havaleAciklama']");
    SelenideElement btnDosyaEkle = $("[id='mainPreviewForm:fileUploadHavaleEk']");
    SelenideElement txtKullaniciListesi = $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText']");
    SelenideElement txtOnaylayacakKisi = $("[id='mainPreviewForm:onaylayacakKisiLov:LovText']");
    BelgenetElement txtKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtEklenenKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:j_idt']");
    SelenideElement btnGonder = $("[class$='havaleGonderButonClass']");
    BelgenetElement txtHavaleIslemleriKisiListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    SelenideElement txtHavaleIslemleriKisiListesiKontrol = $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:selectOneMenu']");

    SelenideElement txtEklenenKisiListesiOpsiyon = $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:selectOneMenu']");
    SelenideElement dosyaPath = $(By.xpath("//input[@id='mainPreviewForm:fileUploadHavaleEk_input']"));
    SelenideElement btnkullaniciGrupDetayEvet = $("[id='mainPreviewForm:kendinedeGonderilsinViewDialogYes']");


    @Step("Toplu Havale Etme ekranı açılır\n")
    public TopluEvrakOnizleme ekranKontrol() {
        Assert.assertEquals(tabTopluHavale.isDisplayed(), true, "Toplu Havale Etme sayfası");
        Allure.addAttachment("Toplu Havale Etme sayfası", "açılmaktadır");
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public TopluEvrakOnizleme havaleAlanKontrolleri() {
        String text = "";
        if (txtBirim.isDisplayed()) {
            text += "Birim Kontrol,";
            Assert.assertEquals(txtBirim.isDisplayed(), true, "Birim Alanı Görüntülendi");
            Allure.addAttachment("Birim Kontrol Alanı Görüntülendi : ", "");
        }
        if (txtKisi.isDisplayed()) {
            text += "Kisi Kontrol, ";
            Assert.assertEquals(txtKisi.isDisplayed(), true, "Kisi Alanı Görüntülendi");
            Allure.addAttachment("Kisi Alanı Görüntülendi : ", "");
        }
        if (txtAciklama.isDisplayed()) {
            text += "Aciklama,";
            Assert.assertEquals(txtAciklama.isDisplayed(), true, "Aciklama Alanı Görüntülendi");
            Allure.addAttachment("Aciklama Alanı Görüntülendi : ", "");
        }
        if (btnDosyaEkle.isDisplayed()) {
            text += "Dosya Ekle,";
            Assert.assertEquals(btnDosyaEkle.isDisplayed(), true, "Dosya Ekle Alanı Görüntülendi");
            Allure.addAttachment("Dosya Ekle Alanı Görüntülendi : ", "");
        }
        if (txtKullaniciListesi.isDisplayed()) {
            text += "Kullanıcı Liste,";
            Assert.assertEquals(txtKullaniciListesi.isDisplayed(), true, "Kullanıcı Liste Alanı Görüntülendi");
            Allure.addAttachment("Kullanıcı Liste Alanı Görüntülendi : ", "");
        }
        if (txtOnaylayacakKisi.isDisplayed()) {
            text += "Onaylayacak Kisi,";
            Assert.assertEquals(txtOnaylayacakKisi.isDisplayed(), true, "Onaylayacak Kisi Alanı Görüntülendi");
            Allure.addAttachment("Onaylayacak Kisi Alanı Görüntülendi : ", "");
        }

        Allure.addAttachment("Alan Kontrolleri : ", text);
        takeScreenshot();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" \"{details}\"seç")
    public TopluEvrakOnizleme havaleIslemleriKisiDoldur(String kisi, String details) {
        txtKisi.selectLov(kisi, details);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{kisi}\" kontrolü")
    public TopluEvrakOnizleme eklenenKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenKisi.isDisplayed(), true, "Kisi Eklendi");
        Allure.addAttachment("Kisi Eklendi:", kisi);
        return this;
    }

    @Step("Gönder Butonu Tıklandı")
    public TopluEvrakOnizleme gonder() {
        btnGonder.click();
        return this;
    }


    @Step("Havale İşlemleri Kişi Listesi alanında \"{kisiliste}\" seç")
    public TopluEvrakOnizleme havaleKisiListesi(String kisiliste) {
        txtHavaleIslemleriKisiListesi.selectLov(kisiliste);
        return this;
    }

    @Step("Kullanıcı Listesinde login olunan kullanıcının bulunması durumunda Evet ile Onaylama")
    public TopluEvrakOnizleme kullaniciGrupDetayEvet() {
        btnkullaniciGrupDetayEvet.click();
        return this;
    }

    @Step("Havale İşlemleri Kişi Listesi Kontrol \"{kisiliste}\" seç")
    public TopluEvrakOnizleme havaleKisiListesiKontrolu(String kisiliste) {
        Assert.assertEquals(txtHavaleIslemleriKisiListesiKontrol.isDisplayed(), true, "Kisi Listesi Eklendi");
        Allure.addAttachment("Kisi Listesi Eklendi", "");
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{opsiyon}\" kontrolü")
    public TopluEvrakOnizleme eklenenKisiListesiOpsiyonKontrolu(String opsiyon) {
        Assert.assertEquals(txtEklenenKisiListesiOpsiyon.getSelectedOption().text().equals(opsiyon), true, "Opsiyon Seçildi");
        Allure.addAttachment("Opsiyon Seçildi:", opsiyon);
        return this;
    }

    @Step("Açıklama alanına \"{konu}\" girilir")
    public TopluEvrakOnizleme aciklamaDoldur(String konu) {
        txtAciklama.setValue(konu);
        return this;
    }

    @Step("Havale İşlemleminde Dosya Ekle")
    public TopluEvrakOnizleme dosyaEkle() {
        btnDosyaEkle.click();
        return this;
    }

    @Step("Evrak Ekleri Dosya Ekleme : \"{pathToFile}\" ")
    public TopluEvrakOnizleme havaleDosyaEkle(String pathToFile) throws InterruptedException {
        uploadFile(dosyaPath, pathToFile);
        Thread.sleep(4000);
//        WebDriverRunner.getWebDriver()
//                .findElement(dosyaPath)
//                .sendKeys(pathToFile);
        return this;
    }

    @Step("Havale dosya ekleme adi kontrol : \"{dosyaAdi}\" ")
    public TopluEvrakOnizleme havaleDosyaEkleDosyaAdiKontrol(String dosyaAdi) {
        $(byText(dosyaAdi)).shouldBe(Condition.visible);
        return this;
    }


}

