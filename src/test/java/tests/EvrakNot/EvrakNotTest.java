package tests.EvrakNot;

import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.altMenuPages.EvrakDetayiPage;
import pages.newPages.EvrakOlusturPage;
import pages.newPages.KararYazisiOlusturPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstYazi;
import pages.pageComponents.tabs.EditorTab;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 28.01.2018
 * Açıklama:
 */
public class EvrakNotTest extends BaseTest {
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "Optiim Birim", "Altyapı ve Sistem Yönetim Uzmanı");
    User ztekin2 = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Genel Müdür");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1");

    /*User user1 = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Antalya İl Müdürü");
    User user2 = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "Genel Müdür");
    */

    int maxLength = 400;
    String kurumLogo = "tccbLogo.png";
    String konu;
    String kaldiralacakKlasor = "Diğer";
    String[] notTipleri = {"Seçiniz", "Genel", "Kişisel"};

    String notTipi1 = "Genel", aciklama1 = "Açıklama1";
    String notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
    String notTipi3 = "Genel", aciklama3 = "Açıklama3";
    String notTipi4 = "Kişisel", aciklama4 = "Açıklama4";
    String notTipi5 = "Genel", aciklama5 = "Açıklama5";
    String date1 = "", date2 = "", date3 = "", date4 = "", date5 = "";
    String time1 = "", time2 = "", time3 = "", time4 = "", time5 = "";

    private String createTextWith(int length) {
        String text = "";
        for (int i = 0; i < length - (int) (Math.log10(length) + 1); i++) {
            text += ".";
        }
        text += String.valueOf(length);
        /*String text = "";
        for (int i = 0; i < length - 1; i++) {
            text += ".";
        }
        text += "!";*/
        return text;
    }

    @Test(enabled = true, description = "TS2091: Not Oluşturma - Evrak Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2091() {

        String notTipi1 = "Genel", aciklama1 = createTextWith(maxLength);
        String notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
        String notTipi3 = "Genel", aciklama3 = "Açıklama3";
        String notTipi4 = "Kişisel", aciklama4 = "Açıklama4";

        login(optiim);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        EditorTab.EvrakNot editorEvrakNot = page.editorTab().openTab().getEvrakNot();

        editorEvrakNot.notOlustur(optiim.getFullname(), notTipi1, aciklama1, maxLength, notTipleri);
        editorEvrakNot.notOlustur(optiim.getFullname(), notTipi2, aciklama2);

        UstYazi.EvrakNot ustYaziEvrakNot = page.bilgileriTab().openTab().getUstYazi().ustYaziGoruntule().evrakNotlariTabiAc();

        ustYaziEvrakNot.notuBul(text(optiim.getFullname()), text(notTipi1), text(aciklama1))
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(notTipi2), text(aciklama2))
                .notOlustur(optiim.getFullname(), notTipi3, aciklama3, 500, notTipleri)
                .notOlustur(optiim.getFullname(), notTipi4, aciklama4);

        page.editorTab().openTab();
        editorEvrakNot
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama1)).postitStyle()
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama2)).postitStyle()
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama3)).postitStyle()
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama4)).postitStyle();

        logout();
    }

    @Test(enabled = true, description = "TS2092: Not Oluşturma - Olur/Takrir Yazısı Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2092() {
        String notTipi1 = "Genel", aciklama1 = "Açıklama1", notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
        String noteText, date1, time1, date2, time2;

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage();

        login(optiim);
        page.openPage().editorTab().openTab();

        EditorTab.EvrakNot evrakNot = page.editorTab().getEvrakNot();
        evrakNot.notOlustur(optiim.getFullname(), notTipi1, aciklama1, maxLength, notTipleri).postitStyle();
        noteText = evrakNot.getNote().text();
        date1 = getDateFromText(noteText);
        time1 = getTimeFromText(noteText);

        UstYazi.EvrakNot ustYaziEvrakNot = page.bilgileriTab().openTab().getUstYazi().ustYaziGoruntule().evrakNotlariTabiAc();
        ustYaziEvrakNot
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1))
                .notOlustur(optiim.getFullname(), notTipi2, aciklama2, 500, notTipleri);

        noteText = ustYaziEvrakNot.getNote().text();
        date2 = getDateFromText(noteText);
        time2 = getTimeFromText(noteText);

        page.editorTab().openTab();
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle()
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama2), text(date2), text(time2)).postitStyle();

        logout();
    }

    @Test(enabled = true, description = "TS2093: Not Oluşturma - Karar Yazısı Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2093() {

        String notTipi1 = "Genel", aciklama1 = "Açıklama1", notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
        SelenideElement note;
        String noteText, date1, time1, date2, time2;

        KararYazisiOlusturPage page = new KararYazisiOlusturPage();

        login(optiim);
        page.openPage().editorTab().openTab();

        EditorTab.EvrakNot evrakNot = page.editorTab().getEvrakNot();
        evrakNot.notOlustur(optiim.getFullname(), notTipi1, aciklama1, 400, notTipleri).postitStyle();
        noteText = evrakNot.getNote().text();
        date1 = getDateFromText(noteText);
        time1 = getTimeFromText(noteText);

        UstYazi.EvrakNot ustYaziEvrakNot = page.bilgileriTab().openTab().getUstYazi().ustYaziGoruntule().evrakNotlariTabiAc();
        ustYaziEvrakNot
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1))
                .notOlustur(optiim.getFullname(), notTipi2, aciklama2, 500, notTipleri);

        noteText = ustYaziEvrakNot.getNote().text();
        date2 = getDateFromText(noteText);
        time2 = getTimeFromText(noteText);

        page.editorTab().openTab();
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle()
                .notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama2), text(date2), text(time2)).postitStyle();

        logout();
    }

    @Test(enabled = true, description = "TS2155: Not İzleme - Evrak Notunun Taslak evraklarda izlenmesi")
