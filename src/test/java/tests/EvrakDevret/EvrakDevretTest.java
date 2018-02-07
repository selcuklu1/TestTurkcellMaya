package tests.EvrakDevret;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.TaslakEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
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

    User mbozdemir = new User("mbozdemir", "123");
    User username22n = new User("username22n", "123");
    User username21g = new User("username21g", "123");


    //    String konu = "TS2178 20180205135705";
    String konu = "TS2178 " + getSysDate();
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
    String remoteDownloadPath = getDownloadPath();

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2178 : İlgisi olan İşlem Bekleyen Cevap Evrakı Devretme ve Sonrasında Devralandan Silinmesi ve İlginin Kontrolü")
    public void TS2178() throws InterruptedException {

        login(mbozdemir);
        evrakOlustur();
        logout();
        login(username21g);

        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec("username21g")
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
                .evrakKontrolu(konu,true)
                .evrakSecKonuyaGore(konu)
                .evrakOnizlemeveEkiKontrolu(icerik)
                .evrakOnizlemeButonTikla("Sil")
                .silAciklamaInputDolduur("Silme işlemi")
                .silSilGonder()
                .silmeOnayıPopUpEvet()
                .evrakKontrolu(konu,false);

        //gelen kutusu kontrolü ?
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS2178"}
            , description = "TS2179 : Devredilen evrakların devralan kullanıcıda hareket/evrak geçmişinin kontrolü")
    public void TS2179() throws InterruptedException {
        login(username22n);

        String mesaj = nameDE + kullaniciTitle + " ait evrak " + nameDA + kullaniciTitle + " adlı kişiye " + nameDE + kullaniciTitle
                + " tarafından İmza Bekleyenler menüsünden devredilmiştir. / " + icerik;
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
                .tabloKontol(mesaj);


        //1. adımda gelen evraklara evrak düşmedi...
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0574 : Devredilen evrakların devredilen kullanıcıda kontrol edilmesi")
    public void TS0574() throws InterruptedException {

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
