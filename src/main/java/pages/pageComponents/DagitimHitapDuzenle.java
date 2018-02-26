package pages.pageComponents;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 5.02.2018
 * Açıklama:
 */
public class DagitimHitapDuzenle extends MainPage {

    SelenideElement page;
    SelenideElement container;

    SearchTable dagitimPlaniDetayDataTable;

    public DagitimHitapDuzenle(SelenideElement page) {
        this.page = page;
        this.container = page.$("div[id$='hitapDuzenlemeDialog']");
        dagitimPlaniDetayDataTable = new SearchTable(container.$("div[id$='dagitimPlaniDetayDataTableId']"));
    }

    public DagitimHitapDuzenle() {
        //this.container = $("html").$("div[id$='dagitimHitapDuzenlePanel']");
        page = $("html");
        this.container = page.$("div[id$='hitapDuzenlemeDialog']");
        dagitimPlaniDetayDataTable = new SearchTable(container.$("div[id$='dagitimPlaniDetayDataTableId']"));
    }

    public SelenideElement getTitle() {
        return container.$(".ui-dialog-title");
    }

    public SelenideElement getHitapDuzenlePanel() {
        return container.$("div[id$='pnlHitapDuzenle']");
    }

    public SelenideElement getParentTableOfDagitimHitapInputByInputText(Condition... conditions) {
        ElementsCollection collection = container.$$x("descendant::input[@type='text']")
                .shouldHave(sizeGreaterThan(0)).filterBy(visible);

        for (Condition condition : conditions) {
            collection = collection.filterBy(condition);
        }
        return collection.first().shouldBe(visible).$x("ancestor::table[1]");
        //return container.$x("descendant::table[descendant::input[@value='"+value+"']][last()]");
    }

    /*@Step("\"{value}\"'nın checkbox'u aranır")
    public SelenideElement getCheckboxOfDagitimHitapInput(Condition condition){
        return getParentTableOfDagitimHitapInputByInputText(condition).first().shouldBe(visible).$x("descendant::input[1]");
    }

    @Step("\"{value}\" alanı aranır")
    public SelenideElement getDagitimHitapInput(Condition condition, int index){
        return getParentTableOfDagitimHitapInputByInputText(condition).first().shouldBe(visible).$x("descendant::input[" + index + "]");
    }

    @Step("\"{value}\"'nın Ek alanı aranır")
    public SelenideElement getEkOfDagitimHitapInput(Condition condition){
        return getParentTableOfDagitimHitapInputByInputText(condition).first().shouldBe(visible).$x("descendant::input[last()]");
    }*/

    @Step("\"Checkbox\": {stepDescription}")
    public SelenideElement getCheckboxOfDagitimHitapInput(String stepDescription, Condition condition) {
        return getParentTableOfDagitimHitapInputByInputText(condition).$x("descendant::input[@type='checkbox'][1]");
    }

    @Step("\"Input\": {stepDescription}")
    public SelenideElement getDagitimHitapInput(String stepDescription, Condition condition, int index) {
        return getParentTableOfDagitimHitapInputByInputText(condition).$x("descendant::input[@type='text'][" + index + "]");
    }

    @Step("\"Ek\": {stepDescription}")
    public SelenideElement getEkOfDagitimHitapInput(String stepDescription, Condition condition) {
        return getParentTableOfDagitimHitapInputByInputText(condition).$x("descendant::input[@type='text'][last()]");
    }

    @Step("\"Ek\" değeri alınır")
    public String getEkValue(Condition condition) {
        return getEkOfDagitimHitapInput("", condition).getValue();
    }

    /*@Step("\"{value}\"'nın checkbox'u aranır")
    public SelenideElement getCheckboxOfDagitimHitapInput(String value){
        return getParentTableOfDagitimHitapInputByInputText(value).$x("descendant::input[1]");
    }

    @Step("\"{value}\" alanı aranır")
    public SelenideElement getDagitimHitapInput(String value, int index){
        return getParentTableOfDagitimHitapInputByInputText(value).$x("descendant::input[" + index + "]");
    }

    @Step("\"{value}\"'nın Ek alanı aranır")
    public SelenideElement getEkOfDagitimHitapInput(String value){
        return getParentTableOfDagitimHitapInputByInputText(value).$x("descendant::input[last()]");
    }*/

    @Step("\"BÜYÜK HARF\" butonu aranır")
    public SelenideElement getBuyukHarfButton() {
        return container.$x("descendant::button[.='BÜYÜK HARF']");
    }

