package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.DagitimHitapDuzenle;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstYazi;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.alanlar.*;

import java.util.Arrays;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class BilgilerTab extends MainPage {

    private final static String tabName = "Bilgileri";
    private SelenideElement container;
    private SearchTable searchTable;

    private By deleteButtonuSelector = By.cssSelector(".delete-icon");
    private By updateButtonuSelector = By.cssSelector(".update-icon");


    public BilgilerTab() {
        container = $("html");
    }

    public BilgilerTab(SelenideElement container) {
        this.container = container;
    }

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    public void shouldHave(String text) {
        getContainer().shouldHave(text(text));
    }

    //SelenideElement tab = $x("//td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='"+ tabName +"']]//button");
    @Step(tabName + " tabı aç")
    public BilgilerTab openTab(boolean... clickIfOpen) {
        if (clickIfOpen.length > 0 || !getTabButtonTextElement().attr("class").equals("tabMenuContainerSelected"))
            getTabButton().click();

        return this;
    }

    @Step(tabName + " sekme butonu bul")
    public SelenideElement getTabButton() {
        return getTabButtonTextElement().$("button");
    }

    private SelenideElement getTabButtonTextElement() {
        return getContainer().$x("descendant::td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='" + tabName + "']]");
    }

    //******************************************************

    //region Konu Kodu
//    BelgenetElement konuKoduCombolov = comboLov("input[id$='konuKoduLov:LovText']");

    @Step("Konu Kodu alan")
    public BelgenetElement getKonuKodu() {
        return comboLov(getContainer(), "input[id$='konuKoduLov:LovText']");
    }

    @Step("Konu Kodu seçilir")
    public BilgilerTab konuKoduSec(String text) {
        getKonuKodu().selectLov(text);
        //getKonuKodu().closeTreePanel();
        return this;
    }

    @Step("Konu Kodu doldurulur")
    public BilgilerTab konuKoduDoldurFilterByTitle(String text, Condition filter) {
        getKonuKodu().type(text).getTitleItems().filterBy(filter).first().click();
        getKonuKodu().closeTreePanel();
        return this;
    }

    @Step("Konu Kodu doldurulur")
    public BilgilerTab konuKoduDoldurFilterByDetail(String text, Condition filterBy) {
        getKonuKodu().type(text).getDetailItems().filterBy(filterBy).first().click();
        getKonuKodu().closeTreePanel();
        return this;
    }
    //endregion

    //******************************************************

    //region Konu
//    SelenideElement konuTextarea = $("textarea[id$='konuTextArea']");

    @Step("Konu Kodu alanı temizlenir")
    public BilgilerTab konuKoduTemizle() {
        getKonuKodu().clearAllSelectedItems();
        return this;
    }

    public SelenideElement getKonuTextarea() {
        return getContainer().$("textarea[id$='konuTextArea']");
    }

    @Step("Konu doldurulur")
    public BilgilerTab konuDoldur(String text) {
        /*konuTextarea.clear();
        konuTextarea.sendKeys(text);*/
        getKonuTextarea().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Evrak Türü
//    SelenideElement konuTextarea = $("textarea[id$='konuTextArea']");

    @Step("Konu temizlenir")
    public BilgilerTab konuTemizle() {
        getKonuTextarea().clear();
        return this;
    }

    public SelenideElement getEvrakTuru() {
        return getContainer().$("select[id$='evrakTuruCombo']");
    }
    //endregion

    //******************************************************

    @Step("Evrak Türü doldurulur")
    public BilgilerTab evrakTuruSec(String text) {
        getEvrakTuru().selectOption(text);
        return this;
    }

    //region Kaldırılacak Klasörler
    @Step("Kaldırılacak Klasörler")
    public BelgenetElement getKaldiralacakKlasorlerCombolov() {
        return comboLov(getContainer(), "input[id$='eklenecekKlasorlerLov:LovText']");
    }

    @Step("Kaldırılacak Klasörleri seçilir")
    public BilgilerTab kaldiralacakKlasorleriSec(String text) {
        getKaldiralacakKlasorlerCombolov().selectLov(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Kayıt Tarihi
    //SelenideElement kayitTarihiDateInput = $("input[id$='kayitTarih_input']");

    @Step("Kaldırılacak Klasörleri temizlenir")
    public BilgilerTab kaldiralacakKlasorleriTemizle() {
        getKaldiralacakKlasorlerCombolov().clearAllSelectedItems().sendKeys(Keys.TAB);
        return this;
    }

    @Step("Kayit Tarihi")
    public SelenideElement getKayitTarihi() {
        return getContainer().$("input[id$='kayitTarih_input']");
    }

    @Step("Kayit Tarihi doldurulur")
    public BilgilerTab kayitTarihiDoldur(String text) {
        getKayitTarihi().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Evrak Dili
    //SelenideElement evrakDiliSelect = $("select[id$='evrakDili']");

    @Step("Kayit Tarihi değeri alınır")
    public String kayitTarihiDegeriAl() {
        String text = getKayitTarihi().text();
        Allure.addAttachment("Değer", text);
        return text;
    }

    @Step("Evrak Dili")
    public SelenideElement getEvrakDili() {
        return getContainer().$("select[id$='evrakDili']");
    }

    @Step("Evrak Dili seçilir")
    public BilgilerTab evrakDiliSec(String text) {
        getEvrakDili().selectOption(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Gizlilik Derecesi
    //SelenideElement gizlilikDerecesiSelect = $("select[id$=guvenlikKodu]");

    @Step("Evrak Dili seçilir")
    public BilgilerTab evrakDiliSec(EvrakDili evrakDili) {
        getEvrakDili().selectOption(evrakDili.getOptionText());
        return this;
    }

    @Step("Gizlilik Derecesi")
    public SelenideElement getGizlilikDerecesi() {
        return getContainer().$("select[id$=guvenlikKodu]");
    }

    /*@Step("Gizlilik Derecesi seçilir")
    public BilgilerTab gizlilikDerecesiSec(Enum gizlilikDerecesi){
        if (!gizlilikDerecesi.getClass().equals(GizlilikDerecesi.class))
            throw new RuntimeException("Yanlış input enum. Olması gereken: " + GizlilikDerecesi.class.toString());

        getGizlilikDerecesi().selectOption(((GizlilikDerecesi) gizlilikDerecesi).getOptionText());
        return this;
    }*/

    @Step("Gizlilik Derecesi seçilir")
    public BilgilerTab gizlilikDerecesiSec(String text) {
        getGizlilikDerecesi().selectOption(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Kanun Kapsam Tipi
    //ElementsCollection kanunKapsamTipiRadioButtons = $$("table[id$='kanunKapsamTipiRadio'] input");

    @Step("Gizlilik Derecesi seçilir")
    public BilgilerTab gizlilikDerecesiSec(GizlilikDerecesi gizlilikDerecesi) {
        getGizlilikDerecesi().selectOption(gizlilikDerecesi.getOptionText());
        return this;
    }

    @Step("Kanun Kapsam Tipi radio")
    public ElementsCollection getKanunKapsamTipiRadioButtons() {
        return getContainer().$$("table[id$='kanunKapsamTipiRadio'] input");
    }

    @Step("Kanun Kapsam Tipi seçilir")
    public BilgilerTab kanunKapsamTipiSec(String radioText) {
        SelenideElement radio = getKanunKapsamTipiRadioButtons().first();
        radio.shouldBe(visible);
        switch (radioText) {
            case "Normalınır":
                radio.selectRadio("N");
                break;
            case "Bilgi Edinme Kanunu":
                radio.selectRadio("B");
                break;
            case "Kişisel Verilerin Korunması Kanunu":
                radio.selectRadio("K");
                break;
            default:
                throw new NotFoundException(radioText + " radio button bulunamadı");
        }
        return this;
    }
    //endregion

    //******************************************************

    //region Evrak Sayı Ek Metni
    //SelenideElement evrakSayiEkMetniInput = $("input[id$=evrakSayiEkMetniInputText]");

    @Step("Kanun Kapsam Tipi seçilen değeri")
    public SelenideElement kanunKapsamTipiSecilenDeger() {
        SelenideElement selectedRadio = getKanunKapsamTipiRadioButtons()
                .filterBy(attribute("checked", "true"))
                .filterBy(visible)
                .first();
        String id = selectedRadio.attr("id");
        Allure.addAttachment("Değer", $("label[for='" + id + "']").text());
        return $("label[for='" + id + "']");
    }

    @Step("Evrak Sayı Ek Metni")
    public SelenideElement getEvrakSayiEkMetni() {
        return getContainer().$("input[id$=evrakSayiEkMetniInputText]");
    }
    //endregion

    //******************************************************

    //region İvedilik
    //SelenideElement ivedilikSelect = $("select[id$=ivedilik]");

    @Step("Evrak Sayı Ek Metni doldurulur")
    public BilgilerTab evrakSayiEkMetniDoldur(String text) {
        getEvrakSayiEkMetni().setValue(text);
        return this;
    }

    @Step("İvedilik")
    public SelenideElement getIvedilik() {
        return getContainer().$("select[id$=ivedilik]");
    }

    @Step("İvedilik seçilir")
    public BilgilerTab ivedilikSec(Ivedilik ivedilik) {
        getIvedilik().selectOption(ivedilik.getOptionText());
        return this;
    }
    //endregion

    //******************************************************

    //region Miat
    //SelenideElement miatDateInput = $("input[id$=miatCalendar_input]");
    //SelenideElement miatTemizleButton = $("button[id$=miatTarihTemizle]");

    @Step("İvedilik seçilir")
    public BilgilerTab ivedilikSec(String text) {
        getIvedilik().selectOption(text);
        return this;
    }

    @Step("Miat")
    public SelenideElement getMiatDateInput() {
        return getContainer().$("input[id$=miatCalendar_input]");
    }

    @Step("Miat Temizle buton")
    public SelenideElement getMiatTemizleButton() {
        return getContainer().$("button[id$=miatTarihTemizle]");
    }

    @Step("Miat doldurulur")
    public BilgilerTab miatDoldur(String text) {
        getMiatDateInput().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Açıklama
    //SelenideElement aciklamaTextarea = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Açıklama']]//textarea");

    @Step("Miat temizlenir")
    public BilgilerTab miatTemizle() {
        getMiatTemizleButton().click();
        getMiatDateInput().shouldBe(empty);
        return this;
    }

    @Step("Açıklama textarea")
    public SelenideElement getAciklamaTextarea() {
        return getContainer().$x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Açıklama']]//textarea");
    }
    //endregion

    //******************************************************

    //region Bilgi Seçim Tipi
    //SelenideElement bilgiSecimTipiSelect = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Bilgi Seçim Tip']]//select");

    @Step("Açıklamayı doldurulur")
    public BilgilerTab aciklamaDoldur(String text) {
        getAciklamaTextarea().setValue(text);
        return this;
    }

    @Step("Bilgi Seçim Tipi select")
    public SelenideElement getBilgiSecimTipiSelect() {
        return getContainer().$x("descendant::tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Bilgi Seçim Tipi']]//select");
    }

    @Step("Bilgi Seçim Tipi seçilir")
    public BilgilerTab bilgiSecimTipiSec(String text) {
        getBilgiSecimTipiSelect().selectOption(text);
        return this;
    }
    //endregion

    //******************************************************

    @Step("Bilgi Seçim Tipi seçilir")
    public BilgilerTab bilgiSecimTipiSec(BilgiSecimTipi bilgiSecimTipi) {
        getBilgiSecimTipiSelect().selectOption(bilgiSecimTipi.getOptionText());
        return this;
    }

    //region Bilgi
    @Step("Bilgi alan combolov")
    public BelgenetElement getBilgiCombolov() {
        return comboLov(getContainer(), "input[id$='bilgiLov:LovText']");
    }

    @Step("Bilgi seçilir")
    public BilgilerTab bilgiSec(String text, boolean... exactValue) {

        if (exactValue.length > 0 && exactValue[0])
            getBilgiCombolov().selectExactLov(text);
        else
            getBilgiCombolov().selectLov(text);

        return this;
    }

    @Step("{bilgiSecimTipi.optionText}/{bilgi} bilgi seçilir")
    public BilgilerTab bilgiSec(BilgiSecimTipi bilgiSecimTipi, String bilgi) {
        bilgiSecimTipiSec(bilgiSecimTipi.getOptionText());
        getBilgiCombolov().selectLov(bilgi);
        return this;
    }

    @Step("Seçilen Bilgi alan kotrolü")
    public BilgilerTab secilenBilgiAlanKotrolu(Condition... conditions){
        ElementsCollection collection = getBilgiCombolov().getSelectedTitles();
        for (Condition condition:conditions) {
            collection = collection.filterBy(condition);
        }
        collection.shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Seçilen Bilginin \"Listeden Çıkar\" butonu")
    public SelenideElement getBilgiGeregiListedenCikarButton(Condition... secilenAramaKriteri){
        ElementsCollection collection = getBilgiCombolov().getSelectedItems();
        for (Condition condition:secilenAramaKriteri) {
            collection = collection.filterBy(condition);
        }
        return collection.shouldHave(sizeGreaterThan(0)).first().$(deleteButtonuSelector);
    }

    @Step("Seçilen Bilginin \"Dağıtım Hitap Düzenleme\" butonu")
    public SelenideElement getBilgiDagitimHitapDuzenlemeButton(Condition... secilenAramaKriteri){
        ElementsCollection collection = getBilgiCombolov().getSelectedItems();
        for (Condition condition:secilenAramaKriteri) {
            collection = collection.filterBy(condition);
        }
        return collection.shouldHave(sizeGreaterThan(0)).first().$(updateButtonuSelector);
    }

    @Step("Seçilen Bilginin \"Dağıtım Hitap Düzenleme\" butona tiklanır")
    public DagitimHitapDuzenle bilgiDagitimHitapDuzenlemeTiklanir(Condition... secilenAramaKriteri){
        getBilgiDagitimHitapDuzenlemeButton(secilenAramaKriteri).shouldBe(visible).click();
        return new DagitimHitapDuzenle();
    }


    @Step("Gereği alanıda değeri seçilecek değer boş mu")
    public BilgilerTab bilgiDegerSecilemez(String text) {
        Assert.assertTrue(getBilgiCombolov().type(text).isEmpty());
        return this;
    }

    //endregion

    //******************************************************

    //region Gereği Seçim Tipi
    //SelenideElement geregiSecimTipiSelect = $x("//tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Gereği Seçim Tipi']]//select");

    @Step("Bilgi alanı temizlenir")
    public BilgilerTab bilgiTemizle() {
        getBilgiCombolov().clearAllSelectedItems();
        return this;
    }

    @Step("Gereği Seçim Tipi select")
    public SelenideElement getGeregiSecimTipi() {
        return getContainer().$x("//tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Gereği Seçim Tipi']]//select");
    }

    @Step("Gereği Seçim Tipi seçilir")
    public BilgilerTab geregiSecimTipiSec(String text) {
        getGeregiSecimTipi().selectOption(text);
        return this;
    }

    @Step("Gereği Seçim Tipi seçilir")
    public BilgilerTab geregiSecimTipiSec(GeregiSecimTipi geregiSecimTipi) {
        getGeregiSecimTipi().selectOption(geregiSecimTipi.getOptionText());
        return this;
    }

    @Step("Seçilen Gereği alan kotrolü")
    public BilgilerTab secilenGeregiAlanKotrolu(Condition... conditions){
        ElementsCollection collection = getGeregiCombolov().getSelectedTitles();
        for (Condition condition:conditions) {
            collection = collection.filterBy(condition);
        }
        collection.shouldHave(sizeGreaterThan(0));
        takeScreenshot();
        return this;
    }

    @Step("Seçilen Gereği \"Listeden Çıkar\" butonu")
    public SelenideElement getGeregiGeregiListedenCikarButton(Condition... secilenAramaKriteri){
        ElementsCollection collection = getGeregiCombolov().getSelectedItems();
        for (Condition condition:secilenAramaKriteri) {
            collection = collection.filterBy(condition);
        }
        return collection.shouldHave(sizeGreaterThan(0)).first().$(deleteButtonuSelector);
    }

    @Step("Seçilen Gereği \"Dağıtım Hitap Düzenleme\" butonu")
    public SelenideElement getGeregiDagitimHitapDuzenlemeButton(Condition... secilenAramaKriteri){
        ElementsCollection collection = getGeregiCombolov().getSelectedItems();
        for (Condition condition:secilenAramaKriteri) {
            collection = collection.filterBy(condition);
        }
        return collection.shouldHave(sizeGreaterThan(0)).first().$(updateButtonuSelector);
    }

    @Step("Seçilen Gereği \"Dağıtım Hitap Düzenleme\" butona tiklanır")
    public DagitimHitapDuzenle geregiDagitimHitapDuzenlemeTiklanir(Condition... secilenAramaKriteri){
        getGeregiDagitimHitapDuzenlemeButton(secilenAramaKriteri).shouldBe(visible).click();
        return new DagitimHitapDuzenle();
    }
    //endregion

    //******************************************************

    //region Gereği

    @Step("Gereği combolov")
    public BelgenetElement getGeregiCombolov() {
        return comboLov(getContainer(), "input[id$='geregiLov:LovText']");
    }

    @Step("Gereği alanı seçilir")
    public BilgilerTab geregiSec(String text, boolean... exactValue) {

        if (exactValue.length > 0 && exactValue[0])
            getGeregiCombolov().selectExactLov(text);
        else
            getGeregiCombolov().selectLov(text);

        return this;
    }

    @Step("Gereği alanı temizlenir")
    public BilgilerTab geregiTemizle() {
        getGeregiCombolov().clearAllSelectedItems();
        return this;
    }

    @Step("\"{secilenGeregi}\" seçilen gereğinin posta tipi: {stepDescription}")
    public SelenideElement getSecilenGeregiPostaTipi(String stepDescription, String... secilenGeregi) {
        ElementsCollection collection = getGeregiCombolov().getSelectedItems().shouldHave(sizeGreaterThan(0));
        for (String v : secilenGeregi) {
            collection = collection.filterBy(text(v));
        }
        return collection.shouldHave(sizeGreaterThan(0)).first().$("select").shouldBe(visible);
    }

    @Step("Seçilen Geregi posta tipi {posta} kontolü")
    public BilgilerTab geregiPostaTipiKontrolu(String postaTipi) {
        //getGeregiCombolov().getSelectedItems().last().$("select").getSelectedOption().shouldHave(text(postaTipi));
        Assert.assertEquals(getGeregiCombolov().getSelectedItems().last().$("select").getSelectedOption().text(), postaTipi
        , "Posta tipinin default " + postaTipi + " olarak geldiği görülür");
        return this;
    }

    @Step("Geregi posta tipi {posta} seçilir")
    public BilgilerTab geregiPostaTipiSec(String postaTipi) {
        getGeregiCombolov().getSelectedItems().last().$("select").selectOption(postaTipi);
        return this;
    }

    @Step("Geregi Tipi {geregiSecimTipi.optionText}, gereği {deger} seçilir")
    public BilgilerTab geregiSec(GeregiSecimTipi geregiSecimTipi, String deger) {
        getGeregiSecimTipi().selectOption(geregiSecimTipi.getOptionText());
        getGeregiCombolov().selectLov(deger);
        return this;
    }

    @Step("Geregi Tipi {geregiSecimTipi.optionText}, gereği {deger} ve posta tipi {postaTipi} seçilir")
    public BilgilerTab geregiSec(GeregiSecimTipi geregiSecimTipi, String deger, String postaTipi) {
        getGeregiSecimTipi().selectOption(geregiSecimTipi.getOptionText());
        getGeregiCombolov().selectLov(deger);
        getGeregiCombolov().getSelectedItems().last().scrollIntoView(true).$("select").selectOption(postaTipi);
        return this;
    }

    //endregion

    //******************************************************

    //region Dağıtımı Ek Yap
    //SelenideElement dagitimiEkYapCheckbox = $("div[id$=dagitimEkYapCheckBoxId]").find("input");

    @Step("Gereği alanıda değeri seçilecek değer boş mu")
    public BilgilerTab geregiDegerSecilemez(String text) {
        Assert.assertTrue(getGeregiCombolov().type(text).isEmpty(), "Gereği diolog tree boş");
        return this;
    }

    @Step("Dağıtımı Ek Yap checkbox")
    public SelenideElement getDagitimiEkYapCheckbox() {
        return getContainer().$("div[id$=dagitimEkYapCheckBoxId]").find("input");
    }
    //endregion

    //******************************************************

    //region Onay Akışı
    //BelgenetElement onayAkisiCombolov = comboLov("table[id$='akisOlusturPanelGrid'] input[id$='akisLov:LovText']");

    @Step("Dağıtımı Ek Yap seçilir")
    public BilgilerTab dagitimiEkYapSec(boolean setSelected) {
        getDagitimiEkYapCheckbox().setSelected(setSelected);
        return this;
    }

    @Step("Onay Akışı combolov")
    public BelgenetElement getOnayAkisiCombolov() {
        return comboLov(getContainer(), "table[id$='akisOlusturPanelGrid'] input[id$='akisLov:LovText']");
    }

    @Step("Onay Akışı doldurulur")
    public BilgilerTab onayAkisiSec(String... texts) {
        for (String text : texts) {
            getOnayAkisiCombolov().selectLov(text);
        }
        getOnayAkisiCombolov().closeTreePanel();
        return this;
    }

    //Onay Akışı Ekle
    //SelenideElement onayAkisiEkleButton = $("button[id$=onayAkisiEkle]");

    @Step("Onay Akışı alanı temizlenir")
    public BilgilerTab onayAkisiTemizle() {
        getOnayAkisiCombolov().clearAllSelectedItems();
        return this;
    }

    @Step("Onay Akışı Ekle button")
    public SelenideElement getOnayAkisiEkleButton() {
        return getContainer().$("button[id$=onayAkisiEkle]");
    }

    @Step("Onay Akışı Ekle butona tıklanır")
    public BilgilerTab onayAkisiEkleButonaTikla() {
        getOnayAkisiEkleButton().click();
        return this;
    }

    @Step("Onay Akışta seçilen kullanıcıyı kontrol yapılır")
    public BilgilerTab onayAkisiSecilenKullaniciKontrolEt(String kullanici, String tipi) {
        Allure.addAttachment("Mevcut seçlen kullanicilar", getOnayAkisiCombolov().getSelectedItems().last().text());
        getOnayAkisiCombolov().getSelectedItems().last().shouldHave(text(kullanici + "-" + tipi));
        return this;
    }

    @Step("Onay Akışta seçilen kullanıcıyı kontrol yapılır")
    public BilgilerTab onayAkisiSecilenKullaniciKontrolEt(User kullanici, String tipi) {
        Allure.addAttachment("Mevcut seçlen kullanicilar", getOnayAkisiCombolov().getSelectedItems().last().text());
        getOnayAkisiCombolov().getSelectedItems().last().shouldHave(text(kullanici.getFullname() + "-" + tipi));
        return this;
    }

    @Step("Onay Akışta seçilen kullanıcıyı kontrol yapılır")
    public BilgilerTab onayAkisiSecilenKullaniciKontrolEt(User kullanici, OnayKullaniciTipi tipi) {
        Allure.addAttachment("Mevcut seçlen kullanicilar", getOnayAkisiCombolov().getSelectedItems().last().text());
        getOnayAkisiCombolov().getSelectedItems().last().shouldHave(text(kullanici.getFullname() + "-" + tipi.getOptionText()));
        return this;
    }


    //Otomatik Onay Akışı Ekle
    //SelenideElement otomatikOnayAkisiEkleButton = $("button[id$=otomatikOnayAkisiEkle]");

    @Step("Anlık onay seçilen Kullanıcıları kontrol et")
    public BilgilerTab onayAkisiSecilenKullaniciKontrolEt(String[][] kullaniciTipi) {
        Allure.addAttachment("Seçlenmesi gereken kullanicilar", Arrays.deepToString(kullaniciTipi));
        Allure.addAttachment("Mevcut seçlen kullanicilar", getOnayAkisiCombolov().getSelectedItems().last().text());
        for (String[] kullanici : kullaniciTipi) {
            getOnayAkisiCombolov().getSelectedItems().last().shouldHave(text(kullanici[0] + "-" + kullanici[1]));
        }
        return this;
    }

    @Step("Otomatik Onay Akışı Ekle button")
    public SelenideElement getOtomatikOnayAkisiEkleButton() {
        return getContainer().$("button[id$=otomatikOnayAkisiEkle]");
    }

    //Otomatik Onay Akışı İşlemleri Dialog
    //SelenideElement otomatikOnayAkisiIslemleriDialog = $("div[id$=hiyerarsikAkisOlusturDialog]");
    @Step("Otomatik Onay Akışı İşlemleri dialog")
    public SelenideElement getOtomatikOnayAkisiIslemleriDialog() {
        return getContainer().$("div[id$=hiyerarsikAkisOlusturDialog]");
    }

    @Step("Otomatik Onay Akışı Ekle butona tıklanır")
    public BilgilerTab otomatikOnayAkisiEkleButonaTikla() {
        getOtomatikOnayAkisiEkleButton().click();
        getOtomatikOnayAkisiIslemleriDialog().shouldBe(visible);
        searchTable = new SearchTable(getOtomatikOnayAkisiIslemleriListId());
        return this;
    }

    //Otomatik Onay Akışı İşlemleri Dialog Title
    //public SelenideElement otomatikOnayAkisiIslemleriDialogTitle = otomatikOnayAkisiIslemleriDialog.$(".ui-dialog-titlebar .ui-dialog-title");
    @Step("Otomatik Onay Akışı İşlemleri diolag title")
    public SelenideElement getOtomatikOnayAkisiIslemleriDialogTitle() {
        return getOtomatikOnayAkisiIslemleriDialog().$(".ui-dialog-titlebar .ui-dialog-title");
    }

    //Otomatik Onay Akışı İşlemleri Dialog Close
    //SelenideElement otomatikOnayAkisiIslemleriDialogClose = otomatikOnayAkisiIslemleriDialog.$(".ui-dialog-titlebar .ui-dialog-titlebar-close");
    @Step("Otomatik Onay Akışı İşlemleri diolag close")
    public SelenideElement getOtomatikOnayAkisiIslemleriDialogClose() {
        return getOtomatikOnayAkisiIslemleriDialog().$(".ui-dialog-titlebar .ui-dialog-titlebar-close");
    }

    //SelenideElement otomatikOnayAkisiIslemleri = $("div[id$='hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId']");
    @Step("Otomatik Onay Akışı İşlemleri list id")
    public SelenideElement getOtomatikOnayAkisiIslemleriListId() {
        return getContainer().$("div[id$='hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId']");
    }

    @Step("Otomatik Onay Akışı İşlemleri table")
    public SearchTable getOtomatikOnayAkisiIslemleriTable() {
        return searchTable;
    }

    ElementsCollection otomatikOnayAkisiIslemlerindeTumSecilenleri() {
        return searchTable.findRows().getFoundRows().filterBy(cssClass("ui-state-active"));
        //getRows().filterBy(cssClass("ui-state-active"));
    }

    @Step("Otomatik Onay Akışında bul ve seçilir")
    public BilgilerTab otomatikOnayAkisindaBulVeSec(String secilecekIslem, Condition... conditions) {
        otomatikOnayAkisiEkleButonaTikla();
        getOtomatikOnayAkisiIslemleriDialog().shouldBe(visible);
        getOtomatikOnayAkisiIslemleriDialogTitle().shouldHave(text("Otomatik Onay Akışı İşlemleri"));

        SelenideElement row = searchTable.findRows(conditions).useFirstFoundRow().getFoundRow();
        //findRowByText(searchText);

        if (row.attr("class").contains("ui-state-active"))
            Allure.addAttachment("Seçiliydi", row.toString());
        else {
            row.$(".ui-chkbox-box").click();
//            Allure.addAttachment("Seçildi", row.toString());
        }

        otomatikOnayAkisindaIslemiSec(row, secilecekIslem);
        return this;
    }

    //public ElementsCollection otomatikOnayAkisiIslemleriList = otomatikOnayAkisiIslemleri.$$("tr[data-ri][data-rk]");
    /*public ElementsCollection getOtomatikOnayAkisiIslemleriList() {
        return getOtomatikOnayAkisiIslemleriListId().$$("tr[data-ri][data-rk]");
    }*/

    /**
     * Return list of columns
     *
     * @param
     * @return
     */
    /*private ElementsCollection otomatikOnayAkisiIslemleriList(int columnIndex){
        String index = String.valueOf(columnIndex - 1);
        return getOtomatikOnayAkisiIslemleriListId().$$("tr[data-ri][data-rk] td:nth-child(" + index + ")");
    }*/

    /*ElementsCollection otomatikOnayAkisiIslemlerindeTumSecilenleri(){
        return getOtomatikOnayAkisiIslemleriListId().$$("tr[data-ri][data-rk] div[class~='ui-state-active']");
    }*/
    @Step("Otomatik Onay Akışı İşlemlerinde tüm seçilenleri kaldırılır")
    public BilgilerTab otomatikOnayAkisiIslemlerindeTumSecilenleriKaldir() {
        int count = otomatikOnayAkisiIslemlerindeTumSecilenleri().size();
        Allure.addAttachment("Seçilen sayısı", String.valueOf(count));

        while (otomatikOnayAkisiIslemlerindeTumSecilenleri().size() > 0) {
            Allure.addAttachment("Seçileni kaldırılır", otomatikOnayAkisiIslemlerindeTumSecilenleri().first().toString());
            otomatikOnayAkisiIslemlerindeTumSecilenleri().first().click();
            otomatikOnayAkisiIslemlerindeTumSecilenleri().shouldHaveSize(--count);
        }
        return this;
    }

    /*@Step("Otomatik Onay Akışı İşlemlerinde bul")
    public ElementsCollection otomatikOnayAkisiIslemlerindeBul(String... searchTextFirstExact){
        ElementsCollection rows = $$("__");
        if (searchTextFirstExact.length == 0)
            throw new RuntimeException("Arama kriterleri verilmedi: input parameter searchText boş");

        rows = getOtomatikOnayAkisiIslemleriListId().$$x("//tr[@data-ri and @data-rk " +
                "and descendant::*[normalize-space(text())='" + searchTextFirstExact[0] +"']]");

//        Allure.addAttachment("1 arama", "arama teksti:" + searchTextFirstExact[0] + "\nsonuç kayıt sayısı: " + String.valueOf(rows.size()), rows.toString());
        for (int i = 0; i < searchTextFirstExact.length; i++) {
            rows = rows.filterBy(text(searchTextFirstExact[i]));
            String result = "";
            if (rows.size() > 0)
                result = rows.toString();
            Allure.addAttachment(i+1 + " arama", "arama teksti:" + searchTextFirstExact[i] + "\nsonuç kayıt sayısı: " + String.valueOf(rows.size()), result);
        }

        return rows;
    }*/

    /*@Step("Otomatik Onay Akışında bul ve seçilir")
    public BilgilerTab otomatikOnayAkisindaBulVeSec(String secilecekIslem, String... searchTextFirstExact){
        otomatikOnayAkisiEkleButonaTikla();
        getOtomatikOnayAkisiIslemleriDialog().shouldBe(visible);
        getOtomatikOnayAkisiIslemleriDialogTitle().shouldHave(text("Otomatik Onay Akışı İşlemleri"));

        SelenideElement row = otomatikOnayAkisiIslemlerindeBul(searchTextFirstExact).shouldHave(sizeGreaterThan(0)).first();

        if (row.attr("class").contains("ui-state-active"))
            Allure.addAttachment("Seçiliydi", row.toString());
        else {
            row.$(".ui-chkbox-box").click();
//            Allure.addAttachment("Seçildi", row.toString());
        }

        otomatikOnayAkisindaIslemiSec(row, secilecekIslem);
        return this;
    }*/

    @Step("Otomatik Onay Akışında işlem seçilir")
    public BilgilerTab otomatikOnayAkisindaIslemiSec(SelenideElement row, String secilecekIslem) {
        row.$("select").selectOption(secilecekIslem);
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri kullan tıklanır")
    public BilgilerTab otomatikOnayAkisiIslemleriKullanTikla() {
        getOtomatikOnayAkisiIslemleriDialog().$("button[id$='hiyerarsikAkisOlusturForm:hiyerarsikAkisKullan']").click();
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri penceriyi kapat")
    public BilgilerTab otomatikOnayAkisiIslemleriDialogKapat() {
        getOtomatikOnayAkisiIslemleriDialogClose().click();
        return this;
    }
    //endregion

    //******************************************************

    //[id$='anlikakisOlusturPanelGrid']

    //region Kullanıcıları - Anlık Akış Oluştur
    //BelgenetElement kullanicilarCombolov = comboLov("input[id$='akisAdimLov:LovText']");

    @Step("Anlık onay Kullanıcılar alan combolov")
    public BelgenetElement getAnlikOnayAkisKullanicilarCombolov() {
        return comboLov(getContainer(), "input[id$='akisAdimLov:LovText']");
    }

    @Step("Anlık onay Kullanıcıları seçilir")
    public BilgilerTab anlikOnayAkisKullanicilariSec(String... texts) {
        for (String text : texts)
            getAnlikOnayAkisKullanicilarCombolov().selectLov(text);
        return this;
    }

    @Step("Anlık onay seçilen Kullanıcı tipi \"Koordeneli\" kontrol et")
    public BilgilerTab secilenAnlikOnayAkisKullanicilariTipiKoordeneliKontrolEt(Condition... kullaniciAramaKriteri) {
        ElementsCollection collection = getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().shouldHave(sizeGreaterThan(0));
        for (Condition condition:kullaniciAramaKriteri) {
            collection = collection.filterBy(condition);
        }
        collection.shouldHave(sizeGreaterThan(0)).last().shouldHave(text("Koordeneli"));
        return this;
    }

    @Step("Anlık onay seçilen Kullanıcıları kontrol et")
    public BilgilerTab secilenAnlikOnayAkisKullanicilariKontrolEt(String kullanici, String tipi) {
        Allure.addAttachment("Seçlen olmalı kullanicilar", kullanici + " / " + tipi);
        Allure.addAttachment("Mevcut seçlen kullanicilar", getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().texts().toString());
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().filterBy(text(kullanici)).shouldHaveSize(1)
                .first().$("select").getSelectedOption().shouldHave(text(tipi));
        return this;
    }

    @Step("Anlık onay seçilen Kullanıcıları kontrol et")
    public BilgilerTab secilenAnlikOnayAkisKullanicilariKontrolEt(String kullanici, OnayKullaniciTipi tipi) {
        Allure.addAttachment("Seçlen olmalı kullanicilar", kullanici + " / " + tipi);
        Allure.addAttachment("Mevcut seçlen kullanicilar", getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().texts().toString());
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().filterBy(text(kullanici)).shouldHaveSize(1)
                .first().$("select").getSelectedOption().shouldHave(text(tipi.getOptionText()));
        return this;
    }

    @Step("Anlık onay seçilen Kullanıcıları kontrol et")
    public BilgilerTab secilenAnlikOnayAkisKullanicilariKontrolEt(User kullanici, OnayKullaniciTipi tipi) {
        Allure.addAttachment("Seçlen olmalı kullanicilar", kullanici + " / " + tipi);
        Allure.addAttachment("Mevcut seçlen kullanicilar", getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().texts().toString());
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().filterBy(text(kullanici.getFullname())).shouldHaveSize(1)
                .first().scrollIntoView(true).$("select").getSelectedOption().shouldHave(text(tipi.getOptionText()));
        return this;
    }

    @Step("Anlık onay seçilen Kullanıcıları kontrol et")
    public BilgilerTab secilenAnlikOnayAkisKullanicilariKontrolEt(String[][] kullaniciTipi) {
        Allure.addAttachment("Seçlen olmalı kullanicilar", Arrays.deepToString(kullaniciTipi));
        Allure.addAttachment("Mevcut seçlen kullanicilar", getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().texts().toString());

        for (String[] kullanici : kullaniciTipi) {
            String k = getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().filterBy(text(kullanici[0]))
                    .shouldHaveSize(1).first().$("select").getSelectedOption().text();
            //Allure.addAttachment("Seçlen kullanici kontrol yapılır", kullanici[0] + "/" + kullanici[1]);
            Assert.assertEquals(k, kullanici[1], "Kullanici tipi");
        }
        return this;
    }

   /* @Step("Onay akışı kullanıcıları seçilir")
    public BilgilerTab onayAkisiKullanicilariSec(String... texts) {
        for (String text:texts)
            getAnlikOnayAkisKullanicilarCombolov().selectLov(text);
        return this;
    }*/

    @Step("Anlık onay akışındaki kullanıcı seçilir")
    public BilgilerTab anlikOnayAkisKullaniciSec(User kullanici) {
        String filter = kullanici.getBirimKisaAdi().isEmpty() ? kullanici.getBirimAdi() : kullanici.getBirimKisaAdi();
        anlikOnayAkisKullanicilarAlaninBirimTumuSec(true);

        getAnlikOnayAkisKullanicilarCombolov()
                .type(kullanici.getFullname())
                .getSelectableItems()
                .filterBy(text(kullanici.getFullname()))
                .filterBy(text(kullanici.getGorev()))
                .filterBy(text(filter))
                .first().click();
        getAnlikOnayAkisKullanicilarCombolov().closeTreePanel();

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici.getFullname()), text(kullanici.getGorev()), text(filter));
        return this;
    }


    @Step("Anlık onay akışındaki Koordeneli kullanıcı seçilir")
    public BilgilerTab anlikOnayAkisKoordeneliKullaniciSec(User kullanici) {
        koodreneliSec(true);
        anlikOnayAkisKullaniciSec(kullanici);
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last().scrollIntoView(true)
                .shouldHave(text(kullanici.getFullname()), text("Koordine"));
        koodreneliSec(false);

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici.getFullname()), text("Koordine"));
        return this;
    }

    @Step("Anlık onay akışındaki kullanıcı ve tipi seçilir")
    public BilgilerTab anlikOnayAkisKullaniciVeTipiSec(String kullanici, String tipi) {
        //anlikOnayAkisKullanicilarAlaninBirimTumuSec(true);
        getAnlikOnayAkisKullanicilarCombolov().selectLov(kullanici);
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldBe(exist).shouldHave(text(kullanici))
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(tipi);

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici), text(tipi));
        return this;
    }

    @Step("Anlık onay akışındaki kullanıcı ve tipi seçilir")
    public BilgilerTab anlikOnayAkisKullaniciVeTipiSec(String kullanici, OnayKullaniciTipi tipi) {
        //anlikOnayAkisKullanicilarAlaninBirimTumuSec(true);
        getAnlikOnayAkisKullanicilarCombolov().selectLov(kullanici);
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldBe(exist).shouldHave(text(kullanici))
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(tipi.getOptionText());

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici), text(tipi.getOptionText()));
        return this;
    }

    @Step("Anlık onay akışındaki kullanıcı ve tipi seçilir")
    public BilgilerTab anlikOnayAkisKullaniciVeTipiSec(User kullanici, OnayKullaniciTipi tipi) {
        anlikOnayAkisKullanicilarAlaninBirimTumuSec(true);

       /* getAnlikOnayAkisKullanicilarCombolov().selectLov(
                kullanici.getFullname()
        , kullanici.getGorev()
        , kullanici.getBirimKisaAdi().isEmpty()?kullanici.getBirimAdi():kullanici.getBirimKisaAdi());*/

        ElementsCollection collection = getAnlikOnayAkisKullanicilarCombolov()
                .type(kullanici.getFullname())
                .getSelectableItems()
                .filterBy(text(kullanici.getFullname()))
                .filterBy(text(kullanici.getGorev()))
                .filterBy(text(kullanici.getBirimKisaAdi().isEmpty() ? kullanici.getBirimAdi() : kullanici.getBirimKisaAdi()));

        Assert.assertTrue(collection.size()>0, "filtrelenen Lov items > 0");
        collection.first().click();
        getAnlikOnayAkisKullanicilarCombolov().closeTreePanel();

        //getAnlikOnayAkisKullanicilarCombolov().selectLov(kullanici);
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldBe(exist).shouldHave(text(kullanici.getFullname()))
                .$("select[id*='selectOneMenu']").selectOptionContainingText(tipi.getOptionText());

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici.getFullname()), text(tipi.getOptionText()));

        return this;
    }

    @Step("Anlık onay akışı kullanıcıları temizlenir")
    public BilgilerTab anlikOnayAkisKullanicilariTemizle() {
        //getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().shouldHaveSize(1);
        if (getAnlikOnayAkisKullanicilarCombolov().exists())
            getAnlikOnayAkisKullanicilarCombolov().clearAllSelectedItems();
        return this;
    }

    @Step("Anlık onay akışındaki kullanıcının tipi seçilir")
    public BilgilerTab anlikOnayAkisKullanicininTipiSec(String kullaniciAdi, String kullaniciTipi) {
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems()
                .filterBy(text(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(kullaniciTipi);

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullaniciAdi), text(kullaniciTipi));

        return this;
    }

    @Step("Anlık onay akışındaki kullanıcının tipi seçilir")
    public BilgilerTab anlikOnayAkisKullanicininTipiSec(User kullanici, OnayKullaniciTipi tipi) {
        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems()
                .filterBy(text(kullanici.getFullname()))
                /*.filterBy(text(kullanici.getGorev()))
                .filterBy(text(kullanici.getBirimAdi()))*/
                .get(0)
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(tipi.getOptionText());

        getAnlikOnayAkisKullanicilarCombolov().getSelectedItems().last()
                .shouldHave(text(kullanici.getFullname()), text(tipi.getOptionText()));
        return this;
    }


    //table[contains(@id,'anlikakisOlusturPanelGrid')]//div[@type='button']/input[@type='checkbox']
    @Step("Anlık onay akışı kullanıcıları alanın Birim/Tümü sec")
    public BilgilerTab anlikOnayAkisKullanicilarAlaninBirimTumuSec(boolean tumu) {
        SelenideElement button = container.$("[id$='anlikakisOlusturPanelGrid'] div[type='button'] input[type='checkbox']");
        if (tumu != button.isSelected())
            clickJs(button);
        return this;
    }

    SelenideElement getKoordineliCheckbox() {
        return container.$("input[id$='koordineliBooleanCheckbox_input']");
    }

    @Step("Koordeneli checkobox {secilir} seçilir")
    public BilgilerTab koodreneliSec(boolean secilir){
        checkboxSelect(getKoordineliCheckbox(), secilir);
        return this;
    }

    @Step("Anlık onay Kullan buton")
    public SelenideElement getKullanButton() {
        return container.$("button[id$='anlikAkisKullanButton']");
    }

    @Step("Anlık onay akiş Kullan butona tıklanır")
    public BilgilerTab kullan() {
        getKullanButton().scrollIntoView(true).click();
        return this;
    }

    @Step("Anlık onay akiş Kullan butona tıklanır")
    public BilgilerTab kullanAndCheck(Condition... conditions) {
        getKullanButton().pressEnter();
        container.$("[id$='anlikakisOlusturPanel']").shouldHave(conditions);
        return this;
    }
    //endregion

    //******************************************************


    ///////////////////////////////////////////////////////////////////////////
    // Reusable methods
    ///////////////////////////////////////////////////////////////////////////

    /*Alanlar:
    Konu Kodu
    Konu
    Kaldırılacak Klasörler
    Evrak Türü
    Kayıt Tarihi
    Evrak Dili
    Gizlilik Derecesi
    Kanun Kapsam Tipi		Normal	 Bilgi Edinme Kanunu	 Kişisel Verilerin Korunması Kanunu
    Evrak Sayı Ek Metni
    Açıklama
    İvedilik
    Miat
    Bilgi Seçim Tipi
    Bilgi
    Gereği Seçim Tipi
    Gereği
    Dağıtımı Ek Yap
    Onay Akışı
    Kullanıcılar
    */

    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi, String onayAkis) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiSec(onayAkis);
        return this;
    }

    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi, Map<String, OnayKullaniciTipi> onayAkisKullanici) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();
        //anlikOnayAkisKullanicilariTemizle();
        onayAkisKullanici.forEach((k, t) -> anlikOnayAkisKullaniciVeTipiSec(k, t.getOptionText()));
        kullan();
        return this;
    }

    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konu, GeregiSecimTipi geregiSecimTipi, String geregi, User kendisiImzaci) {
        konuKoduSec("010.01");
        konuDoldur(konu);
        kaldiralacakKlasorleriSec("Diğer");
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();
        anlikOnayAkisKullanicininTipiSec(kendisiImzaci, OnayKullaniciTipi.IMZALAMA);
        kullan();
        return this;
    }

    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konu, GeregiSecimTipi geregiSecimTipi, String geregi, User parafci, User imzaci) {
        konuKoduSec("010.01");
        konuDoldur(konu);
        kaldiralacakKlasorleriSec("Diğer");
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();

        String kendisi = getAnlikOnayAkisKullanicilarCombolov().getSelectedTitles().first().text();
        if (kendisi.contains(parafci.getFullname()))
            anlikOnayAkisKullanicininTipiSec(parafci, OnayKullaniciTipi.PARAFLAMA);
        else
            anlikOnayAkisKullaniciVeTipiSec(parafci, OnayKullaniciTipi.PARAFLAMA);

        anlikOnayAkisKullaniciVeTipiSec(imzaci, OnayKullaniciTipi.IMZALAMA);
        kullan();
        return this;
    }


    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konu, GeregiSecimTipi geregiSecimTipi, String geregi) {
        konuKoduSec("010.01");
        konuDoldur(konu);
        kaldiralacakKlasorleriSec("Diğer");
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        return this;
    }


    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi, String[][] onayAkisKullaniciTipi) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();
        anlikOnayAkisKullanicilarAlaninBirimTumuSec(true);
        //anlikOnayAkisKullanicilariTemizle();
        String kendisi = getAnlikOnayAkisKullanicilarCombolov().getSelectedTitles().first().text();
        for (String[] kullanici : onayAkisKullaniciTipi) {
            if (kullanici[0].equals(kendisi))
                anlikOnayAkisKullanicininTipiSec(kullanici[0], kullanici[1]);
            else
                anlikOnayAkisKullaniciVeTipiSec(kullanici[0], kullanici[1]);
        }
        kullan();
        return this;
    }

    @Step("Alanları doldurulur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        return this;
    }


    public UstYazi getUstYazi() {
        return new UstYazi(container);
    }
}