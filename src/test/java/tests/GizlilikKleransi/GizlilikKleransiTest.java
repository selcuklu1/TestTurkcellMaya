package tests.GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.HavaleEttiklerimPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.ustMenuPages.*;

import javax.swing.plaf.TableHeaderUI;

import static data.TestData.*;

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
    EvrakOlusturPage evrakOlusturPage;
    ImzaladiklarimPage imzaladiklarimPage;
    GenelEvrakRaporuPage genelEvrakRaporuPage;
    EvrakAramaPage evrakAramaPage;

    String evrakNo = "";
    String basariMesaji = "İşlem başarılıdır!";


    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        genelEvrakRaporuPage = new GenelEvrakRaporuPage();
        evrakAramaPage = new EvrakAramaPage();
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
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        havaleEttiklerimPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "Genel evrak raporunda gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TC2138() throws InterruptedException{
//9267
        login(username4, password4);

        genelEvrakRaporuPage
                .openPage()
                .evrakNoDoldur(evrakNo)
                .sorgula()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .detayEkranınıAcildigiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Evrak aramada gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TC2139() throws InterruptedException{

        login(username3, password3);
//9267
String evrakNo="9267";
        String aranacagiYer = "Birim Evrakları Ara";
        String aranacagiYer2 = "İşlem Yaptıklarımda Ara";
        String aramaKriteri = "Evrak Sayı";
        String aramaKriteri2 = "Evrakın Kayıt Sayısı";
        String evrakTipi = "Giden Evrak";
        String mesaj = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";

        evrakAramaPage
                .openPage()
                .gidenEvrakSec()
                .aramaKriteriSec(aramaKriteri)
                .aramaKriteriDoldur(evrakNo)
                .ara()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .islemMesaji().beklenenMesaj(mesaj);

        evrakAramaPage
                .evrakinAranacagiYerSec(aranacagiYer)
                .aramaKriteriSec(aramaKriteri2)
                .aramaKriteriDoldur(evrakNo)
                .ara()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .islemMesaji().beklenenMesaj(mesaj);

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()
                .sistemdeKayitliEvrakEkleTabAc()
                .evrakinAranacagiYerSec(aranacagiYer)
                .evrakAramaDoldur(evrakNo)
                .dokumanAra()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .islemMesaji().basariliOlmali(basariMesaji);
        Thread.sleep(2000);
        evrakOlusturPage
                .islemMesaji().beklenenMesaj(mesaj);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Yüksek kleranslı evrak oluşturma")
    public void TC1938() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "IMZALAMA";
        String icerik = "TC1938() " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "TestBaris";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Gizli";
        String ivedilik = "Normal";
        String geregi = "Optiim Birim";

        login("gsahin", "123");

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarImzaciSec(tur)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPage
                .openPage();

        evrakNo = imzaladiklarimPage.evrakIcerikKontroluveEvrakNoAl(icerik);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "Genel evrak raporunda gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)")
    public void TC2226() throws InterruptedException {

        login("gsahin", "123");

        genelEvrakRaporuPage
                .openPage()
                .evrakNoDoldur(evrakNo)
                .sorgula()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .detayEkranınıAcildigiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "Evrak aramada gizlilik kleransı kontrolü (evrakta izi olan kullanıcı ile)\n")
    public void TC2140() throws InterruptedException {

        login("gsahin", "123");
//9261

        String aranacagiYer = "İşlem Yaptıklarımda Ara";
        String aramaKriteri = "Evrakın Kayıt Sayısı";
        String evrakTipi = "Giden Evrak";

        evrakAramaPage
                .openPage()
                .gidenEvrakSec()
                .evrakinAranacagiYerSec(aranacagiYer)
                .aramaKriteriSec(aramaKriteri)
                .aramaKriteriDoldur(evrakNo)
                .ara()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .detayEkranınıAcildigiKontrolu()
                .detayEkranınıKapat()

                .detaylıAramaTab()
                .detayTabAranacagiYerSec(aranacagiYer)
                .detayTabAramaKriteriDoldur(evrakNo)
                .detayTabAra()
                .detayTabTablodaKontrolu(evrakNo, evrakTipi)
                .detayTabTablodaDetayTikla(evrakNo, evrakTipi)
                .detayEkranınıAcildigiKontrolu()
                .detayEkranınıKapat();

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()
                .sistemdeKayitliEvrakEkleTabAc()
                .evrakinAranacagiYerSec(aranacagiYer)
                .evrakAramaDoldur(evrakNo)
                .dokumanAra()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .evrakAramaKapat()
                .tablodaIlgiEkleTıkla(evrakNo)
                .tablodaIlgiEkleKontrolu();

    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onay akışında gizlilik derecesi kontrolü\n")
    public void TC1473() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String icerik = "TC1938() " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "ESK05";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String geregi = "Optiim Birim";

        login(username,password);

        String kullaniciTasnifDisi = "OPTİİM TEST3";
        String kullaniciOzel = "Optiim Test2";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()

                .kullanicilarDoldur2(kullaniciOzel)
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafSec(kullaniciOzel, tur)

                .kullanicilarDoldur2(kullaniciTasnifDisi)
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafSec(kullaniciTasnifDisi, tur2)
                .kullan();

        String mesaj = " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .islemMesaji().beklenenMesaj(kullaniciTasnifDisi+mesaj);
    }
}
