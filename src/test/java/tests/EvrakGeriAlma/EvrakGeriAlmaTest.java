package tests.EvrakGeriAlma;

import com.codeborne.selenide.Selenide;
import common.BaseTest;
import data.User;
import org.testng.annotations.Test;
import pages.newPages.EvrakDetayiPage;
import pages.newPages.EvrakOlusturPage;
import pages.newPages.OlurYazisiOlusturPage;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.SearchTable;
import pages.pageData.alanlar.BilgiSecimTipi;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.Ivedilik;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static pages.pageData.alanlar.BilgiSecimTipi.*;
import static pages.pageData.alanlar.OnayKullaniciTipi.*;
import static pages.pageData.alanlar.OnayKullaniciTipi.IMZALAMA;
import static pages.pageData.alanlar.OnayKullaniciTipi.PARAFLAMA;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 27.02.2018
 * Açıklama:
 */
public class EvrakGeriAlmaTest extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
    User user5 = new User("user5", "123", "User5 TEST", "AnaBirim1", "Ağ (Network) Uzmanı");
    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Ağ (Network) Uzman Yardımcısı");
    User ztekin = new User("ztekin", "123", "Zübeyde TEKİN", "YGD/BHUPGMY", "Genel Müdür");

    @Test(description = "TS0978a: Olur yazısı - İmzacıdan evrakın koordine parafçı tarafından geri alınması", enabled = true)
    public void TS0978a() {
        User parafci = user1;
        User koordeneliKullanci = optiim;
        User imzaci = ztekin;

        String konu = "TS0978a - " + getDateTime();
        String notTeksti = "Gerial not tesksti";

        login(parafci);

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(
                        "010.10"
                        , konu
                        , "Diğer"
                        , Ivedilik.NORMAL
                        , GeregiSecimTipi.BIRIM
                        , "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"
                )
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(parafci, PARAFLAMA)
                .anlikOnayAkisKoordeneliKullaniciSec(koordeneliKullanci)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullanAndCheck(
                        text(parafci.getFullname() + "-" + PARAFLAMA.getOptionText())
                        , text(koordeneliKullanci.getFullname() + "-Koordine")
                        , text(imzaci.getFullname() + "-" + IMZALAMA.getOptionText())
                );
        page.editorTab().openTab().getEditor().type("editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(koordeneliKullanci);
        KoordineBekleyenlerPage koordineBekleyenlerPage = new KoordineBekleyenlerPage().openPage();
        koordineBekleyenlerPage.searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakKoordineParafla()
                .islemMesaji().basariliOlmali();

        new KoordineParafladiklarimPage().openPage()
                .searchTable().findRowAndSelect(text(konu));

        new EvrakOnizleme().new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci.getFullname()))
                .evrakPageButtons().geriAl()
                .geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(notTeksti)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        koordineBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(notTeksti);

    }

    @Test(description = "TS0978b: Olur yazısı - İmzacıdan evrakın ilk imzacı tarafından geri alınması", enabled = true)
    public void TS0978b() {
        User parafci = user1;
        User imzaci1 = optiim;
        User imzaci2 = ztekin;

        String konu = "TS0978b - " + getDateTime();
        String notTeksti = "Gerial not tesksti";

        login(parafci);

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(
                        "010.10"
                        , konu
                        , "Diğer"
                        , Ivedilik.NORMAL
                        , GeregiSecimTipi.BIRIM
                        , "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ"
                )
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci1, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci2, IMZALAMA)
                .kullanAndCheck(
                        text(parafci.getFullname() + "-" + PARAFLAMA.getOptionText())
                        , text(imzaci1.getFullname() + "-" + IMZALAMA.getOptionText())
                        , text(imzaci2.getFullname() + "-" + IMZALAMA.getOptionText())
                );
        page.editorTab().openTab().getEditor().type("editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(imzaci1);
        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage().openPage();
        imzaBekleyenlerPage.searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        new ImzaladiklarimPage().openPage()
                .searchTable().findRowAndSelect(text(konu));
        new EvrakOnizleme().new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci2.getFullname()))
                .evrakPageButtons()
                .geriAl().geriAlGeriAl().islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(notTeksti)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        imzaBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(notTeksti);

    }

    @Test(description = "TS0978c: Olur yazısı - İmzacıdan evrakın parafçı tarafından geri alınması", enabled = true)
    public void TS0978c() {
        User parafci = user1;
        User imzaci = optiim;
        User kullanici = ztekin;

        String konu = "TS0978c - " + getDateTime();
        String birim = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        String notTeksti = "Gerial not tesksti";
        String editorTekst = "Editör tekst";
        String yeniEditorTeksti = "Yeni editör teksti";

        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        login(parafci);
        System.out.println("Konu: " + konu);

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec("010.10")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .ivedilikSec(Ivedilik.NORMAL)
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(birim)
                .bilgiSecimTipiSec(KULLANICI)
                .bilgiSec(kullanici.getFullname())
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .secilenAnlikOnayAkisKullanicilariKontrolEt(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullanAndCheck(
                        text(parafci.getFullname() + "-" + PARAFLAMA.getOptionText())
                        , text(imzaci.getFullname() + "-" + IMZALAMA.getOptionText())
                );
        page.editorTab().openTab().getEditor().type(editorTekst)
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        ParafladiklarimPage parafladiklarimPage = new ParafladiklarimPage();
        parafladiklarimPage.openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci.getFullname()))
                .evrakPageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(notTeksti)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        new ParafBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .gonderenNotuTooltip(notTeksti)
                .icerikGoster()
                //.editorTab().getEditor().type(Keys.chord(Keys.COMMAND, "A"), "Yeni editör teskst")
                .editorTab().getEditor().clear().type(yeniEditorTeksti)
                .evrakPageButtons().evrakKaydet()
                .islemMesaji().basariliOlmali()
                .evrakParafla()
                .islemMesaji().basariliOlmali();

        SearchTable searchTable = parafladiklarimPage.openPage().searchTable()
                .findRowAndSelect(text(konu));
        searchTable.getIcerikGosterButton("bulunmalı").shouldBe(visible);
        searchTable.getTamEkranButton("bulunmalı").shouldBe(visible);

        login(imzaci);
        new ImzaBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();
        new ImzaladiklarimPage().openPage().searchTable()
                .findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi()
                .bulunanKayittaKontrol(
                        text("Evrak kurum içi otomatik postalandı.")
                        , text("Evrak Klasöre kaldırıldı")
                );

        //Birim kullanıcı ile
        login(kullanici);
        new TeslimAlinmayiBekleyenlerPage().openPage()
                .searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new TeslimAlveHavaleEt()
                .kisiyeSec(kullanici)
                .teslimAlGonder()
                .islemMesaji().basariliOlmali();
        new GelenEvraklarPage().openPage()
                .searchTable().findRowAndSelect(text(konu));

    }

    @Test(description = "TS0978d: Evrak - İmzacıdan evrakın ilk imzacı tarafından geri alınması ve iade işlemleri", enabled = true)
    public void TS0978d() {
        User parafci = user1;
        User imzaci1 = optiim;
        User imzaci2 = ztekin;

        String konu = "TS0978d - " + getDateTime();
        String kurum = "Cumhurbaşkanlığı";
        String geriAlNotu = "Gerial not teksti";
        String iadeEtNotu = "İade et not teksti";
        String yeniEditorTeksti = "Yeni editör teksti";
        String versiyonlariKarsilastirTooltip = "Evrak "+imzaci1.getFullname()+" kullanıcısının düzelttiği versiyonuyla akışa sokulmuştur.";

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage();
        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        login(parafci);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum)
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci1, IMZALAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci2, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();


        login(imzaci1);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        imzaladiklarimPage.openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .sonHareketKontrol(text(imzaci2.getFullname()), text("İmza bekliyor"))
                .evrakPageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);
        EvrakDetayiPage evrakDetayiPage = imzaBekleyenlerPage.searchTable().findRowAndSelect(text(konu)).icerikGoster();
        evrakDetayiPage.editorTab().getEditor().clear().type(yeniEditorTeksti)
                .evrakPageButtons().evrakKaydet()
                .islemMesaji().basariliOlmali();
        /*evrakDetayiPage.closePage();
        imzaBekleyenlerPage
                .searchTable().findRowAndSelect(text(konu))
                .evrakImzala()
                .islemMesaji().basariliOlmali();*/
        evrakDetayiPage.pageButtons().evrakIadeEt()
                /*.islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().evrakIadeEt(iadeEtNotu)*/
                .islemMesaji().basariliOlmali();

        login(parafci);
        new ParafBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .icerikDegistiIkon(visible)
                .disYaziIkon(visible)
                .iadeEdilmistirIkon(visible)
                .icerikGosterButton(visible)
                .versiyonlariKarsilastirTooltip(versiyonlariKarsilastirTooltip)
                .tamEkran(visible)
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(imzaci1);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        evrakDetayiPage = new ImzaladiklarimPage().openPage()
                .searchTable().findRows(text(konu))
                .icerikGoster();
        evrakDetayiPage.pageButtons().evrakGoster();
        new EvrakOnizleme().pdfOnizlemeKontrol(text(yeniEditorTeksti));

        evrakDetayiPage.pageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();
        imzaBekleyenlerPage.openPage().searchTable().findRowAndSelect(text(konu));
    }

    @Test(description = "TS0978e: Evrak - İmzacıdan evrakın parafçı tarafından geri alınması", enabled = true)
    public void TS0978e() {
        User parafci = user1;
        User imzaci = optiim;

        String konu = "TS0978e - " + getDateTime();
        String kurum = "Cumhurbaşkanlığı";
        String geriAlNotu = "Gerial not teksti";
        String yeniEditorTeksti = "Yeni editör teksti";

        login(parafci);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum, parafci, imzaci);
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        ParafladiklarimPage parafladiklarimPage = new ParafladiklarimPage();
        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        parafladiklarimPage.openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .sonHareketKontrol(text(imzaci.getFullname()), text("İmza bekliyor"))
                .evrakPageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        new ParafBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu)
                .icerikGoster()
                .editorTab().getEditor().clear().type(yeniEditorTeksti)
                .evrakPageButtons().evrakKaydet()
                .islemMesaji().basariliOlmali()
                .evrakParafla()
                .islemMesaji().basariliOlmali();

        SearchTable searchTable = parafladiklarimPage.openPage().searchTable()
                .findRowAndSelect(text(konu));
        searchTable.getIcerikGosterButton("bulunmalı").shouldBe(visible);
        searchTable.getTamEkranButton("bulunmalı").shouldBe(visible);

        login(imzaci);
        new ImzaBekleyenlerPage().openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        EvrakDetayiPage evrakDetayiPage = new ImzaladiklarimPage().openPage()
                .searchTable().findRows(text(konu))
                .icerikGoster();
        evrakDetayiPage.pageButtons()
                .getGeriAl().shouldBe(visible);
        evrakDetayiPage.pageButtons().evrakGoster();

        new EvrakOnizleme().pdfOnizlemeKontrol(text(yeniEditorTeksti), text(kurum.toUpperCase() + "NA"));
    }

    @Test(description = "TS0980a: Olur yazısı - İmzacıdan evrakın kontrolcü tarafından geri alınması", enabled = true)
    public void TS0980a() {
        User parafci = user1;
        User kontolcu = optiim;
        User imzaci = ztekin;

        String konu = "TS0980a - " + getDateTime();
        String geriAlNotu = "Gerial not teksti";

        KontrolBekleyenlerPage kontrolBekleyenlerPage;
        EvrakOnizleme evrakOnizleme;

        login(parafci);
        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .bilgiSec(BIRIM, parafci.getBirimAdi())
                .onayAkisiTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(kontolcu, KONTROL)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(kontolcu);

        kontrolBekleyenlerPage = new KontrolBekleyenlerPage().openPage();
        kontrolBekleyenlerPage.openPage().searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().kontrolEt();

        evrakOnizleme = new EvrakOnizleme();
        evrakOnizleme.new KontrolEt().kontolEtEkrani(
                text(PARAFLAMA.getOptionText() + "\n" + parafci.getFullname())
                ,text(KONTROL.getOptionText() + "\n" + kontolcu.getFullname())
                ,text(IMZALAMA.getOptionText() + "\n" + imzaci.getFullname()))
                .onayla()
                .islemMesaji().basariliOlmali();

        new KontrolEttiklerim().openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci.getFullname()))
                .evrakPageButtons().geriAl()
                .geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        kontrolBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);

    }

    @Test(description = "TS0980b: Olur yazısı - Koordine parafçısından evrakın parafçı tarafından geri alınması", enabled = true)
    public void TS0980b() {
        User parafci = user1;
        User kontolcu = optiim;
        User imzaci = ztekin;

        String konu = "TS0980b - " + getDateTime();
        String geriAlNotu = "Gerial not teksti";

        KontrolBekleyenlerPage kontrolBekleyenlerPage;
        EvrakOnizleme evrakOnizleme;

        login(parafci);
        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage().openPage();
        page.bilgileriTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .bilgiSec(BIRIM, parafci.getBirimAdi())
                .onayAkisiTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(kontolcu, KONTROL)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(kontolcu);
        kontrolBekleyenlerPage = new KontrolBekleyenlerPage().openPage();
        kontrolBekleyenlerPage.searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().kontrolEt();

        evrakOnizleme = new EvrakOnizleme();
        evrakOnizleme.new KontrolEt().kontolEtEkrani(
                text(PARAFLAMA.getOptionText() + "\n" + parafci.getFullname())
                ,text(KONTROL.getOptionText() + "\n" + kontolcu.getFullname())
                ,text(IMZALAMA.getOptionText() + "\n" + imzaci.getFullname()))
                .onayla()
                .islemMesaji().basariliOlmali();

        new KontrolEttiklerim().openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci.getFullname()))
                .evrakPageButtons().geriAl()
                .geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        kontrolBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);

    }



    @Test(description = "TS0979: Postalanmayı bekleyen evrakın önizlemeden geri alınması ve iade işlemleri", enabled = true)
    public void TS0979() {
        User parafci = user1;
        User imzaci = user5;
        //User imzaci2 = ztekin;

        String konu = "TS0979 - " + getDateTime();
        String kurum = "Cumhurbaşkanlığı";
        String geriAlNotu = "Gerial not teksti";
        String iadeEtNotu = "İade et not teksti";
        String yeniEditorTeksti = "Yeni editör teksti";
        String versiyonlariKarsilastirTooltip = "Evrak "+imzaci.getFullname()+" kullanıcısının düzelttiği versiyonuyla akışa sokulmuştur.";

        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage();
        ImzaladiklarimPage imzaladiklarimPage = new ImzaladiklarimPage();
        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();

        login(parafci);

        System.out.println("Konu: " + konu);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum)
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();


        login(imzaci);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        /*new PostalanacakEvraklarPage().openPage()
                .searchTable().findRowAndSelect(text(konu));*/

        imzaladiklarimPage.openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .sonHareketKontrol(text("Evrak postalanmayı bekliyor"))
                .evrakPageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);
        EvrakDetayiPage evrakDetayiPage = imzaBekleyenlerPage.searchTable().findRowAndSelect(text(konu)).icerikGoster();
        evrakDetayiPage.editorTab().getEditor().clear().type(yeniEditorTeksti)
                .evrakPageButtons().evrakKaydet()
                .islemMesaji().basariliOlmali();
        evrakDetayiPage.closePage();
        imzaBekleyenlerPage
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakGuncellenenEvraginImzalaTiklaDialogKontrol()
                //.evrakIadeEt().islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().evrakIadeEt(iadeEtNotu);
        //        Evrakınız güncellendiği için imzalanamaz! Evrakın iade edilmesi gerekmektedir.

        /*evrakDetayiPage.pageButtons().evrakIadeEt()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().evrakIadeEt(iadeEtNotu)
                .islemMesaji().basariliOlmali();*/

        login(parafci);
        new ParafBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .gonderenNotuTooltip(iadeEtNotu)
                .icerikDegistiIkon(visible)
                //.disYaziIkon(visible)
                .iadeEdilmistirIkon(visible)
                .icerikGosterButton(visible)
                .versiyonlariKarsilastirTooltip(versiyonlariKarsilastirTooltip)
                .tamEkran(visible)
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();

        login(imzaci);
        imzaBekleyenlerPage
                .openPage()
                .searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        evrakDetayiPage = new ImzaladiklarimPage().openPage()
                .searchTable().findRows(text(konu))
                .icerikGoster();
        evrakDetayiPage.pageButtons().evrakGoster();
        new EvrakOnizleme().pdfOnizlemeKontrol(text(yeniEditorTeksti));

        evrakDetayiPage.pageButtons().geriAl().geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl();
