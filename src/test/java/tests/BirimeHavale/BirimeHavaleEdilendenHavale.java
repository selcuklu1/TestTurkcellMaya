package tests.BirimeHavale;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.SistemLoglariPage;

public class BirimeHavaleEdilendenHavale extends BaseTest {
    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    HavaleOnayinaSunduklarimPage havaleOnayinaSunduklarimPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerimPage;
    GelenEvraklarPage gelenEvraklarPage;
    SistemLoglariPage sistemLoglariPage;

    @BeforeMethod
    public void loginBeforeTests() {
        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        havaleOnayinaSunduklarimPage = new HavaleOnayinaSunduklarimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleOnayiVerdiklerimPage = new HavaleOnayiVerdiklerimPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        sistemLoglariPage = new SistemLoglariPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2303: Birim havale edilenler listesinden birim ve kişiye havale ve evrak geçmiş kontrolü")
    public void TS2303() throws InterruptedException {
        String testid = "TS-2303";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2303-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak havale edildi (Gereği İçin)";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        String onaylayacakKisi = "Mehmet BOZDEMİR";
        String onayKisiDetails = "BHUPGMY";
        String evrakNo;
        String evrakSayiSag = createRandomNumber(5);
        String gKontrolu = "(G)";

        String gerekicin = "Gereği için";
        String bilgiicin = "Bilgi için";
        String koordinasyonicin = "Koordinasyon İçin";

        testStatus(testid, "PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur(birim,details)
                .teslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "Test Başladı");
        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu)
                .evrakNoIleTablodanEvrakSecme(konu)
                .havaleYapKontrol()
                .havaleYap()
                .havaleAlanKontrolleri()

                .havaleIslemleriKisiSec(onaylayacakKisi,onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .gonder()
                .islemMesaji().basariliOlmali();

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .evrakAlanVeAdetKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo,evrakTarihi,evrakSayiSag,gKontrolu,2)
                .evrakGecmisTabKontrolu()
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(onaylayacakKisi, islemSureci, evrakTarihiSaat)
                .evrakGecmisi(birim, islemSureci, evrakTarihiSaat);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konu)
                .evrakAlanVeAdetKontrolleri(konu,geldigiKurum,evrakTarihi,evrakNo,gerekicin,evrakTarihi,evrakSayiSag,1);





    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2304: Havale işleminin sistem loglarından kontrolü")
    public void TS2304() throws InterruptedException {
        String testid = "TS-2303";
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "120.05";
        String konu = "TS-2303-" + getSysDate();
        String evrakTuru = "Resmi Yazışma";
        String evrakDili = "Türkçe";
        String evrakTarihi = getSysDateForKis();
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String gizlilikDerecesi = "Normal";
        String kisiKurum = "Kurum";
        String geldigiKurum = "Esk Kurum 071216 2";
        String evrakGelisTipi = "Posta";
        String ivedilik = "Normal";

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String details = "BHUPGMY";

        String kisi = "Zübeyde Tekin";
        String islemSureci = "Evrak havale edildi (Gereği İçin)";

        String fileName = "test.txt";
        String pathToFileText = getUploadPath() + "test.txt";

        String gerek = "GEREĞİ İÇİN GÖNDER";
        String bilgi = "BİLGİ İÇİN GÖNDER";
        String koordinasyon = "KOORDİNASYON İÇİN GÖNDER";

        String onaylayacakKisi = "Mehmet BOZDEMİR";
        String onayKisiDetails = "BHUPGMY";
        String evrakNo;
        String evrakSayiSag = createRandomNumber(5);
        String gKontrolu = "(G)";

        String gerekicin = "Gereği için";
        String bilgiicin = "Bilgi için";
        String koordinasyonicin = "Koordinasyon İçin";
        String aksiyon = "Evrak Havale Etme";
        String aciklama = "ztekin kullanıcısı, " + evrakTarihiSaat;
        String ipAdress = "";

        testStatus(testid, "PreCondition Evrak Oluşturma");
        gelenEvrakKayitPage
                .openPage();

        //Pre-requisites Evrak Oluşturma
        gelenEvrakKayitPage
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konu)
                .evrakTuruSec(evrakTuru)
                .evrakDiliSec(evrakDili)
                .evrakTarihiDoldur(evrakTarihi)
                .gizlilikDerecesiSec(gizlilikDerecesi)
                .kisiKurumSec(kisiKurum)
                .geldigiKurumDoldurLovText(geldigiKurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .evrakGelisTipiSec(evrakGelisTipi)
                .ivedilikSec(ivedilik)
                .dagitimBilgileriBirimDoldur2(birim)
//                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .kaydet();

        evrakNo = gelenEvrakKayitPage.popUps();


        gelenEvrakKayitPage
                .islemMesaji().basariliOlmali(basariMesaji);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konu)
                .teslimAlVeHavaleEt()
                .teslimAlVeHavaleEtBirimDoldur(birim,details)
                .teslimAlveHavaleEtTeslimAlGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        testStatus(testid, "Test Başladı");
        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTabloKontrolu(konu)
                .evrakNoIleTablodanEvrakSecme(konu)
                .havaleYapKontrol()
                .havaleYap()
                .havaleAlanKontrolleri()

                .havaleIslemleriKisiSec(onaylayacakKisi,onayKisiDetails)
                .eklenenKisiKontrolu(onaylayacakKisi)
                .eklenenKisiOpsiyonKontrolu(gerek)

                .dagitimBilgileriBirimDoldurWithDetails(birim, details)
                .eklenenBirimKontrolu(birim)
                .eklenenBirimOpsiyonKontrolu(gerek)

                .gonder()
                .islemMesaji().basariliOlmali();
        testStatus(testid, "Test Başladı");



        sistemLoglariPage
                .openPage()
                .ekranSistemLoglarıKontrol()
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon, evrakTarihiSaat, kisi.toUpperCase(), aciklama, true);

//      TODO Müşterinin onaylaması durumunda bu satır aktiflenecek, çünkü external bir adrese gidiyor.
//      ipAdress = myip();
//      login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
//      sistemLoglariPage
//                .openPage()
//                .ekranSistemLoglarıKontrol()
//                .aksiyonSec(aksiyon)
//                .sorgula()
//                .sistemRaporuKontrol(aksiyon, evrakTarihiSaat, kullanici.toUpperCase(), aciklama, ipAdress, true);

    }

}
