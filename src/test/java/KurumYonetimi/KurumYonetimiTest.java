package KurumYonetimi;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.KurumYonetimiPage;


public class KurumYonetimiTest extends BaseTest {

   KurumYonetimiPage kurumYonetimiPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kurumYonetimiPage = new KurumYonetimiPage();
        login();
    }

    @Test(enabled = true, description = "TC01459 : Kurum bilgisi güncellemee")
    public void TC01957_A() {
        kurumYonetimiPage
                .openPage()
                .ara()
                .kurumGuncelle("Yargı")
                .idariBirimKimlikKoduDoldur("95123123")
                .ustKurumSec("Adalet Bakanlığı")
                .kurumAdiDoldur("Yeni Kurum Adi")


    }





}