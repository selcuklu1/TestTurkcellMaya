package tests.KullaniciListesiYonetimi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.KullaniciYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class KullaniciListesiYonetimiTest extends BaseTest {



    @BeforeMethod
    public void loginBeforeTests() {
        
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1464 : Kullanıcı Listeleri Aktiflik/Pasiflik Kontrolü")
    public void TS1464() throws InterruptedException{

        login(TestData.usernameMBOZDEMIR,TestData.usernameMBOZDEMIR);

    }
}
