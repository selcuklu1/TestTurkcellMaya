package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.*;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage extends BaseLibrary {
    SelenideElement mainPageLeftContainer = $("#mainInboxForm");
    SelenideElement mainPageLeftContainerDataTable = $("#mainInboxForm\\:inboxDataTable");

    public SelenideElement getMainPageLeftContainer() {
        return mainPageLeftContainer;
    }

    public SelenideElement getMainPageLeftContainerDataTable() {
        return mainPageLeftContainerDataTable;
    }

    public SearchTable searchTable() {
        return new SearchTable(mainPageLeftContainerDataTable);
    }

    public Filtreler filter() {
        return new Filtreler();
    }

    public void ustMenu(Enum ustMenuData, boolean... useJS) {
        new UstMenu().openMenu(ustMenuData, useJS);
    }

    public void solMenu(Enum solMenuData, boolean... useJS) {
        new SolMenu().openMenu(solMenuData, useJS);
    }

    public MainPage solMenu(Enum solMenuData) {
        return new SolMenu().openMenu(solMenuData);
    }

    public MainPage solMenu2(Enum solMenuData) {
        return new SolMenu().openMenu2(solMenuData);
    }

    public IslemMesajlari islemMesaji() {
        return new IslemMesajlari();
    }

    @Step("Kep bağlantısı alanı aç")
    public MainPage kepBaglantisi() {
        $(By.id("topMenuForm:userMenuButton_button")).click();
        $(By.id("topMenuForm:kepLoginButton")).click();
        return this;
    }

    @Step("Bağlan")
    public MainPage kepAdresBaglantisiBaglan1() {
        $("[id^='kepForm:kayitliKepDataTable:0:j_idt235']").click();
        return this;
    }

    @Step("Kullanıcı Adı ve TC Kimlik No alanlarında kullanıcının verileri görüntülenir")
    public MainPage kullaniciAdiVeTCKimlikNoLoginOlunanKullaniciGeldigiGorme() {
        Boolean durum1 = $("[id$='kullaniciAdi']").shouldBe(visible).exists() == true;
        Boolean durum2 = $("[id$='tcKimlikNo']").shouldBe(visible).exists() == true;
        Assert.assertEquals(durum1, durum2);
        takeScreenshot();
        return this;
    }

    public MainPage kepAdresBaglantisiBaglan2() {
        $("[id='kepForm:kayitliKepDataTable:1:j_idt235']").click();
        return this;
    }

    @Step("{kep} kep olan bağlan tıklanır")
    public MainPage kepAdresleriBaglan(String kep) {
        $$("[id='kepForm:kayitliKepDataTable_data'] tr").filterBy(Condition.text(kep))
                .get(0).$("button").click();
        return this;
    }

    @Step("Kullanıcı adı ve Tc Kimlik no alanlarındaki bilgileri değiştirilir - Değiştirilemez olduğu görülür")
    public MainPage kullaniciAdiTcKimlikNoKontol() {
        $(By.id("kepLogin2FormId:kullaniciAdi")).shouldBe(Condition.disabled);
        $(By.id("kepLogin2FormId:tcKimlikNo")).shouldBe(Condition.disabled);
        return this;
    }

    @Step("Parola doldur")
    public MainPage parolaDoldur(String parola) {
        $(By.id("kepLogin2FormId:parola")).setValue(parola);
        return this;
    }

    @Step("Şifre Doldur")
    public MainPage sifreDoldur(String sifre) {
        $(By.id("kepLogin2FormId:sifre")).setValue(sifre);
        return this;
    }

    @Step("Bağlan")
    public MainPage kepBaglantisiBaglan() {
        $(By.id("kepLogin2FormId:j_idt255")).click();
        return this;
    }

    @Step("Çıkış yap")
    public void logout() {
        $("button[id='topMenuForm:userMenuButton_button']").click();
        $("#topMenuForm\\:logOutButton").click();

        for (int i = 0; i < 5; i++) {
            if (getIslemOnayDialog().is(visible))
                getIslemOnayDialog().$x("descendant::button[.='Evet']").click();
            sleep(1000);
        }
    }

    public SelenideElement getIslemOnayDialog() {
        return $x("//div[@id='baseConfirmationDialog:dialog']");
    }


    public MainPage ustMenuEvrakIslemleriAc() {
        $(By.id("topMenuForm2:ust:0:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuKullaniciIslemleri() {
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:3:ustMenuEleman")).click();
        return this;
    }

    public MainPage ustMenuRaporlar() {
        //Thread.sleep(2000);
        $(By.id("topMenuForm2:ust:6:ustMenuEleman")).click();
        return this;
    }

    @Step("Vekalet var uyarı popup")
    public MainPage vekaletVarUyariPopUp() {
        SelenideElement popUpAktifVekaletUyarı = $(By.id("aktifVekaletinizVarUyariMesajiDialog"));
        SelenideElement btnTamam = $(By.id("aktifVekaletinizVarUyariMesajiDialogEvetBtn"));
        popUpAktifVekaletUyarı.exists();
        btnTamam.click();
        return this;
    }

    @Step("Birim Seç")
    public MainPage birimSec(Condition condition) {
        SelenideElement currentBirim = $("#kullaniciBirimAd").shouldBe(visible)
                .shouldHave(matchText(".*"));
        //String currentBirim = $("#kullaniciBirimAd").shouldBe(visible).shouldHave(matchText(".*")).text();

        if (currentBirim.has(condition))
            return this;

        $$("#leftMenuForm #birimlerimMenusuContainer a")
                .filterBy(condition).shouldHave(sizeGreaterThan(0))
                .first().click();

        Allure.addAttachment("Birim Adı : ", $$("#leftMenuForm #birimlerimMenusuContainer a")
                .filterBy(condition).first().getText());
        //$("#leftMenuForm #birimlerimMenusuContainer").$(byLinkText(birim)).click();

        //$("#kullaniciBirimAd").shouldHave(condition);
        return this;
    }

    @Step("Şuanki Birim kontrolü")
    public MainPage currentBirimKontrol(Condition condition) {
        $("#kullaniciBirimAd").shouldHave(condition);
        return this;
    }

    public ConfirmDialog confirmDialog() {
        return new ConfirmDialog();
    }

    public ElementsCollection getPageCloseButtons() {
        return $$("div[id^='window'][id$='Dialog'] > div[class~='ui-dialog-titlebar'] > a[class~='ui-dialog-titlebar-close']");
    }

    public ElementsCollection getPageTitles() {
        return $$("div[id^='window'][id$='Dialog'] > div[class~='ui-dialog-titlebar'] > span[class='ui-dialog-title']");
    }

    public EvrakPageButtons evrakPageButtons() {
        return new EvrakPageButtons();
        //return new EvrakPageButtons($("#mainPreviewForm"));
    }

    @Step("Footer'da açılan sayfa butonu bul")
    public SelenideElement getFooterPageButton(String pageTitle) {
        return $x("//div[@id='mainTaskBar']//div[@type='button']/span[contains(.,'" + pageTitle + "')]");
    }

    @Step("Parafla")
    public MainPage parafla() {
        new EvrakPageButtons().evrakParafla();
        /*SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();
        sImzalaRadioSec();
        evrakImzaOnay();*/
        return this;
    }

    @Step("Koordine Parafla")
    public MainPage koordineParafla() {
        new EvrakPageButtons().evrakKoordineParafla();
        /*SelenideElement paraflaButon = $x("//*[text()='Parafla']/ancestor::tbody[1]//button");
        paraflaButon.click();
        sImzalaRadioSec();
        evrakImzaOnay();*/
        return this;
    }

    @Step("İmzala butona tıkla")
    public MainPage imzalaButonaTikla() {
        new EvrakPageButtons().imzalaButonaTikla();
        //imzalaButton().click();
        return this;
    }

    @Step("Parafla")
    public MainPage evrakParafla() {
        new EvrakPageButtons().evrakParafla();
        /*paraflaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        evrakImzaOnay();*/
        return this;
    }

    @Step("İmzala")
    public MainPage evrakImzala() {
        new EvrakPageButtons().evrakImzala();
        /*imzalaButonaTikla();
        sImzalaRadioSec();
//        clickJs($("#imzalaForm\\:imzalaRadio").find(By.tagName("input")));
        evrakImzaOnay();*/
        return this;
    }

    @Step("Menülerin geldiği görülür")
    public MainPage evrakIslemleriIslemYaptiklarimMenuKontrol() {

        Assert.assertEquals($(By.id("topMenuForm2:ust:0:ustMenuEleman")).isDisplayed(), true);
        Assert.assertEquals(  $(By.id("leftMenuForm:leftMenuIslemBekleyenEvraklar")).isDisplayed(), true);

        takeScreenshot();

        return this;
    }

}
