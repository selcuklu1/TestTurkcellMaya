package BirimIcerikSablonu;

import common.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageComponents.BasePage;

@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

    BasePage page;
//    BirimIcerikSablonlarPage birimIcerikSablonlarPage;

    @BeforeMethod
    public void setUp() {
        page.loginPage().login();
    }

    @Test(description = "Alan Kontrolleri")
    public void tc1084() throws Exception {
        page.ustMenu("Birim İçerik Şablonları");

//        page.BirimIcerikSablonlarPage()
//                .alanlarinAktifDurumKontrol()
//                .detayButonlarinExist();
    }
}
