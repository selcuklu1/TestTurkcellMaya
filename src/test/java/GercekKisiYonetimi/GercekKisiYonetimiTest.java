package GercekKisiYonetimi;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GercekKisiYonetimPage;

import static pages.pageData.MesajTipi.DIKKAT;

public class GercekKisiYonetimiTest extends BaseTest {

    BasePage page;
    GercekKisiYonetimPage gercekKisiYonetimPage;
    EvrakOlusturPage evrakOlusturPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        gercekKisiYonetimPage = new GercekKisiYonetimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1516: Gerçek kişi tanımlama ve  kontrolü")
    public void TC1516() {
        String tcNO = page.baseLibrary().createMernisTCNO();
        String ad = page.baseLibrary().createRandomText(6);
        String soyad = page.baseLibrary().createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String adSoyad = ad + " " + soyad;
        String hitap = "Sayın";
        String postaTipi = "P";
        String bilgiSecimTipi = "G";
        String geregiSecimTipi = "G";
        String evrakBilgileriListKisiKurumTipi = "G";

        page.ustMenu("Gerçek Kişi Yönetimi");
        gercekKisiYonetimPage
                .yeniGercekKisiEkle()
                .tcKimlikNoDoldur(tcNO)
                .onEkDoldur(onEk)
                .unvanDoldur(unvan)
                .adDoldur(ad)
                .soyadDoldur(soyad)
                //.kepAdresiKullaniyor(true)
                .iletisimBilgileriEkle()

                .iletisimBilgisiAdresDoldur(adres)
                .iletisimBilgisiIlDoldur(il)
                .iletisimBilgisiIlceDoldur(ilce)
                .iletisimBilgisiEpostaDoldur(eposta)
                .iletisimBilgisiKaydet()

                .kaydet();

        page.ustMenu("Evrak Oluştur");
        evrakOlusturPage
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiDoldur(adSoyad)
                .geregiAlaniKontrol(adSoyad, unvan, adres, postaTipi)

                .openTab("Editör")
                .hitapKismiAlaniKontrol(hitap, onEk, ad, soyad)

                .openTab("Bilgileri")
                .bilgiSecimTipiSec(bilgiSecimTipi);
        //.bilgiDoldur(adSoyad);
        //  pages.islemMesaji().beklenenMesajTipi(DIKKAT);

        page.ustMenu("Gelen Evrak Kayıt");
        gelenEvrakKayitPage
                .evrakBilgileriListKisiKurumSec(evrakBilgileriListKisiKurumTipi)
                .evrakBilgileriListGeldigiKurumDoldur(adSoyad);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1516: Gerçek kişi sorgulama")
    public void TC1144() throws InterruptedException {

        String tcNO = "91057625780";
        String tc10 = "10";
        String ad = "OptiimTest";
        String soyad = "TestOptiim";

        page.ustMenu("Gerçek Kişi Yönetimi");
        gercekKisiYonetimPage
                .ara()
                .filtreSorgulamaPaneliAc()
                .filtreSoyadDoldur(soyad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreAdDoldur(ad)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitKontrolu(tcNO, ad, soyad)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tc10)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .tcNoKontrolu(tc10)

                .filtreSorgulamaPaneliAc()
                .filtreTCKimlikNoDoldur(tcNO)
                .filtreDurumSec("AKTIFLER")
                .ara()
                .kayitKontrolu(tcNO, ad, soyad)

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

        String getTbleTC2 = gercekKisiYonetimPage.getTbleTCNO();

        gercekKisiYonetimPage
                .filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .filtreTCKimlikNoDoldur(getTbleTC)
                .ara()
                .tcNoKontrolu(getTbleTC);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1135: Yeni gerçek kişi kayıtta alan kontrolleri")
    public void TC1135() {

        String tcNO = "91057625780";
        String ad = "Sezai";
        String soyad = "Çelik";
        String duzgunOlmayanPostaFormati = "optimtestturksat.com.tr";
        String duzgunPostaFormati = "optim@turksat.com.tr";
        String duzgunOlmayanKepAdresi = "tr6787";
        String duzgunKepAdresi = "turksat.kamu2@testkep.pttkep.gov.tr";
        String kepHizmetSaglayici = "P";
        Boolean kepAdresiKullaniyor = true;

        page.ustMenu("Gerçek Kişi Yönetimi");
        gercekKisiYonetimPage
                .yeniGercekKisiEkle()
                .kaydet()
                .zorunluAdSoyadAlanKontrolu()

                .adDoldur(ad)
                .soyadDoldur(soyad)
                .kepAdresiKullaniyor(kepAdresiKullaniyor)
                .kaydet();
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);

        gercekKisiYonetimPage
                .iletisimBilgileriEkle()
                .iletisimBilgisiKaydet();
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);

        gercekKisiYonetimPage
                .iletisimBilgisiEpostaDoldur(duzgunOlmayanPostaFormati);
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);

        gercekKisiYonetimPage
                .iletisimBilgisiEpostaDoldur(duzgunPostaFormati)
                .iletisimBilgisiKaydet()
                .iletisimBilgisiGüncelle()
                .iletisimBilgisiIptalEt()

                .kepAdresBilgileriEkle()
                .kepAdresiKaydet();
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);

        gercekKisiYonetimPage
                .kepAdresiDoldur(duzgunOlmayanKepAdresi)
                .kepAdresiKaydet();
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);

        gercekKisiYonetimPage
                .kepAdresiDoldur(duzgunKepAdresi)
                .kepHizmetSaglayiciSec(kepHizmetSaglayici)
                .kepAdresiKaydet()

                .adDoldur(ad)
                .soyadDoldur(soyad)
                .kepAdresiKullaniyor(kepAdresiKullaniyor)
                .kaydet();
        //page.islemMesaji().beklenenMesajTipi(DIKKAT);
    }
}
