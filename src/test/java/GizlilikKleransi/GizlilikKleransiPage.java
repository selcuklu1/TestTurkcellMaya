package GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.PulYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class GizlilikKleransiPage extends BaseTest {

    MainPage mainPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;

    String konuKodu = "010.01";
    String evrakTuru = "R";
    String evrakDili = "917";
    String evrakTarihi = "16.11.2017";
    String gizlilikDerecesi = "N";
    String kisiKurum = "D";
    String geldigiKurum = "Esk Kurum 071216 2";
    String evrakGelisTipi = "P";
    String ivedilik = "N";

    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        login();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1471: Kullanıcı gizlilik derecesi değiştirme")
    public void TC1471() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String unvan = "BT İş Analist / Yazılımcı";
        String gizlilikDerecesi = "T";

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("Gsahin")
                .ara()
                .tabloBirimKontrol()
                .kullaniciListesiGuncelleButonuTikla()
                .gorevliOlduguBirimlerKontol()
                .gorevliOlduguBirimGuncelle()
                .kullaniciBirimAtamaGizlilikDerecesiSec(gizlilikDerecesi)
                .kullaniciBirimAtamaKaydetTikla()
                .kullaniciGuncellemeUnvanGuncelle(unvan)
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciYonetimiPage
                .kullaniciListesiGuncelleButonuTikla()
                .gorevliOlduguBirimlerKontol()
                .gorevliOlduguBirimGuncelle()
                .kullaniciBirimAtamaGizlilikDerecesiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1474 : Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TC1474() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";

        String kisi = "Gökçe Şahin";
        String aciklama = "test otomasyon";
        gelenEvrakKayitPage
                .openPage()
                .evrakBilgileriListKonuKoduDoldur(konuKodu)
                .evrakBilgileriListEvrakTuruSec(evrakTuru)
                .evrakBilgileriListEvrakDiliSec(evrakDili)
                .evrakBilgileriListEvrakTarihiDoldur(evrakTarihi)
                .evrakBilgileriListGizlilikDerecesiSec(gizlilikDerecesi)
                .evrakBilgileriListKisiKurumSec(kisiKurum)
                .evrakBilgileriListGeldigiKurumDoldurLovText(geldigiKurum)
                .evrakBilgileriListEvrakSayiSagDoldur()
                .evrakBilgileriListEvrakGelisTipiSec(evrakGelisTipi)
                .evrakBilgileriListIvedilikSec(ivedilik)
                .dagitimBilgileriKisiDoldur(kisi)
                .dagitimBilgileriAciklamaDoldur(aciklama)
                .kaydet();

        //hata mesajı gelmesi gerekiyor. hata mesajı alınmıyor mail bekleniyor.
        String evrakNO = gelenEvrakKayitPage.popUps();
        System.console().printf(evrakNO);
        gelenEvrakKayitPage.islemMesaji().isBasarili();
    }
}
