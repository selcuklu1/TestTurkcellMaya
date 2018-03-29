package pages.pageComponents;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.WebElementsCollectionWrapper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.MainPage;
import pages.newPages.EvrakDetayiPage;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgentCondition.isTableNavButtonDisabled;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 4.01.2018
 * Açıklama:
 */
public class SearchTable extends MainPage {

    private static String rowXpathLocator = "tr[@role='row']";
    private static String rowCssLocator = "tbody > tr[role=row]";
    private static String columnCssLocator = "td[role=gridcell]";


    //region Class Initialization
    private final SelenideElement parentElement;
    private SelenideElement foundRow;
    private ElementsCollection foundRows;
    private String columnName;
    //endregion
    private int columnIndex = -1;
    private boolean searchInAllPages = false;
    private boolean searchStartFromLast = false;

    public SearchTable(SelenideElement parentElement) {
        this.parentElement = parentElement;
    }

    public SearchTable(By parentElementLocator) {
        this.parentElement = $(parentElementLocator);
    }

    public SearchTable(String parentElementCssLocator) {
        this.parentElement = $(By.cssSelector(parentElementCssLocator));
    }

    @Step
    public SelenideElement getTableHeader() {
        return parentElement.$("thead");
    }

    @Step
    public SelenideElement getTableLabel() {
        return getTableHeader().$("label");
    }

    @Step("İlk sayfaya git butonu aranır")
    public SelenideElement getFirstPageButton() {
        return parentElement.$("span[class~='ui-paginator-first']");
    }

    @Step("Önceki sayfaya git butonu aranır")
    public SelenideElement getPrevPageButton() {
        return parentElement.$("span[class~='ui-paginator-prev']");
    }

    @Step("Sonraki sayfaya git butonu aranır")
    public SelenideElement getNextPageButton() {
        return parentElement.$("span[class~='ui-paginator-next']");
    }

    @Step("Son sayfaya git butonu aranır")
    public SelenideElement getLastPageButton() {
        return parentElement.$("span[class~='ui-paginator-last']");
    }


    @Step("İlk sayfaya git")
    public SearchTable goToFirstPage() {
        if (getFirstPageButton().exists() && getFirstPageButton().is(not(isTableNavButtonDisabled)))
            getFirstPageButton().click();
        return this;
    }

    @Step("Son sayfaya git")
    public SearchTable goToLastPage() {
        if (getLastPageButton().exists() && getLastPageButton().is(not(isTableNavButtonDisabled)))
            getLastPageButton().click();
        return this;
    }


    public ElementsCollection getColumnHeaders() {
        //ElementsCollection columnheaders = parentElement.$$("[id$='SearchTable'] th[role='columnheader']");
        ElementsCollection columnheaders = parentElement.$$("thead th[role='columnheader']");
        if (columnheaders.size() == 0)
            columnheaders = parentElement.$$("thead th");
        return columnheaders;
    }

    @Step("Kolonlar bulunur")
    public SearchTable columnHeaderControl(Condition... conditions) {
        for (Condition condition : conditions) {
            getColumnHeaders().filterBy(condition).shouldHave(sizeGreaterThan(0));
        }
        return this;
    }

    @Step("Kolon index'i aranır")
    public int getColumnIndex(String columnName) {
        ElementsCollection columnheaders = getColumnHeaders();
        columnheaders.filterBy(exactText(columnName)).shouldHave(sizeGreaterThan(0));
        int i;
        for (i = 0; i < columnheaders.size(); i++) {
            if (columnheaders.get(i).has(exactText(columnName)))
                break;
        }
        /*int i = 0;
        for (SelenideElement header : columnheaders) {
            if (header.has(exactText(columnName)))
                break;
            else
                i++;
        }*/
        Assert.assertTrue(i < columnheaders.size(), "\"" + columnName + "\" isimli kolon bulunmalı");
        Allure.addAttachment("Kolon index", String.valueOf(i));
        return i;
    }

    /**
     * @param index start with 0
     * @return
     */
    @Step("Kolonun ismi alınır")
    public String getColumnName(int index) {
        String name = getColumnHeaders().shouldHave(sizeGreaterThanOrEqual(index))
                .get(index).text().trim();
        Allure.addAttachment("Kolon ismi", name);
        return name;
    }

    //region Buttons. On development...
    //Eposta Seçenekleri button .email-icon
    //button .user-block-icon   User! Bloke Et
    //Rapor Al  button .document-getReport

