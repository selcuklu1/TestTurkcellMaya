package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class GenelEvrakRaporuPage extends MainPage {

    SelenideElement txtEvrakNo = $(By.id("genelEvrakRaporuForm:evrakNoId"));
    SelenideElement btnSorgula = $(By.id("genelEvrakRaporuForm:sorgulaButton"));
    ElementsCollection tblEvrakListesi = $$("[id='genelEvrakRaporuForm:genelEvrakDataTable_data'] tr[role='row']");
    SelenideElement btnDetay = $("[id^='genelEvrakRaporuForm:genelEvrakDataTable'][id$='evrakGosterButton']");

    public GenelEvrakRaporuPage openPage() {
        new UstMenu().ustMenu("Genel Evrak Raporu");
        return this;
    }

    @Step("Evrak No doldur")
    public GenelEvrakRaporuPage evrakNoDoldur(String evrakNo) {
        txtEvrakNo.sendKeys(evrakNo);
        return this;
    }

    @Step("Sorgula butonu")
    public GenelEvrakRaporuPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Tablodao Evrak no kontrolü")
    public GenelEvrakRaporuPage tabloEvrakNoKontrol(String evrakNo) {
        tblEvrakListesi
                .filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
        return this;
    }

    @Step("Tablodao detay butonuna tıkla")
    public GenelEvrakRaporuPage tablodaDetayTikla(String evrakNo) {
        tblEvrakListesi
                .filterBy(Condition.text(evrakNo)).first()
                .$("[id^='genelEvrakRaporuForm:genelEvrakDataTable'][id$='evrakGosterButton']").click();
        return this;
    }

    @Step("Detay ekran kontrolü")
    public GenelEvrakRaporuPage detayEkranınıAcildigiKontrolu() {
        $("[id='windowReadOnlyEvrakDialog'] span").shouldBe(Condition.text("Evrak Detayı"));
        return this;
    }

}
