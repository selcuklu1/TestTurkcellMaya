package tests.GelenEvrakiCevapliKapat;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.CevapYazPage;
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.*;
import pages.ustMenuPages.CevaplananEvrakRaporuPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KlasorEvrakIslemleriPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static data.TestData.*;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Gelen Evrakı Cevaplı Kapat" konulu senaryoları içerir
 * Yazan: Sezai Çelik, Emre Şencan, Can Şeker
 ****************************************************/
public class GelenEvrakiCevapliKapatTest extends BaseTest {

    CevaplananEvrakRaporuPage cevaplananEvrakRaporuPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakOlusturPage evrakOlusturPage;
    EvrakDetayiPage evrakDetayiPage;
    CevapYazPage cevapYazPage;
    KontrolBekleyenlerPage kontrolBekleyenlerPage;
    CevapladiklarimPage cevapladiklarimPage;
    KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage;
    KlasorEvrakIslemleriPage klasorEvrakIslemleriPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    TextEditor editor;


    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        cevaplananEvrakRaporuPage = new CevaplananEvrakRaporuPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakOlusturPage = new EvrakOlusturPage();
        kontrolBekleyenlerPage = new KontrolBekleyenlerPage();
        cevapladiklarimPage = new CevapladiklarimPage();
        cevapladiklarimPage = new CevapladiklarimPage();
        klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();
        klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        evrakDetayiPage = new EvrakDetayiPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        cevapYazPage = new CevapYazPage();
        editor = new TextEditor();
    }

    @Test(enabled = true, description = "TS930: Kurum içi gelen evraka cevap yaz")
    public void TS930() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-930-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiEkle()
                .onayAkisiEkleIlkImzalaSec2("İmzalama")
                .onayAkisiKullan2();

        evrakOlusturPage
                .cevapYazImzalama()
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kurum, evrakTarihi)
                .kontrolEt();

        login(username2, password2);

        cevapladiklarimPage
                .openPage();


    }

    @Test(enabled = true, description = "TS931: Gerçek kişiden gelen evraka cevap yaz")
    public void TS931() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-931-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiEkle2("asd");

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kurum, evrakTarihi)
                .kontrolEt();

        login(username2, password2);

        cevapladiklarimPage
                .openPage();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1368: Cevaplanan Evrak Raporu")
    public void TS1368() throws IOException, InterruptedException {

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String konuKodu = "Kanunlar";
        String ilkTarih = "17.11.2017";
        String sonTarih = "17.11.2017";

        String evrakTarihi = "17.11.2017";
        String evrakKayitTarihi = "17.11.2017";
        String evrakSayisi = "54354354-8794";
        String konu = "Kanunlar";
        String cevaplananEvrakKonuKodu = "Kanunlar";
        String cevaplananEvrakKonu = "Kanunlar";
        String cevaplananEvrakSayisi = "6345202-010.01-9075";
        String cevaplananEvrakTarihi = "17.11.2017";
        String basariMesaji = "İşlem başarılıdır!";
        String filePath = getDownloadPath();//"C:\\Users\\" + getPCUsername() + "\\Downloads\\";
        String fileName = "Rapor_";
        String fileName2 = "Rapor_.xls";

        cevaplananEvrakRaporuPage
                .openPage()
                .birimDoldur(birim)
                .altBirimEvraklariDahilSec(true)
                .konuKoduDoldur(konuKodu)
                .ilkTarihDoldur(ilkTarih)
                .sonTarihDoldur(sonTarih)
                .sorgula()
                .cevaplananEvrakKayitKontrolu(
                        evrakTarihi,
                        evrakKayitTarihi,
                        evrakSayisi,
                        konu,
                        cevaplananEvrakKonuKodu,
                        cevaplananEvrakKonu,
                        cevaplananEvrakSayisi,
                        cevaplananEvrakTarihi)
                .cevaplananEvrakGecmisiVeDetayKontrolu()
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .sayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .tumSayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .temizle()
                .temizleSonrasiKontrol();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0373: Cevap yazma işleminde evrakın onay akışından silinmesi")
    public void TS0373() throws InterruptedException {

        String onayAkisi = "Ts373 OnayAkışı";
        String tuzelKisi = "Ts373 TüzelKişi";
        String kisiKurum = "Tüzel Kişi";
        String kisi = "Optiim TEST";
        String konuKodu = "040"; //Faaliyet Raporları
        String konuKodu2 = "Faaliyet Raporları";
        String konu = "TS0373 " + getSysDate();
        String kayitTarihi = getSysDateForKis();
        String evrakSayiSol = createRandomNumber(10);
        String evrakSayiSag = createRandomNumber(4);
        String basariMesaji = "İşlem başarılıdır!";
        String evrakSayi = evrakSayiSol + "-" + evrakSayiSag;
        String kaldirilacakKlasorler = "ESK05";
        String aciklama = "Onay işlemi açıklamasıdır.";
        String evrakSilmeNotu = konu + " konulu evrak silinecektir.";

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText(kisiKurum)
                .geldigiTuzelKisiDoldur(tuzelKisi, "Ad")
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTarihiDoldur(kayitTarihi)
                .evrakSayiSolDoldur(evrakSayiSol)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();
        String kayitTarihiSayi = kayitTarihi + " / " + evrakNo;

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        login(username, password);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, tuzelKisi, kayitTarihiSayi, kayitTarihi, evrakSayi);

        evrakDetayiPage
                .sayfaAcilmali()
                .ikonKontrolleri()
                .cevapYaz();

        cevapYazPage
                .sayfaAcilmali();

        cevapYazPage
                .geregiKontrolu(tuzelKisi)
                .konuAlaniKontrolu(konuKodu2)
                .editorTabAc()
                .editorSayiTarihKontrolu(evrakSayi, kayitTarihi);

        editor
                .type("Bu bir text yazısıdır.");

        cevapYazPage
                .bilgilerTabAc()
                .kaldirilacakKlasorlerDoldur(kaldirilacakKlasorler)
                .onayAkisiDoldur(onayAkisi)
                .imzalama()
                .imzalamaEkraniKapat();

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(konu);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konu, kisiKurum, kayitTarihi, evrakNo);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrolVeTiklama(konu)
                .evrakSil()
                .evrakSilmeNotuGir(evrakSilmeNotu)
                .sil()
                .popupSilmeOnayi("Evet");

        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, tuzelKisi, kayitTarihiSayi, kayitTarihi, evrakSayi);

        cevapladiklarimPage
                .openPage()
                .evrakGelmedigiGorme(konu, kisiKurum, kayitTarihi, evrakNo);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS-2188: Cevap evrakında kullanıcı şablonu kullanma")
    public void TS2188() throws InterruptedException {

        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String birim = "OPTİİM BİRİM";
        String konu = "Test " + getSysDate();
        String ad = "Test";
        String soyad = "Otomasyon";
        String kisiKurum = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
        String tur = "IMZALAMA";
        String kaldirilacakKlasor = "Diğer";
        String kladirilacakKlasorTitle = "[Klasör] 000";
        String dagitimBilgisiKisi = "Mehmet Bozdemir";
        String evrakTuru2 = "Form";
        String formSablonu = "Kopya Optiim form şablonu";
        String onayAkisiListe = "DenemeListe";

        String mernisNo = createMernisTCKN();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKisiEkle()
                .iletisimBilgisiTCKNEkle(mernisNo)
                .iletisimBilgisiTCKNAra()
                .iletisimBilgisiAdDoldur(ad)
                .iletisimBilgisiSoyadDoldur(soyad)
                .iletisimBilgisikaydet();

        gelenEvrakKayitPage
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec(dagitimBilgisiKisi)
                .kaydet();

        String evrakNO2186 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username4, password4);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .cevapYaz();

        //TODO: Emre bu kontrolu libraryde yazsak bile pageden yazıp libraryden çağıralım. Testin içi daha temiz durur. id'ler testte olmamalı.
        //Örnek: alanDegeriKontrolEt(konu, true, true);
        alanDegeriKontrolEt($("[id$='konuTextArea']"), konu, true, true);

        evrakOlusturPage
                .bilgilerTabiAc()
                .evrakTuruSec(evrakTuru2)
                .formSec(formSablonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .cmbOnayAkisi(onayAkisiListe)
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur(konu)
                .onayIslemiGonder()
                .onayIslemiOnayaSunmaPopUp()
                .islemMesaji().beklenenMesaj(basariMesaji);

        //DenemeListesindeki kullnıcı veya kullanıcılarla giriş yapılır işlemdeki aksiyonlar alınır.
        logout();
        login("username24o", "123");

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrolVeTiklama(konu)
                .evrakOnizlemeImzala()
                .sImzaSec()
                .sImzaImzala(true);

        logout();
        login(username4, password4);

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakKontrol(konu);

        cevapladiklarimPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrolu(konu);

        String text = " tarihli yazı ile cevap yazılarak kapatılmıştır.";
        evrakDetayiPage
                .hareketGecmisiTabAc()
                .tabloKontol(text);

        klasoreKaldirdiklarimPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);


        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldurwithDetail(kaldirilacakKlasor, kladirilacakKlasorTitle)
                .evrakTarihiDoldur(getSysDateForKis2())
                .ara();

        klasorEvrakIslemleriPage
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        logout();
        login("username24o", "123");

        postalanacakEvraklarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu)
                .evrakPostala()
                .postala()
                .islemMesaji().beklenenMesaj(basariMesaji);

        postalananlarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "")
    public void TS2186() throws InterruptedException {

        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String birim = "OPTİİM BİRİM";
        String konu = "Test " + getSysDate();
        String ad = "Test";
        String soyad = "Otomasyon";
        String kisiKurum = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
        String tur = "IMZALAMA";
        String kaldirilacakKlasor = "Diğer";
        String kladirilacakKlasorTitle = "[Klasör] 000";
        String dagitimBilgisiKisi = "Mehmet Bozdemir";
        String evrakTuru2 = "Form";
        String formSablonu = "Kopya Optiim form şablonu";
        String onayAkisiListe = "DenemeListe";

        String mernisNo = createMernisTCKN();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKisiEkle()
                .iletisimBilgisiTCKNEkle(mernisNo)
                .iletisimBilgisiTCKNAra()
                .iletisimBilgisiAdDoldur(ad)
                .iletisimBilgisiSoyadDoldur(soyad)
                .iletisimBilgisikaydet();

        gelenEvrakKayitPage
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec(dagitimBilgisiKisi)
                .kaydet();

        String evrakNO2186 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username4, password4);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .cevapYaz();

        //TODO: Emre bu kontrolu libraryde yazsak bile pageden yazıp libraryden çağıralım. Testin içi daha temiz durur. id'ler testte olmamalı.
        //Örnek: alanDegeriKontrolEt(konu, true, true);
        alanDegeriKontrolEt($("[id$='konuTextArea']"), konu, true, true);

        evrakOlusturPage
                .bilgilerTabiAc()
                .evrakTuruSec(evrakTuru2)
                .formSec(formSablonu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .cmbOnayAkisi(onayAkisiListe)
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur(konu)
                .onayIslemiGonder()
                .onayIslemiOnayaSunmaPopUp()
                .islemMesaji().beklenenMesaj(basariMesaji);

        //DenemeListesindeki kullnıcı veya kullanıcılarla giriş yapılır işlemdeki aksiyonlar alınır.
        logout();
        login("username24o", "123");

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrolVeTiklama(konu)
                .evrakOnizlemeImzala()
                .sImzaSec()
                .sImzaImzala(true);

        logout();
        login(username4, password4);

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakKontrol(konu);

        cevapladiklarimPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrolu(konu);

        String text = " tarihli yazı ile cevap yazılarak kapatılmıştır.";
        evrakDetayiPage
                .hareketGecmisiTabAc()
                .tabloKontol(text);

        klasoreKaldirdiklarimPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);


        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldurwithDetail(kaldirilacakKlasor, kladirilacakKlasorTitle)
                .evrakTarihiDoldur(getSysDateForKis2())
                .ara();

        klasorEvrakIslemleriPage
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        logout();
        login("username24o", "123");

        postalanacakEvraklarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu)
                .evrakPostala()
                .postala()
                .islemMesaji().beklenenMesaj(basariMesaji);

        postalananlarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);
    }
}
