package tests.EvrakPostalama;

/****************************************************
 * Yazan: Hakan Güner
 * Tarih: 2017-11-23
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Postalama" konulu senaryoları içerir
 ****************************************************/

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.TestData;
import data.User;
import galen.GalenControl;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.newPages.EvrakDetayiPage;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.tabs.AltTabs;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.PostalanacakEvraklarPage;
import pages.solMenuPages.PostalananlarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.PostalananEvrakRaporuPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageData.alanlar.GeregiSecimTipi.*;

public class EvrakPostalamaTest extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    ImzaladiklarimPage imzaladiklarimPage;
    PostalananEvrakRaporuPage postalananEvrakRaporuPage;
    pages.newPages.EvrakOlusturPage evrakOlusturPage2;
    AltTabs altTabs;
    Map dagitimPlanElemanlari;
    EvrakOnizleme.EvrakPostala evrakPostala;


    //User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user1 = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ/YGD");
    String konu = "TS2235_" + getSysDate();
    String konu1685;
    String konuKodu1 = "010.10";
    String konuKoduSayi = "6345202-010.10-";
    //String konuKoduSayi = "01-010.10-";
    String metni = "Metni Tab " + konu;
    String ekleri = "Ekleri Tab " + konu;
    String doc = "documents/Otomasyon.pdf";
    String docText = "Test Otomasyon deneme pdf";

    String[] title = new String[5];
    String[] gonderimSekli = new String[5];
    String konu309 = "";

    @BeforeMethod
    public void beforeTestStart() {
        evrakOlusturPage = new EvrakOlusturPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        postalananEvrakRaporuPage = new PostalananEvrakRaporuPage();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0308: Postalama ekranınlarında pdf'in yapısal kontrolü")
    public void TS0308() throws InterruptedException {
        login("Mbozdemir", "123");
        String konu = "TS0308_" + getSysDate();
        String metin308 = "Metni" + konu;

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .evrakTuruSec("Resmi Yazışma")
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("Normal")
                  .kaldirilacakKlasorler("Diğer")
//                .kaldirilacakKlasorler("B1K1")
                .bilgialaniKontrol()
                .gizlilikDerecesiSec("Normal")
//                .kaldirilacakKlasorler("B1K1")
                .ivedilikSec("Normal")
                .geregiSecimTipiSec("Kullanıcı")
                .geregiSec("Optiim TEST")
                .geregiSecimTipiSec("Dağıtım Planları")
                .geregiSec("TSK DAĞITIM PLANI")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();
        evrakOlusturPage2 = new pages.newPages.EvrakOlusturPage();

        ilgileriTab();

        evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle();


        evrakOlusturPage
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        evrakOlusturPage.ekleriTabAc()
                .ekleriTablariGeldigiGorme()
                .ekleriEkMetniDoldur("TS0308_PDF")
                .ekleriDosyaEkle("C:\\TestAutomation\\BelgenetFTA\\documents\\TS0308PDF.pdf")
                .ekleriEkle()
                .ekleriEkMetniDoldur("TS0308PDF")
                .ekleriDosyaEkle("C:\\TestAutomation\\BelgenetFTA\\documents\\TS0308PDF.pdf")
                .ekleriEkle()
                .fizikselEkEkleTabiniAc()
                .sistemdeKayitliEvrakEkleTabiniAc()
                .arsivdeKayitliEvrakEkleTabiniAc()
                .webAdresiEkleTabiniAc();


        evrakOlusturPage
                .editorTabAc()
                .editorSayiAl();
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS0308")
                .editorEvrakGeregiSec("YAZILIM GELİ")
                .editordeEkKontrol("TS0308_PDF","Ek kontrol")
                .imzala()
                .popupSImzalaIslemleri();


        Thread.sleep(1500);

        evrakOlusturPage.islemMesaji().isBasarili();
        Thread.sleep(2000);

                postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(text(konu)).shouldHaveSize(1).first().click();

                postalanacakEvraklarPage.tabEvrakEkleriAc()
                        .evrakEklerindeDetayButonuKontrol("TS0308_PDF","TS0308PDF" );

                postalanacakEvraklarPage.tabIlgiBilgileriAc();
                EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        evrakOnizleme.new IlgiBilgileri().openTab().getDataTable().findRows(text(metni)).shouldHaveSize(1);


        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama("Test")
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat();

        switchTo().window(1);
        closeNewWindow();

        switchTo().window(0);
        postalanacakEvraklarPage

                .postalanacakEvrakOrjYaz()
                .popupOrjYazYazdirButonKonrolleri()
                .pdfEvrakYazismaKuralkontrol()
                .PDFEibareVeKırmızıYazıktrl()
                .popupEvrOrjYazKapat()
                .dagitimplanyazdir()
                .gramajDoldur("111111")
                .hesapla()
                .postala()
                .dialogpostalaEvet();



        postalananlarPage.openPage();
        postalananlarPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first().click();
        postalananlarPage.postaDetayiTikla()
                .evSay();
        postalananlarPage
                .postalananyerlerKontrol()
                .dagitimPlanYazdir()
                .yazdirpopupYazdirButonktrl()
                .btnEkleriPopupiciYazdir();

        postalananlarPage.ktrlonceustyazi();
        postalananlarPage
                .popupYazpdfkontrolveKapatma();
        postalananlarPage
                .eklerYazdirPopupbtn("TS0308_PDF","TS0308PDF")
                .popupkapatma();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2076: Evrak Postalama işlemleri")
    public void TS2076() throws InterruptedException {

        login("Mbozdemir", "123");

        String evrakKonusu = "TS2076_" + getSysDate();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduSec("Yazılım Geliştirme")
                .konuDoldur(evrakKonusu)
                .evrakTuruSec("Resmi Yazışma")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Avrupa Birliği Bakanlığı", "Kurum")
                .kurumGeregiAlaniKurumPostaTipiKontrol("Avrupa Birliği Bakanlığı", "Adi Posta")

                .geregiKurumPostaTipi("Birim Elden")
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("Normal")

                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol("Mehmet BOZDEMİR", "PARAFLAMA")
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
                .onayAkisiKullan()

        .kaldirilacakKlasorler("000");

        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage.editorTabAc();
        editorTab
                .getEditor()
                .type("TS2076 senaryosu");

        editorTab
                .imzala()
                .popupSImzalaIslemleri();

        postalananlarPage
             .islemMesaji().basariliOlmali("İşlem başarılıdır!");

       // postalananlarPage
         //       .birimLogin("gsahin" , "123");

        login(TestData.usernameZTEKIN, TestData.passwordZTEKIN);

        postalanacakEvraklarPage
                .openPage()
                .konuyaGoreEvrakGelmemeKontrolu(evrakKonusu);

        postalananlarPage
                .openPage()
                .konuyaGoreEvrakGelmemeKontrolu(evrakKonusu);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR);

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakGecmisi()
                .evrakGecmisiWith("Evrak kurum içi otomatik postalandı.");
    }


    String konuKoduRandomTS520 = "TS0520A-"+createRandomNumber(15);
    String konuKodu = "Diğer";
    String kaldirilacakKlasor = "Diğer";
    String gizlilikDerecesi = "Normal";
    String bilgi = "Tüzel Kişi";
    String tuzelKisi = "Can Şeker";
    String editor = createRandomText(15);
    String imzalama = "İmzalama";
    String evrakTarihi = getSysDate();

    @Step("Postalananlar sayfasına evrak oluşturmakta")
    public void TS0520aPreCondition() {
        login("Mbozdemir", "123");

        //TODO Pre Condition Postalanacak evraklar sayfası data oluşturmakta
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandomTS520)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(editor)
                .bilgiSecimTipiSecByText(bilgi)
                .bilgiDoldur(tuzelKisi)
                .OnayAkisiEkle()
                .onayAkisiEkleIlkImzalaSec(imzalama)
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(editor)
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup();

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandomTS520)
                .evrakPostala()
                .evrakPostalaPostala(true);
        //TODO*/

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0520a : Postalanan evrak posta bilgilerinin önizleme ekranından güncellenmesi ve rapor üzerinde kontrolü")
    public void TS0520a() throws InterruptedException {

        TS0520aPreCondition();

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS520, tuzelKisi,evrakTarihi)
                .evrakSec(konuKoduRandomTS520)
                .postaDetayGeldigiGorme()
                .postaDetayiTikla();
        postalananlarPage.gonderilenyerlerKontrol()
                .tuzelKisiVeAciklamaAlanlarDoluGeldigiGorme("Can Şeker",editor);
        postalananlarPage.btnGuncelle();
        Thread.sleep(1000);
        postalananlarPage.btnTarihGuncelle("10.10.2017");
        postalananlarPage.btnPostakoduGuncelle("520");
        postalananlarPage.txtAciklama("TS0520a");
        //postalananlarPage.btnTuzelKisiGuncelle();

