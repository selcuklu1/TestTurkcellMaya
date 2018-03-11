package tests.EvrakIadesi;

import common.BaseTest;
import data.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.pageComponents.TextEditor;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.pageData.alanlar.GizlilikDerecesi;

import static com.codeborne.selenide.Condition.text;

/****************************************************
 * Tarih: 2018-01-30
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Iadesi" konulu senaryoları içerir
 * Yazan: Serdar Kayis
 ****************************************************/

public class EvrakIadesi extends BaseTest {

    EvrakOlusturPage evrakOlusturPage;
    TextEditor editor;
    ImzaBekleyenlerPage imzaBekleyenlerPage;
    ParafBekleyenlerPage parafBekleyenlerPage;
    ImzaladiklarimPage imzaladiklarimPage;
    BeklemeyeAlinanlarPage beklemeyeAlinanlarPage;
    IadeEttiklerimPage iadeEttiklerimPage;
    KoordineBekleyenlerPage koordineBekleyenlerPage;


    @BeforeMethod
    public void loginBeforeTests() {
        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);
        evrakOlusturPage = new EvrakOlusturPage();
        editor = new TextEditor();
        imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        parafBekleyenlerPage = new ParafBekleyenlerPage();
        imzaladiklarimPage = new ImzaladiklarimPage();
        beklemeyeAlinanlarPage = new BeklemeyeAlinanlarPage();
        iadeEttiklerimPage = new IadeEttiklerimPage();
        koordineBekleyenlerPage = new KoordineBekleyenlerPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2017: Akıştaki Evrağın Değiştirilip İmzala Butonuna Basılması - Güncellemeye Devam Et")
    public void TS2017() throws InterruptedException {

        String konu = "TS2017-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu)
                .evrakIcerikKontrol();


        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
                .icerikDegistiIptal();

        evrakOlusturPage
                .sayfaKontrol2("Evrak Detayı")
                .bilgilerTabiAc()
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiSecimTipiEskiKontrol("Kurum")
                .geregiDoldurEski(geregiKurum2,"Kurum")
                .geregiKontrolInbox(geregiKurum2)
                .ivedilikSec("Normal")
                .ivedilikKontrol("Normal")
                .konuDoldur(konu)
                .konuDoldurKontrol(konu)
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiDoldurEski(geregiKurum3,"Kurum")
                .geregiKontrolInbox(geregiKurum3)
                .imzalaButonaTikla();


        evrakOlusturPage
                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
                .evrakIcerikDegistiImzalaveDevamEt()
                .evrakSecmeliDegistiKaydet()
                .evrakImzalaUyariKontrol(evrakİmzaUyari)
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .evrakKonusunaGoreIcerikTiklama(konu);

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiKontrolInbox(geregiKurum3)
                .konuDoldurKontrol(konu)
                .ivedilikKontrol("Normal");

        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .evrakSecmeliDegistiEvet()
                .imzalanamazButtonKontrol();

        evrakOlusturPage
                .bilgilerTabiAc()
                .geregiIptal()
                .geregiSecimTipiEskiEvrak("Kurum")
                .geregiDoldurEski(geregiKurum3,"Kurum")
                .imzalaButonaTikla()
                .evrakGuncellendiImzalanamazUyariKontrol(evrakGuncellendiImzalanamazUyari);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2015: Akıştaki Evrağın Değiştirilip İmzala Butonuna Basılması - İade Et")
    public void TS2015() throws InterruptedException {

        String konu = "TS2015-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu)
                .evrakIcerikKontrol();

        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
                .evrakIcerikDegistiIadeEt()
                .evrakSecmeliDegistiKaydet()
                .kullaniciyaIadeEt()
                .evrakIcerikDegistiKaydetEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameMBOZDEMIR,TestData.passwordMBOZDEMIR);
        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .konuyaGoreEvrakIadeEtKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .parafla()
                .imzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);
        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .iadeSonrasiEvrakOnizlemeImzala()
                .iadeSonrasiImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);
        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .iadeSonrasiEvrakOnizlemeImzala()
                .sImzaİmzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2016: Akıştaki Evrağın Değiştirilip İmzala Butonuna Basılması - Öncekileri Çıkart")
    public void TS2016() throws InterruptedException {

        String konu = "TS2016-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakKonusunaGoreIcerikTiklama(konu)
                .evrakIcerikKontrol();


        editor
                .type(editorIcerik);

        evrakOlusturPage
                .imzalaButonaTikla()
                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
                .evrakIcerikDegistiImzalaveDevamEt()
                .evrakSecmeliDegistiKaydet()
                .evrakImzalaUyariKontrol(evrakİmzaUyari)
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .versiyonSorgulaIkonKontrolü(konu)
                .imzacilari(konu)
                .parafImzacilarListesiKontrol(user1,false);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);
        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .iadeSonrasiEvrakOnizlemeImzala()
                .sImzaİmzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2332: Beklemeye alınanlar listesindeki evrakta değişiklik yapılarak iade")
    public void TS2332() throws InterruptedException {

        String konu = "TS2332-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .evrakSecBeklemeyeAl()
                .beklemeyeAlUyariKontrol()
                .beklemeyeAlUyariEvet()
                .islemMesaji().basariliOlmali(basariMesaji);


        beklemeyeAlinanlarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu);

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .imzalaButonaTikla()
                .icerikDegistiUyarıKontrol(evrakIcerikDegistiUyari,secenek1,secenek2)
                .evrakIcerikDegistiImzalaveDevamEt()
                .evrakSecmeliDegistiKaydet()
                .evrakImzalaUyariKontrol(evrakİmzaUyari)
                .evrakImzala()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaladiklarimPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .versiyonSorgulaIkonKontrolü(konu)
                .imzacilari(konu)
                .parafImzacilarListesiKontrol(user1,false);

        login(TestData.usernameYAKYOL,TestData.passwordYAKYOL);
        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .iadeSonrasiEvrakOnizlemeImzala()
                .sImzaİmzala(true)
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2331: Beklemeye alınanlar listesindeki evrakta değişiklik yapmadan iade")
    public void TS2331() throws InterruptedException {

        String konu = "TS2331-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String islemSureci = "Evrak paraf bekliyor";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .konuyaGoreEvrakOnizlemedeAc(konu)
                .evrakSecBeklemeyeAl()
                .beklemeyeAlUyariKontrol()
                .beklemeyeAlUyariEvet()
                .islemMesaji().basariliOlmali(basariMesaji);


        beklemeyeAlinanlarPage
                .openPage()
                .evrakSecKonuyaGoreIcerikGoster(konu);

        evrakOlusturPage
                .editorTabKontrolInbox();

        evrakOlusturPage
                .iadeEt()
                .parafciKontrol(user1)
                //TODO burasi calismiyor fix it
//                .dosyaEkle(pathToFileText,fileName)
                .notAlaniDoldur(konu)
                .kullaniciyaIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(konu);

        iadeEttiklerimPage
                .openPage()
                .evrakGeldigiGorme(konu,geregiKurum,evrakTarihiSaat)
                .evrakSec(konu)
                .iadeEdilmistirIkonKontrolu(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(user1,islemSureci,evrakTarihiSaat);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS170: Evrak detay ekranından evrakın iade edilmesi ve kontrolü")
    public void TS170() throws InterruptedException {

        String konu = "TS170-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String islemSureci = "Evrak paraf bekliyor";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"İmzalama")
                .onayAkisiKullaniciKontrolu(user2 , "İmzalama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        imzaBekleyenlerPage
                .openPage()
                .evrakNoKontrolu(konu)
                .evrakKonusunaGoreIcerikTiklama(konu);

        evrakOlusturPage
                .editorTabKontrolInbox();

        evrakOlusturPage
                .iadeEt()
                .parafciKontrol(user1)
                //TODO burasi calismiyor fix it
//                .dosyaEkle(pathToFileText,fileName)
                .notAlaniDoldur(konu)
                .kullaniciyaIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(konu);

        iadeEttiklerimPage
                .openPage()
                .evrakGeldigiGorme(konu,geregiKurum,evrakTarihiSaat)
                .evrakSec(konu)
                .iadeEdilmistirIkonKontrolu(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(user1,islemSureci,evrakTarihiSaat);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS105a: Evrak detay ekranından evrakın iade edilmesi ve kontrolü")
    public void TS105a() throws InterruptedException {

        String konu = "TS105a-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String islemSureci = "Evrak paraf bekliyor";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .koordineliSec(true)
                .onayAkisiKullaniciEkle(user2,details)
//                .onayAkisiKullaniciTipiSec(user2,"İmzalama");
                .onayAkisiKullaniciKontrolu(user2 , "Koordine")
                .koordineliTikla()
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
//                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        koordineBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakKontrol(konu)
                .evrakKonusunaGoreIcerikTiklama(konu)
                .evrakIcerikKontrol();

        evrakOlusturPage
                .editorTabKontrolInbox();

        evrakOlusturPage
                .iadeEt()
                .parafciKontrol(user1)
                //TODO burasi calismiyor fix it
//                .dosyaEkle(pathToFileText,fileName)
                .notAlaniDoldur(konu)
                .kullaniciyaIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(konu);

        iadeEttiklerimPage
                .openPage()
                .evrakGeldigiGorme(konu,geregiKurum,evrakTarihiSaat)
                .evrakSec(konu)
                .iadeEdilmistirIkonKontrolu(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(user1,islemSureci,evrakTarihiSaat);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS105b: Evrak detay ekranından evrakın iade edilmesi ve kontrolü")
    public void TS105b() throws InterruptedException {

        String konu = "TS105b-" + getSysDate();
        String konuKodu = "Kanunlar";
        String kaldirilacakKlasorler = "KURUL KARARLARI";
        String evrakDerecesi = GizlilikDerecesi.GIZLI.getOptionText();
        String geregiSecimKurum = "Kurum";
        String geregiKurum = "Adalet Bakanlığı Döner Sermaye İşletmesi";
        String geregiKurum2 = "BÜYÜK HARFLERLE KURUM";
        String geregiKurum3 = "TS1493 Kurumu";
        String geregiSecimBirim = "Birim";
        String geregiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String bilgiBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";//"AFYON VALİLİĞİ";
        String geregiSecimKullanici = "Kullanıcı";
        String geregiKullanici = "Ahmet SAVAŞ";
        String akisAdim = "İmzalama";
        String editorIcerik = "Bu bir deneme mesajıdır. Lütfen dikkate almayınız.";
        String basariMesaji = "İşlem başarılıdır!";
        String user1 = "Mehmet BOZDEMİR";
        String user2 = "Zübeyde TEKİN";
        String user3 = "Yasemin Çakıl AKYOL";
        String details = "BHUPGMY";
        String sayfa1 = "Evrak Oluştur";
        String evrakGuncellendiImzalanamazUyari = "Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.";
        String evrakİmzaUyari = "Sayısal imza ile imzaladığınız belge 5070 sayılı kanun kapsamına girmemektedir.";
        String evrakIcerikDegistiUyari = "Evrak içeriğini değiştirdiğiniz için aşağıdakilerden uygun olanı seçerek işleminize devam edebilirsiniz.";
        String secenek1 = "İade Et";
        String secenek2 = "İmzala ve devam et (Önceki kullanıcıları akıştan çıkartarak)";
        String evrakTarihiSaat = getSysDateForTarihSaat();
        String islemSureci = "Evrak paraf bekliyor";
        String pathToFileText = getUploadPath() + "test.txt";
        String fileName = "test.txt";

        evrakOlusturPage
                .openPage()
                .sayfaKontrol(sayfa1)
                .bilgilerTabiAc()
                .bilgilerTabAlanKontrolleri()
                .konuKoduDoldur(konuKodu)
                .konuKoduDoldurKontrol(konuKodu)
                .konuDoldur(konu)
                //Bug: text alani "Kanunlar" olarak kalıyor yeni değer html domda set edilmiyor. Deger olarak TS2017-20180224165929 set ediliyor bizim tarafımızdan.
//                .konuDoldurKontrol(konu)
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
                .onayAkisiEkle()
                .onayAkisiKullaniciKontrolu(user1 , "Paraflama")
                .onayAkisiKullaniciEkle(user2,details)
                .onayAkisiKullaniciTipiSec(user2,"Paraflama")
                .onayAkisiKullaniciKontrolu(user2 , "Paraflama")
                .onayAkisiKullaniciEkle(user3,details)
                .onayAkisiKullaniciTipiSec(user3,"İmzalama")
                .onayAkisiKullaniciKontrolu(user3 , "İmzalama")
                .kullan()
                .paraflaKontrol();


        evrakOlusturPage
                .editorTabAc();

        editor
                .type(editorIcerik)
                .editorShouldHave(text(editorIcerik));

        evrakOlusturPage
                .editorTabKontrol()
                .editorKonuKontrol(konu)
                .editorHitapKontrol(geregiKurum.toUpperCase())
//                .editorImzaciKontrol(user2)
                .editorImzaciKontrol(user3)
                .editorDagitimKontrol(geregiKurum);

        evrakOlusturPage
                .parafla()
                .islemMesaji().basariliOlmali("İşlem başarılıdır!");


        login(TestData.usernameZTEKIN,TestData.passwordZTEKIN);

        parafBekleyenlerPage
                .openPage()
                .konuyaGoreEvrakDetayGoster(konu);

        evrakOlusturPage
                .editorTabKontrolInbox();

        evrakOlusturPage
                .iadeEt()
                .parafciKontrol(user1)
                //TODO burasi calismiyor fix it
//                .dosyaEkle(pathToFileText,fileName)
                .notAlaniDoldur(konu)
                .kullaniciyaIadeEt()
                .islemMesaji().basariliOlmali(basariMesaji);

        imzaBekleyenlerPage
                .openPage()
                .evrakOlmadigiGorme(konu);

        iadeEttiklerimPage
                .openPage()
                .evrakGeldigiGorme(konu,geregiKurum,evrakTarihiSaat)
                .evrakSec(konu)
                .iadeEdilmistirIkonKontrolu(konu)
                .secilenEvrakEvrakGecmisi()
                .evrakGecmisi(user1,islemSureci,evrakTarihiSaat);
    }

}
