package KisayolTuslari;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kısayol Tuşları" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class KisayolTuslariTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        evrakOlustur = new EvrakOlusturPage();
    }


    public void kisayolKontrol(String editorTab,String bilgileriTab,String kisayol,String sayfaAdi) throws InterruptedException {

        evrakOlustur
                .openPage()
                .openTab(editorTab)
                .editorIcerikDoldur(kisayol)
                .kisayolEkranKontrol(sayfaAdi,false);

        //String winHandleBefore = WebDriverRunner.getWebDriver().getWindowHandle();
        windowHandleBefore();

        evrakOlustur
                .PDFOnizleme();

        Thread.sleep(6000);
        switchToNewWindow();
/*        for(String winHandle : WebDriverRunner.getWebDriver().getWindowHandles()){
            WebDriverRunner.getWebDriver().switchTo().window(winHandle);
        }*/

        evrakOlustur
                .PDFOnizlemeKisayolGonder(kisayol);

        switchToDefaultWindow();
   /*     WebDriverRunner.getWebDriver().close();
        WebDriverRunner.getWebDriver().switchTo().window(winHandleBefore);*/

        evrakOlustur
                .kisayolEkranKontrol(sayfaAdi,false)
                .openTab(bilgileriTab)
                .konuDoldur(kisayol)
                .kisayolEkranKontrol(sayfaAdi,false)
                .kisayolSayfaAcma(kisayol)
                .kisayolEkranKontrol(sayfaAdi,true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Olur Oluştur ekranını açma")
    public void TC1952a() throws InterruptedException {

        String editorTab="Editör";
        String bilgileriTab="Bilgileri";
        String sayfaAdi="Olur Yazısı Oluştur";
        String kisayol=Keys.LEFT_SHIFT+"o";

        kisayolKontrol(editorTab,bilgileriTab,kisayol,sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TC1952: Kısayol tuşları kullanarak Kullanıcı Yönetimi ekranını açma")
    public void TC1952b() throws InterruptedException {

    }


}