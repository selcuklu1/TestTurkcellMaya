package tests.OnayAkisi;

import common.BaseTest;
import data.TestData;
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

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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
                .kullaniciIslemVe1SiraKontrolu(kullanici1, "Paraflama")
                .kullaniciIslemVe2SiraKontrolu(kullanici2, "İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1897a: Varsayılan onay akışının pasif edilmesi")
    public void TS1897a() {

        String kullanici = "TS1897 OnayAkisi";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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
    @Test(enabled = false, description = "TS1901a: Son imza seviyesi kısıtlı ise hiyerarşik onay akışı kullanma (yazışma kuralları yönetimi)")
    public void TS1901a() {

        //Optiim TEST7, Optiim TEST6 ya
        String onayAkisi = "Vekaletli Kullanici";
        String vekaletAlan = "Optiim TEST7";
        String kullanici2 = "Zübeyde TEKİN";
        String vekaletVeren = "Optiim TEST6";
        String kaldirilacakKlasorler = "ESK05";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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
                .kullaniciIslemVe1SiraKontrolu(vekaletAlan, "Paraflama")
                .kullaniciIslemVe2SiraKontrolu(kullanici2, "İmzalama");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1901b: Otomatik Onay akışı kullanma – Vekaletli") //case ismi değişti.
    public void TS1901b() {

        //Optiim TEST7, Optiim TEST6 ya

        String asilKullanici = "Optiim TEST6";
        String vekilKullanici = "Optiim TEST7";
        String parafciKullanici2 = "Zübeyde TEKİN";
        String parafciKullanici1 = "Mehmet BOZDEMİR";
        String imzaciKullanici = "Hamza KANDUR";
        String kaldirilacakKlasorler = "ESK05";

        login(TestData.usernameOPTIIMTEST6, TestData.passwordPTIIMTEST6); //optiimtest6 123

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                //.onayAkisiDoldur(onayAkisi)
                //.onayAkisiGuncelle()
                //.onayAkisiKullaniciKontrol(vekaletAlan, "PARAFLAMA")
                //.onayAkisiKullaniciKontrol(kullaniciImzaci, "IMZALAMA")
                //.onayAkisiVekaletKontrol(vekaletVeren)
                //.kullaniciyaKullaniciTipiSec(kullaniciImzaci, "IMZALAMA")
                //.onayAkisiKullan()

                .otomatikOnayAkisiSec()

                .otomatikOnayAkisiKullaniciKontrol(asilKullanici, "PARAFLAMA", "Asil Kullanıcı")
                .otomatikOnayAkisiKullaniciKontrol(vekilKullanici, "PARAFLAMA", "Vekil Kullanıcı")
                .otomatikOnayAkisiKullaniciKontrol(parafciKullanici1, "PARAFLAMA", "Parafçı Kullanıcı1")
                .otomatikOnayAkisiKullaniciKontrol(parafciKullanici2, "PARAFLAMA", "Parafçı Kullanıcı2")
                .otomatikOnayAkisiKullaniciKontrol(imzaciKullanici, "IMZALAMA", "İmzaci Kullanıcı")

                .otomatikOnayAkisiKullaniciyaGoreCheckBoxKontrolu(asilKullanici, "Asil Kullanıcı")
                .otomatikOnayAkisiKullaniciyaGoreCheckBoxKontrolu(parafciKullanici1, "Parafçı Kullanıcı1")
                .otomatikOnayAkisiKullaniciyaGoreCheckBoxKontrolu(parafciKullanici2, "Parafçı Kullanıcı2")
                .otomatikOnayAkisiKullaniciyaGoreCheckBoxKontrolu(imzaciKullanici, "İmzaci Kullanıcı")

                .otomatikOnayAkisiKullaniciSec(parafciKullanici1, true, "Parafçı Kullanıcı1")
                .otomatikOnayAkisiKullaniciSec(parafciKullanici2, true, "Parafçı Kullanıcı2")
                .otomatikOnayAkisiKullaniciSec(imzaciKullanici, true, "İmzaci Kullanıcı")
                .otomatikOnayAkisiVekilKullaniciKaldir(vekilKullanici, true, "Vekil Kullanıcı")

                .otomatikOnayAkisiKullan()
                .onayAkisiGuncelle()

                .onayAkisiKullaniciKontrol(parafciKullanici1, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(parafciKullanici2, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(imzaciKullanici, "IMZALAMA")

                //.kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .konuKoduDoldur("040");

        evrakOlusturPage
                .kaydetOnayaSun()

                .kullaniciIslemVe1SiraKontrolu(parafciKullanici1, "Paraflama")
                .kullaniciIslemVe2SiraKontrolu(parafciKullanici2, "Paraflama")
                .kullaniciIslemVe3SiraKontrolu(imzaciKullanici, "İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1896: Onay akışı güncelleme")
    public void TS1896() throws InterruptedException {

        String onayAkisi = "TS1896 OnayAkışı";
        String kullanici = "Zübeyde TEKİN";
        String eklenenKullanici1 = "Optiim TEST3";
        String eklenenKullanici2 = "Mehmet BOZDEMİR";
        String ayniBirimliKullanici = "Optiim TEST4";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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
        String defaultKullanici = "Optiim TEST";
        String asilKullanici = "Optiim TEST6"; //vekaletVeren
        String vekilKullanici = "Optiim TEST7"; //vekalet alan
        String vekaletTarihi = "Vekalet: 13.12.2017/04.12.2018";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiAlanKontrolleri()
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiIslemlerKullaniciDoldur(asilKullanici)
                .vekilCheckboxSec(true)
                .kullaniciyaKullaniciTipiSec(asilKullanici, "IMZALAMA")
                .onayAkisiVekilKullaniciKontrol(vekilKullanici, "IMZALAMA")
                .onayAkisiAsilKullaniciKontrol(asilKullanici, "IMZALAMA")
                .onayAkisiVekaletTarihiKontrol(vekaletTarihi)
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
                .onayAkisiDoldurWithoutKontrol(onayAkisi)
                .vekaletKaydet()
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(asilKullanici, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldurWithoutKontrol(onayAkisi)
                .vekaletKaydet()
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(asilKullanici, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldurWithoutKontrol(onayAkisi)
                .vekaletKaydet()
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultKullanici)
                .onayAkisiKullaniciKontrol(asilKullanici);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1895b: Vekaleti olan kullanıcının vekilini onay akışına ekleme")
    public void TS1895b() {

        //Sistemde vekaleti olan bir kullanıcı olmalı
        //Optiim TEST7, Optiim TEST6 ya
        //TODO: Vekalet tarihi db den sql query ile çekilmelidir.
        String onayAkisi = "Sezai Çelik" + getSysDate();
        String defaultKullanici = "Optiim TEST";
        String vekaletVeren = "Optiim TEST6";
        String vekaletAlan = "Optiim TEST7";
        String vekaletTarihi = "Vekalet: 13.12.2017/04.12.2018";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiAlanKontrolleri()
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
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
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(vekaletAlan, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultKullanici)
                .onayAkisiKullaniciKontrol(vekaletAlan);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1892a: Onay akışı güncellemede alan kontrolleri")
    public void TS1892a() {

        //String onayAkisi = "OptiimTest" + getSysDate();
        String onayAkisi = "TS1892a OnayAkışı";

        //String baskaKullanicininKaydettigiOnayAkisi = "SezaiÇelik" + getSysDate();
        String baskaKullanicininKaydettigiOnayAkisi = "TS1892a FarklıKulOnayAkışı";

        String defaultGelenKullanici = "Optiim TEST";
        String defaultGelenKullanici2 = "Sezai ÇELİK";
        String birimDisiKullanici = "MEHMET BAYER";
        String olanBirOnayAkisi = "Sezai Çelik2";
        String imzaciKullanici = "Zübeyde TEKİN";
        String dikkatMesaji = "Kullanıcı boş değer olamaz.";
        String dikkatMesaji2 = "Onay akışındaki kullanıcıların yapacağı işlemi seçiniz.";
        String dikkatMesaji3 = "Eklemek istediğiniz onay akışında imzacı bulunmuyor. Lütfen onay akışında en az bir imzacı seçiniz.";
        String dikkatMesaji4 = "Zorunlu alanları doldurunuz";
        String dikkatMesaji5 = "Aynı isimde onay akışı bulunmaktadır. Aynı isimli birden fazla onay akışı kaydedemezsiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        //Tüm dataları bozmamak için, kendi onay akışı yaratıp işlemleri onun üzerinden yapıyor.
/*        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                .onayAkisiIslemlerKullaniciDoldur(imzaciKullanici)
                .kullaniciyaKullaniciTipiSec(imzaciKullanici, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);*/

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

//Onay akışı güncellenip onun üzerinden devam ediliyor. Test başında ve sonunda kontrol edilip data resetleniyor.
        onayAkisYonetimiPage
                .openPage()
                .onayAkisiDataResetleme(onayAkisi, baskaKullanicininKaydettigiOnayAkisi, basariMesaji);

        onayAkisYonetimiPage
                //.filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()

                .onayAkisiIslemleriKullaniciSil(imzaciKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullanicilarAlanindaGoruntulenmemeKontrolu(birimDisiKullanici)
                .onayAkisiIslemlerKullaniciAlaniniSil()
                .birimTikla()
                .onayAkisiIslemlerKullaniciDoldur(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji2);

        onayAkisYonetimiPage
                .onayAkisiIslemlerKullaniciDoldur(imzaciKullanici)
                .kullaniciyaKullaniciTipiSec(imzaciKullanici, "PARAFLAMA")
                .onayAkisiIslemleriKullaniciSil(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(imzaciKullanici, "KONTROL")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(imzaciKullanici, "IMZALAMA")
                .onayAkisiIslemleriAdSil()
                .onayAkisiIslemleriKaydet()
                .islemMesaji().uyariOlmali(dikkatMesaji4);

        onayAkisYonetimiPage
                .onayAkisiIslemleriAdDoldur(olanBirOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesajiBekle()
                .islemMesaji().dikkatOlmali(dikkatMesaji5);

/*        //TODO: Farklı kullanıcı ile girilip ekrandan yaratmak yerine sql query ile db den data çekilmelidir.
        //Başka kullanıcı ile girilip onay akışı yaratılır.
        login("sezaicelik", "123");

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici2, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login("optiim", "123");*/

        onayAkisYonetimiPage
                //.openPage()
                //.filtredeAdDoldur(onayAkisi)
                //.ara()
                //.guncelle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .onayAkisiDataResetleme(onayAkisi, baskaKullanicininKaydettigiOnayAkisi, basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1892b: Onay akışı kaydetmede alan kontrolleri")
    public void TS1892b() {

        String onayAkisi = "OptiimTest" + getSysDate();

        //String baskaKullanicininKaydettigiOnayAkisi = "SezaiÇelik" + getSysDate();
        String baskaKullanicininKaydettigiOnayAkisi = "TS1892b FarklıKulOnayAkışı";

        String defaultGelenKullanici = "Optiim TEST";
        String defaultGelenKullanici2 = "Sezai ÇELİK";
        String kullanici1 = "Zübeyde TEKİN";
        String birimDisiKullanici = "MEHMET BAYER";
        String olanBirOnayAkisi = "Sezai Çelik2";
        String dikkatMesaji3 = "Eklemek istediğiniz onay akışında imzacı bulunmuyor. Lütfen onay akışında en az bir imzacı seçiniz.";
        String dikkatMesaji4 = "Zorunlu alanları doldurunuz";
        String dikkatMesaji5 = "Aynı isimde onay akışı bulunmaktadır. Aynı isimli birden fazla onay akışı kaydedemezsiniz.";
        String dikkatMesaji6 = "Son kullanıcı imzacı olmalıdır!";
        String dikkatMesaji7 = "Onay akışındaki kullanıcıların yapacağı işlemi seçiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

        //Onay akışı güncellenip onun üzerinden devam ediliyor. Test başında ve sonunda kontrol edilip data resetleniyor.
        onayAkisYonetimiPage
                .openPage()
                .onayAkisiDataResetleme(onayAkisi, baskaKullanicininKaydettigiOnayAkisi, basariMesaji);

        onayAkisYonetimiPage
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(onayAkisi)
/*                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);*/

/*        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()*/

                //.kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "PARAFLAMA")
                .kullanicilarAlanindaGoruntulenmemeKontrolu(birimDisiKullanici)
                .onayAkisiIslemlerKullaniciAlaniniSil()
                .birimTikla()
                .onayAkisiIslemlerKullaniciDoldur(birimDisiKullanici)
                .onayAkisiIslemleriAdDoldur(onayAkisi)
                //.onayAkisiIslemleriKullaniciSil(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji7);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "PARAFLAMA")
                .kullaniciVarsaSil(birimDisiKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici, "KONTROL")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().dikkatOlmali(dikkatMesaji3);

        onayAkisYonetimiPage
                .birimTikla()
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

/*        //TODO: Farklı kullanıcı ile girilip ekrandan yaratmak yerine sql query ile db den data çekilmelidir.
        //Başka kullanıcı ile girilip onay akışı yaratılır.
        login("sezaicelik", "123");

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .kullaniciyaKullaniciTipiSec(defaultGelenKullanici2, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login("optiim", "123");*/

        onayAkisYonetimiPage
                // .openPage()
                //.filtredeAdDoldur(onayAkisi)
                //.ara()
                //.guncelle()
                .onayAkisiIslemleriAdDoldur(baskaKullanicininKaydettigiOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .onayAkisiDataResetleme(onayAkisi, baskaKullanicininKaydettigiOnayAkisi, basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2110: Onay Akışı Yönetimi - Yeni Onay Akışı Oluşturma ve evrak üzerinde kontrolü")
    public void TS2110() {

        String onayAkisi = "ÇelikSezai" + getSysDate();
        String defaultGelenKullanici = "Optiim TEST";
        String kullanici2 = "Sezai ÇELİK";
        String kullanici3 = "Mehmet BOZDEMİR";
        String kullanici4 = "Zübeyde TEKİN";
        String kullanici5 = "MEHMET BAYER"; //birim dışı kullanıcı
        String dikkatMesaji1 = "Onay akışındaki kullanıcıların yapacağı işlemi seçiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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
                .koordineliSecimiKaldir(true)
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
                .onayAkisiKullaniciKontrol(kullanici3, "IMZALAMA")
                .onayAkisiKullaniciKontrol(kullanici5, "IMZALAMA");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultGelenKullanici, "PARAFLAMA")
                .onayAkisiKullaniciKontrol(kullanici2, "KONTROL")
                .onayAkisiKullaniciKoordineKontrol(kullanici4, "Koordine")
                .onayAkisiKullaniciKontrol(kullanici3, "IMZALAMA")
                .onayAkisiKullaniciKontrol(kullanici5, "IMZALAMA");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiGuncelle()
                .onayAkisiKullaniciKontrol(defaultGelenKullanici)
                .onayAkisiKullaniciKontrol(kullanici2)
                .onayAkisiKullaniciKontrol(kullanici4)
                .onayAkisiKullaniciKontrol(kullanici3)
                .onayAkisiKullaniciKontrol(kullanici5);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2111: Onay Akışı Yönetimi - Kayıtlı Onay Akışını kullanım sırasında anlık değiştirme")
    public void TS2111() {

        String onayAkisi = "TS2111 OnayAkışı"; //parafçı, kontrolcu, koordinecisi ve imzacısı olmalı.
        String kullanici1 = "Optiim TEST"; //parafçı
        String kullanici2 = "Zübeyde TEKİN"; //koordineci
        String kullanici3 = "Sezai ÇELİK"; //kontrolcu
        String kullanici4 = "Mehmet BOZDEMİR"; //imzacı
        String eklenecekYeniKullanici = "Çelik SEZAİ";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

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


