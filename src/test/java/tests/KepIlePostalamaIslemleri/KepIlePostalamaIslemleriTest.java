/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Kep ile postalama işlemleri" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.KepIlePostalamaIslemleri;

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
public class KepIlePostalamaIslemleriTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;
    KurumYonetimiPage kurumYonetimiPage;
    BirimYonetimiPage birimYonetimiPage;
    GercekKisiYonetimPage gercekKisiYonetimPage;
    KepIlePostalanacaklarPage kepIlePostalanacaklarPage;
    MainPage mainPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPage = new EvrakOlusturPage();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        kurumYonetimiPage = new KurumYonetimiPage();
        birimYonetimiPage = new BirimYonetimiPage();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        kepIlePostalanacaklarPage = new KepIlePostalanacaklarPage();
        mainPage = new MainPage();
    }

    //TS
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1610: KEP Hesap Menüsü - Tanımlanan KEP hesapları ile login işlemleri")
    public void TS1610() {

        String basariMesaji = "İşlem başarılıdır!";
        String hataMesaji = "Bağlantı kurulamadı, girilen parola veya şifre yanlış !";
        String parola = "71396428";
        String sifre = "71396428a";
        String hataliParola = "123";
        String hataliSifre = "1";

        login(username3, password3);

        mainPage
                .kepBaglantisi()
                .kepAdresBaglantisiBaglan1()
                .kullaniciAdiTcKimlikNoKontol()
                .parolaDoldur(parola)
                .sifreDoldur(sifre)
                .kepBaglantisiBaglan()
                .islemMesaji().beklenenMesaj(hataMesaji);

        login(username2, password2);

        mainPage
                .kepBaglantisi()
                .kepAdresBaglantisiBaglan1()
                .kullaniciAdiTcKimlikNoKontol()
                .parolaDoldur(parola)
                .sifreDoldur(sifre)
                .kepBaglantisiBaglan()
                .islemMesaji().basariliOlmali(basariMesaji);

        mainPage
                .kepAdresBaglantisiBaglan2()
                .parolaDoldur(hataliParola)
                .sifreDoldur(hataliSifre)
                .kepBaglantisiBaglan()
                .islemMesaji().beklenenMesaj(hataMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513: Kurum Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından kontrolü")
    public void TS1513B() throws InterruptedException {

        String popupKepAdresi = "turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "Diğer";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "Kurum";

        login(username2, password2);

        kurumYonetimiPage
                .openPage()
                .ara()
                .guncelle()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriArti()
                .popupKepAdresiDoldur(popupKepAdresi)
                .popupKepHizmetSaglayicisiSec(popupKepHizmetSaglayicisiSec)
                .popupKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        String getIdariBirimKodu = kurumYonetimiPage.idariBirimKimlikKoduCek();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec(bilgiSecimTipi)
                .geregiDoldur(getIdariBirimKodu, "İdari Birim Kodu");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513: Gerçek Kişi Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından Kontrolü")
    public void TS1513C() {

        String popupKepAdresi = "turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "Gerçek Kişi";

        login(username2, password2);

        gercekKisiYonetimPage
                .openPage()
                .ara()
                .gercekKisiGuncelle()
                .kepAdresiKullaniyor(true)
                .kepAdresBilgileriEkle()
                .kepAdresiDoldur(popupKepAdresi)
                .kepHizmetSaglayiciSec(popupKepHizmetSaglayicisiSec)
                .kepAdresiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        String getIdariBirimKodu = gercekKisiYonetimPage.getTbleTCNO();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec(bilgiSecimTipi)
                .geregiDoldur(getIdariBirimKodu, "İdari Birim Kodu");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513: Tüzel Kişi Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından Kontrolü")
    public void TS1513D() {

        String popupKepAdresi = "turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "Tüzel Kişi";
        String kullanici = "Büyük Küçük Harflerle Tüzel Kişi";

        login(username, password);

        tuzelKisiYonetimiPage
                .openPage()
                .ara()
                .tuzelKisiSecGuncele(kullanici)
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriEkle()
                .kepAdresiDoldur(popupKepAdresi)
                .kepHizmetSaglayicisiSec(popupKepHizmetSaglayicisiSec)
                .kepAdresiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        String vergiNo = tuzelKisiYonetimiPage.vergiNoCek();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec(bilgiSecimTipi)
                .geregiDoldur(vergiNo, "Vergi No");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "KEP Adresi Tanımlama işlemleri")
    public void TS1520() {

        String birim = "Yazılım";
        String birimTuru = "İç Birim";
        String popupKepAdresi1 = "turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepAdresi2 = "turksat.kamu2@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";
        String kepAdresi1 = "turksat.kamu" + createRandomNumber(10) + "@testkep.pttkep.gov.tr";
        String kepAdresi2 = "turksat.kamu" + createRandomNumber(10) + "@testkep.pttkep.gov.tr";

        login(username2, password2);

        birimYonetimiPage
                .openPage()
                .birimDoldur(birim)
                .birimTuruSec(birimTuru)
                .ara()
                .tableDuzenle()
                .yeniKepAdresBilgileriEkle()
                .popupKepAdresiDoldur(kepAdresi1)
                .popupHizmetSaglayicisiSec(popupKepHizmetSaglayicisiSec)
                .popupKaydet()
                .yeniKepAdresBilgileriEkle()
                .popupKepAdresiDoldur(kepAdresi2)
                .popupHizmetSaglayicisiSec(popupKepHizmetSaglayicisiSec)
                .popupKaydet()
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kepIlePostalanacaklarPage
                .openPage()
                .ilkEvrakTikla()
                .evrakPostala();

        String gonderici = kepIlePostalanacaklarPage.gondericiCek();

        kepIlePostalanacaklarPage
                .gondericiKontrol(popupKepAdresi2);
    }

}
