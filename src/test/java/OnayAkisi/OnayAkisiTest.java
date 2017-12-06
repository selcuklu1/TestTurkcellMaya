package OnayAkisi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
public class OnayAkisiTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2112: Onay Akışı Yönetimi - Aktif/Pasif Yapma ve Varsayılan Yapma")
    public void TC2112() {

        String onayAkisAdi = "Optiim";
        String kullanici = "Sezai Çelik";
        String basariMesaji = "İşlem başarılıdır!";

        //Data kontrolu için yazıldı. Pasif ise aktif yapılır.
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
                .onayAkisDoldur(kullanici)
                .onayAkisiGuncelle();

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
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

        onayAkisYonetimiPage
                .openPage()
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
                .onayAkisiKullaniciComboKontrol();

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiDoldur(kullanici)
                //TODO: Burda imzaci combosu gelmiyor.
                //   .onayAkisiKullaniciComboKontrol();
                .ekraniKapat()
                .islemPenceresiKaydetPopup("Hayır");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2113: Onay Akıışı Yönetimi - Güncelleme")
        public void TC2113() {

        String onayAkisAdi = "Optiim";
        String eskiKullanici = "Bulut Toprak";
        String yeniKullanici = "Bulut Toprak" +" "+ createRandomNumber(5);
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisDoldur(eskiKullanici)
                .onayAkisiGuncelle();

        onayAkisYonetimiPage
                .openPage()
                .filtreAc()
                .birimKontrol(onayAkisAdi)
                .durumKontrol("Sadece Aktifler")
                .ara()
                .kayitGoruntulenmeKontrolu(eskiKullanici)
                .guncelle()
                .onayAkisiIslemleriAdDoldur(yeniKullanici)
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiAlanindaGoruntulenmemeKontrolu(eskiKullanici)
                .onayAkisDoldur(yeniKullanici)
                .onayAkisiKullaniciComboKontrol();
    }
}
