package tests.DagitimPlaniYonetim;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.User;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageData.alanlar.BilgiSecimTipi;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.ImzaBekleyenlerPage;
import pages.ustMenuPages.DagitimPlaniYonetimiPage;
import pages.ustMenuPages.GidenEvrakKayitPage;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 1.02.2018
 * Açıklama:
 */
public class DagitimPlaniYonetimiTest extends BaseTest {

    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ/YGD");//, "Uzman Test Mühendis");
    User ztekin1 = new User("ztekin", "123", "Zübeyde TEKİN", "Optiim Birim/YGD");//, "Uzman Test Mühendis");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1");//, "Uzman Test Mühendis");
    DagitimPlaniYonetimiPage page;
    String yeniPlanAdi1280;
    String yeniPlanAdi2270;
    Map dagitimPlanElemanlari1;
    Map dagitimPlanElemanlari2;

    @Test(description = "TS1280: Yeni Kayıt İşlemi", enabled = true)
    public void TS1280() {
        //User user = optiim;
        User user = user1;
        login(user);

        yeniPlanAdi1280 = "TS1280_" + getSysDate();
        System.out.println("Dağınım Planı: " + yeniPlanAdi1280);

        dagitimPlanElemanlari1 = new LinkedHashMap<String, String>();
        dagitimPlanElemanlari1.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari1.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari1.put("Kurum", "Başbakanlık");
        dagitimPlanElemanlari1.put("Gerçek Kişi", "ZÜBEYDE");
        dagitimPlanElemanlari1.put("Tüzel Kişi", "Türksat Optiim");

        page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni()
                .adiGir(yeniPlanAdi1280)
                .aciklamaGir("TS1280 açıklama")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true);

        dagitimPlanElemanlari1.forEach((k, v)->dagitimElemanlari(k.toString(),v.toString()));
        page.kaydet().islemMesaji().basariliOlmali();
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
        page.sorgulamaDataTableGuncelleButonaTikla();

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
        if (page.kaydetGuncelleDataTable.findRows(text(dagitimTipi)).findRows(text(dagitimYeri)).getFoundRows().size() > 0){
            yeniDagitimTipi = "Birim";
            yeniDagitimYeri = "Optiim Birim";
        }
        //Eski dağıtımları silinir
        while (page.kaydetGuncelleDataTable.findRows().getFoundRows().size() != 0){
            page.kaydetGuncelleDataTable.findRows().getFoundRow().$(page.deleteButtonLocator).click();
        }

//        Assert.assertEquals(page.getAdi().getValue(), newAd, "Dağıtım Yeri silindikten sonra Adı alanı güncelleme kayboluyor");
//        Assert.assertEquals(page.getAciklama().text(), newAciklama, "Dağıtım Yeri silindikten sonra Açıklama alanı güncelleme kayboluyor");

        //Yeni dağıtım eklenir
        dagitimElemanlari(yeniDagitimTipi, yeniDagitimYeri);
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
                .sorgulamaDataTable
                    .searchByColumnName("Dağıtım Planı Ad")
                    .findRows(exactText(adi))
                    .shouldHaveSize(1);//.getFoundRow().$(page.copyButtonLocator).click();
        page.sorgulamaDataTableKopyalaButonaTikla();
        checkFields(adi, aciklama, kullanildigiBirim, altBirimlerGorsun, dagitimElemanlariTipi, dagitimElemanlari);
        page.adiGir(newAd)
                .kaydet().islemMesaji().basariliOlmali();

