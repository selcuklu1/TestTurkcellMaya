package pages.pageComponents;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.MainPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 5.02.2018
 * Açıklama:
 */
public class DagitimHitapDuzenle extends MainPage{

    SelenideElement page;
    SelenideElement container;

    SearchTable dagitimPlaniDetayDataTable;

    public DagitimHitapDuzenle(SelenideElement page) {
        this.page = page;
        this.container = page.$("div[id$='hitapDuzenlemeDialog']");
        dagitimPlaniDetayDataTable = new SearchTable(container.$("div[id$='dagitimPlaniDetayDataTableId']"));
    }

    public DagitimHitapDuzenle() {
        this.container = $("html").$("div[id$='dagitimHitapDuzenlePanel']");
        dagitimPlaniDetayDataTable = new SearchTable(container.$("div[id$='dagitimPlaniDetayDataTableId']"));
    }

    public SelenideElement getTitle(){
        return container.$(".ui-dialog-title");
    }

    public SelenideElement getHitapDuzenlePanel(){
        return container.$("div[id$='pnlHitapDuzenle']");
    }

    public SelenideElement getTableByInputValueText(Condition... conditions){
        ElementsCollection collection = container.$$x("descendant::input")
                .shouldHave(sizeGreaterThan(0)).filterBy(visible);

        for (Condition condition:conditions) {
            collection = collection.filterBy(condition);
        }
        return collection.first().shouldBe(visible).$x("ancestor::table[1]");
        //return container.$x("descendant::table[descendant::input[@value='"+value+"']][last()]");
    }

    /*@Step("\"{value}\"'nın checkbox'u aranır")
    public SelenideElement getCheckboxOfInput(Condition condition){
        return getTableByInputValueText(condition).first().shouldBe(visible).$x("descendant::input[1]");
    }

    @Step("\"{value}\" alanı aranır")
    public SelenideElement getInput(Condition condition, int index){
        return getTableByInputValueText(condition).first().shouldBe(visible).$x("descendant::input[" + index + "]");
    }

    @Step("\"{value}\"'nın Ek alanı aranır")
    public SelenideElement getEkOfInput(Condition condition){
        return getTableByInputValueText(condition).first().shouldBe(visible).$x("descendant::input[last()]");
    }*/

    @Step("\"Checkbox\": {stepDescription}")
    public SelenideElement getCheckboxOfInput(String stepDescription, Condition condition){
        return getTableByInputValueText(condition).$x("descendant::input[1]");
    }

    @Step("\"Input\": {stepDescription}")
    public SelenideElement getInput(String stepDescription, Condition condition, int index){
        return getTableByInputValueText(condition).$x("descendant::input[" + index + "]");
    }

    @Step("\"Ek\": {stepDescription}")
    public SelenideElement getEkOfInput(String stepDescription, Condition condition){
        return getTableByInputValueText(condition).$x("descendant::input[last()]");
    }

    /*@Step("\"{value}\"'nın checkbox'u aranır")
    public SelenideElement getCheckboxOfInput(String value){
        return getTableByInputValueText(value).$x("descendant::input[1]");
    }

    @Step("\"{value}\" alanı aranır")
    public SelenideElement getInput(String value, int index){
        return getTableByInputValueText(value).$x("descendant::input[" + index + "]");
    }

    @Step("\"{value}\"'nın Ek alanı aranır")
    public SelenideElement getEkOfInput(String value){
        return getTableByInputValueText(value).$x("descendant::input[last()]");
    }*/

    @Step("\"BÜYÜK HARF\" butonu aranır")
    public SelenideElement getBuyukHarfButton(){
        return container.$x("descendant::button[.='BÜYÜK HARF']");
    }

    @Step("\"küçük harf\" butonu aranır")
    public SelenideElement getKucukHarfButton(){
        return container.$x("descendant::button[.='küçük harf']");
    }

    @Step("\"İlk Harfleri Büyük\" butonu aranır")
    public SelenideElement getIlkHarfleriBuyukButton(){
        return container.$x("descendant::button[.='İlk Harfleri Büyük']");
    }

    @Step("\"Hitap\" alanı aranır")
    public SelenideElement getHitapTextarea(){
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Hitap']]//textarea");
    }

    @Step("\"Özel Hitap\" alanı aranır")
    public SelenideElement getOzelHitapCheckbox(){
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Özel Hitap']]//input");
    }

    @Step("\"Özel Hitap\" alanın değeri {secilir} seçilir")
    public DagitimHitapDuzenle ozelHitapSec(boolean secilir){
        checkboxSelect(getOzelHitapCheckbox(), secilir);
        return this;
    }

    @Step("\"Adres Hitapta Görünsün\" alanın değeri {secilir} seçilir")
    public DagitimHitapDuzenle adresHitaptaGorunsunSec(boolean secilir){
        checkboxSelect(getAdresHitaptaGorunsunCheckbox(), secilir);
        return this;
    }