//         postalananlarPage = postalananlarPage.btnKaydet();

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
    @Test(enabled = true, description = "TS0520b : Postalanan evrak posta bilgilerinin içerik ekranından güncellenmesi ve rapordan kontrolü")
    public void TS0520b() throws InterruptedException {

        login("Mbozdemir", "123");

        TS0520aPreCondition();

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS520, tuzelKisi,evrakTarihi)
                .evrakSecIcerikGoster(konuKoduRandomTS520, tuzelKisi,evrakTarihi)
                .postaDetayGeldigiGorme();
        postalananlarPage.icerikDetayPostaDetayi();
        String txt = postalananlarPage.icerikEvrakSay();
        postalananlarPage.gonderilenyerlerKontrol()
                .tuzelKisiVeAciklamaAlanlarDoluGeldigiGorme("Can Şeker",editor);

        postalananlarPage.btnIcerikPostaDetayTuzelKisiGnc();
        postalananlarPage.btnIcerikPDTuzelKisiTebTarGnc("01.01.2018");
        postalananlarPage.btnIcerikPosDetTuzKisPosKodGnc("520");
        postalananlarPage.btnIcerikPDTuzelKisiPosAcikGnc("TS520b");
        postalananlarPage.btnIcerikPDPopupKaydet();

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
        konu1685 = "TS1685_" + getSysDate();
        String tuzelKisiVergiNo = "1122007720";
        String kurum = "Başbakanlık";
        String fizikselEkMetni = "TS1685 Ek metni";
        String basariMesaji = "İşlem başarılıdır!";
        String dagitimSatiriMesaj = "KEP posta birimine gönderildi.";
        String fizikselEkMesaji = "Evrakın fiziksel eki vardır, göndermeyi unutmayınız!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduSec("Yazılım Geliştirme")
                .konuDoldur(konu1685)
                .kaldirilacakKlasorler("Diğer")
                .gizlilikDerecesiSec("Normal")
