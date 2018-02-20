package common;

import data.TestData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.altMenuPages.CevapYazPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;
import pages.ustMenuPages.SistemLoglariPage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static sun.security.jgss.GSSUtil.login;

public class ReusableSteps extends BaseLibrary{

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;
    TaslakEvraklarPage taslakEvraklarPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    EvrakOlusturPage evrakOlusturPage;
    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    ImzaladiklarimPage imzaladiklarimPage;
    SistemLoglariPage sistemLoglariPage;
    MesajlarPage mesajlarPage;
    MainPage mainPage;
    BaseTest baseTest;

    @BeforeMethod
    public void loginBeforeTests() {
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        sistemLoglariPage = new SistemLoglariPage();
        mainPage = new MainPage();
        mesajlarPage = new MesajlarPage();
        baseTest = new BaseTest();
    }

    @Test(enabled = true, description = "TS2195 : Cevap evrakını paylaşma")
    public void TS2195() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS2195-" + randomNumber;
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiYer)
                .evrakSayiSagDoldur(randomNumber)
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();
        String kayitTarihiSayi = tarihBugun + " / " + evrakNo;
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .evrakiSec(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, "")
                .cevapYaz();

        CevapYazPage cevapYazPage = new CevapYazPage();

        cevapYazPage
                .konuDoldur(konu + "Cevap")
                .kaldirilacakKlasorlerDoldur("Diğer")
                .kaydet()
                .evrakKayitPopupEvet();

        String evrakAciklamasi = "Cevap evrakını paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Cevap evrakını paylaşma NOT 2";
        String yeniPaylasan = "Huser1 TUMER1";

        taslakEvraklarPage
                .openPage()
                .evrakSec(konu, geldigiYer, tarihBugun)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Huser1 TUMER1")
                .paylasKisiDoldur("Huser2 TUMER2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder();

        String tarihSaatBugun = "" + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());
        taslakEvraklarPage
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Huser1 TUMER1 / Huser2 TUMER2";

        paylastiklarimPage
                .openPage()
                .evrakSec(konu, "", paylasilanlar, tarihBugun)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, tarihBugun, evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Huser1 TUMER1", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "")
                .paylasilanKontrol("Huser2 TUMER2", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Paylaşımda", "");

    }

}