    @Step("\"Adres Hitapta Görünsün\" alanı aranır")
    public SelenideElement getAdresHitaptaGorunsunCheckbox(){
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres Hitapta Görünsün']]//input");
    }

    @Step("\"Adres Dağıtımda Görünsün\" alanı aranır")
    public SelenideElement getAdresDagitimdaGorunsunCheckbox(){
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres Dağıtımda Görünsün']]//input");
    }

    @Step("\"Adres\" alanı aranır")
    public SelenideElement getAdresTextarea(){
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Adres']]//textarea");
    }

    @Step("\"Dağıtım Metni\" alanı aranır")
    public SelenideElement getDagitimMetniTextarea(){
        //return container.$x("descendant::input[3]");
        return container.$x("descendant::tr[td/label[normalize-space(.)='Dağıtım Metni']]//textarea");
    }

    @Step("\"Kaydet\" butonu: {stepDescription}")
    public SelenideElement getKaydetButton(String stepDescription){
        return container.$x("descendant::button[.='Kaydet']");
    }

    @Step("Kaydet")
    public DagitimHitapDuzenle kaydet(){
        getKaydetButton("").shouldBe(visible).click();
        container.should(disappear);
        return this;
    }

    @Step("\"İptal\" butonu aranır")
    public SelenideElement getIptalButton(){
        return container.$x("descendant::button[.='İptal']");
    }

    /*public ElementsCollection getDagitimPlaniDetayRows(){
        return container.$$("tr[data-ri][role=row]");
    }*/

    @Step("Dağıtım Planı Detay sırası kontrolü")
    public DagitimHitapDuzenle dagitimPlaniDetaySirasiKontrolu(Map<String, String> dagitimPlanElemanlari){
        //ElementsCollection rows = getDagitimPlaniDetayRows();
        ElementsCollection rows = dagitimPlaniDetayDataTable.findRows().getFoundRows();

        Assert.assertEquals(rows.size(), dagitimPlanElemanlari.size(), "Dağıtım Plan Elemanların sayısı dağıtım plan seçilen sayısı ile aynı olmalı");
        List<String> values = new ArrayList<>(dagitimPlanElemanlari.values());
        for (int i = 0; i < rows.size(); i++) {
            Assert.assertTrue(rows.get(i).has(text(values.get(i))));
        }

        return this;
    }

    @Step("Checkbox aranır")
    public SelenideElement getDagitimPlaniDetayCheckbox(){
        return dagitimPlaniDetayDataTable.getFoundRow().$("input");
    }

    @Step("")
    public String getDagitimPlaniDetayText(){
        return dagitimPlaniDetayDataTable.getFoundRow().text();
    }

    @Step("Evrakta Görünecek Hitap: {stepDescription}")
    public SelenideElement getEvraktaGorunecekHitap(String stepDescription){
        return container.$("table[id$='hitapOnizlemeGrid']");
    }

    @Step("Kayıtlı Hitap: {stepDescription}")
    public SelenideElement getKayitliHitap(String stepDescription){
        return container.$x("descendant::span[.='Kayıtlı Hitap']/ancestor::table[1]");
    }

    @Step("Adres seçilir")
    public DagitimHitapDuzenle adresSec(String adres, String evraktaGorunecekHitap) {
        getAdresTextarea().setValue(adres);
        adresHitaptaGorunsunSec(true);
        getEvraktaGorunecekHitap("Görünecek Hitap \""+ evraktaGorunecekHitap +"\" olmalı").shouldHave(text(evraktaGorunecekHitap));
        kaydet();
        return this;
    }

    @Step("Özel hitap seçilir ve girilir")
    public DagitimHitapDuzenle ozelHitapGirilir(String ozelHitap) {
        ozelHitapSec(true);
        getHitapTextarea().setValue(ozelHitap);
        getEvraktaGorunecekHitap("Görünecek Hitap \""+ ozelHitap +"\" olmalı").shouldHave(text(ozelHitap));
        //getKaydetButton("tıklanır").click();
        kaydet();
        return this;
    }

    @Step("{dagitimElemanlariTipi} dağıtım elemanı eklenir ve ek güncellir")
    public DagitimHitapDuzenle ekGuncelle(String dagitimElemanlari, String ek, String evraktaGorunecekHitap) {
        //dagitimHitapDuzenle.getEkOfInput(String.format("\"%s\" alanın ek \"%s\" ile güncelle",dagitimElemanlariTipi,ek), value(dagitimElemanlari)).setValue(ek);
        getEkOfInput(dagitimElemanlari + " alanın eki " + ek + " yap" ,value(dagitimElemanlari)).setValue(ek);
        getEvraktaGorunecekHitap(String.format("Görünecek Hitap \"%s%s\" olmalı", dagitimElemanlari,ek)).shouldHave(text(evraktaGorunecekHitap));
        //getKaydetButton("tıklanır").click();
        kaydet();
        return this;
    }


}
