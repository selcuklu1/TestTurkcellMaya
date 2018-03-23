package tests.EvrakHavaleIslemleri;

import common.BaseTest;
import common.ReusableSteps;
import io.qameta.allure.Epic;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageComponents.EvrakOnizleme;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.*;

@Epic("Evrak Havale İşemleri")
public class EvrakHavaleIslemleriTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    GelenEvraklarPage gelenEvraklarPage;
    HavaleEttiklerimPage havaleEttiklerimPage;
    EvrakDetayiPage evrakDetayiPage;
    TeslimAlinmayiBekleyenlerPage teslimAlinmayiBekleyenlerPage;
    KaydedilenGelenEvraklarPage kaydedilenGelenEvraklarPage;
    BirimHavaleEdilenlerPage birimHavaleEdilenlerPage;
    BirimeIadeEdilenlerPage birimeIadeEdilenlerPage;
    ReusableSteps reusableSteps;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    TeslimAlinanlarPage teslimAlinanlarPage;
    EvrakOlusturPage evrakOlusturPage;
    EvrakOnizleme evrakOnizleme;

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
        teslimAlinanlarPage = new TeslimAlinanlarPage();
        evrakOlusturPage = new EvrakOlusturPage();
        evrakDetayiPage = new EvrakDetayiPage();
        evrakOnizleme = new EvrakOnizleme();
        birimeIadeEdilenlerPage = new BirimeIadeEdilenlerPage();
    }

    @Test(enabled = true, description = "TS2295: Toplu havale ekranı alan kontrolü")
    public void TS2295() {

        String konuKodu = "TS2295-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "Yazılım Geliştirme Direktörlüğü";
        String dikkatMesaji = "Evrak seçilmemiştir!";

        login(usernameZTEKIN,passwordZTEKIN);
        //Pre Condition oluşturulmakta
        gelenEvrakKayitPage.gelenEvrakKayitKullaniciHavaleEt(konuKodu,kurum,kullanici);
        login(usernameZTEKIN,passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitKaydedilenGelenEvraklarEvrakOlustur(konuKodu,kurum);
        login(usernameZTEKIN,passwordZTEKIN);
        reusableSteps.teslimAlinanlarEvrakOlustur(konuKodu,kurum,birim);
        login(usernameZTEKIN,passwordZTEKIN);
        gelenEvrakKayitPage.gelenEvrakKayitBirimHavaleEt(konuKodu,kurum,birim);
        //

        gelenEvraklarPage
                .openPage()
                .topluHavale()
                .islemMesaji().isDikkat(dikkatMesaji);

        kaydedilenGelenEvraklarPage
                .openPage()
                .topluHavale()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .teslimAlveHavaleEt()
                .islemMesaji().dikkatOlmali(dikkatMesaji);

        teslimAlinanlarPage
                .openPage()
                .topluHavale()
                .islemMesaji().isDikkat(dikkatMesaji);
    }

    @Test(enabled = true, description = "TS2198: Havale İşlemi Yapılabilecek Ekranların kontrolü")
    public void TS2198() {

        String konuKodu = "TS2295-"+createRandomNumber(15);
        String kurum = "BÜYÜK HARFLERLE KURUM";
        String kullanici = "Zübeyde Tekin";
        String birim = "Yazılım Geliştirme Direktörlüğü";
        String dikkatMesaji = "Evrak seçilmemiştir!";

        login(usernameZTEKIN,passwordZTEKIN);

        gelenEvrakKayitPage
                .openPage()
                .havaleIslemleriTabAc()
                .havaleIslemleriGeldigiGorulur(true,true,true,true,true,true,true,true,true);

        gelenEvraklarPage
                .openPage()
                .evrakSec()
                .havaleYap()
                .havaleEkranAcilidigiGeldigiGorme()
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true)
                .ilkIcerikGoster()
                .icerikHavaleYap()
                .icerikHavaleYapEkranGeldigiGorme()
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        gelenEvraklarPage
                .openPage()
                .ilkIkiEvrakCheckBoxSec()
                .topluHavale()
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        havaleEttiklerimPage
                .openPage()
                .ilkEvrakSec()
                .havaleYap()
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true)
                .ilkEvrakIcerikGoster()
                .havaleYap();

        evrakDetayiPage
                .havaleYapEkranGeldigiGorme()
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        kaydedilenGelenEvraklarPage
                .openPage()
                .ilkEvrakSec()
                .havaleYap()
                .havaleYapEkranGeldigiGorme();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);

        kaydedilenGelenEvraklarPage
                .ilkEvrakIcerikGoster()
                .havaleYap();

        evrakDetayiPage
                .havaleYapEkranGeldigiGorme()
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        kaydedilenGelenEvraklarPage
                .openPage()
                .ilkIkiEvrakCheckBoxSec()
                .topluHavale();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .evrakSec()
                .teslimAlVeHavaleEt();

        evrakOnizleme
                .teslimAlVeHavaleEtGeldigiGorme()
                .teslimveHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true,true);

        teslimAlinmayiBekleyenlerPage
                .evrakSec()
                .evrakSecCheckboxIlkSec()
                .topluTeslimAlVeHavaleEt()
                .topluHavaleEkranGeldigiGorulur();

        evrakOnizleme
                .teslimAlvehavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);

        teslimAlinmayiBekleyenlerPage
                .ilkEvrakIcerikGoster()
                .teslimAlVeHavaleEt();

        evrakDetayiPage
                .icerikGosterTeslimAlVeHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        teslimAlinmayiBekleyenlerPage
                .openPage()
                .ilkIkiEvrakCheckBoxSec()
                .topluTeslimAlVeHavaleEt()
                .topluHavaleEkranGeldigiGorulur();

        evrakOnizleme
                .teslimAlvehavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);

        teslimAlinanlarPage
                .openPage()
                .evrakSec()
                .havaleYap()
        .havaleYapEkranGeldigiGorulur();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        teslimAlinanlarPage
                .ilkEvrakIcerikGoster()
                .havaleYap();

        evrakDetayiPage
                .havaleYapEkranGeldigiGorme()
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        teslimAlinanlarPage
                .openPage()
                .ilkIkiEvrakCheckBoxSec()
                .topluHavale()
                .havaleYapEkranGeldigiGorulur();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        birimHavaleEdilenlerPage
                .openPage()
                .ilkEvrakSec()
                .havaleYap();

        evrakOnizleme
                .havaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true);

        birimHavaleEdilenlerPage
                .ilkIcerikGosterSec()
                .icerikGosterHavaleYap();

        evrakDetayiPage
                .havaleYapEkranGeldigiGorme()
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        birimeIadeEdilenlerPage
                .openPage()
                .evrakSec()
                .havaleYap();

        evrakOnizleme
                .teslimAlvehavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);

        birimeIadeEdilenlerPage
                .evrakSecCheckboxIlkSec()
                .topluTeslimAlveHavaleEt();

        evrakOnizleme
                .teslimAlvehavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);

        birimeIadeEdilenlerPage
                .evrakSecICerikGoster()
                .teslimAlVeHavaleEt();

        evrakDetayiPage
                .icerikGosterTeslimAlVeHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        birimeIadeEdilenlerPage
                .openPage()
                .ilkIkiEvrakCheckBoxSec()
                .topluTeslimAlveHavaleEt();

        evrakOnizleme
                .teslimAlvehavaleBilgilerininGirilecegiAlanlarınGeldigiGorme(true,true,true,true,true,true,true,true,true);
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

        ///CAN ŞEKER DENEME
    }










}
