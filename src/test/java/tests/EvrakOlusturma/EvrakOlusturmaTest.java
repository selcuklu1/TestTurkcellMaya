package tests.EvrakOlusturma;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.tabs.AltTabs;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakOlusturmaTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    AltTabs alttabs;

    @BeforeMethod
    public void BeforeTestStart() {
        evrakOlusturPage = new EvrakOlusturPage();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2248 : Genelge tipinde evrak oluşturmada hitap ve genelge no kontrolü")
    public void TS2248() throws InterruptedException {
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String konu = "TS2248 " + createRandomNumber(8);

        evrakOlusturPage.openPage()
                .bilgilerTabiAc()
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

        String BirOncekiGenelgeNo = evrakOlusturPage.bilgilerTabiAc().birOncekiGenelgeSayisi();
        String genel = evrakOlusturPage.bilgilerTabiAc().genelgeSayisiArttirma(BirOncekiGenelgeNo);
        String genelgeNo = genel.trim();

        Thread.sleep(500);

        evrakOlusturPage.bilgilerTabiAc()
                .evrakTuruSec("Genelge")
                .genelgeAlaniKontrolu()
                .konuKoduDoldur("010.01")  // YAZILIM GELİŞTİRME
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
                .inputGenelgeSayisi(genelgeNo)
                .geregiSecimTipiSec("Gerçek Kişi")
                .geregiSec("OptiimTest")
                .onayAkisiEkle()
                .onayAkisiotomatikilkgelenKullaniciKontrolEt2("Mehmet Bozdemir")
                .onayAkisiParaflamaGeldigiGorme()
                .onayAkisiEkleIlkSelectSec("İmzalama");

        evrakOlusturPage
                .butonKontrolu("Kaydet ve Onaya Sun",true);
        evrakOlusturPage
                .bilgilerTabiAc()
//                .onayAkisiTemizle()
                .onayAkisiKullan()
                .onayAkisiKontrolu();

        evrakOlusturPage.editorTabAc()
                .editorHitapKontrol("GENELGE")
                .editorIcerikDoldur("Acıklama")
                .hitapTikla()
                .hitapPopupKontrol()
                .hitapGuncellePopup("Genel Müdürlük Emri")
                .hitapGuncellePopupKapat();

        evrakOlusturPage.editorTabAc()
                .pdfGoster();

        //11. adımdan devam edilecek


    }
}
