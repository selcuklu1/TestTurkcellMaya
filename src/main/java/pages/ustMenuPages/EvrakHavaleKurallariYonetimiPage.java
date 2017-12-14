package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

/****************************************************
 * Tarih: 2017-12-27
 * Proje: Türksat Functional Test Automation
 * Class: "Evrak Havale Kuralları" konulu senaryoları içerir
 * Yazan: Can Şeker
 ****************************************************/
public class EvrakHavaleKurallariYonetimiPage extends MainPage{

    SelenideElement btnAra = $("[id='havaleKuralYonetimiListingForm:filterPanel:searchEntitiesButton]'");
    SelenideElement btnSil = $(By.id("havaleKuralYonetimiListingForm:havaleKuralDataTable:0:deleteHavaleKuralButton"));
    SelenideElement btnIslemOnayiEvet = $(By.id("baseConfirmationDialog:confirmButton"));
    BelgenetElement txtBirim = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:birimLov:LovText"));
    SelenideElement txtKuralAdi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:adFilterInput"));
    SelenideElement cmbDurum = $(By.id("havaleKuralYonetimiListingForm:filterPanel:durumSelectBox"));
    SelenideElement cmbGeldigiYerTipi = $(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerTipiSelect"));
    SelenideElement chkAltBirimDahil = $(By.id("havaleKuralYonetimiListingForm:filterPanel:altBirimlerDahilCheckbox_input"));
    BelgenetElement txtKullanici = comboLov(By.id("havaleKuralYonetimiListingForm:filterPanel:geldigiYerKullaniciLov:LovText"));
    @Step("Tablo değer kontrolu")
    public EvrakHavaleKurallariYonetimiPage tabloKontrol(String deger){
        $("[id='havaleKuralYonetimiListingForm:havaleKuralDataTable_data']").shouldHave(Condition.text(deger));
        return this;
    }

    @Step("Alt birim dahil seçilir")
    public EvrakHavaleKurallariYonetimiPage altBirimDahilSec(boolean secim){
        chkAltBirimDahil.setSelected(secim);
        return this;
    }

    @Step("Geldiği yer tipi seçiniz")
    public EvrakHavaleKurallariYonetimiPage geldigiYerTipiSec(String value){
        cmbGeldigiYerTipi.selectOptionByValue(value);
        return this;
    }

    @Step("Durum Seçiniz")
    public EvrakHavaleKurallariYonetimiPage durumSec(String value){
        cmbDurum.selectOptionByValue(value);
        return this;
    }

    @Step("Kural adı doldur")
    public EvrakHavaleKurallariYonetimiPage kuralAdiDoldur(String kuralAdi){
        txtKuralAdi.setValue(kuralAdi);
        return this;
    }

    @Step("Birim alanı doldurulur")
    public EvrakHavaleKurallariYonetimiPage birimDoldur(String birim){
        txtBirim.selectLov(birim);
        return this;
    }

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
        clickJs(btnAra);
        int i=0;
        while (i < 100){
            sleep(i);
        }
        return this;
    }
    
    @Step("Kullanıcı doldur")
    public EvrakHavaleKurallariYonetimiPage kullaniciDoldur(String kullanici){
        txtKullanici.selectLov(kullanici);
        return this;
    }
    
    @Step("Evrak havale kuralları yonetimi sayfası açılır")
    public EvrakHavaleKurallariYonetimiPage openPage(){
        ustMenu("Evrak Havale Kuralları Yönetimi");
        return this;
    }


}
