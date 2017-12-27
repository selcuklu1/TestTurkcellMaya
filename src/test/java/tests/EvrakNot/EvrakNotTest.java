package tests.EvrakNot;

import com.codeborne.selenide.*;
import common.BaseTest;
import data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.IslemMesajlari;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.solMenuPages.*;
import pages.ustMenuPages.EvrakOlusturPage;
import pages.ustMenuPages.KararYazisiOlusturPage;
import pages.ustMenuPages.OlurYazisiOlusturPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 13.12.2017
 * Açıklama:
 */
public class EvrakNotTest extends BaseTest {
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    User user2 = new User("user2", "123", "User2 TEST", "AnaBirim1AltBirim1");
    User user3 = new User("user3", "123", "User3 TEST", "AnaBirim1");
    User user4 = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "YAZILIM GELİŞTİRME");
//    User user2 = new User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME");
    String konu;
    String kaldiralacakKlasor = "Diğer";
    EvrakNot evrakNot = new EvrakNot();

    private String createTextWith(int length) {
        String text = "";
        for (int i = 0; i < length - 1; i++) {
            text += "0";
        }
        text += "!";
        return text;
    }

    @Test(enabled = true, description = "TC2093: Evrak Notları. Karar Yazısı Oluştur")
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
        aciklama = "Açıklama2";
        note = ustYazi.notOlustur(user1.getName(), notTipi, aciklama, 500);
        noteText = note.text();
        date = getDateFromText(noteText);
        time = getTimeFromText(noteText);
        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getName(), aciklama, date, time).shouldHaveSize(1);
        logout();
    }

    @Test(enabled = true, description = "TC2091: Evrak Notları. Evrak Oluştur")
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
        logout();
    }

    @Test(enabled = true, description = "TC2092: Evrak Notları. Olur/Takrir Yazısı Oluştur")
    public void tc2092() throws Exception {
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
        logout();
        clearCookies();
    }


    EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
    String[][] newNotes = {{"Genel", "Açıklama1", "", ""}
            , {"Kişisel", "Açıklama2", "", ""}
            , {"Genel", "Açıklama3", "", ""}
            , {"Kişisel", "Açıklama4", "", ""}};

    @Test(enabled = true, description = "TC2155: Evrak Notları")//, dependsOnMethods = {"tc2091"})
    public void tc2155() throws InterruptedException {
        EvrakOlusturPage page = new EvrakOlusturPage();
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc().getEditor().type("Editor Text");
        page.editorTabAc();
        for (String[] newNote : newNotes) {
            SelenideElement note = evrakNot.notOlustur(user1.getName(), newNote[0], newNote[1]);
            String t = note.text();
            newNote[2] = getDateFromText(t);
            newNote[3] = getTimeFromText(t);
        }


        //------------------------------------
        konu = "TC2155_" + getSysDate();
        System.out.println("Konu: " + konu);
        evrakOlusturVeKaydet(page, konu);

        //------------------------------------
        taslakEvraklarPage.openPage();
        SelenideElement evrak = taslakEvraklarPage.filter().findRowsWith(text(konu))
                .shouldHaveSize(1).first();
        SelenideElement icerikGoster = evrak.$(page.filter().icerikGoster());
        evrak.click();
        notlariKontrolEt(newNotes);

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
        logout();
        Selenide.close();
    }

    @Test(enabled = true, description = "TC2160", dependsOnMethods = {"tc2155"})
    public void tc2160() throws Exception {
        UstYazi ustYazi = new UstYazi();
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        ParafladiklarimPage parafladiklarimPage = new ParafladiklarimPage();
        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage();

        login(user1);
        taslakEvraklarPage.openPage();
        System.out.println("Konu: " + konu);
        taslakEvraklarPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first().click();
        SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();
        SelenideElement sImzaCheckbox = $("div[id='imzalaForm:imzalaRadio']");
        sImzaCheckbox.click();
        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        $("#imzalaForm\\:imzalaButton").click();
        taslakEvraklarPage.islemMesaji().basariliOlmali();

        parafladiklarimPage.openPage();
        SelenideElement evrak = parafladiklarimPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        evrak.click();
        notlariKontrolEt(newNotes);
        notlar.evrakNotlariDialoguKapat();

        ustYazi.evrakNotlariTabiAc();
        for (int i = 0; i < newNotes.length; i++) {
            SelenideElement n = ustYazi.olusturulanNot(newNotes[i][1]).shouldHaveSize(1).first();
            n.$(ustYazi.updateButton).shouldBe(visible);
            n.$(ustYazi.deleteButton).shouldBe(visible);
        }
        SelenideElement icerikGoster = evrak.$(taslakEvraklarPage.filter().icerikGoster());
        icerikGoster.click();
        $("button .evrakNot").click();
        ////td[starts-with(@class,'tabMenuContainer')]//*[normalize-space(text())='Evrak Notları']/ancestor::td[starts-with(@class,'tabMenuContainer')]//button
//        page.openPage().editorTabAc();
        ElementsCollection rows = $$("#inboxItemInfoForm\\:kisiselNotEkleDataTableId_data > tr");
        rows.shouldHaveSize(4);
        for (int i = 0; i < newNotes.length; i++) {
            rows.get(i).shouldHave(text(newNotes[i][1]));
        }

        logout();
        clearCookies();
//        Selenide.close();
        login(user2);
        imzaBekleyenlerPage.openPage();
        evrak = imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        evrak.click();
        //Sadece Genel notları olmalı, kişisel olmamalı
        ArrayList<String[]> newNotesGenel = new ArrayList<String[]>();
        newNotesGenel.add(newNotes[2]);
        newNotesGenel.add(newNotes[0]);
        for (String[] n : newNotesGenel) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(n[0])
                            , text(n[1])
                            , text(n[2])
                            , text(n[3]));
