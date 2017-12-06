package BirimIcerikSablonu;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ustMenuPages.BirimIcerikSablonlarPage;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


/**
 * Yazan: Ilyas Bayraktar
 * Tarih:
 * Açıklama:
 */
@Feature("Birim İçerik Şablonu")
public class BirimIcerikSablonuTest extends BaseTest {

//    LoginPage loginPage;
    BirimIcerikSablonlarPage birimIcerikSablonlarPage;

    @BeforeMethod
    public void setUp() {
        login();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage().openPage();
    }

    @Test(description = "Alan aktif durum kontrolleri")
    public void tc1084a() {
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
        birimIcerikSablonlarPage.getBtnYeniSablonOlustur().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().openTree().titleItems().first().click();
        birimIcerikSablonlarPage.getLovKullanilacakBirimler().closeLovTreePanel();
        birimIcerikSablonlarPage.getEditor().type("text in editor");
        birimIcerikSablonlarPage.getBtnKaydet().click();
        birimIcerikSablonlarPage.islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz");

        Assert.assertFalse(birimIcerikSablonlarPage.sablonExistInTable("")
                , "Birim şablonları tablosunda boş adı ile kayıt bulunmamalı");
    }

    @Test
    @Description("Yeni şablon oluştur (Alt birimler görmesin)")
    public void tc1082() {
        String sablonAdi = "SABLON_" + getSysDate();

        birimIcerikSablonlarPage
                .yeniSablonOlustur(sablonAdi, "optiim birim", false)
                .islemMesaji().basariliOlmali();

        Assert.assertTrue(birimIcerikSablonlarPage.sablonExistInTable(sablonAdi)
                , "Birim şablonları tablosunda bulunmalı");
    }

}
