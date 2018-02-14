package tests.EvrakDevret;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;
import tests.BirimeIade.BirimeIadeEdilenlerdenHavaleTest;


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
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;

    User mbozdemir = new User("mbozdemir", "123");
    User username22n = new User("username22n", "123");
    User username21g = new User("username21g", "123");


    //    String konu = "TS2178 20180205135705";
    String konu = "Evrak Devret " + getSysDate();
    String tur = "IMZALAMA";
    String icerik = "Test Otomasyon " + getSysDate();
    String konuKodu = "010.01";
    String kaldiralacakKlasor = "Diğer";
    String evrakTuru = "Resmi Yazışma";
    String evrakDili = "Türkçe";
    String gizlilikDerecesi = "Normal";
    String ivedilik = "Normal";
    String geregi = "Optiim Birim";
    String kullaniciNormal = "USERNAME22N TEST";
    String basariMesaji = "İşlem başarılıdır!";
    String tabName = "İmza Bekleyen Evraklar";
    String nameDA = "Username22N TEST";
    String nameDE = "Username21G TEST";
    String kullaniciTitle = " [Ağ (Network) Uzman Yardımcısı]";
    String devredecekKisi = "username21g";
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
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2178a : İlgisi olan İşlem Bekleyen Cevap Evrakı Devretme ve Sonrasında Devralandan Silinmesi ve İlginin Kontrolü")
    public void TS2178a() throws InterruptedException {

        login(mbozdemir);
        evrakOlustur();
        logout();
        login(username21g);

        String btnSilName = "Sil";
        String aciklamaSil = "Silme işlemi";

        System.out.println(konu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, konu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);
        kullaniciEvrakDevretPage
                .tabloEvrakKontrolu(konu, false);

        login(username22n);

        taslakEvraklarPage
                .openPage()
                .evrakKontrolu(konu, true)
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeveEkiKontrolu(icerik)
                .evrakOnizlemeButonTikla(btnSilName)
                .silAciklamaInputDolduur(aciklamaSil)
                .silSilGonder()
                .silmeOnayıPopUpEvet()
                .evrakKontrolu(konu, false);

        //gelen kutusu kontrolü ?
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            , dependsOnMethods = {"TS2178"}
            , description = "TS2179 : Devredilen evrakların devralan kullanıcıda hareket/evrak geçmişinin kontrolü")
    public void TS2179() throws InterruptedException {
        remoteDownloadPath = useChromeWindows151("TS2179");
        login(mbozdemir);

        System.out.println(remoteDownloadPath);
        String mesaj = nameDE + kullaniciTitle + " ait evrak " + nameDA + kullaniciTitle + " adlı kişiye " + nameDE + kullaniciTitle
                + " tarafından İmza Bekleyenler menüsünden devredilmiştir. / " + icerik;

        evrakDevret();

        login(username22n);

        taslakEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
//                .evrakBilgileriTabAktifKontrolEt()
                .hareketGecmisiTabAc()
                .tabloKontol(mesaj)
                .raporAl(remoteDownloadPath)
                .evrakDetayiKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSecKonuyaGore(konu)
                .evrakGecmisiTikla()
                .tabloKontol(mesaj)
                .raporAl(remoteDownloadPath);


        //Evrak Gelen Evraklara düşmediğinden Taslak Evraklara göre yazıldı. Cevap bekleniyor.....
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0517 : Devredilen evrakların devredilen kullanıcıda kontrol edilmesi")
    public void TS0517() throws InterruptedException {

        login(mbozdemir);

        String aciklama = "Onay akışı güncelledi.";
        String btnName = "Kaydet ve Onaya Sun";
        String onayAkisi = "TC0574";
        String icerikGuncelle = "İçerik değiştirildi.";
        String tabEditörName = "Editör";

        System.out.println(remoteDownloadPath);
        evrakDevret();

        login(username22n);

        taslakEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konu);

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
    public void TS2178b() throws InterruptedException{

        login(mbozdemir);
        havaleOnayı();
        logout();
        login(username21g);

        String tabName = "Havale Onayına Gelen Evraklar";

        System.out.println(konu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, konu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username22n);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrol(konu,true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0516 : İlgisi olan gelen evrakın devredilmesi.")
    public void TS0516() throws InterruptedException{

        login(mbozdemir);
        gelenEvrak();
        logout();
        login(username21g);

        String tabName = "Gelen Evraklar";

        System.out.println(konu);
        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec(devredecekKisi)
                .listele()
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(tabName, konu)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(icerik)
                .devretTamam()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username22n);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrol(konu,true);

    }

    @Step("Test datası oluşturuldu.")
    private void gelenEvrak() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String evrakSayiSag =  createRandomNumber(10);
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
                .havaleIslemleriKisiDoldur("Username21g")
                .ilgiliBilgiFiltreAc()
                .ilgiBilgileriDosyaEkleme(pathToFileText)
                .ilgiBilgileriDosyaEkleEkMetinDoldur(icerik)
                .ilgiBilgileriTabViewEkle()
                .kaydet()
                .popUps();
    }

    @Step("Test datası oluşturuldu.")
    private void havaleOnayı() {
//        String konuKoduRandomTS2178b = "TC2178" + createRandomNumber(15);

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String evrakSayiSag =  createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .iadeEt()
                .iadeEtIadeEt();
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konu)
                .teslimAlVeHavaleEt()
                .onaylanacakKisiDoldur("Username21g Test","YGD")
                .havaleOnayinaGonder();
    }

    @Step("Test datası oluşturuldu.")
    private void evrakDevret() {

        evrakOlustur();
        login(username21g);


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
    private void evrakOlustur() {
        String imzacı = "username21g";
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("gündem") //kaldırılacakKlasör
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
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakParafla();

    }
}
