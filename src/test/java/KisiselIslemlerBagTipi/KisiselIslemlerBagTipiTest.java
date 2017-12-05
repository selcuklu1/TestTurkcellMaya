/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package KisiselIslemlerBagTipi;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.*;

import static data.TestData.password2;
import static data.TestData.username2;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel İşlemler Bağ Tipi" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
@Epic("Kişisel İşlemler Bağ Tipi")
public class KisiselIslemlerBagTipiTest extends BaseTest {

    KullaniciYonetimiPage kullaniciYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    VekaletVerPage vekaletVerPage;
    GelenEvraklarPage gelenEvraklarPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2141: Bağ tipi amir yardımcısı olması kontrolleri")
    public void TC2141() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Y";
        String farkliKullanici = "Optiim";

        login(username2, password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi)
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi);

        vekaletVerPage
                .openPage()
                .vekaletVerenDoldur(ekranAdi)
                .vekaletVerenFarkliDoldur(farkliKullanici)
                .onayVerecekDoldur(ekranAdi);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2142: Bağ tipi vekaleten amir yardımcısı kontrolleri")
    public void TC2142() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Y";
        String farkliKullanici = "Optiim";
        String onayAkisiKullanicilarTuru = "İmzalama";
        String gnMdV = "Gn.Md. V.";
        String genelMudur = "Genel Müdür V.";
        String kullanicilarTuru = "İmzalama";

        login(username2, password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi)
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi)
                .onayAkisiEkle()
                .kullanicilarDoldur(ekranAdi)
                .kullanicilarImzaciSec(kullanicilarTuru)
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .imzacılarGnMdVKontrol(gnMdV);

        onayAkisYonetimiPage
                .openPage()
                .onayAkisiYeni()
                .onayAkisiIslemlerKullanicilarDoldur(ekranAdi)
                .imzacıSonSec(onayAkisiKullanicilarTuru)
                .onayAkisiIslemleriAdDoldur();

        String ad = onayAkisYonetimiPage.adCek();

        onayAkisYonetimiPage
                .onayAkisiIslemleriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiTemizle(ad);

        evrakOlusturPage
                .editorTabAc()
                .imzacılarGnMdVKontrol(genelMudur);

        vekaletVerPage
                .openPage()
                .vekaletVerenDoldur(ekranAdi)
                .vekaletVerenFarkliDoldur(farkliKullanici)
                .onayVerecekDoldur(ekranAdi);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2168: Bağ tipi personel kontrolleri")
    public void TC2168() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "P";
        String farkliKullanici = "Optiim";
        String onayVerecek = "Zübeyde TEKİN";
        login(username2, password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi)
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGelmedigiGorme(ekranAdi, false);

        vekaletVerPage
                .openPage()
                .vekaletVerenAlanınaGoruntulenmemeKontrolu(ekranAdi, false)
                .vekaletVerenDoldur(farkliKullanici)
                .vekaletAlanAlanınaGoruntulenmemeKontrolu(ekranAdi, false)
                .onayVerecekDoldur(onayVerecek);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi);
    }

}
