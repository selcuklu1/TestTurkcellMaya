package tests.EvrakOlusturma;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.tabs.AltTabs;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakOlusturmaTest  extends BaseTest{

    EvrakOlusturPage evrakOlusturPage;
    AltTabs alttabs;

    @BeforeMethod
    public void BeforeTestStart () {
        evrakOlusturPage = new EvrakOlusturPage();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true , description = "TS2248 : Genelge tipinde evrak oluşturmada hitap ve genelge no kontrolü")
    public void TS2248() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS2248" + getSysDate();
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
                .evrakTuruIcerikKontrol()
                .evrakTuruSec("Genelge")
                .inputGenelgeSayisi("87878978994");
        String Bironceigenelgeno = evrakOlusturPage.bilgilerTabiAc().birOncekiGenelgeSayisi();
        String genel = evrakOlusturPage.bilgilerTabiAc().genelgeSayisiArttirma(Bironceigenelgeno);
        String genelgeno = genel.trim();
        Thread.sleep(500);
                evrakOlusturPage.bilgilerTabiAc()
                        .konuKoduDoldur("YAZILIM GELİŞTİRME")
                        .konuDoldur(konu)
                        .kaldirilacakKlasorler("Diğer")
                        .inputGenelgeSayisi(genelgeno)
                        .geregiSecimTipiSec("Gerçek Kişi")
                        .geregiSec("OptiimTest")
                        .onayAkisiEkle()
                        .onayAkisiotomatikilkgelenKullaniciKontrolEt2("Mehmet Bozdemir")
                        .onayAkisiParaflamaGeldigiGorme()
                        .onayAkisiEkleIlkSelectSec("İmzalama")
                        .onayAkisiTemizle()
                        .onayAkisiKullan()
                        .kaydetVeOnayaSunKontrol();
                evrakOlusturPage.editorTabAc()
                        .editorHitapKontrol("GENELGE");



    }
}
