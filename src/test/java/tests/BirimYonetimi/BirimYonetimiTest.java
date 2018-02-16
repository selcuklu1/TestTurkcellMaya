package tests.BirimYonetimi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.*;

/****************************************************
 * Tarih: 2018-02-15
 * Proje: Türksat Functional Test Automation
 * Class: "Birim Yönetimi" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class BirimYonetimiTest extends BaseTest {

    BirimYonetimiPage birimYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;
    EvrakOlusturPage evrakOlusturPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;

    @BeforeMethod
    public void loginBeforeTests() {

        login();

        birimYonetimiPage = new BirimYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();
        evrakOlusturPage = new EvrakOlusturPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2337: Yeni birim kayıt ve evrak işlemlerinden kontrolü - Görünür Seçilmez")
    public void TS2337() {

        String testID = "TS2337";
        String sistemTarihi = getSysDate();
        String birimAdi = "TS2337_Birim_" + sistemTarihi;
        String birimKisaAdi = "ts2337b_" + sistemTarihi;
        String idariBirimKimlikKodu = sistemTarihi;
        String birim = "Optiim Birim";
        String birimDetail = "YGD";
        String birimTipi = "Genel Müdürlüğü";
        String gelenEvrakNumaratoru = "Türksat AŞ_numarator - Gelen Evrak";
        String gidenEvrakNumaratoru = "Türksat AŞ_numarator - Giden Evrak";
        String basariMesaji = "İşlem başarılıdır!";

        testStatus(testID,"Birim Oluşturma");

        birimYonetimiPage
                .openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()
                .gorunurlukTipiSec("Görünür Seçilmez")
//                .disBirimSec(true)
                .adDoldur(birimAdi)
                .kisaAdiDoldur(birimKisaAdi)
                .antetTipiSec("Normal")
                .antetBilgisiDoldur(birimAdi)
                .idariKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustBirimSec(birim, birimDetail)
                .birimTipiSec(birimTipi)
                .gelenEvraklariNumaratoruDoldur(gelenEvrakNumaratoru)
                .gidenEvraklariNumaratoruDoldur(gidenEvrakNumaratoru)
                .birimBagTuruSec("Bağlı Kuruluş")
                .postaBirimiSec(birim, birimDetail)
                .kepPostaBirimiSec(birim, birimDetail)
                .postaSekliSec("Otomatik")
                .belgenetKullanıyormuSec("Evet")
                .kaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        birimYonetimiPage
                .birimFiltreDoldur(birimAdi)
                .ara()
                .birimKontrolu(birimAdi);

//TODO: Burada defect var. Seçilme durumu istemnmiş ama seçilemiyor.
        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSecByText("Birim")
                //.geldigiBirimDoldur(birimAdi)
                //.evrakSayisiSolAlanKontrolu(idariBirimKimlikKodu) //TS2337_Birim_20180215132100
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı"); //TS2337_Birim_20180215155258

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSecByText("Birim")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .geregiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ")

                .bilgiSecimTipiSecByText("Birim")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(birimAdi, "Birim Adı")
                .bilgiAlanindaBiriminGeldigiSecilemedigiKontrolu(idariBirimKimlikKodu, "İdari Birim Kodu ");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2336: Birimin olur metnini güncelleme")
    public void TS2336() {

        String birimAdi = "TS2336 Birim";

    }
}
