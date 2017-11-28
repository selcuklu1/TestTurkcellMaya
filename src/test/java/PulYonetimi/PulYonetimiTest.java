package PulYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.PostaListesiPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.ustMenuPages.PulYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Pul Yonetimi" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class PulYonetimiTest extends BaseTest {
    MainPage mainPage;
    PulYonetimiPage pulYonetimiPage;
    PostaListesiPage postaListesiPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        pulYonetimiPage = new PulYonetimiPage();
        postaListesiPage = new PostaListesiPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        login("yakyol", "123");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1732: Pul Yönetimi ekranından yeni tanımlama yapma")
    public void TC1732() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";

        String postaTipi = "X";
        String gramaj = "3";
        String tutar = "50";
        String indirimOrani = "10";

        String gramaj1 = "5";
        String tutar1 = "100";
        String indirimOrani1 = "20";

        String postaTipi1 = "U";
        String gonderimTipi = "YURT_ICI";
        String gonderimSekli = "Ankara İçi APS";

        pulYonetimiPage
                .openPage()
                .yeniPulEkle()
                .pulEklemePostaTipiSec(postaTipi)
                .gramajiDoldur(gramaj)
                .tutariDoldur(tutar)
                .indirimOraniDoldur(indirimOrani)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        pulYonetimiPage
                .pulEklemePostaTipiSec(postaTipi)
                .gramajiDoldur(gramaj1)
                .tutariDoldur(tutar1)
                .indirimOraniDoldur(indirimOrani1)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        pulYonetimiPage
                .postaTipiSec(postaTipi1)
                .gonderimTipiSec(gonderimTipi)
                .ara()
                .tabloKontrolu(gonderimSekli);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Pul yönetimi ekranındaki tanımın posta listesinde kontrolü")
    public void TC2215() throws InterruptedException {

        String postaListesi = "optiim";
        String gidisSekli = "Ankara İçi APS";
        String gramaj = "1";
        String indirimOrani = "20";
        String gramaj1 = "3";
        String gramaj2 = "5";
        String tutar = "120";
        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .tablodanIlkRowSec()
                .postaListesiPostala()
                .alanKontrolu()
                .gidisSekliSec(gidisSekli)
                .gramajDoldur(gramaj)
//                .alanKontrolu()
//                .gidisSekliSec("Ankara İçi APS")
                .gramajDoldur("1")
                .tutarHesapla()
//        ekranda gelen bilgilerin kontrolü
                .indirimOraniDoldur(indirimOrani)
                .gramajDoldur(gramaj1)
                .tutarHesapla()
                .gramajDoldur(gramaj2)
                .tutarHesapla()
                .tutarDoldur(tutar);
//                .postaDetayiPostala();
//        alan kontrolleri için mail atıldı.

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2214 : Pul yönetimi ekranındaki tanımın postalanacaklar listesinde kontrolü")
    public void TC2214() throws InterruptedException {

        postalanacakEvraklarPage
                .openPage()
                .postaDetayi()
                .gonderilenYerDetay()
                .gidisSekli("Ankara İçi APS")
                .gramajDoldur("1")
                .tamam()
                .gramajDoldur("3")
                .hesapla()
                .gramajDoldur("5")
                .hesapla()
                .tutarDoldur("120");
//        Postala butonu mevcut değil
    }

}


