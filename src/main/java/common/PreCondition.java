package common;

import common.BaseLibrary;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.VekaletVerPage;

import java.util.List;

public class PreCondition extends BaseLibrary {

    @Step("Kişisel İşlemler Bağ tipi TS2141 Test senaryosu çalıştırılır.")
    public static void TS2141() {

        LoginPage loginPage = new LoginPage();
        KullaniciYonetimiPage kullaniciYonetimiPage = new KullaniciYonetimiPage();
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        VekaletVerPage vekaletVerPage = new VekaletVerPage();
        GelenEvraklarPage gelenEvraklarPage = new GelenEvraklarPage();


        String basariMesaji = "İşlem başarılıdır!";
        String bagTipi = "Amir Yardımcısı";
        String farkliKullanici = "Optiim";

        // Gelen evraklar alanında veri bulunmalı

        loginPage.login("kbagtipi", "123");

        kullaniciYonetimiPage
                .openPage()
                .ara()
                .kullaniciListesiGeldigiGorme();
        String ad = kullaniciYonetimiPage.adCek();
        kullaniciYonetimiPage
                .kullaniciListesiGuncelle(ad)
                .kullaniciBilgileriGeldigiGorme()
                .gorevliOlduguBirimlerGuncelle()
                .popupKullaniciBirimAtamaBagTipiSec(bagTipi, "Bağ Tipi")
                .popupKullaniciBirimAtamaKaydet();

        String ekranAdi = kullaniciYonetimiPage.ekranAdiCek();

        kullaniciYonetimiPage
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .otomatikOnayAkisiSec()
                .otomatikOnayAkisiGeldigiGorme(ekranAdi, "Kullanıcı");

        vekaletVerPage
                .openPage()
                .vekaletVerenDoldur(ekranAdi)
                .vekaletVerenFarkliDoldur(farkliKullanici)
                .onayVerecekDoldur(ekranAdi);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .tabHavaleYap()
                .havaleYapOnaylanacakKisiTreeDoldur(ekranAdi, "Onaylanacak kişi");
    }
}