    @Step("\"küçük harf\" butonu aranır")
    public SelenideElement getKucukHarfButton() {
        return container.$x("descendant::button[.='küçük harf']");
    }

    @Step("\"İlk Harfleri Büyük\" butonu aranır")
    public SelenideElement getIlkHarfleriBuyukButton() {
        return container.$x("descendant::button[.='İlk Harfleri Büyük']");
    }

    @Step("\"Hitap\" alanı aranır")
    public SelenideElement getHitapTextarea() {
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Hitap']]//textarea");
    }

    @Step("\"Özel Hitap\" alanı aranır")
    public SelenideElement getOzelHitapCheckbox() {
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Özel Hitap']]//input");
    }

    @Step("\"Özel Hitap\" alanın değeri {secilir} seçilir")
    public DagitimHitapDuzenle ozelHitapSec(boolean secilir) {
        checkboxSelect(getOzelHitapCheckbox(), secilir);
        return this;
    }

    @Step("\"Adres Hitapta Görünsün\" alanı aranır")
    public SelenideElement getAdresHitaptaGorunsunCheckbox() {
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres Hitapta Görünsün']]//input");
    }

    @Step("\"Adres Hitapta Görünsün\" alanın değeri {secilir} seçilir")
    public DagitimHitapDuzenle adresHitaptaGorunsunSec(boolean secilir) {
        checkboxSelect(getAdresHitaptaGorunsunCheckbox(), secilir);
        return this;
    }

    @Step("\"Adres Dağıtımda Görünsün\" alanı aranır")
    public SelenideElement getAdresDagitimdaGorunsunCheckbox() {
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres Dağıtımda Görünsün']]//input");
    }

    @Step("\"Adres Dağıtımda Görünsün\" alanın değeri {secilir} seçilir")
    public DagitimHitapDuzenle adresDagitimdaGorunsunSec(boolean secilir) {
        checkboxSelect(getAdresDagitimdaGorunsunCheckbox(), secilir);
        return this;
    }

    @Step("\"Adres\" alanı aranır")
    public SelenideElement getAdresTextarea() {
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres']]//textarea");
    }

    @Step("\"Dağıtım Metni\" alanı aranır")
    public SelenideElement getDagitimMetniTextarea() {
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Dağıtım Metni']]//textarea");
    }

    @Step("\"Kaydet\" butonu: {stepDescription}")
    public SelenideElement getKaydetButton(String stepDescription) {
        return container.$x("descendant::button[.='Kaydet']");
    }

    @Step("Kaydet")
    public DagitimHitapDuzenle kaydet() {
        /*if (getKaydetButton("").isDisplayed())
            getKaydetButton("").click();
        else
            //Uzun özel hitap ya da adres girince Kaydet butonu ekran dışında kalıyor ve sadece JS jquery ile çalışıyor. Scroll yok - Belgenet Defect.
            ((JavascriptExecutor) getWebDriver())
                    .executeScript("$(\"div[id$='hitapDuzenlemeDialog'] button:contains('Kaydet')\").trigger('click')");*/

        clickJs(getKaydetButton(""));
        container.should(disappear);
        return this;
    }

    @Step("\"İptal\" butonu aranır")
    public SelenideElement getIptalButton() {
        return container.$x("descendant::button[.='İptal']");
    }

    /*public ElementsCollection getDagitimPlaniDetayRows(){
        return container.$$("tr[data-ri][role=row]");
    }*/

    @Step("Dağıtım Planı Detay sırası kontrolü")
    public DagitimHitapDuzenle dagitimPlaniDetaySirasiKontrolu(Map<String, String> dagitimPlanElemanlari) {
        //ElementsCollection rows = getDagitimPlaniDetayRows();
        ElementsCollection rows = dagitimPlaniDetayDataTable.findRows().getFoundRows();

        Assert.assertEquals(rows.size(), dagitimPlanElemanlari.size(), "Dağıtım Plan Elemanların sayısı dağıtım plan seçilen sayısı ile aynı olmalı");
        List<String> values = new ArrayList<>(dagitimPlanElemanlari.values());
        for (int i = 0; i < rows.size(); i++) {
            Assert.assertTrue(rows.get(i).has(text(values.get(i))));
        }

        return this;
    }

