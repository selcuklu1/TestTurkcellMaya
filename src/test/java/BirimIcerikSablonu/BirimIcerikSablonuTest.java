package BirimIcerikSablonu;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
import pages.ustMenuPages.BirimIcerikSablonlarPage;

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
        birimIcerikSablonlarPage.openPage()
                .yeniSablonOlustur();

        TextEditor editor = new TextEditor();
        editor
                .toolbarCombo("Biçim", "Başlık 1")
                .toolbarButton("Kalın").activate();

        editor.editor().type("dsddsdsdsdsd");

    }

}
