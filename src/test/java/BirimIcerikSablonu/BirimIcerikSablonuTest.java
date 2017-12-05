package BirimIcerikSablonu;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ustMenuPages.BirimIcerikSablonlarPage;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


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
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
    }

    @Test(description = "Alan aktif durum kontrolleri")
    public void tc1084a() {
        SoftAssert sa = new SoftAssert();

        sa.assertTrue(birimIcerikSablonlarPage.getTxtSablonAdi().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getLovKullanilacakBirimler().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getSelEvrakTipi().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getBtnYeniSablonOlustur().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getBtnKaydet().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getBtnSil().is(disabled));
        sa.assertTrue(birimIcerikSablonlarPage.getBtnEvrakOnizleme().is(disabled));

        sa.assertEquals(birimIcerikSablonlarPage.getRowsBirimSablonlari().size(),
                birimIcerikSablonlarPage.getBtnDetayInEachRow().size(),
                "Birim Şablonlar tablosunda her satırda Detay butonu olması");

        sa.assertAll();
    }

    @Test
    @Description("Yeni şablon oluştur (Alt birimler görmesin)")
    public void tc1082() {
        String sablonAdi = "SABLON_" + getSysDate();

        birimIcerikSablonlarPage
                .yeniSablonOlustur(sablonAdi, "optiim birim", false)
                .islemMesaji().basariliOlmali();
        birimIcerikSablonlarPage
                .birimSablonlardaAra(sablonAdi);
    }

}
