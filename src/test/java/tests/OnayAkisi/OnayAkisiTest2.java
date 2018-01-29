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
public class OnayAkisiTest2 extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void beforeTests(Method method) {

        login();

        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2112a: Onay Akışı Yönetimi - Aktif/Pasif Yapma ve Varsayılan Yapma")
    public void TS2112a() throws InterruptedException {

        //Pasif yapma
        String onayAkisAdi = "Optiim";
        String onayAkisi = "TS2112 OnayAkisi";
        String kullaniciParafci = "Optiim TEST"; //PARAFLAMA
        String kullaniciKontrolcu = "Mehmet BOZDEMİR"; //KONTROL
        String kullaniciKoordineci = "Sezai ÇELİK"; //KOORDİNE
        String kullaniciImzaci = "Zübeyde TEKİN"; //KOORDİNE
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiPasifIseAktifYap(onayAkisi);

        onayAkisYonetimiPage
                .ekraniKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");

        onayAkisYonetimiPage
                .openPage()
//                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .aktiflerTumListeKayitKontrolu()   //Bu adımda tüm tabloyu kontrol ettiği için yavaşlatıyor otomasyonu. Çıkartılmalı.
                .adaGorePasifYap(onayAkisi)
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(onayAkisi);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .secilenOnayAkisiSil()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(onayAkisi);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(onayAkisi);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(onayAkisi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2112b: Onay Akışı Yönetimi - Aktif/Pasif Yapma ve Varsayılan Yapma")
    public void TS2112b() {

        //Aktif yapma
        String onayAkisAdi = "Optiim";
        String onayAkisi = "TS2112 OnayAkisi";
        String kullaniciParafci = "Optiim TEST"; //PARAFLAMA
        String kullaniciKontrolcu = "Mehmet BOZDEMİR"; //KONTROL
        String kullaniciKoordineci = "Sezai ÇELİK"; //KOORDİNE
        String kullaniciImzaci = "Zübeyde TEKİN"; //KOORDİNE
        String basariMesaji = "İşlem başarılıdır!";

        //tests.Data kontrolu için yazıldı. Pasif ise aktif yapılır.
        onayAkisYonetimiPage
                .openPage()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("TUMU")
                .ara()
                .onayAkisiAktifIsePasifYap(onayAkisi)

                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("PASIFLER")
                .ara()
                .adaGoreAktifYap(onayAkisi)
                .islemOnayi("Evet");

        onayAkisYonetimiPage
                .islemMesaji().basariliOlmali(basariMesaji);

        onayAkisYonetimiPage
                .filtreAc()
                .filtredeAdDoldur(onayAkisi)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitGoruntulenmeKontrolu(onayAkisi);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama")
                .ekraniKapat()
                .islemPenceresiKaydetPopup("Hayır");
    }
}