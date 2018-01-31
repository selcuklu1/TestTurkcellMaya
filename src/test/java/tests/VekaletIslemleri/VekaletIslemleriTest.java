package tests.VekaletIslemleri;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import data.User;
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
    User usernameVV = new User("usernamevv", "123");
    User usernameVA = new User("usernameva", "123");
    String nameVV = "Usernamevv TEST";
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
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS0025a : Onaya göndererek Vekalet Verme")
    public void TS0025a() throws InterruptedException {

        login(usernameVV);

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
        String onayVerecekKullanici = "Zübeyde TEKİN";
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
    @Test(enabled = true, dependsOnMethods = {"TS0025a"}, description = "TS0025b : Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
    public void TS0025b() throws InterruptedException {

        login(ztekin);

//        String aciklama = "onay 20171206220943 evrak";
//
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
    @Test(enabled = true, priority = 2, description = "TS2208 : Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
    public void TS2208() throws InterruptedException {
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuluyor.");
        TS0025a();
//        Allure.addAttachment("Test Datası", "Test Datası oluşturuldu.");

        login(ztekin);

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

        parafladiklarimPage
                .openPage()
                .filtreleAc()
                .baslangicTarihiDoldur(getSysDateForKis())
                .bitisTarihiDoldur(getSysDateForKis())
                .raporSec()
                .icerikIlkKayıt();

        String evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

        logout();
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
}
