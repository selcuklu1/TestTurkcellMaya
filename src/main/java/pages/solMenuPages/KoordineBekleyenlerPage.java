package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-01-24
 * Proje: Türksat Functional Test Automation
 * Class: "Koordine Bekleyenler" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class KoordineBekleyenlerPage extends MainPage {

    ElementsCollection tblKoordineBekleyenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");


    @Step("Koordine Bekleyenler sayfası aç")
    public KoordineBekleyenlerPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.KoordineBekleyenler);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.KoordineBekleyenler.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Koordine Bekleyenler listesinde evrak kontrolu")
    public KoordineBekleyenlerPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblKoordineBekleyenEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Koordine Bekleyenler listesinden evrak önizlemede aç")
    public KoordineBekleyenlerPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblKoordineBekleyenEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

}
