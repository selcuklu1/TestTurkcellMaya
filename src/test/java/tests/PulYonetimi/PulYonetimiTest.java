package tests.PulYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.PostaListesiPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.solMenuPages.TopluPostalanacakEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
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
    EvrakOlusturPage evrakOlusturPage;
    TopluPostalanacakEvraklarPage topluPostalanacakEvraklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        pulYonetimiPage = new PulYonetimiPage();
        postaListesiPage = new PostaListesiPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        evrakOlusturPage = new EvrakOlusturPage();
        topluPostalanacakEvraklarPage = new TopluPostalanacakEvraklarPage();


    }

    String konuKodu = "010.01";
    String kaldiralacakKlasor = "Diğer";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String gizlilikDerecesi = "Normal";
    String ivedilik = "Normal";
    String geregi = "Ahmet ÇELİK";
    String konu = "TC2214 " + getSysDate();
    String tur = "İmzalama";
    String geregiTipi = "Gerçek Kişi";
    String basariMesaji = "İşlem başarılıdır!";

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1732: Pul Yönetimi ekranından yeni tanımlama yapma")
    public void TC1732() throws InterruptedException {

        login("mbozdemir", "123");
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

        login("mbozdemir", "123");
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu;
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String tutar = "120";
        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };

//        Test datası oluşturuluyor

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedilikSec(ivedilik)
                .geregiSecimTipiSecByText(geregiTipi)
                .geregiSec(geregi)
                .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
                .onayAkisiEkle()
                .onayAkisiEkleIlkImzalaSec(tur)
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu)
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup();

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec("01.01.2017", getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakSec(konu, true)
                .postaListesineAktar()
                .listeAdiDoldur(konu)
                .listeOlustur()
                .postaListesiSec(konu)
                .listeyeEkle();

//         Test başlangıcı

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)

                .postaListesiPostala()
                .alanKontrolu()
                .gidisSekliSec(gidisSekli)

                .gramajDoldur(gramaj1)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("50.00", true)
                .indirimOraniKontrol("10", true)
                .tutarKontrol("45.00", true)

                .indirimOraniDoldur(indirimOrani)
                .tutarKontrol("40.00", true)

                .gramajDoldur(gramaj3)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("100.00", true)
                .indirimOraniKontrol("20", true)
                .tutarKontrol("80.00", true)

                .gramajDoldur(gramaj5)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("100.00", true)
                .indirimOraniKontrol("0", true)
                .tutarKontrol("100.00", true)

                .tutarDoldur(tutar)
                .postaDetayiPostala()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2214 : Pul yönetimi ekranındaki tanımın postalanacaklar listesinde kontrolü")
    public void TC2214() throws InterruptedException {

        login("yakyol", "123");

        String gramaj = "1";
        String gramaj1 = "3";
        String gramaj2 = "5";
        String konu = "TC2214 20180102113202";

        // test datası oluşturuluyor
//        evrakOlusturPage
//                .openPage()
//                .bilgilerTabiAc()
//                .konuKoduSec(konuKodu)
//                .konuDoldur(konu)
//                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
//                .evrakTuruSec(evrakTuru)
//                .evrakDiliSec(evrakDili)
//                .gizlilikDerecesiSec(gizlilikDerecesi)
//                .ivedilikSec(ivedilik)
//                .geregiSecimTipiSecByText(geregiTipi)
//                .geregiSec(geregi)
//                .gercekKisiGeregiAlaniPostaTipiSec("Ankara İçi APS")
//                .onayAkisiEkle()
//                .onayAkisiEkleIlkImzalaSec(tur)
//                .kullan();
//        evrakOlusturPage
//                .editorTabAc()
//                .editorIcerikDoldur(konu)
//                .imzala()
//                .sImzasec()
//                .sImzaImzala()
//                .sayisalImzaEvetPopup();
//
//        logout();
//        login("mbozdemir", "123");

        // test başlangıcı

        postalanacakEvraklarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu)
                .evrakPostala()
                .gidisSekli("Ankara İçi APS")
                .gramajDoldur(gramaj)
                .evrakOnzilemeHesapla()
                .popUpKontrol()
                .popUpIndirimOncesiTutarKontrol("50.00", true)
                .popUpIndirimOraniKontrol("10", true)
                .popUpTutarKontrol("45.00", true)
                .popUpTamam()
                .tutarAlaniKontrolu("45.00", true)

                .gramajDoldur(gramaj1)
                .evrakOnzilemeHesapla()
                .popUpKontrol()
                .popUpIndirimOncesiTutarKontrol("100.00", true)
                .popUpIndirimOraniKontrol("20", true)
                .popUpTutarKontrol("80.00", true)
                .popUpTamam()
                .tutarAlaniKontrolu("80.00", true)


                .gramajDoldur(gramaj2)
                .evrakOnzilemeHesapla()
                .popUpKontrol()
                .popUpIndirimOncesiTutarKontrol("100.00", true)
                .popUpIndirimOraniKontrol("0", true)
                .popUpTutarKontrol("100.00", true)
                .popUpTamam()
                .tutarAlaniKontrolu("100.00", true)

                .tutarDoldur("120")
                .evrakPostalaPostala(true);
    }

}


