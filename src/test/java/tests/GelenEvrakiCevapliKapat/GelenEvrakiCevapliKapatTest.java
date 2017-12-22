package tests.GelenEvrakiCevapliKapat;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.CevaplananEvrakRaporuPage;

import java.io.IOException;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Gelen Evrakı Cevaplı Kapat" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class GelenEvrakiCevapliKapatTest extends BaseTest {

    CevaplananEvrakRaporuPage cevaplananEvrakRaporuPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        cevaplananEvrakRaporuPage = new CevaplananEvrakRaporuPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1368: Cevaplanan Evrak Raporu")
    public void TC1368() throws IOException, InterruptedException {

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String konuKodu = "Kanunlar";
        String ilkTarih = "17.11.2017";
        String sonTarih = "17.11.2017";

        String evrakTarihi = "17.11.2017";
        String evrakKayitTarihi = "17.11.2017";
        String evrakSayisi = "54354354-8794";
        String konu = "Kanunlar";
        String cevaplananEvrakKonuKodu = "Kanunlar";
        String cevaplananEvrakKonu = "Kanunlar";
        String cevaplananEvrakSayisi = "6345202-010.01-9075";
        String cevaplananEvrakTarihi = "17.11.2017";
        String basariMesaji = "İşlem başarılıdır!";
        String filePath = "C:\\Users\\" + getPcUserName() + "\\Downloads\\";
        String fileName = "Rapor_";
        String fileName2 = "Rapor_.xls";

        cevaplananEvrakRaporuPage
                .openPage()
                .birimDoldur(birim)
                .altBirimEvraklariDahilSec(true)
                .konuKoduDoldur(konuKodu)
                .ilkTarihDoldur(ilkTarih)
                .sonTarihDoldur(sonTarih)
                .sorgula()
                .cevaplananEvrakKayitKontrolu(
                        evrakTarihi,
                        evrakKayitTarihi,
                        evrakSayisi,
                        konu,
                        cevaplananEvrakKonuKodu,
                        cevaplananEvrakKonu,
                        cevaplananEvrakSayisi,
                        cevaplananEvrakTarihi)
                .cevaplananEvrakGecmisiVeDetayKontrolu()
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .sayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);
        Thread.sleep(10000);
        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .tumSayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .temizle()
                .temizleSonrasiKontrol();
    }

}
