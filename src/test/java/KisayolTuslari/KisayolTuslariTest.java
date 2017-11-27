package KisayolTuslari;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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


    public void kisayolKontrol(String editorTab, String bilgileriTab, String kisayol, String sayfaAdi) throws InterruptedException {

        evrakOlustur
                .openPage()
                .editorTabAc()
                .editorIcerikDoldur(kisayol);

        evrakOlustur
                .kisayolEkranKontrol(sayfaAdi, false);

        windowHandleBefore();

        evrakOlustur
                .pdfOnIzleme();

        Thread.sleep(6000);
        switchToNewWindow();

        evrakOlustur
                .PDFOnizlemeKisayolGonder(kisayol);

        switchToDefaultWindow();

        evrakOlustur
                .kisayolEkranKontrol(sayfaAdi, false)
                .bilgilerTabiAc()
                .konuDoldur(kisayol);

        evrakOlustur
                .kisayolEkranKontrol(sayfaAdi, false)
                .kisayolSayfaAcma(kisayol)
                .kisayolEkranKontrol(sayfaAdi, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Olur Oluştur ekranını açma")
    public void TC1952a() throws InterruptedException {

        String editorTab = "Editör";
        String bilgileriTab = "Bilgileri";
        String sayfaAdi = "Olur Yazısı Oluştur";
        String kisayol = Keys.LEFT_SHIFT + "o";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = false, description = "TC1952: Kısayol tuşları kullanarak Kullanıcı Yönetimi ekranını açma")
    public void TC1952b() throws InterruptedException {

    }


}