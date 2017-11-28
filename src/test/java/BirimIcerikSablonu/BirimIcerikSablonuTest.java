package BirimIcerikSablonu;

import common.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.BirimIcerikSablonlarPage;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

//    LoginPage loginPage;
    BirimIcerikSablonlarPage birimIcerikSablonlarPage;

    @BeforeMethod
    public void setUp() {
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage();
        login();
    }

    @Test(description = "Alan Kontrolleri")
    public void tc1084a() throws Exception {

        birimIcerikSablonlarPage
                .openPage()
                .alanlarinAktifDurumKontrol()
                .detayButonlarinExist();
    }
}
