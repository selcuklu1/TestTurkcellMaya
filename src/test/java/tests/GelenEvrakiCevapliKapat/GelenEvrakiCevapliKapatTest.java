package tests.GelenEvrakiCevapliKapat;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.CevapladiklarimPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.KontrolBekleyenlerPage;
import pages.ustMenuPages.CevaplananEvrakRaporuPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.io.IOException;

import static data.TestData.*;
import pages.EvrakDetayiPage;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.CevaplananEvrakRaporuPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GelenEvraklarCevapYazPage;

import java.io.IOException;

import static data.TestData.password;
import static data.TestData.username;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Gelen Evrakı Cevaplı Kapat" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class GelenEvrakiCevapliKapatTest extends BaseTest {

    CevaplananEvrakRaporuPage cevaplananEvrakRaporuPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakOlusturPage evrakOlusturPage;
    EvrakDetayiPage evrakDetayiPage;
    GelenEvraklarCevapYazPage gelenEvraklarCevapYazPage;
    KontrolBekleyenlerPage kontrolBekleyenlerPage;
    CevapladiklarimPage cevapladiklarimPage;
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
    }

    @Test(enabled = true, description = "TC930: Kurum içi gelen evraka cevap yaz")
    public void TC930() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2227-" + createRandomNumber(10);
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
                .evrakSec(konuKoduRandom,kurum,evrakTarihi,evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiDoldur2(onayAkisi);

        evrakOlusturPage
                .kaydetOnayaSun2()
                .kaydetOnayaSunAciklamaDoldur2(icerik)
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .openPage()
                .evrakGelmedigiGorme(konuKoduRandom,kurum,evrakTarihi,evrakSayiSag);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu,kurum,evrakTarihi)
                .kontrolEt();

        login(username2, password2);

        cevapladiklarimPage
                .openPage();



       /* evrakDetayiPage = new EvrakDetayiPage();
        gelenEvraklarCevapYazPage = new GelenEvraklarCevapYazPage();
        editor = new TextEditor();*/
    }

    @Test(enabled = true, description = "TC931: Gerçek kişiden gelen evraka cevap yaz")
    public void TC931() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2227-" + createRandomNumber(10);
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
                .evrakSec(konuKoduRandom,kurum,evrakTarihi,evrakSayiSag)
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
                .evrakGelmedigiGorme(konuKoduRandom,kurum,evrakTarihi,evrakSayiSag);

        login("test1", "123");

        kontrolBekleyenlerPage
                .openPage()
                .evrakSec(konuKodu,kurum,evrakTarihi)
                .kontrolEt();

        login(username2, password2);

        cevapladiklarimPage
                .openPage();



       /* evrakDetayiPage = new EvrakDetayiPage();
        gelenEvraklarCevapYazPage = new GelenEvraklarCevapYazPage();
        editor = new TextEditor();*/
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1368: Cevaplanan Evrak Raporu")
    public void TC1368() throws IOException, InterruptedException {

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
        String filePath = "C:\\Users\\" + getPcUserName() + "\\Downloads\\";
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
    @Test(enabled = true, description = "TC0373: Cevap yazma işleminde evrakın onay akışından silinmesi")
    public void TC0373() throws InterruptedException {

        String onayAkisi = "Tc373 OnayAkışı";
        String tuzelKisi = "Tc373 TüzelKişi";
        String kisiKurum = "Tüzel Kişi";
        String kisi = "Optiim TEST";
        String konuKodu = "040"; //Faaliyet Raporları
        String konu = "Faaliyet Raporları";
        String kayitTarihi = getSysDateForKis();
        String evrakSayiSol = createRandomNumber(10);
        String evrakSayiSag = createRandomNumber(4);
        String basariMesaji = "İşlem başarılıdır!";
        String evrakSayi = evrakSayiSol + "-" + evrakSayiSag;
        String kaldirilacakKlasorler = "ESK05";
        String aciklama = "Onay işlemi açıklamasıdır.";

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText(kisiKurum)
                .geldigiTuzelKisiDoldur(tuzelKisi)
                .konuKoduDoldur(konuKodu)
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
                .evrakSec(tuzelKisi, konu, kayitTarihiSayi, kayitTarihi, evrakSayi);

        evrakDetayiPage
                .sayfaAcilmali()
                .ikonKontrolleri()
                .cevapYaz();

        gelenEvraklarCevapYazPage
                .geregiKontrolu(tuzelKisi)
                .konuKonuKontrolu(konu)
                .editorTabAc()
                .editorSayiTarihKontrolu(evrakSayi, kayitTarihi);

        editor
                .type("Bu bir text yazısıdır.");

        gelenEvraklarCevapYazPage
                .bilgilerTabAc()
                .kaldirilacakKlasorlerDoldur(kaldirilacakKlasorler)
                .onayAkisiDoldur(onayAkisi)
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur(aciklama)
                .gonder()
                .evrakKayitUyariPopup("Evet")
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .tabloOlmayanEvrakNoKontrol(evrakNo);

        //TODO: devam edilecek.
    }

}
