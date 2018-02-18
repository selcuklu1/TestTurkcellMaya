package tests.Data;

import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.EvrakPageButtons;
import pages.pageComponents.tabs.AltTabs;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.*;

//import pages.solMenuPages.BirimIadeEdilenlerPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "tests.Data" konulu senaryoları içerir
 * Yazan: Samed Solak
 ****************************************************/

public class DataTest extends BaseTest {

    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1");
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2224: DATA-Teslim alınmayı bekleyenler")
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

        login(ztekin);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
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
                .secilenAnlikOnayAkisKullanicilariKontrolEt(ztekin, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullanicininTipiSec(ztekin, OnayKullaniciTipi.IMZALAMA)
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

        login(ztekin);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
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
                .secilenAnlikOnayAkisKullanicilariKontrolEt(ztekin, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullanicininTipiSec(ztekin, OnayKullaniciTipi.IMZALAMA)
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
        String testid= "TS-2322";
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
        String details = "BHUPGMY";
        String digerBirim = "Birim Deneme";
        String digerDetails = "YGD";

        testStatus(testid,"PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

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

        testStatus(testid,"Test Başladı");
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakOnizlemeKontrol()
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
                .iadeEt()
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .birimDegistirme(birim);


        birimIadeEdilenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu);
    }

    @Test(description = "TS2330: DATA-Kullanıcıya ve Birime evrak havalesi", enabled = true)
    public void TS2330() throws Exception {
        String evrakSayi = getSysDate();
        String konu = "TS2330_" + getSysDate();
        login(ztekin);
        GelenEvrakKayitPage page = new GelenEvrakKayitPage().openPage();
        page.ustYaziEkle("documents/pdf.pdf").islemMesaji().basariliOlmali();
        page.ustYaziPdfAdiKontrol("pdf.pdf")
                .konuKoduDoldur("010.01")
                .konuDoldur(konu)
                .evrakTuruSec("Resmi Yazışma")
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec("Normal")
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2("Başbakanlık")
                .evrakSayiSagDoldur(evrakSayi)
                .evrakGelisTipiSec("Posta")
                .ivedilikSec("Normal")
                .havaleIslemleriBirimDoldur(user1.getBirimAdi())
                .havaleIslemleriKisiDoldur(user1.getFullname())
                .kaydet();

        String evrakNo = page.popUps();
        String kayitTarihiSayi = getSysDateForKis() + " / " + evrakNo;
        page.islemMesaji().basariliOlmali();

        login(user1);
        new GelenEvraklarPage().openPage().searchTable().findRows(text(konu)).should(exist);

    }

    @Test(description = "TS2326: DATA-Postalanacak evraklar listesine evrak düşürme", enabled = true)
    public void TS2326() {
        String konu = "TS2326" + getSysDate();
        login(user1);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .evrakTuruSec("Resmi Yazışma")
                .gizlilikDerecesiSec(GizlilikDerecesi.HIZMETE_OZEL)
                .ivedilikSec(Ivedilik.GUNLU)
                .miatDoldur(getSysDateForKis())
                .geregiSecimTipiSec(GeregiSecimTipi.KURUM)
                .geregiSec("Başbakanlık")
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user1, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(user5, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla()
                .kaldiralacakKlasorleriSec("Diğer");
        page.editorTab().openTab()
                .getEditor().type("Editör tekst");
        page.pageButtons().parafla().islemMesaji().basariliOlmali();

        login(user5);
        new ImzaBekleyenlerPage().openPage().searchTable().findRows(text(konu)).getFoundRow().click();
        new EvrakPageButtons().evrakImzala().islemMesaji().basariliOlmali();
        new PostalanacakEvraklarPage().openPage().searchTable().findRows(text(konu)).getFoundRow().should(exist);

    }


}