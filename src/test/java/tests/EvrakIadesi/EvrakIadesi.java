package tests.EvrakIadesi;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.pageComponents.tabs.AltTabs;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.BirimIadeEdilenlerPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

/****************************************************
 * Tarih: 2018-01-30
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Iadesi" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class EvrakIadesi extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    TextEditor editor;
    User user = new User("mbozdemir", "123");
    User user2 = new User("user2", "123", "Optiim TEST2", "Optiim TEST2", "Optiim Alt Birim1");
    User user3 = new User("user3", "123", "Optiim TEST3", "Optiim TEST3", "Optiim Alt Birim1");
//    GelenEvrakKayitPage gelenEvrakKayitPage;
//    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
//    BirimIadeEdilenlerPage birimIadeEdilenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login("mbozdemir", "123");
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
//        gelenEvrakKayitPage = new GelenEvrakKayitPage();
//        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
//        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2017: Akıştaki Evrağın Değiştirilip İmzala Butonuna Basılması - Güncellemeye Devam Et")
    public void TS2017() throws InterruptedException {

        String konu = "TS2017-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Optiim TEST3";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(user1 , "Paraflama")
//                .onayAkisiKullaniciTipiSec(user1 , "İmzalama")
                .onayAkisiKullaniciEkle(user2)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
//                .onayAkisiKullaniciEkle(user3)
//                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .kullan();

        //paraf buton check add

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik);

//        evrakOlusturPage
//                .parafla();
//
//        evrakOlusturPage
//                .kaydet(true)
//                .islemMesaji().basariliOlmali(basariMesaji);



//        evrakOlusturPage.evrakPageButtons().evrakKaydet().islemMesaji().basariliOlmali();
//        evrakOlusturPage
//                .pageButtons().paraflaButonaTikla();
//
//        evrakOlusturPage.pageButtons().sImzalaRadioSec().evrakImzaOnay().islemMesaji().basariliOlmali();
    }
}
