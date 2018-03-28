package tests.KararYazisiOlusturma;
/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Karar Yazısı Oluşturma" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

import com.codeborne.selenide.Selenide;
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
    @Test(enabled = true, description = "TS1488: Karar yazısında zorunlu alan kontrolleri")
    public void TS1488() {

        String uyariMesajYaziIcerik = "Yazı içeriği boş olamaz!";
        String uyariMesajZorunlu = "Zorunlu alanları doldurunuz";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String konuKoduRandom = "TS-1488-" + createRandomNumber(15);
        String ivedi = "İvedi";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(6);
        String toplantiTarih = getSysDateForKis();
        String kararNo = createRandomNumber(13);
        String aciklama = "Deneme amaçlıdır";
        String editorIcerik = "Deneme Can";
        String kullanici = "Optiim";
        String onayAkisi = "canparafci";
        String imzalama = "İmzalama";

        login(usernameZTEKIN, passwordZTEKIN);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .ivedilikSec(ivedi)
                .onayAkisiEkle()
                .onayAkisiEkleKullaniciGeldigiGorme()
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
                .kullanicilarAlaniSecilenParafciSecilmedigiGorme()
                .onayAkisiDoldur(onayAkisi)
                .imzalamaKontrol(imzalama);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2240: Teslim alınmayı bekleyenler listesinden Gündem klasörüne evrak kapatma")
    public void TS2240() throws InterruptedException {
        String downloadPath = useChromeWindows151("TS2240");
        String basariMesaji = "İşlem başarılıdır!";
        String kaldirilicakKlasor = "Gündem";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-2240_" + createRandomNumber(25);
        String evrakTarihi = getSysDateForKis();
        String evrakSagSayi = createRandomNumber(7);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";

        login(usernameYAKYOL, passwordYAKYOL);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSagSayi)
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .benzerKayit();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeKapat()
                .teslimAlVeKapatAlanGeldigiGorme()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasor)
                //.konuKoduDoldur(konuKodu)
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
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd(downloadPath);
        System.out.println(dosyaAdi);
        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();

        Selenide.close();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2239: Gündem yayınlama")
    public void TS2239() {
        String downloadPath = useChromeWindows151("TS2239");
        String basariMesaji = "İşlem başarılıdır!";
        String kaldirilicakKlasor = "Gündem";
        String path = getDownloadPath();

        login(usernameYAKYOL, passwordYAKYOL);

        gundemIzlemePage
                .openPage()
                .kapatilanKlasorSec(kaldirilicakKlasor)
                .siralamayiDegistir()
                .gundemSirasiniKaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gundemIzlemePage
                .aralikliGundemOlustur()
                .islemMesaji().basariliOlmali(basariMesaji);
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd(downloadPath);

        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();

        Selenide.close();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1497: Karar Yazısı oluşturulması")
    public void TS1497() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String konuKoduRandom = "TS-1497-" + createRandomNumber(15);
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
        String miat = getSysDateForKis();
        String kararNo = createRandomNumber(12);
        String editorIcerik = "Deneme Can";
        String kullanici = "Yasemin Çakıl AKYOL";
        String kullanici2 = "Zübeyde Tekin";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";
        String filePath = getUploadPath() + "Otomasyon.pdf";
        String not = createRandomText(12);
        String birim = "Altyapı ve Sistem Yönetim Uzmanı";

        login(usernameZTEKIN, passwordZTEKIN);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
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
                .sistemdeKayitliEvrakEkleEvrakAramaDoldur("9")
                .sistemdeKayitliEvrakEkleEvrakDokumanAra()
                .sistemdeKayitliEvrakEkleArti()
                .tercumeEkleTabAc()
                .tercumeEkleDosyaEkle(filePath)
                .tercumeEkleIliskiMetniDoldur(not)
                .tercumeEkleEkle();

        kararYazisiOlusturPage
                .editorTabAc()
                .editorNoAlanıGeldigiGorme(toplantiNo, toplantiTarih, kararNo)
                .imzaciGeldigiGorme(kullanici2)
                .ilgiGeldigiGorme(not)
                .editorIcerikDoldur(not)
                .kaydetveOnaySun()
                .kaydetVeOnaySunAciklamaDoldur(not)
                .gonder(true);

        kararIzlemePage
                .openPage()
                .evrakGeldigiGorme(toplantiNo, konuKoduRandom, toplantiTarih);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(kararNo, konuKoduRandom)
                .imzala()
                .sImzaSec()
                .sImzaİmzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPage
                .openPage()
                .evrakGeldigiGorme(toplantiNo, konuKoduRandom, toplantiTarih);

        kararIzlemePage
                .openPage()
                .filtreler()
                .evrakDurumuSec("Y")
                .filtrele()
                .evrakGeldigiGorme(toplantiNo, konuKoduRandom, toplantiTarih);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilicakKlasorler)
                .ara()
                .evrakGeldigiGorme(toplantiNo, konuKoduRandom);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2232: Karar izleme ekranının toplu onaya sunma")
    public void TS2232() {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String konuKoduRandom = "TS-2232-" + createRandomNumber(15);
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
        String kararNo = createRandomNumber(13);
        String editorIcerik = "Deneme Can";
        String kullanici = "Zübeyde TEKİN";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";

        login(usernameZTEKIN, passwordZTEKIN);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
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
                .kaydet()
                .evrakiKaydetmekIstediginizGeldigiGorme()
                .evrakiKaydetmekIsterMisinizEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kararIzlemePage
                .openPage()
                .evrakSec(kararNo, konuKoduRandom, toplantiTarih)
                .topluOnayaSun(true);

        imzaBekleyenlerPage
                .openPage()
                .dogruKonuVeNoKontrol(kararNo, konuKoduRandom);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1714: Karar yazsının iadesi")
    public void TS1714() {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Usul ve Esaslar";
        String konuKoduRandom = "TS-1714" + createRandomNumber(15);
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = createRandomNumber(9);
        String toplantiTarih = getSysDateForKis();
        String miat = getSysDateForKis();
        String kararNo = createRandomNumber(12);
        String editorIcerik = "Deneme Can";
        String kullanici = "Yasemin Çakıl AKYOL";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";
        String filePath = getUploadPath() + "Otomasyon.pdf";
        String not = createRandomText(12);
        String birim = "Altyapı ve Sistem Yönetim Uzmanı";
        login(usernameZTEKIN, passwordZTEKIN);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
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


        login(usernameYAKYOL, passwordYAKYOL);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(kararNo, konuKoduRandom)
                .iadeEt()
                .iadeEtDosyaEkle(filePath)
                .notDoldur(not)
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameZTEKIN, passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(kararNo, konuKoduRandom, true);

        kararIzlemePage
                .openPage()
                .ilkEvrakSec(kararNo, konuKoduRandom)
                .iadeBilgisiGorme(not);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2238: Gündem klasörü oluşturma")
    public void TS2238() {

        String basariMesaji = "İşlem başarılıdır!";
        String birim = "Yazılım Geliştirme Direktörlüğ";
        String kapanisTarih = getAfterSysYear();
        String acilisTarih = getSysDateForKis2();
        String klasorTuru = "Gündem Klasörü";
        String ad = "Zübeyde";

        login(usernameZTEKIN, passwordZTEKIN);

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
                .evrakKapatmaEkranGeldigiGorme()
                .evrakKapatKaldirilacakKlasorlerDoldur(klasorAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1715: Gelen evrak listesinden Gündem klasörüne evrak kapatma")
    public void TS1715() throws InterruptedException {
        String downloadPath = useChromeWindows151("TS1715");
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilicakKlasor = "Gündem";
        String konuKoduRandom = "TS-1715_" + createRandomNumber(25);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Zübeyde Tekin";
        String ustyazipath = getUploadPath()+"Otomasyon.pdf";
        login(usernameZTEKIN, passwordZTEKIN);

        gelenEvrakKayitPage
                .openPage()
                .ustYaziEkle(ustyazipath)
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriKisiDoldur(kullaniciAdi)
                .kaydet()
                .yeniKayitButton();

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .evrakKapat()
                .evrakKapatmaEkranGeldigiGorme()
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
        String dosyaAdi = gundemIzlemePage.indirilenDosyaAd(downloadPath);

        gundemIzlemePage
                .wordDosyaKontrolEt(dosyaAdi)
                .yayimla();

        erisimYonetimiPage
                .openPage()
                .kullaniciErisimTab()
                .kullanıcıErisimAra();

        Selenide.close();
    }


}

