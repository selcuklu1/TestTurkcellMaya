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
                .kayitKontrolu(vergiNo, ad, kisaAd);

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
                .openPage()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktiflerTumListeKayitKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerTumListeKayitKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiPasifIseAktifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitKontrolu(vergiNo, ad, kisaAd)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiAktifIsePasifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitKontrolu(vergiNo, ad, kisaAd)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiPasifIseAktifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitKontrolu(vergiNo, ad, kisaAd)
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiAktifIsePasifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitKontrolu(vergiNo, ad, kisaAd)
                .kayitBulunamadiKontrolu();

        //TODO: DEVAM EDECEK


    }
}
