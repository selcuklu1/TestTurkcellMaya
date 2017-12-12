package tests.VekaletIslemleri;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.VekaletOnaylariPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.VekaletVerPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class VekaletIslemleriTest extends BaseTest {
    MainPage mainPage;
    VekaletVerPage vekaletVerPage;
    GelenEvraklarPage gelenEvraklarPage;
    VekaletOnaylariPage vekaletOnaylariPage;
    EvrakOlusturPage evrakOlusturPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;

    String aciklama = "";
    String redNedeni = "";
    String vekaletVeren = "Optiim TEST1";
    String vekaletAlan = "Optiim TEST2";
    String evrakNo1 = "";
    String evrakNo2 = "";

    @BeforeMethod
    public void loginBeforeTests() {
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        vekaletOnaylariPage = new VekaletOnaylariPage();
        evrakOlusturPage = new EvrakOlusturPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme")
    public void TC0025a() throws InterruptedException {

        login("test1", "123");

        String basariMesaji = "İşlem başarılıdır!";
        String[] evrakNo = new String[2];
        aciklama = "onay " + getSysDate() + " evrak";
        redNedeni = "red " + getSysDate() + " nedeni";

        gelenEvraklarPage
                .openPage();

        evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(2);

        evrakNo1 = evrakNo[0];
        evrakNo2 = evrakNo[1];

        vekaletVerPage
                .openPage();
        for (int i = 0; i < evrakNo.length; i++) {
            vekaletVerPage
                    .evrakEkle()
                    .evrakAramaDoldur(evrakNo[i])
                    .dokumanAra()
                    .evrakAramaTabloKontrolveSecim(evrakNo[i]);
        }
        vekaletVerPage
                .vekaletVerenDoldur(vekaletVeren)
                .devredilecekEvraklarKontrolu()
                .vekaletAlanDoldur(vekaletAlan)
                .onayVerecekDoldur("Zübeyde TEKİN")
                .aciklamaDoldur(aciklama)
                .devredilecekEvrakSec(evrakNo1)
                .uygula()
                .islemMesaji().beklenenMesaj(basariMesaji);

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .sorgula()
                .vekaletListesiTabloKontrol();
        logout();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC0025a"}, description = "Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
    public void TC0025b() throws InterruptedException {

        login(username2, password2);

//        String aciklama = "onay 20171206220943 evrak";
//
//        String not = "red 20171206220943 nedeni";
        vekaletOnaylariPage
                .openPage()
                .filtreleAc()
                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .alanKontrolleri(vekaletVeren, vekaletAlan, getSysDateForKis())
                .ekleyeceginizNotlarDoldur(redNedeni)
                .reddet();

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .durumSec("2")
                .sorgula()
                .vekaletListesiTabloKontrol(11, redNedeni);
        logout();

        login("test1", "123");

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods = {"TC0025b"}, description = "Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
    public void TC2208() throws InterruptedException {
        TC0025a();
        login(username2, password2);

//        String aciklama = "onay 20171206142921 evrak";
        vekaletOnaylariPage
                .openPage()
                .filtreleAc()
                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .onayEvrakiKontrol()
                .detay()
                .evrakKontol(evrakNo1)
                .ekleyeceginizNotlarDoldur(aciklama)
                .onay();

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .sorgula()
                .vekaletListesiTabloKontrol(8, "Onaylandı");

        logout();

        login("test1", "123");

        vekaletVerPage
                .vekaletVarUyarıPopUp();


        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNo1);

        logout();

        login("optiimTest2", "123");

        vekaletVerPage
                .vekaletVarUyarıPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNo1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "Vekaleti alan kullanıcının onay akışında seçilmesi(vekaleten)")
    public void TC0015() throws InterruptedException {

        login(username3, password3);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("010.01")
                .kaldiralacakKlasorlerSec("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .ivedikSec("Normal")
                .geregiSec("Optiim Birim")
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarImzaciSec("Paraflama")
                .kullanicilarDoldur2("Optiim TEST")
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloKapat()
                .kullanicilarDoldur2("Optiim TEST1")
                .kullniciIsmineGoreImzaParafSec("Optiim TEST1", "İmzalama")
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullanilanKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("Test Otomasyon son55")
                .parafla();
        //11 12e 13.  adımlar yazılacak
//                .sImzasec()
//                .evrakImzalama()
//                .imzala();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "vekaleti veren kullanıcının onay akışında seçilmesi (kendisi)")
    public void TC0012() throws InterruptedException {

        login(username3, password3);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("010.01")
                .kaldiralacakKlasorlerSec("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .ivedikSec("Normal")
                .geregiSec("Birim")
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarImzaciSec("Paraflama")
                .kullanicilarDoldur2("Optiim TEST")
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloKapat()
                .kullanicilarDoldur2("Optiim TEST1")
                .kullniciIsmineGoreImzaParafSec("Optiim TEST1", "İmzalama")
                .kullan()
                .onaAkisiTextKontol();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("Test Otomasyon")
                .parafla();

        logout();
        login(username,username);
        imzaBekleyenlerPage
                .openPage();


    }

}
