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

import static com.codeborne.selenide.Condition.text;
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
    public void TC2069A() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069_" + createRandomNumber(12);
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
                .filtreleKuralAdiDoldur(kuralAdi,"Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .sil(kuralAdi,"Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().beklenenMesaj(uyariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2070: Evrak Havale Kuralları - Kural Silme")
    public void TS2070A() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Evrak Bilgilerine Tanımlanmış Otomatik Havale Kuralı Bulunamamıştır.";
        String kuralAdi = "TC-2069_" + createRandomNumber(12);
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
                .filtreleKuralAdiDoldur(kuralAdi,"Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .kopyala(kuralAdi,"Konu");

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
    @Test(enabled = true, description = "2069: Evrak Havale Kuralları Sorgulama ve Filtreleme")
    public void TC2069B() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String durumSadecePasifler = "PASIFLER";
        String durumSadeceAktifler = "AKTIFLER";
        String geldigiYerKullanici = "K";
        String kullaniciKural = "BarisTest";
        String geldigiYerBirim = "Birim";
        String geldigiYerGercekKisi = "Gerçek Kişi";
        String geldigiYerTuzelKisi = "Tüzel Kişi";
        String geldigiYerKurum = "Kurum";
        String kuralAdi = "";
        String kullanici = "Zübeyde Tekin";

        login(username2, password2);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .durumSec(durumSadeceAktifler)
                .ara()
                .durumSec(durumSadecePasifler)
                .ara()
                .geldigiYerTipiSec(geldigiYerKullanici)
                .ara()
                .kullaniciDoldur(kullanici)
                .ara()
                .geldigiYerTipiSec(geldigiYerBirim)
                .ara();
                /*//
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerGercekKisi)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerTuzelKisi)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .geldigiYerTipiSec(geldigiYerKurum)
                .ara()
                //
                // TODO gelecek sistem yavaş
                //
                .birimDoldur("birim")
                .ara()
                .altBirimDahilSec(true)
                .kuralAdiDoldur("birim")
                .islemMesaji().basariliOlmali(basariMesaji);*/

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
                .filtreleKuralAdiDoldur(kuralAdi,"Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .pasifYap(kuralAdi,"Konu")
                .islemOnayiEvet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().beklenenMesaj(uyariMesaji);

        evrakHavaleKurallariYonetimiPage
                .openPage()
                .filtreleKuralAdiDoldur(kuralAdi,"Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .aktifYap(kuralAdi,"Konu")
                .islemOnayiEvet();

        gelenEvrakKayitPage
                .openPage()
                .otomatikHavaleSec();

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil(kuralAdi,"Konu")
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
                .filtreleKuralAdiDoldur(kuralAdi , "Kural adı");
        //TODO

        evrakHavaleKurallariYonetimiPage
                .ara()
                .havaleKurallariListesiGuncelle(kuralAdi,"Konu")
                .kuralAdiDoldur(kuralAdi+" Güncelle")
                .kuralEklemeKaydet();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .otomatikHavaleSec()
                .islemMesaji().beklenenMesaj(uyariMesaji);

        gelenEvrakKayitPage
                .openPage()
                .otomatikHavaleSec();

        //Test bittikten sonra datamızı siliyoruz. Bir sonraki koşumda hata almamamız için.
        evrakHavaleKurallariYonetimiPage
                .openPage()
                .ara()
                .sil(kuralAdi,"Konu")
                .islemOnayiEvet()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

}
