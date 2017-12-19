package tests.GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.*;
import pages.solMenuPages.HavaleEttiklerimPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GenelEvrakRaporuPage;
import pages.ustMenuPages.KullaniciYonetimiPage;

import static data.TestData.password2;
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
    TeslimAlinanlarPage teslimAlinanlarPage;
    GelenEvraklarPage gelenEvraklarPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    ParafladiklarimPage parafladiklarimPage;
    PaylastiklarimPage paylastiklarimPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    IadeEttiklerimPage iadeEttiklerimPage;
    CevapladiklarimPage cevapladiklarimPage;
    EvrakOlusturPage evrakOlusturPage;
    ImzaladiklarimPage imzaladiklarimPage;
    GenelEvrakRaporuPage genelEvrakRaporuPage;

    String evrakNo="";


    @BeforeMethod
    public void loginBeforeTests() {
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        parafladiklarimPage = new ParafladiklarimPage();
        paylastiklarimPage = new PaylastiklarimPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        iadeEttiklerimPage = new IadeEttiklerimPage();
        cevapladiklarimPage = new CevapladiklarimPage();
        evrakOlusturPage = new EvrakOlusturPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        genelEvrakRaporuPage = new GenelEvrakRaporuPage();
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
        String kisi = "Can Şeker";
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

        login("gklerans", password2);

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
               .islemMesaji().uyariOlmali(uyariMesaj1);

        gelenEvrakKayitPage
                .havaleIslemleriKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1475: Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TC1475A() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Gökçe Şahin";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer TC1475";
        String geldigiYer = "Gizlilik Kler";
        String evrakTarihi = "15.12.2017";

        login("gklerans", password2);

        havaleEttiklerimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        havaleEttiklerimPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2223: Teslim aldıklarım listesinden havalede gizlilik klerans kontrolü")
    public void TC2223() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer-TC272";
        String geldigiYer = "Yargı / BÜYÜK HARFLERLE KURUM(G)";
        String evrakTarihi = "15.12.2017";
        String no = "123";

        login("gklerans", password2);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC272: Teslim almayı bekleyenler listesinden havale ederken gizlilik kontrolü")
    public void TC272() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer TC2223";
        String geldigiYer = "Gizlilik KLERANS";
        String evrakTarihi = "15.12.2017";
        String no = "5096";

        login("gklerans", password2);

        teslimAlinanlarPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinanlarPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }



    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1475: Gelen evrak listesinden havalede gizlilik derecesi kontrolü")
    public void TC1475B() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Konu: K/Frekans Yıllık Kullanım Ücreti TC1475";
        String geldigiYer = "Yargı / BÜYÜK HARFLERLE KURUM";
        String evrakTarihi = "15.12.2017";
        String no = "1";

        login("gklerans", password2);

        gelenEvraklarPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .havaleYap()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvraklarPage
                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2131: İşlem bekleyen evraklardan evrak paylaşırken gizlilik kontrolü")
    public void TC2131() throws InterruptedException {
        String uyariMesaj1 = "Can ŞEKER kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer-TC2131";
        String geldigiYer = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ / Gizlilik Klerans";
        String gidecegiyer = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "5107";
        String no2= "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", password2);

        gelenEvraklarPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .paylas()
                .paylasKisiSec(kisi)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        parafBekleyenlerPage
                .openPage()
                .gizlilikRaporSec(konuKodu,gidecegiyer,evrakTarihi,no2)
                .paylas()
                .paylasKisiSec(kisi)
                .paylasimAciklamaYaz(aciklama)
                .paylasPaylas();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2132: İşlem yaptıklarım listesinden evrak paylaşırken gizlilik kontrolü")
    public void TC2132() throws InterruptedException {

        String uyariMesaj1 = "Can ŞEKER kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer-TC2132";
        String geldigiYer = "Gizlilik Klerans";
        String gidecegiyer = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "9250";
        String no2= "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", password2);

        parafladiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,gidecegiyer,evrakTarihi,no)
                .paylas()
                .paylasKisiDoldur(kisi)
                .paylasAciklamaDoldur(aciklama)
                .paylasPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        paylastiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .paylasTabTikla()
                .paylasKisiSec(kisi)
                .paylasimAciklamaYaz(aciklama)
                .paylasPaylas();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2135: İşlem yaptıklarım listesinden evrak takibe eklerken gizlilik kontrolü")
    public void TC2135() throws InterruptedException {
        String uyariMesaj1 = "Kullanici kleransı evrakı takibe eklemek için yetersizdir!";
        String kisi = "Can Şeker";
        String konuKodu = "Diğer-TC2135";
        String geldigiYer = "BÜYÜK HARFLERLE KURUM";
        String gidecegiyer = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakTarihi = "15.12.2017";
        String no = "123";

        login("gklerans", password2);

        iadeEttiklerimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,gidecegiyer,evrakTarihi,no)
                .kullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        iadeEttiklerimPage
                .takipListeKapat();

        cevapladiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu,geldigiYer,evrakTarihi,no)
                .kullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2134: İşlem bekleyen evraklardan takibe eklerken gizlilik kontrolü")
    public void TC2134() throws InterruptedException {
        String uyariMesaj1 = "Kullanici kleransı evrakı takibe eklemek için yetersizdir!";
        String kisi = "Can Şeker";
        String konuKodu = "Diğer-TC2131";
        String konuKodu2 = "Diğer-TC2131";
        String geldigiYer = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ / Gizlilik Klerans";
        String geldigiYer2 = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "5107";
        String no2 = "0";
        login("gklerans", password2);

        gelenEvraklarPage
                .openPage()
                .gizlilikRaporSecTakibeEkle(konuKodu,geldigiYer,evrakTarihi,no)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvraklarPage
                .takipListeKapat();

        parafBekleyenlerPage
                .openPage()
                .gizlilikRaporSecTakipListesi(konuKodu2,geldigiYer2,evrakTarihi,no2)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

    }

    @Test(enabled = true, description = "Yüksek kleranslı evrak oluşturma")
    public void TC1938() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "IMZALAMA";
        String icerik = "TC1938() " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Gizli";
        String geregi = "Optiim Birim";

login("gsahin","123");

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
    @Test(enabled = true,dependsOnMethods = {"TC1938"}, description = "Genel evrak raporunda gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)\n")
    public void TC2226() throws InterruptedException{

        login(username,password);

        genelEvrakRaporuPage
                .openPage()
                .evrakNoDoldur(evrakNo)
                .sorgula()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .detayEkranınıAcildigiKontrolu();


    }
}
