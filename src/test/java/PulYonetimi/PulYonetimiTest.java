package PulYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.PulYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Pul Yonetimi" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class PulYonetimiTest extends BaseTest {
    MainPage mainPage;
    PulYonetimiPage pulYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        pulYonetimiPage = new PulYonetimiPage();
        login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1732: Pul Yönetimi ekranından yeni tanımlama yapma")
    public void TC1732() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";

        pulYonetimiPage
                .openPage()
                .yeniPulEkle()
                .pulEklemePostaTipiSec("X")
                .gramajiDoldur("3")
                .tutariDoldur("50")
                .indirimOraniDoldur("10")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        pulYonetimiPage
                .pulEklemePostaTipiSec("X")
                .gramajiDoldur("5")
                .tutariDoldur("100")
                .indirimOraniDoldur("20")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        pulYonetimiPage
                .postaTipiSec("U")
                .gonderimTipiSec("YURT_ICI")
                .ara();
//                .tabloKontrolu("Ankara İçi APS");
    }


}
