package tests.EvrakDogrulama;

import common.BaseTest;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.newPages.EvrakDetayiPage;
import pages.newPages.RolYonetimiPage;
import pages.pageComponents.UserMenu;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.ParafladiklarimPage;
import pages.ustMenuPages.EvrakOlusturPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;

@Feature("Evrak Dogrulama")
public class EvrakDogrulama extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    User user4 = new User("user4", "123", "User4 TEST", "AnaBirim2");//, "Uzman Test Mühendis");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1");//, "Uzman Test Mühendis");


    @Severity(SeverityLevel.CRITICAL)
    @Test(enabled = true , description = "Sistem sabitindeki gizlilik derecesine göre evrak doğrulama checkinin kontrolü")
    public void TS2077() throws InterruptedException {
        login("Mbozdemir", "123");
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();

        evrakOlusturPage.bilgilerTabiAc()
                .konuAlaniGeldigiGorme()
                .konuKoduAlaniGeldigiKtrl()
                .gizlilikDerecesiAlaniKtrl()
                .ivedilikAlaniKtrl()
                .bilgiAlaniktrol()
                .geregiAlanigeldigiKtrol()
                .onayAkisiAlangelktrl()
                .kaldiralacakKlasoralanKtrol()
                .gizlilikDerecesiSec("Normal");
        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkevrakDogrulanabilirktrol()
                .chkboxEvrakDogrulanabilirclick();
        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Tasnif Dışı");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();

        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Hizmete Özel");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();

        evrakOlusturPage.bilgilerTabiAc()
                .gizlilikDerecesiSec("Özel");

        evrakOlusturPage.evrakDogrulamaTabAc()
                .chkboxEvrakDogrulanabilirclick();
            evrakOlusturPage.evrakDogrulamaTabAc()
                    .chkdogrulanabilirİsaretle();
    }

    @Test(description = "TS2078: Belge olmamış evrakın doğrulanamadığının kontrolü", enabled = true)
    public void TS2078() {
        User parafci = user1;
        User imzaci = user5;

        String konu = "TS2078-" + getDateTime();
        System.out.println("Konu: " + konu);
        String kurum = "Cumhurbaşkanlığı";

        login(parafci);

        pages.newPages.EvrakOlusturPage page = new pages.newPages.EvrakOlusturPage();
        page.openPage()
                .bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum, parafci,imzaci);
        page.editorTab().openTab()
                .getEditor().type("Editör tekst");

        page.dogrulamaTab().openTab()
                .dogrulanabilirSeciliKontrolu(true)
            .evrakPageButtons()
                .parafla().islemMesaji().basariliOlmali();

