package TebligTebellug;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.MesajlarPage;
import pages.solMenuPages.TebligEttiklerimPage;


public class TebligTebellugTest extends BaseTest {

    TebligEttiklerimPage tebligEttiklerimPage;
    MesajlarPage mesajlarPage;

    @BeforeMethod
    public void loginBeforeTests() {
        tebligEttiklerimPage = new TebligEttiklerimPage();
        mesajlarPage = new MesajlarPage();
        login("yakyol", "123");
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
                .evrakSec(konu,"", evrakTarihi, no)
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