package tests.TuzelKisiYonetimi;

import common.BaseTest;
import common.ReusableSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

import java.lang.reflect.Method;
import java.util.List;

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
    public void beforeTests(Method method) {

        login();
        tuzelKisiYonetimiPage = new TuzelKisiYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        sikKullanilanlarPage = new SikKullanilanlarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1124: Yeni tüzel kişi kayıt ve ekranlardan kontrolleri")
    public void TS1124() throws InterruptedException {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " holding";
        String tuzelKisiTipi = "deneme tüzel kişi tipi";
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
                .kepAdresiAlanKontrolu()
                .tuzelKisiTipiSec(tuzelKisiTipi)
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
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiDoldur(ad, "Ad")
                .secilenGeregiSil()
                .geregiDoldur(kisaAd, "Kısa Ad")
                .secilenGeregiSil()
                .geregiDoldur(vergiNo, "Vergi No");

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Tüzel Kişi")
                .geldigiTuzelKisiDoldur(ad, "Ad")
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(kisaAd, "Kısa Ad")
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(vergiNo, "Vergi No");

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiDoldur(ad, "Ad")
                .secilenGeregiSil()
                .geregiDoldur(kisaAd, "Kısa Ad")
                .secilenGeregiSil()
                .geregiDoldur(vergiNo, "Vergi No");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1133: Tüzel kişi sorgulama")
    public void TS1133() throws InterruptedException {

        String vergiNo = "1257452322";
        String ad = "Ts1133 TüzelKişi";
        String kisaAd = "ts1133";

        //NOTE: Test steplerinin sıralaması değiştirildi.

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
                //tests.Data pasif ise, aktif yapılır.
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("TUMU")
                .ara()
                .tuzelKisiPasifIseAktifYap()
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
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
                //tests.Data aktif ise, pasif yapılır.
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
    @Test(enabled = true, description = "TS1460: Yeni tüzel kişi kayıtta alan kontrolleri")
    public void TS1460() {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " holding";
        String tuzelKisiTipi = "deneme tüzel kişi tipi";
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
                .tuzelKisiTipiSec(tuzelKisiTipi)
                .vergiNoDoldur(vergiNo)
                .tuzelKisiKaydet()
                .islemMesaji().uyariOlmali(zorunluAlanUyariMesaji);

        tuzelKisiYonetimiPage
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec(tuzelKisiTipi)
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
    @Test(enabled = true, description = "TS1126: Tüzel Kişi Bilgisi Güncelleme ve kontrolleri")
    public void TS1126() throws InterruptedException {

        String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " İş Çözümleri";
        String vergiNo2 = createRandomNumber(10);
        String kisaAd2 = createRandomText(7);
        String ad2 = kisaAd2 + " İş Çözümleri";
        String tuzelKisiTipi = "LİMİTED ŞİRKETİ";
        String kepAdresi = kisaAd + "@testkep.pttkep.gov.tr";
        String basariMesaji = "İşlem başarılıdır!";
        String postaTipi = "KEP";

        tuzelKisiYonetimiPage

                //tests.Data yaratmak için
                .openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec(tuzelKisiTipi)
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
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiDoldur(ad2, "Ad")
                .tuzelKisiGeregiAlaniVergiNoPostaTipiKontrol(vergiNo2, postaTipi)

                .secilenGeregiSil()
                .geregiDoldur(vergiNo2, "Vergi No")
                .secilenGeregiSil()
                .geregiDoldur(kisaAd2, "Kısa Ad");

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Tüzel Kişi")
                .geldigiTuzelKisiDoldur(ad2, "Ad2")
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(vergiNo2, "Vergi No2")
                .secilenGeregiTuzelKisiSil()
                .geldigiTuzelKisiDoldur(kisaAd2, "Kısa Ad2");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2241: Tüzel kişinin iletişim bilgisinin değiştirilmesi")
    public void TS2241() throws InterruptedException {

        String vergiNo = "85212364597";
        String ad = "Ts2241 Üniversitesi";
        String kisaAd = "ts2241unv";

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
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiDoldur(ad, "Ad")
                .tuzelKisiGeregiAlaniVergiNoAdAdresKontrol(vergiNo, ad, adres)

                .geregiAlaniGuncelle()
                .adresHitaptaGorunsunSec(true)
                .dagitimHitapDuzenlemeKaydet();

        evrakOlusturPage
                .editorTabAc()
                .hitapAlaniAdresKontrol(adres, ilce, il);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgiSecimTipiSecByText("Tüzel Kişi")
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
    @Test(enabled = true, description = "TS1132b: Tüzel kişinin pasif yapılması ve kontrolü")
    public void TS1132b() throws InterruptedException {

        String vergiNo = "34378564433";
        String ad = "Ts1132 TüzelKişi";
        String kisaAd = "ts1132tk";
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
                .islemOnayi("Evet");

        tuzelKisiYonetimiPage
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
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(ad)
                .bilgiSecimTipiSecByText("Tüzel Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        evrakOlusturPage
                .editorTabAc()
                .geregiAlanindaGoruntulenmemeKontrolu(ad)
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Tüzel Kişi")
                .geldigiTuzelKisiGoruntulenmemeKontrolu(ad)
                .geldigiTuzelKisiGoruntulenmemeKontrolu(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiAlanindaGoruntulenmemeKontrolu(ad)

                .bilgiSecimTipiSecByText("Tüzel Kişi")
                .bilgiAlanindaGoruntulenmemeKontrolu(ad);

        sikKullanilanlarPage
                .openPage()
                .dagitimlarListesindeKisininGoruntulenmemeKontrolu(ad);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1458b: Pasif yapılan tüzel kişinin aktif yapılması ve kontrolü")
    public void TS1458b() throws InterruptedException {

        String vergiNo = "55665732323";
        String ad = "Ts1458";
        String soyad = "TüzelKişi";
        String tamAd = "Ts1458 TüzelKişi";
        String kisaAd = "ts1458tk";
        String tip = "Tüzel Kişi";
        String basariMesaji = "İşlem başarılıdır!";
        String popupAktifEtmeMesaji = "Tüzel kişi tekrar aktif etmek istediğinize emin misiniz?";

        //Tüzel kişi datası aktif ise pasif yap. Bu adım testte yok ama data bozulmuşsa düzeltilir.
        //TODO: DB'den data alınıp, update sql ile aktif yapılabilir.
        tuzelKisiYonetimiPage
                .openPage()
                .filtreDurumSec("TUMU")
                .filtreAdDoldur(tamAd)
                .ara()
                .tuzelKisiAktifIsePasifYap()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(tamAd)
                .filtreDurumSec("PASIFLER")
                .ara()
                .pasifTuzelKisiKayitKontrolu(vergiNo, tamAd, kisaAd)

                .tuzelKisiAktifYap()
                .popupTuzelKisiAktifEtmeKontrolu(popupAktifEtmeMesaji)
                .islemOnayi("Evet");

        tuzelKisiYonetimiPage
                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(tamAd)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, tamAd, kisaAd)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(tamAd)
                .filtreDurumSec("PASIFLER")
                .ara()
                .kayitBulunamadiKontrolu()

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(tamAd)
                .filtreDurumSec("TUMU")
                .ara()
                .aktifTuzelKisiKayitKontrolu(vergiNo, tamAd, kisaAd);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiAlanindaGoruntulenmeKontrolu(tamAd)
                .secilenGeregiSil()
                .bilgiSecimTipiSecByText("Tüzel Kişi")
                .bilgiAlanindaGoruntulenmeKontrolu(ad, soyad);

        evrakOlusturPage
                .editorTabAc()
                .bilgiDoldur(tamAd, "Ad")
                .secilenBilgiSil()
                .geregVeBilgiAlanindanSil()
                .geregiDoldur(tamAd, "Ad");

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Tüzel Kişi")
                .geldigiTuzelKisiGoruntulenmeKontrolu(tamAd)
                .geldigiTuzelKisiGoruntulenmeKontrolu(vergiNo);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Tüzel Kişi")
                .geregiAlanindaGoruntulenmeKontrolu(tamAd)
                .secilenGeregiSil()
                .bilgiSecimTipiSecByText("Tüzel Kişi")
                .bilgiAlanindaGoruntulenmeKontrolu(ad, soyad);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2246: Medya şirketi tipinde tüzel kişi ekleme")
    public void TS2246() {

        /*String vergiNo = createRandomNumber(10);
        String kisaAd = createRandomText(7);
        String ad = kisaAd + " Medya Şirketi";
        String tuzelKisiTipi = "MEDYA ŞİRKETİ";
        String adres = "Gültepe Mahallesi";
        String ulke = "TÜRKİYE";
        String il = "İst";
        String ilce = "Kağ";
        String eposta = kisaAd + "medyasirketi@turksat.com.tr";
        String webAdres = "www." + kisaAd + "medyasirketi.com";
        String telNo = "5391111111";
        String faksNo = "2121111111";
        String basariMesaji = "İşlem başarılıdır!";

        tuzelKisiYonetimiPage
                .openPage()
                .yeniTuzelKisiEkle()
                .tuzelKisiTipiSec(tuzelKisiTipi)
                .medyaSirketiAlanKontrolleri()
                .vergiNoDoldur(vergiNo)
                .adDoldur(ad)
                .kisaAdDoldur(kisaAd)

                .karasalTVSec("T1")
                .karasalTVYayindaSec(true)
                .kabloTVSec("TEK")
                .kabloTVYayindaSec(true)
                .istegeBagliTvSec(true)
                .platformIsletmecisiSe(true)

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

           evrakOlusturPage
                .openPage()
                .editorTabAc()
                .geregiDoldur(ad, "Ad")
                .secilenGeregiSil()
                .geregiDoldur(kisaAd, "Kısa Ad")
                .secilenGeregiSil()
                .geregiVergiNoDoldur(vergiNo, "Vergi Kimlik No");*/

        List<String> medyaKisi = new ReusableSteps().medyaSirketiTuzelKisiEkleme();

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .geregiDoldur(medyaKisi.get(0), "Ad")
                .secilenGeregiSil()
                .geregiDoldur(medyaKisi.get(1), "Kısa Ad")
                .secilenGeregiSil()
                .geregiVergiNoDoldur(medyaKisi.get(2), "Vergi Kimlik No");

    }

}
