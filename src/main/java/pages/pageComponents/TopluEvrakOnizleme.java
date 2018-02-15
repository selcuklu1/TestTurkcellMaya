package pages.pageComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TopluEvrakOnizleme extends MainPage {
    SelenideElement tabTopluHavale = $("[id='mainPreviewForm:topluHavaleOnizlemeTab']");
    SelenideElement txtBirim = $("[id='mainPreviewForm:dagitimBilgileriBirimLov:LovText']");
//    SelenideElement txtKisi = $("[id='mainPreviewForm:dagitimBilgileriKullaniciLov:LovText']");
    SelenideElement txtAciklama = $("[id='mainPreviewForm:havaleAciklama']");
    SelenideElement btnDosyaEkle = $("[id='mainPreviewForm:fileUploadHavaleEk_input']");
    SelenideElement txtKullaniciListesi = $("[id='mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText']");
    SelenideElement txtOnaylayacakKisi = $("[id='mainPreviewForm:onaylayacakKisiLov:LovText']");
    BelgenetElement txtKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    SelenideElement txtEklenenKisi = $("div[id^='mainPreviewForm:dagitimBilgileriKullaniciLov:LovSecilenTable:0:j_idt']");
    SelenideElement btnGonder = $("[class$='havaleGonderButonClass']");


    @Step("Toplu Havale Etme ekranı açılır\n")
    public TopluEvrakOnizleme ekranKontrol() {
        Assert.assertEquals(tabTopluHavale.isDisplayed(),true,"Toplu Havale Etme sayfası");
        Allure.addAttachment("Toplu Havale Etme sayfası","açılmaktadır");
        return this;
    }

    @Step("Havale İşlemleri Alanındaki Kontroller")
    public TopluEvrakOnizleme havaleAlanKontrolleri() {
        String text = "";
        if(txtBirim.isDisplayed()) {
            text += "Birim Kontrol,";
            Assert.assertEquals(txtBirim.isDisplayed(),true,"Birim Alanı Görüntülendi");
            Allure.addAttachment("Birim Kontrol Alanı Görüntülendi : ","");
        }
        if(txtKisi.isDisplayed()) {
            text += "Kisi Kontrol, ";
            Assert.assertEquals(txtKisi.isDisplayed(),true,"Kisi Alanı Görüntülendi");
            Allure.addAttachment("Kisi Alanı Görüntülendi : ","");
        }
        if(txtAciklama.isDisplayed()) {
            text += "Aciklama,";
            Assert.assertEquals(txtAciklama.isDisplayed(),true,"Aciklama Alanı Görüntülendi");
            Allure.addAttachment("Aciklama Alanı Görüntülendi : ","");
        }
        if(btnDosyaEkle.isDisplayed()) {
            text += "Dosya Ekle,";
            Assert.assertEquals(btnDosyaEkle.isDisplayed(),true,"Dosya Ekle Alanı Görüntülendi");
            Allure.addAttachment("Dosya Ekle Alanı Görüntülendi : ","");
        }
        if(txtKullaniciListesi.isDisplayed()) {
            text += "Kullanıcı Liste,";
            Assert.assertEquals(txtKullaniciListesi.isDisplayed(),true,"Kullanıcı Liste Alanı Görüntülendi");
            Allure.addAttachment("Kullanıcı Liste Alanı Görüntülendi : ","");
        }
        if(txtOnaylayacakKisi.isDisplayed()) {
            text += "Onaylayacak Kisi,";
            Assert.assertEquals(txtOnaylayacakKisi.isDisplayed(),true,"Onaylayacak Kisi Alanı Görüntülendi");
            Allure.addAttachment("Onaylayacak Kisi Alanı Görüntülendi : ","");
        }

        Allure.addAttachment("Alan Kontrolleri : ", text);
        takeScreenshot();
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında \"{kisi}\" \"{details}\"seç")
    public TopluEvrakOnizleme havaleIslemleriKisiDoldur(String kisi,String details) {
        txtKisi.selectLov(kisi,details);
        return this;
    }

    @Step("Havale İşlemleri Kişi alanında eklenen \"{kisi}\" kontrolü")
    public TopluEvrakOnizleme eklenenKisiKontrolu(String kisi) {
        Assert.assertEquals(txtEklenenKisi.isDisplayed(),true,"Kisi Eklendi");
        Allure.addAttachment("Kisi Eklendi:" , kisi);
        return this;
    }

    @Step("Gönder Butonu Tıklandı")
    public TopluEvrakOnizleme gonder() {
        btnGonder.click();
        return this;
    }


}

