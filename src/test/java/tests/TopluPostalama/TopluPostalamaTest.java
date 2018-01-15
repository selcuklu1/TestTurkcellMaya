package tests.TopluPostalama;

import common.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PttRaporuPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class TopluPostalamaTest extends BaseTest {

    MainPage mainPage;
    TopluPostalanacakEvraklarPage topluPostalanacakEvraklarPage;
    PostaListesiPage postaListesiPage;
    PostalananlarPage postalananlarPage;
    PttRaporuPage pttRaporuPage;
    TopluPostaladiklarimPage topluPostaladiklarimPage;
    ImzaladiklarimPage imzaladiklarimPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    EvrakOlusturPage evrakOlusturPage;

    @BeforeMethod
    public void loginBeforeTests() {
        postalananlarPage = new PostalananlarPage();
        topluPostalanacakEvraklarPage = new TopluPostalanacakEvraklarPage();
        postaListesiPage = new PostaListesiPage();
        pttRaporuPage = new PttRaporuPage();
        topluPostaladiklarimPage = new TopluPostaladiklarimPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        mainPage = new MainPage();
    }

    @Test(enabled = true, description = "1804 : Toplu Postalanacak Evrakların Sorgulanması (UC_POSTAYÖNETİMİ_001)")
    public void TS01804() {

        String[] gidecegiYerler = new String[]{
                "Yenikurum6507",
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String listeAdi = "Liste" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2015";

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String evrakKayitTarihiSayi = tarihBugun;
        String evrakGidecegiYer = gidecegiYerler[0];
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";
        String evrakKonu = "TS1804-" + getRandomNumber(1000, 9000);

        login("mbozdemir", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1804 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(new String[]{
                        "Birim Elden"
                })
                .sorgula();
        //.islemMesaji().uyariOlmali("");

        baslangicTarihi = "01.12.2016";
        bitisTarihi = "02.12.2018";

        topluPostalanacakEvraklarPage
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .postaTipiSec(postaTipleri)
                .gidecegiYerTumunuIsaretle(false)
                .gidecegiYerSec(gidecegiYerler, false)
                .sorgula();
        //.islemMesaji().uyariOlmali("");

        topluPostalanacakEvraklarPage
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakGoster()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();
        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle()
                .islemMesaji().basariliOlmali();


        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .evrakListesiKontrol(evrakGidecegiYer, evrakKonu);

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, false);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesindenCikart(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .islemMesaji().basariliOlmali();


        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true);

    }

    @Test(enabled = true, description = "1808 : Posta Listesine Evrak Ekleme ve Çıkartma (UC_POSTAYÖNETİMİ_002)")
    public void TS01808() {

        String[] gidecegiYerler = new String[]{
                "Yenikurum6507",
                "Yenikurum4105"
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };


        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        String gidecegiYer = "Yenikurum6507";
        String evrakPostaTipi = "Adi Posta";
        String evrakKonu = "TS01808-" + getRandomNumber(1000, 9000);

        login("mbozdemir", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(gidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01808 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        String evrakKonu2 = "TS01808-" + getRandomNumber(1000, 9000);
        String gidecegiYer2 = "Yenikurum4105";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu2)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(gidecegiYer2)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01808 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();


        String baslangicTarihi = "01.12.2017";
        String bitisTarihi = "01.12.2018";

        String postaListesi = "Liste" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String evrak1KayitTarihiSayi = "";
        String evrak1Sayi = "";
        String evrak1GidecegiYer = gidecegiYer;
        String evrak1Konu = evrakKonu;
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";

        String evrak2KayitTarihiSayi = "";
        String evrak2Sayi = "";
        String evrak2GidecegiYer = gidecegiYer2;
        String evrak2Konu = evrakKonu2;
        String evrak2HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak2PostaTipi = "Adi Posta";

        String basariMesaji = "İşlem başarılıdır!";
        String dikkatMesaji = "Posta Listesine eklenecek evrak seçiniz!";

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .postaListesineAktar()
                .listeAdiDoldur(postaListesi)
                .gonderildigiKurumSec(gidecegiYer)
                .listeOlustur()
                .postaListesiSec(postaListesi)
                .listeyeEkle();
        //.islemMesaji().dikkatOlmali(dikkatMesaji);

        topluPostalanacakEvraklarPage
                .filtreGidecegiYer(evrak1GidecegiYer)
                .gidecegiYerSec(evrak1GidecegiYer, true)
                .filtreGidecegiYer(evrak2GidecegiYer)
                .gidecegiYerSec(evrak2GidecegiYer, true)
                .sorgula()
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .evrakSec(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .evrakTikSec(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, true)
                .postaListesineAktar()
                .postaListesiSec(postaListesi)
                .listeyeEkle()
                .islemMesaji().basariliOlmali(basariMesaji);

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, false)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, false);

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, true)
                .postaListesiPostala()
                .evrakListesiKontrol(evrak1GidecegiYer, evrak1Sayi)
                .evrakListesiKontrol(evrak2GidecegiYer, evrak2Sayi)
                .postaListesindenCikart(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesindenCikart(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi);

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, true);
    }

    @Test(enabled = true, description = "1807 : Posta Listesi Oluşturma- Tüzel Kişi Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TS01807() {

        String tuzelKisi1 = "TS01807-TK1";
        String tuzelKisi2 = "TS01807-TK2";

        String evrakKonu1 = "TS01807-" + getRandomNumber(1000, 9000);
        String evrakKonu2 = "TS01807-" + getRandomNumber(1000, 9000);


        login("mbozdemir", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu1)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Tüzel Kişi")
                .geregiTuzelKisiSec(tuzelKisi1)
                .geregiKurumPostaTipi("Adi Posta")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01807 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu2)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Tüzel Kişi")
                .geregiTuzelKisiSec(tuzelKisi2)
                .geregiKurumPostaTipi("Adi Posta")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01807 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        String[] gidecegiYerler = new String[]{
                tuzelKisi1
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String tuzelKisi = tuzelKisi1;

        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";

        String evrakKayitTarihiSayi = "";
        String evrakGidecegiYer = tuzelKisi1;
        String evrakKonu = evrakKonu1;
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String evrak1KayitTarihiSayi = "";
        String evrak1GidecegiYer = tuzelKisi2;
        String evrak1Konu = evrakKonu2;
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";


        String listeAdi = "ListeTS01807-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);


        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .postaListesineAktar();
        // UYARI MESAJI GELMELİ !!!!!!!!!!!! <<<<<<<<<<<<<<<<<<


        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .tuzelKisiKontrolet(tuzelKisi)
                .listeAdiDoldur(listeAdi)
                .seciliTuzelKisiKaldir()
                .gonderildigiTuzelKisiSec(tuzelKisi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();


        postaListesiPage
                .openPage();


        gidecegiYerler = new String[]{
                tuzelKisi1,
                tuzelKisi2
        };

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .postaListesineAktar()
                .tuzelKisiKontrolet(tuzelKisi)
                .listeAdiDoldur(listeAdi)
                .listeOlustur();
        //.islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "TS01807-1-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .evrakListesiKontrol(evrakGidecegiYer, evrakKonu);
    }

    @Test(enabled = true, description = "1807 : Posta Listesi Oluşturma- Gerçek Kişi Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TS01807A() {

        String gercekKisi1 = "TS01807GKADD";
        String gercekKisi2 = "TS01807GKYAD";

        String evrakKonu1 = "TS01807A-" + getRandomNumber(1000, 9000);
        String evrakKonu2 = "TS01807A-" + getRandomNumber(1000, 9000);


        login("mbozdemir", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu1)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Gerçek Kişi")
                .geregiTuzelKisiSec(gercekKisi1)
                .geregiKurumPostaTipi("Adi Posta")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01807 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu2)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Gerçek Kişi")
                .geregiTuzelKisiSec(gercekKisi2)
                .geregiKurumPostaTipi("Adi Posta")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS01807 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        String[] gidecegiYerler = new String[]{
                gercekKisi1,
                gercekKisi2
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String gercekKisi = "TS01807GKADD";


        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";

        String evrakKayitTarihiSayi = "";
        String evrakGidecegiYer = gercekKisi1;
        String evrakKonu = evrakKonu1;
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String evrak1KayitTarihiSayi = "";
        String evrak1GidecegiYer = gercekKisi2;
        String evrak1Konu = evrakKonu2;
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";


        String listeAdi = "TS01807A-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        login("mbozdemir", "123");

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .postaListesineAktar();
        // UYARI MESAJI GELMELİ !!!!!!!!!!!! <<<<<<<<<<<<<<<<<<

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .gerceklKisiKontrolet(gercekKisi)
                .listeAdiDoldur(listeAdi)
                .seciliGercekKisiKaldir()
                .gonderildigiGercekKisiSec(gercekKisi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();


        postaListesiPage
                .openPage();


        gidecegiYerler = new String[]{
                gercekKisi1,
                gercekKisi2
        };

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .postaListesineAktar()
                .gerceklKisiKontrolet(gercekKisi)
                .listeAdiDoldur(listeAdi)
                .listeOlustur();
        //.islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "TS01807-2-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .evrakListesiKontrol(evrakGidecegiYer, evrakKonu);

    }

    @Test(enabled = true, description = "1805 : Posta Listesi Oluşturma- Kurum Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TS01805() {

        String kurum1 = "Yenikurum6507";
        String kurum2 = "Yenikurum4105";

        String[] gidecegiYerler = new String[]{
                kurum1
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String kurum = kurum1;

        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";

        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "";
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet()
                .islemMesaji().basariliOlmali();

        String evrak1KayitTarihiSayi = "";
        String evrak1GidecegiYer = kurum2;
        String evrak1KonuKodu = "Entegrasyon İşlemleri";
        String evrak1Konu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrak1KonuKodu)
                .konuDoldur(evrak1Konu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrak1GidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için ikinci evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup()
                .islemMesaji().basariliOlmali();

        String listeAdi = "TS01807Liste-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .postaListesineAktar();
        // UYARI MESAJI GELMELİ !!!!!!!!!!!! <<<<<<<<<<<<<<<<<<

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .kurumKontrolet(kurum)
                .listeAdiDoldur(listeAdi)
                .seciliKurumKaldir()
                .gonderildigiKurumSec(kurum)
                .listeOlustur()
                .islemMesaji().basariliOlmali();


        postaListesiPage
                .openPage();


        gidecegiYerler = new String[]{
                kurum1,
                kurum2
        };

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .postaListesineAktar()
                .kurumKontrolet(kurum)
                .listeAdiDoldur(listeAdi)
                .listeOlustur();
//                .islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "TS01805-1-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .evrakListesiKontrol(evrakGidecegiYer, evrakKonu);

    }

    @Test(enabled = true, description = "1815A : Toplu postalama PTT raporunda alan kontrolleri-daha önceden rapor alındı ise (UC_POSTAYÖNETİMİ_004)")
    public void TS01815A() {

        String kurum1 = "Yenikurum6507";

        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakPostaTipi = "Adi Posta";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        String listeAdi = "TS01815AListe-" + getRandomNumber(1000, 9000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .postaDetayiPostala();

        String postaTarihi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String postaTipi = "Adi Posta";
        String gittigiYer = kurum1;
        String evrakSayi = "";

        pttRaporuPage
                .openPage()
                .aramaDetaylariPanelAc()
                .postaTarihiDoldur(postaTarihi)
                .postaTipiSec(postaTipi)
                .sorgula()
                .tabloKontrolEt(gittigiYer, evrakSayi, postaTipi, true);

    }

    @Test(enabled = true, description = "1815B : Toplu postalama PTT raporunda alan kontrolleri (UC_POSTAYÖNETİMİ_004)")
    public void TS01815B() {

        String kurum1 = "Yenikurum6507";

        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakPostaTipi = "Adi Posta";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        String listeAdi = "TS01815AListe-" + getRandomNumber(1000, 9000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .postaDetayiPostala();

        String postaTarihi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String postaTipi = "Adi Posta";
        String gittigiYer = kurum1;
        String evrakSayi = "";

        String uyariMesaji = "Zorunlu alanları doldurunuz";

        login("mbozdemir", "123");

        pttRaporuPage
                .openPage()
                .dagiticiDoldur("")
                .aramaDetaylariPanelAc()
                .postaTarihiDoldur(postaTarihi)
                .sorgula()
                .islemMesaji().uyariOlmali(uyariMesaji);

        pttRaporuPage
                .dagiticiDoldur("a")
                .duzenleyenDoldur("")
                .postaTarihiDoldur(postaTarihi)
                .sorgula()
                .islemMesaji().uyariOlmali(uyariMesaji);

        pttRaporuPage
                .duzenleyenDoldur("a")
                .avansSorumlusuDoldur("")
                .postaTarihiDoldur(postaTarihi)
                .sorgula()
                .islemMesaji().uyariOlmali(uyariMesaji);

        pttRaporuPage
                .avansSorumlusuDoldur("a")
                .kontrolEdenDoldur("")
                .postaTarihiDoldur(postaTarihi)
                .sorgula()
                .islemMesaji().uyariOlmali(uyariMesaji);

        pttRaporuPage
                .kontrolEdenDoldur("a")
                .pttMerkezDoldur("")
                .postaTarihiDoldur(postaTarihi)
                .sorgula()
                .islemMesaji().uyariOlmali(uyariMesaji);

    }

    // EXCELL KONTROLÜ YAPILACAK
    @Test(enabled = true, description = "1676 : Toplu Postalama PTT Evrak Raporu (UC_POSTAYÖNETİMİ_005)")
    public void TS01676() {

        /*

        String postaTarihi = "13.12.2017";
        String postaTipi = "Adi Posta";
        String gittigiYer = "Enerjı ve Madencılık Daire Başkanlığı";
        String evrakSayi = "9174";

        login("mbozdemir", "123");

        pttRaporuPage
                .openPage()
                .aramaDetaylariPanelAc()
                .postaTarihiDoldur(postaTarihi)
                .postaTipiSec(postaTipi)
                .sorgula();

        String ulke = pttRaporuPage.tablodanDegerAl("Ülke");
        String il = pttRaporuPage.tablodanDegerAl("Şehir");
        postaTipi = pttRaporuPage.tablodanDegerAl("Gidiş Şekli");

        pttRaporuPage
                .ulkeDoldur(ulke)
                .ilDoldur(il)
                .postaTipiSec(postaTipi)
                .sorgula()
                .tabloKontrolEt(gittigiYer, evrakSayi, postaTarihi, true);

        */


        /*
        pttRaporuPage
                .excell();
                //.excellKontrolEt();
        */


    }

    // DÜZENLENECEK
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1675 : Toplu Postaladıklarım İzleme / Alan Kontrolleri (UC_POSTAYÖNETİMİ_004)")
    public void TS1675() {

//        String[] konu = new String[]{
//                "TC1675 20180113152416", "TC1675 20180113152416100"
//        };
//

//        String[] konu2 = new String[]{
//                "TC1811 20180115115312", "TC1811 20180115115312100"
//        };
//                String[] evrakNo1675 = new String[]{
//                "10538", "10537"
//        };

        login("mbozdemir", "123");
        //region Parameters
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String geregi = "Ahmet Çelik";
        String adres = "adres " + getSysDateForKis();

        String[] konu = new String[]{
                "TC1675 " + getSysDate(), "1675 " + getSysDate() + 100
        };
        String[] evrakNo1675 = new String[2];

        String[] konu2 = new String[]{
                "TC1811 " + getSysDate(), "TC1811 " + getSysDate() + 100
        };


        String tur = "İmzalama";
        String geregiTipi = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu[0];
        String postaListesi2 = konu2[0];
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String hesaplananTutar;
        String number = createRandomNumber(4);
String tutar = "20.000";

        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };
//        //endregion
        Allure.addAttachment("Test Datası", "Test Datası oluşturuluyor.");
//        region Test Datası
        for (int i = 0; i < 2; i++) {
            evrakOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduSec(konuKodu)
                    .konuDoldur(konu[i])
                    .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                    .evrakTuruSec(evrakTuru)
                    .evrakDiliSec(evrakDili)
                    .gizlilikDerecesiSec(gizlilikDerecesi)
                    .ivedilikSec(ivedilik)
                    .geregiSecimTipiSecByText(geregiTipi)
                    .geregiSec(geregi)
                    .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
                    .aciklamaDoldur(konu[i])
                    .onayAkisiEkle()
                    .onayAkisiEkleIlkImzalaSec(tur)
                    .kullan();

            evrakOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(konu[i]);
            mainPage
                    .evrakImzala();
            imzaladiklarimPage
                    .openPage();
            evrakNo1675[i] = imzaladiklarimPage.evrakIcerikKontroluveEvrakNoAl(konu[i]);
        }


        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTumunuSec(true)
                .postaListesineAktar()
                .listeAdiDoldur(konu[0])
                .listeOlustur()
                .postaListesiSec(konu[0])
                .listeyeEkle();


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .evrakSec(konu[0])
                .postaListesiPostala()
                .postaDetayiGonderildigiYer(geregiTipi)
                .adresDoldur(adres)
                .gramajDoldur(gramaj1)
                .tutarHesapla();
        hesaplananTutar = postaListesiPage.tutarAl();
        hesaplananTutar=hesaplananTutar.substring(0,5);

        postaListesiPage.postaDetayiPostala();
        //endregion
        Allure.addAttachment("Test Datası", "Test Datası oluşturuldu.");
//        String postaListesi = "TC1811 20180112161120";
        topluPostaladiklarimPage
                .openPage()
                .topluPostaladiklarimTabloKontrolu()
                .postaListesiKontrol(postaListesi, "", getSysDateForKis(), gramaj1, hesaplananTutar, true)
                .filtrePaneliAc()
                .postaListesiAdiDoldur(postaListesi)
                .filtrele()
                .topluPostaladiklarimTabloKontrolu(postaListesi)

                .temizle()
                .postaListesiAdiDoldur("Posta Listesi")
                .filtrele()
                .topluPostaladiklarimTabloKontrolu("Posta Listesi")
                .temizle()

                .evrakSayisiDoldur(evrakNo1675[0])
                .filtrele()
                .topluPostaladiklarimTabloKontrolu(evrakNo1675[0])
                .temizle()
                .evrakSayisiDoldur(number)
                .filtrele()
                .topluPostaladiklarimTabloKontrolu(number)
                .temizle()

                .postaListesiAdiDoldur(postaListesi)
                .evrakSayisiDoldur(evrakNo1675[0])
                .postaTarihiDoldur(getSysDateForKis())
                .filtrele()
                .topluPostaladiklarimTabloKontrolu(postaListesi)
                .temizle()

                .postaListesiAdiDoldur("Posta Listesi")
                .evrakSayisiDoldur(number)
                .postaTarihiDoldur(getSysDateForKis())
                .filtrele()
                .topluPostaladiklarimTabloKontrolu("Posta Listesi")
                .temizle()

                .postaListesiAdiDoldur(postaListesi)
                .filtrele()
                .topluPostaladiklarimEvrakSec(konu[0])
                .postaDetayiAlanKontrolleri(postaListesi, adres, gramaj1, hesaplananTutar);


//        1811 in stepleri tekrar ediliyor

        //region Test Datası
        for (int i = 0; i < 2; i++) {
            evrakOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduSec(konuKodu)
                    .konuDoldur(konu2[i])
                    .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                    .evrakTuruSec(evrakTuru)
                    .evrakDiliSec(evrakDili)
                    .gizlilikDerecesiSec(gizlilikDerecesi)
                    .ivedilikSec(ivedilik)
                    .geregiSecimTipiSecByText(geregiTipi)
                    .geregiSec(geregi)
                    .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
                    .onayAkisiEkle()
                    .onayAkisiEkleIlkImzalaSec(tur)
                    .kullan();

            evrakOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(konu2[i]);
            mainPage
                    .evrakImzala();
        }

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTumunuSec(true)
                .postaListesineAktar()
                .listeAdiDoldur(konu2[0])
                .listeOlustur()
                .postaListesiSec(konu2[0])
                .listeyeEkle();

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi2)
                .evrakSec(konu2[0])
                .postaListesiPostala()
                .postaDetayiGonderildigiYer(geregiTipi)
                .adresDoldur(adres)
                .gramajDoldur(gramaj1)
                .tutarHesapla();
        hesaplananTutar=postaListesiPage.tutarAl();
        hesaplananTutar=hesaplananTutar.substring(0,5);
        postaListesiPage
                .postaDetayiPostala();
        //endregion
        topluPostaladiklarimPage
                .openPage()
                .topluPostaladiklarimTabloKontrolu()
                .postaListesiKontrol(postaListesi2, "", getSysDateForKis(), gramaj1, hesaplananTutar, true)
                .filtrePaneliAc()
                .postaListesiAdiDoldur(postaListesi2)
                .filtrele()
                .topluPostaladiklarimTabloKontrolu(postaListesi2)
                .topluPostaladiklarimEvrakSec(konu2[0])
                .postaDetayiAlanKontrolleri(postaListesi2, adres, gramaj1, hesaplananTutar)
                .etiketBastir()
                .etiketBastirEkraniKontrolü(adres, geregi)
                .etiketBastirEkraniKapat()
                .evrakListesiYazdir(konu2)
                .evrakListesiOrjinaliYazdir(konu2);

    }

    @Test(enabled = true, description = "TS2087 : Toplu postaladıklarım listesinden evrakın geri alınması")
    public void TS2087() {

        String tarihBugun = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String listeAdi = "Liste" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String gidecegiYer = "Yenikurum6507";
        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2015";
        String evrakKayitTarihiSayi = tarihBugun;
        String evrakGidecegiYer = "Yenikurum6507(G)";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";
        String evrakKonu = "TS1804-" + getRandomNumber(1000, 9000);

        login("mbozdemir", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(gidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1804 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();
        String evrakKonu2 = "TS1804-" + getRandomNumber(1000, 9000);
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("Entegrasyon İşlemleri")
                .konuDoldur(evrakKonu2)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(gidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1804 için ikinci evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(gidecegiYer)
                .gidecegiYerSec(gidecegiYer, true)
                .sorgula();

        String gorunmeyecekEvrakSayi = topluPostalanacakEvraklarPage.evrakSayiGetir(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi);
        String gorunecekEvrakSayi = topluPostalanacakEvraklarPage.evrakSayiGetir(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu2, evrakHazirlayanBirim, evrakPostaTipi);

        //String evrakSayi =
        topluPostalanacakEvraklarPage
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu2, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        String postaListesiAdi = listeAdi;
        String postaTarihi = tarihBugun;
        String postaGramaji = "";
        String pttTutari = "";
        String evrakGonderildigiYer = evrakGidecegiYer;
        String hazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String postaTipi = "Adi Posta";
        String postaTarih = tarihBugun;
        String geriAlAciklama = "CASE TS2087";
        String gonderen = "Mehmet BOZDEMİR";
        String yeniPostaListesiAdi = "Liste" + (new Random().nextInt((900000 - 100000) + 1) + 100000);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .postaDetayiPostala();

        topluPostaladiklarimPage
                .openPage()
                .postaListesiSec(postaListesiAdi, "", postaTarihi, postaGramaji, pttTutari)
                .evrakSil(evrakGonderildigiYer, evrakKonu, "")
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(gidecegiYer)
                .gidecegiYerSec(gidecegiYer, true)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGonderildigiYer, evrakKonu, hazirlayanBirim, postaTipi, true);

        pttRaporuPage
                .openPage()
                .aramaDetaylariPanelAc()
                .postaTarihiDoldur(postaTarih)
                .sorgula()
                .tabloKontrolEt(gidecegiYer, gorunmeyecekEvrakSayi, postaTipi, false)
                .tabloKontrolEt(gidecegiYer, gorunecekEvrakSayi, postaTipi, true);

        imzaladiklarimPage
                .openPage()
                .filtrePanelAc()
                .gidecegiYerSec(gidecegiYer)
                .filtrePanelAc()
                .baslangicTarihiDoldur(postaTarih)
                .bitisTarihiDoldur(postaTarih)
                .evrakSec(evrakKonu, gidecegiYer, postaTarih, gorunmeyecekEvrakSayi)
                .geriAl()
                .geriAlAciklamaDoldurVeOnayla(geriAlAciklama)
                .islemMesaji().basariliOlmali();


        imzaBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, gidecegiYer, gonderen, gorunmeyecekEvrakSayi)
                .imzala()
                .sImzaSec()
                .sImzaImzala(true);


        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(gidecegiYer)
                .gidecegiYerSec(gidecegiYer, true)
                .sorgula()
                .evrakSec(evrakKayitTarihiSayi, gidecegiYer, evrakKonu, hazirlayanBirim, postaTipi)
                .evrakTikSec(evrakKayitTarihiSayi, gidecegiYer, evrakKonu, hazirlayanBirim, postaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(yeniPostaListesiAdi)
                .listeOlustur()
                .postaListesiSec(yeniPostaListesiAdi)
                .listeyeEkle();

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(yeniPostaListesiAdi)
                .evrakSec(evrakKayitTarihiSayi, gidecegiYer, evrakKonu, hazirlayanBirim, postaTipi)
                .postaListesiPostala()
                .postaDetayiPostala();

        //postaGramaji = "0";
        //pttTutari = "0.00";

        topluPostaladiklarimPage
                .openPage()
                .postaListesiSec(yeniPostaListesiAdi, "", postaTarihi, postaGramaji, pttTutari)
                .evrakKontrol(gidecegiYer, evrakKonu, gorunmeyecekEvrakSayi);

    }

    @Test(enabled = true, description = "TS1809 : Posta Listesi Görüntüleme (UC_POSTAYÖNETİMİ_003)")
    public void TS1809() {

        String kurum1 = "Yenikurum6507";


        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "";
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS1809-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String listeAdi = "TS1809Liste-" + getRandomNumber(1000, 9999);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1809 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        String postalanmisListeAdi = topluPostaladiklarimPage
                .openPage()
                .postaListesiAdiGetir(0);

        postalanmisListeAdi = postalanmisListeAdi.substring(postalanmisListeAdi.indexOf("Posta Listesi Adı:") + 18, postalanmisListeAdi.indexOf("Posta Kodu:") - 1);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiKontrol(postalanmisListeAdi, false);


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .postaListesindenCikart(evrakGidecegiYer, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .islemMesaji().basariliOlmali();

        listeAdi = "TS1809Liste-" + getRandomNumber(1000, 9999);

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakKontrol(evrakGidecegiYer, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrakGidecegiYer, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        String gonderildigiYer = "Kurum";

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .postaListesiAdiKontrol(listeAdi, true)
                .gonderildigiYerKontrol(gonderildigiYer, true)
                .gonderildigiKurumKontro(evrakGidecegiYer, true)
                .adresKontrol("yeni adersim", true)
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true)
                .etiketBastir();


    }

    @Test(enabled = true, description = "TS1817 : Tutar alanı kontrol edilir.")
    public void TS1817() {

        String kurum1 = "Yenikurum6507";

        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakPostaTipi = "Adi Posta";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        String listeAdi = "TS1817Liste-" + getRandomNumber(1000, 9000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .tutarKontrol(tutar, true);


    }

    @Test(enabled = true, description = "TS1818 : Posta Tutarı Hesaplama İşlemleri ve Güncelleme (UC_POSTAYÖNETİMİ_006)")
    public void TS1818() {

        String kurum1 = "Yenikurum6507";

        login("mbozdemir", "123");

        String evrakKayitTarihiSayi = "" + new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        String evrakGidecegiYer = kurum1;
        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String evrakKonu = "TS01805-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String evrakPostaTipi = "Adi Posta";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        String listeAdi = "TS1817Liste-" + getRandomNumber(1000, 9000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(evrakKonuKodu)
                .konuDoldur(evrakKonu)
                .kaldiralacakKlasorlerSec("Diğer")
                .geregiSecimTipiSec("Kurum")
                .geregiSec(evrakGidecegiYer)
                .geregiKurumPostaTipi(evrakPostaTipi)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR [Antalya İl Müdürü]", "İmzalama")
                .kullan();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS1805 için evrak.")
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .popupSimzaEvet();

        topluPostalanacakEvraklarPage
                .openPage()
                .filtreGidecegiYer(evrakGidecegiYer)
                .gidecegiYerSec(evrakGidecegiYer, true)
                .sorgula()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .postaListesiSec(listeAdi)
                .listeyeEkle();

        int gramaj = 1999;
        double tutarInt = 200;
        int indirimOrani = 50;
        double indirimSonrasiTutarInt = tutarInt - (tutarInt * indirimOrani / 100);

        String indirimOncesiTutar = String.format("%.2f", tutarInt);
        String tutar = String.format("%.3f", indirimSonrasiTutarInt);

        int gramaj2 = 2499;
        double tutarInt2 = 250;
        int indirimOrani2 = 5;
        double indirimSonrasiTutarInt2 = tutarInt2 - (tutarInt2 * indirimOrani2 / 100);

        String indirimOncesiTutar2 = String.format("%.2f", tutarInt2);
        String tutar2 = String.format("%.3f", indirimSonrasiTutarInt2);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur(gramaj + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .indirimOraniKontrol(indirimOrani + "", true)
                .tutarKontrol(tutar, true)
                .gramajDoldur(gramaj2 + "")
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar2, true)
                .indirimOraniKontrol(indirimOrani2 + "", true)
                .tutarKontrol(tutar2, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1811 : Posta Listesi Postalama İşlemleri (UC_POSTAYÖNETİMİ_003)")
    public void TS1811() throws InterruptedException {

//        String[] konu = new String[]{
//                "TC1811 20180108121603", "TC1811 20180108120329100"
//        };

        login("mbozdemir", "123");
        //region Parameters
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String geregi = "Esk Kurum 071216 2";
        String adres = "adres " + getSysDateForKis();
        String[] konu = new String[]{
                "TC1811 " + getSysDate(), "TC1811 " + getSysDate() + 100
        };
        String tur = "İmzalama";
        String geregiTipi = "Kurum";
        String basariMesaji = "İşlem başarılıdır!";
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu[0];
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String tutar = "120";
        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };
        //endregion
        //region Test Datası
        for (int i = 0; i < 2; i++) {
            evrakOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduSec(konuKodu)
                    .konuDoldur(konu[i])
                    .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                    .evrakTuruSec(evrakTuru)
                    .evrakDiliSec(evrakDili)
                    .gizlilikDerecesiSec(gizlilikDerecesi)
                    .ivedilikSec(ivedilik)
                    .geregiSecimTipiSecByText(geregiTipi)
                    .geregiSec(geregi)
                    .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
                    .onayAkisiEkle()
                    .onayAkisiEkleIlkImzalaSec(tur)
                    .kullan();

            evrakOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(konu[i]);
            mainPage
                    .evrakImzala();
        }

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTumunuSec(true)
                .postaListesineAktar()
                .listeAdiDoldur(konu[0])
                .listeOlustur()
                .postaListesiSec(konu[0])
                .listeyeEkle();
        //endregion

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .evrakSec(konu[0])
//                .evrakOnizlemeKontrolu()
                .konuyaGorePostaListesindenCikart(konu[1]);

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .konuyaGoreEvrakKontrolu(konu[1], true);

        postaListesiPage
                .openPage()
                .filtreleAc()
//                .postaListesiDoldur(konu[0])
                .postaListesiDoldur(postaListesi)
                .evrakSec(konu[0])
                .postaListesiPostala()
                .postaListesiAdiKontrolu(konu[0])
                .postaListesiBarkodNoDoldur(createRandomNumber(5))
                .gonderildigiYerKontrol(geregiTipi, true)
                .gonderildigiKurumKontrolu(geregi, true)
                .postaDetayiGonderildigiYer(geregiTipi)
                .adresDoldur(adres)
                .gidisSekliSec("İadeli Taahhütlü")
                .gonderildigiYerSec("Yurt İçi")
                .gramajDoldur("deneme")

                .gramajDoldur(gramaj1)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("50.00", true)
                .indirimOraniKontrol("10", true)
                .tutarKontrol("45.000", true)

                .etiketBastir()
                .etiketBastirEkraniKontrolü(adres, geregi)
                .etiketBastirEkraniKapat()
                .evrakListesiYazdir(konu);
//        TODO
//        Son 3 step yazılacak...
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1812 : Posta Listesi Postalama İşlemleri (Güncelleme) (UC_POSTAYÖNETİMİ_003)")
    public void TS1812() throws InterruptedException {

        login("mbozdemir", "123");
        //region Parameters
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String geregi = "Sezai Çelik Holding";
        String adres = "adres " + getSysDateForKis();
        String[] konu = new String[]{
                "TC1812 " + getSysDate(), "TC1812 " + getSysDate() + 100
        };
        String tur = "İmzalama";
        String geregiTipi = "Tüzel Kişi";
        String basariMesaji = "İşlem başarılıdır!";
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu[0];
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String tutar = "120";
        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };
//        //endregion
        //region Test Datası
        for (int i = 0; i < 2; i++) {
            evrakOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduSec(konuKodu)
                    .konuDoldur(konu[i])
                    .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                    .evrakTuruSec(evrakTuru)
                    .evrakDiliSec(evrakDili)
                    .gizlilikDerecesiSec(gizlilikDerecesi)
                    .ivedilikSec(ivedilik)
                    .geregiSecimTipiSecByText(geregiTipi)
                    .geregiSec(geregi)
                    .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
                    .onayAkisiEkle()
                    .onayAkisiEkleIlkImzalaSec(tur)
                    .kullan();

            evrakOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(konu[i]);
            mainPage
                    .evrakImzala();
        }

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakTumunuSec(true)
                .postaListesineAktar()
                .listeAdiDoldur(konu[0])
                .listeOlustur()
                .postaListesiSec(konu[0])
                .listeyeEkle();
        //endregion

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .evrakSec(konu[0])
//                .evrakOnizlemeKontrolu()
                .konuyaGorePostaListesindenCikart(konu[1]);

        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
                .postaTipiSec(postaTipleri)
                .sorgula()
                .konuyaGoreEvrakKontrolu(konu[1], true)
                .evrakSec(konu[1], true)
                .postaListesineAktar()
                .postaListesiSec(postaListesi)
                .listeyeEkle();

//                String[] konu = new String[]{
//                "TC1811 20180104150244", "TC1811 20180104150243100"
//        };

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(konu[0])
//                .postaListesiDoldur(postaListesi)
                .evrakSec(konu[0])
                .postaListesiPostala()
                .postaListesiAdiDegistirme(konu[1])
                .postaListesiBarkodNoDoldur(createRandomNumber(5))
                .gonderildigiYerKontrol(geregiTipi, true)
                .gonderildigiKurumKontrolu(geregi, true)
                .postaDetayiGonderildigiYer(geregiTipi)
                .adresDoldur(adres)
                .gidisSekliSec("Kurye")
                .gidisSekliSec("Ankara İçi APS")
                .gonderildigiYerSec("Yurt İçi")
                .gramajDoldur("deneme")

                .gramajDoldur(gramaj1)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("50.00", true)
                .indirimOraniKontrol("10", true)
                .tutarKontrol("45.000", true)

                .gramajDoldur(gramaj3)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("100.00", true)
                .indirimOraniKontrol("20", true)
                .tutarKontrol("80.00", true)

                .etiketBastir()
                .etiketBastirEkraniKontrolü(adres, geregi)
                .etiketBastirEkraniKapat()
                .evrakListesiYazdir(konu);

        //        TODO
//        Son 3 step yazılacak...
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1816 : Toplu Postaladıklarım Güncelleme İşlemleri (UC_POSTAYÖNETİMİ_004)")
    public void TC1816() throws InterruptedException {


        String[] konu = new String[]{
                "TC1816 20180115150635", "TC1816 20180115150635100"
        };

        login("mbozdemir", "123");
        //region Parameters
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String geregi = "Ahmet Çelik";
        String adres = "adres " + getSysDateForKis();
//        String[] konu = new String[]{
//                "TC1816 " + getSysDate(), "TC1816 " + getSysDate() + 100
//        };
        String tur = "İmzalama";
        String geregiTipi = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu[0];
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String tutar = "120";
        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };
        //endregion
        Allure.addAttachment("Test Datası", "Test Datası oluşturuluyor.");
////        region Test Datası
//        for (int i = 0; i < 2; i++) {
//            evrakOlusturPage
//                    .openPage()
//                    .bilgilerTabiAc()
//                    .konuKoduSec(konuKodu)
//                    .konuDoldur(konu[i])
//                    .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
//                    .evrakTuruSec(evrakTuru)
//                    .evrakDiliSec(evrakDili)
//                    .gizlilikDerecesiSec(gizlilikDerecesi)
//                    .ivedilikSec(ivedilik)
//                    .geregiSecimTipiSecByText(geregiTipi)
//                    .geregiSec(geregi)
//                    .gercekKisiGeregiAlaniPostaTipiSec(gidisSekli)
//                    .onayAkisiEkle()
//                    .onayAkisiEkleIlkImzalaSec(tur)
//                    .kullan();
//
//            evrakOlusturPage
//                    .editorTabAc()
//                    .editorIcerikDoldur(konu[i]);
//            mainPage
//                    .evrakImzala();
//        }
//
//        topluPostalanacakEvraklarPage
//                .openPage()
//                .tarihAraligiSec(getSysDateForKis(), getSysDateForKis())
//                .postaTipiSec(postaTipleri)
//                .sorgula()
//                .evrakTumunuSec(true)
//                .postaListesineAktar()
//                .listeAdiDoldur(konu[0])
//                .listeOlustur()
//                .postaListesiSec(konu[0])
//                .listeyeEkle();
//
//
//        postaListesiPage
//                .openPage()
//                .filtreleAc()
//                .postaListesiDoldur(postaListesi)
//                .evrakSec(konu[0])
//                .postaListesiPostala()
//                .postaDetayiGonderildigiYer(geregiTipi)
//                .adresDoldur(adres)
//                .gramajDoldur(gramaj1)
//                .tutarHesapla()
//                .postaDetayiPostala();
//        //endregion
        Allure.addAttachment("Test Datası", "Test Datası oluşturuldu.");
//        String postaListesi = "TC1811 20180112161120";
        topluPostaladiklarimPage
                .openPage()
                .topluPostaladiklarimPostaListesiKontrol(postaListesi, "", getSysDateForKis(), gramaj1, "45.000", true)
                .topluPostaladiklarimEvrakSec(postaListesi)
                .evrakListesiYazdir(konu)
                .evrakListesiYazdirPdfKontrol(konu)//kontrol amaçlı konuldu.
                .evrakListesiOrjinaliYazdir(konu)
                .tutarGuncelle("65.00")
                .guncelle()
                .topluPostaladiklarimEvrakSec(postaListesi)
                .postaListesiAdiDegistirme(konu[1])
                .postaListesiBarkodNoDoldur(createRandomNumber(5))
                .gonderildigiYerSec("Kurum")
                .adresDoldur(adres)
                .gidisSekliSec("Kurye")
                .gidisSekliSec("Ankara İçi APS")
                .gonderildigiYerSec2("Yurt İçi")
                .tutarHesapla()
                .indirimOraniDoldur("20")
                .indirimSonrasiTutarKontrol(20)
                .gramajDoldur(gramaj3)
                .tutarHesapla()
                .indirimOncesiTutarKontrol("100.00", true)
                .indirimOraniKontrol("20", true)
                .tutarKontrol("80.00", true)
                .etiketBastir()
                .etiketBastirEkraniKontrolü(adres, geregi)
                .etiketBastirEkraniKapat();

        //TODO
        //17.step
    }
}