package tests.DagitimPlaniYonetim;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import common.ReusableSteps;
import data.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.PDFOnizleme;
import pages.pageData.alanlar.BilgiSecimTipi;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.GelenEvraklarPage;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.solMenuPages.ImzaladiklarimPage;
import pages.solMenuPages.TeslimAlinmayiBekleyenlerPage;
import pages.ustMenuPages.DagitimPlaniYonetimiPage;
import pages.ustMenuPages.GidenEvrakKayitPage;
import pages.ustMenuPages.TuzelKisiYonetimiPage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static pages.pageData.alanlar.DagitimElemanlariTipi.BIRIM;
import static pages.pageData.alanlar.DagitimElemanlariTipi.KULLANICI;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.02.2018
 * Açıklama:
 */
@Feature("Dağıtım Planı Yönetimi")
@Test(suiteName = "SuitName")
public class DagitimPlaniYonetimiTest extends BaseTest {

    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ/YGD", "Genel Müdür");//, "Uzman Test Mühendis");
    User ztekin1 = new User("ztekin", "123", "Zübeyde TEKİN", "AD MÜDÜRLÜĞÜ/YGD");//, "Uzman Test Mühendis");
    User yakyol = new User("yakyol", "123", "Yasemin Çakıl AKYOL", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ/YGD");//, "Uzman Test Mühendis");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    User user4 = new User("user4", "123", "User4 TEST", "AnaBirim2");//, "Uzman Test Mühendis");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    //User mbozdemir = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "GENEL MÜDÜRLÜK MAKAMI/GENMD");//, "Uzman Test Mühendis");
    User mbozdemir = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ/YGD", "Antalya İl Müdürü");//, "Uzman Test Mühendis");
    DagitimPlaniYonetimiPage page;
    EvrakOlusturPage evrakOlusturPage;
    String yeniPlanAdi1280;
    String yeniPlanAdi2270;
    Map<String, String> dagitimPlanElemanlari;
    ArrayList<String[]> dagitimElemenlariTS2270;
    String[] evraktaGorunecekHitap;

    @Test(description = "TS1280: Yeni Kayıt İşlemi tüm Dağıtım Tipi Elemanları ile", enabled = true, priority = 2)
    public void TS1280a() {
        User user = user1;
        login(user);

        yeniPlanAdi1280 = "TS1280a_" + getSysDate();
        System.out.println("Dağınım Planı: " + yeniPlanAdi1280);

        dagitimPlanElemanlari = new LinkedHashMap<String, String>();
        dagitimPlanElemanlari.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari.put("Kurum", "Cumhurbaşkanlığı");
        dagitimPlanElemanlari.put("Gerçek Kişi", "Zübeyde TEKİN");
        dagitimPlanElemanlari.put("Tüzel Kişi", "Türksat Optiim");

        page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni()
                .adiGir(yeniPlanAdi1280)
                .aciklamaGir("TS1280 açıklama")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true);

        dagitimPlanElemanlari.forEach((k, v) -> page.dagitimElemanlariEkle(k, v));
        page.kaydet().islemMesaji().basariliOlmali();
    }

    @Test(description = "TS1280: Yeni Kayıt İşlemi tek Dağıtım Tipi Elemanı ile", enabled = true, priority = 1)
    public void TS1280b() {
        User user = user1;
        login(user);
        String planAdi = "TS1280b_";
        System.out.println("Dağınım Planı: " + planAdi);

        //TS1280 tanımlanıyor
        Map<String, String> dagitimPlanElemanlari = new LinkedHashMap<>();
        dagitimPlanElemanlari.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari.put("Kurum", "Cumhurbaşkanlığı");
        dagitimPlanElemanlari.put("Gerçek Kişi", "Zübeyde TEKİN");
        dagitimPlanElemanlari.put("Tüzel Kişi", "Türksat Optiim");

        page = new DagitimPlaniYonetimiPage().openPage();
        dagitimPlanElemanlari.forEach((k, v) -> page.dagitimPlaniOlustur(planAdi + getSysDate(), k, user.getBirimAdi(), true, k, v));
    }

