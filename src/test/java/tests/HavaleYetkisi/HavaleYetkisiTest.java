package tests.HavaleYetkisi;

import common.BaseTest;
import common.ReusableSteps;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.RolYonetimiPage;

public class HavaleYetkisiTest extends BaseTest {

    RolYonetimiPage rolYonetimiPage;
    MainPage mainPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;
    ReusableSteps reusableSteps;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;
    HavaleEttiklerimPage havaleEttiklerimPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;

    String[] rolAdi;


    @BeforeMethod
    public void BeforeTest() {
        rolYonetimiPage = new RolYonetimiPage();
        mainPage = new MainPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();
        reusableSteps = new ReusableSteps();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
    }


    @Step("Havale İşlemleri Tüm Birimleri Görebilme aksiyonlu rol oluşturma")
    public void preTS2253(String yenirolad, String eklenecekAksiyon) throws InterruptedException {

        String degerKod = createRandomNumber(8);
        String kullaniciAdi = "username21g";

        rolYonetimiPage.openPage();
        rolYonetimiPage.btnYeniRolekle()
                .txtYeniRolAd(yenirolad)
                .txtYeniRolKısaAd(yenirolad)
                .txtYeniRolEtiket("TS2253")
                .txtRolDegerKod(degerKod)
                .txtRolYetkiOnceligi(3)
                .btnYeniRolKaydetme();

        rolYonetimiPage.txtRolAdArama(yenirolad)
                .btnRolArama()
//                .tblRolListeSecim(yenirolad)
                .tblRolListeSecimAksiyonButonu(yenirolad)
                .btnYeniAksiyonEkle()
                .yeniAksiyonİliskilendirmeSorgulamaveFiltrelemeTabAc()
                .txtDialogAksiyonad(eklenecekAksiyon)
                .btnDialogAksiyonAra()
                .btnDialogselectAction(eklenecekAksiyon)
                .btnDialogAksiyonEkle();

//        kullaniciYonetimiPage
//                .openPage()
//                .kullaniciAdiDoldur(kullaniciAdi)
//                .ara()
//                .kullaniciListesiGuncelle(kullaniciAdi)
//                .rolListeriEkle()
//                .yeniRolIliskilendirmeKullaniciRolSec(yenirolad)
//                .yeniRolIliskilendirmeKaydet()
//                .kullaniciGuncelleKaydet();

    }

    @Step("Havale Ettiklerim evrak oluşturulur.")
    public void kisiyeHavaleYap() {

        gelenEvraklarPage
//                .tabloKonuyaGoreEvrakAc(konu)
//                .evrakOnizlemeButonTikla("Havale Yap")
//                .havaleYapKisiDoldur2("Username26 TEST")
//                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .havaleYapGonder();

    }

    @Step("Havale Ettiklerim evrak oluşturulur.")
    public void teslimAldiklarimBirimeHavaleYap() {

        teslimAlinanlarPage
//                .tabloKonuyaGoreEvrakAc(konu)
//                .evrakOnizlemeButonTikla("Havale Yap")
               .birimeHavaleSil()
                .birimeHavaleDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
//                .havaleYapKisiDoldur("Username22n TEST")
//                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .havaleYapGonder2();

    }


    @Step("Birime havale edilen evrak oluşturulur.")
    public void birimeHavaleYap(String konu) {

        havaleEttiklerimPage
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeButonTikla("Havale Yap")
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla2("Birim")
                .havaleYapBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .havaleYapGonder();

    }

    @Step("Birime havale edilen evrak oluşturulur.")
    public void teslimAlveHavaleEtBirimeHavaleYap() {

        teslimAlinmayiBekleyenlerPage
//                .konuyaGoreEvrakSec(konu)
//                .evrakOnizlemeButonTikla("Havale Yap")
//                .havaleYapBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
//                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .birimeHavaleSil()
                .birimeHavaleDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .teslimAl();

    }


