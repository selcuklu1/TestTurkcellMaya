package tests.OlurYazisiIslemleri;

import common.BaseTest;
import data.User;
import galen.GalenControl;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.AltTabs;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageComponents.tabs.EditorTab;
import pages.pageData.SolMenuData;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static pages.pageData.alanlar.OnayKullaniciTipi.IMZALAMA;
import static pages.pageData.alanlar.OnayKullaniciTipi.PARAFLAMA;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public class OlurYazisiIslemleriTest extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1", "Ağ (Network) Uzmanı");
    User user3 = new User("user3", "123", "User3 TEST", "AnaBirim1", "Ağ (Network) Uzmanı");
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "Optiim Birim", "Altyapı ve Sistem Yönetim Uzmanı");

    OlurYazisiOlusturPage olurYazisiOlusturPage;
    AltTabs altTabs;
    MainPage mainPage;

    String konu = "TS0577_" + getSysDate();
    String doc = "documents/Otomasyon.pdf";
    //konuKodu dokümanda görünen değeri "01-010.10-" konuKodu değiştirince sayi değiştirmeye unutma!(?dönüştürme metodu?)
    String konuKodu = "010.10";
    String konuKoduSayi = "01-010.10-";


    @BeforeMethod(alwaysRun = false)
    public void setUp() {
        //Configuration.browser = "drivers.Chrome";

        /*FirefoxOptions options = new FirefoxOptions()
                .setAcceptInsecureCerts(true)
                .addPreference("security.insecure_field_warning.contextual.enabled", false)
                .setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", TestData.docDownloadPathWindows);
        *//*Capabilities caps = getCapabilities();
        caps.merge(options);*//*
        try {
            URL hub = new URL(Configuration.remote.toString());
            RemoteWebDriver driver = new RemoteWebDriver(hub, options);
            WebDriverRunner.setWebDriver(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        //WebDriver driver = new EventFiringWebDriver(new FirefoxDriver(options)).register(new DriverEventListener());
//        WebDriverRunner.setWebDriver(new FirefoxDriver(options));
        //WebDriverRunner.addListener(new DriverEventListener());
//        WebDriverRunner.setWebDriver((new EventFiringWebDriver(WebDriverRunner.getWebDriver()).register(new DriverEventListener())));
    }

    //Teskilat Kisi tanimlari-->birim yönetimi ekranında birimin olur metni boş olmalı
    //@Link(name = "Galen", type = "html", url = "file:///Users/ilyas/WorkspaceJava/Git/BelgenetFTA/galenReports/TS0577/report.html")
    //@Link(name = "Galen", type = "html", url = "galenReports/TS0577/report.html")
    @Test(description = "TS0577: Olur yazısı oluşturulması ve gönderilmesi", enabled = true)
    public void TS0577() throws Exception {

        System.out.println("Konu: " + konu);
        login(user1);
        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();

        bilgileriTab();
        altTabs = olurYazisiOlusturPage.ekleriTab().openTab().altTabs();
        ekleriTab();
        ilgileriTab();
        iliskiliEvraklar();
        olurYazisiOlusturPage.editorTab().openTab();
        editorTabGalen();
        olurYazisiOlusturPage.editorTab().getEditor().type("Editör tekst");
        imzalaVeOnayaSun();
        hazirladiklarimMenudaGorunmeli();
        ilkImzaciImzala();
        ikinciImzaciImzala();
        geregiBirimKullanici();
        postaciKontolleri();
    }


    OlurYazisiOlusturPage olurYazisiOlusturPage2;
    BilgilerTab bilgilerTab;
    EditorTab editorTab;

    @Test(description = "TS1488: Olur yazısında alan kontrolleri", enabled = false)
    public void TS1488() {
        login(user1);
        olurYazisiOlusturPage2 = new OlurYazisiOlusturPage().openPage();
        bilgilerTab = olurYazisiOlusturPage2.bilgileriTab();

        step2();
        step3();
        step4();
        step5();
        step6();
        step7();
        step8_12();

        //Step13
        bilgilerTab.ivedilikSec(Ivedilik.GUNLU)
            .miatTemizle()
                .evrakPageButtons().paraflaButonaTikla()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");
        bilgilerTab.miatDoldur(getSysDateForKis())
                .bilgiTemizle()
                .geregiTemizle();

        //ilk seferde tıklamasına rağmen açılmıyor sayfa, bu sebeb ile iki kere openTab kullanılıdı
        olurYazisiOlusturPage2.editorTab().openTab(true);

    }

    //region TS1488 steps
    @Step("Gereği alanından içinde kurum dışı olan bir dağıtım planı seç")
    public void step2(){
        bilgilerTab.geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI);
        boolean empty = bilgilerTab.getGeregiCombolov().type("TS1488").isEmpty();
        Assert.assertTrue(empty, "Dağıtım planının gelmediği görülür.");
        bilgilerTab.getGeregiCombolov().closeTreePanel();
    }

    @Step("Editör ekranını boş bırak - İmzala")
    public void step3(){
        bilgilerTab.konuKoduSec("010.01")
                .konuDoldur("TS1488")
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user1, IMZALAMA)
                .kullanButonaTikla();
        olurYazisiOlusturPage2.pageButtons().imzalaButonaTikla()
                .islemMesaji().dikkatOlmali("Yazı içeriği boş olamaz");
    }

    @Step("Editör tabında içeriği doldur, Konu Kodu alanını boş bırak, - İmzala")
    public void step4(){
        editorTab = olurYazisiOlusturPage2.editorTab();
        editorTab.openTab().getEditor().type("editör tekst");
        bilgilerTab.openTab().konuKoduTemizle()
                    .konuDoldur("asdsad").getKonuKodu().sendKeys(Keys.TAB);
        olurYazisiOlusturPage2.pageButtons().imzalaButonaTikla()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");
        //Evrak konusu boş olamaz!
    }

    @Step("Konu kodu alanını doldur ve Konu alanını boş bırak - İmzala")
    public void step5(){
        bilgilerTab.openTab().konuKoduSec("010.01")
                .konuTemizle();
        olurYazisiOlusturPage2.pageButtons().imzalaButonaTikla()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");
        //Evrak konusu boş olamaz!
    }

    @Step("Konu alanını doldur, Kaldırılacak klasörler alanını boş bırak - İmzala")
    public void step6(){
        bilgilerTab.openTab().konuDoldur("aaa")
            .kaldiralacakKlasorleriTemizle();
        olurYazisiOlusturPage2.pageButtons().imzalaButonaTikla()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");
        //Evrak konusu boş olamaz!
    }

    @Step("Kaldırılacak klasörler alanını doldur, Onay akışı alanını boş bırak - Kaydet ve onaya sun butonunu tıkla")
    public void step7(){
        bilgilerTab.openTab().kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle();
        olurYazisiOlusturPage2.pageButtons()
                .evrakPageButtons().evrakKaydet()
                .islemMesaji().basariliOlmali();
        olurYazisiOlusturPage2.pageButtons().evrakKaydetVeOnayaSunTikla()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");
        //Evrak konusu boş olamaz!
    }


    @Step("İmzaci kontroller")
    public OlurYazisiIslemleriTest step8_12(){
        bilgilerTab
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user1, PARAFLAMA)
                .kullanButonaTikla()
                .islemMesaji().dikkatOlmali("Eklemek istediğiniz onay akışında imzacı bulunmuyor. Lütfen onay akışında en az bir imzacı seçiniz.");
        bilgilerTab.anlikOnayAkisKullaniciVeTipiSec(user2, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(user3, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(optiim, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(ztekin, IMZALAMA)
                .kullanButonaTikla()
                .onayAkisiSecilenKullaniciKontrolEt(user2, IMZALAMA)
                .onayAkisiSecilenKullaniciKontrolEt(user3, IMZALAMA)
                .onayAkisiSecilenKullaniciKontrolEt(optiim, IMZALAMA)
                .onayAkisiSecilenKullaniciKontrolEt(ztekin, IMZALAMA);
        return this;
    }
    //endregion

    //region TS0577 steps
    @Step("Bilgileri sekmesinde alanları doldur")
    private void bilgileriTab(){
        olurYazisiOlusturPage.bilgileriTab().openTab()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .gizlilikDerecesiSec(GizlilikDerecesi.NORMAL)
                .ivedilikSec(Ivedilik.NORMAL)
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(optiim.getBirimAdi())
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user1.getFullname(), PARAFLAMA)
                .anlikOnayAkisKullanicilariTemizle()
                .anlikOnayAkisKullaniciVeTipiSec(user2, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(user3, IMZALAMA)
                .kullanButonaTikla();
    }

    @Step("Ekleri sekmesinde ekleme ve kontrolleri")
    private void ekleriTab(){
        altTabs.getFiziksetEkEkleTab().shouldBe(visible);
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getWebAdresiniEkleTab().shouldBe(visible);
        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ekMetniDoldur("Ekleri Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.ekleriTab().getEkListesiTablosu().findRows(text("Ekleri Tab "+ konu)).shouldHaveSize(1);
    }

    @Step("İlgileri sekmesinde ekleme ve kontrolleri")
    private void ilgileriTab(){
        altTabs = olurYazisiOlusturPage.ilgileriTab().openTab().altTabs();
        altTabs.getSistemdeKayitliEvrakEkleTab();
        altTabs.getDosyaEkleTab();
        /*altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ilgiMetniDoldur("İlgileri Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.ilgileriTab().getIlgliliListesiTablosu().findRows(text("İlgileri Tab "+ konu)).shouldHaveSize(1);
        */
        altTabs.metinEkleTabiAc().ilgiMetniDoldur("Metni Tab " + konu).ekleButonaTikla();
        olurYazisiOlusturPage.ilgileriTab().getIlgliliListesiTablosu().findRows(text("Metni Tab " + konu)).shouldHaveSize(1);
    }

    @Step("İlişkili Evraklar sekmesinde ekleme ve kontrolleri")
    private void iliskiliEvraklar() {
        //region İlişkili Evraklar. Sistemde kayıtlı evrak ekle, tercüme evrak ekle
        altTabs = olurYazisiOlusturPage.iliskiliEvraklarTab().openTab().altTabs();
        altTabs.getDosyaEkleTab().shouldBe(visible);
        altTabs.sistemdeKayitliEvrakEkleTabiAc().evrakAraDoldur("a").dokumanAraTikla().islemMesaji().basariliOlmali();
        String docSati = altTabs.getSistemdeKayitliEvrakListesi().findRows().shouldHaveSize(1).useFirstFoundRow().getColumnValue( "Sayı").text();
        altTabs.getSistemdeKayitliEvrakListesi().foundRow().dokumanEkleTikla();
        olurYazisiOlusturPage.iliskiliEvraklarTab().getEkListesiTablosu().findRows(text(docSati)).shouldHaveSize(1);

        altTabs.tercumeEkleTabiAc()
                .dosyaEkle(doc)
                .ilisikMetniDoldur("Tercüme Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.iliskiliEvraklarTab().getEkListesiTablosu().findRows(text("Tercüme Tab "+ konu)).shouldHaveSize(1);
    }

    @Step("Editör tabında alanların layout kontorlü")
    private void editorTabGalen() throws IOException{
        //Editör Tab Galen kontroller
        olurYazisiOlusturPage.editorTab().openTab();

        Allure.addAttachment("Step7", ".... Makamına ifadesinin hitapta geldiği görülür.\n" +
                "\n" +
                "Metin alanının altında \n" +
                "sağda ilk imzacının \n" +
                "ortada OLUR ifadesi ile tarih alanının, \n" +
                "ortada son imzacının geldiği görülür.\n" +
                "\n" +
                "Her imzacının üstünde ve hitap alanının üstünde metin girilecek alanın geldiği görülür.");

        Allure.addAttachment("Step18", "Editör ekranının gereği alanında seçilen birimin adı ile hitabın geldiği görülür.\n" +
                "\n" +
                "sayı ve konu bilgilerinin\n" +
                "\n" +
                "altında ilginin a ve b olarak listelendiği \n" +
                "sayfanın altında eklerin girildiği isimlerle listelendiği görülür.");

        sleep(3000);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("birim", user1.getBirimAdi());
        params.put("sayi", konuKoduSayi);
        params.put("konu", konu);
        params.put("ilgi_a", "İlgileri Tab "+ konu);
        params.put("ilgi_b", "Metni Tab " + konu);
        params.put("ilgi_b", "Metni Tab " + konu);
        params.put("imzaci1Isim", user2.getFullname());
        params.put("imzaci1Gorev", user2.getGorev());
        params.put("imzaci2Isim", user3.getFullname());
        params.put("imzaci2Gorev", user3.getGorev());
        params.put("ek", "Ekleri Tab "+ konu);

        GalenControl galen = new GalenControl();
        galen.generateDump("TS0577", params);
        galen.layoutControl("TS0577", params);
    }

    @Step("İmzala ve Onaya sun")
    private void imzalaVeOnayaSun(){
        //imzala
        olurYazisiOlusturPage.pageButtons().evrakKaydetVeOnayaSunTikla();
        olurYazisiOlusturPage.getPage().shouldHave(text("1. İmzalama " + user2.getFullname()));
        olurYazisiOlusturPage.getPage().shouldHave(text("2. İmzalama " + user3.getFullname()));
        olurYazisiOlusturPage.onayIslemAciklamaDoldur("Onay işlem açıklama")
                .pageButtons().gonderTikla()
                .confirmDialog().confirmEvetTikla();
        olurYazisiOlusturPage.islemMesaji().basariliOlmali();
    }

    @Step("Hazırladıklarım sayfada gideceği yer kontrolü")
    private void hazirladiklarimMenudaGorunmeli(){
        //21	Sol menü > İşlem yaptıklarım > Hazırladıklarım menüsünü aç	Olur yazısının gideceği yer doğru olarak listelendiği görülür.
        mainPage = olurYazisiOlusturPage.solMenu(SolMenuData.IslemYaptiklarim.Hazirladiklarim);
        mainPage.searchTable().findRows(text(konu), text("Gideceği Yer: "+ optiim.getBirimAdi())).shouldHaveSize(1);
    }

    @Step("İlk imzaci ile imzala ve kontrol et")
    private void ilkImzaciImzala(){
        login(user2);
        mainPage.solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler)
                .searchTable()
                .findRows(text(konu))
                .shouldHaveSize(1).getFoundRow().click();
        mainPage.evrakImzala().islemMesaji().basariliOlmali();
        mainPage.solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim).searchTable().findRows(text(konu));
    }

    @Step("İkinci imzaci ile imzala ve kontrol et")
    private void ikinciImzaciImzala(){
        login(user3);
        mainPage.solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler)
                .searchTable().findRows(text(konu)).shouldHaveSize(1).getFoundRow().click();
        mainPage.evrakPageButtons().evrakImzala().islemMesaji().basariliOlmali();
        mainPage.solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim).searchTable().findRows(text(konu)).shouldHaveSize(1);
    }

    @Step("Gereği alanında seçilen birimde Teslim Alınmayı Bekleyenler sayfada listenmeli")
    private void geregiBirimKullanici(){
        //"Gereği alanında seçilken birimden bir kullanıcı ile sisteme login ol
        //Sol menü > birim evrakları > Teslim alınmayı bekleyenler listesini aç"
        login(optiim);
        mainPage.solMenu(SolMenuData.BirimEvraklari.TeslimAlinmayiBekleyenler)
                .searchTable().findRows(text(konu)).shouldHaveSize(1);
    }

    @Step("Evrakın hazırlandığı birimin postacıda listelenmediği görülür")
    private void postaciKontolleri(){
        //"Evrakın hazırlandığı birimin postacı biriminden bir kullanıcı ile login ol
        //Sol menü > Birim evrakları > Postalanacaklar/Postalananlar evraklar menüsünü tıkla"	Olur yazısının !!!listelenmediği!!! görülür.
        login(user1);
        mainPage.solMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar)
                .searchTable().searchInAllPages(false).findRows(text(konu)).shouldHaveSize(0);
        mainPage.solMenu(SolMenuData.BirimEvraklari.Postalananlar)
                .searchTable().searchInAllPages(false).findRows(text(konu)).shouldHaveSize(0);
    }
    //endregion
}
