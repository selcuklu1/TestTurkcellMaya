/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakKopyalama;

import common.BaseLibrary;
import common.BaseTest;
import common.ReusableSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-20
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Kopyalama" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Evrak Kopyalama")
public class EvrakKopyalamaTest extends BaseTest {

    ReusableSteps reusableSteps;
    BeklemeyeAlinanlarPage beklemeyeAlinanlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        reusableSteps = new ReusableSteps();
        beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS1597: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (içerik ekranından)")
    public void TS1597() {

        String konuKodu = "TS1597-" + createRandomNumber(15);

        login(usernameYAKYOL,passwordYAKYOL);

        reusableSteps
                .beklemeyeAlinanlarEvrakOlustur(konuKodu,"Birim","BÜYÜK HARFLERLE BİRİM","Paraflama","Zübeyde Tekin","BHUPGMY","İmzalama",usernameZTEKIN,passwordZTEKIN);

        beklemeyeAlinanlarPage
                .openPage();
    }
}
