/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.DagitimHitapGuncelleme;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.OnayAkisYonetimiPage;
import pages.ustMenuPages.VekaletVerPage;

import static data.TestData.passwordZTEKIN;
import static data.TestData.usernameZTEKIN;

/****************************************************
 * Tarih: 2018-02-05
 * Proje: Türksat Functional Test Automation
 * Class: "Dağıtım Hitap Güncelleme" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Dağıtım Hitap Güncelleme")
public class DagitimHitapGuncellemeTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;

    @BeforeMethod
    public void loginBeforeTests() {
    evrakOlusturPage =new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2123: Birim hitabına NE NA eki eklenmesi")
    public void TS2123() {

        String geregiSecimTipi = "Birim";
        String geregi = "opt";

        login(usernameZTEKIN,passwordZTEKIN);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi)
                .secilenGeregiDagitimHitapGuncelleme();
        login(usernameZTEKIN,passwordZTEKIN);
    }

}
