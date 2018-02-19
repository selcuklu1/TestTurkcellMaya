package tests.KurumYonetimi;

import common.BaseTest;
import data.TestData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GidenEvrakKayitPage;
import pages.ustMenuPages.KurumYonetimiPage;

import java.util.Random;


public class KurumYonetimiTest extends BaseTest {

    KurumYonetimiPage kurumYonetimiPage;
    GelenEvrakKayitPage gelenEvrakKayitPage;
    EvrakOlusturPage evrakOlusturPage;
    GidenEvrakKayitPage gidenEvrakKayitPage;

    @BeforeMethod
    public void loginBeforeTests() {

        kurumYonetimiPage = new KurumYonetimiPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        evrakOlusturPage = new EvrakOlusturPage();
        gidenEvrakKayitPage = new GidenEvrakKayitPage();

    }

    @Test(enabled = true, description = "TS01459 : Kurum bilgisi güncelleme")
    public void TS1459() {

        String yeniKurumAdi1 = "Yenikurum" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String idariBirimKimlikKodu = (new Random().nextInt((900000 - 100000) + 1) + 100000) + "";
        String ustKurum = "Maliye Bakanlığı";
        String hitap = "yeniHitap";

        String kontrolEdilecekGeregiDetay = "";

        String mobilTelNo = "5444444444";
        String telefonNo = "5444444445";
        String adres = "yeni adersim";
        String ulke = "TÜRKİYE";
        String il = "İSTANBUL";
        String ePosta = "xxx@xxx.com";
        String kepAdresi = "xxx@turksat.com.tr";

        login("mbozdemir", "123");
        kurumYonetimiPage
                .openPage()
                .yeniKurumEkle()
                .ozelHitapSec(true)
                .hitapDoldur(hitap)
                .kurumAdiDoldur(yeniKurumAdi1)
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum)
                .kaysisteYerAlmiyorSec(true)
                .kaysisteYerAlmiyorSec(false);

        kurumYonetimiPage
                .yeniIletisimBilgisiEkle()
                .mobilTelNoDoldur(mobilTelNo)
                .telefonNoDoldur(telefonNo)
                .adresDoldur(adres)
                //.ulkeDoldur(ulke)
                .ilDoldur(il)
                .ePostaDoldur(ePosta)
                .iletisimBilgisiKaydet()
                .kepAdresiBilgisiEkle()
                .popupKepAdresiDoldur(kepAdresi)
                .popupKaydet()
                .kurumKaydet()
                .islemMesaji().basariliOlmali();


        String guncellenecekKurumAdi = yeniKurumAdi1;
        String yeniKurumAdi = "kurumm" + (new Random().nextInt((9000 - 1000) + 1) + 1000);

        String isTelefonNo = "5444444446";
        String faxNumarasi1 = "5444444447";
        String faxNumarasi2 = "5444444448";
        String ilce = "Avcılar";
        String webAdresi = "example.com";

        String guncellenecekKepAdresi = kepAdresi;
        String yeniKepadresi = "hihihaha2223@kepadresim.com";
        String kepHizmetSaglayicisi = "KEPKUR";

