package tests.BirimIcerikSablonu;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.EditorTab;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.ustMenuPages.BirimIcerikSablonlarPage;
import pages.ustMenuPages.EvrakOlusturPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

    String sablonAdi1082 = "TS1082_" + getSysDate();
    String editorText1082;

    String sablonAdi1085 = "TS1085_" + getSysDate();
    String editorText1085;

    String sablonAdi1079;

    static String ALT_BIRIMLER_GORSUN = "ALT BİRİMLER GÖRSÜN";
    static String ALT_BIRIMLER_GORMESIN = "ALT BİRİMLER GÖRMESİN";

    String onizlemeText = "T.C.\nGENEL MÜDÜRLÜK MAKAMI\nBİLİŞİM HİZMETLERİ GENEL MÜDÜR YARDIMCISI\nYAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ\n";


    BirimIcerikSablonlarPage birimIcerikSablonlarPage = new BirimIcerikSablonlarPage();
    pages.newPages.EvrakOlusturPage evrakOlusturPage = new pages.newPages.EvrakOlusturPage();
    OlurYazisiOlusturPage olurYazisiOlusturPage = new OlurYazisiOlusturPage();

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1AltBirim1");
    User user3 = new User("user3", "123", "User3 TEST", "AnaBirim1");

    @Test(description = "TS1084: Alan aktif durum kontrolleri", enabled = true, priority = 1)
    public void TS1084() {
        login(user1);
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        TS1084a(birimIcerikSablonlarPage);
        TS1084b(birimIcerikSablonlarPage);
        TS1084c(birimIcerikSablonlarPage);
        TS1084d(birimIcerikSablonlarPage);
        TS1084e(birimIcerikSablonlarPage);
    }

    @Test(description = "TS1082: Yeni şablon oluştur (Alt birimler görsün)", enabled = true, priority = 2)
    public void TS1082() {
        login(user1);
        birimIcerikSablonlarPage
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi1082)
                .kullanilacakBirimiSec(user1.getBirimAdi(), exactText(user1.getBirimAdi()))
                .altBirimlerGorsunMuSec(ALT_BIRIMLER_GORSUN);

        editorText1082 = birimIcerikSablonlarPage.getEditor()
                .type(sablonAdi1082).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim")
                .getText();

        birimIcerikSablonlarPage
                .pdfOnzilemeTextKontol(text(onizlemeText + user1.getBirimAdi()), text(sablonAdi1082), text("(@BIRIM)"))
                .kaydet()
                .islemMesaji().basariliOlmali();

        birimIcerikSablonlarPage.detayButonaTikla(birimIcerikSablonlarPage.sablonuBul(sablonAdi1082));
        birimIcerikSablonlarPage.sablonBilgileriKontrolu(sablonAdi1082, user1.getBirimAdi(), ALT_BIRIMLER_GORSUN, editorText1082);
    }

    @Test(description = "TS1082: Şablonu evrak oluşturmada kullan", dependsOnMethods = {"TS1082"}, enabled = true, priority = 3)
    public void TS1082_kontrol() {
        TS1082_AltBirimKullanici();
        TS1082_BirimKullanici();
        TS1082_BirimKullaniciOlurYazisiOlustur();
    }

    @Test(description = "TS1089: Şablonun Güncellenmesi", dependsOnMethods = {"TS1082"}, enabled = true, priority = 4)
    public void TS1089() throws Exception {
        login(user1);
        birimIcerikSablonlarPage.openPage()
                .detayButonaTikla(birimIcerikSablonlarPage.sablonuBul(sablonAdi1082))
                .sablonBilgileriKontrolu(sablonAdi1082, user1.getBirimAdi(), ALT_BIRIMLER_GORSUN, editorText1082)
                //.altBirimlerGorsunMuDegerKontrol(ALT_BIRIMLER_GORSUN)
                //.getEditor().editorShouldHave(text(editorText1082));
                //birimIcerikSablonlarPage
                .altBirimlerGorsunMuSec("ALT BİRİMLER GÖRMESİN")
                .evrakTipiSec("Giden Evrak");

        editorText1082 = birimIcerikSablonlarPage.getEditor().click().type(Keys.chord(Keys.COMMAND, Keys.DOWN), Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Kullanıcı Soyadı")
                .getText();
        birimIcerikSablonlarPage.getEditor().editorShouldHave(text("(@KULLANICI_SOYADI)"));
        birimIcerikSablonlarPage
                .pdfOnzilemeTextKontol(text(editorText1082))
                .kaydet()
                .islemMesaji().basariliOlmali();

        logout();
        login(user1);
        pages.newPages.EvrakOlusturPage evrakOlusturPage = new pages.newPages.EvrakOlusturPage().openPage();
        EditorTab editorTab = evrakOlusturPage.editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdi1082)
                .onTanimliSablonuOnizlemeKontrol(exactText(editorText1082))
                .onTanimliSablonuUygula()
                .getEditor().editorShouldHave(text(user1.getSurname()));
        //editor.editorShouldHave(not(text(("@KULLANICI_SOYADI"))));
        /*Assert.assertTrue(editor.getText().contains("TEST"), "Kullanıcı soyadı \"TEST\" olmalı");
        Assert.assertFalse(editor.getText().contains("(@KULLANICI_SOYADI)"), "(@KULLANICI_SOYADI) olmamalı");*/
        evrakOlusturPage.closePage(false);

        /*logout();
        login(user1);*/
        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        editorTab = olurYazisiOlusturPage.editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuOlmadigi(sablonAdi1082);

    }


    @Test(description = "TS1085: Yeni şablon oluştur (Alt birimler görmesin)", priority = 5)
    @Description("TS1085: Yeni şablon \"Alt birimler görmesin\" olarak oluştur")
    public void TS1085() {

        login(user1);
        birimIcerikSablonlarPage
                .openPage()
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi1085)
                .kullanilacakBirimiSec(user1.getBirimAdi(), exactText(user1.getBirimAdi()))
                .altBirimlerGorsunMuSec(ALT_BIRIMLER_GORMESIN)
                .evrakTipiSec("Giden Evrak");

        editorText1085 = birimIcerikSablonlarPage.getEditor()
                .type(sablonAdi1085).type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Birim")
                .getText();

        birimIcerikSablonlarPage
                .pdfOnzilemeTextKontol(text(onizlemeText + user1.getBirimAdi()), text(sablonAdi1085), text("(@BIRIM)"))
                .kaydet()
                .islemMesaji().basariliOlmali();

        birimIcerikSablonlarPage.detayButonaTikla(birimIcerikSablonlarPage.sablonuBul(sablonAdi1085));
        birimIcerikSablonlarPage.sablonBilgileriKontrolu(sablonAdi1085, user1.getBirimAdi(), ALT_BIRIMLER_GORMESIN, editorText1085);
    }

    @Test(description = "TS1085: Alt Biriminde görünmemeli", dependsOnMethods = {"TS1085"}, enabled = true, priority = 6)
    @Description("TS1085'da oluşturulan şablon alt biriminde görünmemeli")
    public void TS1085_kontrolAltbirim() {
        login(user2);
        evrakOlusturPage = new pages.newPages.EvrakOlusturPage().openPage();
        EditorTab editorTab = evrakOlusturPage.editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuOlmadigi(sablonAdi1085);
    }

    @Test(description = "TS1085: yaratılan biriminde seçilebilir olmalı ve Olur Yazisi Olustur sayfada görünmemeli", dependsOnMethods = {"TS1085"}, enabled = true, priority = 7)
    public void TS1085_kontrol_birim() {
        login(user1);
        String konu = "TS1085_" + getSysDate();
        EditorTab editorTab = evrakOlusturPage.openPage().editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdi1085)
                .onTanimliSablonuOnizlemeKontrol(exactText(editorText1085))
                .onTanimliSablonuUygula()
                .getEditor().editorShouldHave(text(user1.getBirimAdi()));
        /*Assert.assertTrue(editor.getText().contains("TEST"), "Soyadı TEST olmalı");
        Assert.assertFalse(editor.getText().contains("(@KULLANICI_SOYADI)"), "(@KULLANICI_SOYADI) olmamalı");*/
        evrakOlusturPage.closePage(false);

        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        editorTab = olurYazisiOlusturPage.editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuOlmadigi(sablonAdi1085);
    }

    @Test(description = "TS1085: farklı biriminde görünmemeli", dependsOnMethods = {"TS1085"}, enabled = false, priority = 8)
    public void TS1085_kontrolFarkliBirim() {
        login(user1);
        evrakOlusturPage = new pages.newPages.EvrakOlusturPage().openPage();
        EditorTab editorTab = evrakOlusturPage.editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuOlmadigi(sablonAdi1085);
    }

    @Test(enabled = true, description = "TS1079: Şablon güncelleme kontrolleri", dependsOnMethods = {"TS1085"}, priority = 9)
    public void TS1079() {
        login(user1);
        birimIcerikSablonlarPage.openPage();
        String sablon = birimIcerikSablonlarPage.sablonAdiAl(0);

        birimIcerikSablonlarPage.detayButonaTikla(birimIcerikSablonlarPage.sablonuBul(sablonAdi1085))
                .sablonBilgileriKontrolu(sablonAdi1085, user1.getBirimAdi(), ALT_BIRIMLER_GORMESIN, editorText1085)
                .sablonAdiDoldur(sablon)
                .kaydet().islemMesaji().dikkatOlmali("Daha önce tanımlanmış şablon ismi ile aynı isimli şablon tanımlanamaz!");

        birimIcerikSablonlarPage
                .sablonAdiDoldur(sablonAdi1085)
                .altBirimlerGorsunMuSec(ALT_BIRIMLER_GORSUN)
                .kaydet().islemMesaji().basariliOlmali();

        login(user2);
        birimIcerikSablonlarPage.openPage()
                .detayButonaTikla(birimIcerikSablonlarPage.sablonuBul(sablonAdi1085))
                .sablonAdiDoldur(sablonAdi1085 + "_2")
                .kaydet()
                .islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");

        birimIcerikSablonlarPage.getEditor().type("Güncelleme denemesi");
        birimIcerikSablonlarPage.kaydet()
                .islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");

        /*birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        SelenideElement sablonRow = birimIcerikSablonlarPage.findSablonRowInTable(sablonAdi);

        birimIcerikSablonlarPage.detayButonaTikla(sablonRow);*/

        /*sablonAdi1079 = sablonAdi1082 + "_UPDATED";
        birimIcerikSablonlarPage.sablonAdiDoldur(sablonAdi1079)
                .evrakTipiSec("Giden Evrak")
                .altBirimlerGorsunMuSec("ALT BİRİMLER GÖRSÜN");*/

        /*birimIcerikSablonlarPage.getEditor()
                .type(Keys.END)
                .type(Keys.ENTER)
                .toolbarCombo("Etiket Ekle", "Kullanıcı Adı");*/
        //editorText1079 = birimIcerikSablonlarPage.getEditor().getText();

        //birimIcerikSablonlarPage.kaydet().islemMesaji().basariliOlmali();
    }


    @Test(enabled = false, description = "TS1079: Şablon güncellendiğini kontrolü", dependsOnMethods = {"TS1079"}, priority = 13)
    public void TS1079_kontrol() {
        login(user2);
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        SelenideElement sablonRow = birimIcerikSablonlarPage.findSablonRowInTable(sablonAdi1079);
        String sablonAdi = sablonAdi1079 + "2";
        birimIcerikSablonlarPage.detayButonaTikla(sablonRow).sablonAdiDoldur(sablonAdi)
                .kaydet().islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");

        birimIcerikSablonlarPage.getEditor().type("Güncelleme denemesi");
        birimIcerikSablonlarPage
                .kaydet().islemMesaji().dikkatOlmali("Üst birim şablonuna işlem yapılamaz!");
    }

    @Test(enabled = true, description = "Şablonu sil", dependsOnMethods = {"TS1079"}, priority = 14)
    public void sablonSil() {
        login(user1);
        new BirimIcerikSablonlarPage().openPage()
                .sablonuSil(sablonAdi1085);
    }

    @Test(enabled = false)
    public void tumSablonalariSil() throws Exception {
        login("user1", "123");
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
        birimIcerikSablonlarPage.sablonuSilD("DENEME ŞABLON");
    }

    ///////////////////////////////////////////////////////////////////////////
    // Steps
    ///////////////////////////////////////////////////////////////////////////

    @Step("Alan aktif durum kontrolleri")
    public void TS1084a(BirimIcerikSablonlarPage page) {
        page.getBtnYeniSablonOlustur().shouldBe(visible);
        SoftAssert sa = new SoftAssert();

        Allure.addAttachment("Sablon Adı disabled olmalı", "");
        sa.assertTrue(page.getTxtSablonAdi().is(disabled), "Sablon Adı disabled olmalı");

        Allure.addAttachment("Birimler disabled olmalı", "");
        sa.assertTrue(page.getLovKullanilacakBirimler().is(disabled), "Birimler disabled olmalı");

        Allure.addAttachment("Evrak Tipi disabled olmalı", "");
        sa.assertTrue(page.getSelEvrakTipi().is(disabled), "Evrak Tipi disabled olmalı");

        Allure.addAttachment("Yeni Şablon Oluştur enable olmalı", "");
        sa.assertTrue(page.getBtnYeniSablonOlustur().is(enabled), "Yeni Şablon Oluştur enable olmalı");

        Allure.addAttachment("Kaydet disabled olmalı", "");
        sa.assertTrue(page.getBtnKaydet().is(disabled), "Kaydet disabled olmalı");

        Allure.addAttachment("Sil disabled olmalı", "");
        sa.assertTrue(page.getBtnSil().is(disabled), "Sil disabled olmalı");

        Allure.addAttachment("Evrak Önizleme disabled olmalı", "");
        sa.assertTrue(page.getBtnEvrakOnizleme().is(disabled), "Evrak Önizleme disabled olmalı");

        //Güncelleme testinde tıklanacağı için gerek kalmıyor.
        /*sa.assertEquals(birimIcerikSablonlarPage.getRowsBirimSablonlari().size(),
                birimIcerikSablonlarPage.getBtnDetayInEachRow().size(),
                "Birim Şablonlar tablosunda her satırda Detay butonu olması");*/
        page.editButtonInAllRows();

        sa.assertAll();
    }

    @Step("Şablon içeriği boş ise oluşturmamalı")
    public void TS1084b(BirimIcerikSablonlarPage page) {
        String sablonAdi = "SABLON_" + getSysDate();
        page
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .kullanilacakBirimiSec(user1.getBirimAdi())
                .kaydet()
                .islemMesaji().dikkatOlmali("Şablon içeriği boş olamaz!");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Step("Kullanacak Birimler boş ise oluşturmamalı")
    public void TS1084c(BirimIcerikSablonlarPage page) {
        String sablonAdi = "SABLON_" + getSysDate();
        page
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .editoreYaz("text in editor")
                .kaydet()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Step("Şablon adı boş ise oluşturmamalı")
    public void TS1084d(BirimIcerikSablonlarPage page) {
        page
                .yeniSablonOlustur()
                .kullanilacakBirimiSec(user1.getBirimAdi())
                .editoreYaz("text in editor")
                .kaydet()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        /*Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable("")
                , "Birim şablonları tablosunda boş adı ile kayıt bulunmamalı");*/
    }

    @Step("Şablon adı kayıtlı ise oluşturmamalı")
    public void TS1084e(BirimIcerikSablonlarPage page) {

        String sablonAdi = page.sablonAdiAl(0);

        page
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdi)
                .kullanilacakBirimiSec(user1.getBirimAdi())
                .editoreYaz("text in editor")
                .kaydet();
        page.islemMesaji()
                .dikkatOlmali("Daha önce tanımlanmış şablon ismi ile aynı isimli şablon tanımlanamaz!");

        //Database'dan kontrol yapılmalı
        /*Assert.assertEquals(birimIcerikSablonlarPage.sablonExisTSountInTable(sablonAdi), 1
                , "Birim şablonları tablosunda bulunmamalı");*/
    }

    @Step("Alt biriminden bir kullanıcıda şablonun geldiği kontrolü")
    public void TS1082_AltBirimKullanici() {
        login(user2);
        EditorTab editorTab = evrakOlusturPage.openPage().editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdi1082)
                .onTanimliSablonuOnizlemeKontrol(exactText(editorText1082))
                .onTanimliSablonuUygula();

        editorTab.getEditor().editorShouldHave(text(user2.getBirimAdi()));
        /*Assert.assertTrue(editor.getText().contains(user2.getBirimAdi()), "Editörde " + user2.getBirimAdi() + " olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");*/
    }

    @Step("Birimden bir kullanıcıda şablonun geldiği kontrolü")
    public void TS1082_BirimKullanici() {
        login(user1);
        EditorTab editorTab = evrakOlusturPage.openPage().editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdi1082)
                .onTanimliSablonuOnizlemeKontrol(exactText(editorText1082))
                .onTanimliSablonuUygula();

        editorTab.getEditor().editorShouldHave(text(user1.getBirimAdi()));
        evrakOlusturPage.closePage(false);
        /*Assert.assertTrue(editor.getText().contains(user2.getBirimAdi()), "Editörde " + user2.getBirimAdi() + " olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");*/
    }

    @Step("Birimden bir kullanıcıda şablonun geldiği kontrolü")
    public void TS1082_BirimKullaniciOlurYazisiOlustur() {
        EditorTab editorTab = olurYazisiOlusturPage.openPage().editorTab().openTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdi1082)
                .onTanimliSablonuOnizlemeKontrol(exactText(editorText1082))
                .onTanimliSablonuUygula();

        editorTab.getEditor().editorShouldHave(text(user1.getBirimAdi()));

        Map staff = new HashMap<String, OnayKullaniciTipi>();
        //staff.put(user1.getFullname(), OnayKullaniciTipi.PARAFLAMA);
        staff.put(user3.getFullname(), OnayKullaniciTipi.IMZALAMA);
        olurYazisiOlusturPage.bilgileriTab().openTab().alanlariDoldur(
                "310.04"
                , sablonAdi1082
                , "Diğer"
                , Ivedilik.Normal
                , GeregiSecimTipi.BIRIM
                , user1.getBirimAdi()
//                , "optiimtekin"
                , new String[][]{{user3.getFullname(), OnayKullaniciTipi.IMZALAMA.getOptionText()},}
        );
        olurYazisiOlusturPage.pageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();
        /*evrakOlustur(evrakOlusturPage, konu);
        new EvrakPageButtons().evrakParafla();
        new IslemMesajlari().basariliOlmali();*/
    }



    @Step("Evrak oluştur")
    public BirimIcerikSablonuTest olurYazisiOlustur(String konu) {
        olurYazisiOlusturPage.bilgileriTab().openTab()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .ivedilikSec(Ivedilik.Normal)
                /*.bilgiSecimTipiSec("Kurum")
                .bilgiSec("Başbakanlık")*/
                .geregiSecimTipiSec("Birim")
                .geregiSec("AFYON VALİLİĞİ")
                .onayAkisiEkleButonaTikla()
                .kullanicilariSec(user3.getFullname())
//                .onayAkisiKullaniciTipiSec("user1 TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiKullanicininTipiSec(user1.getFullname(), "Paraflama")
                .onayAkisiKullanicininTipiSec(user3.getFullname(), "İmzalama")
                .kullanButonaTikla();
//                .onayAkisiTitleKontrol("Yeni akış")
//                .onayAkisiDetailKontrol(user1.getName() + "-Paraflama / " + user2.getName() + "-İmzalama");
        return this;
    }

    @Step("Evrak oluştur")
    public BirimIcerikSablonuTest evrakOlustur(EvrakOlusturPage page, String konu) {
        page.bilgilerTabiAc()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
                .ivedilikSec("Normal")
                /*.bilgiSecimTipiSec("Kurum")
                .bilgiSec("Başbakanlık")*/
                .geregiSecimTipiSec("Birim")
                .geregiSec("AFYON VALİLİĞİ")
//                .onayAkisiTemizle()
                .onayAkisiEkle()
                .onayAkisiEkle(user3.getFullname())
//                .onayAkisiKullaniciTipiSec("user1 TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiKullaniciTipiSec(user1.getFullname(), "Paraflama")
                .onayAkisiKullaniciTipiSec(user3.getFullname(), "İmzalama")
                .kullan();
//                .onayAkisiTitleKontrol("Yeni akış")
//                .onayAkisiDetailKontrol(user1.getName() + "-Paraflama / " + user2.getName() + "-İmzalama");
        return this;
    }
    @Test(description = "TS1082: Yeni şablon (Alt birimler görsün) Evrak Oluşturmada kullan"
            , dependsOnMethods = {"TS1082"}, enabled = false
            , priority = 7)
    public void TS1082_kontrolOld() {
        /*login(user2);

        String konu = "TS1082_" + getSysDate();
        olurYazisiOlusturPage = page(OlurYazisiOlusturPage.class).openPage();
//        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();
        TextEditor editor = olurYazisiOlusturPage.editorTab().openTab().getEditor();
        editor.toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTabOntanimliSablonuSec(sablonAdi1082);
        checkEditorHasText(exactText(editorText1082));
        editorTabOntanimliSablonUygula();

        Assert.assertFalse(user2.getBirimAdi().isEmpty(), "Kullanıcının birim boş olamamalı");
        Assert.assertTrue(editor.getText().contains(user2.getBirimAdi()), user2.getBirimAdi() + " olmalı");
        Assert.assertFalse(editor.getText().contains("(@BIRIM)"), "(@BIRIM) olmamalı");

        olurYazisiOlustur(konu);
        olurYazisiOlusturPage.pageButtons().parafla();

        logout();
        login(user3);

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1)
                .first().click();
        new EvrakPageButtons().evrakImzala();
        imzaBekleyenlerPage.islemMesaji().basariliOlmali();*/
    }
}
