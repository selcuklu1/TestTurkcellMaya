package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ImzaBekleyenlerPage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");

    @Step("İmza bekleyenler sayfası aç")
    public ImzaBekleyenlerPage openPage(){
        solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler);
        return this;
    }

    public ImzaBekleyenlerPage dogruKonuVeNoKontrol(String toplantiNo, String konu){

        tableKararIzlemeEvraklar
                .filterBy(Condition.text(toplantiNo)).filterBy(Condition.text(konu)).get(0).shouldBe(visible);
        return this;

    }

    public ImzaBekleyenlerPage evrakSec(String toplantiNo, String konu){
       /* tableKararIzlemeEvraklar
                .filterBy(Condition.text(toplantiNo)).filterBy(Condition.text(konu)).get(0).click(); */
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu)).first()
                .$("[class='ui-dt-c']").click();
        return this;


    }

}
