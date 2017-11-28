/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Kep ile postalama işlemleri" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package KepIlePostalamaIslemleri;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.KepIlePostalanacaklarPage;
import pages.ustMenuPages.*;

import static data.TestData.*;

@Epic("Belgenet1Epic examples")
public class KepIlePostalamaIslemleriTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;
    KurumYonetimiPage kurumYonetimiPage;
    BirimYonetimiPage birimYonetimiPage;
    GercekKisiYonetimPage gercekKisiYonetimPage;
    KepIlePostalanacaklarPage kepIlePostalanacaklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPage = new EvrakOlusturPage();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        kurumYonetimiPage = new KurumYonetimiPage();
        birimYonetimiPage = new BirimYonetimiPage();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        kepIlePostalanacaklarPage = new KepIlePostalanacaklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513b: Kurum Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından kontrolü")
    public void TC1513b() throws InterruptedException {

        String popupKepAdresi ="turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "Diğer";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "D";

        login(username, password);

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
                .geregiDoldur(getIdariBirimKodu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513c: Gerçek Kişi Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından Kontrolü")
    public void TC1513c() throws InterruptedException {

        String popupKepAdresi ="turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "P";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "G";

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
                .geregiDoldur(getIdariBirimKodu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513d: Tüzel Kişi Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından Kontrolü")
    public void TC1513d() throws InterruptedException {

        String popupKepAdresi ="turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "T";

        login(username, password);

        tuzelKisiYonetimiPage
                .openPage()
                .ara()
                .guncelle()
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
                .geregiDoldur(vergiNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "KEP Adresi Tanımlama işlemleri")
    public void TC1520() throws InterruptedException {

        String birim ="Yazılım";
        String birimTuru ="İç Birim";
        String popupKepAdresi1 ="turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepAdresi2 ="turksat.kamu2@testkep.pttkep.gov.tr";
        String popupKepAdresi3 ="turksat.kamu3@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "PTT KEP Servisi";
        String basariMesaji = "İşlem başarılıdır!";

        login(username2, password2);

        birimYonetimiPage
                .openPage()
                .birimDoldur(birim)
                .birimTuruSec(birimTuru)
                .ara()
                .tableDuzenle()
                .kepAdresBilgileriArti()
                .popupKepAdresiDoldur(popupKepAdresi1)
                .popupHizmetSaglayicisiSec(popupKepHizmetSaglayicisiSec)
                .popupKaydet()
                .kepAdresBilgileriArti()
                .popupKepAdresiDoldur(popupKepAdresi3)
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
