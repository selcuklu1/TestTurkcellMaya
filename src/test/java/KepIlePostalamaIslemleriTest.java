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
    public void TC1513() throws InterruptedException {
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

}
