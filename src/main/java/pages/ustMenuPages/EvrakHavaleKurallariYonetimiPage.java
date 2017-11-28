package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;

import static com.codeborne.selenide.Selenide.$;

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
public class EvrakHavaleKurallariYonetimiPage extends MainPage{

    SelenideElement btnAra = $(By.id("havaleKuralYonetimiListingForm:filterPanel:searchEntitiesButton"));
    SelenideElement btnSil = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:0:deleteHavaleKuralButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));


    @Step("Sil")
    public EvrakHavaleKurallariYonetimiPage sil(){
        btnSil.click();
        return this;
    }

    @Step("İslem onayı evet")
    public EvrakHavaleKurallariYonetimiPage islemOnayiEvet(){
        btnIslemOnayiEvet.click();
        return this;
    }

    @Step("Ara")
    public EvrakHavaleKurallariYonetimiPage ara(){
        btnAra.click();
        return this;
    }
    
    @Step("Evrak havale kuralları yonetimi sayfası açılır")
    public EvrakHavaleKurallariYonetimiPage openPage(){
        ustMenu("Evrak Havale Kuralları Yönetimi");
        return this;
    }


}
