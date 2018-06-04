package tests.EvrakTeslimAlma;

import common.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GelenEvrakZimmetRaporuPage;
import pages.ustMenuPages.SistemLoglariPage;



@Feature("Evrak Teslim Alma")
public class EvrakTeslimAlmaTest extends BaseTest {
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
    GelenEvrakZimmetRaporuPage gelenEvrakZimmetRaporu;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    SistemLoglariPage sistemLoglariPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
        sistemLoglariPage = new SistemLoglariPage();
        gelenEvrakZimmetRaporu = new GelenEvrakZimmetRaporuPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
//        Logger logger = LogManager.getRootLogger();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2314: Teslim alınmayı bekleyenler listesinden bir evrakı teslim alma (listeden)")
    public void TS2314() throws InterruptedException {
        String testid = "TS-2314";
//        System.setProperty("log4j.filename","EvrakTeslimAlmaTest:");
//        logger.info(testid + " nolu test başladı:");
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2314-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String kisiGercek = "Gerçek Kişi";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String ekMetni = "test otomasyon" + getSysDateForKis();
        String aciklama = "Test Otomasyon";
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";


        testStatus(testid, "PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();

//        gelenEvrakKayitPage
//                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid, "Test Başladı");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .teslimAlIkonKontrol(konu)
                .evrakSecNoTeslimAl(konu, true)
                .evrakNoGelmedigiGorme(konu)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAdediKontrolu(konu)
                .tabKontrol()
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci, evrakTarihiSaat);

//        logger.info(testid + " nolu test bitti.");
    }

}
