package tests.GelenEvrakiCevapliKapat;

import com.codeborne.selenide.Condition;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.IslemMesajlari;
import pages.pageData.SolMenuData;
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static data.TestData.*;

import pages.EvrakDetayiPage;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.GelenEvraklarPage;
import pages.ustMenuPages.CevaplananEvrakRaporuPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.io.IOException;

import static data.TestData.password;
import static data.TestData.username;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Gelen Evrakı Cevaplı Kapat" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class GelenEvrakiCevapliKapatTest extends BaseTest {

    CevaplananEvrakRaporuPage cevaplananEvrakRaporuPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakOlusturPage evrakOlusturPage;
    EvrakDetayiPage evrakDetayiPage;
    GelenEvraklarCevapYazPage gelenEvraklarCevapYazPage;
    CevapladiklarimPage cevapladiklarimPage;
    KlasoreKaldirdiklarimPage klasoreKaldirdiklarimPage;
    KlasorEvrakIslemleriPage klasorEvrakIslemleriPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    TextEditor editor;


    @BeforeMethod
    public void loginBeforeTests() {
        login("ztekin", "123");
        cevaplananEvrakRaporuPage = new CevaplananEvrakRaporuPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakOlusturPage = new EvrakOlusturPage();
        cevapladiklarimPage = new CevapladiklarimPage();
        klasoreKaldirdiklarimPage = new KlasoreKaldirdiklarimPage();
        klasorEvrakIslemleriPage = new KlasorEvrakIslemleriPage();
        evrakDetayiPage = new EvrakDetayiPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
    }

    @Test(enabled = true, description = "TC310: Kurum içi gelen evraka cevap yaz")
    public void TC310() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2227-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String icerik = createRandomText(15);

        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .cevapYaz();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);
        evrakOlusturPage
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(konuKodu)
                .onayAkisiEkle()
                .onayAkisiKullan();


        evrakDetayiPage = new EvrakDetayiPage();
        gelenEvraklarCevapYazPage = new GelenEvraklarCevapYazPage();
        editor = new TextEditor();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1368: Cevaplanan Evrak Raporu")
    public void TC1368() throws IOException, InterruptedException {

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String konuKodu = "Kanunlar";
        String ilkTarih = "17.11.2017";
        String sonTarih = "17.11.2017";

        String evrakTarihi = "17.11.2017";
        String evrakKayitTarihi = "17.11.2017";
        String evrakSayisi = "54354354-8794";
        String konu = "Kanunlar";
        String cevaplananEvrakKonuKodu = "Kanunlar";
        String cevaplananEvrakKonu = "Kanunlar";
        String cevaplananEvrakSayisi = "6345202-010.01-9075";
        String cevaplananEvrakTarihi = "17.11.2017";
        String basariMesaji = "İşlem başarılıdır!";
        String filePath = "C:\\Users\\" + getPcUserName() + "\\Downloads\\";
        String fileName = "Rapor_";
        String fileName2 = "Rapor_.xls";

        cevaplananEvrakRaporuPage
                .openPage()
                .birimDoldur(birim)
                .altBirimEvraklariDahilSec(true)
                .konuKoduDoldur(konuKodu)
                .ilkTarihDoldur(ilkTarih)
                .sonTarihDoldur(sonTarih)
                .sorgula()
                .cevaplananEvrakKayitKontrolu(
                        evrakTarihi,
                        evrakKayitTarihi,
                        evrakSayisi,
                        konu,
                        cevaplananEvrakKonuKodu,
                        cevaplananEvrakKonu,
                        cevaplananEvrakSayisi,
                        cevaplananEvrakTarihi)
                .cevaplananEvrakGecmisiVeDetayKontrolu()
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .sayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .deleteFile(filePath, fileName);

        cevaplananEvrakRaporuPage
                .tumSayfayiRaporla()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevaplananEvrakRaporuPage
                .excellIndirilmeKontrolu(filePath, fileName2);

        cevaplananEvrakRaporuPage
                .temizle()
                .temizleSonrasiKontrol();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC0373: Cevap yazma işleminde evrakın onay akışından silinmesi")
    public void TC0373() throws InterruptedException {

        String onayAkisi = "Tc373 OnayAkışı";
        String tuzelKisi = "Tc373 TüzelKişi";
        String kisiKurum = "Tüzel Kişi";
        String kisi = "Optiim TEST";
        String konuKodu = "040"; //Faaliyet Raporları
        String konu = "Faaliyet Raporları";
        String kayitTarihi = getSysDateForKis();
        String evrakSayiSol = createRandomNumber(10);
        String evrakSayiSag = createRandomNumber(4);
        String basariMesaji = "İşlem başarılıdır!";
        String evrakSayi = evrakSayiSol + "-" + evrakSayiSag;
        String kaldirilacakKlasorler = "ESK05";
        String aciklama = "Onay işlemi açıklamasıdır.";

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText(kisiKurum)
                .geldigiTuzelKisiDoldur(tuzelKisi)
                .konuKoduDoldur(konuKodu)
                .evrakTarihiDoldur(kayitTarihi)
                .evrakSayiSolDoldur(evrakSayiSol)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet();

        String evrakNo = gelenEvrakKayitPage.popUps();
        String kayitTarihiSayi = kayitTarihi + " / " + evrakNo;

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        login(username, password);

        gelenEvraklarPage
                .openPage()
                .evrakSec(tuzelKisi, konu, kayitTarihiSayi, kayitTarihi, evrakSayi);

        evrakDetayiPage
                .sayfaAcilmali()
                .ikonKontrolleri()
                .cevapYaz();

        gelenEvraklarCevapYazPage
                .geregiKontrolu(tuzelKisi)
                .konuKonuKontrolu(konu)
                .editorTabAc()
                .editorSayiTarihKontrolu(evrakSayi, kayitTarihi);

        editor
                .type("Bu bir text yazısıdır.");

        gelenEvraklarCevapYazPage
                .bilgilerTabAc()
                .kaldirilacakKlasorlerDoldur(kaldirilacakKlasorler)
                .onayAkisiDoldur(onayAkisi)
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur(aciklama)
                .gonder()
                .evrakKayitUyariPopup("Evet")
                .islemMesaji().basariliOlmali(basariMesaji);

        gelenEvraklarPage
                .tabloOlmayanEvrakNoKontrol(evrakNo);

        //TODO: devam edilecek.
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "")
    public void TC2186() throws InterruptedException {

        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String birim = "OPTİİM BİRİM";
        String konu = "Test " + getSysDate();
        String ad = "Test";
        String soyad = "Otomasyon";
        String kisiKurum = "Gerçek Kişi";
        String basariMesaji = "İşlem başarılıdır!";
        String tur = "IMZALAMA";

        String mernisNo = createMernisTCKN();

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKisiEkle()
                .iletisimBilgisiTCKNEkle(mernisNo)
                .iletisimBilgisiTCKNAra()
                .iletisimBilgisiAdDoldur(ad)
                .iletisimBilgisiSoyadDoldur(soyad)
                .iletisimBilgisikaydet();
        gelenEvrakKayitPage
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet();

        String evrakNO2186 = gelenEvrakKayitPage.popUps();

        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
        login(username4, password4);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .cevapYaz();

        alanDegeriKontrolEt($("[id$='konuTextArea']"), konu, true, true);

        evrakOlusturPage
                .bilgilerTabiAc()
                .evrakTuruSec("Form")
                .formSec("Kopya Optiim form şablonu")
                .kaldiralacakKlasorlerSec("Diğer")
                .cmbOnayAkisi("DenemeListe")
                .kaydetVeOnayaSun()
                .onayIslemiAciklamaDoldur(konu)
                .onayIslemiGonder()
                .onayIslemiOnayaSunmaPopUp()
                .islemMesaji().beklenenMesaj(basariMesaji);

        //DenemeListesindeki kullnıcı veya kullanıcılarla giriş yapılır işlemdeki aksiyonlar alınır.
        logout();
        login("username24o","123");

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(konu)
                .evrakOnizlemeImzala()
                .sImzaSec()
                .sImzaImzala(true);

        logout();
        login(username4,password4);

        gelenEvraklarPage
                .openPage()
                .tabloOlmayanEvrakKontrol(konu);

        cevapladiklarimPage
                .openPage()
                .tabloKonuyaGoreEvrakKontrolu(konu);

        evrakDetayiPage
                .hareketGecmisiTabAc()
                .tabloKontol(" tarihli yazı ile cevap yazılarak kapatılmıştır.");

        klasoreKaldirdiklarimPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        klasorEvrakIslemleriPage
                .openPage()
                .klasorDoldurwithDetail("Diğer","[Klasör] 000")
                .evrakTarihiDoldur(getSysDateForKis2())
                .ara();

        klasorEvrakIslemleriPage
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        postalanacakEvraklarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);

        logout();
        login("username24o","123");

        postalanacakEvraklarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu)
                .evrakPostala()
                .postala()
                .islemMesaji().beklenenMesaj(basariMesaji);

        postalananlarPage
                .openPage()
                .filter().findRowsWith(Condition.text(konu))
                .shouldHaveSize(1);
    }
}
