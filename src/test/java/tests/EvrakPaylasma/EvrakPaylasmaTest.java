package tests.EvrakPaylasma;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.altMenuPages.CevapYazPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;
import pages.ustMenuPages.SistemLoglariPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static data.TestData.passwordZTEKIN;
import static data.TestData.userMbozdemir;
import static data.TestData.usernameZTEKIN;


public class EvrakPaylasmaTest extends BaseTest {

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;
    TaslakEvraklarPage taslakEvraklarPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    EvrakOlusturPage evrakOlusturPage;
    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    ImzaladiklarimPage imzaladiklarimPage;
    SistemLoglariPage sistemLoglariPage;
    MesajlarPage mesajlarPage;
    MainPage mainPage;

    @BeforeMethod
    public void loginBeforeTests() {
        //login("mbozdemir", "123");
        login(userMbozdemir);
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        sistemLoglariPage = new SistemLoglariPage();
        mainPage = new MainPage();
        mesajlarPage = new MesajlarPage();
    }

    @Test(enabled = true, description = "TS1881 : Evrak paylaşımını durdurma")
    public void TS1881() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS1881-" + getSysDate();

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = paylasilacakUser;
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("TS1881 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilan, tarihBugun)
                .paylasTabTikla()
                .paylasimiDurdur()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);


        login("huser", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakKontrol(paylasan, tarihBugun, evrakKonu, false)
                .durdurulmusPaylasimlarSec()
                .filtrele()
                .evrakSec(paylasan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);
    }

