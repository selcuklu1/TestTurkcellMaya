package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TuzelKisiYonetimiPage extends BaseLibrary {

    SelenideElement btnAra = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:searchTuzelKisiButton"));
    SelenideElement btnDuzenle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("tuzelKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement btnKepAdresBilgileriEkle = $(By.id("tuzelKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement txtPopupKepAdresi = $(By.id("tuzelKisiKepAdresEditorForm:tuzelKisiKepAdresInputTextId"));
    SelenideElement cmbKepHizmetSaglayicisi = $(By.id("tuzelKisiKepAdresEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("tuzelKisiKepAdresEditorForm:saveKepAdresiButton"));

    public TuzelKisiYonetimiPage popupKaydet() throws InterruptedException{
        btnPopupKaydet.click();
        return this;
    }

    public TuzelKisiYonetimiPage kepHizmetSaglayicisiSec(String value) throws  InterruptedException{
        cmbKepHizmetSaglayicisi.selectOption(value);
        return this;
    }

    public TuzelKisiYonetimiPage popupKepAdresiDoldur(String text) throws InterruptedException{
        txtPopupKepAdresi.setValue(text);
        return this;
    }

    public TuzelKisiYonetimiPage kepAdresBilgileriEkle() throws InterruptedException{
        btnKepAdresBilgileriEkle.click();
        return  this;
    }

    public TuzelKisiYonetimiPage kepAdresiKullaniyorSec(boolean secim) throws InterruptedException{
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public TuzelKisiYonetimiPage duzenleGonder() throws  InterruptedException {
        btnDuzenle.click();
        return this;
    }

    public TuzelKisiYonetimiPage ara() throws  InterruptedException{
        btnAra.click();
        return  this;
    }

}
