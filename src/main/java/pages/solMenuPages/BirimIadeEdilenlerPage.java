package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BirimIadeEdilenlerPage extends MainPage {

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement teslimAlButton = $("[id$='teslimAlButton']");
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");

    public BirimIadeEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimeIadeEdilenler);
        return this;
    }

    @Step("Evrak numara ile evrak seçilir : \"{evrakNo}\" ")
    public BirimIadeEdilenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Teslim Al button kontrolü")
    public BirimIadeEdilenlerPage evrakTeslimAlButtonKontrol() {
        teslimAlButton.should(visible);
        return this;
    }

    @Step("Evrak no ile teslim al ve Evet seçeneğinin tıklanması ve Evrakı teslim almak istediğinize emin misiniz? uyarı kontrolü")
    public BirimIadeEdilenlerPage evrakSecNoTeslimAl(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Birime Iade Edilenler sayfasında evrakın listede olmadığının kontrolu")
    public BirimIadeEdilenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }
}