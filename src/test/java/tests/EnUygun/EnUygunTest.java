package tests.EnUygun;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.UcakBiletiPage;


@Feature("En Uygun")
public class EnUygunTest extends BaseTest {
    UcakBiletiPage ucakBiletiPage;


    @BeforeMethod
    public void loginBeforeTests() {
//        login("ztekin", "123");
        Selenide.open("");
        ucakBiletiPage = new UcakBiletiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0001: En Uygun Havaaalanı Seçme")
    public void TS0001() throws InterruptedException {
        String testid = "TS-0001";


//        testStatus(testid, "PreCondition Evrak Oluşturma");
        ucakBiletiPage
                .openPage();

//        gelenEvrakKayitPage
//                .konuKoduDoldur(konuKodu)
//                .konuDoldur(konu)
//                .evrakTuruSec(evrakTuru)
//                .evrakDiliSec(evrakDili)
//                .evrakTarihiDoldur(evrakTarihi)
//                .gizlilikDerecesiSec(gizlilikDerecesi)
//                .kisiKurumSec(kisiKurum)
//                .geldigiKurumDoldurLovText(geldigiKurum)
//                .evrakSayiSagDoldur()
//                .evrakGelisTipiSec(evrakGelisTipi)
//                .ivedilikSec(ivedilik)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
//                .kaydet()
//                .popUps();



//        testStatus(testid, "Test Başladı");
//        teslimAlinmayiBekleyenlerPage
//                .openPage()
//                .teslimAlIkonKontrol(konu)
//                .evrakSecNoTeslimAl(konu, true)
//                .evrakNoGelmedigiGorme(konu)
//                .islemMesaji().basariliOlmali(basariMesaji);
//
//        teslimAlinanlarPage
//                .openPage()
//                .evrakNoIleEvrakSec(konu)
//                .evrakAdediKontrolu(konu)
//                .tabKontrol()
//                .secilenEvrakEvrakGecmisi()
//                .evrakGecmisi(kisi, islemSureci, evrakTarihiSaat);

    }

}
