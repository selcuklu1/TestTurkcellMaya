package tests.GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.*;

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
    EvrakAramaPage evrakAramaPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    PostalanacakEvraklarPage postalanacakEvraklarPage;
    PostalananlarPage postalananlarPage;
    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;

    String evrakNo = "";
    String basariMesaji = "İşlem başarılıdır!";


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
        evrakAramaPage = new EvrakAramaPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1474 : Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TS1474() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String gizlilikDerecesi = "Gizli";
        String kisiKurum = "Kurum";
        String geldigiKurum = "BÜYÜK HARFLERLE KURUM";
        String evrakGelisTipi = "Posta";
        String ivedilik = "İvedi";

        login("gklerans", passwordZTEKIN);

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
                .havaleIslemleriKisiSecmeyeDene(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvrakKayitPage
                .havaleIslemleriKullaniciListesiSecmeyeDene(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1475: Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TS1475A() throws InterruptedException {

        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer TC1475";
        String geldigiYer = "Gizlilik Kler";
        String evrakTarihi = "15.12.2017";

        login("gklerans", passwordZTEKIN);

        havaleEttiklerimPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        havaleEttiklerimPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi)
                .havaleYap()
                .havaleYapKisiSecmeyeDene(kisi)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

        havaleEttiklerimPage
                .havaleYapKullaniciyiSecmeyeDene(kullaniciListesi)
                .islemMesaji().dikkatOlmali(uyariMesaj2);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0272: Teslim almayı bekleyenler listesinden havale ederken gizlilik kontrolü")
    public void TS0272() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer-TC272";
        String geldigiYer = "Yargı / BÜYÜK HARFLERLE KURUM(G)";
        String evrakTarihi = "15.12.2017";
        String no = "123";

        login("gklerans", passwordZTEKIN);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Tarih", evrakTarihi, Keys.ENTER);
//                .filtreleAc().tarihiDoldur(evrakTarihi, Keys.ENTER)
        teslimAlinmayiBekleyenlerPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .havaleYap()
//                .havaleYapKisiDoldur(kisi)
                .havaleYapKisiKisiSecmeyeDene(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
//                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .havaleYapKullaniciListesiSecmeyeDene(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2223: Teslim aldıklarım listesinden havalede gizlilik klerans kontrolü")
    public void TS2223() throws InterruptedException {

        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer TC2223";
        String geldigiYer = "Gizlilik KLERANS";
        String evrakTarihi = "15.12.2017";
        String no = "5096";

        login("gklerans", passwordZTEKIN);

        teslimAlinanlarPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Tarih", evrakTarihi, Keys.ENTER);
//                .filtreleAc().tarihiDoldur(evrakTarihi, Keys.ENTER)
        teslimAlinanlarPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .havaleYap()
//                .havaleYapKisiDoldur(kisi)
                .havaleYapKisiSecmeyeDene(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinanlarPage
//                .havaleYapKullaniciListesiDoldur(kullaniciListesi)
                .havaleYapKullaniciListesiSecmeyeDene(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1475: Gelen evrak listesinden havalede gizlilik derecesi kontrolü")
    public void TS1475B() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Konu: K/Frekans Yıllık Kullanım Ücreti TC1475";
        String geldigiYer = "Yargı / BÜYÜK HARFLERLE KURUM";
        String evrakTarihi = "15.12.2017";
        String no = "1";

        login("gklerans", passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        gelenEvraklarPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .havaleYap()
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme()
                .havaleYapKisiDoldur(kisi)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

        gelenEvraklarPage
                .havaleYapKullaniciyiSecmeyeDene(kullaniciListesi)
                .islemMesaji().dikkatOlmali(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2131: İşlem bekleyen evraklardan evrak paylaşırken gizlilik kontrolü")
    public void TS2131() throws InterruptedException {
        String uyariMesaj1 = "Can ŞEKER kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String kisi = "Can Şeker";
        String konuKodu = "Diğer-TC2131";
        String geldigiYer = "Gizlilik Klerans";
        String gidecegiyer = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "5107";
        String no2 = "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        gelenEvraklarPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .paylas()
                .kisiVeAciklamaAlaniGeldigiGorme()
                .paylasKisiSec(kisi)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        parafBekleyenlerPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        parafBekleyenlerPage
                .gizlilikRaporSec(konuKodu, gidecegiyer, evrakTarihi, no2)
                .paylas()
                .paylasKisiSec(kisi)
                .paylasimAciklamaYaz(aciklama)
                .paylasPaylas()
                .islemMesaji().dikkatOlmali(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2132: İşlem yaptıklarım listesinden evrak paylaşırken gizlilik kontrolü")
    public void TS2132() throws InterruptedException {

        String uyariMesaj1 = "Can ŞEKER kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer-TC2132";
        String geldigiYer = "Gizlilik Klerans";
        String gidecegiyer = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "9250";
        String no2 = "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", passwordZTEKIN);

        parafladiklarimPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        parafladiklarimPage
                .gizlilikRaporSec(konuKodu, gidecegiyer, evrakTarihi, no)
                .paylas()
                .paylasKisiDoldur(kisi)
                .paylasAciklamaDoldur(aciklama)
                .paylasPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        paylastiklarimPage
                .openPage()
                .filter()
                .filtrelerAc()
                .filtrelerdeAlaniDoldur("Evrak No", no)
                .filtrelerButonuTikla("Filtrele");
        paylastiklarimPage
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .paylasTabTikla()
                .paylasKisiSec(kisi)
                .paylasimAciklamaYaz(aciklama)
                .paylasPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2135: İşlem yaptıklarım listesinden evrak takibe eklerken gizlilik kontrolü")
    public void TS2135() throws InterruptedException {
        String uyariMesaj1 = "Kullanici kleransı evrakı takibe eklemek için yetersizdir!";
        String kisi = "Can Şeker";
        String konuKodu = "Diğer-TC2135";
        String geldigiYer = "BÜYÜK HARFLERLE KURUM";
        String gidecegiyer = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakTarihi = "15.12.2017";
        String no = "123";

        login("gklerans", passwordZTEKIN);

        iadeEttiklerimPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        iadeEttiklerimPage.gizlilikRaporSec(konuKodu, geldigiYer, gidecegiyer, evrakTarihi, no)
                .kullanicilarDoldur(kisi)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

        iadeEttiklerimPage
                .takipListeKapat();

        cevapladiklarimPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        cevapladiklarimPage.gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .kullanicilarDoldur(kisi)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2134: İşlem bekleyen evraklardan takibe eklerken gizlilik kontrolü")
    public void TS2134() throws InterruptedException {
        String uyariMesaj1 = "Kullanici kleransı evrakı takibe eklemek için yetersizdir!";
        String kisi = "Can Şeker";
        String konuKodu = "Diğer-TC2131";
        String konuKodu2 = "Diğer-TC2131";
        String geldigiYer = "Gizlilik Klerans";
        String geldigiYer2 = "Gizlilik Klerans(B)";
        String evrakTarihi = "15.12.2017";
        String no = "5107";
        String no2 = "0";
        login("gklerans", passwordZTEKIN);

        gelenEvraklarPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        gelenEvraklarPage.gizlilikRaporSecTakibeEkle(konuKodu, geldigiYer, evrakTarihi, no)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvraklarPage
                .takipListeKapat();

        parafBekleyenlerPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        parafBekleyenlerPage.gizlilikRaporSecTakipListesi(konuKodu2, geldigiYer2, evrakTarihi, no2)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2227: Teslim alınmayı bekleyenler ve teslim alınanlar listesinde gizlilik klerans kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TS2227() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-2227-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kaldirilicakKlasor = "Gündem";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);

        //TODO Pre Condition Teslim alınmayı bekleyenler sayfası data oluşturmakta
        login("gklerans", passwordYAKYOL);
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(kullaniciAdi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login("cseker", passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Tarih", evrakTarihi, Keys.ENTER);
        teslimAlinmayiBekleyenlerPage.evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
                .evrakSecIcerikGoster(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().dikkatOlmali(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
                .evrakSecTeslimAl(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag, true)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakGeldigiGorme(konuKoduRandom, kurum, evrakTarihi)
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().dikkatOlmali(uyariMesaj1);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2225: Posta işleminde gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)")
    public void TS2225() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TS-2225-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kaldirilicakKlasor = "Gündem";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
        String gizlilikDerecesi = "Gizli";
        String gizlilikKlerans = "Gizlilik Kleransı";
        String bilgi = "Kurum";
        String imzalama = "İmzalama";
        String editor = createRandomText(15);

        //TODO Pre Condition Postalanacak evraklar sayfası data oluşturmakta
        login("gklerans", passwordYAKYOL);
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .kaldiralacakKlasorlerSec(gizlilikKlerans)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .bilgiSecimTipiSecByText(bilgi)
                .bilgiDoldur(kurum)
                .OnayAkisiEkle()
                .onayAkisiEkleIlkImzalaSec(imzalama)
                .onayAkisiKullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(editor)
                .imzala()
                .sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup();
        //TODO

        login("gklerans", passwordYAKYOL);

        postalanacakEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi)
                .evrakSecIcerikGoster(konuKoduRandom, kurum, evrakTarihi)
                .evrakSecIcerikKapat(true)
                .evrakSec(konuKoduRandom, kurum, evrakTarihi)
                .evrakPostala()
                .evrakPostalaPostala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        postalananlarPage
                .openPage().filter().filtrelerAc()
                .filtrelerdeAlaniDoldur("Başlangıç Tarihi", evrakTarihi, Keys.ENTER)
                .filtrelerdeAlaniDoldur("Bitiş Tarihi", evrakTarihi, Keys.ENTER);
        postalananlarPage.evrakSec(konuKoduRandom, kurum, evrakTarihi)
                .evrakSecIcerikGoster(konuKoduRandom, kurum, evrakTarihi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TS1471: Kullanıcı gizlilik derecesi değiştirme")
    public void TS1471() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String unvan = "BT İş Analist / Yazılımcı";
        String gizlilikDerecesi = "Tasnif Dışı";

        login("gsahin", "123");

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
                .kullaniciBirimAtamaGizlilikDerecesiDeğerKontrolu("Tasnif Dışı");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , priority = 1
            , description = "TS1938: Yüksek kleranslı evrak oluşturma")
    public void TS1938() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konu = "TS1938 " + getSysDate();
        String tur = "IMZALAMA";
        String tur2 = "PARAFLAMA";
        String icerik = "TS1938() " + getSysDate();
        String konuKodu = "010.01";
//        String kaldiralacakKlasor = "TestBaris";
        String kaldiralacakKlasor = "gündem";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Gizli";
        String ivedilik = "Normal";
        String geregi = "Optiim Birim";

        login("username23t", "123");

        kullaniciYonetimiPage
                .openPage()
                .kullaniciAdiDoldur("username23t")
                .ara()
                .tabloBirimKontrol()
                .kullaniciListesiGuncelleButonuTikla()
                .gorevliOlduguBirimlerKontol()
                .gorevliOlduguBirimGuncelle()
                .kullaniciBirimAtamaGizlilikDerecesiSec("Tasnif Dışı")
                .kullaniciBirimAtamaKaydetTikla()
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
//
//        logout();
//        login("gsahin", "123");
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedilikSec(ivedilik)
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafKontrol("username23t", tur2)
                .IlkKullaniciImzalamaVeyaParaflamaSec(tur)
                .onayAkisiKullaniciKontrol("username23t", tur)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakImzala()
                /*.sImzasec()
                .sImzaImzala()
                .sayisalImzaEvetPopup()*/
                .islemMesaji().basariliOlmali();

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .evrakIcerikKontroluveEvrakNoAl(konu);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            , dependsOnMethods = {"TS1938"}
            , description = "TS2138 : Genel evrak raporunda gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TS2138() throws InterruptedException {
//9267
        TS1938();

        login("username23t", "123");
        String mesaj = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";


        genelEvrakRaporuPage
                .openPage()
                .evrakNoDoldur(evrakNo)
                .sorgula()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .islemMesaji().dikkatOlmali(mesaj);
//                .detayEkranınıAcildigiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
            , dependsOnMethods = {"TS1938"}
            , description = "TS2139 : Evrak aramada gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TS2139() throws InterruptedException {

        login("username22n", "123");

        String evrakNo = "10180";
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
                .islemMesaji().dikkatOlmali(mesaj);

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()
                .sistemdeKayitliEvrakEkleTabAc()
                .evrakinAranacagiYerSec(aranacagiYer)
                .evrakAramaDoldur(evrakNo)
                .dokumanAra()
                .islemMesaji().basariliOlmali(basariMesaji);

        evrakOlusturPage
                .ekleriTabAc()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .islemMesaji().beklenenMesaj(mesaj);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TS1938"}, description = "TS2226: Genel evrak raporunda gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)")
    public void TS2226() throws InterruptedException {

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
    @Test(enabled = true, dependsOnMethods = {"TS1938"}, description = "TS2140 : Evrak aramada gizlilik kleransı kontrolü (evrakta izi olan kullanıcı ile)\n")
    public void TS2140() throws InterruptedException {

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
    @Test(enabled = true, description = "TS1473 : Onay akışında gizlilik derecesi kontrolü")
    public void TS1473() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String icerik = "TS1473() " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String geregi = "Esk Kurum 071216 2";

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        String kullaniciTasnifDisi = "USERNAME23T TEST";
        String kullaniciOzel = "username24o";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedilikSec(ivedilik)
                .geregiSecimTipiSecByText("Kurum")
                .geregiSec(geregi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()

                .kullanicilarDoldur(kullaniciOzel)
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafSec(kullaniciOzel, tur)

                .kullanicilarDoldur(kullaniciTasnifDisi)
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafSec(kullaniciTasnifDisi, tur2)
                .kullan();

        String mesaj = " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .islemMesaji().beklenenMesaj(kullaniciTasnifDisi + mesaj);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2191 : Akışta gizlilik kleransı değiştirilen evrakın dağıtım yeri kontrolü")
    public void TS2191() throws InterruptedException {
//8.tepten Devam edilecek


        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String icerik = "TS2191 " + getSysDate();
        String konuKodu = "010.01";
        String konu = "TS2191 " + getSysDate();
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String kullaniciNormal = "USERNAME22N TEST";
        String mesaj = kullaniciNormal + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedilikSec(ivedilik)
                .geregiSecimTipiSecByText("Kullanıcı")
                .geregiSec(kullaniciNormal)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarDoldur("username21g")
                .kullaniciTabloKontrol()
                .kullniciIsmineGoreImzaParafSec("username21g", tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .evrakParafla();
//                .parafla()
//                .sImzasec()
//                .sImzaImzala2();
//                .islemMesaji().beklenenMesaj(basariMesaji);

//        parafladiklarimPage
//                .openPage();
//        evrakNo = parafladiklarimPage.evrakDetayiEvrakNoAl();

        logout();
        login("username21g", "123");

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu);

        evrakOlusturPage
                .bilgilerTabiAc()
                .gizlilikDerecesiSec("Özel")
                .kaydet()
                .confirmDialog().confirmEvetTikla();
        evrakOlusturPage
                .bilgilerTabiAc()
                .iadeEtbutonKontol()
                .iadeEt()
                .kullaniciListesiKontrol("Mehmet BOZDEMİR")
                .notDoldur("iade")
                .iadeEt2()
//                .popUpEvraktaDegisiklik()
                .islemMesaji().beklenenMesaj(basariMesaji);

        logout();
        login(usernameMBOZDEMIR, passwordMBOZDEMIR);


        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS1472 : Dağıtımda gizlilik derecesi kontrolü")
    public void TS1472() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur2 = "IMZALAMA";
        String icerik = "TS1472 " + getSysDate();
        String konuKodu = "010.01";
        String konu = "TS1472 " + getSysDate();
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String kullaniciOzel = "USERNAME24O TEST";
        String kullaniciTasnifDisi = "USERNAME23T TEST";

        String mesaj = kullaniciTasnifDisi + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String mesaj2 = "DAGPLAN1 adlı Dağıtım Planınında gizlilik kleransı yetersiz kullanıcılar vardır: " + kullaniciTasnifDisi;


        login(usernameMBOZDEMIR, passwordMBOZDEMIR);


        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(icerik)
                .ivedilikSec(ivedilik)
                .geregiSecimTipiSecByText("Kullanıcı")
                .geregiSec(kullaniciOzel)
                .geregiSec(kullaniciTasnifDisi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .IlkKullaniciImzalamaVeyaParaflamaSec(tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .imzalaButonaTikla()
                .islemMesaji().dikkatOlmali(mesaj);

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiSonKayitSil()
                .geregiSecimTipiSecByText("Dağıtım Planları")
                .geregiSec("DAGPLAN1");

        evrakOlusturPage
                .editorTabAc()
                .imzalaButonaTikla()
                .islemMesaji().dikkatOlmali(mesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2190 : Dağıtımda gizlilik klerans kontrolü (Cevap)")
    public void TS2190() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur2 = "IMZALAMA";
        String konu = "TS2190 " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String kullaniciOzel = "USERNAME24O TEST";
        String kullaniciTasnifDisi = "USERNAME23T TEST";

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";

        String mesaj = kullaniciTasnifDisi + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String mesaj2 = "DAGPLAN1 adlı Dağıtım Planınında gizlilik kleransı yetersiz kullanıcılar vardır: " + kullaniciTasnifDisi;

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        //testte kullanılacak data oluşturuluyor.
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet();
        String evrakNO2190 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .cevapYaz();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(konu)
                .ivedilikSec(ivedilik)
                .geregiSecimTipiSecByText("Kullanıcı")
                .geregiSec(kullaniciOzel)
                .geregiSec(kullaniciTasnifDisi)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .IlkKullaniciImzalamaVeyaParaflamaSec(tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu)
                .imzalaButonaTikla()
                .islemMesaji().dikkatOlmali(mesaj);

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiSonKayitSil()
                .geregiSecimTipiSecByText("Dağıtım Planları")
                .geregiSec("DAGPLAN1");

        evrakOlusturPage
                .editorTabAc()
                .imzalaButonaTikla()
                .islemMesaji().dikkatOlmali(mesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2189 : Onay akışında gizlilik klerans kontrolü (Cevap)")
    public void TS2189() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String text = "TS2189 " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String kullaniciOzel = "USERNAME24O TEST";
        String kullaniciTasnifDisi = "USERNAME23T TEST";

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";

        String mesaj = kullaniciTasnifDisi + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";

        login(usernameMBOZDEMIR, passwordMBOZDEMIR);

        //testte kullanılacak data oluşturuluyor.
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(text)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("Mehmet Bozdemir")
                .kaydet();
        String evrakNO2189 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(text)
                .cevapYaz();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec(konuKodu)
                .konuDoldur(text)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .aciklamaDoldur(text)
                .ivedilikSec(ivedilik)
                .geregiSec(birim)
                .onayAkisiEkle()
                .kullaniciTabloKontrol()
                .kullanicilarDoldur(kullaniciOzel)
                .kullniciIsmineGoreImzaParafSec(kullaniciOzel, tur)
                .kullanicilarDoldur(kullaniciTasnifDisi)
                .kullniciIsmineGoreImzaParafSec(kullaniciTasnifDisi, tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(text)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2181 : Gizlilik kleransı yeterli olmayan kullanıcıya evrak devrediemelmesi")
    public void TS2181() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String text = "TS2181 " + getSysDate();
//        String text = "TS2181 20171223151625";
        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Gizli";
        String ivedilik = "Normal";
        String kullaniciNormal = "USERNAME22N TEST";
        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        String aciklama = "Yetersiz Klerans";

        login(usernameYAKYOL, passwordYAKYOL);

        //testte kullanılacak data oluşturuluyor.
        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(text)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(getSysDateForKis())
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText2(geldigiKurum)
                .evrakSayiSagDoldur()
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriKisiSec("YASEMİN")
                .kaydet();
        String evrakNO2189 = gelenEvrakKayitPage.popUps();
        gelenEvrakKayitPage.islemMesaji().basariliOlmali();

        kullaniciEvrakDevretPage
                .openPage()
                .ekranTabKontrolleri()
                .devredecekKisiSec("Yasemin Çakıl")
                .listele();
//                .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciEvrakDevretPage
                .tabloAlanKontrolleri()
                .tabloEvrakSecimi(text)
                .devret()
                .devralacakKisiAlanKontolu()
                .devralacakKisiSec(kullaniciNormal)
                .aciklamaDoldur(text)
                .devretTamam()
                .popUpDevredilemeyenEvraklarKontrol()
                .devredelimeyenEvraklarEvrakKontrolu(text, aciklama);
    }
}
