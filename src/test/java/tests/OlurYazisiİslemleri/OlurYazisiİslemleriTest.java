package tests.OlurYazisiİslemleri;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.page;
import static pages.pageComponents.belgenetElements.Belgenet.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public class OlurYazisiİslemleriTest extends BaseTest {

    User user1 = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");


    OlurYazisiOlusturPage olurYazisiOlusturPage;
    @Step("Konu kodu, Konu alanlarını doldur\n" +
            "Gizlilik derecesini Normal seç\n" +
            "İvedilik (veya Aciliyet) alanını doldur\n" +
            "Gereği alanından birim seç")
    public OlurYazisiİslemleriTest alanlariDoldur(){
        /*BilgilerTab tab = olurYazisiOlusturPage.bilgileriTab().openTab();
        tab.konuKodu().selectLov("010.10");
        tab.getGizlilikDerecesi().selectOption(GizlilikDerecesi.Normal.getOptionText());
        tab.getIvedilik().selectOption(Ivedilik.Normal.getOptionText());
        tab.getGeregiSecimTipi().selectOption(GeregiSecimTipi.Birim.getOptionText());*/
        return this;
    }


    //Teskilat Kisi tanimlari-->birim yönetimi ekranında birimin olur metni boş olmalı
    @Test(description = "TS0577: Olur yazısı oluşturulması ve gönderilmesi", enabled = true)
    @Link(name = "Galen", type = "html", url = "file:///Users/ilyas/WorkspaceJava/Git/BelgenetFTA/galenReports/TS0577/report.html")
    public void TS0577() throws Exception {
        login(user1);
//        olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class).openPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        olurYazisiOlusturPage.closePage(false);

        BilgilerTab tab = olurYazisiOlusturPage.bilgileriTab().openTab();
        tab.getKonuKodu().selectLov("010.10");
        tab.getGizlilikDerecesi().selectOption(GizlilikDerecesi.Normal.getOptionText());
        tab.getIvedilik().selectOption(Ivedilik.Normal.getOptionText());
        tab.getGeregiSecimTipi().selectOption(GeregiSecimTipi.BIRIM.getOptionText());

        tab.getOnayAkisiEkleButton().click();
        tab.getKullanicilarCombolov().getSelectedItems()
                .shouldHaveSize(1).first()
                .shouldHave(text(user1.getFullname()))
                .$("select").shouldHave(value("Parafla"));


        /*olurYazisiOlusturPage.ekleriTab().openTab().altTabs().dosyaEkleTabiAc().ekMetniDoldur("34242343424");
        ElementsCollection c = olurYazisiOlusturPage.ekleriTab().altTabs().sistemdeKayitliEvrakEkleTabiAc()
                .evrakAraDoldur("a").dokumanAraTikla()
                .getArananEvrakTablosu().getRows().shouldHave(sizeGreaterThan(0));
        SelenideElement colName = olurYazisiOlusturPage.ekleriTab().altTabs().sistemdeKayitliEvrakEkleTabiAc().getArananEvrakTablosu()
                .getColumn(c.first(), "Sayı");
        c.first().$("button").click();
        olurYazisiOlusturPage.ekleriTab().getEkListesiTablosu().findRowsByText(colName.text()).shouldHaveSize(1);
*/
//        olurYazisiOlusturPage.ekleriTab().getEkListesiTablosu().findRowsMy().;

        //        olurYazisiOlusturPage.ekleriTabAc().dosyaEkleTabiAc2().getDosyaEkleButton()
         /*BilgilerTab bilgilerTab = olurYazisiOlusturPage.bilgileriTabiAc();
        bilgilerTab.konuKoduSec("010.10")
            .konuDoldur("TS0577_" + getSysDate())
            .gizlilikDerecesiSec(GizlilikDerecesi.Normal)
            .ivedilikSec(Ivedilik.Normal)
            .geregiSecimTipiSec(GeregiSecimTipi.Birim)
                .onayAkisiEkleButonaTikla()
                .onayAkisiKullanicilarTemizle()
                .onayAkisiKullaniciVeTipiSec(user1.getName(), "Paraflama")
                .onayAkisiKullaniciVeTipiSec("user2", "İmzalama")
                .onayAkisiKullaniciVeTipiSec("tekin", "İmzalama")
                .kullanButonaTikla()
                .kaldiralacakKlasorleriSec("Diğer");
        olurYazisiOlusturPage.editorTabAc();

        new GalenControl()  //.galenGenerateDump("TS0577");
                .galenLayoutControl("TS0577");
*/
       /* String fileName = "Otomasyon.pdf";
        String ekMetni = "QQQQQ";
        AltTabs baseAltTab = olurYazisiOlusturPage.ekleriTabAc();
        baseAltTab.dosyaEkleTabAc()
                .ekMetniDoldur(ekMetni)
                .dosyaEkleAlan(fileName)
                .getDosyaEkleButonaTikla();
        baseAltTab.getEkListesiTablosu().findRowsByText(fileName, ekMetni).shouldHaveSize(1);
*/

    }


    @Test(description = "TS1488: Olur yazısında alan kontrolleri", enabled = true)
    public void TS1488() throws Exception {
        login(user1);
        OlurYazisiOlusturPage olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class);
//        BilgilerTab bilgilerTab = olurYazisiOlusturPage.openPage().bilgileriTabiAc();
    }
}
