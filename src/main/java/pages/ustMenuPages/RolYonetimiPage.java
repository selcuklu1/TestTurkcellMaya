package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.UstMenuData;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboBox;


public class RolYonetimiPage extends MainPage {


    // Sorgulama ve Filtreleme
    SelenideElement txtAdfilterinput = $x("//*[@id='rolYonetimiListingForm:filterPanel:adFilterInput']");
    BelgenetElement cmbDurumSecimi = comboBox("select[id$='rolYonetimiListingForm:filterPanel:durumSelectBox']");
    SelenideElement btnSorgulamaFiltrelemeArama = $x("//*[@id='rolYonetimiListingForm:filterPanel:searchEntitiesButton']");

    //Rol Listesi
    SelenideElement btnYeniRolEkleme = $x("//*[@id='rolYonetimiListingForm:rolDataTable:addNewRolButton']");
    ElementsCollection tblRolListesi = $$("[@id='rolYonetimiListingForm:rolDataTable_data'] tr[data-ri]");



    //Rol ekleme sayfası
    SelenideElement txtRolAd = $x("//*[@id='rolYonetimiEditorForm:rolAdiInput']");
    SelenideElement txtRolKısaAd = $x("//*[@id='rolYonetimiEditorForm:rolKisaAdiInput']");
    SelenideElement txtRolEtiket = $x("//*[@id='rolYonetimiEditorForm:etiketInput']");
    SelenideElement txtRolDegerKod = $x("//*[@id='rolYonetimiEditorForm:degerKodInput']");
    SelenideElement txtRolAciklama = $x("//*[@id='rolYonetimiEditorForm:aciklamaInput']");
    SelenideElement txtRolYetkiOnceligi = $x("//*[@id='rolYonetimiEditorForm:siraInput']");
    SelenideElement btnRolKaydetme = $x("//*[@id='rolYonetimiEditorForm:saveRolButton']");
    SelenideElement btnRolIptal = $x("//*[@id='rolYonetimiEditorForm:cancelSaveRolButton']");


    @Step("Rol Yönetimi Sayfasını aç")
    public RolYonetimiPage openPage() {

        ustMenu(UstMenuData.KullaniciIslemleri.RolYonetimi);
        return this;
    }

    @Step("Aranacak Rol adı doldurma : \"{RolAd}\" ")
    public RolYonetimiPage txtRolAdArama(String RolAd) {
        txtAdfilterinput.setValue(RolAd);
        return this;
    }

    @Step("Aranacak durum seçimi : \"{durum}\"")
    public RolYonetimiPage cmbDurumSecim(String durum) {
        cmbDurumSecimi.selectComboBox(durum);
        return this;
    }

    @Step("Arama butonuna tıkla")
    public RolYonetimiPage btnRolArama () {
        btnSorgulamaFiltrelemeArama.click();
        return this;
    }

    @Step("Yeni Rol Ekleme butonu")
    public RolYonetimiPage btnYeniRolekle () {
        btnYeniRolEkleme.click();
        return this;
    }
    @Step("Yeni Rol ad doldurma : \"{Rolad}\"")
    public RolYonetimiPage txtYeniRolAd (String Rolad) {
        txtRolAd.setValue(Rolad);
        return this;
    }

    @Step("Yeni Rol Kısa Ad doldurma : \"{RolKısaad}\"")
    public RolYonetimiPage txtYeniRolKısaAd (String RolKısaad) {
        txtRolKısaAd.setValue(RolKısaad);
        return this;
    }

    @Step("Yeni Rol Etiket doldurma : \"{etiket}\"")
    public RolYonetimiPage txtYeniRolEtiket (String etiket) {
        txtRolEtiket.setValue(etiket);
        return this;
    }

    @Step("Yeni Rol Deger kod doldurma :\"{degerkod}\"")
    public RolYonetimiPage txtRolDegerKod (String degerkod) {
        txtRolDegerKod.setValue(degerkod);
        return this;
    }

    @Step("Yeni Rol Açıklama doldurma : \"{aciklama}\"")
    public RolYonetimiPage txtRolAciklama (String aciklama) {
        txtRolAciklama.setValue(aciklama);
        return this;
    }

    @Step("Yeni Rol Yetki Onceligi doldurma : \"{oncelik}\"")
    public RolYonetimiPage txtRolYetkiOnceligi (int oncelik) {
        txtRolYetkiOnceligi.setValue(String.valueOf(oncelik));
        return this;
    }

    @Step("Yeni Rol Kaydetme ")
    public RolYonetimiPage btnYeniRolKaydetme () {
        btnRolKaydetme.click();
        return this;
    }

    @Step("Yeni Rol Kaydetme iptal")
    public RolYonetimiPage btnYeniRolIptal() {
        btnRolIptal.click();
        return this;
    }
}