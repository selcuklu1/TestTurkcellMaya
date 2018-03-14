package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class TakibimdekiEvraklarPage extends MainPage {

    ElementsCollection tblTakibimdekiEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");


    @Step("Takibimdeki Evraklar sayfasını aç")
    public TakibimdekiEvraklarPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.TakibimdekiEvraklar);
        return this;
    }

    @Step("{konu} konulu evrak evrak listesinde olmalı mı? : {evrakOlmali}.")
    public TakibimdekiEvraklarPage evrakKontrol(String konu, boolean evrakOlmali){
        if(evrakOlmali == true){
            tblTakibimdekiEvraklar
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldBe(visible);
        }
        else {
            tblTakibimdekiEvraklar
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldNotBe(visible);
        }
        return this;
    }

    @Step("{konu} konulu evrak evrak listesinde olduğu görülür.")
    public TakibimdekiEvraklarPage takiptenCikart(String konu) {
        tblTakibimdekiEvraklar
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class,'document-unfollow')]/..")
                .click();

        return this;
    }


}

