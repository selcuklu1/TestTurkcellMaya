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

    @Test(enabled = true, description = "TC01957_A : Yazışma Kuralı Ekle")
    public void TC01957_A() {

<<<<<<< HEAD
=======
//        kurumYonetimiPage
//                .openPage()
//                .guncelle();
>>>>>>> fe78941825d7bfc70142fd315ab0edd14abee708

    }





}