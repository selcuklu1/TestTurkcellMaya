package tests.SuresizEvrakKapatma;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KlasorEvrakIslemleriPage;
import pages.ustMenuPages.PersonelVeAcikEvrakIstatigiPage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SuresizEvrakKpatmaTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTests() {

    }

    @Test(enabled = true, description = "TS0073 : Gelen Evrakın Kapatılması ve Klasöre kaldırılması")
    public void TS0073() throws InterruptedException {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();
        // Gelen evrak kayıt oluşturma>>>

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0073-" + randomNumber;

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
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakiKapat();

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true);

        klasoreKaldirdiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

    }

    @Test(enabled = true, description = "TS0074 : Kapatma onayındaki evrakın reddedilmesi")
    public void TS0074() throws InterruptedException {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        OnayaSunduklarimPage onayaSunduklarimPage = new OnayaSunduklarimPage();
        ImzaBekleyenlerPageKapatmaIslemleri imzaBekleyenlerPageKapatmaIslemleri = new ImzaBekleyenlerPageKapatmaIslemleri();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();

        // Gelen evrak kayıt oluşturma>>>

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0074-" + randomNumber;

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
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKapatmaTipiSec("Kapatma")
                .evrakKapatKonuKodu("Entegrasyon İşlemleri")
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS0074")
                .evrakKapatOnayAkisiKullaniciSec("Huser TUMER")
                .evrakKapatOnayAkisiKullaniciTipiSec("Huser TUMER", "Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun();

        onayaSunduklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login("huser", "123");


        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakSec(gelenEvrakKonu, "", "", "")
                .kapatmaImzala()
                .kapatmayIptalEt()
                .islemMesaji().basariliOlmali();

        logout();

        login("huser", "123");

        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakKontrol(gelenEvrakKonu, false);

        login("huser1", "123");

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

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

    @Test(enabled = true, description = "TS0077 : Klasörden çıkartılan evrak - \"Gelen Evrak\", \"Kapattıklarım\" ve \"Klasöre Kaldırdıklarım\" listelerinden kontrolü")
    public void TS0077() throws InterruptedException {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        KapattiklarimPage kapattiklarimPage = new KapattiklarimPage();
        KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();

        // Gelen evrak kayıt oluşturma>>>

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0077-" + randomNumber;

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
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakiKapat();

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true)
                .evrakiKlasordenCikar(gelenEvrakKonu);

        kapattiklarimPage
                .openPage();

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

    @Test(enabled = true, description = "TS0383 : Evrak Onaylı Kapatma ve Klasöre Kaldırılması Kontrolü")
    public void TS0383() throws InterruptedException {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        OnayaSunduklarimPage onayaSunduklarimPage = new OnayaSunduklarimPage();
        ImzaBekleyenlerPageKapatmaIslemleri imzaBekleyenlerPageKapatmaIslemleri = new ImzaBekleyenlerPageKapatmaIslemleri();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        KapattiklarimPage kapattiklarimPage = new KapattiklarimPage();
        // Gelen evrak kayıt oluşturma>>>

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0383-" + randomNumber;

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
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKapatmaTipiSec("Kapatma")
                .evrakKapatKonuKodu("Entegrasyon İşlemleri")
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS0074")
                .evrakKapatOnayAkisiKullaniciSec("Huser TUMER")
                .evrakKapatOnayAkisiKullaniciTipiSec("Huser TUMER", "Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun();

        onayaSunduklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login("huser", "123");


        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakSec(gelenEvrakKonu, "", "", "")
                .kapatmaImzala()
                .kapatmaImzalaImzala();

        login("huser1", "123");

        kapattiklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true)
                .evrakiKlasordenCikarButonKontrolu(gelenEvrakKonu);


    }

    @Test(enabled = true, description = "TS2167 : Evrakın Teslim Alınıp Kapatılması ve Klasöre Kaldırılması")
    public void TS2167() throws InterruptedException {

        KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();
        TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS2167-" + randomNumber;

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

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, true);

    }

    @Test(enabled = true, description = "TS2166 : Evrak Kapatma - Alan Kontrolleri")
    public void TS2166() {

        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();

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

        login("ztekin", "123");

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konuKoduRandom, "BÜYÜK HARFLERLE KURUM", kullanici);
        login("ztekin", "123");
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
                .kaldirilicakKlasorlerKisiselKlasorlerimSec()
                .kisiselKlasorlerimSecKaldirilicakKlasorlerDataGeldigiGorme()
                .evrakKapatEvrakKapat()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .evrakSec(konuKoduRandom2,"","","")
                .kapatKontrol()
                .evrakKapat()
                .evrakKapatmaEkranGeldigiGorme()
                .kapatmaTipiTikla()
                .evrakKapatKonuKodu(konuKodu)
                .evrakKapatKaldirilacakKlasorlerDoldur(kaldirilacakKlasör)
                .evrakKapatNotDoldur(randomSayi)
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
                .islemMesaji().isBasarili();

    }

    @Test(enabled = true, description = "TS2169 : Kapatılan evrakın Personel ve Açık Evrak İstatistiği raporunda kontrolü")
    public void TS2169() {

        PersonelVeAcikEvrakIstatigiPage personelVeAcikEvrakIstatigiPage = new PersonelVeAcikEvrakIstatigiPage();
        login("huser1", "123");
        String gelenEvrakKonu = "TS0073-20180221100551";

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