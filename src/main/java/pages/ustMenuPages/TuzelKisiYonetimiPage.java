package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class TuzelKisiYonetimiPage extends MainPage {

    SelenideElement btnAra = $(By.id("tuzelKisiYonetimiListingForm:filterPanel:searchTuzelKisiButton"));
    SelenideElement btnDuzenle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));
    SelenideElement txtVergiNo = $(By.id("tuzelKisiYonetimiEditorForm:vergiNoInput"));
    SelenideElement btnTuzelKisiEkle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:addNewTuzelKisiButton"));

    SelenideElement btnKepAdresBilgileriEkle = $(By.id("tuzelKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement txtPopupKepAdresi = $(By.id("tuzelKisiKepAdresEditorForm:tuzelKisiKepAdresInputTextId"));
    SelenideElement cmbPopupKepHizmetSaglayicisi = $(By.id("tuzelKisiKepAdresEditorForm:kephs"));
    SelenideElement btnPopupKaydet = $(By.id("tuzelKisiKepAdresEditorForm:saveKepAdresiButton"));
    SelenideElement btnGuncelle = $(By.id("tuzelKisiYonetimiListingForm:tuzelKisiDataTable:0:updateTuzelKisiButton"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("tuzelKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));


    public TuzelKisiYonetimiPage openPage() {
        ustMenu("Tüzel Kişi Yönetimi");
        return this;
    }

    public String vergiNoCek(){
        String vergiNo = txtVergiNo.getValue();
        return vergiNo;
    }

    public TuzelKisiYonetimiPage guncelle(){
        btnGuncelle.click();
        return this;
    }

    public TuzelKisiYonetimiPage tuzeKisiEkle(){
        btnTuzelKisiEkle.click();
        return this;
    }

    public TuzelKisiYonetimiPage popupKaydet() {
        btnPopupKaydet.click();
        return this;
    }

    public TuzelKisiYonetimiPage kepHizmetSaglayicisiSec(String value) {
        cmbPopupKepHizmetSaglayicisi.selectOption(value);
        return this;
    }

    public TuzelKisiYonetimiPage popupKepAdresiDoldur(String text) {
        txtPopupKepAdresi.setValue(text);
        return this;
    }

    public TuzelKisiYonetimiPage kepAdresBilgileriEkle() {
        btnKepAdresBilgileriEkle.click();
        return this;
    }

    public TuzelKisiYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public TuzelKisiYonetimiPage duzenleGonder() {
        btnDuzenle.click();
        return this;
    }

    public TuzelKisiYonetimiPage ara() {
        btnAra.click();
        return this;
    }

}
