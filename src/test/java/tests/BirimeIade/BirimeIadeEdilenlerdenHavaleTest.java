package tests.BirimeIade;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;


public class BirimeIadeEdilenlerdenHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerimPage;
    GelenEvraklarPage gelenEvraklarPage;

    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin", "123");
    User cseker = new User("cseker", "123");

    @BeforeMethod
    public void loginBeforeTests() {
        login(ztekin);
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleOnayiVerdiklerimPage = new HavaleOnayiVerdiklerimPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1587: Birim seçilerek evrak havale etme (detay ekranından)")
    public void TS1587() throws InterruptedException {
        String testid = "TS-1587";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-1587-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        testStatus(testid, "PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true)
                .ekranIcerikKontrol()
                .içeriktenEvrakTeslimAlHavaleEt()
                .ekranHavaleKontrol()
                .dagitimBilgileriBirimDoldur2(birim)
                .eklenenIcerikBirimKontrolu(birim)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .teslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1588: Kişi seçilerek evrak havalesini onaya sunma (önizleme ekranından)")
    public void TS1588() throws InterruptedException {
        String testid = "TS-1588";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-1588-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";

        String kisi = "Zübeyde TEKİN";
        String islemSureci = "Evrak Teslim Alındı ";

        String onaylayacakKisi = "Mehmet BOZDEMİR";
        String onayKisiDetails = "YGD";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        testStatus(testid, "PreCondition Evrak Oluşturma");
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
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "PreCondition Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .evrakOnizlemeKontrol()
                .onizlemeTeslimAlveHavaleYap()
                .evrakHavaleKontrol()

                .havaleIslemleriKisiDoldur(kisi)
                .eklenenOnizlemeKisiKontrolu(kisi)
                .eklenenKisiOnizlemeOpsiyonKontrolu(gerek)
                .havaleIslemleriOnizlemeKisiOpsiyonSec(bilgi)
                .eklenenKisiOnizlemeOpsiyonKontrolu(bilgi)

                .dagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenOnizlemeOnaylayanKontrolu(onaylayacakKisi)

                .onizlemeDosyaEkle()
                .onizlemeHavaleDosyaEkle(pathToFileText)
                .onizlemeHavaleDosyaEkleDosyaAdiKontrol(fileName)
                .havaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleBilgisiSec()
                .kisiKontrol(kisi);

        login(mbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakSecIcerikGoster(konu, true)
                .icerikEkranKontrol()
                .icerikHavaleOnay()

                .dagitimIcinGonderileceklerKisiKontrolu(kisi)

                .dagitimBilgileriBirimDoldur2(birim)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)
                .havaleIslemleriBirimOpsiyonSec(bilgi)
                .eklenenBirimOpsiyonKontrolu(bilgi)
                .dagitimOnayla()
                .dagitimOnaylaEvet()
                .islemMesaji().basariliOlmali(basariMesaji);


        havaleOnayiVerdiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(ztekin);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konu);

        login(ztekin);

        gelenEvrakKayitPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS463: Birime İade Edilenler listesinden Toplu evrak havale edilmesi")
    public void TS463() throws InterruptedException {
        String testid = "TS-463";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu1 = "TS-463-" + getSysDate();
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

//        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String kisi = "Mehmet BOZDEMİR";
        String kisiDetails = "YGD";
        String kullanici = "TS2994";
        String kullaniciDetails = "Ts2994";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakSayiSag1 = createRandomNumber(5);
        String evrakNo1 ="";
        String gKontrolu = "(G)";
        String gerekicin = "Gereği için";
        String bilgiicin = "Bilgi için";
        String koordinasyonicin = "Koordinasyon İçin";

        testStatus(testid, "PreCondition 1. Evrak Oluşturma");
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
                .evrakSayiSagDoldur(evrakSayiSag1)
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet();

        evrakNo1 = gelenEvrakKayitPage.popUpsv2();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String konu2 = "TS-463-" + getSysDate();

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
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

        String konu3 = "TS-463-" + getSysDate();

        testStatus(testid, "PreCondition 3. Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu3)
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


        testStatus(testid, "PreCondition 1. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "PreCondition 2. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu2)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "PreCondition 3. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu3)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu1)
                .evrakSec(konu2)
                .evrakSec(konu3)
                .evrakSecToplu2(konu1, konu2, konu3, true)

                .havaleIslemleriKisiDetails(kisi, kisiDetails)
                .eklenenOnizlemeKisiKontrolu(kisi)
                .eklenenKisiOnizlemeOpsiyonKontrolu(gerek)

                .havaleAlanKontrolleri()

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenOnizlemeBirimKontrolu(birim)
                .eklenenBirimOnizlemeOpsiyonKontrolu(gerek)

                .havaleKisiListesi(kullanici)
                .eklenenOnizlemeKisiListesiKontrolu(kullanici)
                .eklenenKisiListesiOnizlemeOpsiyonKontrolu(gerek)

                .birimTopluTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);


        birimHavaleEdilenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu1,geldigiKurum,birim, evrakTarihi, evrakNo1)
                .evrakNoIleTabloKontrolu(konu1)
                .evrakNoIleTabloKontrolu(konu2)
                .evrakNoIleTabloKontrolu(konu3);

        login(mbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu1,geldigiKurum,evrakTarihi,evrakNo1,evrakTarihi,evrakSayiSag1,gKontrolu)
                .evrakNoIleEvrakSec(konu1)
                .evrakNoIleEvrakSec(konu2)
                .evrakNoIleEvrakSec(konu3);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu1,geldigiKurum,evrakTarihi,evrakNo1,gerekicin,evrakTarihi,evrakSayiSag1)
                .evrakGeldigiGorme(konu1)
                .evrakGeldigiGorme(konu2)
                .evrakGeldigiGorme(konu3);

        login(ztekin);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu1,geldigiKurum,evrakTarihi,evrakNo1,gerekicin,evrakTarihi,evrakSayiSag1)

                .evrakGeldigiGorme(konu1)
                .evrakGeldigiGorme(konu2)
                .evrakGeldigiGorme(konu3);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1590: Kullanıcı listesi seçilerek evrak havale etme (listeden)")
    public void TS1590() throws InterruptedException {
        String testid = "TS-1590";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-1590-" + getSysDate();
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

//        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak Teslim Alındı ";

        String kisi = "Mehmet BOZDEMİR";
        String kisiDetails = "YGD";
        String kullanici = "TS2994";
        String kullaniciDetails = "Ts2994";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

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
                .popUpsv2();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid, "PreCondition 1. Evrak Iade Et");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);


        testStatus(testid, "Test Başladı");
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .evrakTeslimAlHavaletEt()
                .evrakOnizlemeKontrol()
                .havaleKisiListesi(kullanici)
                .eklenenOnizlemeOnaylayanKontrolu(kullanici)
                .birimTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);


        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu);

        login(mbozdemir);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konu);

    }
}