    @Step("PreContion : \"{eklenecekAksiyon}\" aksiyonlu rollere eklendi")
    public void preconRollereAksiyonEkleme(String[] rolAdi, String eklenecekAksiyon) throws InterruptedException {

        String kullaniciAdi = "username21g";

        rolYonetimiPage
                .openPage();

        for (int i = 0; i < rolAdi.length; i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .btnYeniAksiyonEkle()
                    .yeniAksiyonİliskilendirmeSorgulamaveFiltrelemeTabAc()
                    .txtDialogAksiyonad(eklenecekAksiyon)
                    .btnDialogAksiyonAra()
                    .btnDialogselectAction(eklenecekAksiyon);
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Havale İşlemleri Tüm Birimleri Görebilme aksiyonunu kaldırma")
    public void TS2253() throws InterruptedException {
        String aksiyonAdi = "Havale İşlemleri Tüm Birimleri Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);
//        login(TestData.userMbozdemir);

//        preTS2253(rolad, aksiyonAdi);

        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi = mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconRollereAksiyonEkleme(rolAdi, aksiyonAdi);

        login(TestData.user21g);
        rolYonetimiPage
                .openPage()
                .rolYonetimiSayfaKontrolu();

        for (int i = 0; i < rolAdi.length; i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .rolListesiKontrolu(rolAdi[i])
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .aksiyonListesiKontrol()
                    .aksiyonListesiAdDoldur(aksiyonAdi)
                    .aksiyonListesiKontol(aksiyonAdi)
                    .aksiyonListesiRolSil(aksiyonAdi)
                    .islemOnayPopupKapat(mesaj)
                    .islemMesaji().isBasarili();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2250 : Havale İşlemleri Tüm Kullanıcıları Görebilme aksiyonunu kaldırma")
    public void TS2250() throws InterruptedException {

        String aksiyonAdi = "Havale İşlemleri Tüm Kullanıcıları Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
//        String guncelBirim = "TS2200 Alt Birim";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);
//        login(TestData.username27);


        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi = mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconRollereAksiyonEkleme(rolAdi, aksiyonAdi);

        login(TestData.user21g);
//        login(TestData.username27);

        rolYonetimiPage
                .openPage()
                .rolYonetimiSayfaKontrolu();

        for (int i = 0; i < rolAdi.length; i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .rolListesiKontrolu(rolAdi[i])
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .aksiyonListesiKontrol()
                    .aksiyonListesiAdDoldur(aksiyonAdi)
                    .aksiyonListesiKontol(aksiyonAdi)
                    .aksiyonListesiRolSil(aksiyonAdi)
                    .islemOnayPopupKapat(mesaj)
                    .islemMesaji().isBasarili();
        }
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0614 : Tüm birimlere havale yetkisinin verilmesi")
    public void TS0614() throws InterruptedException {

//        login(TestData.user21g);

        login(TestData.username27);

        String konu = "TS0614 " + createRandomNumber(8);
        String konu2 = "TS0614 " + createRandomNumber(8);
//        String konu = "TS0614 15603241";
//        String konu2 = "TS0614 14361052";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Username27 TEST";
        String aksiyonAdi = "Havale İşlemleri Tüm Birimleri Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";
        String btnHavaleYap = "Havale Yap";
        String btnTeslimAlveHavaleYap = "Teslim Al ve Havale Et";
        String btnBirim = "Birim";
        String btnTumu = "Tümü";
        String ustBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String ustBirim2 = "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR YARDIMCISI";

//        String menuName = "Profil";
//        mainPage
//                .userMenuAc()
//                .userMenuKontrol(menuName)
//                .userMenuMenuSec(menuName);
//
//        rolAdi = mainPage.profildenRolAdiAlma(guncelBirim);
//        mainPage.profilEkraniKapat();
//
//        preconRollereAksiyonEkleme(rolAdi, aksiyonAdi);

        reusableSteps.gelenEvraklarEvrakOlustur(konu, kurum, birim);

        login(TestData.username27);

        reusableSteps.gelenEvraklarEvrakOlustur(konu2, kurum, birim);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapBirimAlanindaButonKontrolu(btnBirim, true)
                .havaleYapBirimAlanindaButonTikla(btnBirim)
                .havaleYapBirimAlanindaButonKontrolu(btnTumu, true)
                .havaleYapAlanindaBirimSec(ustBirim)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .topluHavale()
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim);

        kisiyeHavaleYap();

        havaleEttiklerimPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .havaleYapBirimDoldur(ustBirim)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapBirimAlanindaButonKontrolu(btnBirim, true)
                .havaleYapBirimAlanindaButonTikla(btnBirim)
                .havaleYapBirimAlanindaButonKontrolu(btnTumu, true)
                .havaleYapAlanindaBirimSec(ustBirim)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        birimeHavaleYap(konu);
        birimeHavaleYap(konu2);

        login(TestData.username27YGD);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnTeslimAlveHavaleYap)
                .evrakOnizlemeButonTikla(btnTeslimAlveHavaleYap)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim2)
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnTeslimAlveHavaleYap, true)
                .btnTikla(btnTeslimAlveHavaleYap)
                .havaleYapBirimAlanindaButonKontrolu(btnBirim, true)
                .havaleYapBirimAlanindaButonTikla(btnBirim)
                .havaleYapBirimAlanindaButonKontrolu(btnTumu, true)
                .havaleYapAlanindaBirimSec(ustBirim2)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .secilenTeslimAlVeHavaleEt()
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim2);

        teslimAlveHavaleEtBirimeHavaleYap();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim2)  //ygdnın ustunu yaz
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapBirimAlanindaButonKontrolu(btnBirim, true)
                .havaleYapBirimAlanindaButonTikla(btnBirim)
                .havaleYapBirimAlanindaButonKontrolu(btnTumu, true)
                .havaleYapAlanindaBirimSec(ustBirim2)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinanlarPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .topluHavale()
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .birimeHavaleDoldur(ustBirim2);