        evrakOlusturSayfadaAktifKontrolu(newAd);
        gidenEvrakSayfadaAktifKontrolu(newAd);
    }

    @Test(description = "TS1279: Pasif / Aktif Yapma", enabled = true)
    public void TS1279() {
        String adi = "TS1279";
        boolean pasif = false;

        User user = optiim;
        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();
        page.sorgulamadaAdGir(adi).sorgulamadaDurumSec("Sadece Aktifler").ara();
        int size = page.sorgulamaDataTable.searchByColumnName("Dağıtım Planı Ad").findRows(exactText(adi)).getFoundRows().size();

        if (size == 1) {
            pasif = true;
            pasifYap(adi);
            evrakOlusturSayfadaPasifKontrolu(adi);
        } else {
            Assert.assertEquals(0, size, adi + " Pasif ise Sadece Aktifler aramada 0 kayıt gelmeli");
            aktifYap(adi);
            evrakOlusturSayfadaAktifKontrolu(adi);
        }

        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();
        if (pasif) {
            aktifYap(adi);
            evrakOlusturSayfadaAktifKontrolu(adi);
        } else {
            pasifYap(adi);
            evrakOlusturSayfadaPasifKontrolu(adi);
        }
    }

    @Test(description = "TS2260: Sorgulama ve Filtreleme", enabled = true)
    public void TS2260() {
        User user = optiim;
        login(user);
        page = new DagitimPlaniYonetimiPage().openPage();

        step("STEP: Durum \"Sadece Pasifler\"", "Pasifler bulunmalı, aktifler bulunmamalı");
        page.sorgulamadaDurumSec("Sadece Pasifler").ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text("PASİF")).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text("TS2260")).shouldHaveSize(0);

        step("STEP: Durum \"Sadece Aktifler\"", "Aktifler bulunmalı, pasifler bulunmamalı");
        page.sorgulamadaDurumSec("Sadece Aktifler").ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text("TS2260")).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text("PASİF")).shouldHaveSize(0);

        step("STEP: Durum \"Tümü\"", "Aktifler ve pasifler bulunmalı");
        page.sorgulamadaDurumSec("Tümü").ara().sorgulamaDataTable
                .searchInAllPages(true)
                .findRows(text("PASİF")).shouldHaveSize(1)
                .goToFirstPage()
                .findRows(text("TS2260")).shouldHaveSize(1);

        step("STEP: Durum \"Tümü\" ve Ad girilir", "Sadece adi girilen bulunmalı");
        page.sorgulamadaDurumSec("Tümü")
                .sorgulamadaAdGir("PASİF")
                .ara()
                .sorgulamaDataTable.searchInAllPages(true)
                .findRows(text("PASİF")).shouldHaveSize(1);
    }

    @Test(description = "TS2323: Yeni Dağıtım Planı Kayıt (Ekranlardan Kontrolü)", enabled = true
            , dependsOnMethods = {"TS1280"}
            )
    public void TS2323() {
        //User user = optiim;
        User user = user1;
        login(user);

        //yeniPlanAdi1280 = "TS1280_20180205102433";
        dagitimPlanElemanlari1 = new LinkedHashMap<String, String>();
        dagitimPlanElemanlari1.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari1.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari1.put("Kurum", "Başbakanlık");
        dagitimPlanElemanlari1.put("Gerçek Kişi", "ZÜBEYDE");
        dagitimPlanElemanlari1.put("Tüzel Kişi", "Türksat Optiim");

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
        evrakOlusturPage.dagitimHitapDuzenle().dagitimPlaniDetaySirasiKontrolu(dagitimPlanElemanlari1)
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
                .kullanButonaTikla()
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

        page = new DagitimPlaniYonetimiPage().openPage().araVeGuncelleTiklanir(adi);

        u = page.getKullanildigiBirim().getSelectedTitles().last().has(text(u1.getBirimAdi())) ? u2 :u1;

        page.kullanildigiBirimSec(u.getBirimAdi(), u.getBirimKisaAdi()).kaydet().islemMesaji().basariliOlmali();

        evrakOlusturSayfadaPasifKontrolu(adi);

        login(u);
        evrakOlusturSayfadaAktifKontrolu(adi);

    }

    @Test(description = "TS2270: Dağıtım Hitap Düzenleme", enabled = true)
    public void TS2270() {
        User user = user1;
        login(user);

        yeniPlanAdi2270 = "TS2270_" + getSysDate();
        System.out.println("Dağınım Planı: " + yeniPlanAdi2270);

        /*dagitimPlanElemanlari2 = new LinkedHashMap<String, String>();
        dagitimPlanElemanlari2.put("Kullanıcı", user.getFullname());
        dagitimPlanElemanlari2.put("Birim", user.getBirimAdi());
        dagitimPlanElemanlari2.put("Kurum", "Başbakanlık");
        dagitimPlanElemanlari2.put("Gerçek Kişi", "ZÜBEYDE");
        dagitimPlanElemanlari2.put("Tüzel Kişi", "Türksat Optiim");*/

        page = new DagitimPlaniYonetimiPage().openPage();
        page.yeni()
                .adiGir(yeniPlanAdi2270)
                .aciklamaGir("Dağıtım Planı_Dağıtım Hitap Düzenleme")
                .kullanildigiBirimSec(user.getBirimAdi())
                .altBirimlerGorsunSec(true);

        DagitimHitapDuzenle dagitimHitapDuzenle;
        String dagitimElemanlariTipi, dagitimElemanlari;

        //Kullanıcı
        dagitimElemanlariTipi = "Kullanıcı";
        dagitimElemanlari = user.getFullname();
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi), text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        dagitimHitapDuzenle = page.guncelleTikla();
        String ek = "NA";
        dagitimHitapDuzenle.getEkOfInput("\"" + dagitimElemanlariTipi + "\" alanın ek \""+ek+"\" ile güncelle", value(dagitimElemanlari)).setValue(ek);
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ dagitimElemanlari + ek +"\" olmalı").shouldHave(text(dagitimElemanlari + ek));
        dagitimHitapDuzenle.getKaydetButton("tıklanır").click();

        //Birim
        dagitimElemanlariTipi = "Birim";
        dagitimElemanlari = user.getBirimAdi();
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi), text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        dagitimHitapDuzenle = page.guncelleTikla();
        String birimOzelHitap = "BirimÖzelHitap";
        clickJs(dagitimHitapDuzenle.getOzelHitapCheckbox());
        dagitimHitapDuzenle.getHitapTextarea().setValue(birimOzelHitap);
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ birimOzelHitap +"\" olmalı").shouldHave(text(birimOzelHitap));
        dagitimHitapDuzenle.getKaydetButton("tıklanır").click();

        //Kurum
        dagitimElemanlariTipi = "Kurum";
        dagitimElemanlari = "Başbakanlık";
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi),text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        page.getGuncelleButton().shouldBe(visible);

        //Tüzel Kişi
        dagitimElemanlariTipi = "Tüzel Kişi";
        dagitimElemanlari = "Türksat Optiim";
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi), text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        dagitimHitapDuzenle = page.guncelleTikla();
        String tuzelOzelHitap = "TüzelÖzelHitap";
        clickJs(dagitimHitapDuzenle.getOzelHitapCheckbox());
        dagitimHitapDuzenle.getHitapTextarea().setValue(tuzelOzelHitap);
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ tuzelOzelHitap +"\" olmalı").shouldHave(text(tuzelOzelHitap));
        dagitimHitapDuzenle.getKaydetButton("tıklanır").click();

        //Gerçek Kişi
        dagitimElemanlariTipi = "Gerçek Kişi";
        dagitimElemanlari = "Zübeyde TEKİN";
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi), text("Zübeyde"));
        page.getSilButton().shouldBe(visible);
        dagitimHitapDuzenle = page.guncelleTikla();
        String adres = "HitapAdres";
        dagitimHitapDuzenle.getAdresTextarea().setValue(adres);
        clickJs(dagitimHitapDuzenle.getAdresHitaptaGorunsunCheckbox());
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ adres +"\" olmalı").shouldHave(text(adres));
        clickJs(dagitimHitapDuzenle.getAdresHitaptaGorunsunCheckbox());
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ adres +"\" bulunmamalı").shouldNotHave(text(adres));
        clickJs(dagitimHitapDuzenle.getAdresHitaptaGorunsunCheckbox());
        dagitimHitapDuzenle.getEvraktaGorunecekHitap("Görünecek Hitap \""+ adres +"\" olmalı").shouldHave(text(adres));
        dagitimHitapDuzenle.getKaydetButton("tıklanır").click();

        /*DagitimHitapDuzenle screen;
        //dagitimPlanElemanlari2.forEach((k, v)->dagitimElemanlariVeGuncelle(k.toString(),v.toString()));
        dagitimPlanElemanlari2.forEach((k,v)->{

            switch (k.toString()){
                case "Kullanıcı":
                    kullaniciHitapEkUpdate(dagitimElemanlariVeGuncelle(k.toString(), v.toString()), v.toString(), "NA");
                    break;
                case "Birim":
                    break;
                case "Kurum":
                    break;
                case "Gerçek Kişi":
                    break;
                case "Tüzel Kişi":
                    break;
                default:
                    break;
            }

            if (k.equals("Kullanıcı"))

            System.out.println("Item : " + k + " Count : " + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
        //kullaniciHitapEkUpdate(dagitimHitapDuzenle, dagitimElemanlari, "NA");
        //page.kaydet().islemMesaji().basariliOlmali();*/
    }

    @Test(description = "TS2271: Hitabın oluşturulan evrak üzerinde kontrolü", enabled = true)
    public void TS2271() {

    }

    //region Steps
    @Step("{name} : {description}")
    private void step(String name, String description){ }

    @Step("Evrak Oluştur sayfada pasif yapılan dağıtım planının gereği alanında gelmediği görülür")
    private void evrakOlusturSayfadaPasifKontrolu(String adi) {
        new EvrakOlusturPage().openPage().bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiDegerSecilemez(adi);
    }

    @Step("Dağıtım Planı Pasif yapılır")
    private void pasifYap(String adi) {
        page.sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec("Sadece Aktifler")
                .ara()
                .sorgulamaDataTable
                .searchByColumnName("Dağıtım Planı Ad")
                .findRows(exactText(adi))
                .shouldHaveSize(1);
        page.sorgulamaDataTablePasifYapButonaTikla()
                .confirmDialog()
                .onayMesajKontrolu(text("Dağıtım planının durumunu değiştirmek istediğinize emin misiniz?"))
                .confirmEvetTikla();
        page.islemMesaji().basariliOlmali();
    }

    @Step("Dağıtım Planı Aktif yapılır")
    private void aktifYap(String adi) {
        page.sorgulamadaAdGir(adi)
                .sorgulamadaDurumSec("Sadece Pasifler")
                .ara()
                .sorgulamaDataTable
                .searchByColumnName("Dağıtım Planı Ad")
                .findRows(exactText(adi))
                .shouldHaveSize(1);
        page.sorgulamaDataTableAktifYapButonaTikla()
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
        page.kaydetGuncelleDataTable.findRows().shouldHaveSize(1)
                .findRows(text(dagitimElemanlariTipi),text(dagitimElemanlari))
                .shouldHaveSize(1);
    }

    @Step("\"{dagitimElemanlariTipi}\" eklenir")
    void dagitimElemanlari(String dagitimElemanlariTipi, String dagitimElemanlari) {
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi)
                .dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi)).findRows(text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        page.getGuncelleButton().shouldBe(visible);

        /*page.getDagitimElemanlariCombolov().getSelectedItems().first().$(page.deleteButtonLocator).shouldBe(visible);
        page.getDagitimElemanlariCombolov().getSelectedItems().first().$(page.updteButtonLocator).shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimPlanElemanlari1));
        page.kaydetGuncelleDataTable.getFoundRow().$(page.deleteButtonLocator).shouldBe(visible);
        page.kaydetGuncelleDataTable.getFoundRow().$(page.updteButtonLocator).shouldBe(visible);*/
    }

    @Step("\"{dagitimElemanlariTipi}\" eklenir")
    DagitimHitapDuzenle dagitimElemanlariVeGuncelle(String dagitimElemanlariTipi, String dagitimElemanlari) {
        page.dagitimElemanlariSelecSec(dagitimElemanlariTipi).dagitimElemanlariCombolovSec(dagitimElemanlari);
        page.getDagitimElemanlariCombolovDagitimHitapDuzenlemeButton().shouldBe(visible);
        page.getDagitimElemanlariCombolovListedenCikartButton().shouldBe(visible);
        page.ekle();
        page.kaydetGuncelleDataTable.findRows(text(dagitimElemanlariTipi)).findRows(text(dagitimElemanlari));
        page.getSilButton().shouldBe(visible);
        return page.guncelleTikla();
    }
    
    @Step("")
    public DagitimPlaniYonetimiTest kullaniciHitapEkUpdate(DagitimHitapDuzenle screen, String kullaniciIsim, String newEk){
        screen.getEkOfInput(kullaniciIsim + " alanın yeni \""+ newEk +"\" ek kullan", value(kullaniciIsim)).setValue(newEk);
        screen.getEvraktaGorunecekHitap("Görünecek Hitap \""+ kullaniciIsim + newEk +"\" olmalı").shouldHave(text(kullaniciIsim + newEk));
        screen.getKaydetButton("tıklanır").click();
        return this;
    }

    @Step("Evrak Oluştur ekranda gereği/bilgi alanlarından kontrol edilir")
    public void evrakOlusturSayfadaAktifKontrolu(String dagitimPlanAdi){
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
    public void gidenEvrakSayfadaAktifKontrolu(String dagitimPlanAdi){
        new GidenEvrakKayitPage().openPage()
                .bilgiSecimTipiSecByText(BilgiSecimTipi.DAGITIM_PLANLARI.getOptionText())
                .bilgiDoldur(dagitimPlanAdi)
                .bilgiTemizle()
                .geregiSecimTipiSecByText(GeregiSecimTipi.DAGITIM_PLANLARI.getOptionText())
                .geregiDoldur(dagitimPlanAdi,"Dağıtım Planı");
    }
    //endregion

}
