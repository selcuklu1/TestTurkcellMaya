package KurumYonetimi;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.KurumYonetimiPage;


public class KurumYonetimiTest extends BaseTest {

   KurumYonetimiPage kurumYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kurumYonetimiPage = new KurumYonetimiPage();
        login();
    }

    @Test(enabled = true, description = "TC01459 : Kurum bilgisi güncellemee")
    public void TC01957_A() {
        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur("huseyindeneme")
                .ara()
                .kurumGuncelle("huseyindeneme")
                .idariBirimKimlikKoduDoldur("95123123")
                .ustKurumSec("Adalet Bakanlığı")
                .kurumAdiDoldur("huseyindeneme234")
                .iletisimGuncelle()
                .mobilTelNoDoldur("5444444444")
                .telefonNoDoldur("2129999999")
                .isTelefonNoDoldur("2129999998")
                .faxNumarasi1Doldur("2129999997")
                .faxNumarasi2Doldur("2129999996")
                .adresDoldur("yoooooooookk iii")
                //.ulkeDoldur("TÜRKİYE")
                //.ilDoldur("İSTANBUL")
                .ilceDoldur("xxxxasdasd")
                .ePostaDoldur("deneme@denememail.com")
                .webAdresiDoldur("www.denemecik.com")
                .iletisimBilgisiKaydet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage
                .kepAdresiGuncelle("turksat.kamu1@testkep.pttkep.gov.tr", null)
                //.kepAdresiGuncelle("turksat.kamu1@testkep.pttkep.gov.tr", null)
                .kepAdresiDoldur("deneme@kepadresim.com")
                .kepHizmetSaglayiciSec("KEPKUR")
                .kepAdresiBilgileriKaydet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage
                .kepAdresiKontrol("deneme@kepadresim.com", 0, true)
                .kurumKaydet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage
                .kurumHiyerarsisiniGuncelle();
                //.islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage
                .sorgulaKurumDoldur("huseyindeneme234")
                .kurumKontrolEt("huseyindeneme234", true);


    }





}