    @Test(enabled = true, description = "TS1882 : Paylaştıklarım listesinden evrak paylaşma")
    public void TS1882() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String paylasilanKullanici = "Huser TUMER";
        String paylasanKisi = "Mehmet BOZDEMİR";
        String paylasanKisiNotAciklamasi = "TS1882 : Paylaştıklarım listesinden evrak paylaşma";
        String konu = "TS1882 " + getRandomNumber(1000, 9000);

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR";
        String tabPaylaşılanlar = "Paylaşılanlar";
        String bi̇ri̇m = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String durumu = "Paylaşımda";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturSayfaKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilanKullanici)
                .paylasanAciklamaDoldur("TS1882 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        String[] paylasilacakKullanicilar = new String[]{
                "Huser1 TUMER1",
                "Huser2 TUMER2",
                "Huser3 TUMER3"
        };

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasilanKontrol(paylasilanKullanici, "", "Paylaşımda", "")
                .paylasTabTikla()
                .paylasKisiSec(paylasilacakKullanicilar)
                .paylasimAciklamaYaz(paylasanKisiNotAciklamasi)
                .paylas()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi, tarihBugun, paylasanKisiNotAciklamasi)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasilanKontrolTumKullanıcılıar(paylasilacakKullanicilar, durumu);

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuSilinmemeliKontrolet(paylasanKisi, tarihBugun, paylasanKisiNotAciklamasi)
                .evrakNotuKontrol(paylasanKisi, tarihBugun, paylasanKisiNotAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(konu)
                .evrakNotuSil("huser1", tarihBugun, konu)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(konu);

        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Huser1 TUMER1", tarihBugun, konu);

        String baslangicTarihi = tarihBugun;

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + baslangicTarihi;

        // String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihBugun, kullanici.toUpperCase(), aciklama, true);

    }

    @Test(enabled = true, description = "TS1877 : Paylaşılan evrakın geri alınması")
    public void TS1877() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS1881-" + getSysDate();

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR";

        String paylasan = "Mehmet BOZDEMİR";
        String[] paylasilanlar = new String[]{"Huser TUMER", "Huser1 TUMER1", "Huser2 TUMER2"};
        String[] paylasimdanGeriAlanicaklar = new String[]{"Huser TUMER", "Huser2 TUMER2"};

        String paylasilmaTarihi = "";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilanlar)
                .paylasanAciklamaDoldur("TS1877 için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage();

        paylasilmaTarihi = paylastiklarimPage.evrakPaylasimTarihiGetir(paylasilanlar, evrakKonu, "", tarihBugun);

        paylastiklarimPage
                .evrakSec(paylasilanlar, evrakKonu, "", tarihBugun)
                .paylasimdanGeriAlTabSec()
                .paylasimdanGeriAl(paylasimdanGeriAlanicaklar);


        login("huser", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonu, paylasilmaTarihi, false);

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonu, paylasilmaTarihi, true);

    }

    //Pass
    @Test(enabled = true, description = "TS1876 : Evrakı paylaşmada alan kontrolleri")
    public void TS1876() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakkonu = "TS1876" + getRandomNumber(1000, 9000);

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakkonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali();
        evrakOlusturPage.evrakOlusturPageKapat()
                .islemMesaji().closeMessage();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakkonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasPaylasGonder()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        taslakEvraklarPage
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("")
                .paylasPaylasGonder()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        taslakEvraklarPage
                .paylasilanKisileriTemizle()
                .paylasanAciklamaDoldur("yeni açkılmala")
                .paylasPaylasGonder()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        taslakEvraklarPage
                .paylasKisiDoldur(paylasilacakUser)
                .paylasilanKisileriTemizle()
                .paylasanAciklamaDoldur("yeni açkılmala")
                .paylasPaylasGonder()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        taslakEvraklarPage
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("TS1876")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakkonu, "", paylasilacakUser, tarihBugun)
                .paylasTabTikla()
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasimAciklamaYaz("")
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        paylastiklarimPage
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        String baslangicTarihi = tarihBugun;

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + baslangicTarihi;

        //         String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihBugun, kullanici.toUpperCase(), aciklama, true);


    }

    @Test(enabled = true, description = "TS1876A : Taslak Evrakı kullanıcı ile paylaşma (Tümü aksiyonu ile)")
    public void TS1876A() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakkonu = "TS1876A" + getSysDate();

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = paylasilacakUser;
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakkonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .islemMesaji().basariliOlmali();
        evrakOlusturPage.evrakOlusturPageKapat()
                .islemMesaji().closeMessage();

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yeni açıklama notu";

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakkonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST")
                .paylasKisiDoldur("Huser1 TUMER1")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();
        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        login("optiim", "123");

        mesajlarPage
                .openPage()
                .mesajSec("", evrakkonu, "Evrak Paylaşıldı");

        login("mbozdemir", "123");

        String paylasilanlar = "Optiim TEST / Huser1 TUMER1";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakkonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "");

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakkonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakkonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST", tarihBugun, yeniEvrakPaylasimNotu);

        String baslangicTarihi = tarihBugun;

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);

    }

    @Test(enabled = true, description = "TS1904 : Evrak paylaşmada not kontrolü")
    public void TS1904() {

        login("mbozdemir", "123");

        String evrakKonu = "TS1904" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakGidecegiYer = "TS1904Birim";
        String evrakGonderen = "Mehmet BOZDEMİR";
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSec(evrakGidecegiYer)
                .onayAkisiEkle()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiKullaniciEkle("Paraf BEKLEYEN")
                .onayAkisiKullaniciTipiSec("Paraf BEKLEYEN", "Paraflama")
                .kendimiEkle()
                .onayAkisiKullaniciTipiSec(evrakGonderen, "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("deneme bir içerik");

        evrakOlusturPage
                .kaydetOnayaSun()
                .kaydetOnayaSunGonder()
                .kaydetOnayaSunGonderEvet();

        login("parafbekleyen01", "123");

        String evrakTarih = "";
        String basariMesaji = "İşlem başarılıdır!";
        String evrakiPaylasan = "Paraf BEKLEYEN";

        String notEkleyen = "Huser TUMER";
        String eklenenNot = evrakKonu + "konulu evrak not";
        String paylasilan = "Huser TUMER";

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .paylasTabTikla()
                .paylasKisiSec("Huser TUMER")
                .paylasimAciklamaYaz("Deneme bir açıklama girdim.")
                .paylasPaylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        login("huser", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(eklenenNot);

        login("parafbekleyen01", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilan, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, "", eklenenNot);

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, tarihBugun, eklenenNot, false);

    }

    //TODO Can Şeker yazmıştır
    @Test(enabled = true, description = "TS1905: Evrak paylaşma yetkisi olmayan kullanıcıda evrak paylaşma kontrolü")
    public void TS1905() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konu = "TS1905_" + getSysDate();//+ createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Normal";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisi2 = "Ömer ÖZÜLKÜ";
        String aciklama = createRandomText(15);
        String birim = "BHUPGMY";
        String anaBirim = "Yazılım Geliştirme Direktörlüğ";
        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(usernameZTEKIN, passwordZTEKIN);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .islemMesaji().basariliOlmali(basariMesaji);
        //TODO

        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, kurum, evrakTarihi, evrakSayiSag)
                .paylas()
                .paylasBirim()
                .paylasKisiSecBirim(kisi2, birim)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(konu, evrakTarihi)
                .evrakNotlariTabAc()
                .evrakNotlariAciklamaGorme(aciklama)
                .paylasilanlarTabAc()
                .paylasilanlarKullaniciGorme(kisi2);

        login("oozulku", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(kisi, evrakTarihi, konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(aciklama)
                .evrakNotuKontrol(kisi2, "2018", aciklama);

        sistemLoglariPage
                .openPage()
                .kullaniciSec(kisi2)
                .sorgula()
                .sistemRaporuGeldigiGorme("Paylaşım Notu Girme", evrakTarihi, kisi2);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

        imzaladiklarimPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

    }

    @Test(enabled = true, description = "TS2195 : Cevap evrakını paylaşma")
    public void TS2195() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS2195-" + randomNumber;
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiYer)
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();
        String kayitTarihiSayi = tarihBugun + " / " + evrakNo;
        //gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .evrakiSec(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, "")
                .cevapYaz();

        CevapYazPage cevapYazPage = new CevapYazPage();

        cevapYazPage
                .konuDoldur(konu + "Cevap")
                .kaldirilacakKlasorlerDoldur("Diğer")
                .kaydet()
                .evrakKayitPopupEvet();

        String evrakAciklamasi = "Cevap evrakını paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Cevap evrakını paylaşma NOT 2";
        String yeniPaylasan = "Huser1 TUMER1";

        taslakEvraklarPage
                .openPage()
                .evrakSec(konu, geldigiYer, tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Huser1 TUMER1")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();

        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(konu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, konu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, konu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, tarihBugun, yeniEvrakPaylasimNotu);

        String baslangicTarihi = tarihBugun;

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);
    }

    @Test(enabled = true, description = "TS2194 : İçeriğinde kişisel şablon olan evrakı paylaşma")
    public void TS2194() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakKonu = "TS2194-" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();
        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST", tarihBugun, yeniEvrakPaylasimNotu);

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(tarihBugun)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);


    }

    @Test(enabled = true, description = "TS2192 : İçeriğinde form olan evrakı paylaşma")
    public void TS2192() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS2192-" + getRandomNumber(1000, 9000);

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Huser1 TUMER1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR";

        String formSablonAdi = "Optiim form şablonu";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .evrakTuruSec("Form")
                .formSablonuSec(formSablonAdi)
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();
        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST", tarihBugun, yeniEvrakPaylasimNotu);

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(tarihBugun)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);


    }

    @Test(enabled = true, description = "TS2193 : İçeriğinde birim içerik şablonu olan evrakı paylaşma")
    public void TS2193() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS2193-" + getSysDate();

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String kullanacakBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true);

        evrakOlusturPage
                .sablonIslemleriTabAc()
                .sablonTuruSec("Birim")
                .sablonAdiDoldur(evrakSablonAdi)
                .kullanacakBirimSec(kullanacakBirim)
                .evrakiYeniSablonOlarakKaydet()
                .birimSablonuEvrakaUygula(evrakSablonAdi);

        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();
        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST", tarihBugun, yeniEvrakPaylasimNotu);

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(tarihBugun)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);


    }

    @Test(enabled = true, description = "TS2197 : Devredilen evrakı paylaşma")
    public void TS2197() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS2197-" + getRandomNumber(1000, 9000);
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR";


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        kullaniciEvrakDevretPage
                .openPage()
                .devredecekKisiSec("Mehmet BOZDEMİR")
                .listele()
                .panelAc("Taslak Evraklar")
                .taslakEvrakSec(evrakKonu, "")
                .devret()
                .devralacakKisiSec("Huser TUMER")
                .aciklamaDoldur("evrak huser e devredildi.")
                .devretTamam();

        login("huser", "123");

        String evrakAciklamasi = "Devredilen evrakı paylaşma NOT 1";
        String evrakiPaylasan = "Huser TUMER";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Devredilen evrakı paylaşma NOT 2";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST", "", yeniEvrakPaylasimNotu);

        String baslangicTarihi = tarihBugun;

        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Huser TUMER";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "huser kullanıcısı " + baslangicTarihi;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihBugun, kullanici.toUpperCase(), aciklama, true);

    }

    @Test(enabled = true, description = "TS1876 : İşlem bekleyen Evrakı kullanıcılarla paylaşma")
    public void TS1876B() {
        String evrakKonu = "TS1876B-" + getSysDate();
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Huser1 TUMER1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Huser1 TUMER1")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();

        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());

        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        login("huser1", "123");

        mesajlarPage
                .openPage()
                .mesajSec("", evrakKonu, "Evrak Paylaşıldı");

        login("mbozdemir", "123");

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, tarihBugun, yeniEvrakPaylasimNotu);


        // Bitiş tarihi
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        // Bitiş Tarihi

        String bitisTarihi = new SimpleDateFormat("dd.MM.yyyy").format(dt);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullanici = "Mehmet BOZDEMİR";
        String aksiyon = "Evrak Paylaş - Paylaş";
        String aciklama = "mbozdemir kullanıcısı " + tarihSaatBugun;

        sistemLoglariPage
                .openPage()
                .baslangicTarihiDoldur(tarihBugun)
                .bitisTarihiDoldur(bitisTarihi)
                .birimSec(birim)
                .kullaniciSec(kullanici)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, tarihSaatBugun, kullanici.toUpperCase(), aciklama, true);


    }


}