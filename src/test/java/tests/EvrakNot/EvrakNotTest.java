package tests.EvrakNot;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.solMenuPages.TaslakEvraklarPage;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KararYazisiOlusturPage;
import pages.ustMenuPages.OlurYazisiOlusturPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 13.12.2017
 * Açıklama:
 */
public class EvrakNotTest extends BaseTest {

    class EvrakNot {
        TextEditor editor = new TextEditor();

        SelenideElement notEkleDialog = $("div[id*='notEkleDialog']");
        BelgenetElement notTipi = comboBox("div[id*='notEkleDialog'] label[class~='ui-selectonemenu-label']");
        ElementsCollection olusturulanNotları = $$("div[id*='evrakNotlariTable']>div");


        @Step("Not Ekle butona basılır, not ekleme ekranı gelmeli")
        public EvrakNot notEkle() {
            editor.toolbarButton("Not Ekle", true);
            $("div[id*='notEkleDialog']").shouldBe(visible);
            return this;
        }

        @Step("Açıklama gir")
        public EvrakNot aciklamaGir(String text) {
            notEkleDialog.$("textarea").sendKeys(text);
            notEkleDialog.$("textarea").shouldHave(value(text));
            return this;
        }

        @Step("Açıklama karakter sayısı kontrolü")
        public EvrakNot aciklamaKarakterSayisiKontrolu(int maxLength) {

            int leftCount = getNumber($("span[id$='NotEkleDialogCounter']").text());

            SoftAssert sa = new SoftAssert();
            sa.assertEquals(leftCount, maxLength, "Max karakter sayısı ");

            String text = "";
            for (int i = 0; i < maxLength - (int) (Math.log10(maxLength) + 1); i++) {
                text += "0";
            }
            notEkleDialog.$("textarea").sendKeys(text + String.valueOf(maxLength));

            leftCount = getNumber($("span[id$='NotEkleDialogCounter']").text());
            sa.assertEquals(leftCount, 0, "Kalan karakter sayısı");

            notEkleDialog.$("textarea").shouldHave(value(String.valueOf(maxLength)));
            notEkleDialog.$("textarea").sendKeys("*");
            notEkleDialog.$("textarea").shouldNotHave(value("*"));

            sa.assertAll();

            notEkleDialog.$("textarea").clear();
            notEkleDialog.$("textarea").shouldBe(empty);

            return this;
        }

        public int getNumber(String text) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(text);
            Assert.assertTrue(m.find(), "\"" + text + "\" tekst içinde numara bulunamadı");
            int number = Integer.parseInt(m.group());
            return number;
        }

        @Step("Not Tipi seçenek değerleri konrolü")
        public EvrakNot notTipiSecenekDegerleriKontrolu(String... values) {
            notTipi.getComboBoxValues().shouldHave(texts(values));
            return this;
        }

        @Step("Not Tipi \"{value}\" seç")
        public EvrakNot notTipiSec(String value) {
            notTipi.selectComboBox(value);
            return this;
        }

        @Step("Kaydet tıkla")
        public EvrakNot kaydet() {
            notEkleDialog.$("button[type=submit]").click();
            return this;
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String aciklamaText, String... dateTime) {
            olusturulanNotları.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);

            String date = "", time = "";
            if (dateTime.length > 0) {
                String p = dateTime[0];
                p = p.replace("\n", "_");
                p = p.replace(" ", "_");
                date = p.split("_")[0];
                time = p.split("_")[1];
            }

            ElementsCollection notes = olusturulanNotları
                    .filterBy(text(olusturan))
                    .filterBy(text(aciklamaText));
            if (!date.isEmpty())
                notes.filterBy(text(date)).filterBy(text(time));

