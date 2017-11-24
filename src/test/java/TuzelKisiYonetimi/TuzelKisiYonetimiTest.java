package TuzelKisiYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

/****************************************************
 * Tarih: 2017-11-24
 * Proje: Türksat Functional Test Automation
 * Class: "Tüzel Kişi Yönetimi" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class TuzelKisiYonetimiTest extends BaseTest{

    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1124: Yeni tüzel kişi kayıt ve ekranlardan kontrolleri")
    public void TC1124() {

        tuzelKisiYonetimiPage
                .openPage()
                .tuzeKisiEkle();

    }
}
