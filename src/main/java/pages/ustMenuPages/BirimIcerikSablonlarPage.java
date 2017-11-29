package pages.ustMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.pageComponents.UstMenu;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
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
        lovKullanilacakBirimler.type("optiim birim").titleItems()
                .filterBy(Condition.exactText("optiim birim")).first().click();
        lovKullanilacakBirimler.lastSelectedLov().$(By.tagName("select")).selectOption("ALT BİRİMLER GÖRSÜN");

        ElementsCollection col = $(By.id("cke_birimSablonForm:birimIcerikCkEditor")).$$(".cke_toolbox a span[class*='label']");


    }

}