    @Test(description = "TS1476: Adı Alanının Güncellenmesi", enabled = true)
    public void TS1476() {
        String ad = "TS1476";
        String aciklama = "TS1476 Açıklama";

        User user = optiim;
        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();
        page.sorgulamadaDurumSec("Tümü")
                .ara()
                .sorgulamaDataTable.findRows().shouldHave(sizeGreaterThan(0));
        page.sorgulamadaDurumSec("Sadece Aktifler")
                .sorgulamadaAdGir(ad)
                .ara()
                .sorgulamaDataTable.findRows(text(ad)).shouldHave(sizeGreaterThan(0));
        page.guncelle();

        /*String oldAd = page.getAdi().getValue();
        String oldAciklama = page.getAciklama().text();
        String newAd = oldAd.equals(ad) ? ad + "_guncellenen": ad;
        String newAciklama = oldAciklama.equals(aciklama) ? aciklama + "_guncellenen": aciklama;*/
        String newAd = ad;
        String newAciklama = aciklama;
        //page.adiGir(newAd).aciklamaGir(newAciklama);

        String dagitimTipi = "Kullanıcı";
        String dagitimYeri = "Optiim TEST";
        String yeniDagitimTipi = dagitimTipi;
        String yeniDagitimYeri = dagitimYeri;
        if (page.dagitimPlaniDataTable.findRows(text(dagitimTipi)).findRows(text(dagitimYeri)).getFoundRows().size() > 0) {
            yeniDagitimTipi = "Birim";
            yeniDagitimYeri = "Optiim Birim";
        }
        //Eski dağıtımları silinir
        while (page.dagitimPlaniDataTable.findRows().getFoundRows().size() != 0) {
            page.dagitimPlaniDataTable.findRows().getFoundRow().$(page.deleteButtonLocator).click();
        }

//        Assert.assertEquals(page.getAdi().getValue(), newAd, "Dağıtım Yeri silindikten sonra Adı alanı güncelleme kayboluyor");
//        Assert.assertEquals(page.getAciklama().text(), newAciklama, "Dağıtım Yeri silindikten sonra Açıklama alanı güncelleme kayboluyor");

        //Yeni dağıtım eklenir
        page.dagitimElemanlariEkle(yeniDagitimTipi, yeniDagitimYeri);
        page.kaydet().islemMesaji().basariliOlmali();

        evrakOlusturSayfadaAktifKontrolu(newAd);
        gidenEvrakSayfadaAktifKontrolu(newAd);
    }

    @Test(description = "TS1478: Kopyalama", enabled = true)
    public void TS1478() {
        String adi = "TS1478";
        String aciklama = "Dağıtım Planı Kopyalama";
        String kullanildigiBirim = "Optiim Birim";
        String dagitimElemanlariTipi = "Birim";
        String dagitimElemanlari = "Optiim Birim";
        boolean altBirimlerGorsun = true;
        String newAd = adi + "-KOPYA-" + getSysDate();

        User user = optiim;
        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();
        page.sorgulamadaAdGir(adi)
                .ara()
                .sorgulamaDataTable.searchByColumnName("Dağıtım Planı Ad").findRows(exactText(adi)).shouldHaveSize(1);//.getFoundRow().$(page.copyButtonLocator).click();
        page.kopyala();
        checkFields(adi, aciklama, kullanildigiBirim, altBirimlerGorsun, dagitimElemanlariTipi, dagitimElemanlari);
        page.adiGir(newAd)
                .kaydet().islemMesaji().basariliOlmali();

        evrakOlusturSayfadaAktifKontrolu(newAd);
        gidenEvrakSayfadaAktifKontrolu(newAd);
    }

    @Test(description = "TS1279: Pasif / Aktif Yapma", enabled = true)
    public void TS1279() {
        String adi = "TS1279";
        boolean aktif = false;

        User user = optiim;
        login(user);

        page = new DagitimPlaniYonetimiPage().openPage();

        page.sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec("Tümü")
                .ara()
                .sorgulamaDataTable.findRows(text(adi)).shouldHaveSize(1);

        aktif = page.aktifMi();

        if (aktif) {
            aktifPasifYap(adi, false);
            evrakOlusturSayfadaPasifKontrolu(adi);

            page.openPage();
            aktifPasifYap(adi, true);
            evrakOlusturSayfadaAktifKontrolu(adi);
        }
        else {
            aktifPasifYap(adi, true);
            evrakOlusturSayfadaAktifKontrolu(adi);

            page.openPage();
            aktifPasifYap(adi, false);
            evrakOlusturSayfadaPasifKontrolu(adi);
        }
    }

