package tests.AntetIslemleri;

import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.solMenuPages.*;
import pages.ustMenuPages.BirimYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static com.codeborne.selenide.Condition.text;

/****************************************************
 * Tarih: 2018-03-24
 * Proje: Türksat Functional Test Automation
 * Class: "Antet Islemleri" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/
@Feature("Antet Islemleri")
public class AntetIslemleri extends BaseTest {

    public static final User ztekinEnUst = new User("ztekin", "123", "Zübeyde TEKİN", "GENEL MÜDÜRLÜK MAKAMI");
    public static final User gsahinGuncel = new User("gsahin", "123", "Gökçe ŞAHİN", "Antet Güncel Birim");
    public static final User ztekinGuncel = new User("ztekin", "123", "Zübeyde TEKİN", "Antet Güncel Birim");

    BirimYonetimiPage birimYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    TextEditor editor;
//    ImzaBekleyenlerPage imzaBekleyenlerPage;
//    ParafBekleyenlerPage parafBekleyenlerPage;
//    ImzaladiklarimPage imzaladiklarimPage;
//    BeklemeyeAlinanlarPage beklemeyeAlinanlarPage;
//    IadeEttiklerimPage iadeEttiklerimPage;
//    KoordineBekleyenlerPage koordineBekleyenlerPage;
//    KontrolBekleyenlerPage kontrolBekleyenlerPage;
//    GelenEvrakKayitPage gelenEvrakKayitPage;
//    GelenEvraklarPage gelenEvraklarPage;
//    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
//    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
//    TeslimAlinanlarPage teslimAlinanlarPage;
//    ParafladiklarimPage parafladiklarimPage;
//    PostalanacakEvraklarPage postalanacakEvraklarPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login(ztekinEnUst);
        birimYonetimiPage = new BirimYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
//        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
//        parafBekleyenlerPage = new ParafBekleyenlerPage();
//        imzaladiklarimPage = new ImzaladiklarimPage();
//        beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage();
//        iadeEttiklerimPage = new IadeEttiklerimPage();
//        koordineBekleyenlerPage = new KoordineBekleyenlerPage();
//        kontrolBekleyenlerPage = new KontrolBekleyenlerPage();
//        gelenEvrakKayitPage = new GelenEvrakKayitPage();
//        gelenEvraklarPage = new GelenEvraklarPage();
//        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
//        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
//        teslimAlinanlarPage = new TeslimAlinanlarPage();
//        parafladiklarimPage = new ParafladiklarimPage();
//        postalanacakEvraklarPage = new PostalanacakEvraklarPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2383: Güncel birimin anteti normalse, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2383() throws InterruptedException {
        String testid = "TS2383";
        String konu = "TS2383-" + getSysDate();
        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String kokBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String konuKodu = "Kanunlar";
//        String kaldirilacakKlasorler = "KURUL KARARLARI";
//        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
//        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
//        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
//        String geregiKurum3 = "TS1493 Kurumu";
//        String geregiSecimBirim = "Birim";
//        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
//        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
//        String geregiSecimKullanici = "Kullanıcı";
//        String geregiKullanici = "Ahmet SAVAŞ";
//        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Zübeyde TEKİN";
//        String user3 = "Yasemin Çakıl AKYOL";
        String details = "Antet Üst Birim";
//        String sayfa1 = "Evrak Oluştur";
//        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
//        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
//        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
//
//        String secenek1 = "İade Et";
//        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec("Normal")
                .antetBilgisiDoldur("Normal Antet")

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec("Normal")
                .antetBilgisiDoldur("Üst Birim Normal Antet")

                .birimFiltreDoldur(kokBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec("Antet Yok");

        testStatus(testid, "Test Başladı");
        login(ztekinGuncel);

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
//                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
//                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                .dagitimiEkYapSec(true)
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .kullan();
//                .paraflaKontrol();

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorAntetKontrol("Normal Antet", "Üst Birim Normal Antet", "ANKARA");

        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");
//                .editorHitapKontrol(geregiKurum.toUpperCase());
//                .editorImzaciKontrol(user2)
//                .editorImzaciKontrol(user3)
//                .editorDagitimKontrol(geregiKurum);
//
//        evrakOlusturPage
//                .parafla()
//                .islemMesaji().basariliOlmali("İşlem başarılıdır!");
//
//
//        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
//
//        imzaBekleyenlerPage
//                .openPage()
//                .evrakKonusunaGoreIcerikTiklama(konu)
//                .evrakIcerikKontrol();
//
//
//        editor
//                .type(editorIcerik);
//
//        evrakOlusturPage
//                .imzalaButonaTikla()
//                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
//                .icerikDegistiIptal();
//
//        evrakOlusturPage
//                .sayfaKontrol2("Evrak Detayı")
//                .bilgilerTabiAc()
//                .geregiIptal()
//                .geregiSecimTipiEskiEvrak("Kurum")
//                .geregiSecimTipiEskiKontrol("Kurum")
//                .geregiDoldurEski(geregiKurum2,"Kurum")
//                .geregiKontrolInbox(geregiKurum2)
//                .ivedilikSec("Normal")
//                .ivedilikKontrol("Normal")
//                .konuDoldur(konu)
//                .konuDoldurKontrol(konu)
//                .geregiIptal()
//                .geregiSecimTipiEskiEvrak("Kurum")
//                .geregiDoldurEski(geregiKurum3,"Kurum")
//                .geregiKontrolInbox(geregiKurum3)
//                .imzalaButonaTikla();
//
//
//        evrakOlusturPage
//                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
//                .evrakIcerikDegistiImzalaveDevamEt()
//                .evrakSecmeliDegistiKaydet()
//                .evrakImzalaUyariKontrol(evrakİmzaUyari)
//                .evrakImzala()
//                .islemMesaji().basariliOlmali(basariMesaji);
//
//        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);
//
//        imzaBekleyenlerPage
//                .openPage()
//                .evrakNoKontrolu(konu)
//                .evrakKonusunaGoreIcerikTiklama(konu);
//
//        evrakOlusturPage
//                .bilgilerTabiAc()
//                .geregiKontrolInbox(geregiKurum3)
//                .konuDoldurKontrol(konu)
//                .ivedilikKontrol("Normal");
//
//        evrakOlusturPage
//                .editorTabAc();
//
//        editor
//                .type(editorIcerik);
//
//        evrakOlusturPage
//                .imzalaButonaTikla()
//                .evrakSecmeliDegistiEvet()
//                .imzalanamazButtonKontrol();
//
//        evrakOlusturPage
//                .bilgilerTabiAc()
//                .geregiIptal()
//                .geregiSecimTipiEskiEvrak("Kurum")
//                .geregiDoldurEski(geregiKurum3,"Kurum")
//                .imzalaButonaTikla()
//                .evrakGuncellendiImzalanamazUyariKontrol(evrakGuncellendiImzalanamazUyari);
    }

}