    //Güncelle button .update-icon  button[id*='update']
    @Step("Güncelle butona tıkla")
    public SearchTable guncelleTikla() {
        foundRow.$("button[id*='update']").shouldBe(visible, enabled).click();
        return this;
    }

    //Havale Kural Listesi - Kopyala button .formSablonKopyala    button[id*='copy']
    @Step("Kopyala butona tıkla")
    public SearchTable kopyalaTikla() {
        foundRow.$("button[id*='copy']").shouldBe(visible, enabled).click();
        return this;
    }

    //Aktif Yap     button .to-active-status-icon
    @Step("Aktif Yap butona tıkla")
    public SearchTable aktifYapTikla() {
        foundRow.$("button .to-active-status-icon").shouldBe(visible, enabled).click();
        return this;
    }

    //Passif Yap button .to-passive-status-icon     button[id*='Pasif']
    @Step("Passif Yap butona tıkla")
    public SearchTable pasifYapTikla() {
        foundRow.$("button[id*='Pasif']").shouldBe(visible, enabled).click();
        return this;
    }

    //Sil button .delete-icon  button[id*='delete']
    @Step("Sil butona tıkla")
    public SearchTable silTikla() {
        foundRow.$("button[id*='delete']").shouldBe(visible, enabled).click();
        return this;
    }

    //Doküman ekle button .document-follow
    @Step("Doküman Ekle tıkla")
    public SearchTable dokumanEkleTikla() {
        foundRow.$("button .document-follow").shouldBe(visible, enabled).click();
        return this;
    }

    @Step("Bulunan kayıtta \"İçerik Göster\" butonu {stepDescription}")
    public SelenideElement getIcerikGosterButton(String... stepDescription) {
        return foundRow.$("button[id$='detayGosterButton']");
    }

    @Step("Bulunan kayıtta \"İçerik Göster\" butonu {condition}")
    public SearchTable icerikGosterButton(Condition condition) {
        foundRow.$("button[id$='detayGosterButton']").should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"İçerik Göster\" butona tıklanır")
    public EvrakDetayiPage icerikGoster() {
        getIcerikGosterButton().click();
        return new EvrakDetayiPage();
    }
    
    @Step("Bulunan kayıtta \"Gönderen Notu\" butonu aranır")
    public SelenideElement getGonderenNotuButton(){
        return foundRow.$("button[id$='gonderenNotuButton']");
    }

    @Step("Bulunan kayıtta \"Gönderen Notu\" tooltip kotrollü")
    public SearchTable gonderenNotuTooltip(String tooltipText){
        getGonderenNotuButton().hover();
        $("#tiptip_content").shouldHave(exactText(tooltipText));
        return this;
    }

    @Step("Bulunan kayıtta \"Gönderen Notu\" butona tıklanır")
    public SearchTable gonderenNotu() {
        getGonderenNotuButton().click();
        return this;
    }

    @Step("Bulunan kayıtta \"Versiyonlarını Sorgula\" butonu {stepDescription}")
    public SelenideElement getVersiyonlariniSorgulaButton(String... stepDescription){
        return foundRow.$("button[id$='versiyonlariSorgulaButton']");
    }

    @Step("Bulunan kayıtta \"Versiyonlarını Sorgula\" butona tıklanır")
    public SearchTable versiyonlariniSorgula() {
        getVersiyonlariniSorgulaButton().click();
        return this;
    }

    @Step("Bulunan kayıtta \"Tam Ekran\" butonu {stepDescription}")
    public SelenideElement getTamEkranButton(String... stepDescription) {
        return foundRow.$("button[id$='detayGosterButton']");
    }

    @Step("Bulunan kayıtta \"Tam Ekran\" butonu {condition}")
    public SearchTable tamEkran(Condition condition) {
        foundRow.$("button[id$='detayGosterButton']").should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"Tam Ekran\" butona tıklanır")
    public SearchTable tamEkran() {
        getTamEkranButton().click();
        return this;
    }

    @Step("Bulunan kayıtta \"Versiyonları Karşılaştır\" butonu {stepDescription}")
    public SelenideElement getVersiyonlariKarsilastirButton(String... stepDescription) {
        return foundRow.$("button[id$='degistirilmemisVersiyonlariSorgulaButton']");
    }

