package tests.HitaptaBuyukKucukHarf;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.DriverEventListener;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.SearchTable;
import pages.pageData.alanlar.EvrakDili;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.SistemSabitleriPage;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static pages.pageData.alanlar.GeregiSecimTipi.DAGITIM_PLANLARI;
import static pages.pageData.alanlar.GeregiSecimTipi.KULLANICI;
import static pages.pageData.alanlar.OnayKullaniciTipi.IMZALAMA;
import static pages.pageData.alanlar.OnayKullaniciTipi.PARAFLAMA;

/****************************************************
 * Tarih: 2017-12-28
 * Proje: Türksat Functional Test Automation
 * Class: "Hitapta Büyük Küçük Harf" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class HitaptaBuyukKucukHarfTest extends BaseTest {

    EvrakOlusturPage evrakOlustur;
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

    /*@BeforeMethod
    public void loginBeforeTests() {
    }*/

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2064: Tüzel kişi- Hitapta büyük/küçük harf kontrolü")
    public void TS2064() throws InterruptedException {

        String geregiSecimTipi = "Tüzel Kişi";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE TÜZEL KİŞİ";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE TÜZEL KİŞİYE";

        String geregiHepsiKucuk = "hepsi küçük harflerle tüzel kişi";
        String beklenenHepsiKucukHitap = "HEPSİ KÜÇÜK HARFLERLE TÜZEL KİŞİYE";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Tüzel Kişi";
        String beklenenBuyukKucukHitap = "BÜYÜK KÜÇÜK HARFLERLE TÜZEL KİŞİYE";

        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2156: Kurum- Hitapta büyük/küçük harf kontrolü")
    public void TS2156() throws InterruptedException {

        String geregiSecimTipi = "Kurum";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE KURUM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE KURUMA";

        String geregiHepsiKucuk = "hepsi küçük harflerle kurum";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle kuruma";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Kurum";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Kuruma";
        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2157: Birim- Hitapta büyük/küçük harf kontrolü")
    public void TS2157() throws InterruptedException {

        String geregiSecimTipi = "Birim";

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE BİRİM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE BİRİME";

        String geregiHepsiKucuk = "hepsi küçük harflerle birim";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle birime";

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Birim";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Birime";
        login("ztekin", "123");
        evrakOlustur = new EvrakOlusturPage();
        evrakOlustur
                .openPage();

        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSonKayitSil();

    }

    @Test(enabled = true, description = "TS2090: Dağıtım planına ve kullanıcıya hitap")
    public void TS2090() throws Exception {
        //Dağıtım planına ve kullanıcıya hitap
        //sistemde kayıtlı dağıtım planı olmalı, dağıtım planının içeriğinde küçük harfli birim, büyük harfli kurum olmalı
//        DAĞITIM YERLERİNE
//        login(user1);
        String uygulanacakDeger;
        User user = optiim;

        login(user);

        SistemSabitleriPage sistemSabitleriPage = new SistemSabitleriPage().openPage();
        sistemSabitleriPage.sorgulamaVeFiltreleme().alanDoldur("Ad", "Dağıtım Planı Hitap").butonaTikla("Ara");

        sistemSabitleriPage.openSistemSabitleriTab("Genel İşlemler");
        SearchTable searchTable = sistemSabitleriPage.getSistemSabitleriList("Genel İşlemler").findRows(text("Dağıtım Planı Hitap"));
        uygulanacakDeger = searchTable.getColumnValue("Uygulanacak Değer").text();
        searchTable.columnHeaderControl(text("Ad"), text("Uygulanacak Değer"), text("Aktif Değer"), text("Durum"), text("Açıklama"));

        String konu = "TS2090_" + getSysDate();
        pages.newPages.EvrakOlusturPage evrakOlusturPage = new pages.newPages.EvrakOlusturPage().openPage();
        evrakOlusturPage.bilgileriTab()
                .konuKoduSec("010.10")
                .konuDoldur(konu)
                .evrakDiliSec(EvrakDili.Turkce)
                .kaldiralacakKlasorleriSec("Diğer")
                .geregiSecimTipiSec(DAGITIM_PLANLARI)
                .geregiSec("KÜÇÜK BİRİM BÜYÜK KURUM")
                .geregiSecimTipiSec(KULLANICI)
                .geregiSec("tekin")
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user, PARAFLAMA)
                .anlikOnayAkisKullanicininTipiSec(user, IMZALAMA)
                .kullanButonaTikla()
                .evrakPageButtons().getImzalaButton().shouldBe(visible);
        evrakOlusturPage.editorTab().openTab().getEditor().type("Editör tekst");
        evrakOlusturPage.evrakPageButtons().evrakImzala();

/*WebDriver.Window a; a.
        com.codeborne.selenide.webdriver.DefaultDriverFactory defaultDriverFactory

        FirefoxOptions options = new FirefoxOptions()
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", TestData.docDownloadPathWindows);
        Capabilities caps = getCapabilities();
        caps.merge(options);
        Selenide.close();
        WebDriver driver = new EventFiringWebDriver(new FirefoxDriver(options)).register(new DriverEventListener()) ;
        WebDriverRunner.setWebDriver(driver);

        login(ztekin);
        new SolMenu().openMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar);
        new MainPage().searchTable().findRows(text(konu)).getFoundRow().click();*/


    }


    @BeforeMethod
    public void tsDriver() {
        try {

            //Capabilities caps = getCapabilities();
            //caps.merge(options);

            FirefoxOptions options = new FirefoxOptions();
            options.setAcceptInsecureCerts(true)
                    .addPreference("security.insecure_field_warning.contextual.enabled", false)
                    .addPreference("browser.download.folderList", 2)
                    .addPreference("browser.download.dir", TestData.docDownloadPathLinux + "/qqq");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            options.merge(capabilities);
            WebDriver driver;
            if (Configuration.remote == null)
                 driver = new EventFiringWebDriver(new FirefoxDriver(options))
                        .register(new DriverEventListener());
            else driver = new EventFiringWebDriver(new RemoteWebDriver(new URL(Configuration.remote), options))
                    .register(new DriverEventListener());


            WebDriverRunner.setWebDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException("Invalid 'remote' parameter: " + Configuration.remote, e);
        }

    }

    public void hitapKontrol(String geregiSecimTipi, String geregi, String beklenenHitap) throws InterruptedException {

        evrakOlustur
                .bilgilerTabiAc()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiDoldur(geregi, "");

        evrakOlustur
                .editorTabAc()
                .editorHitapKontrol(beklenenHitap);

        windowHandleBefore();

        evrakOlustur
                .pdfOnIzleme();

        Thread.sleep(6000);
        switchToNewWindow();

        evrakOlustur
                .pdfKontrol
                .PDFHitapKontrol(beklenenHitap);

        switchToDefaultWindow();

        evrakOlustur
                .bilgilerTabiAc();
    }
}
