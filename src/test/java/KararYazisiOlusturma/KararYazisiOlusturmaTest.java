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
            String uyariMesajYaziIcerik = "Yazı içeriği boş olamaz!";
            String uyariMesajZorunlu = "Zorunlu alanları doldurunuz";
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
                    .kaldirilacakKlasorlerDoldur("Diğer")
                    .toplantiNoDoldur("123123")
                    .toplantiTarihDoldur("30.11.2017")
                    .kararNoDoldur("1231231231231231231")
                    .kaydetveOnaySun()
                    .aciklamaDoldur("Deneme amaçlıdır")
                    .gonder(true)
                    .islemMesaji().beklenenMesaj(uyariMesajYaziIcerik);

            kararYazisiOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur("Deneme can");
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);
//1
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur("1231231231231231231")
                    .konuKoduTemizle()
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);
//2
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .konuKoduDoldur("K/Frekans Yıllık Kullanım Ücreti")
                    .konuDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);
//3
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorTemizle()
                    .konuKoduDoldur("K/Frekans Yıllık Kullanım Ücreti")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);
//4

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorlerDoldur("Diğer")
                    .toplantiNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);



        }
}