//        ParafladiklarimPage parafladiklarimPage = new ParafladiklarimPage();
//        EvrakDetayiPage evrakDetayiPage =
        new ParafladiklarimPage().openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster()
                .dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarılacak")
                .islemZamaniKontrolu(empty)
                .aktarilmaDurumuVeIslemZamaniKontorlu("Aktarılacak", empty, 120);

        /*evrakDetayiPage.closePage();

        //125 saniye beklenir
        step("2 dk beklenir", "121 saniye");
        Selenide.sleep(121000);*/
        /*parafladiklarimPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarılacak")
                .islemZamaniKontrolu(empty);*/
    }

    @Test(description = "TS2079: Doğrulanacak evrak oluşturma", enabled = true)
    public void TS2079() {
        User imzaci = user1;

        String konu = "TS2078-" + getDateTime();
        System.out.println("Konu: " + konu);
        String kurum = "Cumhurbaşkanlığı";

        login(imzaci);

        pages.newPages.EvrakOlusturPage page = new pages.newPages.EvrakOlusturPage();
        page.openPage()
                .bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum, imzaci);
        page.editorTab().openTab()
                .getEditor().type("Editör tekst");

        page.dogrulamaTab().openTab()
                .dogrulanabilirSeciliKontrolu(true)
                .evrakPageButtons()
                .evrakImzala().islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage();
        EvrakDetayiPage evrakDetayiPage = imzaladiklarimPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarılacak")
                .islemZamaniKontrolu(empty)
                .aktarilmaDurumuVeIslemZamaniKontorlu("Aktarıldı", empty, 10, 120);
        /*evrakDetayiPage.closePage();

        //125 saniye beklenir
        step("2 dk beklenir", "121 saniye");
        Selenide.sleep(121000);
        imzaladiklarimPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarıldı")
                .islemZamaniKontrolu(not(empty));*/
    }

    @Test(description = "TS2081: Evrak Doğrulama Aktarım - Aktar/Geri Al aksiyonunun eklemesi ve kaldırılması", enabled = true)
    public void TS2081() {
        User user = new User("user6", "123", "User6 TS2081", "AnaBirim1", "Danışman");

        String rolAdi;// = "TS2081";
        String aksiyonAdi = "Evrak Doğrulama Aktarım - Aktar/Geri Al";
        RolYonetimiPage rolYonetimiPage;
        RolYonetimiPage.YeniAksiyonIliskilendirme yeniAksiyonIliskilendirme;
        pages.newPages.EvrakOlusturPage evrakOlusturPage = new pages.newPages.EvrakOlusturPage();

        login(user);

        UserMenu userMenu = new UserMenu();
        UserMenu.Profil profil = userMenu.userMenuAc().profilMenuSec();
        profil.closeDialog();
        rolAdi = profil.getAllRoles().get(0);

        rolYonetimiPage = new RolYonetimiPage();
        aksiyonAranirYoksaEklenir(user, rolAdi, aksiyonAdi, rolYonetimiPage);

        rolYonetimiPage
                .bulunanAksiyondaIliskiyiSilBasarili();

        logout();
        login(user);

        evrakOlusturPage.openPage()
                .dogrulamaTab().tabKontrol(not(exist));

    }

    @Step("\"{aksiyonAdi}\" aksiyon yoksa eklenir")
    private void aksiyonAranirYoksaEklenir(User user, String rolAdi, String aksiyonAdi, RolYonetimiPage rolYonetimiPage) {
        rolYonetimiPage.openPage()
                .sorgulamadaAdGir(rolAdi)
                .ara()
                .rolListesindeKayitBul(text(rolAdi))
                .bulunanRoldeAksiyonlarTikla()
                .aksiyonListesindeAdGirilir(aksiyonAdi);

        if (!rolYonetimiPage.aksiyonListesindeKayitVarMi(text(aksiyonAdi))) {
            rolYonetimiPage
                    .yeniAksiyonEkle()
                    .sorgulamadaAdGir(aksiyonAdi)
                    .aksiyonBulunurVeCheckboxSecilir(aksiyonAdi, true)
                    .ekleBasarili();

            logout();
            login(user);

            rolYonetimiPage.openPage()
                    .sorgulamadaAdGir(rolAdi)
                    .ara()
                    .rolListesindeKayitBul(text(rolAdi))
                    .bulunanRoldeAksiyonlarTikla()
                    .aksiyonListesindeAdGirilir(aksiyonAdi);
        }
    }

    @Test(description = "TS2082: Onay sürecinde evrak doğrulama checkinin kaldırılması", enabled = true)
    public void TS2082() {
        User parafci = user1;
        User imzaci = user5;

        String konu = "TS2078-" + getDateTime();
        System.out.println("Konu: " + konu);
        String kurum = "Cumhurbaşkanlığı";

        login(parafci);

        pages.newPages.EvrakOlusturPage page = new pages.newPages.EvrakOlusturPage();
        page.openPage()
                .bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum, parafci, imzaci);
        page.editorTab().openTab()
                .getEditor().type("Editör tekst");

        page.dogrulamaTab().openTab()
                .dogrulanabilirSeciliKontrolu(true)
                .evrakPageButtons()
                .evrakParafla().islemMesaji().basariliOlmali();

        EvrakDetayiPage evrakDetayiPage = new ParafladiklarimPage().openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarılacak")
                .islemZamaniKontrolu(empty);
        evrakDetayiPage.closePage();


        login(imzaci);

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        evrakDetayiPage = imzaBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Aktarılacak")
                .dogrulanabilirSec(false);
        evrakDetayiPage.pageButtons()
                .evrakKaydetMesajKontrollu()
                .islemMesaji().basariliOlmali();
        evrakDetayiPage.closePage();
        imzaBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala().islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage();
        imzaladiklarimPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster()
                .dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Geri Alınacak")
                .islemZamaniKontrolu(empty)
                .aktarilmaDurumuVeIslemZamaniKontorlu("Geri alındı", not(empty), 10, 120);
        /*evrakDetayiPage.closePage();

        //125 saniye beklenir
        step("2 dk beklenir", "121 saniye");
        Selenide.sleep(121000);
        imzaladiklarimPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .icerikGoster();
        evrakDetayiPage.dogrulamaTab().openTab()
                .aktarilmaDurumuKontrolu("Geri alındı")
                .islemZamaniKontrolu(not(empty));*/
    }
}