    @Step("Bulunan kayıtta \"Versiyonları Karşılaştır\" butonu {condition}")
    public SearchTable versiyonlariKarsilastir(Condition condition) {
        getVersiyonlariKarsilastirButton().should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"Versiyonları Karşılaştır\" butona tıklanır")
    public SearchTable versiyonlariKarsilastir() {
        getVersiyonlariKarsilastirButton().click();
        return this;
    }

    @Step("Bulunan kayıtta \"Versiyonları Karşılaştır\" tooltip kotrollü")
    public SearchTable versiyonlariKarsilastirTooltip(String tooltipText){
        getVersiyonlariKarsilastirButton().hover();
        $("#tiptip_content").shouldHave(exactText(tooltipText));
        return this;
    }


    @Step("Bulunan kayıtta \"İçerik değişti\" ikon {condition}")
    public SearchTable icerikDegistiIkon(Condition condition) {
        foundRow.$("button[id$='icerikDegistiButton']").should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"Dış yazı\" ikon {condition}")
    public SearchTable disYaziIkon(Condition condition) {
        foundRow.$("button span.document-typeDisGiden").should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"İade edilmiştir\" ikon {stepDescription}")
    public SearchTable iadeEdilmistirIkon(Condition condition) {
        foundRow.$("button span.document-typeIade").should(condition);
        return this;
    }

    @Step("Bulunan kayıtta \"İade edilmiştir\" ikon {stepDescription}")
    public SearchTable iadeEdilmistirIkon1(String... stepDescription) {
        foundRow.$("button span.document-typeIade");
        return this;
    }

    @Step("Bulunan kayıtta \"İade edilmiştir\" ikon {stepDescription}")
    public SearchTable iadeEdilmistirIkon2(String... stepDescription) {
        foundRow.$("button span.document-typeIade1");
        return this;
    }




    
    @Step
    public SelenideElement getYeniKayitEkleButton() {
//        return parentElement.$x("descendant::button[span[contains(@class,'add-icon')]]");
        return parentElement.$("button .add-icon");
    }

    @Step("Yeni Kayıt Ekle butona tıkla")
    public SearchTable yeniKayitEkleTikla() {
        getYeniKayitEkleButton().shouldBe(visible, enabled).click();
        return this;
    }

    //endregion

    public SearchTable foundRow() {
        /*if (foundRow == null)
            if (foundRows == null)
                throw new RuntimeException("Bulunan satır yok");
            else
                foundRow = foundRows.first();*/

        return this;
    }

    public SearchTable foundRows() {
        if (foundRows == null)
            throw new RuntimeException("Bulunan satırlar yok");
        return this;
    }

    public SelenideElement getFoundRow() {
        return foundRow;
    }

    public ElementsCollection getFoundRows() {
        return foundRows;
    }

    @Step("Kayıt Bulunamamıştır yazısı var mı?")
    public boolean isRowsExist() {
        return !parentElement.find(Selectors.byText("Kayıt Bulunamamıştır")).exists()
                && parentElement.$$(rowCssLocator + " " + columnCssLocator).size() != 0;
    }

    @Step("Arama ayarı: tüm sayfalarda aranır")
    public SearchTable searchInAllPages(boolean searchInAllPages) {
        this.searchInAllPages = searchInAllPages;
        return this;
    }

    @Step("Arama ayarı: son sayfadan başlanır")
    public SearchTable searchStartFromLastPage(boolean searchStartFromLast) {
        this.searchStartFromLast = searchStartFromLast;
        return this;
    }

    @Step("Arama ayarı: kolon ismine göre aranır")
    public SearchTable searchByColumnName(String columnName) {

        this.columnName = columnName;
        columnIndex = getColumnIndex(columnName);
        return this;
    }

