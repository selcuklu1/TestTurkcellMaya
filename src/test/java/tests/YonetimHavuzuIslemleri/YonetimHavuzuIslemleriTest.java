package tests.YonetimHavuzuIslemleri;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

import java.util.Random;


public class YonetimHavuzuIslemleriTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    YonetimHavuzuYonetimiPage yonetimHavuzuYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    OlurYazisiOlusturPage.BilgilerTab olurYazisiBilgilerTab;
    BirimYonetimiPage birimYonetimiPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPage = new EvrakOlusturPage();
        yonetimHavuzuYonetimiPage = new YonetimHavuzuYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        olurYazisiBilgilerTab = new OlurYazisiOlusturPage().new BilgilerTab();
        birimYonetimiPage = new BirimYonetimiPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        login("huser", "123");
    }

    @Test(enabled = true, description = "TS0001 : Yönetim havuzunu pasife alma")
    public void TS0001() {


        String yeniBirimAdi = birimYonetimiPage
                .openPage()
                .birimOlustur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞ");

        String yeniKullanici = kullaniciYonetimiPage
                .openPage()
                .kullaniciOlustur("YAZILIM", "Ağ (Network) Uzmanı");

        String eklenecekYonetimHavuzuAdi = "havuz" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String eklenecekBirim = yeniBirimAdi;
        String eklenecekKullanici = yeniKullanici;

        yonetimHavuzuYonetimiPage
                .openPage()
                .yonetimHavuzuEkle(eklenecekYonetimHavuzuAdi)
                .kullananBirimEkle(eklenecekBirim)
                .kullaniciEkle(eklenecekKullanici)
                .yonetimHavuzuKaydet();

        String pasifeAlinacakHavuzAdi = eklenecekYonetimHavuzuAdi;


        String[] kontrolEdilecekBirimler = new String[]{yeniBirimAdi};
        String[] kontrolEdilecekKullanicilar = new String[]{yeniKullanici};

        yonetimHavuzuYonetimiPage
                .ara(null, pasifeAlinacakHavuzAdi, "Sadece Aktifler", true)
                .yonetimHavuzuGuncelle(pasifeAlinacakHavuzAdi, false)
                .yonetimHavuzuKontrol(pasifeAlinacakHavuzAdi, kontrolEdilecekBirimler, kontrolEdilecekKullanicilar)
                .yonetimHavuzuPasifYap(pasifeAlinacakHavuzAdi);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Huser TUMER", "Paraflama")
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, false);


    }

    @Test(enabled = true, description = "TS0002 : Yeni yönetim havuzu kayıt")
    public void TS0002() {

        String yeniBirimAdi = birimYonetimiPage
                .openPage()
                .birimOlustur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞ");

        String yeniKullanici = kullaniciYonetimiPage
                .openPage()
                .kullaniciOlustur("YAZILIM", "Ağ (Network) Uzmanı");

        String eklenecekYonetimHavuzuAdi = "havuz" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String eklenecekBirim = yeniBirimAdi;
        String eklenecekKullanici = yeniKullanici;

        yonetimHavuzuYonetimiPage
                .openPage()
                .yonetimHavuzuEkle(eklenecekYonetimHavuzuAdi)
                .kullananBirimEkle(eklenecekBirim)
                .kullaniciEkle(eklenecekKullanici)
                .yonetimHavuzuKaydet()
                .ara(eklenecekBirim, eklenecekYonetimHavuzuAdi, null, true);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Huser TUMER", "Paraflama")
                .onayAkisiKullaniciEkle(eklenecekKullanici)
                .onayAkisiKullaniciTipiSec(eklenecekKullanici, "İmzalama")
                .onayAkisiKullan()
                .onayAkisiKullanilanKullaniciKontrolEt("Huser TUMER-Paraflama / " + eklenecekKullanici + "-İmzalama");

        logout();
        login("Ztekin", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Zübeyde TEKİN ", "Paraflama")
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, false);


    }

    @Test(enabled = true, description = "TS0003 : yönetim havuzunu güncelleme")
    public void TS0009() {


        String yeniBirimAdi = birimYonetimiPage
                .openPage()
                .birimOlustur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞ");

        String eklenecekAltBirim = birimYonetimiPage
                .birimOlustur(yeniBirimAdi);

        String yeniKullaniciAdi = "Kullanici" + (new Random().nextInt((900000 - 100000) + 1) + 100000);
        String yeniKullaniciSifre = "123";

        String yeniKullanici = kullaniciYonetimiPage
                .openPage()
                .kullaniciOlustur(yeniKullaniciAdi, yeniKullaniciSifre, "YAZILIM", "Ağ (Network) Uzmanı");

        String eklenecekYonetimHavuzuAdi = "havuz" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        yonetimHavuzuYonetimiPage
                .openPage()
                .yonetimHavuzuEkle(eklenecekYonetimHavuzuAdi)
                .kullananBirimEkle(yeniBirimAdi)
                .kullaniciEkle(yeniKullanici)
                .yonetimHavuzuKaydet()
                .ara(yeniBirimAdi, eklenecekYonetimHavuzuAdi, null, true);


        String yonetimHavuzuAdi = eklenecekYonetimHavuzuAdi;
        String[] kontrolEdilecekBirimler = new String[]{yeniBirimAdi};
        String[] kontrolEdilecekKullanicilar = new String[]{yeniKullanici};
        String silinecekKullanici = yeniKullanici;
        String eklenecekKullanici = yeniKullanici;
        String yeniYonetimHavuzuAdi = "YeniAd" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String guncelKullanici = "Huser TUMER";
        String guncelKullanici2 = yeniKullanici;

        String basariMesaji = "İşlem başarılıdır!";


        yonetimHavuzuYonetimiPage
                .ara(null, yonetimHavuzuAdi, null, true)
                .yonetimHavuzuGuncelle(yonetimHavuzuAdi, false)
                .yonetimHavuzuKontrol(yonetimHavuzuAdi, kontrolEdilecekBirimler, kontrolEdilecekKullanicilar)
                .kullananBirimEkle(eklenecekAltBirim)
                .kullaniciSil(silinecekKullanici)
                .kullaniciEkle(eklenecekKullanici)
                .yonetimHavuzuAdiDoldur(yeniYonetimHavuzuAdi)
                .yonetimHavuzuKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        yonetimHavuzuYonetimiPage
                .ara(null, yeniYonetimHavuzuAdi, null, true);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(guncelKullanici, "Paraflama")
                .onayAkisiTreeKullaniciKontrol(silinecekKullanici, false)
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, true)
                .onayAkisiKullaniciEkle(eklenecekKullanici)
                .onayAkisiKullaniciTipiSec(eklenecekKullanici, "İmzalama")
                .onayAkisiKullan();


        logout();
        clearCookies();
        login(yeniKullaniciAdi, yeniKullaniciSifre);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(guncelKullanici2, "Paraflama")
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, true);

    }
}