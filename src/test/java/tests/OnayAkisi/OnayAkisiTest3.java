package tests.OnayAkisi;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/

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

public class OnayAkisiTest3 extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void beforeTests() {

        login();

        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2113a: Onay Akışı Yönetimi - Güncelleme")
    public void TS2113a() {

        String onayAkisAdi = "Optiim";
        String eskiOnayAkisi = "TS2113 OnayAkışı";
        String yeniOnayAkisi = "TS2113 OnayAkışı" + getSysDate();

        String kullaniciParafci = "Optiim TEST"; //PARAFLAMA
        String kullaniciKontrolcu = "Mehmet BOZDEMİR"; //KONTROL
        String kullaniciKoordineci = "Sezai ÇELİK"; //KOORDİNE
        String kullaniciImzaci = "Zübeyde TEKİN"; //KOORDİNE

        String basariMesaji = "İşlem başarılıdır!";

        //NOTE: Note:  Kullanılan data güncelleneceği için bir sonraki adımda test patlar. O yüzden data eski haline geti getiriliyor.
        //Test steplerinde yok ama data için eklendi. Kullanıcı eski haline güncellemek için.
        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .filtredeAdDoldur(eskiOnayAkisi)
                .ara()
                .guncelle()
                .onayAkisiIslemleriAdDoldur(yeniOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(yeniOnayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .filtredeAdDoldur(yeniOnayAkisi)
                .ara()
                .guncelle()
                .onayAkisiIslemleriAdDoldur(yeniOnayAkisi)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(eskiOnayAkisi)
                .onayAkisDoldur(yeniOnayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2113b: Onay Akıışı Yönetimi - Güncelleme")
    public void TS2113b() {

        String onayAkisAdi = "Optiim";
        String onayAkisi = "TS2113b OnayAkışı";

        String kullaniciParafci = "Optiim TEST"; //PARAFLAMA
        String kullaniciKontrolcu = "Mehmet BOZDEMİR"; //KONTROL
        String kullaniciKoordineci = "Sezai ÇELİK"; //KOORDİNE
        String kullaniciImzaci = "Zübeyde TEKİN"; //KOORDİNE

        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .guncelle()
                .onayAkisiIslemleriKullaniciSil(kullaniciImzaci)
                .onayAkisiIslemlerKullaniciDoldur(kullaniciImzaci)
                .kullaniciyaKullaniciTipiSec(kullaniciImzaci, "IMZALAMA")
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Kontrol")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2113c: Onay Akışı Yönetimi - Güncelleme")
    public void TS2113c() {

        String onayAkisAdi = "Optiim";

        String onayAkisi = "TS2113c OnayAkışı";

        String kullaniciParafci = "Optiim TEST"; //PARAFLAMA
        String kullaniciKontrolcu = "Mehmet BOZDEMİR"; //KONTROL
        String kullaniciKoordineci = "Sezai ÇELİK"; //KOORDİNE
        String kullaniciImzaci = "Zübeyde TEKİN"; //IMZACI
        String kullaniciImzaci2 = "Optiim TEST2"; //IMZACI


        String basariMesaji = "İşlem başarılıdır!";

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .filtredeAdDoldur(onayAkisi)
                .ara()
                .kayitGoruntulenmeKontrolu(onayAkisi)
                .guncelle()
                .kontrolcuYoksaEkle(kullaniciKontrolcu)
                .kontrolcuSil()
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(onayAkisi)
                .onayAkisiDetailKontrol("Paraflama")
                .onayAkisiDetailKontrol("Koordine")
                .onayAkisiDetailKontrol("İmzalama")
                .onayAkisiDetailKontrol("İmzalama");
    }
}
