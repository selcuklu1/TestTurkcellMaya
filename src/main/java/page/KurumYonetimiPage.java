package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class KurumYonetimiPage extends BaseLibrary {

    SelenideElement txtKurum = $(By.id("kurumYonetimiListingForm:filterPanel:kurumFilterLov:LovText"));
    SelenideElement btnAra = $(By.id("kurumYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement cmbDurum = $(By.id("kurumYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement txtIdariBirimKimlikKodu = $(By.id("kurumYonetimiEditorForm:kurumEKodInput"));
    SelenideElement chkKaysisteYerAlmiyor = $(By.id("kurumYonetimiEditorForm:kaysisteVarMiCheckbox_input"));
    SelenideElement txtKurumAdi = $(By.id("kurumYonetimiEditorForm:kurumAdiInput"));
    SelenideElement chkPaketKullanim = $(By.id("kurumYonetimiEditorForm:paketKullanimCheckbox_input"));
    SelenideElement chkKepAdresiKullaniyor = $(By.id("kurumYonetimiEditorForm:kepAdresiKullanimCheckbox_input"));
    SelenideElement chkOzelHitap = $(By.id("kurumYonetimiEditorForm:ozelHitapExistSelBoolean_input"));
    SelenideElement btnKaydet = $(By.id("kurumYonetimiEditorForm:saveKurumButton"));
    SelenideElement btnKepAdresBilgileriArti = $(By.id("kurumYonetimiEditorForm:kepBilgileriDataTable:addNewKepAdresiButton"));

    public KurumYonetimiPage kepAdresBilgileriArtiGonder() {
        btnKepAdresBilgileriArti.click();
        return this;
    }

    public KurumYonetimiPage kaydetGonder() {
        btnKaydet.click();
        return this;
    }

    public KurumYonetimiPage ozelHitapSec(boolean secim) {
        chkOzelHitap.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage kepAdresiKullaniyorSec(boolean secim) {
        chkKepAdresiKullaniyor.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage paketKullanimSec(boolean secim) {
        chkPaketKullanim.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage kurumAdiDoldur(String text) {
        txtKurumAdi.setValue(text);
        return this;
    }

    public KurumYonetimiPage kaysisteYerAlmiyorSec(boolean secim) {
        chkKaysisteYerAlmiyor.setSelected(secim);
        return this;
    }

    public KurumYonetimiPage idariBirimKimlikKoduDoldur(String text) {
        txtIdariBirimKimlikKodu.setValue(text);
        return this;
    }

    public KurumYonetimiPage durumSec(String value) {
        cmbDurum.selectOption(value);
        return this;
    }

    public KurumYonetimiPage araDoldur(String text) {
        btnAra.click();
        return this;
    }

    public KurumYonetimiPage kurumDoldur(String text) {
        txtKurum.setValue(text);
        return this;
    }
}

