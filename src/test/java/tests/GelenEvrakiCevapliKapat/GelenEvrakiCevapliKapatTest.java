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
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
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
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        editor = new TextEditor();
    }

    @Test(enabled = true, description = "TS930: Kurum içi gelen evraka cevap yaz")
    public void TS930() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-930-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kisiKurum = "Kullanıcı";
        String kullanici = "Can Şeker";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Normal";
        String evrakSayiSag = createRandomNumber(15);
        String kisi = "Zübeyde Tekin";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";
        String geregi= "Optiim Birim";
        String geldigiYer= "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiGercekKisiDoldur(kullanici,"Kullanıcı")
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .benzerKayit();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kullanici, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konuKoduRandom)
                .kaldiralacakKlasorlerSec(konuKodu)
                .secilenGeregiSil2()
                .geregiDoldur2(geregi,"Birim")
                .onayAkisiDoldur(onayAkisi);

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
                .evrakSec(konuKoduRandom, kisi, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKoduRandom, geregi, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kullanici,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .aramaKriteriDoldur(konuKoduRandom)
                .ara();

        login(username, password);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom,evrakTarihi,geldigiYer);
    }

    @Test(enabled = true, description = "TS929: Dış kurumdan gelen evraka cevap yaz")
    public void TS929() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-929-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

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
                .yeniKayitButton()
                .benzerKayit();

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
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kurum, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kurum, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kurum,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .ara();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKodu,kurum,evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKodu,kurum,evrakTarihi);
    }

    @Test(enabled = true, description = "TS931: Gerçek kişiden gelen evraka cevap yaz")
    public void TS931() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-931-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kisi2 = "Optiim";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisiKurum = "Gerçek Kişi";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiGercekKisiDoldur(kisi2,"Gerçek kişi")
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .benzerKayit();

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kisi2,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .ara();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKodu,kisi2,evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKodu,kisi2,evrakTarihi);
    }

    @Test(enabled = true, description = "TS932: Tüzel kişiden gelen evraka cevap yaz")
    public void TS932() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-932-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kisi2 = "Can Şeker";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisiKurum = "Tüzel Kişi";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiTuzelKisiDoldur(kisi2,"Tüzel kişi")
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .benzerKayit();

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kisi2,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .ara();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKodu,kisi2,evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKodu,kisi2,evrakTarihi);
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

    //Sezai
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
    @Test(enabled = true, description = "TS2188: Cevap evrakında kullanıcı şablonu kullanma")
    public void TS2188() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-2187-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kisi2 = "Optiim";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisiKurum = "Gerçek Kişi";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiGercekKisiDoldur(kisi2,"Tüzel kişi")
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .benzerKayit();

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .onTanimliIcerikSablonuKullan("TS2188 (K)");

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kisi2,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .ara();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKodu,kisi2,evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKodu,kisi2,evrakTarihi);
    }

    @Test(enabled = true, description = "TS2187: Cevap evrakında birim içerik şablonu kullanma")
    public void TS2187() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String kaldirilacakKlasor = "Diğer";
        String konuKoduRandom = "TS-2187-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kisi2 = "Optiim";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisiKurum = "Gerçek Kişi";
        String icerik = createRandomText(15);
        String onayAkisi = "CanKontrol";

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiGercekKisiDoldur(kisi2,"Gerçek kişi")
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton()
                .benzerKayit();

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .onTanimliIcerikSablonuKullan("A11 (B)");

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiDoldur(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .kontrolEt()
                .kontrolEtNotDoldur(icerik)
                .kontrolEtGonder();

        login(username2, password2);

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu, kisi2, evrakTarihi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom, kisi2, evrakTarihi, evrakSayiSag);

        cevapladiklarimPage
                .openPage()
                .tabloEvrakSec(konuKoduRandom,kisi2,evrakTarihi)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisiEvrakKapandiIbaresiGorme();

        klasoreKaldirdiklarimPage
                .openPage()
                .cevapYazilanEvrakListeyeDustuguGorme(konuKoduRandom);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur(kaldirilacakKlasor)
                .ara();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKodu,kisi2,evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKodu,kisi2,evrakTarihi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2186 : Cevap evrakında form kullanma")
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
