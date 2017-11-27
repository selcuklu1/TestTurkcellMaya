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

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " holding";
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
                .filtreVergiNoDoldur(vergiNo)
                .ara()
                .aktifKisiKayitKontrolu(vergiNo, ad, kisaAd);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .secilenGeregiSil()
                .geregiDoldur(kisaAd)
                .secilenGeregiSil()
                .geregiDoldur(vergiNo);

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriListKisiKurumSec("T")
                .geldigiTuzelKisiDoldur(ad)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(kisaAd)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .secilenGeregiSil()
                .geregiDoldur(kisaAd)
                .secilenGeregiSil()
                .geregiDoldur(vergiNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1124: Tüzel kişi sorgulama")
    public void TC1133() throws InterruptedException {

        String vergiNo = "8524567913";
        String ad = "Türksat Optiim";
        String kisaAd = "trkstopttm";

        tuzelKisiYonetimiPage
                //Step num: 2
                .openPage()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktiflerTumListeKayitKontrolu()

                //Step num: 3
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerTumListeKayitKontrolu()

                //Step num: 4
                //Data pasif ise, aktif yapılır.
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiPasifIseAktifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 6
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 8
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 10
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 5
                //Data aktif ise, pasif yapılır.
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiAktifIsePasifYap()
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 7
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 9
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 11
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu();
    }
}
