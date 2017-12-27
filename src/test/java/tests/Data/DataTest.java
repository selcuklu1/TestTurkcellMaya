package tests.Data;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.IslemMesajlari;
import pages.solMenuPages.BeklemeyeAlinanlarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.ustMenuPages.EvrakOlusturPage;

import java.sql.Time;
import java.time.LocalDateTime;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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
    @Test(enabled = true, description = "TC2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TC2224() throws InterruptedException {

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
                .geregiDoldur(geregiKurum)
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiDoldur(geregiBirim)
                .geregiSecimTipiSec(geregiSecimKullanici)
                .geregiDoldur(geregiKullanici)
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
    @Test(enabled = true, description = "TC2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TC2234() throws InterruptedException {

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
                .geregiDoldur(geregiBirim)
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
                .ekleriDosyaAciklamaDoldur(ekleriDosyaAciklama)
                .ekleriEkle()
                .imzala()
                .popupImzalaVeEvrakKapatma();
    }
}