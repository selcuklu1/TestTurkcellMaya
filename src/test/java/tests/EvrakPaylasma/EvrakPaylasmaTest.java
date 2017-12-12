package tests.EvrakPaylasma;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.BenimlePaylasilanlarPage;
import pages.solMenuPages.ParafBekleyenlerPage;
import pages.solMenuPages.PaylastiklarimPage;
import pages.solMenuPages.TaslakEvraklarPage;


public class EvrakPaylasmaTest extends BaseTest {

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;
    TaslakEvraklarPage taslakEvraklarPage;
    ParafBekleyenlerPage parafBekleyenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login("mbozdemir", "123");
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
    }

    @Test(enabled = true, description = "TC1881 : Evrak paylaşımını durdurma")
    public void TC1881() {

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = "Optiim TEST";
        String basariMesaji = "İşlem başarılıdır!";


        paylastiklarimPage
                .openPage()
                .evrakSec("", "","Optiim TEST1 / Optiim TEST2","11.12.2017 13:07:26 ")
                .paylasTabTikla()
                .paylasimiDurdur()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);


        logout();
        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .durdurulmusPaylasimlarSec()
                .filtrele()
                .evrakSec(paylasan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);


    }

    @Test(enabled = true, description = "TC1882 : Paylaştıklarım listesinden evrak paylaşma")
    public void TC1882() {

        String evrakKonu = "";
        String evrakNo = "0";
        String paylasilanKullanici = "Optiim TEST";
        String paylasilmaTarihi = "04.12.2017 11:00:23";
        String paylasanKisi = "Mehmet BOZDEMİR";
        String paylasanKisiNotAciklamasi = "TC: 1882 Case açıklaması";

        String[] paylasilacakKullanicilar = new String[]{
                "Optiim TEST1",
                "Optiim TEST2",
                "Optiim TEST3"
        };

        String basariMesaji = "İşlem başarılıdır!";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, evrakNo, paylasilanKullanici, paylasilmaTarihi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasTabTikla()
                .paylasKisiSec(paylasilacakKullanicilar)
                .paylasimAciklamaYaz(paylasanKisiNotAciklamasi)
                .paylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, evrakNo, paylasilanKullanici, paylasilmaTarihi)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi, "", paylasanKisiNotAciklamasi);

    }

    @Test(enabled = true, description = "TC1877 : Paylaşılan evrakın geri alınması")
    public void TC1877() {

        String paylasan = "Mehmet BOZDEMİR";
        String[] paylasilanlar = new String[]{"Zübeyde TEKİN", "Optiim TEST", "Esin Gül KARABACAKOĞLU"};
        String[] paylasimdanGeriAlanicaklar = new String[]{"Zübeyde TEKİN", "Esin Gül KARABACAKOĞLU"};
        String[] paylasimdanGeriAlinmayacaklar = new String[]{"Optiim TEST"};


        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilanlar)
                .paylasimdanGeriAlTabSec()
                .paylasimdanGeriAl(paylasimdanGeriAlanicaklar);


        logout();
        login("ztekin", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, false);


        logout();
        login("optiim", "Avis1111");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, true);

    }

    @Test(enabled = true, description = "TC1876 : Evrakı paylaşmada alan kontrolleri")
    public void TC1876() {


        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST")
                .paylasTabTikla()
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");


        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("")
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        paylastiklarimPage
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


    }

    @Test(enabled = true, description = "TC1876A : Taslak Evrakı kullanıcı ile paylaşma (Tümü aksiyonu ile)")
    public void TC1876A() {


        String evrakKonu = "Personel İşleri (Genel)";
        String evrakGidecegiYer = "Optiim TEST1(B) / Optiim TEST2(G)";
        String evrakTarihSaat = "05.12.2017 15:45";

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);

        /*

        String secilecekEvrakKonu = "";
        String gidecegiYer = "";
        String secilecekEvrakTarihSaat = "21.11.2017 12:01";
        String paylasilacakKisi = "Optiim TEST";
        String paylasanAciklamasi = "Paylaşım açıklaması 03";
        String evrakNo = "0";

        String evrakNotuEkleyen = "Mehmet BOZDEMİR";
        String paylasan = "Mehmet BOZDEMİR";
        String evrakNotuTarih = "";

        String paylasilmaTarihi = "";


        taslakEvraklarPage
                .openPage()
                .evrakSec(secilecekEvrakKonu, gidecegiYer, secilecekEvrakTarihSaat)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilacakKisi)
                .paylasanAciklamaDoldur(paylasanAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        paylastiklarimPage
                .openPage()
                .evrakSec(secilecekEvrakKonu, evrakNo, paylasilacakKisi,"")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakNotuEkleyen, evrakNotuTarih, paylasanAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST", "Optiim Birim", "Paylaşımda", "");

        paylasilmaTarihi = paylastiklarimPage.paylasilmaTarihiGetir(secilecekEvrakKonu, evrakNo, paylasilacakKisi);

        logout();

        login("optiim", "Avis1111");

        String yeniEvrakNotu = "benimle paylaşılan not";


        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(paylasan, paylasilmaTarihi, secilecekEvrakKonu, evrakNo)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakNotuEkleyen, evrakNotuTarih, paylasanAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakNotu);


        logout();

        login("optiim", "Avis1111");

        evrakNotuEkleyen = "Optiim TEST [Ağ (Network) Uzman Yardımcısı]";
        evrakNotuTarih = "2017";


        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(paylasan, paylasilmaTarihi, secilecekEvrakKonu, evrakNo)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakNotuEkleyen, evrakNotuTarih, yeniEvrakNotu);


        */


    }

    @Test(enabled = true, description = "TC1904 : Evrak paylaşmada not kontrolü")
    public void TC1904() {

        String evrakKonu = "Bilimsel ve Kültürel Organizasyonlar, Toplantılar";
        String evrakGidecegiYer = "Optiim TEST2(B) / Optiim TEST1(G)";
        String evrakGonderen = "Mehmet BOZDEMİR";
        String evrakTarih = "05.12.2017 10:47";
        String basariMesaji = "İşlem başarılıdır!";
        String evrakiPaylasan = "Mehmet BOZDEMİR";

        String notEkleyen = "Optiim TEST [Ağ (Network) Uzman Yardımcısı]";
        String eklenenNot = "Optiim evrak notuuuuuussds";
        String paylasilan = "Optiim TEST";
        String evrakNo = "9132";

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .paylasTabTikla()
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("Deneme bir açıklama girdim.")
                .paylas()
                .islemMesaji().basariliOlmali(basariMesaji);


        logout();

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, evrakNo)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(eklenenNot);

        logout();

        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, evrakNo, paylasilan, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, "", eklenenNot);

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, "", eklenenNot, false);


    }

    @Test(enabled = true, description = "TC2195 : Cevap evrakını paylaşma")
    public void TC2195() {

        String evrakKonu = "Yurtiçi Projeler";
        String evrakGidecegiYer = "Başbakan Başmüşavirleri(G)";
        String evrakTarihSaat = "05.12.2017 16:42";

        String evrakAciklamasi = "Cevap evrakını paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Cevap evrakını paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2194 : İçeriğinde kişisel şablon olan evrakı paylaşma")
    public void TC2194() {

        String evrakKonu = "Faaliyet Raporları";
        String evrakGidecegiYer = "Optiim TEST(B) / Optiim TEST1(G) / Optiim TEST1(G)";
        String evrakTarihSaat = "05.12.2017 12:53";

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2192 : İçeriğinde form olan evrakı paylaşma")
    public void TC2192() {

        String evrakKonu = "Personel İşleri (Genel)";
        String evrakGidecegiYer = "Optiim TEST1(B) / Optiim TEST2(G)";
        String evrakTarihSaat = "05.12.2017 15:45";

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2193 : İçeriğinde birim içerik şablonu olan evrakı paylaşma")
    public void TC2193() {

        String evrakKonu = "Faaliyet Raporları";
        String evrakGidecegiYer = "Optiim TEST1(G) / Optiim TEST1(G) / Optiim TEST(B)";
        String evrakTarihSaat = "05.12.2017 16:04";

        String evrakAciklamasi = "İçeriğinde birim içerik şablonu olan evrakı paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "İçeriğinde birim içerik şablonu olan evrakı paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2197 : Devredilen evrakı paylaşma")
    public void TC2197() {

        logout();
        login("yakyol", "123");

        String evrakKonu = "Faaliyet Raporları";
        String evrakGidecegiYer = "Optiim TEST1(G) / Optiim TEST1(G) / Optiim TEST(B)";
        String evrakTarihSaat = "05.12.2017 16:55";

        String evrakAciklamasi = "Devredilen evrakı paylaşma NOT 1";
        String evrakiPaylasan = "Yasemin Çakıl AKYOL";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Devredilen evrakı paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }


}