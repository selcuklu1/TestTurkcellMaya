package tests.DagitimHitapGuncelleme;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import common.BaseTest;
import data.User;
import io.qameta.allure.Epic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.EvrakOnizleme;
import pages.pageComponents.PDFOnizleme;
import pages.pageData.alanlar.BilgiSecimTipi;
import pages.pageData.alanlar.GeregiSecimTipi;
import pages.pageData.alanlar.OnayKullaniciTipi;
import pages.solMenuPages.PostalanacakEvraklarPage;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;

/****************************************************
 * Tarih: 2018-02-05
 * Proje: Türksat Functional Test Automation
 * Class: "Dağıtım Hitap Güncelleme" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Dağıtım Hitap Güncelleme")
public class DagitimHitapGuncellemeTest extends BaseTest {

    User user = new User("user1", "123", "User1 TEST", "AnaBirim1");

    String adres1 = "GÖLBAŞI / ANKARA";
    String adres2 = "ATAŞEHİR / İSTANBUL";

    @Test(description = "TS2089: Dağıtım hitap güncellemede üst kurumun seçilmesi", enabled = true)
    public void TS2089() {

        String kurum = "Cumhurbaşkanlığı";
        String altKurum = "Bilişim Teknolojileri Başkanlığı";
        String evraktaGorunecekHitap = "CUMHURBAŞKANLIĞINA\n(Bilişim Teknolojileri Başkanlığı)";

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.KURUM)
                .geregiSec(altKurum)
                .geregiDagitimHitapDuzenlemeTiklanir(text(altKurum))
                .hitapSec(value(kurum), true)
                .evraktaGorunecekHitapKotrollu(textCaseSensitive(evraktaGorunecekHitap))
                .kaydet();

        page.bilgileriTab().secilenGeregiAlanKotrolu(textCaseSensitive(evraktaGorunecekHitap));

    }

    @DataProvider
    public Object[][] testData1() {
        return new Object[][]{
                {"Birim", "Optiim Birim"}
                , {"Kullanıcı", "Optiim TEST"}
                , {"Kurum", "Cumhurbaşkanlığı"}
                , {"Tüzel Kişi", "Türksat Optiim"}
        };
    }

    @Test(description = "TS2122: Özel hitap kullanma", enabled = true, dataProvider = "testData1")
    public void TS2122(String bilgiSecimTipi, String bilgi) {

        EvrakOlusturPage page;
        DagitimHitapDuzenle hitapDuzenle;
        int maxChar;
        String ozelHitap;

        login(user);

        page = new EvrakOlusturPage().openPage();
        hitapDuzenle = page.bilgileriTab()
                .bilgiSecimTipiSec(bilgiSecimTipi)
                .bilgiSec(bilgi)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(bilgi))
                .hitapAlaniBulunurKontorlu(exactValue(bilgi));
                /*.getCheckboxOfDagitimHitapInput("Hitap alanında seçilen " + bilgiSecimTipi + " geldiği görülür", value(bilgi))
                .shouldBe(visible);*/
        maxChar = Integer.parseInt(hitapDuzenle.ozelHitapSec(true).ozelHitapMaxKarakterSayisi());
        ozelHitap = createRandomTextWithLineBreaks(maxChar);
        hitapDuzenle
                .ozelHitapGirilir(ozelHitap)
                .kaydet();

        page.bilgileriTab().secilenBilgiAlanKotrolu(textCaseSensitive(ozelHitap));
        page.editorTab().openTab().getHitapAlani().shouldHave(textCaseSensitive(ozelHitap));
        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(ozelHitap));

    }

    @Test(description = "TS2123: Hitabına NE NA eki eklenmesi", enabled = true, dataProvider = "testData1")
    public void TS2123(String geregiSecimTipi, String geregi) {

        String ek, hitap, evraktaGorunecekHitap;

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();

        login(user);

        page.openPage().bilgileriTab()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(geregi));

        hitapDuzenle
                .getCheckboxOfDagitimHitapInput("Hitap alanında seçilen " + geregiSecimTipi + " geldiği görülür", value(geregi))
                .shouldBe(visible);

        ek = hitapDuzenle.getEkValue(value(geregi)).equalsIgnoreCase("e") ? "na" : "e";
        ek = geregi.substring(geregi.length() - 1).equals(geregi.substring(geregi.length() - 1).toUpperCase())
                ? ek.toUpperCase()
                : ek;

        if (geregiSecimTipi.equals("Kullanıcı")) {
            hitap = "Sayın " + geregi + ek;
            evraktaGorunecekHitap = hitap;
        } else {
            hitap = geregi + ek;
            evraktaGorunecekHitap = hitap.toUpperCase();
        }

        hitapDuzenle.ekGuncelle(geregi, ek, evraktaGorunecekHitap).kaydet();

        page.bilgileriTab().secilenGeregiAlanKotrolu(textCaseSensitive(hitap));

        page.editorTab().openTab().getHitapAlani().shouldHave(textCaseSensitive(evraktaGorunecekHitap));
        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(evraktaGorunecekHitap));
    }

    @Test(description = "TS2124: Hitapta adres kullanma", enabled = true, dataProvider = "testData1")
    public void TS2124(String bilgiSecimTipi, String bilgi) {

        String ek, hitap, evraktaGorunecekHitap;
        String adres = "GÖLBAŞI / ANKARA";

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();

        login(user);

        page.openPage().bilgileriTab()
                .bilgiSecimTipiSec(bilgiSecimTipi)
                .bilgiSec(bilgi)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(bilgi));

        hitapDuzenle
                .getCheckboxOfDagitimHitapInput("Hitap alanında seçilen " + bilgiSecimTipi + " geldiği görülür", value(bilgi))
                .shouldBe(visible);

        ek = hitapDuzenle.getEkValue(value(bilgi));

        if (bilgiSecimTipi.equals("Kullanıcı")) {
            hitap = "Sayın " + bilgi + ek;
            evraktaGorunecekHitap = hitap + "\n" + adres;
        } else {
            hitap = bilgi + ek;
            evraktaGorunecekHitap = hitap.toUpperCase() + "\n" + adres;
        }

        hitapDuzenle
                .adresGirilir(adres, evraktaGorunecekHitap)
                .kaydet();

        page.bilgileriTab().secilenBilgiAlanKotrolu(exactTextCaseSensitive(hitap));

        page.editorTab().openTab().getHitapAlani().shouldHave(exactTextCaseSensitive(evraktaGorunecekHitap));
        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(evraktaGorunecekHitap));

    }

    @DataProvider
    public Object[][] testData2() {
        return new Object[][]{
                {"Kullanıcı"
                        /*bilgi*/, "Mehmet BOZDEMİR", adres1, "Sayın Mehmet BOZDEMİR", "Sayın Mehmet BOZDEMİR"
                        /*gereği*/, "Zübeyde TEKİN", adres2, "Sayın Zübeyde TEKİN", "Sayın Zübeyde TEKİN\n" + adres2}

                , {"Kurum"
                /*bilgi*/, "Cumhurbaşkanlığı", adres1, "Cumhurbaşkanlığına", "Cumhurbaşkanlığına"
                /*gereği*/, "Maliye Bakanlığı", adres2, "Maliye Bakanlığına", "Maliye Bakanlığına\n" + adres2}

                , {"Tüzel Kişi"
                /*bilgi*/, "Türksat Optiim", adres1, "Türksat Optiime", "Türksat Optiime"
                /*gereği*/, "Optiim İş Çözümleri AŞ", adres2, "Optiim İş Çözümleri AŞNE", "Optiim İş Çözümleri AŞNE\n" + adres2}
        };
    }

    @Test(description = "TS2125: Dağıtımda adres kullanma", enabled = true, dataProvider = "testData2")
    public void TS2125(String tipi, String bilgi, String bilgiAdres, String bilgiHitap, String bilgiPDFGorunecekHitap
            , String geregi, String geregiAdres, String geregiHitap, String geregiPDFGorunecekHitap) {
        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();

        page.openPage().bilgileriTab()
                .bilgiSecimTipiSec(tipi)
                .bilgiSec(bilgi)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(bilgi));
        hitapDuzenle
                .adresGirilir(bilgiAdres)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();

        page.bilgileriTab()
                .geregiSecimTipiSec(tipi)
                .geregiSec(geregi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(geregi));
        hitapDuzenle
                .adresGirilir(geregiAdres)
                //.adresDagitimdaGorunsunSec(true)
                .kaydet();

        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1)
                .checkText(textCaseSensitive(geregiPDFGorunecekHitap))
                .checkText(textCaseSensitive(bilgiPDFGorunecekHitap))
                .checkNoText(text(bilgiAdres));

        /*
        SelenideElement bilgiLabelElement = $(Selectors.byText("Bilgi:"));
        SelenideElement bilgiElement = $(Selectors.byText(bilgiHitap));
        SelenideElement bilgiAdresElement = $(Selectors.byText(bilgiAdres));

        SelenideElement geregiLabelElement = $(Selectors.byText("Gereği:"));
        SelenideElement geregiElement = $(Selectors.byText(geregiHitap));
        SelenideElement geregiAdresElement = $(Selectors.byText(geregiAdres));

        Assert.assertEquals(bilgiLabelElement.getCoordinates().onPage().getX(), bilgiElement.getCoordinates().onPage().getX()
                ,"PDF önizlemede \"Bilgi:\" tekst ve \"" + bilgi + "\" left X coordinate");
        Assert.assertEquals(bilgiElement.getCoordinates().onPage().getY() - bilgiLabelElement.getCoordinates().onPage().getY(), 25
                ,"PDF önizlemede \"Bilgi:\" tekst ve \"" + bilgi + "\" arasında pixel sayısı");
        Assert.assertEquals(bilgiAdresElement.getCoordinates().onPage().getY() - bilgiElement.getCoordinates().onPage().getY(), 21
                ,"PDF önizlemede \""+bilgi+"\" tekst ve \"" + bilgiAdres + "\" arasında pixel sayısı");

        Assert.assertEquals(geregiLabelElement.getCoordinates().onPage().getX(), geregiElement.getCoordinates().onPage().getX()
                ,"PDF önizlemede \"Geregi:\" tekst ve \"" + geregi + "\" left X coordinate");
        Assert.assertEquals(geregiElement.getCoordinates().onPage().getY() - geregiLabelElement.getCoordinates().onPage().getY(), 25
                ,"PDF önizlemede \"Geregi:\" tekst ve \"" + geregi + "\" arasında pixel sayısı");
        Assert.assertEquals(geregiAdresElement.getCoordinates().onPage().getY() - geregiElement.getCoordinates().onPage().getY(), 21
                ,"PDF önizlemede \""+geregi+"\" tekst ve \"" + geregiAdres + "\" arasında pixel sayısı");
        */
    }

    @Test(description = "TS2126: Dağıtım metni kullanma ve hariç tutma", enabled = true)
    public void TS2126() {
        useFirefox();
        String konu = "TS2126_" + getSysDate();

        //Yaratılmış 2 dağıtım planı olmalı:
        //1. TS2126_1 ["Kullanıcı", "Optiim TEST"],["Kurum", "Cumhurbaşkanliği"]
        //2. TS2126_2 ["Kullanıcı", "Zübeyde TEKİN"], ["Birim", "AD MÜDÜRLÜĞÜ"]

        Map<String, String> dagitimPlaniMap1 = new LinkedHashMap<>();
        dagitimPlaniMap1.put("Kullanıcı", "Optiim TEST");
        dagitimPlaniMap1.put("Kurum", "Cumhurbaşkanliğina");
        //dagitimPlaniMap1.put("Birim", "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ");
        String dagitimPlani1 = "TS2126_1";
        String metni1 = "TS2126_1 Metni";
        /*String hitap1 = "Sayın " + dagitimPlaniMap1.get("Kullanıcı");
        String hitap2 = dagitimPlaniMap1.get("Kurum").toUpperCase() + "NA";*/

        Map<String, String> dagitimPlaniMap2 = new LinkedHashMap<>();
        dagitimPlaniMap2.put("Kullanıcı", "Zübeyde TEKİN");
        dagitimPlaniMap2.put("Birim", "AD MÜDÜRLÜĞÜ");
        String dagitimPlani2 = "TS2126_2";
        String metni2 = "TS2126_2 Metni";//defect oluduğu için kullanılmıyor
        String haricMetni = dagitimPlani2 + "E ("+dagitimPlaniMap2.get("Kullanıcı")+" Hariç)";
        //String haricMetni = dagitimPlani2 + "E ("+dagitimPlaniMap2.get("Birim").toUpperCase()+" Hariç)";
        String hitap2 = "Sayın " + dagitimPlaniMap1.get("Kullanıcı");

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiSec(dagitimPlani1)
                .geregiDagitimHitapDuzenlemeTiklanir(text(dagitimPlani1))
                .dagitimPlaniDetayListesiKontrolu(dagitimPlaniMap1)
                .dagitimMetniGirilir(metni1)
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(textCaseSensitive(metni1));

        page.bilgileriTab()
                .geregiSec(dagitimPlani2)
                .geregiDagitimHitapDuzenlemeTiklanir(text(dagitimPlani2))
                .dagitimPlaniDetayListesiKontrolu(dagitimPlaniMap2)
                .dagitimPlaniDetayCheckboxSecilir(dagitimPlaniMap2.get("Kullanıcı"), false)
                .dagitimMetniTekstKontrol(exactTextCaseSensitive(haricMetni))
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(textCaseSensitive(haricMetni));
        page.editorTab().openTab()
                .geregiListesindeAra(textCaseSensitive(metni1)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        page.editorTab()
                .geregiListesindeAra(textCaseSensitive(haricMetni)).shouldHaveSize(1).getFoundRow().shouldBe(visible);
        page.editorTab().getEditor().type("editör tekst");

        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1)
                .checkText(textCaseSensitive(metni1)
                        , textCaseSensitive(haricMetni));

        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(0);

        System.out.println("Konu: " + konu);
        page.bilgileriTab().openTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullan()
                .evrakPageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        new PostalanacakEvraklarPage().openPage()
                .searchTable().findRowAndSelect(text(konu));

        EvrakOnizleme.EvrakPostala evrakPostala = new EvrakOnizleme().evrakPostala();

        evrakPostala.postalanacakYerlerdeAra(text(metni1))
                .orjinaliniYazdir()
                .ustVerileriListesindeAra(text(konu))
                .ustVerilerOrjinaliniYazdir();

        new PDFOnizleme(1).checkText(0, textCaseSensitive(metni1), textCaseSensitive(haricMetni));
        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(0);
        evrakPostala.evrakDetayDialogClose();
//        evrakOnizleme.new EvrakDetaylari().close();

        evrakPostala
                .postalanacakYerlerdeAra(text(metni1))
                .yazdir()
                .ustVerileriListesindeAra(text(konu))
                .getUstVerilerYazdirButton("tıkla").click();
        new PDFOnizleme(1)
                .checkText(0, textCaseSensitive(metni1), textCaseSensitive(haricMetni))
                .checkText(1, textCaseSensitive(metni1), textCaseSensitive(haricMetni));
    }

    @Test(description = "TS2127: Hitabına NA NE eklenmesi", enabled = true)
    public void TS2127() {

        String dagitimPlani = "TS2126_1";
        String hitap = "TS2126_1";
        String evraktaGorunecekHitap = "DAĞITIM YERLERİNE";
        String ek = "NA";

        EvrakOlusturPage page;
        DagitimHitapDuzenle hitapDuzenle;

        login(user);

        page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.DAGITIM_PLANLARI)
                .geregiSec(dagitimPlani);
        hitapDuzenle = page.bilgileriTab()
                .geregiDagitimHitapDuzenlemeTiklanir(text(dagitimPlani))
                .hitapAlaniBulunurKontorlu(exactValue(hitap));

        String eskiEk = hitapDuzenle.getEkValue(value(hitap));
        ek = eskiEk.equalsIgnoreCase("e") ? "NA" : "E";
        //step(String.format("Hitapta \"%s\" ek \"%s\" ile değiştirildi", eskiEk, ek), "");
        hitapDuzenle.ekGuncelle(hitap, ek)
                .kaydet();

        page.editorTab().openTab()
                .geregiListesindeAra(text(hitap + ek)).shouldHaveSize(1);
        //page.editorTab().openTab().getHitapAlani().shouldHave(textCaseSensitive(evraktaGorunecekHitap));
        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(hitap + ek));
    }

    @Test(description = "TS2254: Gerçek kişi hitabına NE NA eklenmesi", enabled = true)
    public void TS2254() {
        String gercekKisi = "Ahmet ÇELİK";
        String hitap = "Sayın Ahmet ÇELİK";
        String ek = "E";
        String evraktaGorunecekHitap = "Sayın Ahmet ÇELİKE";

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.GERCEK_KISI)
                .geregiSec(gercekKisi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(gercekKisi))
                .ekGuncelle(gercekKisi, ek, evraktaGorunecekHitap)
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(evraktaGorunecekHitap));

        page.editorTab().openTab()
                .hitapAlanindaTekstBulunmali(textCaseSensitive(evraktaGorunecekHitap))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(evraktaGorunecekHitap));
    }

    @Test(description = "TS2255: Kullanıcı - Dağıtımda adres kullanma", enabled = true)
    public void TS2255() {
        String gercekKisi1 = "Ahmet ÇELİK";
        String hitap1 = "Sayın Ahmet ÇELİK";
        //String adres1 = "GÖLBAŞI / ANKARA";
        String evraktaGorunecekHitap1 = gercekKisi1 + "\n" + adres1;

        String gercekKisi2 = "Optiim OTOMASYON";
        String hitap2 = "Sayın Optiim OTOMASYON";
        //String adres2 = "ATAŞEHİR / İSTANBUL";
        String evraktaGorunecekHitap2 = gercekKisi2 + "\n" + adres2;

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.GERCEK_KISI)
                .geregiSec(gercekKisi1)
                .geregiDagitimHitapDuzenlemeTiklanir(text(gercekKisi1))
                .adresGirilir(adres1)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();
        //page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(evraktaGorunecekHitap1));

        page.bilgileriTab()
                .bilgiSecimTipiSec(BilgiSecimTipi.GERCEK_KISI)
                .bilgiSec(gercekKisi2)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(gercekKisi2))
                .adresGirilir(adres2)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();
        //page.bilgileriTab().secilenBilgiAlanKotrolu(exactTextCaseSensitive(evraktaGorunecekHitap2));

        page.editorTab().openTab()
                .dagitimPaneldeTekstBulunmali(textCaseSensitive(evraktaGorunecekHitap1), textCaseSensitive(evraktaGorunecekHitap2))
                .evrakPageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(evraktaGorunecekHitap1),textCaseSensitive(evraktaGorunecekHitap2));
    }

    @Test(description = "TS2130: Birim - Dağıtımda adres kullanma", enabled = true)
    public void TS2130() {
        String birim1 = "Optiim Birim";
        //String adres1 = "GÖLBAŞI / ANKARA";
        String hitap1 = birim1 + "e\n" + adres1;
        String evraktaGorunecekHitap1 = birim1.toUpperCase();

        String birim2 = "YAZILIM GELİŞTİRME DİREKTÖRLÜĞÜ";
        //String adres2 = "ATAŞEHİR / İSTANBUL";
        String hitap2 = birim2;// + "NE\n" + adres2;
        String evraktaGorunecekHitap2 = birim2.toUpperCase();

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(birim1)
                .geregiDagitimHitapDuzenlemeTiklanir(text(birim1))
                .adresGirilir(adres1)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();
        //page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(evraktaGorunecekHitap1));

        page.bilgileriTab()
                .bilgiSecimTipiSec(BilgiSecimTipi.BIRIM)
                .bilgiSec(birim2)
                .bilgiDagitimHitapDuzenlemeTiklanir(text(birim2))
                .adresGirilir(adres2)
                //.adresDagitimdaGorunsunSec(true)
                .kaydet();
        //page.bilgileriTab().secilenBilgiAlanKotrolu(exactTextCaseSensitive(evraktaGorunecekHitap2));

        page.editorTab().openTab()
                .dagitimPaneldeTekstBulunmali(textCaseSensitive(hitap1), textCaseSensitive(hitap2))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(hitap1),textCaseSensitive(hitap2));
    }

    @Test(description = "TS2256: Gerçek kişi - Hitapta adres kullanma", enabled = true)
    public void TS2256() {
        String gercekKisi = "Ahmet ÇELİK";
        String hitap = "Sayın " + gercekKisi;
        String adres = "GÖLBAŞI / ANKARA";
        String evraktaGorunecekHitap = hitap + "\n" + adres;

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.GERCEK_KISI)
                .geregiSec(gercekKisi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(gercekKisi))
                .adresGirilir(adres, evraktaGorunecekHitap)
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(hitap));

        page.editorTab().openTab()
                .hitapAlanindaTekstBulunmali(textCaseSensitive(evraktaGorunecekHitap))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(evraktaGorunecekHitap));

    }

    @Test(description = "TS2257: Gerçek kişi - özel hitap kullanma", enabled = true)
    public void TS2257() {
        String gercekKisi = "Ahmet ÇELİK";
        String ozelHitap = "Özel ÇELİK";

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.GERCEK_KISI)
                .geregiSec(gercekKisi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(gercekKisi))
                .ozelHitapSec(true)
                .ozelHitapGirilir(ozelHitap)
                .evraktaGorunecekHitapKotrollu(text(ozelHitap))
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(ozelHitap));

        page.editorTab().openTab()
                .hitapAlanindaTekstBulunmali(textCaseSensitive(ozelHitap))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(ozelHitap));

    }

    @Test(description = "TS2258: Gerçek kişi - Hitabın posta işlemlerinde kontrolü", enabled = true)
    public void TS2258() {
        String gercekKisi = "Ahmet ÇELİK";
        String ozelHitap = "Özel ÇELİK";
        String adres = "GÖLBAŞI / ANKARA";
        String evraktaGorunecekHitap = ozelHitap + "\n" + adres;

        String konu = "TS2258_" + getSysDate();

        useFirefox();

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.GERCEK_KISI)
                .geregiSec(gercekKisi)
                .geregiDagitimHitapDuzenlemeTiklanir(text(gercekKisi))
                .ozelHitapSec(true)
                .ozelHitapGirilir(ozelHitap)
                .adresGirilir(adres, evraktaGorunecekHitap)
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(ozelHitap));

        /*page.editorTab().openTab()
                .hitapAlanindaTekstBulunmali(textCaseSensitive(ozelHitap))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(ozelHitap));*/

        System.out.println("Konu: " + konu);
        page.bilgileriTab().openTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullan();

        page.editorTab().openTab().getEditor().type("editör tekst");
        page.pageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        new PostalanacakEvraklarPage().openPage()
                .searchTable().findRowAndSelect(text(konu));

        new EvrakOnizleme().evrakPostala()
                .postalanacakYerlerdeAra(text(ozelHitap))
                .yazdir()
                .ustVerileriListesindeAra(text(konu))
                .ustVerileriYazdir();

        new PDFOnizleme(1).checkText(0
                , textCaseSensitive(evraktaGorunecekHitap));

    }

    @Test(description = "TS2259: Birim - Hitabın posta işlemlerinde kontrolü", enabled = true)
    public void TS2259() {
        String birim = "Optiim Birim";
        String ozelHitap = "Özel Birim";
        String adres = "GÖLBAŞI / ANKARA";
        String evraktaGorunecekHitap = ozelHitap + "\n" + adres;

        String gercekKisi = "Ahmet ÇELİK";
        String konu = "TS2259_" + getSysDate();

        useFirefox();

        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .geregiSecimTipiSec(GeregiSecimTipi.BIRIM)
                .geregiSec(birim)
                .geregiDagitimHitapDuzenlemeTiklanir(text(birim))
                .ozelHitapSec(true)
                .ozelHitapGirilir(ozelHitap)
                .adresGirilir(adres, evraktaGorunecekHitap)
                .kaydet();
        page.bilgileriTab().secilenGeregiAlanKotrolu(exactTextCaseSensitive(ozelHitap));
        page.bilgileriTab()
                .bilgiSecimTipiSec(BilgiSecimTipi.GERCEK_KISI)
                .bilgiSec(gercekKisi);

        /*page.editorTab().openTab()
                .hitapAlanindaTekstBulunmali(textCaseSensitive(ozelHitap))
                .evrakPageButtons()
                .pdfOnizlemeTikla();
        new PDFOnizleme(1).checkText(textCaseSensitive(ozelHitap));*/

        System.out.println("Konu: " + konu);
        page.bilgileriTab().openTab()
                .konuKoduSec("010.01")
                .konuDoldur(konu)
                .kaldiralacakKlasorleriSec("Diğer")
                .onayAkisiTemizle()
                .anlikOnayAkisKullanicilariTemizle()
                .onayAkisiEkleButonaTikla()
                .anlikOnayAkisKullanicininTipiSec(user, OnayKullaniciTipi.IMZALAMA)
                .kullan();

        page.editorTab().openTab().getEditor().type("editör tekst");
        page.pageButtons().evrakImzala()
                .islemMesaji().basariliOlmali();

        new PostalanacakEvraklarPage().openPage()
                .searchTable().findRowAndSelect(text(konu));

        new EvrakOnizleme().evrakPostala()
                .postalanacakYerlerdeAra(text(ozelHitap))
                .yazdir()
                .ustVerileriListesindeAra(text(konu))
                .ustVerileriYazdir();

        new PDFOnizleme(1).checkText(0
                , textCaseSensitive(evraktaGorunecekHitap));

    }
}

