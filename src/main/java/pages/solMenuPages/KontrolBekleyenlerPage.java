package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KontrolBekleyenlerPage extends MainPage {

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    ElementsCollection tableKontrolListesi = $$("table[id^='mainPreviewForm:j_idt'] tbody tr[role='row']");
    SelenideElement bntKontrolEt = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td[class='buttonMenuContainerDefault'] span[class='ui-button-icon-left ui-icon kontrol']");
    SelenideElement txtKontrolEtNot = $("[id$='paraflaPanelGrid'] textarea");
    SelenideElement btnKontrolEtGonder = $(By.id("mainPreviewForm:imzalaButton"));
    SelenideElement panelOnizlemeKontrol = $(By.id("mainPreviewForm:onizlemePanel"));


    @Step("Kontrol bekleyenler sayfası aç")
    public KontrolBekleyenlerPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.KontrolBekleyenler);
        return this;
    }

    @Step("Parafladıklarım listesinden evrak detay göster")
    public KontrolBekleyenlerPage konuyaGoreEvrakDetayGoster(String konu) {

        tableEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='detayGosterButton']").click();

        return this;
    }
    @Step("Evrak seç")
    public KontrolBekleyenlerPage evrakSec(String konu, String geldigiYer, String kayitTarihiSayi) {
        tableEvraklar
                .filterBy(text(konu))
                .filterBy(text(geldigiYer))
                .filterBy(text(kayitTarihiSayi))
                .get(0).click();
        return this;
    }

    @Step("Kontrol Et Panel Kontrolü")
    public KontrolBekleyenlerPage kontrolEtPanelKontrolu(String user,String tip) {
        boolean durum = tableKontrolListesi
                .filterBy(text(tip))
                .filterBy(text(user))
                .size() > 0;
        Assert.assertEquals(durum,true,tip + ":"  + user + " listelendi");
        Allure.addAttachment(tip + ":"  + user + " listelendi","");
        takeScreenshot();
        return this;
    }



    @Step("Evrak seç")
    public KontrolBekleyenlerPage evrakSec(String konu) {
        tableEvraklar
                .filterBy(text(konu))
                .get(0).click();
        return this;
    }

    @Step("Kontrol et not alanını \"{not}\" doldur")
    public KontrolBekleyenlerPage kontrolEtNotDoldur(String not) {
        txtKontrolEtNot.setValue(not);
        return this;
    }

    @Step("Gönder tıklanır")
    public KontrolBekleyenlerPage kontrolEtGonder() {
        btnKontrolEtGonder.click();
        return this;
    }

    @Step("Kontrol et")
    public KontrolBekleyenlerPage kontrolEt() {
        bntKontrolEt.parent().click();
        return this;
    }

    @Step("Önizleme Kontrolü")
    public KontrolBekleyenlerPage onizlemeKontrol() {
        Assert.assertEquals(panelOnizlemeKontrol.isDisplayed(),true,"Önizleme Kontrol");
        Allure.addAttachment("Önizleme Kontrol", "");
        return this;
    }

    @Step("Kontrol Button Kontrolü")
    public KontrolBekleyenlerPage kontrolKontrolu() {
        Assert.assertEquals(bntKontrolEt.isDisplayed(),true,"Önizleme Kontrol");
        Allure.addAttachment("Önizleme Kontrol", "");
        return this;
    }

    @Step("Kontrol Button Tıklama")
    public KontrolBekleyenlerPage kontrolTıklama() {
        bntKontrolEt.click();
        return this;
    }




}
