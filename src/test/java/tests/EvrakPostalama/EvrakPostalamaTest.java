package tests.EvrakPostalama;

/****************************************************
 * Yazan: Hakan Güner
 * Tarih: 2017-11-23
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Postalama" konulu senaryoları içerir
 ****************************************************/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.User;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.solMenuPages.PostalananlarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PostalananEvrakRaporuPage;

public class EvrakPostalamaTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    ImzaladiklarimPage imzaladiklarimPage;
    PostalananEvrakRaporuPage postalananEvrakRaporuPage;

    User user1 = new User("user1", "123", "User1 TEST");

    @BeforeMethod
    public void loginBeforeTest() {

        evrakOlusturPage = new EvrakOlusturPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        postalananEvrakRaporuPage = new PostalananEvrakRaporuPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0308: Evrak Postalama")
    public void TS0308() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS0308_" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
//                .kaldirilacakKlasorler("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle();

        evrakOlusturPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS0308")
                .editorEvrakGeregiSec("YAZILIM GELİ")
                .imzala()
                .popupSImzalaIslemleri();


        Thread.sleep(4000);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();

        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama("Test")
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat()
                .postalanacakEvrakOrjYaz()
                .gramajDoldur("111111")
                .hesapla()
                .postala();


        Selenide.close();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2076: Evrak Postalama işlemleri")
    public void TS2076() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS2076_" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                //  .kaldirilacakKlasorler("B1K1")
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık", "")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                //    .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage.editorTabAc();
        editorTab.getEditor().type("TS2076");
        editorTab.imzala()
                .popupSImzalaIslemleri();

        Thread.sleep(2000);
        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .first().click();

        imzaladiklarimPage
                .openPage()
                .dokumaniSec(konu)
                .evrakGecmisi();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0520b : Postalanan evrak posta bilgilerinin içerik ekranından güncellenmesi ve rapordan kontrolü")
    public void TS0520b() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "Konu: TS2235";


        postalananlarPage
                .openPage();


        Thread.sleep(2000);
        postalananlarPage.filter().findRowsWith(Condition.text(konu)).first().click();
        Thread.sleep(1000);
        postalananlarPage.postaDetayiTikla();
        postalananlarPage.btnGuncelle();
        Thread.sleep(1000);
        postalananlarPage.btnTarihGuncelle("10.10.2017");
        postalananlarPage.btnPostakoduGuncelle("520");
        postalananlarPage.txtAciklama("TS0520b");
        postalananlarPage = postalananlarPage.btnKaydet();

        String txt = postalananlarPage.evSay();
        postalananEvrakRaporuPage
                .openPage();

        postalananEvrakRaporuPage
                .evrakSayisi(txt)
                .postaAramaBaslangicTarihi("01.12.2017 00:00")
                .postaSorgulama()
                .sonucKarsilastirma();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0520a : Postalanan evrak posta bilgilerinin önizleme ekranından güncellenmesi ve rapor üzerinde kontrolü")
    public void TS0520a() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "Konu: TS2235";

        postalananlarPage
                .openPage();

        //  Thread.sleep(2000);
        // postalananlarPage.filter().findRowsWith(Condition.text(konu)).first().click();
        //Thread.sleep(1000);

        postalananlarPage.btnFiltrenenPostaIcerikGoster(konu);
        Thread.sleep(1000);
        postalananlarPage.icerikDetayPostaDetayi();

        postalananlarPage.btnIcerikPostaDetayTuzelKisiGnc();
        postalananlarPage.btnIcerikPDTuzelKisiTebTarGnc("01.01.2018");
        postalananlarPage.btnIcerikPosDetTuzKisPosKodGnc("520");
        postalananlarPage.btnIcerikPDTuzelKisiPosAcikGnc("TS520a");
        postalananlarPage.btnIcerikPDPopupKaydet();

        String txt = postalananlarPage.icerikEvrakSay();
        postalananEvrakRaporuPage
                .openPage();

        postalananEvrakRaporuPage
                .evrakSayisi(txt)
                .postaAramaBaslangicTarihi("01.12.2017 00:00")
                .postaSorgulama()
                .sonucKarsilastirma();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1685 : Fiziksel eki olan iç yazışmaların postaya düşürülmesi")
    public void TS1685() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS1685_" + getSysDate();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
//                .kaldirilacakKlasorler("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle();

        evrakOlusturPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu)
                .editorEvrakGeregiSec("YAZILIM GELİ")
                .imzala()
                .popupSImzalaIslemleri();


        Thread.sleep(4000);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();

        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("Evrak Servisi Elden")
                .postala();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0312 : postalama işlemi sonrası postalananlar listesinin kontrolü")
    public void TS0312() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS1685_" + getSysDate();

        postalananlarPage.openPage();
        postalananlarPage.btnKurdele()
                .mngImzaDialog()
                .btnImzaciPopupKapat()
                .btnTamEkran()
                .btnTamEkranKapat()
                .btnIcerikGoster();

        postalananlarPage
                .btnIcerikIlgileriTab()
                .btnIcerikEkleriTab()
                .btnIcerikDetayKapat();

        postalananlarPage
                .btnFiltreSpan()
                .btnFiltreBaslangicTarihi(getSysDateForKis())
                .btnFiltrePostaladiklarim();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2235 : Ek ilgi ilişiği olan evrakı postaya düşürme")
    public void TS2235() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS2235_" + getSysDate();
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
//                .kaldirilacakKlasorler("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Gerçek Kişi")
                .geregiDoldur("OptiimTest", "Kullanıcı Adı")
                .geregiKurumPostaTipi("APS")
                .geregiSecimTipiSec("Dağıtım Planları")
                .geregiDoldur("50 BİRİMLİK", "Dağıtım Planları")
                .geregiKurumPostaTipi("İadeli Taahhütlü")
                .geregiSecimTipiSec("Birim")
                .geregiDoldur("ARAŞ", "Birim")
                .geregiSecimTipiSec("Tüzel Kişi")
                .geregiDoldur("Optiim İş", "Ad")
                .geregiKurumPostaTipi("APS")
                .geregiSecimTipiSec("Kullanıcı")
                .geregiDoldur("Optiim TEST", "Kullanıcı")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık", "Kurum")
                .geregiKurumPostaTipi("Evrak Servisi Elden")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle();

        evrakOlusturPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu)
                .imzala()
                .popupSImzalaIslemleri();


        Thread.sleep(4000);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();
        postalanacakEvraklarPage
                .icerikGoster()
                .icerikIlgileriTab()
                .icerikEkleriTab()
                .icerikPencereKapatma();

        postalanacakEvraklarPage
                .filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();
        postalanacakEvraklarPage
                .evrakPostala()
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat()
                .dagitimDetay();

        Selenide.close();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0802 : Postalanan Evrak Raporu")
    public void TS0802() {
        login("Mbozdemir", "123");
//Download control should be added. But working now.
        postalananEvrakRaporuPage
                .openPage()
                .evrakSayisi("6345202")
                .postaAramaBaslangicTarihi("01.12.2017 00:00")
                .postaSorgulama()
                .sonucKarsilastirma();

        postalananEvrakRaporuPage
                .ilkEvrakGecmisi()
                .evrakGecmisiKapat();
        postalananEvrakRaporuPage
                .evrakIcerikGoster()
                .evrakIcerikKapat();
        postalananEvrakRaporuPage
                .etiketBastir();
        postalananEvrakRaporuPage
                .etiketBastirPopupKapat();
        postalananEvrakRaporuPage
                .btnExcel()
                .btnPdf()
                .btnEtiket();
        postalananEvrakRaporuPage
                .popupEtiketBastirma("3", "3");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0309 : Önizleme ekranından ek ilgi ve ilişiği olan evrakın postalanması")
    public void TS0309() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS2235_";
        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).first().click();

        postalanacakEvraklarPage.evrakPostala()
                .tuzelKisiPostaKod("309")
                .tuzelKisiPostaAciklama("TS0309")
                .birimPostaKod("309")
                .birimPostaAciklama("TS0309")
                .dagitimDetay()
                .dagitimDetayKapat()
                .gidisSekli("Adi Posta")
                .ilkPostaPostaKod("309")
                .ilkPostaAciklama("TS0309")
                .gramajDoldur("15")
                .hesapla()
                .etiketYazdir()
                .etiketYazdirPopupKapat()
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat()
                .postala()
                .dialogpostalaEvet();

        postalananlarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu)).first().click();


        postalananlarPage
                .btnFiltrenenPostaIcerikGoster(konu)
                .btnIcerikEkleriTab()
                .btnIcerikIlgileriTab()
                .btnIcerikDetayKapat();

        postalananlarPage
                .btnFiltrenenPostaIcerikGoster(konu)
                .postaDetayiTikla()
                .evrakYazdir()
                .etiketBastir();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1434 : Postalanan Evrak Raporu Alan kontrolleri")
    public void TS1434() throws InterruptedException {
        login("mbozdemir", "123");

        postalananEvrakRaporuPage
                .openPage()
                .btnPostalayanAltBirim()
                .btnPostaSahibiAltbirim()
                .postaSorgulama();

        Thread.sleep(4000);
        postalananEvrakRaporuPage.ekranSorgulananSonucKontrol();

        postalananEvrakRaporuPage.cmbEvrakSahibi("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .postaSorgulama();
        Thread.sleep(1000);
        postalananEvrakRaporuPage.evrakSahibiKontrol("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");

        postalananEvrakRaporuPage.cmbPostalananYerSecimi("Optiim otomasyon")
                .postaSorgulama();

        postalananEvrakRaporuPage.postalananyerKontrol("Optiim otomasyon");
        postalananEvrakRaporuPage.cmbpostaSeklisecimi("Iç giden")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbpostaSeklisecimi("Dış giden")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbPostaTipisec("Adi Posta")
                .postaSorgulama();
        postalananEvrakRaporuPage.txtPostaAciklama("TS")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbPostalayanadi("Zübeyde TEKİN")
                .postaSorgulama();
        postalananEvrakRaporuPage.chkboxPostaladiklarim()
                .postaSorgulama();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0310 : İçerik ekranından evrakın postalanması")
    public void TS0310() throws InterruptedException {
        login("mbozdemir", "123");
        String konu = "TS1685_";
        postalanacakEvraklarPage.openPage()
                .btnFiltrenenPostaIcerikGoster(konu);

        postalanacakEvraklarPage.btnDagitimGidisSekli("APS")
                .inputIcerikPstakod("0310");
        postalanacakEvraklarPage.btnDagitimGidisSekli("KEP");
        postalanacakEvraklarPage
                .btnIcerikPostaYazdir();
        postalanacakEvraklarPage
                .btnPopupPostaYazdirma()
                .btnYazdirPopupKapat();

        postalanacakEvraklarPage
                .btnIcerikEtiketBastir()
                .txtPopupEtiketAciklama()
                .btnEtiketpopupkapat();

        postalanacakEvraklarPage
                .btnIcerikEvrakPostalama();
        // .btnIcerikPostalamaEvet();
    }
}
