package pages.pageComponents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.$;

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
    SearchTable searchTable = new SearchTable($(By.id("dagPlanTuzelKisiEkleForm:tuzelKisiDataTable")));

    WebElement dagitimPlaninaEkleRadio = dialog.$x("descendant::input[@type='radio'][1]");
    WebElement kriterleriKaydetRadio = dialog.$x("descendant::input[@type='radio'][2]");

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


}
