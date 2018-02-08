package pages.solMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class KapattiklarimPage extends MainPage {

    ElementsCollection tblKapattiklarim = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");


    @Step("Kapattıklarım sayfasını aç")
    public KapattiklarimPage openPage() {
        solMenu(SolMenuData.KapatmaIslemleri.Kapattiklarim);
        return this;
    }

    BelgenetElement txtKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection tblTakipListesi = $$("tbody[id='evrakTakibimeEkleDialogForm:takipListLov:LovSecilenTable_data'] > tr[role='row']");
    SelenideElement btnTakipListesiKapat = $("div[id='evrakTakibimeEkleDialogForm:takipDialog'] span[class*='ui-icon-closethick']");

    @Step("{konu} konulu evrak üzerinde Takip Listesi butonuna tıkla.")
    public KapattiklarimPage takipListesiAc(String konu) {
        tblKapattiklarim
                .filterBy(text("Konu: " + konu))
                .first()
                .$x(".//span[contains(@class,'ui-button-icon-left ui-icon document-addFollow')]/..")
                .click();
        return this;
    }

    @Step("Takip Listesinde {adiSoyadi} kullanıcısının ve {birim} birim bilgisinin olduğu görülür.")
    public KapattiklarimPage takipListesiKontrol(String adiSoyadi, String birim){
        tblTakipListesi.filterBy(text(adiSoyadi)).filterBy(text(birim)).first().shouldBe(visible);
        return this;
    }

    @Step("Takip Listesi ekranında bulunan (X) \"Sayfayı Kapatma\" butonuna basılır. Takip listesi ekranın kapatıldığı görülür.")
    public KapattiklarimPage takipListesiKapat(){
        btnTakipListesiKapat.click();
        txtKullaniciListesi.shouldNotBe(visible);
        return this;
    }





}

