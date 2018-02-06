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
        String tebligNot = "TS0845 not";

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
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (" + tarihBugun + ")", tebligGecmisiKontrolEdilecekKullanicilar);

        login("huser1", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu(true)
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
                .tebligEtKullaniciListesiDoldur("TS0845LISTE")
                .tebligEtKullaniciListesiTemizle()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (" + tarihBugun + ")", tebligGecmisiKontrolEdilecekKullanicilar);

        login("huser2", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu(true)
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .tebellugEtButonuKontrolEt()
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .evrakBilgileriTabAktifKontrolEt()
                .tebellugButonuKontrolEt();

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

        String tebligNot = "TS0067 not";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Huser2 TUMER2"
        };


        gelenEvraklarPage
                .openPage()
                .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, randomNumber)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtKullaniciListesiDoldur("TS0845LISTE")
                .tebligEtKullaniciListesiTemizle()
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (" + tarihBugun + ")", tebligGecmisiKontrolEdilecekKullanicilar);

        login("huser2", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu(true)
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali();

        tebellugEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        login("mbozdemir", "123");

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, randomNumber);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (" + tarihBugun + ")", tebligGecmisiKontrolEdilecekKullanicilar, new String[]{tarihBugun});


    }

    @Test(enabled = true, description = "TS0936 : Tebliğ hatırlatma ve Mesaj kontrolü")
    public void TS0936() {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        TebligEttiklerimPage tebligEttiklerimPage = new TebligEttiklerimPage();
        MesajlarPage mesajlarPage = new MesajlarPage();
        EvrakDetayiPage evrakDetayiPage = new EvrakDetayiPage();

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String randomNumber = "" + getRandomNumber(1000, 9999999);
        String konu = "TS0936-" + randomNumber;
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
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();
        String kayitTarihiSayi = tarihBugun + " / " + evrakNo;


        String tebligEdilecekKisi = "Huser2 TUMER2";
        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.";

        gelenEvraklarPage
                .openPage()
                .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, randomNumber)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();


        String tebligHatirlatNotu = "tebliğhatırlatma";
        String tebligEdenKullanici = "Mehmet BOZDEMİR";
        String basariMesaj = "İşlem başarılıdır!";
        String mesajKonu = "Tebliğ Hatırlatma";

        tebligEttiklerimPage
                .openPage()
                .evrakSec(konu, "", evrakTarihi, randomNumber)
                .tebligHatirlatTabTikla()
                .tebligHatirlatBilgiKontrol()
                .tebligHatirlatTabloKontrol()
                .okunmamisTebligleriHatirlat(true)
                .okunmusTebellugEdilmemisTebligleriHatirlat(true)
                .tebligHatirlatNotuGir(tebligHatirlatNotu)
                .tebligHatirlatVazgecButonKontrolu()
                .tebligHatirlat()
                .islemMesaji().basariliOlmali();

        login("huser2", "123");

        mesajlarPage
                .openPage()
                .mesajSec(tebligEdenKullanici, mesajKonu, konu)
                .mesajKontrol(mesajKonu, tarihBugun, tebligHatirlatNotu);

    }

    @Test(enabled = true, description = "TS0847 : Tebliğlerin teker teker ve toplu silinmesi")
    public void TS0847() {

        GelenEvrakKayitPage gelenEvrakKayitPage = new GelenEvrakKayitPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();
        TebliglerPage tebliglerPage = new TebliglerPage();
        TebellugEttiklerimPage tebellugEttiklerimPage = new TebellugEttiklerimPage();

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String konuKodu = "Entegrasyon İşlemleri";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String evrakGelisTipi = "Posta";
        String geldigiYer = "Yenikurum1485";
        String evrakTarihi = tarihBugun;

        String tebligEdilecekKisi = "Huser2 TUMER2";
        //String tebligNot = "TS0847 : Tebliğlerin teker teker ve toplu silinmesi";


        String[] evrakKonular = new String[4];
        String[] evrakNolar = new String[4];
        String[] evrakKayitSayilar = new String[4];
        String[] nolar = new String[4];

        String aciklama = "TS0847 aciklama";

        for (int i = 0; i <= 3; i++) {
            login("mbozdemir", "123");

            String randomNumber = "" + getRandomNumber(1000, 9999999);
            String konu = "TS0847-" + randomNumber;

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
            gelenEvrakKayitPage.islemMesaji().basariliOlmali();
            String kayitTarihiSayi = tarihBugun + " / " + evrakNo;

            evrakKonular[i] = konu;
            evrakNolar[i] = evrakNo;
            evrakKayitSayilar[i] = kayitTarihiSayi;
            nolar[i] = randomNumber;

            gelenEvraklarPage
                    .openPage()
                    .evrakIcerikGoster(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, randomNumber)
                    .tebligEt()
                    .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                    .tebligEtNotInputDoldur(aciklama)
                    .tebligEtTebligEt();

        }

        login("huser2", "123");

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakTipi = "Gelen Evrak";
        String tebligEdenKullanici = "Mehmet BOZDEMİR";

        String dikkatMesajiSecimsizSilme = "Tebliğ listesinden seçim yaptıktan sonra işlem yapılabilir!";
        String dikkatMesaji = "Tebellüğ edilmeden tebliğ silinemez!";
        String basariMesaj = "İşlem başarılıdır!";

        tebliglerPage
                .openPage()
                .evrakSec(evrakKonular[0], birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesajiSecimsizSilme);

        tebliglerPage
                .evrakTikSec(evrakKonular[0], birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        tebliglerPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec(evrakKonular[0], birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakKontrol(evrakKonular[0], birim, tebligEdenKullanici, evrakTipi, aciklama, false);

        tebliglerPage
                .evrakSec(evrakKonular[1], birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec(evrakKonular[2], birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec(evrakKonular[3], birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec(evrakKonular[1], birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .evrakTikSec(evrakKonular[2], birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .evrakTikSec(evrakKonular[3], birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);

        tebellugEttiklerimPage
                .openPage()
                .evrakSec(evrakKonular[1], "", tarihBugun, nolar[1])
                .evrakSec(evrakKonular[2], "", tarihBugun, nolar[2])
                .evrakSec(evrakKonular[3], "", tarihBugun, nolar[3]);


    }


}