package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 26.01.2018
 * Açıklama:
 */
public class UstYazi extends MainPage {

    private SelenideElement notDialog = $("#evrakKisiselNotDialogFormId");
    private SelenideElement container;

    public UstYazi(SelenideElement container) {
        this.container = container;
        //searchTable = new SearchTable(container.$("div[id$='kisiselNotEkleDataTableId'] table"));
    }

    public UstYazi() {
        this.container = $("html");
        //searchTable = new SearchTable(container.$("div[id$='kisiselNotEkleDataTableId'] table"));
    }

    @Step("\"Üst yazıyı görüntülemek için tıklayınız.\" link bulunur")
    public SelenideElement getUstYaziLink() {
        return container.$("a[id$='ustYaziLinkButtonSag']");
    }
/*
    //@Step("Evrak Notlari listesi bulunur")
    public SearchTable getEvrakNotlariTable(){
        return searchTable;
    }

    @Step("Yeni evrak ekle butonu bulunur")
    public SelenideElement getYeniEvrakNotEkleButton(){
        return container.$("button[id$='kisiselNotEkleDataTableId:kisiselNotEkleId']");
    }

    @Step("Yeni evrak ekle butonu bulunur")
    public UstYazi yeniEvrakNotEkle(){
        getYeniEvrakNotEkleButton().sendKeys("\n");
        getYeniEvrakNotEkleButton().click(1, 1);
        return this;
    }

    @Step("Not Ekle dialog bulunur")
    public SelenideElement getNotEkleDialog(){
        return $("div[id='evrakKisiselNotDialogFormId:evrakKisiselNotDialogId']");
    }

    @Step("Not Ekle \"Açıklama\" alanı bulunur")
    public SelenideElement getYeniNotAciklamaAlani() {
        return getNotEkleDialog().$("textarea");
    }

    @Step("Not Ekle \"Açıklama\" alanı doldurulur")
    public UstYazi yeniNotAciklamaAlaniDoldur(String aciklama) {
        getYeniNotAciklamaAlani().setValue(aciklama);
        getYeniNotAciklamaAlani().shouldHave(value(aciklama));
        return this;
    }

    @Step("Not Ekle \"Not Tipi\" alan bulunur")
    public BelgenetElement getYeniNotNotTipi(){
        return comboBox(notDialog,"label[id='evrakKisiselNotDialogFormId:evrakNotTipi_label']");
    }

    @Step("Not Ekle \"Not Tipi\" alan seçilir")
    public UstYazi yeniNotNotTipiSec(String value){
        getYeniNotNotTipi().selectComboBox(value);
        getYeniNotNotTipi().shouldHave(text(value));
        return this;
    }

    @Step("Not Ekle \"Kaydet\" butonu bulunur")
    public SelenideElement getYeniNotKaydetButton(){
        return notDialog.$("button[id='evrakKisiselNotDialogFormId:evrakKisiselNotKaydet']");
    }

    @Step("Not Ekle \"Kaydet\" butona tıkla")
    public UstYazi yeniNotKaydet() {
        getYeniNotKaydetButton().click();
        return this;
    }

    @Step("Yeni Not tipi alanın değer kontrolleri")
    public UstYazi yeniNotTipiAlanDegerKontrol(String[] values){
        getYeniNotNotTipi().getComboBoxValues().shouldHave(texts(values));
        return this;
    }

    @Step("Açıklama karakter sayısı maksimum {maxLength} olmalı")
    public UstYazi yeniNotAciklamaKarakterSayisiKontrolu(int maxLength) {
        SelenideElement counter = getNotEkleDialog().$("span[id='evrakKisiselNotDialogFormId:aciklamaCounter']");

        counter.should(visible);
        int leftCount = getNumber(counter.text());

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(leftCount, maxLength, "Max karakter sayısı");

        String text = "";
        for (int i = 0; i < maxLength - (int) (Math.log10(maxLength) + 1); i++) {
            text += ".";
        }
        yeniNotAciklamaAlaniDoldur(text + String.valueOf(maxLength));

        leftCount = getNumber(counter.text());
        sa.assertEquals(leftCount, 0, "Kalan karakter sayısı");

        getYeniNotAciklamaAlani().sendKeys("*");
        getYeniNotAciklamaAlani().shouldNotHave(value("*"));

        sa.assertAll();

        getYeniNotAciklamaAlani().clear();
        getYeniNotAciklamaAlani().shouldBe(empty);

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
    public SelenideElement yeniNotOlustur(String olusturan, String notTipi, String aciklama, int maxLength, String[] notTipiValues) {

        yeniEvrakNotEkle();
        yeniNotAciklamaKarakterSayisiKontrolu(maxLength);
        yeniNotAciklamaAlaniDoldur(aciklama);

        yeniNotTipiAlanDegerKontrol(notTipiValues);
        yeniNotNotTipiSec(notTipi);
        yeniNotKaydet();
        getNotEkleDialog().should(disappear);

        String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
        String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
        //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
        return evrakNotlarindaNotlariBul(text(olusturan), text(notTipi), text(aciklama), text(date), text(time)).shouldHaveSize(1).first();
    }

    @Step("Yeni not oluşturulur")
    public SelenideElement yeniNotOlustur(String olusturan, String notTipi, String aciklama) {

        yeniEvrakNotEkle();
        yeniNotAciklamaAlaniDoldur(aciklama);

        yeniNotNotTipiSec(notTipi);
        yeniNotKaydet();
        getNotEkleDialog().should(disappear);

        String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
        String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
        //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
        return evrakNotlarindaNotlariBul(text(olusturan), text(notTipi), text(aciklama), text(date), text(time)).shouldHaveSize(1).first();
    }

    @Step("Evrak Notları listesinde not bulunur")
    public ElementsCollection evrakNotlarindaNotlariBul(Condition... aramaConditions){
        getEvrakNotlariTable().findRows(aramaConditions);
        return getEvrakNotlariTable().getFoundRows();
    }

    @Step("Not Güncelle butonu bulunur")
    public SelenideElement getNoteGuncelleButton() {
        return $("button span[class~='update-icon']");
    }

    @Step("Not Sil butonu bulunur")
    public SelenideElement getNoteSilButton() {
        return $("button span[class~='delete-icon']");
    }*/

