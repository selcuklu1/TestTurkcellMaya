package pages.pageComponents.tabs;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.NotFoundException;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.alanlar.EvrakDili;
import pages.pageData.alanlar.GizlilikDerecesi;
import pages.pageData.alanlar.Ivedilik;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

//import static com.codeborne.selenide.Selenide.$$;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 25.12.2017
 * Açıklama:
 */
public class BilgilerTab2 extends MainPage {

    final static String tabName = "Bilgileri";

    SelenideElement tab = $x("//td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='"+ tabName +"']]//button");

    @Step(tabName + " tabı aç")
    public BilgilerTab2 openTab(boolean... clickIfOpen){
        SelenideElement tab = $x("//td[contains(@class,'tabMenuContainer') and descendant::span[contains(@class,'tabMenu') and text()='Bilgileri']]");

        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        if (clickIfOpen.length > 0 || !tab.attr("class").equals("tabMenuContainerSelected"))
            tab.$("button").click();

        return this;
    }

//******************************************************

    //region Konu Kodu
    BelgenetElement konuKoduCombolov = comboLov("input[id$='konuKoduLov:LovText']");

    @Step("Konu Kodu alan")
    public BelgenetElement getKonuKodu() {
        return konuKoduCombolov;
    }

    @Step("Konu Kodu seç")
    public BilgilerTab2 konuKoduSec(String text){
        konuKoduCombolov.selectLov(text);
        konuKoduCombolov.closeTreePanel();
        return this;
    }

    @Step("Konu Kodu seç")
    public BilgilerTab2 konuKoduSecFilterByTitle(String text, Condition filter){
        konuKoduCombolov.type(text).getTitleItems().filterBy(filter).first().click();
        konuKoduCombolov.closeTreePanel();
        return this;
    }

    @Step("Konu Kodu seç")
    public BilgilerTab2 konuKoduSecFilterByDetail(String text, Condition filterBy){
        konuKoduCombolov.type(text).getDetailItems().filterBy(filterBy).first().click();
        konuKoduCombolov.closeTreePanel();
        return this;
    }

