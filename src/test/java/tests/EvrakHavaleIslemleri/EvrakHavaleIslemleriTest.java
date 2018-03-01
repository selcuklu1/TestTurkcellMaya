package tests.EvrakHavaleIslemleri;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.BirimHavaleEdilenlerPage;
import pages.solMenuPages.KaydedilenGelenEvraklarPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.*;


public class EvrakHavaleIslemleriTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
    }

    @Test(enabled = true, description = "2295: Toplu havale ekranı alan kontrolü")
    public void TS2295() {

        String konuKodu = "TS2295-"+createRandomNumber(15);
        String konuKodu2 = "TS2295-2_"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "Zübeyde Tekin";

        login(usernameZTEKIN,passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitKullaniciHavaleEt(konuKodu,kurum,kullanici);

        gelenEvrakKayitPage
                .evrakOlusturSayfaKapat();

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKodu2,kurum,"Yazılım Geliştirme Direktörlüğü");
    }


    @Test(enabled = true, description = "TS2293: Görüntülenen evrakın birim havale edilenler listesinden geri çekilemediğinin kontrolü")
    public void TS2293() {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "TS2293-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        login(usernameZTEKIN,passwordZTEKIN);

        //gelenEvrakKayitPage.gelenEvrakKayitKaydedilenGelenEvraklarEvrakOlustur(konuKodu,kurum);

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakSecHavaleYap()
                .havaleYapBirimDoldur(birim)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu);

        login(usernameZTEKIN,passwordZTEKIN);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konuKodu);

    }

}
