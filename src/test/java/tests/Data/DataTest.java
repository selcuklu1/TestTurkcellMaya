package tests.Data;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "tests.Data" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class DataTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;

    @BeforeMethod
    public void loginBeforeTests() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TS2224() throws InterruptedException {

        String konu = "Kanunlar";
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = "Gizli";
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";

        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
        evrakOlustur
                .openPage()
                .bilgilerTabiAc()
                .konuDoldur(konu)
                .konuKoduDoldur(konuKodu)
                .kaldirilacakKlasorler(kaldirilacakKlasorler)
                .evrakDerecesiSec(evrakDerecesi)
                .geregiSecimTipiSec(geregiSecimKurum)
                .geregiDoldur(geregiKurum, "Kurum")
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim, "Birim")
                .geregiSecimTipiSec(geregiSecimKullanici)
                .geregiDoldur(geregiKullanici, "Kullanıcı")
                .onayAkisiEkle()
                .akisAdimSec(akisAdim)
                .onayAkisiKullan();

        evrakOlustur
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .imzala()
                .popupSImzalaIslemleri() // Metodlara bölünecek.
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TS2234() throws InterruptedException {

        String konuKodu = "Gelen-Giden Evrak";
        String evrakTuru = "Resmi Yazışma";
        String evrakDerecesi = "Hizmete Özel";
        String evrakSayiEkMetni = "A1";
        String ivedilik = "Günlü";
        String miat = getSysDateForKis();
        String geregiSecimBirim = "Birim";
        String geregiBirim = "AFYON VALİLİĞİ";
        String akisAdim = "İmzalama";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String ekleriDosyaAciklama = "Açıklama";
        String filePath = System.getProperty("user.dir") + "/Documents/Otomasyon.pdf";//"C:\\Users\\TheKing\\Desktop\\s1.txt";

        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
        evrakOlustur
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDerecesiSec(evrakDerecesi)
                .evrakSayiEkMetniDoldur(evrakSayiEkMetni)
                .ivediSec(ivedilik)
                .miatDoldur(miat)
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim, "Birim")
                .onayAkisiEkle()
                .akisAdimSec(akisAdim)
                .onayAkisiKullan()
                .kaldirilacakKlasorler(kaldirilacakKlasorler);

        evrakOlustur
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik);

        evrakOlustur
                .ekleriTabAc()
                .ekleriDosyaEkle(filePath)
                .ekleriEkMetniDoldur(ekleriDosyaAciklama)
                .ekleriEkle()
                .imzala()
                .popupImzalaVeEvrakKapatma();
    }
}