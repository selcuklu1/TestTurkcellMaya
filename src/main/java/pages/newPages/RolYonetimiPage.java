package pages.newPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.SearchTable;
import pages.pageComponents.UstMenuPageHeader;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData.KullaniciIslemleri;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import static pages.pageComponents.belgenetElements.BelgentCondition.isChecked;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 13.03.2018
 * Açıklama:
 */
public class RolYonetimiPage extends MainPage {

    public final KullaniciIslemleri pageTitle = KullaniciIslemleri.RolYonetimi;

    public RolYonetimiPage openPage() {
        ustMenu(pageTitle);
        pageHeader().getPageTitle().shouldHave(text(pageTitle.getName()));
        return this;
    }

    public UstMenuPageHeader pageHeader() {
        return new UstMenuPageHeader(rolYonetimiListingForm);
    }

    @Step("Sayfayı kapat")
    public RolYonetimiPage closePage() {
        pageHeader().closePage();
        confirmDialog().confirmEvetTikla();
        return this;
    }

    //region Sorgulama ve Filtreleme
    SelenideElement rolYonetimiListingForm = $(By.id("rolYonetimiListingForm"));
    SelenideElement sorgulamaAdInput = $(By.id("rolYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement sorgulamaDurumSelect = $(By.id("rolYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement sorgulamaAraButton = $(By.id("rolYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement yeniRolButton = $(By.id("rolYonetimiListingForm:rolDataTable:addNewRolButton"));
    public SearchTable rolDataTable = new SearchTable($(By.id("rolYonetimiListingForm:rolDataTable")));

    By guncelleButtonLocator = By.cssSelector("button[id$=updateRolButton]");
    By rolKopyalaButtonLocator = By.cssSelector("button[id$=duplicateRolButton]");
    By aksiyonlariButtonLocator = By.cssSelector("button[id$=rolAksiyonlariButton]");
    By rolStatuButtonLocator = By.cssSelector("button[id$=changeRolStatusButton]");
    By toPassiveButtonLocator = By.cssSelector("button[id$=changeRolStatusButton] .to-passive-status-icon");
    By toActiveButtonLocator = By.cssSelector("button[id$=changeRolStatusButton] .to-active-status-icon");

    @Step("Sorgulama ve Filtrelemeyi genişlet")
    public RolYonetimiPage sorgulamayiGenislet(boolean... genislet) {
        SelenideElement element = rolYonetimiListingForm.$("h3[role=tab]").shouldBe(visible);

        if (!element.attr("aria-expanded").equalsIgnoreCase(String.valueOf(genislet.length > 0 ? genislet[0] : "true")))
            element.find("a").click();
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ad\" alana \"{text}\" girilir")
    public RolYonetimiPage sorgulamadaAdGir(String text) {
        sorgulamayiGenislet();
        sorgulamaAdInput.setValue(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Durum\" alanda \"{text}\" seçilir")
    public RolYonetimiPage sorgulamadaDurumSec(String text) {
        sorgulamayiGenislet();
        sorgulamaDurumSelect.selectOption(text);
        return this;
    }

    @Step("Sorgulama ve Filtrelemede \"Ara\" butona tıklanır")
    public RolYonetimiPage ara() {
        sorgulamayiGenislet();
        sorgulamaAraButton.click();
        return this;
    }

    @Step("Yeni Rol butona tıklanır")
    public RolYonetimiPage yeniRol() {
        yeniRolButton.pressEnter();
        return this;
    }

    @Step("Rol Listesinde {aramaKriteri} arama kritere göre kayıt bulunur")
    public RolYonetimiPage rolListesindeKayitBul(Condition... aramaKriteri){
        rolDataTable.findRows(aramaKriteri).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Bulunan rolde Güncelle butona tıkla")
    public RolYonetimiPage bulunanRoldeGuncelle(){
        rolDataTable.getFoundRow().$(guncelleButtonLocator).click();
        return this;
    }

    @Step("Bulunan rolde Rol Kopyala butona tıkla")
    public RolYonetimiPage bulunanRoldeRolKopyala(){
        rolDataTable.getFoundRow().$(rolKopyalaButtonLocator).click();
        return this;
    }

    @Step("Bulunan rolde Aksiyonlar butona tıkla")
    public RolYonetimiPage bulunanRoldeAksiyonlarTikla(){
        rolDataTable.getFoundRow().$(aksiyonlariButtonLocator).click();
        return this;
    }

    @Step("Bulunan rolde Aktif statü {statu} yapılır")
    public RolYonetimiPage bulunanRoldeAktifStatuDegistir(boolean aktif){
        if (aktif != rolDataTable.getFoundRow().$(toPassiveButtonLocator).exists())
            rolDataTable.getFoundRow().$(rolStatuButtonLocator).click();
        return this;
    }

    @Step("Bulunan rol Aktif mı")
    public boolean bulunanRolAktifMi(){
        boolean aktif = rolDataTable.getFoundRow().$(toPassiveButtonLocator).exists();
        if (!aktif)
            rolDataTable.getFoundRow().$(toActiveButtonLocator).shouldBe(visible);
        return aktif;
    }
    //endregion

    SelenideElement rolYonetimiEditorForm = $(By.id("rolYonetimiEditorForm"));

    //region Yeni, Güncelle
    SelenideElement rolEklemeAdInput = $(By.id("rolYonetimiEditorForm:rolAdiInput"));
    SelenideElement rolEklemeKisaAdInput = $(By.id("rolYonetimiEditorForm:rolKisaAdiInput"));
    SelenideElement rolEklemeEtiketInput = $(By.id("rolYonetimiEditorForm:etiketInput"));
    SelenideElement rolEklemeDegerKodInput = $(By.id("rolYonetimiEditorForm:degerKodInput"));
    SelenideElement rolEklemeAciklamaTextarea = $(By.id("rolYonetimiEditorForm:aciklamaInput"));
    SelenideElement rolEklemeYetkiOnceligiInput = $(By.id("rolYonetimiEditorForm:siraInput"));
    SelenideElement rolEklemeKaydetButton = $(By.id("rolYonetimiEditorForm:saveRolButton"));
    SelenideElement rolEklemeIptalButton = $(By.id("rolYonetimiEditorForm:cancelSaveRolButton"));

    public SearchTable durumGecmisiDataTable = new SearchTable($("tbody[id$='tipAktifDataTable_data']"));
    //endregion

    //region Aksiyonlar
    SelenideElement yeniAksiyonButton = $(By.id("rolYonetimiEditorForm:rolAksiyonDataTable:newRolAksiyonButton"));
    public SearchTable aksiyonDataTable = new SearchTable($(By.id("rolYonetimiEditorForm:rolAksiyonDataTable")));
    SelenideElement aksiyonListesindeAdInput = $x("//div[@id='rolYonetimiEditorForm:rolAksiyonDataTable']//th[.//span[.='Ad']]//input");
    BelgenetElement aksitonGrupCombolov = comboLov(By.id("rolYonetimiEditorForm:rolAksiyonDataTable:lovAksiyonGrup:LovText"));

    By icerikGosterButtonLocator = By.cssSelector("button[id$=viewRolAksiyonButton]");
    By iliskiyiSilButtonLocator = By.cssSelector("button[id$=deleteRolAksiyonButton]");


    @Step("Yeni Aksiyon ekle")
    public YeniAksiyonIliskilendirme yeniAksiyonEkle(){
        yeniAksiyonButton.click();
        return new YeniAksiyonIliskilendirme();
    }

    @Step("Aksiyon Listesinde Ad alana {ad} girilir")
    public RolYonetimiPage aksiyonListesindeAdGirilir(String ad){
        /*aksiyonListesindeAdInput.shouldBe(visible).clear();
        sleep(3000);
        aksiyonListesindeAdInput.sendKeys(ad);*/
        //aksiyonListesindeAdInput.setValue(ad);
        setValueJS(aksiyonListesindeAdInput, ad);
        aksiyonListesindeAdInput.sendKeys(Keys.RIGHT);
        return this;
    }

    @Step("Aksiyon Listesinde Grup alana {ad} seçilir")
    public RolYonetimiPage aksiyonGrupSecilir(String grup){
        aksitonGrupCombolov.selectLov(grup);
        return this;
    }

    @Step("Aksiyon Listesinde {aramaKriteri} arama kritere göre kayıt ")
    public boolean aksiyonListesindeKayitVarMi(Condition... aramaKriteri){
        aksiyonDataTable.findRows(aramaKriteri);
        return aksiyonDataTable.isRowsExist();
    }

    @Step("Aksiyon Listesinde {aramaKriteri} arama kritere göre kayıt bulunur")
    public RolYonetimiPage aksiyonListesindeKayitBulunur(Condition... aramaKriteri){
        aksiyonDataTable.findRows(aramaKriteri).shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Bulunan aksiyon kayıtta \"İçerik Göster\" butona tıkla")
    public RolYonetimiPage bulunanAksiyondaIcerikGoster(){
        aksiyonDataTable.getFoundRow().$(icerikGosterButtonLocator).click();
        return this;
    }

    @Step("Bulunan aksiyon kayıtta \"İlişkiyi Sil\" butona tıkla")
    public RolYonetimiPage bulunanAksiyondaIliskiyiSilTiklanir(){
        aksiyonDataTable.getFoundRow().$(iliskiyiSilButtonLocator).click();
        return this;
    }

    @Step("Bulunan aksiyon kayıtta \"İlişkiyi Sil\" tıklanır ve başarılı silindiğini kotrol edilir")
    public RolYonetimiPage bulunanAksiyondaIliskiyiSilBasarili(){
        aksiyonDataTable.getFoundRow().$(iliskiyiSilButtonLocator).click();
        confirmDialog()
                .onayMesajKontrolu(text("Rolün aksiyonunu silmek istediğinize emin misiniz?"))
                .confirmEvetTikla()
                .islemMesaji().basariliOlmali();
        Assert.assertFalse(aksiyonDataTable.isRowsExist(), "Kayıt Bulunamamıştır olmalı");
        return this;
    }

    public class YeniAksiyonIliskilendirme {
        SelenideElement container = $(By.id("rolAksiyonUpdateDialog"));
        //rolYonetimiAksiyonFiltrelemeForm:filterPanel
        SelenideElement sorgulamaAdInput = $(By.id("rolYonetimiAksiyonFiltrelemeForm:filterPanel:adFilterInput"));
        SelenideElement sorgulamaAraButton = $(By.id("rolYonetimiAksiyonFiltrelemeForm:filterPanel:searchEntitiesButton"));
        SelenideElement aksiyonEkleButton = $(By.id("rolAksiyonEditorForm:addActionButton"));

        By checkbox = By.cssSelector(".ui-chkbox-box");

        public SearchTable aksiyonDataTable = new SearchTable($(By.id("rolAksiyonEditorForm:eklenecekAksiyonList")));

        @Step("Yeni Aksiyon İlişkilendirme - Sorgulama ve Filtrelemeyi genişlet")
        public YeniAksiyonIliskilendirme sorgulamayiGenislet(boolean... genislet) {
            SelenideElement element = container.$("h3[role=tab]").shouldBe(visible);

            if (!element.attr("aria-expanded").equalsIgnoreCase(String.valueOf(genislet.length > 0 ? genislet[0] : "true")))
                element.find("a").click();
            return this;
        }

        @Step("Yeni Aksiyon İlişkilendirme - Sorgulama ve Filtrelemede \"Ad\" alana \"{text}\" girilir")
        public YeniAksiyonIliskilendirme sorgulamadaAdGir(String text) {
            sorgulamayiGenislet();
            sorgulamaAdInput.setValue(text);
            return this;
        }

        @Step("Yeni Aksiyon İlişkilendirme - Sorgulama ve Filtrelemede \"Ara\" butona tıklanır")
        public YeniAksiyonIliskilendirme ara() {
            sorgulamayiGenislet();
            sorgulamaAraButton.click();
            return this;
        }

        @Step("Yeni Aksiyon İlişkilendirme - Sorgulama ve Filtrelemede \"Ara\" tıklanır ve kayıt bulunur")
        public YeniAksiyonIliskilendirme ara(String aksiyon) {
            sorgulamayiGenislet();
            sorgulamaAraButton.click();
            aksiyonListesindeKayitBul(text(aksiyon));
            return this;
        }

        @Step("Yeni Aksiyon İlişkilendirme - Aksiyon Listesinde {aramaKriteri} arama kritere göre kayıt bulunur")
        public YeniAksiyonIliskilendirme aksiyonListesindeKayitBul(Condition... aramaKriteri){
            aksiyonDataTable.findRows(aramaKriteri).shouldHave(sizeGreaterThan(0));
            return this;
        }

        @Step("eni Aksiyon İlişkilendirme - Bulunan aksiyon kayıtta checkbox {aktif} seçilir")
        public YeniAksiyonIliskilendirme bulunanAksiyondaCheckboxSecilir(boolean aktif){
            if (aksiyonDataTable.getFoundRow().$("div.ui-chkbox-box").is(isChecked) != aktif)
                aksiyonDataTable.getFoundRow().$("div.ui-chkbox-box").click();
            return this;
        }

        @Step("eni Aksiyon İlişkilendirme - \"{aksiyon}\" aksiyon bulunur ve checkbox {aktif} seçilir")
        public YeniAksiyonIliskilendirme aksiyonBulunurVeCheckboxSecilir(String aksiyon, boolean aktif){
            aksiyonListesindeKayitBul(text(aksiyon));
            if (aksiyonDataTable.getFoundRow().$("div.ui-chkbox-box").is(isChecked) != aktif)
                aksiyonDataTable.getFoundRow().$("div.ui-chkbox-box").click();
            return this;
        }

        @Step("eni Aksiyon İlişkilendirme - Ekle butona tıklanir")
        public YeniAksiyonIliskilendirme ekleButonaTiklanir(){
            clickJs(aksiyonEkleButton);
            return this;
        }

        @Step("eni Aksiyon İlişkilendirme - Ekle tıkla, Başarılı mesajı ve dialog penceresinin kapandığını kontrol et")
        public YeniAksiyonIliskilendirme ekleBasarili(){
            clickJs(aksiyonEkleButton);
            islemMesaji().basariliOlmali();
            container.should(disappear);
            return this;
        }

    }

    //endregion

}
