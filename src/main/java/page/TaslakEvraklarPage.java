package page;

import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TaslakEvraklarPage extends BaseLibrary {

    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement btnTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));
    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));

    SelenideElement btnImzala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));

    //Sil Button alt div
    SelenideElement btnSil = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:2:cmdbutton"));
    SelenideElement txtSilAciklama = $(By.id("mainPreviewForm:j_idt14773"));
    SelenideElement btnSilSil = $(By.id("mainPreviewForm:j_idt14775"));

    SelenideElement btnEvrakKopyala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:3:cmdbutton"));

    //Paylaş Button alt div
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement txtPaylasKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasanAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));

    SelenideElement btnEvrakNotlariTabYeni = $(By.id("mainPreviewForm:j_idt11557:kisiselNotEkleDataTableId:kisiselNotEkleId"));
    SelenideElement txtEvrakNotlariModalAcikalama = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotAciklamaid"));
    SelenideElement cmbEvrakNotlariModalNotTipi = $(By.id("evrakKisiselNotDialogFormId:evrakNotTipi_input"));
    SelenideElement btnEvrakNotlariModalKaydet = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotKaydet"));
    SelenideElement btnEvrakNotlariModalIptal = $(By.id("evrakKisiselNotDialogFormId:evrakKisiselNotIptal"));

    public TaslakEvraklarPage evrakNotlariModalIptalGonder() {
        btnEvrakNotlariModalIptal.click();
        return this;
    }

    public TaslakEvraklarPage evrakNotlariModalKaydetGonder() {
        btnEvrakNotlariModalKaydet.click();
        return this;
    }

    public TaslakEvraklarPage evrakNotlariModalNotTipiSec(String value) {
        cmbEvrakNotlariModalNotTipi.selectOption(value);
        return this;
    }

    public TaslakEvraklarPage evrakNotlariModalAcikalamDoldur(String text) {
        txtEvrakNotlariModalAcikalama.setValue(text);
        return this;
    }

    public TaslakEvraklarPage evrakNotlariTabYeniButtonClick() {
        btnEvrakNotlariTabYeni.click();
        return this;
    }

    public TaslakEvraklarPage imzalaGonder() {
        btnImzala.click();
        return this;
    }

    public TaslakEvraklarPage evrakGosterGonder() {
        btnEvrakGoster.click();
        return this;
    }

    public TaslakEvraklarPage raporAlGonder() {
        btnRaporAl.click();
        return this;
    }

    public TaslakEvraklarPage topluSecimGonder() {
        btnTopluSecim.click();
        return this;
    }

    public TaslakEvraklarPage tarihDoldur(String text) {
        dateTarih.setValue(text);
        return this;
    }

    public TaslakEvraklarPage sayfadaAraDoldur(String text) {
        txtSayfadaAra.setValue(text);
        return this;
    }

    public TaslakEvraklarPage silSilGonder() {
        btnSilSil.click();
        return this;
    }

    public TaslakEvraklarPage silAciklamaInputDolduur(String text) {
        txtSilAciklama.setValue(text);
        return this;
    }

    public TaslakEvraklarPage silButtonGonder() {
        btnSil.click();
        return this;
    }

    public TaslakEvraklarPage evrakKopyalaGonder() {
        btnEvrakKopyala.click();
        return this;
    }

    public TaslakEvraklarPage paylasPaylasGonder() {
        btnPaylasPaylas.click();
        return this;
    }

    public TaslakEvraklarPage paylasanAciklamaDoldur(String text) {
        txtPaylasanAciklama.setValue(text);
        return this;
    }

    public TaslakEvraklarPage paylasKisiDoldur(String text) {
        txtPaylasKisi.setValue(text);
        return this;
    }

    public TaslakEvraklarPage paylasGonder() {
        btnPaylas.click();
        return this;
    }
}
