package tests.EvrakPaylasma;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;
import pages.ustMenuPages.SistemLoglariPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static data.TestData.*;


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

    @BeforeMethod
    public void loginBeforeTests() {
        login("mbozdemir", "123");
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
    }

    @Test(enabled = true, description = "TS1881 : Evrak paylaşımını durdurma")
    public void TS1881() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS1881-" + getRandomNumber(1000, 9000);

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


        logout();
        login("huser", "123");

        benimlePaylasilanlarPage
                .openPage()
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
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String tabPaylaşılanlar = "Paylaşılanlar";
        String bi̇ri̇m = "OPTİİM BİRİM";
        String durumu = "Paylaşımda";

        String basariMesaji = "İşlem başarılıdır!";

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
                "Optiim TEST1",
                "Optiim TEST2",
                "Optiim TEST3"
        };


        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasTabTikla()
                .paylasKisiSec(paylasilacakKullanicilar)
                .paylasimAciklamaYaz(paylasanKisiNotAciklamasi)
                .paylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi, tarihBugun, paylasanKisiNotAciklamasi)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasilanKontrolTumKullanıcılıar(paylasilacakKullanicilar, durumu);

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi, tarihBugun, paylasanKisiNotAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeSil(konu)
                .evrakNotuGirVeKaydet(konu);

        logout();
        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("Optiim TEST1", tarihBugun, konu);

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

    @Test(enabled = true, description = "TS1877 : Paylaşılan evrakın geri alınması")
    public void TS1877() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS1881-" + getRandomNumber(1000, 9000);

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String paylasan = "Mehmet BOZDEMİR";
        String[] paylasilanlar = new String[]{"Zübeyde TEKİN", "Optiim TEST", "Esin Gül KARABACAKOĞLU"};
        String[] paylasimdanGeriAlanicaklar = new String[]{"Zübeyde TEKİN", "Esin Gül KARABACAKOĞLU"};

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
                .paylasanAciklamaDoldur("TS1877 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage();

        paylasilmaTarihi = paylastiklarimPage.evrakPaylasimTarihiGetir(paylasilanlar, evrakKonu, "", tarihBugun);

        paylastiklarimPage
                .evrakSec(paylasilanlar, evrakKonu, "", tarihBugun)
                .paylasimdanGeriAlTabSec()
                .paylasimdanGeriAl(paylasimdanGeriAlanicaklar);


        logout();
        login("ztekin", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonu, paylasilmaTarihi, false);


        logout();
        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonu, paylasilmaTarihi, true);

    }

    @Test(enabled = true, description = "TS1876 : Evrakı paylaşmada alan kontrolleri")
    public void TS1876() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakkonu = "TS1876" + getRandomNumber(1000, 9000);

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
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
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakkonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("TS1876 case i için evrak oluşturuldu ve paylaşıldı.")
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
        String evrakkonu = "TS1876A" + getRandomNumber(1000, 9000);

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
                .evrakOlusturPageKapat();

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Huser1 TUMER1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakkonu, "", tarihBugun)
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
                .evrakSec(evrakkonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakkonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakkonu, "")
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

    @Test(enabled = true, description = "TS1904 : Evrak paylaşmada not kontrolü")
    public void TS1904() {

        login("mbozdemir", "123");

        String evrakKonu = "TS1904" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakGidecegiYer = "AFYON VALİLİĞİ";
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

        logout();
        login("parafbekleyen01", "123");


        String evrakTarih = "";
        String basariMesaji = "İşlem başarılıdır!";
        String evrakiPaylasan = "Paraf BEKLEYEN";

        String notEkleyen = "Optiim TEST [Ağ (Network) Uzman Yardımcısı]";
        String eklenenNot = evrakKonu + "konulu evrak not";
        String paylasilan = "Optiim TEST";
        //String evrakNo = "9132";

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .paylasTabTikla()
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("Deneme bir açıklama girdim.")
                .paylasPaylas()
                .islemMesaji().basariliOlmali(basariMesaji);


        logout();

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(eklenenNot);

        logout();

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
        String konuKoduRandom = "TS-2227-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisi2 = "Yasemin Çakıl Akyol";
        String aciklama = createRandomText(15);
        String birim = "TEST HASAN BİRİMİ";
        String anaBirim = "Yazılım Geliştirme Direktörlüğ";
        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .paylas()
                .paylasBirim()
                .paylasKisiSecBirim(kisi2, birim)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(konuKoduRandom, evrakTarihi)
                .evrakNotlariTabAc()
                .evrakNotlariAciklamaGorme(aciklama)
                .paylasilanlarTabAc()
                .paylasilanlarKullaniciGorme(kisi2);

        login(username3, password3);

        benimlePaylasilanlarPage
                .birimSec(birim)
                .openPage()
                .evrakSec(kisi, evrakTarihi, konuKoduRandom)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(aciklama)
                .evrakNotuKontrol(kisi2, "2017", aciklama);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

        imzaladiklarimPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

        benimlePaylasilanlarPage
                .birimSec(anaBirim);
    }

    @Test(enabled = true, description = "TS2195 : Cevap evrakını paylaşma")
    public void TS2195() {

        String evrakKonu = "Yurtiçi Projeler";
        String evrakGidecegiYer = "Başbakan Başmüşavirleri(G)";
        String evrakTarihSaat = "05.12.2017 16:42";

        String evrakAciklamasi = "Cevap evrakını paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Cevap evrakını paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TS2194 : İçeriğinde kişisel şablon olan evrakı paylaşma")
    public void TS2194() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Huser1 TUMER1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakKonu = "TS2194-" + getRandomNumber(1000, 9000);
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
                .sablonTuruSec("Kişi")
                .sablonAdiDoldur(evrakSablonAdi)
                .evrakiYeniSablonOlarakKaydet()
                .kisiselSablonuEvrakaUygula(evrakSablonAdi);

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

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
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
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
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

    @Test(enabled = true, description = "TS2193 : İçeriğinde birim içerik şablonu olan evrakı paylaşma")
    public void TS2193() {
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakKonu = "TS2193-" + getRandomNumber(1000, 9000);

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Huser1 TUMER1";

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
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
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
                .devralacakKisiSec("huser TUMER")
                .aciklamaDoldur("evrak huser e devredildi.")
                .devretTamam();

        logout();
        login("huser", "123");

        String evrakAciklamasi = "Devredilen evrakı paylaşma NOT 1";
        String evrakiPaylasan = "Huser TUMER";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Devredilen evrakı paylaşma NOT 2";
        String yeniPaylasan = "Huser1 TUMER1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, "", tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Huser1 TUMER1")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("huser2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "", yeniEvrakPaylasimNotu);

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
        String evrakKonu = "TS1876B-" + getRandomNumber(1000, 9000);
        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Huser1 TUMER1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

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
                .sablonTuruSec("Kişi")
                .sablonAdiDoldur(evrakSablonAdi)
                .evrakiYeniSablonOlarakKaydet()
                .kisiselSablonuEvrakaUygula(evrakSablonAdi);


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

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

        logout();

        login("huser1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, tarihBugun, evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

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