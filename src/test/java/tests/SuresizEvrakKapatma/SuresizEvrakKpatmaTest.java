package tests.SuresizEvrakKapatma;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.TestData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KlasorEvrakIslemleriPage;
import pages.ustMenuPages.PersonelVeAcikEvrakIstatigiPage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SuresizEvrakKpatmaTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    KlasorEvrakIslemleriPage klasorEvrakIslemleriPage;
    KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage;
    OnayaSunduklarimPage onayaSunduklarimPage;
    ImzaBekleyenlerPageKapatmaIslemleri imzaBekleyenlerPageKapatmaIslemleri;
    KapattiklarimPage kapattiklarimPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    PersonelVeAcikEvrakIstatigiPage personelVeAcikEvrakIstatigiPage;
    ImzaladiklarimPageKapatmaIslemleri imzaladiklarimPageKapatmaIslemleri;

    @BeforeMethod
    public void loginBeforeTests() {

        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();
        onayaSunduklarimPage = new OnayaSunduklarimPage();
        imzaBekleyenlerPageKapatmaIslemleri = new ImzaBekleyenlerPageKapatmaIslemleri();
        kapattiklarimPage = new KapattiklarimPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        personelVeAcikEvrakIstatigiPage = new PersonelVeAcikEvrakIstatigiPage();
        imzaladiklarimPageKapatmaIslemleri = new ImzaladiklarimPageKapatmaIslemleri();
    }

    @Test(enabled = true, description = "TS0073 : Gelen Evrakın Kapatılması ve Klasöre kaldırılması")
    public void TS0073() throws InterruptedException {

        // Gelen evrak kayıt oluşturma>>>
        testStatus("Data Oluşturma", "Gelen evrak kayıt oluşturma");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0073-" + randomNumber;
        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<
        testStatus("TS0073", "Test Başlamıştır");

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakiKapat()
                .islemMesaji().basariliOlmali(basariMesaji);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .aramaKriteriDoldur("TS0073")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true);

        klasoreKaldirdiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

    }

    @Test(enabled = true, description = "TS0074 : Kapatma onayındaki evrakın reddedilmesi")
    public void TS0074() throws InterruptedException {

        // Gelen evrak kayıt oluşturma>>>
        testStatus("Data Oluşturma", "Gelen evrak kayıt oluşturma");

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0074-" + randomNumber;
        String basariMesaji = "İşlem başarılıdır!";
        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<

        testStatus("TS0074", "Test Başlamıştır");

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKapatmaTipiSec("Kapatma")
                .evrakKapatKonuKodu("Entegrasyon İşlemleri")
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS0074")
                .evrakKapatOnayAkisiKullaniciSec("Huser TUMER")
                .evrakKapatOnayAkisiKullaniciTipiSec("Huser TUMER", "Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayaSunduklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login(TestData.usernameHuser, TestData.passwordHuser);

        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakKonusunaGoreKontrol(gelenEvrakKonu)
                .evrakSec(gelenEvrakKonu, "", "", "")
                .kapatmaImzala()
                .kapatmayIptalEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        Selenide.refresh();

        logout();

        login(TestData.usernameHuser, TestData.passwordHuser);

        imzaladiklarimPageKapatmaIslemleri
                .openPage()
                .evrakKontrol(gelenEvrakKonu, false);

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, false);

        gelenEvraklarPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

    }

    @Test(enabled = true, description = "TS0077: Klasörden çıkartılan evrak - \"Gelen Evrak\", \"Kapattıklarım\" ve \"Klasöre Kaldırdıklarım\" listelerinden kontrolü")
    public void TS0077() throws InterruptedException {

        // Gelen evrak kayıt oluşturma>>>
        testStatus("Data Oluşturma", "Gelen evrak kayıt oluşturma");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0077-" + randomNumber;
        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<
        testStatus("TS0077", "Test Başlamıştır");

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakiKapat()
                .islemMesaji().basariliOlmali(basariMesaji);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .aramaKriteriDoldur("TS0077")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true)
                .evrakiKlasordenCikar(gelenEvrakKonu)
                .islemMesaji().basariliOlmali(basariMesaji);

        Selenide.refresh();

        gelenEvraklarPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        kapattiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        klasoreKaldirdiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, false);

    }

    @Test(enabled = true, description = "TS0383: Evrak Onaylı Kapatma ve Klasöre Kaldırılması Kontrolü")
    public void TS0383() throws InterruptedException {

        // Gelen evrak kayıt oluşturma>>>
        testStatus("Data Oluşturma", "Gelen evrak kayıt oluşturma");

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0383-" + randomNumber;
        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String basariMesaji = "İşlem başarılıdır!";

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<
        testStatus("TS0383", "Test Başlamıştır");

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKapatmaTipiSec("Kapatma")
                .evrakKapatKonuKodu("Entegrasyon İşlemleri")
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS0074")
                .evrakKapatOnayAkisiKullaniciSec("Huser TUMER")
                .evrakKapatOnayAkisiKullaniciTipiSec("Huser TUMER", "Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayaSunduklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login(TestData.usernameHuser, TestData.passwordHuser);

        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakSec(gelenEvrakKonu, "", "", "")
                .kapatmaImzala()
                .kapatmaImzalaImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPageKapatmaIslemleri
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        kapattiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true)
                .evrakiKlasordenCikarButonKontrolu(gelenEvrakKonu);

        klasoreKaldirdiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true)
                .evrakSec(gelenEvrakKonu, "", "", "")
                .btnEvrakGecmisiTab()
                .evrakGoruntulenmekUzereKlasoreKaldirilmasi("Evrak Klasöre kaldırıldı");
    }

    @Test(enabled = true, description = "TS2167 : Evrakın Teslim Alınıp Kapatılması ve Klasöre Kaldırılması")
    public void TS2167() throws InterruptedException {

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS2167-" + randomNumber;
        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTarihiDoldur(getSysDateForKis())
                .geldigiKurumDoldurLovText("YAZILIM GELİŞTİRME")
                .evrakSayiSagDoldur(randomNumber)
                .havaleIslemleriBirimDoldur("YAZILIM GELİŞTİRME")
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec(gelenEvrakKonu, "", "", "")
                .teslimAlVeKapat()
                .teslimAlVeKapatAlanGeldigiGorme()
                .kaldirilacakKlasorlerDoldur("gündem")
                .teslimAlveKapatTeslimAlVeKapat();

        klasoreKaldirdiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true);

    }

    @Test(enabled = true, description = "TS2166 : Evrak Kapatma - Alan Kontrolleri")
    public void TS2166() {

        String konuKoduRandom = "TS2066-" + createRandomText(15);
        String konuKoduRandom2 = "TS2066-" + createRandomText(15);
        String uyariMesaji = " Zorunlu alanları doldurunuz";
        String kaldirilacakKlasör = "diğer";
        String konuKodu = "Diğer";
        String randomSayi = createRandomText(251);
        String kullanici = "Zübeyde Tekin";
        String kullanicihkandur = "Hamza Kandur";
        String kullanicimbozdemir = "Mehmet BOZDEMİR";
        String kapat = "Kapatma";
        String sureliKapatma = "Süreli Kapatma";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konuKoduRandom, "BÜYÜK HARFLERLE KURUM", kullanici);

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konuKoduRandom2, "BÜYÜK HARFLERLE KURUM", kullanici);

        testStatus("TS2166","Test Başlamıştır.");

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, "", "", "")
                .kapatKontrol()
                .evrakKapat()
                .kapatmaTipiTikla()
                .evrakKapatEvrakKapat()
                .islemMesaji().isUyari(uyariMesaji);

        gelenEvraklarPage
                .evrakKapatKaldirilacakKlasorlerDoldur(kaldirilacakKlasör)
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarSec()
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarGeldigiGorme()
                .kaldirilicakKlasorlerKisiselKlasorlerimSec()
                .kisiselKlasorlerimSecKaldirilicakKlasorlerDataGeldigiGorme()
                .evrakKapatEvrakKapat()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .evrakSec(konuKoduRandom2,"","","")
                .kapatKontrol()
                .evrakKapat()
                .evrakKapatmaEkranGeldigiGorme()
                .evrakKapatKonuKodu(konuKodu)
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarSec()
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarGeldigiGorme()
                .evrakKapatKisiselKlasorlerimKaldir()
                .evrakKapatKaldirilacakKlasorlerDoldur(kaldirilacakKlasör)
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarSec()
                .evrakKapatKaldirilacakKlasorlerSikKullanilanlarGeldigiGorme()
                .evrakKapatNotDoldur("Evrak Kapatma Notu") //randomSayi
                .onayAkisiDoldur(" Zübeyde Tekin")
                .onayAkisiSil()
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS2166")
                .evrakKapatOnayAkisiKullaniciSec(kullanicimbozdemir)
                .evrakKapatOnayAkisiKullaniciTipiSec(kullanicimbozdemir,"Kapatma Parafı")
                .evrakKapatOnayAkisiKullan()
                .islemMesaji().isUyari(uyariMesaji);

        gelenEvraklarPage
                .evrakKapatOnayAkisiKullaniciSec(kullanicihkandur)
                .evrakKapatOnayAkisiKullaniciTipiSec(kullanicihkandur,"Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Test(enabled = true, description = "TS2169 : Kapatılan evrakın Personel ve Açık Evrak İstatistiği raporunda kontrolü")
    public void TS2169() {

        String gelenEvrakKonu = "TS0073-20180221100551";

        login(TestData.usernameHuser1, TestData.passwordHuser1);

        /*
        String randomNumber = "" + getSysDate();
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakiKapat();
        */

        String tarih = "21.02.2018";

        personelVeAcikEvrakIstatigiPage
                .openPage()
                .personelDoldur("Huser1 TUMER1")
                .personelIstatigiSorgula()
                .personelTabTarihAraligiDoldurVeAra(tarih, tarih)
                .cevapsizKapatilanlarKontrol()
                .klasoreKaldirdiklariKontrol()
                .klasoreKaldirilanlarEvrakGoster()
                .klasoreKaldirilanlarEvrakKontrol(gelenEvrakKonu, true)
                .cevapsizKapatilanlarEvrakGoster()
                .cevapsizKapatilanlarEvrakKontrol(gelenEvrakKonu, true);

    }

}