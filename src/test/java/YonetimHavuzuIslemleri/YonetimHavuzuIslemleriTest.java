package YonetimHavuzuIslemleri;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
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

    @Test(enabled = true, description = "TC0001 : Yönetim havuzunu pasife alma")
    public void TC0001() {

        String pasifeAlinacakHavuzAdi = "Test-tec012";

        String[] kontrolEdilecekBirimler = new String[]{"Optiim Birim"};
        String[] kontrolEdilecekKullanicilar = new String[]{"Veysel KIRAN"};
        String filtrelenecekBirimAdi = "Optiim Birim";

        yonetimHavuzuYonetimiPage
                .openPage()
                .ara(null, pasifeAlinacakHavuzAdi, "Sadece Aktifler", true)
                .yonetimHavuzuGuncelle(pasifeAlinacakHavuzAdi, false)
                .yonetimHavuzuKontrol(pasifeAlinacakHavuzAdi, kontrolEdilecekBirimler, kontrolEdilecekKullanicilar)
                .yonetimHavuzuPasifYap(pasifeAlinacakHavuzAdi);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim TEST", "Paraflama")
                .onayAkisiTreeKullaniciKontrol("Veysel KIRAN", false);


    }

    @Test(enabled = true, description = "TC0002 : Yeni yönetim havuzu kayıt")
    public void TC0002() {

        String eklenecekYonetimHavuzuAdi = "Tc02yonetimhavuzu";
        String eklenecekBirim = "Optiim Birim";
        String eklenecekKullanici = "Veysel KIRAN";


        yonetimHavuzuYonetimiPage
                .openPage()
                .yonetimHavuzuEkle(eklenecekYonetimHavuzuAdi)
                .kullananBirimEkle(eklenecekBirim)
                .kullaniciEkle(eklenecekKullanici)
                .yonetimHavuzuKaydet()
                .ara(eklenecekBirim, eklenecekYonetimHavuzuAdi, null, true);


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Optiim TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiKullaniciEkle(eklenecekKullanici)
                .onayAkisiKullaniciTipiSec(eklenecekKullanici, "İmzalama")
                .onayAkisiKullan()
                .onayAkisiKullanilanKullanilanKontrolEt("Optiim TEST-Paraflama / Veysel KIRAN-İmzalama");


        logout();
        login("Ztekin", "123");


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Zübeyde TEKİN ", "Paraflama")
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, false);


    }

    @Test(enabled = true, description = "TC0003 : yönetim havuzunu güncelleme")
    public void TC0009() {

        String yonetimHavuzuAdi = "Test-tec01";
        String[] kontrolEdilecekBirimler = new String[]{"Optiim Birim"};
        String[] kontrolEdilecekKullanicilar = new String[]{ "Veysel KIRAN"};
        String eklenecekAltBirim = "Optiim Alt Birim1";
        String silinecekKullanici = "Veysel KIRAN";
        String eklenecekKullanici = "Yasin TELLİ";
        String yeniYonetimHavuzuAdi = "Test-tec012";

        String guncelKullanici = "Optiim TEST [Ağ (Network) Uzman Yardımcısı]";

        /*
        yonetimHavuzuYonetimiPage
                .openPage()
                .ara(null, yonetimHavuzuAdi, null, true)
                .yonetimHavuzuGuncelle(yonetimHavuzuAdi, false)
                .yonetimHavuzuKontrol(yonetimHavuzuAdi, kontrolEdilecekBirimler, kontrolEdilecekKullanicilar)
                .kullananBirimEkle(eklenecekAltBirim)
                .kullaniciSil(silinecekKullanici)
                .kullaniciEkle(eklenecekKullanici)
                .yonetimHavuzuAdiDoldur(yeniYonetimHavuzuAdi)
                .yonetimHavuzuKaydet()
                .ara(null, yeniYonetimHavuzuAdi, null, true);


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(guncelKullanici, "Paraflama")
                .onayAkisiTreeKullaniciKontrol(silinecekKullanici, false)
                .onayAkisiTreeKullaniciKontrol(eklenecekKullanici, true)
                .onayAkisiKullaniciEkle(eklenecekKullanici)
                .onayAkisiKullaniciTipiSec(eklenecekKullanici, "İmzalama")
                .onayAkisiKullan();

         */

        logout();
        login("optiimtest1", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(guncelKullanici, "Paraflama");


    }
}