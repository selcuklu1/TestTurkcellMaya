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

}
