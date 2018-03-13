package tests.EvrakHavaleIslemleri;

import common.BaseTest;
import common.ReusableSteps;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.*;


public class EvrakHavaleIslemleriTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleEttiklerimPage havaleEttiklerimPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    ReusableSteps reusableSteps;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;

    @BeforeMethod
    public void loginBeforeTests() {
        kaydedilenGelenEvraklarPage = new KaydedilenGelenEvraklarPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        teslimAlinmayiBekleyenlerPage = new TeslimAlinmayiBekleyenlerPage();
        birimHavaleEdilenlerPage = new BirimHavaleEdilenlerPage();
        reusableSteps = new ReusableSteps();
        gelenEvraklarPage = new GelenEvraklarPage();
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
    }

    @Test(enabled = true, description = "2295: Toplu havale ekranı alan kontrolü")
    public void TS2295() {

        useFirefox();
        String konuKodu = "TS2295-"+createRandomNumber(15);
        String konuKodu2 = "TS2295-2_"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "Yazılım Geliştirme Direktörlüğü";

        login(usernameZTEKIN,passwordZTEKIN);

        gelenEvrakKayitPage
                .gelenEvrakKayitBirimHavaleEt(konuKodu,kurum,birim);
        login(usernameZTEKIN,passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitKaydedilenGelenEvraklarEvrakOlustur(konuKodu,kurum);
        login(usernameYAKYOL,passwordYAKYOL);
        reusableSteps.teslimAlinanlarEvrakOlustur(konuKodu,kurum,birim);
        login(usernameYAKYOL,passwordYAKYOL);
        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKodu,kurum,birim);

        gelenEvrakKayitPage
                .evrakOlusturSayfaKapat();

        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKodu2,kurum,"Yazılım Geliştirme Direktörlüğü");
    }


    @Test(enabled = true, description = "TS2297: Görüntülenen evrakın havale onayından geri çekilemediğinin kontrolü")
    public void TS2297() {

        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "TS2293-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        login(usernameZTEKIN,passwordZTEKIN);

        gelenEvrakKayitPage.gelenEvrakKayitKaydedilenGelenEvraklarEvrakOlustur(konuKodu,kurum);

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakSecHavaleYap()
                .haveleYapEkranGeldigigorme(true)
                .havaleYapBirimDoldur(birim)
                .havaleYapOnaylanacakKisiDoldur("Mehmet Bozdemir","BHUPGMY")
                .havaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakOnizlemeGeldigiGorme(true)
                .havaleOnayi()
                .havaleOnayiOnayla()
                .havaleyiOnaylamakUzeresinizEvet();

        login(usernameZTEKIN,passwordZTEKIN);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konuKodu)
                .gerialGelmedigiGorme(false)
                .evrakSecIcerikGoster(konuKodu,true)
                .evrakSecGeriAlGelmedigiGorme(false);

    }

    @Test(enabled = true, description = "TS2283: Görüntülenen evrakın birim havale edilenler listesinden geri çekilemediğinin kontrolü")
    public void TS2283() {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "TS2283-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        login(usernameZTEKIN,passwordZTEKIN);

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konuKodu,kurum,kullanici);

        gelenEvraklarPage
                .openPage()
                .konuyaGoreEvrakIcerikGoster(konuKodu)
                .icerikHavaleYap()
                .icerikHavaleAlanKontrolleri()
                .icerikGosterKullaniciListesiDoldur("TS1590")
                .icerikGosterGonder();

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        gelenEvraklarPage
                .openPage()
                .evrakNoyaGoreEvrakSec(konuKodu)
                .evrakOnizlemeKontrolu();

        login(usernameZTEKIN,passwordYAKYOL);

        havaleEttiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakSagındaGerialGelmedigiKontrolu(false)
                .evrakNoIleEvrakIcerikGoster(konuKodu)
                .icerikGosterGeriAlGelmedigiGorme(false);
    }

    @Test(enabled = true, description = "TS2293: Görüntülenen evrakın birim havale edilenler listesinden geri çekilemediğinin kontrolü")
    public void TS2293() {
        String basariMesaji = "İşlem başarılıdır!";
        String konuKodu = "TS2293-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        gelenEvrakKayitPage.gelenEvrakKayitKaydedilenGelenEvraklarEvrakOlustur(konuKodu,kurum);

        kaydedilenGelenEvraklarPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakSecHavaleYap()
                .haveleYapEkranGeldigigorme(true)
                .havaleYapBirimDoldur(birim)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        login(usernameYAKYOL,passwordYAKYOL);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakNoIleEvrakSec(konuKodu)
                .evrakOnizlemeGeldigiGorme(true);

        login(usernameMBOZDEMIR,passwordMBOZDEMIR);

        birimHavaleEdilenlerPage
                .openPage()
                .evrakNoIleTablodanEvrakSecme(konuKodu)
                .evrakSecGerialGelmedigiGorme(false)
                .evrakSecIcerikGoster(konuKodu,true)
                .evrakIcerikGosterGerialGelmedigiGorme(false);
    }

}
