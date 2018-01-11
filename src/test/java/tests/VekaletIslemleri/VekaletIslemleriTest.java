package tests.VekaletIslemleri;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ParafladiklarimPage;
import pages.solMenuPages.VekaletOnaylariPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.VekaletVerPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
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
    ParafladiklarimPage parafladiklarimPage;
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
        parafladiklarimPage = new ParafladiklarimPage();
        mainPage = new MainPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS0025a : Onaya göndererek Vekalet Verme")
    public void TS0025a() throws InterruptedException {

        login("test1", "123");

        String basariMesaji = "İşlem başarılıdır!";
        String[] evrakNo = new String[2];
        aciklama = "onay " + getSysDate() + " evrak";
        redNedeni = "red " + getSysDate() + " nedeni";

        if ($("[id='aktifVekaletinizVarUyariMesajiDialog']").isDisplayed()) {
            mainPage
                    .vekaletVarUyariPopUp();
        }

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .sorgula()
                .vekaletListesiVekaletIptal(vekaletVeren);

        vekaletVerPage
                .openPage();

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
        String onayVerecekKullanici = "Zübeyde TEKİN";
        vekaletVerPage
                .vekaletVerenDoldur(vekaletVeren)
                .devredilecekEvraklarKontrolu()
                .vekaletAlanDoldur(vekaletAlan)
                .onayVerecekDoldur(onayVerecekKullanici)
                .aciklamaDoldur(aciklama)
                .devredilecekEvrakSec(evrakNo1)
                .uygula();
//                .islemMesaji().beklenenMesaj(basariMesaji);

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .sorgula()
                .vekaletListesiTabloKontrol();
//                .islemMesaji().beklenenMesaj(basariMesaji);
        logout();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS0025a"}, description = "TS0025b : Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
    public void TS0025b() throws InterruptedException {

        login(username2, password2);

//        String aciklama = "onay 20171206220943 evrak";
//
//        String not = "red 20171206220943 nedeni";
        vekaletOnaylariPage
                .openPage()
//                .filtreleAc()
//                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .alanKontrolleri(vekaletVeren, vekaletAlan, getSysDateForKis(), aciklama)
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
    @Test(enabled = true, priority = 3, description = "TS2208 : Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
    public void TS2208() throws InterruptedException {
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuluyor.");
        TS0025a();
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuldu.");

        login(username2, password2);

//        String aciklama = "onay 20180109134612 evrak";
        vekaletOnaylariPage
                .openPage()
                .tablodanOnaylanacakKayıtKontrolu(vekaletVeren, vekaletAlan, getSysDateForKis(), aciklama)
                .tablodanOnaylanacakKayıtSec(aciklama)
                .onayEvrakiKontrol()
                .detay()
                .evrakKontol(evrakNo1)
                .detayEkraniKapat();
//                .islemMesaji().basariliOlmali(basariMesaji);

        vekaletOnaylariPage
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
                .vekaletVarUyariPopUp();


        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNo1)
                .tabloEvrakNoKontrol(evrakNo2);

        logout();

        login("optiimTest2", "123");

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .birimKontol("Vekalet " + vekaletVeren + " - " + geregi + " - " + getSysDateForKis() + " - " + getSysDateForKis())
                .birimSec(Condition.text("Vekalet"))
                .takeScreenshot();
        //                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1)
                .tabloOlmayanEvrakNoKontrol(evrakNo2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS2208"}, description = "TS0015 : Vekaleti alan kullanıcının onay akışında seçilmesi(vekaleten)")
    public void TS0015() throws InterruptedException {

        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        String konu = "konu " + getSysDateForKis();
        login(username3, password3);

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
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .IlkKullaniciImzalamaVeyaParaflamaSec("PARAFLAMA")
                .kullanicilarDoldurWithTitle(vekaletVeren, kullaniciTitle)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletAlan)
//                .vekeletAlanVerenTabloKapat()
//                .kullanicilarDoldur2("Optiim TEST2"," [Ağ (Network) Uzman Yardımcısı]")
                .kullniciIsmineGoreImzaParafSec(vekaletAlan, tur)
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullaniciKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .sImzasec()
                .sImzaImzala2();
//                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarimPage
                .openPage()
                .filtreleAc()
                .baslangicTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .raporSec()
                .icerikIlkKayıt();

        String evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

        logout();
        login("test1", "123");

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login("optiimtest2", "123");

        mainPage
                .vekaletVarUyariPopUp()
                .birimSec(Condition.text("Vekalet"));
//                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .icerikKontrol("V.");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS2208"}, description = "TS0012 : vekaleti veren kullanıcının onay akışında seçilmesi (kendisi)")
    public void TS0012() throws InterruptedException {

        login(username3, password3);

        String tur = "IMZALAMA";
        String icerik = "TS0012 " + getSysDate();
        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(icerik)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedilikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .IlkKullaniciImzalamaVeyaParaflamaSec("PARAFLAMA")
                .kullanicilarDoldurWithTitle(vekaletVeren, kullaniciTitle)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletVeren)
                .kullniciIsmineGoreImzaParafSec(vekaletVeren, tur)
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullaniciKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .sImzasec()
                .sImzaImzala2();
//                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarimPage
                .openPage()
//                .filtreleAc()
//                .baslangicTarihiDoldur(getSysDateForKis())
//                .bitisTarihiDoldur(getSysDateForKis())
                .konuyaGoreRaporSec(icerik)
                .icerikIlkKayıt();


        String evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

        logout();

        login("optiimtest2", "123");

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login("test1", "123");

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .icerikKontrol("V.");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS2208"}, description = "TS0014 : Vekalet veren kullanıcıya evrak havalesi ve kontrolü")
    public void TS0014() throws InterruptedException {

        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String ekMetni = "test otomasyon";
        String gelenEvrakNo = "";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String konu = "konu " + getSysDateForKis();

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
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

        login("test1", "123");

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(gelenEvrakNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            , dependsOnMethods = {"TS2208"}
            , description = "TS2212 : Vekalet veren kullanıcının bulunduğu kullanıcı listesine evrak havalesi ve kontrolü")
    public void TS2212() throws InterruptedException {

        login(username3, password3);

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        //region Test Datası
        gelenEvrakKayitPage
                .openPage()
//                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf.pdf")
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("YASEMİN")
                .kaydet();
        String evrakNO2212 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();
        //endregion

        String mesaj = "Seçmiş olduğunuz kullanıcı grubunda vekalet vermiş kişiler bulunmaktadır. Kullanıcı grubunu kullanırsanız havale asıl kişilere(vekalet veren) gidecektir. Yine de işleme devam etmek istiyor musunuz?";
        String kullanici = "OPTİİM";
        String title = "optiim";
        gelenEvraklarPage
                .evrakNoyaGoreEvrakSec(evrakNO2212)
                .havaleYap()
                .havaleYapKisiTreeSec(vekaletVeren)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletVeren)
                .kullanciListesiSecWithTitle(kullanici, title)//ikinci gelen seçilmeli
                .confirmDialog().dialogMessage().shouldHave(text(mesaj));
        gelenEvraklarPage.confirmDialog().button("Evet").click();
        gelenEvraklarPage
                .havaleYapGonder();
//                .islemMesaji().basariliOlmali(basariMesaji);
        logout();
        login("test1", "123");

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNO2212);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS0012"}
            , description = "TS0011 : Vekalet alan kullanıcıya evrak havalesi ve kontrolü")
    public void TS0011() throws InterruptedException {

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";

        login(username3, password3);

//        gelenEvraklarPage
//                .openPage();
//        int size = gelenEvraklarPage.tabloEvrakAdetKontrol();
//
//        Test için gelen evrak kayit işlemi ile data oluşturuluyor.
//        if (size == 0) {
        gelenEvrakKayitPage
                .openPage()
//                .evrakBilgileriUstYaziEkle("C:\\Users\\Emre_Sencan\\Pictures\\pdf.pdf")
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("YASEMİN")
                .kaydet();
        String evrakNO11 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();
//        }

//        String[] evrakNo = new String[2];
//        gelenEvraklarPage
//                .openPage();
//
//        evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(1);

        gelenEvraklarPage
                .evrakNoyaGoreEvrakSec(evrakNO11)
                .havaleYap()
                .havaleYapKisiTreeSec(vekaletVeren)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(vekaletAlan)
                .havaleYapGonder();
//                .islemMesaji().basariliOlmali(basariMesaji);
        logout();
        login("test1", "123");

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakNoKontrol(evrakNO11);


        logout();
        login("optiimtest2", "123");

        mainPage
                .vekaletVarUyariPopUp()
                .birimSec(Condition.text("Vekalet"));
//                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNO11);
    }
}
