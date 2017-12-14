package TopluPostalama;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;


public class TopluPostalamaTest extends BaseTest {


    TopluPostalanacakEvraklarPage topluPostalanacakEvraklarPage;
    PostaListesiPage postaListesiPage;
    PostalananlarPage postalananlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        postalananlarPage = new PostalananlarPage();
        topluPostalanacakEvraklarPage = new TopluPostalanacakEvraklarPage();
        postaListesiPage = new PostaListesiPage();
    }

    // Not finished
    @Test(enabled = true, description = "1804 : Toplu Postalanacak Evrakların Sorgulanması (UC_POSTAYÖNETİMİ_001)")
    public void TC01804() {


        login("mbozdemir", "123");
        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu();
        //.gidecegiYerSec("Başbakan Başmüşavirleri", true);


    }


    // Not finished
    @Test(enabled = true, description = "1808 : Posta Listesine Evrak Ekleme ve Çıkartma (UC_POSTAYÖNETİMİ_002)")
    public void TC01808() {

        String[] gidecegiYerler = new String[] {
                "Adalet Bakanlığı",
                "Adalet Bakanlığı Döner Sermaye İşletmesi",
                "Aile ve Sosyal Politikalar Bakanlığı",
                "Başbakan Başmüşavirleri"
        };

        String[] postaYerleri = new String[] {
                "Adi Posta"
        };

        String baslangicTarihi = "01.12.2017";
        String bitisTarihi = "01.12.2018";

        String postaListesi = "deneme";


        // Seçilecek evraklar >>

        String evrak1KayitTarihiSayi = "11.12.2017 15:40:14 / 9180";
        String evrak1Sayi = "9180";
        String evrak1GidecegiYer = "Aile ve Sosyal Politikalar Bakanlığı(G)";
        String evrak1Konu = "Entegrasyon İşlemleri";
        String evrak1HazirlayanBirim = "Optiim Birim";
        String evrak1PostaTipi = "Adi Posta";

        String evrak2KayitTarihiSayi = "11.12.2017 11:10:07 / 9174";
        String evrak2Sayi = "9174";
        String evrak2GidecegiYer = "Adalet Bakanlığı(G)";
        String evrak2Konu = "Cihaz Takip İşlemleri";
        String evrak2HazirlayanBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrak2PostaTipi = "Adi Posta";

        // Seçilecek evraklar <<

        String basariMesaji = "İşlem başarılıdır!";
        String dikkatMesaji = "Posta Listesine eklenecek evrak seçiniz!";

        login("mbozdemir", "123");


        /*

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
                .listeyeEkle()
                .islemMesaji().basariliOlmali(basariMesaji);




        topluPostalanacakEvraklarPage
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true, true)
                .postaTipiSec(postaYerleri)
                .sorgula()
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, false)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, false);
        */



        evrak1KayitTarihiSayi = "11.12.2017 15:39:40 / 9180";
        evrak2KayitTarihiSayi = "11.12.2017 11:09:46 / 9174";

        /*
        postaListesiPage
                .openPage()
                .filtreleAc()
                .postaListesiDoldur(postaListesi)
                .evrakKontrol(evrak1KayitTarihiSayi, evrak1GidecegiYer, evrak1Konu, evrak1HazirlayanBirim, evrak1PostaTipi, true)
                .evrakKontrol(evrak2KayitTarihiSayi, evrak2GidecegiYer, evrak2Konu, evrak2HazirlayanBirim, evrak2PostaTipi, true)
                .postaListesiPostala()
                .evrakListesiKontrol(evrak1GidecegiYer, "9180")
                .evrakListesiKontrol(evrak2GidecegiYer, "9174")
                .postaDetayiPostala();

         */


        postalananlarPage
                .openPage()
                .evrakSec(evrak1Konu, evrak1GidecegiYer, "", evrak1Sayi)
                .postaDetayiTikla()
                .evrakSec(evrak2Konu, evrak2GidecegiYer, "", evrak2Sayi)
                .postaDetayiTikla();





    }




}