package tests.EvrakTeslimAlma;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GelenEvrakZimmetRaporuPage;
import pages.ustMenuPages.SistemLoglariPage;

import java.text.SimpleDateFormat;
import java.util.Date;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
/****************************************************
 * Tarih: 2018-01-23
 * Proje: Türksat Functional Test Automation
 * Class: Evrak Teslim Alma
 * Yazan: Serdar Kayis
 ****************************************************/

public class EvrakTeslimAlmaTest extends BaseTest {
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;
    GelenEvrakZimmetRaporuPage gelenEvrakZimmetRaporu;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;

    SistemLoglariPage sistemLoglariPage;
//    static final Logger logger = LogManager.getLogger("EvrakTeslimAlmaTest");


    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
        sistemLoglariPage = new SistemLoglariPage();
        gelenEvrakZimmetRaporu = new GelenEvrakZimmetRaporuPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
//        Logger logger = LogManager.getRootLogger();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2314: Teslim alınmayı bekleyenler listesinden bir evrakı teslim alma (listeden)")
    public void TS2314() throws InterruptedException {
        String testid= "TS-2314";
//        System.setProperty("log4j.filename","EvrakTeslimAlmaTest:");
//        Logger logger1 = LogManager.getLogger();
//        logger.info(testid + " nolu test başladı:");
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2314-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
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


        testStatus(testid,"PreCondition Evrak Oluşturma");
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

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid,"Test Başladı");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecNoTeslimAl(konu, true)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAdediKontrol(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci, evrakTarihi);

//        logger.info(testid + " nolu test bitti.");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2316: Teslim alınmayı bekleyenler listesinden bir evrakı teslim alma (içerikten)")
    public void TS2316() throws InterruptedException {
        String testid= "TS-2316";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2316-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
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

        testStatus(testid,"PreCondition Evrak Oluşturma");
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


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"Test Başladı");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true)
                .içeriktenEvrakTeslimAl()
                .içeriktenEvrakEvet();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2318: Evrak teslim alma işleminin loglardan kontrolü")
    public void TS2318() throws InterruptedException {
        String testid= "TS-2318";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu1 = "TS-2318-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String kisiGercek = "Gerçek Kişi";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String ekMetni = "test otomasyon" + getSysDateForKis();
        //String aciklama1 = "Test Otomasyon";
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String aksiyon = "Kaydedilen Gelen Evraklar - Teslim Al";

        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        String kullanici = "Zübeyde Tekin";
        String aciklama = "ztekin kullanıcısı, " + tarihSaatBugun;

        testStatus(testid,"PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu1)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String konu2 = "TS-2318-" + getSysDate();

        testStatus(testid,"PreCondition 2. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu2)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid,"PreCondition 1. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition 2. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);


        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu1)
                .evrakSec(konu2)
                .evrakSecToplu(konu1,konu2,true)
                .evrakNoGelmedigiGorme(konu1)
                .evrakNoGelmedigiGorme(konu2)
                .islemMesaji().basariliOlmali(basariMesaji);


        //TODO: Bu adim test senaryosunda net degildir. Netlesmesi durumunda test case update edilecektir. Bu hali ile test fail etmektedir.
        sistemLoglariPage
                .openPage()
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2319: Teslim alınmayı bekleyenler listesinden evrak teslim alma (toplu)")
    public void TS2319() throws InterruptedException {
        String testid= "TS-2319";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu1 = "TS-2319-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String kisiGercek = "Gerçek Kişi";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String ekMetni = "test otomasyon" + getSysDateForKis();
        //String aciklama1 = "Test Otomasyon";
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        testStatus(testid,"PreCondition 1. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu1)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String konu2 = "TS-2319-" + getSysDate();

        testStatus(testid,"PreCondition 2. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu2)
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
                .popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"Test Başladı");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecToplu(konu1, konu2, true)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .evrakNoIleEvrakSec(konu2)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2315: Birime iade edilenler listesinden evrak teslim alma (listeden)")
    public void TS2315() throws InterruptedException {
        String testid= "TS-2315";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2315-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        testStatus(testid,"PreCondition Evrak Oluşturma");
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


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakTeslimAlButtonKontrol()
                .evrakSecNoTeslimAl(konu,true)
                .evrakNoGelmedigiGorme(konu)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAdediKontrol(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci,evrakTarihi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2317: Birime iade edilenler listesinden evrak teslim alma (önizlemeden)")
    public void TS2317() throws InterruptedException {
        String testid= "TS-2317";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2317-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        testStatus(testid,"PreCondition Evrak Oluşturma");
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


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);



        testStatus(testid,"Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .evrakOnizlemeTeslimAl()
                .evrakNoGelmedigiGorme(konu)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAdediKontrol(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci,evrakTarihi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2320: Birime iade edilenler listesinden evrak teslim alma (toplu)")
    public void TS2320() throws InterruptedException {
        String testid= "TS-2320";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu1 = "TS-2320-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        testStatus(testid,"PreCondition 1. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu1)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String konu2 = "TS-2320-" + getSysDate();

        testStatus(testid,"PreCondition 2. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu2)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid,"PreCondition 1. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition 2. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid,"Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu1)
                .evrakSec(konu2)
                .evrakSecToplu(konu1,konu2,true)
                .evrakNoGelmedigiGorme(konu1)
                .evrakNoGelmedigiGorme(konu2)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .evrakNoIleEvrakSec(konu2)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi,islemSureci);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2321: Teslim alınan evrakın gelen evrak zimmet raporundan kontrolü")
    public void TS2321() throws InterruptedException {
        String testid= "TS-2321";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu1 = "TS-2321-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        testStatus(testid,"PreCondition 1. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu1)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String konu2 = "TS-2321-" + getSysDate();

        testStatus(testid,"PreCondition 2. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu2)
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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid,"PreCondition 1. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid,"PreCondition 2. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);


       birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu1)
                .evrakSec(konu2)
                .evrakSecToplu(konu1,konu2,true)
                .evrakNoGelmedigiGorme(konu1)
                .evrakNoGelmedigiGorme(konu2)
                .islemMesaji().basariliOlmali(basariMesaji);

//        teslimAlinanlarPage
//                .openPage()
//                .evrakNoIleEvrakSec(konu1)
//                .evrakNoIleEvrakSec(konu2)
//                .secilenEvrakEvrakGecmisi()
//                .evrakGecmisi(kisi, islemSureci, evrakTarihi);

        testStatus(testid,"Test Başladı");
        String kullanici = "Zübeyde TEKİN";

        gelenEvrakZimmetRaporu
                .openPage()
                .sorgula()
                .rapordaEvraklarıListele(konu1,konu2)
                .evrakGecmisiButtonTıklama(konu1)
                .evrakGecmisiKontrolu(konu1,kullanici,islemSureci)
                .popupKapatma()
                .evrakDetayButtonTıklama(konu1)
                .evrakDetayKontrolu(konu1)
                .evrakKapatma()
                .evrakEtiketButtonTıklama(konu1)
                .evrakEtiketKontrolu();

    }
}
