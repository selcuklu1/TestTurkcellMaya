/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.TeslimAlinmayiBekleyenHavale;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-05
 * Proje: Türksat Functional Test Automation
 * Class: "Teslim Alınmayı Bekleyen Havale" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Teslim Alınmayı Bekleyen Havale")
public class TeslimAlinmayiBekleyenHavaleTest extends BaseTest {

    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    GelenEvraklarPage gelenEvraklarPage;
    HaveleOnayinaSunduklarimPage haveleOnayinaSunduklarimPage;
    BirimIadeEdilenlerPage birimIadeEdilenlerPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerimPage;
    String basariMesaji = "İşlem başarılıdır!";
    String konuKodu = "Diğer";
    String konuKoduRandomTS2285 = "TC-2285_" + createRandomNumber(15);
    String randomKonuKodu2TS2285 = createRandomNumber(15) + "-TS-2285";
    String evrakSayiSag = createRandomNumber(10);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String birim = "Yazılım Geliştirme Direktörlüğ";
    String not = createRandomText(15);
    String konuKoduRandomTS2294 = "TC-2294_" + createRandomNumber(15);
    String konuKoduRandomTS2300 = "TC-2300_" + createRandomNumber(15);

    @BeforeMethod
    public void loginBeforeTests() {
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        haveleOnayinaSunduklarimPage = new HaveleOnayinaSunduklarimPage();
        birimIadeEdilenlerPage = new BirimIadeEdilenlerPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleOnayiVerdiklerimPage = new HavaleOnayiVerdiklerimPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0443: Birime Evrak Havale (önizleme ekranından) ve Havaleyi Geri Alma")
    public void TS0443() {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-0443_" + createRandomNumber(25);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
        String not = createRandomText(15);
        login(usernameZTEKIN, passwordZTEKIN);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        gelenEvrakKayitPage
                .gelenEvrakKayitBirimHavaleEt(konuKoduRandom,kurum,kullaniciAdi);
        //TODO

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandom)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur("Optiim Birim", "YGD")
                .teslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameOPTIIM, passwordOPTIIM);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom, evrakTarihi, kurum);

        login(usernameZTEKIN, passwordZTEKIN);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konuKoduRandom)
                .geriAl()
                .geriAlNotAlaniniDoldur(not)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom, evrakTarihi, kurum);

        login(usernameOPTIIM, passwordOPTIIM);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoGelmedigiGorme(konuKoduRandom);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0453: Gereği için birim bilgi için kişi seçilerek evrak havale etme (içerik ekranından)")
    public void TS0453() {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-0443_" + createRandomNumber(15);
        String evrakSayiSag = createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Yazılım Geliştirme Direktörlüğ";
        String not = createRandomText(15);
        login(usernameZTEKIN, passwordZTEKIN);

        gelenEvrakKayitPage
                .gelenEvrakKayitBirimHavaleEt(konuKoduRandom,kurum,birim);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakIcerikGosterSec(konuKoduRandom)
                .teslimAlVeHavaleEt()
                .evrakDetayTeslimAlVeHavaleEtBirimDoldur("Optiim Birim", "YGD")
                .evrakDetayTeslimAlVeHavaleEtSecilenBirimBilgiSec()
                .evrakDetayTeslimAlVeHavaleEtKisiDoldur("Optiim TEST", "YGD")
                .evrakDetayTeslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konuKoduRandom);

        login(usernameOPTIIM, passwordOPTIIM);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom, evrakTarihi, kurum);

        login(usernameOPTIIM, passwordOPTIIM);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandom);
    }

    @Step("Teslim alınmayı bekleyenler alanına evrak düşürmekte")
    public void TS2285PreCondition() {

        login(usernameZTEKIN, passwordZTEKIN);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        //1.Teslim Alınmayı Bekleyenler
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandomTS2285)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();

        //2.Teslim Alınmayı Bekleyenler
        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(randomKonuKodu2TS2285)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .evetDugmesi()
                .benzerKaydet()
                .yeniKayitButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2285: Kişi seçimi yapmadan toplu havaleyi onaya sunma")
    public void TS2285() {

        TS2285PreCondition();
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakCheckboxSec(konuKoduRandomTS2285)
                .evrakNoIleEvrakCheckboxSec(randomKonuKodu2TS2285)
                .secilenTeslimAlVeHavaleEt()
                .secilenOnaylayacakKisiDoldur("Mehmet BOZDEMİR","BHUPGMY")
                .secilenHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        haveleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakIcerikGosterSec(konuKoduRandomTS2285)
                .icerikGosterHavaleBilgisi()
                .birimVeKisiBilgilerinBosOlarakGeldigiGorme();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2294: Havale yeri Birim, Kişi, Kullanıcı Listesi seçilerek evrakın havale edilmesi")
    public void TS2294() {

        login(usernameZTEKIN, passwordZTEKIN);

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKoduRandomTS2294,kurum, birim);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2294)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "")
                .teslimAlVeHavaleEtKisiDoldur("Mehmet BOZDEMİR", "YGD")
                .kisiGeregiIcinGonderBilgiIcinGonderDegistir()
                .teslimAlVeHavaleEtKullaniciListesiDoldur("TS2994", "TS2994")
                .kullaniciListesiGeregiIcinGonderKordinasyonIcinGonderDegistir()
                .teslimAlVeGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konuKoduRandomTS2294);

        login(usernameYAKYOL, passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS2294, evrakTarihi, kurum);

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS2294);

        login(usernameYAKYOL, passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS2294);
    }

    @Step("Havale onayına gelenler sayfasına evrak düşürmektedir.")
    public void TS2300PreCondition() {

        login(usernameZTEKIN, passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKoduRandomTS2300,kurum,birim);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2300)
                .iadeEt()
                .iadeEtIadeEt();
        birimIadeEdilenlerPage
                .openPage()
                .evrakSec(konuKoduRandomTS2300)
                .teslimAlVeHavaleEt()
                .onaylanacakKisiDoldur("Mehmet Bozdemir", "YGD")
                .havaleOnayinaGonder();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2300: Evrakın Onaylı havale edilmesi ve güncellenerek onaylanması")
    public void TS2300() {
        String kisi = "Mehmet Bozdemir";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        TS2300PreCondition();

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2300)
                .havaleOnayi()
                .havaleOnayiBirimDoldur(birim)
                .havaleOnayinaBirimGeregiIcinBilgiIcinSec()
                .havaleOnayiKisiDoldur(kisi, "YGD")
                .havaleOnayiOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .havaleyiOnaylamakUzeresinizEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayiVerdiklerimPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS2300)
                .evrakNoIleEvrakSec(konuKoduRandomTS2300)
                .evrakSecEvrakGecmisiSec()
                .evrakGecimisiGeregiVeBilgiGeldigiGorme("Gereği", kisi, "Bilgi", birim);

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleEvragıGeldigiGorme(konuKoduRandomTS2300);

        login(usernameYAKYOL, passwordYAKYOL);
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS2300);
    }


}
