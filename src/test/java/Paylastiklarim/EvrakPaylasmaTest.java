package Paylastiklarim;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.BenimlePaylasilanlarPage;
import pages.solMenuPages.PaylastiklarimPage;
import pages.solMenuPages.TaslakEvraklarPage;


public class EvrakPaylasmaTest extends BaseTest {

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;
    TaslakEvraklarPage taslakEvraklarPage;


    @BeforeMethod
    public void loginBeforeTests() {
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        login("mbozdemir", "123");
    }

    @Test(enabled = true, description = "1881 : Evrak paylaşımını durdurma")
    public void TC_01881() {

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = "Optiim TEST";
        String basariMesaji = "İşlem başarılıdır!";


        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST")
                .paylasTabTikla()
                .paylasimiDurdur()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);

        logout();
        login("optiim", "Avis1111");

        benimlePaylasilanlarPage
                .openPage()
                .durdurulmusPaylasimlarSec()
                .filtrele()
                .evrakSec(paylasan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);


    }

    @Test(enabled = true, description = "1882 : Paylaştıklarım listesinden evrak paylaşma")
    public void TC_01882() {

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
                .evrakNotuKontrol(paylasanKisi, "",paylasanKisiNotAciklamasi);



    }


    @Test(enabled = true, description = "1877 : Paylaşılan evrakın geri alınması")
    public void TC_01877() {

        String paylasan = "Mehmet BOZDEMİR";
        String[] paylasilanlar = new String[] { "Zübeyde TEKİN", "Optiim TEST", "Esin Gül KARABACAKOĞLU" };
        String[] paylasimdanGeriAlanicaklar = new String[] { "Zübeyde TEKİN", "Esin Gül KARABACAKOĞLU" };
        String[] paylasimdanGeriAlinmayacaklar = new String[] { "Optiim TEST" };



        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilanlar)
                .paylasimdanGeriAlTabSec()
                .paylasimdanGeriAl(paylasimdanGeriAlanicaklar);


        logout();
        login("ztekin" , "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, false);


        logout();
        login("optiim" , "Avis1111");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, true);

    }

    @Test(enabled = true, description = "1876 : Evrakı paylaşmada alan kontrolleri")
    public void TC_01876() {


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

    @Test(enabled = true, description = "1876 : Taslak Evrakı kullanıcı ile paylaşma (Tümü aksiyonu ile)")
    public void TC_01876_A() {

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








    }


}