    @Step("Konu Kodu alanı temizle")
    public BilgilerTab2 konuKoduTemizle(){
        konuKoduCombolov.clearAllSelectedItems();
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Konu
    SelenideElement konuTextarea = $("textarea[id$='konuTextArea']");

    public SelenideElement konuTextarea(){
        return konuTextarea;
    }

    @Step("Konu doldur")
    public BilgilerTab2 konuDoldur(String text){
        /*konuTextarea.clear();
        konuTextarea.sendKeys(text);*/
        konuTextarea.setValue(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Kaldırılacak Klasörler

    BelgenetElement kaldiralacakKlasorler = comboLov("input[id$='eklenecekKlasorlerLov:LovText']");
    @Step("Kaldırılacak Klasörler")
    public BelgenetElement kaldiralacakKlasorlerCombolov(){
        return kaldiralacakKlasorler;
    }

    @Step("Kaldırılacak Klasörleri seç ")
    public BilgilerTab2 kaldiralacakKlasorleriSec(String text){
        kaldiralacakKlasorler.selectLov(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Kayıt Tarihi
    SelenideElement kayitTarihiDateInput = $("input[id$='kayitTarih_input']");

    @Step("Kayit Tarihi doldur")
    public BilgilerTab2 kayitTarihiDoldur(String text){
        kayitTarihiDateInput.setValue(text);
        return this;
    }

    @Step("Kayit Tarihi değeri al")
    public String kayitTarihiDegeriAl(){
        String text = kayitTarihiDateInput.text();
        Allure.addAttachment("Değer", text);
        return text;
    }
    //endregion
    
//******************************************************
    
    //region Evrak Dili
    SelenideElement evrakDiliSelect = $("select[id$='evrakDili']");

    @Step("Evrak Dili seç")
    public BilgilerTab2 evrakDiliSec(String text){
        evrakDiliSelect.selectOption(text);
        return this;
    }

    @Step("Evrak Dili seç")
    public BilgilerTab2 evrakDiliSec(EvrakDili evrakDili){
        evrakDiliSelect.selectOption(evrakDili.getOptionText());
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Gizlilik Derecesi
    SelenideElement gizlilikDerecesiSelect = $("select[id$=guvenlikKodu]");

    @Step("Gizlilik Derecesi seç")
    public BilgilerTab2 gizlilikDerecesiSec(String text){
        gizlilikDerecesiSelect.selectOption(text);
        return this;
    }

    @Step("Gizlilik Derecesi seç")
    public BilgilerTab2 gizlilikDerecesiSec(Enum gizlilikDerecesi){
        if (!gizlilikDerecesi.getClass().equals(GizlilikDerecesi.class))
            throw new RuntimeException("Yanlış input enum. Olması gereken: " + GizlilikDerecesi.class.toString());

        gizlilikDerecesiSelect.selectOption(((GizlilikDerecesi) gizlilikDerecesi).getOptionText());
        return this;
    }

    /*@Step("Gizlilik Derecesi seç")
    public BilgilerTab2 gizlilikDerecesiSec(GizlilikDerecesi gizlilikDerecesi){
        gizlilikDerecesiSelect.selectOption(gizlilikDerecesi.getOptionText());
        return this;
    }*/
    //endregion
    
//******************************************************
    
    //region Kanun Kapsam Tipi
    ElementsCollection kanunKapsamTipiRadioButtons = $$("table[id$='kanunKapsamTipiRadio'] input");

    @Step("Kanun Kapsam Tipi seç")
    public BilgilerTab2 kanunKapsamTipiSec(String radioText){
        SelenideElement radio = kanunKapsamTipiRadioButtons.first();
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
        SelenideElement selectedRadio = kanunKapsamTipiRadioButtons
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
    SelenideElement evrakSayiEkMetniInput = $("input[id$=evrakSayiEkMetniInputText]");

    @Step("Evrak Sayı Ek Metni doldur")
    public BilgilerTab2 evrakSayiEkMetniDoldur(String text){
        evrakSayiEkMetniInput.setValue(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region İvedilik
    SelenideElement ivedilikSelect = $("select[id$=ivedilik]");

    @Step("İvedilik seç")
    public BilgilerTab2 ivedilikSec(Enum ivedilik){
        if (!ivedilik.getClass().equals(Ivedilik.class))
            throw new RuntimeException("Yanlış input enum. Olması gereken: " + GizlilikDerecesi.class.toString());
        ivedilikSelect.selectOption(((Ivedilik)ivedilik).getOptionText());
        return this;
    }

    @Step("İvedilik seç")
    public BilgilerTab2 ivedilikSec(String text){
        ivedilikSelect.selectOption(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Miat
    SelenideElement miatDateInput = $("input[id$=miatCalendar_input]");
    SelenideElement miatTemizleButton = $("button[id$=miatTarihTemizle]");

    @Step("Miat doldur")
    public BilgilerTab2 miatDoldur(String text){
        miatDateInput.setValue(text);
        return this;
    }

    @Step("Miat temizle")
    public BilgilerTab2 miatTemizle(){
        miatTemizleButton.click();
        miatDateInput.shouldBe(empty);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Açıklama
    SelenideElement aciklamaTextarea = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Açıklama']]//textarea");

    @Step("Açıklamayı doldur")
    public BilgilerTab2 aciklamaDoldur(String text){
        aciklamaTextarea.setValue(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Bilgi Seçim Tipi
    SelenideElement bilgiSecimTipiSelect = $x("tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Bilgi Seçim Tip']]//select");

    @Step("Bilgi Seçim Tipi seç")
    public BilgilerTab2 bilgiSecimTipiSec(String text){
        bilgiSecimTipiSelect.selectOption(text);
        return this;
    }
    //endregion
    
//******************************************************
    
    //region Bilgi
    BelgenetElement bilgiCombolov = comboLov("input[id$='bilgiLov:LovText']");

    @Step("Bilgi alan")
    public BelgenetElement bilgiCombolov(){
        return bilgiCombolov;
    }

    @Step("Bilgi seç")
    public BilgilerTab2 bilgiSec(String text){
        bilgiCombolov.selectLov(text);
        return this;
    }
    //endregion
    
//******************************************************
  
    //region Gereği Seçim Tipi
    SelenideElement geregiSecimTipiSelect = $x("//tr[@class='ui-datagrid-row' and descendant::label[normalize-space(text())='Gereği Seçim Tipi']]//select");

    @Step("Gereği Seçim Tipi seç")
    public BilgilerTab2 geregiSecimTipiSec(String text){
        geregiSecimTipiSelect.selectOption(text);
        return this;
    }
    //endregion
    
//******************************************************
  
    //region Gereği
    BelgenetElement geregiCombolov = comboLov("input[id$='geregiLov:LovText']");

    @Step("Gereği alanı")
    public BelgenetElement geregiCombolov(){
        return comboLov("input[id$='geregiLov:LovText']");
    }

    @Step("Gereği seç")
    public BilgilerTab2 geregiSec(String text){
        geregiCombolov.selectLov(text);
        return this;
    }
    //endregion

//******************************************************

    //region Dağıtımı Ek Yap
    SelenideElement dagitimiEkYapCheckbox = $("div[id$=dagitimEkYapCheckBoxId]").find("input");

    @Step("Dağıtımı Ek Yap seç")
    public BilgilerTab2 dagitimiEkYapSec(boolean setSelected){
        dagitimiEkYapCheckbox.setSelected(setSelected);
        return this;
    }
    //endregion

//******************************************************
    
    //region Onay Akışı
    BelgenetElement onayAkisiCombolov = comboLov("table[id$='akisOlusturPanelGrid'] input[id$='akisLov:LovText']");

    @Step("Onay Akışı alan")
    public BelgenetElement onayAkisiCombolov(){
        return onayAkisiCombolov;
    }

    @Step("Onay Akışı doldur")
    public BilgilerTab2 onayAkisiSec(String... texts){
        for (int i = 0; i < texts.length; i++) {
            onayAkisiCombolov.selectLov(texts[0]);
        }
        onayAkisiCombolov.closeTreePanel();
        return this;
    }

    @Step("Onay Akışı alanı temizle")
    public BilgilerTab2 onayAkisiTemizle(){
        onayAkisiCombolov.clearAllSelectedItems();
        return this;
    }

    //Onay Akışı Ekle
    SelenideElement onayAkisiEkleButton = $("button[id$=onayAkisiEkle]");

    @Step("Onay Akışı Ekle butona tıkla")
    public BilgilerTab2 onayAkisiEkleButonaTikla(){
        onayAkisiEkleButton.click();
        return this;
    }

    //Otomatik Onay Akışı Ekle
    SelenideElement otomatikOnayAkisiEkleButton = $("button[id$=otomatikOnayAkisiEkle]");

    @Step("Otomatik Onay Akışı Ekle butona tıkla")
    public BilgilerTab2 otomatikOnayAkisiEkleButonaTikla(){
        otomatikOnayAkisiEkleButton.click();
        otomatikOnayAkisiIslemleriDialog.shouldBe(visible);
        return this;
    }

    //Otomatik Onay Akışı İşlemleri Dialog
    SelenideElement otomatikOnayAkisiIslemleriDialog = $("div[id$=hiyerarsikAkisOlusturDialog]");

    //Otomatik Onay Akışı İşlemleri Dialog Title
    public SelenideElement otomatikOnayAkisiIslemleriDialogTitle = otomatikOnayAkisiIslemleriDialog.$(".ui-dialog-titlebar .ui-dialog-title");

    //Otomatik Onay Akışı İşlemleri Dialog Close
    SelenideElement otomatikOnayAkisiIslemleriDialogClose = otomatikOnayAkisiIslemleriDialog.$(".ui-dialog-titlebar .ui-dialog-titlebar-close");

    SelenideElement otomatikOnayAkisiIslemleri = $("div[id$='hiyerarsikAkisOlusturForm:otomatikAkisKullaniciBirimListId']");

    /**
     *
     * @return full row
     */
    public ElementsCollection otomatikOnayAkisiIslemleriList = otomatikOnayAkisiIslemleri.$$("tr[data-ri][data-rk]");

    /**
     * Return list of columns
     * @param columnIndex starts with 1
     * @return
     */
    private ElementsCollection otomatikOnayAkisiIslemleriList(int columnIndex){
        String index = String.valueOf(columnIndex - 1);
        return otomatikOnayAkisiIslemleri.$$("tr[data-ri][data-rk] td:nth-child(" + index + ")");
    }

    ElementsCollection otomatikOnayAkisiIslemlerindeTumSecilenleri(){
        return otomatikOnayAkisiIslemleri.$$("tr[data-ri][data-rk] div[class~='ui-state-active']");
    }

    @Step("Otomatik Onay Akışı İşlemlerinde tüm seçilenleri kaldır")
    public BilgilerTab2 otomatikOnayAkisiIslemlerindeTumSecilenleriKaldir(){
        int count = otomatikOnayAkisiIslemlerindeTumSecilenleri().size();
        Allure.addAttachment("Seçilen sayısı", String.valueOf(count));

        while (otomatikOnayAkisiIslemlerindeTumSecilenleri().size() > 0){
            Allure.addAttachment("Seçileni kaldır", otomatikOnayAkisiIslemlerindeTumSecilenleri().first().toString());
            otomatikOnayAkisiIslemlerindeTumSecilenleri().first().click();
            otomatikOnayAkisiIslemlerindeTumSecilenleri().shouldHaveSize(--count);
        }
        return this;
    }

    /**
     * Find rows with column with exact text
     * @param searchTextFirstExact
     * @return List of rows
     */
    @Step("Otomatik Onay Akışı İşlemlerinde bul")
    public ElementsCollection otomatikOnayAkisiIslemlerindeBul(String... searchTextFirstExact){
        ElementsCollection rows = $$("__");
        if (searchTextFirstExact.length == 0)
            throw new RuntimeException("Arama kriterleri verilmedi: input parameter searchText boş");

        rows = otomatikOnayAkisiIslemleri.$$x("//tr[@data-ri and @data-rk " +
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
    }

    @Step("Otomatik Onay Akışında bul ve seç")
    public BilgilerTab2 otomatikOnayAkisindaBulVeSec(String secilecekIslem, String... searchTextFirstExact){
        otomatikOnayAkisiEkleButonaTikla();
        otomatikOnayAkisiIslemleriDialog.shouldBe(visible);
        otomatikOnayAkisiIslemleriDialogTitle.shouldHave(text("Otomatik Onay Akışı İşlemleri"));

        SelenideElement row = otomatikOnayAkisiIslemlerindeBul(searchTextFirstExact).shouldHave(sizeGreaterThan(0)).first();

        if (row.attr("class").contains("ui-state-active"))
            Allure.addAttachment("Seçiliydi", row.toString());
        else {
            row.$(".ui-chkbox-box").click();
//            Allure.addAttachment("Seçildi", row.toString());
        }

        otomatikOnayAkisindaIslemiSec(row, secilecekIslem);
        return this;
    }

    @Step("Otomatik Onay Akışında işlem seç")
    public BilgilerTab2 otomatikOnayAkisindaIslemiSec(SelenideElement row, String secilecekIslem){
        row.$("select").selectOption(secilecekIslem);
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri kullan tıkla")
    public BilgilerTab2 otomatikOnayAkisiIslemleriKullanTikla(){
        otomatikOnayAkisiIslemleriDialog.$("button[id$='hiyerarsikAkisOlusturForm:hiyerarsikAkisKullan']").click();
        return this;
    }

    @Step("Otomatik Onay Akışı İşlemleri penceriyi kapat")
    public BilgilerTab2 otomatikOnayAkisiIslemleriDialogKapat(){
        otomatikOnayAkisiIslemleriDialogClose.click();
        return this;
    }
    //endregion

//******************************************************
    
    //region Onay Akışı Kullanıcılar

    BelgenetElement onayAkisiKullanicilarCombolov = comboLov("input[id$='akisAdimLov:LovText']");
    SelenideElement kullanButton = $("button[id$='anlikAkisKullanButton']");

    @Step("Onay akışı kullanıcıları seç")
    public BilgilerTab2 onayAkisiKullanicilarSec(String... texts){
        for (int i = 0; i < texts.length; i++) {
            onayAkisiKullanicilarCombolov.selectLov(texts[i]);
        }
        return this;
    }

    @Step("Onay akışı kullanıcı tipi seç")
    public BilgilerTab2 onayAkisiKullaniciTipiSec(String kullaniciAdi, String kullaniciTipi) {
        onayAkisiKullanicilarCombolov.getSelectedItems()
                .filterBy(text(kullaniciAdi))
                .get(0)
                .shouldBe(exist)
                .$("select[id*='selectOneMenu']")
                .selectOptionContainingText(kullaniciTipi);
        return this;
    }

    @Step("Kullanıcılar alan")
    public BelgenetElement kullanicilarCombolov(){
        return onayAkisiKullanicilarCombolov;
    }

    SelenideElement koordineliCheckbox(){
        return $("input[id$='koordineliBooleanCheckbox_input']");
    }

    @Step("Kullan buton")
    public SelenideElement kullanButton(){
        return kullanButton;
    }

    @Step("Onay akiş kullanıcıları kullan")
    public BilgilerTab2 kullanButonaTikla(){
        kullanButton.pressEnter();
        return this;
    }
    //endregion

}
