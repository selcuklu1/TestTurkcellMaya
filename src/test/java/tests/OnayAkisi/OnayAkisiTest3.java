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

import java.lang.reflect.Method;

public class OnayAkisiTest3 extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void beforeTests(Method method) {

        log.info(method.getName() + "Nolu test senaryosu başladı.");

        login();
        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2113A: Onay Akıışı Yönetimi - Güncelleme")
    public void TS2113A() {

        String onayAkisAdi = "Optiim";
        String eskiKullanici = "Bulut Toprak";
        String yeniKullanici = "Bulut Toprak" + createRandomNumber(5);
        String ikinciKullanici = "Zübeyde Tekin";
        String basariMesaji = "İşlem başarılıdır!";

        //NOTE: Note:  Kullanılan data güncelleneceği için bir sonraki adımda test patlar. O yüzden data eski haline geti getiriliyor.
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
    @Test(enabled = true, description = "TS2113B: Onay Akıışı Yönetimi - Güncelleme")
    public void TS2113B() {

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
    @Test(enabled = true, description = "TS2113C: Onay Akıışı Yönetimi - Güncelleme")
    public void TS2113C() {

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
}
