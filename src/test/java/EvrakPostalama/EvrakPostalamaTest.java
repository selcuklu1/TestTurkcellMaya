package EvrakPostalama;

/****************************************************
 * Yazan: Hakan Güner
 * Tarih: 2017-11-23
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Postalama" konulu senaryoları içerir
 ****************************************************/

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageData.SolMenuData;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.solMenuPages.PostalananlarPage;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakPostalamaTest extends BaseTest {

MainPage mainPage;
EvrakOlusturPage evrakOlusturPage;
PostalanacakEvraklarPage postalanacakEvraklarPage;
PostalananlarPage postalananlarPage;
ImzaladiklarimPage imzaladiklarimPage;

    @BeforeMethod
    public  void loginBeforeTest() {

    evrakOlusturPage = new EvrakOlusturPage();
    postalanacakEvraklarPage = new PostalanacakEvraklarPage();
    mainPage = new MainPage();
    postalananlarPage = new PostalananlarPage();
    imzaladiklarimPage = new ImzaladiklarimPage();

    login("Mbozdemir" , "123");


    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0308: Evrak Postalama")
    public void TC0308() throws InterruptedException {
    evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                .onayAkisiKullan();

       evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle();

                evrakOlusturPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


                evrakOlusturPage
                        .editorTabAc()
                       .editorIcerikDoldur("TC308")
                       .editorEvrakGeregiSec("YAZILIM GELİ")
                      .imzala()
                       .popupSImzalaIslemleri();

                postalanacakEvraklarPage
                        .openPage()
                        .evrakSec()
                        .evrakPostala()
                        .gidisSekli("E-Posta")
                        .PostalacanakEposta("test@test.com")
                        .PostalamaAciklama("Test")
                        .postalanacakEvrakYaz()
                        .PopupPostalanacakEvrakYazdir()
                        .PopupPostaYazdirmaKapat()
                        .postalanacakEvrakOrjYaz()
                        .gramajDoldur("111111")
                        .hesapla()
                        .evrakPostala();



    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2076: Evrak Postalama işlemleri")
    public void TC2076() throws InterruptedException {
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TC2076")
                .editorEvrakGeregiSec("Başbakanlık")
                .imzala()
                .popupSImzalaIslemleri();

        postalananlarPage
                .openPage();


       imzaladiklarimPage
               .openPage()
               .evrakSec()
               .evrakGecmisi();


    }


}
