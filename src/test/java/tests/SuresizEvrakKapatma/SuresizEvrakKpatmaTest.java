package tests.SuresizEvrakKapatma;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KlasorEvrakIslemleriPage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SuresizEvrakKpatmaTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTests() {

    }

    @Test(enabled = true, description = "TS0074 : Kapatma onayındaki evrakın reddedilmesi.")
    public void TS0074() throws InterruptedException {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        OnayaSunduklarimPage onayaSunduklarimPage = new OnayaSunduklarimPage();
        ImzaBekleyenlerPageKapatmaIslemleri imzaBekleyenlerPageKapatmaIslemleri = new ImzaBekleyenlerPageKapatmaIslemleri();
        KlasorEvrakIslemleriPage klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();

        // Gelen evrak kayıt oluşturma>>>

        login("huser1", "123");

        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS0074-" + randomNumber;

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(gelenEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Huser1 TUMER1")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<

        gelenEvraklarPage
                .openPage()
                .evrakSec(gelenEvrakKonu,"","","")
                .evrakKapat()
                .evrakKapatmaEkraniKontrol()
                .evrakKapatKapatmaTipiSec("Kapatma")
                .evrakKapatKonuKodu("Entegrasyon İşlemleri")
                .evrakKapatKaldirilacakKlasorlerDoldur("gündem","")
                .evrakKapatmaOnayAkisiEkle()
                .evrakKapatOnayAkisiAdiDoldur("TS0074")
                .evrakKapatOnayAkisiKullaniciSec("Huser TUMER")
                .evrakKapatOnayAkisiKullaniciTipiSec("Huser TUMER", "Kapatma İmzası")
                .evrakKapatOnayAkisiKullan()
                .evrakKapatKapatmaOnayinaSun();

        onayaSunduklarimPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

        login("huser", "123");


        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakSec(gelenEvrakKonu, "", "", "")
                .kapatmaImzala()
                .kapatmayIptalEt()
                .islemMesaji().basariliOlmali();

        logout();

        login("huser", "123");

        imzaBekleyenlerPageKapatmaIslemleri
                .openPage()
                .evrakKontrol(gelenEvrakKonu, false);

        login("huser1", "123");

        String tarihBugun = "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldur("gündem")
                .evrakTarihiDoldur(tarihBugun)
                .ara()
                .evrakListesiKontrol(gelenEvrakKonu, false);

        gelenEvraklarPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu, true);

    }



}