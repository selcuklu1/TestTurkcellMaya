package page;


import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class KullaniciYonetimiPage extends BaseLibrary {

    SelenideElement pageTitle = $(By.cssSelector("#window1Dialog .ui-dialog-title"));
    SelenideElement gorevCombobox = $(By.id("kullaniciYonetimiListingForm:filterPanel:filterGorevAutocomplete"));
    SelenideElement txtTCKimlikNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:tcKimlikNoFilter"));
    SelenideElement txtAd = $(By.id("kullaniciYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement txtSoyad = $(By.id("kullaniciYonetimiListingForm:filterPanel:soyadFilterInput"));
    SelenideElement txtKullaniciAdi = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciAdiFilterInput"));
    SelenideElement txtEmail = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciEmailFilterInput"));
    SelenideElement txtSicilNo = $(By.id("kullaniciYonetimiListingForm:filterPanel:sicilInput"));
    SelenideElement cmbKullaniciTuru = $(By.id("kullaniciYonetimiListingForm:filterPanel:kullaniciTuruSelectBox"));
    SelenideElement btnAra = $(By.id("kullaniciYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement cmbDurum = $(By.id("kullaniciYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement chkGorevSuresiDolanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:gorevSuresiDolanlarCheckbox"));
    SelenideElement chkBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:birimiOlmayanlarCheckbox"));
    SelenideElement chkAltBirimiOlmayanlar = $(By.id("kullaniciYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox"));

    public KullaniciYonetimiPage() {

        //waitUntil(visibilityOfElementLocated(pageTitle));
    }

    public KullaniciYonetimiPage gorevAlaniDoldur(String value) throws InterruptedException {
        //TODO
        return this;
    }

    public KullaniciYonetimiPage TCKimlikNoDoldur(String value) throws InterruptedException {
        txtTCKimlikNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage adDoldur(String value) throws InterruptedException {
        txtAd.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage soyadDoldur(String value) throws InterruptedException {
        txtSoyad.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage kullaniciAdiDoldur(String value) throws InterruptedException {
        txtKullaniciAdi.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage emailDoldur(String value) throws InterruptedException {
        txtEmail.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage sicilNoDoldur(String value) throws InterruptedException {
        txtSicilNo.sendKeys(value);
        return this;
    }

    public KullaniciYonetimiPage kullaniciTuruSec(String value) throws InterruptedException {
        cmbKullaniciTuru.selectOptionByValue(value);
        //selectCombobox(cmbKullaniciTuru,value);
        return this;
    }

    public KullaniciYonetimiPage durumSec(String value) throws InterruptedException {
        cmbDurum.selectOptionByValue(value);
        return this;
    }

    public KullaniciYonetimiPage gorevSuresiDolanlarSec(boolean value) throws InterruptedException {
        chkGorevSuresiDolanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage birimiOlmayanlarSec(boolean value) throws InterruptedException {
        chkBirimiOlmayanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage altBirimlerDahilSec(boolean value) throws InterruptedException {
        chkAltBirimiOlmayanlar.setSelected(value);
        return this;
    }

    public KullaniciYonetimiPage ara() throws InterruptedException {
        btnAra.click();
        return this;
    }

}
