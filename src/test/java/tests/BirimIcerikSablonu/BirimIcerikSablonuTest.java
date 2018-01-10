package tests.BirimIcerikSablonu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.alanlar.Ivedilik;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.ustMenuPages.BirimIcerikSablonlarPage;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

    /*@AfterMethod
    public void tearDown() throws Exception {
        logout();
    }*/

    String sablonAdi;
    String sablonAdi_1082;
    String sablonAdi_1079;
    String editorText;
    String editorText_1082;
    String editorText_1085;
    String editorText_1079;

    BirimIcerikSablonlarPage birimIcerikSablonlarPage = new BirimIcerikSablonlarPage();
    EvrakOlusturPage evrakOlusturPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;

    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");
    User optiim4 = new User("optiimtest4", "123", "Optiim TEST4", "Optiim Alt Birim1");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1AltBirim1");

    String onizlemeText = "T.C.\nGENEL MÜDÜRLÜK MAKAMI\nBİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI\nYAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ\nOptiim Birim";

    @Test(description = "TS1084: Alan aktif durum kontrolleri", enabled = false, priority = 1)
    public void TS1084a() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().shouldBe(visible);
        SoftAssert sa = new SoftAssert();

        Allure.addAttachment("Sablon Adı disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getTxtSablonAdi().is(disabled), "Sablon Adı disabled olmalı");

        Allure.addAttachment("Birimler disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getLovKullanilacakBirimler().is(disabled), "Birimler disabled olmalı");

        Allure.addAttachment("Evrak Tipi disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getSelEvrakTipi().is(disabled), "Evrak Tipi disabled olmalı");

        Allure.addAttachment("Yeni Şablon Oluştur enable olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnYeniSablonOlustur().is(enabled), "Yeni Şablon Oluştur enable olmalı");

        Allure.addAttachment("Kaydet disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnKaydet().is(disabled), "Kaydet disabled olmalı");

        Allure.addAttachment("Sil disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnSil().is(disabled), "Sil disabled olmalı");

        Allure.addAttachment("Evrak Önizleme disabled olmalı", "");
        sa.assertTrue(birimIcerikSablonlarPage.getBtnEvrakOnizleme().is(disabled), "Evrak Önizleme disabled olmalı");

        //Güncelleme testinde tıklanacağı için gerek kalmıyor.
        /*sa.assertEquals(birimIcerikSablonlarPage.getRowsBirimSablonlari().size(),
                birimIcerikSablonlarPage.getBtnDetayInEachRow().size(),
                "Birim Şablonlar tablosunda her satırda Detay butonu olması");*/
        birimIcerikSablonlarPage.editButtonInAllRows();

        sa.assertAll();
    }

    @Test(description = "TS1084: Şablon içeriği boş ise oluşturmamalı", enabled = false, priority = 2)
    public void TS1084b() {
        login(optiim);
        String sablonAdi = "SABLON_" + getSysDate();
        new BirimIcerikSablonlarPage()
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .kullanilacakBirimiSec(optiim.getBirimAdi())
                .kaydet()
                .islemMesaji().dikkatOlmali("Şablon içeriği boş olamaz!");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Test(description = "TS1084: Kullanacak Birimler boş ise oluşturmamalı", enabled = false, priority = 3)
    public void TS1084c() {
        login(optiim);
        String sablonAdi = "SABLON_" + getSysDate();
        new BirimIcerikSablonlarPage()
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .editoreYaz("text in editor")
                .kaydet()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Test(description = "TS1084: Şablon adı boş ise oluşturmamalı", enabled = false, priority = 4)
    public void TS1084d() {
        login(optiim);
        String sablonAdi = "SABLON_" + getSysDate();
        new BirimIcerikSablonlarPage()
                .openPage()
                .yeniSablonOlustur()
                .kullanilacakBirimiSec(optiim.getBirimAdi())
                .editoreYaz("text in editor")
                .kaydet()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable("")
                , "Birim şablonları tablosunda boş adı ile kayıt bulunmamalı");*/
    }

    @Test(description = "TS1084: Şablon adı kayıtlı ise oluşturmamalı", enabled = false, priority = 5)
    public void TS1084e() {
        login(optiim);
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        String sablonAdi = birimIcerikSablonlarPage.sablonAdiAl(0);

        birimIcerikSablonlarPage
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .kullanilacakBirimiSec(optiim.getBirimAdi())
                .editoreYaz("text in editor")
                .kaydet();
        birimIcerikSablonlarPage.islemMesaji()
                .dikkatOlmali("Daha önce tanımlanmış şablon ismi ile aynı isimli şablon tanımlanamaz!");

        //Database'dan kontrol yapılmalı
        /*Assert.assertEquals(birimIcerikSablonlarPage.sablonExisTSountInTable(sablonAdi), 1
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Test(description = "TS1082: Yeni şablon oluştur (Alt birimler görsün)", enabled = true, priority = 6)
    public void TS1082() {
        login(optiim);
        sablonAdi_1082 = "SABLON_" + getSysDate();
//        String altBirimler = "ALT BİRİMLER GÖRSÜN";
//        String altBirimler = "ALT BİRİMLER GÖRMESİN";
        birimIcerikSablonlarPage
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi_1082)
                .kullanilacakBirimiSec(optiim.getBirimAdi(), exactText(optiim.getBirimAdi()))
                .altBirimlerGorsunMu("ALT BİRİMLER GÖRSÜN");
        editorText_1082 = birimIcerikSablonlarPage.getEditor()
                .type(sablonAdi_1082).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim")
                .getText();

        birimIcerikSablonlarPage
                .pdfOnzileme(text(onizlemeText), text(sablonAdi_1082), text("(@BIRIM)"))
                .kaydet()
                .islemMesaji().basariliOlmali();
    }

    @Test(description = "TS1082: Yeni şablon (Alt birimler görsün) Evrak Oluşturmada kullan"
            , dependsOnMethods = {"TS1082"}, enabled = true
            , priority = 7)
    public void TS1082_kontrol() {
        login(optiim4);

        String konu = "TS1082_" + getSysDate();
        olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class).openPage();
//        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        TextEditor editor = olurYazisiOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTabOntanimliSablonuSec(sablonAdi_1082);
        checkEditorHasText(exactText(editorText_1082));
        editorTabOntanimliSablonUygula();

        Assert.assertFalse(optiim4.getBirimAdi().isEmpty(), "Kullanıcının birim boş olamamalı");
        Assert.assertTrue(editor.getText().contains(optiim4.getBirimAdi()), optiim4.getBirimAdi() + " olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");

        olurYazisiOlustur(konu);
        olurYazisiOlusturPage.ustMenuPageHeader.parafla();

        logout();
        login(ztekin);

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1)
                .first().click();
        new UstMenuPageHeader().imzala();
        imzaBekleyenlerPage.islemMesaji().basariliOlmali();
    }

    @Test(description = "TS1085: Yeni şablon oluştur (Alt birimler görmesin)", priority = 8)
    @Description("Yeni şablon \"Alt birimler görmesin\" olarak oluştur")
    public void TS1085() {
        login(optiim);

        String sablonAdi = "SABLON_" + getSysDate();
//        String altBirimler = "ALT BİRİMLER GÖRMESİN";
//        String altBirimler = "ALT BİRİMLER GÖRSÜN";

        birimIcerikSablonlarPage
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .kullanilacakBirimiSec(optiim.getBirimAdi(), exactText(optiim.getBirimAdi()))
                .altBirimlerGorsunMu("ALT BİRİMLER GÖRMESİN")
                .evrakTipiSec("Giden Evrak");

        editorText_1085 = birimIcerikSablonlarPage.getEditor()
                .type(sablonAdi).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Kullanıcı Soyadı")
                .getText();

        birimIcerikSablonlarPage
                .pdfOnzileme(text(onizlemeText), text(sablonAdi), text("(@KULLANICI_SOYADI)"))
                .kaydet()
                .islemMesaji().basariliOlmali();

        /*Assert.assertTrue(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmalı");*/
        this.sablonAdi = sablonAdi;
    }

    @Test(description = "TS1085: alt biriminde görünmemeli",
            dependsOnMethods = {"TS1085"}, enabled = true, priority = 9)
    @Description("TS1085'da oluşturulan şablon alt biriminde görünmemeli")
    public void TS1085_kontrolAltbirim() {
        login(optiim4);
        evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTabOntanimliSablonuOlmadigi();
    }

    @Test(description = "TS1085: farklı biriminde görünmemeli", dependsOnMethods = {"TS1085"}
            , enabled = true, priority = 10)
    @Description("TS1085'da oluşturulan şablon farklı biriminde görünmemeli")
    public void TS1085_kontrolFarkliBirim() {
        login(user1);
        evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTabOntanimliSablonuOlmadigi();
    }

    @Test(description = "TS1085: yaratılan biriminde seçilebilir olmalı ve !Olur Yazisi Olustur sayfada görünmemeli"
            , dependsOnMethods = {"TS1085"}, enabled = true
            , priority = 11)
    public void TS1085_kontrol_birim() throws InterruptedException {
        login(optiim);
        String konu = "TS1085_" + getSysDate();
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        TextEditor editor = evrakOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);

        editorTabOntanimliSablonuSec(sablonAdi);
        checkEditorHasText(exactText(editorText_1085));
        editorTabOntanimliSablonUygula();

        Assert.assertTrue(editor.getText().contains("TEST"), "Soyadı TEST olmalı");
        Assert.assertFalse(editor.getText().contains("(@KULLANICI_SOYADI)"), "(@KULLANICI_SOYADI) olmamalı");
        evrakOlusturPage.closePage();
        evrakOlusturPage.confirmDialog().button("Hayır").shouldBe(visible).click();

        olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class).openPage();
        editor = olurYazisiOlusturPage.editorTabAc().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTabOntanimliSablonuOlmadigi();
    }

    @Test(enabled = true, description = "TS1079: Şablon güncelleme", dependsOnMethods = {"TS1085"}, priority = 12)
    public void TS1079() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        SelenideElement sablonRow = birimIcerikSablonlarPage.findSablonRowInTable(sablonAdi);

        birimIcerikSablonlarPage.detayButonaTikla(sablonRow);

        sablonAdi_1079 = sablonAdi + "_UPDATED";
        birimIcerikSablonlarPage.sablonAdiDoldur(sablonAdi_1079)
                .evrakTipiSec("Giden Evrak")
                .altBirimlerGorsunMu("ALT BİRİMLER GÖRSÜN");

        /*birimIcerikSablonlarPage.getEditor()
                .type(Keys.END)
                .type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Kullanıcı Adı");*/
        editorText_1079 = birimIcerikSablonlarPage.getEditor().getText();

        birimIcerikSablonlarPage.kaydet().islemMesaji().basariliOlmali();
    }

    @Test(enabled = true, description = "TS1079: Şablon güncellendiğini kontrolü"
            , dependsOnMethods = {"TS1079"}
            , priority = 13)
    public void TS1079_kontrol() {
        login(optiim4);
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        SelenideElement sablonRow = birimIcerikSablonlarPage.findSablonRowInTable(sablonAdi_1079);
        String sablonAdi = sablonAdi_1079 + "2";
        birimIcerikSablonlarPage.detayButonaTikla(sablonRow).sablonAdiDoldur(sablonAdi)
                .kaydet().islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");

        birimIcerikSablonlarPage.getEditor().type("Güncelleme denemesi");
        birimIcerikSablonlarPage
                .kaydet().islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");
    }

    @Test(enabled = true, description = "Şablonu sil", dependsOnMethods = {"TS1079_kontrol"}, priority = 14)
    public void sablonSil() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        birimIcerikSablonlarPage.sablonuSil(sablonAdi_1079);
    }

    /*@Test(description = "Şablonları sil", dependsOnMethods = {"TS1085"}, priority = 13)
    public void sablonSil2() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        birimIcerikSablonlarPage.sablonuSil("DENEME ŞABLON");
    }*/

    @Test(enabled = false)
    public void testName() throws Exception {
        login("user1", "123");

        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();

        birimIcerikSablonlarPage.sablonuSilD("DENEME ŞABLON");
    }


    @Step("Ön tanımlı şablonu seç")
    public BirimIcerikSablonuTest editorTabOntanimliSablonuSec(String sablonAdi) {
        $("div[id*='icerikSablonDialog']").shouldBe(visible);
//        $(By.id("yeniOnayEvrakForm:icerikSablonDialogDOnay")).shouldBe(visible);
//        $(By.id("yeniGidenEvrakForm:icerikSablonDialogD1")).shouldBe(visible);
        BelgenetElement cmbSablon = comboBox("div[id*='icerikSablonDialog'] label[id$='_label']");
//        BelgenetElement cmbSablon = comboBox("[id='yeniGidenEvrakForm:icerikSablonDialogD1'] label[id$='_label']");
        cmbSablon.shouldBe(visible);
//        cmbSablon.getComboBoxValues().shouldHave(sizeGreaterThan(0)).filterBy(text(sablonAdi)).shouldHaveSize(1);
        cmbSablon.selectComboBox(sablonAdi);
        return this;
    }

    @Step("Gelen şablon ekranındaki comboda kaydedilen şablonun gelmediği görülür")
    public BirimIcerikSablonuTest editorTabOntanimliSablonuOlmadigi() {
        BelgenetElement cmbSablon = comboBox("div[id*='icerikSablonDialog'] label[id$='_label']");
        cmbSablon.shouldBe(visible);
        ElementsCollection s = cmbSablon.getComboBoxValues();
        s.filterBy(text(sablonAdi)).shouldHaveSize(0);
        Allure.addAttachment("Şablonlar", (s.size() > 0) ? s.texts().toString() : "");
        return this;
    }

    @Step("Editör teksti kontrol et")
    public BirimIcerikSablonuTest checkEditorHasText(Condition condition) {
        sleep(5000);
//        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        switchTo().frame($("[id$='onizlemeField'] iframe"));
        String actualText = $("body").text();
        $("body").shouldBe(visible).shouldHave(condition);
        switchTo().defaultContent();
        return this;
    }

    @Step("Uygula")
    public BirimIcerikSablonuTest editorTabOntanimliSablonUygula() {
//        $x("//div[@id='yeniGidenEvrakForm:icerikSablonDialogD1']//button/span[text()='Uygula']/..").click();
        $("div[id*='icerikSablonDialog']").$x("//button/span[text()='Uygula']/..").click();
        return this;
    }

    @Step("Evrak oluştur")
    public BirimIcerikSablonuTest olurYazisiOlustur(String konu) {
        olurYazisiOlusturPage.bilgileriTabiAc()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .ivedilikSec(Ivedilik.Normal)
                /*.bilgiSecimTipiSec("Kurum")
                .bilgiSec("Başbakanlık")*/
                .geregiSecimTipiSec("Birim")
                .geregiSec("AFYON VALİLİĞİ")
//                .onayAkisiTemizle()
                .onayAkisiEkleButonaTikla()
                .onayAkisiKullanicilarSec(ztekin.getName())
//                .onayAkisiKullaniciTipiSec("Optiim TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiKullanicininTipiSec(optiim.getName(), "Paraflama")
                .onayAkisiKullanicininTipiSec(ztekin.getName(), "İmzalama")
                .kullanButonaTikla();
//                .onayAkisiTitleKontrol("Yeni akış")
//                .onayAkisiDetailKontrol(user1.getName() + "-Paraflama / " + user2.getName() + "-İmzalama");
        return this;
    }

}
