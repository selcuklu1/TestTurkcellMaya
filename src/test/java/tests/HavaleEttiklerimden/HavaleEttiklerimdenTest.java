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
import pages.altMenuPages.EvrakDetayiPage;
import pages.pageComponents.EvrakOnizleme;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.HavaleEttiklerimPage;
import pages.solMenuPages.HavaleOnayiVerdiklerimPage;
import pages.solMenuPages.HavaleOnayınaGelenlerPage;
import pages.ustMenuPages.GelenEvrakKayitPage;
import pages.ustMenuPages.SistemLoglariPage;

import static data.TestData.*;

/****************************************************
 * Tarih: 2018-02-12
 * Proje: Türksat Functional Test Automation
 * Class: "Teslim Aldıklarım Havale" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Havale Ettiklerim")
public class HavaleEttiklerimdenTest extends BaseTest {

    GelenEvrakKayitPage gelenEvrakKayitPage;
    HavaleEttiklerimPage havaleEttiklerimPage;
    SistemLoglariPage sistemLoglariPage;
    GelenEvraklarPage gelenEvraklarPage;
    EvrakOnizleme evrakOnizleme;
    EvrakDetayiPage evrakDetayiPage;
    HavaleOnayınaGelenlerPage havaleOnayınaGelenlerPage;
    HavaleOnayiVerdiklerimPage havaleOnayiVerdiklerimPage;

    String basariMesaji = "İşlem başarılıdır!";
    String konuKodu = "Diğer";
    String evrakSayiSag = createRandomNumber(10);
    String evrakTarihi = getSysDateForKis();
    String kurum = "BÜYÜK HARFLERLE KURUM";
    String birim = "Yazılım Geliştirme Direktörlüğ";
    String kisi = "Mehmet Bozdemir";
    String kisi2 = "Zübeyde Tekin";
    String aksiyon = "Havale Onayına Gelenler-Havale Onayla";
    String birim2 = "YGD";
    String not = createRandomText(15);
    String konuKoduRandomTS2302 = "TC-2302_" + createRandomNumber(15);

    @BeforeMethod
    public void loginBeforeTests() {
        havaleEttiklerimPage = new HavaleEttiklerimPage();
        gelenEvrakKayitPage = new GelenEvrakKayitPage();
        sistemLoglariPage = new SistemLoglariPage();
        gelenEvraklarPage = new GelenEvraklarPage();
        evrakDetayiPage = new EvrakDetayiPage();
        havaleOnayınaGelenlerPage = new HavaleOnayınaGelenlerPage();
        havaleOnayiVerdiklerimPage = new HavaleOnayiVerdiklerimPage();
    }

    @Step("Havale Ettiklerim sayfasına evrak düşürmektedir.")
    public void TS2302PreCondition(String konuKodu,String kurum,String kullanici,String kullanici2) {

        gelenEvrakKayitPage
                .gelenEvrakKayitKullaniciHavaleEt(konuKodu,kurum,kullanici);
        gelenEvraklarPage
                .openPage()
                .tabloEvrakNoSec(konuKodu)
                .havaleYap()
                .havaleYapKisiDoldur(kullanici2)
                .havaleYapGonder()
                .islemMesaji().basariliOlmali(basariMesaji);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2302: Havale ettiklerim listesinden kullanıcı listesine havale onayına sunma ve evrak geçmiş kontrolü")
    public void TS2302() {

        login(usernameZTEKIN, passwordZTEKIN);

        TS2302PreCondition(konuKoduRandomTS2302,kurum,"Zübeyde Tekin","Mehmet Bozdemir");

        havaleEttiklerimPage
                .openPage()
                .evrakNoIleEvrakIcerikGoster(konuKoduRandomTS2302)
                .havaleYap();

        evrakDetayiPage
                .icerikGosterHavaleBilgilerininGirilecegiAlanlarınGeldigiGorme();

        havaleEttiklerimPage
                .icerikGosterHavaleYapKullaniciListesiDoldur("TS1590")
                .icerikGosterHavaleyapKullaniciListesiGeregiIcınBilgiIcinDegistir()
                .icerikGosterHavaleYapOnaylayacakKisiDoldur(kisi, birim2)
                .icerikGosterHavaleYapHavaleOnayinaGonder()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleEttiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2302)
                .evrakGecmisiSec()
                .evrakGecmisiKisiVeMesajKontrol("Evrak onaya sunuldu", kisi2)
                .evrakGecmisiKisiVeMesajKontrol("Evrak havale onayı bekliyor", kisi);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2312: Onaya sunulan havalenin onaylanması")
    public void TS2312() {
        TS2302();
        login(userMbozdemir);

        havaleOnayınaGelenlerPage
                .openPage()
                .evrakSecIcerikGoster(konuKoduRandomTS2302,true)
                .havaleOnayi()
                .havaleOnayiOnaylaOnayiReddetGeldigiGorme()
                .icerikGosterHavaleOnayiOnayla()
                .havaleyiOnaylamakUzersinizUyariGeldigiGorme()
                .icerikHavaleyiOnaylamakUzeresinizEvet()
                .islemMesaji().basariliOlmali(basariMesaji);

        havaleOnayiVerdiklerimPage
                .openPage()
                .evrakNoIleEvrakSec(konuKoduRandomTS2302)
                .evrakSecEvrakGecmisiSec()
                .evrakGecimisiGeregiVeBilgiGeldigiGorme(" Evrak havale edildi (Bilgi İçin)","Mehmet Bozdemir"," Evrak havale edildi (Gereği İçin)","YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2312: Onaya sunulan havalenin onaylanması")
    public void TS2313() {

        TS2312();

        sistemLoglariPage
                .openPage()
                .kullaniciSec(kisi)
                .aksiyonSec(aksiyon)
                .sorgula()
                .sistemRaporuKontrol(aksiyon,evrakTarihi,kisi,"evrakın havalesini onaylamıştır.",true);
    }
}
