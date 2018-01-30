package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.TextEditor;
import pages.pageComponents.UstYazi;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.ustMenuPages.EvrakOlusturPage;

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
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

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

        page.$("[id$=allPanels_content]").shouldBe(visible);
        page.$$("span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
//        page.$$("#DOnayDivToolbar span.cke_toolbar a[id*=cke]").shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Şablonlar alanında \"{secim}\" seçilir")
    public EditorTab onTanimliIcerikSablonuKullan(String secim) {
        $("[id$='windowCevapEvrakForm:icerikSablonListPanel'] div[class='ui-selectonemenu ui-widget ui-state-default ui-corner-all ui-helper-clearfix'] select").selectOption(secim);
        ($$("[class='form-buttons'] button[id^='windowCevapEvrakForm'] span").filterBy(Condition.text("Uygula")).get(0)).parent().click();
        return this;
    }

    @Step("Ön tanımlı dialog bul")
    public SelenideElement getOnTanimliDialog(){
        return page.$("div[id*='icerikSablonDialog']");
    }

    @Step("Ön tanımlı şablonu bul ve seçilir")
    public EditorTab onTanimliSablonuSec(String sablonAdi) {
        //container.$("div[id*='icerikSablonDialog']").shouldBe(visible);
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(),"label[id$='_label']");
        cmbSablon.shouldBe(visible);
        cmbSablon.selectComboBox(sablonAdi);
        return this;
    }

    @Step("Ön tanımlı şablonun gelmediği görülür")
    public EditorTab onTanimliSablonuOlmadigi(String sablonAdi) {
        BelgenetElement cmbSablon = comboBox(getOnTanimliDialog(),"label[id$='_label']");
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
        for (Condition condition:conditions) {
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

    @Step("Editör alanı bulunur")
    public SelenideElement getEditorArea(){
        return page.$("div[id$='allPanels']");
    }

    @Step("Hitap alanı bulunur")
    public SelenideElement getHitapAlani(){
        return page.$("div[id$=hitapInplace]");
    }


    @Step("Not Ekle butona basılır")
    public EditorTab notEkleTikla(){
        getEditor().toolbarButton("Not Ekle", true);
        page.$("div[id*='notEkleDialog']").shouldBe(visible);
        return this;
    }


    public EvrakNot getEvrakNot(){
        return new EvrakNot();
    }

    public class EvrakNot {

        private SelenideElement note;
        private ElementsCollection notes;

        private List<EvrakNot> createdNotes = new ArrayList<EvrakNot>();

        public SelenideElement getNotesTable(){
            return page.$("div[id*='evrakNotlariTable']");
        }

        public ElementsCollection getNoteDivs(){
            return page.$$("div[id*='evrakNotlariTable']>div");
        }

        @Step("Not Ekle dialog bulunur")
        public SelenideElement getNotEkleDialog(){
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
        public BelgenetElement getNotTipi(){
//            return comboBox(getNotEkleDialog(),"label[id$='evrakNotTipiD1_label']");
            return comboBox(getNotEkleDialog(),"label[class~='ui-selectonemenu-label']");
        }

        @Step("Not Ekle \"Not Tipi\" alan seçilir")
        public EvrakNot notTipiSec(String value){
            getNotTipi().selectComboBox(value);
            getNotTipi().shouldHave(text(value));
            return this;
        }

        @Step("Yeni Not tipi alanın değer kontrolleri")
        public EvrakNot notTipiAlanDegerKontrol(String... values){
            getNotTipi().getComboBoxValues().shouldHave(texts(values));
            return this;
        }

        @Step("Not Ekle \"Kaydet\" butonu bulunur")
        public SelenideElement getKaydetButton(){
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
        public EvrakNot notlariAra(Condition... aramaConditions){
            getNoteDivs().shouldHave(sizeGreaterThan(0)).last().shouldBe(visible);
            notes = getNoteDivs();
            for (Condition condition:aramaConditions)
                notes = notes.filterBy(condition);
            return this;
        }

        @Step("Not bulunur")
        public EvrakNot notuBul(Condition... aramaConditions){
            notlariAra(aramaConditions);
            note = notes.shouldHave(sizeGreaterThan(0)).first().shouldBe(visible);
            return this;
        }

        @Step("Not Sil butonu bulunur")
        public SelenideElement getNoteSilButton() {
            return $("button .noteClose");
        }

        @Step("Notu sil")
        public EvrakNot notuSil(){
            note.$("button .noteClose").click();
            note.shouldNotBe(exist);
            return this;
        }

        @Step("Postit şeklinde")
        public EvrakNot postitStyle(){
            String style = "position: relative; background: rgb(254, 250, 188); padding: 5px; font-size: 10px; color: rgb(0, 0, 0); width: 200px; margin-bottom: 15px; box-shadow: rgb(51, 51, 51) 0px 4px 6px;";
            note.shouldHave(attribute("style", style));
            return this;
        }

        public SelenideElement getNote(){
            return note;
        }

        public ElementsCollection getNotes(){
            return notes;
        }

        public List<EvrakNot> getCreatedNotes(){
            return createdNotes;
        }
    }

}
