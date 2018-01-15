/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.KisiselIslemlerBagTipi;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.OnayAkisYonetimiPage;
import pages.ustMenuPages.VekaletVerPage;

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
    @Test(enabled = true, description = "TS2141: Bağ tipi amir yardımcısı olması kontrolleri")
    public void TS2141() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Amir Yardımcısı";
        String farkliKullanici = "Optiim";

        // Gelen evraklar alanında veri bulunmalı

        login("kbagtipi", password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi, "Bağ Tipi")
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi, "Kullanıcı");

        vekaletVerPage
                .openPage()
                .vekaletVerenDoldur(ekranAdi)
                .vekaletVerenFarkliDoldur(farkliKullanici)
                .onayVerecekDoldur(ekranAdi);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi, "Onaylanacak kişi");
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2142: Bağ tipi vekaleten amir yardımcısı kontrolleri")
    public void TS2142() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Amir Yardımcısı";
        String farkliKullanici = "Optiim";
        String onayAkisiKullanicilarTuru = "İmzalama";
        String gnMdV = "Gn.Md. V.";
        String genelMudur = "Genel Müdür V.";
        String kullanicilarTuru = "İmzalama";
        String randomAd = createRandomNumber(7);

        //Gelen evraklar alanında veri bulunmalı

        login(username2, password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi, "Bağ Tipi")
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi, "Kullanıcı")
                .onayAkisiEkle()
                .kullanicilarDoldur(ekranAdi)
                .kullanicilarImzaciSec2(kullanicilarTuru)
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .imzacılarGnMdVKontrol(gnMdV);

        onayAkisYonetimiPage
                .openPage()
                .yeniOnayAkisiEkle()
                .onayAkisiIslemlerKullaniciDoldur(ekranAdi)
                .imzacıSonSec(onayAkisiKullanicilarTuru)
                .onayAkisiIslemleriAdDoldur(randomAd);

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
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi, "Onaylanacak kişi");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2168: Bağ tipi personel kontrolleri")
    public void TS2168() {

        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Personel";
        String farkliKullanici = "Optiim";
        String onayVerecek = "Zübeyde TEKİN";

        //Gelen evraklar alanında veri bulunmalı

        login("kbagtipi", password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi, "Bağ Tipi")
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
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi, "Onaylanacak kişi");
    }

}