    @Step("Üst yazıyı görüntüle")
    public UstYazi ustYaziGoruntule() {
        getUstYaziLink().shouldBe(visible).click();
        return this;
    }
    //private SearchTable searchTable;

    @Step("Evrak Notları tab bulunur")
    public SelenideElement getEvrakNotlariTab() {
        return container.$(By.linkText("Evrak Notları"));
    }

    @Step("Evrak Notlari tab açılır")
    public EvrakNot evrakNotlariTabiAc() {
        getEvrakNotlariTab().shouldBe(visible).click();
        return new EvrakNot(container);
    }

    public SelenideElement getContainer() {
        return container;
    }

    public class EvrakNot {

        private SelenideElement container;
        private SearchTable searchTable;
        private SelenideElement note;
        private ElementsCollection notes;
        private SelenideElement notDialog = $("#evrakKisiselNotDialogFormId");

        private List<EvrakNot> createdNotes = new ArrayList<>();

        public EvrakNot() {
            this.container = $("html");
            //searchTable = new SearchTable(container.$("div[id$='kisiselNotEkleDataTableId'] table"));
        }

        public EvrakNot(SelenideElement container) {
            this.container = container;
            searchTable = new SearchTable(container.$("div[id$='kisiselNotEkleDataTableId'] table"));
        }

        public SearchTable getEvrakNotlariTable() {
            return searchTable;
        }

        @Step("Yeni evrak not ekle butonu bulunur")
        public SelenideElement getYeniNotEkleButton() {
            return container.$("button[id$='kisiselNotEkleDataTableId:kisiselNotEkleId']");
        }

        @Step("Yeni evrak not ekle")
        public EvrakNot yeniNotEkle() {
            getYeniNotEkleButton().sendKeys("\n");
            getYeniNotEkleButton().click(1, 1);
            return this;
        }