    @Step("Arama ayarı: kolon index'a göre aranır")
    public SearchTable searchByColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
        columnName = getColumnName(columnIndex);
        return this;
    }

    @Step("Tablodaki satırlar aranır")
    public SearchTable findRows(Condition... conditions) {
        foundRow = null;
        foundRows = null;
        String rowCssLocator = SearchTable.rowCssLocator;

        boolean searchByColumn = (columnIndex > -1);

        ArrayList<WebElement> rows = new ArrayList<>();

        //[data-ri] varsa o ana row.
        if (parentElement.$$(rowCssLocator + "[data-ri]").size() > 0)
            rowCssLocator = rowCssLocator + "[data-ri]";

        if (searchStartFromLast)
            goToLastPage();
        else
            goToFirstPage();

        SelenideElement pageNavigationButton = searchStartFromLast ? getPrevPageButton() : getNextPageButton();

        ElementsCollection collection = searchByColumn ?
                parentElement.$$(rowCssLocator + " " + columnCssLocator + ":nth-child(" + columnIndex + 1 + ")")
                : parentElement.$$(rowCssLocator);

        while (true) {
            for (Condition condition : conditions)
                collection = collection.filterBy(condition);

            takeScreenshot();
            if (collection.size() > 0 || !searchInAllPages || pageNavigationButton.is(isTableNavButtonDisabled))
                break;

            pageNavigationButton.click();
        }

        //If search by column get column rows
        if (searchByColumn) {
            for (SelenideElement column : collection)
                rows.add(column.$x("ancestor::" + rowXpathLocator + "[1]"));
            collection = new ElementsCollection(new WebElementsCollectionWrapper(rows));
        }

        Allure.addAttachment("Filtrelere göre bulunan satırlar", collection.texts().toString());
        foundRows = collection;
        foundRow = collection.first();
        return this;
    }

    @Step("Evrağı bul ve seç")
    public SearchTable findRowAndSelect(Condition... conditions){
        findRows(conditions).getFoundRow().click(1,1);
        return this;
    }

    public SearchTable useFoundRow(int index) {
        foundRow = foundRows.get(index);
        return this;
    }

    public SearchTable useFirstFoundRow() {
        /*if (foundRows == null)
            throw new NotFoundException("Satırlar bulunamadı");*/
        foundRow = foundRows.first();
        return this;
    }

    public SearchTable useLastFoundRow() {
        foundRow = foundRows.last();
        return this;
    }

    @Step("Satırdaki kolonlar bulunur")
    public ElementsCollection getColumnValues() {
        return foundRow.$$(columnCssLocator);
    }

    @Step("{columnIndex} kolon bulunur")
    public SelenideElement getColumnValue(int columnIndex) {
        //return foundRows.get((rowIndex.length > 0)? rowIndex[0]:0).$$("td[role=gridcell]").get(columnIndex);
        return foundRow.$$(columnCssLocator).get(columnIndex);
    }

    @Step("{columnName} isimli kolon bulunur")
    public SelenideElement getColumnValue(String columnName) {
        if (columnIndex == -1)
            columnIndex = getColumnIndex(columnName);
        //return foundRows.get((rowIndex.length > 0)? rowIndex[0]:0).$$("td[role=gridcell]").get(columnIndex);
        return foundRow.$$(columnCssLocator).get(columnIndex);
    }

    public ElementsCollection rowsToElementsCollection() {
        return foundRows;
    }

    public SelenideElement rowToSelenideElement() {
        return foundRow;
    }

    @Step("Bulunan kayıt kontrolü {condition}")
    public SearchTable should(Condition... condition) {
        foundRow.should(condition);
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition}")
    public SearchTable shouldHave(Condition... condition) {
        foundRow.shouldHave(condition);
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition}")
    public SearchTable shouldBe(Condition... condition) {
        foundRow.shouldBe(condition);
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition} olmamalı")
    public SearchTable shouldNot(Condition... condition) {
        foundRow.shouldNot(condition);
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition} olmamalı")
    public SearchTable shouldNotHave(Condition... condition) {
        foundRow.shouldNotHave(condition);
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition} olmamalı")
    public SearchTable shouldNotBe(Condition... condition) {
        foundRow.shouldNotBe(condition);
        return this;
    }

    @Step("Bulunan kayıt sayısı kontrolü")
    public SearchTable shouldHaveSize(int expectedSize) {
        foundRows.shouldHave(CollectionCondition.size(expectedSize));
        return this;
    }

    @Step("Bulunan kayıt kontrolü {condition}")
    public boolean is(Condition condition) {
        return foundRow.is(condition);
    }

    @Step("Bulunan kayıtların kontrolü {conditions}")
    public SearchTable shouldBe(CollectionCondition... conditions) {
        foundRows.shouldBe(conditions);
        return this;
    }

    @Step("Bulunan kayıtların kontrolü {conditions}")
    public SearchTable shouldHave(CollectionCondition... conditions) {
        foundRows.shouldHave(conditions);
        return this;
    }

}
