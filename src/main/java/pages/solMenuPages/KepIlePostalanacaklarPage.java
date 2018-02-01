package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KepIlePostalanacaklarPage extends MainPage {

    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnEvrakPostala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement cmbGonderici = $(By.id("mainPreviewForm:dataTableId:0:fromKepAdresId"));
    ElementsCollection tblEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnEvrakPostalaPostala = $(By.id("mainPreviewForm:postalaButton_id"));

    @Step("\"{konuKodu}\" adlı evrağın listelenmediği görülür.")
    public KepIlePostalanacaklarPage evragınListelenmedigiGorme(String konuKodu) {
        boolean durum = tblEvraklar.filterBy(Condition.text(konuKodu)).size() == 0;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Kep ile Postalanacaklar sayfası aç")
    public KepIlePostalanacaklarPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.KEPilePostalanacaklar);
        return this;
    }

    @Step("Gönderici kontrolü")
    public KepIlePostalanacaklarPage gondericiKontrol(String gonderici) {
        String gonderen = cmbGonderici.getText();
        Assert.assertEquals(gonderici, gonderen);
        System.out.println("Metin değer: " + gonderen + " Metin değer: " + gonderici);
        return this;
    }

    @Step("Evrak Postala")
    public KepIlePostalanacaklarPage evrakPostala() {
        btnEvrakPostala.click();
        return this;
    }

    @Step("{alan} - {kisi} : {gidisSekli} gönderici ve alıcı kep adreslerinin geldiği görülür.")
    public KepIlePostalanacaklarPage postalanacakYerlerAlanGoreSecimGeldigiGorme(String alan,String kisi, String gidisSekli){
        boolean durum = $$("[id='mainPreviewForm:dataTableId_data'] > tr[role='row']").filterBy(Condition.text(kisi))
                .filterBy(Condition.text(gidisSekli)).size()==1;
        Assert.assertEquals(durum,true);
        takeScreenshot();
        return this;
    }

    @Step("{alan} - {kisi} : {gidisSekli} gönderici ve alıcı kep adreslerinin listelenmediği görülür.")
    public KepIlePostalanacaklarPage postalanacakYerlerAlanGoreSecimListelenmedigiGorme(String alan,String kisi, String gidisSekli){
        boolean durum = $$("[id='mainPreviewForm:dataTableId_data'] > tr[role='row']").filterBy(Condition.text(kisi))
                .filterBy(Condition.text(gidisSekli)).size()==0;
        Assert.assertEquals(durum,true);
        takeScreenshot();
        return this;
    }

    @Step("Postala")
    public KepIlePostalanacaklarPage evrakPostalaPostala() {
        btnEvrakPostalaPostala.click();
        return this;
    }

    @Step("Belge elektronik imzalı değil! Evrakı postalamak üzeresiniz. Devam etmek istiyor musunuz? uyarısının geldiği görülür.")
    public KepIlePostalanacaklarPage belgeElektronikImzaliDegilUyariGeldigiGorme() {
        boolean durum = $$("[id^='mainPreviewForm:postalaDogrulaDialogForm']").filterBy(Condition.visible).size() == 0;
        Assert.assertEquals(durum, false);
        takeScreenshot();
        return this;
    }

    @Step("Evet seçilir")
    public KepIlePostalanacaklarPage belgeElektronikImzaliDegilUyariEvet() {
        $("[id$='evetButton_id']").click();
        return this;
    }

    @Step("Kayıtlı e-posta adresleri bağlantı noktası ekranının geldiği görülür.")
    public KepIlePostalanacaklarPage kayitliEpostaAdresleriBaglantisiGeldigiGorme() {
        boolean durum = $$(By.id("kepLoginDialogId")).size() == 1;
        Assert.assertEquals(durum, true);
        takeScreenshot();
        return this;
    }

    @Step("Gönderici çek")
    public String gondericiCek() {
        String secilen = cmbGonderici.getSelectedText();
        return secilen;
    }

    @Step("Evrak seçilir")
    public KepIlePostalanacaklarPage evrakSec(String konuKodu) {
        tblEvraklar
                .filterBy(Condition.text(konuKodu)).get(0).click();
        return this;
    }

    @Step("İlk evrak tıkla")
    public KepIlePostalanacaklarPage ilkEvrakTikla() {
        tblIlkEvrak.click();
        return this;
    }

}
