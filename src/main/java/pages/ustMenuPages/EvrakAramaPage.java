package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class EvrakAramaPage extends MainPage {


    SelenideElement radiobtnGidenEvrak = $(By.xpath("//table[@id='menuYonetimiTabView:hizliEvrakAramaForm:evrakTipiRadioId']//td[3]//div[2]"));
    SelenideElement cmbEvrakınAranacagiYer = $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:evrakAramaAranacakYer_id"));
    SelenideElement cmbAramaKriteri = $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:gelenEvrakSelectOneMenuId_input"));
    SelenideElement txtAramaKriteri = $("[id='menuYonetimiTabView:hizliEvrakAramaForm:aramaKriterInputAlani'] input");
    SelenideElement btnAra = $(By.id("menuYonetimiTabView:hizliEvrakAramaForm:hizliEvrakAraButton"));
ElementsCollection tblListe = $$("[id='menuYonetimiTabView:hizliEvrakAramaForm:evrakAramaDataTableSolr_data'] tr[role='row']");


    //Detaylı Arama Tabı
    SelenideElement btnDetayliArama = $("a[href='#menuYonetimiTabView:detayliEvrakAramaTab']");
    SelenideElement cmbDetayliAramaKriteri = $(By.id("menuYonetimiTabView:detayliEvrakAramaForm:accordionPanel:detayliEvrakAramaAranacakYer_id"));

    public EvrakAramaPage openPage() {
        new UstMenu().ustMenu("Evrak Arama");
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
    public EvrakAramaPage aramaKriteriSec(String aramaKriteri) {
        cmbAramaKriteri.selectOption(aramaKriteri);
        return this;
    }

    @Step("Arama Kriteri doldur")
    public EvrakAramaPage aramaKriteriDoldur(String value) {
        txtAramaKriteri.sendKeys(value);
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
    @Step("Tablodao detay butonuna tıkla")
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
        $(By.xpath("//div[@id='windowItemInfoDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
        islemPenceresiKapatmaOnayiPopup("Kapat");

        return this;
    }
    @Step("Detaylı arama tab")
    public EvrakAramaPage detaylıAramaTab(){
        btnDetayliArama.click();
        return this;
    }

}
