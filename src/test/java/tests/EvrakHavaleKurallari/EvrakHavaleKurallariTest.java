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

import static com.codeborne.selenide.Selenide.sleep;
import static data.TestData.passwordZTEKIN;
import static data.TestData.userCanSeker;
import static data.TestData.usernameZTEKIN;

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
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları - Kural Silme")
    public void TS2069a() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TS-2069_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Genelge";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "YGD";
        login(usernameZTEKIN, passwordZTEKIN);
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
                .havaleKurallariListesiGorme()
                .sil(kuralAdi, "Konu")
                .havaleKuraliniSilmekIstediginizeEminMisinizGeldigiGorme()
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().isDikkat(uyariMesaji);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2068: Evrak Havale Kuralları - Yeni Kural Ekleme")
    public void TS2068() {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Aynı ada sahip başka havale kuralı vardır.";
        String kuralAdi = "TC-2068_" + createRandomNumber(12);
        String kuralAdi2 = "TC-2068_2_" + createRandomNumber(12);
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "YGD";
        String konu = "K/Frekans Yıllık Kullanım Ücreti";
        String kullaniciListesi = "Can";
        String kullanici = "Can Şeker";
        String aciklama = "Deneme amaçlıdır";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        login(usernameZTEKIN, passwordZTEKIN);

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
                .evrakDiliTurkceSec()
                .konuKoduDoldur(konu)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
               // .geldigiYerTipiSec("Birim")
                .geldigiYerBirimDoldur(birim)
                .geldigiYerKullaniciDoldur(kullanici)
                .geldigiYerKurumDoldur(kurum)
                .geldigiYerGercekKisiDoldur(kisi)
                .kimeHavaleEdilecekBirimDoldur(birim)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kimeHavaleEdilecekKullaniciListesiDoldur(kullaniciListesi)
                .kimeHavaleEdilecekAciklamaDoldur(aciklama)
                .kuralEklemeKaydet()
                .islemMesaji().dikkatOlmali(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .kuralAdiDoldur(kuralAdi2)
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı")
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi2, "Kural adı")
                .ara()
                .sil(kuralAdi2, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2070: Evrak Havale Kuralları - Kural Kopyalama")
    public void TS2070a() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TS-2070_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Beyanname";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "YGD";

        login("cseker", passwordZTEKIN);
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
                .havaleKurallariListesiGorme()
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

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı")
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAd, "Kural adı")
                .ara()
                .sil(kuralAd, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları Sorgulama ve Filtreleme")
    public void TS2069b() {
        String basariMesaji = "İşlem başarılıdır!";
        String durumSadecePasifler = "Sadece Pasifler";
        String durumSadeceAktifler = "Sadece Aktifler";
        String geldigiYerKullanici = "Kullanıcı";
        String kullanici = "Zübeyde Tekin";
        String kullanici2 = "Can Şeker";
        String kuralAdi = "TC-2069 Sakın Silme";
        String birim = "Yazılım Geliştirme Direktörlüğü";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String geldigiYerBirim = "Birim";
        String geldigiYerGercekKisi = "Gerçek Kişi";
        String geldigiYerTuzelKisi = "Tüzel Kişi";
        String geldigiYerKurum = "Kurum";
        String geldigiYerSeciniz = "Seçiniz";

        login(usernameZTEKIN, passwordZTEKIN);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .kurallarListelendigiGorme("Bütün")
                .durumSec(durumSadecePasifler)
                .ara()
                .kurallarListelendigiGorme("Sadece pasif")
                .durumSec(durumSadeceAktifler)
                .ara()
                .kurallarListelendigiGorme("Sadece aktif")
                .geldigiYerTipiSec(geldigiYerKullanici)
                .ara()
                .kurallarListelendigiGorme("Geldiği yer bilgisinde Kullanıcı içeren bütün")
                .kullaniciDoldur(kullanici2)
                .ara()
                .kurallarListelendigiGorme("Bilgisi girilen kullanıcıyı içeren")
                .geldigiYerTipiSec(geldigiYerBirim)
                .ara()
                .kurallarListelendigiGorme("Geldiği yer bilgisinde Birim içeren bütün")
                .filtreleGeldigiYerBirimDoldur(birim)
                .ara()
                .kurallarListelendigiGorme("Bilgisi girilen Birimi içeren")
                .geldigiYerTipiSec(geldigiYerGercekKisi)
                .ara()
                .kurallarListelendigiGorme("Geldiği yer bilgisinde Gerek Kişi içeren bütün")
                .filtreleGeldigiYerGercekKisiDoldur(kullanici)
                .ara()
                .kurallarListelendigiGorme("Bilgisi girilen Gerçek kişiyi içeren")
                .geldigiYerTipiSec(geldigiYerTuzelKisi)
                .ara()
                .kurallarListelendigiGorme("Geldiği yer bilgisinde Tüzel Kişi içeren bütün")
                .filtreleGeldigiYerTuzelKisiDoldur(kullanici2)
                .ara()
                .kurallarListelendigiGorme("Bilgisi girilen Tüzel Kişiyi içeren")
                .geldigiYerTipiSec(geldigiYerKurum)
                .ara()
                .kurallarListelendigiGorme("Geldiği yer bilgisinde Kurum içeren bütün")
                .filtreleGeldigiYerKurumDoldur(kurum)
                .ara()
                .kurallarListelendigiGorme("Bilgisi girilen kurumu içeren")
                .birimDoldur(birim)
                .ara()
                .kurallarListelendigiGorme("Seçilen birim")
                .altBirimDahilSec(true)
                .ara()
                .kurallarListelendigiGorme("Seçilen birim altbirimlerinde tanımlı")
                .geldigiYerTipiSec(geldigiYerSeciniz)
                .filtrelemeKuralAdiDoldur(kuralAdi)
                .ara()
                .kurallarListelendigiGorme("Girilen kural adındaki");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2069: Evrak Havale Kuralları - Kuralları aktif / pasif yapma")
    public void TS2069c() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069C_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Diğer";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "BHUPGMY";

        login(userCanSeker);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(5, "Diğer")
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
                .havaleKurallariListesiGorme()
                .pasifYap(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().isDikkat(uyariMesaji);

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
    @Test(enabled = true, description = "TS2070: Evrak Havale Kuralları - Kural Güncelleme")
    public void TS2070b() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069C_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Tebrik,Davetiye vb.";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "YGD";
        String kuralAdiGuncelle = kuralAdi + " Güncelle";
        login(userCanSeker);
        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(4, "Tebrik,Davetiye vb.")
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
                .havaleKurallariListesiGorme()
                .havaleKurallariListesiGuncelle(kuralAdi, "Konu")
                .kuralAdiDoldur(kuralAdiGuncelle)
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

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
    public void TS2069d() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069D_" + createRandomNumber(12);
        String kuralAdi2 = "TC-2069D_" + createRandomNumber(12);
        String konuKodu = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakTuru2 = "Tebrik,Davetiye vb.";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String birim1 = "TESİSLER ALTYAPI VE SOSYAL HİZMETLER DİREKTÖRLÜĞÜ";
        String kisi = "Zübeyde Tekin";
        String birim2 = "YGD";
        String sadecePasifler = "Sadece Pasifler";
        String sadeceAktifler = "Sadece Aktifler";

        login("cseker", "123");

        //TODO PRE Conditon bir kural bulunmalı
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .yeniKural()
                .evrakTuruSec(0, "Resmi Yazı")
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim)
                .birimEkleEkle()
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleBirimDoldur(birim1)
                .birimEkleEkle()
                .kuralAdiDoldur(kuralAdi)
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralEklemeKaydet();

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .havaleKurallariListesiGorme()
                .havaleKurallariListesiGuncelle(kuralAdi, "Kural adı")
                .kuralGuncellemeEkraniGeldigiGorme()
                .ilkPasifYap()
                .islemOnayiEvet();

        sleep(10000);

        evrakHavaleKurallariYonetimiPage
                .kuralinTanimliOlduguBirimlerSec(sadecePasifler)
                .kuralEklemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().dikkatOlmali(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .havaleKurallariListesiGorme()
                .pasifYapilanKullaniciGuncelle(kuralAdi)
                .kuralGuncellemeEkraniGeldigiGorme()
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
                .evrakTuruSec(4, "Tebrik,Davetiye vb.")
                .kimeHavaleEdilecekKisiDoldur(kisi, birim2)
                .kuralinTanimliOlduguBirimlerYeni()
                .birimEkleAltBirimlerDahil(true)
                .birimEkleBirimDoldur(birim)
                .birimEkleAltBirimEkle(true);

        String isim = evrakHavaleKurallariYonetimiPage.birimEkleAltBirimIlkAdCek();

        evrakHavaleKurallariYonetimiPage
                .birimEkleEkle()
                .secilenBirimVeAltBirimlerinAtandigiGorulur(isim)
                .yeniBirimSil(isim)
                .birimIliskisiniSilmekIstemisinizUyariGeldigiGorme()
                .islemOnayiEvet()
                .kuralAdiDoldur(kuralAdi2)
                .kuralEklemeKaydet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru2)
                .otomatikHavaleSec2(false)
                .otomatikHavaleGeldigiGorme(kuralAdi2);

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .filtreleKuralAdiDoldur(kuralAdi, "Kural adı")
                .ara()
                .sil(kuralAdi, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakHavaleKurallariYonetimiPage
                .filtreleKuralAdiDoldur(kuralAdi2, "Kural adı")
                .ara()
                .sil(kuralAdi2, "Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
    }
}
