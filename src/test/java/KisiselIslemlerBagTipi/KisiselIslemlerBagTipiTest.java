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
public class KisiselIslemlerBagTipiTest extends BaseTest{

    KullaniciYonetimiPage kullaniciYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    VekaletVerPage vekaletVerPage;
    GelenEvraklarPage gelenEvraklarPage;
    pages.EvrakOlusturPage evrakOlusturPageTab;
    OnayAkisYonetimiPage onayAkisYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPageTab = new pages.EvrakOlusturPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2141: Bağ tipi amir yardımcısı olması kontrolleri")
    public void TC2141() throws InterruptedException {
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
    public void TC2142() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Y";
        String farkliKullanici = "Optiim";
        String onayAkisiKullanicilarTuru = "İmzalama";
        String gnMdV = "Gn.Md. V.";
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
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi)
                .onayAkisiEkle()
                .kullanicilarDoldur(ekranAdi)
                .onayAkisiKullanicilarImzacıSec(onayAkisiKullanicilarTuru)
                .onayAkisiKullan();
        evrakOlusturPageTab
                .editorTabAc()
                .imzacılarGnMdVKontrol(gnMdV);
        onayAkisYonetimiPage
                .onayAkisiYeni()
                .onayAkisiIslemlerKullanicilarDoldur(ekranAdi)
                .imzacıSonSec(onayAkisiKullanicilarTuru)
                .onayAkisiIslemleriKaydet();
        //  vekaletVerPage
        //        .openPage()
          //      .vekaletVerenDoldur(ekranAdi)
            //    .vekaletVerenFarkliDoldur(farkliKullanici)
              //  .onayVerecekDoldur(ekranAdi);
        //gelenEvraklarPage
          //      .openPage()
            //    .evrakSec()
              //  .tabHavaleYap()
                //.havaleYapOnaylanacakKisiTreeDoldur(ekranAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2168: Bağ tipi personel kontrolleri")
    public void TC2168() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "P";
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
                .otomatikOnayAkisi()
                .otomatikOnayAkisiGelmedigiGorme(ekranAdi,false);
        vekaletVerPage
                .openPage()
                .vekaletVerenAlanınaGoruntulenmemeKontrolu(ekranAdi, false)
                .vekaletVerenDoldur(farkliKullanici)
                .vekaletAlanAlanınaGoruntulenmemeKontrolu(ekranAdi, false)
                .onayVerecekDoldur("Zübeyde TEKİN");
        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi);
    }


}
