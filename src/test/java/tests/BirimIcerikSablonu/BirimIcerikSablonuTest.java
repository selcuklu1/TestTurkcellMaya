package tests.BirimIcerikSablonu;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.ustMenuPages.BirimIcerikSablonlarPage;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

    String sablonAdi;
    String sablonAdi_1082;
    String sablonAdi_1079;
    String editorText;
    String editorText_1079;

    BirimIcerikSablonlarPage birimIcerikSablonlarPage;

    @Test(description = "Alan aktif durum kontrolleri", enabled = true, priority = 1)
    public void tc1084a() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().shouldBe(visible);
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

    @Test(description = "Şablon içeriği boş ise oluşturmamalı", enabled = true, priority = 2)
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

    @Test(description = "Kullanacak Birimler boş ise oluşturmamalı", enabled = true, priority = 3)
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

    @Test(description = "Şablon adı boş ise oluşturmamalı", enabled = true, priority = 4)
    public void tc1084d() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().shouldBe(enabled);
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().openTree().titleItems().first().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().closeLovTreePanel();
        birimIcerikSablonlarPage.getEditor().type("text in editor");
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable("")
                , "Birim şablonları tablosunda boş adı ile kayıt bulunmamalı");
    }

    @Test(description = "Şablon adı kayıtlı ise oluşturmamalı", enabled = true, priority = 5)
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

    @Test(description = "Yeni şablon oluştur (Alt birimler görsün)", enabled = true, priority = 6)
    public void tc1082() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

//        String sablonAdi = "SABLON_" + getSysDate();
        sablonAdi_1082 = "SABLON_" + getSysDate();
//        String altBirimler = "ALT BİRİMLER GÖRMESİN";
        String altBirimler = "ALT BİRİMLER GÖRSÜN";

        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();

        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi_1082);

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

        Assert.assertTrue(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi_1082)
                , "Birim şablonları tablosunda bulunmalı");
//        this.sablonAdi = sablonAdi;
    }

    @Test(description = "Yeni şablon (Alt birimler görsün) Evrak Oluşturmada kullan", dependsOnMethods = {"tc1082"}, enabled = true
            , priority = 7)
    public void tc1082_kontrol() {
        login("optiimtest4", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        BelgenetElement cmbSablon = comboBox("[id='yeniGidenEvrakForm:icerikSablonDialogD1'] label[id$='_label']");
        cmbSablon.shouldBe(visible);
        cmbSablon.getComboBoxValues().filterBy(text(sablonAdi_1082)).shouldHaveSize(1);
        cmbSablon.selectComboBox(sablonAdi_1082);

        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        String actualText = $("body").text();
        switchTo().defaultContent();

        Assert.assertEquals(editorText, actualText);

        $x("//div[@id='yeniGidenEvrakForm:icerikSablonDialogD1']//button/span[text()='Uygula']/..").click();

        Assert.assertTrue(editor.getText().contains("Optiim Alt Birim1"), "Optiim Alt Birim1 olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");

    }

    @Test(description = "Yeni şablon oluştur (Alt birimler görmesin)", priority = 8)
    public void tc1085() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        String sablonAdi = "SABLON_" + getSysDate();
        String altBirimler = "ALT BİRİMLER GÖRMESİN";
//        String altBirimler = "ALT BİRİMLER GÖRSÜN";

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
        this.sablonAdi = sablonAdi;
    }

    @Test(description = "Yeni şablon (Alt birimler görmesin) biriminde görünmeli", dependsOnMethods = {"tc1085"}, enabled = true
            , priority = 9)
    public void tc1085_kontrol_birim() {
        login("optiim", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        BelgenetElement cmbSablon = comboBox("#yeniGidenEvrakForm\\:icerikSablonDialogD1 label[id$='_label']");
        cmbSablon.getComboBoxValues().filterBy(text(sablonAdi)).shouldHaveSize(1);
        cmbSablon.selectComboBox(sablonAdi);


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
                .geregiSecimTipiSecByText("Kurum")
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

    @Test(description = "Yeni şablon (Alt birimler görmesin) alt biriminde görünmemeli", dependsOnMethods = {"tc1085"}
            , enabled = true, priority = 10)
    public void tc1085_kontrol_altbirim() {
        login("optiimtest4", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        BelgenetElement cmbSablon = comboBox("#yeniGidenEvrakForm\\:icerikSablonDialogD1 label[id$='_label']");
        cmbSablon.getComboBoxValues().filterBy(exactText(sablonAdi)).shouldHaveSize(0);

    }

    @Test(description = "Şablon güncelleme", dependsOnMethods = {"tc1085"}, priority = 11)
    public void tc1079() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        SelenideElement sablonRow = birimIcerikSablonlarPage.findSablonRowInTable(sablonAdi);

        try {
            sablonRow.$("[id$='sablonListesiDetayButton_id']").sendKeys("\n");
        } catch (Exception e) {
        }
        sablonRow.$("[id$='sablonListesiDetayButton_id']").click();

        sablonAdi_1079 = sablonAdi + "_UPDATED";
        birimIcerikSablonlarPage.getTxtSablonAdi().setValue(sablonAdi_1079);

        birimIcerikSablonlarPage.getSelEvrakTipi().selectOption("Giden Evrak");

        String altBirimler = "ALT BİRİMLER GÖRSÜN";
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().lastSelectedLov()
                .$(By.tagName("select")).selectOption(altBirimler);


        birimIcerikSablonlarPage.getEditor()
                .type(Keys.END)
                .type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Kullanıcı Adı");

        editorText_1079 = birimIcerikSablonlarPage.getEditor().getText();

        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().basariliOlmali();
    }

    @Test(description = "Şablon güncellendiğini kontrolü", dependsOnMethods = {"tc1079"}, priority = 12)
    public void tc1079_kontrol() {
        login("optiimtest4", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        BelgenetElement cmbSablon = comboBox("#yeniGidenEvrakForm\\:icerikSablonDialogD1 label[id$='_label']");

        cmbSablon.getComboBoxValues().filterBy(text(sablonAdi_1079)).shouldHaveSize(1);
        cmbSablon.selectComboBox(sablonAdi_1079);

        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        String actualText = $("body").text();
        switchTo().defaultContent();

        Assert.assertEquals(editorText_1079.trim(), actualText.trim());

        $x("//div[@id='yeniGidenEvrakForm:icerikSablonDialogD1']//button/span[text()='Uygula']/..").click();

        Assert.assertTrue(editor.getText().contains("Optiim Alt Birim1"), "Optiim Alt Birim1 olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");

        Assert.assertTrue(editor.getText().contains("Optiim"), "Optiim kullanıcı adı olmalı");
        Assert.assertFalse(editor.getText().contains("(@KULLANICI_ADI)"), "(@KULLANICI_ADI) olmamalı");

    }

    @Test(enabled = false)
    public void testName() throws Exception {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        birimIcerikSablonlarPage.sablonuSil("DENEME ŞABLON");
    }
}
