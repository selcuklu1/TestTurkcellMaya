package tests.OlurYazisiİslemleri;

import common.BaseTest;
import data.User;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.BilgilerTab;

import static com.codeborne.selenide.Selenide.page;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public class OlurYazisiİslemleriTest extends BaseTest {

    User user1 = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");


    //Teskilat Kisi tanimlari-->birim yönetimi ekranında birimin olur metni boş olmalı
    @Test(description = "TS0577: Olur yazısı oluşturulması ve gönderilmesi", enabled = true)
    @Link(name = "Galen", type = "mylink", url = "file:///Users/ilyas/WorkspaceJava/Git/BelgenetFTA/galenReports/TS0577/report.html")
    public void TS0577() throws Exception {
        login(user1);
        OlurYazisiOlusturPage olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class).openPage();
        olurYazisiOlusturPage.ekleriTabAc().altTabs().dosyaEkleTabiAc().ekMetniDoldur("34242343424");
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
        BilgilerTab bilgilerTab = olurYazisiOlusturPage.openPage().bilgileriTabiAc();
    }
}
