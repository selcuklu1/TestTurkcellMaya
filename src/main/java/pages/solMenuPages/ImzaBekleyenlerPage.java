package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ImzaBekleyenlerPage extends MainPage {
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");// span[class='ui-chkbox-icon']");
    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement btnDosyaEkle = $(By.xpath("//input[@id='mainPreviewForm:fileUploadIadeEk_input']"));
    SelenideElement txtNot = $(By.id("mainPreviewForm:notTextArea_id"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));
    SelenideElement btnImzala = $(By.xpath("//span[contains(@class, 'imzala')]/.."));
    SelenideElement btnEvrakOnizlemeImzala = $("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td:nth-child(5) button");
    SelenideElement rdbSImaza = $("[id='imzalaForm:imzalaRadio'] [class='ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default']");
    SelenideElement btnSImzaImzala = $(By.id("imzalaForm:sayisalImzaConfirmDialogOpener"));
    SelenideElement btnSImzaImzalaEvet = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton"));
    SelenideElement btnSImzaImzalaHayir = $(By.id("imzalaForm:sayisalImzaConfirmForm:sayisalImzaHayirButton"));
    SelenideElement btnIcerik = $("[id^='mainInboxForm:inboxDataTable'][id$='detayGosterButton']");
    SelenideElement pdfIcerikKontrol = $(By.xpath("/html//div[@id='inboxItemInfoForm:imzacilarPanel']/center/table/tbody/tr/td[3]/center/table/tbody/tr[6]/td/button[@role='button']/span[@class='ui-button-text']"));
    ElementsCollection solMenuBirim = $$("[id='birimlerimMenusuContainer'] li");
    ElementsCollection tblImzaBekleyenler = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnEvrakSil = $("[id^='mainPreviewForm:onizlemeRightTab:uiRepeat'] [class$='evrakSil']");
    SelenideElement btnSil = $("[id^='mainPreviewForm:j_idt'] [class$='ui-button-text']");


    SelenideElement txtEvrakSilmeNotu = $(By.xpath("/html//table[@id='mainPreviewForm:evrakSilPanelGrid']/tbody//table[@class='gridForm']//textarea[@role='textbox']"));

    @Step("İmza bekleyenler sayfası aç")
    public ImzaBekleyenlerPage openPage() {
        solMenu(SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler);
        String pageTitle = SolMenuData.IslemBekleyenEvraklar.ImzaBekleyenler.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage imzala() {
        btnImzala.click();
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage evrakOnizlemeImzala() {
        btnEvrakOnizlemeImzala.click();
        return this;
    }

    @Step("S-İmza seç")
    public ImzaBekleyenlerPage sImzaSec() {
        rdbSImaza.click();
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage sImzaİmzala(boolean secim) {
        btnSImzaImzala.click();
        if (secim == true) {
            btnSImzaImzalaEvet.pressEnter();
        } else {
            btnSImzaImzalaHayir.pressEnter();
        }
        return this;
    }

    @Step("İmzala")
    public ImzaBekleyenlerPage sImzaImzala(boolean secim) {
        btnSImzaImzala.click();
        if (secim == true) {
            btnSImzaImzalaEvet.pressEnter();
        } else {
            btnSImzaImzalaHayir.pressEnter();
        }
        return this;
    }

    public ImzaBekleyenlerPage dogruKonuVeNoKontrol(String toplantiNo, String konu) {

        tableKararIzlemeEvraklar
                .filterBy(Condition.text(toplantiNo)).filterBy(Condition.text(konu)).get(0).shouldBe(visible);
        return this;

    }

    public ImzaBekleyenlerPage evrakSec(String toplantiNo, String konu) {
        int i = 0;
        while (i < 100) {
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
    public ImzaBekleyenlerPage evrakOlmadigiGorme(String toplantiNo, String konu, boolean vardir) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(toplantiNo)).get(0).shouldBe(not(Condition.exist));
        ;
        return this;
    }

    @Step("Evrak olmadığı görülür : \"{evrakNo}\" ")
    public ImzaBekleyenlerPage evrakOlmadigiGorme(String evrakNo) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(evrakNo)).shouldHaveSize(0);
        return this;
    }

    @Step("Evrak  kontrolü : \"{evrakNo}\" ")
    public ImzaBekleyenlerPage evrakNoKontrolu(String evrakNo) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(evrakNo)).shouldHaveSize(1);
        return this;
    }

    @Step("İade et")
    public ImzaBekleyenlerPage iadeEt() {
        btnIadeEt.click();
        return this;
    }

    @Step("İçerik")
    public ImzaBekleyenlerPage icerik() {
        btnIcerik.click();
        return this;
    }

    @Step("Pdf Önizleme butonuna tıklanır")
    public ImzaBekleyenlerPage pdfOnizleme() {
        SelenideElement btnPdfOnizleme = $x("//*[text()='PDF Önizleme']/ancestor::tbody[1]//button");
        btnPdfOnizleme.click();
        return this;
    }
    @Step("PDF içerik Kontrolü : \"{icerik}\" ")
    public ImzaBekleyenlerPage geregiBilgiAlaniAdresPdfKontrol(String icerik) {

        SelenideElement icerikPDF = $(By.xpath("//div[@id='viewer']/div[@class='page']//div[.='Ağ (Network) Uzman Yardımcısı V.']"));

        System.out.println(icerikPDF.getText());

        Assert.assertEquals(icerikPDF.getText().contains(icerik), true);

        takeScreenshot();

        return this;
    }

    @Step("Dosya ekle")
    public ImzaBekleyenlerPage iadeEtDosyaEkle(String pathToFile) {
        uploadFile(btnDosyaEkle, pathToFile);
        return this;
    }

    @Step("Evrak Sil")
    public ImzaBekleyenlerPage evrakSil() {
        btnEvrakSil.shouldBe(visible).click();
        return this;
    }

    @Step("Not sonrası Evrak Sil")
    public ImzaBekleyenlerPage sil() {
        btnSil.shouldBe(visible).click();
        return this;
    }

    @Step("Not doldur")
    public ImzaBekleyenlerPage notDoldur(String not) {
        txtNot.setValue(not);
        return this;
    }

    @Step("İade et")
    public ImzaBekleyenlerPage iadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }

    @Step("icerik Kontrol : \"{deger}\" ")
    public ImzaBekleyenlerPage icerikKontrol(String deger) {
        String text = pdfIcerikKontrol.getText();
        text.contains(deger);
        return this;
    }

    @Step("İade et")
    public ImzaBekleyenlerPage birimSec(String birim) {
        solMenuBirim.filterBy(text(birim)).first().click();
        return this;
    }

    @Step("Evrak konusuna göre İçerik tıklama : {konu} ")
    public ImzaBekleyenlerPage evrakKonusunaGoreIcerikTiklama(String konu) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(konu)).first()
                .$("[id^='mainInboxForm:inboxDataTable'][id$='detayGosterButton']").click();
        return this;
    }

    @Step("Evrak konusuna göre İçerik tıklama : \"{konu}\" ")
    public ImzaBekleyenlerPage evrakKonusunaGoreKontrolVeTiklama(String konu) {
        tableKararIzlemeEvraklar.filterBy(Condition.text(konu))
                .first()
                .click();
        return this;
    }

    @Step("Evrakın İmza Bekleyenler menüsünde olduğunu görme")
    public ImzaBekleyenlerPage evrakKonusunaGoreKontrol(String konu) {
        boolean durum = tableKararIzlemeEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Evrak Seç")
    public ImzaBekleyenlerPage evrakSec(String konu, String gidecegiYer, String gonderen, String evrakNo) {
        tblImzaBekleyenler
                .filterBy(text(konu))
                .filterBy(text(gidecegiYer))
                .filterBy(text(gonderen))
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Seç")
    public ImzaBekleyenlerPage evrakSec(String konu, String gidecegiYer, String gonderen) {
        tblImzaBekleyenler
                .filterBy(text(konu))
                .filterBy(text(gidecegiYer))
                .filterBy(text(gonderen))
                .first()
                .click();
        return this;
    }


    @Step("Evrak silme notu gir")
    public ImzaBekleyenlerPage evrakSilmeNotuGir(String not) {
        txtEvrakSilmeNotu.setValue(not);
        return this;
    }

    @Step("Popup Silme Onayı:  {secim}")
    public void popupSilmeOnayi(String secim) {

        SelenideElement btnIslemOnayiEvet = $(By.id("mainPreviewForm:evrakSilEvetButton"));
        SelenideElement btnIslemOnayiHayir = $(By.id("mainPreviewForm:evrakSilHayirButton"));

        switch (secim) {
            case "Evet":
                btnIslemOnayiEvet.click();
                break;
            case "Hayır":
                btnIslemOnayiHayir.click();
                break;
        }

    }
}
