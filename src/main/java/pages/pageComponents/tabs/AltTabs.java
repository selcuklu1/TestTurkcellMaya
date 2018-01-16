package pages.pageComponents.tabs;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.belgenetElements.BelgenetElement;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 9.01.2018
 * Açıklama:
 */
public class AltTabs extends MainPage {

    protected SelenideElement container;
    protected SelenideElement tab;

    private final static String dosyaEkleTabName = "Dosya Ekle";
    private final static String fizikselEkEkleTabName = "Fiziksel Ek Ekle";
    private final static String sistemdeKayitliEvrakEkleTabName = "Sistemde Kayıtlı Evrak Ekle";
    private final static String webAdresiniEkleTabName = "Web Adresini Ekle";
    private final static String arsivdeKayitliEvrakEkleTabName = "Arşivde Kayıtlı Evrak Ekle";
    private final static String metinEkleTabName = "Metin Ekle";
    private final static String tercumeEkleTabName = "Tercüme Ekle";

    SearchTable sistemdeKayitliEvrakListesiDataTable;
    SearchTable arsivdenEvrakAraListesiDataTable;


    public AltTabs(SelenideElement container) {
        this.container = container;
    }

    private BelgenetElement findBelgenetElement(String labelText, String parentTag, String targetTag, int index){
        return comboLov(tab, By.xpath("(descendant::"+ parentTag +"[descendant::label[normalize-space(.)='"+labelText+"']]//"+ targetTag +")[" + index + "]"));
        //return pages.pageComponents.belgenetElements.Belgenet.$(By.xpath("(descendant::"+ parentTag +"[descendant::label[normalize-space(.)='"+labelText+"']])[" + index + "]"));
    }
    private SelenideElement findElement(String labelText, String parentTag, String targetTag, int index){
        return tab.$x("(descendant::"+ parentTag +"[descendant::label[normalize-space(.)='"+labelText+"']]//"+ targetTag +")[" + index + "]");
    }
    public BelgenetElement inputComboLov(String label, int... index){
        return findBelgenetElement(label, "table", "input", ((index.length > 0)?index[0]:1));
    }
    public SelenideElement input(String label, int... index){
        return findElement(label, "table", "input", ((index.length > 0)?index[0]:1));
    }
    public SelenideElement inputButton(String label, int... index){
        return findElement(label, "table", "button", ((index.length > 0)?index[0]:1));
    }
    public SelenideElement textarea(String label, int... index){
        return findElement(label, "table", "textarea", ((index.length > 0)?index[0]:1));
    }
    public SelenideElement select(String label, int... index){
        return findElement(label, "table", "select", ((index.length > 0)?index[0]:1));
    }
    public SelenideElement button(String name, int... index){
        return tab.$x("(descendant::button[descendant::span[normalize-space(.)='"+name+"']])[" + ((index.length > 0)?index[0]:1) + "]");
    }


    @Step(dosyaEkleTabName + " tabı aç")
    public AltTabs dosyaEkleTabiAc() {
        getDosyaEkleTab().click();
        tab = container.$("div[id$='dosyaEkleTab']");
        return this;
    }
    @Step(dosyaEkleTabName + " tabı bul")
    public SelenideElement getDosyaEkleTab() {
        return container.$(byLinkText(dosyaEkleTabName));
    }

    @Step(tercumeEkleTabName + " tabı aç")
    public AltTabs tercumeEkleTabiAc() {
        getTercumeEkleTab().click();
        tab = container.$("div[id$='tercumeEvragiEkleTab']");
        return this;
    }
    @Step(tercumeEkleTabName + " tabı bul")
    public SelenideElement getTercumeEkleTab() {
        return container.$(byLinkText(tercumeEkleTabName));
    }

    @Step(metinEkleTabName + " tabı aç")
    public AltTabs metinEkleTabiAc() {
        getMetinEkleTab().click();
        tab = container.$("div[id$='aciklamaEkleTab']");
        return this;
    }
    @Step(metinEkleTabName + " tabı bul")
    public SelenideElement getMetinEkleTab() {
        return container.$(byLinkText(metinEkleTabName));
    }

    @Step(arsivdeKayitliEvrakEkleTabName + " tabı aç")
    public AltTabs arsivdeKayitliEvrakEkleTabiac() {
        getArsivdeKayitliEvrakEkleTab().click();
        tab = tab.$("div[id$='arsivdenEkEkleTabId']");
        arsivdenEvrakAraListesiDataTable = new SearchTable($("div[id$='arsivdenEvrakAraListesiDataTable']"));
        return this;
    }
    @Step(arsivdeKayitliEvrakEkleTabName + " tabı bul")
    public SelenideElement getArsivdeKayitliEvrakEkleTab() {
        return container.$(byLinkText(arsivdeKayitliEvrakEkleTabName));
    }

