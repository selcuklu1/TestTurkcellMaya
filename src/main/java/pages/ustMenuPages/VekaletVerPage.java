package pages.ustMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.visible;
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
    SelenideElement tabVekaletListesi = $("a[href='#vekaletVerForm:vekaletLayout:vekaletSorgulaField']");
    SelenideElement tabYeniVekalet = $("a[href='#vekaletVerForm:vekaletLayout:yeniVekaletTab']");

    SelenideElement btnVekalelVerenTemizle = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    By txtVekaletVeren = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText']");
    By txtVekaletAlan = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText']");
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletOnaylayacakKisiLov:LovText"));


    SelenideElement popUpAktifVekaletUyarı = $(By.id("aktifVekaletinizVarUyariMesajiDialog"));
    SelenideElement btnTamam = $(By.id("aktifVekaletinizVarUyariMesajiDialogEvetBtn"));
    // Evrak Arama

    SelenideElement txtEvrakArama = $("[id$='evrakAramaText']");
    SelenideElement btnDokumanAra = $(By.id("vekaletOnayEvrakDialogForm:dokumanAraButton"));
    ElementsCollection tblEvrakListesi = $$("tbody[id='vekaletOnayEvrakDialogForm:sistemdeKayitliEvrakListesiDataTableId_data']");

    //Vekalet Listesi Tabı
    SelenideElement btnSorgula = $(By.id("vekaletVerForm:vekaletLayout:vekaletSorgula_Id"));
    ElementsCollection tblVekaletListesi = $$("[id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel'] tbody tr[role='row']");
    SelenideElement tblVekaletListesi2 = $(By.xpath("//div[@id='vekaletVerForm:vekaletLayout:bulunanVekaletlerPanel']//div[starts-with(@id,'vekaletVerForm:vekaletLayout:j_idt')]"));

    SelenideElement dateTxtVekaletListesiBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBasTarih_input"));
    SelenideElement dateTxtVekaletListesiBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletListeBitTarih_input"));
//    BelgenetElement cmbDurum = comboBox (By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));

    SelenideElement cmbDurum = $(By.xpath("//table[@id='vekaletVerForm:vekaletLayout:vekaletSorgulaPanelGrid']//select"));
    @Step("Vekalet Ver sayfası aç")
    public VekaletVerPage openPage() {
        new UstMenu().ustMenu("Vekalet Ver");
        $("form[id='vekaletVerForm']").shouldBe(visible);
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

    @Step("Vekalet Listesi Tab aç")
    public VekaletVerPage veklatListesiTabAc() {
        tabVekaletListesi.click();
        return this;
    }
    @Step("Yenş vekalet Tab aç")
    public VekaletVerPage yeniVekaletTabAc() {
        tabYeniVekalet.click();
        return this;
    }
    @Step("Sorgula butonu")
    public VekaletVerPage sorgula() {
        btnSorgula.click();
        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiTabloKontrol(int column, String retNedeni) {
        boolean status = findElementOnTableByColumnInputInAllPages(tblVekaletListesi2, column, retNedeni).isDisplayed();
        Assert.assertEquals(status, true);
        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiTabloKontrol() {
       tblVekaletListesi.shouldHave(CollectionCondition.sizeGreaterThan(0));
       return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiVekaletIptal(String vekaletveren) {
        Selenide.sleep(3000); // tablo yavaş geldiğinden sleep koyuldu.
        ElementsCollection rows = tblVekaletListesi
                .filterBy(Condition.text(vekaletveren));

        for (SelenideElement row:rows) {
            row.$("textarea").sendKeys("İptal");
            row.$("button").click();
            SelenideElement popUp = $("[id='vekaletUyariDaialog']");
            popUp.shouldBe(Condition.visible);
            $(By.xpath("//div[@id= 'vekaletUyariDaialog']//button[span[text()='Evet']]")).click();
        }
        return this;
    }

    @Step("Vekalet Listesi bitiş tarihi doldur")
    public VekaletVerPage vekaletListesiBitisTarihiDoldur(String text) {
        dateTxtVekaletListesiBitisTarihi.setValue(text);
        return this;
    }

    @Step("Vekalet Listesi bitiş tarihi doldur")
    public VekaletVerPage vekaletListesiBaslangicTarihiDoldur(String text) {
        dateTxtVekaletListesiBaslangicTarihi.setValue(text);
        return this;
    }

    @Step("Vekalet Listesi Tablo Kontrol")
    public VekaletVerPage vekaletListesiTabloAlanKontrolleri(String onayMakami, String onayDurumu) {
        tblVekaletListesi.filterBy(Condition.matchesText(onayMakami));
        tblVekaletListesi.filterBy(Condition.text(onayDurumu));
        return this;
    }

    @Step("Vekalet var uyarı popup")
    public VekaletVerPage vekaletVarUyarıPopUp() {
        popUpAktifVekaletUyarı.exists();
        btnTamam.click();
        return this;
    }

    @Step("Durum Seç")
    public VekaletVerPage durumSec(String value) {
        cmbDurum.selectOptionByValue(value);
        return this;
    }
}
