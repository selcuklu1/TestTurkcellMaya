package YonetimHavuzuIslemleri;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.YonetimHavuzuYonetimiPage;


@Epic("Belgenet1Epic examples")

public class YonetimHavuzuIslemleriTest extends BaseTest {

    YonetimHavuzuYonetimiPage yonetimHavuzuYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        yonetimHavuzuYonetimiPage = new YonetimHavuzuYonetimiPage();
        login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC0002() {

        /*
        pages.ustMenuAc("Yönetim Havuzu Yönetimi");
        pages.yonetimHavuzuYonetimiPage()
                .yonetimHavuzuEkle()
                .yonetimHavuzuAdiDoldur("Testdeneme1122153")
                .kullananBirimiEkle()
                .kullananBirimSec("OPTİİM BİRİM")
                .kullananBirimKaydet()
                .kullaniciEkle()
                .kullaniciSec("Optiim TEST1")
                .kullaniciKaydet()
                .yonetimHavuzuKaydet();
         */

        /*
        pages.ustMenuAc("Evrak Oluştur");
        pages.evrakOlusturPage()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim OPTİİM", "Paraflama")
                .onayAkisiKullaniciSec("Optiim TEST1")
                .onayAkisiKullaniciTipiSec("Optiim TEST1", "İmzalama")
                .onayAkisiKullan();
*/
        

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TC0001")
    public void TC0001() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Yönetim Havuzu Arama")
    public void TC0009() {

       yonetimHavuzuYonetimiPage
               .openPage()
                .ara("OPTİİM BİRİM11","Testdeneme1123","Sadece Aktifler")
                .yonetimHavuzuGuncelle("Testdeneme1123");
    }
}