    @Step(webAdresiniEkleTabName + " tabı aç")
    public AltTabs webAdresiniEkleTabiAc() {
        getWebAdresiniEkleTab().click();
        tab = container.$("div[id$='webAdresindenEkEkle']");
        return this;
    }
    @Step(webAdresiniEkleTabName + " tabı bul")
    public SelenideElement getWebAdresiniEkleTab() {
        return container.$(byLinkText(webAdresiniEkleTabName));
    }

    @Step(sistemdeKayitliEvrakEkleTabName + " tabı aç")
    public AltTabs sistemdeKayitliEvrakEkleTabiAc() {
        getSistemdeKayitliEvrakEkleTab().click();
        tab = container.$("div[id$='sistemdeKayitliEvragiEkleTab']");
        sistemdeKayitliEvrakListesiDataTable = new SearchTable(tab.$("div[id$='sistemdeKayitliEvrakListesiDataTable']"));
        return this;
    }
    @Step(sistemdeKayitliEvrakEkleTabName + " tabı bul")
    public SelenideElement getSistemdeKayitliEvrakEkleTab() {
        return container.$(byLinkText(sistemdeKayitliEvrakEkleTabName));
    }

    @Step(fizikselEkEkleTabName + " tabı aç")
    public AltTabs fizikselEkEkleTabiAc() {
        getFiziksetEkEkleTab().click();
        tab = container.$("div[id$='aciklamaEkleTab']");
        return this;
    }
    @Step(fizikselEkEkleTabName + " tabı bul")
    public SelenideElement getFiziksetEkEkleTab() {
        return container.$(byLinkText(fizikselEkEkleTabName));
    }




    @Step("Ek Metni bul")
    public SelenideElement getEkMetniTextarea() {
        return textarea("Ek Metni");
    }
    @Step("Ek Metni doldur")
    public AltTabs ekMetniDoldur(String text) {
        getEkMetniTextarea().setValue(text);
        return this;
    }

