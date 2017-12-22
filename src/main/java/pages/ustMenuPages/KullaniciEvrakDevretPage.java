package pages.ustMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class KullaniciEvrakDevretPage extends MainPage {

    BelgenetElement txtDevredecekKisi = comboLov(By.id("kullaniciEvrakDevretForm:kisidenLov_id:LovText"));
    SelenideElement btnListele = $(By.id("kullaniciEvrakDevretForm:listeleButton"));
    SelenideElement btnDevret = $(By.id("kullaniciEvrakDevretForm:devretButton"));
    ElementsCollection tableTaslakEvraklar = $$("tbody[id='kullaniciEvrakDevretForm:evrakDevretAccordionPanelId:devirTaslakEvraklar_data'] > tr[role='row']");
    BelgenetElement txtDevralacakKisi = comboLov(By.id("devredilenKullaniciDialogForm:kisiyeLov_id:LovText"));
    SelenideElement txtAciklama = $(By.id("devredilenKullaniciDialogForm:evrakDevretAciklamaId"));
    SelenideElement btnDevretTamam = $(By.id("devredilenKullaniciDialogForm:evrakDevretTamamButtonId"));

    @Step("Kullanıcı Evrak Devret sayfasını aç")
    public KullaniciEvrakDevretPage openPage() {
        ustMenu("Kullanıcı Evrak Devret");
        return this;
    }
    
    @Step("Devredecek Kişi seç: {devredecekKisi}")
    public KullaniciEvrakDevretPage devredecekKisiSec(String devredecekKisi){
        txtDevredecekKisi.selectLov(devredecekKisi);
        return this;
    }

    @Step("Panel aç: {panelAdi}")
    public KullaniciEvrakDevretPage panelAc(String panelAdi){
        $x("//h3[.='"+panelAdi+"']").sendKeys(Keys.SHIFT);
        $x("//h3[.='"+panelAdi+"']").click();
        return this;
    }

    @Step("Listele butonuna tıkla.")
    public KullaniciEvrakDevretPage listele(){
        btnListele.click();
        $("div[id='bekleyinizStatusDialog']").waitUntil(Condition.not(Condition.visible), 50000);
        return this;
    }

    @Step("Taslak evrak seç")
    public KullaniciEvrakDevretPage taslakEvrakSec(String konu, String gidecegiYer){

        Boolean isSelected = false;

        SelenideElement currentRow = tableTaslakEvraklar
                .filterBy(Condition.text("Konu: " + konu))
                .filterBy(Condition.text("Gideceği Yer: " + gidecegiYer))
                .first();

        SelenideElement currentRowCheckBox = currentRow.$(By.xpath(".//div[contains(@class, 'ui-chkbox ui-widget')]"));

        if (currentRowCheckBox.$(By.xpath(".//div[contains(@class, 'ui-state-active')]")).exists())
            isSelected = true;

        if (isSelected == false)
            currentRowCheckBox.click();

        return this;
    }

    @Step("Devralacak Kişi seç: {devralacakKisi}")
    public KullaniciEvrakDevretPage devralacakKisiSec(String devralacakKisi){
        txtDevralacakKisi.selectLov(devralacakKisi);
        return this;
    }

    @Step("Açıklama doldur: {aciklama}")
    public KullaniciEvrakDevretPage aciklamaDoldur(String aciklama){
        txtAciklama.setValue(aciklama);
        return this;
    }

    @Step("Devret butonuna tıkla")
    public KullaniciEvrakDevretPage devret(){
        btnDevret.click();
        return this;
    }

    @Step("Devret panelinde Tamam butonuna tıkla.")
    public KullaniciEvrakDevretPage devretTamam(){
        btnDevretTamam.click();
        return this;
    }

}

