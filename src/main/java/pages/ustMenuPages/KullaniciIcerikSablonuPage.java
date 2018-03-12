package pages.ustMenuPages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class KullaniciIcerikSablonuPage extends MainPage
{

    SelenideElement btnYeniSablonOlustur = $(By.id("kullaniciSablonForm:sablonIslemYeniButton_Id"));
    SelenideElement txtSablonAdi = $(By.id("kullaniciSablonForm:sablonAdiText_id"));
    SelenideElement btnKaydet = $(By.id("kullaniciSablonForm:sablonIslemKaydetButton_id"));
    SelenideElement btnSil = $(By.id("kullaniciSablonForm:sablonIslemSilButton_id"));
    SelenideElement btnEvrakOnizleme = $(By.id("kullaniciSablonForm:sablonIslemSilButton_id"));


    @Step("Kullanici Icerik Sablonu Sayfasi Acilir")
    public KullaniciIcerikSablonuPage openPage()
    {
        ustMenu(UstMenuData.KullaniciIslemleri.KullaniciIcerikSablonlari);
        return this;
    }

    @Step("Yeni Şablon oluşturu butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage yeniSablonOlusturGeldigiGorme()
    {
        boolean durum = btnYeniSablonOlustur.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Sablon adi geldigi gorulur")
    public KullaniciIcerikSablonuPage sablonAdiGeldigiGorme()
    {
        boolean durum = txtSablonAdi.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Kaydet butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage kaydetGeldigiGorme()
    {
        boolean durum = btnKaydet.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Sil butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage silGeldigiGorme()
    {
        boolean durum = btnSil.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Sil butonunun geldigi gorulur")
    public KullaniciIcerikSablonuPage evrakOnizlemeGeldigiGorme()
    {
        boolean durum = btnEvrakOnizleme.shouldBe(visible).exists();
        Assert.assertEquals(durum,true);
        return  this;
    }

    @Step("Yeni Şablon oluştur tıklanır")
    public KullaniciIcerikSablonuPage yeniSablonOlustur(){
        btnYeniSablonOlustur.click();
        return this;
    }
    
    @Step("Şablon adı alanını doldur: {sablonAdi}")
    public KullaniciIcerikSablonuPage sablonAdiDoldur(String sablonAdi){
    txtSablonAdi.setValue(sablonAdi);
        return this;
    }

    @Step("")
    public KullaniciIcerikSablonuPage stepmethod(String text){
        $(By.id("cke_88_contents")).sendKeys(text);
        return this;
    }
}