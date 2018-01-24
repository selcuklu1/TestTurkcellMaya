package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-01-23
 * Proje: Türksat Functional Test Automation
 * Class: "Koordine Parafladıklarım" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class KoordineParafladiklarimPage extends MainPage {

    ElementsCollection tblKoordineParafladiklarimEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    //Önizleme
    SelenideElement tabEvrakEkleri = $(By.xpath("//*[contains(text(),'Evrak Ekleri')]"));
    SelenideElement tabIlgiBilgileri = $(By.xpath("//*[contains(text(),'İlgi Bilgileri')]"));
    SelenideElement tabIlisikBilgileri = $(By.xpath("//*[contains(text(),'İlişik Bilgileri')]"));
    SelenideElement accordionEvrakEkleri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnl:0'] [class='onizlemeFrame']");
    SelenideElement accordionIlgiBilgileri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnlI:0'] [class='onizlemeFrame']");
    SelenideElement accordionIlisikBilgileri = $("[id^='mainPreviewForm:j_idt'] [id*='accpnlIlisik:0'] [class='onizlemeFrame']");


    @Step("Parafladıklarım sayfası aç")
    public KoordineParafladiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.KoordineParafladiklarim);
        String pageTitle = SolMenuData.IslemYaptiklarim.KoordineParafladiklarim.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Koordine Parafladıklarım listesinden evrak önizlemede aç")
    public KoordineParafladiklarimPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblKoordineParafladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Koordine Parafladıklarım listesinde evrak kontrolu")
    public KoordineParafladiklarimPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblKoordineParafladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Evrak Ek/İlgi/İlişikler tablarının geldiği kontrolu")
    public KoordineParafladiklarimPage tabKontrolleri() {

        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);
        Assert.assertEquals(tabIlisikBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Parafladıklarım/Evrak Ekleri tabını aç")
    public KoordineParafladiklarimPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Parafladıklarım/İlgi Bilgieri tabını aç")
    public KoordineParafladiklarimPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }

    @Step("Parafladıklarım/İlişik Bilgieri tabını aç")
    public KoordineParafladiklarimPage tabIlisikBilgileriAc() {
        tabIlisikBilgileri.click();
        return this;
    }

    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public KoordineParafladiklarimPage evrakEkleriAccordionKontrol() {
        Assert.assertEquals(accordionEvrakEkleri.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlgi Bilgileri Accordion kontrolu")
    public KoordineParafladiklarimPage ilgiBilgieriAccordionKontrol() {
        Assert.assertEquals(accordionIlgiBilgileri.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlişik Bilgileri Accordion kontrolu")
    public KoordineParafladiklarimPage ilisikBilgieriAccordionKontrol() {
        Assert.assertEquals(accordionIlisikBilgileri.isDisplayed(), true);
        return this;
    }
}
