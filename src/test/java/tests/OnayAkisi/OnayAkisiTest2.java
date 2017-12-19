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

/****************************************************
 * Tarih: 2017-12-04
 * Proje: Türksat Functional Test Automation
 * Class: "Onay Akışı" konulu senaryoları içerir
 * Yazan: Sezai Çelik
 ****************************************************/
public class OnayAkisiTest2 extends BaseTest {

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
        String kullanici = "TC2112 OnayAkisi";
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
                .filtredeAdDoldur(kullanici)
                .ara()
               // .aktiflerTumListeKayitKontrolu()   Bu adımda tüm tabloyu kontrol ettiği için yavaşlatıyor otomasyonu. Çıkartılmalı.
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
        String kullanici = "TC2112 OnayAkisi";
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
}