        kurumYonetimiPage
                .sorgulaKurumDoldur(guncellenecekKurumAdi)
                .ara()
                .kurumGuncelle(guncellenecekKurumAdi)
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum);

        kontrolEdilecekGeregiDetay = kurumYonetimiPage.ustKurumGetir() + " | " + yeniKurumAdi;

        kurumYonetimiPage
                .kurumAdiDoldur(yeniKurumAdi)
                .iletisimGuncelle()
                .mobilTelNoDoldur(mobilTelNo)
                .telefonNoDoldur(telefonNo)
                .isTelefonNoDoldur(isTelefonNo)
                .faxNumarasi1Doldur(faxNumarasi1)
                .faxNumarasi2Doldur(faxNumarasi2)
                .adresDoldur(adres)
                .ulkeDoldur(ulke)
                .ilDoldur(il)
                .ilceDoldur(ilce)
                .ePostaDoldur(ePosta)
                .webAdresiDoldur(webAdresi)
                .iletisimBilgisiKaydet()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .kepAdresiGuncelle(guncellenecekKepAdresi, null)
                .kepAdresiDoldur(yeniKepadresi)
                .kepHizmetSaglayiciSec(kepHizmetSaglayicisi)
                .kepAdresiBilgileriKaydet()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .kepAdresiKontrol(yeniKepadresi, 0, true)
                .kurumKaydet()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .kurumHiyerarsisiniGuncelle()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .filtrePanelAc()
                .sorgulaKurumDoldur(yeniKurumAdi)
                .ara()
                .kurumKontrolEt(yeniKurumAdi, true)
                .panelKapat();

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText(yeniKurumAdi)
                .solEvrakSayiKontrol(idariBirimKimlikKodu);
        gelenEvrakKayitPage.panelKapat(false);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Kurum")
                .geregiTreeKontrolEt(yeniKurumAdi, true)
                .bilgiSecimTipiSec("Kurum")
                .bilgiSec(yeniKurumAdi);

        evrakOlusturPage
                .editorTabAc()
                .hitapKontrol(hitap);

        evrakOlusturPage
                .evrakOlusturPageKapat();

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("Kurum")
                .geregiAlanindaDegerKontrolu(yeniKurumAdi, true)
                .panelKapat(false);

        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur(yeniKurumAdi)
                .ara()
                .kurumGuncelle(yeniKurumAdi)
                .kepAdresiKullaniyorSec(false)
                .kurumKaydet()
                .islemMesaji().basariliOlmali();
        kurumYonetimiPage.panelKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Kurum")
                .geregiSec(yeniKurumAdi)
                .geregiSecilenKontrol(yeniKurumAdi, kontrolEdilecekGeregiDetay, "Adi Posta");

    }

    @Test(enabled = true, description = "TS01109 : Kurum tanımlama ve kontrolü")
    public void TS1109() {

        String yeniKurumAdi = "Yenikurum" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String idariBirimKimlikKodu = (new Random().nextInt((900000 - 100000) + 1) + 100000) + "";
        String yeniKurumKisaAdi = "KISA" + yeniKurumAdi;
        String ustKurum = "Maliye Bakanlığı";
        String hitap = "Hitap" + yeniKurumAdi;

        String kontrolEdilecekGeregiDetay = "";

        String mobilTelNo = "5444444444";
        String telefonNo = "5444444445";
        String adres = "yeni adersim";
        String ulke = "TÜRKİYE";
        String il = "İSTANBUL";
        String ePosta = "xxx@xxx.com";
        String basariMesaji = "İşlem başarılıdır!";

        login("mbozdemir", "123");

        kurumYonetimiPage
                .openPage()
                .yeniKurumEkle()
                .alanKontrolu()
                .ozelHitapSec(true)
                .kisaAdiDoldur(yeniKurumKisaAdi)
                .hitapDoldur(hitap)
                .kurumAdiDoldur(yeniKurumAdi)
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum);

        kontrolEdilecekGeregiDetay = kurumYonetimiPage.ustKurumGetir() + " | " + yeniKurumAdi;

        kurumYonetimiPage
                .yeniIletisimBilgisiEkle()
                .mobilTelNoDoldur(mobilTelNo)
                .telefonNoDoldur(telefonNo)
                .adresDoldur(adres)
                .ulkeDoldur(ulke)
                .ilDoldur(il)
                .ePostaDoldur(ePosta)
                .iletisimBilgisiKaydet()
                .kurumKaydet()
                .islemMesaji().basariliOlmali();
        kurumYonetimiPage
                .kurumHiyerarsisiniGuncelle()
                .islemMesaji().basariliOlmali();
        kurumYonetimiPage
                .sorgulaKurumDoldur(yeniKurumAdi)
                .panelKapat();

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText(yeniKurumAdi)
                .solEvrakSayiKontrol(idariBirimKimlikKodu);

        gelenEvrakKayitPage
                .panelKapat(false);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Kurum")
                .geregiSec(yeniKurumAdi)
                .geregiSecilenKontrol(yeniKurumAdi, kontrolEdilecekGeregiDetay, "Adi Posta")
                .geregiTemizle()
                .geregiAlanindaKurumKisaAdSec(yeniKurumKisaAdi, yeniKurumAdi);

        evrakOlusturPage
                .editorTabAc()
                .hitapKontrol(hitap);

    }

    @Test(enabled = true, description = "TS01108 : Kurum Sorgulama")
    public void TS1108() {

        String pasifYapilacakKurum = "PasifKurum" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String pasifYapilacakKurumIdariKimlikkodu = (new Random().nextInt((900000 - 100000) + 1) + 100000) + "";

        String aktifKurumAdi = "Yenikurum" + (new Random().nextInt((9000 - 1000) + 1) + 1000);
        String aktifIdariBirimKimlikKodu = (new Random().nextInt((900000 - 100000) + 1) + 100000) + "";
        String ustKurum = "Maliye Bakanlığı";
        String hitap = "yeniHitap";

        String mobilTelNo = "5444444444";
        String telefonNo = "5444444445";
        String adres = "yeni adersim";
        String ulke = "TÜRKİYE";
        String il = "İSTANBUL";
        String ePosta = "xxx@xxx.com";

        String pasifKurumadi = "Maliye Bakanlığı";
        String pasifIdariBirimKimlikKodu = "24316011";
        String pasifYapilacakKurumUstKurum = "Maliye Bakanlığı";

        login("mbozdemir", "123");

        kurumYonetimiPage
                .openPage()
                .yeniKurumEkle()
                .ozelHitapSec(true)
                .hitapDoldur(hitap)
                .kurumAdiDoldur(aktifKurumAdi)
                .idariBirimKimlikKoduDoldur(aktifIdariBirimKimlikKodu)
                .ustKurumSec(ustKurum)
                .yeniIletisimBilgisiEkle()
                .mobilTelNoDoldur(mobilTelNo)
                .telefonNoDoldur(telefonNo)
                .adresDoldur(adres)
                .ulkeDoldur(ulke)
                .ilDoldur(il)
                .ePostaDoldur(ePosta)
                .iletisimBilgisiKaydet()
                .kurumKaydet()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .yeniKurumEkle()
                .ozelHitapSec(true)
                .hitapDoldur(hitap)
                .kurumAdiDoldur(pasifYapilacakKurum)
                .idariBirimKimlikKoduDoldur(pasifYapilacakKurumIdariKimlikkodu)
                .ustKurumSec(ustKurum)
                .yeniIletisimBilgisiEkle()
                .mobilTelNoDoldur(mobilTelNo)
                .telefonNoDoldur(telefonNo)
                .adresDoldur(adres)
                .ulkeDoldur(ulke)
                .ilDoldur(il)
                .ePostaDoldur(ePosta)
                .iletisimBilgisiKaydet()
                .kurumKaydet()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .kurumHiyerarsisiniGuncelle()
                .islemMesaji().basariliOlmali();

        kurumYonetimiPage
                .durumSec("Sadece Aktifler")
                .ara()
                .kurumTableKontrol(null, "Sadece Aktifler", true)

                .filtrePanelAc()
                .durumSec("Sadece Pasifler")
                .sorgulaKurumDoldur(pasifKurumadi)
                .ara()
                .kurumTableKontrol(null, "Sadece Pasifler", true)

                .filtrePanelAc()
                .durumSec("Tümü")
                .ara()
                .kurumTableKontrol(null, "Tümü", true)

                .filtrePanelAc()
                .sorgulaKurumDoldur(aktifKurumAdi)
                .durumSec("Sadece Aktifler")
                .ara()
                .kurumTableKontrol(null, "Sadece Aktifler", true)

                .filtrePanelAc()
                .sorgulaKurumDoldur(aktifIdariBirimKimlikKodu)
                .durumSec("Sadece Aktifler")
                .ara()
                .kurumTableKontrol(null, "Sadece Aktifler", true)

                .filtrePanelAc()
                .sorgulaKurumDoldur(pasifKurumadi)
                .durumSec("Sadece Pasifler")
                .ara()
                .kurumTableKontrol(null, "Sadece Pasifler", true)

                .filtrePanelAc()
                .sorgulaKurumDoldur(pasifIdariBirimKimlikKodu)
                .durumSec("Sadece Pasifler")
                .ara()
                .kurumTableKontrol(null, "Sadece Pasifler", true)
                .panelKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgiSecimTipiSec("Kurum")
                .bilgiSec(aktifIdariBirimKimlikKodu, true)
                .bilgiSec(aktifKurumAdi, true)
                .geregiSecimTipiSec("Kurum")
                .geregiSec(aktifIdariBirimKimlikKodu, true)
                .geregiSec(aktifKurumAdi, true);

        evrakOlusturPage
                .evrakOlusturPageKapat();

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("Kurum")
                .geldigiKurumDoldurLovText(aktifIdariBirimKimlikKodu)
                .geldigiKurumDoldurLovText(aktifKurumAdi)
                .panelKapat(false);

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("Kurum")
                .geregiDoldur(aktifIdariBirimKimlikKodu, true)
                .geregiDoldur(aktifKurumAdi, true)
                .bilgiSecimTipiSec("Kurum")
                .bilgiDoldur(aktifIdariBirimKimlikKodu, true)
                .bilgiDoldur(aktifKurumAdi, true)
                .panelKapat(false);

        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur(pasifYapilacakKurum)
                .ara()
                .kurumPasifYap(pasifYapilacakKurum)
                .sorgulaKurumDoldur(pasifYapilacakKurumUstKurum)
                .durumSec("Sadece Pasifler")
                .ara()
                .kurumTableKontrol(pasifYapilacakKurum, "Sadece Pasifler", false)
                .panelKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Kurum")
                .geregiTreeKontrolEt(pasifYapilacakKurum, false);

        evrakOlusturPage
                .evrakOlusturPageKapat();

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("Kurum")
                .geregiAlanindaDegerKontrolu(pasifYapilacakKurum, false)
                .bilgiSecimTipiSec("Kurum")
                .bilgiAlanindaDegerKontrolu(pasifYapilacakKurum, false)
                .panelKapat(false);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("Kurum")
                .geldigiKurumDegerGoruntulemeKontrolu(pasifYapilacakKurum, false)
                .panelKapat(false);


        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur(pasifYapilacakKurumUstKurum)
                .durumSec("Sadece Pasifler")
                .ara()
                .kurumAktifYap(pasifYapilacakKurum);
        kurumYonetimiPage.yeniKurumEkle();

        kurumYonetimiPage
                .filtrePanelAc()
                .sorgulaKurumDoldur(pasifYapilacakKurum)
                .durumSec("Sadece Aktifler")
                .ara()
                .kurumTableKontrol(pasifYapilacakKurum, "Sadece Aktifler", false)
                .panelKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("Kurum")
                .geregiTreeKontrolEt(pasifYapilacakKurum, true)
                .bilgiSecimTipiSec("Kurum")
                .bilgiSecimTipiTreeKontrolEt(pasifYapilacakKurum, true);

        evrakOlusturPage
                .evrakOlusturPageKapat();

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("Kurum")
                .geregiAlanindaDegerKontrolu(pasifYapilacakKurum, true)
                .bilgiSecimTipiSec("Kurum")
                .bilgiAlanindaDegerKontrolu(pasifYapilacakKurum, true)
                .panelKapat(false);

        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("Kurum")
                .geldigiKurumDegerGoruntulemeKontrolu(pasifYapilacakKurum, true)
                .panelKapat(false);

    }

    @Test(enabled = true, description = "TS2245 : Kurum tanımlamada alan kontrolleri")
    public void TS2245() {

        String kurumAdi = "TS2245_Kurum_" + getSysDate();
        String idariBirimKimlikKodu = getSysDate();
        String ustKurum = "Maliye Bakanlığı";
        String uyariMesaji = "Zorunlu alanları doldurunuz";
        String kokBirimDikkatMesaji = "Sistemde bir tane kök birim olabilir!";
        String ePostaDikkatMesaji = "Lütfen Türkçe karakter ve boşluk içermeyen, @ işareti ve nokta içeren geçerli bir e-mail giriniz!";
        String kepAdresiDikkatMesaji = "Girilen kep adresi geçersiz!";
        String kepAdresiBolBirakilamazDikkatMesaji = "Kep adresi boş bırakılamaz! Lütfen bir kep adresi ekleyiniz.";
        String il = "İSTANBUL";
        String ePostaYanlisFormat = "TS2245turksat.com.tr";
        String kepAdresiYanlisFormat = "TS2245turksat.com.tr";

        login(TestData.usernameOPTIIM, TestData.passwordOPTIIM);

        kurumYonetimiPage
                .openPage()
                .yeniKurumEkle()
                .alanKontrolu()
                .ustKurumSec(ustKurum)
                .kurumAdiDoldur(kurumAdi)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kurumYonetimiPage
                .iptal()
                .yeniKurumEkle()
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum)
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kurumYonetimiPage
                .iptal()
                .yeniKurumEkle()
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .kurumAdiDoldur(kurumAdi)
                .kaydet()
                .islemMesaji().dikkatOlmali(kokBirimDikkatMesaji);

        kurumYonetimiPage
                .iptal()
                .yeniKurumEkle()
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum)
                .kurumAdiDoldur(kurumAdi)
                .ozelHitapSec(true)
                .hitapAlaniKontrolu()
                .kaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kurumYonetimiPage
                .ozelHitapSec(false)
                .yeniIletisimBilgisiEkle()
                .iletisimBilgisiKaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kurumYonetimiPage
                .ilDoldur(il)
                .ePostaDoldur(ePostaYanlisFormat)
                .iletisimBilgisiKaydet()
                .islemMesaji().dikkatOlmali(ePostaDikkatMesaji);

        kurumYonetimiPage
                .iletisimBilgisiIptalEt()
                .kepAdresBilgileriArti()
                .kepAdresiBilgileriKaydet()
                .islemMesaji().uyariOlmali(uyariMesaji);

        kurumYonetimiPage
                .kepAdresiDoldur(kepAdresiYanlisFormat)
                .kepAdresiBilgileriKaydet()
                .islemMesaji().dikkatOlmali(kepAdresiDikkatMesaji);

        kurumYonetimiPage
                .kepAdresiBilgileriIptalEt()
                .kepAdresiKullaniyorSec(true)
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu) //uyarı popupdan sonra buraları silinior. o yüzden eklendi
                .kurumAdiDoldur(kurumAdi)  //uyarı popupdan sonra buraları silinior. o yüzden eklendi
                .kaydet()
                .islemMesaji().dikkatOlmali(kepAdresiBolBirakilamazDikkatMesaji);

    }

}