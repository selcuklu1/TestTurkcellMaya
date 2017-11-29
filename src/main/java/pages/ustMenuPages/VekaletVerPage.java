package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class VekaletVerPage extends BaseLibrary {

    BelgenetElement txtVekaletVerenCombolov = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText"));
    SelenideElement btnVekaletVerenCombolov = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    SelenideElement txtVekaletAlanCombolov = $(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu = $(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));
    SelenideElement btnVekalelVerenTemizle = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    By txtVekaletVeren = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText']");
    By txtVekaletAlan = By.cssSelector("[id^='vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText']");
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletOnaylayacakKisiLov:LovText"));

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


}
