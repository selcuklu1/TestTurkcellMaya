package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class EvrakAramaPage extends MainPage {

    //Evrak Arama Tab
    SelenideElement radiobtnGidenEvrak = $(By.xpath("//table[@id='menuYonetimiTabView:hizliEvrakAramaForm:evrakTipiRadioId']//td[3]//div[2]"));
    SelenideElement cmbEvrakınAranacagiYer = $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:evrakAramaAranacakYer_id"));
    BelgenetElement cmbAramaKriteri = comboLov(By.id("menuYonetimiTabView:hizliEvrakAramaForm:gidenEvrakSelectOneMenuId_label"));
    SelenideElement txtAramaKriteri = $("[id='menuYonetimiTabView:hizliEvrakAramaForm:aramaKriterInputAlani'] input");
    SelenideElement btnAra = $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:hizliEvrakAraButton"));
    ElementsCollection tblListe = $$("[id='menuYonetimiTabView:hizliEvrakAramaForm:evrakAramaDataTableSolr_data'] tr[role='row']");


    //Detaylı Arama Tabı
    SelenideElement btnDetayliAramaTab = $("a[href='#menuYonetimiTabView:detayliEvrakAramaTab']");
    SelenideElement cmbDetayliAranacagiYer = $(By.id("menuYonetimiTabView:detayliEvrakAramaForm:accordionPanel:detayliEvrakAramaAranacakYer_id"));
    SelenideElement txtDetayliAramaKriter = $(By.id("menuYonetimiTabView:detayliEvrakAramaForm:accordionPanel:aramaKriteriText_id"));
    SelenideElement btnDetaylıAra = $(By.id("menuYonetimiTabView:detayliEvrakAramaForm:detayliAraButton_id"));
    ElementsCollection tblDetayliAramaTabListe = $$("[id='menuYonetimiTabView:detayliEvrakAramaForm:evrakAramaDataTable_data'] tr[role='row']");

    public EvrakAramaPage openPage(){
        ustMenu(UstMenuData.EvrakIslemleri.EvrakArama);
        return this;
    }

    @Step("Giden Evrak seç")
    public EvrakAramaPage gidenEvrakSec() {
        radiobtnGidenEvrak.click();
        return this;
    }

    @Step("Evrakın Aranaği Yer seç")
    public EvrakAramaPage evrakinAranacagiYerSec(String aranacakYer) {
        cmbEvrakınAranacagiYer.selectOption(aranacakYer);
        return this;
    }

    @Step("Arama Kriteri seç")
    public EvrakAramaPage aramaKriteriSec(String aramaKriteriTxt) {
        cmbAramaKriteri.selectComboBox(aramaKriteriTxt);
        return this;
    }

    @Step("Arama Kriteri seç")
    public EvrakAramaPage aramaKriteriSecwithValue(String aramaKriteriValue) {
        cmbAramaKriteri.selectOptionByValue(aramaKriteriValue);
        return this;
    }

    @Step("Arama Kriteri doldur")
    public EvrakAramaPage aramaKriteriDoldur(String value) {
        txtAramaKriteri.sendKeys(value);
        return this;
    }

    @Step("Arama Sayısı Kriteri Kontrol")
    public EvrakAramaPage aramaSayisiKriteriKontrol() {
        $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:aramaCesitSelectOneMenuId_input")).shouldBe(Condition.visible);
        return this;
    }

    @Step("Sorgula butonu")
    public EvrakAramaPage ara() {
        btnAra.click();
        return this;
    }

    @Step("Tablodao Evrak no kontrolü")
    public EvrakAramaPage tabloEvrakNoKontrol(String evrakNo) {
        tblListe
                .filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
        return this;
    }

    @Step("Tabloda detay butonuna tıkla")
    public EvrakAramaPage tablodaDetayTikla(String evrakNo) {
        tblListe
                .filterBy(Condition.text(evrakNo)).first()
                .$("[id^='menuYonetimiTabView:hizliEvrakAramaForm:evrakAramaDataTableSolr'][id$='aramaSonuclariDetayButton_id']").click();
        return this;
    }

    @Step("Detay ekran kontrolü")
    public EvrakAramaPage detayEkranınıAcildigiKontrolu() {
        $("[id='windowReadOnlyEvrakDialog'] span").shouldBe(Condition.text("Evrak Detayı"));
        return this;
    }

    @Step("Evrak Detay ekranı kapat")
    public EvrakAramaPage detayEkranınıKapat() {
        $(By.xpath("//div[@id='windowReadOnlyEvrakDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        islemPenceresiKapatmaOnayiPopup("Kapat");

        return this;
    }

    @Step("Detaylı arama tab")
    public EvrakAramaPage detaylıAramaTab() {
        btnDetayliAramaTab.click();
        return this;
    }

    @Step("Detalı Arama tab Aranacaği Yer seç")
    public EvrakAramaPage detayTabAranacagiYerSec(String aramaKriteri) {
        cmbDetayliAranacagiYer.selectOption(aramaKriteri);
        return this;
    }

    @Step("Detay Tabı Arama Kriteri doldur")
    public EvrakAramaPage detayTabAramaKriteriDoldur(String value) {
        txtDetayliAramaKriter.sendKeys(value);
        return this;
    }

    @Step("Detay Tabı Ara")
    public EvrakAramaPage detayTabAra() {
        btnDetaylıAra.click();
        return this;
    }

    @Step("Detaylı Arama Tabı Tablo kontrolü")
    public EvrakAramaPage detayTabTablodaKontrolu(String evrakNo, String evrakTipi) {
        tblDetayliAramaTabListe
                .filterBy(Condition.text(evrakNo))
                .filterBy(Condition.text(evrakTipi))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Detaylı Arama Tabı Tabloda detay butonuna tıkla")
    public EvrakAramaPage detayTabTablodaDetayTikla(String evrakNo, String evrakTipi) {
        tblDetayliAramaTabListe
                .filterBy(Condition.text(evrakNo))
                .filterBy(Condition.text(evrakTipi)).first()
                .$("[id^='menuYonetimiTabView:detayliEvrakAramaForm:evrakAramaDataTable'][id$='aramaSonuclariDetayButtonId']").click();
        return this;
    }

    @Step("Evrak Arama ekranı kapat")
    public EvrakAramaPage evrakAramaKapat() {
        $(By.xpath("//div[@id='window1Dialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        islemPenceresiKapatmaOnayiPopup("Kapat");
        return this;
    }


}
