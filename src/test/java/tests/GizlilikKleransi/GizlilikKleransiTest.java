package tests.GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.HavaleEttiklerimPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciYonetimiPage;

import static data.TestData.password2;
import static data.TestData.username2;
import static data.TestData.username3;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan - Can Şeker
 ****************************************************/
public class GizlilikKleransiTest extends BaseTest {

    MainPage mainPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    HavaleEttiklerimPage havaleEttiklerimPage;


    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1471: Kullanıcı gizlilik derecesi değiştirme")
    public void TC1471() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String unvan = "BT İş Analist / Yazılımcı";
        String gizlilikDerecesi = "T";

        login("yakyol", "123");

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
    public void TC1474() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Gökçe Şahin";
        String kullaniciListesi = "Optiim";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String evrakTuru = "R";
        String evrakDili = "917";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "G";
        String kisiKurum = "D";
        String geldigiKurum = "BÜYÜK HARFLERLE KURUM";
        String evrakGelisTipi = "P";
        String ivedilik = "N";

        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
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
               .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvrakKayitPage
                .havaleIslemleriKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1475 : Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TC1475() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Gökçe Şahin";
        String kullaniciListesi = "Optiim";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String geldigiYer = "Zübeyde Tekin";
        String evrakTarihi = "14.12.2017";
        String no = "12013";
        login(username2, password2);

        havaleEttiklerimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        havaleEttiklerimPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }


}