    @Step("Tarama Havuzundan Ekle butonu bul")
    public SelenideElement getTaramaHavuzundanEkleButton() {
        return button("Tarama Havuzundan Ekle");
    }
    @Step("Tarama Havuzundan Ekle butona tıkla")
    public AltTabs taramaHavuzundanEkleTikla() {
        getTaramaHavuzundanEkleButton().shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Tarayıcıdan Ekle butonu bul")
    public SelenideElement getTarayicidanEkleButton() {
        return button("Tarayıcıdan Ekle");
    }
    @Step("Tarayıcıdan Ekle butona tıkla")
    public AltTabs tarayicidanEkleTikla() {
        getTarayicidanEkleButton().shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Dosya Ekle butonu bul")
    public SelenideElement getDosyaEkleButton(){
        return tab.$x("descendant::label[span[normalize-space(.)='Dosya Ekle']]/input");
    }
    @Step("Dosya Ekle alanda dosyayı seç")
    public AltTabs dosyaEkle(String filePath) {
        File file = new File(filePath);
        getDosyaEkleButton().uploadFile(file);
        tab.shouldHave(text(file.getName()));
        return this;
    }
    @Step("Dosya Ekle alanda dosyayı seç")
    public AltTabs dosyaEkleFromClasspath(String filePath) {
        getDosyaEkleButton().uploadFromClasspath(filePath);
        tab.shouldHave(text(filePath));
        return this;
    }

    @Step("Ekle butonu bul")
    public SelenideElement getEkleButton() {
        return button("Ekle");
    }
    @Step("Dosya Ekle butona tıkla")
    public AltTabs ekleButonaTikla() {
        getEkleButton().click();
        return this;
    }

    @Step("Konu alanı bul")
    public SelenideElement getKonuInput() {
        return input("Konu");
    }
    @Step("Konu alanı doldur")
    public AltTabs konuDoldur(String text) {
        getKonuInput().setValue(text);
        return this;
    }

    @Step("Kullanıcı alanı bul")
    public BelgenetElement getKullaniciCombolov() {
        return inputComboLov("Kullanıcı");
    }
    @Step("Konu alanı doldur")
    public AltTabs kullaniciSec(String value, Condition... filterBy) {
        if (filterBy.length > 0) {
            ElementsCollection filtered = getKullaniciCombolov().type(value).getSelectableItems();
            for (Condition condition : filterBy) {
                filtered = filtered.filterBy(condition);
            }
            filtered.shouldHave(CollectionCondition.sizeGreaterThan(0)).first().click();
        } else
            getKullaniciCombolov().selectLov(value);

        return this;
    }

    @Step("Evrak Sayı alanı bul")
    public SelenideElement getEvrakSayiInput() {
        return input("Evrak Sayı");
    }
    @Step("Evrak Sayı alanı doldur")
    public AltTabs evrakSayiDoldur(String text) {
        getEvrakSayiInput().setValue(text);
        return this;
    }

    @Step("Doküman Ara butonu bul")
    public SelenideElement getDokumanAraButton() {
        return button("Doküman Ara");
    }
    @Step("Doküman Ara butona tıkla")
    public AltTabs dokumanAraTikla() {
        getDokumanAraButton().click();
        return this;
    }

    @Step("Aranan Evrak tablosunda işlem yapılacak")
    public SearchTable getArsivdeKayitliEvrakListesi() {
        return arsivdenEvrakAraListesiDataTable;
    }

    @Step("İlişik Metni alanı bul")
    public SelenideElement getIlisikMetniTextarea() {
        return textarea("İlişik Metni");
    }
    @Step("İlişik Metni doldur")
    public AltTabs ilisikMetniDoldur(String text) {
        getIlisikMetniTextarea().setValue(text);
        return this;
    }

    @Step("İlgi Metni alanı bul")
    public SelenideElement getIlgiMetniTextarea() {
        return textarea("İlgi Metni");
    }
    @Step("İlgi Metni doldur")
    public AltTabs ilgiMetniDoldur(String text) {
        getIlgiMetniTextarea().setValue(text);
        return this;
    }

    @Step("Web Adresi alanı bul")
    public SelenideElement getWebAdresiInput() {
        return input("Web Adresi");
    }
    @Step("Web Adresi alanı doldur")
    public AltTabs webAdresiAlan(String text) {
        getWebAdresiInput().setValue(text);
        return this;
    }
    @Step("Web Adresi Ekle butonu bul")
    public SelenideElement getWebAdresiEkleButton() {
        return inputButton("Ekle");
    }
    @Step("Web Adresi Ekle butona tıkla")
    public AltTabs webAdresiEkleTikla() {
        getWebAdresiEkleButton().click();
        return this;
    }

    @Step("Evrak Tarihi başlangıç alanı bul")
    public SelenideElement getEvrakTarihiBasInput() {
        return input("Evrak Tarihi", 0);
    }
    @Step("Evrak Tarihi başlangiç gir")
    public AltTabs evrakTarihiBasGir(String tarih) {
        getEvrakTarihiBasInput().setValue(tarih);
        return this;
    }

    @Step("Evrak Tarihi son alanı bul")
    public SelenideElement getEvrakTarihiSonInput() {
        return input("Evrak Tarihi", 1);
    }
    @Step("Evrak Tarihi başlangiç tarihi gir")
    public AltTabs evrakTarihiSonGir(String tarih) {
        getEvrakTarihiSonInput().setValue(tarih);
        return this;
    }

    @Step("Evrakın Aranacağı Yer alanı bul")
    public SelenideElement getEvrakinAranacagiYerSelect() {
        return input("Evrakın Aranacağı Yer");
    }
    @Step("Evrakın Aranacağı Yeri seç")
    public AltTabs evrakinAranacagiYeriSec(String text) {
        getEvrakinAranacagiYerSelect().selectOption(text);
        return this;
    }

    @Step("Evrak Arama alanı bul")
    public SelenideElement getEvrakAramaInput() {
        return input("Evrak Arama");
    }
    @Step("Evrak Arama alanı doldur")
    public AltTabs evrakAraDoldur(String text) {
        getEvrakAramaInput().setValue(text);
        return this;
    }

    @Step("Aranan Evrak tablosunda işlem yapılacak")
    public SearchTable getSistemdeKayitliEvrakListesi() {
        return sistemdeKayitliEvrakListesiDataTable;
    }

    @Step("Fiziksel Ek Metni bul")
    public SelenideElement getFizikselEkMetniTextarea() {
        return input("Fiziksel Ek Metni");
    }
    @Step("Ek Metni doldur")
    public AltTabs fizikselEkMetniDoldur(String text) {
        getFizikselEkMetniTextarea().setValue(text);
        return this;
    }

}
