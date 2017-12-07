package pages.solMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class GundemIzlemePage extends MainPage {

    BelgenetElement txtKlasor = comboLov(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gundemKlasorLov:LovText"));
    SelenideElement btnFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gundemFiltrele"));
    @Step("Klasor seç")
    public GundemIzlemePage kapatilanKlasorSec(String klasor, String klasorAdi){
        txtKlasor.selectLov(klasor);
        btnFiltrele.click();
        return this;
    }

    @Step("Kurul işlemleri sayfası aç")
    public GundemIzlemePage openPage(){
        solMenu(SolMenuData.KurulIslemleri.GundemIzleme);
        return this;
    }
    
    @Step("Klasör doldur")
    public GundemIzlemePage klasorDoldur(String klasor){
        txtKlasor.selectLov(klasor);
        return this;
    }
}