    @Test(description = "TS2260: Sorgulama ve Filtreleme", enabled = true)
    public void TS2260() {
        User user = optiim;

        String pasifDagitimPlanIsmi = "PASİF";
        String aktifDagitimPlanIsmi = "TS2260";

        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();

        step("STEP: Durum \"Sadece Pasifler\"", "Pasifler bulunmalı, aktifler bulunmamalı");
        page.sorgulamadaDurumSec("Sadece Pasifler")
                .ara()
                .sorgulamaDataTable.searchInAllPages(true)
                .findRows(text(pasifDagitimPlanIsmi)).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text(aktifDagitimPlanIsmi)).shouldHaveSize(0);

        step("STEP: Durum \"Sadece Aktifler\"", "Aktifler bulunmalı, pasifler bulunmamalı");
        page.sorgulamadaDurumSec("Sadece Aktifler")
                .ara()
                .sorgulamaDataTable.searchInAllPages(true)
                .findRows(text(aktifDagitimPlanIsmi)).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text(pasifDagitimPlanIsmi)).shouldHaveSize(0);

        step("STEP: Durum \"Tümü\"", "Aktifler ve pasifler bulunmalı");
        page.sorgulamadaDurumSec("Tümü")
                .ara()
                .sorgulamaDataTable
                .searchInAllPages(true).findRows(text(pasifDagitimPlanIsmi)).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text(aktifDagitimPlanIsmi)).shouldHaveSize(1);

        step("STEP: Durum \"Tümü\" ve Ad girilir", "Sadece adi girilen bulunmalı");
        page.sorgulamadaDurumSec("Tümü")
                .sorgulamadaAdGir(pasifDagitimPlanIsmi)
                .ara()
                .sorgulamaDataTable.searchInAllPages(true)
                .findRows(text(pasifDagitimPlanIsmi)).shouldHaveSize(1);
    }

    @Test(description = "TS2323: Yeni Dağıtım Planı Kayıt (Ekranlardan Kontrolü)", enabled = true
            , dependsOnMethods = {"TS1280a"}
    )
    public void TS2323() {
        //User user = optiim;
        User user = user1;
        login(user);

        //yeniPlanAdi1280 = "TS1280_20180205102433";
        dagitimPlanElemanlari = new LinkedHashMap<String, String>();
        dagitimPlanElemanlari.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari.put("Kurum", "Başbakanlık");
        dagitimPlanElemanlari.put("Gerçek Kişi", "ZÜBEYDE");
        dagitimPlanElemanlari.put("Tüzel Kişi", "Türksat Optiim");

        /*System.out.println("Dağınım Planı: " + yeniPlanAdi1280);
        page = new DagitimPlaniYonetimiPage().openPage();

        step("STEP: Durum \"Sadece Aktifler\"", "bulunmalı");
        page.sorgulamadaDurumSec("Sadece Aktifler")
                .sorgulamadaAdGir(yeniPlanAdi1280)
                .ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text(yeniPlanAdi1280)).shouldHaveSize(1);

        step("STEP: Durum \"Sadece Pasifler\"", "bulunmamalı");
        page.sorgulamadaDurumSec("Sadece Pasifler")
                .sorgulamadaAdGir(yeniPlanAdi1280)
                .ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text(yeniPlanAdi1280)).shouldHaveSize(0);

        step("STEP: Durum \"Tümü\"", "bulunmalı");
        page.sorgulamadaDurumSec("Tümü")
                .sorgulamadaAdGir(yeniPlanAdi1280)
                .ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text(yeniPlanAdi1280)).shouldHaveSize(1);*/

        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        evrakOlusturPage.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiSec(yeniPlanAdi1280)
                .getGeregiCombolov().getSelectedItems().last().$(".update-icon").click();
        evrakOlusturPage.dagitimHitapDuzenle().dagitimPlaniDetaySirasiKontrolu(dagitimPlanElemanlari)
                .getIptalButton().click();
        evrakOlusturPage.bilgileriTab().geregiTemizle()
                .bilgiSecimTipiSec(BilgiSecimTipi.DAGITIM_PLANLARI)
                .bilgiSec(yeniPlanAdi1280)
                .bilgiTemizle();

        evrakOlusturPage.editorTab().openTab()
                .select("Gereği", yeniPlanAdi1280)
                .clear("Gereği")
                .select("Bilgi", yeniPlanAdi1280);
        evrakOlusturPage.pageButtons().pdfOnizlemeTikla();
        Selenide.switchTo().window(1);
        Selenide.$(Selectors.byText("DAĞITIM YERLERİNE")).shouldBe(visible);
        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(0);
        evrakOlusturPage.editorTab().getEditor().type("editör teksti");

        String konu = "TS1280_" + getSysDate();
        System.out.println("Konu: " + konu);
        evrakOlusturPage.bilgileriTab().openTab()
                .bilgiTemizle()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiSec(yeniPlanAdi1280)
                .onayAkisiTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullaniciVeTipiSec(user5, OnayKullaniciTipi.IMZALAMA)
                .kullan()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .evrakPageButtons().evrakParafla().islemMesaji().basariliOlmali();

        login(user5);
        new ImzaBekleyenlerPage().openPage().searchTable().findRows(text(konu)).getFoundRow().click();
    }

    @Test(description = "TS2324: Kullanıldığı Birim Alanının Güncellenmesi", enabled = true)
    public void TS2324() {
        User u1 = ztekin;
        User u2 = ztekin1;
        User u;
        String adi = "TS2324";

        login(u1);

        page = new DagitimPlaniYonetimiPage().openPage().bulVeGuncelleTikla(adi);

        u = page.getKullanildigiBirim().getSelectedTitles().last().has(text(u1.getBirimAdi())) ? u2 : u1;

        page.kullanildigiBirimSec(u.getBirimAdi(), u.getBirimKisaAdi()).kaydet().islemMesaji().basariliOlmali();

        evrakOlusturSayfadaPasifKontrolu(adi);

        login(u);
        evrakOlusturSayfadaAktifKontrolu(adi);

    }

    @Test(description = "TS2270: Dağıtım Hitap Düzenleme", enabled = true)
    public void TS2270() {
        User user = user1;
        yeniPlanAdi2270 = "TS2270_" + getSysDate();

        String tuzelKisi = "Türksat Optiim";
        String gercekKisi = "Zübeyde TEKİN";

        String adres = "GÖLBAŞI / ANKARA";
        //String dagitimElemanlariTipi, dagitimElemanlari, evraktaGorunecekHitap, kayitliHitap;
        evraktaGorunecekHitap = new String[]{
                String.format("Sayın %s%s", user.getFullname(), "NA")
                , user.getBirimAdi() + "E"
                , "CUMHURBAŞKANLIĞINA"
                , tuzelKisi.toUpperCase() + "E"
                , String.format("Sayın %s \n %s", gercekKisi, adres)
        };

        dagitimElemenlariTS2270 = new ArrayList<>();
        //dagitimElemanlariTipi, dagitimElemanlariEkle, Ek, evraktaGorunecekHitap, kayitliHitap
        dagitimElemenlariTS2270.add(new String[]{"Kullanıcı", user.getFullname(), "NA"});
        dagitimElemenlariTS2270.add(new String[]{"Birim", user.getBirimAdi(), "E", "BirimÖzelHitap"});
        dagitimElemenlariTS2270.add(new String[]{"Kurum", "Cumhurbaşkanlığı"});
        dagitimElemenlariTS2270.add(new String[]{"Tüzel Kişi", tuzelKisi, "E", "TüzelÖzelHitap"});
        dagitimElemenlariTS2270.add(new String[]{"Gerçek Kişi", gercekKisi, adres});

        System.out.println("Dağınım Planı Adı: " + yeniPlanAdi2270);

        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni();

        for (String[] d : dagitimElemenlariTS2270) {
            switch (d[0]) {
                case "Kullanıcı":
                    page.dagitiminElemaniEkleVeEkGuncelle(d[0], d[1], d[2], evraktaGorunecekHitap[0]);
                    break;
                case "Birim":
                    page.dagitiminElemaniEkleVeOzelHitapSec(d[0], d[1], evraktaGorunecekHitap[1]);
                    break;
                case "Kurum":
                    page.dagitimElemanlariEkle(d[0], d[1]);
                    break;
                case "Tüzel Kişi":
                    page.dagitiminElemaniEkleVeOzelHitapSec(d[0], d[1], evraktaGorunecekHitap[3]);
                    break;
                case "Gerçek Kişi":
                    page.dagitiminElemaniEkleVeAdresSec(d[0], d[1], d[2], evraktaGorunecekHitap[4]);
                    break;
                default:
                    break;
            }
        }
        page.adiGir(yeniPlanAdi2270)
                .aciklamaGir("Dağıtım Planı_Dağıtım Hitap Düzenleme")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true)
                .kaydet().islemMesaji().basariliOlmali();
    }

    @Test(description = "TS2271: Hitabın oluşturulan evrak üzerinde kontrolü", enabled = true
            , dependsOnMethods = "TS2270"
    )
    public void TS2271() {
        useFirefox();
        User user = user1;
        login(user);

        /*String adres = "HitapAdres";
        String[] evraktaGorunecekHitap = new String[]{
                String.format("Sayın %s%s", user.getFullname(), "NA")
                , user.getBirimAdi() + "E"
                , "Başbakanlıka"
                , "Türksat Optiim".toUpperCase() + "E"
                , String.format("Sayın %s \n %s", "Zübeyde TEKİN", adres)
        };*/

        //String dagitimPlani = "TS2270_20180212162246";
        String dagitimPlani = yeniPlanAdi2270;
        String konu = "TS2271_" + getSysDate();
        evrakOlusturPage = new EvrakOlusturPage().openPage();
        evrakOlusturPage.bilgileriTab()
                .geregiSec(GeregiSecimTipi.DAGITIM_PLANLARI, dagitimPlani)
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullan();
        evrakOlusturPage.editorTab().openTab().getEditor().type("Some text");
        evrakOlusturPage.pageButtons().evrakImzala().islemMesaji().basariliOlmali();

        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage().openPage();
        //clickJs(imzaladiklarimPage.searchTable().findRows(text(konu)).getFoundRow());
        //imzaladiklarimPage.searchTable().findRows(text(konu)).getFoundRow().click();
        imzaladiklarimPage.searchTable().findRowAndSelect(text(konu));

        EvrakOnizleme.EvrakDetaylari evrakDetaylari = new EvrakOnizleme().new PostaDetayi().tabiAc()
                .postalananYerlerindeAra(text(yeniPlanAdi2270 + "E"))
                .yazdir();
        evrakDetaylari.ustVerileriListesindeAra(text(konu)).ustVerileriYazdir();

        new PDFOnizleme(1).checkTextInAllPages(
                exactText(evraktaGorunecekHitap[0])
                , exactText(evraktaGorunecekHitap[1])
                , exactText(evraktaGorunecekHitap[2])
                , exactText(evraktaGorunecekHitap[3])
                , exactText(evraktaGorunecekHitap[4]));
        WebDriverRunner.getWebDriver().close();

        switchTo().window(0);
    }

    @Test(description = "TS2296: Dağıtım Planı Kaydet/Güncelle Ekranı Alan Kontrolleri", enabled = true, priority = 3)
    public void TS2296() {
        User user = user1;
        login(user);

        String messageZorunluAlanlar = "Zorunlu alanları doldurunuz";
        String messageDagitimElemanlari = "Dağıtım elemanları boş olamaz";
        String messageDagitimElemanBos = " boş değer olamaz";

        String planAdi = "TS2296_" + getSysDate();
        System.out.println("Dağınım Planı: " + planAdi);

        //TS1280 tanımlanıyor
//        Map<String, String> dagitimPlanElemanlari = new LinkedHashMap<>();
//        dagitimPlanElemanlari.put("Kullanıcı", user.getFullname());
//        dagitimPlanElemanlari.put("Birim", user.getBirimAdi());
//        dagitimPlanElemanlari.put("Kurum", "Cumhurbaşkanlığı");
//        dagitimPlanElemanlari.put("Gerçek Kişi", "Zübeyde TEKİN");
//        dagitimPlanElemanlari.put("Tüzel Kişi", "Türksat Optiim");

        DagitimPlaniYonetimiPage page = new DagitimPlaniYonetimiPage().openPage();

        page.yeni();

        //Step 6 Dağıtım elemanları eklemeden kaydet
        page.adiGir(planAdi)
                .aciklamaGir("TS2296 açıklama")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true)
                .kaydet().islemMesaji().warningMessage(messageDagitimElemanlari);

        //Step 5 Kullanılacak Birim Boş kaydet
        page.dagitimElemanlariEkle("Birim", user.getBirimAdi())
                .kullanildigiBirimTemizle()
                .kaydet().islemMesaji().warningMessage(messageZorunluAlanlar);

        //Step 3 Adı Boş kaydet
        page.adiGir("")
                .kullanildigiBirimSec(user.getBirimAdi())
                .kaydet().islemMesaji().warningMessage(messageZorunluAlanlar);

        //Step 4 Açıklama Boş kaydet
        page.adiGir(planAdi)
                .aciklamaGir("")
                .kaydet().islemMesaji().warningMessage(messageZorunluAlanlar);

        //Step  Dağıtım Elemanı boşken ekleme
        page.aciklamaGir("TS2296 açıklama");
        dagitimPlanElemanlari.forEach((k, v) -> {
            page.dagitimElemanlariTipiSec(k)
                    .ekle().islemMesaji().warningMessage(k + messageDagitimElemanBos);
        });

        //dagitimPlanElemanlari.forEach((k, v) -> page.dagitimPlaniOlustur("TS2296_" + k,"TS2296 "+ k +" açıklama",user.getBirimAdi(), true, k, v));
        /*dagitimPlanElemanlari.forEach((k, v) -> {
            page.yeni()
                    .adiGir("TS2296_" + k)
                    .aciklamaGir(k + " açıklama")
                    .kullanildigiBirimSec(user.getBirimAdi())
                    .altBirimlerGorsunSec(true)
                    .dagitimElemanlariTipiSec(k).ekle().islemMesaji().warningMessage(k + messageDagitimElemanBos);
            *//*page.dagitimElemanlariEkle(k, v)
                    .kaydet().islemMesaji().basariliOlmali();*//*
        });*/
    }

    @Test(description = "TS2296: Dağıtım Planı Kaydet/Güncelle Ekranı Tüzel Kişi Arama Detayları Alan Kontrolleri", enabled = true, priority = 4)
    public void TS2296a() {
        User user = user1;
        login(user);

        String planAdi = "TS2296a_" + getSysDate();
        System.out.println("Dağınım Planı: " + planAdi);

        String tuzelKisi = "TS2296a Uydu Tv";

        page = new DagitimPlaniYonetimiPage().openPage();

        page.yeni();
        //Step 6 Dağıtım elemanları eklemeden kaydet
        page.adiGir(planAdi)
                .aciklamaGir("TS2296 açıklama")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true)
                .dagitimElemanlariTipiSec("Tüzel Kişi");

        page.tuzelKisiAramaDetaylari()
                .tuzelKisiTipiSecilir("MEDYA ŞİRKETİ")
                .alanlariKonrolu()
                .tuzelKisiDoldurulur(tuzelKisi)
                .uyduTvSecilir(true)
                .ara()
                .listelenKayidinCheckboxIsaretlenir(text(tuzelKisi), true)
                .ekle();

        page.getDagitimHitapDuzenlemeSilButton(tuzelKisi, "bulunur").shouldBe(visible);
        page.getDagitimHitapDuzenlemeGuncelleButton(tuzelKisi, "bulunur").shouldBe(visible);
        page.ekle(text(tuzelKisi));
    }

    @Test(description = "TS1936: Medya Tipinde Tüzel Kişi Ekleme İşlemleri", enabled = true)
    public void TS1936() {
        User user = user1;

        login(user);

        //List<String> tuzelKisi = new ReusableSteps().medyaSirketiTuzelKisiEkleme();
        List<String> tuzelKisi = new TuzelKisiYonetimiPage().medyaSirketiTuzelKisiEkleme();

        String adi = "TS1936_" + getSysDate();
        System.out.println("Dağınım Planı: " + adi);
        page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni()
                .adiGir(adi)
                .aciklamaGir("Medya Şirketi")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true)
                .dagitimElemanlariTipiSec("Tüzel Kişi")
                .tuzelKisiAramaDetaylari()
                .tuzelKisiTipiSecilir("MEDYA ŞİRKETİ")
                .alanlariKonrolu()
                .tuzelKisiDoldurulur(tuzelKisi.get(0))
                .ara()
                .listelenKayidinCheckboxIsaretlenir(text(tuzelKisi.get(0)), true)
                .ekle();
        page.ekle()
                .kaydet().islemMesaji().basariliOlmali();
        //page.dagitimPlaniOlustur(adi, "Medya Şirketi", user.getBirimAdi(),true, "Tüzel Kişi", tuzelKisi.get(0));
    }

    @Test(description = "TS1942: Onay Akışındaki sırasında dağıtım planının güncellenemesi", enabled = true)
    public void TS1942() {
        User parafci = mbozdemir;
        User imzaci = ztekin;
        //User user = optiim;
        User silenecekDagitimPlanUser = mbozdemir;
        User birimDagitimPlanUser = ztekin1;

        String konu = "TS1942_" + getSysDate();
        String dagitimPlanAdi = konu;
        System.out.println("Dağınım Planı: " + dagitimPlanAdi);
        String aciklama = "Dağıtım Elemanları: 2 kullanıcı + 1 birim";

        String[][] dagitimElemanlari = new String[][]{
                {KULLANICI.getOptionText(), parafci.getFullname(), ""}
                , {KULLANICI.getOptionText(), birimDagitimPlanUser.getFullname(), birimDagitimPlanUser.getBirimAdi()}
                , {BIRIM.getOptionText(), imzaci.getBirimAdi(), ""}

                //,{KULLANICI.getOptionText(), silenecekDagitimPlanUser.getFullname(), ""}
                //,{KULLANICI.getOptionText(), user.getFullname()}
        };

        login(parafci);
        DagitimPlaniYonetimiPage dagitimPlaniYonetimiPage = new DagitimPlaniYonetimiPage()
                .openPage()
                .dagitimPlaniOlustur(dagitimPlanAdi, aciklama, parafci.getBirimAdi(), true, dagitimElemanlari);

        new ReusableSteps().evrakOlusturVeParafla(konu, GeregiSecimTipi.DAGITIM_PLANLARI, dagitimPlanAdi, parafci, imzaci);


        dagitimPlaniYonetimiPage.openPage()
                .bulVeGuncelleTikla(dagitimPlanAdi)
                .dagitimPlaniListesindeAra(text(silenecekDagitimPlanUser.getFullname()))
                .sil(text(silenecekDagitimPlanUser.getFullname()))
                .dagitimElemanlariEkle(BIRIM.getOptionText(), birimDagitimPlanUser.getBirimAdi())
                .kaydet()
                .islemMesaji().basariliOlmali();

        login(imzaci);
        new ImzaBekleyenlerPage().openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        //login(silenecekDagitimPlanUser);
        new GelenEvraklarPage().openPage()
                .searchTable().findRows(text(konu)).shouldHaveSize(0);

        //login(birimDagitimPlanUser);
        new TeslimAlinmayiBekleyenlerPage().openPage()
                .searchTable().findRows(text(konu)).shouldHaveSize(1);
    }

    @Test(description = "TS1949: Dağıtım Hitap Düzenleme (Evrak Oluştur Ekranından)", enabled = true)
    public void TS1949() {
        User user = user1;
        login(user);

        String planAdi = "TS1280_20180206164745";
        System.out.println("Dağınım Planı: " + planAdi);

        Map<String, String> dagitimPlanElemanlari = new LinkedHashMap<>();
        dagitimPlanElemanlari.put("Kullanıcı", "Sayın User1 TEST");
        dagitimPlanElemanlari.put("Birim", "AnaBirim1E");
        dagitimPlanElemanlari.put("Kurum", "BAŞBAKANLIĞA");
        dagitimPlanElemanlari.put("Gerçek Kişi", "Sayın Zübeyde TEKİN");
        dagitimPlanElemanlari.put("Tüzel Kişi", "Türksat Optiime");

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .bilgiSecimTipiSec(BilgiSecimTipi.DAGITIM_PLANLARI)
                .bilgiSec(planAdi)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(planAdi));
        DagitimHitapDuzenle dagitimHitapDuzenle = new DagitimHitapDuzenle();
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("DAĞITIM YERLERİNE olmalı").shouldBe(visible);
        Assert.assertEquals(dagitimHitapDuzenle.getEvraktaGorunecekHitap("değişikliğin yapılamadığı görülür")
                .$(Selectors.byText("DAĞITIM YERLERİNE")).getTagName(), "span", "evrakta görünecek hitap span olmalı");

        dagitimHitapDuzenle.dagitimPlaniDetayListesiKontroluGereksizKontrollu(dagitimPlanElemanlari);
    }


    //region Steps
    @Step("Evrak Oluştur sayfada pasif yapılan dağıtım planının gereği alanında gelmediği görülür")
    private void evrakOlusturSayfadaPasifKontrolu(String adi) {
        new EvrakOlusturPage().openPage().bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiDegerSecilemez(adi);
    }


    @Step("Dağıtım Planı Aktif/Pasif yapılır")
    private void aktifPasifYap(String adi, boolean aktif) {

        String sorgulamaDurum = aktif ? "Sadece Pasifler": "Sadece Aktifler";

        page.sorgulamayiGenislet()
                .sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec(sorgulamaDurum)
                .ara()
                .sorgulamaDataTable.findRows(text(adi)).shouldHaveSize(1);
        if (aktif)
            page.aktifYap();
        else
            page.pasifYap();

        page.confirmDialog()
                .onayMesajKontrolu(text("Dağıtım planının durumunu değiştirmek istediğinize emin misiniz?"))
                .confirmEvetTikla()
                .islemMesaji().basariliOlmali();
    }

    @Step("Dağıtım Planı Pasif yapılır")
    private void pasifYap_o(String adi) {
        page.sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec("Sadece Aktifler")
                .ara()
                .sorgulamaDataTable
                .searchByColumnName("Dağıtım Planı Ad")
                .findRows(exactText(adi))
                .shouldHaveSize(1);
        page.pasifYap()
                .confirmDialog()
                .onayMesajKontrolu(text("Dağıtım planının durumunu değiştirmek istediğinize emin misiniz?"))
                .confirmEvetTikla();
        page.islemMesaji().basariliOlmali();
    }

    @Step("Dağıtım Planı Aktif yapılır")
    private void aktifYap_o(String adi) {
        page.sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec("Sadece Pasifler")
                .ara()
                .sorgulamaDataTable
                .searchByColumnName("Dağıtım Planı Ad")
                .findRows(exactText(adi))
                .shouldHaveSize(1);
        page.aktifYap()
                .confirmDialog()
                .onayMesajKontrolu(text("Dağıtım planının durumunu değiştirmek istediğinize emin misiniz?"))
                .confirmEvetTikla();
        page.islemMesaji().basariliOlmali();
    }

    @Step("Alanların değerleri kontrol edilir")
    private void checkFields(String adi, String aciklama, String kullanildigiBirim, boolean altBirimlerGorsun, String dagitimElemanlariTipi, String dagitimElemanlari) {
        page.getAdi().shouldHave(value(adi + "-KOPYA"));
        Assert.assertEquals(page.getAciklama().text(), aciklama, "Açıklama alanı");
        Assert.assertEquals(page.getKullanildigiBirim().getSelectedTitles().first().text(), kullanildigiBirim, "Kullanıldığı Birim");
        Assert.assertEquals(page.getAltBirimlerGorsun().isSelected(), altBirimlerGorsun, "Alt Birimler Görsün");
        page.dagitimPlaniDataTable.findRows().shouldHaveSize(1)
                .findRows(text(dagitimElemanlariTipi), text(dagitimElemanlari))
                .shouldHaveSize(1);
    }

    @Step("Evrak Oluştur ekranda gereği/bilgi alanlarından kontrol edilir")
    public void evrakOlusturSayfadaAktifKontrolu(String dagitimPlanAdi) {
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage().openPage();
        evrakOlusturPage.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiSec(dagitimPlanAdi)
                .geregiTemizle()
                .bilgiSecimTipiSec(BilgiSecimTipi.DAGITIM_PLANLARI)
                .bilgiSec(dagitimPlanAdi);
        evrakOlusturPage.closePage(false);
    }

    @Step("Giden Evrak Kayıt ekranda gereği/bilgi alanlarından kontrol edilir")
    public void gidenEvrakSayfadaAktifKontrolu(String dagitimPlanAdi) {
        new GidenEvrakKayitPage().openPage()
                .bilgiSecimTipiSecByText(BilgiSecimTipi.DAGITIM_PLANLARI.getOptionText())
                .bilgiDoldur(dagitimPlanAdi)
                .bilgiTemizle()
                .geregiSecimTipiSecByText(GeregiSecimTipi.DAGITIM_PLANLARI.getOptionText())
                .geregiDoldur(dagitimPlanAdi, "Dağıtım Planı");
    }
    //endregion

}
