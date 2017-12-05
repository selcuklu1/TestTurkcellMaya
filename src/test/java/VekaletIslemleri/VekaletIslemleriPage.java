package VekaletIslemleri;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.VekaletVerPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class VekaletIslemleriPage extends BaseTest{
    MainPage mainPage;
    VekaletVerPage vekaletver;
    GelenEvraklarPage gelenEvraklarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        vekaletver = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        login("optiim", "123");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme")
    public void TC0025() throws InterruptedException{

        gelenEvraklarPage
                .openPage();
        String evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(1);

        vekaletver
                .openPage()
                .evrakEkle()
                .evrakAramaDoldur(evrakNo)
                .evrakAramaTabloKontrolveSecim(evrakNo)
                .vekaletVerenDoldur("Optiim Test")
                .devredilecekEvraklarKontrolu()
                .vekaletAlanDoldur("Optiim Test")
                .onayVerecekDoldur("Zübeyde TEKİN")
                .aciklamaDoldur("Test Otomasyon")
                .devredilecekEvrakSec(evrakNo)
                .uygula();
    }
}
