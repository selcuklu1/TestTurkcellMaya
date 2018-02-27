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
import pages.ustMenuPages.HavaleEdilenEvrakRaporuPage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/****************************************************
 * Tarih: 2018-02-22
 * Proje: Türksat Functional Test Automation
 * Class: "KaydedilenGelenEvrakHavale" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class GelenEvrakListesindenHavaleTest extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerim;
    TopluEvrakOnizleme topluEvrakOnizleme;
    HavaleEdilenEvrakRaporuPage havaleEdilenEvrakRaporuPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    HavaleEttiklerimPage havaleEttiklerimPage;

    static final Logger logger = LogManager.getLogger("GelenEvrakListesindenHavale");

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
    String kullanici = "Yasemin Çakıl";

    String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
    String details = "BHUPGMY";
    String onaylayacakKisi = "Mehmet BOZDEMİR";
    String onayKisiDetails = "BHUPGMY";
    String kullaniciListesi = "TS2994";
    String onaylayacakPersonel = "Ali Osman TOPRAK";


    @BeforeMethod
    public void loginBeforeTests() {
        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        havaleOnayiVerdiklerim = new HavaleOnayiVerdiklerimPage();
        topluEvrakOnizleme = new TopluEvrakOnizleme();
        havaleEdilenEvrakRaporuPage = new HavaleEdilenEvrakRaporuPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
    }


//    public String getDocPath1() {
//        return "C:\\TestAutomation\\BelgenetFTA\\documents\\";
//    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS823: Kişi seçilerek evrakın onaylı havale edilmesi (içerikten)")
    public void TS823() throws InterruptedException {
        String testid = "TS-823";
        konu = "TS-823-" + getSysDate();

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet()
                .popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(kullanici,details)
                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1591: Evrakın havale onayından geri çekilmesi")
    public void TS1591() throws InterruptedException {
        String testid = "TS-1591";
        konu = "TS-1591-" + getSysDate();

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet()
                .popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(kullanici,details)
                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        testStatus(testid, "Test Başladı");
        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .onizlemeHavaleBilgisiKontrol()
                .onizlemeGeriAlKontrol()
                .havaleBilgisiSec()
                .kisiKontrol(kullanici)
                .geriAlSec()
                .notAlaniKontrol()
                .geriAlNotDoldur(konu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS826: Havale yeri Birim, Kişi, Kullanıcı Listesi seçilerek evrakın havale edilmesi")
    public void TS826() throws InterruptedException {
        String testid = "TS-826";
        konu = "TS-826-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo;

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .havaleIslemleriKisiSec(onaylayacakKisi,details)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .havaleIslemleriKisiOpsiyonSec(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)

                .kullaniciListesiSec(kullaniciListesi)
                .kullaniciListesiKullaniciGrupDetayEvet()
                .eklenenKullaniciListesiKontrolu(kullaniciListesi)
                .eklenenKullaniciListesiOpsiyonKontrolu(gerek)
                .havaleIslemleriKullaniciListesiOpsiyonSec(koordinasyon)
                .eklenenKullaniciListesiOpsiyonKontrolu(koordinasyon)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali();

        havaleEttiklerimPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,birim,evrakTarihi,evrakNo);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1597: Havale onayından geri çekilen evrakın havale edilmesi")
    public void TS1597() throws InterruptedException {
        String testid = "TS-1597";
        konu = "TS-1597-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo;

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabloEvrakNoileIcerikSec(konu)
                .ekranKontrolEvrakDetayi()
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikHavaleIslemleriKisiDoldur(kullanici,details)
                .eklenenIcerikKisiKontrolu(kisi)
                .icerikDagitimBilgileriOnaylayanWithDetails(onaylayacakKisi, onayKisiDetails)
                .eklenenIcerikOnaylayanKontrolu(onaylayacakKisi)
                .icerikHavaleOnayinaGonder2()
                .islemMesaji().basariliOlmali();

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .onizlemeHavaleBilgisiKontrol()
                .onizlemeGeriAlKontrol()
                .havaleBilgisiSec()
                .kisiKontrol(kullanici)
                .geriAlSec()
                .notAlaniKontrol()
                .geriAlNotDoldur(konu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();


        testStatus(testid, "Test Başladı");

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()
                .havaleIslemleriKisiSec(kullanici,details)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .havaleIslemleriKisiStatusKontrol(kullanici,true)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .havaleIslemleriKisiOpsiyonSec(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali();

        havaleEttiklerimPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,birim,evrakTarihi,evrakNo);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS477: Kullanıcıya havale edilen evrakın iade edilmesi")
    public void TS477() throws InterruptedException {
        String testid = "TS-477";
        konu = "TS-477-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo;
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName ="test.txt";

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .havaleIslemleriKisiSec(onaylayacakKisi,details)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)
                .havaleIslemleriKisiOpsiyonSec(bilgi)
                .eklenenKisiOpsiyonKontrolu(bilgi)

                .kullaniciListesiSec(kullaniciListesi)
                .kullaniciListesiKullaniciGrupDetayEvet()
                .eklenenKullaniciListesiKontrolu(kullaniciListesi)
                .eklenenKullaniciListesiOpsiyonKontrolu(gerek)
                .havaleIslemleriKullaniciListesiOpsiyonSec(koordinasyon)
                .eklenenKullaniciListesiOpsiyonKontrolu(koordinasyon)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali();

        havaleEttiklerimPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,birim,evrakTarihi,evrakNo);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        testStatus(testid, "Test Başladı");
        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .evrakAlanKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo)
                .tabloEvrakNoSec(konu)
                .onizlemeIadeEtKontrol()
                .onizlemeIadeEt()
                .onizlemeIadeEdilecekKullaniciKontrolu(kisi)
                .iadeEtNotInputDoldur(konu)
                .onizlemeIadeEtDosyaEkle()
                .onizlemeIadeDosyaEkle(pathToFileText)
                .onizlemeIadeDosyaEkleDosyaAdiKontrol(fileName)
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2291: Gelen evrak listesi- havale ekranı alan kontrolleri")
    public void TS2291() throws InterruptedException {
        String testid = "TS-2291";
        konu = "TS-2291-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo;
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName ="test.txt";
        String disKullanici = "distest";
        String kurum = "Cumhurbaşkanlığı";
        String ustBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String ustBirimKullanici = "alkanseker";
        String uyarıMesajı = "Evrakı kendinize havale edemezsiniz!";
        String uyarıMesajı2 = "Havaleyi onaylayacak kullanıcıyı seçiniz";

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYapKontrol()
                .tabHavaleYap()
                .onizlemeHavaleAlanKontrolleri()

                .havaleIslemleriKisiStatusKontrol(disKullanici,false)
                .havaleIslemleriKisiStatusKontrol(ustBirimKullanici,false)
                .havaleIslemleriBirimStatusKontrol(kurum,false)
                .havaleIslemleriBirimStatusKontrol(ustBirim,false);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .tabHavaleYap()
                .dagitimBilgileriBirimDoldurWithDetails(birim,details)
                .havaleIslemleriKisiSec(kisi,details)
                .havaleYapGonder()
                .islemMesaji().dikkatOlmali(uyarıMesajı);

        gelenEvraklarPage
                .onizlemeHavaleEtDosyaEkle()
                .onizlemeHavaleDosyaEkle(pathToFileText)
                .onizlemeHavaleDosyaEkleDosyaAdiKontrol(fileName,true)
                .onizlemeHavaleEklenenDosyaSil()
                .onizlemeHavaleDosyaEkleDosyaAdiKontrol(fileName,false)
                .onizlemeHavaleOnayinaGonder()
                .islemMesaji().dikkatOlmali(uyarıMesajı2);

        gelenEvraklarPage
                .havaleIslemleriOnaylayacakKisiStatusKontrol(onaylayacakPersonel,false);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS489: Toplu evrak havale edilmesi")
    public void TS489() throws InterruptedException {
        String testid = "TS-489";
        String konu1 = "TS-489-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo1;
        String evrakNo2;
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName ="test.txt";
        String kullanici = "TS2994";
        String kullaniciDetails = "Ts2994";

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo1 = gelenEvrakKayitPage.popUpsv2();

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
        String konu2 = "TS-2291-" + getSysDate();
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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo2 = gelenEvrakKayitPage.popUpsv2();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        testStatus(testid, "Test Başladı");
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu1)
                .tabloEvrakNoSec(konu2)
                .evraklariSecTopluHavaleYap(konu1,konu2,true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleKisiListesi(kullanici)
                .kullaniciGrupDetayEvet()
                .havaleKisiListesiKontrolu(kullanici)
                .eklenenKisiListesiOpsiyonKontrolu(gerek)
                .aciklamaDoldur(konu1 + " " + konu2)
                .aciklamaKontrol(konu1 + " " + konu2)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .gonder()
                .islemMesaji().basariliOlmali();

        havaleEttiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konu1)
                .evrakNoIleEvrakSec(konu2);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu1)
                .tabloEvrakNoSec(konu2);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS2289: Gelen evrak listesinden havalenin Havale Edilen Evrak Raporundan kontrolü")
    public void TS2289() throws InterruptedException {
        String testid = "TS-2289";
        String konu1 = "TS-2289-" + getSysDate();
        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";
        String evrakNo1;
        String evrakNo2;
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName ="test.txt";
        String kullanici = "TS2994";
        String kullaniciDetails = "Ts2994";

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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo1 = gelenEvrakKayitPage.popUpsv2();

        testStatus(testid, "PreCondition 2. Evrak Oluşturma");
        String konu2 = "TS-2289-" + getSysDate();
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
                .dagitimBilgileriKisiSec(kisi)
                .kaydet();

        evrakNo2 = gelenEvrakKayitPage.popUpsv2();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu1)
                .tabloEvrakNoSec(konu2)
                .evraklariSecTopluHavaleYap(konu1,konu2,true);

        topluEvrakOnizleme
                .ekranKontrol()
                .havaleAlanKontrolleri()
                .havaleKisiListesi(kullanici)
                .kullaniciGrupDetayEvet()
                .havaleKisiListesiKontrolu(kullanici)
                .eklenenKisiListesiOpsiyonKontrolu(gerek)
                .aciklamaDoldur(konu1 + " " + konu2)
                .aciklamaKontrol(konu1 + " " + konu2)
                .dosyaEkle()
                .havaleDosyaEkle(pathToFileText)
                .havaleDosyaEkleDosyaAdiKontrol(fileName)
                .gonder()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        testStatus(testid, "Test Başladı");
        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdilenEvrakRaporAlanKontrolu()
                .havaleEdilenBirimDoldur(birim)
                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
                .havaleTarihAraligiBitisDoldur(evrakTarihi)
                .sorgula()
                .rapordaEvraklarıListele(konu1)
                .sorgula()
                .rapordaEvraklarıListele(konu2);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdilenKullaniciDoldur(kisi)
                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
                .havaleTarihAraligiBitisDoldur(evrakTarihi)
                .sorgula()
                .rapordaEvraklarıListele(konu1)
                .sorgula()
                .rapordaEvraklarıListele(konu2)
                .rapordaEvraklarıListeleDetayTikla(konu2)
                .ekranKontrolEvrakDetayi();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdenKullaniciDoldur(kisi)
                .havaleTarihAraligiBaslangicDoldur(evrakTarihi)
                .havaleTarihAraligiBitisDoldur(evrakTarihi)
                .sorgula()
                .rapordaEvraklarıListele(konu1)
                .sorgula()
                .rapordaEvraklarıListele(konu2);
    }


}