//                .kaldirilacakKlasorler("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .ivedilikSec("Normal")
                .geregiSecimTipiSec("Tüzel Kişi")
                .geregiDoldur("Optiim İş Çözümleri AŞ", "Tüzel Kişi Adı")
                .tuzelKisiGeregiAlaniVergiNoPostaTipiKontrol(tuzelKisiVergiNo, "KEP")
                //.geregiKurumPostaTipi("E-Posta")
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur("Başbakanlık", "Kurum Adı")
                //.geregiKurumPostaTipi("APS")
                .kurumGeregiAlaniKurumPostaTipiKontrol(kurum, "KEP")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Mehmet BOZDEMİR", "İmzalama")
//                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        String evrakTarihi = evrakOlusturPage.bilgilerTabiAc().evrakTarihiAl();

        evrakOlusturPage
                .ekleriTabAc()
                .ekleriTabKontrolu()
               //.webAdresiEkleTabiniAc()
                //.arsivdeKayitliEvrakEkleTabiniAc()
                //.sistemdeKayitliEvrakEkleTabiniAc()
                .fizikselEkEkleTabiniAc()
                .fizikselEkMetniDoldur(fizikselEkMetni)
                .fizikselEkMetniEkle()
                .listelenenEklerdeKontrol(fizikselEkMetni, "Fiziksel Ek Metni");
      /*  evrakOlusturPage
                .ilgileriTabAc()
                .sistemeKayitliEvrakEkleTab()
                .sistemeKayitliEvrakAra("yazı")
                .sistemeKayitliDokumanArama()
                .tablodaBulunanEvrakiEkle(); */

