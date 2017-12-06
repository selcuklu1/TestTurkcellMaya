package pages.ustMenuPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KlasorYonetimiPage extends MainPage {

    SelenideElement btnYeni = $(By.id("klasorYonetimiListingForm:klasorTreeTable:addNewKlasorButton"));
    BelgenetElement txtBirim = comboLov("[id^='klasorYonetimiEditorForm'][id$='LovText']");
    SelenideElement cmbKlasorTuru = $(By.id("klasorYonetimiEditorForm:klasorTuruListe"));
    SelenideElement txtKlasorKodu = $(By.id("klasorYonetimiEditorForm:klasorKoduInput"));
    SelenideElement txtKlasorAdi = $(By.id("klasorYonetimiEditorForm:klasorAdiInput"));
    SelenideElement dateKlasorAcilisTarihi = $(By.id("klasorYonetimiEditorForm:klasorAcilisTarihiCalendar_input"));
    SelenideElement dateKlasorKapanisTarihi = $(By.id("klasorYonetimiEditorForm:klasorKapanisTarihiCalendar_input"));
    @Step("Klasör Yönetimi sayfası aç")
    public KlasorYonetimiPage openPage(){
        ustMenu("Klasör Yönetimi");
        return this;
    }

    @Step("Klasör kapanış tarihi doldur")
    public KlasorYonetimiPage klasorKapanisTarihiDoldur(String tarih){
        dateKlasorKapanisTarihi.setValue(tarih);
        return this;
    }

    @Step("Klasör açılış tarihi doldur")
    public KlasorYonetimiPage klasorAcilisTarihDoldur(String tarih){
        dateKlasorAcilisTarihi.setValue(tarih);
        return this;
    }

    @Step("Klasör kodu doldur")
    public KlasorYonetimiPage klasorKoduDoldur(String klasorKodu){
        txtKlasorKodu.setValue(klasorKodu);
        return this;
    }

    @Step("Klaör adı doldur")
    public KlasorYonetimiPage klasorAdiDoldur(String klasorAdi){
        txtKlasorAdi.setValue(klasorAdi);
        return this;
    }

    @Step("Klasör türü seç")
    public KlasorYonetimiPage klasorTuruSec(String value){
        cmbKlasorTuru.selectOption(value);
        return this;
    }

    @Step("Yeni")
    public KlasorYonetimiPage yeni(){
        btnYeni.click();
        return this;
    }
    
    @Step("Birim alanını doldur")
    public KlasorYonetimiPage birimDoldur(String birim){
        txtBirim.selectLov(birim);
        return this;
    }

}
