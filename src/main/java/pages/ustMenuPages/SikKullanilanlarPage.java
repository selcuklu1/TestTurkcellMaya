package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.exactText;
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
    SelenideElement cmbDagitimlarTip = $(By.xpath("//form[@id='sikKullanilanForm']//label[normalize-space(text())='Tip']/ancestor::tr/td[4]/div/label"));
    By cmbDagitimlarTipBy = By.xpath("//form[@id='sikKullanilanForm']//label[normalize-space(text())='Tip']/ancestor::tr/td[4]/div/label");
    By txtDagitimlarDagitimlarBy = By.id("sikKullanilanForm:sikKullanilanDagitimLov_id:LovText");
    BelgenetElement txtDagitimlarDagitimlar = comboLov(By.id("sikKullanilanForm:sikKullanilanDagitimLov_id:LovText"));
    SelenideElement btnDagitimlarKaydet = $(By.id("sikKullanilanForm:sikKullanilanDagitimButton"));
    SelenideElement btnDagitimlarKaldir = $(By.id("sikKullanilanForm:sikKullanilanDagitimKaldirButton"));
    SelenideElement btnEkranKapat  = $(By.cssSelector("[id='window2Dialog'] span[class='ui-icon ui-icon-closethick']"));

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
        txtDagitimlarDagitimlar.selectLov(dagitim);

        return this;
    }

    @Step("Sık Kullanılan Dağıtımlar - Dağıtım Doldur")
    public SikKullanilanlarPage dagitimdaVarIseKaldir(String dagitim) {

        // txtDagitimlarDagitimlar.clearAllSelectedLov().selectLov(dagitim);
        //comboLov("").titleItems().contains(exactText("Türksat Optiim"))
        //comboLov("").titleItems().filterBy(exactText("Türksat Optiim")).size() > 0

        if (txtDagitimlarDagitimlar.selectedTitles().filterBy(exactText(dagitim)).size() > 0) {
            dagitimlarKaldir();
            ekraniKapat();
            openPage();
            System.out.println("Comboda daha önce seçildiği için kaldırıldı.");
        }
        return this;
    }

    @Step("Sık Kullanılan Dağıtımlar - Dağıtımlar Kaydet")
    public SikKullanilanlarPage dagitimlarKaydet() {
        btnDagitimlarKaydet.click();
        return this;
    }

    @Step("Seçili dağıtımlar kaldır")
    public SikKullanilanlarPage dagitimlarKaldir() {
        btnDagitimlarKaldir.click();
        return this;
    }

    @Step("Ekranı kapat")
    public SikKullanilanlarPage ekraniKapat() {
        btnEkranKapat.click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }

    @Step("Dağıtımlar listesinde görüntülenmeme kontrolu")
    public SikKullanilanlarPage dagitimlarListesindeKisininGoruntulenmemeKontrolu(String kisi) {

        boolean selectable = comboLov(txtDagitimlarDagitimlarBy).isLovValueSelectable(kisi);
        Assert.assertEquals(selectable, false, "MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür");
        System.out.println("MyCombolov alanında " + kisi + ": Kişinin görüntülenmediği görülür.");
        return this;
    }
}
