package tests.EvrakPostalama;

/****************************************************
 * Yazan: Hakan Güner
 * Tarih: 2017-11-23
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Postalama" konulu senaryoları içerir
 ****************************************************/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.solMenuPages.PostalananlarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PostalananEvrakRaporuPage;

import java.sql.Driver;

public class EvrakPostalamaTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    ImzaladiklarimPage imzaladiklarimPage;
    PostalananEvrakRaporuPage postalananEvrakRaporuPage;

    User user1 = new User("user1", "123", "User1 TEST");

    @BeforeMethod
    public void loginBeforeTest() {

        evrakOlusturPage = new EvrakOlusturPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        postalananEvrakRaporuPage = new PostalananEvrakRaporuPage();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0308: Evrak Postalama")
    public void TC0308() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TC0308_" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
//                .kaldirilacakKlasorler("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
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



        Thread.sleep(4000);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();

        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama("Test")
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat()
                .postalanacakEvrakOrjYaz()
                .gramajDoldur("111111")
                .hesapla()
                .evrakPostala();


        Selenide.close();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2076: Evrak Postalama işlemleri")
    public void TC2076() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TC2076_" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
              //  .kaldirilacakKlasorler("B1K1")
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
            //    .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage.editorTabAc();
        editorTab.getEditor().type("TC2076");
        editorTab.imzala()
                .popupSImzalaIslemleri()
                .islemMesaji().basariliOlmali();;

        postalananlarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(0);

        imzaladiklarimPage
                .openPage()
                .dokumaniSec(konu)
                .evrakGecmisi();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true , description = "TC520 : Postalanan evrak posta bilgilerinin içerik ekranından güncellenmesi ve rapordan kontrolü")
    public void TC0520() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "Konu:";

        postalananlarPage
                .openPage();

        Thread.sleep(2000);
        postalananlarPage.tablodanSecim();

        Thread.sleep(1000);
        postalananlarPage.postaDetayiTikla();
        postalananlarPage.btnGuncelle();
        Thread.sleep(1000);
        postalananlarPage.btnTarihGuncelle("10.10.2017");
        postalananlarPage.btnPostakoduGuncelle("121212");
        postalananlarPage.txtAciklama("Bu bir açıklamadır");
        postalananlarPage = postalananlarPage.btnKaydet();

        String txt = postalananlarPage.evSay();
        postalananEvrakRaporuPage
                .openPage();

        postalananEvrakRaporuPage
                .evrakSayisi(txt)
                .postaAramaBaslangicTarihi("01.12.2017 00:00")
                .postaSorgulama()
                .sonucKarsilastirma();

    }

}
