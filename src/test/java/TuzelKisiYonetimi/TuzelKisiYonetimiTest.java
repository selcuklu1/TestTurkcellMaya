package TuzelKisiYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

/****************************************************
 * Tarih: 2017-11-24
 * Proje: Türksat Functional Test Automation
 * Class: "Tüzel Kişi Yönetimi" konulu metotları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class TuzelKisiYonetimiTest extends BaseTest {

    TuzelKisiYonetimiPage tuzelKisiYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;
    SikKullanilanlarPage sikKullanilanlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        sikKullanilanlarPage = new SikKullanilanlarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1124: Yeni tüzel kişi kayıt ve ekranlardan kontrolleri")
    public void TC1124() {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " holding";
        String adres = "Mecidiyeköy Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İst";
        String ilce = "Şiş";
        String eposta = kisaAd + "holding@turksat.com.tr";
        String webAdres = "www." + kisaAd + "holding.com";
        String telNo = "5391111111";
        String faksNo = "2121111111";
        String basariMesaji = "İşlem başarılıdır!";

        tuzelKisiYonetimiPage
                .openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec("12729")
                .vergiNoDoldur(vergiNo)
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)
                .yeniIletisimEkle()

                .mobilTelNoDoldur(telNo)
                .telNoDoldur(telNo)
                .faks1NoDoldur(faksNo)
                .faks2NoDoldur(faksNo)
                .adresDoldur(adres)
                .ulkeSec(ulke)
                .ilSec(il)
                .ilceSec(ilce)
                .ePostaDoldur(eposta)
                .webAdresDoldur(webAdres)
                .iletisimBilgisiKaydet()

                .tuzelKisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        tuzelKisiYonetimiPage
                .filtreVergiNoDoldur(vergiNo)
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .secilenGeregiSil()
                .geregiDoldur(kisaAd)
                .secilenGeregiSil()
                .geregiDoldur(vergiNo);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("T")
                .geldigiTuzelKisiDoldur(ad)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(kisaAd)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .secilenGeregiSil()
                .geregiDoldur(kisaAd)
                .secilenGeregiSil()
                .geregiDoldur(vergiNo);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1133: Tüzel kişi sorgulama")
    public void TC1133() {

        String vergiNo = "8524567913";
        String ad = "Türksat Optiim";
        String kisaAd = "trkstopttm";

        tuzelKisiYonetimiPage
                //Step num: 2
                .openPage()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktiflerTumListeKayitKontrolu()

                //Step num: 3
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasiflerTumListeKayitKontrolu()

                //Step num: 4
                //Data pasif ise, aktif yapılır.
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiPasifIseAktifYap()
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 6
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 8
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 10
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 5
                //Data aktif ise, pasif yapılır.
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiAktifIsePasifYap()
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 7
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                //Step num: 9
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)

                //Step num: 11
                .filtreSorgulamaPaneliAc()
                .filtreVergiNoDoldur(vergiNo)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1460: Yeni tüzel kişi kayıtta alan kontrolleri")
    public void TC1460() {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " holding";
        String adres = "Gültepe Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İstanbul";
        String ilce = "Kağıthane";
        String eposta = kisaAd + "holding@turksat.com.tr";
        String yanlisFormattaEPosta = "testholdingturksat.com.tr";
        String gecersizKepAdresi = "45454";

        String zorunluAlanUyariMesaji = "Zorunlu alanları doldurunuz";
        String kepDikkatMesaji = "Kep adresi boş bırakılamaz! Lütfen bir kep adresi ekleyiniz.";
        String gecersizKepAdresiDikkatMesaji = "Girilen kep adresi geçersiz!";
        String epostaDikkatMesaji = "Lütfen Türkçe karakter ve boşluk içermeyen, @ işareti ve nokta içeren geçerli bir e-mail giriniz!";

        tuzelKisiYonetimiPage
                .openPage()
                .yeniTuzelKisiEkle()
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)
                .tuzelKisiKaydet()
                .islemMesaji().uyariOlmali(zorunluAlanUyariMesaji);

        tuzelKisiYonetimiPage
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec("12729")
                .vergiNoDoldur(vergiNo)
                .tuzelKisiKaydet()
                .islemMesaji().uyariOlmali(zorunluAlanUyariMesaji);

        tuzelKisiYonetimiPage
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec("12729")
                .adDoldur(ad)
                .kepAdresiKullaniyorSec(true)
                .tuzelKisiKaydet()
                .islemMesaji().dikkatOlmali(kepDikkatMesaji);

        tuzelKisiYonetimiPage
                .yeniIletisimEkle()
                .iletisimBilgisiKaydet()
                .islemMesaji().uyariOlmali(zorunluAlanUyariMesaji);

        tuzelKisiYonetimiPage
                .adresDoldur(adres)
                .ulkeSec(ulke)
                .ilSec(il)
                .ilceSec(ilce)
                .ePostaDoldur(yanlisFormattaEPosta)
                .iletisimBilgisiKaydet()
                .islemMesaji().dikkatOlmali(epostaDikkatMesaji);

        tuzelKisiYonetimiPage
                .ePostaDoldur(eposta)
                .iletisimBilgisiKaydet();

        tuzelKisiYonetimiPage
                .kepAdresBilgileriEkle()
                .kepAdresiKaydet()
                .islemMesaji().uyariOlmali(zorunluAlanUyariMesaji);

        tuzelKisiYonetimiPage
                .kepAdresiDoldur(gecersizKepAdresi)
                .kepAdresiKaydet()
                .islemMesaji().dikkatOlmali(gecersizKepAdresiDikkatMesaji);

        tuzelKisiYonetimiPage
                .kepAdresiIptalet();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1126: Tüzel Kişi Bilgisi Güncelleme ve kontrolleri")
    public void TC1126() {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " İş Çözümleri";
        String vergiNo2 = createRandomNumber(10);
        String kisaAd2 = createRandomText(7);
        String ad2 = kisaAd2 + " İş Çözümleri";
        String kepAdresi = kisaAd + "@testkep.pttkep.gov.tr";
        String basariMesaji = "İşlem başarılıdır!";
        String postaTipi = "Z";

        tuzelKisiYonetimiPage

                //Data yaratmak için
                .openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec("22782")
                .vergiNoDoldur(vergiNo)
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)
                .tuzelKisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        tuzelKisiYonetimiPage
                .filtreVergiNoDoldur(vergiNo)
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)
                .tuzelKisiGuncelle()

                .vergiNoDoldur(vergiNo2)
                .adDoldur(ad2)
                .kisaAdDoldur(kisaAd2)
                .kepAdresiKullaniyorSec(true)
                .kepAdresBilgileriEkle()
                .kepAdresiDoldur(kepAdresi)
                .kepAdresiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        tuzelKisiYonetimiPage
                .tuzelKisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad2)
                .tuzelKisiGeregiAlaniVergiNoPostaTipiKontrol(vergiNo2, postaTipi)

                .secilenGeregiSil()
                .geregiDoldur(vergiNo2)
                .secilenGeregiSil()
                .geregiDoldur(kisaAd2);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("T")
                .geldigiTuzelKisiDoldur(ad2)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(vergiNo2)
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(kisaAd2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2241: Tüzel kişinin iletişim bilgisinin değiştirilmesi")
    public void TC2241() throws InterruptedException {

        String vergiNo = "85212364597";
        String ad = "SÇ Üniversitesi";
        String kisaAd = "scunv";

        String mobilTelNo = "539" + createRandomNumber(7);
        String telNo = "212" + createRandomNumber(11);
        String isTelNo = "212" + createRandomNumber(11);
        String faxNo = "212" + createRandomNumber(7);
        String adres = createRandomText(7) + " " + "Mahallesi";
        String il = "İstanbul";
        String ilce = "Kadıköy";
        String ulke = "TÜRKİYE";
        String ePosta = createRandomText(5) + "@turksat.com.tr";
        String webAdres = "http://www.scunv.com.tr/";
        String basariMesaji = "İşlem başarılıdır!";
        String birinciKullaniciGeregiAdresi = adres + ilce + "/" + il;
        String vergiNo2 = "1122007720";

        tuzelKisiYonetimiPage
                .openPage()
                .filtreVergiNoDoldur(vergiNo)
                .filtreAdDoldur(ad)
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)
                .tuzelKisiGuncelle()
                .iletisimBilgileriGuncelle()

                .mobilTelNoDoldur(mobilTelNo)
                .telNoDoldur(telNo)
                .faks1NoDoldur(faxNo)
                .faks2NoDoldur(faxNo)
                .adresDoldur(adres)
                .ulkeSec(ulke)
                .ilSec(il)
                .ilceSec(ilce)
                .ePostaDoldur(ePosta)
                .webAdresDoldur(webAdres)
                .iletisimBilgisiKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("T")
                .geregiDoldur(ad)
                .tuzelKisiGeregiAlaniVergiNoAdAdresKontrol(vergiNo, ad, adres)

                .geregiAlaniGuncelle()
                .adresHitaptaGorunsunSec(true)
                .dagitimHitapDuzenlemeKaydet();

        evrakOlusturPage
                .editorTabAc()
                .hitapAlaniAdresKontrol(adres, ilce, il);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgiSecimTipiSec("T")
                .bilgiDoldur(vergiNo2)
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
    @Test(enabled = true, description = "TC1132: Tüzel kişinin pasif yapılması ve kontrolü")
    public void TC1132() {

        String vergiNo = "8524567913";
        String ad = "Türksat Optiim";
        String kisaAd = "trkstopttm";
        String tip = "Tüzel Kişi";
        String basariMesaji = "İşlem başarılıdır!";

        //Tüzel kişi datası pasif ise aktif yap. Bu adım testte yok ama data bozulmuşsa düzeltilir.
        //TODO: DB'den data alınıp, update sql ile aktif yapılabilir.
        tuzelKisiYonetimiPage
                .openPage()
                .filtreDurumSec("TUMU")
                .filtreAdDoldur(ad)
                .ara()
                .tuzelKisiPasifIseAktifYap();

        sikKullanilanlarPage
                .openPage()
                .dagitimdaVarIseKaldir(ad) //Seçili geldiği durumlarda seçili kişiyi kaldırır.
                .datigimlarTipSec(tip)
                .dagitimlarDoldur(ad)
                .dagitimlarKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        tuzelKisiYonetimiPage
                .openPage()
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)
                .tuzelKisiPasifYap()
                .islemOnayi("Evet")

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .pasifTuzelKisiKayitKontrolu(vergiNo, ad, kisaAd);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("T")
                .geregiAlanindaGoruntulenmemeKontrolu(ad)
                .bilgiSecimTipiSec("T")
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        evrakOlusturPage
                .editorTabAc()
                .geregiAlanindaGoruntulenmemeKontrolu(ad)
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("T")
                .geldigiTuzelKisiGoruntulenmemeKontrolu(ad)
                .geldigiTuzelKisiGoruntulenmemeKontrolu(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("T")
                .geregiAlanindaGoruntulenmemeKontrolu(ad)

                .bilgiSecimTipiSec("T")
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        sikKullanilanlarPage
                .openPage()
                .dagitimlarListesindeKisininGoruntulenmemeKontrolu(ad);
    }
}
