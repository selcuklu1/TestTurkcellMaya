package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HaveleOnayinaSunduklarimPage extends MainPage {

    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    @Step("Havale Onayına Sunduklarım sayfasını aç")
    public HaveleOnayinaSunduklarimPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayinaSunduklarim);
        return this;
    }

    @Step("Evrak no ile evrak içerik göster seçilir : \"{evrakNo}\" ")
    public HaveleOnayinaSunduklarimPage evrakNoIleEvrakIcerikGosterSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Havale bilgisi tıklanır")
    public HaveleOnayinaSunduklarimPage icerikGosterHavaleBilgisi(){
    $("[class='ui-button-icon-left ui-icon havaleBilgisi']").click();
        return this;
    }
    
    @Step("Birim ve kişi bilgilerinin boş olarak geldiği görülür.")
    public HaveleOnayinaSunduklarimPage birimVeKisiBilgilerinBosOlarakGeldigiGorme(){
        boolean durum = $(By.id("inboxItemInfoForm:dagitimBilgileriBirimLov_id:LovText")).getValue()=="";
        boolean durum1 = $(By.id("inboxItemInfoForm:dagitimBilgileriKullaniciLov_id:LovText")).getValue()=="";
        Assert.assertEquals(durum,durum1);
        return this;
    }
    
}
