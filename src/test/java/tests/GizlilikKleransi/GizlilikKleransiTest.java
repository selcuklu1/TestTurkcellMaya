package tests.GizlilikKleransi;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
        imzaBekleyenlerPage= new ImzaBekleyenlerPage();
        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        postalananlarPage = new PostalananlarPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
    }

    //TODO Can Şeker yazmıştır.
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1474 : Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TC1474() throws InterruptedException {
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
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvrakKayitPage
                .havaleIslemleriKullaniciListesiDoldur(kullaniciListesi)
                .islemMesaji().beklenenMesaj(uyariMesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1475: Havale ettiklerim listesinden havalede gizlilik derecesi kontrolü")
    public void TC1475A() throws InterruptedException {
        String uyariMesaj1 = "Havale etmek istediğiniz kullanıcı kleransı yetersizdir!!";
        String uyariMesaj2 = "Havale etmek istediğiniz kullanıcı grubundaki Mehmet Emin YÜCEANT, Mehmet Gökhan BAYSAN, Mehmet Koray BALCIOĞLU ın kleransı yetersizdir, kleransı yeterli olmayan kullanıcılara havale edilmeyecektir !!";
        String kisi = "Can Şeker";
        String kullaniciListesi = "Optiim";
        String konuKodu = "Diğer TC1475";
        String geldigiYer = "Gizlilik Kler";
        String evrakTarihi = "15.12.2017";

        login("gklerans", password2);

        havaleEttiklerimPage
                .openPage()
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi)
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
        String konuKodu = "Diğer-TC2223";
        String geldigiYer = "Yargı / BÜYÜK HARFLERLE KURUM(G)";
        String evrakTarihi = "15.12.2017";
        String no = "123";

        login("gklerans", password2);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
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
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
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
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
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
        String no2 = "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", password2);

        gelenEvraklarPage
                .openPage()
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
                .paylas()
                .paylasKisiSec(kisi)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        parafBekleyenlerPage
                .openPage()
                .gizlilikRaporSec(konuKodu, gidecegiyer, evrakTarihi, no2)
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
        String no2 = "0";
        String aciklama = "Deneme amaçlıdır";

        login("gklerans", password2);

        parafladiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu, gidecegiyer, evrakTarihi, no)
                .paylas()
                .paylasKisiDoldur(kisi)
                .paylasAciklamaDoldur(aciklama)
                .paylasPaylas()
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        paylastiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
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
                .gizlilikRaporSec(konuKodu, geldigiYer, gidecegiyer, evrakTarihi, no)
                .kullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        iadeEttiklerimPage
                .takipListeKapat();

        cevapladiklarimPage
                .openPage()
                .gizlilikRaporSec(konuKodu, geldigiYer, evrakTarihi, no)
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
                .gizlilikRaporSecTakibeEkle(konuKodu, geldigiYer, evrakTarihi, no)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        gelenEvraklarPage
                .takipListeKapat();

        parafBekleyenlerPage
                .openPage()
                .gizlilikRaporSecTakipListesi(konuKodu2, geldigiYer2, evrakTarihi, no2)
                .takipListesiKullanicilarDoldur(kisi)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2227: Teslim alınmayı bekleyenler ve teslim alınanlar listesinde gizlilik klerans kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TC2227() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2227-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kaldirilicakKlasor = "Gündem";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullaniciAdi = "Yazılım Geliştirme Direktörlüğ";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);

        //TODO Pre Condition Teslim alınmayı bekleyenler sayfası data oluşturmakta
        login("gklerans", password3);
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

        login("cseker", password3);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
                .evrakSecIcerikGoster(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

        teslimAlinmayiBekleyenlerPage
                .evrakSecTeslimAl(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag,true)
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinanlarPage
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi, evrakSayiSag)
                .islemMesaji().beklenenMesaj(uyariMesaj1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2225: Posta işleminde gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)")
    public void TC2225() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2225-" + createRandomNumber(10);
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
        login("gklerans", password3);
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

        login("gklerans", password3);

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
                .openPage()
                .evrakSec(konuKoduRandom, kurum, evrakTarihi)
                .evrakSecIcerikGoster(konuKoduRandom, kurum, evrakTarihi);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, priority = 0, description = "TC1471: Kullanıcı gizlilik derecesi değiştirme")
    public void TC1471() throws InterruptedException {

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
                .kullaniciBirimAtamaGizlilikDerecesiKontrolu();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1471"}, description = "TC1938: Yüksek kleranslı evrak oluşturma")
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
                .kullaniciGuncellemeKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);

        logout();
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
                .ivedilikSec(ivedilik)
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
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "TC2138 : Genel evrak raporunda gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TC2138() throws InterruptedException {
//9267
        login("username20g", "123");

        genelEvrakRaporuPage
                .openPage()
                .evrakNoDoldur(evrakNo)
                .sorgula()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo)
                .detayEkranınıAcildigiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "TC2139 : Evrak aramada gizlilik kleransı kontrolü (evrakta izi olmayan kullanıcı ile)")
    public void TC2139() throws InterruptedException {

        login(username4, password4);
//9267
//        String evrakNo = "9267";
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
                .tablodaDetayTikla(evrakNo);
//                .islemMesaji().beklenenMesaj(mesaj);

        evrakAramaPage
                .evrakinAranacagiYerSec(aranacagiYer)
                .aramaKriteriSec(aramaKriteri2)
                .aramaKriteriDoldur(evrakNo)
                .ara()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo);
