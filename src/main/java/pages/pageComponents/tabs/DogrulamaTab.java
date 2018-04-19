package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.SearchTable;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 12.03.2018
 * Açıklama:
 */
public class DogrulamaTab extends MainPage {
    private final static String tabName = "Evrak Doğrulama";
    private SelenideElement container;
    private SearchTable searchTable;

    private By deleteButtonuSelector = By.cssSelector(".delete-icon");
    private By updateButtonuSelector = By.cssSelector(".update-icon");

    public DogrulamaTab() {
        container = $("html");
    }

    public DogrulamaTab(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    public void shouldHave(String text) {
        getContainer().shouldHave(text(text));
    }

    @Step(tabName + " tabı aç")
    public DogrulamaTab openTab(boolean... clickIfOpen) {
        if (clickIfOpen.length > 0 || !getTabButtonTextElement().attr("class").equals("tabMenuContainerSelected"))
            getTabButton().click();

        return this;
    }

    @Step(tabName + " sekme butonu bul")
    public SelenideElement getTabButton() {
        return getTabButtonTextElement().$("button");
    }

    private SelenideElement getTabButtonTextElement() {
        return getContainer().$x(".//td[contains(@class,'tabMenuContainer') and .//span[contains(@class,'tabMenu') and .='" + tabName + "']]");
    }

    @Step(tabName + " tab {conditions} olmalı")
    public DogrulamaTab tabKontrol(Condition... conditions) {
        getTabButtonTextElement().should(conditions);
        return this;
    }

    //Use element hidden use checkboxSelect()
    public SelenideElement getDogrulanabilirCheckbox(){
        return container.$("table[id$='dogrulanabilirPanelGrid'] input[type=checkbox]");
    }

    @Step("Doğrulanabilir checkbox {secilir} secilir")
    public DogrulamaTab dogrulanabilirSec(boolean secilir){
        checkboxSelect(getDogrulanabilirCheckbox(), secilir);
        return this;
    }

    @Step("Doğrulanabilir checkbox {secilir} olmalı")
    public DogrulamaTab dogrulanabilirSeciliKontrolu(boolean secilir){
        Assert.assertEquals( getDogrulanabilirCheckbox().shouldBe(visible).isSelected(), secilir, "Doğrulanabilir checkbox");
        return this;
    }


    //Value "Aktarılacak",
    public SelenideElement getAktarilmaDurumuLabel() {
        return container.$("table[id$='aktarilmaDurumuPanelGrid'] label.columnLabel");
    }

    @Step("Aktarılma Durumu değeri {aktarilmaDurum} olmalı")
    public DogrulamaTab aktarilmaDurumuKontrolu(String aktarilmaDurum){
        Assert.assertEquals(getAktarilmaDurumuLabel().shouldBe(visible).text(), aktarilmaDurum, "Aktarılma Durumu değeri");
        return this;
    }

    @Step("Aktarılma Durumu değeri {aktarilmaDurum} olmalı")
    public DogrulamaTab aktarilmaDurumuKontrolu(Condition aktarilmaDurum){
        getAktarilmaDurumuLabel().should(aktarilmaDurum);
        return this;
    }

    @Step("Aktarılma Durumu değeri {aktarilmaDurum} olmalı")
    public DogrulamaTab aktarilmaDurumuKontroluAssert(String aktarilmaDurum){
        Assert.assertEquals(getAktarilmaDurumuLabel().text(), aktarilmaDurum, "Aktarılmam durumu");
        return this;
    }


    public SelenideElement getIslemZamaniLabel() {
        return container.$("table[id$='islemZamaniPanelGrid'] label.columnLabel");
    }

    @Step("İşlem Zamanı değeri {islemZamani} olmalı")
    public DogrulamaTab islemZamaniKontrolu(String islemZamani){
        Assert.assertEquals(getIslemZamaniLabel().shouldBe(visible).text(), islemZamani, "İşlem Zamanı değeri");
        return this;
    }

    @Step("İşlem Zamanı değeri {islemZamani} olmalı")
    public DogrulamaTab islemZamaniKontrolu(Condition islemZamani){
        getIslemZamaniLabel().should(islemZamani);
        return this;
    }


    public SelenideElement getEvrakDogrulaGuncelleButton() {
        return container.$("button[id$='evrakDogrulaGuncelleButton']");
    }
    
    @Step("Güncelle butona tıklanır")
    public DogrulamaTab guncelle(){
        getEvrakDogrulaGuncelleButton().click();
        return this;
    }

    @Step("Güncelle butona her {intervalSeconds} saniye tıklanır, toplam bekleme zamanı - {timeoutSeconds} saniye")
    public DogrulamaTab guncelleTimeout(int intervalSeconds, int timeoutSeconds){
        for (int i = 0; i < timeoutSeconds; i++) {
            guncelle();
            Selenide.sleep(intervalSeconds*1000);
        }
        return this;
    }

    @Step("Aktarılma durumu ve islem zamani kontrolü")
    public DogrulamaTab aktarilmaDurumuVeIslemZamaniKontorlu(String aktarilmaDurumu, Condition islemZamani, int waitSeconds){

        for (int i = 0; i < waitSeconds; i+=10) {
            Selenide.sleep(10000);
            guncelle();
        }

        Selenide.sleep(5000);

        aktarilmaDurumuKontroluAssert(aktarilmaDurumu);
        islemZamaniKontrolu(islemZamani);

        return this;
    }

    @Step("Aktarılma durumu ve islem zamani kontrolü")
    public DogrulamaTab aktarilmaDurumuVeIslemZamaniKontorlu(String aktarilmaDurumu, Condition islemZamani, int intervalSeconds, int timeoutSeconds){

        for (int i = 0; i < timeoutSeconds; i+=intervalSeconds) {
            Selenide.sleep(intervalSeconds*1000);
            guncelle();

            if (getAktarilmaDurumuLabel().has(text(aktarilmaDurumu)))
                break;
        }

        Selenide.sleep(5000);

        aktarilmaDurumuKontroluAssert(aktarilmaDurumu);
        islemZamaniKontrolu(islemZamani);

        return this;
    }
    
}
