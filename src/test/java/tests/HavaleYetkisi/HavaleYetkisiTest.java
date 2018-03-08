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
    public void preconTümBirimleriGorebilme(String[] rolAdi, String eklenecekAksiyon) throws InterruptedException {

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
                    .btnDialogselectAction(eklenecekAksiyon)
                    .btnDialogAksiyonEkle()
                    .islemMesaji().isBasarili();
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true, description = "\"Havale İşlemleri Tüm Birimleri Görebilme\" aksiyonunu kaldırma")
    public void TS2253() throws InterruptedException {
        String rolad = "TS2253 20180308160222";
        String yeniAksiyon = "Havale İşlemleri Tüm Birimleri Görebilme";
        String guncelBirim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String ad = "Havale İşlemleri Tüm Birimleri Görebilme";
        String mesaj = "Rolün aksiyonunu silmek istediğinize emin misiniz?";

        login(TestData.user21g);
//        login(TestData.userMbozdemir);

//        preTS2253(rolad, yeniAksiyon);

        String menuName = "Profil";
        mainPage
                .userMenuAc()
                .userMenuKontrol(menuName)
                .userMenuMenuSec(menuName);

        profildenRolAdıAlma(guncelBirim);
        profilEkraniKapat();

        preconTümBirimleriGorebilme(rolAdi,yeniAksiyon);

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
                    .aksiyonListesiAdDoldur(ad)
                    .aksiyonListesiKontol(ad)
                    .aksiyonListesiRolSil(ad)
                    .islemOnayPopupKapat(mesaj)
                    .islemMesaji().isBasarili();
        }


//        login("hgner", "123");

    }

    @Step("Profil ekranında Rol Listesi tablosundan Rol adları alınır.")
    private String[] profildenRolAdıAlma(String guncelBirim) {

        ElementsCollection tblRolListesi = $$("div[class='ui-datatable-scrollable-body'] tbody[id$='data'] tr[data-ri]");

        rolAdi = new String[tblRolListesi.size()];

        for (int i = 0; i < tblRolListesi.size(); i++) {
            String birim = tblRolListesi.get(i).$("td:nth-child(2)").text();
            if (birim.equals(guncelBirim))
                rolAdi[i] = tblRolListesi.get(i).$("td:nth-child(1)").text();
            Allure.addAttachment("Rol Adı : ", rolAdi[i]);
        }

//        $x("//span[text()='Profil']//..//..//div//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();

        return rolAdi;
    }

    @Step("Profil ekranı kapatılır.")
    private void profilEkraniKapat() {
        $x("//span[text()='Profil']//..//..//div//a[@class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all']").click();
    }
}
