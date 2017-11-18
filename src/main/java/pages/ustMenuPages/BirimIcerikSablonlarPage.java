package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.pageComponents.belgenetElements.BelgenetElement;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.BelgenetFramework.comboLov;

public class BirimIcerikSablonlarPage extends BaseLibrary {

    SelenideElement txtSablonAdi = $(By.id("birimSablonForm:sablonAdiText_id"));
    BelgenetElement lovKullanilacakBirimler = comboLov(By.id("birimSablonForm:sablonLov_id:LovText"));
    SelenideElement selEvrakTipi = $(By.id("birimSablonForm:j_idt5751"));
    SelenideElement btnYeniSablonOlustur = $(By.id("birimSablonForm:sablonIslemYeniButton_Id"));
    SelenideElement btnKaydet = $(By.id("birimSablonForm:sablonIslemKaydetButton_id"));
    SelenideElement btnSil = $(By.id("birimSablonForm:sablonIslemSilButton_id"));
    SelenideElement btnEvrakOnizleme = $(By.id("birimSablonForm:sablonOnizlemeButton_id"));

    SelenideElement tblBirimSablonlari = $("[id^='birimSablonForm'][id$='sablonDataTable']");
    //Detay butonları row sayısına eşit olmalı
    ElementsCollection rowsBirimSablonlari = $$("[id$='sablonDataTable'] tbody tr[role='row']");
    ElementsCollection btnDetayInEachRow = $$("[id$='sablonListesiDetayButton_id']");

    SelenideElement editor = $(By.id("cke_birimSablonForm:birimIcerikCkEditor"));

    @Step("Birim İçerik Şablonlarını açıldığında alanların aktif durum kontrol adımı")
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

    @Step("Birim Şablorın her kayıda ait Detay butonunun bulunduğu")
    public BirimIcerikSablonlarPage detayButonlarinExist() {
        Assert.assertEquals(rowsBirimSablonlari.size(), btnDetayInEachRow.size());
        return this;
    }

}
