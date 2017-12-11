package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KurulIslemleriPage extends MainPage {

    BelgenetElement txtKlasor = comboLov(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gundemKlasorLov:LovText"));

    @Step("Kurul işlemleri sayfası aç")
    public KurulIslemleriPage openPage() {
        solMenu(SolMenuData.KurulIslemleri.GundemIzleme);
        return this;
    }

    @Step("Klasör doldur")
    public KurulIslemleriPage klasorDoldur(String klasor) {
        txtKlasor.selectLov(klasor);
        return this;
    }
}
