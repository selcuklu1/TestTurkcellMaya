package tests.BakimaAl;

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
    public void TS02108() {

        String girilecekBilgilendirmeMetni = "Bu bir deneme bilgilendirme metnidir.";
        String iptalEdilecekBilgilendirmeMetni = "İptal edilecek bilgilendirme metni.";

        String pasifKullanici = "PASIF KULLANICI";

        bakimaAlPage
                .openPage()
                .bilgilendirmeMetniGir(500)
                .bilgilendirmeMetni500KarakterKontrolu()
                .bilgilendirmeMetniGir(600)
                .bilgilendirmeMetniMaxKarakterKontrolu()
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
                .bakimdaOlmali();

        logout();

        loginPage
                .loginBakim("test1", "123")
                .islemMesaji().dikkatOlmali(girilecekBilgilendirmeMetni);

    }

    @Test(enabled = true, description = "2109 : Bakım Modundan Çıkar")
    public void TS02109() {

        bakimaAlPage
                .openPage()
                .bakimdaOlmali()
                .bakimdanCikar()
                .bakimdaOlmamali();
        login("ztekin", "123");

    }


}