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
import pages.solMenuPages.BirimHavaleEdilenlerPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.HaveleOnayinaSunduklarimPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
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

    @BeforeMethod
    public void loginBeforeTests() {
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        haveleOnayinaSunduklarimPage = new HaveleOnayinaSunduklarimPage();
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
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur()
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandom)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur("Optiim Birim","YGD")
                .teslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameOPTIIM,passwordOPTIIM);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom,evrakTarihi,kurum);

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
                .evrakGeldigiGorunur(konuKoduRandom,evrakTarihi,kurum);

        login(usernameOPTIIM,passwordOPTIIM);

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
        String evrakSayiSag =  createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
        String not = createRandomText(15);
        login(usernameZTEKIN, passwordZTEKIN);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakIcerikGosterSec(konuKoduRandom)
                .teslimAlVeHavaleEt()
                .evrakDetayTeslimAlVeHavaleEtBirimDoldur("Optiim Birim","YGD")
                .evrakDetayTeslimAlVeHavaleEtSecilenBirimBilgiSec()
                .evrakDetayTeslimAlVeHavaleEtKisiDoldur("Optiim TEST","YGD")
                .evrakDetayTeslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konuKoduRandom);

        login(usernameOPTIIM,passwordOPTIIM);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandom,evrakTarihi,kurum);

        login(usernameOPTIIM, passwordOPTIIM);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandom);
    }

    String basariMesaji = "İşlem başarılıdır!";
    String konuKodu = "Diğer";
    String konuKoduRandomTS2285 = "TC-2285_" + createRandomNumber(15);
    String randomKonuKodu2TS2285 = createRandomNumber(15)+"-TS-2285";
    String evrakSayiSag =  createRandomNumber(10);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
    String not = createRandomText(15);
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
                .havaleIslemleriBirimDoldur(kullaniciAdi)
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
                .havaleIslemleriBirimDoldur(kullaniciAdi)
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
                .secilenOnaylayacakKisiDoldur("Can Şeker")
                .secilenHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        haveleOnayinaSunduklarimPage
                .openPage()
                .evrakNoIleEvrakIcerikGosterSec(konuKoduRandomTS2285)
                .icerikGosterHavaleBilgisi()
                .birimVeKisiBilgilerinBosOlarakGeldigiGorme();
    }

    String konuKoduRandomTS2294 = "TC-2294_" + createRandomNumber(15);

    @Step("Teslim alınmayı bekleyenler alanına evrak düşürmekte")
    public void TS2294PreCondition() {

        login(usernameZTEKIN, passwordZTEKIN);

        //TODO Bu alanda Pre Condition alanı olan teslim alınmayı bekleyenler alanına data oluşturmakta
        //1.Teslim Alınmayı Bekleyenler
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandomTS2294)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2294: Havale yeri Birim, Kişi, Kullanıcı Listesi seçilerek evrakın havale edilmesi")
    public void TS2294() {

        TS2294PreCondition();
        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2294)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ","")
                .teslimAlVeHavaleEtKisiDoldur("Mehmet BOZDEMİR","YGD")
                .kisiGeregiIcinGonderBilgiIcinGonderDegistir()
                .teslimAlVeHavaleEtKullaniciListesiDoldur("TS2994","TS2994")
                .kullaniciListesiGeregiIcinGonderKordinasyonIcinGonderDegistir()
                .teslimAlVeGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konuKoduRandomTS2294);

        login(usernameYAKYOL,passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakGeldigiGorunur(konuKoduRandomTS2294,evrakTarihi,kurum);

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS2294);

        login(usernameYAKYOL,passwordYAKYOL);

        gelenEvraklarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandomTS2294);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2300: Evrakın Onaylı havale edilmesi ve güncellenerek onaylanması")
    public void TS2300() {
        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";
        String kisi = "Zübeyde Tekin";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR Y";
        String testid= "TS-405";
        String konu = "TS-405-" + getSysDate();
        String pathToFilePdf = getUploadPath() + "Otomasyon.pdf";
        String pdfName = "Otomasyon.pdf";

        login(usernameYAKYOL,passwordYAKYOL);

        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriUstYaziEkle(pathToFilePdf)
                .ustYaziPdfAdiKontrol(pdfName)
                .islemMesaji().basariliOlmali();

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
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .popUps();
    }




}
