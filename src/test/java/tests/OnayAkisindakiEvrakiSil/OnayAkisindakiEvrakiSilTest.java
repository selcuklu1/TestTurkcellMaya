package tests.OnayAkisindakiEvrakiSil;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ParafBekleyenlerPage;
import pages.ustMenuPages.*;

import java.lang.reflect.Method;

/****************************************************
 * Tarih: 2018-02-05
 * Proje: Türksat Functional Test Automation
 * Class: "Onay akışındaki evraki sil" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class OnayAkisindakiEvrakiSilTest extends BaseTest{

    EvrakOlusturPage evrakOlusturPage;
    OnayAkisYonetimiPage onayAkisYonetimiPage;
    OlurYazisiOlusturPage olurYazisiOlusturPage;
    KararYazisiOlusturPage kararYazisiOlusturPage;
    GelenEvraklarPage gelenEvraklarPage;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    TextEditor editor;
    EvrakDetayiPage evrakDetayiPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    IptalEdilenEvraklarRaporuPage iptalEdilenEvraklarRaporuPage;


    @BeforeMethod
    public void beforeTests(Method method) {

        login();

        evrakOlusturPage = new EvrakOlusturPage();
        onayAkisYonetimiPage = new OnayAkisYonetimiPage();
        olurYazisiOlusturPage = new OlurYazisiOlusturPage();
        kararYazisiOlusturPage = new KararYazisiOlusturPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        editor = new TextEditor();
        evrakDetayiPage = new EvrakDetayiPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        iptalEdilenEvraklarRaporuPage = new IptalEdilenEvraklarRaporuPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0983: İmzacıdaki Karar Yazısının Silinememesi")
    public void TS0983() {

        String evrakKonusu = "TS0983_Senaryosu_" + getSysDate();
        String konuKodu = "160";
        String kaldirilacakKlasor = "ESK05";
        String toplantiNo = createRandomNumber(10);
        String toplantiTarihi = getSysDateForKis();
        String kararNo = createRandomNumber(10);
        String onayAkisiDefaultKullanici = "Optiim TEST";
        String kullanici2 = "Mehmet BOZDEMİR";
        String aciklama = "TS0983 Senaryosu";
        String basariMesaji = "İşlem başarılıdır!";

        kararYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(evrakKonusu)
                .ivedilikSec("İvedi")
                .kaldirilacakKlasorlerDoldur(kaldirilacakKlasor)
                .toplantiNoDoldur(toplantiNo)
                .toplantiTarihDoldur(toplantiTarihi)
                .kararNoDoldur(kararNo)
                .onayAkisiTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(onayAkisiDefaultKullanici)
                .kullanicilarDoldur(kullanici2)
                .kullan();

        kararYazisiOlusturPage
                .editorTabAc();

        editor
                .type("TS0983 nolu senaryonun testi için bir editör metni girildi.");

        kararYazisiOlusturPage
                .pdfKontrol
                .toplantiNoKontrol(toplantiNo)
                .toplantiTarihiKontrol(toplantiTarihi)
                .kararNoKontrol(kararNo)
                .imzacilarKontrol(onayAkisiDefaultKullanici)
                .imzacilarKontrol(kullanici2);

        kararYazisiOlusturPage
                .kaydetveOnaySun()
                .kullaniciIslemVeSiraKontrolu(onayAkisiDefaultKullanici, "İmzalama", kullanici2, "İmzalama")
                .imzaAciklamaDoldur(aciklama)
                .imzaGonder(true)
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakOnizlemeKontrol()
                .silButonuGelmedigiKontrolu()
                .evrakKonusunaGoreIcerikTiklama(evrakKonusu);

        evrakDetayiPage
                .sayfaAcilmali()
                .editorTabKontrolu()
                .silButonunGelmedigiKontrolu();

        evrakOlusturPage
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameMBOZDEMIR, TestData.passwordMBOZDEMIR); //mbozdemir

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakOnizlemeKontrol()
                .silButonuGelmedigiKontrolu()
                .evrakKonusunaGoreIcerikTiklama(evrakKonusu);

        evrakDetayiPage
                .sayfaAcilmali()
                .editorTabKontrolu()
                .silButonunGelmedigiKontrolu();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0102: Parafa Gelen Evrakın Ön İzleme Ekranından Silinmesi ve Kontrolü")
    public void TS0102() {

        String evrakKonusu = "TS0102_Senaryosu_" + getSysDate();
        String konuKodu = "399";
        String kaldirilacakKlasor = "ESK05";
        String kurum = "Başbakanlık";
        String onayAkisiDefaultKullanici = "Optiim TEST";
        String kullanici2 = "Sezai ÇELİK";
        String kullanici3 = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Zorunlu alanları doldurunuz";
        String ilkTarih = getSysDateForKis();
        String sonTarih = getSysDateForKis();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabTumAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(evrakKonusu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("İvedi")
                .geregiSecimTipiSecByText("Kurum")
                .geregiDoldur(kurum, "Kurum")
                .secilenOnayAkisiSil()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(onayAkisiDefaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciEkle(kullanici2)
                .onayAkisiKullaniciTipiSec(kullanici2, "Paraflama")
                .onayAkisiKullaniciEkle(kullanici3)
                .onayAkisiKullaniciTipiSec(kullanici3, "İmzalama")
                .kullan()
                .onayAkisiDoluGeldigiKontrolu();

        evrakOlusturPage
                .editorTabAc();

        editor
                .type("TS0102 nolu senaryonun testi için bir editör metni girildi.");

        evrakOlusturPage
                .editorTabAc()
                .metinAlaninGeldigiGorme()
                .editorHitapKontrol("BAŞBAKANLIĞa")
                .editordeImzaciKontrol(kullanici3)
                .geregiAlaniKontrolu(kurum)
                .editordeKonuKontrol(evrakKonusu);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameSEZAICELIK, TestData.passwordSEZAICELIK); //sezaiceik

        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakOnizlemeKontrol()
                .silButonuKontrolu()
                .sil()
                .evrakOnizlemedeSil()
                .islemMesaji().uyariOlmali(uyariMesaji);

        parafBekleyenlerPage
                .evrakSilNotuDoldur(evrakKonusu + "Konulu evrak silinecek")
                .evrakOnizlemedeSil()
                .silmeOnayiEvrakSilPopup("Evet");

        iptalEdilenEvraklarRaporuPage
                .openPage()
                .sayfaAcilmali()
                .ilkTarihDoldur(ilkTarih)
                .sonTarihDoldur(sonTarih)
                .belgeDurumuSec("Tamamı")
                .sorgula()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakDetayiTikla(evrakKonusu);

        evrakDetayiPage
                .sayfaAcilmasiKontrolu();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0103: Parafa Gelen Evrakın Detay Ekranından Silinmesi ve Kontrolü")
    public void TS0103() {

        String evrakKonusu = "TS0103_Senaryosu_" + getSysDate();
        String konuKodu = "399";
        String kaldirilacakKlasor = "ESK05";
        String kurum = "Başbakanlık";
        String onayAkisiDefaultKullanici = "Optiim TEST";
        String kullanici2 = "Sezai ÇELİK";
        String kullanici3 = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Zorunlu alanları doldurunuz";
        String ilkTarih = getSysDateForKis();
        String sonTarih = getSysDateForKis();
        String evrakSilmeNotu = "TS0103 nolu evrak sil";

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabTumAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(evrakKonusu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("İvedi")
                .geregiSecimTipiSecByText("Kurum")
                .geregiDoldur(kurum, "Kurum")
                .secilenOnayAkisiSil()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(onayAkisiDefaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciEkle(kullanici2)
                .onayAkisiKullaniciTipiSec(kullanici2, "Paraflama")
                .onayAkisiKullaniciEkle(kullanici3)
                .onayAkisiKullaniciTipiSec(kullanici3, "İmzalama")
                .kullan()
                .onayAkisiDoluGeldigiKontrolu();

        evrakOlusturPage
                .editorTabAc();

        editor
                .type("TS0103 nolu senaryonun testi için bir editör metni girildi.");

        evrakOlusturPage
                .editorTabAc()
                .metinAlaninGeldigiGorme()
                .editorHitapKontrol("BAŞBAKANLIĞa")
                .editordeImzaciKontrol(kullanici3)
                .geregiAlaniKontrolu(kurum)
                .editordeKonuKontrol(evrakKonusu);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameSEZAICELIK, TestData.passwordSEZAICELIK); //sezaiceik

        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakDetayGoster(evrakKonusu);

        evrakDetayiPage
                .editorTabKontrolu()
                .sayfaAcilmasiKontrolu()
                .silButonununGeldigiKontrolu()
                .evrakSil()
                .evrakSilmeNotuSonrasiSil()
                .islemMesaji().uyariOlmali(uyariMesaji);

        evrakDetayiPage
                .evrakSilmeNotuDoldur(evrakSilmeNotu)
                .evrakSilmeNotuSonrasiSil()
                .evrakSilPopup("Evet")
                .islemMesaji().basariliOlmali(basariMesaji);

        iptalEdilenEvraklarRaporuPage
                .openPage()
                .sayfaAcilmali()
                .ilkTarihDoldur(ilkTarih)
                .sonTarihDoldur(sonTarih)
                .belgeDurumuSec("Belge olmamış evraklar")
                .sorgula()
                .konuyaGoreEvrakKontrol(evrakKonusu)
                .konuyaGoreEvrakDetayiTikla(evrakKonusu);

        evrakDetayiPage
                .sayfaAcilmasiKontrolu();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0133: Postalanmayı Bekleyen Evrakın Geri Alınarak Silinmesi ve Kontrolü")
    public void TS0133() {

        String evrakKonusu = "TS0133_Senaryosu_" + getSysDate();
        String konuKodu = "399";
        String kaldirilacakKlasor = "ESK05";
        String kurum = "Başbakanlık";
        String kullanici = "Sezai Çelik";
        String onayAkisiDefaultKullanici = "Optiim TEST";
        String kullanici2 = "Sezai ÇELİK";
        String kullanici3 = "Mehmet BOZDEMİR";
        String basariMesaji = "İşlem başarılıdır!";
        String uyariMesaji = "Zorunlu alanları doldurunuz";
        String ilkTarih = getSysDateForKis();
        String sonTarih = getSysDateForKis();
        String evrakSilmeNotu = "TS0103 nolu evrak sil";

        olurYazisiOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabTumAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(evrakKonusu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasor)
                .gizlilikDerecesiSec("Normal")
                .ivedilikSec("İvedi")
                .geregiSecimTipiSecByText("Kullanıcı")
                .geregiDoldur(kullanici3, "Kullanıcı")

                .secilenOnayAkisiSil()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrol(onayAkisiDefaultKullanici, "PARAFLAMA")
                .onayAkisiKullaniciEkle(kullanici2)
                .onayAkisiKullaniciTipiSec(kullanici2, "İmzalama")
                .kullan()
                .onayAkisiDoluGeldigiKontrolu();

        olurYazisiOlusturPage
                .editorTabAc();

        editor
                .type("TS0133 nolu senaryonun testi için bir editör metni girildi.");

        olurYazisiOlusturPage
                .editorTabAc()
                .metinAlaninGeldigiGorme()
                .editorHitapKontrol("... Makamına")  // kurum seçilmediği için
                .editordeImzaciKontrol(kullanici3);
              //  .geregiAlaniKontrolu(kurum)
               // .editordeKonuKontrol(evrakKonusu);

        //TODO: HATALAR VAR. ÇÖZÜLÜNCE DEVAM EDİLECEK.
        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameSEZAICELIK, TestData.passwordSEZAICELIK); //sezaiceik

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreKontrol(evrakKonusu)
                .konuyaGoreEvrakOnizlemedeAc(evrakKonusu)
                .evrakOnizlemeKontrol()
                .evrakImzala();
    }
}
