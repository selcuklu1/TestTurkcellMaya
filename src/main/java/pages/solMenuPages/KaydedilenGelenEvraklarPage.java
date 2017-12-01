package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KaydedilenGelenEvraklarPage extends MainPage {
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt349_label"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement chkKaydettiklerim = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kaydettiklerimCheckbox"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection  tblKaydedilenGelenEvraklar = $$(By.id("mainInboxForm:inboxDataTable_data"));
    ElementsCollection tblIlkRapor = $$(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    ElementsCollection tblKaydedilenGelenEvraklar2 = $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[role=row] div[class=searchText]");


//    Evrak Detayları Sayfası

    SelenideElement txtEvrakBilgileriListKonuKodu = $("[id$='konuKoduLov:LovText']");
    SelenideElement txtEvrakBilgileriListKonu = $("[id$='konuTextArea']");
    SelenideElement cmbEvrakBilgileriListEvrakTuru = $("[id$='evrakTuruCombo']");
    SelenideElement cmbEvrakBilgileriListEvrakDili = $("[id$='evrakDili']");

    SelenideElement dateTxtEvrakBilgileriListEvrakTarihi = $("[id$='evrakTarihi_input']");
    SelenideElement cmbEvrakBilgileriListGizlilikDerecesi = $("[id$='guvenlikKodu']");
    SelenideElement cmbEvrakBilgileriListKisiKurum = $("[id$='kisiKurum']");
    SelenideElement txtEvrakBilgileriListEvrakSayiTextAreaSag = $("[id$='evrakSayiTextAreaSag']");
    BelgenetElement cmbEvrakBilgileriListGeldigiKisi = comboLov("[id$='geldigiGercekKisiLov:LovText']");
    SelenideElement cmbEvrakBilgileriListEvrakGelisTipi = $("[id$='evrakGelisTipi']");
    SelenideElement cmbEvrakBilgileriListIvedilik = $("[id$='ivedilik']");
    SelenideElement txtEvrakBilgileriListMiat = $("[id$=miatCalendar_input");
    SelenideElement txtEvrakBilgileriListAciklama = $(By.id("inboxItemInfoForm:evrakBilgileriList:14:j_idt12226"));


    @Step("Kaydedilen gelen evraklar sayfası aç")
    public KaydedilenGelenEvraklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KaydedilenGelenEvraklar);
        return this;
    }

    @Step("Filtrele alanını aç")
    public KaydedilenGelenEvraklarPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public KaydedilenGelenEvraklarPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public KaydedilenGelenEvraklarPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public KaydedilenGelenEvraklarPage tarihDoldur(String tarih) {
        dateTxtTarih.sendKeys(tarih);
        dateTxtTarih.pressEnter();
        return this;
    }

    @Step("Tablodan rapor seç")
    public KaydedilenGelenEvraklarPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tabloda evrak no kontrolu")
    public KaydedilenGelenEvraklarPage tabloKontrolu(String evrakNo) {
        int row = tblKaydedilenGelenEvraklar2.filterBy(Condition.text(evrakNo)).size();
        System.out.println(row);
        Assert.assertEquals(row, 1);
        return this;
    }

    @Step("Tabloda ilk evrak içerik tıklama")
    public KaydedilenGelenEvraklarPage tabloRaporIcerik(String evrakNo) {
        tblIlkRapor.click();
        return this;
    }

    @Step("Tabloda evrak noya göre İçerik tıklama")
    public KaydedilenGelenEvraklarPage tabloEvrakNoileIcerikSec(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo)).shouldHaveSize(1)
                .first()
                .$("[id$='detayGosterButton']").click();
        return this;
    }
}
