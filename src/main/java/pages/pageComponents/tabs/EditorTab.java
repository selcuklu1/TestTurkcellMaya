package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 10.01.2018
 * Açıklama:
 */
public class EditorTab extends MainPage {
    final static String tabName = "Editör";
    protected SelenideElement page;

    public EditorTab() {
        this.page = $("html");
    }

    public EditorTab(SelenideElement page) {
        this.page = page;
    }

    public TextEditor getEditor() {
        return new TextEditor(page);
    }

    @Step(tabName + " tabı açılır")
    public EditorTab openTab(boolean... clickIfOpen) {
        SelenideElement tab = page.$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");
        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        //System.out.println("[id$=allPanels_content]"+ page.$("[id$=allPanels_content]").getCssValue("class"));
        page.$("[id$=allPanels_content]").shouldBe(visible);
        page.$$("span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
        page.$$("span.cke_toolbar a[id*=cke]").filterBy(not(cssClass("cke_button_disabled"))).shouldHave(sizeGreaterThanOrEqual(0));
//        page.$$("#DOnayDivToolbar span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
        sleep(3000);
        return this;
    }

    @Step("Gereği alan aranır")
    public BelgenetElement getGeregiCombolov() {
        return comboLov(page, "[id$='geregiKurumLov:LovText']");
    }

    @Step("Gereği alan aranır")
    public BelgenetElement getBilgiCombolov() {
        return comboLov(page, "[id$='bilgiKurumLov:LovText']");
    }

    @Step("\"{fieldName}\" alanı doldur")
    public EditorTab select(String fieldName, String value) {
        BelgenetElement element;
        if (fieldName.equalsIgnoreCase("Gereği"))
            element = getGeregiCombolov();
        else if (fieldName.equalsIgnoreCase("Bilgi"))
            element = getBilgiCombolov();
        else
            throw new RuntimeException(fieldName + " kodda tanımlı değil");

        element.selectLov(value);
        return this;
    }

    @Step("\"{fieldName}\" alanı temizle")
    public EditorTab clear(String fieldName) {
        BelgenetElement element;
        if (fieldName.equalsIgnoreCase("Gereği"))
            element = getGeregiCombolov();
        else if (fieldName.equalsIgnoreCase("Bilgi"))
            element = getBilgiCombolov();
        else
            throw new RuntimeException(fieldName + " kodda tanımlı değil");

        element.clearAllSelectedItems();
        return this;
    }

    @Step("Şablonlar alanında \"{secim}\" seçilir")
    public EditorTab onTanimliIcerikSablonuKullan(String secim) {
        $("[id$='windowCevapEvrakForm:icerikSablonListPanel'] div[class='ui-selectonemenu ui-widget ui-state-default ui-corner-all ui-helper-clearfix'] select").selectOption(secim);
        ($$("[class='form-buttons'] button[id^='windowCevapEvrakForm'] span").filterBy(Condition.text("Uygula")).get(0)).parent().click();
        return this;
    }

    @Step("Ön tanımlı dialog bul")
    public SelenideElement getOnTanimliDialog() {
        return page.$("div[id*='icerikSablonDialog']");
    }

    @Step("Ön tanımlı şablonu bul ve seçilir")
    public EditorTab onTanimliSablonuSec(String sablonAdi) {
        //container.$("div[id*='icerikSablonDialog']").shouldBe(visible);
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(), "label[id$='_label']");
        cmbSablon.shouldBe(visible);
        cmbSablon.selectComboBox(sablonAdi);
        return this;
    }

