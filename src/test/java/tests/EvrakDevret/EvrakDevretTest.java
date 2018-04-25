package tests.EvrakDevret;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.CevapYazPage;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;


/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/

public class EvrakDevretTest extends BaseTest {

    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    EvrakOlusturPage evrakOlusturPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;
    TaslakEvraklarPage taslakEvraklarPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;

    User mbozdemir = new User("mbozdemir", "123");
    User username22n = new User("username22n", "123");
    User username21g = new User("username21g", "123");
    User username30 = new User("un30", "123");
    User username20n = new User("username20n", "123");


    //    String konu = "TS2178 20180205135705";
    String konu = "Evrak Devret ";
    String tur = "IMZALAMA";
    String icerik = "Test Otomasyon " + getSysDate();
    String konuKodu = "010.01";
    String kaldiralacakKlasor = "Diğer";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String gizlilikDerecesi = "Normal";
    String ivedilik = "Normal";
    String geregi = "Optiim Birim";
    String kullaniciNormal = "USERNAME20N TEST";
    String basariMesaji = "İşlem başarılıdır!";
    String uyariMesaji = "Zorunlu alanları doldurunuz";
    String dikkatMesaji = "Evrak seçilmemiştir!";
    String tabName = "İmza Bekleyen Evraklar";
    String nameDA = "Username20n TEST";
    String nameDE = "Username30 TEST";
    String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
    String devredecekKisi = "username30";
    String remoteDownloadPath;


    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , description = "TS2178a : İlgisi olan İşlem Bekleyen Cevap Evrakı Devretme ve Sonrasında Devralandan Silinmesi ve İlginin Kontrolü")
    public void TS2178a() throws InterruptedException {
        String yeniKonu=konu +getSysDate();

        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);

        gelenEvrak(yeniKonu);
        ilgisiOlanCevapEvrakiOluştur(yeniKonu);

//        imzaBekleyenEvrakOlustur(yeniKonu);

        logout();

        login(username30);

        String btnSilName = "Sil";
        String aciklamaSil = "Silme işlemi";
//        String konu ="Evrak Devret 20180301150342";

        System.out.println(yeniKonu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele();

        waitForLoadingJS(WebDriverRunner.getWebDriver(), 12000);

        kullaniciEvrakDevretPage
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, yeniKonu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam();

        waitForLoadingJS(WebDriverRunner.getWebDriver(), 12000);

//                .islemMesaji().basariliOlmali(basariMesaji);
        kullaniciEvrakDevretPage
                .tabloEvrakKontrolu(yeniKonu, false);

        login(username20n);

        taslakEvraklarPage
                .openPage()
                .evrakKontrolu(yeniKonu, true)
                .evrakSecKonuyaGore(yeniKonu)
                .evrakOnizlemeİlgiBilgileriTabAc()
                .evrakOnizlemeİlgiBilgileriTabloKontrolu(icerik)
//               .evrakOnizlemeveEkiKontrolu(icerik)
                .evrakOnizlemeButonTikla(btnSilName)
                .silAciklamaInputDolduur(aciklamaSil)
                .silSilGonder()
                .silmeOnayıPopUpEvet()
                .evrakKontrolu(yeniKonu, false);

        //gelen kutusu kontrolü ?
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            , dependsOnMethods = {"TS2178"}
            , description = "TS2179 : Devredilen evrakların devralan kullanıcıda hareket/evrak geçmişinin kontrolü")
    public void TS2179() throws InterruptedException {

        remoteDownloadPath = useChromeWindows151("TS2179");
String yeniKonu = konu + getSysDate();
        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);

        System.out.println(remoteDownloadPath);
        String mesaj = nameDE + kullaniciTitle + " ait evrak " + nameDA + kullaniciTitle + " adlı kişiye " + nameDE + kullaniciTitle
                + " tarafından Gelen Evraklar menüsünden devredilmiştir. / " + icerik;

        gelenEvrak(yeniKonu);

        evrakDevret("Gelen Evraklar");


        login(username20n);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(yeniKonu);

        evrakDetayiPage
                .sayfaAcilmali()
//                .evrakBilgileriTabAktifKontrolEt()
                .hareketGecmisiTabAc()
                .tabloKontol(mesaj)
                .raporAl(remoteDownloadPath)
                .evrakDetayiKapat();

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakOnizlemedeAc(yeniKonu)
                .evrakGecmisiTikla()
                .tabloKontol(mesaj)
                .raporAl(remoteDownloadPath);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0517 : Devredilen evrakların devredilen kullanıcıda kontrol edilmesi")
    public void TS0517() throws InterruptedException {
        String yeniKonu=konu +getSysDate();
        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);

        String aciklama = "Onay akışı güncelledi.";
        String btnName = "Kaydet ve Onaya Sun";
        String onayAkisi = "TS0517";
        String icerikGuncelle = "İçerik değiştirildi.";
        String tabEditörName = "Editör";

        System.out.println(remoteDownloadPath);

        imzaBekleyenEvrakOlustur(yeniKonu);

        evrakDevret("İmza Bekleyen Evraklar");

        login(username20n);

        taslakEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(yeniKonu);

        evrakDetayiPage
                .sayfaAcilmali()
                .evrakDetayEkraniTabSeçimKontrolu(tabEditörName)
                .editorTaAc()
                .editorIcerikDoldur(icerikGuncelle);

        evrakDetayiPage
                .bilgileriTabAc()
                .onayAkişGuncelle(onayAkisi);

        evrakDetayiPage
                .btnTikla(btnName)
                .kaydetVeOnayaSunAciklama(aciklama)
                .gonder()
                .kaydetVeOnayaSunUyariPopUpEvet()
                .islemMesaji().basariliOlmali(basariMesaji);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2178b : Havale Onayına Gelen evrakın devredilmesi")
    public void TS2178b() throws InterruptedException {
        String yeniKonu=konu +getSysDate();

        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);

        havaleOnayı(yeniKonu);
        logout();
        login(username30);

        String tabName = "Havale Onayına Gelen Evraklar";

        System.out.println(yeniKonu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, yeniKonu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username20n);

        gelenEvraklarPage
                .openPage()
