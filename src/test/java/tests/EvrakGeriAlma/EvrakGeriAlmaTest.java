package tests.EvrakGeriAlma;

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
import pages.solMenuPages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static pages.pageData.alanlar.OnayKullaniciTipi.IMZALAMA;
import static pages.pageData.alanlar.OnayKullaniciTipi.PARAFLAMA;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 27.02.2018
 * Açıklama:
 */
public class EvrakGeriAlmaTest extends BaseTest {

    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1", "Altyapı ve Sistem Yönetim Uzmanı");
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
                .evrakGecmisiListesindeBulunur(text("İmza bekliyor"))
                .evrakGecmisiBulununaKayitKontrol(text(imzaci.getFullname()))
                .evrakPageButtons()
                .geriAl().geriAlGeriAl().islemMesaji().uyariOlmali("Zorunlu alanları doldurunuz")
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
                .evrakGecmisiListesindeBulunur(text("İmza bekliyor"))
                .evrakGecmisiBulununaKayitKontrol(text(imzaci2.getFullname()))
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
                .bilgiSecimTipiSec(BilgiSecimTipi.KULLANICI)
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
                .evrakGecmisiListesindeBulunur(text("İmza bekliyor"))
                .evrakGecmisiBulununaKayitKontrol(text(imzaci.getFullname()))
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
                .evrakGecmisiBulununaKayitKontrol(
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
                .evrakGecmisiListesindeSonHareketKontrol(text(imzaci.getFullname()), text("İmza bekliyor"))
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
}
