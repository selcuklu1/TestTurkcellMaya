package tests.EvrakOlusturma;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.tabs.AltTabs;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakOlusturmaTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    ImzaladiklarimPage imzaladiklarimPage;
    AltTabs alttabs;

    @BeforeMethod
    public void BeforeTestStart() {
        evrakOlusturPage = new EvrakOlusturPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2248 : Genelge tipinde evrak oluşturmada hitap ve genelge no kontrolü")
    public void TS2248() throws InterruptedException {

        useFirefox();
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String konu = "TS2248 " + createRandomNumber(8);
//        String konu = "TS2248 12165034";
        String guncelHitap = "Genel Müdürlük Emri";
        String basariMesaji = "İşlem başarılıdır!";

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
                .evrakTuruSec("Genelge");

//                .inputGenelgeSayisi("87878978994");

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
                .hitapGuncellePopup(guncelHitap)
                .hitapGuncellePopupKapat();

        evrakOlusturPage.editorTabAc()
                .pdfGoster();

        evrakOlusturPage
                .pdfKontrol
                .PDFHitapKontrol(guncelHitap);

        evrakOlusturPage
                .editorTabAc()
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);
//
//        Selenide.close();
//        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .evrakOnizlemeHitapKontrol(guncelHitap);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .evrakTuruSec("Genelge")
                .genelgeNoKontrol(genelgeNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2251 : Yetki Devri Kullanılarak Evrak Oluşturma (Geri al ve iade et senaryoları)")
    public void TS2251() throws InterruptedException{


        useFirefox();
        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        String konu = "TS2248 " + createRandomNumber(8);
//        String konu = "TS2248 12165034";
        String guncelHitap = "Genel Müdürlük Emri";
        String basariMesaji = "İşlem başarılıdır!";

        String sablon = "TS2251 (K)";
        String icerik="TS2251 senaryosu için hazırlanmıştır.";

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .onTanimliIcerikSablonuGoster()
                .onTanimliIcerikSablonuSec(sablon)
                .sablonlarUygula()
//                .onizlemeIcerikKontrol(icerik)
                .pdfGoster();

        evrakOlusturPage
                .pdfKontrol
                .PDFIcerikKontrol(icerik);

        evrakOlusturPage
                .bilgilerTabiAc();

    }
}
