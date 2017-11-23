/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package KisiselIslemlerBagTipi;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.VekaletVerPage;

import static data.TestData.password2;
import static data.TestData.username2;

@Epic("Kişisel İşlemler Bağ Tipi")
public class KisiselIslemlerBagTipiTest extends BaseTest {

    KullaniciYonetimiPage kullaniciYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    VekaletVerPage vekaletVerPage;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2141: Bağ tipi amir yardımcısı olması kontrolleri")
    public void TC2141() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";

        login(username2, password2);

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGuncelle()
                //.gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec("str")
                .popupKullaniciBirimAtamaKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        evrakOlusturPage
                .openPage()
                .otomatikOnayAkisi();
        vekaletVerPage
                .openPage()
                .vekaletAlanDoldur("deneme")
                .vekaletVerenFarkliDoldur("sda")
                .uygula();
        gelenEvraklarPage
                .openPage()
                .tabHavaleYap();


        //  String getIdariBirimKodu = kurumYonetimiPage.idariBirimKimlikKoduCek();

    }


}
