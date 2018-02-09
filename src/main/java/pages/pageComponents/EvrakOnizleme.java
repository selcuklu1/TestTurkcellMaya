package pages.pageComponents;

import com.codeborne.selenide.*;
import data.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 29.01.2018
 * Açıklama:
 */
public class EvrakOnizleme extends MainPage {
    SelenideElement container = $("#layoutRightContainer");

    public EvrakNotlari getEvrakNotlari() {
        return new EvrakNotlari();
    }

    @Step("Evrak Postala")
    public EvrakPostala evrakPostala() {
        getOnizlemeButton("Evrak Postala").click();
        return new EvrakPostala();
    }

    @Step("Evrak önzleme \"{butonIsmi}\" butonu ara")
    public SelenideElement getOnizlemeButton(String butonIsmi) {
        return $x("//div[@id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab']//td[descendant::span[.='" + butonIsmi + "']]//button");
    }

    @Step("Evrak önzleme \"{butonIsmi}\" butona tikla")
    public EvrakOnizleme onizlemeButtonClick(String butonIsmi) {
        getOnizlemeButton(butonIsmi).click();
        return this;
    }


    public class DagitimPlaniIcerigi extends MainPage {
        SelenideElement dagitimPlaniDetayViewDialog = $(By.id("mainPreviewForm:dagitimPlaniDetayViewDialog"));
        SelenideElement dagitimPlaniDetay = $(By.id("mainPreviewForm:dagitimPlaniDetay"));
        SearchTable searchTable = new SearchTable(dagitimPlaniDetay);

        SelenideElement kaydet = $(By.id("mainPreviewForm:dagitimPlaniDetayKaydetViewDialog"));

        @Step("Kaydet")
        public DagitimPlaniIcerigi kaydet(){
            kaydet.click();
            return this;
        }

        @Step("Dağıtım Planı İçeriği listesi")
        public SearchTable getDagitimPlaniDetayDataTable(){
            return searchTable;
        }

        @Step("Gidiş Şekli {stepDescription}")
        public BelgenetElement getGidisSekli(String stepDescription) {
            return comboBox(searchTable.getFoundRow(), ".ui-selectonemenu-label");
        }

        @Step("Postalanacak Yerler listesinde ara")
        public DagitimPlaniIcerigi postalanacakYerlerdeAra(Condition... aramaKriterleri) {
            searchTable.findRows(aramaKriterleri).shouldHave(CollectionCondition.sizeGreaterThan(0)).getFoundRows().first();
            return this;
        }

        @Step("Gidiş Şekli seç")
        public DagitimPlaniIcerigi gidisSekliSec(String dagitimSekli) {
            getGidisSekli("aranır").selectComboBox(dagitimSekli);
            return this;
        }

        @Step("Detay buton {stepDescription}")
        public SelenideElement getDetayButtonInFoundRow(String stepDescription){
            return searchTable.getFoundRow().$x("descendant::button[span[.='Detay']]");
        }

        //region Ayrıntı alanlar
        @Step("Ayrıntıda Gönderici seç")
        public DagitimPlaniIcerigi gondericiSec(String gonderici) {
            searchTable.getFoundRow().$("select[id$='fromKepAdresId']").selectOption(gonderici);
            return this;
        }

        @Step("Ayrıntıda Alıcı seç")
        public DagitimPlaniIcerigi aliciSec(String alici) {
            searchTable.getFoundRow().$("select[id$='toKepAdresId']").selectOption(alici);
            return this;
        }

        @Step("Ayrıntıda e-posta gir")
        public DagitimPlaniIcerigi epostaGir(String eposta) {
            getAyrintiAlanInput("e-posta").setValue(eposta);
            return this;
        }

        @Step("Ayrıntıda Açıklama doldur")
        public DagitimPlaniIcerigi aciklamaGir(String aciklama) {
            getAyrintiAlanTextarea("Açıklama").setValue(aciklama);
            return this;
        }

        @Step("Ayrıntıda Gönderildiği Yer seç")
        public DagitimPlaniIcerigi gonderildigiYerSec(String gonderildigiYer) {
            getAyrintiAlanSelect("Gönderildiği Yer").selectOption(gonderildigiYer);
            return this;
        }

