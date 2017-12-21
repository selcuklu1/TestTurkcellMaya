package tests.EvrakNot;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;
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
    User user1 = new User("user1", "123", "User1 TEST");
    User user2 = new User("user2", "123", "User2 TEST");
    String konu;

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

        String notTipi, aciklama, date, time;
        SelenideElement note;
        String noteText;

        KararYazisiOlusturPage page = new KararYazisiOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc();
        //------------------------------------
        notTipi = "Genel";
        aciklama = "Açıklama1";
        note = evrakNot.notOlustur(user1.getName(), notTipi, aciklama, 400);
        noteText = note.text();
        date = getDateFromText(noteText);
        time = getTimeFromText(noteText);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot(user1.getName(), notTipi, aciklama, date, time).shouldHaveSize(1);

        //------------------------------------
        notTipi = "Kişisel";
        aciklama = "açıklama2";
        note = ustYazi.notOlustur(user1.getName(), notTipi, aciklama, 500);
        noteText = note.text();
        date = getDateFromText(noteText);
        time = getTimeFromText(noteText);
        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getName(), aciklama, date, time).shouldHaveSize(1);
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

        login(user1);
        page.openPage().editorTabAc();
        evrakNot.notOlustur(user1.getName(), notTipi1, aciklama1, maxLength);
        evrakNot.notOlustur(user1.getName(), notTipi2, aciklama2, maxLength);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule().evrakNotlariTabiAc();
        ustYazi.olusturulanNot(user1.getName(), notTipi1, aciklama1).shouldHaveSize(1);
        ustYazi.olusturulanNot(user1.getName(), notTipi2, aciklama2).shouldHaveSize(1);

        ustYazi.notOlustur(user1.getName(), notTipi3, aciklama3, 500);
        ustYazi.notOlustur(user1.getName(), notTipi4, aciklama4, 500);

        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getName(), aciklama1).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getName(), aciklama2).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getName(), aciklama3).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getName(), aciklama4).shouldHaveSize(1);
    }

    @Test(description = "TC2092: Evrak Notları. Olur/Takrir Yazısı Oluştur")
    public void tc2092() {
        String notTipi, aciklama;

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc();

        notTipi = "Genel";
        aciklama = "Açıklama1";
        evrakNot.notOlustur(user1.getName(), notTipi, aciklama, 400);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot(user1.getName(), notTipi, aciklama).shouldHaveSize(1);


        notTipi = "Kişisel";
        aciklama = "açıklama2";
        ustYazi.notOlustur(user1.getName(), notTipi, aciklama, 500);
        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getName(), aciklama).shouldHaveSize(1);
    }


    EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
    String[][] newNotes = {{"Genel", "Açıklama1", "", ""}
            , {"Kişisel", "Açıklama2", "", ""}
            , {"Genel", "Açıklama3", "", ""}
            , {"Kişisel", "Açıklama4", "", ""}};

    @Step("Evrak Önizlemede notları kontrol")
    public EvrakNotTest notlariKontrolEt() {
        for (int i = newNotes.length - 1; i > -1; i--) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(newNotes[i][0])
                            , text(newNotes[i][1])
                            , text(newNotes[i][2])
                            , text(newNotes[i][3]));
            Assert.assertTrue(notlar.kurumLogosu().contains("tccbLogo.png"), "Kurum logosu");

            if (newNotes[i][0].equals("Kişisel"))
                Assert.assertTrue(notlar.zeminRengiBeyaz(), "Arka plan beyaz");
            else if (newNotes[i][0].equals("Genel"))
                Assert.assertTrue(notlar.zeminRengiSari(), "Arka plan sarı");
            else
                throw new RuntimeException(newNotes[i][0] + " not arka palan ne beyaz ne de sarı");

            if (i != 0)
                notlar.sonrakiNot();
        }
        return this;
    }

    @Test(description = "TC2155: Evrak Notları")//, dependsOnMethods = {"tc2091"})
    public void tc2155() {
        EvrakOlusturPage page = new EvrakOlusturPage();
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc().getEditor().type("Editor Text");

        for (String[] newNote : newNotes) {
            SelenideElement note = evrakNot.notOlustur(user1.getName(), newNote[0], newNote[1]);
            String t = note.text();
            newNote[2] = getDateFromText(t);
            newNote[3] = getTimeFromText(t);
        }


        //------------------------------------
        konu = "TC2155_" + getSysDate();
        evrakOlusturVeKaydet(page, konu);

        //------------------------------------
        taslakEvraklarPage.openPage();
        SelenideElement evrak = taslakEvraklarPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        SelenideElement icerikGoster = evrak.$(page.filter().icerikGoster());
        evrak.click();

        notlariKontrolEt();

        notlar.evrakNotlariDialoguKapat();

        ustYazi.evrakNotlariTabiAc();
        for (int i = 0; i < newNotes.length; i++) {
            SelenideElement n = ustYazi.olusturulanNot(newNotes[i][1]).shouldHaveSize(1).first();
            n.$(ustYazi.updateButton).shouldBe(visible);
            n.$(ustYazi.deleteButton).shouldBe(visible);
        }

        icerikGoster.click();
        for (int i = 0; i < newNotes.length; i++) {
            evrakNot.olusturulanNot(newNotes[i][1]).shouldHaveSize(1);
        }
    }

    @Test(description = "", enabled = false)//, dependsOnMethods = {"2155"})
    public void tc2160() throws Exception {
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();
        TaslakEvraklarPage page = new TaslakEvraklarPage();

        login(user1);
        page.openPage();

        page.filter().findRowsWith(text("Konu: TC2155_20171217230342")).first().click();

        SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();

        SelenideElement sImzaCheckbox = $("div[id='imzalaForm:imzalaRadio']");
        sImzaCheckbox.click();
        $("#imzalaForm\\:imzalaButton").click();
        page.islemMesaji().basariliOlmali();
        page.solMenu(SolMenuData.IslemYaptiklarim.Parafladiklarim);
        page.filter().findRowsWith(text("Konu: TC2155_20171217230342")).first().click();


        notlariKontrolEt();
    }


    /**
     * Editör tab
     */
    class EvrakNot {
        TextEditor editor = new TextEditor();

        SelenideElement notEkleDialog = $("div[id*='notEkleDialog']");
        BelgenetElement notTipi = comboBox("div[id*='notEkleDialog'] label[class~='ui-selectonemenu-label']");
        ElementsCollection olusturulanNotları = $$("div[id*='evrakNotlariTable']>div");


        @Step("Not Ekle butona basılır, not ekleme ekranı gelmeli")
        public EvrakNot notEkle() {
            editor.toolbarButton("Not Ekle", true);
            for (int i = 0; i < Configuration.timeout/1000; i++) {
                sleep(1000);
                if ($("div[id*='notEkleDialog']").is((visible)))
                    break;
                else
                    editor.toolbarButton("Not Ekle", true);
            }
            $("div[id*='notEkleDialog']").waitUntil(visible,1000);
            /*$("div[id*='notEkleDialog']").waitUntil(visible, Configuration.timeout);
            if ($("div[id*='notEkleDialog']").is(not(visible)))
                editor.toolbarButton("Not Ekle", true);
            $("div[id*='notEkleDialog']").shouldBe(visible);*/
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

        public ElementsCollection getCreatedNotes() {
            olusturulanNotları.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
            return olusturulanNotları;
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String aciklamaText) {
            ElementsCollection rows = getCreatedNotes();
            return rows.filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText) {
            ElementsCollection rows = getCreatedNotes();
            return rows
                    .filterBy(text(olusturan))
                    .filterBy(text(notTipi))
                    .filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String aciklamaText, String date, String time) {
            ElementsCollection rows = getCreatedNotes();
            return rows
                    .filterBy(text(olusturan))
                    .filterBy(text(aciklamaText))
                    .filterBy(text(date))
                    .filterBy(text(time));
        }

        @Step("Oluşturulan notu bul")
        private ElementsCollection olusturulanNot(String olusturan, String aciklamaText, String... dateTime) {
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
            return evrakNot.olusturulanNot(olusturanAdSoyad, aciklama, date, time).shouldHaveSize(1).first();
        }

    }


    /**
     * Bilgiler Tab Üst yazıyı görüntüle
     */
    class UstYazi {

        SelenideElement notEkle = $("button[id$='kisiselNotEkleDataTableId:kisiselNotEkleId']");
        SelenideElement noteEkleDialog = $("div[id='evrakKisiselNotDialogFormId:evrakKisiselNotDialogId']");
        BelgenetElement noteTipi = comboBox("label[id='evrakKisiselNotDialogFormId:evrakNotTipi_label']");
        SelenideElement kaydet = $("button[id='evrakKisiselNotDialogFormId:evrakKisiselNotKaydet']");
        public By updateButton = By.cssSelector("button span[class~='update-icon']");
        public By deleteButton = By.cssSelector("button span[class~='delete-icon']");

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
            return olusturulanNot(olusturan, notTipi, aciklama, date, time).shouldHaveSize(1).first();
        }

        public ElementsCollection getCreatedNotes() {
            $("tbody[id$='kisiselNotEkleDataTableId_data']").shouldHave(visible);
            ElementsCollection rows = $$("tbody[id$='kisiselNotEkleDataTableId_data'] > tr[data-ri][role=row]");
            rows.shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
            return rows;
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String aciklamaText) {
            ElementsCollection rows = getCreatedNotes();
            return rows.filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText) {
            ElementsCollection rows = getCreatedNotes();
            return rows
                    .filterBy(text(olusturan))
                    .filterBy(text(notTipi))
                    .filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText, String date, String time) {
            ElementsCollection rows = getCreatedNotes();
            return rows
                    .filterBy(text(olusturan))
                    .filterBy(text(notTipi))
                    .filterBy(text(aciklamaText))
                    .filterBy(text(date))
                    .filterBy(text(time));
        }

        @Step("Oluşturulan notu bul")
        private ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText, String... dateTime) {
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

            counter.should(visible);
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


    /**
     * Evrak Önizleme
     */
    class EvrakOnizleme {

        class Notlari {
            SelenideElement evrakNotlariDialog = $("#evrakOnizlemeNotlarDialogId");
            SelenideElement closeDialog = $("#evrakOnizlemeNotlarDialogId span[class~=ui-icon-closethick]");
            SelenideElement noteBody = $("#evrakOnizlemeNotlarDialogId td[role='gridcell'");
            SelenideElement note = $("#evrakOnizlemeNotlariDatatableId_data");
            SelenideElement deleteNote = $("#evrakOnizlemeNotlariDatatableId_data [class~='delete-icon']");
            SelenideElement next = $("#evrakOnizlemeNotlariDatatableId_paginator_bottom span[class~='ui-paginator-next']");

            @Step("Evrak Notları pencereyi kapat")
            public Notlari evrakNotlariDialoguKapat() {
                closeDialog.click();
                return this;
            }

            public SelenideElement getNote() {
                return note;
            }

            public boolean kisiselNot() {
                note.shouldBe(visible);
                return note.text().contains("Kişisel Not");
            }

            public boolean genelNot() {
                note.shouldBe(visible);
                return note.text().contains("Genel Not");
            }

            @Step("Zemin rengi beyaz")
            public boolean zeminRengiBeyaz() {
                String styleAtt = noteBody.attr("style");
                return styleAtt.contains("white") || styleAtt.contains("rgb(255, 255, 255)");
            }

            @Step("Zemin rengi satı")
            public boolean zeminRengiSari() {
                String styleAtt = noteBody.attr("style");
                return styleAtt.contains("rgb(254, 250, 188)") || styleAtt.contains("#fefabc");
            }

            @Step("Sonraki nota geç")
            public Notlari sonrakiNot() {
                next.click();
                return this;
            }

            //Kurum logosu img'nin src attribute
            @Step("Kurum logosu kotrol")
            public String kurumLogosu() {
                String src = "";
                if (note.$("img").exists())
                    src = note.$("img").attr("src");
                return src;
            }
        }


    }

    @Step("Evrak Oluştur")
    private void evrakOlusturVeKaydet(EvrakOlusturPage page, String konu) {
        page.bilgilerTabiAc()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec("B1K1")
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .kanunKapsamTipiNormalSec()
                .ivedikSec("Normal")
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiSec("Başbakanlık")
                .geregiSecimTipiSecByText("Birim")
                .geregiSec("AnaBirim1")
                .onayAkisiEkle()
//                .onayAkisiKullaniciTipiSec("Optiim TEST [Ağ (Network) Uzman Yardımcısı]", "Paraflama")
                .onayAkisiKullaniciTipiSec(user1.getName(), "Paraflama")
                .onayAkisiEkle(user2.getName())
                .onayAkisiKullaniciTipiSec(user2.getName(), "İmzalama")
                .kullan()
                .onayAkisiTitleKontrol("Yeni akış")
                .onayAkisiDetailKontrol(user1.getName() + "-Paraflama / " + user2.getName() + "-İmzalama");
        page.kaydet();
        $("#kaydetConfirmForm\\:kaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();
        $x("//form[@id='yeniGidenEvrakForm']/ancestor::div[starts-with(@id,'window') and contains(@id,'Dialog')]/div[contains(@class,'ui-dialog-titlebar')]/a[contains(@class,'ui-dialog-titlebar-close')]").click();
        $("#kapatKaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();
    }
}
