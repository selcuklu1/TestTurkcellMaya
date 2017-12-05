package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class VekaletVerPage extends MainPage {

    BelgenetElement txtVekaletVerenCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText"));
    SelenideElement btnVekaletVerenCombolov = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    BelgenetElement txtVekaletAlanCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu = $(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));
    SelenideElement btnEvrakEkle = $("[id$='onayEvrakiDialogButton']");
    ElementsCollection tblDevredilecekEvrakklar = $$("tbody[id='vekaletVerForm:vekaletLayout:devredileceklerTabView:vekaletDataTable_data'] tr[role='row'][data-rk]");
    SelenideElement tabVekaletListesi = $("a[href='#evrakBilgileriForm:evrakEkTabView:aciklamaEkleTab']");


    SelenideElement btnVekalelVerenTemizle = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    By txtVekaletVeren = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText']");
    By txtVekaletAlan = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText']");
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletOnaylayacakKisiLov:LovText"));

    // Evrak Arama

    SelenideElement txtEvrakArama = $("[id$='evrakAramaText']");
    SelenideElement btnDokumanAra = $(By.id("vekaletOnayEvrakDialogForm:dokumanAraButton"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId_data'] tr[role=row]");

    SelenideElement btnSorgula = $(By.id("vekaletVerForm:vekaletLayout:vekaletSorgula_Id"));

    @Step("Vekalet Ver sayfası aç")
    public VekaletVerPage openPage() {
        new UstMenu().ustMenu("Vekalet Ver");
        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public VekaletVerPage vekaletVerenAlanınaGoruntulenmemeKontrolu(String bilgi, Boolean secilebilmeli) {
        Assert.assertEquals(secilebilmeli, comboLov(txtVekaletVeren).isLovValueSelectable(bilgi));
        return this;
    }

    @Step("Kişinin Bilgi alanında görüntülenmediği kontrolu")
    public VekaletVerPage vekaletAlanAlanınaGoruntulenmemeKontrolu(String bilgi, Boolean secilebilmeli) {
        Assert.assertEquals(secilebilmeli, comboLov(txtVekaletAlan).isLovValueSelectable(bilgi));
        System.out.println("Vekalet alan alanı başarılı");
        return this;
    }

    @Step("Vekalet veren alanını farklı doldur")
    public VekaletVerPage vekaletVerenFarkliDoldur(String text) {
        btnVekalelVerenTemizle.click();
        txtVekaletVerenCombolov.selectLov(text);
        return this;
    }

    @Step("Onay verecek doldur")
    public VekaletVerPage onayVerecekDoldur(String kullanici) {
        txtOnaylayacakKisi.selectLov(kullanici);
        return this;
    }

    @Step("Vekalet veren alanını doldur")
    public VekaletVerPage vekaletVerenDoldur(String text) {
        txtVekaletVerenCombolov.selectLov(text);
        return this;
    }

    @Step("Vekalet alan alanını doldur")
    public VekaletVerPage vekaletAlanDoldur(String text) {
        txtVekaletAlanCombolov.selectLov(text);
        return this;
    }


    public VekaletVerPage uygula() {
        btnUygula.click();
        return this;
    }

    public VekaletVerPage aciklamaDoldur(String aciklama) {
        txtAciklama.setValue(aciklama);
        return this;
    }

    public VekaletVerPage ozelUnvanKullanSec(boolean secim) {
        chkOzelUnvanKullan.setSelected(secim);
        return this;
    }

    public VekaletVerPage evraktaVelaketeSonEkiGorunsunSec(boolean secim) {
        chkEvraktaVelaketeSonEkiGorunsun.setSelected(secim);
        return this;
    }

    public VekaletVerPage bitisTarihiDoldur(String text) {
        txtBitisTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage baslangicTarihDoldur(String text) {
        txtBaslangicTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage tumuSec(boolean secim) {
        chkTumu.setSelected(secim);
        return this;
    }

    @Step("Evrak Ekle butnu")
    public VekaletVerPage evrakEkle() {
        clickJs(btnEvrakEkle);
        return this;
    }

    @Step("Evrak arama doldur")
    public VekaletVerPage evrakAramaDoldur(String evrakNo) {
        txtEvrakArama.sendKeys(evrakNo);
        return this;
    }

    @Step("Tablo Kontrolü ve seçim")
    public VekaletVerPage evrakAramaTabloKontrolveSecim(String evrakNo) {
        tblEvrakListesi
                .filterBy(Condition.text(evrakNo)).shouldHaveSize(1)
                .first()
                .$("[id^='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId'][id$='ekleButton']").click();
        return this;
    }

    @Step("Devredilecek Evraklar kontrolü")
    public VekaletVerPage devredilecekEvraklarKontrolu() {
        int size = tblDevredilecekEvrakklar.size();
        Assert.assertNotEquals(size, 0);
        return this;
    }

    @Step("Devredilecek Evrak seç")
    public VekaletVerPage devredilecekEvrakSec(String evrakNo) {
        tblDevredilecekEvrakklar
                .filterBy(Condition.text(evrakNo)).first()
                .$("[class='ui-chkbox ui-widget']").click();
        return this;
    }

    @Step("Dokuman ara")
    public VekaletVerPage dokumanAra() {
        btnDokumanAra.click();
        return this;
    }
    @Step("Vekalet Listesi aç")
    public VekaletVerPage veklatListeiTabAc(){
        tabVekaletListesi.click();
        return this;
    }
    @Step("Sorgula butonu")
    public VekaletVerPage sorgula(){
        btnSorgula.click();
        return this;
    }
}
