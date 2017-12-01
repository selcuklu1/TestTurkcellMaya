package pages.ustMenuPages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimIcerikSablonlarPage extends BaseLibrary {

    public SelenideElement txtSablonAdi = $(By.id("birimSablonForm:sablonAdiText_id"));
    public BelgenetElement lovKullanilacakBirimler = comboLov(By.id("birimSablonForm:sablonLov_id:LovText"));
    public SelenideElement selEvrakTipi = $x("//label[normalize-space(text())='Evrak Tipi']/ancestor::tr[last()]//select[starts-with(@id,'birimSablonForm')]");
                                            //$("#birimSablonForm select:first-child")
    public SelenideElement btnYeniSablonOlustur = $(By.id("birimSablonForm:sablonIslemYeniButton_Id"));
    public SelenideElement btnKaydet = $(By.id("birimSablonForm:sablonIslemKaydetButton_id"));
    public SelenideElement btnSil = $(By.id("birimSablonForm:sablonIslemSilButton_id"));
    public SelenideElement btnEvrakOnizleme = $(By.id("birimSablonForm:sablonOnizlemeButton_id"));


    public SelenideElement tblBirimSablonlari = $("[id^='birimSablonForm'][id$='sablonDataTable']");
    //Detay butonları row sayısına eşit olmalı
    public ElementsCollection rowsBirimSablonlari = $$("[id$='sablonDataTable'] tbody tr[role='row']");
    public ElementsCollection btnDetayInEachRow = $$("[id$='sablonListesiDetayButton_id']");

    public SelenideElement editor = $(By.id("cke_birimSablonForm:birimIcerikCkEditor"));

    public BirimIcerikSablonlarPage openPage() {
        new UstMenu().ustMenu("Birim İçerik Şablonları");
        return this;
    }

    @Step("Birim Şablorın her kayıda ait Detay butonunun bulunduğu")
    public BirimIcerikSablonlarPage detayButonlarinExist() {
        Assert.assertEquals(rowsBirimSablonlari.size(), btnDetayInEachRow.size());
        return this;
    }

    @Step("Yeni Şablon Oluştur, Şablon Adı, Kullanacak Birimle, Evrak Tipi, Yeni Şablon Oluştur, Kaydet, Sil, Evrak Önizleme")
    public BirimIcerikSablonlarPage alanlarinAktifDurumKontrol() {
        txtSablonAdi.shouldBe(disabled);
        lovKullanilacakBirimler.shouldBe(disabled);
        selEvrakTipi.shouldBe(disabled);
        btnYeniSablonOlustur.shouldBe(enabled);
        btnKaydet.shouldBe(disabled);
        btnSil.shouldBe(disabled);
        btnEvrakOnizleme.shouldBe(disabled);
        return this;


    }


    public void mymethod(){
        btnYeniSablonOlustur.click();
        lovKullanilacakBirimler.type("optiim birim").titleItems()
                .filterBy(Condition.exactText("optiim birim")).first().click();

        $(By.id("birimSablonForm:sablonLov_id:LovSecilenTable:0:selectOneMenu")).$$(By.tagName("option"))
                .shouldHave(CollectionCondition.texts("ALT BİRİMLER GÖRSÜN","ALT BİRİMLER GÖRMESİN"));
        $(By.id("birimSablonForm:sablonLov_id:LovSecilenTable:0:selectOneMenu")).getSelectedOption()
                .shouldBe(Condition.text("ALT BİRİMLER GÖRSÜN"));
        $(By.id("birimSablonForm:sablonLov_id:LovSecilenTable:0:selectOneMenu")).selectOption("ALT BİRİMLER GÖRMESİN");


    }


    public void yeniSablonOlustur(){
        btnYeniSablonOlustur.click();

        //==============
        String e1 = $x("//*[contains(@class,'cke') and contains(@class,'label') and normalize-space(text())='Yazı Türü']").attr("id");
        $("a[aria-labelledby="+ e1 +"]").click();
//        SelenideElement epro =  $inframe(By.xpath("//li//a/span[text()='Courier New']/.."));
//        epro.click();

        switchTo().defaultContent();
//        switchToFrameOfElement(By.xpath("//li//a/span[text()='Courier New']/.."));
//        $x("//li//a/span[text()='Courier New']/..").click();

        String e2 = $x("//*[contains(@class,'cke') and contains(@class,'label') and normalize-space(text())='Kalın']").attr("id");
        $("a[aria-labelledby="+ e2 +"]").click();
        switchTo().defaultContent();

//.cke_contents iframe
//        aria-labelledby="cke_106_label"
        //==============


        lovKullanilacakBirimler.type("optiim birim").titleItems()
                .filterBy(Condition.exactText("optiim birim")).first().click();
        lovKullanilacakBirimler.closeLovTreePanel();

        lovKullanilacakBirimler.lastSelectedLov().$(By.tagName("select")).selectOption("ALT BİRİMLER GÖRSÜN");

        ElementsCollection col = $(By.id("cke_birimSablonForm:birimIcerikCkEditor")).$$(".cke_toolbox a span[class*='label']");
// SelenideElement e = getToolboxElement("Etiket Ekle");
 SelenideElement e = getToolboxElement("Yazı Türü");
 e.click();
 SelenideElement ef = $("html:not(root) div[class='cke_panel_block'][title='Yazı Türü'] h1");
//        getType(e);
 e.click();
        String a = e.innerText();
        //*[@class='cke_toolbox']//a/span[normalize-space(text())='Kes']/..
    }

    public SelenideElement getToolboxElement(String name) {
        return $x("//*[@class='cke_toolbox']//a/span[normalize-space(text())='"+ name +"']/..");
    }

    public void getType(SelenideElement element){
        String elementType = element.attr("class").contains("cke_combo_button") ? "combo":"button" ;
//        iframe[id^="cke"]
//        switch (elementClass){
//            case "cke_combo_button":
//                return;
//            case "cke_button":
//                return;
//        }
    }

    /**
     * First search in main iframe, then first level child iframes.
     * Stay in founded iframe, to return to main iframe use switchTo().defaultContent().
     * @param locator
     */
    private void switchToFrameOfElement(By locator){
        switchTo().defaultContent();
        if ($(locator).exists())
            return;

        ElementsCollection iframes = $$(".cke_panel_frame");
        for (SelenideElement iframe:iframes) {
            switchTo().frame(iframe);
            if ($(locator).exists())
                return;
        }
        throw new NoSuchElementException("Element not found in main and the child first level iframes. By: " + locator);
    }

    /**
     * First search in main iframe, then first level child iframes.
     * Stay in founded iframe, to return to main iframe use switchTo().defaultContent().
     * @param element
     */
    private void switchToFrameOfElement(SelenideElement element){

        switchTo().defaultContent();
        if (element.exists())
            return;

        ElementsCollection iframes = $$(".cke_panel_frame");
        for (SelenideElement iframe:iframes) {
            switchTo().frame(iframe);
            if (element.exists())
                return;
        }
        throw new NoSuchElementException("Element not found in main and the child first level iframes. By: " + element.toWebElement().toString());
    }

    private void findFrameOfElement(){

    }

}
