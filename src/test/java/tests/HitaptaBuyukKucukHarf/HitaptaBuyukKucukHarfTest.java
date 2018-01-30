package tests.HitaptaBuyukKucukHarf;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.User;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.SolMenu;
import pages.pageData.SolMenuData;
import pages.pageData.alanlar.EvrakDili;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.ustMenuPages.SistemSabitleriPage;

import static com.codeborne.selenide.Condition.*;
import static pages.pageData.alanlar.GeregiSecimTipi.*;
import static pages.pageData.alanlar.OnayKullaniciTipi.IMZALAMA;
import static pages.pageData.alanlar.OnayKullaniciTipi.PARAFLAMA;

/****************************************************
 * Tarih: 2017-12-28
 * Proje: Türksat Functional Test Automation
 * Class: "Hitapta Büyük Küçük Harf" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/
@Feature("Hitapta büyük/küçük harf")

public class HitaptaBuyukKucukHarfTest extends BaseTest {

    /*
    EvrakOlusturPage evrakOlustur;
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2064: Tüzel kişi - Hitapta büyük/küçük harf kontrolü")
    public void TS2064() throws InterruptedException {

        String geregiSecimTipi = TUZEL_KISI.getOptionText();

        login(ztekin);
        evrakOlustur = new EvrakOlusturPage().openPage();

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE TÜZEL KİŞİ";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE TÜZEL KİŞİYE";
        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        String geregiHepsiKucuk = "hepsi küçük harflerle tüzel kişi";
        String beklenenHepsiKucukHitap = "HEPSİ KÜÇÜK HARFLERLE TÜZEL KİŞİYE";
        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Tüzel Kişi";
        String beklenenBuyukKucukHitap = "BÜYÜK KÜÇÜK HARFLERLE TÜZEL KİŞİYE";
        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2156: Kurum- Hitapta büyük/küçük harf kontrolü")
    public void TS2156() throws InterruptedException {

        String geregiSecimTipi = KURUM.getOptionText();

        login(ztekin);
        evrakOlustur = new EvrakOlusturPage().openPage();

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE KURUM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE KURUMA";
        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        String geregiHepsiKucuk = "hepsi küçük harflerle kurum";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle kuruma";
        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Kurum";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Kuruma";
        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2157: Birim- Hitapta büyük/küçük harf kontrolü")
    public void TS2157() throws InterruptedException {

        String geregiSecimTipi = GeregiSecimTipi.BIRIM.getOptionText();

        login(ztekin);
        evrakOlustur = new EvrakOlusturPage().openPage();

        String geregiHepsiBuyuk = "BÜYÜK HARFLERLE BİRİM";
        String beklenenHepsiBuyukHitap = "BÜYÜK HARFLERLE BİRİME";
        hitapKontrol(geregiSecimTipi, geregiHepsiBuyuk, beklenenHepsiBuyukHitap);

        String geregiHepsiKucuk = "hepsi küçük harflerle birim";
        String beklenenHepsiKucukHitap = "hepsi küçük harflerle birime";
        hitapKontrol(geregiSecimTipi, geregiHepsiKucuk, beklenenHepsiKucukHitap);

        String geregiBuyukKucuk = "Büyük Küçük Harflerle Birim";
        String beklenenBuyukKucukHitap = "Büyük Küçük Harflerle Birime";
        hitapKontrol(geregiSecimTipi, geregiBuyukKucuk, beklenenBuyukKucukHitap);
    }

    @Test(enabled = true, description = "TS2090: Dağıtım planına ve kullanıcıya hitap")
    public void TS2090() throws Exception {
        //Dağıtım planına ve kullanıcıya hitap
        //sistemde kayıtlı dağıtım planı olmalı, dağıtım planının içeriğinde küçük harfli birim, büyük harfli kurum olmalı
        //DAĞITIM YERLERİNE

        useFirefox();

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
                .kullanButonaTikla();
        evrakOlusturPage.pageButtons().getImzalaButton().shouldBe(visible);
        evrakOlusturPage.editorTab().openTab().getEditor().type("Editör tekst");
        evrakOlusturPage.pageButtons().evrakImzala();

        step9_10(konu);

        searchTable = new SearchTable(Selenide.$("#mainPreviewForm\\:dataTableId"));
        SelenideElement row = gonderilenYer(uygulanacakDeger, searchTable);
        postalanackYazdir(row);
        detayYazdir(konu);
        yazdirPdf();

        row = gonderilenYer("Sayın " + ztekin.getFullname(), searchTable);
        postalanackYazdir(row);
        detayYazdir(konu);
        Selenide.switchTo().window(1);
        Selenide.$$x("//*[.='Sayın " + ztekin.getFullname() + "']")
                .shouldHaveSize(2).first().shouldBe(visible);
        Allure.addAttachment("Hitap \"Sayın " + ztekin.getFullname() + "\" kontrolü", "");
        takeScreenshot();
        Selenide.close();
    }

    @Step("Postalanacak Evraklarda evrağı bul ve seç")
    private void step9_10(String konu) {
        login(ztekin);
        new SolMenu().openMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar);
        new MainPage().searchTable().findRows(text(konu)).getFoundRow().click();
        Selenide.$("button .postala").click();
    }

    @Step("Postalanacak Yerler. Gönderilen Yer kontrolü")
    private SelenideElement gonderilenYer(String gonderilenYer, SearchTable table) {
        return table.findRows(textCaseSensitive(gonderilenYer)).getFoundRow().shouldBe(visible);
    }

    @Step("Postalanacak Yerler. Yazdır butona tıklanır")
    public HitaptaBuyukKucukHarfTest postalanackYazdir(SelenideElement row) {
        row.$x("descendant::button[.='Yazdır']").shouldBe(visible).click();
        return this;
    }

    @Step("Evrak Detayları. Yazdır butona tıklanır")
    public HitaptaBuyukKucukHarfTest detayYazdir(String konu) {
        new SearchTable(Selenide.$("#postaDetayYazdirForm\\:dtPostaEvrakUstVeri"))
                .findRows(text(konu))
                .getFoundRow()
                .$x("descendant::button[.='Yazdır']").click();
        return this;
    }

    @Step("Yazdır ekranda Hitap kontrolü")
    public HitaptaBuyukKucukHarfTest yazdirPdf() {
        Selenide.switchTo().window(1);
        ElementsCollection pages = Selenide.$$("#viewer .page");
        Allure.addAttachment("Sayfa sayısı 2 kotrolu", String.valueOf(pages.size()));
        pages.shouldHaveSize(2);

        String birimHitap = "hepsi küçük harflerle birime";
        SelenideElement e = Selenide.$x("//*[.='" + birimHitap + "']");
        Allure.addAttachment("Hitap \"" + birimHitap + "\" kontrolü yapılacak", "");
        e.shouldBe(visible);
        String p = e.$x("ancestor::div[@class='page']").shouldBe(visible).attr("data-page-number");
        Allure.addAttachment("Hitap \"" + birimHitap + "\" kontrolü " + p + " syafada bulundu", "");
        e.scrollIntoView(true);
        //Selenide.executeJavaScript("arguments[0].scrollIntoView();", e);
        takeScreenshot();


        int k = p.equals("1") ? 2 : 1;
        String kurumHitap = "BÜYÜK HARFLERLE KURUMA";
        Allure.addAttachment("Hitap \"" + kurumHitap + "\" sayfa " + k + " bulunmalı", "");
        e = Selenide.$$("#viewer .page").get(k).$x("//div[.='" + kurumHitap + "']").shouldBe(visible);
        Selenide.executeJavaScript("arguments[0].scrollIntoView();", e);
        takeScreenshot();

        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(0);
        return this;
    }


    @Step("\"{geregi}\" hitap kontolleri")
    private void hitapKontrol(String geregiSecimTipi, String geregi, String beklenenGeregi) {
        evrakOlustur.bilgileriTab().openTab()
                .bilgiTemizle().geregiTemizle()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi);
        hitapKontrolEditor(beklenenGeregi);
        hitapKontrolPdfOnIzleme(beklenenGeregi);
    }

    @Step("Editör tabında hitap konrolü yapılır")
    private void hitapKontrolEditor(String beklenenHitap) {
        evrakOlustur.editorTab().openTab()
                .getHitapAlani().shouldHave(text(beklenenHitap));
    }

    @Step("Pdf Önizlemede hitap konrolü yapılır")
    private void hitapKontrolPdfOnIzleme(String beklenenHitap) {
        evrakOlustur.evrakPageButtons().pdfOnizlemeTikla();
        Selenide.switchTo().window(1);
        Selenide.sleep(6000);
        Selenide.$x("//*[.='" + beklenenHitap + "']").shouldBe(visible);
        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(0);
    }
*/

}
