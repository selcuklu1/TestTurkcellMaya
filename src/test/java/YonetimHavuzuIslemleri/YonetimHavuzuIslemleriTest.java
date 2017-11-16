package YonetimHavuzuIslemleri;

import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Belgenet1Epic examples")

public class YonetimHavuzuIslemleriTest extends BaseTest {

    BasePage page = new BasePage();

    @BeforeMethod
    public void loginBeforeTests() {
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC0002() {

        /*


        page.ustMenuAc("Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
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
        page.ustMenuAc("Evrak Oluştur");
        page.evrakOlusturPage()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim OPTİİM", "Paraflama")
                .onayAkisiKullaniciSec("Optiim TEST1")
                .onayAkisiKullaniciTipiSec("Optiim TEST1", "İmzalama")
                .onayAkisiKullan();
<<<<<<< HEAD:src/test/java/YonetimHavuzuIslemleriTest.java
=======
*/



>>>>>>> 7cfb106ba52eaee62cf21b80aad145309ff65fd9:src/test/java/YonetimHavuzuIslemleri/YonetimHavuzuIslemleriTest.java
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TC0001")
    public void TC0001() {




    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Yönetim Havuzu Arama")
    public void TC0009() {
        page.ustMenuAc("Yönetim Havuzu Yönetimi");
        page.yonetimHavuzuYonetimiPage()
                .ara("OPTİİM BİRİM11","Testdeneme1123","Sadece Aktifler")
                .yonetimHavuzuGuncelle("Testdeneme1123");
    }
}
