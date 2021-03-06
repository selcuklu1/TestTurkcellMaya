package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KlasorEvrakIslemleriPage extends MainPage {

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
    SelenideElement tblKlasorEvrakIslemleri = $(By.id("klasorEvrakIslemleriListingForm:klasorEvrakDataTable"));
    SelenideElement f = $(By.xpath("//div[@id='klasorEvrakIslemleriListingForm:filterPanel']//a[text()='Sorgulama ve Filtreleme']/parent::h3"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='klasorEvrakIslemleriListingForm:klasorEvrakDataTable_data'] > tr[role='row']");
    SelenideElement btnEvrakiKlasordenCikarEvet = $(By.id("baseConfirmationDialog:confirmButton"));

    public KlasorEvrakIslemleriPage birimDoldur(String birim) {
        //sendKeys(birimInput, birim, false);
        txtBirim.sendKeys(birim);
        return this;
    }

    @Step("Filtrele alanını aç")
    public KlasorEvrakIslemleriPage filtreleAc() {
        f.click();
        return this;
    }


    @Step("Klasor evrak işlemleri sayfası aç")
    public KlasorEvrakIslemleriPage openPage() {
        ustMenu(UstMenuData.KlasorIslemleri.KlasorEvrakIslemleri);
        return this;
    }

    @Step("Evrak geldiği görülür")
    public KlasorEvrakIslemleriPage evrakGeldigiGorme(String toplantiNo, String konu) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu)).filterBy(Condition.text(konu))
                .filterBy(Condition.visible);
        return this;
    }

    @Step("Evrak geldiği görülür")
    public KlasorEvrakIslemleriPage evrakGeldigiGorme(String konu) {
        boolean status = findElementOnTableByColumnInputInAllPages(tblKlasorEvrakIslemleri, 2, konu).isDisplayed();
        Assert.assertEquals(status, true);
        return this;
    }

    @Step("Klasör alanını doldur")
    public KlasorEvrakIslemleriPage klasorDoldur(String klasor) {
        txtKlasor.selectLov(klasor);
        return this;
    }

    public KlasorEvrakIslemleriPage klasorDoldurwithDetail(String klasor, String title) {
        txtKlasor.type(klasor).getDetailItems().filterBy(text(title)).first().click();
        return this;
    }

    public KlasorEvrakIslemleriPage aramaKriteriDoldur(String aramaKriteri) {
        //sendKeys(aramaKriteriInput, aramaKriteri, false);
        txtAramaKriteri.sendKeys(aramaKriteri);
        return this;
    }

    @Step("Evrak tarihi alanını doldur")
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

    @Step("Ara")
    public KlasorEvrakIslemleriPage ara() throws InterruptedException {
        //click(araButton);
        Thread.sleep(2000);
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

    @Step("Evrak listesinde {konu} konulu evrak olmali mi? {evrakOlmali}")
    public KlasorEvrakIslemleriPage evrakListesiKontrol(String konu, boolean evrakOlmali){
        if(evrakOlmali == true){

            tblEvrakListesi
                    .filterBy(text(konu))
                    .first()
                    .shouldBe(visible);
            Allure.addAttachment("Evrakın listelendiği görülür.", "");
        } else {

            tblEvrakListesi
                    .filterBy(text(konu))
                    .first()
                    .shouldNotBe(visible);
            Allure.addAttachment("Evrakın listelenmediği görülür.", "");
        }

        return this;
    }


    @Step("{konu} Konulu evrak satırı üzerinde 'Evrakı Klasörden Çıkar' butonuna tıkla ve İşlem Onayı ekranında Evet butonuna tıkla")
    public KlasorEvrakIslemleriPage evrakiKlasordenCikar(String konu){

        tblEvrakListesi
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class, 'remove-from-folder-icon')]/..")
                .click();

        btnEvrakiKlasordenCikarEvet.click();

        return this;
    }

    @Step("{konu} Konulu evrak satırı üzerinde 'Evrakı Klasörden Çıkar' butonunun geldiği görülür. ")
    public KlasorEvrakIslemleriPage evrakiKlasordenCikarButonKontrolu(String konu){

        tblEvrakListesi
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class, 'remove-from-folder-icon')]/..")
                .shouldBe(visible);

        return this;
    }
}