        @Step("Not Ekle dialog bulunur")
        public SelenideElement getNotEkleDialog() {
            return $("div[id='evrakKisiselNotDialogFormId:evrakKisiselNotDialogId']");
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
            SelenideElement counter = getNotEkleDialog().$("span[id='evrakKisiselNotDialogFormId:aciklamaCounter']");

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
            return comboBox(getNotEkleDialog(), "label[id='evrakKisiselNotDialogFormId:evrakNotTipi_label']");
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
            return getNotEkleDialog().$("button[id='evrakKisiselNotDialogFormId:evrakKisiselNotKaydet']");
//            notDialog.$("button[id='evrakKisiselNotDialogFormId:evrakKisiselNotKaydet']");
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
            return Integer.parseInt(m.group());
        }

        @Step("Yeni not oluşturulur, açıklama maksimum uzunluk ve not tipi değerleri kontrolleri")
        public EvrakNot notOlustur(String olusturan, String notTipi, String aciklama, int maxLength, String[] notTipiValues) {

            yeniNotEkle();
            aciklamaKarakterSayisiKontrolu(maxLength);
            aciklamaAlaniDoldur(aciklama);

            notTipiAlanDegerKontrol(notTipiValues);
            notTipiSec(notTipi);
            kaydet();
            getNotEkleDialog().should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
            //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
           /* System.out.println("Time ofLocalizedTime: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            System.out.println("Time1: " + DateTimeFormatter.ofPattern("HH:mm", new Locale("tr", "TR")).format(LocalDateTime.now()));
            System.out.println("Time2: " + DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now()));
            System.out.println("Time3: " + System.currentTimeMillis());*/

            notuBul(text(olusturan), text(notTipi), text(aciklama), text(date), text(time));
            createdNotes.add(this);
            return this;
        }

        @Step("Yeni not oluşturulur")
        public EvrakNot notOlustur(String olusturan, String notTipi, String aciklama) {

            yeniNotEkle();
            aciklamaAlaniDoldur(aciklama);

            notTipiSec(notTipi);
            kaydet();
            getNotEkleDialog().should(disappear);

            String date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now());
            String time = DateTimeFormatter.ofPattern("HH").format(LocalDateTime.now());
            //String time = DateTimeFormatter.ofPattern("HH:mm").format(LocalDateTime.now());
            notuBul(text(olusturan), text(notTipi), text(aciklama), text(date), text(time));
            createdNotes.add(this);
            return this;
        }

        @Step("Notları ara")
        public EvrakNot notlariAra(Condition... aramaConditions) {
            notes = getEvrakNotlariTable()
                    .goToFirstPage()
                    .searchInAllPages(true)
                    .findRows(aramaConditions).getFoundRows();
            return this;
        }

        @Step("Not bulunur")
        public EvrakNot notuBul(Condition... aramaConditions) {
            notlariAra(aramaConditions);
            Assert.assertEquals(notes.size(), 1, Arrays.toString(aramaConditions) + " olan not sayıs 1 olmalı");
            //note = notes.shouldHaveSize(1).first().shouldBe(visible);
            note = notes.first().shouldBe(visible);
            return this;
        }

        @Step("Not Güncelle butonu: {stepDescription}")
        public SelenideElement getNoteGuncelleButton(String stepDescription) {
            return note.$("button span[class~='update-icon']");
        }

        @Step("Not Sil butonu: {stepDescription}")
        public SelenideElement getNoteSilButton(String stepDescription) {
            return note.$("button span[class~='delete-icon']");
        }

        @Step("Not Sil butonu: {condition}")
        public EvrakNot noteSilButton(Condition condition) {
            note.$("button span[class~='delete-icon']").should(condition);
            return this;
        }

        @Step("Notu sil ve olmadığı kontrol et")
        public EvrakNot notuSil() {
            getNoteSilButton("").click();
            note.shouldNotBe(exist);
            return this;
        }

        @Step("Notu güncelle")
        public EvrakNot notuGuncelle(String aciklama) {
            getNoteGuncelleButton("").click();
            getAciklamaAlani().clear();
            aciklamaAlaniDoldur(aciklama);
            kaydet();
            return this;
        }

        @Step("Postit şeklinde")
        public EvrakNot postitStyle() {
            String style = "position:relative; background:#fefabc; padding: 5px;  font-size: 10px; color: #000; width: 200px; margin-bottom:15px; box-shadow: 0px 4px 6px #333; -moz-box-shadow: 0px 4px 6px #333; -webkit-box-shadow: 0px 4px 6px #333;";
            note.shouldHave(attribute("style", style));
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
