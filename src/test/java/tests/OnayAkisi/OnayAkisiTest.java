package tests.OnayAkisi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KararYazisiOlusturPage;
import pages.ustMenuPages.OlurYazisiOlusturPage;
import pages.ustMenuPages.OnayAkisYonetimiPage;

import java.lang.reflect.Method;

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
    public void beforeTests(Method method) {

        //log.info(method.getName() + "Nolu test senaryosu başladı.");

        login();
        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1900: Evrak oluşturma ekranından kayıtlı onay akışı güncelleme")
    public void TS1900() {

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
    @Test(enabled = true, description = "TS1897a: Varsayılan onay akışının pasif edilmesi")
    public void TS1897a() {

        String kullanici = "TS1897 OnayAkisi";
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
    @Test(enabled = true, description = "TS1897b: Onay akışı varsayılan yapma")
    public void TS1897b() {

        String kullanici = "TS1897 OnayAkisi";
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
                .evrakSec2()
                .cevapYaz()
                .onayAkisiTitleKontrol(kullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1901a: Son imza seviyesi kısıtlı ise hiyerarşik onay akışı kullanma (yazışma kuralları yönetimi)")
    public void TS1901a() {

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
                .kullaniciyaKullaniciTipiSec(kullanici2, "IMZALAMA")
                .onayAkisiKullan()
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler);

        evrakOlusturPage
                .kaydetOnayaSun()
                .kullaniciIslemVeSiraKontrolu(vekaletAlan, "Paraflama", kullanici2, "İmzalama");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1901b: Hiyararşik onay akışı kullanma- Vekaletli")
    public void TS1901b() {

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
                .kullaniciyaKullaniciTipiSec(kullanici2, "IMZALAMA")
                .onayAkisiKullan()
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler);

        evrakOlusturPage
                .kaydetOnayaSun()
                .kullaniciIslemVeSiraKontrolu(vekaletAlan, "Paraflama", kullanici2, "İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1896: Onay akışı güncelleme")
    public void TS1896() throws InterruptedException {

        String onayAkisi = "Sezaiii Çelikkk";
        String kullanici = "Zübeyde TEKİN";
        String eklenenKullanici1 = "Optiim TEST3";
        String eklenenKullanici2 = "MEHMET EMİN YÜCEANT";
        String ayniBirimliKullanici = "OPTİİM TEST4";
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(onayAkisi)
                .guncelle()

                //Test için datalar silinir, teste hazır hale getirilir.
                .kullaniciVarsaSil(eklenenKullanici1)
                .kullaniciVarsaSil(eklenenKullanici2)
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

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1, "KONTROL")
                .onayAkisiKullaniciKontrol(eklenenKullanici2, "IMZALAMA")
                .onayAkisiKullaniciKoordineKontrol(ayniBirimliKullanici, "Koordine");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1, "KONTROL")
                .onayAkisiKullaniciKontrol(eklenenKullanici2, "IMZALAMA")
                .onayAkisiKullaniciKoordineKontrol(ayniBirimliKullanici, "Koordine");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(eklenenKullanici1)
                .onayAkisiKullaniciKontrol(eklenenKullanici2)
                .onayAkisiKullaniciKontrol(ayniBirimliKullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1895a: Vekaleti olan kullanıcıyı onay akışına ekleme")
    public void TS1895a() {

        //Sistemde vekaleti olan bir kullanıcı olmalı
        //Optiim TEST7, Optiim TEST6 ya
        //TODO: Vekalet tarihi db den sql query ile çekilmelidir.
        String onayAkisi = "Sezai Çelik" + getSysDate();
        String deaultKullanici = "Optiim TEST";
        String vekaletVeren = "Optiim TEST6";
        String vekaletAlan = "Optiim TEST7";
        String vekaletTarihi = "Vekalet: 13.12.2017/04.12.2018";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiIslemlerIstenilenDetaildeKullaniciDoldur(vekaletAlan)
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
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(deaultKullanici)
                .onayAkisiKullaniciKontrol(vekaletAlan);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1895b: Vekaleti olan kullanıcının vekilini onay akışına ekleme")
    public void TS1895b() {

        //Sistemde vekaleti olan bir kullanıcı olmalı
        //Optiim TEST7, Optiim TEST6 ya
        //TODO: Vekalet tarihi db den sql query ile çekilmelidir.
        String onayAkisi = "Sezai Çelik" + getSysDate();
        String deaultKullanici = "Optiim TEST";
        String vekaletVeren = "Optiim TEST6";
        String vekaletAlan = "Optiim TEST7";
        String vekaletTarihi = "Vekalet: 13.12.2017/04.12.2018";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiIslemlerKullaniciDoldur(vekaletVeren)
                .kullaniciyaKullaniciTipiSec(vekaletAlan, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletVeren, "IMZALAMA")
                .onayAkisiKullaniciKontrol(vekaletTarihi, "IMZALAMA")
                //.vekaletSec(true) //seçili geliyor
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
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(deaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(deaultKullanici)
                .onayAkisiKullaniciKontrol(vekaletAlan);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1892a: Onay akışı güncellemede alan kontrolleri")
    public void TS1892a() {

        String onayAkisi = "OptiimTest" + getSysDate();
        String baskaKullanicininKaydettigiOnayAkisi = "SezaiÇelik" + getSysDate();
        String defaultGelenKullanici = "Optiim TEST";
        String defaultGelenKullanici2 = "Sezai ÇELİK";
        String birimDisiKullanici = "MEHMET BAYER";
        String olanBirOnayAkisi = "Sezai Çelik2";
        String dikkatMesaji = "Kullanıcı boş değer olamaz.";
        String dikkatMesaji2 = "Onay akışındaki kullanıcıların yapacağı işlemi seçiniz.";
        String dikkatMesaji3 = "Eklemek istediğiniz onay akışında imzacı bulunmuyor. Lütfen onay akışında en az bir imzacı seçiniz.";
        String dikkatMesaji4 = "Zorunlu alanları doldurunuz";
        String dikkatMesaji5 = "Aynı isimde onay akışı bulunmaktadır. Aynı isimli birden fazla onay akışı kaydedemezsiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        //Tüm dataları bozmamak için, kendi onay akışı yaratıp işlemleri onun üzerinden yapıyor.
        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()

                .onayAkisiIslemleriKullaniciSil(defaultGelenKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        onayAkisYonetimiPage
                .kullanicilarAlanindaGoruntulenmemeKontrolu(birimDisiKullanici)
                .onayAkisiIslemlerKullaniciAlaniniSil()
                .birimTikla()
                .onayAkisiIslemlerKullaniciDoldur(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji2);

        onayAkisYonetimiPage
                .onayAkisiIslemlerKullaniciDoldur(defaultGelenKullanici)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "PARAFLAMA")
                .onayAkisiIslemleriKullaniciSil(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "KONTROL")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "IMZALAMA")
                .onayAkisiIslemleriAdSil()
                .onayAkisiIslemleriKaydet()
                .islemMesaji().uyariOlmali(dikkatMesaji4);

        onayAkisYonetimiPage
                .onayAkisiIslemleriAdDoldur(olanBirOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji5);

        //TODO: Farklı kullanıcı ile girilip ekrandan yaratmak yerine sql query ile db den data çekilmelidir.
        //Başka kullanıcı ile girilip onay akışı yaratılır.
        login("sezaicelik", "123");

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici2, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login("optiim", "123");

        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1892b: Onay akışı kaydetmede alan kontrolleri")
    public void TS1892b() {

        String onayAkisi = "OptiimTest" + getSysDate();
        String baskaKullanicininKaydettigiOnayAkisi = "SezaiÇelik" + getSysDate();
        String defaultGelenKullanici = "Optiim TEST";
        String defaultGelenKullanici2 = "Sezai ÇELİK";
        String kullanici1 = "Zübeyde TEKİN";
        String birimDisiKullanici = "MEHMET BAYER";
        String olanBirOnayAkisi = "Sezai Çelik2";
        String dikkatMesaji3 = "Eklemek istediğiniz onay akışında imzacı bulunmuyor. Lütfen onay akışında en az bir imzacı seçiniz.";
        String dikkatMesaji4 = "Zorunlu alanları doldurunuz";
        String dikkatMesaji5 = "Aynı isimde onay akışı bulunmaktadır. Aynı isimli birden fazla onay akışı kaydedemezsiniz.";
        String dikkatMesaji6 = "Son kullanıcı imzacı olmalıdır!";
        String basariMesaji = "İşlem başarılıdır!";

        //Tüm dataları bozmamak için, kendi onay akışı yaratıp işlemleri onun üzerinden yapıyor.
        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()

                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "PARAFLAMA")
                .kullanicilarAlanindaGoruntulenmemeKontrolu(birimDisiKullanici)
                .onayAkisiIslemlerKullaniciAlaniniSil()
                .birimTikla()
                .onayAkisiIslemlerKullaniciDoldur(birimDisiKullanici)
                .onayAkisiIslemleriKullaniciSil(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "KONTROL")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .onayAkisiIslemlerKullaniciDoldur(kullanici1)
                .kullaniciyaKullaniciTipiSec(kullanici1, "IMZALAMA")
                .onayAkisiKullaniciEnAlttaGetirme(defaultGelenKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji6);

        onayAkisYonetimiPage
                .onayAkisiIslemleriAdSil() //data yaratığımızda isim dolu oluyor o yüzden silinmeli.
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().uyariOlmali(dikkatMesaji4);

        onayAkisYonetimiPage
                .onayAkisiIslemleriAdDoldur(olanBirOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji5);

        //TODO: Farklı kullanıcı ile girilip ekrandan yaratmak yerine sql query ile db den data çekilmelidir.
        //Başka kullanıcı ile girilip onay akışı yaratılır.
        login("sezaicelik", "123");

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici2, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login("optiim", "123");

        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2110: Onay Akışı Yönetimi - Yeni Onay Akışı Oluşturma ve evrak üzerinde kontrolü")
    public void TS2110() {

        String onayAkisi = "ÇelikSezai" + getSysDate();
        String defaultGelenKullanici = "Optiim TEST";
        String kullanici2 = "Sezai ÇELİK";
        String kullanici3 = "MEHMET EMİN YÜCEANT";
        String kullanici4 = "Zübeyde TEKİN";
        String kullanici5 = "MEHMET BAYER"; //birim dışı kullanıcı
        String dikkatMesaji1 = "Onay akışındaki kullanıcıların yapacağı işlemi seçiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .onayAkisiIslemlerKullaniciDoldur(kullanici2)
                .kullaniciyaKullaniciTipiSec(kullanici2, "KONTROL")
                .onayAkisiIslemlerKullaniciDoldur(kullanici3)
                .koordineliSec(true)
                .onayAkisiIslemlerKullaniciDoldur(kullanici4)
                .onayAkisiKullaniciKontrol(kullanici4, "KOORDINE")
                .koordineliSec(true)
                .birimTikla()
                .onayAkisiIslemlerKullaniciDoldur(kullanici5)
                .kullaniciyaKullaniciTipiSec(kullanici5, "IMZALAMA")
                .onayAkisiIslemleriAdDoldur(onayAkisi) //Birim tıklandıktan sonra burası siliniyor. O yüzden tekrar eklendi.
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji1);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(kullanici3, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "KONTROL")
                .onayAkisiKullaniciKoordineKontrol(kullanici4, "Koordine")
                .onayAkisiKullaniciKontrol(kullanici3, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "KONTROL")
                .onayAkisiKullaniciKoordineKontrol(kullanici4, "Koordine")
                .onayAkisiKullaniciKontrol(kullanici3, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultGelenKullanici)
                .onayAkisiKullaniciKontrol(kullanici2)
                .onayAkisiKullaniciKontrol(kullanici4)
                .onayAkisiKullaniciKontrol(kullanici3);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2111: Onay Akışı Yönetimi - Kayıtlı Onay Akışını kullanım sırasında anlık değiştirme")
    public void TS2111() {

        String onayAkisi = "TS2111 Onay Akisi"; //parafçı, kontrolcu, koordinecisi ve imzacısı olmalı.
        String kullanici1 = "Optiim TEST"; //parafçı
        String kullanici2 = "Zübeyde TEKİN"; //koordineci
        String kullanici3 = "Sezai ÇELİK"; //kontrolcu
        String kullanici4 = "MEHMET EMİN YÜCEANT"; //imzacı
        String eklenecekYeniKullanici = "Mehmet BOZDEMİR";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("İmzalama")
                .onayAkisiGuncelle()
                .onayAkisiKullaniciSil(kullanici3)
                .kullan()
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("İmzalama")
                .onayAkisiGuncelle()
                .onayAkisiKullaniciDoldur(eklenecekYeniKullanici)
                .kullaniciyaKullaniciTipiSec(eklenecekYeniKullanici, "IMZALAMA")
                .kullan()
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("İmzalama")
                .onayAkisiDetailKontrol("İmzalama");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(kullanici1)
                .onayAkisiKullaniciKontrol(kullanici2)
                .onayAkisiKullaniciKontrol(kullanici3)
                .onayAkisiKullaniciKontrol(kullanici4);
    }
}


