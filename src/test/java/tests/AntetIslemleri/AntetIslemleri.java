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

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;

/****************************************************
 * Tarih: 2018-03-24
 * Proje: Türksat Functional Test Automation
 * Class: "Antet Islemleri" konulu senaryoları içerir
 * Yazan: Serdar Kayis - Can Şeker
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
    ParafBekleyenlerPage parafBekleyenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        useFirefox();

//        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);

//        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);
//        birimDegistirme("GENEL MÜDÜRLÜK MAKAMI");

        login("hkandur","123");

        birimYonetimiPage = new BirimYonetimiPage();
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2383: Güncel birimin anteti normalse, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2383() throws InterruptedException, IOException {
        String testid = "TS2383";
        String konu = "TS2383-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
//        String user1 = "Antet otomasyontest";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,true);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
//        login("antetotomasyontest","123");
        birimDegistirme("Antet Üst Birim");
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //TODO: Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2382: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2382() throws InterruptedException, IOException {
        String testid = "TS2382";
        String konu = "TS2382-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,true);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2385: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2385() throws InterruptedException, IOException {
        String testid = "TS2385";
        String konu = "TS2385-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,true);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2370: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birimde antet yok ise evrak kontrolü")
    public void TS2370() throws InterruptedException, IOException {
        String testid = "TS2370";
        String konu = "TS2370-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,true);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2380: Güncel birimin anteti normalse, üst birim özel antetse evrak kontrolü")
    public void TS2380() throws InterruptedException, IOException {
        String testid = "TS2380";
        String konu = "TS2380-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2, antetGuncelBirim, antetUstBirim, antetEnUstBirim,false);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2368: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2368() throws InterruptedException, IOException {
        String testid = "TS2368";
        String konu = "TS2368-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,false);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2384: Güncel birimin anteti normalse, üst birimde antet yok ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2384() throws InterruptedException, IOException {
        String testid = "TS2384";
        String konu = "TS2384-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,false);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2376: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti normalse")
    public void TS2376() throws InterruptedException, IOException {
        String testid = "TS2376";
        String konu = "TS2376-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
//        String user1 = "Gökçe ŞAHİN";
//        String user1Details = "Antet Güncel Birim";
//        String user2 = "Antet İSLEMLERİ";
//        String details = "Antet Üst Birim";

        String user1 = "Antet İslemleri";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Yasemin Çakıl Akyol";
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
        login(TestData.userYakyol);

        evrakOlusturPage
                .openPage()
                .editorTabAc();
//                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim);

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiTumuSec()
                .onayAkisiKullaniciEkle(user1,guncelBirim)
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
                .kaydetOnayaSun()
                .kaydetOnayaSunAciklamaDoldur2(editorIcerik)
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

//        imzaBekleyenlerPage
//                .ekOnizlemePDFKontrol("","",antetGuncelBirim, antetUstBirim, antetEnUstBirim);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2367: Güncel birimin anteti yoksa, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2367() throws InterruptedException, IOException {
        String testid = "TS2367";
        String konu = "TS2367-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,true);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,1);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2362: Güncel birimin anteti yoksa, üst birim özel antetse evrak kontrolü")
    public void TS2362() throws InterruptedException, IOException {
        String testid = "TS2362";
        String konu = "TS2362-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
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
                .editorAntetKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,false);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);


        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-1", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-1", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0)
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetGuncelBirim, antetUstBirim, antetEnUstBirim,0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2372: Güncel birimin anteti yoksa, üst birimde antet yok ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2372() throws InterruptedException, IOException {
        String testid = "TS2372";
        String konu = "TS2372-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Antet Yok";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "İki Üst Birim Normal Antet";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";


        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,"","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2371: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2371() throws InterruptedException, IOException {
        String testid = "TS2371";
        String konu = "TS2371-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String ustustbirim ="YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
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
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()

                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .kaydet()

                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(ustBirim);
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol2("","",antetGuncelBirim, antetUstBirim, antetEnUstBirim);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
        birimDegistirme(ustBirim);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,"","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);
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
        String user1 = "Antet İslemleri";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Yasemin Çakıl Akyol";
        String details = "BHUPGMY";
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
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";


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
        login(TestData.userYakyol);

        evrakOlusturPage
                .openPage()
                .editorTabAc();

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiTumuSec()
                .onayAkisiKullaniciEkle(user1,guncelBirim)
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
                .kaydetOnayaSun()
                .kaydetOnayaSunAciklamaDoldur2(editorIcerik)
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,"");

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu("","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);
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
        String user1Details = "Antet Güncel Birim";
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
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu(antetDefault1,antetDefault2,"","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu(antetDefault1,antetDefault2,antetEnUstBirim,antetUstBirim,antetGuncelBirim);
            }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2395: Güncel birimin anteti normalse, üst birim normal ve 2 üst birim anteti normalse evrak kontrolü")
    public void TS2395() throws InterruptedException, IOException {
        String testid = "TS2395";
        String konu = "TS2395-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Antet İslemleri";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String normalAntet = "Normal Antet";
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
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .kaydet();


        testStatus(testid, "Test Başladı");

        login(TestData.userYakyol);

        evrakOlusturPage
                .openPage()
                .editorTabAc();

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiTumuSec()
                .onayAkisiKullaniciEkle(user1,guncelBirim)
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
                .kaydetOnayaSun()
                .kaydetOnayaSunAciklamaDoldur2(editorIcerik)
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemePDFKontrol("","",antetEnUstBirim,antetUstBirim,antetGuncelBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu("","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu("","",antetEnUstBirim,antetUstBirim,antetGuncelBirim);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2394: Güncel birimin anteti özelse evrak kontrolü")
    public void TS2394() throws InterruptedException, IOException {
        String testid = "TS2394";
        String konu = "TS2394-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String antetGuncelBirim = "Özel Antet";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Özel";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetGuncelBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);
        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol2("","",antetGuncelBirim, "","");

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol("","","","",antetGuncelBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu("","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu("","","","",antetGuncelBirim);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2397: Güncel birimin anteti normalse, üst birim özel antetse evrak kontrolü")
    public void TS2397() throws InterruptedException, IOException {
        String testid = "TS2395";
        String konu = "TS2395-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Antet İSLEMLERİ";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String normalAntet = "Normal Antet";
        String ustBirim = "Antet Üst Birim";
        String ustustBirim = "Yazılım Geliştirme Direktörlüğü";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String antetGuncelBirimTipi = "Antet Yok";
        String antetGuncelBirim = "";
        String antetUstBirimTipi = "Özel";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Normal";
        String antetEnUstBirim = "Üst Birim";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";
        String antetDefault = "ANKARA";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String ust ="Üst";
        String birim ="Birim";
        String ozelAntet ="Özel Antet";
        String ozelAntet2 ="Üst Birimin Özel Antet";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetUstBirimTipi)
                .antetOzelBilgisiTumuDoldur(ust,birim,ozelAntet,ozelAntet2)
                .kaydet()

                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetBilgisiDoldur("Normal Antet")
                .kaydet();

        testStatus(testid, "Test Başladı");

        login(TestData.userYakyol);

        evrakOlusturPage
                .openPage()
                .editorTabAc();

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
                .onayAkisiEkle()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiTumuSec()
                .onayAkisiKullaniciEkle(user1,guncelBirim)
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
                .kaydetOnayaSun()
                .kaydetOnayaSunAciklamaDoldur2(editorIcerik)
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        imzaBekleyenlerPage
                .ekOnizlemePDFKontrolOzelAntet("","",ust,birim,ozelAntet,ozelAntet2,normalAntet);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFOzelKontrolu("","","","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFOzelKontrolu("","",ust,birim,ozelAntet,ozelAntet2,normalAntet);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2398: Güncel birimin anteti yoksa, üst birimde antet yok ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2398() throws InterruptedException, IOException {
        String testid = "TS2398";
        String konu = "TS2398-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";
        String antetGuncelBirimTipi = "Antet Yok";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String antetGuncelBirim = "Özel Antet";
        String antetUstBirim = "";
        String antetEnUstBirimTipi = "Özel";
        String antetUstBirimTipi = "Antet Yok";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetGuncelBirimTipi)
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

        login(TestData.userYakyol);
        evrakOlusturPage
                .openPage()
                .editorTabAc();

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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiTumuSec()
                .onayAkisiKullanicilariTemizle()
                .onayAkisiKullaniciEkle(user2,user1Details)
                .onayAkisiKullaniciTipiSec(user2,"Paraflama")
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .kaydetOnayaSun()
                .kaydetOnayaSunAciklamaDoldur2(editorIcerik)
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);
        birimDegistirme(guncelBirim);
        parafBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(Condition.text(konu));

        parafBekleyenlerPage
                .ekOnizlemePDFKontrol("","",antetEnUstBirim,antetUstBirim,antetUstBirim);

        parafBekleyenlerPage
                .evrakEkleriTabAc();
        imzaBekleyenlerPage
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu("","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu("","",antetEnUstBirim,antetUstBirim,antetUstBirim);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2381: Güncel birimin anteti yoksa, üst birimde antet yok ve 2 üst birim anteti özelse evrak kontrolü")
    public void TS2381() throws InterruptedException, IOException {
        String testid = "TS2381";
        String konu = "TS2381-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "Kanunlar";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
//        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Gökçe ŞAHİN";
        String user1Details = "Antet Güncel Birim";
        String user2 = "Antet İSLEMLERİ";
        String details = "Antet Üst Birim";
        String pathToFileText = getUploadPath() + "Otomasyon.pdf";
        String fileName = "Otomasyon.pdf";

        String guncelBirim = "Antet Güncel Birim";
        String ustBirim = "Antet Üst Birim";
        String antetGuncelBirim = "Özel Antet";
        String antetUstBirim = "Üst Birim Normal Antet";
        String antetnormalBirim = "Normal Antet";
        String antetEnUstBirimTipi = "Özel";
        String antetEnUstBirimTipi2 = "Normal";
        String antetEnUstBirim = "İki Üst Birim Özel Antet";
        String enUstBirim = "GENEL MÜDÜRLÜK MAKAMI";
        String bilgiKurum = "BÜYÜK HARFLERLE KURUM";
        String antetDefault1 = "T.C.";
        String antetDefault2 = "ANKARA";

        testStatus(testid, "PreCondition");
        birimYonetimiPage
                .openPage()
                .birimFiltreDoldur(enUstBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi)
                .antetOzelBilgisiDoldur(antetEnUstBirim)
                .kaydet()

                .birimFiltreDoldur(ustBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi2)
                .antetBilgisiDoldur(antetUstBirim)
                .kaydet()

                .birimFiltreDoldur(guncelBirim)
                .ara()
                .aktiflerIlkBirimGuncelle()
                .antetTipiSec(antetEnUstBirimTipi2)
                .antetBilgisiDoldur(antetnormalBirim)
                .kaydet();


        testStatus(testid, "Test Başladı");

        login(TestData.usernameAntetIslem1,TestData.passwordAntetIslem1);

        birimDegistirme(guncelBirim);

        evrakOlusturPage
                .openPage()
                .editorTabAc()
                .editorAntetKontrol2("","",antetnormalBirim, antetUstBirim,antetEnUstBirim);


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
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiDoldur(bilgiKurum,"Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(user2,"Paraflama")
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama", user1Details)
                .onayAkisiKullaniciEkle(user1,details)
                .onayAkisiKullaniciTipiSec(user1,"İmzalama")
                .onayAkisiKullaniciKontrolu(user1 , "İmzalama")
                .dagitimiEkYapSec(true)
                .dagitimiEkYapSecKontrol()
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
                .ekOnizlemePDFKontrol("","",antetEnUstBirim,antetUstBirim,antetnormalBirim);

        imzaBekleyenlerPage
                .evrakEkleriTabAc()
                .ekListesiKontrol("EK-3", "Dağıtım Listesi")
                .ekListesiKontrol("EK-2", fileName)
                .ekListesiKontrol("EK-3","Ek Listesi")
                .ekListesindeDetayGoster("EK-3", "Dağıtım Listesi")
                //Bug mevcut, antetUstBirim ve AntetGuncelBirim verileri gelmiyor
                .dagitimListesiPDFKontrolu("","","","","")
                .ekListesindeDetayGoster("EK-3", "Ek Listesi")
                .ekListesiPDFKontrolu("","",antetEnUstBirim,antetUstBirim,antetnormalBirim);
    }
}
