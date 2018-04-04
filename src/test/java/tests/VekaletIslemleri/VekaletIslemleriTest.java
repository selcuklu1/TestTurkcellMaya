package tests.VekaletIslemleri;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import common.ReusableSteps;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ParafladiklarimPage;
import pages.solMenuPages.VekaletOnaylariPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.VekaletVerPage;

import java.text.ParseException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

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
    KullaniciYonetimiPage kullaniciYonetimiPage;
    ReusableSteps reusableSteps;

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
    String vekaletBirimi = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";


    User optiim = new User("optiim", "123");
    User yakyol = new User("yakyol", "123");
    User mbozdemir = new User("mbozdemir", "123");
    User ztekin = new User("ztekin", "123");
    User usernameVV = new User("unvv", "123");
    User usernameVA = new User("usernameva", "123");
    String nameVV = "Unvv TEST";
    String nameVA = "Usernameva TEST";

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
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        reusableSteps = new ReusableSteps();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , description = "TS0025a : Onaya göndererek Vekalet Verme")
    public void TS0025a() throws InterruptedException {

        login(usernameVV);

        String basariMesaji = "İşlem başarılıdır!";
        String[] evrakNo = new String[2];
        aciklama = "onay " + getSysDate() + " evrak";
        redNedeni = "Red Edildi " + createRandomNumber(10) + " nedeni";
        String onayVerecekKullanici = "Username22N TEST";

        if ($("[id='aktifVekaletinizVarUyariMesajiDialog']").isDisplayed()) {
            mainPage
                    .vekaletVarUyariPopUp();
        }

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .sorgula()
                .vekaletListesiVekaletIptal(nameVV);

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

        vekaletVerPage
                .vekaletVerenDoldur(nameVV)
                .devredilecekEvraklarKontrolu()
                .vekaletAlanDoldur(nameVA)
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
    @Test(enabled = true
            , dependsOnMethods = {"TS0025a"}
            , description = "TS0025b : Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
    public void TS0025b() throws InterruptedException {

        login(TestData.username22n, TestData.password22n);

//        String aciklama = "onay 20171206220943 evrak";

//        String not = "red 20171206220943 nedeni";
        vekaletOnaylariPage
                .openPage()
//                .filtreleAc()
//                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .alanKontrolleri(nameVV, nameVA, getSysDateForKis(), aciklama)
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

        login(usernameVV);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS0025b"}
            , description = "TS2208 : Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
    public void TS2208() throws InterruptedException {
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuluyor.");
        vekaletVer();
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuldu.");
//emre
        login(TestData.username22n, TestData.password22n);

//        String aciklama = "onay 20180109134612 evrak";
        vekaletOnaylariPage
                .openPage()
                .tablodanOnaylanacakKayıtKontrolu(nameVV, nameVA, getSysDateForKis(), aciklama)
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

        login(usernameVV);


        mainPage
                .vekaletVarUyariPopUp();


        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1, false)
                .tabloEvrakNoKontrol(evrakNo2, true);

        logout();

        login(usernameVA);

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .birimKontol("Vekalet " + nameVV + " - " + vekaletBirimi + " - " + getSysDateForKis() + " - " + getSysDateForKis())
                .birimSec(Condition.text("Vekalet"))
                .takeScreenshot();
        //                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1, true)
                .tabloEvrakNoKontrol(evrakNo2, false);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS0015 : Vekaleti alan kullanıcının onay akışında seçilmesi(vekaleten)")
    public void TS0015() throws InterruptedException {

        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        String konu = "TS0015 " + getSysDate();
        login(yakyol);

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
                .kullanicilarDoldurWithTitle(nameVV, kullaniciTitle)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVA)
//                .vekeletAlanVerenTabloKapat()
//                .kullanicilarDoldur2("Optiim TEST2"," [Ağ (Network) Uzman Yardımcısı]")
                .kullniciIsmineGoreImzaParafSec(nameVA, tur)
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

        login(yakyol);
        parafladiklarimPage
                .openPage()
                .filtreleAc()
                .baslangicTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .raporSec()
                .icerikTikla(konu);

        String evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

//        logout();
        login(usernameVV);

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login(usernameVA);

        mainPage
                .vekaletVarUyariPopUp()
                .birimSec(Condition.text("Vekalet"));
//                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .pdfOnizleme();
        switchTo().window(1);
        imzaBekleyenlerPage
                .geregiBilgiAlaniAdresPdfKontrol("V.", true)
                .closeNewWindow();
        switchTo().window(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS0012 : vekaleti veren kullanıcının onay akışında seçilmesi (kendisi)")
    public void TS0012() throws InterruptedException {

        login(yakyol);

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
                .kullanicilarDoldurWithTitle(nameVV, kullaniciTitle)
                .vekeletAlanVerenTabloKontrolu()
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVV)
                .kullniciIsmineGoreImzaParafSec(nameVV, tur)
                .kullan()
                .onaAkisiTextKontol()
                .onayAkisiKullanilanKullaniciKontrolEt("Yasemin");

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakParafla();
//                .islemMesaji().basariliOlmali(basariMesaji);

        parafladiklarimPage
                .openPage()
                .konuyaGoreEvrakDetayiTikla(icerik);

        //Buraya gerek yok artık.
        // .icerikIlkKayıt();


        String evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

        logout();

        login(usernameVA);

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(evrakNo);

        logout();

        login(usernameVV);

        mainPage
                .vekaletVarUyariPopUp();

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(evrakNo)
                .icerik()
                .pdfOnizleme();
        switchTo().window(1);
        imzaBekleyenlerPage
                .geregiBilgiAlaniAdresPdfKontrol("V.", false)
                .closeNewWindow();
        switchTo().window(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS0014 : Vekalet veren kullanıcıya evrak havalesi ve kontrolü")
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

        login(ztekin);

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
                //.dagitimBilgileriKisiSec(nameVV)
                .dagitimBilgileriKisiSec2(nameVV)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVV)
                .kaydet();

        gelenEvrakNo = gelenEvrakKayitPage.popUps();
        logout();

        login(usernameVV);

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(gelenEvrakNo, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS2212 : Vekalet veren kullanıcının bulunduğu kullanıcı listesine evrak havalesi ve kontrolü")
    public void TS2212() throws InterruptedException {

        login(yakyol);

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        //region Test Datası
        String evrakNO2212 = gelenEvrakKayit(evrakGelisTipi, geldigiKurum);

        String mesaj = "Seçmiş olduğunuz kullanıcı grubunda vekalet vermiş kişiler bulunmaktadır. Kullanıcı grubunu kullanırsanız havale asıl kişilere(vekalet veren) gidecektir. Yine de işleme devam etmek istiyor musunuz?";
        String kullanici = "YAZILIM GELİŞTİRME";
        String title = "optiim";
        gelenEvraklarPage
                .evrakNoyaGoreEvrakSec(evrakNO2212)
                .havaleYap()
                .havaleYapKisiTreeSec(nameVV)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVV)
                .kullanciListesiSec2(kullanici)//ikinci gelen seçilmeli
                .confirmDialog().dialogMessage().shouldHave(text(mesaj));

        gelenEvraklarPage.confirmDialog().confirmEvetTikla();
        gelenEvraklarPage
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);
        logout();
        login(usernameVV);

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNO2212, true);

    }

    @Step("Testte kullanılmak üzere Gelen Evrak Kayit datası oluşturuldu.")
    private String gelenEvrakKayit(String evrakGelisTipi, String geldigiKurum) {
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
//        gelenEvrakKayitPage.islemMesaji().basariliOlmali();
        //endregion
        return evrakNO2212;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS0012"}
            , description = "TS1907 : Vekalet alan kullanıcıya evrak havalesi ve kontrolü")
    public void TS1907() throws InterruptedException {

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        String konu = "konu " + getSysDateForKis();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS0012"}
            , description = "TS0011 : Vekalet alan kullanıcıya evrak havalesi ve kontrolü")
    public void TS0011() throws InterruptedException {

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        String konu = "konu " + getSysDateForKis();

        login(mbozdemir);

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
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec2(nameVV)
                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVA)
                .kaydet();
        String evrakNO11 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();
//        }

//        String[] evrakNo = new String[2];
//        gelenEvraklarPage
//                .openPage();
//
//        evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(1);

//        gelenEvraklarPage
//                .evrakNoyaGoreEvrakSec(evrakNO11)
//                .havaleYap()
//                .havaleYapKisiTreeSec(nameVV)
//                .vekeletAlanVerenTabloVekaletAlanveyaVerenSec(nameVA)
//                .havaleYapGonder()
//                .islemMesaji().basariliOlmali(basariMesaji);
        logout();
        login(usernameVV);

        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNO11, false);

        logout();
        login(usernameVA);

        mainPage
                .vekaletVarUyariPopUp()
                .birimSec(Condition.text("Vekalet"));
//                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNO11, true);
    }

    @Step("Testte kullanılmak üzere kullanıcıya vekalet verildi.")
    private void vekaletVer() throws InterruptedException {
        TS0025a();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS2288 : Vekalet alan kullanıcıya havale gelen evrak iadesi ve kontrolü")
    public void TS2288() throws InterruptedException {
        login(usernameVA);
        mainPage
                .vekaletVarUyariPopUp()
                .birimSec(Condition.text("Vekalet"));

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1, true)
                .tabloEvrakNoSec(evrakNo1)
                .evrakOnizlemeButonKontrolu("İade Et")
                .evrakOnizlemeButonTikla("İade Et")
                .evrakOnizlemeIadeEdilecekKullanici()
                .iadeEtNotInputDoldur("TS2288 Vekalet İade")
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(usernameVV);
        mainPage
                .vekaletVarUyariPopUp();

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoKontrol(evrakNo1, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS2203 : Vekalet veren kullanıcının havale onayında seçilmesi")
    public void TS2203() throws InterruptedException {
        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        login(TestData.userMbozdemir);
        gelenEvrakKayitPage
                .openPage()
                .dagitimBilgileriOnaylayacakKisiPanel()
                .dagitimBilgileriOnaylayacakKisiKontrolü(nameVA, kullaniciTitle)
                .dagitimBilgileriOnaylayacakKisiKontrolü(nameVV, kullaniciTitle)
                .dagitimBilgileriOnaylayanKisiSecWithTitle(nameVV, kullaniciTitle)
                .popUpKullaniciSecimKontrulu()
                .popUpKullaniciSecimi(nameVA)
                .dagitimBilgileriOnaylayacakKisiAlaniKontrolü(nameVA, kullaniciTitle, nameVV);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS2210 : Kullanıcı yönetimi ekranında vekalet kontrolü")
    public void TS2210() throws InterruptedException {
        login(TestData.userMbozdemir);
        String gorevliOlduguBirim = "Vekalet";

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("usernameva")
                .ara()
                .kullaniciListesiTabloKontrolu()
                .kullaniciListesiGuncelleButonuTikla()
                .gorevliOlduguBirimlerKontol(gorevliOlduguBirim)
                .gorevliOlduguBirimlerGuncelle(gorevliOlduguBirim)
                .VekaletBirimiKullaniciBirimAtamaEkranKontrolu();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2208"}
            , description = "TS2204 : Vekalet Alan Kullanıcın Evrak Kapatma Onayında Seçilmesi")
    public void TS2204() throws InterruptedException {

        login(TestData.userMbozdemir);
        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        String title = "Ağ (Network) Uzman Yardımcısı";
        gelenEvraklarPage
                .openPage()
                .evrakSec()
//                .tabloEvrakNoKontrol(sayi, true)
//                .tabloEvrakNoSec(sayi)
                .evrakOnizlemeButonKontrolu("Evrak Kapat")
                .evrakOnizlemeButonTikla("Evrak Kapat")
                .evrakKapamaOnayAkisiTikla()
                .evrakKapamaKullaniciSecWithTitle(nameVV, kullaniciTitle)
                .popUpKullaniciSecimKontrulu()
                .popUpKullaniciSecimi(nameVA)
                .evrakKapamaKullanicilarAlaniKontrolü(nameVA, title, nameVV);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , priority = 0
            , description = "TS2200 : Vekalet verme ekranında alan kontrolleri")
    public void TS2200() throws InterruptedException, ParseException {

        login(TestData.username27);
        int afterDay = 2;
        String onayVerecekKullanici = "Username22N TEST";
        String baslangicTarihi = getAfterSysDate(afterDay);
        String bitisTarihi = getSysDateForKis();
        System.out.println(baslangicTarihi);
        String dikkatMesaj = "Bitiş tarihi başlangıç tarihinden önce olamaz!";

        vekaletVerPage
                .openPage()
                .vekaletAlanAlanKontrolu(false)
                .onayVerecekAlanKontrolu(false)
                .vekaletVerenKontrolu("Username26 TEST", false)//Güncel birim TS2200 Alt Birim Personel Username26 üst birim YGD Personel
                .vekaletVerenKontrolu("Kıvanç KASIMOĞLU ", false)//Güncel birim TS2200 Alt Birim Personel Kıvanc üst birim YGD Amir
                .vekaletVerenKontrolu("Username29 TEST", true)//Güncel birim TS2200 Alt Birim Personel Username29 TS2200 2. Alt Birim Amir
                .vekaletVerenDoldur("Username28 TEST")//Güncel birim TS2200 Alt Birim Personel Username28 güncel birim TS2200 Alt Birim Amir
                .vekaletAlanAlanKontrolu(true)
//                .onayVerecekAlanKontrolu(true)  //Onay verecek kişi alanı pasif kalıyor
//                .vekaletVerenSil()
                .vekaletAlanKontrolu("UsernemaVV TEST", false)  //Güncel birim TS2200 Alt Birim Personel Usernamevv üst birim Amir Yardımcısı
                .vekaletAlanKontrolu("Username35 TEST", true)//Güncel birim TS2200 Alt Birim Personel Username29 TS2200 2. Alt Birim Amir
                .vekaletAlanDoldur("Username27 TEST")//Güncel birim TS2200 Alt Birim Personel
                .onayVerecekDoldur(onayVerecekKullanici)
                .baslangicTarihDoldur(baslangicTarihi)
                .bitisTarihiDoldur(bitisTarihi)
                .uygula()
                .islemMesaji().dikkatOlmali(dikkatMesaj);

        vekaletVerPage
                .bitisTarihiDoldur(getAfterSysDate(afterDay + 5))
                .evraktaVelaketeSonEkiGorunsunSeçilebilirlikKontrolu(true)
                .ozelUnvanSec()
                .ozelUnvanGirilebilecekAlanKontrolu(true)
                .aciklamaDoldur(aciklama)
                .uygula();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            ,dependsOnMethods = {"TS2208"}
            , description = "TS2205 : Vekalet alan kullanıcının havale onayında seçilmesi")
    public void TS2205() throws InterruptedException {

        login(TestData.userMbozdemir);

        String konu = "TS2205 " + createRandomNumber(8);
//        String konu = "TS2205 15324160";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birimName = "Usernameva TEST";
        String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
        String birim = "Vekalet: " + getSysDateForKis()+ "/" + getSysDateForKis() + " " + nameVV;
        String detail = nameVA + kullaniciTitle+ '\n' +"YGD"+'\n'+"BHUPGMY"+" - "+ birim;

//        reusableSteps.gelenEvraklarEvrakOlustur(konu, kurum, birimName);
//
//        login(TestData.usernameva,TestData.passwordva);
//
//        mainPage.vekaletVarUyariPopUp()
//                .birimSec(Condition.exactText("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"));


//        gelenEvraklarPage
//                .openPage()
//                .konuyaGoreEvrakOnizlemedeAc(konu)
//                .evrakOnizlemeButonTikla("Havale Yap")
//                .havaleIslemleriOnaylayacakKisiSec(nameVV)
//                .vekaletVarPopupSeçim(nameVV)
//                .evrakOnzilemeOnaylayanKisiKontrolu(nameVV,kullaniciTitle);

        login(TestData.userMbozdemir);

        gelenEvrakKayitPage
                .openPage()
                .dagitimBilgileriOnaylayacakKisiKontrolü(nameVV, kullaniciTitle)
                .dagitimBilgileriOnaylayacakKisiKontrolü(nameVA, kullaniciTitle)
                .dagitimBilgileriOnaylayacakKisiDetailKontrol(nameVA, birim)
                .dagitimBilgileriOnaylayanKisiSec(nameVA)
                .dagitimBilgileriSeçilenOnaylayacakKisiDetailKontrol(nameVA, detail);

    }
}
