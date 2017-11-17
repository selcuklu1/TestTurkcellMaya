package KepIlePostalamaIslemleri;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ustMenuPages.BirimYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KurumYonetimiPage;
import pages.ustMenuPages.TuzelKisiYonetimiPage;

@Epic("Belgenet1Epic examples")
public class KepIlePostalamaIslemleriTest extends BaseTest {

    BasePage page;
    EvrakOlusturPage evrakOlusturPage;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
        evrakOlusturPage = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Tüzel Kişi Yönetimi")
    public void TC1513a() throws InterruptedException {
        page.loginPage().login();
        page.ustMenu("Teşkilat/Kişi Tanımları", "Tüzel Kişi Yönetimi");
        new TuzelKisiYonetimiPage()
                .ara()
                .duzenleGonder()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriEkle()
                .popupKepAdresiDoldur("turksat.kamu1@testkep.pttkep.gov.tr\n")
                .kepHizmetSaglayicisiSec("Diğer")
                .popupKaydet();
        //  pages.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);  Obje Değişti
        page.ustMenu("Evrak İşlemleri", "Evrak Oluştur");
        evrakOlusturPage
                .bilgiDoldur("OPTiiM1");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Tüzel Kişi Yönetimi")
    public void TC1513b() throws InterruptedException {
        String text="";
        page.ustMenu("Teşkilat/Kişi Tanımları", "Kurum Yönetimi");
        new KurumYonetimiPage()
                .ara()
                .guncelle()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriArti()
                .popupKepAdresi("turksat.kamu1@testkep.pttkep.gov.tr")
                .popupKepHizmetSaglayicisi("PTT KEP Servisi")
                .popupKaydet();
                //.idariBirimKimlikKodu(text);
        // String text1=   pages.ustMenuPages.KurumYonetimiPage().idariBirimKimlikKoduCek();
        //  pages.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);  Obje Değişti
        page.ustMenu("Evrak İşlemleri", "Evrak Oluştur");
        evrakOlusturPage
                .bilgiDoldur("OPTiiM1");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "KEP Adresi Tanımlama işlemleri")
    public void TC1520() throws InterruptedException {
        page.loginPage().login();
        page.ustMenu("Teşkilat/Kişi Tanımları", "Birim Yönetimi");
        new BirimYonetimiPage()
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
