package tests.KullaniciIcerikSablonu;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.tabs.EditorTab;
import pages.ustMenuPages.*;

import static data.TestData.passwordZTEKIN;
import static data.TestData.userZtekin;
import static data.TestData.usernameZTEKIN;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Can
 ****************************************************/
public class KullaniciIcerikSablonuTest extends BaseTest
{
    KullaniciIcerikSablonuPage kullaniciIcerikSablonuPage;
    BirimIcerikSablonlarPage birimIcerikSablonlarPage;
    EvrakOlusturPage evrakOlusturPage;
    pages.newPages.EvrakOlusturPage evrakOlusturPageEditor;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;

    @BeforeMethod
    public void loginBeforeTests()
    {
        kullaniciIcerikSablonuPage = new KullaniciIcerikSablonuPage();
        birimIcerikSablonlarPage = new BirimIcerikSablonlarPage();
        evrakOlusturPage = new EvrakOlusturPage();
        evrakOlusturPageEditor = new pages.newPages.EvrakOlusturPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
    }

    @Step("TS0989 Koşmaktadır")
    public void TS0989PreCondition(String sablonAdi){
            login(userZtekin);

            kullaniciIcerikSablonuPage
                    .openPage()
                    .yeniSablonOlustur()
                    .yeniSablonOlustur()
                    .sablonAdiVeEditorAktifHaleGeldigiGorme(true,true)
                    .sablonAdiDoldur(sablonAdi);

            birimIcerikSablonlarPage.getEditor()
                    .type(icerik).type(Keys.ENTER);

            kullaniciIcerikSablonuPage
                    .kaydet()
                    .islemMesaji().basariliOlmali(basariMesaji);

            kullaniciIcerikSablonuPage
                    .kaydedilenSablonListeyeDustuguGorme(sablonAdi)
                    .kaydedilenSablonListesindenSablonDetaySec(sablonAdi);

            String editorIcerik = birimIcerikSablonlarPage.getEditor().getText();

            kullaniciIcerikSablonuPage.detaySecilenEditorKarsilastirma(editorIcerik,icerik);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0985 : Kullanıcı şablonlarının listelenmesi")
    public void TS0985() throws InterruptedException
    {
        String sablonAdi = "TS0989-"+createRandomNumber(10);

        TS0989PreCondition(sablonAdi);

        login(userZtekin);

       kullaniciIcerikSablonuPage
               .openPage()
               .sablonAdiGeldigiGorme()
               .yeniSablonOlusturGeldigiGorme()
               .kaydetGeldigiGorme()
               .silGeldigiGorme()
               .evrakOnizlemeGeldigiGorme();

        kullaniciIcerikSablonuPage
                .sablonAdiGoreDetaySec(sablonAdi);

        String editorIcerik = birimIcerikSablonlarPage.getEditor().getText();

        kullaniciIcerikSablonuPage
                .sablonDetayGeldigiGorme(icerik,editorIcerik)
                .evrakOnizleme()
                .sablonPDFGeldigiGormeDogruBilgiGeldigiGorme(editorIcerik);
    }

    String sablonAdiTS989 = "TS0989-"+createRandomNumber(12);
    String icerik = createRandomText(20);
    String basariMesaji = "İşlem başarılıdır!";
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0989 : Kopyala - yapıştır yapılarak şablon oluşturma işlemi")
    public void TS0989() throws InterruptedException
    {
        login(userZtekin);

       kullaniciIcerikSablonuPage
               .openPage()
               .yeniSablonOlustur()
               .yeniSablonOlustur()
               .sablonAdiVeEditorAktifHaleGeldigiGorme(true,true)
               .sablonAdiDoldur(sablonAdiTS989);

        birimIcerikSablonlarPage.getEditor()
                .type(icerik).type(Keys.ENTER);

        kullaniciIcerikSablonuPage
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciIcerikSablonuPage
                .kaydedilenSablonListeyeDustuguGorme(sablonAdiTS989)
                .kaydedilenSablonListesindenSablonDetaySec(sablonAdiTS989);

        String editorIcerik = birimIcerikSablonlarPage.getEditor().getText();

        kullaniciIcerikSablonuPage.detaySecilenEditorKarsilastirma(editorIcerik,icerik);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,dependsOnMethods =  {"TS0989"}, description = "TS0995 : Yeni kullanıcı şablonu oluşturmaYeni kullanıcı şablonu oluşturma")
    public void TS0995() throws InterruptedException
    {
        useFirefox();

        String sablonAdiTS0997 = "TS0995-"+createRandomNumber(10);
        String icerikTS0995 = createRandomText(15);
        String icerikToplam = icerikTS0995+ icerik;

        login(userZtekin);

        kullaniciIcerikSablonuPage
                .openPage()
                .sablonAdiGeldigiGorme()
                .yeniSablonOlusturGeldigiGorme()
                .kaydetGeldigiGorme()
                .silGeldigiGorme()
                .evrakOnizlemeGeldigiGorme();

        kullaniciIcerikSablonuPage
                .sablonAdiGoreDetaySec(sablonAdiTS989);

        String editorIcerik = birimIcerikSablonlarPage.getEditor().getText();

        kullaniciIcerikSablonuPage
                .sablonBilgilerininGeldigiGorme(icerik,editorIcerik)
                .sablonAdiDoldur(sablonAdiTS0997);

        birimIcerikSablonlarPage.getEditor()
                .type(icerikTS0995);

        kullaniciIcerikSablonuPage
                .evrakOnizleme();

        kullaniciIcerikSablonuPage
                .sablonPDFGeldigiGormeDogruBilgiGeldigiGorme(icerikToplam);

        kullaniciIcerikSablonuPage
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .editorTabAc();

        EditorTab editorTab = evrakOlusturPageEditor.editorTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdiTS0997)
                .onTanimliSablonuUygula();

        evrakOlusturPage
                .evrakOlusturSayfayiKapat();

       // login(usernameZTEKIN, passwordZTEKIN);

        olurYazisiOlusturPage
                .openPage()
                .editorTabAc();
        EditorTab olurYazisiEditorTab = olurYazisiOlusturPage.editorTab();
        olurYazisiEditorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        olurYazisiEditorTab.onTanimliSablonuSec(sablonAdiTS0997)
                .onTanimliSablonuUygula();

        evrakOlusturPage
                .evrakOlusturSayfayiKapat();

        kararYazisiOlusturPage
                .openPage()
                .editorTabAc();

        EditorTab kararYazisiEditorTab = kararYazisiOlusturPage.editorTab();
        kararYazisiEditorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        kararYazisiEditorTab.onTanimliSablonuSec(sablonAdiTS0997)
                .onTanimliSablonuUygula();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0987: Yeni kullanıcı şablonu oluşturma")
    public void TS0987() throws InterruptedException
    {
        useFirefox();

        String sablonAdiTS0987 = "TS0987-"+createRandomNumber(10);

        login(userZtekin);

        kullaniciIcerikSablonuPage
                .openPage()
                .sablonAdiGeldigiGorme()
                .yeniSablonOlusturGeldigiGorme()
                .kaydetGeldigiGorme()
                .silGeldigiGorme()
                .evrakOnizlemeGeldigiGorme();

        kullaniciIcerikSablonuPage
                .yeniSablonOlustur()
                .sablonAdiDoldur(sablonAdiTS0987);

        //Font değiştirme yapmıyoruz

        birimIcerikSablonlarPage.getEditor()
                .type(icerik).type(Keys.ENTER);

        kullaniciIcerikSablonuPage
                .evrakOnizleme()
                .sablonPDFGeldigiGormeDogruBilgiGeldigiGorme(icerik)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .editorTabAc();

        EditorTab editorTab = evrakOlusturPageEditor.editorTab();
        editorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        editorTab.onTanimliSablonuSec(sablonAdiTS0987)
                .onTanimliSablonuUygula();

        evrakOlusturPage
                .evrakOlusturSayfayiKapat();

        olurYazisiOlusturPage
                .openPage()
                .editorTabAc();
        EditorTab olurYazisiEditorTab = olurYazisiOlusturPage.editorTab();
        olurYazisiEditorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        olurYazisiEditorTab.onTanimliSablonuSec(sablonAdiTS0987)
                .onTanimliSablonuUygula();

        evrakOlusturPage
                .evrakOlusturSayfayiKapat();

        kararYazisiOlusturPage
                .openPage()
                .editorTabAc();

        EditorTab kararYazisiEditorTab = kararYazisiOlusturPage.editorTab();
        kararYazisiEditorTab.getEditor().toolbarButton("Öntanımlı İçerik Şablonu Kullan", true);
        kararYazisiEditorTab.onTanimliSablonuSec(sablonAdiTS0987)
                .onTanimliSablonuUygula();

    }

}