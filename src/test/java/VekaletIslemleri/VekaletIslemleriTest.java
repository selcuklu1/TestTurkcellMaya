package VekaletIslemleri;

import common.BaseTest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.VekaletOnaylariPage;
import pages.ustMenuPages.VekaletVerPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class VekaletIslemleriTest extends BaseTest {
    MainPage mainPage;
    VekaletVerPage vekaletVerPage;
    GelenEvraklarPage gelenEvraklarPage;
    VekaletOnaylariPage vekaletOnaylariPage;

    String aciklama = "";
    String vekaletVeren = "Yasemin";
    String vekaletAlan = "Optiim TEST";

    @BeforeMethod
    public void loginBeforeTests() {
        vekaletVerPage = new VekaletVerPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        vekaletOnaylariPage = new VekaletOnaylariPage();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme")
    public void TC0025a() throws InterruptedException {

        login(username3, password);

        String basariMesaji = "İşlem başarılıdır!";
        String[] evrakNo = new String[2];
        aciklama = "onay " + getSysDate() + " evrak";
        gelenEvraklarPage
                .openPage();

        evrakNo = gelenEvraklarPage.tablodanEvrakNoAl(2);
        String evrakNo1 = evrakNo[0];
        String evrakNo2 = evrakNo[1];
        vekaletVerPage
                .openPage();
        for (int i = 0; i < evrakNo.length; i++) {
            vekaletVerPage
                    .evrakEkle()
                    .evrakAramaDoldur(evrakNo[i])
                    .dokumanAra()
                    .evrakAramaTabloKontrolveSecim(evrakNo[i]);
        }
        vekaletVerPage
                .vekaletVerenDoldur(vekaletVeren)
                .devredilecekEvraklarKontrolu()
                .vekaletAlanDoldur(vekaletAlan)
                .onayVerecekDoldur("Zübeyde TEKİN")
                .aciklamaDoldur(aciklama)
                .devredilecekEvrakSec(evrakNo1)
                .uygula()
                .islemMesaji().beklenenMesaj(basariMesaji);

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .sorgula()
                .vekaletListesiTabloKontrol();
        logout();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme işleminde onayın Red edilmesi")
    public void TC0025b() throws InterruptedException {

        login(username2, password2);

        String aciklama = "onay 20171206142209 evrak";

        vekaletOnaylariPage
                .openPage()
                .filtreleAc()
                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .alanKontrolleri(vekaletVeren, vekaletAlan, getSysDateForKis())
                .ekleyeceginizNotlarDoldur("Not")
                .reddet();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "Onaya göndererek Vekalet Verme işleminde onayın kabul edilmesi")
    public void TC2208() throws InterruptedException {

        login(username2, password2);

        String aciklama = "onay 20171206142921 evrak";
        vekaletOnaylariPage
                .openPage()
                .filtreleAc()
                .tarihiDoldur(getSysDateForKis())
                .tablodanOnaylanacakKayıtSec(aciklama)
                .onayEvrakiKontrol()
                .detay()
                .evrakKontol("4989")
                .ekleyeceginizNotlarDoldur("Not")
                .onay();

        vekaletVerPage
                .openPage()
                .veklatListesiTabAc()
                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
                .sorgula()
                .vekaletListesiTabloKontrol();

        logout();

        login(username, password);

        vekaletVerPage
                .openPage()
                .vekaletVarUyarıPopUp();
        //tabloda vekalet verılen evragın lıstelenmedıgı kontrol edilecek...

//                .veklatListesiTabAc()
//                .vekaletListesiBaslangicTarihiDoldur(getSysDateForKis())
//                .vekaletListesiBitisTarihiDoldur(getSysDateForKis())
//                .sorgula()
//                .vekaletListesiTabloKontrol();

        login(username2, password2);
        vekaletVerPage
                .vekaletVarUyarıPopUp();

        gelenEvraklarPage
                .openPage();
        //tabloda vekalet verılen evragın lıstelenmedıgı kontrol edilecek...
    }
}
