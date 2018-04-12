package tests.GelenGidenEvrakKayit;

import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TopluEvrakOnizleme;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.HavaleEdilenEvrakRaporuPage;
/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "KaydedilenGelenEvrakHavale" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

@Feature("Kaydedilen Gelen Evrak Havale")
public class KaydedilenGelenEvrakHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;
    TopluEvrakOnizleme topluEvrakOnizleme;
    HavaleEdilenEvrakRaporuPage havaleEdilenEvrakRaporuPage;

//    static final Logger logger = LogManager.getLogger("KaydedilenGelenEvrakHavaleTest");
//    User yakyol = new User("yakyol", "123");


    //    User yakyol = new User("yakyol", "123");

    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin", "123");
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
        havaleEdilenEvrakRaporuPage = new HavaleEdilenEvrakRaporuPage();
    }


//    public String getDocPath1() {
//        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
//    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS422: Kişi seçilerek evrak havale etme (detay ekranından)")
    public void TS422() throws InterruptedException {
        String testid = "TS-422";
        konu = "TS-422-" + getSysDate();
        String sayfa1 = "Kaydedilen Gelen Evraklar";
        String sayfa2 = "Birim Havale Edilenler";

        testStatus(testid, "PreCondition Evrak Oluşturma");

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

        testStatus(testid, "Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evrakNoIleEvrakSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikKisiKontrolu(onaylayacakKisi)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS430: Kişiye havale edilen evrakın geri çekilmesi (önizleme ekranından)")
    public void TS430() throws InterruptedException {
        String testid = "TS-430";
        konu = "TS-430-" + getSysDate();
        String sayfa1 = "Birim Havale Edilenler";

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAlKontrol()
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();


        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);
        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konu);

        login(TestData.usernameZTEKIN,TestData.passwordMBOZDEMIR);
        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeHavaleButtonKontrol()
                .onizlemeHavaleYap()
                .havaleAlanKontrolleri()
                .ekranKontrol()
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .buttonGonder()
                .islemMesaji().basariliOlmali();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileEvrakGelmediKontrolu(konu);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2286: Havaleden geri çekilen evrakın onaylı havale edilmesi (önizleme ekranından)")
    public void TS2286() throws InterruptedException {
        String testid = "TS-2286";
        konu = "TS-2286-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        testStatus(testid, "PreCondition Evrak Oluşturma");

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
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl();
//                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .ekranKontrolEvrakDetayi()
                .icerikHavaleAlanKontrolleri()
//                .icerikHavaleIslemleriKisiDoldur(kisi)
//                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenIcerikBirimKontrolu(birim)
                .eklenenIcerikBirimOpsiyonKontrolu(gerek)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .kaydet()
                .evrakDetayiKaydetPopUpClose()
                //TODO : 6. test adımından sonra yeni bir adım eklenmeli. Aksi takdirde havaleOnayınaGelenlerPage sayfasına ulaşmaz.
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleOnayIkonKontrolu()
                .havaleOnay()
//                .eklenenKisiKontrolu(kisi)
                .havaleOnayiBirimDoldur(birim)
                .eklenenOnizlemeBirimKontrolu(birim)
//                .eklenenBirimKontrolu(birim)
                .dagitimBilgileriBirimOpsiyon(bilgi)
                .eklenenBirimOnizlemeOpsiyonKontrolu(bilgi)
//                .eklenenBirimOpsiyonKontrolu(bilgi)
                .havaleOnayiOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .havaleyiOnaylamakUzeresinizEvet()
                .islemMesaji().basariliOlmali();

        havaleOnayiVerdiklerim
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(TestData.usernameYAKYOL, TestData.passwordYAKYOL);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);
        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1586: Geri çekilen evrakın tekrar havalesi - toplu")
    public void TS1586() throws InterruptedException {
        String testid = "TS-1586";
        String konu1 = "TS-1586-" + getSysDate();
        String sayfa1 = "Kaydedilen Gelen Evraklar";
        String sayfa2 = "Birim Havale Edilenler";

        testStatus(testid, "PreCondition 1. Evrak Oluşturma");
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
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu1)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu1)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
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
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu2)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu2)
                .onizlemeGeriAl()
                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evraklariSecTopluHavaleYap(konu1, konu2, true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .gonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu1)
                .evrakNoIleTabloKontrolu(konu2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1585: Toplu evrak havale - kullanıcı listesine")
    public void TS1585() throws InterruptedException {
        String testid = "TS-1585";
        String konu1 = "TS-1585-" + getSysDate();
        String sayfa1 = "Kaydedilen Gelen Evraklar";
        String sayfa2 = "Birim Havale Edilenler";
        String kullanici = "TS2994";
        String kullaniciDetails = "Ts2994";
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        testStatus(testid, "PreCondition 1. Evrak Oluşturma");
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

//        kaydedilenGelenEvraklarPage
//                .openPage()
//                .evrakNoIleEvrakSec(konu1)
//                .tabloEvrakNoileIcerikSec(konu1)
//                .icerikHavaleYap()
//                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
//                .icerikHavaleOnayinaGonder()
//                .islemMesaji().basariliOlmali();
//
//        birimHavaleEdilenlerPage
//                .openPage()
//                .evrakNoIleTablodanEvrakSecme(konu1)
//                .onizlemeHavaleGeriAl()
//                .onizlemeNotAlanınıDoldur(konu1)
//                .onizlemeGeriAl()
//                .islemMesaji().basariliOlmali();

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
        String konu2 = "TS-1585-" + getSysDate();

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

//        kaydedilenGelenEvraklarPage
//                .openPage()
//                .evrakNoIleEvrakSec(konu2)
//                .tabloEvrakNoileIcerikSec(konu2)
//                .icerikHavaleYap()
//                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)
//                .icerikHavaleOnayinaGonder()
//                .islemMesaji().basariliOlmali();
//
//        birimHavaleEdilenlerPage
//                .openPage()
//                .evrakNoIleTablodanEvrakSecme(konu2)
//                .onizlemeHavaleGeriAl()
//                .onizlemeNotAlanınıDoldur(konu2)
//                .onizlemeGeriAl()
//                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        kaydedilenGelenEvraklarPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .evraklariSecTopluHavaleYap(konu1, konu2, true);
        //checkbox ların checked edildigi kontrolu

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleKisiListesi(kullanici)
                .kullaniciGrupDetayEvet()
                .havaleKisiListesiKontrolu(kullanici)
                .eklenenKisiListesiOpsiyonKontrolu(gerek)
                .aciklamaDoldur(konu1 + " " + konu2)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .gonder()
                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu1)
                .evrakNoIleTabloKontrolu(konu2);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
        gelenEvrakKayitPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu1)
                .evrakNoIleTabloKontrolu(konu2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS440: Toplu evrak havale - kullanıcı listesine")
    public void TS440() throws InterruptedException {
        String testid = "TS-440";
        String konu1 = "TS-440-" + getSysDate();
        String sayfa1 = "Kaydedilen Gelen Evraklar";
        String sayfa2 = "Birim Havale Edilenler";

        testStatus(testid, "PreCondition 1. Evrak Oluşturma");
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

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
        String konu2 = "TS-440-" + getSysDate();

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
                .sayfaKontrol(sayfa1)
                .evraklariSecTopluHavaleYap(konu1, konu2, true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .gonder()
                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu1)
                .evrakSecIcerikGoster(konu1, true)
                .ekranKontrolEvrakDetayi()
                .havaleGeriAl()
                .notAlanınıDoldur(konu1)
                .icerikGeriAl();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakBulunamadı(konu1);

        birimHavaleEdilenlerPage
                .openPage()
                .sayfaKontrol(sayfa2)
                .evrakNoIleTabloKontrolu(konu2)
                .evrakSecIcerikGoster(konu2, true)
                .ekranKontrolEvrakDetayi()
                .havaleGeriAl()
                .notAlanınıDoldur(konu2)
                .icerikGeriAl();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakBulunamadı(konu2);

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileEvrakKontrolu(konu1)
                .tabloEvrakNoileEvrakKontrolu(konu2)
                .evraklariSecTopluHavaleYap(konu1, konu2, true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .gonder()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu1)
                .tabloEvrakNoSec(konu2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1370: Kaydedilen gelen evrak havalesinin Havale Edilen Evrak Raporundan kontrolü")
    public void TS1370() throws InterruptedException {
        String testid = "TS-1370";
        konu = "TS-1370-" + getSysDate();
        String evrakTarihi = getSysDateForKis();

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi, onayKisiDetails)
                .icerikHavaleOnayinaGonder();
//                .islemMesaji().basariliOlmali();

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu)
                .onizlemeHavaleGeriAl()
                .onizlemeNotAlanınıDoldur(konu)
                .onizlemeGeriAl();
//                .islemMesaji().basariliOlmali();

        kaydedilenGelenEvraklarPage
                .openPage()
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(kisi)
                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenIcerikBirimKontrolu(birim)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .kaydet()
                .evrakDetayiKaydetPopUpClose()
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdilenEvrakRaporAlanKontrolu()
                .havaleEdilenBirimDoldur(birim)
                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
                .havaleTarihAraligiBitisDoldur(evrakTarihi)
                .sorgula()
                .rapordaEvraklarıListele(konu);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdilenKullaniciDoldur(onaylayacakKisi)
                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
                .havaleTarihAraligiBitisDoldur(evrakTarihi)
                .sorgula()
                .rapordaEvraklarıListele(konu)
                .rapordaEvraklarıListeleDetayTikla(konu)
                .ekranKontrolEvrakDetayi();

//        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        //Bug: Raporda havaleEden doldurulmuyor
//        havaleEdilenEvrakRaporuPage
//                .openPage()
//                .havaleEdenKullaniciDoldur(onaylayacakKisi)
//                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
//                .havaleTarihAraligiBitisDoldur(evrakTarihi)
//                .sorgula()
//                .rapordaEvraklarıListele(konu);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2292: Kaydedilen Gelen evraklar listesi - havale ekranı alan kontrolleri")
    public void TS2292() throws InterruptedException {
        String testid= "TS-2292";
        konu = "TS-2292-" + getSysDate();
        String evrakTarihi = getSysDateForKis();
        String disKullanici = "distest";
        String kurum = "Cumhurbaşkanlığı";
        String ustBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName ="test.txt";
        String personel = "Ali Osman TOPRAK";
        String dikkatMesaj = "Havaleyi onaylayacak kullanıcıyı seçiniz.";
        String ustBirimKullanici = "alkanseker";

//        logger.info(testid + " nolu test başladı:");

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
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .ekranKontrolEvrakDetayi()
                .havaleIslemleriKisiKontrol(disKullanici)
                .havaleIslemleriKisiKontrol(ustBirimKullanici)
                .havaleIslemleriBirimKontrol(kurum)
                .havaleIslemleriBirimKontrol(ustBirim)
                .icerikHavaleIslemleriKisiDoldur(onaylayacakKisi,onayKisiDetails)

                .icerikDosyaEkle()
                .icerikHavaleDosyaEkle(pathToFileText)
                .icerikHavaleDosyaEkleDosyaAdiKontrol(fileName)
                .icerikDosyaDeleteIcon()
                .icerikHavaleDosyaEkleDosyaAdiKontrol(fileName,false)
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().dikkatOlmali(dikkatMesaj);

        kaydedilenGelenEvraklarPage
                .havaleIslemleriOnaylayacakKisiKontrol(personel);

    }

}
