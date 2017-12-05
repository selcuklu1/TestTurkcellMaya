package KararYazisiOlusturma;
/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Karar Yazısı Oluşturma" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.KepIlePostalanacaklarPage;
import pages.ustMenuPages.*;

import static data.TestData.*;

@Epic("Kep ile Postalama İşlemleri")
public class KararYazisiOlusturmaTest extends BaseTest{

        KararYazisiOlusturPage kararYazisiOlusturPage;

        @BeforeMethod
        public void loginBeforeTests() {
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        }

        @Severity(SeverityLevel.CRITICAL)
        @Test(enabled = true, description = "1610: KEP Hesap Menüsü - Tanımlanan KEP hesapları ile login işlemleri")
        public void TC1610() throws InterruptedException {

            String basariMesaji = "İşlem başarılıdır!";
            String hataMesaji = "Bağlantı kurulamadı, girilen parola veya şifre yanlış !";
            String parola = "71396428";
            String sifre = "71396428a";
            String hataliParola = "123";
            String hataliSifre = "1";

            login(username3, password3);

            kararYazisiOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduDoldur("K/Frekans Yıllık Kullanım Ücreti")
                    .onayAkisiEkle()
                    .kullan()
                    .kaldirilacakKlasorlerDoldur("")
                    .toplantiNoDoldur("")
                    .toplantiTarihDoldur("")
                    .kararNoDoldur("")
                    .islemMesaji().uyariOlmali(hataMesaji);




        }
}

