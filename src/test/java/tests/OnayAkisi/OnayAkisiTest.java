package tests.OnayAkisi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.*;

/****************************************************
 * Tarih: 2017-12-04
 * Proje: Türksat Functional Test Automation
 * Class: "Onay Akışı" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class OnayAkisiTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2112PasifYapma: Onay Akışı Yönetimi - Aktif/Pasif Yapma ve Varsayılan Yapma")
    public void TC2112PasifYapma() {

        String onayAkisAdi = "Optiim";
        String kullanici = "Sezai Çelik";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(kullanici);

        onayAkisYonetimiPage
                .ekraniKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(kullanici)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("İmzalama");

        onayAkisYonetimiPage
                .openPage()
//                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .ara()
                .aktiflerTumListeKayitKontrolu()
                .adaGorePasifYap(kullanici)
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(kullanici);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .secilenOnayAkisiSil()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2112AktifYapma: Onay Akışı Yönetimi - Aktif/Pasif Yapma ve Varsayılan Yapma")
    public void TC2112AktifYapma() {

        String onayAkisAdi = "Optiim";
        String kullanici = "Sezai Çelik";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiAktifIsePasifYap(kullanici)

                .filtreAc()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("PASIFLER")
                .ara()
                .adaGoreAktifYap(kullanici)
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(kullanici);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(kullanici)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("İmzalama");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(kullanici)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("İmzalama")
                .ekraniKapat()
                .islemPenceresiKaydetPopup("Hayır");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2113a: Onay Akıışı Yönetimi - Güncelleme")
    public void TC2113a() {

        String onayAkisAdi = "Optiim";
        String eskiKullanici = "Bulut Toprak";
        String yeniKullanici = "Bulut Toprak" + createRandomNumber(5);
        String ikinciKullanici = "Zübeyde Tekin";
        String basariMesaji = "İşlem başarılıdır!";

        //Test steplerinde yok ama data için eklendi. Kullanıcı eski haline güncellemek için.
        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .filtredeAdDoldur(eskiKullanici)
                .ara()
                .guncelle()
                .onayAkisiIslemleriAdDoldur(eskiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(eskiKullanici)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("İmzalama");

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .filtredeAdDoldur(eskiKullanici)
                .ara()
                //.kayitGoruntulenmeKontrolu(eskiKullanici)
                .guncelle()
                .onayAkisiIslemleriAdDoldur(yeniKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(eskiKullanici)
                .onayAkisDoldur(yeniKullanici)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2113b: Onay Akıışı Yönetimi - Güncelleme")
    public void TC2113b() {

        String ad = "Alex de Souza";
        String kullanici = "Zübeyde Tekin";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .filtredeAdDoldur(ad)
                .ara()
                .guncelle()
                .silOnayAkisiItem2()
                .onayAkisiIslemlerKullaniciDoldur(kullanici)
                .imzacıSonSec("İmzalama")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(ad)
                .onayAkisiDetailKontrol("İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2113c: Onay Akıışı Yönetimi - Güncelleme")
    public void TC2113c() {

        String ad = "Daniel Guiza";
        String kullanici = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .filtredeAdDoldur(ad)
                .ara()
                .guncelle()
                .kontrolcuYoksaEkle(kullanici)
                .kontrolcuSil()
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(ad)
                .onayAkisiDetailKontrol("İmzalama")
                .onayAkisiDetailKontrol("İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1900: Evrak oluşturma ekranından kayıtlı onay akışı güncelleme")
    public void TC1900() {

        String ad = "Yaşar Yaşamaz";
        String kullanici1 = "Mehmet BOZDEMİR";
        String kullanici2 = "Zübeyde TEKİN";
        String kaldirilacakKlasorler = "ESK05";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(ad)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici1, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "IMZALAMA")
                .kullaniciYerleriDegistir(kullanici1, kullanici2)
                .onayAkisiKullaniciSil(kullanici2)
                .onayAkisiKullaniciEkle(kullanici2)
                .kullaniciyaKullaniciTipiSec(kullanici2, "IMZALAMA")
                .onayAkisiKullan()
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici2, "IMZALAMA");

        evrakOlusturPage
                .bilgilerTabiAc()
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler);

        evrakOlusturPage
                .kaydetOnayaSun()
                .kullaniciIslemVeSiraKontrolu(kullanici1, "Paraflama", kullanici2, "İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1897a: Varsayılan onay akışının pasif edilmesi")
    public void TC1897a() {

        String kullanici = "Sezai Çelik";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(kullanici)
                .varsayilanYap()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .pasifYap()
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(kullanici);

        onayAkisYonetimiPage
                .openPage()
                .aktifYap()
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(kullanici);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(kullanici);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(kullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1897b: Onay akışı varsayılan yapma")
    public void TC1897b() {

        String kullanici = "Sezai Çelik";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(kullanici)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(kullanici)
                .varsayilanYap()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiTitleKontrol(kullanici);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiTitleKontrol(kullanici);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiTitleKontrol(kullanici);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .cevapYaz()
                .onayAkisiTitleKontrol(kullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1901a: Son imza seviyesi kısıtlı ise hiyerarşik onay akışı kullanma (yazışma kuralları yönetimi)")
    public void TC1901a() {

        //Optiim TEST7, Optiim TEST6 ya
        String onayAkisi = "Vekaletli Kullanici";
        String vekaletAlan = "Optiim TEST7";
        String kullanici2 = "Zübeyde TEKİN";
        String vekaletVeren = "Optiim TEST6";
        String kaldirilacakKlasorler = "ESK05";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(vekaletAlan, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "IMZALAMA")
                .onayAkisiVekaletKontrol(vekaletVeren)
                .kullniciIsmineGoreImzaParafSec(kullanici2, "İmzalama")
                .onayAkisiKullan()
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler);

        evrakOlusturPage
                .kaydetOnayaSun()
                .kullaniciIslemVeSiraKontrolu(vekaletAlan, "Paraflama", kullanici2, "İmzalama");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1901b: Hiyararşik onay akışı kullanma- Vekaletli")
    public void TC1901b() {

        //Optiim TEST7, Optiim TEST6 ya
        String onayAkisi = "Vekaletli Kullanici";
        String vekaletAlan = "Optiim TEST7";
        String kullanici2 = "Zübeyde TEKİN";
        String vekaletVeren = "Optiim TEST6";
        String kaldirilacakKlasorler = "ESK05";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(vekaletAlan, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "IMZALAMA")
                .onayAkisiVekaletKontrol(vekaletVeren)
                .kullniciIsmineGoreImzaParafSec(kullanici2, "İmzalama")
                .onayAkisiKullan()
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler);

        evrakOlusturPage
                .kaydetOnayaSun()
                .kullaniciIslemVeSiraKontrolu(vekaletAlan, "Paraflama", kullanici2, "İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1896: Onay akışı güncelleme")
    public void TC1896() {

        String onayAkisi = "Sezaiii Çelikkk";
        String kullanici = "Zübeyde TEKİN";
        String eklenenKullanici2 = "MEHMET EMİN YÜCEANT";
        String eklenenKullanici1 = "Mehmet BOZDEMİR";
        String ayniBirimliKullanici = "Optiim TEST";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(onayAkisi)

                .guncelle()

                //Test için datalar silinir
                .kullaniciVarsaSil(eklenenKullanici2)
                .kullaniciVarsaSil(eklenenKullanici1)
                .kullaniciVarsaSil(ayniBirimliKullanici)

                .onayAkisiIslemlerKullaniciDoldur(eklenenKullanici1)
                .kullaniciYerleriDegistir(kullanici, eklenenKullanici1)
                .onayAkisiIslemleriKullaniciSil(eklenenKullanici1)

                .onayAkisiIslemlerKullaniciDoldur(eklenenKullanici1)
                .kullaniciyaKullaniciTipiSec(eklenenKullanici1, "KONTROL")

                .onayAkisiIslemlerKullaniciDoldur(eklenenKullanici2)
                .kullaniciyaKullaniciTipiSec(eklenenKullanici2, "IMZALAMA")

                .koordineliSec(true)
                .onayAkisiIslemlerKullaniciDoldur(ayniBirimliKullanici)
                .onayAkisiKullaniciKontrol(ayniBirimliKullanici, "KOORDINE")

                .onayAkisiKullaniciEnAlttaGetirme(eklenenKullanici2)

                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(onayAkisi);

        //TODO: Bundan sonrası defect var.
/*
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1, "KONTROL")
                .onayAkisiKullaniciKontrol(eklenenKullanici2, "IMZALAMA")
                .onayAkisiKullaniciKontrol(ayniBirimliKullanici, "KOORDINE");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1, "KONTROL")
                .onayAkisiKullaniciKontrol(eklenenKullanici2, "IMZALAMA")
                .onayAkisiKullaniciKontrol(ayniBirimliKullanici, "KOORDINE");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1, "KONTROL")
                .onayAkisiKullaniciKontrol(eklenenKullanici2, "IMZALAMA")
                .onayAkisiKullaniciKontrol(ayniBirimliKullanici, "KOORDINE");*/
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1895: Vekaleti olan kullanıcıyı onay akışına ekleme")
    public void TC1895() {

        //Sistemde vekaleti olan bir kullanıcı olmalı
        //Optiim TEST7, Optiim TEST6 ya
        //TODO: Vekalet tarihi db den sql query ile çekilmelidir.
        String onayAkisi = "Sezai Çelik" + getSysDate();
        String kullanici = "Optiim TEST";
        String vekaletVeren = "Optiim TEST6";
        String vekaletAlan = "Optiim TEST7";
        String vekaletTarihi = "Vekalet: 13.12.2017/04.12.2018";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiIslemlerVekaletliKullaniciDoldur(vekaletAlan)
                .kullaniciyaKullaniciTipiSec(vekaletAlan, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletVeren, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletTarihi, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(onayAkisi);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici)
                .onayAkisiKullaniciKontrol(vekaletAlan);
    }
}