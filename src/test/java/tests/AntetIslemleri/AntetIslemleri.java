package tests.AntetIslemleri;

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

    public static final User ztekinEnUst = new User("ztekin", "123", "Zübeyde TEKİN", "GENEL MÜDÜRLÜK MAKAMI");
    public static final User gsahinGuncel = new User("gsahin", "123", "Gökçe ŞAHİN", "Antet Güncel Birim");
    public static final User gsahinUstBirim = new User("gsahin", "123", "Gökçe ŞAHİN", "Antet Üst Birim");
    public static final User ztekinGuncel = new User("ztekin", "123", "Zübeyde TEKİN", "Antet Güncel Birim");

    BirimYonetimiPage birimYonetimiPage;
    EvrakOlusturPage evrakOlusturPage;
    TextEditor editor;
    ImzaBekleyenlerPage imzaBekleyenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login(ztekinEnUst);
        birimYonetimiPage = new BirimYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2383: Güncel birimin anteti normalse, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2383() throws InterruptedException, IOException, UnsupportedFlavorException {
        String testid = "TS2383";
        String konu = "TS2383-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Zübeyde TEKİN";
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
        login(ztekinGuncel);

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

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

        login(gsahinUstBirim);
        imzaBekleyenlerPage
                .openPage()
                .evrakKonuyaGoreSec(konu)
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                .ekKontrol()
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekKontrol();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2382: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2382() throws InterruptedException, IOException, UnsupportedFlavorException {
        String testid = "TS2382";
        String konu = "TS2382-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Zübeyde TEKİN";
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
        login(ztekinGuncel);

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

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

        login(gsahinUstBirim);
        imzaBekleyenlerPage
                .openPage()
                .evrakKonuyaGoreSec(konu)
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                .ekKontrol()
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekKontrol();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2385: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2385() throws InterruptedException, IOException, UnsupportedFlavorException {
        String testid = "TS2385";
        String konu = "TS2385-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Zübeyde TEKİN";
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
        login(ztekinGuncel);

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

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

        login(gsahinUstBirim);
        imzaBekleyenlerPage
                .openPage()
                .evrakKonuyaGoreSec(konu)
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                .ekKontrol()
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekKontrol();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2370: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2370() throws InterruptedException, IOException, UnsupportedFlavorException {
        String testid = "TS2370";
        String konu = "TS2370-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user2 = "Zübeyde TEKİN";
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
        login(ztekinGuncel);

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol(antetDefault,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

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

        login(gsahinUstBirim);
        imzaBekleyenlerPage
                .openPage()
                .evrakKonuyaGoreSec(konu)
                .ekOnizlemeKontrol(textCaseSensitive(antetDefault),textCaseSensitive(antetUstBirim),textCaseSensitive(antetGuncelBirim));

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                .ekKontrol()
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekKontrol();
    }

}
