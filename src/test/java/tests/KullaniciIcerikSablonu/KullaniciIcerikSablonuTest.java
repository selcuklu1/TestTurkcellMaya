package tests.KullaniciIcerikSablonu;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciIcerikSablonuPage;
import pages.ustMenuPages.KullaniciListesiYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Can
 ****************************************************/
public class KullaniciIcerikSablonuTest extends BaseTest
{
    KullaniciIcerikSablonuPage kullaniciIcerikSablonuPage;


    @BeforeMethod
    public void loginBeforeTests()
    {
        kullaniciIcerikSablonuPage = new KullaniciIcerikSablonuPage();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0987 : Yeni kullanıcı şablonu oluşturmaYeni kullanıcı şablonu oluşturma")
    public void TS0987() throws InterruptedException
    {
        String sablonAdi = "TS0987-"+createRandomNumber(10);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        kullaniciIcerikSablonuPage
                .openPage()
                .yeniSablonOlusturGeldigiGorme()
                .silGeldigiGorme()
                .kaydetGeldigiGorme()
                .sablonAdiGeldigiGorme()
                .evrakOnizlemeGeldigiGorme();

        kullaniciIcerikSablonuPage
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi);

        kullaniciIcerikSablonuPage
                .stepmethod("Verdana");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE==result.getStatus()){
           TS0987();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0987 : Yeni kullanıcı şablonu oluşturmaYeni kullanıcı şablonu oluşturma")
    public void TS09871() throws InterruptedException
    {
        String sablonAdi = "TS0987-"+createRandomNumber(10);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        kullaniciIcerikSablonuPage
                .openPage()
                .yeniSablonOlusturGeldigiGorme()
                .silGeldigiGorme()
                .kaydetGeldigiGorme()
                .sablonAdiGeldigiGorme()
                .evrakOnizlemeGeldigiGorme();

        kullaniciIcerikSablonuPage
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi);

        kullaniciIcerikSablonuPage
                .stepmethod("Verdana");
    }

}