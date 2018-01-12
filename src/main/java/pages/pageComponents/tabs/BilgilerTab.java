package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.NotFoundException;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.alanlar.*;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class BilgilerTab extends MainPage {

    private final static String tabName = "Bilgileri";
    private SelenideElement container;

    public SelenideElement getContainer() {
        if (super.getSelf() != null)
            container = super.getSelf();
        return container;
    }

    public BilgilerTab() {
        container = $("html");
    }

    public BilgilerTab(SelenideElement container) {
        this.container = container;
    }

    public void shouldHave(String text) {
        getContainer().shouldHave(text(text));
    }

    //SelenideElement tab = $x("//td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='"+ tabName +"']]//button");
    @Step(tabName + " tabı aç")
    public BilgilerTab openTab(boolean... clickIfOpen){
        SelenideElement tab = $x("//td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='"+ tabName +"']]");

        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        return this;
    }

    //******************************************************

    //region Konu Kodu
//    BelgenetElement konuKoduCombolov = comboLov("input[id$='konuKoduLov:LovText']");

    @Step("Konu Kodu alan")
    public BelgenetElement getKonuKodu() {
        return comboLov(getContainer(),"input[id$='konuKoduLov:LovText']");
    }

    @Step("Konu Kodu seç")
    public BilgilerTab konuKoduSec(String text){
        getKonuKodu().selectLov(text);
        getKonuKodu().closeTreePanel();
        return this;
    }

    @Step("Konu Kodu doldur")
    public BilgilerTab konuKoduDoldurFilterByTitle(String text, Condition filter){
        getKonuKodu().type(text).getTitleItems().filterBy(filter).first().click();
        getKonuKodu().closeTreePanel();
        return this;
    }

    @Step("Konu Kodu doldur")
    public BilgilerTab konuKoduDoldurFilterByDetail(String text, Condition filterBy){
        getKonuKodu().type(text).getDetailItems().filterBy(filterBy).first().click();
        getKonuKodu().closeTreePanel();
        return this;
    }

    @Step("Konu Kodu alanı temizle")
    public BilgilerTab konuKoduTemizle(){
        getKonuKodu().clearAllSelectedItems();
        return this;
    }
    //endregion

    //******************************************************

    //region Konu
//    SelenideElement konuTextarea = $("textarea[id$='konuTextArea']");

    public SelenideElement getKonuTextarea(){
        return getContainer().$("textarea[id$='konuTextArea']");
    }

    @Step("Konu doldur")
    public BilgilerTab konuDoldur(String text){
        /*konuTextarea.clear();
        konuTextarea.sendKeys(text);*/
        getKonuTextarea().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Kaldırılacak Klasörler
    @Step("Kaldırılacak Klasörler")
    public BelgenetElement getKaldiralacakKlasorlerCombolov(){
        return comboLov(getContainer(),"input[id$='eklenecekKlasorlerLov:LovText']");
    }

    @Step("Kaldırılacak Klasörleri seç")
    public BilgilerTab kaldiralacakKlasorleriSec(String text){
        getKaldiralacakKlasorlerCombolov().selectLov(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Kayıt Tarihi
    //SelenideElement kayitTarihiDateInput = $("input[id$='kayitTarih_input']");

    @Step("Kayit Tarihi")
    public SelenideElement getKayitTarihi(){
        return getContainer().$("input[id$='kayitTarih_input']");
    }

    @Step("Kayit Tarihi doldur")
    public BilgilerTab kayitTarihiDoldur(String text){
        getKayitTarihi().setValue(text);
        return this;
    }

    @Step("Kayit Tarihi değeri al")
    public String kayitTarihiDegeriAl(){
        String text = getKayitTarihi().text();
        Allure.addAttachment("Değer", text);
        return text;
    }
    //endregion

    //******************************************************

    //region Evrak Dili
    //SelenideElement evrakDiliSelect = $("select[id$='evrakDili']");

    @Step("Evrak Dili")
    public SelenideElement getEvrakDili(){
        return getContainer().$("select[id$='evrakDili']");
    }

    @Step("Evrak Dili seç")
    public BilgilerTab evrakDiliSec(String text){
        getEvrakDili().selectOption(text);
        return this;
    }

    @Step("Evrak Dili seç")
    public BilgilerTab evrakDiliSec(EvrakDili evrakDili){
        getEvrakDili().selectOption(evrakDili.getOptionText());
        return this;
    }
    //endregion

    //******************************************************

    //region Gizlilik Derecesi
    //SelenideElement gizlilikDerecesiSelect = $("select[id$=guvenlikKodu]");

    @Step("Gizlilik Derecesi")
    public SelenideElement getGizlilikDerecesi(){
        return getContainer().$("select[id$=guvenlikKodu]");
    }

    @Step("Gizlilik Derecesi seç")
    public BilgilerTab gizlilikDerecesiSec(String text){
        getGizlilikDerecesi().selectOption(text);
        return this;
    }

    /*@Step("Gizlilik Derecesi seç")
    public BilgilerTab gizlilikDerecesiSec(Enum gizlilikDerecesi){
        if (!gizlilikDerecesi.getClass().equals(GizlilikDerecesi.class))
            throw new RuntimeException("Yanlış input enum. Olması gereken: " + GizlilikDerecesi.class.toString());

        getGizlilikDerecesi().selectOption(((GizlilikDerecesi) gizlilikDerecesi).getOptionText());
        return this;
    }*/

    @Step("Gizlilik Derecesi seç")
    public BilgilerTab gizlilikDerecesiSec(GizlilikDerecesi gizlilikDerecesi){
        getGizlilikDerecesi().selectOption(gizlilikDerecesi.getOptionText());
        return this;
    }
    //endregion

    //******************************************************

    //region Kanun Kapsam Tipi
    //ElementsCollection kanunKapsamTipiRadioButtons = $$("table[id$='kanunKapsamTipiRadio'] input");

    @Step("Kanun Kapsam Tipi radio")
    public ElementsCollection getKanunKapsamTipiRadioButtons(){
        return getContainer().$$("table[id$='kanunKapsamTipiRadio'] input");
    }

    @Step("Kanun Kapsam Tipi seç")
    public BilgilerTab kanunKapsamTipiSec(String radioText){
        SelenideElement radio = getKanunKapsamTipiRadioButtons().first();
        radio.shouldBe(visible);
        switch (radioText) {
            case "Normal":
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

    @Step("Kanun Kapsam Tipi seçilen değeri")
    public SelenideElement kanunKapsamTipiSecilenDeger(){
        SelenideElement selectedRadio = getKanunKapsamTipiRadioButtons()
                .filterBy(attribute("checked","true"))
                .filterBy(visible)
                .first();
        String id = selectedRadio.attr("id");
        Allure.addAttachment("Değer", $("label[for='" + id + "']").text());
        return $("label[for='" + id + "']");
    }
    //endregion

    //******************************************************

    //region Evrak Sayı Ek Metni
    //SelenideElement evrakSayiEkMetniInput = $("input[id$=evrakSayiEkMetniInputText]");

    @Step("Evrak Sayı Ek Metni")
    public SelenideElement getEvrakSayiEkMetni(){
        return getContainer().$("input[id$=evrakSayiEkMetniInputText]");
    }

    @Step("Evrak Sayı Ek Metni doldur")
    public BilgilerTab evrakSayiEkMetniDoldur(String text){
        getEvrakSayiEkMetni().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region İvedilik
    //SelenideElement ivedilikSelect = $("select[id$=ivedilik]");

    @Step("İvedilik")
    public SelenideElement getIvedilik(){
        return getContainer().$("select[id$=ivedilik]");
    }

    @Step("İvedilik seç")
    public BilgilerTab ivedilikSec(Ivedilik ivedilik){
        getIvedilik().selectOption(ivedilik.getOptionText());
        return this;
    }

    @Step("İvedilik seç")
    public BilgilerTab ivedilikSec(String text){
        getIvedilik().selectOption(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Miat
    //SelenideElement miatDateInput = $("input[id$=miatCalendar_input]");
    //SelenideElement miatTemizleButton = $("button[id$=miatTarihTemizle]");

    @Step("Miat")
    public SelenideElement getMiatDateInput(){
        return getContainer().$("input[id$=miatCalendar_input]");
    }

    @Step("Miat Temizle buton")
    public SelenideElement getMiatTemizleButton(){
        return getContainer().$("button[id$=miatTarihTemizle]");
    }

    @Step("Miat doldur")
    public BilgilerTab miatDoldur(String text){
        getMiatDateInput().setValue(text);
        return this;
    }

    @Step("Miat temizle")
    public BilgilerTab miatTemizle(){
        getMiatTemizleButton().click();
        getMiatDateInput().shouldBe(empty);
        return this;
    }
    //endregion

    //******************************************************

    //region Açıklama
    //SelenideElement aciklamaTextarea = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Açıklama']]//textarea");

    @Step("Açıklama textarea")
    public SelenideElement getAciklamaTextarea(){
        return getContainer().$x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Açıklama']]//textarea");
    }

    @Step("Açıklamayı doldur")
    public BilgilerTab aciklamaDoldur(String text){
        getAciklamaTextarea().setValue(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Bilgi Seçim Tipi
    //SelenideElement bilgiSecimTipiSelect = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Bilgi Seçim Tip']]//select");

    @Step("Bilgi Seçim Tipi select")
    public SelenideElement getBilgiSecimTipiSelect(){
        return getContainer().$x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Bilgi Seçim Tip']]//select");
    }

    @Step("Bilgi Seçim Tipi seç")
    public BilgilerTab bilgiSecimTipiSec(String text){
        getBilgiSecimTipiSelect().selectOption(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Bilgi
    @Step("Bilgi alan combolov")
    public BelgenetElement getBilgiCombolov() {
        return comboLov(getContainer(),"input[id$='bilgiLov:LovText']");
    }

    @Step("Bilgi seç")
    public BilgilerTab bilgiSec(String text) {
        getBilgiCombolov().selectLov(text);
        return this;
    }
    //endregion

    //******************************************************

    //region Gereği Seçim Tipi
    //SelenideElement geregiSecimTipiSelect = $x("//tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Gereği Seçim Tipi']]//select");

    @Step("Gereği Seçim Tipi select")
    public SelenideElement getGeregiSecimTipi() {
        return getContainer().$x("//tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Gereği Seçim Tipi']]//select");
    }

    @Step("Gereği Seçim Tipi seç")
    public BilgilerTab geregiSecimTipiSec(String text){
        getGeregiSecimTipi().selectOption(text);
        return this;
    }

    @Step("Gereği Seçim Tipi seç")
    public BilgilerTab geregiSecimTipiSec(GeregiSecimTipi geregiSecimTipi) {
        getGeregiSecimTipi().selectOption(geregiSecimTipi.getOptionText());
        return this;
    }

    //endregion

    //******************************************************

    //region Gereği

    @Step("Gereği combolov")
    public BelgenetElement getGeregiCombolov(){
        return comboLov(getContainer(),"input[id$='geregiLov:LovText']");
    }

    @Step("Gereği alanı seç")
    public BilgilerTab geregiSec(String text){
        getGeregiCombolov().selectLov(text);
        return this;
    }

    //endregion

    //******************************************************

    //region Dağıtımı Ek Yap
    //SelenideElement dagitimiEkYapCheckbox = $("div[id$=dagitimEkYapCheckBoxId]").find("input");

    @Step("Dağıtımı Ek Yap checkbox")
    public SelenideElement getDagitimiEkYapCheckbox(){
        return getContainer().$("div[id$=dagitimEkYapCheckBoxId]").find("input");
    }

    @Step("Dağıtımı Ek Yap seç")
    public BilgilerTab dagitimiEkYapSec(boolean setSelected){
        getDagitimiEkYapCheckbox().setSelected(setSelected);
        return this;
    }
    //endregion

    //******************************************************

    //region Onay Akışı
    //BelgenetElement onayAkisiCombolov = comboLov("table[id$='akisOlusturPanelGrid'] input[id$='akisLov:LovText']");

    @Step("Onay Akışı combolov")
    public BelgenetElement getOnayAkisiCombolov(){
        return comboLov(getContainer(),"table[id$='akisOlusturPanelGrid'] input[id$='akisLov:LovText']");
    }

    @Step("Onay Akışı doldur")
    public BilgilerTab onayAkisiSec(String... texts){
        for (int i = 0; i < texts.length; i++) {
            getOnayAkisiCombolov().selectLov(texts[0]);
        }
        getOnayAkisiCombolov().closeTreePanel();
        return this;
    }

    @Step("Onay Akışı alanı temizle")
    public BilgilerTab onayAkisiTemizle(){
        getOnayAkisiCombolov().clearAllSelectedItems();
        return this;
    }

    //Onay Akışı Ekle
    //SelenideElement onayAkisiEkleButton = $("button[id$=onayAkisiEkle]");

    @Step("Onay Akışı Ekle button")
    public SelenideElement getOnayAkisiEkleButton() {
        return getContainer().$("button[id$=onayAkisiEkle]");
    }

    @Step("Onay Akışı Ekle butona tıkla")
    public BilgilerTab onayAkisiEkleButonaTikla(){
        getOnayAkisiEkleButton().click();
        return this;
    }




    //Otomatik Onay Akışı Ekle
    //SelenideElement otomatikOnayAkisiEkleButton = $("button[id$=otomatikOnayAkisiEkle]");

    @Step("Otomatik Onay Akışı Ekle button")
    public SelenideElement getOtomatikOnayAkisiEkleButton(){
        return getContainer().$("button[id$=otomatikOnayAkisiEkle]");
    }

    //Otomatik Onay Akışı İşlemleri Dialog
    //SelenideElement otomatikOnayAkisiIslemleriDialog = $("div[id$=hiyerarsikAkisOlusturDialog]");
    @Step("Otomatik Onay Akışı İşlemleri dialog")
    public SelenideElement getOtomatikOnayAkisiIslemleriDialog() {
        return getContainer().$("div[id$=hiyerarsikAkisOlusturDialog]");
    }

    @Step("Otomatik Onay Akışı Ekle butona tıkla")
    public BilgilerTab otomatikOnayAkisiEkleButonaTikla(){
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

    private SearchTable searchTable;
    @Step("Otomatik Onay Akışı İşlemleri table")
    public SearchTable getOtomatikOnayAkisiIslemleriTable(){
        return searchTable;
    }

    ElementsCollection otomatikOnayAkisiIslemlerindeTumSecilenleri(){
        return searchTable.getRows().filterBy(cssClass("ui-state-active"));
    }

    @Step("Otomatik Onay Akışında bul ve seç")
    public BilgilerTab otomatikOnayAkisindaBulVeSec(String secilecekIslem, String... searchText){
        otomatikOnayAkisiEkleButonaTikla();
        getOtomatikOnayAkisiIslemleriDialog().shouldBe(visible);
        getOtomatikOnayAkisiIslemleriDialogTitle().shouldHave(text("Otomatik Onay Akışı İşlemleri"));

        SelenideElement row = searchTable.findRowByText(searchText);

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

    @Step("Otomatik Onay Akışı İşlemlerinde tüm seçilenleri kaldır")
    public BilgilerTab otomatikOnayAkisiIslemlerindeTumSecilenleriKaldir(){
        int count = otomatikOnayAkisiIslemlerindeTumSecilenleri().size();
        Allure.addAttachment("Seçilen sayısı", String.valueOf(count));

        while (otomatikOnayAkisiIslemlerindeTumSecilenleri().size() > 0){
            Allure.addAttachment("Seçileni kaldır", otomatikOnayAkisiIslemlerindeTumSecilenleri().first().toString());
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

    /*@Step("Otomatik Onay Akışında bul ve seç")
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

    @Step("Otomatik Onay Akışında işlem seç")
    public BilgilerTab otomatikOnayAkisindaIslemiSec(SelenideElement row, String secilecekIslem){
        row.$("select").selectOption(secilecekIslem);
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri kullan tıkla")
    public BilgilerTab otomatikOnayAkisiIslemleriKullanTikla(){
        getOtomatikOnayAkisiIslemleriDialog().$("button[id$='hiyerarsikAkisOlusturForm:hiyerarsikAkisKullan']").click();
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri penceriyi kapat")
    public BilgilerTab otomatikOnayAkisiIslemleriDialogKapat(){
        getOtomatikOnayAkisiIslemleriDialogClose().click();
        return this;
    }
    //endregion

    //******************************************************

    //[id$='anlikakisOlusturPanelGrid']

    //region Kullanıcıları - Anlık Akış Oluştur
    //BelgenetElement kullanicilarCombolov = comboLov("input[id$='akisAdimLov:LovText']");

    @Step("Kullanıcılar alan combolov")
    public BelgenetElement getKullanicilarCombolov(){
        return comboLov(getContainer(),"input[id$='akisAdimLov:LovText']");
    }

    @Step("Kullanıcıları seç")
    public BilgilerTab kullanicilariSec(String... texts){
        for (String text:texts)
            getKullanicilarCombolov().selectLov(text);
        return this;
    }

   /* @Step("Onay akışı kullanıcıları seç")
    public BilgilerTab onayAkisiKullanicilariSec(String... texts) {
        for (String text:texts)
            getKullanicilarCombolov().selectLov(text);
        return this;
    }*/

    @Step("Onay akışındaki kullanıcı ve tipi seç")
    public BilgilerTab onayAkisiKullaniciVeTipiSec(String kullanici, String tipi) {
        getKullanicilarCombolov().selectLov(kullanici);
        getKullanicilarCombolov().getSelectedItems().last()
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(tipi);
        return this;
    }

    @Step("Onay akışı kullanıcıları temizle")
    public BilgilerTab onayAkisiKullanicilarTemizle() {
        getKullanicilarCombolov().getSelectedItems().shouldHaveSize(1);
        getKullanicilarCombolov().clearAllSelectedItems();
        return this;
    }

    @Step("Onay akışındaki kullanıcının tipi seç")
    public BilgilerTab onayAkisiKullanicininTipiSec(String kullaniciAdi, String kullaniciTipi) {
        getKullanicilarCombolov().getSelectedItems()
                .filterBy(text(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(kullaniciTipi);
        return this;
    }

    //table[contains(@id,'anlikakisOlusturPanelGrid')]//div[@type='button']/input[@type='checkbox']
    @Step("")
    public BilgilerTab kullanicilarBirimTumu(boolean tumu){
        SelenideElement button = container.$("[id$='anlikakisOlusturPanelGrid'] div[type='button'] input[type='checkbox']");
        if (tumu != button.isSelected())
            clickJs(button);
        return this;
    }

    SelenideElement koordineliCheckbox(){
        return $("input[id$='koordineliBooleanCheckbox_input']");
    }

    @Step("Kullan buton")
    public SelenideElement getKullanButton(){
        return $("button[id$='anlikAkisKullanButton']");
    }

    @Step("Onay akiş kullanıcıları kullan")
    public BilgilerTab kullanButonaTikla() {
        getKullanButton().pressEnter();
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

    @Step("Alanları doldur")
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

    @Step("Alanları doldur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi, Map<String,OnayKullaniciTipi> onayAkisKullanici) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();
        //onayAkisiKullanicilarTemizle();
        onayAkisKullanici.forEach((k,t)-> onayAkisiKullaniciVeTipiSec(k,t.getOptionText()));
        kullanButonaTikla();
        return this;
    }

    @Step("Alanları doldur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi, String[][] onayAkisKullaniciTipi) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        onayAkisiEkleButonaTikla();
        kullanicilarBirimTumu(true);
        //onayAkisiKullanicilarTemizle();
        String kendisi = getKullanicilarCombolov().getSelectedTitles().first().text();
        for (String[] kullanici:onayAkisKullaniciTipi) {
            if (kullanici[0].equals(kendisi))
                onayAkisiKullanicininTipiSec(kullanici[0],kullanici[1]);
            else
                onayAkisiKullaniciVeTipiSec(kullanici[0],kullanici[1]);
        }
        kullanButonaTikla();
        return this;
    }


    @Step("Alanları doldur")
    public BilgilerTab alanlariDoldur(String konuKodu, String konu, String kaldirilacakKlasorleri, Ivedilik ivedilik, GeregiSecimTipi geregiSecimTipi, String geregi) {
        konuKoduSec(konuKodu);
        konuDoldur(konu);
        kaldiralacakKlasorleriSec(kaldirilacakKlasorleri);
        ivedilikSec(ivedilik);
        geregiSecimTipiSec(geregiSecimTipi);
        geregiSec(geregi);
        onayAkisiTemizle();
        return this;
    }

}