package tests.EvrakNot;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseTest;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.pageComponents.Filtreler;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.tabs.EditorTab;
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
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 13.12.2017
 * Açıklama:
 */
@Feature("Evrak Not")
public class EvrakNotTest extends BaseTest {

    User optiim = new User("optiim", "123", "Optiim TEST", "Optiim Birim", "Optiim TEST [Ağ (Network) Uzman Yardımcısı]");

    //    data.User user1 = new data.User("user1", "123", "User1 TEST", "AnaBirim1");
    User user1 = new User("user1", "123", "User1 TEST", "AnaBirim1");
    //User user2 = new User("ztekin", "123", "Zübeyde TEKİN");
    User user6 = new User("optiim", "123", "Optiim TEST");
    //    data.User user2 = new data.User("user2", "123", "User2 TEST", "AnaBirim1AltBirim1");
    User user2 = new User("user3", "123", "User3 TEST", "AnaBirim1");
    User user5 = new User("mbozdemir", "123", "Mehmet BOZDEMİR", "YAZILIM GELİŞTİRME");
    //    data.User user2 = new data.User("ztekin", "123", "Zübeyde TEKİN", "YAZILIM GELİŞTİRME");
    String konu;
    String kaldiralacakKlasor = "Diğer";
    EvrakNot evrakNot = new EvrakNot();
    EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();

    String[] notTipleri = {"Seçiniz", "Genel", "Kişisel"};

    String[][] newNotes = {{"Genel", "Açıklama1", "", ""}
            , {"Kişisel", "Açıklama2", "", ""}
            , {"Genel", "Açıklama3", "", ""}
            , {"Kişisel", "Açıklama4", "", ""}};


    private String createTextWith(int length) {
        String text = "";
        for (int i = 0; i < length - (int) (Math.log10(length) + 1); i++) {
            text += ".";
        }
        text += String.valueOf(length);
        /*String text = "";
        for (int i = 0; i < length - 1; i++) {
            text += ".";
        }
        text += "!";*/
        return text;
    }

    //Bitmedi
    @Test(enabled = false, description = "TS2026: Evrak Önizleme - Evrak Notları Post-it")
    public void TS2026() throws Exception {

        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
        UstYazi ustYazi = new UstYazi();
        String konu = "TS2026" + getSysDate();
        String[][] notes = {{"Genel", "Açıklama1", "", ""}
                , {"Kişisel", "Açıklama2", "", ""}
                , {"Genel", "Açıklama3", "", ""}};
        login(user1);
        evrakOlusturVeImzala(user1, konu, notes);
    }

    @Test(enabled = true, description = "TS2091: Not Oluşturma - Evrak Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2091() {
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
        evrakNot.notOlustur(user1.getFullname(), notTipi1, aciklama1, maxLength);
        evrakNot.notOlustur(user1.getFullname(), notTipi2, aciklama2, maxLength);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule().evrakNotlariTabiAc();
        ustYazi.olusturulanNot(user1.getFullname(), notTipi1, aciklama1).shouldHaveSize(1);
        ustYazi.olusturulanNot(user1.getFullname(), notTipi2, aciklama2).shouldHaveSize(1);

        ustYazi.notOlustur(user1.getFullname(), notTipi3, aciklama3, 500);
        ustYazi.notOlustur(user1.getFullname(), notTipi4, aciklama4, 500);

        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getFullname(), aciklama1).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getFullname(), aciklama2).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getFullname(), aciklama3).shouldHaveSize(1);
        evrakNot.olusturulanNot(user1.getFullname(), aciklama4).shouldHaveSize(1);
        logout();
    }

    @Test(enabled = true, description = "TS2092: Not Oluşturma - Olur/Takrir Yazısı Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2092() {
        String notTipi, aciklama;

        OlurYazisiOlusturPage page = new OlurYazisiOlusturPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc();

        notTipi = "Genel";
        aciklama = "Açıklama1";
        evrakNot.notOlustur(user1.getFullname(), notTipi, aciklama, 400);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot(user1.getFullname(), notTipi, aciklama).shouldHaveSize(1);


        notTipi = "Kişisel";
        aciklama = "açıklama2";
        ustYazi.notOlustur(user1.getFullname(), notTipi, aciklama, 500);
        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getFullname(), aciklama).shouldHaveSize(1);
        logout();
        //clearCookies();
    }

    @Test(enabled = true, description = "TS2093: Not Oluşturma - Karar Yazısı Oluşturma da Kişisel ve Genel Not oluşturma")
    public void TS2093() {

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
        note = evrakNot.notOlustur(user1.getFullname(), notTipi, aciklama, 400);
        noteText = note.text();
        date = getDateFromText(noteText);
        time = getTimeFromText(noteText);

        page.bilgilerTabiAc();
        ustYazi.ustYaziGoruntule()
                .evrakNotlariTabiAc()
                .olusturulanNot(user1.getFullname(), notTipi, aciklama, date, time).shouldHaveSize(1);

        //------------------------------------
        notTipi = "Kişisel";
        aciklama = "Açıklama2";
        note = ustYazi.notOlustur(user1.getFullname(), notTipi, aciklama, 500);
        noteText = note.text();
        date = getDateFromText(noteText);
        time = getTimeFromText(noteText);
        page.editorTabAc();
        evrakNot.olusturulanNot(user1.getFullname(), aciklama, date, time).shouldHaveSize(1);
        logout();
    }

    @Test(enabled = true, description = "TS2155: Not İzleme - Evrak Notunun Taslak evraklarda izlenmesi")