        @Step("Ayrıntıda Gramaj gir")
        public DagitimPlaniIcerigi grammajGir(String grammaj) {
            executeJavaScript("arguments[0].value = arguments[1]", getAyrintiAlanInput("Gramaj"), grammaj);
            //getAyrintiAlanInput("Gramaj").setValue(grammaj);
            return this;
        }

        @Step("Ayrıntıda Gramaj Hesapla")
        public DagitimPlaniIcerigi grammajHesapla() {
            getAyrintiAlanButton("Gramaj").click();
            return this;
        }

        @Step("Tutar dialog tamam butona basılır")
        public DagitimPlaniIcerigi tutarDialogTamamTikla(){
            $(By.id("mainPreviewForm:tutarDialogButtonId")).click();
            return this;
        }

        @Step("Ayrıntıda Tutar gir")
        public DagitimPlaniIcerigi tutarGir(String tutar) {
            getAyrintiAlanInput("Tutar").setValue(tutar);
            return this;
        }

        @Step("Ayrıntıda Tutar alanı kontrol et")
        public DagitimPlaniIcerigi tutarKonrolEt(String tutar) {
            getAyrintiAlanInput("Tutar").shouldHave(value(tutar));
            return this;
        }

        @Step("{label} alan doldur")
        public DagitimPlaniIcerigi ayrintiAlanDoldur(String label, String value) {
            getAyrintiAlanInput(label).setValue(value);
            return this;
        }

        public SelenideElement getAyrintiAlan(String label) {
            return searchTable.getFoundRow().$x("descendant::tr[td[1]/label[contains(.,'" + label + "')]]");
        }