//                .searchTable().findRows(Condition.text(konu)).shouldHaveSize(1);
                .tabloKonuyaGoreEvrakKontrol(yeniKonu, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0516 : İlgisi olan gelen evrakın devredilmesi.")
    public void TS0516() throws InterruptedException {

        String yeniKonu=konu +getSysDate();

        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);
        gelenEvrak(yeniKonu);
//        logout();
//        login(username21g);

        login(username30);
        String tabName = "Gelen Evraklar";

        System.out.println(yeniKonu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
//                .devredecekKisiSec("username30")

                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, yeniKonu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
//                .devralacakKisiSec("username20n")

                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);
        kullaniciEvrakDevretPage
                .tabloEvrakKontrolu(yeniKonu, false);

        logout();
        login(username20n);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrol(yeniKonu, true);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2183 : Evrak devretmede alan kontrolleri")
    public void TS2183() throws InterruptedException {
        String yeniKonu = konu + getSysDate();
        login(TestData.usernameOPTIIM,TestData.passwordOPTIIM);

        gelenEvrak(yeniKonu);
        logout();
        login(username30);

        String tabName = "Gelen Evraklar";

        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .listele()
                .islemMesaji().uyariOlmali(uyariMesaji);
        kullaniciEvrakDevretPage
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloAlanKontrolleri()
                .devret()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        kullaniciEvrakDevretPage
                .tabloEvrakSecimi(tabName, yeniKonu)
                .devret()
                .devralacakKisiAlanKontolu()
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kullaniciEvrakDevretPage
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaTemizle()
                .devretTamam()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kullaniciEvrakDevretPage
                .aciklamaDoldur(createRandomText(500))
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

//    @Test(enabled = true, description = "TS2183 : Evrak devretmede alan kontrolleri")
//    public void test() throws InterruptedException {
//        login(TestData.uservv);
//        String yeniKonu = konu + getSysDate();
//    gelenEvrak(yeniKonu);
//    }

    @Step("Test datası oluşturuldu. Gelen Evrak")
    private void gelenEvrak(String konu) throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String evrakSayiSag = createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String pathToFileText = getUploadPath() + "test.txt";

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
//                .havaleIslemleriKisiDoldur("Username21g")
                .havaleIslemleriKisiDoldur("Optiim Test")
//                .havaleIslemleriKisiDoldur("UnVV Test")
                .ilgiliBilgiFiltreAc()
                .ilgiBilgileriDosyaEkleme(pathToFileText)
                .ilgiBilgileriDosyaEkleEkMetinDoldur(icerik)
                .ilgiBilgileriTabViewEkle()
                .kaydet()
                .popUps();
    }

    @Step("Test datası oluşturuldu.")
    private void havaleOnayı(String konu) {
//        String konuKoduRandomTS2178b = "TC2178" + createRandomNumber(15);

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String evrakSayiSag = createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "OPTİİM";

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(kullaniciAdi,"YGD")
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt();
        birimeIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .teslimAlVeHavaleEt()
                .onaylanacakKisiDoldur("Username30 Test", "Optiim")
                .havaleOnayinaGonder();
    }

    @Step("Test datası oluşturuldu.")
    private void evrakDevret(String tabName) {

//        imzaBekleyenEvrakOlustur();
        login(username30);


        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloEvrakSecimi(tabName, konu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Step("Test datası oluşturuldu.")
    private void imzaBekleyenEvrakOlustur(String konu) {
        String imzacı = "username30";
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("Diğer") //kaldırılacakKlasör
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .ivedilikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullanicilarDoldur(imzacı)
                .kullniciIsmineGoreImzaParafSec(imzacı, tur)
                .kullan();

        evrakOlusturPage
                .ilgileriTabAc()
                .ilgileriMetinEkleTabAc()
                .ilgileriMetinEkleIlgiMetniDoldur(icerik)
                .ilgileriMetinEkleEkle();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakParafla();

    }
    @Step("Test datası oluşturuldu. İlgisi Olan Cevap Evraki")
    private void ilgisiOlanCevapEvrakiOluştur(String konu) {

        String onayAkisi = "TS2178A";
        CevapYazPage cevapYazPage = new CevapYazPage();

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .cevapYaz();
        cevapYazPage.sayfaAcilmali()
                .kaldirilacakKlasorlerDoldur("Diğer")
                .onayAkisiDoldur(onayAkisi)
                .editorTabOpen()
                .editorIcerikDoldur(icerik)
                .ilgileriTabiAc()
                .ilgileriMetinEkleTabAc()
                .ilgileriMetinEkleIlgiMetniDoldur(icerik)
                .ilgileriMetinEkleEkle()
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur("İmza Bekleyenler.")
                .gonder()
                .confirmDialog().buttonClick("Evet");
    }
}
