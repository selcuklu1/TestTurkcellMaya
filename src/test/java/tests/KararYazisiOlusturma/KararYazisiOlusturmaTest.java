package tests.KararYazisiOlusturma;
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
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

import static data.TestData.*;

@Epic("Kep ile Postalama İşlemleri")
public class KararYazisiOlusturmaTest extends BaseTest {

    KararYazisiOlusturPage kararYazisiOlusturPage;
    KararIzlemePage kararIzlemePage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    KlasorYonetimiPage klasorYonetimiPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    KurulIslemleriPage kurulIslemleriPage;
    GundemIzlemePage gundemIzlemePage;
    ErisimYonetimiPage erisimYonetimiPage;
    ImzaladiklarimPage imzaladiklarimPage;
    KlasorEvrakIslemleriPage klasorEvrakIslemleriPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    String klasorAdi = createRandomText(12);
    String klasorKodu = createRandomNumber(10);

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
        erisimYonetimiPage = new ErisimYonetimiPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1488: Karar yazısında zorunlu alan kontrolleri")
    public void TS1488() throws InterruptedException {

        String uyariMesajYaziIcerik = "Yazı içeriği boş olamaz!";
        String uyariMesajZorunlu = "Zorunlu alanları doldurunuz";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(6);
        String toplantiTarih = getSysDateForKis();
        String kararNo = createRandomNumber(13);
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
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .kararNoDoldur(kararNo)
                .konuKoduTemizle()
                .kaydetveOnaySun()
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur("")
                .kaydetveOnaySun()
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .kaldirilacakKlasorTemizle()
                .konuKoduDoldur(konuKodu)
                .kaydetveOnaySun()
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                .toplantiNoDoldur("")
                .kaydetveOnaySun()
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .toplantiNoDoldur(toplantiNo)
                .onayAkisiTemizle()
                .kaydetveOnaySun()
                .islemMesaji().uyariOlmali(uyariMesajZorunlu);

        kararYazisiOlusturPage
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .kullanicilarDoldur(kullanici, "")
                .onayAkisiDoldur(onayAkisi)
                .imzalamaKontrol(imzalama);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2240: Teslim alınmayı bekleyenler listesinden Gündem klasörüne evrak kapatma")
    public void TS2240() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String kaldirilicakKlasor = "Gündem";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-2240_"+createRandomNumber(25);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";

        login(username3, password3);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .benzerKayit()
                .yeniKayitButton();
        //TODO


        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeKapat()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasor)
                .konuKoduDoldur(konuKodu)
                .teslimAlveKapatTeslimAlVeKapat();

        gundemIzlemePage
                .openPage()
                .kapatilanKlasorSec(kaldirilicakKlasor)
                .siralamayiDegistir()
                .gundemSirasiniKaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gundemIzlemePage
                .aralikliGundemOlustur()
                .islemMesaji().basariliOlmali(basariMesaji);
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd();

        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2239: Gündem yayınlama")
    public void TS2239() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String kaldirilicakKlasor = "Gündem";

        login(username3, password3);

        gundemIzlemePage
                .openPage()
                .kapatilanKlasorSec(kaldirilicakKlasor)
                .siralamayiDegistir()
                .gundemSirasiniKaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gundemIzlemePage
                .aralikliGundemOlustur()
                .islemMesaji().basariliOlmali(basariMesaji);
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd();

        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1497: Karar Yazısı oluşturulması")
    public void TS1497() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
        String miat = getSysDateForKis();
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
                .kullan()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                .toplantiNoDoldur(toplantiNo)
                .toplantiTarihDoldur(toplantiTarih)
                .kararNoDoldur(kararNo);

        kararYazisiOlusturPage
                .ekleriTabAc()
                .dosyaEkleTabDosyaEkle(filePath)
                .dosyaEkleEkMetniDoldur(not)
                .dosyaEkleKaydet();

        kararYazisiOlusturPage
                .ilgileriTabAc()
                .metinEkleTab()
                .ilgiMetniDoldur(not)
                .metinEkleEkle();

        kararYazisiOlusturPage
                .iliskiliEvraklarTabAc()
                .sistemdeKayitliEvrakEkleTabAc()
                .sistemdeKayitliEvrakEkleEvrakAramaDoldur("4")
                .sistemdeKayitliEvrakEkleEvrakDokumanAra()
                .sistemdeKayitliEvrakEkleArti()
                .tercumeEkleTabAc()
                .tercumeEkleDosyaEkle(filePath)
                .tercumeEkleIliskiMetniDoldur(not)
                .tercumeEkleEkle();

        kararYazisiOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(not)
                .kaydetveOnaySun()
                .kaydetVeOnaySunAciklamaDoldur(not)
                .gonder(true);

        kararIzlemePage
                .openPage()
                .evrakGeldigiGorme(toplantiNo, konuKodu, toplantiTarih);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(kararNo, konuKodu)
                .imzala()
                .sImzaSec()
                .sImzaİmzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPage
                .openPage()
                .evrakGeldigiGorme(toplantiNo, konuKodu, toplantiTarih);

        kararIzlemePage
                .openPage()
                .filtreler()
                .evrakDurumuSec("Y")
                .filtrele()
                .evrakGeldigiGorme(toplantiNo, konuKodu, toplantiTarih);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilicakKlasorler)
                .ara()
                .evrakGeldigiGorme(toplantiNo, konuKodu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2232: Karar izleme ekranının toplu onaya sunma")
    public void TS2232() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
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
                .kullanicilarDoldur(kullanici, "")
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
                .evrakSec(kararNo, konuKodu, toplantiTarih)
                .topluOnayaSun(true);

        imzaBekleyenlerPage
                .openPage()
                .dogruKonuVeNoKontrol(kararNo, konuKodu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1714: Karar yazsının iadesi")
    public void TS1714() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
        String miat = getSysDateForKis();
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
                .evrakSec(kararNo, konuKodu)
                .iadeEt()
                .iadeEtDosyaEkle(filePath)
                .notDoldur(not)
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(kararNo, konuKodu, true);

        kararIzlemePage
                .openPage()
                .ilkEvrakSec(kararNo, konuKodu)
                .iadeBilgisiGorme(not);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2238: Gündem klasörü oluşturma")
    public void TS2238() throws InterruptedException {

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
    @Test(enabled = true, description = "1715: Gelen evrak listesinden Gündem klasörüne evrak kapatma")
    public void TS1715() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilicakKlasor = "Gündem";
        String konuKoduRandom = "TS-1715_"+createRandomNumber(25);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Zübeyde Tekin";
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriKisiDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
               .benzerKayit()
                .yeniKayitButton();

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur(kaldirilicakKlasor, kaldirilicakKlasor)
                .evrakKapatKonuKodu(konuKodu)
                .evrakKapatEvrakKapat();

        gundemIzlemePage
                .openPage()
                .kapatilanKlasorSec("gündem")
                .siralamayiDegistir()
                .gundemSirasiniKaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gundemIzlemePage
                .aralikliGundemOlustur()
                .islemMesaji().basariliOlmali(basariMesaji);
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd();

        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();

    }


}

