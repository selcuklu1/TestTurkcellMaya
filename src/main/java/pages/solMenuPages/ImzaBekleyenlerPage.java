package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class ImzaBekleyenlerPage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");// span[class='ui-chkbox-icon']");
    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement btnDosyaEkle = $(By.xpath("//input[@id='mainPreviewForm:fileUploadIadeEk_input']"));
    SelenideElement txtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    SelenideElement btnImzala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    SelenideElement rdbSImaza = $("[id='imzalaForm:imzalaRadio'] [class='ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default']");
    SelenideElement btnSImzaImzala = $(By.id("imzalaForm:sayisalImzaConfirmDialogOpener"));
    SelenideElement btnSImzaImzalaEvet = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
    SelenideElement btnSImzaImzalaHayir = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaHayirButton"));
    SelenideElement btnIcerik = $("[id^='mainInboxForm:inboxDataTable'][id$='detayGosterButton']");
    SelenideElement pdfIcerikKontrol = $(By.xpath("/html//div[@id='inboxItemInfoForm:imzacilarPanel']/center/table/tbody/tr/td[3]/center/table/tbody/tr[6]/td/button[@role='button']/span[@class='ui-button-text']"));
    ElementsCollection solMenuBirim = $$("[id='birimlerimMenusuContainer'] li");

    @Step("İmza bekleyenler sayfası aç")
    public ImzaBekleyenlerPage openPage(){
        solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage imzala(){
        btnImzala.click();
        return this;
    }

    @Step("S-İmza seç")
    public ImzaBekleyenlerPage sImzaSec(){
        rdbSImaza.click();
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage sImzaİmzala(boolean secim){
        btnSImzaImzala.click();
        if (secim==true){
            btnSImzaImzalaEvet.pressEnter();
        }
        else {
            btnSImzaImzalaHayir.pressEnter();
        }
        return this;
    }

    public ImzaBekleyenlerPage dogruKonuVeNoKontrol(String toplantiNo, String konu){

        tableKararIzlemeEvraklar
                .filterBy(Condition.text(toplantiNo)).filterBy(Condition.text(konu)).get(0).shouldBe(visible);
        return this;

    }

    public ImzaBekleyenlerPage evrakSec(String toplantiNo, String konu){
        int i=0;
        while (i<100)
        {
            sleep(i);
            i++;
        }
        tableKararIzlemeEvraklar.
                filterBy(Condition.text(toplantiNo))
                .filterBy(Condition.text(konu))
                .first().click();
        return this;
    }

    @Step("Evrak olmadığı görünür")
    public ImzaBekleyenlerPage evrakOlmadigiGorme(String toplantiNo, String konu,boolean vardir){
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo)).get(0).shouldBe(not(Condition.exist));;
        return this;
    }
    @Step("Evrak olmadığı görünür")
    public ImzaBekleyenlerPage evrakOlmadigiGorme(String evrakNo){
        tableKararIzlemeEvraklar.filterBy(Condition.text(evrakNo)).shouldHaveSize(0);
        return this;
    }

    @Step("Evrak  kontrolü")
    public ImzaBekleyenlerPage evrakNoKontrolu(String evrakNo){
        tableKararIzlemeEvraklar.filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
        return this;
    }
    @Step("İade et")
    public ImzaBekleyenlerPage iadeEt(){
        btnIadeEt.click();
        return this;
    }
    @Step("İçerik")
    public ImzaBekleyenlerPage icerik(){
        btnIcerik.click();
        return this;
    }
    @Step("Dosya ekle")
    public ImzaBekleyenlerPage iadeEtDosyaEkle(String pathToFile) {
        uploadFile(btnDosyaEkle, pathToFile);
        return this;
    }
    
    @Step("Not doldur")
    public ImzaBekleyenlerPage notDoldur(String not){
        txtNot.setValue(not);
        return this;
    }
    
    @Step("İade et")
    public ImzaBekleyenlerPage iadeEtIadeEt(){
        btnIadeEtIadeEt.click();
        return this;
    }
    @Step("icerik Kontrol")
    public ImzaBekleyenlerPage icerikKontrol(String deger){
        String text = pdfIcerikKontrol.getText();
        text.contains(deger);
        return this;
    }
    @Step("İade et")
    public ImzaBekleyenlerPage birimSec(String birim){
        solMenuBirim.filterBy(text(birim)).first().click();
        return this;
    }
    @Step("Evrak no'ya göre İçerik tıklama")
    public ImzaBekleyenlerPage evrakKonusunaGoreIcerikTiklama(String konu){
        tableKararIzlemeEvraklar.filterBy(Condition.text(konu)).first()
                .$("[id^='mainInboxForm:inboxDataTable'][id$='detayGosterButton']").click();
        return this;
    }

}
