package tests.EvrakHavaleIslemleri;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.BakimaAlPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.passwordZTEKIN;
import static data.TestData.usernameZTEKIN;


public class EvrakHavaleIslemleriTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    @BeforeMethod
    public void loginBeforeTests() {
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
    }

    @Test(enabled = true, description = "2295: Toplu havale ekranı alan kontrolü")
    public void TS2295() {

        String konuKodu = "TS2295-"+createRandomNumber(15);
        String konuKodu2 = "TS2295-2_"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "Zübeyde Tekin";

        login(usernameZTEKIN,passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitKullaniciHavaleEt(konuKodu,kurum,kullanici);

        gelenEvrakKayitPage
                .evrakOlusturSayfayiKapat();

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKodu2,kurum,"Yazılım Geliştirme Direktörlüğü");
    }

}