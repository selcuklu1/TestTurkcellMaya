package pages.pageComponents;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgentCondition.isChecked;

/**
 * Yazan: Ilyas Bayraktar
 * Tarih: 13.02.2018
 * Açıklama:
 */
public class TuzelKisiEkleDialog extends MainPage {
    SelenideElement dialog = $("#dagPlaniTuzelKisiEkleDialog");
    SelenideElement tuzelKisiInput = $(By.id("dagPlanTuzelKisiEkleForm:tuzelKisiAd"));
    SelenideElement vergiKimlikNoInput = $(By.id("dagPlanTuzelKisiEkleForm:vergiKimlikNo"));
    SelenideElement tuzelKisiTipiSelect = $(By.id("dagPlanTuzelKisiEkleForm:tuzelKisiTipi"));
    SelenideElement araButton = $(By.id("dagPlanTuzelKisiEkleForm:dagitimPlaniAra_id"));
    SelenideElement ekleButton = $(By.id("dagPlanTuzelKisiEkleForm:dagPlaniTuzelKEkleButton"));
    //SelenideElement ekleButton = $x("//div[@id='dagPlaniTuzelKisiEkleDialog']//button[.='Ekle']");

    SelenideElement dagitimPlaninaEkleRadio = dialog.$x("descendant::input[@type='radio'][1]");
    SelenideElement kriterleriKaydetRadio = dialog.$x("descendant::input[@type='radio'][2]");

    SelenideElement karasalTvSelect = $(By.id("dagPlanTuzelKisiEkleForm:karasalTvId"));
    SelenideElement karasalTvYayındaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:karasalTvYayindaId"));
    SelenideElement karasalRadyoSelect = $(By.id("dagPlanTuzelKisiEkleForm:karasalRadyoId"));
    SelenideElement karasalRadyoYayındaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:karasalRadyoYayindaId"));
    SelenideElement uyduTvCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:uyduTvId"));
    SelenideElement uyduTvYayindaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:uyduTvYayindaId"));
    SelenideElement uyduRadyoCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:uyduRadyoId"));
    SelenideElement uyduRadyoYayindaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:uyduRadyoYayindaId"));
    SelenideElement kabloTvSelect = $(By.id("dagPlanTuzelKisiEkleForm:kabloTvId"));
    SelenideElement kabloTvYayindaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:kabloTvYayindaId"));
    SelenideElement kabloRadyoSelect = $(By.id("dagPlanTuzelKisiEkleForm:kabloRadyoId"));
    SelenideElement kabloRadyoYayindaCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:kabloRadyoYayindaId"));
    SelenideElement istegeBagliRadyoCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:istegeBagliRadyoId"));
    SelenideElement istegeBagliTvCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:istegeBagliTvId"));
    SelenideElement platformIsletmecisiCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:platformIsletmecisiId"));
    SelenideElement altyapiIsletmecisiCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:altyapiIsletmecisiId"));
    SelenideElement lisansIptalCheckbox = $(By.id("dagPlanTuzelKisiEkleForm:lisansIptalId"));

    SearchTable searchTable = new SearchTable($(By.id("dagPlanTuzelKisiEkleForm:tuzelKisiDataTable")));
    By listelenKayitCheckbox = By.cssSelector(".ui-chkbox-box");


    public TuzelKisiEkleDialog() {
        dialog.shouldBe(visible);
    }

    @Step("Tüzel Kişi Tipi {tipi} seçilir")
    public TuzelKisiEkleDialog tuzelKisiTipiSecilir(String tipi){
        tuzelKisiTipiSelect.selectOption(tipi);
        return this;
    }

    @Step("Alanları kontrolü")
    public TuzelKisiEkleDialog alanlariKonrolu(){
        Allure.addAttachment("Alanları",
                "Dağıtım Planına Ekle\n" +
                "Kriterleri Kaydet seçenekleri görüntülenir.\n" +
                "\n" +
                "Karasal TV, Karasal TV Yayında, Karasal Radyo, Karasal Radyo Yayında\n" +
                "Uydu TV, Uydu TV Yayında, Uydu Radyo, Uydu Radyo Yayında\n" +
                "Kablo TV, Kablo TV Yayında, Kablo Radyo, Kablo Radyo Yayında\n" +
                "İsteğe Bağlı Radyo, İsteğe Bağlı TV, Platform İşletmecisi, AltYapı İşletmecisi\n" +
                "Lisans İptal");
        dagitimPlaninaEkleRadio.shouldBe(visible);
        kriterleriKaydetRadio.shouldBe(visible);
        karasalTvSelect.shouldBe(visible);
        karasalTvYayındaCheckbox.shouldBe(visible);
        karasalRadyoSelect.shouldBe(visible);
        karasalRadyoYayındaCheckbox.shouldBe(visible);
        uyduTvCheckbox.shouldBe(visible);
        uyduTvYayindaCheckbox.shouldBe(visible);
        uyduRadyoCheckbox.shouldBe(visible);
        uyduRadyoYayindaCheckbox.shouldBe(visible);
        kabloTvSelect.shouldBe(visible);
        kabloTvYayindaCheckbox.shouldBe(visible);
        kabloRadyoSelect.shouldBe(visible);
        kabloRadyoYayindaCheckbox.shouldBe(visible);
        istegeBagliRadyoCheckbox.shouldBe(visible);
        istegeBagliTvCheckbox.shouldBe(visible);
        platformIsletmecisiCheckbox.shouldBe(visible);
        altyapiIsletmecisiCheckbox.shouldBe(visible);
        lisansIptalCheckbox.shouldBe(visible);
        return this;
    }

    @Step("Tüzel Kişi doldurulur")
    public TuzelKisiEkleDialog tuzelKisiDoldurulur(String tuzelKisi){
        tuzelKisiInput.setValue(tuzelKisi);
        return this;
    }

    @Step("Uydu Tv {value} seçilir")
    public TuzelKisiEkleDialog uyduTvSecilir(boolean value){
        checkboxSelect(uyduTvCheckbox, value);
        return this;
    }

    @Step("Ara")
    public TuzelKisiEkleDialog ara(){
        araButton.click();
        return this;
    }
    
    @Step("Arama listesinde aranır")
    public TuzelKisiEkleDialog aramaListesindeAranir(Condition... aramaKriterleri){
        searchTable.findRows(aramaKriterleri);
        return this;
    }

    @Step("Listelen kayıdın checkbox işaretlenir")
    public TuzelKisiEkleDialog listelenKayidinCheckboxIsaretlenir(Condition aramaKriterleri, boolean checkboxValue){
        SelenideElement checkbox = searchTable.findRows(aramaKriterleri).getFoundRow().$(listelenKayitCheckbox);
        if (checkbox.is(isChecked) != checkboxValue) {
            //checkbox.click(1,1);
            clickJs(checkbox);
        }
        return this;
    }

    @Step("Ekle")
    public TuzelKisiEkleDialog ekle(){
        clickJs(ekleButton);
        dialog.should(disappear);
        return this;
    }
}
