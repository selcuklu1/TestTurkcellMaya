package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$$;

/****************************************************
 * Tarih: 2018-02-04
 * Proje: Türksat Functional Test Automation
 * Class: "HavaleOnayiVerdiklerimPage" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class HavaleOnayiVerdiklerimPage extends MainPage{
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] > tr[role='row']");

    @Step("Birim Havale Onayı Verdiklerim sayfasını aç")
    public HavaleOnayiVerdiklerimPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.HavaleOnayiVerdiklerim);
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public HavaleOnayiVerdiklerimPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Geçmişi tıklanır")
    public HavaleOnayiVerdiklerimPage evrakSecEvrakGecmisiSec(){
        $$("[id='mainPreviewForm:evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).first().click();
        return this;
    }

    @Step("Evrak geçmişinde {geregiKisi} evrak havale edildi (gereği için) - {bilgiKisiler} evrak havale edildi (bilgi için) kayıtlarının geldiği görülür")
    public HavaleOnayiVerdiklerimPage evrakGecimisiGeregiVeBilgiGeldigiGorme(String geregi,String geregiKisi, String bilgi,String bilgiKisiler){
        boolean durum = $$("[id$='hareketGecmisiDataTable']").filterBy(Condition.text(geregi))
                .filterBy(Condition.text(bilgi)).size()>0;
        Assert.assertEquals(durum,true,"Evrak geçmişinde gereği ve bilgi görülmemiştir.");
        takeScreenshot();
        return this;
    }

    @Step("Evrak geldiği görünür.")
    public HavaleOnayiVerdiklerimPage evrakGeldigiGorme(String evrakNo){
        tblEvraklar.filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
        return this;
    }

    @Step("Evrak geçmişi alanına tıklanır")
    public HavaleOnayiVerdiklerimPage secilenEvrakEvrakGecmisi() {
        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    @Step("Evrak Geçmişi Kontrol")
    public HavaleOnayiVerdiklerimPage evrakGecmisi(String teslimAlinan, String islemSureci) {
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text(islemSureci)).filter(Condition.text(teslimAlinan)).size() > 0;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }
}