    @Step("Dağıtım Planı Detay Seçili dağıtım listesinin adı bu alanda görüntülendiği. Kullanıcının bu alanı değişteremediği görülür")
    public DagitimHitapDuzenle dagitimPlaniDetayListesiKontrolu(Map<String, String> dagitimPlanElemanlari) {
        ElementsCollection rows = dagitimPlaniDetayDataTable.findRows().getFoundRows();

        Assert.assertEquals(rows.size(), dagitimPlanElemanlari.size(), "Dağıtım Plan Elemanların sayısı dağıtım plan seçilen sayısı ile aynı olmalı");

        List<String> values = new ArrayList<>(dagitimPlanElemanlari.values());
        for (int i = 0; i < rows.size(); i++) {
            Assert.assertTrue(rows.get(i).has(text(values.get(i))));
            Assert.assertEquals(rows.get(i).$(byText(values.get(i))).getTagName(),"div"," dağıtım eleman tagname Div olmalı, Kullanıcının bu alanı değişteremediği görülür");
        }

        return this;
    }

    @Step("Checkbox aranır")
    public SelenideElement getDagitimPlaniDetayCheckbox() {
        return dagitimPlaniDetayDataTable.getFoundRow().$("input");
    }

    @Step("")
    public String getDagitimPlaniDetayText() {
        return dagitimPlaniDetayDataTable.getFoundRow().text();
    }

    @Step("Evrakta Görünecek Hitap: {stepDescription}")
    public SelenideElement getEvraktaGorunecekHitap(String stepDescription) {
        return container.$("table[id$='hitapOnizlemeGrid']");
        //adres eklenince .preformatted_with_line_break 2 element oluyor. bir hitap için bir adres için
    }

    @Step("Kayıtlı Hitap: {stepDescription}")
    public SelenideElement getKayitliHitap(String stepDescription) {
        return container.$x("descendant::span[.='Kayıtlı Hitap']/ancestor::table[1]");
    }

    @Step("Adres seçilir")
    public DagitimHitapDuzenle adresSec(String adres, String evraktaGorunecekHitap) {
        getAdresTextarea().setValue(adres);
        getEvraktaGorunecekHitap("Görünecek Hitap \"" + evraktaGorunecekHitap + "\" olmalı").shouldHave(text(evraktaGorunecekHitap));
        return this;
    }

    @Step("Adres seçilir")
    public DagitimHitapDuzenle adresSec(String adres) {
        getAdresTextarea().setValue(adres);
        return this;
    }

    public SelenideElement getOzelHitapMaxKarakterElement(){
        return container.$("span[id$='ozelHitapTextArea']");
    }

    @Step("Özel Hitap maksimum karakter sayisi alınır")
    public String ozelHitapMaxKarakterSayisi(){
        return getNumberFromText(getOzelHitapMaxKarakterElement().shouldBe(visible).text());
    }

    @Step("Özel hitap seçilir ve girilir")
    public DagitimHitapDuzenle ozelHitapGirilir(String ozelHitap) {
        //ozelHitapSec(true);
        getHitapTextarea().setValue(ozelHitap);
        getEvraktaGorunecekHitap("Görünecek Hitap \"" + ozelHitap + "\" olmalı").shouldHave(textCaseSensitive(ozelHitap));
        //getKaydetButton("tıklanır").click();
        //kaydet();
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve ek güncellir")
    public DagitimHitapDuzenle ekGuncelle(String dagitimElemanlari, String ek) {
        getEkOfDagitimHitapInput(dagitimElemanlari + " alanın eki " + ek + " yap", value(dagitimElemanlari)).setValue(ek);
        getEvraktaGorunecekHitap(String.format("Görünecek Hitap \"%s%s\" olmalı", dagitimElemanlari, ek)).shouldHave(textCaseSensitive(dagitimElemanlari + ek));
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve ek güncellir")
    public DagitimHitapDuzenle ekGuncelle(String dagitimElemanlari, String ek, String evraktaGorunecekHitap) {
        //dagitimHitapDuzenle.getEkOfDagitimHitapInput(String.format("\"%s\" alanın ek \"%s\" ile güncelle",dagitimElemanlariTipi,ek), value(dagitimElemanlari)).setValue(ek);
        getEkOfDagitimHitapInput(dagitimElemanlari + " alanın eki " + ek + " yap", value(dagitimElemanlari)).setValue(ek);
        getEvraktaGorunecekHitap(String.format("Görünecek Hitap \"%s%s\" olmalı", dagitimElemanlari, ek)).shouldHave(textCaseSensitive(evraktaGorunecekHitap));
        //getKaydetButton("tıklanır").click();
        //kaydet();
        return this;
    }
    
    @Step("")
    public DagitimHitapDuzenle getDagitimHitap(){
        return this;
    }


}
