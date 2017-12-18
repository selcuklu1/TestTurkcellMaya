package tests.GercekKisiYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GercekKisiYonetimPage;
import pages.ustMenuPages.GidenEvrakKayitPage;

import java.awt.*;

/****************************************************
 * Tarih: 2017-11-20
 * Proje: Türksat Functional Test Automation
 * Class: "Gerçek Kişi Yönetimi " konulu senaryoları içerir
 * Yazan: Sezai Çelik
 ****************************************************/
public class GercekKisiYonetimiTest extends BaseTest {

    GercekKisiYonetimPage gercekKisiYonetimPage;
    EvrakOlusturPage evrakOlusturPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1516: Gerçek kişi tanımlama ve kontrolü")
    public void TC1516() throws InterruptedException {

        String tcNO = createMernisTCKN();
        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String adSoyad = ad + " " + soyad;
        String hitap = "Sayın";
        String postaTipi = "P";
        String bilgiSecimTipi = "Gerçek Kişi";
        String geregiSecimTipi = "Gerçek Kişi";
        String evrakBilgileriListKisiKurumTipi = "G";
        String gercekKisiMesaj = "Seçtiğiniz gerçek kişi gereği / bilgi listesinde ekli olduğu için bu gerçek kişiyi seçemezsiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        gercekKisiYonetimPage
                .openPage()
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(tcNO)
                .onEkDoldur(onEk)
                .unvanDoldur(unvan)
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(eposta)
                .iletisimBilgisiKaydet()

                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText(geregiSecimTipi)
                .geregiDoldur(adSoyad)
                .gercekKisiGeregiAlaniKontrol(adSoyad, unvan, adres, postaTipi);

        evrakOlusturPage
                .editorTabAc()
                .hitapAlaniUnvanAdSoyadKontrol(hitap, onEk, ad, soyad);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgiSecimTipiSecByText(bilgiSecimTipi)
                .manuelBilgiDoldur(adSoyad)
                .islemMesaji().dikkatOlmali(gercekKisiMesaj);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec(evrakBilgileriListKisiKurumTipi)
                .geldigiGercekKisiDoldur(adSoyad);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1144: Gerçek kişi sorgulama")
    public void TC1144() throws InterruptedException {

        String tcNO = "91057625780";
        String tc10 = "10";
        String ad = "OptiimTest";
        String soyad = "TestOptiim";

        gercekKisiYonetimPage
                .openPage()
                .ara()
                .filtreSorgulamaPaneliAc()
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("AKTIFLER")
                .ara() //araButonuTikla
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tc10)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .tcNoKontrolu(tc10)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tcNO)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tcNO)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerKayitKontrolu();

        String getTbleTC = gercekKisiYonetimPage.getTbleTCNO();

        gercekKisiYonetimPage
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .filtreTCKimlikNoDoldur(getTbleTC)
                .ara()
                .tcNoKontrolu(getTbleTC);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1135: Yeni gerçek kişi kayıtta alan kontrolleri")
    public void TC1135() throws AWTException {

        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String duzgunOlmayanPostaFormati = "optimtestturksat.com.tr";
        String duzgunPostaFormati = "optim@turksat.com.tr";
        String duzgunOlmayanKepAdresi = "tr6787";
        String duzgunKepAdresi = "turksat.kamu2@testkep.pttkep.gov.tr";
        String kepHizmetSaglayici = "P";
        String kepMesaj1 = "Kep adresi boş bırakılamaz! Lütfen bir kep adresi ekleyiniz.";
        String ePostaMesaj = "Lütfen Türkçe karakter ve boşluk içermeyen, @ işareti ve nokta içeren geçerli bir e-mail giriniz!";
        String kepMesaj2 = "Girilen kep adresi geçersiz!";
        String basariMesaji = "İşlem başarılıdır!";

        gercekKisiYonetimPage.openPage()
                .yeniGercekKisiEkle()
                .kaydet()
                .zorunluAdSoyadAlanKontrolu()

                .adDoldur(ad)
                .soyadDoldur(soyad)
                .kepAdresiKullaniyor(true)
                .kaydet()
                .islemMesaji().dikkatOlmali(kepMesaj1);

        gercekKisiYonetimPage
                .iletisimBilgileriEkle()
                .iletisimBilgisiKaydet()
                .islemMesaji().dikkatOlmali(ePostaMesaj);

        gercekKisiYonetimPage
                .iletisimBilgisiEpostaDoldur(duzgunOlmayanPostaFormati)
                .iletisimBilgisiKaydet()
                .islemMesaji().dikkatOlmali(ePostaMesaj);

        gercekKisiYonetimPage
                .iletisimBilgisiEpostaDoldur(duzgunPostaFormati)
                .iletisimBilgisiKaydet()
                .iletisimBilgisiGüncelle()
                .iletisimBilgisiIptalEt()

                .kepAdresBilgileriEkle()
                .kepAdresiKaydet()
                .islemMesaji().dikkatOlmali(kepMesaj2);

        gercekKisiYonetimPage
                .kepAdresiDoldur(duzgunOlmayanKepAdresi)
                .kepAdresiKaydet()
                .islemMesaji().dikkatOlmali(kepMesaj2);

        gercekKisiYonetimPage
                .kepAdresiDoldur(duzgunKepAdresi)
                .kepHizmetSaglayiciSec(kepHizmetSaglayici)
                .kepAdresiKaydet();
//                .islemMesaji().basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .kepAdresiKullaniyor(true)
                .kaydet2()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1137: Gerçek Kişi Bilgisi Güncelleme ve kontrolleri")
    public void TC1137() throws InterruptedException {

        String tcNO = createMernisTCKN();
        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String basariMesaji = "İşlem başarılıdır!";

        String tcNO2 = createMernisTCKN();
        String ad2 = createRandomText(6);
        String soyad2 = createRandomText(6);
        String onEk2 = "Dr";
        String unvan2 = "Doktor";
        String duzgunKepAdresi = "turksat.kamu2@testkep.pttkep.gov.tr";
        String adSoyad2 = ad2 + " " + soyad2;
        String postaTipi = "Z"; //Z=KEP
        String hitap = "Sayın";

        //tests.Data yaratmak için.
        gercekKisiYonetimPage
                .openPage()
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(tcNO)
                .onEkDoldur(onEk)
                .unvanDoldur(unvan)
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(eposta)
                .iletisimBilgisiKaydet()

                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .filtreAdDoldur(ad)
                .filtreTCKimlikNoDoldur(tcNO)
                .ara()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .gercekKisiGuncelle()
                .tcKimlikNoDoldur(tcNO2)
                .onEkDoldur(onEk2)
                .unvanDoldur(unvan2)
                .adDoldur(ad2)
                .soyadDoldur(soyad2)

                .kepAdresiKullaniyor(true)
                .kepAdresBilgileriEkle()
                .kepAdresiDoldur(duzgunKepAdresi)
                .kepAdresiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiDoldur(adSoyad2)
                .gercekKisiGeregiAlaniKontrol(adSoyad2, unvan2, adres, postaTipi);

        evrakOlusturPage
                .editorTabAc()
                .hitapAlaniUnvanAdSoyadKontrol(hitap, onEk2, ad2, soyad2);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Gerçek Kişi")
                .geldigiGercekKisiDoldur(adSoyad2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1119: Gerçek Kişi iletişim bilgilerinin değiştirilmesi")
    public void TC1119() throws InterruptedException {

        String TCKN1 = "54548785445";
        String ad = "TC1119";
        String soyad = "GerçekKişi";
        String TCKN2 = "69848836158"; //TCKN2 = "69848836158" kullanıcının adresi
        String adSoyad = ad + " " + soyad;
        String unvan = "Mühendis";
        String telNo = "539" + createRandomNumber(7);
        String isTelNo = "539" + createRandomNumber(7);
        String faxNo = "212" + createRandomNumber(7);
        String adres = createRandomText(7) + " " + "Mahallesi";
        String il = "İstanbul";
        String ilce = "Şişli";
        String postaTipi = "P";
        String ePosta = createRandomText(5) + "@turksat.com.tr";
        String webAdres = "http://www.belgenet.com.tr/";
        String basariMesaji = "İşlem başarılıdır!";

        String birinciKullaniciGeregiAdresi = adres + ilce + "/" + il;

        //TCKN2 = "69848836158" kullanıcının adresi
        String ikinciKullaniciBilgiAdresi = "Gültepe Mahallesi KAĞITHANE / İSTANBUL";

        gercekKisiYonetimPage
                .openPage()
                .filtreTCKimlikNoDoldur(TCKN1)
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .ara()
                .aktifKisiKayitKontrolu(TCKN1, ad, soyad)

                .gercekKisiGuncelle()
                .unvanDoldur(unvan)
                .iletisimBilgisiGüncelle()

                .iletisimBilgisiMobilTelNoDoldur(telNo)
                .iletisimBilgisiTelefonNoDoldur(telNo)
                .iletisimBilgisiIsTelefonNoDoldur(isTelNo)
                .iletisimBilgisiFaxs1Doldur(faxNo)
                .iletisimBilgisiFaxs2Doldur(faxNo)

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(ePosta)
                .iletisimBilgisiWebAdresiDoldur(webAdres)
                .iletisimBilgisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .kaydet();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiDoldur(TCKN1)
                .gercekKisiGeregiAlaniKontrol(adSoyad, unvan, adres, postaTipi)

                .geregiAlaniGuncelle()
                .adresHitaptaGorunsunSec(true)
                .dagitimHitapDuzenlemeKaydet();

        evrakOlusturPage
                .editorTabAc()
                .hitapAlaniAdresKontrol(adres, ilce, il);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiDoldur(TCKN2)
                .bilgiAlaniGuncelle();

        String getIkinciKullaniciAdres = evrakOlusturPage.bilgilerTabiAc().getDagitimHitapAdres();

        evrakOlusturPage
                .bilgilerTabiAc()
                .adresDagitimdaGorunsunSec(true)
                .dagitimHitapDuzenlemeKaydet()
                .windowHandleBefore();

        evrakOlusturPage
                .pdfOnIzleme()
                .switchToNewWindow();

        evrakOlusturPage
                .pdfKontrol
                .geregiBilgiAlaniAdresPdfKontrol(birinciKullaniciGeregiAdresi, getIkinciKullaniciAdres)
                .switchToDefaultWindow();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1132: Gerçek kişinin pasif yapılması ve ekranlardan kontrolü")
    public void TC1132() throws InterruptedException {

        String tcNO = "43534543543";
        String ad = "TC1132";
        String soyad = "GerçekKişi";
        String adSoyad = ad + " " + soyad;

        gercekKisiYonetimPage
                .openPage()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("TUMU")
                .ara()
                .gercekKisiPasifIseAktifYap()
                .aktifKisiKayitKontrolu(tcNO, ad, soyad)

                .gercekKisiPasifYap()
                .islemOnayi("Evet");

        gercekKisiYonetimPage
                .pasiflerKayitKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifKisiKayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("TUMU")
                .ara()
                .pasifKisiKayitKontrolu(tcNO, ad, soyad);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Gerçek Kişi")
                .geldigiGercekKisiGoruntulenmemeKontrolu(adSoyad);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(adSoyad)

                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(adSoyad);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(adSoyad)

                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(adSoyad);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "1458: Pasif yapılan gerçek kişinin aktif yapılması ve ekranlardan kontrolü")
    public void TC1458() {

        String TCKN = "21861197500";
        String ad = "Tc1458";
        String soyad = "GerçekKişi";
        String adSoyad = ad + " " + soyad;

        gercekKisiYonetimPage
                .openPage()
                .filtreTCKimlikNoDoldur(TCKN)
                .filtreDurumSec("TUMU")
                .ara()
                .gercekKisiAktifIsePasifYap()

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCKN)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifKisiKayitKontrolu(TCKN, ad, soyad)
                .gercekKisiAktifYap()
                .islemOnayi("Evet");

        gercekKisiYonetimPage
                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCKN)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(TCKN, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCKN)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCKN)
                .filtreDurumSec("TUMU")
                .ara()
                .aktifKisiKayitKontrolu(TCKN, ad, soyad);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Gerçek Kişi")
                .gercekKisiGoruntulenmeKontrolu(TCKN, adSoyad);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmeKontrolu(adSoyad)
                .secilenGeregiSil()
                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmeKontrolu(ad, soyad);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmeKontrolu(adSoyad)
                .secilenGeregiSil()
                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmeKontrolu(ad, soyad);
    }
}
