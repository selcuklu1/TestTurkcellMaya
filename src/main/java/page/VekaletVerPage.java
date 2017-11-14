package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class VekaletVerPage extends BaseLibrary{

    SelenideElement btnVekaletVeren =  $(By.id("vekaletVerForm:vekaletLayout:vekaletVerenLov:j_idt134"));
    SelenideElement txtVekaletAlan = $(By.id("vekaletVerForm:vekaletLayout:vekaletAlanLov:LovText"));
    SelenideElement chkTumu=$(By.id("vekaletVerForm:vekaletLayout:j_idt5302_input"));
    SelenideElement txtBaslangicTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBasTarihi_input"));
    SelenideElement txtBitisTarihi = $(By.id("vekaletVerForm:vekaletLayout:vekaletBitTarihi_input"));
    SelenideElement chkEvraktaVelaketeSonEkiGorunsun = $(By.id("vekaletVerForm:vekaletLayout:j_idt5317_input"));
    SelenideElement chkOzelUnvanKullan = $(By.id("vekaletVerForm:vekaletLayout:j_idt5320_input"));
    SelenideElement txtAciklama = $(By.id("vekaletVerForm:vekaletLayout:aciklamaTextArea"));
    SelenideElement btnUygula = $(By.id("vekaletVerForm:vekaletLayout:onayaSunButton"));

    public VekaletVerPage uygula() throws InterruptedException{
        btnUygula.click();
        return this;
    }

    public VekaletVerPage aciklamaDoldur(String text) throws InterruptedException{
        txtAciklama.setValue(text);
        return this;
    }

    public VekaletVerPage ozelUnvanKullanSec(boolean secim) throws InterruptedException{
        chkOzelUnvanKullan.setSelected(secim);
        return this;
    }

    public VekaletVerPage evraktaVelaketeSonEkiGorunsunSec(boolean secim) throws InterruptedException{
        chkEvraktaVelaketeSonEkiGorunsun.setSelected(secim);
        return this;
    }

    public VekaletVerPage bitisTarihiDoldur(String text) throws InterruptedException{
        txtBitisTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage baslangicTarihDoldur(String text) throws InterruptedException{
        txtBaslangicTarihi.setValue(text);
        return this;
    }

    public VekaletVerPage tumuSec(boolean secim) throws InterruptedException{
        chkTumu.setSelected(secim);
        return this;
    }

    public VekaletVerPage vekaletAlanDoldur(String text) throws InterruptedException{
        txtVekaletAlan.setValue(text);
        return this;
    }

    public VekaletVerPage vekaletVeren() throws InterruptedException{
        btnVekaletVeren.click();
        return this;
    }


}
