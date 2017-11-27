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

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1124: Yeni tüzel kişi kayıt ve ekranlardan kontrolleri")
    public void TC1124() {

        String vergiNo = createRandomNumber(7);
        String ad = "Sezai Çelik Holding";
        String kisaAd = "SCH";
        String adres = "Mecidiyeköy Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İst";
        String ilce = "Şiş";
        String eposta = "scholding@turksat.com.tr";
        String webAdres = "www.scholding.com";
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

    }
}