//            if (notlar.getSonrakiButton().has(cssClass("ui-state-disabled")))
//                break;
            notlar.sonrakiNot();
        }
        notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
        notlar.evrakNotlariDialoguKapat();

        icerikGoster = evrak.$(imzaBekleyenlerPage.filter().icerikGoster());
        icerikGoster.click();
        evrakNot.getCreatedNotes().shouldHaveSize(2);
        evrakNot.olusturulanNot(newNotesGenel.get(0)[1]).shouldHaveSize(1);
        evrakNot.olusturulanNot(newNotesGenel.get(1)[1]).shouldHaveSize(1);
        newNotesGenel.add(0, new String[]{"Genel", "Açıklama5", "", ""});
        evrakNot.notOlustur(user2.getName(), newNotesGenel.get(0)[0], newNotesGenel.get(0)[1]);
        String t = new TextEditor().type(newNotesGenel.get(0)[1]).getText();
        Assert.assertTrue(t.contains(newNotesGenel.get(0)[1]), "Evrakım metin alanında " + newNotesGenel.get(0)[1] + " olmalı");
        $("button .kaydet").click();
        $("#kaydetConfirmForm\\:kaydetEvetButton").click();
        new IslemMesajlari().basariliOlmali();

        $("button .iadeEt").click();
        $("#inboxItemInfoForm\\:notTextArea_id").setValue("İade notu");
        $("#inboxItemInfoForm\\:iadeEtButton_id").click();
        new IslemMesajlari().basariliOlmali();

        logout();
        clearCookies();
