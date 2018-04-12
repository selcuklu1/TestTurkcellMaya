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
import pages.pageComponents.EvrakOnizleme;
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
    EvrakOnizleme evrakOnizleme;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
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
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleEdilenEvrakRaporuPage = new HavaleEdilenEvrakRaporuPage();
        evrakOnizleme = new EvrakOnizleme();
    }

    @Step("Havale onayına gelenler sayfasına evrak düşürmektedir.")
    public void TS1597PreCondition() {

        login(userZtekin);

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKoduRandomTS1597,kurum,birim);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSecNoTeslimAl(konuKoduRandomTS1597, true);
    }

    @Step("Teslim Alınanlar sayfasına evrak düşürmektedir.")
    public void TS446PreCondition() {

        login(userZtekin);

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKoduRandomTS1597,kurum,birim);

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
                .havaleYap();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        teslimAlinanlarPage
                .havaleYapKullaniciListesiSecmeyeDene("TS1590")
                .havaleYapBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .secilenBirimDefaultGeregiIcinGonderGorme()
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakBilgileriIleEvragıGeldigiGorme(konuKoduRandomTS1597,"Büyük Harflerle Kurum","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ",evrakTarihi);

        login(userYakyol);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS1597,evrakTarihi,"Büyük Harflerle Kurum","(G)");

        login(userMbozdemir);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS1597,evrakTarihi,"Büyük Harflerle Kurum");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1590: Kullanıcı listesinden hariç tutarak evrak havale etme (önizleme ekranından)")
    public void TS1590() {

        TS446PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .havaleYap();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        teslimAlinanlarPage
                .havaleYapKullaniciListesiSecmeyeDene("TS1590")
                .havaleYapKullaniciListesiSecGeregiIcinGonderGeldigiGorme()
                .kisiListesiSecilenGuncelle()
                .kisiListesiSecilenGrupDetaySeciliGeldigiGorme()
                .grupDetayKullaniciIsaretKaldir()
                .kullaniciGrupDetayKullan()
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakBilgileriIleEvragıGeldigiGorme(konuKoduRandomTS1597,"Yasemin Çakıl Akyol",evrakTarihi);

        login(userYakyol);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS1597,kurum,evrakTarihi);

        login(userMbozdemir);

        gelenEvraklarPage
                .openPage()
                .evrakNoGelmedigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0446: Birime Evrak Havale ve Havaleyi İade Etme")
    public void TS0446() {

        TS446PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .evrakOnizlemeKontrol()
                .havaleYap()
                .havaleYapEkranGeldigiGorulur()
                .havaleYapBirimDoldur(birim)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(userYakyol);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .evrakOnizlemeGeldigiGorme(true)
                .iadeEt()
                .iadeEtnotVeDosyaEkleGeldigiGorme(true,true)
                .iadeEtNotDoldur(not)
                .iadeEtIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .evrakNoGelmedigiGorme(konuKoduRandomTS1597);

        login(userZtekin);

        birimeIadeEdilenlerPage
                .openPage()
                .evrakNoGeldigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0448: Evrakı havale onayına sunma")
    public void TS0448() {

        TS446PreCondition();

        teslimAlinanlarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .evrakOnizlemeKontrol()
                .havaleYap()
                .havaleYapEkranGeldigiGorulur()
                .havaleYapOnaylanacakKisiDoldur(kisi, birim2)
                .havaleYaphavaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS1597)
                .geriAlSec()
                .geriAlNotGerialGeldigiGorme(true,true)
                .geriAlNotDoldur(not)
                .geriAlGeriAl();

        login(userMbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakGelmedigiGorme(konuKoduRandomTS1597);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1591b: Teslim aldıklarım listesinden yapılan havalenin Havale Edilen Evrak Raporundan kontrolü")
    public void TS1591b() {

        login(userZtekin);

        havaleEdilenEvrakRaporuPage
                .openPage()
                .alanlarGeldigiGorme(true,true,true,true,true,true,true)
                .havaleEdilenBirimDoldur(birim)
                .sorgula()
                .kayitlarinListelendigiGorme()
                .havaleEdilenKullaniciDoldur("Can Şeker")
                .sorgula()
                .kayitlarinListelendigiGorme()
                .havaleTarihAraligiBaslangicDoldur("13.02.2018")
                .sorgula()
                .kayitlarinListelendigiGorme()
                .havaleTarihAraligiBitisDoldur("13.02.2018")
                .sorgula()
                .kayitlarinListelendigiGorme()
                .evrakDetaySec("TC-1597_1121310802619547311")
                .evrakDetayGeldigGorme();

    }
}
