package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-02-08
 * Proje: Türksat Functional Test Automation
 * Class: "HavaleOnayinaSunduklarimPage" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class HavaleOnayinaSunduklarimPage extends MainPage {
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");

    SelenideElement havaleBilgisi = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    SelenideElement txtGeriAlNot = $(By.id("mainPreviewForm:evrakGeriAlInputTextareaId"));
    ElementsCollection kisiKontrol = $$("[id^='mainPreviewForm:dagitimBilgileriKullaniciLov_id:LovSecilenTable:0:j_idt']");


    @Step("Birim Havale Onayına Sunduklarim sayfasını aç")
    public HavaleOnayinaSunduklarimPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayinaSunduklarim);
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public HavaleOnayinaSunduklarimPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Geri al tıklanır")
    public HavaleOnayinaSunduklarimPage geriAl() {
        $("[class='ui-button-icon-left ui-icon evrakGeriAl']").click();
        return this;
    }

    @Step("Geri al tıklanır")
    public HavaleOnayinaSunduklarimPage geriAlGeriAl() {
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] button").filterBy(Condition.text("Geri Al")).first().click();
        return this;
    }

    @Step("Not alanını doldur: {not}")
    public HavaleOnayinaSunduklarimPage geriAlNotDoldur(String not) {
        txtGeriAlNot.setValue(not);
        return this;
    }

    @Step("Havale Onayına Sunduklarım Havale Bilgisi butonu tıklanır")
    public HavaleOnayinaSunduklarimPage havaleBilgisi() {
        havaleBilgisi.click();
        return this;
    }

    @Step("Havale Onayına Sunduklarım Havale Bilgisi Kisi Alanındaki : \"{kisi}\" ")
    public HavaleOnayinaSunduklarimPage kisiKontrol(String kisi) {
        System.out.println("size" + kisiKontrol.size());
        boolean durum = kisiKontrol.filterBy(Condition.text(kisi)).size() > 0;
        Assert.assertEquals(durum, true);
        return this;
    }

}
