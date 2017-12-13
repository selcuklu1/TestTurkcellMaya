package TopluPostalama;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;


public class TopluPostalamaTest extends BaseTest {


    TopluPostalanacakEvraklarPage topluPostalanacakEvraklarPage;


    @BeforeMethod
    public void loginBeforeTests() {

        topluPostalanacakEvraklarPage = new TopluPostalanacakEvraklarPage();
    }

    @Test(enabled = true, description = "1804 : Toplu Postalanacak Evrakların Sorgulanması (UC_POSTAYÖNETİMİ_001)")
    public void TC01804() {


        login("mbozdemir", "123");
        topluPostalanacakEvraklarPage
                .openPage()
                .gidecegiYerListesiAlfabetikSiraKontrolu();
        //.gidecegiYerSec("Başbakan Başmüşavirleri", true);


    }

    @Test(enabled = true, description = "1808 : Posta Listesine Evrak Ekleme ve Çıkartma (UC_POSTAYÖNETİMİ_002)")
    public void TC01808() {

        String[] gidecegiYerler = new String[] {
                "Adalet Bakanlığı",
                "Adalet Bakanlığı Döner Sermaye İşletmesi",
                "Aile ve Sosyal Politikalar Bakanlığı",
                "Başbakan Başmüşavirleri"
        };

        String[] postaYerleri = new String[] {
                "Adi Posta"
        };

        String baslangicTarihi = "01.12.2017";
        String bitisTarihi = "01.12.2018";

        String postaListesi = "deneme";

        login("mbozdemir", "123");
        topluPostalanacakEvraklarPage
                .openPage()
                .tarihAraligiSec(baslangicTarihi, bitisTarihi)
                .gidecegiYerSec(gidecegiYerler, true)
                .postaTipiSec(postaYerleri)
                .sorgula()
                .postaListesineAktar()
                .postaListesiSec(postaListesi);



    }


}