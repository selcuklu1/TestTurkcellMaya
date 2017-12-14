/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakHavaleKurallari;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

import static data.TestData.password2;
import static data.TestData.username2;
import static data.TestData.username4;

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
@Epic("Evrak Havale Kuralları")
public class EvrakHavaleKurallariTest extends BaseTest {

    EvrakHavaleKurallariYonetimiPage evrakHavaleKurallariYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakHavaleKurallariYonetimiPage = new EvrakHavaleKurallariYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2069: Evrak Havale Kuralları - Kural Silme")
    public void TC2069A() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Y";
        String farkliKullanici = "Optiim";

        login(username4, password2);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil()
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
        gelenEvrakKayitPage
                .openPage()
                .otomatikHavaleSec(true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2069: Evrak Havale Kuralları Sorgulama ve Filtreleme")
    public void TC2069B() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String durumSadecePasifler = "PASIFLER";
        String durumSadeceAktifler = "AKTIFLER";
        String geldigiYerKullanici = "K";
        String kullaniciKural = "BarisTest";
        String geldigiYerBirim = "Birim";
        String geldigiYerGercekKisi = "Gerçek Kişi";
        String geldigiYerTuzelKisi = "Tüzel Kişi";
        String geldigiYerKurum = "Kurum";
        String kuralAdi = "";
        String kullanici = "Zübeyde Tekin";

        login(username2, password2);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .durumSec(durumSadeceAktifler)
                .ara()
                .durumSec(durumSadecePasifler)
                .ara()
                .geldigiYerTipiSec(geldigiYerKullanici)
                .ara()
                .kullaniciDoldur(kullanici)
                .ara()
                .geldigiYerTipiSec(geldigiYerBirim)
                .ara();
                /*//
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerGercekKisi)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerTuzelKisi)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerKurum)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .birimDoldur("birim")
                .ara()
                .altBirimDahilSec(true)
                .kuralAdiDoldur("birim")
                .islemMesaji().basariliOlmali(basariMesaji);*/

    }




}
