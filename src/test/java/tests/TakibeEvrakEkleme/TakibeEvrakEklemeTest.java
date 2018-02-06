package tests.TakibeEvrakEkleme;

import common.BaseTest;
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

        // Gelen evrak kayıt oluşturma>>>

        /*
        String randomNumber = "" + getSysDate();
        String gelenEvrakKonu = "TS2073-" + randomNumber;
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
                .popUps();
        */
        // Gelen evrak kayıt oluşturma <<<


        // İmzaladıklarım sayfasına evrak gönderme >>>

        String imzaladiklarimEvrakKonu = "TS2073-" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur("Entegrasyon İşlemleri")
                .konuDoldur(imzaladiklarimEvrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("Normal")
                .geregiSecimTipiSecByText("Kurum")
                .geregiSec("Yenikurum1485")
                .gercekKisiGeregiAlaniPostaTipiSec("Adi Posta")
                .aciklamaDoldur(imzaladiklarimEvrakKonu)
                .onayAkisiEkle()
                .onayAkisiEkleIlkImzalaSec("İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("deneme içerik");
        evrakOlusturPage
                .evrakImzala();

        // İmzaladıklarım sayfasına evrak gönderme <<<

        // Postalanacak evrak oluşturma >>>

        String postalanacakEvrakKonu = "TS2073-" + getSysDate();
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
        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage.editorTabAc();
        editorTab.getEditor().type("TS2073");
        editorTab.imzala()
                .popupSImzalaIslemleri();
        Thread.sleep(2000);

        // Postalanacak evrak oluşturma <<<

        // kapattıklarım >>

        String randomNumber2 = "" + getSysDate();
        String kapattiklarimEvrakKonu = "TS2073-" + randomNumber2;
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
                .evrakSayiSagDoldur(kapattiklarimEvrakKonu)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet()
                .popUps();

        gelenEvraklarPage
                .openPage()
                .evrakSec(kapattiklarimEvrakKonu, "", "", "")
                .evrakKapat()
                .evrakKapatKaldirilacakKlasorlerDoldur("Diğer")
                .evrakiKapat();
        // kapattımlarım <<

        gelenEvraklarPage
                .openPage()
                .takipListesiAc("")
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .kullaniciListesiSec(secilecekKullaniciAdiSoyadi)
                .takipListesiKontrol(secilecekKullaniciAdiSoyadi, secilecekKullaniciBirim);

        imzaladiklarimPage
                .openPage()
                .takipListesiAc(imzaladiklarimEvrakKonu)
                .takipListesiKontrol(kullaniciAdiSoyadi, kullaniciBirim)
                .kullaniciListesiSec(secilecekKullaniciAdiSoyadi)
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
                .evrakKontrol("")
                .evrakKontrol(imzaladiklarimEvrakKonu)
                .evrakKontrol(postalanacakEvrakKonu)
                .evrakKontrol(kapattiklarimEvrakKonu);

        login("huser2", "123");

        takibimdekiEvraklarPage
                .openPage()
                .evrakKontrol("")
                .evrakKontrol(imzaladiklarimEvrakKonu);

    }



}