        public SelenideElement getAyrintiAlanInput(String label, int... index) {
            return getAyrintiAlan(label).$("input:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanTextarea(String label, int... index) {
            return getAyrintiAlan(label).$("textarea:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanSelect(String label, int... index) {
            return getAyrintiAlan(label).$("select:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanButton(String label, int... index) {
            return getAyrintiAlan(label).$("button:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }
        //endregion

        //Yazdır
        SelenideElement yazdirEvrakDetayForm = $("#postaDetayYazdirForm");
        SelenideElement yazdirEvrakDetayClose = $("#postaDetayYazdirForm a.ui-dialog-titlebar-close");
        SearchTable yazdirUstVeriler = new SearchTable($(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri")));
        SearchTable yazdirEvrakinEkleri = new SearchTable($(By.id("postaDetayYazdirForm:evrakEkListesi")));

        @Step("Yazdır - penceriyi kapa")
        public DagitimPlaniIcerigi yazdirClose(){
            yazdirEvrakDetayClose.click();
            return this;
        }

        @Step("Yazdır")
        public DagitimPlaniIcerigi yazdir() {
            searchTable.getFoundRow().$x("descendant::button[.='Yazdır']").click();
            return this;
        }

        @Step("Yazdır - Üst Veriler listesi")
        public SearchTable getYazdirUstVerilerListesi(){
            return yazdirUstVeriler;
        }

        @Step("Yazdır - Evrakın ekleri listesi")
        public SearchTable getYazdirEvrakinEkleriListesi(){
            return yazdirEvrakinEkleri;
        }

        @Step("Üst Veriler - Yazdır butonu: {stepDescription}")
        public SelenideElement getUstVerilerYazdirButton(String stepDescription) {
            return yazdirUstVeriler.getFoundRow().$x("descendant::button[.='Yazdır']");
        }

        @Step("Evrakın ekleri - Yazdır butonu: {stepDescription}")
        public SelenideElement getEvrakinEkleriYazdirButton(String stepDescription) {
            return yazdirUstVeriler.getFoundRow().$x("descendant::button[.='Yazdır']");
        }


        @Step("Orjinalini Yazdır")
        public DagitimPlaniIcerigi orjinaliniYazdir() {
            searchTable.getFoundRow().$x("descendant::button[.='Orjinalini Yazdır']").click();
            return this;
        }

        @Step("Etiket Bastır")
        public DagitimPlaniIcerigi etiketBastir() {
            searchTable.getFoundRow().$x("descendant::button[.='Etiket Bastır']").click();
            return this;
        }

        @Step("Doğrulama Uyarı")
        public DagitimPlaniIcerigi dogrulamaUyari(String text, boolean evet) {
            $(byText(text)).shouldBe(visible);
            if (evet)
                $(By.id("mainPreviewForm:postalaDogrulaDialogForm:evetButton_id")).click();
            else
                $(By.id("mainPreviewForm:postalaDogrulaDialogForm:hayirButton_id")).click();
            return this;
        }
    }

    public class EvrakPostala extends MainPage {
        SelenideElement container = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
        SelenideElement evrakDetaylariContainer = $x("//fieldset[legend[.='Evrak Detayları']]");
        SelenideElement evrakDetaylari = $(By.id("mainPreviewForm:evrakDetayPanelGrid"));
        SelenideElement postalanacakYerlerContainer = $(By.id("#mainPreviewForm:postalanacakYerlerFieldsetId"));
        SelenideElement postalanacakYerlerTitle = $(By.id("mainPreviewForm:postalanacakYerlerFieldsetId legend"));
        SelenideElement postalanacakDataGrid = $(By.id("mainPreviewForm:postalanacakDataGrid"));
        SelenideElement postalanacakDataTable = $(By.id("mainPreviewForm:dataTableId"));
        SelenideElement postalaButton = $(By.id("mainPreviewForm:postalaButton_id"));

        SearchTable searchTable = new SearchTable(postalanacakDataTable);

        @Step("Postalancak Yerler listesi")
        public SearchTable getPostalanacakListesi() {
            return searchTable;
        }

        @Step("Postalanacak Yerler listesinde ara")
        public EvrakPostala postalanacakYerlerdeAra(Condition... aramaKriterleri) {
            searchTable.findRows(aramaKriterleri).shouldHave(CollectionCondition.sizeGreaterThan(0)).getFoundRows().first();
            return this;
        }

        //Dağıtım Şekli
        @Step("Gidiş Şekli {stepDescription}")
        public BelgenetElement getGidisSekli(String stepDescription) {
            return comboBox(searchTable.getFoundRow(), ".ui-selectonemenu-label");
        }

        @Step("Gidiş Şekli seç")
        public EvrakPostala gidisSekliSec(String dagitimSekli) {
            getGidisSekli("aranır").selectComboBox(dagitimSekli);
            return this;
        }

        @Step("Detay buton {stepDescription}")
        public SelenideElement getDetayButtonInFoundRow(String stepDescription){
            return searchTable.getFoundRow().$x("descendant::button[span[.='Detay']]");
        }

        //region Ayrıntı alanlar
        @Step("Ayrıntıda Gönderici seç")
        public EvrakPostala gondericiSec(String gonderici) {
            searchTable.getFoundRow().$("select[id$='fromKepAdresId']").selectOption(gonderici);
            return this;
        }

        @Step("Ayrıntıda Alıcı seç")
        public EvrakPostala aliciSec(String alici) {
            searchTable.getFoundRow().$("select[id$='toKepAdresId']").selectOption(alici);
            return this;
        }

        @Step("Ayrıntıda e-posta gir")
        public EvrakPostala epostaGir(String eposta) {
            getAyrintiAlanInput("e-posta").setValue(eposta);
            return this;
        }

        @Step("Ayrıntıda Açıklama doldur")
        public EvrakPostala aciklamaGir(String aciklama) {
            getAyrintiAlanTextarea("Açıklama").setValue(aciklama);
            return this;
        }

        @Step("Ayrıntıda Gönderildiği Yer seç")
        public EvrakPostala gonderildigiYerSec(String gonderildigiYer) {
            getAyrintiAlanSelect("Gönderildiği Yer").selectOption(gonderildigiYer);
            return this;
        }

        @Step("Ayrıntıda Gramaj gir")
        public EvrakPostala grammajGir(String grammaj) {
            executeJavaScript("arguments[0].value = arguments[1]", getAyrintiAlanInput("Gramaj"), grammaj);
            //getAyrintiAlanInput("Gramaj").setValue(grammaj);
            return this;
        }

        @Step("Ayrıntıda Gramaj Hesapla")
        public EvrakPostala grammajHesapla() {
            getAyrintiAlanButton("Gramaj").click();
            return this;
        }

        @Step("Ayrıntıda Tutar gir")
        public EvrakPostala tutarGir(String tutar) {
            getAyrintiAlanInput("Tutar").setValue(tutar);
            return this;
        }

        @Step("{label} alan doldur")
        public EvrakPostala ayrintiAlanDoldur(String label, String value) {
            getAyrintiAlanInput(label).setValue(value);
            return this;
        }

        public SelenideElement getAyrintiAlan(String label) {
            return searchTable.getFoundRow().$x("descendant::tr[td[1]/label[contains(.,'" + label + "')]]");
        }

        public SelenideElement getAyrintiAlanInput(String label, int... index) {
            return getAyrintiAlan(label).$("input:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanTextarea(String label, int... index) {
            return getAyrintiAlan(label).$("textarea:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanSelect(String label, int... index) {
            return getAyrintiAlan(label).$("select:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }

        public SelenideElement getAyrintiAlanButton(String label, int... index) {
            return getAyrintiAlan(label).$("button:nth-child(" + (index.length > 0 ? index[0] : 1) + ")");
        }
        //endregion

        //Yazdır
        SelenideElement yazdirEvrakDetayForm = $("#postaDetayYazdirForm");
        SelenideElement yazdirEvrakDetayClose = $("#postaDetayYazdirForm a.ui-dialog-titlebar-close");
        SearchTable yazdirUstVeriler = new SearchTable($(By.id("postaDetayYazdirForm:dtPostaEvrakUstVeri")));
        SearchTable yazdirEvrakinEkleri = new SearchTable($(By.id("postaDetayYazdirForm:evrakEkListesi")));

        @Step("Yazdır - penceriyi kapa")
        public EvrakPostala yazdirClose(){
            yazdirEvrakDetayClose.click();
            return this;
        }

        @Step("Yazdır")
        public EvrakPostala yazdir() {
            searchTable.getFoundRow().$x("descendant::button[.='Yazdır']").click();
            return this;
        }

        @Step("Yazdır - Üst Veriler listesi")
        public SearchTable getYazdirUstVerilerListesi(){
            return yazdirUstVeriler;
        }

        @Step("Yazdır - Evrakın ekleri listesi")
        public SearchTable getYazdirEvrakinEkleriListesi(){
            return yazdirEvrakinEkleri;
        }

        @Step("Üst Veriler - Yazdır butonu: {stepDescription}")
        public SelenideElement getUstVerilerYazdirButton(String stepDescription) {
            return yazdirUstVeriler.getFoundRow().$x("descendant::button[.='Yazdır']");
        }

        @Step("Evrakın ekleri - Yazdır butonu: {stepDescription}")
        public SelenideElement getEvrakinEkleriYazdirButton(String stepDescription) {
            return yazdirUstVeriler.getFoundRow().$x("descendant::button[.='Yazdır']");
        }


        @Step("Orjinalini Yazdır")
        public EvrakPostala orjinaliniYazdir() {
            searchTable.getFoundRow().$x("descendant::button[.='Orjinalini Yazdır']").click();
            return this;
        }

        @Step("Etiket Bastır")
        public EvrakPostala etiketBastir() {
            searchTable.getFoundRow().$x("descendant::button[.='Etiket Bastır']").click();
            return this;
        }

        @Step("Postala")
        public EvrakPostala postala() {
            postalaButton.pressEnter();
            return this;
        }

        @Step("Doğrulama Uyarı")
        public EvrakPostala dogrulamaUyari(String text, boolean evet) {
            $(byText(text)).shouldBe(visible);
            if (evet)
                $(By.id("mainPreviewForm:postalaDogrulaDialogForm:evetButton_id")).click();
            else
                $(By.id("mainPreviewForm:postalaDogrulaDialogForm:hayirButton_id")).click();
            return this;
        }
    }

    public class EvrakNotlari extends MainPage {
        SelenideElement evrakNotlariDialog = $("#evrakOnizlemeNotlarDialogId");
        SelenideElement closeDialog = $x("//div[@id='evrakOnizlemeNotlarDialogId']//a[span[contains(@class,'ui-icon-closethick')]]");
        SelenideElement noteBody = $("#evrakOnizlemeNotlarDialogId td[role='gridcell'");
        SelenideElement note = $("#evrakOnizlemeNotlariDatatableId_data");
        SelenideElement deleteNote = $("#evrakOnizlemeNotlariDatatableId_data [class~='delete-icon']");
        SelenideElement next = $("#evrakOnizlemeNotlariDatatableId_paginator_bottom span[class~='ui-paginator-next']");

        @Step("Evrak Notları tabı aç")
        public EvrakNotlari evrakNotlariTabiAc() {
            $x("//div[@id='mainPreviewForm:evrakOnizlemeTab']//a[.='Evrak Notları']").click();
            return this;
        }

        @Step("Evrak Notları pencereyi kapat")
        public EvrakNotlari evrakNotlariDialoguKapat() {
//                closeDialog.click();
//                closeDialog.pressEnter();
            clickJs(closeDialog);
            closeDialog.shouldNotBe(visible);
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

        @Step("Zemin rengi sarı")
        public boolean zeminRengiSari() {
            String styleAtt = noteBody.attr("style");
            return styleAtt.contains("rgb(254, 250, 188)") || styleAtt.contains("#fefabc");
        }

        @Step("Sonraki nota geç")
        public EvrakNotlari sonrakiNot() {
            next.click();
            return this;
        }

        @Step("Sonraki nota geç buton disabled olmalı")
        public EvrakNotlari sonrakiNotIsDisabled() {
            next.shouldHave(cssClass("ui-state-disabled"));
            return this;
        }

        public SelenideElement getSonrakiButton() {
            return next;
        }

        @Step("Kurum logosu bulunur")
        public String kurumLogosu() {
            String src = "";
            if (note.$("img").exists())
                src = note.$("img").attr("src");
            return src;
        }

        /*@Step("Güncelle butonu enabled olmalı")
        public EvrakNotlari notGuncelleButonEnabled() {
            note.$("#evrakOnizlemeNotlariDatatableId_data button").shouldBe(Condition.enabled);
            return this;
        }*/

        @Step("Sil butonu enabled olmalı")
        public EvrakNotlari notSilButonEnabled() {
            note.$("#evrakOnizlemeNotlariDatatableId_data button").shouldBe(Condition.enabled);
            return this;
        }

        @Step("Sil butonu disabled olmalı")
        public EvrakNotlari notSilButonIsDisabled() {
            note.$("#evrakOnizlemeNotlariDatatableId_data button").shouldBe(Condition.disabled);
            return this;
        }

        @Step("Notu kontrol et")
        public EvrakNotlari notuKontrolEt(User user, String notTipi, String aciklama, String date, String time, String kurumLogo) {

            note.shouldBe(visible)
                    .shouldHave(text(user.getFullname())
                            , text(user.getGorev())
                            , text(notTipi)
                            , text(aciklama)
                            , text(date)
                            , text(time));

            Assert.assertTrue(kurumLogosu().contains(kurumLogo), kurumLogo + " kurum logosu olmalı");

            if (notTipi.equalsIgnoreCase("Kişisel"))
                Assert.assertTrue(zeminRengiBeyaz(), "Kişisel notun arka plan beyaz");
            else if (notTipi.equalsIgnoreCase("Genel"))
                Assert.assertTrue(zeminRengiSari(), "Genel notun arka plan sarı");
            else
                throw new RuntimeException("Notun arka palan ne beyaz ne de sarı: " + note.toString());

            return this;
        }
    }

    public class EvrakEkleri extends MainPage {

        private final String tabName = "Evrak Ekleri";

        SearchTable searchTable = new SearchTable(container.$("[id$='ekListesiOnizlemeDataTable']"));

        @Step(tabName + " tabı aranır {stepDescription}")
        public SelenideElement getTab(String stepDescription){
            return $x("//div[@id='mainPreviewForm:evrakOnizlemeTab']//a[.='"+tabName+"']");
        }

        @Step(tabName + " tabı açılır")
        public EvrakEkleri openTab(){
            getTab("").shouldBe(visible).click();
            //container.$("[id$='ekListesiOnizlemeDataTable']").shouldBe(visible);
            return this;
        }

        @Step(tabName + " ek listesi aranır")
        public SearchTable getDataTable(){
            return searchTable;
        }

        //Bitmedi, birden fazla doc eklenince kontrol edilmeli
        String evrakDivId;

        public SelenideElement getEvrakDiv(){
            evrakDivId = container.$("div[id$=accpnl]").attr("id");
            return container.$("div[id$=accpnl]");
        }

        public EvrakEkleri getEvrakTitle(){
            return this;
        }

        public SelenideElement getEvrakFrame(){
            return getEvrakDiv().$("iframe.onizlemeFrame");
        }

        @Step("Evrak tekst içeriği kontrolü")
        public EvrakEkleri evrakTextControl(String...texts){
            switchTo().frame(getEvrakFrame());

            for (String text:texts) {
                $(".textLayer").$(Selectors.byText(text)).should(exist);
            }

            switchTo().window(0);
            return this;
        }

        @Step("Sayisal filigran bulunmalı")
        public EvrakEkleri checkIfSayisalFiligranExist(){
            switchTo().frame(getEvrakFrame());
            $("div.textLayer").$$("div").filterBy(matchText("\\d+ - \\d+ - \\d+ - \\d+ - \\d+")).shouldHaveSize(1);
            switchTo().window(0);
            return this;
        }

        @Step("Filigran sayı al")
        public int getFiligramSayi(){
            switchTo().frame(getEvrakFrame());
            int sayi = Integer.valueOf(
                    $("div.textLayer").$$("div")
                            .filterBy(matchText("\\d+ - \\d+ - \\d+ - \\d+ - \\d+"))
                            .shouldHaveSize(1).first().text()
                    .split("-")[0].trim()
            );

            switchTo().frame(0);
            return sayi;
        }

    }

    public class IliskiliEvraklar extends MainPage {

        private final String tabName = "ilişkili Evraklar";

        @Step(tabName + " tabı aranır {stepDescription}")
        public SelenideElement getTab(String stepDescription){
            return $x("//div[@id='mainPreviewForm:evrakOnizlemeTab']//a[.='"+tabName+"']");
        }

        @Step(tabName + " tabı açılır")
        public IliskiliEvraklar openTab(){
            getTab("").shouldBe(visible).click();
            //container.$("[id$='ekListesiOnizlemeDataTable']").shouldBe(visible);
            return this;
        }
    }

    public class IlgiBilgileri extends MainPage {

        private final String tabName = "İlgi Bilgileri";

        SearchTable searchTable = new SearchTable(container.$("[id$='ilgiListesiDataTable']"));

        @Step(tabName + " tabı aranır {stepDescription}")
        public SelenideElement getTab(String stepDescription){
            return $x("//div[@id='mainPreviewForm:evrakOnizlemeTab']//a[.='"+tabName+"']");
        }

        @Step(tabName + " tabı açılır")
        public IlgiBilgileri openTab(){
            getTab("").shouldBe(visible).click();
            //container.$("[id$='ekListesiOnizlemeDataTable']").shouldBe(visible);
            return this;
        }

        @Step(tabName + " ek listesi aranır")
        public SearchTable getDataTable(){
            return searchTable;
        }

        //Bitmedi, birden fazla doc eklenince kontrol edilmeli
        String evrakDivId;

        public SelenideElement getEvrakDiv(){
            evrakDivId = container.$("div[id$=accpnl]").attr("id");
            return container.$("div[id$=accpnl]");
        }

        public IlgiBilgileri getEvrakTitle(){
            return this;
        }

        public SelenideElement getEvrakFrame(){
            return getEvrakDiv().$("iframe.onizlemeFrame");
        }

        @Step("Evrak tekst içeriği kontrolü")
        public IlgiBilgileri evrakTextControl(String...texts){
            switchTo().frame(getEvrakFrame());

            for (String text:texts) {
                $(".textLayer").$(Selectors.byText(text)).should(exist);
            }

            switchTo().frame(0);
            return this;
        }

        @Step("Sayisal filigran bulunmalı")
        public IlgiBilgileri checkIfSayisalFiligranExist(){
            switchTo().frame(getEvrakFrame());
            $("div.textLayer").$$("div").filterBy(matchText("\\d+ - \\d+ - \\d+ - \\d+ - \\d+")).shouldHaveSize(1);
            switchTo().frame(0);
            return this;
        }

        @Step("Filigran sayı al")
        public int getFiligramSayi(){
            switchTo().frame(getEvrakFrame());
            int sayi = Integer.valueOf(
                    $("div.textLayer").$$("div")
                            .filterBy(matchText("\\d+ - \\d+ - \\d+ - \\d+ - \\d+"))
                            .shouldHaveSize(1).first().text()
                            .split("-")[0].trim()
            );

            switchTo().frame(0);
            return sayi;
        }

    }

}
