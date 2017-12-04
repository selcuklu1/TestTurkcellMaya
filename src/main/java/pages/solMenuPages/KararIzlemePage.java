package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.KararYazisiOlusturPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KararIzlemePage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");

    @Step("Karar izleme sayfası aç")
    public KararIzlemePage openPage(){
        solMenu(SolMenuData.KurulIslemleri.KararIzleme);
        return this;
    }


    public KararIzlemePage evrakSec(String toplantiNo){
       tableKararIzlemeEvraklar
                .filterBy(Condition.text("Toplantı No: "+ toplantiNo))
                .get(0).click();
        return this;
    }
}
