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
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GidenEvrakKayitPage extends MainPage {

    //region Elements
    SelenideElement cmbGeregiSecimTipi = $(By.id("gidenEvrakDefterKaydiForm:evrakBilgileriList:11:j_idt16516"));
    BelgenetElement cmbGeregi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='geregiLov:LovText']");
    SelenideElement cmbBilgiSecimTipi = $(By.id("gidenEvrakDefterKaydiForm:evrakBilgileriList:12:j_idt16507"));
    BelgenetElement cmbBilgi = comboLov("[id^='gidenEvrakDefterKaydiForm:evrakBilgileriList'][id$='bilgiLov:LovText']");

    //endregion

    public GidenEvrakKayitPage openPage() {
        new UstMenu().ustMenu("Giden Evrak Kayıt");
        return this;
    }

    @Step("Gereği seçim tipi seç")
    public GidenEvrakKayitPage geregiSecimTipiSec(String geregi) {
        cmbGeregiSecimTipi.selectOptionByValue(geregi);
        return this;
    }

    @Step("Gereği doldur")
    public GidenEvrakKayitPage geregiDoldur(String geregiAdSoyad) {

        cmbGeregi.selectComboLov(geregiAdSoyad);

        System.out.println("title: " + cmbGeregi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbGeregi.lastSelectedLovDetailText());
        return this;
    }

    @Step("Bilgi seçim tipi seç")
    public GidenEvrakKayitPage bilgiSecimTipiSec(String bilgi) {
        cmbBilgiSecimTipi.selectOptionByValue(bilgi);
        return this;
    }

    @Step("Bilgi doldur")
    public GidenEvrakKayitPage bilgiDoldur(String geregiAdSoyad) {

        cmbBilgi.selectComboLov(geregiAdSoyad);

        System.out.println("title: " + cmbBilgi.lastSelectedLovTitleText());
        System.out.println("detail: " + cmbBilgi.lastSelectedLovDetailText());
        return this;
    }

}