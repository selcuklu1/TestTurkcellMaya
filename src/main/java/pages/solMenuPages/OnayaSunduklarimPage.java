package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class OnayaSunduklarimPage extends MainPage {

    ElementsCollection tblOnayaSunduklarim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    @Step("Onaya Sunduklarım sayfasını aç")
    public OnayaSunduklarimPage openPage() {
        solMenu(SolMenuData.KapatmaIslemleri.OnayaSunduklarim);
        return this;
    }

    @Step("{konu} konulu evrak evrak listesinde olmalı mı? : {evrakOlmali}.")
    public OnayaSunduklarimPage evrakKontrol(String konu, boolean evrakOlmali){
        if(evrakOlmali == true){
            tblOnayaSunduklarim
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldBe(visible);
        }
        else {
            tblOnayaSunduklarim
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldNotBe(visible);
        }
        return this;
    }



}

