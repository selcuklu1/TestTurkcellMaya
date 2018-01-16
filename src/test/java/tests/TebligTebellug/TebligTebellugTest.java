package tests.TebligTebellug;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.solMenuPages.*;


public class TebligTebellugTest extends BaseTest {

    TebligEttiklerimPage tebligEttiklerimPage;
    MesajlarPage mesajlarPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;
    TebliglerPage tebliglerPage;
    TebellugEttiklerimPage tebellugEttiklerimPage;

    @BeforeMethod
    public void loginBeforeTests() {
        tebligEttiklerimPage = new TebligEttiklerimPage();
        mesajlarPage = new MesajlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        tebliglerPage = new TebliglerPage();
        tebellugEttiklerimPage = new TebellugEttiklerimPage();
    }

    @Test(enabled = true, description = "TS0845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.")
    public void TS0845() {

        String konu = "Brifingler ve Bilgi Notları";
        String geldigiYer = "Yürütme / Adalet Bakanlığı";
        String kayitTarihiSayi = "18.12.2017 / 5128";
        String evrakTarihi = "18.12.2017";
        String evrakNo = "123161";

        String tebligEdilecekKullanicilistesi = "YAZILIM GELİŞTİRME";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Bilsay OTÇU"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        login("mbozdemir", "123");

        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, evrakNo)
                .tebligEt()
                .tebligEtKullaniciListesiDoldur(tebligEdilecekKullanicilistesi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();


        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, evrakNo);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (18.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("boTSu", "qskJfhcQ");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();


    }

    @Test(enabled = true, description = "TS0845A : Gelen Evrakın Önizleme ekranından kullanıcıya tebliğ edilmesi")
    public void TS0845A() {

        String konu = "TS845";
        String geldigiYer = "Yargı / hepsi küçük harflerle kurum ";
        String kayitTarihiSayi = "18.12.2017 / 5129";
        String evrakTarihi = "18.12.2017";
        String evrakNo = "123";

        String tebligEdilecekKisi = "Optiim TEST1";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST1"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        login("mbozdemir", "123");


        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, evrakNo)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, evrakNo);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (18.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("test1", "123");

        tebliglerPage
                .openPage()
                //.tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();


    }

    @Test(enabled = true, description = "TS0067 : Tebliğe gelen evrakın tebellüğ edilmesi ve tebliğ eden kullanıcıdan kontrolü")
    public void TS0067() {

        String konu = "TS845";
        String geldigiYer = "Yargı / hepsi küçük harflerle kurum ";
        String kayitTarihiSayi = "18.12.2017 / 5129";
        String evrakTarihi = "18.12.2017";
        String evrakNo = "123";

        String tebligEdilecekKisi = "Optiim TEST6";

        String tebligNot = "67 : 1-Tebliğe gelen evrakın tebellüğ edilmesi ve tebliğ eden kullanıcıdan kontrolü";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST6"
        };

        String[] tebligGecmisiKontrolEdilecekTarihler = new String[]{
                "18.12.2017"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Mehmet BOZDEMİR";
        String evrakTipi = "Gelen Evrak";

        String basariMesaj = "İşlem başarılıdır!";

        login("mbozdemir", "123");

        gelenEvraklarPage
                .openPage()
                .evrakSec(konu, geldigiYer, kayitTarihiSayi, evrakTarihi, evrakNo)
                .tebligEt()
                .tebligEtKisiInputDoldur(tebligEdilecekKisi)
                .tebligEtNotInputDoldur(tebligNot)
                .tebligEtTebligEt();

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, evrakNo);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (18.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);


        logout();


        login("optiimtest6", "123");

        tebliglerPage
                .openPage()
                //.tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, evrakTipi, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebellugEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, evrakNo);

        logout();
        login("mbozdemir", "123");

        tebligEttiklerimPage
                .openPage()
                .icreikGoster(konu, "", evrakTarihi, evrakNo);

        evrakDetayiPage
                .tebligGecmisiTabAc()
                .tebligGecmisiKontrol("Mehmet BOZDEMİR - (18.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar, tebligGecmisiKontrolEdilecekTarihler);


    }

    @Test(enabled = true, description = "TS0936 : Tebliğ hatırlatma ve Mesaj kontrolü")
    public void TS0936() {
        String konu = "TS0936";
        String evrakTarihi = "18.12.2017";

        String no = "123";
        String tebligHatirlatNotu = "tebliğhatırlatma";
        String tebligEdenKullanici = "Mehmet BOZDEMİR";
        String basariMesaj = "İşlem başarılıdır!";
        String mesajKonu = "Tebliğ Hatırlatma";


        login("mbozdemir", "123");

        tebligEttiklerimPage
                .openPage()
                .evrakSec(konu, "", evrakTarihi, no)
                .tebligHatirlatTabTikla()
                .okunmamisTebligleriHatirlat(true)
                .okunmusTebellugEdilmemisTebligleriHatirlat(true)
                .tebligHatirlatNotuGir(tebligHatirlatNotu)
                .tebligHatirlat()
                .islemMesaji().basariliOlmali(basariMesaj);

        logout();


        login("optiimtest2", "123");

        mesajlarPage
                .openPage()
                .mesajSec(tebligEdenKullanici, mesajKonu, konu)
                .mesajKontrol(mesajKonu, "18.12.2017", tebligHatirlatNotu);

    }

    @Test(enabled = true, description = "TS0847 : Tebliğlerin teker teker ve toplu silinmesi")
    public void TS0847() {

        String konu = "Kanunlar";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String evrakTipi = "Gelen Evrak";
        String aciklama = "test7";
        String tebligEdenKullanici = "Yasemin Çakıl AKYOL";

        String dikkatMesajiSecimsizSilme = "Tebliğ listesinden seçim yaptıktan sonra işlem yapılabilir!";
        String dikkatMesaji = "Tebellüğ edilmeden tebliğ silinemez!";
        String basariMesaj = "İşlem başarılıdır!";

        tebliglerPage
                .openPage()
                .evrakSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesajiSecimsizSilme);

        tebliglerPage
                .evrakTikSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        tebliglerPage
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);


        tebliglerPage
                .evrakKontrol(konu, birim, tebligEdenKullanici, evrakTipi, aciklama, false);


        tebliglerPage
                .evrakSec("Veri Toplama", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec("Cihaz Takip İşlemleri", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakSec("Şikayet ve Talepler", birim, tebligEdenKullanici, evrakTipi, "test7")
                .tebellugEt(true)
                .islemMesaji().basariliOlmali(basariMesaj);

        tebliglerPage
                .evrakTikSec("Veri Toplama", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .evrakTikSec("Cihaz Takip İşlemleri", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .evrakTikSec("Şikayet ve Talepler", birim, tebligEdenKullanici, evrakTipi, "test7", true)
                .tebligSil()
                .islemMesaji().basariliOlmali(basariMesaj);


        tebellugEttiklerimPage
                .openPage()
                .evrakSec("Veri Toplama", "", "", "")
                .evrakSec("Cihaz Takip İşlemleri", "", "", "")
                .evrakSec("Şikayet ve Talepler", "", "", "");


    }


}