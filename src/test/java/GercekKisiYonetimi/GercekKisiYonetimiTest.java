package GercekKisiYonetimi;

import common.BaseLibrary;
import common.BasePage;
import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.GercekKisiYonetimPage;
import pageData.MesajTipi;
import pageData.SolMenuData;

import static pageData.MesajTipi.*;

public class GercekKisiYonetimiTest extends BaseTest{

    BasePage page;

    @BeforeMethod
    public void loginBeforeTests() {
        page = new BasePage();
        page.loginPage().login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1516: Gerçek kişi tanımlama ve  kontrolü")
    public void TC1516() throws InterruptedException {

        //TODO: TestData classına alınacak.
        String tcNO = page.baseLibrary().createMernisTCNO();
        String ad = page.baseLibrary().createRandomText(6);
        String soyad = page.baseLibrary().createRandomText(6);
        String onEk = "Muh";
        String unvan = "Mühendis";
        String adres = "Kuştepe Mahallesi";
        String il = "İstanbul";
        String ilce = "Şişli";
        String eposta = "test@turksat.com.tr";
        String adSoyad = ad+" "+soyad;
        String hitap = "Sayın";

        page.ustMenuAc("Gerçek Kişi Yönetimi");
        page.gercekKisiYonetimPage()
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

        page.ustMenuAc("Evrak Oluştur");
        page.evrakOlusturPage()
                .geregiSecimTipiSec("G")
                .geregiDoldur(adSoyad)
                .geregiAlaniKontrol(adSoyad, unvan, adres, "P" )

                .openTab("Editör")
                .hitapKismiAlaniKontrol(hitap, onEk, ad, soyad )

                .openTab("Bilgileri")
                .bilgiSecimTipiSec("G");
        //.bilgiDoldur(adSoyad);
        //  page.islemMesaji().beklenenMesajTipi(DIKKAT);

        page.ustMenuAc("Gelen Evrak Kayıt");
        page.gelenEvrakKayitPage()
                .evrakBilgileriListKisiKurumSec("G")
                .evrakBilgileriListGeldigiKurumDoldur(adSoyad);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1516: Gerçek kişi sorgulama")
    public void TC1144() {

        //TODO: DB'den çekilecek.
        String tcNO = "91057625780";
        String ad = "OptiimTest";
        String soyad = "TestOptiim";

        page.ustMenuAc("Gerçek Kişi Yönetimi");
        page.gercekKisiYonetimPage()
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
                .filtreTCKimlikNoDoldur("10")
                .filtreDurumSec("AKTIFLER")
                .ara()
                .tcNoKontrolu("10")

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

      String getTbleTC =  page.gercekKisiYonetimPage().getTbleTCNO();

      page.gercekKisiYonetimPage()
              .filtreSorgulamaPaneliAc()
              .filtreDurumSec("PASIFLER")
              .filtreTCKimlikNoDoldur(getTbleTC)
              .ara()
              .tcNoKontrolu(getTbleTC);

        String getTbleTC2 = page.gercekKisiYonetimPage().getTbleTCNO();

        page.gercekKisiYonetimPage().filtreSorgulamaPaneliAc()
                .filtreDurumSec("PASIFLER")
                .filtreTCKimlikNoDoldur(getTbleTC)
                .ara()
                .tcNoKontrolu(getTbleTC);
    }
}
