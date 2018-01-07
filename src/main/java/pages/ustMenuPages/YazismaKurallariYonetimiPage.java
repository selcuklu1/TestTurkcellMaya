package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YazismaKurallariYonetimiPage extends MainPage {

    SelenideElement btnGrupBirimTipiEkle = $(By.id("grupBirimTipleriListingForm:grupBirimTipleriDataTable:grupBirimTipiYeniKayit_id"));
    SelenideElement btnBirimTipiDropDown = $(By.id("grupBirimTipleriEditorForm:grupBirimTipiAutoCompleteId"));
    SelenideElement txtBirimTipi = $(By.id("grupBirimTipleriEditorForm:grupBirimTipiAutoCompleteId_input"));
    ElementsCollection listBirimTipleri = $$("div[id='grupBirimTipleriEditorForm:grupBirimTipiAutoCompleteId_panel'] > ul > li");
    SelenideElement chkSinirsizYazilabilir = $(By.id("grupBirimTipleriEditorForm:grupTipiSinirsizYazilabilirId"));
    SelenideElement chkVekaletSeviyesi = $(By.id("grupBirimTipleriEditorForm:grupTipiVekaletSeviyesiId"));
    SelenideElement chkSonImzaSeviyesi = $(By.id("grupBirimTipleriEditorForm:grupTipiSonImzaSeviyesiId"));
    SelenideElement btnGrupBirimTipleriKaydet = $(By.id("grupBirimTipleriEditorForm:grupTipiKaydet_id"));
    ElementsCollection tableYazismaKurallari = $$(By.id("grupBirimTipleriListingForm:grupBirimTipleriDataTable_data"));
    SelenideElement btnYazismaKuraliSilEvet = $(By.id("baseConfirmationDialog:confirmButton"));

    public YazismaKurallariYonetimiPage openPage() {
        ustMenu(UstMenuData.YonetimSayfalari.YazismaKurallariYonetimi);
        return this;
    }

    @Step("Yeni Yazışma Kuralı Ekle")
    public YazismaKurallariYonetimiPage yazismaKurallariEkle() {
        btnGrupBirimTipiEkle.click();
        return this;
    }

    @Step("Birim Tipi seç")
    public YazismaKurallariYonetimiPage birimTipiSec(String birimTipi) {

        txtBirimTipi.setValue(birimTipi);
        listBirimTipleri
                .filterBy(text(birimTipi))
                .get(0)
                .click();
        return this;
    }

    @Step("Sınırsız Yazılabilir checkbox tıkla")
    public YazismaKurallariYonetimiPage tiklaSinirsizYazilabilir(boolean checked) {
        boolean isChecked = chkSinirsizYazilabilir
                .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                .has(cssClass("ui-state-active"));

        if (checked == true) {
            if (isChecked == false)
                chkSinirsizYazilabilir
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();

        } else {
            if (isChecked == true)
                chkSinirsizYazilabilir
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();
        }

        return this;
    }

    @Step("Vekalet Seviyesi checkbox tıkla")
    public YazismaKurallariYonetimiPage tiklaVekaletSeviyesi(boolean checked) {
        boolean isChecked = chkVekaletSeviyesi
                .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                .has(cssClass("ui-state-active"));

        if (checked == true) {
            if (isChecked == false)
                chkVekaletSeviyesi
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();

        } else {
            if (isChecked == true)
                chkVekaletSeviyesi
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();
        }

        return this;
    }

    @Step("Son İmza Seviesi checkbox tıkla")
    public YazismaKurallariYonetimiPage tiklaSonImzaSeviyesi(boolean checked) {
        boolean isChecked = chkSonImzaSeviyesi
                .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                .has(cssClass("ui-state-active"));

        if (checked == true) {
            if (isChecked == false)
                chkSonImzaSeviyesi
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();

        } else {
            if (isChecked == true)
                chkSonImzaSeviyesi
                        .$(By.xpath("./div/span[contains(@class,'ui-chkbox-icon')]/.."))
                        .click();
        }

        return this;
    }

    @Step("Grup Birim Tipleri Kaydet")
    public YazismaKurallariYonetimiPage grupBirimTipleriKaydet() {
        btnGrupBirimTipleriKaydet.click();
        return this;
    }

    @Step("Yazışma Kuralları kontrol et")
    public YazismaKurallariYonetimiPage yazismakurallariKontrolEt(String birimTipi, boolean shouldBeExist, boolean sinirsizYazilabilir, boolean vekaletSeviyesi, boolean sonImzaSeviyesi) {

        if (shouldBeExist == true) {
            tableYazismaKurallari
                    .filterBy(text(birimTipi))
                    .get(0)
                    .shouldBe(exist);

            if (sinirsizYazilabilir == true)
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[2]//span"))
                        .shouldHave(cssClass("true"));
            else
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[2]//span"))
                        .shouldHave(cssClass("false"));

            if (vekaletSeviyesi == true)
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[3]//span"))
                        .shouldHave(cssClass("true"));
            else
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[3]//span"))
                        .shouldHave(cssClass("false"));

            if (sonImzaSeviyesi == true)
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[4]//span"))
                        .shouldHave(cssClass("true"));
            else
                tableYazismaKurallari
                        .filterBy(text(birimTipi))
                        .get(0)
                        .$(By.xpath("./tr/td[4]//span"))
                        .shouldHave(cssClass("false"));
        } else {
            tableYazismaKurallari
                    .filterBy(text(birimTipi))
                    .get(0)
                    .shouldBe(not(exist));
        }

        return this;

    }

    @Step("Yazışma Kuralı sil")
    public YazismaKurallariYonetimiPage yazismaKuraliSil(String birimTipi) {
        tableYazismaKurallari
                .filterBy(text(birimTipi))
                .get(0)
                .$("button[id^='grupBirimTipleriListingForm:grupBirimTipleriDataTable:'][id$='grupBirimTipleriButton']")
                .click();
        btnYazismaKuraliSilEvet.click();
        return this;
    }

    @Step("Yazışma Kuralı Güncelle")
    public YazismaKurallariYonetimiPage yazismaKuraliGuncelle(String birimTipi) {
        tableYazismaKurallari
                .filterBy(text(birimTipi))
                .get(0)
                .$("button[id^='grupBirimTipleriListingForm:grupBirimTipleriDataTable:'][id$=':grupBirimTipleriGuncelle_id']")
                .click();
        return this;
    }


}