//        evrakOlusturPage
  //              .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu1685)
                .imzala()
                .popupSImzalaIslemleri()
                .islemMesaji().basariliOlmali(basariMesaji);

        postalanacakEvraklarPage
                .openPage()
                .konuyaGoreEvrakKontroluAllPages(konu1685)
                .konuyaGoreEvrakKontrol(konu1685, evrakTarihi)
                .konuyaGoreEvrakOnizlemedeAc(konu1685);
                //.filter().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first().click();

        postalanacakEvraklarPage
                .btnFizikselEkIkonKontrol();

        postalanacakEvraklarPage
                .evrakPostala()
              //  .KntrlEvrakFizikselEkYaziSayTar()
                .evrakOnizlemeDagitimSatiriKontrol(dagitimSatiriMesaj)
                .evrakOnizlemeFizilselEkMesajiKontrolu(fizikselEkMesaji);
                //.postala()
                //.dialogpostalaEvet();

        //postalanacakEvraklarPage.islemMesaji().isBasarili();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0312 : postalama işlemi sonrası postalananlar listesinin kontrolü")
    public void TS0312() throws InterruptedException {

        login("Mbozdemir", "123");
        String konu = "TS1685_" + getSysDate();
        String imzaci ="Mehmet BOZDEMİR";

        postalananlarPage
                .openPage()
                .tabloEvrakGeldigiGorme()
                .icDisEvrakIkonuKontrolu()
                .btnKurdele()
                .tekImzaciKontrol(imzaci)
                .mngImzaDialog()
                .btnImzaciPopupKapat()
                .btnTamEkran()
                .icDisSuretKtrl()
                .sagTabKontrol()
                .btnTamEkranKapat()
                .btnIcerikGoster();

        postalananlarPage
                .btnIcerikIlgileriTab()
                .btnIcerikEkleriTab()
                .btnIcerikDetayKapat();

        postalananlarPage
                .btnFiltreSpan()
                .btnFiltreBaslangicTarihi(getSysDateForKis())
                .btnFiltrePostaladiklarim()
                .tabloEvrakGeldigiGorme();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2235 : Ek ilgi ilişiği olan evrakı postaya düşürme")
    public void TS2235() throws Exception {
        System.out.println("Konu: " + konu);

        login(user1);
        dagitimPlanElemanlari = new LinkedHashMap<GeregiSecimTipi, String>();
        dagitimPlanElemanlari.put(GERCEK_KISI, "OptiimTEST");
        dagitimPlanElemanlari.put(DAGITIM_PLANLARI, "OPTİİM DAĞITIM 1");
        dagitimPlanElemanlari.put(BIRIM, "ARAŞTIRMA-GELİŞTİRME ALTTTT");
        dagitimPlanElemanlari.put(TUZEL_KISI, "Türksat Optiim");
        dagitimPlanElemanlari.put(KULLANICI, "Optiim Test");
        dagitimPlanElemanlari.put(KURUM, "Başbakan");


        evrakOlusturPage2 = new pages.newPages.EvrakOlusturPage();
        evrakOlusturPage2.openPage()
                .bilgileriTab()
                    .konuKoduSec(konuKodu1)
                    .konuDoldur(konu)
                    .kaldiralacakKlasorleriSec("Diğer")
                    .gizlilikDerecesiSec(GizlilikDerecesi.NORMAL)
                    .ivedilikSec(Ivedilik.NORMAL)
                    .evrakTuruSec("Resmi Yazışma")
                .geregiSec(GERCEK_KISI, dagitimPlanElemanlari.get(GERCEK_KISI).toString(), "APS")
                .geregiSec(DAGITIM_PLANLARI,dagitimPlanElemanlari.get(DAGITIM_PLANLARI).toString(), "Adi Posta")
                .geregiSec(BIRIM, dagitimPlanElemanlari.get(BIRIM).toString())
                .geregiSec(TUZEL_KISI, dagitimPlanElemanlari.get(TUZEL_KISI).toString(), "Adi Posta")
                .geregiSec(KULLANICI, dagitimPlanElemanlari.get(KULLANICI).toString())
                .geregiSec(KURUM,dagitimPlanElemanlari.get(KURUM).toString(), "Adi Posta");

        /*evrakOlusturPage2.bilgileriTab().geregiSec(GERCEK_KISI, "OptiimTest")
                .getSecilenGeregiPostaTipi("Değeri \"Adi Posta\" ve değiştirilebilir modda olmalı", "OptiimTest")
                .shouldBe(enabled).getSelectedOption().shouldHave(text("Adi Posta"));
        evrakOlusturPage2.bilgileriTab().getSecilenGeregiPostaTipi("Değeri \"APS\" seçilir","OptiimTest").selectOption("APS");

        evrakOlusturPage2.bilgileriTab().geregiSec(DAGITIM_PLANLARI,"50 BİRİMLİK TEST DENEME DAĞITIM PLANI")
                .getSecilenGeregiPostaTipi("Değeri \"Adi Posta\" ve değiştirilebilir modda olmalı","50 BİRİMLİK TEST DENEME DAĞITIM PLANI")
                .shouldBe(enabled).getSelectedOption().shouldHave(text("Adi Posta"));

        evrakOlusturPage2.bilgileriTab().geregiSec(BIRIM, "ARAŞTIRMA-GELİŞTİRME");

        evrakOlusturPage2.bilgileriTab().geregiSec(TUZEL_KISI, "Türksat Optiim")
                .getSecilenGeregiPostaTipi("Değeri \"Adi Posta\" ve değiştirilebilir modda olmalı","Türksat Optiim")
                .shouldBe(enabled).getSelectedOption().shouldHave(text("Adi Posta"));

        evrakOlusturPage2.bilgileriTab().geregiSec(KULLANICI, "Optiim TEST");

        evrakOlusturPage2.bilgileriTab().geregiSec(KURUM,"Başbakanlık")
                .getSecilenGeregiPostaTipi("Değeri \"KEP\" ve değiştirilebilir modda olmalı", "Başbakanlık")
                .shouldBe(enabled).getSelectedOption().shouldHave(text("KEP"));*/
        //Optiim İş seçtiğinde "Adi Posta" gelmiyor "KEP" geliyor.
        /*page.bilgileriTab().geregiSec(TUZEL_KISI, "Optiim İş")
                .getSecilenGeregiPostaTipi("Değeri \"Adi Posta\" ve değiştirilebilir modda olmalı","Optiim İş")
                .shouldBe(enabled).getSelectedOption().shouldHave(text("Adi Posta"));*/

        evrakOlusturPage2.bilgileriTab().onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user1, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla();

        ekleriTab();
        ilgileriTab();
        iliskiliEvraklar();
        editorTab();

        evrakOlusturPage2.pageButtons().evrakImzala().islemMesaji().basariliOlmali();


        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage().openPage();
        postalanacakEvraklarPage.searchTable().findRows(text(konu)).shouldHave(text(getSysDateForKis()))
                .shouldHave(matchText("/ No: \\d+"))
                .getFoundRow().click();

        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        evrakOnizleme.new IliskiliEvraklar().getTab("İlişkili Evraklar tabının gelmediği görülür").shouldNot(exist);

        EvrakOnizleme.EvrakEkleri evrakEkleri = evrakOnizleme.new EvrakEkleri().openTab();
        evrakEkleri.getDataTable().findRows(text(ekleri)).shouldHaveSize(1);
        evrakEkleri.evrakTextControl(docText);

        evrakOnizleme.new IlgiBilgileri().openTab().getDataTable().findRows(text(metni)).shouldHaveSize(1);

        evrakPostala = evrakOnizleme.evrakPostala();
        gidisSekliKontrol(BIRIM.getOptionText(), dagitimPlanElemanlari.get(BIRIM).toString(), "Elektronik Gönderilmiştir");
        gidisSekliKontrol(KULLANICI.getOptionText(), dagitimPlanElemanlari.get(KULLANICI).toString(), "Elektronik Gönderilmiştir");
        gidisSekliKontrol(TUZEL_KISI.getOptionText(), dagitimPlanElemanlari.get(TUZEL_KISI).toString(), "Adi Posta");
        gidisSekliKontrol(KURUM.getOptionText(), dagitimPlanElemanlari.get(KURUM).toString(), "Adi Posta");
        gidisSekliKontrol(GERCEK_KISI.getOptionText(), dagitimPlanElemanlari.get(GERCEK_KISI).toString(), "APS");

        evrakPostala.yazdir();
        evrakPostala.getYazdirUstVerilerListesi().findRows(text(konu)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        evrakPostala.getUstVerilerYazdirButton(konu + " satırda bulunmalı").shouldBe(visible);
        evrakPostala.getYazdirEvrakinEkleriListesi().findRows(text(ekleri)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        evrakPostala.getEvrakinEkleriYazdirButton(ekleri + " satırda bulunmalı").shouldBe(visible);
        evrakPostala.yazdirClose();

        gidisSekliKontrol(DAGITIM_PLANLARI.getOptionText(), "DAĞITIM YERLERİNE", "Detaya tıkla");
        evrakPostala.getDetayButtonInFoundRow("buton bulunur").shouldBe(visible).click();
        EvrakOnizleme.DagitimPlaniIcerigi dagitimPlaniIcerigi = evrakOnizleme.new DagitimPlaniIcerigi();
        dagitimPlaniIcerigi.getDagitimPlaniDetayDataTable().findRows(text("Başbakanlık")).shouldBe(visible);
        dagitimPlaniIcerigi.getGidisSekli("Adi Posta olmalı").shouldHave(exactText("Adi Posta"));
        dagitimPlaniIcerigi.getDagitimPlaniDetayDataTable().findRows(text("Cumhurbaşkanlığı")).shouldBe(visible);
        dagitimPlaniIcerigi.getGidisSekli("Adi Posta olmalı").shouldHave(exactText("Adi Posta"));

        /*login("Mbozdemir", "123");
        String konu = "TS2235_" + getSysDate();
        konu309 = konu;

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
                .gizlilikDerecesiSec("Normal")
//                .kaldirilacakKlasorler("B1K1")
                .ivedilikSec("Normal")
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
        title = evrakOlusturPage.bilgilerTabiAc().geregiTitleAl();
        gonderimSekli = evrakOlusturPage.bilgilerTabiAc().geregiGonderimSekliAl();


        evrakOlusturPage
                .ekleriTabAc()
                .ekleriTablariGeldigiGorme()
                .ekleriEkMetniDoldur("TS2235_PDF")
                .ekleriDosyaEkle("C:\\TestAutomation\\BelgenetFTA\\documents\\TS2235PDF.pdf")
                .ekleriEkle()
                .ekleriEkMetniDoldur("TS2235PDF")
                .ekleriDosyaEkle("C:\\TestAutomation\\BelgenetFTA\\documents\\TS2235PDF.pdf")
                .ekleriEkle()
                .webAdresiEkleTabiniAc()
                .fizikselEkEkleTabiniAc()
                .sistemdeKayitliEvrakEkleTabiniAc();


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
                .editordeEkKontrol("TS2235PDF" , "Ek kontrolleri")
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
                .pdfEvrakYazismaKuralkontrol();

        closeNewWindow();
        switchTo().window(0);

        postalanacakEvraklarPage
                .popupPostaYazdirmaKapat()
                .dagitimDetay();*/

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
    @Test(enabled = true
            , dependsOnMethods = {"TS2235"}
            , description = "TS0309 : Önizleme ekranından ek ilgi ve ilişiği olan evrakın postalanması")
    public void TS0309() throws InterruptedException {
        login(user1);
        //login("Mbozdemir", "123");
        //String konu = "TS2235_20180209022613";
        //String konu = konu309;
        String basariMesaji = "İşlem başarılıdır!";
//        String title = "OptiimTest TestOptiim";
        String tarih = getSysDateForKis();
        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(text(konu)).first().click();
        String evrakNo = postalanacakEvraklarPage.filter().findRowsWith(text(konu)).first().text().split("No:")[1];
        evrakNo = evrakNo.split("Miat")[0].trim();

        title[0] = dagitimPlanElemanlari.get(GERCEK_KISI).toString();
        /*dagitimPlanElemanlari = new LinkedHashMap<GeregiSecimTipi, String>();
        dagitimPlanElemanlari.put(GERCEK_KISI, "OptiimTEST");
        dagitimPlanElemanlari.put(DAGITIM_PLANLARI, "OPTİİM DAĞITIM 1");
        dagitimPlanElemanlari.put(BIRIM, "ARAŞTIRMA-GELİŞTİRME ALTTTT");
        dagitimPlanElemanlari.put(TUZEL_KISI, "Türksat Optiim");
        dagitimPlanElemanlari.put(KULLANICI, "Optiim Test");
        dagitimPlanElemanlari.put(KURUM, "Başbakan");*/

        //postalanacakEvraklarPage.evrakPostala()
          //      .alanKontrolleri(konu,title,gonderimSekli)
        //postalanacakEvraklarPage.postalanacakYerlerGidisSekliDoldur("Adi Posta")
        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();
        evrakPostala = evrakOnizleme.evrakPostala();
        gidisSekliKontrol(DAGITIM_PLANLARI.getOptionText(), "DAĞITIM YERLERİNE", "Detaya tıkla");
        gidisSekliKontrol(BIRIM.getOptionText(), dagitimPlanElemanlari.get(BIRIM).toString(), "Elektronik Gönderilmiştir");
        gidisSekliKontrol(KULLANICI.getOptionText(), dagitimPlanElemanlari.get(KULLANICI).toString(), "Elektronik Gönderilmiştir");
        gidisSekliKontrol(TUZEL_KISI.getOptionText(), dagitimPlanElemanlari.get(TUZEL_KISI).toString(), "Adi Posta");
        gidisSekliKontrol(KURUM.getOptionText(), dagitimPlanElemanlari.get(KURUM).toString(), "Adi Posta");
        gidisSekliKontrol(GERCEK_KISI.getOptionText(), dagitimPlanElemanlari.get(GERCEK_KISI).toString(), "APS");

        evrakPostala.postalanacakYerlerdeAra(text(dagitimPlanElemanlari.get(BIRIM).toString()))
                .ayrintiAlanDoldur("Posta Kodu", "1111")
                .aciklamaGir(BIRIM.getOptionText() + " açıklama")
                .postalanacakYerlerdeAra(text(dagitimPlanElemanlari.get(TUZEL_KISI).toString()))
                .ayrintiAlanDoldur("Posta Kodu", "1112")
                .aciklamaGir(TUZEL_KISI.getOptionText() + " açıklama");

        evrakPostala.postalanacakYerlerdeAra(text("DAĞITIM YERLERİNE"))
                .getDetayButtonInFoundRow("buton bulunur").shouldBe(visible).click();
        EvrakOnizleme.DagitimPlaniIcerigi dagitimPlaniIcerigi = evrakOnizleme.new DagitimPlaniIcerigi();
        dagitimPlaniIcerigi.postalanacakYerlerdeAra(text("Başbakanlık"))
                .gidisSekliSec("Adi Posta")
                .ayrintiAlanDoldur("Posta Kodu", "309")
                .gonderildigiYerSec("Yurt İçi")
                .aciklamaGir("Başbakanlık açıklama")
                .grammajGir("15")
                .grammajHesapla()
                .tutarDialogTamamTikla()
                .tutarKonrolEt("100.000")
                .kaydet();
        evrakPostala.yazdir();
        evrakPostala.getYazdirUstVerilerListesi().findRows(text(konu)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        evrakPostala.getUstVerilerYazdirButton(konu + " satırda bulunmalı").shouldBe(visible);
        evrakPostala.getYazdirEvrakinEkleriListesi().findRows(text(ekleri)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        evrakPostala.getEvrakinEkleriYazdirButton(ekleri + " satırda bulunur ve tıklanır").shouldBe(visible).click();
        postalanacakEvraklarPage.pdfKontrol();
        evrakPostala.yazdirClose();



        /*postalanacakEvraklarPage.detayTikla()
//                .dagitimPlaniIcerigiEkraniKapat()
                .dagitimPlaniIlkAyrintiDoldur("309","TS0309","Yurt Dışı","15")
                .dagitimPlaniIlkGramajHesapla()
                .popUpKontrol();
        String tutar =postalanacakEvraklarPage.popUpTutarAl();
        String[] spltTutar = tutar.split(" ");
        System.out.println(spltTutar[0]);*/

        /*postalanacakEvraklarPage
                .popUpTamam()
                .dagitimPlaniTutarKontrol(spltTutar[0])
                .dagitimPlaniKaydetl()
                .postalanacakYerleryazdir()
                .evrakDetaylariPopUpKontrol()
                .evrakDetaylariUstVerilerYazdirButonKontrol(konu)
                .evrakDetaylarieEvrakEkleriYazdirButonKontrol("Ek-1")
                //.evrakDetaylarieEvrakEkleriYazdirButonKontrol("Ek-2")
                .evrakDetaylariUstVerilerYazdirButonTikla(konu)
                .pdfKontrol()
                .evrakDetaylariEkranKapat()*/
        postalanacakEvraklarPage.etiketYazdir()
                .etiketBastirEkraniKontrolü(tarih, title[0], evrakNo)
                .etiketYazdirPopupKapat()
                .postala()
                .dialogpostalaEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage()
                .evrakGeldigiGorme(konu,title[0],tarih)
                .evrakSec(konu,tarih,title[0])
                .evrakOnizlemeButonKontrol("Posta Detayı")
//                .evrakOnizlemeEvrakEkKontrolu()
                .btnEvrakEkleri()
                .evrakEkleriKontrol("Ek-1");
                //.evrakEkleriKontrol("Ek-2");
        postalananlarPage
                .btnFiltrenenPostaIcerikGoster(konu)
//                .btnIcerikEkleriTab()
                .btnIcerikIlgileriTab()
                .btnIcerikDetayKapat();

        postalananlarPage
                .evrakSec(konu,tarih,title[0])
                .postaDetayiTikla()
                .postaDetayiPostalananYerlerKontrolu("DAĞITIM YERLERİNE","309","Başbakanlık açıklama")
                .postaDetayiPostalananYerlerYazdir("DAĞITIM YERLERİNE","309","Başbakanlık açıklama")
                .evrakDetaylariUstVerilerYazdirButonTikla(konu)
                .pdfKontrol();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1434 : Postalanan Evrak Raporu Alan kontrolleri")
    public void TS1434() throws InterruptedException    {
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

        postalananEvrakRaporuPage.cmbClearEvrakSahibi();
        postalananEvrakRaporuPage.cmbPostalananYerSecimi("OptiimTest TestOptiim")
                .postaSorgulama();

        String expected = "OptiimTestTestOptiim";
        postalananEvrakRaporuPage.postalananyerKontrol(expected);
        postalananEvrakRaporuPage.cmbPostalananYerSecimiTemizle();

        postalananEvrakRaporuPage.cmbpostaSeklisecimi("İç Giden")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbpostaSeklisecimi("Dış Giden")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbpostaSeklisecimi("Seçiniz");
        postalananEvrakRaporuPage.cmbPostaTipisec("Adi Posta")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbPostaTipisec("Seçiniz");
        postalananEvrakRaporuPage.txtPostaAciklama("TS")
                .postaSorgulama();
        postalananEvrakRaporuPage.clearPostaAciklamaAlani();
        postalananEvrakRaporuPage.cmbPostalayanadi("Zübeyde TEKİN")
                .postaSorgulama();
        postalananEvrakRaporuPage.cmbClearPostalayanAdi();
        postalananEvrakRaporuPage.chkboxPostaladiklarim()
                .postaSorgulama();
        postalananEvrakRaporuPage
                .cmbEvrakSahibi("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .cmbPostalananYerSecimi("OptiimTest TestOptiim")
                .cmbpostaSeklisecimi("İç Giden")
                .cmbPostaTipisec("Adi Posta")
                .cmbPostalayanadi("Zübeyde TEKİN")
                .postaSorgulama();
        postalananEvrakRaporuPage.ekranSorgulananSonucKontrol();
        postalananEvrakRaporuPage.evrakRaporForm();


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0310 : İçerik ekranından evrakın postalanması"
    ,dependsOnMethods = {"TS1685"})
    public void TS0310() throws InterruptedException {
        login("mbozdemir", "123");
        //String konu = "TS1685_";
        postalanacakEvraklarPage.openPage()
                .btnFiltrenenPostaIcerikGoster(konu1685);
        postalanacakEvraklarPage.btnEvrakIcerikEvrakPostala();
        postalanacakEvraklarPage.btnDagitimGidisSekli("Adi Posta")
                .inputIcerikPstakod("0310");
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
                .btnIcerikEvrakPostalama()
         .btnIcerikPostalamaEvet();

        postalanacakEvraklarPage.islemMesaji().isBasarili();

        postalananlarPage.openPage();
        postalananlarPage.filter().findRowsWith(text(konu1685)).first().click();
        postalananlarPage.postaDetayiTikla();
        postalananlarPage.evSay();
        postalananlarPage.evrakYazdir();
        postalananlarPage.etiketBastir();
        postalananlarPage.btnPopupEtiketBastirKapat();


    }


    @Step("Ekleri sekmesinde ekleme ve kontrolleri")
    private void ekleriTab() {
        altTabs = evrakOlusturPage2.ekleriTab().openTab().altTabs();
        altTabs.getFiziksetEkEkleTab().shouldBe(visible);
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getWebAdresiniEkleTab().shouldBe(visible);
        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ekMetniDoldur(ekleri)
                .ekleButonaTikla();
        SelenideElement row = evrakOlusturPage2.ekleriTab().getEkListesiTablosu().findRows(text(ekleri)).shouldHaveSize(1).getFoundRow();
        checkDagitimElemanlariComboboxValues(comboBox(row, ".ui-selectcheckboxmenu").getComboBoxValues(), new ArrayList<>(dagitimPlanElemanlari.values()));
    }

    @Step("Dağıtım yerlerinin doğru olarak listelendiği görülür")
    public void checkDagitimElemanlariComboboxValues(ElementsCollection actualDagitimElemenlari, ArrayList<String> expectedDagitimElemenlari){
        for (int i = 0; i < actualDagitimElemenlari.size(); i++) {
            Assert.assertTrue(actualDagitimElemenlari.get(i).$("span.ui-chkbox-icon").has(cssClass("ui-icon-check")), expectedDagitimElemenlari.get(i) + " dağıtım elemanın checkbox seçili olmalı");
            actualDagitimElemenlari.get(i).shouldHave(text(expectedDagitimElemenlari.get(i)));
        }
    }

    @Step("İlgileri sekmesinde ekleme ve kontrolleri")
    private void ilgileriTab() {
        altTabs = evrakOlusturPage2.ilgileriTab().openTab().altTabs();
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getDosyaEkleTab().shouldBe(visible);
        altTabs.metinEkleTabiAc().ilgiMetniDoldur(metni).ekleButonaTikla();
        evrakOlusturPage2.ilgileriTab().getIlgliliListesiTablosu().findRows(text(metni)).shouldHaveSize(1);
    }

    @Step("İlişkili Evraklar sekmesinde ekleme ve kontrolleri")
    private void iliskiliEvraklar() {
        altTabs = evrakOlusturPage2.iliskiliEvraklarTab().openTab().altTabs();
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getTercumeEkleTab().shouldBe(visible);
        altTabs.getArsivdeKayitliEvrakEkleTab().shouldBe(visible);

        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ilisikMetniDoldur("İlişkili Tab " + konu)
                .ekleButonaTikla();
        evrakOlusturPage2.iliskiliEvraklarTab().getEkListesiTablosu().findRows(text("İlişkili Tab " + konu))
                .shouldHaveSize(1)
                .getColumnHeaders().filterBy(text("Dağıtım Yerleri")).shouldHaveSize(0);
    }

    @Step("Editör sekmesinde kontrolleri")
    public void editorTab() throws IOException {
        evrakOlusturPage2.editorTab().openTab().getEditor().type("Editör tekst");

        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("birim", user1.getBirimAdi());
        params.put("sayi", konuKoduSayi);
        params.put("konu", konu);
        params.put("ilgi", metni);
        params.put("ek", ekleri);

        GalenControl galen = new GalenControl();
        galen.generateDump("TS2235", params);
        galen.layoutControl("TS2235", params);
    }


    @Step("{dagitimTipi} gidiş şekli kontrolü")
    private void gidisSekliKontrol(String dagitimTipi, String dagitimElemani, String gidisSekli) {
        evrakPostala.postalanacakYerlerdeAra(text(dagitimElemani));
        if (evrakPostala.getGidisSekli("").exists())
            evrakPostala.getGidisSekli(gidisSekli + " olmalı").shouldHave(exactText(gidisSekli));
        else
            evrakPostala.getPostalanacakListesi().shouldHave(text(gidisSekli));
    }
}
