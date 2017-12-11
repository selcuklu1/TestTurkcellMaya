package GercekKisiYonetimi2;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GercekKisiYonetimPage;
import pages.ustMenuPages.GidenEvrakKayitPage;

/****************************************************
 * Tarih: 2017-11-20
 * Proje: Türksat Functional Test Automation
 * Class: "Gerçek Kişi Yönetimi " konulu senaryoları içerir
 * Yazan: Sezai Çelik
 ****************************************************/
public class GercekKisiYonetimiTest2 extends BaseTest {

    GercekKisiYonetimPage gercekKisiYonetimPage;
    EvrakOlusturPage evrakOlusturPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1144: Gerçek kişi sorgulama")
    public void TC1144() throws InterruptedException {

        String tcNO = "91057625780";
        String tc10 = "10";
        String ad = "OptiimTest";
        String soyad = "TestOptiim";

        gercekKisiYonetimPage
                .openPage()
                .ara()
                .filtreSorgulamaPaneliAc()
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("AKTIFLER")
                .ara() //araButonuTikla
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tc10)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .tcNoKontrolu(tc10)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tcNO)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tcNO)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerKayitKontrolu();

        String getTbleTC = gercekKisiYonetimPage.getTbleTCNO();

        gercekKisiYonetimPage
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .filtreTCKimlikNoDoldur(getTbleTC)
                .ara()
                .tcNoKontrolu(getTbleTC);
    }
}
