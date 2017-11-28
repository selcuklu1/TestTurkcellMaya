package Paylastiklarim;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.BenimlePaylasilanlarPage;
import pages.solMenuPages.PaylastiklarimPage;


public class PaylastiklarimTest extends BaseTest {

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
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




    }


}