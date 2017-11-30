package KisayolTuslari;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
<<<<<<< HEAD
import pages.MainPage;
import pages.ustMenuPages.EvrakOlusturPage;

import javax.activation.MailcapCommandMap;

=======
import pages.ustMenuPages.EvrakOlusturPage;

>>>>>>> 978364c0d6a202db360d56c8a2999abaf142682b
/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kısayol Tuşları" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class KisayolTuslariTest extends BaseTest {

<<<<<<< HEAD
    EvrakOlusturPage evrakOlusturPage;
    MainPage mainPage;

    String editorTab = "Editör";
    String bilgileriTab = "Bilgileri";
=======
    EvrakOlusturPage evrakOlustur;
>>>>>>> 978364c0d6a202db360d56c8a2999abaf142682b

    @BeforeMethod
    public void loginBeforeTests() {
        login();
<<<<<<< HEAD
        evrakOlusturPage = new EvrakOlusturPage();
        mainPage = new MainPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Olur Oluştur ekranını açma")
    public void TC1952a() throws InterruptedException {

        String sayfaAdi = "Olur Yazısı Oluştur";
        String kisayol = Keys.LEFT_SHIFT + "o";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Kullanıcı Yönetimi ekranını açma")
    public void TC1952b() throws InterruptedException {

        String sayfaAdi = "Kullanıcı Yönetimi";
        String kisayol = Keys.LEFT_SHIFT + "u";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Giden Evrak Kayıt ekranını açma")
    public void TC1952c() throws InterruptedException {

        String sayfaAdi = "Giden Evrak Kayıt";
        String kisayol = Keys.LEFT_SHIFT + "ı";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Evrak Oluştur ekranını açma")
    public void TC1952e() throws InterruptedException {

        String sayfaAdi = "Evrak Oluştur";
        String kisayol = Keys.LEFT_SHIFT + "e";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Evrak Arama ekranını açma")
    public void TC1952f() throws InterruptedException {

        String sayfaAdi = "Evrak Arama";
        String kisayol = Keys.LEFT_SHIFT + "a";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Genel Evrak Raporu ekranını açma")
    public void TC1952g() throws InterruptedException {

        String sayfaAdi = "Genel Evrak Raporu";
        String kisayol = Keys.LEFT_SHIFT + "n";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Gelen Evrak Kayıt ekranını açma")
    public void TC1952h() throws InterruptedException {

        String sayfaAdi = "Gelen Evrak Kayıt";
        String kisayol = Keys.LEFT_SHIFT + "g";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Karar Yazısı Oluştur ekranını açma")
    public void TC1952i() throws InterruptedException {

        String sayfaAdi = "Karar Yazısı Oluştur";
        String kisayol = Keys.LEFT_SHIFT + "k";
=======
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
>>>>>>> 978364c0d6a202db360d56c8a2999abaf142682b

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    @Severity(SeverityLevel.CRITICAL)
<<<<<<< HEAD
    @Test(enabled = true, description = "TC1955: Kısayol Tuşlarının Tooltipleri")
    public void TC1955() throws InterruptedException {

        mainPage
                .ustMenuEvrakIslemleriAc()
                .altMenuTooltipKontrol("Evrak Oluştur")
                .altMenuTooltipKontrol("Giden Evrak Kayıt")
                .altMenuTooltipKontrol("Gelen Evrak Kayıt")
                .altMenuTooltipKontrol("Evrak Arama")
                .altMenuTooltipKontrol("Olur Yazısı Oluştur")
                .altMenuTooltipKontrol("Karar Yazısı Oluştur")
                .ustMenuKullaniciIslemleri()
                .altMenuTooltipKontrol("Kullanıcı Yönetimi")
                .ustMenuRaporlar()
                .altMenuTooltipKontrol("Gelen Evrak Raporu")
                .altMenuTooltipKontrol("Personel ve Açık Evrak İstatistiği");
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1952: Kısayol tuşları kullanarak Personel ve Açık Evrak İstatistiği ekranını açma")
    public void TC1952d() throws InterruptedException {

        String sayfaAdi = "Personel ve Açık Evrak İstatistiği";
        String kisayol = Keys.LEFT_SHIFT + "p";

        kisayolKontrol(editorTab, bilgileriTab, kisayol, sayfaAdi);
    }

    //Tüm testlerde kullanılıyor.
    public void kisayolKontrol(String editorTab, String bilgileriTab, String kisayol, String sayfaAdi) throws InterruptedException {

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorIcerikDoldur(kisayol);

        evrakOlusturPage
                .kisayolEkranKontrol(sayfaAdi, false);
        windowHandleBefore();

        evrakOlusturPage
                .pdfOnIzleme()
                .switchToNewWindow();

        evrakOlusturPage
                .pdfKontrol
                .PDFOnizlemeKisayolGonder(kisayol);

        switchToDefaultWindow();

        evrakOlusturPage

                .kisayolEkranKontrol(sayfaAdi, false);

        evrakOlusturPage
                .bilgilerTabiAc()
                .konuDoldur(kisayol);

        evrakOlusturPage
                .kisayolEkranKontrol(sayfaAdi, false);

        evrakOlusturPage
                .bilgilerTabiAc()
                .kisayolSayfaAcma(kisayol);
        evrakOlusturPage
                .kisayolEkranKontrol(sayfaAdi, true);
    }
=======
    @Test(enabled = false, description = "TC1952: Kısayol tuşları kullanarak Kullanıcı Yönetimi ekranını açma")
    public void TC1952b() throws InterruptedException {

    }

>>>>>>> 978364c0d6a202db360d56c8a2999abaf142682b

}