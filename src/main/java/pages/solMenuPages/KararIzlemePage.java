package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.KararYazisiOlusturPage;
import pages.ustMenuPages.KlasorYonetimiPage;
import pages.ustMenuPages.YazismaKurallariYonetimiPage;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KararIzlemePage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");
    SelenideElement chkSinirsizYazilabilir = $(By.id("grupBirimTipleriEditorForm:grupTipiSinirsizYazilabilirId"));
    SelenideElement btnTopluOnayaSun = $(By.id("mainInboxForm:inboxDataTable:kararIzlemeTopluOnayId"));
    SelenideElement btnTopluOnaySunEvet = $(By.id("kararIzlemeTopluOnayaGonderConfirmForm:kararIzlemeTopluOnayEvetButton"));
    SelenideElement getBtnTopluOnayaSunHayir = $(By.id("kararIzlemeTopluOnayaGonderConfirmForm:kararIzlemeTopluOnayHayirButton"));
    ElementsCollection tblIlkEvrak = $$(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection divEvrakNotlari = $$(By.id("evrakOnizlemeNotlarDialogId"));
    SelenideElement cmbEvrakDurumu = $("select[id^='mainInboxForm:inboxDataTable:filtersAccordion:j_idt']");
    SelenideElement btnFiltrele = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:kararIzlemeFiltreButton"));
    SelenideElement filtrelerAcordion = $("[id^='mainInboxForm:inboxDataTable:filtersAccordion'] [class='ui-accordion-header ui-helper-reset ui-state-default ui-corner-all']");

    @Step("Karar izleme sayfası aç")
    public KararIzlemePage openPage(){
        solMenu(SolMenuData.KurulIslemleri.KararIzleme);
        return this;
    }

    public KararIzlemePage filtreler(){
        filtrelerAcordion.click();
        return this;
    }

    @Step("Filtrele")
    public KararIzlemePage filtrele(){
        btnFiltrele.click();
        return this;
    }

    @Step("Evrak durumu seç")
    public KararIzlemePage evrakDurumuSec(String value){
        cmbEvrakDurumu.selectOptionByValue(value);
        return this;
    }

    @Step("İlk evrak seç")
    public KararIzlemePage ilkEvrakSec(String toplantiNo, String konu){
        tblIlkEvrak.
                filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu))
                .first().click();
        return this;
    }

    @Step("İade bilgisi gelidği görünür")
    public KararIzlemePage iadeBilgisiGorme(String not){
        divEvrakNotlari.filterBy(Condition.text(not)).get(0).shouldBe(visible);
        return this;
    }

    @Step("Evrak seç")
    public KararIzlemePage evrakSec(String toplantiNo, String konu, String toplantiTarih){
               tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                       .filterBy(Condition.text(konu)).filterBy(Condition.text(konu))
                       .filterBy(Condition.text(toplantiTarih)).first()
                       .$("[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }
    
    @Step("Evrak geldiği görme")
    public KararIzlemePage evrakGeldigiGorme(String toplantiNo, String konu, String toplantiTarih){
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu)).filterBy(Condition.text(konu))
                .filterBy(Condition.text(toplantiTarih)).filterBy(Condition.visible);
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
