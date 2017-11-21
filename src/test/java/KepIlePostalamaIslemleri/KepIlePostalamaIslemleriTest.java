package KepIlePostalamaIslemleri;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.BirimYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KurumYonetimiPage;
import pages.ustMenuPages.TuzelKisiYonetimiPage;

@Epic("Belgenet1Epic examples")
public class KepIlePostalamaIslemleriTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;
    KurumYonetimiPage kurumYonetimiPage;
    BirimYonetimiPage birimYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        evrakOlusturPage = new EvrakOlusturPage();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        kurumYonetimiPage = new KurumYonetimiPage();
        birimYonetimiPage = new BirimYonetimiPage();
        login();
    }


    /****************************************************
     * Tarih: 2017-11-21
     * Proje: Türksat Functional Test Automation
     * Class: "Kep ile postalama işlemleri" konulu senaryoları içerir
     * Yazan: Can Şeker
     ****************************************************/
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1513b: Kurum Kep Hesabı Tanımlama ve Evrak Oluşturma Ekranından kontrolü")
    public void TC1513b() throws InterruptedException {
        String popupKepAdresi ="turksat.kamu1@testkep.pttkep.gov.tr";
        String popupKepHizmetSaglayicisiSec = "Diğer";
        String basariMesaji = "İşlem başarılıdır!";
        String bilgiSecimTipi = "D";
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
                .geregiSecimTipiSec(bilgiSecimTipi)
                .geregiDoldur(getIdariBirimKodu);
    }



    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Tüzel Kişi Yönetimi")
    public void TC1513c() throws InterruptedException {

        String text = "";

        kurumYonetimiPage
                .openPage()
                .ara()
                .guncelle()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriArti()
               // .popupKepAdresi("turksat.kamu1@testkep.pttkep.gov.tr")
                //.popupKepHizmetSaglayicisi("PTT KEP Servisi")
                .popupKaydet();
        //.idariBirimKimlikKodu(text);
        // String text1=   pages.ustMenuPages.KurumYonetimiPage().idariBirimKimlikKoduCek();
        //  pages.islemMesaji().beklenenMesajTipi(MessageTitle.BASARILI);  Obje Değişti

        evrakOlusturPage
                .openPage()
                .bilgiDoldur("OPTiiM1");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "KEP Adresi Tanımlama işlemleri")
    public void TC1520() throws InterruptedException {

        birimYonetimiPage
                .openPage()
                .birimDoldur("Yazılım")
                .birimTuruSec("İç Birim")
                .ara()
                .tableDuzenle()
                .kepAdresBilgileriArti()
                .popupKepAdresiDoldur("turksat.kamu1@testkep.pttkep.gov.tr")
                .popupHizmetSaglayicisiSec("PTT KEP Servisi")
                .popupKaydet()
                .kepAdresBilgileriArti()
                .popupKepAdresiDoldur("turksat.kamu2@testkep.pttkep.gov.tr")
                .popupHizmetSaglayicisiSec("TÜRKKEP Servisi")
                .popupKaydet()
                .kaydet();
    }

}
