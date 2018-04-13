/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakKopyalama;

import common.BaseTest;
import common.ReusableSteps;
import data.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.CevapYazPage;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;

import static data.TestData.*;
import static tests.BirimIcerikSablonu.BirimIcerikSablonuTest.TS1082;
import static tests.BirimIcerikSablonu.BirimIcerikSablonuTest.sablonAdi1082;

/****************************************************
 * Tarih: 2018-02-20
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Kopyalama" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Evrak Kopyalama")
public class EvrakKopyalamaTest extends BaseTest {

    ReusableSteps reusableSteps;
    BeklemeyeAlinanlarPage beklemeyeAlinanlarPage;
    GelenEvraklarPage gelenEvraklarPage;
    CevapYazPage cevapYazPage;
    EvrakOlusturPage evrakOlusturPage;
    TaslakEvraklarPage taslakEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;
    ParafladiklarimPage parafladiklarimPage;
    ImzaladiklarimPage imzaladiklarimPage;

    @BeforeMethod
    public void loginBeforeTests() {
        reusableSteps = new ReusableSteps();
        beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        cevapYazPage = new CevapYazPage();
        evrakOlusturPage = new EvrakOlusturPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        parafladiklarimPage = new ParafladiklarimPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
    }

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2233: Beklemeye alınanlar listesinden evrak kopyalanması ve imzalanması")
    public void TS2233() {
        String evrakTarihi = getSysDateForKis();
        String konuKoduTS2233 = "TS2233-" + createRandomNumber(15);
        String konuKodu = "Diğer";
        String evrakturu="Resmi Yazışma";
        String gizlilikderecesi ="Normal";
        String ivedilik ="Normal";
        String kaldirilicakKlasor = "Diğer";
        String icerik = createRandomText(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Mehmet Bozdemir";
        String kullanici2 = "Yasemin Çakıl Akyol";
        String basariMesaji2 = "Kopyalanan evraka \"Taslak Evraklar\" kısmından erişebilirsiniz.";
        String basariMesaji = "İşlem başarılıdır!";
        String mesaj ="Evrakı kopyalamak istediğinize emin misiniz?";
        login(userYakyol);

        reusableSteps
                .beklemeyeAlinanlarEvrakOlustur(konuKoduTS2233,konuKodu,kaldirilicakKlasor,icerik,"Kurum",kurum,"Paraflama",kullanici,"BHUPGMY","İmzalama",usernameMBOZDEMIR,passwordMBOZDEMIR);
        login(userMbozdemir);
        beklemeyeAlinanlarPage
                .openPage()
                .evrakSecKonuyagore(konuKoduTS2233)
                .evrakKopyalaGeldigiGorme()
                .evrakKopyala()
                .evrakKopyalamaSorusununGeldiginiGormeUyariMesaj(mesaj)
                .evrakKopyalaEvet()
                .islemMesaji().basariliOlmali(basariMesaji2);

        taslakEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduTS2233,kurum,evrakTarihi)
                .evrakNoIleIcerikGoster(konuKoduTS2233);

        evrakDetayiPage
                 .editorIcerigiGeldigiGorme(icerik)
                .bilgileriTabAc();
        evrakDetayiPage
                .bilgilerAlanlarDogruGeldigiGorme(konuKodu,konuKoduTS2233,kaldirilicakKlasor,evrakturu,gizlilikderecesi,kurum,kullanici,kullanici2);

        //İmzala butonu 6.adımda gelmektedir. Beklemeye alınanlar sayfası bu nedenle tekrar açılarak bu alanda imzalanmktadır

        beklemeyeAlinanlarPage
                .openPage()
                .evrakSecKonuyagore(konuKoduTS2233)
                .evrakKopyalaGeldigiGorme()
                .evrakKopyala()
                .evrakKopyalamaSorusununGeldiginiGormeUyariMesaj(mesaj)
                .evrakKopyalaEvet();

        evrakDetayiPage
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2196: kişisel şablon olan evrakın kopyalanması")
    public void TS2196() {

        String konuKoduTS2196 = "TS2196-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Mehmet Bozdemir";
        String basariMesaji2 = "Kopyalanan evraka \"Taslak Evraklar\" kısmından erişebilirsiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        login(userYakyol);

        evrakOlusturPage
                .evrakOlusturParafla(konuKoduTS2196,"Kurum",kurum,"Paraflama",kullanici,"BHUPGMY","İmzalama");

        login(userYakyol);

        parafladiklarimPage
                .openPage()
                .evrakNoGoreEvrakSec(konuKoduTS2196)
                .evrakSecEvrakKopyala()
                .evrakKopyalaUyariGeldigiGorme()
                .evrakKopyalaUyariEvet()
                .islemMesaji().basariliOlmali(basariMesaji2);

        taslakEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konuKoduTS2196);

        evrakDetayiPage
                .bilgileriTabAc()
                .kopyasiOlusturulanEvrakBilgilerininDegitirilebilgiGorme();

        evrakDetayiPage
                .ekleriTabAc()
                .eklenenDosyaninKopyalananDosyaAyniGeldigiGorulurDegistirelbildigiGorme();

        evrakDetayiPage
                .ilgileriTabAc()
                .cevapYazilanEvrakBilgisiKopyalananBosEvrakAyniGeldigiGorme();

        evrakDetayiPage
                .kaydet()
                .kaydetEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakDetayiPage
                .parafla();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2176: form türündeki evrakın kopyalanması")
    public void TS2176() {

        String konuKodu = "TS2176-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Yasemin Çakıl AKYOL";
        String basariMesaji = "İşlem başarılıdır!";
        String basariMesaji2 = "Kopyalanan evraka \"Taslak Evraklar\" kısmından erişebilirsiniz.";
        String sablon = "Optiim form şablonu";

        login(userZtekin);

        evrakOlusturPage
                .evrakOlusturEvrakTuruneGore(konuKodu,"Kurum",kurum,"Paraflama",kullanici,"BHUPGMY","İmzalama","Form",sablon);

        taslakEvraklarPage
                .openPage()
                .evrakSecKonuyaGore(konuKodu)
                .evrakKopyalaGonder()
                .evrakKopyalaEvet()
                .islemMesaji().basariliOlmali(basariMesaji2);

        taslakEvraklarPage
                .openPage()
                .evrakNoIleIcerikGoster(konuKodu);

        evrakDetayiPage
                .bilgileriTabAc()
                .kopyasiOlusturulanEvrakBilgilerininDegitirilebilgiGorme();

        evrakDetayiPage
                .kaydet()
                .kaydetEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakDetayiPage
                .parafla();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2175: birim içerik şablonu olan evrakı kopyalama")
    public void TS2175() {

        String konuKodu = "TS2175-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Mehmet Bozdemir";
        String kaldirilacakKlasor = "Diğer";
        String basariMesaji = "İşlem başarılıdır!";
        String basariMesaji2 = "Kopyalanan evraka \"Taslak Evraklar\" kısmından erişebilirsiniz.";

        TS1082();

        evrakOlusturPage
                .evrakOlusturBirimIcerikKullanParafla(konuKodu,"Kurum",kurum,"Paraflama",kullanici,"BHUPGMY","İmzalama",sablonAdi1082);

        login(user1);

        parafladiklarimPage
                .openPage()
                .evrakNoGoreEvrakSec(konuKodu)
                .evrakSecEvrakKopyala()
                .evrakKopyalaUyariGeldigiGorme()
                .evrakKopyalaUyariEvet()
                .islemMesaji().basariliOlmali(basariMesaji2);

        taslakEvraklarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGosterSec(konuKodu);

        evrakDetayiPage
                .bilgileriTabAc()
                .bilgileriTabKaldirilacakKlasorOnayAkisiGeregiGeldigiGorme(kaldirilacakKlasor,kurum,kullanici);

        evrakDetayiPage
                .ekleriTabAc()
                .eklenenDosyaninKopyalananDosyaAyniGeldigiGorulur();

        evrakDetayiPage
                .ilgileriTabAc()
                .cevapYazilanEvrakBilgisiKopyalananBosEvrakAyniGeldigiGorme();

        evrakDetayiPage
                .kaydet()
                .kaydetEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakDetayiPage
                .parafla();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2184: Cevap evrakın kopyalanması")
    public void TS2184() {

        String konuKodu = "TS2184-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Zübeyde Tekin";
        String kaldirilacakKlasor = "Diğer";
        String geregi = "Optiim Birim";
        String onayAkisi = "TS2172";
        String basariMesaji = "İşlem başarılıdır!";
        String basariMesaji2 = "Kopyalanan evraka \"Taslak Evraklar\" kısmından erişebilirsiniz.";
        String icerik = createRandomText(15);
        String filePath = getUploadPath() + "Otomasyon.pdf";

        login(usernameZTEKIN,passwordZTEKIN);

        reusableSteps
                .gelenEvraklarEvrakOlustur(konuKodu,kurum,birim);

        gelenEvraklarPage
                .openPage()
                .evrakNoyaGoreEvrakSec(konuKodu)
                .cevapYaz();

        cevapYazPage
                .kaldirilacakKlasorlerDoldur(kaldirilacakKlasor)
                .geregiDoldur(geregi)
                .onayAkisiDoldur(onayAkisi)
                .kaydet()
                .evrakKayitPopupEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

        evrakOlusturPage
                .ekleriTabAc();

        cevapYazPage
                .evrakEkTabViewDosyaEkle(filePath)
                .evrakEkTabViewEkMetniDoldur(icerik)
                .evrakEkTabViewEkle()
                .kaydet()
                .evrakKayitPopupEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        cevapYazPage
                .evrakKopyala()
                .evrakKopyalaUyariGeldigiGorme()
                .evrakKopyalaUyariEvet()
                .islemMesaji().basariliOlmali(basariMesaji2);

        taslakEvraklarPage
                .openPage()
                .kopyaliEvraklarGeldigiGorme(konuKodu)
                .evrakNoIleIcerikGoster(konuKodu)
                .editorAlaniGirilenIcerikAyniGeldigiGorme(icerik);

        evrakDetayiPage
                .bilgileriTabAc()
                .bilgileriTabKaldirilacakKlasorOnayAkisiGeregiGeldigiGorme(kaldirilacakKlasor,geregi,onayAkisi);

        evrakDetayiPage
                .ekleriTabAc()
                .eklenenDosyaninGeldigiGorulur(icerik);

        evrakDetayiPage
                .ilgileriTabAc()
                .cevapYazilanEvrakBilgisiGeldigiGorme();

    }
}
