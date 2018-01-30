package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 29.01.2018
 * Açıklama:
 */
public class EvrakOnizleme extends MainPage {
    SelenideElement container = $("layoutRightContainer");

    public EvrakNotlari getEvrakNotlari(){
        return new EvrakNotlari();
    }

    @Step("Evrak Postala")
    public EvrakPostala evrakPostala(){
        getOnizlemeButton("Evrak Postala").click();
        return new EvrakPostala();
    }

    @Step("Evrak önzleme \"{butonIsmi}\" butonu ara")
    public SelenideElement getOnizlemeButton(String butonIsmi){
        return $x("//div[@id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab']//td[descendant::span[.='"+ butonIsmi +"']]//button");
    }

    @Step("Evrak önzleme \"{butonIsmi}\" butona tikla")
    public EvrakOnizleme onizlemeButtonClick(String butonIsmi){
        getOnizlemeButton(butonIsmi).click();
        return this;
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
        private SearchTable getPostalanacakListesi(){
            return searchTable;
        }

        @Step("Postalanacak Yerler listesinde ara")
        public EvrakPostala postalanacakYerlerdeAra(Condition... aramaKriterleri){
            searchTable.findRows(aramaKriterleri);
            return this;
        }

        //Dağıtım Şekli
        @Step("Gidiş Şekli seç")
        public EvrakPostala gidisSekliSec(String dagitimSekli){
            comboBox(searchTable.getFoundRow(), ".ui-selectonemenu-label").selectComboBox(dagitimSekli);
            return this;
        }

        //region Ayrıntı alanlar
        @Step("Ayrıntıda Gönderici seç")
        public EvrakPostala gondericiSec(String gonderici){
            searchTable.getFoundRow().$("select[id$='fromKepAdresId']").selectOption(gonderici);
            return this;
        }

        @Step("Ayrıntıda Alıcı seç")
        public EvrakPostala aliciSec(String alici){
            searchTable.getFoundRow().$("select[id$='toKepAdresId']").selectOption(alici);
            return this;
        }

        @Step("Ayrıntıda e-posta gir")
        public EvrakPostala epostaGir(String eposta){
            getAyrintiAlanInput("e-posta").setValue(eposta);
            return this;
        }

        @Step("Ayrıntıda Açıklama doldur")
        public EvrakPostala aciklamaGir(String aciklama){
            getAyrintiAlanTextarea("Açıklama").setValue(aciklama);
            return this;
        }

        @Step("Ayrıntıda Gönderildiği Yer doldur")
        public EvrakPostala gonderildigiYerSec(String gonderildigiYer){
            getAyrintiAlanSelect("Gönderildiği Yer").selectOption(gonderildigiYer);
            return this;
        }

        @Step("Ayrıntıda Gramaj gir")
        public EvrakPostala grammajGir(String grammaj){
            getAyrintiAlanInput("Gramaj").setValue(grammaj);
            return this;
        }

        @Step("Ayrıntıda Gramaj Hesapla")
        public EvrakPostala grammajHesapla(){
            getAyrintiAlanButton("Gramaj").click();
            return this;
        }

        @Step("Ayrıntıda Tutar gir")
        public EvrakPostala tutarGir(String tutar){
            getAyrintiAlanInput("Tutar").setValue(tutar);
            return this;
        }

        public SelenideElement getAyrintiAlan(String label){
            return searchTable.getFoundRow().$x("descendant::tr[td[1]/label[contains(.,'" + label + "')]]");
        }

        public SelenideElement getAyrintiAlanInput(String label, int... index){
            return getAyrintiAlan(label).$("input:nth-child("+ (index.length > 0 ? index[0]:1) +")");
        }

        public SelenideElement getAyrintiAlanTextarea(String label, int... index){
            return getAyrintiAlan(label).$("textarea:nth-child("+ (index.length > 0 ? index[0]:1) +")");
        }

        public SelenideElement getAyrintiAlanSelect(String label, int... index){
            return getAyrintiAlan(label).$("select:nth-child("+ (index.length > 0 ? index[0]:1) +")");
        }

        public SelenideElement getAyrintiAlanButton(String label, int... index){
            return getAyrintiAlan(label).$("button:nth-child("+ (index.length > 0 ? index[0]:1) +")");
        }
        //endregion

        //Yazdır
        @Step("Yazdır")
        public EvrakPostala yazdir(){
            searchTable.getFoundRow().$x("descendant::button[.='Yazdır']").click();
            return this;
        }

        @Step("Orjinalini Yazdır")
        public EvrakPostala orjinaliniYazdir(){
            searchTable.getFoundRow().$x("descendant::button[.='Orjinalini Yazdır']").click();
            return this;
        }

        @Step("Etiket Bastır")
        public EvrakPostala etiketBastir(){
            searchTable.getFoundRow().$x("descendant::button[.='Etiket Bastır']").click();
            return this;
        }

        @Step("Postala")
        public EvrakPostala postala(){
            postalaButton.pressEnter();
            return this;
        }

        @Step("Doğrulama Uyarı")
        public EvrakPostala dogrulamaUyari(String text, boolean evet){
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
        public EvrakNotlari evrakNotlariTabiAc(){
            $x("//div[@id='mainPreviewForm:evrakOnizlemeTab']//a[.='Evrak Notları']").click();
            return this;
        }

        @Step("Evrak Notları pencereyi kapat")
        public EvrakNotlari evrakNotlariDialoguKapat() {
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

        @Step("Sil butonu enabled olmalı")
        public EvrakNotlari notSilButonEnabled(){
            note.$("#evrakOnizlemeNotlariDatatableId_data button").shouldBe(Condition.enabled);
            return this;
        }

        @Step("Sil butonu disabled olmalı")
        public EvrakNotlari notSilButonDisabled(){
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
}
