package tests.HavaleYetkisi;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.sun.activation.registries.MailcapTokenizer;
import common.BaseTest;
import data.TestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.tabs.AltTabs;
import pages.ustMenuPages.KullaniciYonetimiPage;
import pages.ustMenuPages.RolYonetimiPage;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class HavaleYetkisiTest extends BaseTest {


    RolYonetimiPage rolYonetimiPage;
    MainPage mainPage;
    KullaniciYonetimiPage kullaniciYonetimiPage;

    String degerKod = createRandomNumber(8);
    String[] rolAdi;


    @BeforeMethod
    public void BeforeTest() {
        rolYonetimiPage = new RolYonetimiPage();
        mainPage = new MainPage();
        kullaniciYonetimiPage = new KullaniciYonetimiPage();


    }


    @Step("Havale işlemleri Tüm birimleri görebilme aksiyonlu rol oluşturma")
    public void preTS2253(String yenirolad, String eklenecekAksiyon) throws InterruptedException {

        String kullaniciAdi = "username21g";

        rolYonetimiPage.openPage();
        rolYonetimiPage.btnYeniRolekle()
                .txtYeniRolAd(yenirolad)
                .txtYeniRolKısaAd(yenirolad)
                .txtYeniRolEtiket("TS2253")
                .txtRolDegerKod(degerKod)
                .txtRolYetkiOnceligi(3)
                .btnYeniRolKaydetme();

        rolYonetimiPage.txtRolAdArama(yenirolad)
                .btnRolArama()
//                .tblRolListeSecim(yenirolad)
                .tblRolListeSecimAksiyonButonu(yenirolad)
                .btnYeniAksiyonEkle()
                .yeniAksiyonİliskilendirmeSorgulamaveFiltrelemeTabAc()
                .txtDialogAksiyonad(eklenecekAksiyon)
                .btnDialogAksiyonAra()
                .btnDialogselectAction(eklenecekAksiyon)
                .btnDialogAksiyonEkle();

//        kullaniciYonetimiPage
//                .openPage()
//                .kullaniciAdiDoldur(kullaniciAdi)
//                .ara()
//                .kullaniciListesiGuncelle(kullaniciAdi)
//                .rolListeriEkle()
//                .yeniRolIliskilendirmeKullaniciRolSec(yenirolad)
//                .yeniRolIliskilendirmeKaydet()
//                .kullaniciGuncelleKaydet();

    }

    @Step("PreContion : Havale işlemleri Tüm birimleri görebilme aksiyonlu rol oluşturma")
    public void preconTümBirimleriGorebilmeEkle(String[] rolAdi, String eklenecekAksiyon) throws InterruptedException {

        String kullaniciAdi = "username21g";

        rolYonetimiPage
                .openPage();

        for(int i=0;i<rolAdi.length;i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .btnYeniAksiyonEkle()
                    .yeniAksiyonİliskilendirmeSorgulamaveFiltrelemeTabAc()
                    .txtDialogAksiyonad(eklenecekAksiyon)
                    .btnDialogAksiyonAra()
                    .btnDialogselectAction(eklenecekAksiyon);
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "\"Havale İşlemleri Tüm Birimleri Görebilme\" aksiyonunu kaldırma")
    public void TS2253() throws InterruptedException {
        String aksiyonAdi = "Havale İşlemleri Tüm Birimleri Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);
//        login(TestData.userMbozdemir);

//        preTS2253(rolad, aksiyonAdi);

        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi=mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconTümBirimleriGorebilmeEkle(rolAdi,aksiyonAdi);

        login(TestData.user21g);
        rolYonetimiPage
                .openPage()
                .rolYonetimiSayfaKontrolu();

        for (int i = 0; i < rolAdi.length; i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .rolListesiKontrolu(rolAdi[i])
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .aksiyonListesiKontrol()
                    .aksiyonListesiAdDoldur(aksiyonAdi)
                    .aksiyonListesiKontol(aksiyonAdi)
                    .aksiyonListesiRolSil(aksiyonAdi)
                    .islemOnayPopupKapat(mesaj)
                    .islemMesaji().isBasarili();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS2250 : Havale İşlemleri Tüm Kullanıcıları Görebilme aksiyonunu kaldırma")
    public void TS2250() throws InterruptedException{

        String aksiyonAdi = "Havale İşlemleri Tüm Kullanıcıları Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);

        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        rolAdi=mainPage.profildenRolAdiAlma(guncelBirim);
        mainPage.profilEkraniKapat();

        preconTümBirimleriGorebilmeEkle(rolAdi,aksiyonAdi);

        login(TestData.user21g);
        rolYonetimiPage
                .openPage()
                .rolYonetimiSayfaKontrolu();

        for (int i = 0; i < rolAdi.length; i++) {
            rolYonetimiPage
                    .rolYonetimiSorgulamaveFiltrelemeTabAc()
                    .txtRolAdArama(rolAdi[i])
                    .btnRolArama()
                    .rolListesiKontrolu(rolAdi[i])
                    .tblRolListeSecimAksiyonButonu(rolAdi[i])
                    .aksiyonListesiKontrol()
                    .aksiyonListesiAdDoldur(aksiyonAdi)
                    .aksiyonListesiKontol(aksiyonAdi)
                    .aksiyonListesiRolSil(aksiyonAdi)
                    .islemOnayPopupKapat(mesaj)
                    .islemMesaji().isBasarili();
        }
    }
    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "TS0597 : Tüm kullanıcılara havale yetkisi olmayan kullanıcının ekran kontrolü.")
    public void TS0597() throws InterruptedException{

    }

}
