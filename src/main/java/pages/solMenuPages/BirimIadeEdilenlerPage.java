package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class BirimIadeEdilenlerPage extends MainPage {

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement teslimAlButton = $("[id$='teslimAlButton']");
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement onizlemeTeslimAl = $("[id='mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton']");
    ElementsCollection btnTeslimAl = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-delivery']");
    BelgenetElement txtOnaylanacakKisi = comboLov(By.id("mainPreviewForm:onaylayacakKisiLov:LovText"));


    public BirimIadeEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimeIadeEdilenler);
        return this;
    }

    @Step("Evrak numara ile evrak seçilir : \"{evrakNo}\" ")
    public BirimIadeEdilenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Teslim Al button kontrolü")
    public BirimIadeEdilenlerPage evrakTeslimAlButtonKontrol() {
        teslimAlButton.should(visible);
        return this;
    }

    @Step("Evrak no ile teslim al ve Evet seçeneğinin tıklanması ve Evrakı teslim almak istediğinize emin misiniz? uyarı kontrolü")
    public BirimIadeEdilenlerPage evrakSecNoTeslimAl(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak tıklanır ve listelendiği görülür {konu}")
    public BirimIadeEdilenlerPage evrakSec(String konu) {
        tblEvraklar.filterBy(text(konu)).get(0).click();
        return this;
    }

    @Step("Teslim Al ve Havale Et")
    public BirimIadeEdilenlerPage teslimAlVeHavaleEt(){
        $("[class='ui-button-icon-left ui-icon teslimAlHavale']").click();
        return this;
    }

    @Step("Onaylayacak Kişi doldur: {onaylanacakKisi} - {birim}")
    public BirimIadeEdilenlerPage onaylanacakKisiDoldur(String onaylanacakKisi,String birim){
        txtOnaylanacakKisi.selectLov(onaylanacakKisi,birim);
        return this;
    }
    
    @Step("")
    public BirimIadeEdilenlerPage havaleOnayinaGonder(){
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Havale Onayına Gönder")).first().click();
        return this;
    }

    @Step("Önizleme ekranından Teslim Al butonuna basılır ve Evrakı teslim almak istediğinize emin misiniz? uyarısı Evet ile onaylanır")
    public BirimIadeEdilenlerPage evrakOnizlemeTeslimAl()
    {
        onizlemeTeslimAl.click();
         $(By.id("teslimAlEvetButton")).click();
         return this;
    }

    @Step("Evrak Sec Checkbox ile ve Teslim Al")
    public BirimIadeEdilenlerPage evrakSecCheckBox(String konu1, String konu2, boolean secim) {
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        takeScreenshot();
        btnTeslimAl.get(0).click();

        return this;
    }


    @Step("Birime Iade Edilenler sayfasında evrakın listede olmadığının kontrolu")
    public BirimIadeEdilenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }
}
