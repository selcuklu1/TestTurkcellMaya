package TuzelKisiYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

/****************************************************
 * Tarih: 2017-11-24
 * Proje: Türksat Functional Test Automation
 * Class: "Tüzel Kişi Yönetimi" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class TuzelKisiYonetimiTest extends BaseTest {

    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1124: Yeni tüzel kişi kayıt ve ekranlardan kontrolleri")
    public void TC1124() throws InterruptedException {

        String vergiNo = createRandomNumber(7);
        String ad = createRandomText(7) + " holding";
        String kisaAd = createRandomNumber(7);
        String adres = "Mecidiyeköy Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İst";
        String ilce = "Şiş";
        String eposta = kisaAd + "holding@turksat.com.tr";
        String webAdres = "www." + kisaAd + "holding.com";
        String telNo = "5391111111";
        String faksNo = "2121111111";
        String basariMesaji = "İşlem başarılıdır!";

        tuzelKisiYonetimiPage
                .openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec("12729")
                .vergiNoDoldur(vergiNo)
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)
                .yeniIletisimEkle()

                .mobilTelNoDoldur(telNo)
                .telNoDoldur(telNo)
                .faks1NoDoldur(faksNo)
                .faks2NoDoldur(faksNo)
                .adresDoldur(adres)
                .ulkeSec(ulke)
                .ilSec(il)
                .ilceSec(ilce)
                .ePostaDoldur(eposta)
                .webAdresDoldur(webAdres)
                .iletisimBilgisiKaydet()

                .tuzelKisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        tuzelKisiYonetimiPage
                .filtreSorgulamaPaneliAc()
                .vergiNoDoldur(vergiNo)
                .ara()
                .kayitKontrolu(vergiNo, ad, kisaAd);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                    .geregiSecimTipiSec("T")
                    .geregiDoldur(ad)
                    .geregiDoldur(kisaAd)
                    .geregiDoldur(vergiNo);

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriListKisiKurumSec("T")
                .evrakBilgileriListGeldigiKisiDoldur(ad)
                .evrakBilgileriListGeldigiKisiDoldur(kisaAd)
                .evrakBilgileriListGeldigiKisiDoldur(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .geregiDoldur(kisaAd)
                .geregiDoldur(vergiNo);


    }
}
