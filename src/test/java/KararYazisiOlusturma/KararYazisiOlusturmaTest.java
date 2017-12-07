package KararYazisiOlusturma;
/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Karar Yazısı Oluşturma" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

import static data.TestData.*;

@Epic("Kep ile Postalama İşlemleri")
public class KararYazisiOlusturmaTest extends BaseTest{

        KararYazisiOlusturPage kararYazisiOlusturPage;
        KararIzlemePage kararIzlemePage;
        ImzaBekleyenlerPage imzaBekleyenlerPage;
        KlasorYonetimiPage klasorYonetimiPage;
        TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
        GelenEvraklarPage gelenEvraklarPage;
        KurulIslemleriPage kurulIslemleriPage;
        GundemIzlemePage gundemIzlemePage;

        @BeforeMethod
        public void loginBeforeTests() {
            kararYazisiOlusturPage = new KararYazisiOlusturPage();
            kararIzlemePage = new KararIzlemePage();
            imzaBekleyenlerPage = new ImzaBekleyenlerPage();
            klasorYonetimiPage = new KlasorYonetimiPage();
            teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
            gelenEvraklarPage = new GelenEvraklarPage();
            kurulIslemleriPage = new KurulIslemleriPage();
            gundemIzlemePage = new GundemIzlemePage();
        }

        @Severity(SeverityLevel.CRITICAL)
        @Test(enabled = true, description = "1610: KEP Hesap Menüsü - Tanımlanan KEP hesapları ile login işlemleri")
        public void TC1610() throws InterruptedException {

            String uyariMesajYaziIcerik = "Yazı içeriği boş olamaz!";
            String uyariMesajZorunlu = "Zorunlu alanları doldurunuz";
            String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
            String kaldirilicakKlasorler = "Diğer";
            String toplantiNo = "123123";
            String toplantiTarih = "30.11.2017";
            String kararNo = "1231231231231231231";
            String aciklama = "Deneme amaçlıdır";
            String editorIcerik = "Deneme Can";
            String kullanici = "Optiim";
            String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
            String imzalama = "İmzalama";

            login(username2, password2);

            kararYazisiOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduDoldur(konuKodu)
                    .onayAkisiEkle()
                    .kullan()
                    .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                    .toplantiNoDoldur(toplantiNo)
                    .toplantiTarihDoldur(toplantiTarih)
                    .kararNoDoldur(kararNo)
                    .kaydetveOnaySun()
                    .aciklamaDoldur(aciklama)
                    .gonder(true)
                    .islemMesaji().beklenenMesaj(uyariMesajYaziIcerik);

            kararYazisiOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(editorIcerik);
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur(kararNo)
                    .konuKoduTemizle()
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .konuKoduDoldur(konuKodu)
                    .konuDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorTemizle()
                    .konuKoduDoldur(konuKodu)
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                    .toplantiNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .toplantiNoDoldur(toplantiNo)
                    .onayAkisiTemizle()
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .onayAkisiEkle()
                    .kullanicilarDoldur(kullanici,"")
                    .onayAkisiDoldur(onayAkisi)
                    .imzalamaKontrol(imzalama);



        }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1714: Karar yazsının iadesi")
    public void TC1714() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih =getSysDateForKis();
        String miat =getSysDateForKis();
        String kararNo = createRandomNumber(12);
        String editorIcerik = "Deneme Can";
        String kullanici = "Yasemin Çakıl AKYOL";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";
        String filePath = "C:\\TestAutomation\\BelgenetFTA\\documents\\Otomasyon.pdf";
        String not = createRandomText(12);
        String birim = "Altyapı ve Sistem Yönetim Uzmanı";
        login(username2, password2);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .ivedilikSec(ivedilik)
                .onayAkisiEkle()
                .kullanicilarDoldur(kullanici, birim)
                .kullan()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                .toplantiNoDoldur(toplantiNo)
                .toplantiTarihDoldur(toplantiTarih)
                .kararNoDoldur(kararNo);

        kararYazisiOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);


        // Yapılmadığı zaman çalışmamakta
       kararYazisiOlusturPage
                .bilgilerTabiAc()
                .miatDoldur(miat)
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);


        login(username3, password3);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(kararNo,konuKodu)
                .iadeEt()
                .iadeEtDosyaEkle(filePath)
                .notDoldur(not)
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(kararNo,konuKodu,true);

        kararIzlemePage
                .openPage()
              .ilkEvrakSec(kararNo,konuKodu)
              .iadeBilgisiGorme(not);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2232: Karar izleme ekranının toplu onaya sunma")
    public void TC2232() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih =getSysDateForKis();
        String kararNo = createRandomNumber(15);
        String editorIcerik = "Deneme Can";
        String kullanici = "Zübeyde TEKİN";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";

        login(username2, password2);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .ivedilikSec(ivedilik)
                .onayAkisiEkle()
                .kullanicilarDoldur(kullanici,"")
                .kullan()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                .toplantiNoDoldur(toplantiNo)
                .toplantiTarihDoldur(toplantiTarih)
                .kararNoDoldur(kararNo);

        kararYazisiOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        kararIzlemePage
                .openPage()
                .evrakSec(kararNo,konuKodu,toplantiTarih)
                .topluOnayaSun(true);

        imzaBekleyenlerPage
                .openPage()
                .dogruKonuVeNoKontrol(kararNo,konuKodu);


    }
    String klasorAdi = createRandomText(12);
    String klasorKodu = createRandomNumber(10);
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2238: Gündem klasörü oluşturma")
    public void TC2238() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String birim = "Yazılım Geliştirme Direktörlüğ";
        String kapanisTarih = getAfterSysYear();
        String acilisTarih = getSysDateForKis2();
        String klasorTuru = "Gündem Klasörü";
        String ad = "Zübeyde";

        login(username2, password2);

        klasorYonetimiPage
                .openPage()
                .yeni()
                .birimDoldur(birim)
                .klasorTuruSec(klasorTuru)
                .klasorAdiDoldur(klasorAdi)
                .klasorKoduDoldur(klasorKodu)
                .klasorAcilisTarihDoldur(acilisTarih)
                .klasorKapanisTarihiDoldur(kapanisTarih)
                .kullaniciYetkiListesiYetkiEkle()
                .ktxtKullaniciYetkiEklemeAdDoldur(ad)
                .kullaniciYetkiEklemeAra()
                .yetkiTanimlanabilicekSec(ad)
                .yetkiTanimlanabilicekSecKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        klasorYonetimiPage
                .klasorEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeKapat()
                .kaldirilacakKlasorlerDoldur(klasorAdi);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur(klasorAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods={"TC2238"}, description = "1715: Gelen evrak listesinden Gündem klasörüne evrak kapatma")
    public void TC1715() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String birim = "Yazılım Geliştirme Direktörlüğ";
        String kapanisTarih = getAfterSysYear();
        String acilisTarih = getSysDateForKis2();
        String klasorTuru = "Gündem Klasörü";
        String klasorAdi = createRandomText(12);
        String klasorKodu = createRandomNumber(10);
        String ad = "Zübeyde";
        String konuKodu = "Usul ve Esaslar";
        String kaldirilicakKlasor = "Gündem";

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur(kaldirilicakKlasor)
                .evrakKapatKonuKodu(konuKodu)
                .evrakKapatEvrakKapat();
        gundemIzlemePage
                .openPage();


    }
}