        teslimAldiklarimBirimeHavaleYap();

        birimHavaleEdilenlerPage
                .openPage()
                .konuyaGoreTablodanEvrakSecme(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, true)
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapBirimAlanindaButonKontrolu(btnBirim, true)
                .havaleYapBirimAlanindaButonTikla(btnBirim)
                .havaleYapBirimAlanindaButonKontrolu(btnTumu, true)
                .havaleYapAlanindaBirimSec(ustBirim2)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        birimHavaleEdilenlerPage
                .konuyaGoreTablodanEvrakSecme(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, true)
                .evrakOnizlemeHavaleYapBirimAlaniButonTikla(btnBirim)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnTumu, true)
                .dagitimBilgileriBirimDoldur(ustBirim2);

    }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true
//            ,dependsOnMethods = {"TS2250"}
            , description = "TS0597 : Tüm kullanıcılara havale yetkisi olmayan kullanıcının ekran kontrolü. ")
    public void TS0597() throws InterruptedException {

        login(TestData.user21g);

        String konu = "TS0597 " + createRandomNumber(8);
        String konu2 = "TS0597 " + createRandomNumber(8);
//        String konu = "TS0597 15162430";
//        String konu2 = "TS0597 13254160";
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Username21g TEST";
        String btnHavaleYap = "Havale Yap";
        String btnTeslimAlveHavaleYap = "Teslim Al ve Havale Et";
        String btnBirim = "Birim";


        reusableSteps.gelenEvraklarEvrakOlustur(konu, kurum, birim);

        login(TestData.user21g);

        reusableSteps.gelenEvraklarEvrakOlustur(konu2, kurum, birim);

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapKisiAlanindaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        gelenEvraklarPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .topluHavale()
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false);

        kisiyeHavaleYap();

        havaleEttiklerimPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapKisiAlanindaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");


//        havaleEttiklerimPage
//                .konuyaGoreEvrakIsaratle(konu)
//                .konuyaGoreEvrakIsaratle(konu2)
//                .topluHavale()
//                .evrakOnizlemeButonKontrolu(btnBirim, false);

        birimeHavaleYap(konu);
        birimeHavaleYap(konu2);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnTeslimAlveHavaleYap)
                .evrakOnizlemeButonTikla(btnTeslimAlveHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false)
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnTeslimAlveHavaleYap, true)
                .btnTikla(btnTeslimAlveHavaleYap)
                .havaleYapKisiAlanindaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .secilenTeslimAlVeHavaleEt()
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false);

        teslimAlveHavaleEtBirimeHavaleYap();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapKisiAlanindaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinanlarPage
                .konuyaGoreEvrakIsaratle(konu)
                .konuyaGoreEvrakIsaratle(konu2)
                .topluHavale()
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false);

        teslimAldiklarimBirimeHavaleYap();
//        birimeHavaleYap(konu);

        birimHavaleEdilenlerPage
                .openPage()
                .konuyaGoreTablodanEvrakSecme(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapKisiAlaniButonKontrolu(btnBirim, false)
                .evrakSecIcerikGoster(konu, true);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapKisiAlanindaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0614 : Tüm birimlere havale yetkisi olmayan kullanıcının ekran kontrolü")
    public void TS0615() throws InterruptedException {


        login(TestData.user21g);

        String konu = "TS0614 " + createRandomNumber(8);
//        String konu = "TS0597 16403152";
        String konu2 = "TS0614 " + createRandomNumber(8);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Username21g TEST";
        String aksiyonAdi = "Havale İşlemleri Tüm Birimleri Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";


        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi = mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconRollereAksiyonEkleme(rolAdi, aksiyonAdi);


        reusableSteps.gelenEvraklarEvrakOlustur(konu, kurum, birim);

        String btnHavaleYap = "Havale Yap";
        String btnTeslimAlveHavaleYap = "Teslim Al ve Havale Et";
        String btnBirim = "Birim";

        gelenEvraklarPage
                .openPage()
                .tabloKonuyaGoreEvrakAc(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeHavaleYapBirimAlaniButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

    }

}
