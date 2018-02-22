/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.TeslimAldiklarimHavale;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.HavaleEdilenEvrakRaporuPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "Teslim Aldıklarım Havale" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Teslim Aldıklarım Havale")
public class TeslimAldiklarimHavaleTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleEdilenEvrakRaporuPage havaleEdilenEvrakRaporuPage;
    String basariMesaji = "İşlem başarılıdır!";
    String konuKodu = "Diğer";
    String evrakSayiSag = createRandomNumber(10);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String birim = "Yazılım Geliştirme Direktörlüğ";
    String kisi = "Mehmet Bozdemir";
    String birim2 = "YGD";
    String not = createRandomText(15);
    String konuKoduRandomTS1597 = "TC-1597_" + createRandomNumber(15);

    @BeforeMethod
    public void loginBeforeTests() {
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleEdilenEvrakRaporuPage = new HavaleEdilenEvrakRaporuPage();
    }

    @Step("Havale onayına gelenler sayfasına evrak düşürmektedir.")
    public void TS1597PreCondition() {

        login(usernameZTEKIN, passwordZTEKIN);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        //1.Teslim Alınmayı Bekleyenler
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandomTS1597)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecNoTeslimAl(konuKoduRandomTS1597, true);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1597: Havale onayı bekleyen evrakın geri çekilmesi ve tekrar havalesi (içerik ekranından)")
    public void TS1597() {

        TS1597PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .havaleYap()
                .havaleYapKullaniciListesiSecmeyeDene("TS1590")
                .havaleYapBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleEvragıGeldigiGorme(konuKoduRandomTS1597);

        login(usernameYAKYOL, passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS1597);

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1590: Kullanıcı listesinden hariç tutarak evrak havale etme (önizleme ekranından)")
    public void TS1590() {

        TS1597PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .havaleYap()
                .havaleYapKullaniciListesiSecmeyeDene("TS1590")
                .kisiListesiSecilenGuncelle()
                .kisiListesiSecilenGrupDetaySeciliGeldigiGorme()
                .grupDetayKullaniciIsaretKaldir()
                .kullaniciGrupDetayKullan()
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleEvragıGeldigiGorme(konuKoduRandomTS1597);

        login(usernameYAKYOL, passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS1597);

        login(usernameMBOZDEMIR, passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0446: Birime Evrak Havale ve Havaleyi İade Etme")
    public void TS0446() {

        TS1597PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .havaleYap()
                .havaleYapBirimDoldur(birim)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameYAKYOL, passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .iadeEt()
                .iadeEtNotDoldur(not)
                .iadeEtIadeEt();

        login(usernameZTEKIN, passwordZTEKIN);

        birimIadeEdilenlerPage
                .openPage()
                .evrakNoGeldigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0448: Evrakı havale onayına sunma")
    public void TS0448() {

        TS1597PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .havaleYap()
                .havaleYapOnaylanacakKisiDoldur(kisi, birim2)
                .havaleYaphavaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .geriAlSec()
                .geriAlNotDoldur(not)
                .geriAlGeriAl();

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakGelmedigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1591: Teslim aldıklarım listesinden yapılan havalenin Havale Edilen Evrak Raporundan kontrolü")
    public void TS1591() {

        login(usernameZTEKIN, passwordZTEKIN);

        havaleEdilenEvrakRaporuPage
                .openPage()
                .havaleEdilenBirimDoldur(birim)
                .sorgula()
                .havaleEdilenKullaniciDoldur("Can Şeker")
                .sorgula()
                .havaleTarihAraligiBaslangicDoldur("13.02.2018")
                .sorgula()
                .havaleTarihAraligiBitisDoldur("13.02.2018")
                .sorgula()
                .evrakDetaySec("TC-1597_1121310802619547311")
                .evrakDetayGeldigGorme();

    }
}
