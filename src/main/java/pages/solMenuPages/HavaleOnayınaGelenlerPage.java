package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-01-26
 * Proje: Türksat Functional Test Automation
 * Class: "BirimHavaleOnayınaGelenler" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class HavaleOnayınaGelenlerPage extends MainPage {
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnHavaleOnay = $("button[id^='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']");
    SelenideElement notAlanıDoldur = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement onayıReddet = $(By.id("mainPreviewForm:reddetButton_id"));
    SelenideElement onizlemeOnayla = $(By.id("mainPreviewForm:onaylaButton_id"));

    //      SelenideElement onayıReddetEvet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
//      SelenideElement onayıReddetEvet = $("button[id='inboxItemInfoForm:reddetEvetButton_id']");
//      SelenideElement btnHavaleOnayReddet = $(By.id("inboxItemInfoForm:reddetEvetButton_id"));
    ElementsCollection btnHavaleOnayReddet = $$("[id$='mainPreviewForm:reddetEvetButton_id']");
    ElementsCollection btnHavaleOnayEvet = $$("[id$='mainPreviewForm:evetButton_id']");

//    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
//    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
//    SelenideElement cmbFiltre = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
//    SelenideElement txtSayfadaAra = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
//    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt389_input"));
//    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt394_input"));
//    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
//    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
//    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
//    ElementsCollection tblKaydedilenGelenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");

    @Step("Birim Havale Onayına Gelenler sayfası aç")
    public HavaleOnayınaGelenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayinaGelenler);
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public HavaleOnayınaGelenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak no ile teslim al")
    public HavaleOnayınaGelenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Havale butonunu tıkla")
    public HavaleOnayınaGelenlerPage havaleOnay() {
        btnHavaleOnay.click();
        return this;
    }

    @Step("Not Alanını Doldur")
    public HavaleOnayınaGelenlerPage notAlanınıDoldur(String not) {
        notAlanıDoldur.setValue(not);
        return this;
    }

    @Step("Havale Onay Reddet")
    public HavaleOnayınaGelenlerPage onayıReddet() {
        onayıReddet.click();
        return this;
    }

    @Step("Onizleme Havale Onay")
    public HavaleOnayınaGelenlerPage onizlemeOnayla() {
        onizlemeOnayla.click();
        return this;
    }

    // TODO: Her dönen butonu click yapma
    // 2 tane Evet buttonu dönüyor ve aralarında fark yok
    @Step("Havale Onay Reddet Evet")
    public HavaleOnayınaGelenlerPage onayıReddetEvet() {
        btnHavaleOnayReddet.last().click();
        return this;
    }

    // TODO: Her dönen butonu click yapma
    // 2 tane Evet buttonu dönüyor ve aralarında fark yok
    @Step("Havale Onay Reddet Evet")
    public HavaleOnayınaGelenlerPage onayıOnaylaEvet() {
        btnHavaleOnayEvet.last().click();
        return this;
    }


}
