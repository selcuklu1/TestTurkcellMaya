package KurumYonetimi;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.GidenEvrakKayitPage;
import pages.ustMenuPages.KurumYonetimiPage;


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
        login();
    }

    @Test(enabled = true, description = "TC01459 : Kurum bilgisi güncellemee")
    public void TC01957_A() throws InterruptedException {

        String guncellenecekKurumAdi = "huseyindeneme";
        String yeniKurumAdi = "huseyindeneme0012";
        String idariBirimKimlikKodu = "123456";
        String ustKurum = "Adalet Bakanlığı";
        String kontrolEdilecekGeregiDetay = "Türkiye Devlet Teşkilatı | Yürütme | Adalet Bakanlığı | huseyindeneme0012";

        // İletişim bilgileri güncelleme
        String mobilTelNo = "5444444444";
        String telefonNo = "5444444445";
        String isTelefonNo = "5444444446";
        String faxNumarasi1 = "5444444447";
        String faxNumarasi2 = "5444444448";
        String adres = "yeni adersim";
        String ulke = "TÜRKİYE";
        String il = "İSTANBUL";
        String ilce = "Avcılar";
        String ePosta = "xxx@xxx.com";
        String webAdresi = "example.com";
        String basariMesaji = "İşlem başarılıdır!";

        // Kep adresi güncelleme
        String guncellenecekKepAdresi = "hihihaha2223@kepadresim.com";
        String yeniKepadresi = "hihihaha2223@kepadresim.com";
        String kepHizmetSaglayicisi = "KEPKUR";


        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur(guncellenecekKurumAdi)
                .ara()
                .kurumGuncelle(guncellenecekKurumAdi)
                .idariBirimKimlikKoduDoldur(idariBirimKimlikKodu)
                .ustKurumSec(ustKurum)
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
                .islemMesaji().basariliOlmali(basariMesaji);
        kurumYonetimiPage
                .kepAdresiGuncelle(guncellenecekKepAdresi, null)
                .kepAdresiDoldur(yeniKepadresi)
                .kepHizmetSaglayiciSec(kepHizmetSaglayicisi)
                .kepAdresiBilgileriKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        kurumYonetimiPage
                .kepAdresiKontrol(yeniKepadresi, 0, true)
                .kurumKaydet()
                .islemMesaji().basariliOlmali(basariMesaji);
        kurumYonetimiPage
                .kurumHiyerarsisiniGuncelle()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage
                .sorgulaKurumDoldur(yeniKurumAdi)
                .ara()
                .kurumKontrolEt(yeniKurumAdi, true)
                .panelKapat();


        gelenEvrakKayitPage
                .openPage()
                .kisiKurumSec("D")
                .geldigiKurumDoldurLovText(yeniKurumAdi)
                .alanDegeriKontrolEt(gelenEvrakKayitPage.txtEvrakBilgileriListEvrakSayiTextAreaSol, idariBirimKimlikKodu, true, false);

        gelenEvrakKayitPage.panelKapat(false);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("D")
                .geregiTreeKontrolEt(yeniKurumAdi, true)
                .bilgiSecimTipiSec("D")
                .bilgiSec(yeniKurumAdi);

        evrakOlusturPage
                .editorTabAc()
                .hitapKontrol(yeniKurumAdi);

        evrakOlusturPage
                .evrakOlusturPageKapat();

        gidenEvrakKayitPage
                .openPage()
                .geregiSecimTipiSec("D")
                .geregiAlanindaDegerKontrolu(yeniKurumAdi, true)
                .panelKapat(false);

        kurumYonetimiPage
                .openPage()
                .sorgulaKurumDoldur(yeniKurumAdi)
                .ara()
                .kurumGuncelle(yeniKurumAdi)
                .kepAdresiKullaniyorSec(false)
                .kurumKaydet()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
        kurumYonetimiPage.panelKapat();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .geregiSecimTipiSec("D")
                .geregiSec(yeniKurumAdi)
                .geregiSecilenKontrol(yeniKurumAdi, kontrolEdilecekGeregiDetay, "Adi Posta");

    }
}