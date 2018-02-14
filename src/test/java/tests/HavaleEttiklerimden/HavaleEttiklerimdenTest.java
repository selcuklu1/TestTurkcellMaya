/****************************************************
 * Tarih: 2017-11-22
 * Proje: Türksat Functional Test Automation
 * Class: "Kişisel işlemler bağ tipi " konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
package tests.HavaleEttiklerimden;

import common.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.solMenuPages.*;
import pages.ustMenuPages.GelenEvrakKayitPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "Teslim Aldıklarım Havale" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Teslim Aldıklarım Havale")
public class HavaleEttiklerimdenTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    HavaleEttiklerimPage havaleEttiklerimPage;

    @BeforeMethod
    public void loginBeforeTests() {
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
    }

    String basariMesaji = "İşlem başarılıdır!";
    String konuKodu = "Diğer";
    String evrakSayiSag =  createRandomNumber(10);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String birim = "Yazılım Geliştirme Direktörlüğ";
    String kisi = "Mehmet Bozdemir";
    String birim2 = "YGD";
    String not = createRandomText(15);
    String konuKoduRandomTS2302 = "TC-2302_" + createRandomNumber(15);

    @Step("Havale Ettiklerim sayfasına evrak düşürmektedir.")
    public void TS2302PreCondition() {

        login(usernameZTEKIN, passwordZTEKIN);

        gelenEvrakKayitPage
                .openPage()
                .konuKoduDoldur(konuKodu)
                .konuDoldur(konuKoduRandomTS2302)
                .evrakTarihiDoldur(evrakTarihi)
                .geldigiKurumDoldurLovText(kurum)
                .evrakSayiSagDoldur(evrakSayiSag)
                .havaleIslemleriBirimDoldur(birim)
                .kaydet()
                .evetDugmesi()
                .yeniKayitButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2302: Havale ettiklerim listesinden kullanıcı listesine havale onayına sunma ve evrak geçmiş kontrolü")
    public void TS2302() {

        TS2302PreCondition();

        havaleEttiklerimPage
                .openPage()
                .evrakNoIleEvrakIcerikGoster(konuKoduRandomTS2302)
                .havaleYap()
                .icerikGosterHavaleYapKullaniciListesiDoldur("TS1590")
                .icerikGosterHavaleyapKullaniciListesiGeregiIcınBilgiIcinDegistir()
                .icerikGosterHavaleYapOnaylayacakKisiDoldur(kisi,birim2);
    }
}
