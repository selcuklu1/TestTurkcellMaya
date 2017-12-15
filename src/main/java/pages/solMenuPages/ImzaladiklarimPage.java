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


public class ImzaladiklarimPage extends MainPage {

    SelenideElement tblImzaladiklarim = $(By.id("mainInboxForm:inboxDataTable_data"));
    SelenideElement tabEvrakGecmisi = $(By.xpath("//*[text()[contains(.,'Evrak Geçmişi')]]"));
    SelenideElement btnIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement tabEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");
    ElementsCollection tblImzalananEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row'] table");
SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");

    @Step("Imzaladiklarim Sayfasini aç")
    public ImzaladiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim);

        return this;
    }

    @Step("Evrak geldiği görülür")
    public ImzaladiklarimPage evrakGeldigiGorme(String toplantiNo, String konu, String toplantiTarih) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu)).filterBy(Condition.text(konu))
                .filterBy(Condition.text(toplantiTarih)).filterBy(Condition.visible);
        return this;
    }

    @Step("ImzaladiklarimIlkPostaSec")
    public ImzaladiklarimPage evrakSec() {
        btnIlkEvrak.click();

        return this;
    }

    @Step("Evrak Geçmişi tab")
    public ImzaladiklarimPage evrakGecmisi() {

        tabEvrakGecmisi.click();
        return this;

    }

    @Step("")
    public String evrakIcerikKontroluveEvrakNoAl(String icerik) {
        int size = tblImzalananEvraklar.size();
        String evrakNo = "";
        boolean flag = false;

        for (int i = 0; i < size; i++) {
            $(By.id("mainInboxForm:inboxDataTable:" + i + ":detayGosterButton")).click();
            evrakNo= evrakDetayiEvrakNoAl();
            String icerikTxt = $("[id='inboxItemInfoForm:evrakBilgileriList_content'] tr:nth-child(13) tr textarea").text();
            if (icerik.equals(icerikTxt)) {
                flag = true;
                break;
            }
            $(By.xpath("//div[@id='windowItemInfoDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
            islemPenceresiKapatmaOnayiPopup("Kapat");

        }
        Assert.assertEquals(flag,true,"Evrak listelenmiştir");
        return evrakNo;
    }

    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }
}
