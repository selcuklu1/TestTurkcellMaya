package tests.EvrakIadesi;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.ImzaBekleyenlerPage;
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
    ImzaBekleyenlerPage imzaBekleyenlerPage;
//    GelenEvrakKayitPage gelenEvrakKayitPage;
//    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
//    BirimIadeEdilenlerPage birimIadeEdilenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
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
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Gelen Evraklar";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";

        evrakOlusturPage
                .openPage()
//                .sayfaKontrol(sayfa1)
                //TODO Evrak Oluştur kontrolü
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu);

        evrakOlusturPage
                .editorTabKontrolInbox();

        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .icerikDegistiIptal();

        evrakOlusturPage
                .editorTabKontrolInbox();

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiDoldurEski(geregiKurum2,"Kurum")
                .gizlilikDerecesiSec("Normal")
                .konuDoldur(konu)
                .gizlilikDerecesiKontrol("Normal")
                .geregiKontrolInbox(geregiKurum2)
                .konuDoldurKontrol(konu)
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiDoldurEski(geregiKurum3,"Kurum")
                .imzalaButonaTikla();


        evrakOlusturPage
                .evrakIcerikDegistiImzalaveDevamEt()
                .evrakSecmeliDegistiKaydet()
                .evrakImzala()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu);

        evrakOlusturPage
                .bilgilerTabiAc()
                .gizlilikDerecesiKontrol("Normal")
                .geregiKontrolInbox(geregiKurum3)
                .konuDoldurKontrol(konu);

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .evrakSecmeliDegistiEvet();

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiDoldurEski(geregiKurum3,"Kurum")
                .imzalaButonaTikla()
                .evrakGuncellendiImzalanamazUyariKontrol(evrakGuncellendiImzalanamazUyari);
    }
}
