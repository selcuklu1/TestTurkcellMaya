package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Sık Kullanılanlar" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class SikKullanilanlarPage extends MainPage {

    //<editor-fold desc="Elements">
    SelenideElement cmbDagitimlarTip = $(By.id("sikKullanilanForm:j_idt24213_input"));
    By cmbDagitimlarTipBy = By.id("sikKullanilanForm:j_idt24213_label");
    BelgenetElement txtDagitimlarDagitimlar = comboLov(By.id("sikKullanilanForm:sikKullanilanDagitimLov_id:LovText"));
    SelenideElement btnDagitimlarKaydet = $(By.id("sikKullanilanForm:sikKullanilanDagitimButton"));
    SelenideElement btnDagitimlarKaldir = $(By.id("sikKullanilanForm:sikKullanilanDagitimKaldirButton"));


    //</editor-fold>

    @Step("Sık Kullanılanlar sayfasını aç")
    public SikKullanilanlarPage openPage() {
        ustMenu("Sık Kullanılanlar");
        return this;
    }

    @Step("Sık Kullanılan Dağıtımlar - Tip Seç")
    public SikKullanilanlarPage datigimlarTipSec(String secim) {

        comboBox(cmbDagitimlarTipBy).selectComboBox(secim);
        return this;
    }

    @Step("Sık Kullanılan Dağıtımlar - Dağıtım Doldur")
    public SikKullanilanlarPage dagitimlarDoldur(String dagitim) {
        txtDagitimlarDagitimlar.clearAllSelectedLov().selectLov(dagitim);

        return this;
    }

    @Step("Sık Kullanılan Dağıtımlar - Dağıtımlar Kaydet")
    public SikKullanilanlarPage dagitimlarKaydet() {
        btnDagitimlarKaydet.click();
        return this;
    }

    public SikKullanilanlarPage dagitimlarKaldir() {
        btnDagitimlarKaldir.click();
        return this;
    }


}
