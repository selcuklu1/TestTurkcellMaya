import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

    BasePage page;

    @BeforeMethod
    public void setUp() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Test(description = "Alan Kontrolleri")
    public void tc1084() throws Exception {
        page.ustMenuAc("Birim İçerik Şablonları");

     //   page.BirimIcerikSablonlarPage()
       //         .alanlarinAktifDurumKontrol()
         //       .detayButonlarinExist();
    }
}
