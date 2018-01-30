package tests.EvrakTeslimAlma;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.TeslimAlinanlarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.SistemLoglariPage;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    SistemLoglariPage sistemLoglariPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        sistemLoglariPage = new SistemLoglariPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2314: Evrak Teslim Alma")
    public void TS2314() throws InterruptedException {
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
        String evrakNO321;
        String evrakNO328;
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        //Pre-requisites Gelen Evrak Oluşturma
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
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


        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecNoTeslimAl(konu, true);


        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2316: Teslim alınmayı bekleyenler listesinden bir evrakı teslim alma (içerikten)")
    public void TS2316() throws InterruptedException {
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
        String evrakNO321;
        String evrakNO328;
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        //Pre-requisites Gelen Evrak Oluşturma
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
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
    @Test(enabled = true, description = "TS2318: Evrak Teslim Alma - Sistem Loglari Sorgu")
    public void TS2318() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2318-" + getSysDate();
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
        String evrakNO321;
        String evrakNO328;
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        String aksiyon = "Kaydedilen Gelen Evraklar - Teslim Al";


        //Pre-requisites Gelen Evrak Oluşturma
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
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


        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecNoTeslimAl(konu, true);

        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        String kullanici = "Zübeyde Tekin";
        String aciklama = "ztekin kullanıcısı, " + tarihSaatBugun;

        System.out.println(tarihSaatBugun + " " + aciklama + kullanici.toUpperCase());

        sistemLoglariPage
                .openPage()
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2319: Ayni Anda 2 Evrak Teslim Alma")
    public void TS2319() throws InterruptedException {
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
        String evrakNO321;
        String evrakNO328;
        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";


        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";


        //Pre-requisites Gelen Evrak Oluşturma 1. dosya
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
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

        //Pre-requisites Gelen Evrak Oluşturma 2. dosya
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
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


        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecCheckBox(konu1, konu2, true);

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(kisi, islemSureci);


    }


}
