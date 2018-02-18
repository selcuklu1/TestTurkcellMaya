package pages.ustMenuPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.UstMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GelenEvrakZimmetRaporuPage extends MainPage {
    SelenideElement sorgula = $(By.id("gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:sorgulaButtonTab1"));
    SelenideElement zimmetRaporTablo = $(By.id("gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1_data"));
    SelenideElement popupKapatma = $("[class='ui-dialog-titlebar-icon ui-dialog-titlebar-close ui-corner-all ui-state-hover']");
    SelenideElement konuKontrolu = $(By.id("windowReadOnlyForm:evrakBilgileriList:3:konuTextArea"));
    SelenideElement evrakEtiket = $("[id$='etiketMetinID']");
    SelenideElement islemKapat = $(By.id("kapatButton"));
    ElementsCollection zimmetEvrakListele = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr");
    ElementsCollection hareketGecmisi = $$("[id='zimmetRaporHareketGecmisiForm:hareketGecmisiDataTableId_data']");
    ElementsCollection pencereListesi1 = $$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']");
    ElementsCollection pencereListesi2 = $$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']");

    @Step("GelenEvrakZimmetRaporu sayfasını aç")
    public GelenEvrakZimmetRaporuPage openPage() {
        ustMenu(UstMenuData.Raporlar.GelenEvrakZimmetRaporu);
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Sorgula")
    public GelenEvrakZimmetRaporuPage sorgula() {
        sorgula.click();
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda kontrol. Evrak1: {konu1}, Evrak2: {konu2}")
    public GelenEvrakZimmetRaporuPage rapordaEvraklarıListele(String konu1, String konu2) {
//        boolean durum = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr")
        boolean durum = zimmetEvrakListele.filterBy(text(konu1)).size() == 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakGecmisiButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id$='evrakGecmisiId']").get(0).click();
        }
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Detay Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakDetayButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id$='evrakGosterButtonTab1']").get(0).click();
        }
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Etiket Buton Tıklama: {konu}")
    public GelenEvrakZimmetRaporuPage evrakEtiketButtonTıklama(String konu) {
//        ElementsCollection tr = $$("[id='gelenEvrakZimmetRaporuYonetimiTabView:gelenEvrakZimmetRaporuTab1Form:gelenEvrakDataTableTab1'] tbody tr").filterBy(text(konu));
        ElementsCollection tr = zimmetEvrakListele.filterBy(text(konu));
        if (tr.size() > 0) {
            tr.get(0).$$("td [id*='j_idt']").get(0).click();
        }
        return this;
    }


    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Kontrolü: Evrak: {konu}, Kullanıcı: {kullanıcı}, IslemSureci: {islemSureci}")
    public GelenEvrakZimmetRaporuPage evrakGecmisiKontrolu(String konu, String kullanıcı, String islemSureci) {
//        boolean durumKonu1 = $$("[id='zimmetRaporHareketGecmisiForm:hareketGecmisiDataTableId_data']")
        boolean durumKonu1 = hareketGecmisi
                .filterBy(text(kullanıcı))
                .filterBy(text(islemSureci)).size() == 0;
        Assert.assertEquals(durumKonu1, false);
        takeScreenshot();
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Geçmiş Kontrolü: Evrak: {konu}")
    public GelenEvrakZimmetRaporuPage evrakDetayKontrolu(String konu) {
        boolean durum = konuKontrolu.getText().equals(konu);
        Assert.assertEquals(durum, true);
        return this;
    }

    @Step("Gelen Evrak Zimmet Raporu Tablosunda Evrak Etiket Kontrolü")
    public GelenEvrakZimmetRaporuPage evrakEtiketKontrolu() {
        boolean durum = evrakEtiket.isDisplayed();
        Assert.assertEquals(durum, true);
        Allure.addAttachment("Evrak Etiket", "Bulunmaktadır");
        return this;
    }


    @Step("Popup Kapatma")
    public GelenEvrakZimmetRaporuPage popupKapatma() {
//        ElementsCollection tr =$$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']").filterBy(text("Evrak Geçmişi"));
        ElementsCollection tr = pencereListesi1.filterBy(text("Evrak Geçmişi"));
        tr.get(0).$("[href]").click();
        return this;
    }

    @Step("Evrak Kapatma")
    public GelenEvrakZimmetRaporuPage evrakKapatma() {
//        ElementsCollection tr =$$("[class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']").filterBy(text("Evrak Detayı"));
        ElementsCollection tr = pencereListesi2.filterBy(text("Evrak Detayı"));
        tr.get(0).$("[href]").click();
        islemKapat.click();
        return this;
    }

}