    @Step("Ön tanımlı şablonun gelmediği görülür")
    public EditorTab onTanimliSablonuOlmadigi(String sablonAdi) {
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(), "label[id$='_label']");
        cmbSablon.shouldBe(visible);
        ElementsCollection s = cmbSablon.getComboBoxValues();
        s.filterBy(text(sablonAdi)).shouldHaveSize(0);
        Allure.addAttachment("Şablonlar", (s.size() > 0) ? s.texts().toString() : "");
        return this;
    }

    @Step("Editör teksti kontrol edilir")
    public EditorTab onTanimliSablonuOnizlemeKontrol(Condition... conditions) {
        SelenideElement element = getOnTanimliDialog().$("[id$='onizlemeField']").shouldBe(visible);
//        sleep(5000);
//        switchTo().frame($("[id='yeniGidenEvrakForm:onizlemeField'] iframe"));
        switchTo().frame($(element.$("iframe")));
        //String actualText = $("body").text();
        for (Condition condition : conditions) {
            $("body").shouldBe(visible).shouldHave(condition);
        }
        switchTo().defaultContent();
        return this;
    }

    @Step("Ön tanımlı şablonu uygulanır")
    public EditorTab onTanimliSablonuUygula() {
        getOnTanimliDialog().$("button[type=submit]").click();
        //.$x("descenbutton/span[text()='Uygula']/..").click();
        return this;
    }

    @Step("Editör alanı aranır")
    public SelenideElement getEditorArea() {
        return page.$("div[id$='allPanels']");
    }

    @Step("Hitap alanı aranır")
    public SelenideElement getHitapAlani() {
        return page.$("div[id$=hitapInplace]");
    }

    @Step("Hitapta alanda \"{hitapAramaKriteri}\" tesks bulunmalı")
    public EditorTab hitapAlanindaTekstBulunmali(Condition... hitapAramaKriteri){
        getHitapAlani().scrollIntoView(true).shouldHave(hitapAramaKriteri);
        return this;
    }

    @Step("Dağıtım Panel aranır")
    public SelenideElement getDagitimPanel() {
        return page.$("div[id$=editorDagitimPanel]");
    }

    @Step("Hitapta alanda \"{dagitimAramaKriteri}\" tesks bulunmalı")
    public EditorTab dagitimPaneldeTekstBulunmali(Condition... dagitimAramaKriteri){
        getDagitimPanel().scrollIntoView(true).shouldHave(dagitimAramaKriteri);
        return this;
    }

    public SearchTable getGeregiSearchTable() {
        return new SearchTable(page.$("div[id$=geregiLovTable]").scrollIntoView(true));
    }

    @Step("Gereği listesinde kayıt ara")
    public SearchTable geregiListesindeAra(Condition... aramaKriteri){
        return getGeregiSearchTable().findRows(aramaKriteri);
    }

    @Step("Not Ekle butona basılır")
    public EditorTab notEkleTikla() {
        getEditor().toolbarButton("Not Ekle", true);
        page.$("div[id*='notEkleDialog']").shouldBe(visible);
        return this;
    }


    public EvrakNot getEvrakNot() {
        return new EvrakNot();
    }

    public class EvrakNot {

        private SelenideElement note;
        private ElementsCollection notes;

        private List<EvrakNot> createdNotes = new ArrayList<EvrakNot>();

        public SelenideElement getNotesTable() {
            return page.$("div[id*='evrakNotlariTable']");
        }

        public ElementsCollection getNoteDivs() {
            return page.$$("div[id*='evrakNotlariTable']>div");
        }

        @Step("Not Ekle dialog bulunur")
        public SelenideElement getNotEkleDialog() {
            return page.$("div[id*='notEkleDialog']");
        }

        @Step("Not Ekle \"Açıklama\" alanı bulunur")
        public SelenideElement getAciklamaAlani() {
            return getNotEkleDialog().$("textarea");
        }

        @Step("Not Ekle \"Açıklama\" alanı doldurulur")
        public EvrakNot aciklamaAlaniDoldur(String aciklama) {
            getAciklamaAlani().setValue(aciklama);
            getAciklamaAlani().shouldHave(value(aciklama));
            return this;
        }

        @Step("Açıklama karakter sayısı maksimum {maxLength} olmalı")
        public EvrakNot aciklamaKarakterSayisiKontrolu(int maxLength) {
            SelenideElement counter = getNotEkleDialog().$("span[id*='DialogCounter']");

            counter.should(visible);
            int leftCount = getNumber(counter.text());

            SoftAssert sa = new SoftAssert();
            sa.assertEquals(leftCount, maxLength, "Max karakter sayısı");

            String text = "";
            for (int i = 0; i < maxLength - (int) (Math.log10(maxLength) + 1); i++) {
                text += ".";
            }
            aciklamaAlaniDoldur(text + String.valueOf(maxLength));

            leftCount = getNumber(counter.text());
            sa.assertEquals(leftCount, 0, "Kalan karakter sayısı");

            getAciklamaAlani().sendKeys("*");
            getAciklamaAlani().shouldNotHave(value("*"));

            sa.assertAll();

            getAciklamaAlani().clear();
            getAciklamaAlani().shouldBe(empty);

            return this;
        }

        @Step("Not Ekle \"Not Tipi\" alan bulunur")
        public BelgenetElement getNotTipi() {
//            return comboBox(getNotEkleDialog(),"label[id$='evrakNotTipiD1_label']");
            return comboBox(getNotEkleDialog(), "label[class~='ui-selectonemenu-label']");
        }

        @Step("Not Ekle \"Not Tipi\" alan seçilir")
        public EvrakNot notTipiSec(String value) {
            getNotTipi().selectComboBox(value);
            getNotTipi().shouldHave(text(value));
            return this;
        }

        @Step("Yeni Not tipi alanın değer kontrolleri")
        public EvrakNot notTipiAlanDegerKontrol(String... values) {
            getNotTipi().getComboBoxValues().shouldHave(texts(values));
            return this;
        }

        @Step("Not Ekle \"Kaydet\" butonu bulunur")
        public SelenideElement getKaydetButton() {
            return getNotEkleDialog().$x("descendant::button[.='Kaydet']");
        }

        @Step("Not Ekle \"Kaydet\" butona tıkla")
        public EvrakNot kaydet() {
            getKaydetButton().click();
            return this;
        }

        public int getNumber(String text) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(text);
            Assert.assertTrue(m.find(), "\"" + text + "\" tekst içinde numara bulunamadı");
            int number = Integer.parseInt(m.group());
            return number;
        }

        @Step("Yeni not oluşturulur, açıklama maksimum uzunluk ve not tipi değerleri kontrolleri")
        public EvrakNot notOlustur(String olusturan, String notTipi, String aciklama, int maxLength, String[] notTipiValues) {

            notEkleTikla();
            aciklamaKarakterSayisiKontrolu(maxLength);
            aciklamaAlaniDoldur(aciklama);

            notTipiAlanDegerKontrol(notTipiValues);
            notTipiSec(notTipi);
            kaydet();
            getNotEkleDialog().should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
           /* System.out.println("Time ofLocalizedTime: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            System.out.println("Time1: " + DateTimeFormatter.ofPattern("HH:mm", new Locale("tr", "TR")).format(LocalDateTime.now()));
            System.out.println("Time2: " + DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now()));
            System.out.println("Time3: " + System.currentTimeMillis());*/

            //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            notuBul(text(olusturan), text(aciklama), text(date), text(time));
            createdNotes.add(this);
            return this;
        }

        @Step("Yeni not oluşturulur")
        public EvrakNot notOlustur(String olusturan, String notTipi, String aciklama, String[] notTipiValues) {

            notEkleTikla();
            aciklamaAlaniDoldur(aciklama);

            notTipiAlanDegerKontrol(notTipiValues);
            notTipiSec(notTipi);
            kaydet();
            getNotEkleDialog().should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
            //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            notuBul(text(olusturan), text(aciklama), text(date), text(time));
            createdNotes.add(this);
            return this;
        }


        @Step("Yeni not oluşturulur")
        public EvrakNot notOlustur(String olusturan, String notTipi, String aciklama) {

            notEkleTikla();
            aciklamaAlaniDoldur(aciklama);

            notTipiSec(notTipi);
            kaydet();
            getNotEkleDialog().should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
            //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            notuBul(text(olusturan), text(aciklama), text(date), text(time));
            createdNotes.add(this);
            return this;
        }

        @Step("Notları ara")
        public EvrakNot notlariAra(Condition... aramaConditions) {
            getNoteDivs().shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
            notes = getNoteDivs();
            for (Condition condition : aramaConditions)
                notes = notes.filterBy(condition);
            return this;
        }

        @Step("Not bulunur")
        public EvrakNot notuBul(Condition... aramaConditions) {
            notlariAra(aramaConditions);
            note = notes.shouldHaveSize(1).first().shouldBe(visible);
            return this;
        }

        @Step("Not Sil butonu bulunur")
        public SelenideElement getNoteSilButton() {
            return $("button .noteClose");
        }

        @Step("Notu sil")
        public EvrakNot notuSil() {
            note.$("button .noteClose").click();
            note.shouldNotBe(exist);
            return this;
        }

        @Step("Postit şeklinde")
        public EvrakNot postitStyle() {
            //String styleChrome = "position: relative; background: rgb(254, 250, 188); padding: 5px; font-size: 10px; color: rgb(0, 0, 0); width: 200px; margin-bottom: 15px; box-shadow: rgb(51, 51, 51) 0px 4px 6px;";
            //String styleFirefox = "position:relative; background:rgb(254, 250, 188); padding: 5px;  font-size: 10px; color: #000; width: 200px; margin-bottom:15px; box-shadow: 0px 4px 6px #333; -moz-box-shadow: 0px 4px 6px #333; -webkit-box-shadow: 0px 4px 6px #333;"
            //note.shouldHave(attribute("style", style));
            Assert.assertTrue(note.getCssValue("position").equals("relative")
                    , "Style position shoul have value \"relative\" but actual value is \"" + note.getCssValue("position") + "\"");
//            Assert.assertTrue(note.getCssValue("background").equals("rgb(254, 250, 188) none repeat scroll 0% 0% / auto padding-box border-box")
//                    , "Style background should have value \"rgb(254, 250, 188) none repeat scroll 0% 0% / auto padding-box border-box\" but actual value is \"" +note.getCssValue("position")+"\"");
//            Assert.assertTrue(note.getCssValue("padding").equals("5px")
//                    , "Style padding should have value \"5px\" but actual value is \"" +note.getCssValue("padding")+"\"");
            Assert.assertTrue(note.getCssValue("font-size").equals("10px")
                    , "Style font-size should have value \"10px\" but actual value is \"" + note.getCssValue("font-size") + "\"");
            Assert.assertTrue(note.getCssValue("color").contains("0, 0, 0")
                    , "Style color should have value \"rgb(0, 0, 0, 1)\" but actual value is \"" + note.getCssValue("color") + "\"");
            Assert.assertTrue(note.getCssValue("width").equals("200px")
                    , "Style width should have value \"200px\" but actual value is \"" + note.getCssValue("width") + "\"");
            Assert.assertTrue(note.getCssValue("margin-bottom").equals("15px")
                    , "Style margin-bottom should have value \"15px\" but actual value is \"" + note.getCssValue("margin-bottom") + "\"");
            Assert.assertTrue(note.getCssValue("box-shadow").contains("rgb(51, 51, 51) 0px 4px 6px 0px")
                    , "Style box-shadow should have value \"rgb(51, 51, 51) 0px 4px 6px 0px\" but actual value is \"" + note.getCssValue("box-shadow") + "\"");
            return this;
        }

        public SelenideElement getNote() {
            return note;
        }

        public ElementsCollection getNotes() {
            return notes;
        }

        public List<EvrakNot> getCreatedNotes() {
            return createdNotes;
        }
    }

}
