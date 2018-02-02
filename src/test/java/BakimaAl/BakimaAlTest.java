package BakimaAl;

import common.BaseTest;
import io.qameta.allure.Allure;
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


        String iptalEdilecekBilgilendirmeMetni = "İptal edilecek bilgilendirme metni.";
        String pasifKullanici = "pasifkullanici";
        String girilecekBilgilendirmeMetni = "Bu bir deneme bilgilendirme metnidir.";


        bakimaAlPage
                .openPage()
                .bilgilendirmeMetniGir(500)
                .bilgilendirmeMetni500KarakterKontrolu()
                .bilgilendirmeMetniKaydet()
                .bilgilendirmeMetniGir(600)
                .bilgilendirmeMetni500denfazlaKarakterKontrolu()
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

        logout();

        loginPage
                .loginBakim("test1", "123")
                .islemMesaji().dikkatOlmali(girilecekBilgilendirmeMetni);

    }

    @Test(enabled = true, description = "2109 : Bakım Modundan Çıkar")
    public void TC02109() {

        String bilgilendirmeMetni = "Bu bir deneme bilgilendirme metnidir.";
        String[] kontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST-Ağ (Network) Uzman Yardımcısı",
                "Mehmet BOZDEMİR-Ağ (Network) Uzman Yardımcısı",
        };
        bakimaAlPage
                .openPage()
                .bakimdaOlmali(true)
                .bilgilendirmeMetni500KarakterKontrolu()
                .bilgilendirmeMetniIptal()
                .bilgilendirmeMetniKontrol(bilgilendirmeMetni)
                .secilenKullaniciKontrol(kontrolEdilecekKullanicilar)
                .bakimdanCikar()
                .bakimdanCikarKontrol();
        login("ztekin", "123");


    }


}