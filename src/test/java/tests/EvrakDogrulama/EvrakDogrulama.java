package tests.EvrakDogrulama;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.tabs.AltTabs;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakDogrulama extends BaseTest {
    EvrakOlusturPage evrakOlusturPage;
    AltTabs alttabs;

    @BeforeMethod
    public void BeforeTestStart () {
    evrakOlusturPage = new EvrakOlusturPage();

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true , description = "Sistem sabitindeki gizlilik derecesine göre evrak doğrulama checkinin kontrolü")
    public void TS2077() throws InterruptedException {
        login("Mbozdemir", "123");
        evrakOlusturPage.openPage();

        evrakOlusturPage.bilgilerTabiAc()
                .konuAlaniGeldigiGorme()
                .konuKoduAlaniGeldigiKtrl()
                .gizlilikDerecesiAlaniKtrl()
                .ivedilikAlaniKtrl()
                .bilgiAlaniktrol()
                .geregiAlanigeldigiKtrol()
                .onayAkisiAlangelktrl()
                .kaldiralacakKlasoralanKtrol()
                .gizlilikDerecesiSec("Normal");
        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkevrakDogrulanabilirktrol()
                .chkboxEvrakDogrulanabilirclick();
        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Tasnif Dışı");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();

        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Hizmete Özel");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();

        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Özel");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();
            evrakOlusturPage.evrakDogrulamaTabAc()
                    .chkdogrulanabilirİsaretle();
    }

}
