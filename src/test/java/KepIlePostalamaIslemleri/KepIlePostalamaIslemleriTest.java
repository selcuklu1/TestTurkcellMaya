package KepIlePostalamaIslemleri;

import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Belgenet1Epic examples")
public class KepIlePostalamaIslemleriTest extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Tüzel Kişi Yönetimi")
    public void TC1513a() throws InterruptedException {
        page.loginPage().login();
        page.ustMenuAc("Teşkilat/Kişi Tanımları","Tüzel Kişi Yönetimi");
        page.TuzelKisiYonetimiPage()
                .ara()
                .duzenleGonder()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriEkle()
                .popupKepAdresiDoldur("turksat.kamu1@testkep.pttkep.gov.tr\n")
                .kepHizmetSaglayicisiSec("Diğer")
                .popupKaydet();
        //  page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);  Obje Değişti
        page.ustMenuAc("Evrak İşlemleri","Evrak Oluştur");
        page.evrakOlusturPage()
                .bilgiDoldur("OPTiiM1");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Tüzel Kişi Yönetimi")
    public void TC1513b() throws InterruptedException {
        String text="";
        page.ustMenuAc("Teşkilat/Kişi Tanımları","Kurum Yönetimi");
        page.KurumYonetimiPage()
                .ara()
                .guncelle()
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriArti()
                .popupKepAdresi("turksat.kamu1@testkep.pttkep.gov.tr")
                .popupKepHizmetSaglayicisi("PTT KEP Servisi")
                .popupKaydet();
                //.idariBirimKimlikKodu(text);
               // String text1=   page.KurumYonetimiPage().idariBirimKimlikKoduCek();
        //  page.islemMesaji().beklenenMesajTipi(MesajTipi.BASARILI);  Obje Değişti
        page.ustMenuAc("Evrak İşlemleri","Evrak Oluştur");
        page.evrakOlusturPage()
                .bilgiDoldur("OPTiiM1");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "KEP Adresi Tanımlama işlemleri")
    public void TC1520() throws InterruptedException {
        page.loginPage().login();
        page.ustMenuAc("Teşkilat/Kişi Tanımları","Birim Yönetimi");
        page.BirimYonetimiPage()
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