//                .islemMesaji().basariliOlmali();
        imzaBekleyenlerPage.openPage().searchTable().findRowAndSelect(text(konu));
    }



    @Test(description = "TS976a: Olur yazısı - Koordine parafçısından evrakın kontrolcü tarafından geri alınması", enabled = true)
    public void TS0976a() {
        User parafci = user1;
        User kontolcu = user5;
        User koordeneli = optiim;
        User imzaci = ztekin;

        String konu = "TS0976a - " + getDateTime();
        String kurum = "Cumhurbaşkanlığı";
        String geriAlNotu = "Gerial not teksti";

        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();
        KontrolBekleyenlerPage kontrolBekleyenlerPage = new KontrolBekleyenlerPage();

        login(parafci);

        System.out.println("Konu: " + konu);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum)
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(kontolcu, KONTROL)
                .anlikOnayAkisKoordeneliKullaniciSec(koordeneli)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();


        login(kontolcu);
        kontrolBekleyenlerPage.openPage().searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().kontrolEt();
        evrakOnizleme.new KontrolEt().kontolEtEkrani(
                text(PARAFLAMA.getOptionText() + "\n" + parafci.getFullname())
                ,text(KONTROL.getOptionText() + "\n" + kontolcu.getFullname())
                ,text("Koordeni" + "\n" + koordeneli.getFullname())
                ,text(IMZALAMA.getOptionText() + "\n" + imzaci.getFullname()))
                .onayla()
                .islemMesaji().basariliOlmali();

        new KontrolEttiklerim().openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.new EvrakGecmisi().tabiAc()
                .kayitBulunmali(text("Evrak Koordine bekliyor"))
                .bulunanKayittaKontrol(text(koordeneli.getFullname()))
                .evrakPageButtons().geriAl()
                .geriAlGeriAl()
                .islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
                .evrakPageButtons().geriAlNotDoldur(geriAlNotu)
                .geriAlGeriAl()
                .islemMesaji().basariliOlmali();

        kontrolBekleyenlerPage.openPage()
                .searchTable().findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);
    }

    @Test(description = "TS976b: Olur yazısı- Kontrolcüden parafçı tarafından evrakın geri alınması", enabled = true)
    public void TS0976b() {
        User parafci = user1;
        User kontolcu = user5;
        User imzaci = optiim;

        String konu = "TS0976b - " + getDateTime();
        String kurum = "Cumhurbaşkanlığı";
        String geriAlNotu = "Gerial not teksti";

        EvrakOnizleme evrakOnizleme = new EvrakOnizleme();
        KontrolBekleyenlerPage kontrolBekleyenlerPage = new KontrolBekleyenlerPage();

        login(parafci);

        System.out.println("Konu: " + konu);
        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .alanlariDoldur(konu, GeregiSecimTipi.KURUM, kurum)
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(parafci, PARAFLAMA)
                .anlikOnayAkisKullaniciVeTipiSec(kontolcu, KONTROL)
                .anlikOnayAkisKullaniciVeTipiSec(imzaci, IMZALAMA)
                .kullan();
        page.editorTab().openTab().getEditor().type("Editör tekst")
                .evrakPageButtons().evrakParafla()
                .islemMesaji().basariliOlmali();


        login(kontolcu);
        kontrolBekleyenlerPage.openPage().searchTable().findRowAndSelect(text(konu))
                .evrakPageButtons().kontrolEt();
        evrakOnizleme.kontrolEt.kontolEtEkrani(
                text(PARAFLAMA.getOptionText() + "\n" + parafci.getFullname())
                ,text(KONTROL.getOptionText() + "\n" + kontolcu.getFullname())
                ,text(IMZALAMA.getOptionText() + "\n" + imzaci.getFullname()))
                .onayla()
                .islemMesaji().basariliOlmali();

        login(imzaci);
        new ImzaBekleyenlerPage().openPage().searchTable().findRowAndSelect(text(konu));
        evrakOnizleme.evrakGecmisi.tabiAc()
                .kayitBulunmali(text("İmza bekliyor"))
                .bulunanKayittaKontrol(text(imzaci.getFullname()))
                .evrakPageButtons().evrakIadeEt(kontolcu, geriAlNotu)
                .islemMesaji().basariliOlmali();


        /*login(parafci);
        new ParafBekleyenlerPage().openPage().searchTable()
                .findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);*/
        login(kontolcu);
        kontrolBekleyenlerPage.openPage().searchTable()
                .findRowAndSelect(text(konu))
                .gonderenNotuTooltip(geriAlNotu);
    }
}
