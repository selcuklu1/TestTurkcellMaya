package tests.OlurYazisiİslemleri;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import data.User;
import org.testng.annotations.Test;
import pages.galen.GalenControl;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public class OlurYazisiİslemleriTest extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");

    //Teskilat Kisi tanimlari-->birim yönetimi ekranında birimin olur metni boş olmalı
    @Test(description = "TS0577: Olur yazısı oluşturulması ve gönderilmesi", enabled = true)
    public void TS0577() throws Exception {
        login(user1);
        OlurYazisiOlusturPage olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class);
        BilgilerTab bilgilerTab = olurYazisiOlusturPage.openPage().bilgileriTabiAc();
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
                //.getEditor().editorTextShouldHave(text("... Makamına"));

        new GalenControl().galenGenerateDump("TS0577");
    }
}
