/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakHavaleKurallari;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakHavaleKurallariYonetimiPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.password2;
import static data.TestData.username2;

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
@Epic("Evrak Havale Kuralları")
public class EvrakHavaleKurallariTest extends BaseTest {

    EvrakHavaleKurallariYonetimiPage evrakHavaleKurallariYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakHavaleKurallariYonetimiPage = new EvrakHavaleKurallariYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2069: Evrak Havale Kuralları - Kural Silme")
    public void TS2069A() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TS-2069_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Genelge";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";
        login(username2, password2);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSecGenelge()
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().dikkatOlmali(uyariMesaji);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2068: Evrak Havale Kuralları - Kural Silme")
    public void TS2068() {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Aynı ada sahip başka havale kuralı vardır.";
        String kuralAdi = "TC-2068_" + createRandomNumber(12);
        String kuralAdi2 = "TC-2068_2_" + createRandomNumber(12);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";
        String konu = "K/Frekans Yıllık Kullanım Ücreti";
        String kullaniciListesi = "Can";
        String kullanici = "Can Şeker";
        String aciklama = "Deneme amaçlıdır";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        login(username2, password2);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSecDilekce()
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();
        //TODO

        evrakHavaleKurallariYonetimiPage
                .yeniKural()
                .kuralAdiDoldur(kuralAdi)
                .evrakTuruSecDilekce()
//                .evrakDiliTurkceSec()
                .konuKoduDoldur(konu)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .geldigiYerBirimDoldur(birim2)
                .geldigiYerKullaniciDoldur(kullanici)
                .geldigiYerKurumDoldur(kurum)
                .geldigiYerGercekKisiDoldur(kisi)
                .kimeHavaleEdilecekBirimDoldur(birim2)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kimeHavaleEdilecekKullaniciListesiDoldur(kullaniciListesi)
                .kimeHavaleEdilecekAciklamaDoldur(aciklama)
                .kuralEklemeKaydet()
                .islemMesaji().dikkatOlmali(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .kuralAdiDoldur(kuralAdi2)
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2070: Evrak Havale Kuralları - Kural Silme")
    public void TS2070A() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2070_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Beyanname";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";

        login("cseker", password2);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSecBeyanname()
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .kopyala(kuralAdi, "Konu");

        String kuralAd = evrakHavaleKurallariYonetimiPage.kuralAdiCek();

        evrakHavaleKurallariYonetimiPage
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .popupOtomatikHavaleSec(kuralAd);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları Sorgulama ve Filtreleme")
    public void TS2069B() {
        String basariMesaji = "İşlem başarılıdır!";
        String durumSadecePasifler = "Sadece Pasifler";
        String durumSadeceAktifler = "Sadece Aktifler";
        String geldigiYerKullanici = "Kullanıcı";
        String kullanici = "Zübeyde Tekin";
        String kullanici2 = "Can Şeker";
        String birim = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR YARDIMCISI";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String geldigiYerBirim = "Birim";
        String geldigiYerGercekKisi = "Gerçek Kişi";
        String geldigiYerTuzelKisi = "Tüzel Kişi";
        String geldigiYerKurum = "Kurum";
        String geldigiYerSeciniz = "Seçiniz";

        login(username2, password2);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .durumSec(durumSadecePasifler)
                .ara()
                .durumSec(durumSadeceAktifler)
                .ara()
                .geldigiYerTipiSec(geldigiYerKullanici)
                .ara()
                .kullaniciDoldur(kullanici)
                .ara()
                .geldigiYerTipiSec(geldigiYerBirim)
                .ara()
                .geldigiYerBirimDoldur(birim)
                .ara()
                .geldigiYerTipiSec(geldigiYerGercekKisi)
                .ara()
                .geldigiYerGercekKisiDoldur(kullanici)
                .ara()
                .geldigiYerTipiSec(geldigiYerTuzelKisi)
                .ara()
                .geldigiYerTuzelKisiDoldur(kullanici2)
                .ara()
                .geldigiYerTipiSec(geldigiYerKurum)
                .ara()
                .geldigiYerKurumDoldur(kurum)
                .ara()
                .birimDoldur(birim)
                .ara()
                .altBirimDahilSec(true)
                .ara()
                .kuralAdiDoldur(kullanici2)
                .geldigiYerTipiSec(geldigiYerSeciniz)
                .ara();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları - Kuralları aktif / pasif yapma")
    public void TS2069C() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069C_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Diğer";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";

        login("cseker", password2);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(5)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .pasifYap(kuralAdi, "Konu")
                .islemOnayiEvet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().beklenenMesaj(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .aktifYap(kuralAdi, "Konu")
                .islemOnayiEvet();

        gelenEvrakKayitPage
                .openPage()
                .otomatikHavaleSec();

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2070: Evrak Havale Kuralları - Kuralları aktif / pasif yapma")
    public void TS2070B() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069C_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Tebrik,Davetiye vb.";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";
        String kuralAdiGuncelle = kuralAdi + " Güncelle";
        login("cseker", password2);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(4)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .havaleKurallariListesiGuncelle(kuralAdi, "Konu")
                .kuralAdiDoldur(kuralAdiGuncelle)
                .kuralEklemeKaydet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .otomatikHavaleGeldigiGorme(kuralAdiGuncelle);

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları - Birim Kaydet - Güncelleme")
    public void TS2069D() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069D_" + createRandomNumber(12);
        String kuralAdi2 = "TC-2069D_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakTuru2 = "Tebrik,Davetiye vb.";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR";
        String sadecePasifler = "Sadece Pasifler";
        String sadeceAktifler = "Sadece Aktifler";

        login("cseker", password2);

        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(0)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim2)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi,"Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .havaleKurallariListesiGuncelle(kuralAdi,"")
                .ilkPasifYap()
                .islemOnayiEvet();

        evrakHavaleKurallariYonetimiPage
                .kuralinTanimliOlduguBirimlerSec(sadecePasifler)
                .kuralEklemeKaydet();
                //.islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().dikkatOlmali(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .pasifYapilanKullaniciGuncelle(kuralAdi)
                .kuralinTanimliOlduguBirimlerSec(sadecePasifler)
                .ilkAktifYap()
                .islemOnayiEvet()
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .otomatikHavaleGeldigiGorme(kuralAdi);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(4)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleAltBirimlerDahil(true)
                .birimEkleBirimDoldur(birim)
                .birimEkleAltBirimEkle(true);

            String isim = evrakHavaleKurallariYonetimiPage.birimEkleAltBirimIlkAdCek();

            evrakHavaleKurallariYonetimiPage
                .birimEkleEkle()
                .yeniBirimSil(isim)
                .islemOnayiEvet()
                .kuralAdiDoldur(kuralAdi2)
                .kuralEklemeKaydet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru2)
                .otomatikHavaleSec()
                .otomatikHavaleGeldigiGorme(kuralAdi2);

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
     /*   evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil(kuralAdi,"Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);*/
    }
}
