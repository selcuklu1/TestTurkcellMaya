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
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;

public class EvrakPostalamaTest extends BaseTest {

MainPage mainPage;
EvrakOlusturPage evrakOlusturPage;
PostalanacakEvraklarPage postalanacakEvraklarPage;
    @BeforeMethod
    public  void loginBeforeTest() {

    evrakOlusturPage = new EvrakOlusturPage();
    postalanacakEvraklarPage = new PostalanacakEvraklarPage();
    mainPage = new MainPage();
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
                        .gramajDoldur("1")
                        .hesapla()
                        .evrakPostala();









    }


}
