package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import java.beans.IntrospectionException;

import static com.codeborne.selenide.Selenide.$;

public class GercekKisiYonetimPage extends BaseLibrary {

    SelenideElement txtTcKimlikNo = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiTcKimlikNoFilterInput"));
    SelenideElement txtAd = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiAdFilterInput"));
    SelenideElement txtSoyad = $(By.id("gercekKisiYonetimiListingForm:filterPanel:gercekKisiSoyadFilterInput"));
    SelenideElement cmbDurum = $(By.id("gercekKisiYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement btnAra = $(By.id("gercekKisiYonetimiListingForm:filterPanel:searchGercekKisiButton"));
    SelenideElement txtDuzenleTcKimlikNo = $(By.id("gercekKisiYonetimiEditorForm:tcKimlikNoInput"));
    SelenideElement txtOnEk = $(By.id("gercekKisiYonetimiEditorForm:onEkInput"));
    SelenideElement txtUnvan = $(By.id("gercekKisiYonetimiEditorForm:unvanId"));
    SelenideElement txtDuzenleAd = $(By.id("gercekKisiYonetimiEditorForm:adInput"));
    SelenideElement txtDuzenleSoyad = $(By.id("gercekKisiYonetimiEditorForm:soyadInput"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("gercekKisiYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement btnDuzenleKepAdresBilgiler = $(By.id("gercekKisiYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));
    SelenideElement btnKaydet = $(By.id("gercekKisiYonetimiEditorForm:saveGercekKisiButton"));

    public GercekKisiYonetimPage kaydetGonder() throws InterruptedException{
        btnKaydet.click();
        return this;
    }

    public GercekKisiYonetimPage duzenleKepAdresBilgilerGonder() throws InterruptedException{
        btnDuzenleKepAdresBilgiler.click();
        return this;
    }

    public GercekKisiYonetimPage kepAdresiKullaniyor(boolean secim) throws InterruptedException{
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public GercekKisiYonetimPage duzenleSoyadDoldur(String text) throws InterruptedException{
        txtDuzenleSoyad.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage duzenleAdDoldur(String text) throws InterruptedException{
        txtDuzenleAd.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage unvanDoldur(String text) throws InterruptedException{
        txtUnvan.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage onEkDoldur(String text) throws InterruptedException{
        txtOnEk.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage duzenleTcKimlikNoDoldur(String text) throws InterruptedException{
        txtDuzenleTcKimlikNo.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage araGonder() throws InterruptedException{
        btnAra.click();
        return this;
    }

    public GercekKisiYonetimPage durumSec(String value) throws InterruptedException{
       cmbDurum.selectOption(value);
        return this;
    }

    public GercekKisiYonetimPage soyadDoldur(String text) throws InterruptedException{
        txtSoyad.setValue(text);
        return  this;
    }

    public GercekKisiYonetimPage adDoldur(String text) throws IntrospectionException{
        txtAd.setValue(text);
        return this;
    }

    public GercekKisiYonetimPage tcKimlikNoDoldur(String text) throws IntrospectionException{
        txtTcKimlikNo.setValue(text);
        return this;
    }

}
