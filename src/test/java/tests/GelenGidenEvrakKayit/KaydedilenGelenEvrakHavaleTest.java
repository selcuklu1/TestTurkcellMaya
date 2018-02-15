package tests.GelenGidenEvrakKayit;

import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TopluEvrakOnizleme;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "KaydedilenGelenEvrakHavale" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class KaydedilenGelenEvrakHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;
    TopluEvrakOnizleme topluEvrakOnizleme;

//    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin" , "123");
//    User ztekin = new User("ztekin", "123");
    String konuKodu = "010.01";
    String konu = "";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String evrakTarihi = getSysDateForKis();
    String gizlilikDerecesi = "Normal";
    String kisiKurum = "Kurum";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakGelisTipi = "Posta";
    String ivedilik = "Normal";
    String kisi = "Zübeyde Tekin";
    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String details = "BHUPGMY";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BHUPGMY";
//    String basariMesaji = "İşlem başarılıdır!";

    @BeforeMethod
    public void loginBeforeTests() {
        login(ztekin);
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        havaleOnayiVerdiklerim = new HavaleOnayiVerdiklerimPage();
        topluEvrakOnizleme = new TopluEvrakOnizleme();
    }


//    public String getDocPath1() {
//        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
//    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS422: Kişi seçilerek evrak havale etme (detay ekranından)")
    public void TS422() throws InterruptedException {
        String testid= "TS-422";
        konu = "TS-422-" + getSysDate();

        testStatus(testid,"PreCondition Evrak Oluşturma");

        gelenEvrakKayitPage
                .openPage()
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
                .kaydet()
                .popUpsv2();

        testStatus(testid,"Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS430: Kişiye havale edilen evrakın geri çekilmesi (önizleme ekranından)")
    public void TS430() throws InterruptedException {
        String testid= "TS-430";
        konu = "TS-430-" + getSysDate();
        String sayfa1 = "Birim Havale Edilenler";
        testStatus(testid,"PreCondition Evrak Oluşturma");

        gelenEvrakKayitPage
                .openPage()
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
                .kaydet()
                .popUpsv2();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        testStatus(testid,"Test Başladı");
        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        // TODO: 4 nolu step cevap bekliyor : "Destek Gerektiren Senaryolar"

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeHavaleYap()
                .havaleAlanKontrolleri()
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .buttonGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2286: Havaleden geri çekilen evrakın onaylı havale edilmesi (önizleme ekranından)")
    public void TS2286() throws InterruptedException {
        String testid= "TS-2286";
        konu = "TS-2286-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        testStatus(testid,"PreCondition Evrak Oluşturma");

        gelenEvrakKayitPage
                .openPage()
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
                .kaydet()
                .popUpsv2();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        testStatus(testid,"Test Başladı");
//        login(mbozdemir);

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec(konu)
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleIslemleriKisiDoldur(kisi)
                .icerikDagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .evrakDetayiKaydetPopUpClose()
                //TODO : 6. test adımından sonra yeni bir adım eklenmeli. Aksi takdirde havaleOnayınaGelenlerPage sayfasına ulaşmaz.
                .icerikHavaleOnayinaGonder2();

        login(mbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleOnay()
                //TODO : Havale alanında seçilen kişinin geldiği görülür.
                .havaleOnayiBirimDoldur(birim)
                .dagitimBilgileriBirimOpsiyon(bilgi)
                .havaleOnayiOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .havaleyiOnaylamakUzeresinizEvet()
                .islemMesaji().basariliOlmali();

        havaleOnayiVerdiklerim
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(ztekin);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1586: Geri çekilen evrakın tekrar havalesi - toplu")
    public void TS1586() throws InterruptedException {
        String testid= "TS-1586";
        String konu1 = "TS-1586-" + getSysDate();
        String sayfa1 = "Kaydedilen Gelen Evraklar";
        String sayfa2 = "Birim Havale Edilenler";

        testStatus(testid,"PreCondition 1. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage()
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
                .kaydet()
                .popUpsv2();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .tabloEvrakNoileIcerikSec(konu1)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu1)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu1)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        testStatus(testid,"PreCondition 2. Evrak Oluşturma");
        String konu2 = "TS-1586-" + getSysDate();

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);
        gelenEvrakKayitPage
                .openPage()
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
                .kaydet()
                .popUpsv2();

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .tabloEvrakNoileIcerikSec(konu2)
                .icerikHavaleYap()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .icerikHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu2)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu2)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        testStatus(testid,"Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evraklariSecTopluHavaleYap(konu1, konu2, true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .gonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu1)
                .evrakNoIleTabloKontrolu(konu2);
    }
}
