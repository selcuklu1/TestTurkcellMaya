package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KullaniciEvrakDevretPage extends MainPage {

    BelgenetElement txtDevredecekKisi = comboLov(By.id("kullaniciEvrakDevretForm:kisidenLov_id:LovText"));
    SelenideElement btnListele = $(By.id("kullaniciEvrakDevretForm:listeleButton"));
    SelenideElement btnDevret = $(By.id("kullaniciEvrakDevretForm:devretButton"));
    ElementsCollection tableTaslakEvraklar = $$("tbody[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devirTaslakEvraklar_data'] > tr[role='row']");
    BelgenetElement txtDevralacakKisi = comboLov(By.id("devredilenKullaniciDialogForm:kisiyeLov_id:LovText"));
    SelenideElement txtAciklama = $(By.id("devredilenKullaniciDialogForm:evrakDevretAciklamaId"));
    SelenideElement btnDevretTamam = $(By.id("devredilenKullaniciDialogForm:evrakDevretTamamButtonId"));
    ElementsCollection tblGelenEvraklar = $$("[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devredilebilecekEvrakListesi_data'] tr[role='row']");
    SelenideElement btnDevretIptal = $(By.id("devredilenKullaniciDialogForm:evrakDevretIptalButtonId"));
    SelenideElement popUpDevredilemeyenEvraklar = $(By.id("devredilemeyenEvraklarRaporDialogId"));
    ElementsCollection tblDevredilemeyenEvraklar = $$("[id='devredilemeyenEvraklarDatatableId_data'] tr[role='row']");

    @Step("Kullanıcı Evrak Devret sayfasını aç")
    public KullaniciEvrakDevretPage openPage() {
        ustMenu(UstMenuData.AmirIslemleri.KullaniciEvrakDevret);
        return this;
    }

    @Step("Devredecek Kişi seç: {devredecekKisi}")
    public KullaniciEvrakDevretPage devredecekKisiSec(String devredecekKisi) {
        txtDevredecekKisi.selectLov(devredecekKisi);
        return this;
    }

    @Step("Panel aç: {panelAdi}")
    public KullaniciEvrakDevretPage panelAc(String panelAdi) {
        $x("//h3[.='" + panelAdi + "']").sendKeys(Keys.SHIFT);
        $x("//h3[.='" + panelAdi + "']").click();
        return this;
    }

    @Step("Listele butonuna tıkla.")
    public KullaniciEvrakDevretPage listele() {
        btnListele.click();
        $("div[id='bekleyinizStatusDialog']").waitUntil(not(visible), 50000);
        return this;
    }

    @Step("Taslak evrak seç")
    public KullaniciEvrakDevretPage taslakEvrakSec(String konu, String gidecegiYer) {

        Boolean isSelected = false;

        SelenideElement currentRow = tableTaslakEvraklar
                .filterBy(text("Konu: " + konu))
                .filterBy(text("Gideceği Yer: " + gidecegiYer))
                .first();

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (isSelected == false)
            currentRowCheckBox.click();

        return this;
    }

    @Step("Devralacak Kişi seç: {devralacakKisi}")
    public KullaniciEvrakDevretPage devralacakKisiSec(String devralacakKisi) {
        txtDevralacakKisi.selectLov(devralacakKisi);
        return this;
    }

    @Step("Açıklama doldur: {aciklama}")
    public KullaniciEvrakDevretPage aciklamaDoldur(String aciklama) {
        txtAciklama.setValue(aciklama);
        return this;
    }

    @Step("Devret butonuna tıkla")
    public KullaniciEvrakDevretPage devret() {
        btnDevret.click();
        return this;
    }

    @Step("Devret panelinde Tamam butonuna tıkla.")
    public KullaniciEvrakDevretPage devretTamam() {
        btnDevretTamam.click();
        return this;
    }

    @Step("Ekran Alan kontrolleri")
    public KullaniciEvrakDevretPage ekranTabKontrolleri() {
        $x("//h3[.='Gelen Evraklar']").shouldBe(visible);
        $x("//h3[.='Taslak Evraklar']").shouldBe(visible);
        $x("//h3[.='İmza Bekleyen Evraklar']").shouldBe(visible);
        $x("//h3[.='Paraf Bekleyen Evraklar']").shouldBe(visible);
        $x("//h3[.='Koordine Bekleyen Evraklar']").shouldBe(visible);
        $x("//h3[.='Kontrol Bekleyen Evraklar']").shouldBe(visible);
        $x("//h3[.='Havale Onayına Gelen Evraklar']").shouldBe(visible);
        $x("//h3[.='Teslim Aldıklarım']").shouldBe(visible);
        $x("//h3[.='Kapatma İmzasi Bekleyenler']").shouldBe(visible);
        $x("//h3[.='Kapatma Parafı Bekleyenler']").shouldBe(visible);
        btnDevret.shouldBe(visible);

        return this;
    }

    @Step("Tablo Evrak Seçimi : {konu}")
    public KullaniciEvrakDevretPage tabloEvrakSecimi(String konu) {
        tblGelenEvraklar
                .filterBy(text(konu)).first()
                .$("div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
        return this;
    }

    @Step("Devralacak popup alan kontrolleri")
    public KullaniciEvrakDevretPage devralacakKisiAlanKontolu() {
        txtDevralacakKisi.shouldBe(visible);
        txtAciklama.shouldBe(visible);
        btnDevretTamam.shouldBe(visible);
        btnDevretIptal.shouldBe(visible);
        return this;
    }

    @Step("Devredilemeyen Evraklar popup kontrolü")
    public KullaniciEvrakDevretPage popUpDevredilemeyenEvraklarKontrol() {
        popUpDevredilemeyenEvraklar.shouldBe(visible);
        return this;
    }

    @Step("Devredilemeyen Evraklar popup kontrolü")
    public KullaniciEvrakDevretPage devredelimeyenEvraklarEvrakKontrolu(String konu, String aciklama) {
        tblDevredilemeyenEvraklar
                .filterBy(text(konu))
                .filterBy(text(aciklama))
                .shouldHaveSize(1);
        return this;
    }
}

