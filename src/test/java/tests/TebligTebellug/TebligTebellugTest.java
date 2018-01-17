package tests.TebligTebellug;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TebligTebellugTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTests() {

    }

    @Test(enabled = true, description = "TS0845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.")
    public void TS0845() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        TebligEttiklerimPage tebligEttiklerimPage = new TebligEttiklerimPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        EvrakDetayiPage evrakDetayiPage = new EvrakDetayiPage();
        TebliglerPage tebliglerPage = new TebliglerPage();

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS0845-" + randomNumber;
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        login("mbozdemir", "123");

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

        String tebligEdilecekKullanicilistesi = "TS0845LISTE";
        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Huser1 TUMER1"
        };

        gelenEvraklarPage
                .openPage()
                .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, "")
                .tebligEt()
                .tebligEtKullaniciListesiDoldur(tebligEdilecekKullanicilistesi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();


        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - ("+tarihBugun+")", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("huser1", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();


    }

    @Test(enabled = true, description = "TS0845A : Gelen Evrakın Önizleme ekranından kullanıcıya tebliğ edilmesi")
    public void TS0845A() {
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        TebligEttiklerimPage tebligEttiklerimPage = new TebligEttiklerimPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        EvrakDetayiPage evrakDetayiPage = new EvrakDetayiPage();
        TebliglerPage tebliglerPage = new TebliglerPage();

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS0845-" + randomNumber;
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        login("mbozdemir", "123");

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

        String tebligEdilecekKisi = "Huser2 TUMER2";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Huser2 TUMER2"
        };


        gelenEvraklarPage
                .openPage()
                .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, randomNumber)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - ("+tarihBugun+")", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("huser2", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();

    }

    @Test(enabled = true, description = "TS0067 : Tebliğe gelen evrakın tebellüğ edilmesi ve tebliğ eden kullanıcıdan kontrolü")
    public void TS0067() {
        TebellugEttiklerimPage tebellugEttiklerimPage = new TebellugEttiklerimPage();
        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        TebligEttiklerimPage tebligEttiklerimPage = new TebligEttiklerimPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        EvrakDetayiPage evrakDetayiPage = new EvrakDetayiPage();
        TebliglerPage tebliglerPage = new TebliglerPage();

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS0845-" + randomNumber;
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        login("mbozdemir", "123");

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

        String tebligEdilecekKisi = "Huser2 TUMER2";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Huser2 TUMER2"
        };


        gelenEvraklarPage
                .openPage()
                .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, randomNumber)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - ("+tarihBugun+")", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("huser2", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali();

        tebellugEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        logout();
        login("mbozdemir", "123");

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - ("+tarihBugun+")", tebligGecmisiKontrolEdilecekKullanicilar, new String[] { tarihBugun });


    }

    @Test(enabled = true, description = "TS0936 : Tebliğ hatırlatma ve Mesaj kontrolü")
    public void TS0936() {
        TebligEttiklerimPage tebligEttiklerimPage = new TebligEttiklerimPage();
        MesajlarPage mesajlarPage = new MesajlarPage();


        String konu = "TS0936";
        String evrakTarihi = "18.12.2017";

        String no = "123";
        String tebligHatirlatNotu = "tebliğhatırlatma";
        String tebligEdenKullanici = "Mehmet BOZDEMİR";
        String basariMesaj = "İşlem başarılıdır!";
        String mesajKonu = "Tebliğ Hatırlatma";


        login("mbozdemir", "123");

        tebligEttiklerimPage
                .openPage()
                .evrakSec(konu, "", evrakTarihi, no)
                .tebligHatirlatTabTikla()
                .okunmamisTebligleriHatirlat(true)
                .okunmusTebellugEdilmemisTebligleriHatirlat(true)
                .tebligHatirlatNotuGir(tebligHatirlatNotu)
                .tebligHatirlat()
                .islemMesaji().basariliOlmali(basariMesaj);

        logout();


        login("optiimtest2", "123");

        mesajlarPage
                .openPage()
                .mesajSec(tebligEdenKullanici, mesajKonu, konu)
                .mesajKontrol(mesajKonu, "18.12.2017", tebligHatirlatNotu);

    }

    @Test(enabled = true, description = "TS0847 : Tebliğlerin teker teker ve toplu silinmesi")
    public void TS0847() {

        TebliglerPage tebliglerPage = new TebliglerPage();
        TebellugEttiklerimPage tebellugEttiklerimPage = new TebellugEttiklerimPage();

        String konu = "Kanunlar";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakTipi = "Gelen Evrak";
        String aciklama = "test7";
        String tebligEdenKullanici = "Yasemin Çakıl AKYOL";

        String dikkatMesajiSecimsizSilme = "Tebliğ listesinden seçim yaptıktan sonra işlem yapılabilir!";
        String dikkatMesaji = "Tebellüğ edilmeden tebliğ silinemez!";
        String basariMesaj = "İşlem başarılıdır!";

        tebliglerPage
                .openPage()
                .evrakSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesajiSecimsizSilme);

        tebliglerPage
                .evrakTikSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        tebliglerPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);


        tebliglerPage
                .evrakKontrol(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, false);


        tebliglerPage
                .evrakSec("Veri Toplama", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec("Cihaz Takip İşlemleri", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec("Şikayet ve Talepler", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec("Veri Toplama", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .evrakTikSec("Cihaz Takip İşlemleri", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .evrakTikSec("Şikayet ve Talepler", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);


        tebellugEttiklerimPage
                .openPage()
                .evrakSec("Veri Toplama", "", "", "")
                .evrakSec("Cihaz Takip İşlemleri", "", "", "")
                .evrakSec("Şikayet ve Talepler", "", "", "");


    }


}