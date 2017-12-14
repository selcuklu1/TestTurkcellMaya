package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.solMenuPages.ImzaladiklarimPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class    KlasorEvrakIslemleriPage extends MainPage {

     SelenideElement txtBirim = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:birimLov:LovText"));
     BelgenetElement txtKlasor = comboLov(By.id("klasorEvrakIslemleriListingForm:filterPanel:klasorLov:LovText"));
     SelenideElement txtAramaKriteri = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakKonusuInput"));
     SelenideElement txtEvrakTarihi = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakTarihiCalendar_input"));
     SelenideElement cmbEvrakTipi = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:evrakTipiSelect"));
     SelenideElement chkAltKlasorlerDahil = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:altBirimlerDahilCheckbox_input"));
     SelenideElement btnAra = $(By.id("klasorEvrakIslemleriListingForm:filterPanel:searchEvrakButton"));
     SelenideElement btnKlasoreKopyala = $(By.id("klasorEvrakIslemleriListingForm:copyAllSelectedEvrakButton"));
     SelenideElement btnKlasoreTasi = $(By.id("klasorEvrakIslemleriListingForm:moveAllSelectedEvrakButton"));
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");



    public KlasorEvrakIslemleriPage birimDoldur(String birim) {
        //sendKeys(birimInput, birim, false);
        txtBirim.sendKeys(birim);
        return this;
    }

    @Step("Klasor evrak işlemleri sayfası aç")
    public KlasorEvrakIslemleriPage openPage(){
        ustMenu("Klasör Evrak İşlemleri");
        return this;
    }

    @Step("Evrak geldiği görülür")
    public KlasorEvrakIslemleriPage evrakGeldigiGorme(String toplantiNo, String konu){
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu)).filterBy(Condition.text(konu))
                .filterBy(Condition.visible);
        return this;
    }

    public KlasorEvrakIslemleriPage klasorDoldur(String klasor) {
        txtKlasor.selectLov(klasor);
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
