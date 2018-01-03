package tests.TopluPostalama;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PttRaporuPage;

import java.util.Random;


public class TopluPostalamaTest extends BaseTest {

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
    }

    @Test(enabled = true, description = "1804 : Toplu Postalanacak Evrakların Sorgulanması (UC_POSTAYÖNETİMİ_001)")
    public void TC01804() {

        String[] gidecegiYerler = new String[]{
                "Adalet Bakanlığı",
                "Başbakan Başmüşavirleri"
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2015";

        String evrakKayitTarihiSayi = "14.12.2017 12:44:59 / 9230";
        String evrakGidecegiYer = "BÜYÜK HARFLERLE KURUM(G)";
        String evrakKonu = "K/Frekans Yıllık Kullanım Ücreti";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String listeAdi = "Liste" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        login("mbozdemir", "123");


        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula();
        //.islemMesaji().uyariOlmali("");

        baslangicTarihi = "01.12.2016";
        bitisTarihi = "02.12.2018";

        topluPostalanacakEvraklarPage
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .postaTipiSec(postaTipleri)
                .gidecegiYerTumunuIsaretle(false)
                .sorgula();
        //.islemMesaji().uyariOlmali("");

        topluPostalanacakEvraklarPage
                .gidecegiYerTumunuIsaretle(true)
                .sorgula();

        topluPostalanacakEvraklarPage
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakGoster()
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesineAktar()
                .gidecegiYerTumunuIsaretle(true)
                .sorgula()
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .postaListesiSec(listeAdi)
                .listeyeEkle();


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesiPostala()
                .evrakListesiKontrol(evrakGidecegiYer, "");

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, false);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .postaListesindenCikart("", evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .islemMesaji().basariliOlmali();


        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaTipleri)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true);

    }

    @Test(enabled = true, description = "1808 : Posta Listesine Evrak Ekleme ve Çıkartma (UC_POSTAYÖNETİMİ_002)")
    public void TC01808() {

        String[] gidecegiYerler = new String[]{
                "Adalet Bakanlığı Döner Sermaye İşletmesi",
                "Başbakanlık",
                "Başbakan Müşavirleri"
        };

        String[] postaYerleri = new String[]{
                "Adi Posta"
        };

        String baslangicTarihi = "01.12.2017";
        String bitisTarihi = "01.12.2018";

        String postaListesi = "Liste5761";


        String evrak1KayitTarihiSayi = "9181";
        String evrak1Sayi = "9181";
        String evrak1GidecegiYer = "Başbakanlık(G)";
        String evrak1Konu = "Entegrasyon İşlemleri";
        String evrak1HazirlayanBirim = "Optiim Birim";
        String evrak1PostaTipi = "Adi Posta";

        String evrak2KayitTarihiSayi = "9232";
        String evrak2Sayi = "9232";
        String evrak2GidecegiYer = "Başbakan Müşavirleri(B)";
        String evrak2Konu = "Veri Toplama";
        String evrak2HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak2PostaTipi = "Adi Posta";

        // Seçilecek evraklar <<

        String basariMesaji = "İşlem başarılıdır!";
        String dikkatMesaji = "Posta Listesine eklenecek evrak seçiniz!";


        login("mbozdemir", "123");


        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true)
                .postaTipiSec(postaYerleri)
                .sorgula()
                .postaListesineAktar()
                .postaListesiSec(postaListesi)
                .listeyeEkle()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        topluPostalanacakEvraklarPage
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
                .postaTipiSec(postaYerleri)
                .sorgula()
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, false)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, false);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
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
                .postaTipiSec(postaYerleri)
                .sorgula()
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, true);
    }

    @Test(enabled = true, description = "1807 : Posta Listesi Oluşturma- Tüzel Kişi Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TC01807() {

        String[] gidecegiYerler = new String[]{
                "mehmet ali"
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String tuzelKisi = "mehmet ali";

        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";

        String evrakKayitTarihiSayi = "9246";
        String evrakGidecegiYer = "mehmet ali(G)";
        String evrakKonu = "İstatistikler";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String evrak1KayitTarihiSayi = "9252";
        String evrak1GidecegiYer = "dfyfzek holding(G)";
        String evrak1Konu = "memo";
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";


        String listeAdi = "tc01807-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

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
                .tuzelKisiKontrolet(tuzelKisi)
                .listeAdiDoldur(listeAdi)
                .seciliTuzelKisiKaldir()
                .gonderildigiTuzelKisiSec(tuzelKisi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();


        postaListesiPage
                .openPage();


        gidecegiYerler = new String[]{
                "mehmet ali",
                "dfyfzek holding"
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
                .listeOlustur()
                .islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "tc01807-1-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala();
    }

    @Test(enabled = true, description = "1807 : Posta Listesi Oluşturma- Gerçek Kişi Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TC01807A() {

        String[] gidecegiYerler = new String[]{
                "Ahmet Çelik"
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String gercekKisi = "Ahmet Çelik";


        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";

        String evrakKayitTarihiSayi = "9256";
        String evrakGidecegiYer = "Ahmet Çelik(GEREĞİ İÇİN GÖNDER)";
        String evrakKonu = "Yörünge ve Spektrum İşlemleri";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String evrak1KayitTarihiSayi = "9257";
        String evrak1GidecegiYer = "Arif Hakan(GEREĞİ İÇİN GÖNDER)";
        String evrak1Konu = "Tanıtım ve Yayın İşleri (Genel)";
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";


        String listeAdi = "tc01807-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

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
                "Ahmet Çelik",
                "Arif Hakan"
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
                .listeOlustur()
                .islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "tc01807-2-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala();

    }

    @Test(enabled = true, description = "1805 : Posta Listesi Oluşturma- Kurum Listesi (UC_POSTAYÖNETİMİ_002)")
    public void TC01805() {

        String[] gidecegiYerler = new String[]{
                "Cumhurbaşkanlığı"
        };

        String[] postaTipleri = new String[]{
                "Adi Posta"
        };

        String kurum = "Cumhurbaşkanlığı";

        String baslangicTarihi = "01.12.2015";
        String bitisTarihi = "02.12.2018";


        String evrakKayitTarihiSayi = "9258";
        String evrakGidecegiYer = "Cumhurbaşkanlığı(G)";
        String evrakKonu = "Bilgi İşlem İşleri (Genel)";
        String evrakHazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakPostaTipi = "Adi Posta";

        String evrak1KayitTarihiSayi = "9259";
        String evrak1GidecegiYer = "Çalışma ve Sosyal Güvenlik Bakanlığı(G)";
        String evrak1Konu = "Mali İşler (Genel)";
        String evrak1HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak1PostaTipi = "Adi Posta";


        String listeAdi = "tc01807-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

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
                .kurumKontrolet(kurum)
                .listeAdiDoldur(listeAdi)
                .seciliKurumKaldir()
                .gonderildigiKurumSec(kurum)
                .listeOlustur()
                .islemMesaji().basariliOlmali();


        postaListesiPage
                .openPage();


        gidecegiYerler = new String[]{
                "Cumhurbaşkanlığı",
                "Çalışma ve Sosyal Güvenlik Bakanlığı"
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
                .listeOlustur()
                .islemMesaji().dikkatOlmali("Bu Liste Adında posta listesi vardır. Yeni Liste Adı verin! ");

        listeAdi = "tc01807-1-" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        topluPostalanacakEvraklarPage
                .evrakTikSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi, true)
                .evrakTikSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .listeAdiDoldur(listeAdi)
                .listeOlustur()
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .postaListesiSec(listeAdi)
                .listeyeEkle();


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(listeAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGidecegiYer, evrakKonu, evrakHazirlayanBirim, evrakPostaTipi)
                .evrakSec(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi)
                .postaListesiPostala();

    }

    @Test(enabled = true, description = "1815A : Toplu postalama PTT raporunda alan kontrolleri-daha önceden rapor alındı ise (UC_POSTAYÖNETİMİ_004)")
    public void TC01815A() {

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
                .sorgula()
                .tabloKontrolEt(gittigiYer, evrakSayi, postaTipi, true);


    }

    @Test(enabled = true, description = "1815B : Toplu postalama PTT raporunda alan kontrolleri-daha önceden rapor alındı ise (UC_POSTAYÖNETİMİ_004)")
    public void TC01815B() {

        String uyariMesaji = "Zorunlu alanları doldurunuz";

        String postaTarihi = "13.12.2017";
        String postaTipi = "Adi Posta";
        String gittigiYer = "Enerjı ve Madencılık Daire Başkanlığı";
        String evrakSayi = "9174";

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
    public void TC01676() {

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
    @Test(enabled = true, description = "TC1675 : Toplu Postaladıklarım İzleme / Alan Kontrolleri (UC_POSTAYÖNETİMİ_004)")
    public void TC1675() {


        topluPostaladiklarimPage
                .openPage()
                .filtrePaneliAc()
                .postaListesiAdiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", true)

                .temizle()
                .postaListesiAdiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", false)

                .temizle()

                .evrakSayisiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", true)
                .evrakSayisiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", false)

                .temizle()
                .postaListesiAdiDoldur("")
                .evrakSayisiDoldur("")
                .postaTarihiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", true)

                .temizle()
                .postaListesiAdiDoldur("")
                .evrakSayisiDoldur("")
                .postaTarihiDoldur("")
                .filtrele()
                .postaListesiKontrol("", "", "", "", "", false);

    }

    @Test(enabled = true, description = "TC2087 : Toplu postaladıklarım listesinden evrakın geri alınması")
    public void TC2087() {

        String postaListesiAdi = "TC2087";
        String postaTarihi = "23.12.2017";
        String postaGramaji = "1000";
        String pttTutari = "787.50";

        String evrakGonderildigiYer = "BÜYÜK HARFLERLE KURUM(G)";
        String evrakKonu = "Anketler";
        String evrakSayi = "6345202-044-9647";

        String evrakKayitTarihiSayi = "23.12.2017";
        String hazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String postaTipi = "Adi Posta";
        String postaTarih = "23.12.2017";
        String gorunmeyecekEvrakSayi = "9647";
        String gorunecekEvrakSayi = "9645";

        String gidecegiYer = "BÜYÜK HARFLERLE KURUM";

        String geriAlAciklama = "CASE TC2087";

        String gonderen = "Mehmet BOZDEMİR";
        String yeniPostaListesiAdi = "Liste" + (new Random().nextInt((900000 - 100000) + 1) + 100000);


        topluPostaladiklarimPage
                .openPage()
                .postaListesiSec(postaListesiAdi, "", postaTarihi, postaGramaji, pttTutari)
                .evrakSil(evrakGonderildigiYer, evrakKonu, evrakSayi)
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerSec(evrakGonderildigiYer, true)
                .sorgula()
                .evrakKontrol(evrakKayitTarihiSayi, evrakGonderildigiYer, evrakKonu, hazirlayanBirim, postaTipi, true);

        pttRaporuPage
                .openPage()
                .aramaDetaylariPanelAc()
                .postaTarihiDoldur(postaTarih)
                .sorgula()
                .tabloKontrolEt(evrakGonderildigiYer, gorunmeyecekEvrakSayi, postaTipi, false)
                .tabloKontrolEt(evrakGonderildigiYer, gorunecekEvrakSayi, postaTipi, true);

        imzaladiklarimPage
                .openPage()
                .filtrePanelAc()
                .gidecegiYerSec(gidecegiYer)
                .baslangicTarihiDoldur(postaTarih)
                .bitisTarihiDoldur(postaTarih)
                .evrakSec(evrakKonu, evrakGonderildigiYer, postaTarih, evrakSayi)
                .geriAl()
                .geriAlAciklamaDoldurVeOnayla(geriAlAciklama)
                .islemMesaji().basariliOlmali();

        imzaBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGonderildigiYer, gonderen, evrakSayi)
                .imzala()
                .sImzaSec()
                .imzala()
                .sImzaImzala(true);


        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerSec(gidecegiYer, true)
                .sorgula()
                .evrakSec(evrakKayitTarihiSayi, evrakGonderildigiYer, evrakKonu, hazirlayanBirim, postaTipi)
                .evrakTikSec(evrakKayitTarihiSayi, evrakGonderildigiYer, evrakKonu, hazirlayanBirim, postaTipi, true)
                .postaListesineAktar()
                .listeAdiDoldur(yeniPostaListesiAdi)
                .listeOlustur()
                .postaListesiSec(postaListesiAdi)
                .listeyeEkle();

        postaListesiPage
                .filtreleAc()
                .postaListesiDoldur(yeniPostaListesiAdi)
                .evrakSec(evrakKayitTarihiSayi, evrakGonderildigiYer, evrakKonu, hazirlayanBirim, postaTipi)
                .postaListesiPostala()
                .postaDetayiPostala();

        postaGramaji = "0";
        pttTutari = "0.00";

        topluPostaladiklarimPage
                .openPage()
//                .postaListesiSec("" ,"" ,"","", "")
//                .evrakKontrol("", "", "");
                .postaListesiSec(yeniPostaListesiAdi, "", postaTarihi, postaGramaji, pttTutari)
                .evrakKontrol(evrakGonderildigiYer, evrakKonu, evrakSayi);
    }

    @Test(enabled = true, description = "TC1809 : Posta Listesi Görüntüleme (UC_POSTAYÖNETİMİ_003)")
    public void TC1809() {

        login("mbozdemir", "123");

        String postalanmisListeAdi = topluPostaladiklarimPage
                .openPage()
                .postaListesiAdiGetir(0);

        postalanmisListeAdi = postalanmisListeAdi.substring(postalanmisListeAdi.indexOf("Posta Listesi Adı:") + 18, postalanmisListeAdi.indexOf("Posta Kodu:") - 1);

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiKontrol(postalanmisListeAdi, false);

        String gidecegiYer1 = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String postaListesiAdi = "TC1809-01";
        String gonderildigiYer = "Kurum";
        String gonderildigiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi(G)";
        String adres = "1099314815";
        String gidisSekli = "Evrak Servisi Elden";
        String gramaj = "0";
        String indirimOncesiTutar = "140.00";
        String indirimOrani = "0";
        String tutar = "140.00";


        String kayitTarihiSayi = "9497";
        String gidecegiYer = "Adalet Bakanlığı Döner Sermaye İşletmesi(G)";
        String konu = "Kanunlar";
        String hazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String postaTipi = "Adi Posta";


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesiAdi)
                .evrakSec(kayitTarihiSayi, gidecegiYer, konu, hazirlayanBirim, postaTipi)
                .postaListesindenCikart(kayitTarihiSayi, gidecegiYer, konu, hazirlayanBirim, postaTipi)
                .islemMesaji().basariliOlmali();

        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerSec(gidecegiYer1, true)
                .sorgula()
                .evrakKontrol(kayitTarihiSayi, gidecegiYer, konu, hazirlayanBirim, postaTipi, true);


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesiAdi)
                .postaListesiPostala()
                .postaListesiAdiKontrol(postaListesiAdi, true)
                .gonderildigiYerKontrol(gonderildigiYer, true)
                .gonderildigiKurumKontro(gonderildigiKurum, true)
                .adresKontrol(adres, true)
                .gidisSekliKontrol(gidisSekli, true)
                .gramajKontrol(gramaj, true)
                .tutarHesapla()
                .indirimOncesiTutarKontrol(indirimOncesiTutar, true)
                .indirimOraniKontrol(indirimOrani, true)
                .tutarKontrol(tutar, true)
                .etiketBastir();

    }

    @Test(enabled = true, description = "TC1817 : Tutar alanı kontrol edilir.")
    public void TC1817() {

        login("mbozdemir", "123");


        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur("cubbada")
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur("1000")
                .tutarHesapla()
                .indirimOncesiTutarKontrol("1050.00", true)
                .indirimOraniKontrol("3", true)
                .tutarKontrol("1018.5000", true);


    }

    @Test(enabled = true, description = "TC1818 : Posta Tutarı Hesaplama İşlemleri ve Güncelleme (UC_POSTAYÖNETİMİ_006)")
    public void TC1818() {

        login("mbozdemir", "123");

        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur("cubbada")
                .postaListesiPostala()
                .gidisSekliSec("Adi Posta")
                .gramajDoldur("1000")
                .tutarHesapla()
                .indirimOncesiTutarKontrol("1050.00", true)
                .indirimOraniKontrol("0", true)
                .tutarKontrol("1050.00", true)
                .gramajDoldur("2000")
                .tutarHesapla()
                .indirimOncesiTutarKontrol("1050.00", true)
                .indirimOraniKontrol("0", true)
                .tutarKontrol("1050.00", true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Posta Listesi Postalama İşlemleri (UC_POSTAYÖNETİMİ_003)")
    public void TC1811() throws InterruptedException {

        login("mbozdemir", "123");
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String geregi = "Ahmet ÇELİK";
        String konu = "TC2214 " + getSysDate();
        String tur = "İmzalama";
        String geregiTipi = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
//        String konu = "TC2214 20180102112101";
        String postaListesi = konu;
        String gidisSekli = "Ankara İçi APS";
        String gramaj1 = "1";
        String indirimOrani = "20";
        String gramaj3 = "3";
        String gramaj5 = "5";
        String tutar = "120";
        String[] postaTipleri = new String[]{
                "Ankara İçi APS"
        };

        //        Test datası oluşturuluyor

        for (int i = 0; i < 2; i++) {
            evrakOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduSec(konuKodu)
                    .konuDoldur(konu)
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
                    .editorIcerikDoldur(konu)
                    .imzala()
                    .sImzasec()
                    .sImzaImzala()
                    .sayisalImzaEvetPopup();
        }
    }
}