/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakKopyalama;

import common.BaseTest;
import common.PreCondition;
import common.ReusableSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.HavaleEdilenEvrakRaporuPage;
import tests.EvrakBeklemeyeAlma.TS2095;
import tests.EvrakPaylasma.EvrakPaylasmaTest;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-20
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Kopyalama" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Evrak Kopyalama")
public class EvrakKopyalamaTest extends BaseTest {

    PreCondition preCondition;
    PreCondition.BakimaAlinanlarEvrakOlustur bakimaAlinanlarEvrakOlustur;

    @BeforeMethod
    public void loginBeforeTests() {
        preCondition = new PreCondition();
        bakimaAlinanlarEvrakOlustur =new PreCondition.BakimaAlinanlarEvrakOlustur();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS1597: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (içerik ekranından)")
    public void TS1597() {
        login(usernameYAKYOL,passwordYAKYOL);

        bakimaAlinanlarEvrakOlustur
                .bakimaAlinanlarEvrakOlusturA("Diğer",createRandomText(15),"Diğer", "Birim","BÜYÜK HARFLERLE BİRİM","Yasemin Çakıl Akyol","Paraflama","Zübeyde Tekin","BHUPGMY","İmzalama",createRandomText(15));

        login();
    }
}
