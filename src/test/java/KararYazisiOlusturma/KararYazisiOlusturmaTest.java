package KararYazisiOlusturma;
/****************************************************
 * Tarih: 2017-11-21
 * Proje: Türksat Functional Test Automation
 * Class: "Karar Yazısı Oluşturma" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.KararIzlemePage;
import pages.solMenuPages.KepIlePostalanacaklarPage;
import pages.ustMenuPages.*;

import static data.TestData.*;

@Epic("Kep ile Postalama İşlemleri")
public class KararYazisiOlusturmaTest extends BaseTest{

        KararYazisiOlusturPage kararYazisiOlusturPage;
        KararIzlemePage kararIzlemePage;

        @BeforeMethod
        public void loginBeforeTests() {
            kararYazisiOlusturPage = new KararYazisiOlusturPage();
            kararIzlemePage = new KararIzlemePage();
        }

        @Severity(SeverityLevel.CRITICAL)
        @Test(enabled = true, description = "1610: KEP Hesap Menüsü - Tanımlanan KEP hesapları ile login işlemleri")
        public void TC1610() throws InterruptedException {

            String uyariMesajYaziIcerik = "Yazı içeriği boş olamaz!";
            String uyariMesajZorunlu = "Zorunlu alanları doldurunuz";
            String konuKodu = "K/Frekans Yıllık Kullanım Ücreti";
            String kaldirilicakKlasorler = "Diğer";
            String toplantiNo = "123123";
            String toplantiTarih = "30.11.2017";
            String kararNo = "1231231231231231231";
            String aciklama = "Deneme amaçlıdır";
            String editorIcerik = "Deneme Can";
            String kullanici = "Optiim";
            String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
            String imzalama = "İmzalama";

            login(username2, password2);

            kararYazisiOlusturPage
                    .openPage()
                    .bilgilerTabiAc()
                    .konuKoduDoldur(konuKodu)
                    .onayAkisiEkle()
                    .kullan()
                    .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                    .toplantiNoDoldur(toplantiNo)
                    .toplantiTarihDoldur(toplantiTarih)
                    .kararNoDoldur(kararNo)
                    .kaydetveOnaySun()
                    .aciklamaDoldur(aciklama)
                    .gonder(true)
                    .islemMesaji().beklenenMesaj(uyariMesajYaziIcerik);

            kararYazisiOlusturPage
                    .editorTabAc()
                    .editorIcerikDoldur(editorIcerik);
            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kararNoDoldur(kararNo)
                    .konuKoduTemizle()
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .konuKoduDoldur(konuKodu)
                    .konuDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorTemizle()
                    .konuKoduDoldur(konuKodu)
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                    .toplantiNoDoldur("")
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .toplantiNoDoldur(toplantiNo)
                    .onayAkisiTemizle()
                    .kaydetveOnaySun()
                    .islemMesaji().beklenenMesaj(uyariMesajZorunlu);

            kararYazisiOlusturPage
                    .bilgilerTabiAc()
                    .onayAkisiEkle()
                    .kullanicilarDoldur(kullanici)
                    .onayAkisiDoldur(onayAkisi)
                    .imzalamaKontrol(imzalama);



        }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "2232: Karar izleme ekranının toplu onaya sunma")
    public void TC2232() throws InterruptedException {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "010.10";
        String kaldirilicakKlasorler = "Diğer";
        String toplantiNo = "123123";
        String toplantiTarih = "30.11.2017";
        String kararNo = "1231231231231231231";
        String aciklama = "Deneme amaçlıdır";
        String editorIcerik = "Deneme Can";
        String kullanici = "Zübeyde TEKİN";
        String onayAkisi = "ZUZU_ONAY_AKİSİ_1";
        String ivedilik = "İvedi";

        login(username2, password2);

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .ivedilikSec(ivedilik)
                .onayAkisiEkle()
                .kullanicilarDoldur(kullanici)
                .kullan()
                .kaldirilacakKlasorlerDoldur(kaldirilicakKlasorler)
                .toplantiNoDoldur(toplantiNo)
                .toplantiTarihDoldur(toplantiTarih)
                .kararNoDoldur(kararNo);

        kararYazisiOlusturPage
                .editorTabAc()
                .editorIcerikDoldur(editorIcerik)
                .kaydet(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        kararIzlemePage
                .openPage()
                .evrakSec("231");



    }
}

