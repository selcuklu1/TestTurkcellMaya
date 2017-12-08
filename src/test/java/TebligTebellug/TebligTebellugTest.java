package TebligTebellug;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EvrakDetayiPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.MesajlarPage;
import pages.solMenuPages.TebligEttiklerimPage;
import pages.solMenuPages.TebliglerPage;


public class TebligTebellugTest extends BaseTest {

    TebligEttiklerimPage tebligEttiklerimPage;
    MesajlarPage mesajlarPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakDetayiPage evrakDetayiPage;
    TebliglerPage tebliglerPage;

    @BeforeMethod
    public void loginBeforeTests() {
        tebligEttiklerimPage = new TebligEttiklerimPage();
        mesajlarPage = new MesajlarPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        tebliglerPage = new TebliglerPage();
        login("yakyol", "123");
    }

    @Test(enabled = true, description = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi.")
    public void TC00845() {

        String konu = "Şikayet ve Talepler";
        String geldigiYer = "Yürütme / Adalet Bakanlığı";
        String kayitTarihiSayi = "07.12.2017 / 5036";
        String evrakTarihi = "07.12.2017";
        String evrakNo = "5123";

        String tebligEdilecekKullanicilistesi = "YAZILIM GELİŞTİRME";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Bilsay OTÇU"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Yasemin Çakıl AKYOL";
        String evrakTipi = "Gelen Evrak";

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
                .tebligGecmisiKontrol("Yasemin Çakıl AKYOL - (07.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("botcu", "qskJfhcQ");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();


    }

    @Test(enabled = true, description = "845 : Gelen Evrakın Önizleme ekranından kullanıcıya tebliğ edilmesi")
    public void TC00845A() {

        String konu = "Aday Müşteri İşlemleri";
        String geldigiYer = "Yürütme / Aile ve Sosyal Politikalar Bakanlığı";
        String kayitTarihiSayi = "07.12.2017 / 5037";
        String evrakTarihi = "07.12.2017";
        String evrakNo = "8876";

        String tebligEdilecekKisi = "Optiim TEST1";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST1"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Yasemin Çakıl AKYOL";
        String evrakTipi = "Gelen Evrak";

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
                .tebligGecmisiKontrol("Yasemin Çakıl AKYOL - (07.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);

        logout();

        login("test1", "123");

        tebliglerPage
                .openPage()
                .tebliglerMenuKirmiziKontrolu()
                .evrakSec(konu, birim, tebligEden, tebligNot)
                .icerikGoster(konu, birim, tebligEden, evrakTipi, tebligNot);

        evrakDetayiPage
                .sayfaAcilmali();


    }


    @Test(enabled = true, description = "67 : Tebliğe gelen evrakın tebellüğ edilmesi ve tebliğ eden kullanıcıdan kontrolü")
    public void TC00067() {

        String konu = "Aday Müşteri İşlemleri";
        String geldigiYer = "Yürütme / Aile ve Sosyal Politikalar Bakanlığı";
        String kayitTarihiSayi = "07.12.2017 / 5037";
        String evrakTarihi = "07.12.2017";
        String evrakNo = "8876";

        String tebligEdilecekKisi = "Optiim TEST1";

        String tebligNot = "845 : Gelen Evrakın kullanıcı listesine detay ekrandan tebliğ edilmesi. Deneme 02";

        String[] tebligGecmisiKontrolEdilecekKullanicilar = new String[]{
                "Optiim TEST1"
        };

        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞ";
        String tebligEden = "Yasemin Çakıl AKYOL";
        String evrakTipi = "Gelen Evrak";

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
                .tebligGecmisiKontrol("Yasemin Çakıl AKYOL - (07.12.2017)", tebligGecmisiKontrolEdilecekKullanicilar);





    }

    @Test(enabled = true, description = "936 : Tebliğ hatırlatma ve Mesaj kontrolü")
    public void TC00936() {

        String konu = "USUL VE ESASLAR";
        String evrakTarihi = "15.11.2017";
        String no = "1";
        String tebligHatirlatNotu = "tebliğhatırlatma";
        String tebligEdenKullanici = "Yasemin Çakıl AKYOL";
        String basariMesaj = "İşlem başarılıdır!";
        String mesajKonu = "Tebliğ Hatırlatma";

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
                .mesajKontrol(mesajKonu, "2017", tebligHatirlatNotu);

    }

}