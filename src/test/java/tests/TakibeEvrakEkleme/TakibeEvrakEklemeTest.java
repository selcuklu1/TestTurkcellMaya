package tests.TakibeEvrakEkleme;

import common.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageData.SolMenuData;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TakibeEvrakEklemeTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTests() {

    }

    @Test(enabled = true, description = "TS2073 : Takibe Evrak Ekle")
    public void TS2073() throws InterruptedException {

        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage();
        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        KapattiklarimPage kapattiklarimPage = new KapattiklarimPage();
        TakibimdekiEvraklarPage takibimdekiEvraklarPage = new TakibimdekiEvraklarPage();

        login("mbozdemir", "123");

        String kullaniciAdiSoyadi = "Mehmet BOZDEMİR";
        String kullaniciBirim = "YGD";
        String secilecekKullaniciAdiSoyadi = "Huser2 TUMER2";
        String secilecekKullaniciBirim = "YGD";

        Allure.addAttachment("Pre Condition evrak oluşturma", "başlatıldı.");

        String gelenEvrakKonu = "";
        String imzaladiklarimEvrakKonu = "";
        String postalanacakEvrakKonu = "";
        String randomNumber2 = "";
        String kapattiklarimEvrakKonu = "";


        // İmzaladıklarım sayfasına evrak gönderme >>>

        imzaladiklarimEvrakKonu = "TS2073-" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(imzaladiklarimEvrakKonu)
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Yenikurum1485", "")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1804 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        Thread.sleep(2000);

        // İmzaladıklarım sayfasına evrak gönderme <<<

        // Postalanacak evrak oluşturma >>>

        postalanacakEvrakKonu = "TS2073-" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(postalanacakEvrakKonu)
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Yenikurum1485", "")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                .onayAkisiKullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1804 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        // Postalanacak evrak oluşturma <<<

        // Gelen evrak kayıt oluşturma>>>

        String randomNumber = "" + getSysDate();
        gelenEvrakKonu = "TS2073-" + randomNumber;

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
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet()
                .popUps(true);

        // Gelen evrak kayıt oluşturma <<<


        // kapattıklarım >>

        randomNumber2 = "" + getSysDate();
        kapattiklarimEvrakKonu = "TS2073-" + randomNumber2;
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(kapattiklarimEvrakKonu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Hizmete Özel")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Yenikurum1485")
                .evrakSayiSagDoldur(randomNumber2)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet()
                .popUps(true);

        gelenEvraklarPage
                .openPage()
                .evrakSec(kapattiklarimEvrakKonu, "", "", "")
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur("Diğer")
                .evrakiKapat();
        // kapattımlarım <<




        Allure.addAttachment("Pre Condition evrak oluşturma", "bitti.");


        gelenEvraklarPage
                .openPage()
                .takipListesiAc(gelenEvrakKonu)
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .takipListesiKullanicilarDoldur(secilecekKullaniciAdiSoyadi)
                .takipListesiKontrol(secilecekKullaniciAdiSoyadi, secilecekKullaniciBirim)
                .takipListesiKapat();

        imzaladiklarimPage
                .openPage()
                .takipListesiAc(imzaladiklarimEvrakKonu)
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .takipListesiKullanicilarDoldur(secilecekKullaniciAdiSoyadi)
                .takipListesiKontrol(secilecekKullaniciAdiSoyadi, secilecekKullaniciBirim)
                .takipListesiKapat();

        postalanacakEvraklarPage
                .openPage()
                .takipListesiAc(postalanacakEvrakKonu)
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .takipListesiKapat();

        kapattiklarimPage
                .openPage()
                .takipListesiAc(kapattiklarimEvrakKonu)
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .takipListesiKapat();

        takibimdekiEvraklarPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu)
                .evrakKontrol(imzaladiklarimEvrakKonu)
                .evrakKontrol(postalanacakEvrakKonu)
                .evrakKontrol(kapattiklarimEvrakKonu);

        login("huser2", "123");

        takibimdekiEvraklarPage
                .openPage()
                .evrakKontrol(gelenEvrakKonu)
                .evrakKontrol(imzaladiklarimEvrakKonu);

    }



}