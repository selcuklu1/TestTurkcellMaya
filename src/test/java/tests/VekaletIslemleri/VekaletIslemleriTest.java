package tests.VekaletIslemleri;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.Parafladiklarim;
import pages.solMenuPages.VekaletOnaylariPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
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
    Parafladiklarim parafladiklarim;
    GelenEvrakKayitPage gelenEvrakKayitPage;

    String aciklama = "";
    String redNedeni = "";
    String vekaletVeren = "Optiim TEST1";
    String vekaletAlan = "Optiim TEST2";
    String evrakNo1 = "";
    String evrakNo2 = "";
    String basariMesaji = "İşlem başarılıdır!";
    String tur = "IMZALAMA";
    String icerik = "Test Otomasyon " + getSysDate();
    String konuKodu = "010.01";
    String kaldiralacakKlasor = "Diğer";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String gizlilikDerecesi = "Normal";
    String ivedilik = "Normal";
    String geregi = "Optiim Birim";

    @BeforeMethod
    public void loginBeforeTests() {
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        vekaletOnaylariPage = new VekaletOnaylariPage();
        evrakOlusturPage = new EvrakOlusturPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        parafladiklarim = new Parafladiklarim();
        mainPage = new MainPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
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
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
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
    @Test(enabled = true, dependsOnMethods = {"TC0025b"}, description = "Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
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


        mainPage
                .vekaletVarUyarıPopUp();


        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNo1);

        logout();

        login("optiimTest2", "123");

        mainPage
                .vekaletVarUyarıPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNo1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Vekaleti alan kullanıcının onay akışında seçilmesi(vekaleten)")
    public void TC0015() throws InterruptedException {

        login(username3, password3);


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarImzaciSec("PARAFLAMA")
                .kullanicilarDoldur2(vekaletVeren)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletAlan)
//                .vekeletAlanVerenTabloKapat()
//                .kullanicilarDoldur2("Optiim TEST2")
                .kullniciIsmineGoreImzaParafSec(vekaletAlan, tur)
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullanilanKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .sImzasec()
                .sImzaImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarim
                .openPage()
                .filtreleAc()
                .baslangicTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .raporSec()
                .icerikIlkKayıt();

        String evrakNo= parafladiklarim.evrakDetayiEvrakNoAl();

        logout();
        login("test1","123");

        mainPage
                .vekaletVarUyarıPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login("optiimtest2","123");

        mainPage
                .vekaletVarUyarıPopUp();

        imzaBekleyenlerPage
                .birimSec(getSysDateForKis())
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .icerikKontrol("V.");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "vekaleti veren kullanıcının onay akışında seçilmesi (kendisi)")
    public void TC0012() throws InterruptedException {

        login(username3, password3);

        String tur = "IMZALAMA";
        String icerik = "Test Otomasyon " + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarImzaciSec("PARAFLAMA")
                .kullanicilarDoldur2(vekaletVeren)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletVeren)
                .kullniciIsmineGoreImzaParafSec(vekaletVeren, tur)
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullanilanKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .sImzasec()
                .sImzaImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarim
                .openPage()
                .filtreleAc()
                .baslangicTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .raporSec()
                .icerikIlkKayıt();

        String evrakNo= parafladiklarim.evrakDetayiEvrakNoAl();

        logout();

        login("optiimtest2","123");

        mainPage
                .vekaletVarUyarıPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login("test1","123");

        mainPage
                .vekaletVarUyarıPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .icerikKontrol("V.");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Vekalet veren kullanıcıya evrak havalesi ve kontrolü")
    public void TC0014() throws InterruptedException{

        String kisiKurum = "D";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "P";
        String ivedilik = "N";
        String ekMetni = "test otomasyon";
        String gelenEvrakNo = "";
        String evrakTuru = "R";
        String evrakDili = "917";
        String gizlilikDerecesi = "N";

        login(username,password);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec(vekaletVeren)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletVeren)
                .kaydet();

        gelenEvrakNo = gelenEvrakKayitPage.popUps();
        logout();

        login("test1","123");

        mainPage
                .vekaletVarUyarıPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(gelenEvrakNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Vekalet veren kullanıcının bulunduğu kullanıcı listesine evrak havalesi ve kontrolü")
    public void TC2212() throws InterruptedException{

        login(username3,password3);

        String[] evrakNo = new String[2];
        gelenEvraklarPage
                .openPage();

        evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(1);

        gelenEvraklarPage
                .evrakSec()
                .havaleYap()
                .havaleYapKisiTreeSec(vekaletVeren)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletVeren)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);
        logout();
        login("test1","123");

        mainPage
                .vekaletVarUyarıPopUp();

        gelenEvraklarPage
                .tabloEvrakNoKontrol(evrakNo[0].toString());

    }
}