//        Selenide.close();
        login(user1);
        ParafBekleyenlerPage parafBekleyenlerPage = new ParafBekleyenlerPage();
        parafBekleyenlerPage.openPage();
        evrak = parafBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        evrak.click();
        for (String[] n : newNotesGenel) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(n[0])
                            , text(n[1])
                            , text(n[2])
                            , text(n[3]));
            notlar.sonrakiNot();
        }
        notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
        notlar.evrakNotlariDialoguKapat();

        ustYazi.evrakNotlariTabiAc();
        ustYazi.getCreatedNotes().shouldHaveSize(newNotesGenel.size());
        for (String[] n1 : newNotesGenel) {
            SelenideElement n2 = ustYazi.olusturulanNot(n1[1]).shouldHaveSize(1).first();
            n2.shouldBe(visible)
                    .shouldHave(text(n1[0])
                            , text(n1[1])
                            , text(n1[2])
                            , text(n1[3]));
            if (n1[1].equals(newNotesGenel.get(0)[1])) {
                n2.$(ustYazi.updateButton).shouldNotBe(exist);
                n2.$(ustYazi.deleteButton).shouldNotBe(exist);
            } else {
                n2.$(ustYazi.updateButton).shouldBe(visible);
                n2.$(ustYazi.deleteButton).shouldBe(visible);
            }
        }

        //Delete note
        int lastIndex = newNotesGenel.size() - 1;
        SelenideElement n = ustYazi.olusturulanNot(newNotesGenel.get(lastIndex)[1]).shouldHaveSize(1).first();
        n.$(ustYazi.deleteButton).click();
        ustYazi.getCreatedNotes().shouldHaveSize(newNotesGenel.size() - 1);
        for (String[] n1 : newNotesGenel) {

            if (n1[1].equals(newNotesGenel.get(lastIndex)[1])) {
                ustYazi.olusturulanNot(n1[1]).shouldHaveSize(0);
            } else {
                SelenideElement n2 = ustYazi.olusturulanNot(n1[1]).shouldHaveSize(1).first();
                n2.shouldBe(visible)
                        .shouldHave(text(n1[0])
                                , text(n1[1])
                                , text(n1[2])
                                , text(n1[3]));
            }
        }
        newNotesGenel.remove(lastIndex);

        //Güncelleme
        n = ustYazi.olusturulanNot(newNotesGenel.get(1)[1]).shouldHaveSize(1).first();
        n.$(ustYazi.updateButton).shouldBe(visible).click();
        ustYazi.aciklamaAlaniDegitir("Değiştirilen açıklama");
        ustYazi.kaydet();
        newNotesGenel.get(1)[1] = "Değiştirilen açıklama";
        for (String[] n1 : newNotesGenel) {
            SelenideElement n2 = ustYazi.olusturulanNot(n1[1]).shouldHaveSize(1).first();
            n2.shouldBe(visible)
                    .shouldHave(text(n1[0])
                            , text(n1[1])
                            , text(n1[2])
                            , text(n1[3]));
        }


        //Parafla
        paraflaButon.click();
        sImzaCheckbox.click();
        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        $("#imzalaForm\\:imzalaButton").click();
        new IslemMesajlari().basariliOlmali();

        //
        Selenide.close();
        login(user2);
        imzaBekleyenlerPage.openPage();
        evrak = imzaBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        evrak.click();
        for (String[] n1 : newNotesGenel) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(n1[0])
                            , text(n1[1])
                            , text(n1[2])
                            , text(n1[3]));
            notlar.sonrakiNot();
        }
        notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
        notlar.evrakNotlariDialoguKapat();
        $x("//*[text()='İmzala']/ancestor::tbody[1]//button").click();
        sImzaCheckbox.click();
        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        $("#imzalaForm\\:sayisalImzaConfirmDialogOpener").click();

        $$("#imzalaForm\\:sayisalImzaConfirmForm\\:sayisalImzaEvetButton")
                .shouldHave(sizeGreaterThan(0))
                .filterBy(visible)
                .last()
                .click();
        new IslemMesajlari().basariliOlmali();
        logout();
        clearCookies();
    }

    @Test(enabled = true, description = "Not İzleme - Evrak Notunun Postalanacak Evraklar ve Postananlar ekranlarında izlenmesi")
    public void tc2162() throws Exception {
        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
        UstYazi ustYazi = new UstYazi();
        String konu = "TC2162_" + getSysDate();

        String[][] notes = {{"Genel", "Açıklama1", "", ""}
                , {"Kişisel", "Açıklama2", "", ""}
                , {"Genel", "Açıklama3", "", ""}};

        evrakOlusturma(konu, notes);

//        Selenide.close();
        clearCookies();
        login(user3);
        SelenideElement evrak = postalanacakEvraklarPage.openPage().filter()
                .findRowsWith(Condition.text(konu)).shouldHaveSize(1).first();
        evrak.click();

        String[][] genelNotes = {{"Genel", "Açıklama1", "", ""}
                , {"Genel", "Açıklama3", "", ""}};
        notlariKontrolEt(genelNotes);
        notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
        notlar.evrakNotlariDialoguKapat();

        /*ustYazi.evrakNotlariTabiAc();
        ustYazi.getCreatedNotes().shouldHaveSize(genelNotes.length);
        for (String[] n1 : genelNotes) {
            SelenideElement n2 = ustYazi.olusturulanNot(n1[1]).shouldHaveSize(1).first();
            n2.shouldBe(visible)
                    .shouldHave(text(n1[0])
                            , text(n1[1])
                            , text(n1[2])
                            , text(n1[3]));
            n2.$(ustYazi.updateButton).shouldNotBe(exist);
            n2.$(ustYazi.deleteButton).shouldNotBe(exist);
        }*/
        /*SelenideElement icerikGoster = evrak.$(postalanacakEvraklarPage.filter().icerikGoster());
        icerikGoster.click();
        $("button .evrakNot").click();
        ElementsCollection rows = $$("#inboxItemInfoForm\\:kisiselNotEkleDataTableId_data > tr");
        rows.shouldHaveSize(2);
        for (int i = 0; i < genelNotes.length; i++) {
            rows.get(i).shouldHave(text(genelNotes[i][1]));
            rows.get(i).$("button  .update-icon").shouldNotBe(exist);
            rows.get(i).$("button  .delete-icon").shouldNotBe(exist);
        }*/

        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama(konu);
        clickJs($("#mainPreviewForm\\:postalaButton_id"));
        $(byText("Belge elektronik imzalı değil! Evrakı postalamak üzeresiniz. Devam etmek istiyor musunuz?")).shouldBe(visible);
        $("#mainPreviewForm\\:postalaDogrulaDialogForm\\:evetButton_id").click();
        postalanacakEvraklarPage.islemMesaji().basariliOlmali();

        PostalananlarPage postalananlarPage = new PostalananlarPage();
        evrak = postalananlarPage.openPage().filter()
                .findRowsWith(Condition.text(konu)).shouldHaveSize(1).first();
        evrak.click();
        notlariKontrolEt(genelNotes);
        notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
        notlar.evrakNotlariDialoguKapat();
        clearCookies();
    }

    @Test(enabled = false, description = "TC2026: Evrak Önizleme - Evrak Notları Post-it")
    public void tc2026() throws Exception {

        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
        UstYazi ustYazi = new UstYazi();
        String konu = "TC2026" + getSysDate();
        String[][] notes = {{"Genel", "Açıklama1", "", ""}
                , {"Kişisel", "Açıklama2", "", ""}
                , {"Genel", "Açıklama3", "", ""}};
        evrakOlusturma(konu, notes);


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
//            sleep(5000);
            editor.toolbarButton("Not Ekle", true);
//            for (int i = 0; i < Configuration.timeout / 1000; i++) {
//                sleep(1000);
//                if ($("div[id*='notEkleDialog']").is((visible)))
//                    break;
//                else
//                    editor.toolbarButton("Not Ekle", true);
//            }
//            $("div[id*='notEkleDialog']").waitUntil(visible, 1000);
            /*$("div[id*='notEkleDialog']").waitUntil(visible, Configuration.timeout);
            if ($("div[id*='notEkleDialog']").is(not(visible)))
                editor.toolbarButton("Not Ekle", true);*/
            $("div[id*='notEkleDialog']").shouldBe(visible);
            return this;
        }

        @Step("Açıklama gir")
        public EvrakNot aciklamaGir(String text) {
            notEkleDialog.$("textarea").shouldBe(visible).setValue(text).shouldHave(value(text));
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
//            EvrakNot evrakNot = new EvrakNot();
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

        @Step("Açıklama alanı değiştir")
        public UstYazi aciklamaAlaniDegitir(String aciklama) {
            noteEkleDialog.$("textarea").setValue(aciklama);
            noteEkleDialog.$("textarea").shouldHave(value(aciklama));
            return this;
        }

        @Step("Kaydet tıkla")
        public UstYazi kaydet() {
            kaydet.click();
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
            kaydet.click();
            noteEkleDialog.should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            return olusturulanNot(olusturan, notTipi, aciklama, date, time).shouldHaveSize(1).first();
        }

        public ElementsCollection getCreatedNotes() {
            $("tbody[id$='kisiselNotEkleDataTableId_data']").shouldHave(visible);
            $("tbody[id$='kisiselNotEkleDataTableId_data'] > tr[data-ri][role=row]").shouldBe(visible);
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
//            sleep(3000);
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

            public SelenideElement getSonrakiButton() {
                return next;
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
    public void evrakOlusturma(String konu, String[][] notes) throws InterruptedException {
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        login(user1);
        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("YAZILIM GEL")
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(user1.getName(), "İmzalama")
                .onayAkisiKullan();

        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TC2162")
                .editorEvrakGeregiSec("YAZILIM GELİ");

        for (String[] note : notes) {
            SelenideElement n = evrakNot.notOlustur(user1.getName(), note[0], note[1]);
            String t = n.text();
            note[2] = getDateFromText(t);
            note[3] = getTimeFromText(t);
        }
        editorTab.imzala()
                .popupSImzalaIslemleri().islemMesaji().basariliOlmali();


        /*postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama("Test")
                .postalanacakEvrakYaz()
                .popupPostalanacakEvrakYazdir()
                .popupPostaYazdirmaKapat()
                .postalanacakEvrakOrjYaz()
                .gramajDoldur("111111")
                .hesapla()
                .evrakPostala();*/
    }

    @Step("Evrak Oluştur")
    private void evrakOlusturVeKaydet(EvrakOlusturPage page, String konu) throws InterruptedException {
        page.bilgilerTabiAc()
                .konuKoduSec("310.04")
                .konuDoldur(konu)
                .kaldiralacakKlasorlerSec(kaldiralacakKlasor)
                .evrakTuruSec("Resmi Yazışma")
                .evrakDiliSec("Türkçe")
                .gizlilikDerecesiSec("Normal")
                .kanunKapsamTipiNormalSec()
                .ivedilikSec("Normal")
                .bilgiSecimTipiSecByText("Kurum")
                .bilgiSec("Başbakanlık")
                .geregiSecimTipiSecByText("Birim")
                .geregiSec(user1.getBirimAdi())
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

    @Step("Evrak Önizlemede notları kontrol")
    public EvrakNotTest notlariKontrolEt(String[][] notes) {
        for (int i = notes.length - 1; i > -1; i--) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(notes[i][0])
                            , text(notes[i][1])
                            , text(notes[i][2])
                            , text(notes[i][3]));
            Assert.assertTrue(notlar.kurumLogosu().contains("tccbLogo.png"), "Kurum logosu");

            if (notes[i][0].equals("Kişisel"))
                Assert.assertTrue(notlar.zeminRengiBeyaz(), "Arka plan beyaz");
            else if (notes[i][0].equals("Genel"))
                Assert.assertTrue(notlar.zeminRengiSari(), "Arka plan sarı");
            else
                throw new RuntimeException(notes[i][0] + " not arka palan ne beyaz ne de sarı");

            if (i != 0)
                notlar.sonrakiNot();
        }
        return this;
    }

}
