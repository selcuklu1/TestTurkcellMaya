/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Giden Evrak Kayit" sayfasındaki metotları içerir
 * Yazan: Sezai Çelik
 ****************************************************/

package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.security.Key;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GidenEvrakKayitPage extends MainPage {

    //region Elements

    // gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt14590
    SelenideElement cmbGeregiSecimTipi = $(By.xpath("//select[starts-with(@id,'gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt')]"));
    BelgenetElement cmbGeregi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    SelenideElement cmbBilgiSecimTipi = $(By.xpath("//select[starts-with(@id,'gidenEvrakDefterKaydiForm:evrakBilgileriList:12:j_idt')]"));
    BelgenetElement cmbBilgi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    By cmbGeregiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    By cmbBilgiBy = By.cssSelector("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");
    SelenideElement btnGeregiDelete = $("button[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList:11:geregiLov:LovSecilenTable'] span[class$='delete-icon']");

    //endregion

    public GidenEvrakKayitPage openPage() {
        new UstMenu().ustMenu("Giden Evrak Kayıt");
        return this;
    }

    @Step("Gereği seçim tipi seç")
    public GidenEvrakKayitPage geregiSecimTipiSec(String geregi) {
        cmbGeregiSecimTipi.sendKeys(Keys.SHIFT);
        cmbGeregiSecimTipi.selectOptionByValue(geregi);
        return this;
    }

    @Step("Gereği doldur")
    public GidenEvrakKayitPage geregiDoldur(String geregiAdSoyad) {

        cmbGeregi.selectLov(geregiAdSoyad);

        System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());
        return this;
    }

    @Step("Gereği doldur")
    public GidenEvrakKayitPage geregiDoldur(String geregiAdSoyad, Boolean clearAfter) {

        cmbGeregi.selectLov(geregiAdSoyad);

        System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());

        cmbGeregi.clearAllSelectedLov();
        return this;
    }

    @Step("Kişinin Geregi alanında görüntülenmediği kontrolu")
    public GidenEvrakKayitPage geregiAlanindaGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbGeregiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");
        return this;
    }

    @Step("Kurumun Geregi alanında görüntüleme kontrolu")
    public GidenEvrakKayitPage geregiAlanindaDegerKontrolu(String aranacakDeger, Boolean shouldBeExist) {

        Assert.assertEquals(comboLov(cmbGeregiBy).isLovValueSelectable(aranacakDeger), shouldBeExist);
        return this;
    }

    @Step("Kişinin Geregi alanında görüntülenme kontrolu")
    public GidenEvrakKayitPage geregiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

        String adSoyad = ad + " " + soyad;
        cmbGeregi.selectLov(adSoyad);
        System.out.println("Gelen title:     " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);
        Assert.assertEquals(cmbGeregi.lastSelectedLovTitleText().contains(adSoyad), true);

        return this;
    }

    @Step("Bilgi seçim tipi seç")
    public GidenEvrakKayitPage bilgiSecimTipiSec(String bilgi) {
        cmbBilgiSecimTipi.sendKeys(Keys.SHIFT);
        cmbBilgiSecimTipi.selectOptionByValue(bilgi);
        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad) {

        cmbBilgi.selectLov(geregiAdSoyad);
        System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());

        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad, Boolean clearAfter) {

        cmbBilgi.selectLov(geregiAdSoyad);
        System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());

        cmbBilgi.clearAllSelectedLov();

        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(cmbBilgiBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");

        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenme kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaGoruntulenmeKontrolu(String ad, String soyad) {

        String adSoyad = ad + " " + soyad.toUpperCase();
        cmbBilgi.selectLov(adSoyad);
        System.out.println("Gelen title:     " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("Beklenen title:  " + adSoyad);
        Assert.assertEquals(cmbBilgi.lastSelectedLovTitleText().contains(adSoyad), true);

        return this;
    }



    @Step("Kurumun Geregi alanında görüntüleme kontrolu")
    public GidenEvrakKayitPage bilgiAlanindaDegerKontrolu(String aranacakDeger, Boolean shouldBeExist) {

        Assert.assertEquals(cmbBilgi.isLovValueSelectable(aranacakDeger), shouldBeExist);
        return this;
    }



    public GidenEvrakKayitPage secilenGeregiSil() {
        btnGeregiDelete.click();
        return this;
    }

    @Step("Panel kapat")
    public GidenEvrakKayitPage panelKapat(Boolean kaydet){
        $(By.xpath("//div[@id='mainTaskBar']//span[text()='[Giden Evrak Kayıt]']"))
                .contextClick();

        if(kaydet)
            $(By.id("kapatKaydetEvetButton")).click();
        else
            $(By.id("kapatKaydetHayirButton")).click();

        return this;
    }



}