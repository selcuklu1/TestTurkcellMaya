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
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import tests.KisiselIslemlerBagTipi.KisiselIslemlerBagTipiTest;

import static common.PreCondition.TS2141;
import static data.TestData.*;

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

    @BeforeMethod
    public void loginBeforeTests() {
        reusableSteps = new ReusableSteps();
        beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        cevapYazPage = new CevapYazPage();
        evrakOlusturPage = new EvrakOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS1597: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (içerik ekranından)")
    public void TS1597() {
        String konuKodu = "TS1597-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE BİRİM";

        login(usernameYAKYOL,passwordYAKYOL);

        TS2141();

        reusableSteps
                .beklemeyeAlinanlarEvrakOlustur(konuKodu,"Birim",kurum,"Paraflama","Zübeyde Tekin","BHUPGMY","İmzalama",usernameZTEKIN,passwordZTEKIN);

        beklemeyeAlinanlarPage
                .openPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true,description = "TS2184: Cevap evrakın kopyalanması")
    public void TS2184() {

        String konuKodu = "TS2184-" + createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Zübeyde Tekin";
        String kaldirilacakKlasor = "Diğer";
        String geregi = "Optiim Birim";
        String basariMesaji = "İşlem başarılıdır!";
        String icerik = createRandomText(15);

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
                .onayAkisiDoldur("TS2172")
                .kaydet()
                .evrakKayitPopupEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik);

    }
}
