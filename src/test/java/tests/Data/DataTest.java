package tests.Data;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.tabs.AltTabs;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.OnayKullaniciTipi;
//import pages.solMenuPages.BirimIadeEdilenlerPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "tests.Data" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class DataTest extends BaseTest {

    EvrakOlusturPage page;

    User user = new User("ztekin", "123", "Zübeyde TEKİN");
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    //BirimIadeEdilenlerPage birimIadeEdilenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        //birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2224: DATA-Teslim alınmayı bekleyenler, gelen kutusu ve postalanacaklar listesine gizlilik derecesi yüksek evrak düşürme")
    public void TS2224() throws InterruptedException {

        String konu = "TS2224_" + getSysDate();
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

        login(user);
        page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec(kaldirilacakKlasorler)
                .gizlilikDerecesiSec(GizlilikDerecesi.GIZLI)
                .geregiSecimTipiSec(GeregiSecimTipi.KURUM)
                .geregiSec(geregiKurum)
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(geregiBirim)
                .geregiSecimTipiSec(GeregiSecimTipi.KULLANICI)
                .geregiSec(geregiKullanici)
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla();
        page.editorTab().openTab()
                .getEditor().type(editorIcerik);
        page.pageButtons().evrakImzala().islemMesaji().basariliOlmali();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2234: DATA-İmzala bekleyenler listesine evrak düşürülmesi")
    public void TS2234() throws InterruptedException {

        String konuKodu = "Gelen-Giden Evrak";
        String konu = "TS2234_" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDerecesi = "Hizmete Özel";
        String evrakSayiEkMetni = "A1";
        String ivedilik = "Günlü";
        String miat = getSysDateForKis();
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String akisAdim = "İmzalama";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String ekleriDosyaAciklama = "Açıklama";
        String filePath = "documents/Otomasyon.pdf";

        login(user);
        page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec(konuKodu)
                .evrakTuruSec(evrakTuru)
                .gizlilikDerecesiSec(evrakDerecesi)
                .evrakSayiEkMetniDoldur(evrakSayiEkMetni)
                .ivedilikSec(ivedilik)
                .miatDoldur(miat)
                .geregiSecimTipiSec(geregiSecimBirim)
                .geregiSec(geregiBirim)
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla()
                .kaldiralacakKlasorleriSec(kaldirilacakKlasorler);

        page.editorTab().openTab().getEditor().type(editorIcerik);

        AltTabs altTabs = page.ekleriTab().openTab().altTabs();
        altTabs.getFiziksetEkEkleTab().shouldBe(visible);
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getWebAdresiniEkleTab().shouldBe(visible);
        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(filePath)
                .ekMetniDoldur("Ekleri Tab " + konu)
                .ekleButonaTikla();
        page.ekleriTab().getEkListesiTablosu().findRows(text("Ekleri Tab " + konu)).shouldHaveSize(1);
        page.pageButtons().imzalaButonaTikla().closeEvrakImzalaDialog();
        page.closePage(false);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2322: DATA-Birime iade edilenler listesine evrak düşürme")
    public void TS2322() throws InterruptedException {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2322-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
        String digerBirim = "Birim Deneme";
        String digerDetails = "YGD";

        //Pre-requisites Gelen Evrak Oluşturma
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet()
                .popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);


        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .havaleYap()
                .dagitimBilgileriBirimDoldurWithDetails(digerBirim, digerDetails)
                .teslimAlGonder();


        teslimAlinmayiBekleyenlerPage
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .birimDegistirme(digerBirim);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .btnIadeEt()
                .btnIadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .birimDegistirme(birim);

        //birimIadeEdilenlerPage
          //      .openPage()
            //    .evrakNoIleEvrakSec(konu);
    }

}