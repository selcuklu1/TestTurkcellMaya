package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class VekaletVerPage extends BaseLibrary {

    BelgenetElement txtVekaletVeren = comboLov(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:LovText"));
    SelenideElement btnVekaletVeren = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    SelenideElement txtVekaletAlan = $(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu = $(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));
    SelenideElement btnVekalelVerenTemizle = $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));

    public VekaletVerPage openPage()
    {
        new UstMenu().ustMenu("Vekalet Ver");
        return this;
    }

    public  VekaletVerPage vekaletVerenFarkliDoldur(String text){
        btnVekalelVerenTemizle.click();
        txtVekaletVeren.selectComboLov(text);
        return this;
    }

    public VekaletVerPage vekaletVerenDoldur(String text) {
        txtVekaletVeren.selectComboLov(text);
        return this;
    }

    public VekaletVerPage uygula() {
        btnUygula.click();
        return this;
    }

    public VekaletVerPage aciklamaDoldur(String text) {
        txtAciklama.setValue(text);
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

    public VekaletVerPage vekaletAlanDoldur(String text) {
        txtVekaletAlan.setValue(text);
        return this;
    }

    public VekaletVerPage vekaletVeren() {
        btnVekaletVeren.click();
        return this;
    }


}
