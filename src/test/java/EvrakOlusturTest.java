import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EvrakOlusturPage;
import pages.EvrakOlusturPage.BilgilerTab;
import pages.pageComponents.belgenetElements.BelgentCondition;

public class EvrakOlusturTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    BilgilerTab tabBilgiler;

    @BeforeClass
    public void setUp() throws Exception {

        evrakOlusturPage = new EvrakOlusturPage();
        evrakOlusturPage.open();

        tabBilgiler = evrakOlusturPage.new BilgilerTab();
        tabBilgiler.open();
        tabBilgiler.konuKoduSec("ss").getTxtKonu();

    }

    @Test(dataProvider = "zorunluAlanlar")
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
                {"Konu Kodu", tabBilgiler.getCmlKonuKodu()}
                , {"Konu", tabBilgiler.getTxtKonu()}
        };
    }
}
