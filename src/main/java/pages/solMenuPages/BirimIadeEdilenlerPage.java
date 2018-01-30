package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$$;

public class BirimIadeEdilenlerPage extends MainPage {

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    public BirimIadeEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimeIadeEdilenler);
        return this;
    }
    @Step("Evrak numara ile evrak seçilir : \"{evrakNo}\" ")
    public BirimIadeEdilenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }
}
