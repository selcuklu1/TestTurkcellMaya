package tests.OlurYazisiIslemleri;

import common.BaseTest;
import data.User;
import galen.GalenControl;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.tabs.AltTabs;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageData.SolMenuData;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;

import java.io.IOException;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 7.01.2018
 * Açıklama:
 */
public class OlurYazisiIslemleriTest extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1", "Ağ (Network) Uzmanı");
    User user3 = new User("user3", "123", "User3 TEST", "AnaBirim1", "Ağ (Network) Uzmanı");
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");
    //User optiim = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ", "BİLİŞİM HİZMETLERİ VE UYDU PAZARLAMA GENEL MÜDÜR");

    OlurYazisiOlusturPage olurYazisiOlusturPage;
    AltTabs altTabs;
    MainPage mainPage;

    String konu = "TS0577_" + getSysDate();
    String doc = "documents/Otomasyon.pdf";
    //konuKodu dokümanda görünen değeri "01-010.10-" konuKodu değiştirince sayi değiştirmeye unutma!(?dönüştürme metodu?)
    String konuKodu = "010.10";
    String konuKoduSayi = "01-010.10-";

    //Teskilat Kisi tanimlari-->birim yönetimi ekranında birimin olur metni boş olmalı
    @Test(description = "TS0577: Olur yazısı oluşturulması ve gönderilmesi", enabled = true)
    //@Link(name = "Galen", type = "html", url = "file:///Users/ilyas/WorkspaceJava/Git/BelgenetFTA/galenReports/TS0577/report.html")
    @Link(name = "Galen", type = "html", url = "galenReports/TS0577/report.html")
    public void TS0577() throws Exception {

        System.out.println("Konu: " + konu);
        login(user1);
        olurYazisiOlusturPage = new OlurYazisiOlusturPage().openPage();

        bilgileriTab();
        altTabs = olurYazisiOlusturPage.ekleriTab().openTab().altTabs();
        ekleriTab();
        ilgileriTab();
        iliskiliEvraklar();
        editorTabGalen();
        imzalaVeOnayaSun();
        hazirladiklarimMenudaGorunmeli();
        ilkImzaciImzala();
        ikinciImzaciImzala();
        geregiBirimKullanici();
        postaciKontolleri();
    }

    @Step("Bilgileri sekmesinde alanları doldur")
    private void bilgileriTab(){
        olurYazisiOlusturPage.bilgileriTab().openTab()
                .konuKoduSec(konuKodu)
                .konuDoldur(konu)
                .gizlilikDerecesiSec(GizlilikDerecesi.Normal)
                .ivedilikSec(Ivedilik.Normal)
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(optiim.getBirimAdi())
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(user1.getFullname(), OnayKullaniciTipi.PARAFLAMA)
                .anlikOnayAkisKullanicilariTemizle()
                .anlikOnayAkisKullaniciVeTipiSec(user2, OnayKullaniciTipi.IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(user3, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla();
    }

    @Step("Ekleri sekmesinde ekleme ve kontrolleri")
    private void ekleriTab(){
        altTabs.getFiziksetEkEkleTab().shouldBe(visible);
        altTabs.getSistemdeKayitliEvrakEkleTab().shouldBe(visible);
        altTabs.getWebAdresiniEkleTab().shouldBe(visible);
        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ekMetniDoldur("Ekleri Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.ekleriTab().getEkListesiTablosu().findRows(text("Ekleri Tab "+ konu)).shouldHaveSize(1);
    }

    @Step("İlgileri sekmesinde ekleme ve kontrolleri")
    private void ilgileriTab(){
        altTabs = olurYazisiOlusturPage.ilgileriTab().openTab().altTabs();
        altTabs.getSistemdeKayitliEvrakEkleTab();
        altTabs.dosyaEkleTabiAc()
                .dosyaEkle(doc)
                .ilgiMetniDoldur("İlgileri Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.ilgileriTab().getIlgliliListesiTablosu().findRows(text("İlgileri Tab "+ konu)).shouldHaveSize(1);

        altTabs.metinEkleTabiAc().ilgiMetniDoldur("Metni Tab " + konu).ekleButonaTikla();
        olurYazisiOlusturPage.ilgileriTab().getIlgliliListesiTablosu().findRows(text("Metni Tab " + konu)).shouldHaveSize(1);
    }

    @Step("İlişkili Evraklar sekmesinde ekleme ve kontrolleri")
    private void iliskiliEvraklar() {
        //region İlişkili Evraklar. Sistemde kayıtlı evrak ekle, tercüme evrak ekle
        altTabs = olurYazisiOlusturPage.iliskiliEvraklarTab().openTab().altTabs();
        altTabs.getDosyaEkleTab().shouldBe(visible);
        altTabs.sistemdeKayitliEvrakEkleTabiAc().evrakAraDoldur("a").dokumanAraTikla().islemMesaji().basariliOlmali();
        String docSati = altTabs.getSistemdeKayitliEvrakListesi().findRows().shouldHaveSize(1).useFirstFoundRow().getColumnValue( "Sayı").text();
        altTabs.getSistemdeKayitliEvrakListesi().foundRow().dokumanEkleTikla();
        olurYazisiOlusturPage.iliskiliEvraklarTab().getEkListesiTablosu().findRows(text(docSati)).shouldHaveSize(1);

        altTabs.tercumeEkleTabiAc()
                .dosyaEkle(doc)
                .ilisikMetniDoldur("Tercüme Tab "+ konu)
                .ekleButonaTikla();
        olurYazisiOlusturPage.iliskiliEvraklarTab().getEkListesiTablosu().findRows(text("Tercüme Tab "+ konu)).shouldHaveSize(1);
    }

    @Step("Editör tabında alanların layout kontorlü")
    private void editorTabGalen() throws IOException{
        //Editör Tab Galen kontroller
        olurYazisiOlusturPage.editorTab().openTab();
        sleep(3000);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("birim", user1.getBirimAdi());
        params.put("sayi", konuKoduSayi);
        params.put("konu", konu);
        params.put("ilgi_a", "İlgileri Tab "+ konu);
        params.put("ilgi_b", "Metni Tab " + konu);
        params.put("ilgi_b", "Metni Tab " + konu);
        params.put("imzaci1Isim", user2.getFullname());
        params.put("imzaci1Gorev", user2.getGorev());
        params.put("imzaci2Isim", user3.getFullname());
        params.put("imzaci2Gorev", user3.getGorev());
        params.put("ek", "Ekleri Tab "+ konu);
        GalenControl galen = new GalenControl();
        galen.setTextValuesToGalenSpec("TS0577", params);
        //galen.galenGenerateDump("TS0577");
        galen.galenLayoutControl("TS0577");

        olurYazisiOlusturPage.editorTab().getEditor().type("Editör tekst");
    }

    @Step("İmzala ve Onaya sun")
    private void imzalaVeOnayaSun(){
        //imzala
        olurYazisiOlusturPage.pageButtons().evrakKaydetVeOnayaSunTikla();
        olurYazisiOlusturPage.getPage().shouldHave(text("1. İmzalama " + user2.getFullname()));
        olurYazisiOlusturPage.getPage().shouldHave(text("2. İmzalama " + user3.getFullname()));
        olurYazisiOlusturPage.onayIslemAciklamaDoldur("Onay işlem açıklama")
                .pageButtons().gonderTikla()
                .confirmDialog().confirmEvetTikla();
        olurYazisiOlusturPage.islemMesaji().basariliOlmali();
    }

    @Step("Hazırladıklarım sayfada gideceği yer kontrolü")
    private void hazirladiklarimMenudaGorunmeli(){
        //21	Sol menü > İşlem yaptıklarım > Hazırladıklarım menüsünü aç	Olur yazısının gideceği yer doğru olarak listelendiği görülür.
        mainPage = olurYazisiOlusturPage.solMenu(SolMenuData.IslemYaptiklarim.Hazirladiklarim);
        mainPage.searchTable().findRows(text(konu), text("Gideceği Yer: "+ optiim.getBirimAdi())).shouldHaveSize(1);
    }

    @Step("İlk imzaci ile imzala ve kontrol et")
    private void ilkImzaciImzala(){
        login(user2);
        mainPage.solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler)
                .searchTable()
                .findRows(text(konu))
                .shouldHaveSize(1).getFoundRow().click();
        mainPage.evrakImzala().islemMesaji().basariliOlmali();
        mainPage.solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim).searchTable().findRows(text(konu));
    }

    @Step("İkinci imzaci ile imzala ve kontrol et")
    private void ikinciImzaciImzala(){
        login(user3);
        mainPage.solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler)
                .searchTable().findRows(text(konu)).shouldHaveSize(1).getFoundRow().click();
        mainPage.evrakPageButtons().evrakImzala().islemMesaji().basariliOlmali();
        mainPage.solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim).searchTable().findRows(text(konu)).shouldHaveSize(1);
    }

    @Step("Gereği alanında seçilen birimde Teslim Alınmayı Bekleyenler sayfada listenmeli")
    private void geregiBirimKullanici(){
        //"Gereği alanında seçilken birimden bir kullanıcı ile sisteme login ol
        //Sol menü > birim evrakları > Teslim alınmayı bekleyenler listesini aç"
        login(optiim);
        mainPage.solMenu(SolMenuData.BirimEvraklari.TeslimAlinmayiBekleyenler)
                .searchTable().findRows(text(konu)).shouldHaveSize(1);
    }

    @Step("Evrakın hazırlandığı birimin postacıda listelenmediği görülür")
    private void postaciKontolleri(){
        //"Evrakın hazırlandığı birimin postacı biriminden bir kullanıcı ile login ol
        //Sol menü > Birim evrakları > Postalanacaklar/Postalananlar evraklar menüsünü tıkla"	Olur yazısının !!!listelenmediği!!! görülür.
        login(user1);
        mainPage.solMenu(SolMenuData.BirimEvraklari.PostalanacakEvraklar)
                .searchTable().searchInAllPages(false).findRows(text(konu)).shouldHaveSize(0);
        mainPage.solMenu(SolMenuData.BirimEvraklari.Postalananlar)
                .searchTable().searchInAllPages(false).findRows(text(konu)).shouldHaveSize(0);
    }


    OlurYazisiOlusturPage olurYazisiOlusturPage2;
    BilgilerTab bilgilerTab;

    @Test(description = "TS1488: Olur yazısında alan kontrolleri", enabled = false)
    public void TS1488() throws Exception {
        login(user1);
        olurYazisiOlusturPage2 = new OlurYazisiOlusturPage().openPage();
        bilgilerTab = olurYazisiOlusturPage2.bilgileriTab();

        step2();
        step3();

    }

    @Step("Gereği alanından içinde kurum dışı olan bir dağıtım planı seç")
    public void step2(){
        bilgilerTab.geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI);
        boolean empty = bilgilerTab.getGeregiCombolov().type("TS1488").isEmpty();
        Assert.assertTrue(empty, "Dağıtım planının gelmediği görülür.");
        bilgilerTab.getGeregiCombolov().closeTreePanel();
    }

    @Step("Editör ekranını boşken İmzala")
    public void step3(){
        bilgilerTab.konuKoduSec("010.01")
                .konuDoldur("TS1488")
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user1, OnayKullaniciTipi.IMZALAMA)
                .kullanButonaTikla();
        olurYazisiOlusturPage2.pageButtons().imzalaButonaTikla()
                .islemMesaji().dikkatOlmali("Yazı içeriği boş olamaz");
    }
}
