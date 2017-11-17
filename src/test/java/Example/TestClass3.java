package Example;

import common.BaseTest;
import org.testng.annotations.Test;
import page.ustMenuPages.EvrakOlusturPage;
import page.ustMenuPages.GelenEvrakKayitPage;
import pageComponents.BasePage;

public class TestClass3 extends BaseTest {

    BasePage page = new BasePage();

    @Test
    public void testName() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
        new EvrakOlusturPage().konuKoduDoldur("010.10");
    }

    @Test
    public void testName2() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
    }

    @Test
    public void testName3() {
        page.loginPage().login();
        page.ustMenu("Evrak Oluştur");
    }


    @Test
    public void ustYaziFileUpload() {
        page.loginPage().login();
        page.ustMenu("Gelen Evrak Kayıt");
        new GelenEvrakKayitPage().ustYaziUploadFile("C:\\TestAutomation\\BelgenetFTA\\testpdf.pdf");
    }
}
