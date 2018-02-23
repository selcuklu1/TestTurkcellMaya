/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.EvrakKopyalama;

import common.BaseLibrary;
import common.BaseTest;
import common.ReusableSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.CevapYazPage;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import tests.KisiselIslemlerBagTipi.KisiselIslemlerBagTipiTest;
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
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS1597: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (içerik ekranından)")
    public void TS2175() {

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

        TS1082();

        evrakOlusturPage
                .evrakOlusturBirimIcerikKullanParafla(konuKodu,"Kurum",kurum,"Paraflama","Zübeyde Tekin","BHUPGMY","İmzalama",sablonAdi1082);

        parafladiklarimPage
                .openPage()
                ;

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
