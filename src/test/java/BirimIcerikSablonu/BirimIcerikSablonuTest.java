package BirimIcerikSablonu;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.TextEditor;
import pages.ustMenuPages.BirimIcerikSablonlarPage;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.$inFrame;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {


    String sablonAdi;
    String editorText;

//    LoginPage loginPage;
    BirimIcerikSablonlarPage birimIcerikSablonlarPage;

    @BeforeMethod
    public void setUp() {
//        login();
//        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
    }

//    @BeforeMethod
//    public void tearDown() throws Exception {
//        WebDriverRunner.closeWebDriver();
//    }

    @Test(description = "Alan aktif durum kontrolleri")
    public void tc1084a() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        SoftAssert sa = new SoftAssert();

        sa.assertTrue(birimIcerikSablonlarPage.getTxtSablonAdi().is(disabled)
                , "Sablon Adı disabled olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getLovKullanilacakBirimler().is(disabled)
                , "Birimler disabled olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getSelEvrakTipi().is(disabled)
                , "Evrak Tipi disabled olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnYeniSablonOlustur().is(enabled)
                , "Yeni Şablon Oluştur enable olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnKaydet().is(disabled)
                , "Kaydet disabled olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnSil().is(disabled)
                , "Sil disabled olmalı");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnEvrakOnizleme().is(disabled)
                , "Evrak Önizleme disabled olmalı");

        //Güncelleme testinde tıklanacağı için gerek kalmıyor.
//        sa.assertEquals(birimIcerikSablonlarPage.getRowsBirimSablonlari().size(),
//                birimIcerikSablonlarPage.getBtnDetayInEachRow().size(),
//                "Birim Şablonlar tablosunda her satırda Detay butonu olması");

        sa.assertAll();
    }

    @Test(description = "Şablon içeriği boş ise oluşturmamalı")
    public void tc1084b() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        String sablonAdi = "SABLON_" + getSysDate();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi);
        birimIcerikSablonlarPage.getLovKullanilacakBirimler()
                .openTree()
                .titleItems().first().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().closeLovTreePanel();
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().dikkatOlmali("Şablon içeriği boş olamaz!");

        Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");
    }

    @Test(description = "Kullanacak Birimler boş ise oluşturmamalı")
    public void tc1084c() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        String sablonAdi = "SABLON_" + getSysDate();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi);
        birimIcerikSablonlarPage.getEditor().type("text in editor");
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");
    }

    @Test(description = "Şablon adı boş ise oluşturmamalı")
    public void tc1084d() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().openTree().titleItems().first().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().closeLovTreePanel();
        birimIcerikSablonlarPage.getEditor().type("text in editor");
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable("")
                , "Birim şablonları tablosunda boş adı ile kayıt bulunmamalı");
    }

    @Test(description = "Şablon adı kayıtlı ise oluşturmamalı")
    public void tc1084e() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        String sablonAdi = birimIcerikSablonlarPage
                .getRowsBirimSablonlariSablonAdi().filterBy(visible).first().text();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi);
        birimIcerikSablonlarPage.getLovKullanilacakBirimler()
                .openTree()
                .titleItems().first().click();
        birimIcerikSablonlarPage.getEditor().type("text in editor");
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji()
                .dikkatOlmali("Daha önce tanımlanmış şablon ismi ile aynı isimli şablon tanımlanamaz!");


        //Database'dan kontrol yapılmalı
        Assert.assertEquals(birimIcerikSablonlarPage.sablonExistCountInTable(sablonAdi), 1
                , "Birim şablonları tablosunda bulunmamalı");
    }

    @Test
    @Description("Yeni şablon oluştur (Alt birimler görmesin)")
    public void tc1082() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        sablonAdi = "SABLON_" + getSysDate();

        String altBirimler = "ALT BİRİMLER GÖRMESİN";

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();

        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi);

        String birim = "optiim birim";
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().type(birim).titleItems()
                .filterBy(exactText(birim))
                .first()
                .click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().closeLovTreePanel()
                .lastSelectedLov()
                .$(By.tagName("select")).selectOption(altBirimler);

        birimIcerikSablonlarPage.getEditor()
                .type("my text").type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim");

        editorText = birimIcerikSablonlarPage.getEditor().getText();
        birimIcerikSablonlarPage.getBtnKaydet().click();

        birimIcerikSablonlarPage.islemMesaji().basariliOlmali();

        Assert.assertTrue(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmalı");
    }

    @Test(dependsOnMethods = {"tc1082"})
    public void tc1082_kontol() throws Exception {
        login("test1", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        comboBox("#yeniGidenEvrakForm\\:icerikSablonDialogD1 label[id$='_label']").selectComboBox(sablonAdi);


        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        String actualText = $("body").text();
        switchTo().defaultContent();

        Assert.assertEquals(editorText, actualText);

        $x("//div[@id='yeniGidenEvrakForm:icerikSablonDialogD1']//button/span[text()='Uygula']/..").click();

        Assert.assertTrue(editor.getText().contains("Optiim Birim"), "Optiim Birim olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");


        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .kaldirilacakKlasorler("ESK05")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Optiim", "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
//                .editorEvrakGeregiSec("Başbakanlık")
                .imzala()
                .popupSImzalaIslemleri()
                .islemMesaji().basariliOlmali();
    }



    /*@Test(dependsOnMethods={"tc1082"})
    public void testName() throws Exception {
    }*/
}