//, dependsOnMethods = {"TS2091"})
    public void TS2155() throws InterruptedException {
        EvrakOlusturPage page = new EvrakOlusturPage();
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        EvrakNot evrakNot = new EvrakNot();
        UstYazi ustYazi = new UstYazi();

        login(user1);
        page.openPage().editorTabAc().getEditor().type("Editor Text");
        page.editorTabAc();
        newNotes = evrakNot.notlariOlustur(newNotes);
        //takeScreenshot();

        //------------------------------------
        konu = "TS2155_" + getSysDate();
        System.out.println("Konu: " + konu);

        evrakOlusturVeKaydet(page, konu);

        //------------------------------------
        taslakEvraklarPage.openPage();
        SelenideElement evrak = evragiBul(konu);
        evrak.click();
        notlar.notlariKontolEtLogoVeRenk(newNotes, "tccbLogo.png");


        ustYazi.evrakNotlariTabiAc().notlariKontolEt(newNotes);
        //takeScreenshot();

        evrak.$(page.filter().icerikGoster()).click();
        evrakNot.notlariKontrolEt(newNotes);
        logout();
    }

    @Test(description = "TS2160: Not İzleme - Evrak Notunun Paraf bekleneler, Parafladıklarım, İmza Bekleyenler ve İmzaladıklarım ekranlarında izlenmesi"
            , dependsOnMethods = {"TS2155"}
            , enabled = true
    )
    public void TS2160() {
        UstYazi ustYazi = new UstYazi();
        TaslakEvraklarPage taslakEvraklarPage = new TaslakEvraklarPage();
        ParafladiklarimPage parafladiklarimPage = new ParafladiklarimPage();
        ImzaBekleyenlerPage imzaBekleyenlerPage = new ImzaBekleyenlerPage();

        login(user1);
        taslakEvraklarPage.openPage();
        System.out.println("Konu: " + konu);
        evragiBul(konu).click();

        taslakEvraklarPage.parafla().islemMesaji().basariliOlmali();

        parafladiklarimPage.openPage();
        SelenideElement evrak = evrak = evragiBul(konu);
        evrak.click();
        notlar.notlariKontolEtLogoVeRenk(newNotes, "tccbLogo.png");
        notlar.evrakNotlariDialoguKapat();

        ustYazi.evrakNotlariTabiAc().notlariKontolEt(newNotes);
        //takeScreenshot();

//////////////////////
        SelenideElement icerikGoster = evrak.$(imzaBekleyenlerPage.filter().icerikGoster());
        icerikGoster.click();
        $("button .evrakNot").click();
        ////td[starts-with(@class,'tabMenuContainer')]//*[normalize-space(text())='Evrak Notları']/ancestor::td[starts-with(@class,'tabMenuContainer')]//button
//        page.openPage().editorTabAc();
        ElementsCollection rows = $$("#inboxItemInfoForm\\:kisiselNotEkleDataTableId_data > tr");
        rows.shouldHaveSize(4);
        for (int i = 0; i < newNotes.length; i++) {
            rows.get(i).shouldHave(text(newNotes[i][1]));
        }
        //takeScreenshot();
///////////////////////

        logout();
        //clearCookies();
        login(user2);
        imzaBekleyenlerPage.openPage();
        evrak = evragiBul(konu);
        evrak.click();
        //Sadece Genel notları olmalı, kişisel olmamalı
        ArrayList<String[]> newNotesGenel = new ArrayList<String[]>();
        newNotesGenel.add(newNotes[2]);
        newNotesGenel.add(newNotes[0]);

        notlar.notlariKontolEt(newNotesGenel);
        //takeScreenshot();

        icerikGoster = evrak.$(imzaBekleyenlerPage.filter().icerikGoster());
        icerikGoster.click();
        evrakNot.getCreatedNotes().shouldHaveSize(2);
        evrakNot.notlariKontrolEt(newNotesGenel);

        newNotesGenel.add(0, new String[]{"Genel", "Açıklama5", "", ""});
        evrakNot.notOlustur(user2.getFullname(), newNotesGenel.get(0)[0], newNotesGenel.get(0)[1]);
        String t = new TextEditor().type(newNotesGenel.get(0)[1]).getText();
        Assert.assertTrue(t.contains(newNotesGenel.get(0)[1]), "Evrakım metin alanında " + newNotesGenel.get(0)[1] + " olmalı");
        imzaBekleyenlerPage.evrakPageButtons().evrakKaydet().islemMesaji().basariliOlmali();
        imzaBekleyenlerPage.evrakPageButtons().evrakIadeEt("İade notu").islemMesaji().basariliOlmali();

        logout();
        ////clearCookies();
        login(user1);
        ParafBekleyenlerPage parafBekleyenlerPage = new ParafBekleyenlerPage();
        parafBekleyenlerPage.openPage();
//        evrak = parafBekleyenlerPage.filter().findRowsWith(text(konu)).shouldHaveSize(1).first();
        evrak = evragiBul(konu);
        evrak.click();

        notlar.notlariKontolEt(newNotesGenel);

        ustYazi.evrakNotlariTabiAc();
        ustYazi.getCreatedNotes().shouldHaveSize(newNotesGenel.size());
        ustYazi.notlariVeButonlariKontolEt(newNotesGenel, newNotesGenel.get(0)[1]);

        //Delete note
        int lastIndex = newNotesGenel.size() - 1;
        ustYazi.notuSil(newNotesGenel, lastIndex);
        newNotesGenel.remove(lastIndex);

        //Güncelleme
        String yeniAciklama = "Güncellene açıklama";
        ustYazi.notuGuncelle(newNotesGenel, 1, yeniAciklama);

        //Parafla
        ustYazi.parafla().islemMesaji().basariliOlmali();

        logout();
        //clearCookies();
        login(user2);
        imzaBekleyenlerPage.openPage();
        evrak = evragiBul(konu);
        evrak.click();
        notlar.notlariKontolEt(newNotesGenel);
        imzaBekleyenlerPage.evrakImzala().islemMesaji().basariliOlmali();
    }

    @Test(enabled = true, description = "TS2162: Not İzleme - Evrak Notunun Postalanacak Evraklar ve Postananlar ekranlarında izlenmesi"
            , dependsOnMethods = {"TS2160"}
    )
    public void TS2162() throws Exception {
        PostalanacakEvraklarPage postalanacakEvraklarPage = new PostalanacakEvraklarPage();
        EvrakOnizleme.Notlari notlar = new EvrakOnizleme().new Notlari();
        UstYazi ustYazi = new UstYazi();
        String konu = "TS2162_" + getSysDate();
        System.out.println("Konu: " + konu);

        String[][] notes = {{"Genel", "Açıklama1", "", ""}, {"Kişisel", "Açıklama2", "", ""}, {"Genel", "Açıklama3", "", ""}};

        /*login(user6);
        evrakOlusturVeImzala(user6, konu, notes);*/
        login(user1);
        evrakOlusturVeImzala(user1, konu, notes);

        //clearCookies();
        login(user2);
        SelenideElement evrak = postalanacakEvraklarPage.openPage().filter()
                .findRowsWith(Condition.text(konu)).shouldHaveSize(1).first();
        evrak.click();

        String[][] genelNotes = {{"Genel", "Açıklama1", "", ""}, {"Genel", "Açıklama3", "", ""}};
        notlar.notlariKontolEtLogoVeRenk(genelNotes, "tccbLogo.png");

        postalanacakEvraklarPage.evrakPostala()
                .gidisSekli("E-Posta")
                .postalacanakEposta("test@test.com")
                .postalamaAciklama(konu);
        postala();
        postalanacakEvraklarPage.islemMesaji().basariliOlmali();

        PostalananlarPage postalananlarPage = new PostalananlarPage();
        evrak = postalananlarPage.openPage().filter()
                .findRowsWithToday(Condition.text(konu)).shouldHaveSize(1).first();
        evrak.click();
        notlar.notlariKontolEtLogoVeRenk(genelNotes, "tccbLogo.png");
    }

    @Step("Postala")
    public EvrakNotTest postala() {
        clickJs($("#mainPreviewForm\\:postalaButton_id"));
        $(byText("Belge elektronik imzalı değil! Evrakı postalamak üzeresiniz. Devam etmek istiyor musunuz?")).shouldBe(visible);
        $("#mainPreviewForm\\:postalaDogrulaDialogForm\\:evetButton_id").click();
        return this;
    }

    @Step("Evrak Oluştur")
    public void evrakOlusturVeImzala(User user, String konu, String[][] notes) throws InterruptedException {
        EvrakOlusturPage evrakOlusturPage = new EvrakOlusturPage();
        EvrakNot evrakNot = new EvrakNot();

        evrakOlusturPage
                .openPage()
                .bilgilerTabiAc()
                .konuKoduSec("010.10")
//                .konuKoduSec(user.getBirimAdi())
                .konuDoldur(konu)
                .kaldirilacakKlasorler("Diğer")
                .evrakTuruSec("Resmi Yazışma")
                .onayAkisiKullanicilariTemizle()
                .onayAkisiEkle()
                .onayAkisiKullaniciTipiSec(user.getFullname(), "İmzalama")
                .onayAkisiKullan();

        EvrakOlusturPage.EditorTab editorTab = evrakOlusturPage
                .editorTabAc()
                .editorIcerikDoldur("TS2162")
//                .editorEvrakGeregiSec("YAZILIM GELİ");
                .editorEvrakGeregiSec(user.getBirimAdi());

        for (String[] note : notes) {
            SelenideElement n = evrakNot.notOlustur(user.getFullname(), note[0], note[1]);
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

    @Step("Evrak Oluştur ve kaydet")
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
                .onayAkisiKullaniciTipiSec(user1.getFullname(), "Paraflama")
                .onayAkisiEkle(user2.getFullname())
                .onayAkisiKullaniciTipiSec(user2.getFullname(), "İmzalama")
                .kullan()
                .onayAkisiTitleKontrol("Yeni akış")
                .onayAkisiDetailKontrol(user1.getFullname() + "-Paraflama / " + user2.getFullname() + "-İmzalama");
//        page.kaydet();
        page.evrakPageButtons().evrakKaydet();
//        $("#kaydeTSonfirmForm\\:kaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();
        $x("//form[@id='yeniGidenEvrakForm']/ancestor::div[starts-with(@id,'window') and contains(@id,'Dialog')]/div[contains(@class,'ui-dialog-titlebar')]/a[contains(@class,'ui-dialog-titlebar-close')]").click();
        $("#kapatKaydetEvetButton").click();
        page.islemMesaji().basariliOlmali();
    }

    @Step("Evrak Önizlemede notları kontrol")
    public EvrakNotTest notlariKontrolEt(String[][] notes, String kurumLogo, boolean renk) {
        for (int i = notes.length - 1; i > -1; i--) {
            notlar.getNote()
                    .shouldBe(visible)
                    .shouldHave(text(notes[i][0])
                            , text(notes[i][1])
                            , text(notes[i][2])
                            , text(notes[i][3]));
            Assert.assertTrue(notlar.kurumLogosu().contains("tccbLogo.png"), "Kurum logosu olmalı");

            if (notes[i][0].equals("Kişisel"))
                Assert.assertTrue(notlar.zeminRengiBeyaz(), "Kişisel notun arka plan beyaz");
            else if (notes[i][0].equals("Genel"))
                Assert.assertTrue(notlar.zeminRengiSari(), "Genel notun arka plan sarı");
            else
                throw new RuntimeException(notes[i][0] + " not arka palan ne beyaz ne de sarı");

            if (i != 0)
                notlar.sonrakiNot();
        }
        return this;
    }

    @Step("Evrağı bul")
    public SelenideElement evragiBul(String konu) {
        SelenideElement evrak = new Filtreler().findRowsWith(Condition.text(konu)).shouldHaveSize(1).first();
        return evrak;
    }

    /**
     * Editör tab
     */
    class EvrakNot {
        TextEditor editor = new TextEditor();

        SelenideElement notEkleDialog = $("div[id*='notEkleDialog']");
        BelgenetElement notTipi = comboBox("div[id*='notEkleDialog'] label[class~='ui-selectonemenu-label']");
        ElementsCollection olusturulanNotları = $$("div[id*='evrakNotlariTable']>div");
        SelenideElement notesDiv = $("div[id*='evrakNotlariTable']");

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
            notEkleDialog.$("textarea").shouldBe(visible);
            notEkleDialog.$("textarea").clear();
            notEkleDialog.$("textarea").setValue(text);
            return this;
        }

        @Step("Açıklama karakter sayısı kontrolü")
        public EvrakNot aciklamaKarakterSayisiKontrolu(int maxLength) {

            int lefTSount = getNumber($("span[id$='NotEkleDialogCounter']").text());

            SoftAssert sa = new SoftAssert();
            sa.assertEquals(lefTSount, maxLength, "Max karakter sayısı ");

            String text = "";
            for (int i = 0; i < maxLength - (int) (Math.log10(maxLength) + 1); i++) {
                text += "0";
            }
            notEkleDialog.$("textarea").sendKeys(text + String.valueOf(maxLength));

            lefTSount = getNumber($("span[id$='NotEkleDialogCounter']").text());
            sa.assertEquals(lefTSount, 0, "Kalan karakter sayısı");

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
            $$("div[id*='evrakNotlariTable']>div").shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
            return $$("div[id*='evrakNotlariTable']>div");
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String aciklamaText) {
            ElementsCollection rows = getCreatedNotes();
            return rows.filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String notTipi, String aciklamaText) {
            notesDiv.shouldHave(text(aciklamaText));
            ElementsCollection rows = getCreatedNotes();
            return rows
                    .filterBy(text(olusturan))
                    .filterBy(text(notTipi))
                    .filterBy(text(aciklamaText));
        }

        @Step("Oluşturulan notu bul")
        public ElementsCollection olusturulanNot(String olusturan, String aciklamaText, String date, String time) {
            notesDiv.shouldBe(visible).shouldHave(text(aciklamaText));
            ElementsCollection rows = getCreatedNotes();
            for (int i = 0; i < 5; i++) {
                rows = getCreatedNotes().filterBy(text(olusturan)).filterBy(text(aciklamaText))
                        .filterBy(text(date)).filterBy(text(time));
                if (rows.size() > 0)
                    break;
                sleep(1000);
            }

            return rows;
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
            notEkle();

            if (maxLength.length > 0)
                aciklamaKarakterSayisiKontrolu(maxLength[0]);

            aciklamaGir(aciklama)
                    .notTipiSecenekDegerleriKontrolu("Seçiniz", "Genel", "Kişisel")
                    .notTipiSec(notTipi)
                    .kaydet();
            notEkleDialog.should(disappear);
            sleep(2000);
//            Supplier<byte[]> screenshot = () -> takeScreenshot();
//            Allure.addByteAttachmentAsync("Oluşturan not", "image/png", () -> takeScreenshot());
//            takeScreenshot();

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
//            String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            return olusturulanNot(olusturanAdSoyad, aciklama, date, time).shouldHaveSize(1).first().shouldBe(visible);
        }

        @Step("Notları kontrol et")
        public EvrakNot notlariKontrolEt(String[][] notes) {
            for (int i = 0; i < notes.length; i++) {
                olusturulanNot(notes[i][1]).shouldHaveSize(1);
            }
            return this;
        }

        @Step("Notları kontrol et")
        public EvrakNot notlariKontrolEt(ArrayList<String[]> notes) {
            for (int i = 0; i < notes.size(); i++) {
                olusturulanNot(notes.get(i)[1]).shouldHaveSize(1);
            }
            return this;
        }

        /**
         * @param notes [][4] 1.oluşturan 2.açıklama,3.tarih,4.saat
         * @return notes with full details
         */
        @Step("Notları oluştur")
        public String[][] notlariOlustur(String[][] notes) {
            for (String[] note : notes) {
                SelenideElement createdNote = notOlustur(user1.getFullname(), note[0], note[1]);
                String text = createdNote.text();
                note[2] = getDateFromText(text);
                note[3] = getTimeFromText(text);
                //Önemli koşarken 1 saniye fark oldu
                note[3] = note[3].substring(0, note[3].length() - 2);
            }
            return notes;
        }
    }

    /**
     * Bilgiler Tab Üst yazıyı görüntüle
     */
    class UstYazi extends MainPage {

        public By updateButton = By.cssSelector("button span[class~='update-icon']");
        public By deleteButton = By.cssSelector("button span[class~='delete-icon']");
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
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
//            String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
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

        @Step("Notları kontrol et")
        public UstYazi notlariKontolEt(String[][] notes) {
            for (int i = 0; i < newNotes.length; i++) {
                SelenideElement note = olusturulanNot(notes[i][1]).shouldHaveSize(1).first();
                note.$(updateButton).shouldBe(visible);
                note.$(deleteButton).shouldBe(visible);
            }
            return this;
        }

        @Step("Notları kontrol et")
        public UstYazi notlariVeButonlariKontolEt(ArrayList<String[]> notes, String butonlarOlmayacakNot) {
            for (String[] note : notes) {
                SelenideElement n = olusturulanNot(note[1]).shouldHaveSize(1).first();
                n.shouldBe(visible).shouldHave(text(note[0]), text(note[1]), text(note[2]), text(note[3]));
                if (note[1].equals(butonlarOlmayacakNot)) {
                    n.$(updateButton).shouldNotBe(exist);
                    n.$(deleteButton).shouldNotBe(exist);
                } else {
                    n.$(updateButton).shouldBe(visible);
                    n.$(deleteButton).shouldBe(visible);
                }
            }
            return this;
        }

        @Step("Notu sil")
        public UstYazi notuSil(ArrayList<String[]> notes, int index) {
            SelenideElement deleteNote = olusturulanNot(notes.get(index)[1]).shouldHaveSize(1).first();
            deleteNote.$(deleteButton).click();
            getCreatedNotes().shouldHaveSize(notes.size() - 1);
            for (String[] note : notes) {
                if (note[1].equals(notes.get(index)[1])) {
                    olusturulanNot(note[1]).shouldHaveSize(0);
                } else {
                    olusturulanNot(note[1]).shouldHaveSize(1).first().shouldBe(visible)
                            .shouldHave(text(note[0]), text(note[1]), text(note[2]), text(note[3]));
                }
            }
            return this;
        }

        @Step("")
        public UstYazi notuGuncelle(ArrayList<String[]> notes, int index, String yeniAciklama) {
            SelenideElement note = olusturulanNot(notes.get(index)[1]).shouldHaveSize(1).first();
            note.$(updateButton).shouldBe(visible).click();
            aciklamaAlaniDegitir(yeniAciklama);
            kaydet();
            notes.get(index)[1] = yeniAciklama;
            for (String[] n : notes) {
                olusturulanNot(n[1]).shouldHaveSize(1).first().shouldBe(visible)
                        .shouldHave(text(n[0]), text(n[1]), text(n[2]), text(n[3]));
            }
            return this;
        }
    }

    /**
     * Evrak Önizleme
     */
    class EvrakOnizleme {
        class Notlari {
            SelenideElement evrakNotlariDialog = $("#evrakOnizlemeNotlarDialogId");
            SelenideElement closeDialog = $x("//div[@id='evrakOnizlemeNotlarDialogId']//a[span[contains(@class,'ui-icon-closethick')]]");
            SelenideElement noteBody = $("#evrakOnizlemeNotlarDialogId td[role='gridcell'");
            SelenideElement note = $("#evrakOnizlemeNotlariDatatableId_data");
            SelenideElement deleteNote = $("#evrakOnizlemeNotlariDatatableId_data [class~='delete-icon']");
            SelenideElement next = $("#evrakOnizlemeNotlariDatatableId_paginator_bottom span[class~='ui-paginator-next']");

            @Step("Evrak Notları pencereyi kapat")
            public Notlari evrakNotlariDialoguKapat() {
//                closeDialog.click();
//                closeDialog.pressEnter();
                clickJs(closeDialog);
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

            @Step("Notlatı kontrol et")
            public Notlari notlariKontolEt(String[][] notes) {
                int i = 0;
                for (String[] note : notes) {
                    Allure.addAttachment(String.valueOf(++i), note.toString());

                    notlar.getNote()
                            .shouldBe(visible)
                            .shouldHave(text(note[0])
                                    , text(note[1])
                                    , text(note[2])
                                    , text(note[3]));
                    notlar.sonrakiNot();
                }
                notlar.getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
                notlar.evrakNotlariDialoguKapat();
                return this;
            }

            @Step("Notlatı kontrol et")
            public Notlari notlariKontolEt(ArrayList<String[]> notes) {
                int i = 0;
                for (String[] note : notes) {
                    Allure.addAttachment(String.valueOf(++i), note.toString());
                    getNote()
                            .shouldBe(visible)
                            .shouldHave(text(note[0])
                                    , text(note[1])
                                    , text(note[2])
                                    , text(note[3]));
                    sonrakiNot();
                }
                getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
                notlar.evrakNotlariDialoguKapat();
                return this;
            }

            @Step("Notlatı kontrol et")
            public Notlari notlariKontolEtLogoVeRenk(String[][] notes, String kurumLogo) {
                for (int i = notes.length - 1; i >= 0; i--) {
                    Allure.addAttachment(String.valueOf(i), note.toString());
                    getNote()
                            .shouldBe(visible)
                            .shouldHave(text(notes[i][0])
                                    , text(notes[i][1])
                                    , text(notes[i][2])
                                    , text(notes[i][3]));

                    Assert.assertTrue(kurumLogosu().contains(kurumLogo), kurumLogo + " kurum logosu olmalı");

                    if (notes[i][0].equals("Kişisel"))
                        Assert.assertTrue(notlar.zeminRengiBeyaz(), "Kişisel notun arka plan beyaz");
                    else if (notes[i][0].equals("Genel"))
                        Assert.assertTrue(notlar.zeminRengiSari(), "Genel notun arka plan sarı");
                    else
                        throw new RuntimeException(notes[i][0] + " not arka palan ne beyaz ne de sarı");

                    sonrakiNot();
                }
                /*for (String[] note : notes) {
                    Allure.addAttachment(String.valueOf(++i), note.toString());
                    getNote()
                            .shouldBe(visible)
                            .shouldHave(text(note[0])
                                    , text(note[1])
                                    , text(note[2])
                                    , text(note[3]));

                    Assert.assertTrue(kurumLogosu().contains(kurumLogo), kurumLogo + " kurum logosu olmalı");

                    if (note[0].equals("Kişisel"))
                        Assert.assertTrue(notlar.zeminRengiBeyaz(), "Kişisel notun arka plan beyaz");
                    else if (note[0].equals("Genel"))
                        Assert.assertTrue(notlar.zeminRengiSari(), "Genel notun arka plan sarı");
                    else
                        throw new RuntimeException(note[0] + " not arka palan ne beyaz ne de sarı");

                    sonrakiNot();
                }*/
                getSonrakiButton().shouldHave(cssClass("ui-state-disabled"));
                evrakNotlariDialoguKapat();
                return this;
            }
        }
    }
}
