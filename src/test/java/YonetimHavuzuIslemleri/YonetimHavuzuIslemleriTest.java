package YonetimHavuzuIslemleri;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EvrakOlusturPage;
import pages.ustMenuPages.OlurYazisiOlusturPage;
import pages.ustMenuPages.YonetimHavuzuYonetimiPage;


public class YonetimHavuzuIslemleriTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    YonetimHavuzuYonetimiPage yonetimHavuzuYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    OlurYazisiOlusturPage.BilgilerTab olurYazisiBilgilerTab;



    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPage = new EvrakOlusturPage();
        yonetimHavuzuYonetimiPage = new YonetimHavuzuYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        olurYazisiBilgilerTab = new OlurYazisiOlusturPage().new BilgilerTab();
        login();
    }

    @Test(enabled = true, description = "TC0001")
    public void TC0001() {

        /*
        yonetimHavuzuYonetimiPage
                .openPage()
                .ara("Optiim Birim", "Test-tec01", "Sadece Aktifler", true)
                .yonetimHavuzuGuncelle("Test-tec01", false)
                .yonetimHavuzuKontrol("Test-tec01", new String[]{"Optiim Birim"}, new String[]{"Veysel KIRAN"})
                .yonetimHavuzuPasifYap("Test-tec01");


        evrakOlusturPage.open();
        evrakBilgilerTab
                .open()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim TEST", "Paraflama")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", false);
        */

        olurYazisiOlusturPage.open();
        olurYazisiBilgilerTab
                .open()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim TEST", "Paraflama")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", false);
    }

    @Test(enabled = true, description = "Paylaştıklarım")
    public void TC0002() {

        yonetimHavuzuYonetimiPage
                .openPage()
                .yonetimHavuzuEkle("Tc02yonetimhavuzu")
                .kullananBirimEkle("Optiim")
                .kullaniciEkle("Veysel KIRAN")
                .yonetimHavuzuKaydet()
                .ara("Optiim", "Tc02yonetimhavuzu", null, true);


        evrakOlusturPage.open()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim OPTİİM ", "Paraflama")
                .onayAkisiKullaniciEkle("Veysel KIRAN")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", true)
                .onayAkisiKullaniciTipiSec("Veysel KIRAN", "İmzalama")
                .onayAkisiKullan()
                .onayAkisiKullanilanKullanilanKontrolEt("Optiim OPTİİM-Paraflama / Veysel KIRAN-İmzalama");


        logout();
        login("Ztekin", "123");


        evrakOlusturPage.open()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Zübeyde TEKİN ", "Paraflama")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", false);


    }

    @Test(enabled = true, description = "Yönetim Havuzu Arama")
    public void TC0009() {
        yonetimHavuzuYonetimiPage
                .openPage()
                .ara(null, "Test-tec01", null, true)
                .yonetimHavuzuGuncelle("Test-tec01", false)
                .yonetimHavuzuKontrol("Test-tec01",
                        new String[]{"OPTİİM BİRİM"},
                        new String[]{"Yasin TELLİ", "Veysel KIRAN"})
                .kullananBirimEkle("Optiim AltBirim")
                .kullaniciSil("Veysel KIRAN")
                .kullaniciEkle("Yasin TELLİ")
                .yonetimHavuzuAdiDoldur("Test-tec012")
                .yonetimHavuzuKaydet()
                .ara(null, "Test-tec012", null, true);

        /*


        yonetimHavuzuYonetimiPage
                .ara(null, "Guncellenen Havuzum2", null, true)
                .yonetimHavuzuGuncelle("Guncellenen Havuzum2", false)
                .yonetimHavuzuKontrol("Guncellenen Havuzum2",
                        new String[]{"OPTİİM BİRİM"} ,
                        new String[]{"Optiim TEST1", "Optiim TEST2", "Veysel KIRAN"})
                .kullananBirimEkle("OPTİİM BİRİM")
                .kullaniciSil("Veysel KIRAN")
                .kullaniciEkle("Yasin TELLİ")
                .yonetimHavuzuAdiDoldur("Guncellenen Havuzum23")
                .yonetimHavuzuKaydet()
                .ara(null, "Guncellenen Havuzum23", null, true);
         */

        /*


        yonetimHavuzuYonetimiPage
                .ara(null, "Guncellenen Havuzum2", null, true)
                .yonetimHavuzuGuncelle("Guncellenen Havuzum2", false)
                .yonetimHavuzuKontrol("Guncellenen Havuzum2",
                        new String[]{"OPTİİM BİRİM"} ,
                        new String[]{"Optiim TEST1", "Optiim TEST2", "Veysel KIRAN"})
                .kullananBirimEkle("OPTİİM BİRİM")
                .kullaniciSil("Yasin TELLİ")
                .kullaniciEkle("Veysel KIRAN")
                .yonetimHavuzuAdiDoldur("Guncellenen Havuzum2")
                .yonetimHavuzuKaydet()
                .ara(null, "Guncellenen Havuzum2", null, true);
         */


        /*
        evrakOlusturPage
                .open()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim OPTİİM ", "Paraflama")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", false)
                .onayAkisiTreeKullaniciKontrol("Yasin TELLİ", true)
                .onayAkisiKullaniciEkle("Yasin TELLİ")
                .onayAkisiKullaniciTipiSec("Yasin TELLİ", "İmzalama")
                .onayAkisiKullan();

        */


    }
}