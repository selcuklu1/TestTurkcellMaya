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
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.tabs.AltTabs;
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

    String degerKod = createRandomNumber(8);
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


    @Step("Havale işlemleri Tüm birimleri görebilme aksiyonlu rol oluşturma")
    public void preTS2253(String yenirolad, String eklenecekAksiyon) throws InterruptedException {

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
    public void kisiyeHavaleYap(String konu) {

        gelenEvraklarPage
                .tabloKonuyaGoreEvrakAc(konu)
                .evrakOnizlemeButonTikla("Havale Yap")
                .havaleYapKisiDoldur("Username22n TEST")
                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .havaleYapGonder();

    }

    @Step("Birime havale edilen evrak oluşturulur.")
    public void birimeHavaleYap(String konu) {

        havaleEttiklerimPage
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeButonTikla("Havale Yap")
                .havaleYapBirimDoldur("YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ")
                .havaleYapAciklamaDoldur("Havale Yap acikalama")
                .havaleYapGonder();

    }

    @Step("PreContion : Havale işlemleri Tüm birimleri görebilme aksiyonlu rol oluşturma")
    public void preconTümBirimleriGorebilmeEkle(String[] rolAdi, String eklenecekAksiyon) throws InterruptedException {

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

        preconTümBirimleriGorebilmeEkle(rolAdi, aksiyonAdi);

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
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);

        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi = mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconTümBirimleriGorebilmeEkle(rolAdi, aksiyonAdi);

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
    @Test(enabled = true, description = "TS0597 : Tüm kullanıcılara havale yetkisi olmayan kullanıcının ekran kontrolü. ")
    public void TS0597() throws InterruptedException {

        login(TestData.user21g);

        String konu = "TS0597 " + createRandomNumber(8);
//        String konu = "TS0597 16403152";
        String konu2 = "TS0597 " + createRandomNumber(8);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "Username21g TEST";


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
                .evrakOnizlemeButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapAlanındaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        kisiyeHavaleYap(konu);

        havaleEttiklerimPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapAlanındaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        birimeHavaleYap(konu);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnTeslimAlveHavaleYap)
                .evrakOnizlemeButonTikla(btnTeslimAlveHavaleYap)
                .evrakOnizlemeButonKontrolu(btnBirim, false)
                .konuyaGoreIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnTeslimAlveHavaleYap, true)
                .btnTikla(btnTeslimAlveHavaleYap)
                .havaleYapAlanındaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        teslimAlinmayiBekleyenlerPage
                .konuyaGoreEvrakSec(konu)
                .btnTikla("Teslim Al")
                .confirmDialog().confirmEvetTikla();

        teslimAlinanlarPage
                .openPage()
                .konuyaGoreEvrakSec(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeButonKontrolu(btnBirim, false)
                .konuyaGoreEvrakIcerikGoster(konu);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapAlanındaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");

        birimeHavaleYap(konu);

        birimHavaleEdilenlerPage
                .openPage()
                .konuyaGoreTablodanEvrakSecme(konu)
                .evrakOnizlemeKontrolu()
                .evrakOnizlemeButonKontrolu(btnHavaleYap)
                .evrakOnizlemeButonTikla(btnHavaleYap)
                .evrakOnizlemeButonKontrolu(btnBirim, false)
                .evrakSecIcerikGoster(konu,true);

        evrakDetayiPage
                .sayfaAcilmali()
                .butonKontrolu(btnHavaleYap, true)
                .btnTikla(btnHavaleYap)
                .havaleYapAlanındaButonKontrolu(btnBirim, false)
                .evrakDetayiSayfasiKapat()
                .islemPenceresiKapatmaOnayiPopup("Kapat");
    }

    

}
