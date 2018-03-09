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

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.switchTo;

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
    public void beforeTests(Method method) {

        login();
//test deneme
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1516: Gerçek kişi tanımlama ve kontrolü")
    public void TS1516() throws InterruptedException {

        String TCNO = createMernisTCKN();
        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String adSoyad = ad + " " + soyad;
        String hitap = "Sayın";
        String postaTipi = "Adi Posta";
        String bilgiSecimTipi = "Gerçek Kişi";
        String geregiSecimTipi = "Gerçek Kişi";
        String evrakBilgileriListKisiKurumTipi = "Gerçek Kişi";
        String gercekKisiMesaj = "Seçtiğiniz gerçek kişi gereği/bilgi listesinde ekli olduğu için bu gerçek kişiyi seçemezsiniz.";
        String basariMesaji = "İşlem başarılıdır!";

        gercekKisiYonetimPage
                .openPage()
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(TCNO)
                .onEkDoldur(onEk)
                .unvanDoldur(unvan)
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiUlkeDoldur(ulke)
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
                .geregiDoldur(adSoyad, "Ad Soyad")
                .gercekKisiGeregiAlaniKontrol(adSoyad, unvan, adres, postaTipi);

        evrakOlusturPage
                .editorTabAc()
                .hitapAlanindaSayinOnAdAdSoyadKontrol(hitap, onEk, ad, soyad);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgiSecimTipiSecByText(bilgiSecimTipi)
                .manuelBilgiDoldur(adSoyad)
                .islemMesaji().dikkatOlmali(gercekKisiMesaj);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText(evrakBilgileriListKisiKurumTipi)
                .geldigiGercekKisiDoldur(adSoyad, "Ad Soyad");

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1144: Gerçek kişi sorgulama")
    public void TS1144() throws InterruptedException {

        String TCNO = "91057625780";
        String TS10 = "10";
        String ad = "OptiimTest";
        String soyad = "TestOptiim";

        gercekKisiYonetimPage
                .openPage()
                //.ara()
                //.filtreSorgulamaPaneliAc()
                .defaultDurumComboKontrol("Sadece Aktifler")
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(TCNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(TCNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TS10)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .tcNoKontrolu(TS10)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCNO)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifKisiKayitKontrolu(TCNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(TCNO)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerListesiKayitKontrolu();

        String getTbleTCNO = gercekKisiYonetimPage.getTbleTCNO();

        gercekKisiYonetimPage
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .filtreTCKimlikNoDoldur(getTbleTCNO)
                .ara()
                .tcNoKontrolu(getTbleTCNO);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1135: Yeni gerçek kişi kayıtta alan kontrolleri")
    public void TS1135() {

        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String duzgunOlmayanPostaFormati = "optimtestturksat.com.tr";
        String duzgunPostaFormati = "optim@turksat.com.tr";
        String duzgunOlmayanKepAdresi = "tr6787";
        String duzgunKepAdresi = "turksat.kamu2@testkep.pttkep.gov.tr";
        String kepHizmetSaglayici = "PTT KEP Servisi";
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
//                .islemMesaji().basariliOlmali(basariMesaji); //kaydet tıklanmadığı için çıkmıyor.

        gercekKisiYonetimPage
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .kepAdresiKullaniyor(true)
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1137: Gerçek Kişi Bilgisi Güncelleme ve kontrolleri")
    public void TS1137() throws InterruptedException {

        String TCNO = createMernisTCKN();
        String ad = createRandomText(6);
        String soyad = createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String basariMesaji = "İşlem başarılıdır!";

        String TCNO2 = createMernisTCKN();
        String ad2 = createRandomText(6);
        String soyad2 = createRandomText(6);
        String onEk2 = "Dr";
        String unvan2 = "Doktor";
        String duzgunKepAdresi = "turksat.kamu2@testkep.pttkep.gov.tr";
        String adSoyad2 = ad2 + " " + soyad2;
        String postaTipi = "KEP"; //Z=KEP
        String hitap = "Sayın";

        //Test Datası yaratmak için bu adımlar yazıldı.
        gercekKisiYonetimPage
                .openPage()
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(TCNO)
                .onEkDoldur(onEk)
                .unvanDoldur(unvan)
                .adDoldur(ad)
                .soyadDoldur(soyad)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiUlkeDoldur(ulke)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(eposta)
                .iletisimBilgisiKaydet()

                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .filtreAdDoldur(ad)
                .filtreTCKimlikNoDoldur(TCNO)
                .ara()
                .aktifKisiKayitKontrolu(TCNO, ad, soyad)

                .gercekKisiGuncelle()
                .tcKimlikNoDoldur(TCNO2)
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
                .geregiDoldur(adSoyad2, "Ad Soyad")
                .gercekKisiGeregiAlaniKontrol(adSoyad2, unvan2, adres, postaTipi);

        evrakOlusturPage
                .editorTabAc()
                .hitapAlanindaSayinOnAdAdSoyadKontrol(hitap, onEk2, ad2, soyad2);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Gerçek Kişi")
                .geldigiGercekKisiDoldur(adSoyad2, "Ad Soyad");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1119: Gerçek Kişi iletişim bilgilerinin değiştirilmesi")
    public void TS1119() throws InterruptedException {

        String TCKN1 = "54548785445";
        String ad = "Ts1119";
        String soyad = "GerçekKişi";
        String TCKN2 = "69848836158"; //TCKN2 = "69848836158" kullanıcının adresi
        String adSoyad = ad + " " + soyad;
        String unvan = "Mühendis";
        String telNo = "539" + createRandomNumber(7);
        String isTelNo = "539" + createRandomNumber(7);
        String faxNo = "212" + createRandomNumber(7);
        String adres = createRandomText(7) + " " + "Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İstanbul";
        String ilce = "Şişli";
        String postaTipi = "Adi Posta";
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
                .iletisimBilgisiUlkeDoldur(ulke)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(ePosta)
                .iletisimBilgisiWebAdresiDoldur(webAdres)
                .iletisimBilgisiKaydet()
                .islemMesaji()
                .basariliOlmali(basariMesaji);

        gercekKisiYonetimPage
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiDoldur(TCKN1, "TCKN")
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
                .dagitimHitapDuzenlemeKaydet();

        evrakOlusturPage
                .pdfOnIzleme();
        switchTo().window(1);

        evrakOlusturPage
                .pdfKontrol
                .geregiBilgiAlaniAdresPdfKontrol(birinciKullaniciGeregiAdresi, getIkinciKullaniciAdres);

        closeNewWindow();
        switchTo().window(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1132a: Gerçek kişinin pasif yapılması ve ekranlardan kontrolü")
    public void TS1132a() throws InterruptedException {

        String TCNO = "43534543543";
        String ad = "Ts1132";
        String soyad = "GerçekKişi";
        String adSoyad = ad + " " + soyad;

        gercekKisiYonetimPage
                .openPage()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("TUMU")
                .ara()
                .gercekKisiPasifIseAktifYap()
                .aktifKisiKayitKontrolu(TCNO, ad, soyad)

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
                .pasifKisiKayitKontrolu(TCNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("TUMU")
                .ara()
                .pasifKisiKayitKontrolu(TCNO, ad, soyad);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Gerçek Kişi")
                .geldigiGercekKisiGoruntulenmemeKontrolu(adSoyad);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(adSoyad, "Gerçek Kişi")

                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(adSoyad, "Gerçek Kişi");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Gerçek Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(adSoyad, "Gerçek Kişi")

                .bilgiSecimTipiSecByText("Gerçek Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(adSoyad, "Gerçek Kişi");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1458a: Pasif yapılan gerçek kişinin aktif yapılması ve ekranlardan kontrolü")
    public void TS1458a() throws InterruptedException {

        String TCKN = "21861197500";
        String ad = "Ts1458";
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
