package tests.AntetIslemleri;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.TestData;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.PDFOnizleme;
import pages.pageComponents.TextEditor;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.solMenuPages.*;
import pages.ustMenuPages.BirimYonetimiPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;

/****************************************************
 * Tarih: 2018-03-24
 * Proje: Türksat Functional Test Automation
 * Class: "Antet Islemleri" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/
@Feature("Antet Islemleri")
public class AntetIslemleri extends BaseTest {

    public static final User gsahinUstBirim = new User("gsahin", "123", "Gökçe ŞAHİN", "Antet Üst Birim");
    public static final User gsahin = new User("gsahin", "123", "Gökçe ŞAHİN");
   public static final User antetIslem1EnUst = new User("antetislem1", "123", "Antet İSLEMLERİ", "GENEL MÜDÜRLÜK MAKAMI");
    public static final User antetIslem1 = new User("antetislem1", "123", "Antet İSLEMLERİ");

        public static final User antetIslem1Guncel = new User("antetislem1", "123", "Antet İSLEMLERİ", "Antet Güncel Birim");

    //antetislem1
    BirimYonetimiPage birimYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    TextEditor editor;
    ImzaBekleyenlerPage imzaBekleyenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        useFirefox();

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("GENEL MÜDÜRLÜK MAKAMI");
        birimYonetimiPage = new BirimYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2383: Güncel birimin anteti normalse, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2383() throws InterruptedException, IOException {
        String testid = "TS2383";
        String konu = "TS2383-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Antet Yok";
        String antetEnUstBirim = "";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2382: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2382() throws InterruptedException, IOException {
        String testid = "TS2382";
        String konu = "TS2382-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
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


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetDefault),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2385: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2385() throws InterruptedException, IOException {
        String testid = "TS2385";
        String konu = "TS2385-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Antet Yok";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
//                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetDefault),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2370: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2370() throws InterruptedException, IOException {
        String testid = "TS2370";
        String konu = "TS2370-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Antet Yok";
        String antetEnUstBirim = "";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
//                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
//                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
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


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetDefault),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2380: Güncel birimin anteti normalse, üst birim özel antetse evrak kontrolü")
    public void TS2380() throws InterruptedException, IOException {
        String testid = "TS2380";
        String konu = "TS2380-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Özel";
        String antetUstBirim = "Üst Birim Özel Antet";
        String antetEnUstBirimTipi = "Antet Yok";
        String antetEnUstBirim = "";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetOzelBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2368: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2368() throws InterruptedException, IOException {
        String testid = "TS2368";
        String konu = "TS2368-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Özel";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
//                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2384: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2384() throws InterruptedException, IOException {
        String testid = "TS2384";
        String konu = "TS2384-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Antet Yok";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Özel";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2376: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti normalse")
    public void TS2376() throws InterruptedException, IOException {
        String testid = "TS2376";
        String konu = "TS2376-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "BHUPGMY";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
//                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");
        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
//        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
//        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"Paraflama")
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2367: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2367() throws InterruptedException, IOException {
        String testid = "TS2367";
        String konu = "TS2367-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
//                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2362: Güncel birimin anteti yoksa, üst birim özel antetse evrak kontrolü")
    public void TS2362() throws InterruptedException, IOException {
        String testid = "TS2362";
        String konu = "TS2362-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Özel";
        String antetUstBirim = "Üst Birim Özel Antet";
        String antetEnUstBirimTipi = "Antet Yok";
        String antetEnUstBirim = "";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
//                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetOzelBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .dosyaEkle(pathToFileText,fileName)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault2),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2372: Güncel birimin anteti yoksa, üst birimde antet yok ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2372() throws InterruptedException, IOException {
        String testid = "TS2372";
        String konu = "TS2382-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Genel Müdürlük Makamı");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,"", "", antetEnUstBirim);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("TS2372")
                .kaldiralacakKlasorlerKontrol("TS2372")
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


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekleriDosyaEkle(pathToFileText)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetDefault),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2371: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2371() throws InterruptedException, IOException {
        String testid = "TS2371";
        String konu = "TS2371-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "TS2372";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Özel";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(enUstBirim);
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol("","",antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekleriDosyaEkle(pathToFileText)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(""),textCaseSensitive(""),textCaseSensitive(antetEnUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(""),textCaseSensitive(""),textCaseSensitive(""),textCaseSensitive(""))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(""),textCaseSensitive(""),textCaseSensitive(antetEnUstBirim),textCaseSensitive(antetGuncelBirim));
            }


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2396: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2396() throws InterruptedException, IOException {
        String testid = "TS2396";
        String konu = "TS2396-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Normal";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
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


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekleriDosyaEkle(pathToFileText)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault),textCaseSensitive(antetEnUstBirim),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault),textCaseSensitive(antetEnUstBirim),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));
            }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2392: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti normalse")
    public void TS2392() throws InterruptedException, IOException {

        String testid = "TS2392";
        String konu = "TS2392-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Normal";
        String antetGuncelBirim = "Normal Antet";
        String antetUstBirimTipi = "Antet Yok";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .antetBilgisiDoldur(antetGuncelBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
//                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur(antetEnUstBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");
        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme("Antet Güncel Birim");

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldirilacakKlasorler)
                .kaldiralacakKlasorlerKontrol(kaldirilacakKlasorler)
                .gizlilikDerecesiSec("Normal")
                .gizlilikDerecesiKontrol("Normal")
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .geregiSecimTipiYeniEvrak("Kurum")
                .geregiSecimTipiKontrol("Kurum")
                .geregiDoldur(geregiKurum,"Kurum")
                .geregiKontrol(geregiKurum)
                //bilgileri secim ekle
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .kullan();


        evrakOlusturPage
                .ekleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekleriDosyaEkle(pathToFileText)
                .ekleriEkMetniDoldur(konu)
                .ekleriEkle()
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiniEkYap()
                .ekListesiKontrol("EK-3","Ek Listesi");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));


        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameGSAHIN,TestData.passwordGSAHIN);
        birimDegistirme("Antet Üst Birim");

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .ekPopPDFKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetDefault),textCaseSensitive(antetDefault))
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekPopPDFKontrol(textCaseSensitive(antetDefault1),textCaseSensitive(antetDefault),textCaseSensitive(antetEnUstBirim),textCaseSensitive(antetGuncelBirim));
            }



}
