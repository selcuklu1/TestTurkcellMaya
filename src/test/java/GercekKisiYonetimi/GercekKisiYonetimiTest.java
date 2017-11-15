package GercekKisiYonetimi;

import common.BaseLibrary;
import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageData.MesajTipi;
import pageData.SolMenuData;

public class GercekKisiYonetimiTest extends BaseTest{

    BasePage page;
    BaseLibrary lib;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        lib = new BaseLibrary();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Gerçek Kişi Yönetimi TC1516")
    public void TC1516() {
        page.ustMenuAc("Gerçek Kişi Yönetimi");
        page.gercekKisiYonetimPage()
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(lib.createMernisTCNO())
                .onEkDoldur("Mühendis")
                .adDoldur("Sezai")
                .soyadDoldur("Çelik")
                .kepAdresiKullaniyor(true)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur("Cumhuriyet Mahallesi")
                .iletisimBilgisiIlDoldur("İSTANBUL")
                .iletisimBilgisiIlceDoldur("Şişli")
                .iletisimBilgisiEpostaDoldur("test@turksat.com.tr")
                .iletisimBilgisiKaydet();





    }
}
