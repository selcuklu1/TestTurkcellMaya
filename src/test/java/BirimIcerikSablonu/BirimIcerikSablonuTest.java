package BirimIcerikSablonu;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.BirimIcerikSablonlarPage;

import static com.codeborne.selenide.Selenide.$;


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
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage();
    }

    @Test(description = "Alan aktif durumu kontrolleri")
    public void tc1084a() {
        birimIcerikSablonlarPage
                .openPage()
                .alanlarinAktifDurumKontrol()
                .detayButonlarinExist();
    }

    @Test
    @Description("Yeni şablon oluşturma (Alt birimler görsün)")
    public void tc1082() {

    }
}