//, dependsOnMethods = {"TS2091"})
    public void TS2155() {
        /*User user1 = optiim;
        User user2 = ztekin;*/

        konu = "TS2155_" + getSysDate();
        System.out.println("Konu: " + konu);

        String noteText;

        login(user1);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.editorTab().openTab().getEditor().type("Editor Text");

        EditorTab.EvrakNot editorEvrakNot = page.editorTab().getEvrakNot();
        editorEvrakNot.notOlustur(user1.getFullname(), notTipi1, aciklama1, notTipleri).postitStyle();
        noteText = editorEvrakNot.getNote().text();
        date1 = getDateFromText(noteText);
        time1 = getTimeFromText(noteText);

        editorEvrakNot.notOlustur(user1.getFullname(), notTipi2, aciklama2).postitStyle();
        noteText = editorEvrakNot.getNote().text();
        date2 = getDateFromText(noteText);
        time2 = getTimeFromText(noteText);


        UstYazi.EvrakNot ustYaziEvrakNot = page.bilgileriTab().openTab().getUstYazi().ustYaziGoruntule().evrakNotlariTabiAc();
        ustYaziEvrakNot
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1))
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi2), text(aciklama2), text(date2), text(time2));

        ustYaziEvrakNot.notOlustur(user1.getFullname(), notTipi3, aciklama3);
        noteText = ustYaziEvrakNot.getNote().text();
        date3 = getDateFromText(noteText);
        time3 = getTimeFromText(noteText);

        ustYaziEvrakNot.notOlustur(user1.getFullname(), notTipi4, aciklama4);
        noteText = ustYaziEvrakNot.getNote().text();
        date4 = getDateFromText(noteText);
        time4 = getTimeFromText(noteText);

        page.editorTab().openTab();
        editorEvrakNot
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle()
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama2), text(date2), text(time2)).postitStyle()
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama3), text(date3), text(time3)).postitStyle()
                .notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama4), text(date4), text(time4)).postitStyle();

        page.bilgileriTab().openTab()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec(kaldiralacakKlasor)
                .ivedilikSec(Ivedilik.NORMAL)
                /*.bilgiSecimTipiSec(BilgiSecimTipi.KURUM)
                .bilgiSec("Başbakanlık")*/
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(user2.getBirimAdi())
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user1, OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(user2, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla()
                .onayAkisiSecilenKullaniciKontrolEt(user1, OnayKullaniciTipi.PARAFLAMA)
                .onayAkisiSecilenKullaniciKontrolEt(user2, OnayKullaniciTipi.IMZALAMA);
        page.pageButtons().evrakKaydet();
        page.closePage(true);

        //------------------------------------
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage().openPage();
        SearchTable searchTable = taslakEvraklarPage.searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();

        EvrakOnizleme.EvrakNotlari evrakOnizlemeEvrakNotlari = new EvrakOnizleme().new EvrakNotlari();
        evrakOnizlemeEvrakNotlari
                .notuKontrolEt(user1, notTipi4, aciklama4, date4, time4, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi2, aciklama2, date2, time2, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi1, aciklama1, date1, time1, kurumLogo)
                .evrakNotlariDialoguKapat();

        UstYazi.EvrakNot ustYazi = new UstYazi().evrakNotlariTabiAc();
        UstYazi.EvrakNot n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi2), text(aciklama2), text(date2), text(time2));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi4), text(aciklama4), text(date4), text(time4));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);

        searchTable.foundRow().icerikGosterTikla();
        EditorTab.EvrakNot evrakNot = new EditorTab().getEvrakNot();
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama2), text(date2), text(time2)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama3), text(date3), text(time3)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama4), text(date4), text(time4)).postitStyle().getNoteSilButton().shouldBe(visible);

        //taslakEvraklarPage.evrakPageButtons().evrakParafla().islemMesaji().basariliOlmali();
        logout();
    }

    @Description("TS2160: Not İzleme - Evrak Notunun Paraf bekleneler, Parafladıklarım, İmza Bekleyenler ve İmzaladıklarım ekranlarında izlenmesi")
    @Test(dependsOnMethods = {"TS2155"}, enabled = true, description = "TS2160: Not İzleme - Evrak Notunun Paraf bekleneler, Parafladıklarım, İmza Bekleyenler ve İmzaladıklarım ekranlarında izlenmesi")
    public void TS2160() {
        /*User user1 = optiim;
        User user2 = ztekin;*/

        EditorTab.EvrakNot evrakNot;// = new EditorTab().getEvrakNot();
        System.out.println("Konu: " + konu);
        login(user1);
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage().openPage();
        taslakEvraklarPage.searchTable().findRows(text(konu)).getFoundRow().click();
        taslakEvraklarPage.evrakPageButtons().evrakParafla().islemMesaji().basariliOlmali();

        SearchTable searchTable = new ParafladiklarimPage().openPage().searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();
        new EvrakOnizleme().getEvrakNotlari()
                .notuKontrolEt(user1, notTipi4, aciklama4, date4, time4, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi2, aciklama2, date2, time2, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi1, aciklama1, date1, time1, kurumLogo)
                .evrakNotlariDialoguKapat();

        UstYazi.EvrakNot ustYazi = new UstYazi().evrakNotlariTabiAc();
        UstYazi.EvrakNot n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi2), text(aciklama2), text(date2), text(time2));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi4), text(aciklama4), text(date4), text(time4));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);


        //10. Bilgisi bulunan Taslak evrakın "İçerik Göster" butonun basılır.	Editör tabının açık geldiği, 4 adet notun Post-it görünümde (Notu sil butonu, Notu hazırlayan (kişi adı,soyadı ve görev bilgisi), İşlem tarihi, İşlem saati, Not açıklaması) geldiği görülür.
        // Editör tabının gelmiyor. Evrak Not tabı var.
        searchTable.foundRow().icerikGosterTikla();
        ustYazi = new pages.newPages.EvrakDetayiPage().evrakNotlariTabiAc().evrakNotlari();
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi2), text(aciklama2), text(date2), text(time2));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi4), text(aciklama4), text(date4), text(time4));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        /*evrakNot = new EditorTab().getEvrakNot();
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama2), text(date2), text(time2)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama3), text(date3), text(time3)).postitStyle().getNoteSilButton().shouldBe(visible);
        evrakNot.notuBul(text(optiim.getFullname()), text(optiim.getGorev()), text(aciklama4), text(date4), text(time4)).postitStyle().getNoteSilButton().shouldBe(visible);*/
        logout();
        ////////////////////////////////////////////////////////////

        login(user2);
        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        searchTable = imzaBekleyenlerPage.searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();

        new EvrakOnizleme().new EvrakNotlari().evrakNotlariTabiAc()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi1, aciklama1, date1, time1, kurumLogo)
                .evrakNotlariDialoguKapat();

        ustYazi = new UstYazi().evrakNotlariTabiAc();
        ustYazi.getYeniNotEkleButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);

        searchTable.foundRow().icerikGosterTikla();
        evrakNot = new EditorTab().getEvrakNot();
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama1), text(date1), text(time1)).postitStyle().getNoteSilButton().shouldNotBe(visible);
        evrakNot.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(aciklama3), text(date3), text(time3)).postitStyle().getNoteSilButton().shouldNotBe(visible);
        evrakNot.notOlustur(user2.getName(), notTipi5, aciklama5);
        date5 = getDateFromText(evrakNot.getNote().text());
        time5 = getTimeFromText(evrakNot.getNote().text());

        imzaBekleyenlerPage.evrakPageButtons().evrakKaydet().islemMesaji().basariliOlmali();
        imzaBekleyenlerPage.evrakPageButtons().evrakIadeEt("İade notu").islemMesaji().basariliOlmali();
        logout();

        /////////////////////////////////////////////////

        login(user1);
        ParafBekleyenlerPage parafBekleyenlerPage = new ParafBekleyenlerPage().openPage();
        searchTable = parafBekleyenlerPage.searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();
        new EvrakOnizleme().new EvrakNotlari()
                .notuKontrolEt(user2, notTipi5, aciklama5, date5, time5, kurumLogo)
                .notSilButonDisabled()
                .sonrakiNot()
                /*.notuKontrolEt(user1, notTipi4, aciklama4, date4, time4, kurumLogo)
                .notSilButonEnabled()
                .sonrakiNot()*/
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .notSilButonEnabled()
                .sonrakiNot()
                /*.notuKontrolEt(user1, notTipi2, aciklama2, date2, time2, kurumLogo)
                .notSilButonEnabled()
                .sonrakiNot()*/
                .notuKontrolEt(user1, notTipi1, aciklama1, date1, time1, kurumLogo)
                .notSilButonEnabled()
                .evrakNotlariDialoguKapat();

        ustYazi = new UstYazi().evrakNotlariTabiAc();
        ustYazi.getYeniNotEkleButton().shouldBe(visible);
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        /*n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi2), text(aciklama2), text(date2), text(time2));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);*/
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);
        /*n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi4), text(aciklama4), text(date4), text(time4));
        n.getNoteGuncelleButton().shouldBe(visible);
        n.getNoteSilButton().shouldBe(visible);*/
        n = ustYazi.notuBul(text(user2.getFullname()), text(user2.getGorev()), text(notTipi5), text(aciklama5), text(date5), text(time5));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);

        ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi1), text(aciklama1), text(date1), text(time1))
                .notuSil();
        ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3))
                .notuGuncelle(aciklama3 + " guncellenen");
        aciklama3 = aciklama3 + " guncellenen";
        ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        parafBekleyenlerPage.evrakPageButtons().evrakParafla().islemMesaji().basariliOlmali();
        logout();

        /////////////////////////////////////////////////

        login(user2);
        searchTable = imzaBekleyenlerPage.openPage().searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();

        new EvrakOnizleme().new EvrakNotlari()
                .notuKontrolEt(user2, notTipi5, aciklama5, date5, time5, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .evrakNotlariDialoguKapat();
        imzaBekleyenlerPage.evrakPageButtons().evrakImzala().islemMesaji().basariliOlmali();
    }

    @Description("TS2162: Not İzleme - Evrak Notunun Postalanacak Evraklar ve Postananlar ekranlarında izlenmesi")
    @Test(enabled = true, dependsOnMethods = {"TS2160"}, description = "TS2162: Not İzleme - Evrak Notunun Postalanacak Evraklar ve Postananlar ekranlarında izlenmesi")
    public void TS2162() throws Exception {
        /*User user1 = optiim;
        User user2 = ztekin;*/

        //String konu = "TS2162_" + getSysDate();
        System.out.println("Konu: " + konu);

        /*login(user1);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab().openTab()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec(kaldiralacakKlasor)
                *//*.ivedilikSec(Ivedilik.NORMAL)
                .bilgiSecimTipiSec(BilgiSecimTipi.KURUM)
                .bilgiSec("Başbakanlık")*//*
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(user1.getBirimAdi())
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user1, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla();

        TextEditor textEditor = page.editorTab().openTab().getEditor();
        textEditor.type("TS2162");
//                .editorEvrakGeregiSec("YAZILIM GELİ");
        *//*textEditor.editorEvrakGeregiSec(user.getBirimAdi());
        page.pageButtons().evrakKaydet();
        page.closePage(false);
        evrakOlusturVeImzala(user1, konu, notes);*//*
        EditorTab.EvrakNot evrakNot = new EditorTab().getEvrakNot();*/


        //clearCookies();
        login(user5);
        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage().openPage();
        SearchTable searchTable = postalanacakEvraklarPage.searchTable();
        searchTable.findRows(text(konu)).getFoundRow().click();

        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();
        evrakOnizleme.new EvrakNotlari()
                .notuKontrolEt(user2, notTipi5, aciklama5, date5, time5, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .evrakNotlariDialoguKapat();

        UstYazi.EvrakNot ustYazi = new UstYazi().evrakNotlariTabiAc();
        ustYazi.getYeniNotEkleButton().shouldBe(visible);
        UstYazi.EvrakNot n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);
        n = ustYazi.notuBul(text(user2.getFullname()), text(user2.getGorev()), text(notTipi5), text(aciklama5), text(date5), text(time5));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);

        evrakOnizleme.evrakPostala()
                .postalanacakYerlerdeAra(text(user1.getBirimAdi()))
                .gidisSekliSec("E-Posta")
                .epostaGir("test@test.com")
                .aciklamaGir(konu)
                .postala()
                .dogrulamaUyari("Belge elektronik imzalı değil! Evrakı postalamak üzeresiniz. Devam etmek istiyor musunuz?", true)
                .islemMesaji().basariliOlmali();

        /*postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama(konu);
        postala();
        postalanacakEvraklarPage.islemMesaji().basariliOlmali();*/

        PostalananlarPage postalananlarPage = new PostalananlarPage().openPage();
        postalananlarPage.searchTable().findRows(text(konu)).getFoundRow().click();
        new EvrakOnizleme().new EvrakNotlari()
                .notuKontrolEt(user2, notTipi5, aciklama5, date5, time5, kurumLogo)
                .sonrakiNot()
                .notuKontrolEt(user1, notTipi3, aciklama3, date3, time3, kurumLogo)
                .evrakNotlariDialoguKapat();

        ustYazi = new UstYazi().evrakNotlariTabiAc();
        n = ustYazi.notuBul(text(user1.getFullname()), text(user1.getGorev()), text(notTipi3), text(aciklama3), text(date3), text(time3));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);
        n = ustYazi.notuBul(text(user2.getFullname()), text(user2.getGorev()), text(notTipi5), text(aciklama5), text(date5), text(time5));
        guncelleButtonBulmamamli(n);
        silButtonBulmamamli(n);
    }

    @Step("Güncelle butonu bulunmadığı görülür")
    EvrakNotTest guncelleButtonBulmamamli(UstYazi.EvrakNot not) {
        not.getNoteGuncelleButton().shouldNotBe(visible);
        return this;
    }

    @Step("Güncelle butonu bulunmadığı görülür")
    EvrakNotTest silButtonBulmamamli(UstYazi.EvrakNot not) {
        not.getNoteSilButton().shouldNotBe(visible);
        return this;
    }
}
