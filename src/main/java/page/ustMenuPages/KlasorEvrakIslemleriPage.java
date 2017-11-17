package page.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class KlasorEvrakIslemleriPage extends BaseLibrary {

    private SelenideElement txtBirim = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:birimLov:LovText"));
    private SelenideElement txtKlasor = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:klasorLov:LovText"));
    private SelenideElement txtAramaKriteri = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakKonusuInput"));
    private SelenideElement txtEvrakTarihi = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakTarihiCalendar_input"));
    private SelenideElement cmbEvrakTipi = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakTipiSelect"));
    private SelenideElement chkAltKlasorlerDahil = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:altBirimlerDahilCheckbox_input"));
    private SelenideElement btnAra = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:searchEvrakButton"));
    private SelenideElement btnKlasoreKopyala = $(By.id("klasorEvrakIslemleriListingForm:copyAllSelectedEvrakButton"));
    private SelenideElement btnKlasoreTasi = $(By.id("klasorEvrakIslemleriListingForm:moveAllSelectedEvrakButton"));

    public KlasorEvrakIslemleriPage birimDoldur(String birim) {
        //sendKeys(birimInput, birim, false);
        txtBirim.sendKeys(birim);
        return this;
    }

    public KlasorEvrakIslemleriPage klasorDoldur(String klasor) {
        //sendKeys(klasorInput, klasor, false);
        txtKlasor.sendKeys(klasor);
        return this;
    }

    public KlasorEvrakIslemleriPage aramaKriteriDoldur(String aramaKriteri) {
        //sendKeys(aramaKriteriInput, aramaKriteri, false);
        txtAramaKriteri.sendKeys(aramaKriteri);
        return this;
    }

    public KlasorEvrakIslemleriPage evrakTarihiDoldur(String evrakTarihi) {
        //sendKeys(evrakTarihiInput, evrakTarihi, false);
        txtEvrakTarihi.sendKeys(evrakTarihi);
        return this;
    }

    public KlasorEvrakIslemleriPage evrakTipiSec(String evrakTipi) {
        //selectCombobox(evrakTipiSelectbox, evrakTipi);
        cmbEvrakTipi.selectOption(evrakTipi);
        return this;
    }

    public KlasorEvrakIslemleriPage altKlasorlerDahilSec(boolean altKlasorler) {
        //setCheckbox(altKlasorlerDahilCheckbox, altKlasorler);
        chkAltKlasorlerDahil.setSelected(altKlasorler);
        return this;
    }

    public KlasorEvrakIslemleriPage ara() {
        //click(araButton);
        btnAra.click();
        return this;
    }

    public KlasorEvrakIslemleriPage klasoreKopyala() {
        //click(klasoreKopyalaButton);
        btnKlasoreKopyala.click();
        return this;
    }

    public KlasorEvrakIslemleriPage klasoreTasi() {
        //click(klasoreTasiButton);
        btnKlasoreTasi.click();
        return this;
    }
}
