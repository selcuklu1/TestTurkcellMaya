package tests.DagitimPlaniYonetim;

import common.BaseTest;
import data.User;
import org.testng.annotations.Test;
import pages.ustMenuPages.DagitimPlaniYonetimiPage;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.02.2018
 * Açıklama:
 */
public class DagitimPlaniYonetimiTest extends BaseTest {

    User optiim = new User("optiim", "123", "Optiim TEST");

    @Test(description = "TS1280: Yeni Dağıtım Planı Kayıt İşlemi", enabled = true)
    public void TS1280() throws Exception {
        login(optiim);

        DagitimPlaniYonetimiPage page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni();
        

    }
}
