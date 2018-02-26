package tests.DagitimHitapGuncelleme;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.newPages.EvrakOlusturPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.PDFOnizleme;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.Selenide.$;

/****************************************************
 * Tarih: 2018-02-05
 * Proje: Türksat Functional Test Automation
 * Class: "Dağıtım Hitap Güncelleme" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/

@Epic("Dağıtım Hitap Güncelleme")
public class DagitimHitapGuncellemeTest extends BaseTest {

    User user = new User("user1", "123", "User1 TEST", "AnaBirim1");

    @DataProvider
    public Object[][] testData1() {
        return new Object[][]{
                {"Birim", "Optiim Birim"}
                , {"Kullanıcı", "Optiim TEST"}
                , {"Kurum", "Cumhurbaşkanlığı"}
                , {"Tüzel Kişi", "Türksat Optiim"}
        };
    }

    @Test(description = "TS2122: özel hitap kullanma", enabled = true, dataProvider = "testData1")
    public void TS2122(String bilgiSecimTipi, String bilgi) {

        EvrakOlusturPage page;
        DagitimHitapDuzenle hitapDuzenle;
        int maxChar;
        String ozelHitap;

        login(user);

        page = new EvrakOlusturPage().openPage();
        page.bilgileriTab()
                .bilgiSecimTipiSec(bilgiSecimTipi)
                .bilgiSec(bilgi)
                .secilenBilgiUpdateTiklanır(text(bilgi));

        hitapDuzenle = new DagitimHitapDuzenle();

        hitapDuzenle
                .getCheckboxOfDagitimHitapInput("Hitap alanında seçilen " + bilgiSecimTipi + " geldiği görülür", value(bilgi))
                .shouldBe(visible);

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

    @Test(description = "TS2123: hitabına NE NA eki eklenmesi", enabled = true, dataProvider = "testData1")
    public void TS2123(String geregiSecimTipi, String geregi) {

        String ek, hitap, evraktaGorunecekHitap;

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();

        login(user);

        page.openPage().bilgileriTab()
                .geregiSecimTipiSec(geregiSecimTipi)
                .geregiSec(geregi)
                .secilenGeregiUpdateTiklanır(text(geregi));

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

    @Test(description = "TS2124: hitapta adres kullanma", enabled = true, dataProvider = "testData1")
    public void TS2124(String bilgiSecimTipi, String bilgi) {

        String ek, hitap, evraktaGorunecekHitap;
        String adres = "My Adress";

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();

        login(user);

        page.openPage().bilgileriTab()
                .bilgiSecimTipiSec(bilgiSecimTipi)
                .bilgiSec(bilgi)
                .secilenBilgiUpdateTiklanır(text(bilgi));

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
                .adresHitaptaGorunsunSec(true)
                .adresSec(adres, evraktaGorunecekHitap)
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
                        /*bilgi*/, "Mehmet BOZDEMİR", "Mbozdemir Adresi", "Sayın Mehmet BOZDEMİR", "Sayın Mehmet BOZDEMİR\nMbozdemir Adresi"
                        /*gereği*/, "Zübeyde TEKİN", "Ztekin Adresi", "Sayın Zübeyde TEKİN", "Sayın Zübeyde TEKİN\nZtekin Adresi"}

                , {"Kurum"
                /*bilgi*/, "Cumhurbaşkanlığı", "Cumhurbaşkanlığı Adresi", "Cumhurbaşkanlığına", "Cumhurbaşkanlığına\nCumhurbaşkanlığı Adresi"
                /*gereği*/, "Maliye Bakanlığı", "Maliye Adresi", "Maliye Bakanlığına", "Maliye Bakanlığına\nMaliye Adresi"}

                , {"Tüzel Kişi"
                /*bilgi*/, "Türksat Optiim", "Türksat Adresi", "Türksat Optiime", "Türksat Optiime\nTürksat Adresi"
                /*gereği*/, "Optiim İş Çözümleri AŞ", "Optiim Adresi", "Optiim İş Çözümleri AŞNE", "Optiim İş Çözümleri AŞNE\nOptiim Adresi"}
        };
    }

    @Test(description = "TS2125: dağıtımda adres kullanma", enabled = true, dataProvider = "testData2")
    public void TS2125(String tipi, String bilgi, String bilgiAdres, String bilgiHitap, String bilgiPDFGorunecekHitap
            , String geregi, String geregiAdres, String geregiHitap, String geregiPDFGorunecekHitap) {
        login(user);

        EvrakOlusturPage page = new EvrakOlusturPage();
        DagitimHitapDuzenle hitapDuzenle = new DagitimHitapDuzenle();


        page.openPage().bilgileriTab()
                .bilgiSecimTipiSec(tipi)
                .bilgiSec(bilgi)
                .secilenBilgiUpdateTiklanır(text(bilgi));
        hitapDuzenle
                .adresSec(bilgiAdres)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();

        page.bilgileriTab()
                .geregiSecimTipiSec(tipi)
                .geregiSec(geregi)
                .secilenGeregiUpdateTiklanır(text(geregi));
        hitapDuzenle
                .adresSec(geregiAdres)
                .adresDagitimdaGorunsunSec(true)
                .kaydet();

        page.pageButtons().pdfOnizlemeTikla();
        new PDFOnizleme(1)
                .checkText(textCaseSensitive(bilgiPDFGorunecekHitap)
                    , textCaseSensitive(geregiPDFGorunecekHitap));

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

    }

    
}

