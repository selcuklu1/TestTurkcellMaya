import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EvrakOlusturPage;
import pages.pageComponents.belgenetElements.BelgentCondition;

public class EvrakOlusturTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    @BeforeClass
    public void setUp() throws Exception {

//        login();
//        new UstMenu().ustMenu("Evrak İşlemleri", "Evrak Oluştur");
//        evrakOlusturPage = new EvrakOlusturPage();
//        evrakOlusturPage.open();
//
//        tabBilgiler = evrakOlusturPage.new BilgilerTab();
//        tabBilgiler.open();
//        tabBilgiler.konuKoduSec("ss");

    }

    @Test
    public void testName() throws Exception {
        login();
//        group.$(By.partialLinkText(menuText)).click();
//
//        System.out.println("Text: " + $("button[id='topMenuForm:userMenuButton_button']").text());
//        System.out.println("InnerText: " + $("button[id='topMenuForm:userMenuButton_button']").innerText());
//        takeScreenshot();
    }

    @Test(enabled = false, dataProvider = "zorunluAlanlar")
    public void test1(String fieldName, Object field) {
//        if (field instanceof BelgenetElement) {
//            BelgenetElement dog = (BelgenetElement) field;
//        }
//        else{
        SelenideElement dog = (SelenideElement) field;
//        }


//        System.out.println("!!!!!!!!-" + field.toString());
        dog.shouldBe(BelgentCondition.required);
    }

    @DataProvider
    public Object[][] zorunluAlanlar1() {
        return new Object[][]{
//                {"Konu Kodu", tabBilgiler.getCmlKonuKodu()}
//                , {"Konu", tabBilgiler.getTxtKonu()}
        };
    }
}
