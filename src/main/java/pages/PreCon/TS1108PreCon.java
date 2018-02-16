package pages.PreCon;

import io.qameta.allure.Step;
import pages.MainPage;
import pages.ustMenuPages.BirimYonetimiPage;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak kontroller" konulu senaryoları içerir
 * Yazan: Sezai Çelik 
 ****************************************************/
public class TS1108PreCon extends MainPage {

    BirimYonetimiPage birimYonetimiPage = new BirimYonetimiPage();

    @Step("Birim oluştur")
    public TS1108PreCon birimOlustur() {

        birimYonetimiPage
                .openPage()
                .ekle()
                .birimYonetimiAlanKontrolleri()
                .gorunurlukTipiSec("Görünür Seçilmez")
//                .disBirimSec(true)
                .adDoldur("Birim Adı");

        return this;
    }
}
