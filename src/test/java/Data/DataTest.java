package Data;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Data" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class DataTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin","123");
        evrakOlustur = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TC2224() throws InterruptedException {

        String konu = "Kanunlar";
        String konuKodu="Kanunlar";
        String kaldirilacakKlasorler="KURUL KARARLARI";
        String evrakDerecesi="Gizli";
        String geregiSecimKurum= "Kurum";
        String geregiKurum="Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiSecimBirim= "Birim";   //Güncel Birim olmalı.
        String geregiBirim="AFYON VALİLİĞİ";
        String geregiSecimKullanici= "Kullanıcı";
        String geregiKullanici="Ahmet SAVAŞ";
        String akisAdim="İmzalama";
        String editorTab="Editör";
        String editorIcerik="Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlustur
                .openPage()
                .bilgilerTabiAc()
                .konuDoldur(konu)
                .konuKoduDoldur(konuKodu)
                .kaldirilacakKlasorler(kaldirilacakKlasorler)
                .evrakDerecesiSec(evrakDerecesi)
                .geregiSecimTipiSec(geregiSecimKurum)
                .geregiDoldur(geregiKurum)
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim)  //Güncel Birim doldurulmalı.
                .geregiSecimTipiSec(geregiSecimKullanici)
                .geregiDoldur(geregiKullanici)
                .onayAkisiEkle()
                .akisAdimSec(akisAdim)
                .onayAkisiKullan();
                //.imzalaButonuKontrol()

        evrakOlustur
                .openPage()
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .imzala()
                .popupSImzalaIslemleri() // Metodlara bölünecek.
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TC2234() throws InterruptedException {

        String konuKodu="Gelen-Giden Evrak";
        String evrakTuru="Resmi Yazışma";
        String evrakDerecesi="Hizmete Özel";
        String evrakSayiEkMetni="A1";
        String ivedilik="Günlü";
        String miat="24.11.2017";
        String geregiSecimBirim= "Birim";
        String geregiBirim="AFYON VALİLİĞİ";
        String akisAdim="İmzalama";
        String kaldirilacakKlasorler="KURUL KARARLARI";
        String editorTab="Editör";
        String editorIcerik="Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String ekleriTab="Ekleri";
        String ekleriDosyaAciklama = "Açıklama";
        String filePath = "C:\\Users\\TheKing\\Desktop\\s1.txt";

        evrakOlustur
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .evrakTuruSec(evrakTuru)
                .evrakDerecesiSec(evrakDerecesi)
                .evrakSayiEkMetniDoldur(evrakSayiEkMetni)
                .ivediSec(ivedilik)
                .miatDoldur(miat)   //Alanı seçiyor imleç yanıp sönüyor ama veri göndermiyor.
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim)
                .onayAkisiEkle()
                .akisAdimSec(akisAdim)
                .onayAkisiKullan()
                //.imzalaButonuKontrol()
                .kaldirilacakKlasorler(kaldirilacakKlasorler);

        evrakOlustur
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik);

        evrakOlustur
                .ekleriTabAc()
                .ekleriDosyaEkle(filePath)
                .ekleriDosyaAciklamaDoldur(ekleriDosyaAciklama)
                .ekleriEkle()
                .imzala()
                .popupImzalaVeEvrakKapatma();

    }
}