//                .islemMesaji().beklenenMesaj(mesaj);

        evrakOlusturPage
                .openPage()
                .ekleriTabAc()
                .sistemdeKayitliEvrakEkleTabAc()
                .evrakinAranacagiYerSec(aranacagiYer)
                .evrakAramaDoldur(evrakNo)
                .dokumanAra()
                .tabloEvrakNoKontrol(evrakNo)
                .tablodaDetayTikla(evrakNo);
//                .islemMesaji().beklenenMesaj(basariMesaji);
        Thread.sleep(4000);
//        evrakOlusturPage
//                .islemMesaji().beklenenMesaj(mesaj);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "TC2226: Genel evrak raporunda gizlilik klerans kontrolü (evrakta izi olan kullanıcı ile)")
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
    @Test(enabled = true, dependsOnMethods = {"TC1938"}, description = "TC2140 : Evrak aramada gizlilik kleransı kontrolü (evrakta izi olan kullanıcı ile)\n")
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
    @Test(enabled = true, description = "TC1473 : Onay akışında gizlilik derecesi kontrolü")
    public void TC1473() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String icerik = "TC1473() " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String geregi = "Optiim Birim";

        login(username4, password4);

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
    @Test(enabled = true, description = "TC2191 : Akışta gizlilik kleransı değiştirilen evrakın dağıtım yeri kontrolü")
    public void TC2191() throws InterruptedException {
//8.tepten Devam edilecek


        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String icerik = "TC2191 " + getSysDate();
        String konuKodu = "010.01";
        String konu = "TC2191 " + getSysDate();
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Normal";
        String ivedilik = "Normal";
        String kullaniciNormal = "USERNAME22N TEST";
        String mesaj = kullaniciNormal + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";

        login(username4, password4);

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
                .parafla()
                .sImzasec()
                .sImzaImzala2();
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
                .iadeEtbutonKontol()
                .iadeEt()
                .kullaniciListesiKontrol("Yasemin")
                .notDoldur("iade")
                .iadeEt2()
                .popUpEvraktaDegisiklik()
                .islemMesaji().beklenenMesaj(basariMesaji);

        logout();
        login(username4, password4);


        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC1472 : Dağıtımda gizlilik derecesi kontrolü")
    public void TC1472() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur2 = "IMZALAMA";
        String icerik = "TC1472 " + getSysDate();
        String konuKodu = "010.01";
        String konu = "TC1472 " + getSysDate();
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String kullaniciOzel = "USERNAME24O TEST";
        String kullaniciTasnifDisi = "USERNAME23T TEST";

        String mesaj = kullaniciTasnifDisi + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";
        String mesaj2 = "DAGPLAN1 adlı Dağıtım Planınında gizlilik kleransı yetersiz kullanıcılar vardır: " + kullaniciTasnifDisi;


        login(username4, password4);


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
                .kullanicilarImzaciSec(tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(icerik)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiSonKayitSil()
                .geregiSecimTipiSecByText("Dağıtım Planları")
                .geregiSec("DAGPLAN1");

        evrakOlusturPage
                .editorTabAc()
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2190 : Dağıtımda gizlilik klerans kontrolü (Cevap)")
    public void TC2190() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String tur2 = "IMZALAMA";
        String konu = "TC2190 " + getSysDate();
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

        login(username4, password4);

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
        gelenEvrakKayitPage.islemMesaji().isBasarili();

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
                .kullanicilarImzaciSec(tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(konu)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiSonKayitSil()
                .geregiSecimTipiSecByText("Dağıtım Planları")
                .geregiSec("DAGPLAN1");

        evrakOlusturPage
                .editorTabAc()
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj2);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2189 : Onay akışında gizlilik klerans kontrolü (Cevap)")
    public void TC2189() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String tur = "PARAFLAMA";
        String tur2 = "IMZALAMA";
        String text = "TC2189 " + getSysDate();
        String konuKodu = "010.01";
        String kaldiralacakKlasor = "Diğer";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Hizmete Özel";
        String ivedilik = "Normal";
        String birim = "AFYON VALİLİĞİ";
        String kullaniciOzel = "USERNAME24O TEST";
        String kullaniciTasnifDisi = "USERNAME23T TEST";

        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";

        String mesaj = kullaniciTasnifDisi + " kullanıcısının gizlilik kleransı evrakı görüntülemek için yeterli değildir.";

        login(username4, password4);

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
        gelenEvrakKayitPage.islemMesaji().isBasarili();

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
                .kullniciIsmineGoreImzaParafSec(kullaniciOzel,tur)
                .kullanicilarDoldur(kullaniciTasnifDisi)
                .kullniciIsmineGoreImzaParafSec(kullaniciTasnifDisi,tur2)
                .kullan();

        evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(text)
                .parafla()
                .islemMesaji().dikkatOlmali(mesaj);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TC2181 : Gizlilik kleransı yeterli olmayan kullanıcıya evrak devrediemelmesi")
    public void TC2181() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String text = "TC2181 "+getSysDate();
//        String text = "TC2181 20171223151625";
        String konuKodu = "010.01";
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String gizlilikDerecesi = "Gizli";
        String ivedilik = "Normal";
        String kullaniciNormal = "USERNAME22N TEST";
        String evrakGelisTipi = "Posta";
        String geldigiKurum = "Esk Kurum 071216 2";
        String aciklama = "Yetersiz Klerans";

        login(username3,password3);

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
        gelenEvrakKayitPage.islemMesaji().isBasarili();

            kullaniciEvrakDevretPage
                    .openPage()
                    .devredecekKisiSec("Yasemin Çakıl")
                    .listele()
                    .islemMesaji().basariliOlmali(basariMesaji);

        kullaniciEvrakDevretPage
                    .ekranTabKontrolleri()
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
