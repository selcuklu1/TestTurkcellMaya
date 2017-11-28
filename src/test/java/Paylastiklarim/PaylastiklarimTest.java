package Paylastiklarim;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.BenimlePaylasilanlarPage;
import pages.solMenuPages.PaylastiklarimPage;
import pages.ustMenuPages.GelenEvraklarPage;


public class PaylastiklarimTest extends BaseTest {

    GelenEvraklarPage gelenEvraklarPage;
    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        login("mbozdemir", "123");
    }

    @Test(enabled = true, description = "1881 : Evrak paylaşımını durdurma")
    public void TC_01881() {

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = "Optiim TEST";


        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST")
                .paylasTabTikla()
                .paylasimiDurdur()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

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

    @Test(enabled = true, description = "1877 : Evrakı paylaşmada alan kontrolleri")
    public void TC_01876() {


        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST")
                .paylasTabTikla()
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        paylastiklarimPage
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas();

        paylastiklarimPage
                .paylasimAciklamaYaz("yeni açkılmalaasdsdasdasdsada");
                //.islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");


        /*

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST")
                .paylasTabTikla()
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec("Optiim TEST")
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
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");


        paylastiklarimPage
                .openPage()
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        */

    }


    @Test(enabled = true, description = "1904 : Evrak paylaşmada not kontrolü")
    public void TC_01904() {



        String evrakKonu = "Gelen evrak";
        String evrakGeldigiYer = "Başbakanlık / Başbakan Başmüşavirleri";
        String evrakKayitTarihiSayi = "23.11.2017 / 4986";
        String evrakTarihi = "23.11.2017";
        String paylasilmaTarihi = "23.11.2017";
        String No = "2343";
        String evrakNo = "4986";
        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = "Optiim TEST";
        String paylasimAciklamasi = "Optiim TEST ile evrak paylaşıldı.";
        String evrakNotuAciklamasi = "tc 1904 evrak notu";

        /*
        gelenEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGeldigiYer, evrakKayitTarihiSayi, evrakTarihi, No)
                .paylas()
                .paylasKisiSec(paylasilan)
                .paylasanAciklamaDoldur(paylasimAciklamasi)
                .paylasIcPaylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        */

        logout();
        login("optiim", "Avis1111");


        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(paylasan, paylasilmaTarihi, evrakKonu, evrakNo)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(evrakNotuAciklamasi);


        logout();
        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec("","","","")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrolEt(paylasilan, evrakNotuAciklamasi, true);





        /*
        paylastiklarimPage
                .openPage()
                .evrakSec("Optiim TEST","27.11.2017 18:00:05","test","9070");
        */




    }



}