            return notes;
        }

        @Step("Editör tabında yeni not oluştur")
        public SelenideElement notOlustur(String olusturanAdSoyad, String notTipi, String aciklama, int... maxLength) {
            EvrakNot evrakNot = new EvrakNot();
            evrakNot.notEkle();

            if (maxLength.length > 0)
                evrakNot.aciklamaKarakterSayisiKontrolu(maxLength[0]);

            evrakNot.aciklamaGir(aciklama)
                    .notTipiSecenekDegerleriKontrolu("Seçiniz", "Genel", "Kişisel")
                    .notTipiSec(notTipi)
                    .kaydet();

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            return evrakNot.olusturulanNot(olusturanAdSoyad, aciklama, date + " " + time).shouldHaveSize(1).first();
        }

    }

    class UstYazi {

        SelenideElement notEkle = $("button[id$='kisiselNotEkleDataTableId:kisiselNotEkleId']");
        SelenideElement noteEkleDialog = $("div[id='evrakKisiselNotDialogFormId:evrakKisiselNotDialogId']");
        BelgenetElement noteTipi = comboBox("label[id='evrakKisiselNotDialogFormId:evrakNotTipi_label']");
        SelenideElement kaydet = $("button[id='evrakKisiselNotDialogFormId:evrakKisiselNotKaydet']");


        @Step("Üst yazıyı görüntüle")
        public UstYazi ustYaziGoruntule() {
            $("a[id$='ustYaziLinkButtonSag']").shouldBe(visible).click();
            return this;
        }

        @Step("Evrak Notlari tabı aç")
        public UstYazi evrakNotlariTabiAc() {
            $(By.linkText("Evrak Notları")).shouldBe(visible).click();
            return this;
        }

        @Step("Bilgiler tabında yeni not oluştur")
        public SelenideElement notOlustur(String olusturan, String notTipi, String aciklama, int... maxLength) {

            notEkle.sendKeys("\n");
            notEkle.click(1, 1);

            if (maxLength.length > 0)
                aciklamaKarakterSayisiKontrolu(maxLength[0]);

            noteEkleDialog.$("textarea").sendKeys(aciklama);
            noteEkleDialog.$("textarea").shouldHave(value(aciklama));
            noteTipi.getComboBoxValues().shouldHave(texts("Seçiniz", "Genel", "Kişisel"));
            noteTipi.selectComboBox(notTipi);
            kaydet.shouldBe(visible).click();

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            return olusturulanNot(olusturan, notTipi, aciklama, date + "_" + time).shouldHaveSize(1).first();
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText, String... dateTime) {
            $("tbody[id$='kisiselNotEkleDataTableId_data']").shouldHave(visible);
            ElementsCollection rows = $$("tbody[id$='kisiselNotEkleDataTableId_data'] > tr[data-ri][role=row]");
            rows.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);

            String date = "", time = "";
            if (dateTime.length > 0) {
                String p = dateTime[0];
                p = p.replace("\n", "_");
                p = p.replace(" ", "_");
                date = p.split("_")[0];
                time = p.split("_")[1];
            }

            ElementsCollection notes = rows
                    .filterBy(text(olusturan))
                    .filterBy(text(notTipi))
                    .filterBy(text(aciklamaText));
            if (!date.isEmpty())
                notes.filterBy(text(date)).filterBy(text(time));

            return notes;
        }

        @Step("Açıklama karakter sayısı kontrolü")
        public UstYazi aciklamaKarakterSayisiKontrolu(int maxCount) {
            SelenideElement counter = $("span[id='evrakKisiselNotDialogFormId:aciklamaCounter']");

            int leftCount = getNumber(counter.text());

            SoftAssert sa = new SoftAssert();
            sa.assertEquals(leftCount, maxCount, "Max karakter sayısı");

            String text = "";
            for (int i = 0; i < maxCount - (int) (Math.log10(maxCount) + 1); i++) {
                text += "0";
            }
            noteEkleDialog.$("textarea").sendKeys(text + String.valueOf(maxCount));

            leftCount = getNumber(counter.text());
            sa.assertEquals(leftCount, 0, "Kalan karakter sayısı");

            noteEkleDialog.$("textarea").shouldHave(value(String.valueOf(maxCount)));
            noteEkleDialog.$("textarea").sendKeys("*");
            noteEkleDialog.$("textarea").shouldNotHave(value("*"));

            sa.assertAll();

            noteEkleDialog.$("textarea").clear();
            noteEkleDialog.$("textarea").shouldBe(empty);

            return this;
        }

        public int getNumber(String text) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(text);
            Assert.assertTrue(m.find(), "\"" + text + "\" tekst içinde numara bulunamadı");
            int number = Integer.parseInt(m.group());
            return number;
        }
    }

    private String createTextWith(int length) {
        String text = "";
        for (int i = 0; i < length - 1; i++) {
            text += "0";
        }
        text += "!";
        return text;
    }

    @Test(description = "TC2093: Evrak Notları. Karar Yazısı Oluştur")
    public void tc2093() {

        String notTipi, aciklama, dateTime;
        SelenideElement note;

        KararYazisiOlusturPage page = new KararYazisiOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login();
        page.openPage().editorTabAc();

        notTipi = "Genel";
        aciklama = "Açıklama1";
        note = evrakNot.notOlustur("Optiim TEST", notTipi, aciklama, 400);
        dateTime = note.text().split("-")[1].trim();

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot("Optiim TEST", notTipi, aciklama, dateTime).shouldHaveSize(1);


        notTipi = "Kişisel";
        aciklama = "açıklama2";
        note = ustYazi.notOlustur("Optiim TEST", notTipi, aciklama, 500);
        dateTime = note.text().split("-")[1].trim();
        page.editorTabAc();
        evrakNot.olusturulanNot("Optiim TEST", aciklama, dateTime).shouldHaveSize(1);
    }

    @Test(description = "TC2091: Evrak Notları. Evrak Oluştur")
    public void tc2091() {
        int maxLength = 400;

        String notTipi1 = "Genel", aciklama1 = createTextWith(maxLength);
        String notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
        String notTipi3 = "Genel", aciklama3 = "Açıklama3";
        String notTipi4 = "Kişisel", aciklama4 = "Açıklama4";

        EvrakOlusturPage page = new EvrakOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login();
        page.openPage().editorTabAc();
        evrakNot.notOlustur("Optiim TEST", notTipi1, aciklama1, maxLength);
        evrakNot.notOlustur("Optiim TEST", notTipi2, aciklama2, maxLength);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule().evrakNotlariTabiAc();
        ustYazi.olusturulanNot("Optiim TEST", notTipi1, aciklama1).shouldHaveSize(1);
        ustYazi.olusturulanNot("Optiim TEST", notTipi2, aciklama2).shouldHaveSize(1);

        ustYazi.notOlustur("Optiim TEST", notTipi3, aciklama3, 500);
        ustYazi.notOlustur("Optiim TEST", notTipi4, aciklama4, 500);

        page.editorTabAc();
        evrakNot.olusturulanNot("Optiim TEST", aciklama1).shouldHaveSize(1);
        evrakNot.olusturulanNot("Optiim TEST", aciklama2).shouldHaveSize(1);
        evrakNot.olusturulanNot("Optiim TEST", aciklama3).shouldHaveSize(1);
        evrakNot.olusturulanNot("Optiim TEST", aciklama4).shouldHaveSize(1);
    }

    @Test(description = "TC2092: Evrak Notları. Olur/Takrir Yazısı Oluştur")
    public void tc2092() {
        String notTipi, aciklama;

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login();
        page.openPage().editorTabAc();

        notTipi = "Genel";
        aciklama = "Açıklama1";
        evrakNot.notOlustur("Optiim TEST", notTipi, aciklama, 400);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot("Optiim TEST", notTipi, aciklama).shouldHaveSize(1);


        notTipi = "Kişisel";
        aciklama = "açıklama2";
        ustYazi.notOlustur("Optiim TEST", notTipi, aciklama, 500);
        page.editorTabAc();
        evrakNot.olusturulanNot("Optiim TEST", aciklama).shouldHaveSize(1);
    }

    @Test(description = "TC2155: Evrak Notları")//, dependsOnMethods = {"tc2091"})
    public void tc2155() {
        String notTipi1 = "Genel", aciklama1 = "Açıklama1";
        String notTipi2 = "Kişisel", aciklama2 = "Açıklama2";
        String notTipi3 = "Genel", aciklama3 = "Açıklama3";
        String notTipi4 = "Kişisel", aciklama4 = "Açıklama4";

        EvrakOlusturPage page = new EvrakOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login();
        page.openPage().editorTabAc().getEditor().type("Editor Text");
        evrakNot.notOlustur("Optiim TEST", notTipi1, aciklama1);
        evrakNot.notOlustur("Optiim TEST", notTipi2, aciklama2);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule().evrakNotlariTabiAc();
        ustYazi.notOlustur("Optiim TEST", notTipi3, aciklama3);
        ustYazi.notOlustur("Optiim TEST", notTipi4, aciklama4);


        String konu = "TC2155_" + getSysDate();
        page.bilgilerTabiAc()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("ESK05")
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .kanunKapsamTipiNormalSec()
                .ivedikSec("Normal")
                .geregiSecimTipiSecByText("Birim")
                .bilgiSecimTipiSec("Kurum")
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec("Optiim TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiEkle("Optiim TEST7")
                .onayAkisiKullaniciTipiSec("Optiim TEST7", "İmzalama")
                .kullan()
                .onayAkisiTitleKontrol("Yeni akış")
                .onayAkisiDetailKontrol("Optiim TEST-Paraflama / Optiim TEST7-İmzalama");
        page.kaydet();
        $("#kaydetConfirmForm\\:kaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();
        $x("//form[@id='yeniGidenEvrakForm']/ancestor::div[starts-with(@id,'window') and contains(@id,'Dialog')]/div[contains(@class,'ui-dialog-titlebar')]/a[contains(@class,'ui-dialog-titlebar-close')]").click();
        $("#kapatKaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();

        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        taslakEvraklarPage.openPage();
        $("#mainInboxForm\\:inboxDataTable_data").shouldBe(visible);
        SelenideElement row = $x("//*[contains(text(),'" + konu + "')]/ancestor::tr[@data-ri and @role='row']");
        row.click();



        /*evrakOnizlemeNotlarDialogId
                evrakOnizlemeNotlariDatatableId
        evrakOnizlemeNotlariDatatableId_data

                evrakOnizlemeNotlariDatatableId_paginator_bottom
        span class="ui-paginator-next ui-state-default ui-corner-all"
        ui-state-disabled

                <td role="gridcell" style="background-color: white !important;  width: 80% !important; text-align: justify !important;">
                <td role="gridcell" style="background-color: #fefabc !important;width: 80% !important;text-align: justify !important;">

<img src="resources/tccb/tccbLogo.png" width="15%">

                <a href="#" class="ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all"><span class="ui-icon ui-icon-closethick"></span></a>

                row button[id$='detayGosterButton']*/
    }
}
