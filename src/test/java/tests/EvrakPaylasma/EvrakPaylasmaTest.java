package tests.EvrakPaylasma;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.KullaniciEvrakDevretPage;

import java.util.Random;

import static data.TestData.*;


public class EvrakPaylasmaTest extends BaseTest {

    PaylastiklarimPage paylastiklarimPage;
    BenimlePaylasilanlarPage benimlePaylasilanlarPage;
    TaslakEvraklarPage taslakEvraklarPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    EvrakOlusturPage evrakOlusturPage;
    KullaniciEvrakDevretPage kullaniciEvrakDevretPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    ImzaladiklarimPage imzaladiklarimPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login("mbozdemir", "123");
        paylastiklarimPage = new PaylastiklarimPage();
        benimlePaylasilanlarPage = new BenimlePaylasilanlarPage();
        taslakEvraklarPage = new TaslakEvraklarPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        evrakOlusturPage = new EvrakOlusturPage();
        kullaniciEvrakDevretPage = new KullaniciEvrakDevretPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
    }

    @Test(enabled = true, description = "TC1881 : Evrak paylaşımını durdurma")
    public void TC1881() {

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = paylasilacakUser;
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("TC1881 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilan, "")
                .paylasTabTikla()
                .paylasimiDurdur()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(paylasilan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);


        logout();
        login("huser", "123");

        benimlePaylasilanlarPage
                .openPage()
                .durdurulmusPaylasimlarSec()
                .filtrele()
                .evrakSec(paylasan)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotEklemeButonuAktifOlmali(false);
    }

    @Test(enabled = true, description = "TC1882 : Paylaştıklarım listesinden evrak paylaşma")
    public void TC1882() {

        String paylasilanKullanici = "Huser TUMER";
        String paylasanKisi = "Mehmet BOZDEMİR";
        String paylasanKisiNotAciklamasi = "TC1882 : Paylaştıklarım listesinden evrak paylaşma";
        String text = "TC1882 "+ getSysDate();

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String tabPaylaşılanlar = "Paylaşılanlar";
        String bi̇ri̇m = "OPTİİM BİRİM";
        String durumu = "Paylaşımda";

        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .konuDoldur(text)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturSayfaKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSecKonuyaGore(text)
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilanKullanici)
                .paylasanAciklamaDoldur("TC1882 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        String[] paylasilacakKullanicilar = new String[]{
                "Optiim TEST1",
                "Optiim TEST2",
                "Optiim TEST3"
        };


        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(text)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasTabTikla()
                .paylasKisiSec(paylasilacakKullanicilar)
                .paylasimAciklamaYaz(paylasanKisiNotAciklamasi)
                .paylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(text)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi, "", paylasanKisiNotAciklamasi)
                .evrakOnizlemeTabSec(tabPaylaşılanlar)
                .paylasilanKontrolTumKullanıcılıar(paylasilacakKullanicilar, durumu);

        logout();

        login("test1","123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSecKonuyaGore(text)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(paylasanKisi,"",paylasanKisiNotAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeSil(text)
                .evrakNotuGirVeKaydet(text);

        logout();
        login("mbozdemir","123");

        paylastiklarimPage
                .openPage()
                .evrakSecKonuyaGore(text)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol("OPTİİM TEST1","",text);
    }

    @Test(enabled = true, description = "TC1877 : Paylaşılan evrakın geri alınması")
    public void TC1877() {

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String paylasan = "Mehmet BOZDEMİR";
        String[] paylasilanlar = new String[]{"Zübeyde TEKİN", "Optiim TEST", "Esin Gül KARABACAKOĞLU"};
        String[] paylasimdanGeriAlanicaklar = new String[]{"Zübeyde TEKİN", "Esin Gül KARABACAKOĞLU"};

        String paylasilmaTarihi = "";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilanlar)
                .paylasanAciklamaDoldur("TC1877 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage();

        paylasilmaTarihi = paylastiklarimPage.evrakPaylasimTarihiGetir(paylasilanlar, evrakKonuKodu, "","");

        paylastiklarimPage
                .evrakSec(paylasilanlar, evrakKonuKodu, "","")
                .paylasimdanGeriAlTabSec()
                .paylasimdanGeriAl(paylasimdanGeriAlanicaklar);


        logout();
        login("ztekin", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonuKodu, paylasilmaTarihi,false);


        logout();
        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .paylasilanlarKontrol(paylasan, evrakKonuKodu,paylasilmaTarihi,true);

    }

    @Test(enabled = true, description = "TC1876 : Evrakı paylaşmada alan kontrolleri")
    public void TC1876() {

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasKisiDoldur(paylasilacakUser)
                .paylasanAciklamaDoldur("TC1876 case i için evrak oluşturuldu ve paylaşıldı.")
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali();

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu,"", paylasilacakUser,"")
                .paylasTabTikla()
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");


        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasimAciklamaYaz("")
                .paylas()
                .islemMesaji().dikkatOlmali("Açıklama girilmesi zorunludur!");

        paylastiklarimPage
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasilanKisileriTemizle()
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().dikkatOlmali("Evrakın paylaşılacağı Kullanıcıyı seçiniz!");

        paylastiklarimPage
                .paylasKisiSec(paylasilacakUser)
                .paylasimAciklamaYaz("yeni açkılmala")
                .paylas()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


    }

    @Test(enabled = true, description = "TC1876A : Taslak Evrakı kullanıcı ile paylaşma (Tümü aksiyonu ile)")
    public void TC1876A() {

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";
        String paylasilacakUser = "Huser TUMER";

        String paylasan = "Mehmet BOZDEMİR";
        String paylasilan = paylasilacakUser;
        String basariMesaji = "İşlem başarılıdır!";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        //String evrakGidecegiYer = "Optiim TEST1(B) / Optiim TEST2(G)";
        //String evrakTarihSaat = "05.12.2017 15:45";

        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC1904 : Evrak paylaşmada not kontrolü")
    public void TC1904() {

        String evrakKonu = "Bilimsel ve Kültürel Organizasyonlar, Toplantılar";
        String evrakGidecegiYer = "Optiim TEST2(B) / Optiim TEST1(G)";
        String evrakGonderen = "Mehmet BOZDEMİR";
        String evrakTarih = "05.12.2017 10:47";
        String basariMesaji = "İşlem başarılıdır!";
        String evrakiPaylasan = "Mehmet BOZDEMİR";

        String notEkleyen = "Optiim TEST [Ağ (Network) Uzman Yardımcısı]";
        String eklenenNot = "Optiim evrak notuuuuuussds";
        String paylasilan = "Optiim TEST";
        String evrakNo = "9132";

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .paylasTabTikla()
                .paylasKisiSec("Optiim TEST")
                .paylasimAciklamaYaz("Deneme bir açıklama girdim.")
                .paylas()
                .islemMesaji().basariliOlmali(basariMesaji);


        logout();

        login("optiim", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, evrakNo)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(eklenenNot);

        logout();

        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, evrakNo, paylasilan, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, "", eklenenNot);

        parafBekleyenlerPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakGonderen, evrakTarih)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(notEkleyen, "", eklenenNot, false);


    }
    //TODO Can Şeker yazmıştır
    @Test(enabled = true, description = "TC1905: Evrak paylaşma yetkisi olmayan kullanıcıda evrak paylaşma kontrolü")
    public void TC1905() throws InterruptedException{

        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaj1 = "Gizlilik kleransınız evrakın gizlilik derecesini görüntülemek için yeterli değildir.";
        String konuKodu = "Diğer";
        String konuKoduRandom = "TC-2227-" + createRandomNumber(10);
        String evrakTarihi = getSysDateForKis();
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String gizlilikDerecesi = "Gizli";
        String evrakSayiSag = createRandomNumber(10);
        String kisi = "Zübeyde Tekin";
        String kisi2 = "Yasemin Çakıl Akyol";
        String aciklama = createRandomText(15);
        String birim = "TEST HASAN BİRİMİ";
        String anaBirim = "Yazılım Geliştirme Direktörlüğü";
        //TODO Pre Condition Gelen Evraklar sayfası data oluşturmakta
        login(username2, password2);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandom)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .geldigiKurumDoldurLovText2(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriKisiDoldur(kisi)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
        //TODO

        login(username2, password2);

        gelenEvraklarPage
                .openPage()
                .evrakSec(konuKoduRandom,kurum,evrakTarihi,evrakSayiSag)
                .paylas()
                .paylasBirim()
                .paylasKisiSecBirim(kisi2,birim)
                .paylasanAciklamaDoldur(aciklama)
                .paylasIcPaylas()
                .islemMesaji().basariliOlmali(basariMesaji);

        paylastiklarimPage
                .openPage()
                .evrakSec(konuKoduRandom,evrakTarihi)
                .evrakNotlariTabAc()
                .evrakNotlariAciklamaGorme(aciklama)
                .paylasilanlarTabAc()
                .paylasilanlarKullaniciGorme(kisi2);

        login(username3, password3);

        benimlePaylasilanlarPage
                .birimSec(birim)
                .openPage()
                .evrakSec(kisi, evrakTarihi, konuKoduRandom)
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(aciklama)
                .evrakNotuKontrol(kisi2, "2017", aciklama);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

        imzaladiklarimPage
                .openPage()
                .evrakSec()
                .paylasButonGelmedigiGorme("Paylaş");

        benimlePaylasilanlarPage
                .birimSec(anaBirim);
    }

    @Test(enabled = true, description = "TC2195 : Cevap evrakını paylaşma")
    public void TC2195() {

        String evrakKonu = "Yurtiçi Projeler";
        String evrakGidecegiYer = "Başbakan Başmüşavirleri(G)";
        String evrakTarihSaat = "05.12.2017 16:42";

        String evrakAciklamasi = "Cevap evrakını paylaşma NOT 1";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Cevap evrakını paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonu, evrakGidecegiYer, evrakTarihSaat)
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2194 : İçeriğinde kişisel şablon olan evrakı paylaşma")
    public void TC2194() {

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Optiim TEST1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true);

        evrakOlusturPage
                .sablonIslemleriTabAc()
                .sablonTuruSec("Kişi")
                .sablonAdiDoldur(evrakSablonAdi)
                .evrakiYeniSablonOlarakKaydet()
                .kisiselSablonuEvrakaUygula(evrakSablonAdi);


        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2192 : İçeriğinde form olan evrakı paylaşma")
    public void TC2192() {


        String evrakAciklamasi = "İçeriğinde form olan evrakı paylaşma";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "İçeriğinde form olan evrakı paylaşma yenii açıklama notu";
        String yeniPaylasan = "Optiim TEST1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String formSablonAdi = "Optiim form şablonu";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .evrakTuruSec("Form")
                .formSablonuSec(formSablonAdi)
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC2193 : İçeriğinde birim içerik şablonu olan evrakı paylaşma")
    public void TC2193() {

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Optiim TEST1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String kullanacakBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true);

        evrakOlusturPage
                .sablonIslemleriTabAc()
                .sablonTuruSec("Birim")
                .sablonAdiDoldur(evrakSablonAdi)
                .kullanacakBirimSec(kullanacakBirim)
                .evrakiYeniSablonOlarakKaydet()
                .birimSablonuEvrakaUygula(evrakSablonAdi);


        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);



    }

    // DÜZENLENECEK!!
    @Test(enabled = true, description = "TC2197 : Devredilen evrakı paylaşma")
    public void TC2197() {

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        /*
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");
        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();
        */

        kullaniciEvrakDevretPage
                .openPage()
                .devredecekKisiSec("Mehmet BOZDEMİR")
                .listele()
                .panelAc("Taslak Evraklar")
                .taslakEvrakSec(evrakKonuKodu, "")
                .devret()
                .devralacakKisiSec("huser TUMER")
                .aciklamaDoldur("evrak huser e devredildi.")
                .devretTamam();

        logout();
        login("huser", "123");

        String evrakAciklamasi = "Devredilen evrakı paylaşma NOT 1";
        String evrakiPaylasan = "Huser TUMER";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Devredilen evrakı paylaşma NOT 2";
        String yeniPaylasan = "Optiim TEST1";


        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("optiimtest2", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }

    @Test(enabled = true, description = "TC1876 : İşlem bekleyen Evrakı kullanıcılarla paylaşma")
    public void TC1876B() {

        String evrakAciklamasi = "evrak açıklaması için kontrol";
        String evrakiPaylasan = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";

        String yeniEvrakPaylasimNotu = "Optiiim paylaştıııı";
        String yeniPaylasan = "Optiim TEST1";

        String evrakKonuKodu = "Entegrasyon İşlemleri";
        String kaldirilacakKlasorler = "Diğer";
        String kullaniciAdi = "Mehmet BOZDEMİR [Antalya İl Müdürü]";

        String evrakSablonAdi = "YeniŞablon" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(evrakKonuKodu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(kullaniciAdi, "İmzalama");

        evrakOlusturPage
                .kaydet(true);

        evrakOlusturPage
                .sablonIslemleriTabAc()
                .sablonTuruSec("Kişi")
                .sablonAdiDoldur(evrakSablonAdi)
                .evrakiYeniSablonOlarakKaydet()
                .kisiselSablonuEvrakaUygula(evrakSablonAdi);


        evrakOlusturPage
                .kaydet(true)
                .evrakOlusturPageKapat();

        taslakEvraklarPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", "")
                .paylasTabTikla()
                .paylasBirimTikla()
                .paylasKisiDoldur("Optiim TEST1")
                .paylasKisiDoldur("Optiim TEST2")
                .paylasanAciklamaDoldur(evrakAciklamasi)
                .paylasPaylasGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        String paylasilanlar = "Optiim TEST1 / Optiim TEST2";

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "", evrakAciklamasi)
                .evrakOnizlemeTabSec("Paylaşılanlar")
                .paylasilanKontrol("Optiim TEST1", "Optiim Birim", "Paylaşımda", "")
                .paylasilanKontrol("Optiim TEST2", "Optiim Birim", "Paylaşımda", "");

        logout();

        login("test1", "123");

        benimlePaylasilanlarPage
                .openPage()
                .evrakSec(evrakiPaylasan, "", evrakKonuKodu, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(evrakiPaylasan, "2017", evrakAciklamasi)
                .evrakNotuEkle()
                .evrakNotuGirVeKaydet(yeniEvrakPaylasimNotu);

        logout();

        login("mbozdemir", "123");

        paylastiklarimPage
                .openPage()
                .evrakSec(evrakKonuKodu, "", paylasilanlar, "")
                .evrakOnizlemeTabSec("Evrak Notları")
                .evrakNotuKontrol(yeniPaylasan, "2017", yeniEvrakPaylasimNotu);


    }




}