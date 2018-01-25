package BakimaAl;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ustMenuPages.BakimaAlPage;


public class BakimaAlTest extends BaseTest {

    BakimaAlPage bakimaAlPage;
    LoginPage loginPage;

    @BeforeMethod
    public void loginBeforeTests() {
        bakimaAlPage = new BakimaAlPage();
        loginPage = new LoginPage();
        login("mbozdemir", "123");
    }

    @Test(enabled = true, description = "2108 : Bakım Moduna Alma ve alan kontrolleri")
    public void TC02108() {

        String bilgilendirmeMetni500Karakter = "";
        String bilgilendirmeMetni600Karakter = "";
        String girilecekBilgilendirmeMetni = "Bu bir deneme bilgilendirme metnidir.";
        String iptalEdilecekBilgilendirmeMetni = "İptal edilecek bilgilendirme metni.";

        String pasifKullanici = "pasifkullanici";


        for (int i = 0; i < 500; i++) {
            bilgilendirmeMetni500Karakter += "x";
        }
        for (int i = 0; i < 600; i++) {
            bilgilendirmeMetni600Karakter += "x";
        }


        bakimaAlPage
                .openPage()
                .bilgilendirmeMetniGir(bilgilendirmeMetni500Karakter)
                .bilgilendirmeMetni500KarakterKontrolu()
                .bilgilendirmeMetniGir(bilgilendirmeMetni600Karakter)
                .bilgilendirmeMetni500KarakterKontrolu()
                .bilgilendirmeMetniGir(girilecekBilgilendirmeMetni)
                .bilgilendirmeMetniKaydet()
                .bilgilendirmeMetniKontrol(girilecekBilgilendirmeMetni)
                .bilgilendirmeMetniGir(iptalEdilecekBilgilendirmeMetni)
                .bilgilendirmeMetniIptal()
                .bilgilendirmeMetniKontrol(girilecekBilgilendirmeMetni)
                .kullanicilarTemizle()
                .kullaniciKontrol(pasifKullanici, false)
                .kullaniciEkle("Optiim TEST")
                .kullaniciEkle("Mehmet BOZDEMİR")
                .bakimaAl()
                .bakimdaOlmali(true);

        loginPage
                .loginBakim("test1", "123")
                .islemMesaji().dikkatOlmali(girilecekBilgilendirmeMetni);

    }

    @Test(enabled = true, description = "2109 : Bakım Modundan Çıkar")
    public void TC02109() {

        String bilgilendirmeMetni = "Bu bir deneme bilgilendirme metnidir.";
        String[] kontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST-Ağ (Network) Uzman Yardımcısı",
                "Mehmet BOZDEMİR-Ağ (Network) Uzman Yardımcısı"
        };
        bakimaAlPage
                .openPage()
                .bakimdaOlmali(true)
                .bilgilendirmeMetniKontrol(bilgilendirmeMetni)
                .secilenKullaniciKontrol(kontrolEdilecekKullanicilar)
                .bakimdanCikar()
                .bakimdaOlmali(false);
        login("ztekin", "123");


    }


}