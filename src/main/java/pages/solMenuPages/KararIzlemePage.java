package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.KararYazisiOlusturPage;
import pages.ustMenuPages.YazismaKurallariYonetimiPage;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KararIzlemePage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");
    SelenideElement chkSinirsizYazilabilir = $(By.id("grupBirimTipleriEditorForm:grupTipiSinirsizYazilabilirId"));
    SelenideElement btnTopluOnayaSun = $(By.id("mainInboxForm:inboxDataTable:kararIzlemeTopluOnayId"));
    SelenideElement btnTopluOnaySunEvet = $(By.id("kararIzlemeTopluOnayaGonderConfirmForm:kararIzlemeTopluOnayEvetButton"));
    SelenideElement getBtnTopluOnayaSunHayir = $(By.id("kararIzlemeTopluOnayaGonderConfirmForm:kararIzlemeTopluOnayHayirButton"));
    @Step("Karar izleme sayfası aç")
    public KararIzlemePage openPage(){
        solMenu(SolMenuData.KurulIslemleri.KararIzleme);
        return this;
    }


    public KararIzlemePage evrakSec(String toplantiNo, String konu, String toplantiTarih){
               tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo)).filterBy(Condition.text(konu)).filterBy(Condition.text(konu)).filterBy(Condition.text(toplantiTarih)).first().$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }
    
    @Step("Toplu onay sun")
    public KararIzlemePage topluOnayaSun(boolean secim){
        btnTopluOnayaSun.click();
        if (secim == true){
        btnTopluOnaySunEvet.click();
        }
        else{
        getBtnTopluOnayaSunHayir.click();
        }
        return this;
    }

}
