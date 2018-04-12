package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ImzaladiklarimPageKapatmaIslemleri extends MainPage {

    ElementsCollection tblImzaladiklarim = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");// span[class='ui-chkbox-icon']");

    @Step("Kapatma işlemleri altında İmza bekleyenler sayfası aç")
    public ImzaladiklarimPageKapatmaIslemleri openPage() {
        solMenu2(SolMenuData.KapatmaIslemleri.Imzaladiklarim);
        return this;
    }

    @Step("Evrak listesinde {konu} konulu evrak olmali mi? {evrakOlmali}")
    public ImzaladiklarimPageKapatmaIslemleri evrakKontrol(String konu, boolean evrakOlmali){
        if(evrakOlmali == true){
            tblImzaladiklarim
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldBe(visible);
            Allure.addAttachment("Evrakın listelendiği görülür.", "");
        }
        else {
            tblImzaladiklarim
                    .filterBy(text("Konu: " + konu))
                    .first()
                    .shouldNotBe(visible);
            Allure.addAttachment("Evrakın listelenmediği görülür.", "");
        }
        return this;
    }

}
