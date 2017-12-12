package TopluPostalama;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;


public class TopluPostalamaTest extends BaseTest {


    TopluPostalanacakEvraklarPage topluPostalanacakEvraklarPage;


    @BeforeMethod
    public void loginBeforeTests() {

        topluPostalanacakEvraklarPage = new TopluPostalanacakEvraklarPage();
    }

    @Test(enabled = true, description = "1804 : Toplu Postalanacak Evrakların Sorgulanması (UC_POSTAYÖNETİMİ_001)")
    public void TC01804() {


        login("mbozdemir", "123");
        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu();
        //.gidecegiYerSec("Başbakan Başmüşavirleri", true);


    }


}