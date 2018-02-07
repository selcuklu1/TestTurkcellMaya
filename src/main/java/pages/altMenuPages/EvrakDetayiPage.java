package pages.altMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EvrakDetayiPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
    SelenideElement btnTebellugEt = $("button .tebellugEt");
    SelenideElement btnPanelHayir = $(By.id("mainInboxForm:tebellugEtHayirButton"));
    SelenideElement dialogTabMenuRight = $(By.id("inboxItemInfoForm:dialogTabMenuRight:dialogTabMenuRight"));
    SelenideElement btnEvrakGoster = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton"));
    SelenideElement btnHavaleYap = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='havaleEt']");
    SelenideElement btnTebligEt = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='tebligEt']");
    SelenideElement btnIadeEt = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='iadeEt']");
    SelenideElement btnCevapYaz = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='cevapYaz']");
    SelenideElement btnEvrakKapat = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='evrakKapat']");
    SelenideElement btnSil = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='sil']");
    SelenideElement divContainer = $("#evrakBilgileriContainerDiv");
    SelenideElement spanBilgileri = $x("//span[. = 'Bilgileri']");
    SelenideElement tabEditor = $("button .editor");

    private HareketGecmisiTab hareketGecmisiTab = new HareketGecmisiTab();

    @Step("Sayfa açıldı mı kontrolü")
    public EvrakDetayiPage sayfaAcilmali() {
        Assert.assertEquals(pageTitle.is(visible),true);
        return this;
    }

    @Step("Hareket Geçmisi tab aç")
    public HareketGecmisiTab hareketGecmisiTabAc() {
        return hareketGecmisiTab.open();
    }

    @Step("Tebliğ geçmişi tab aç")
    public TebligGecmisiTab tebligGecmisiTabAc() {
        return new TebligGecmisiTab().open();
    }

    @Step("Tebellüğ Et butonuna tıkla.")
    public EvrakDetayiPage tebellugEt(boolean onay) {
        Selenide.sleep(5000);
        btnTebellugEt.waitUntil(visible, 5000);
        btnTebellugEt.click();

        if (onay == true)
            $$(By.id("mainInboxForm:tebellugEtEvetButton")).last().click();
        else
            btnPanelHayir.click();


        return this;
    }

    @Step("Evrak göster, Havale yap, tebliğ et, iade et, cevap yaz, evrak kapat Ikon kontrolleri")
    public EvrakDetayiPage ikonKontrolleri() {

        dialogTabMenuRight.shouldBe(visible);

        Assert.assertEquals(btnEvrakGoster.isDisplayed(), true);
        Assert.assertEquals(btnHavaleYap.isDisplayed(), true);
        Assert.assertEquals(btnTebligEt.isDisplayed(), true);
        Assert.assertEquals(btnIadeEt.isDisplayed(), true);
        Assert.assertEquals(btnCevapYaz.isDisplayed(), true);
        Assert.assertEquals(btnEvrakKapat.isDisplayed(), true);

        return this;
    }

    @Step("Cevap Yaz")
    public EvrakDetayiPage cevapYaz() {
        btnCevapYaz.shouldBe(visible);
        btnCevapYaz.click();
        return this;
    }

    @Step("Tebellüğ Butonu kontrolü")
    public EvrakDetayiPage tebellugButonuKontrolEt() {
        btnTebellugEt.shouldBe(visible);
        return this;
    }

    @Step("Evrak Bilgileri tabı açıldı.")
    public EvrakDetayiPage evrakBilgileriTabAktifKontrolEt() {
        spanBilgileri.shouldHave(attribute("class", "tabMenuTextSelected")).shouldBe(visible);
        return this;
    }

    @Step("Sil butonunun gelmediği kontrolu")
    public EvrakDetayiPage silButonuKontrolu() {

        Assert.assertEquals(btnSil.isDisplayed(), false);

        return this;
    }

    @Step("Editör tabı ekranında açıldığı kontrolu")
    public EvrakDetayiPage editorTabKontrolu() {

        Assert.assertEquals(tabEditor.isDisplayed(), true);

        return this;
    }

    @Step("\"Evrak Detayı\" ekranının görüntülendiği görülür")
    public EvrakDetayiPage sayfaAcilmasiKontrolu() {
        pageTitle.shouldBe(visible);
        return this;
    }


    public class TebligGecmisiTab extends MainPage {

        SelenideElement tabTebligGecmisi = $(By.xpath("//span[. = 'Tebliğ Geçmişi']/../../..//button"));
        ElementsCollection tableTebligGecmisi = $$("tbody[id='inboxItemInfoForm:tebligDataTable_data'] > tr[role='row']");

        private TebligGecmisiTab open() {
            tabTebligGecmisi.click();
            return this;
        }

        @Step("Teblig geçmişi kontrol et")
        public TebligGecmisiTab tebligGecmisiKontrol(String tebligEdenveTarih, String[] kullanicilar) {

            $x("//span[contains(text(), '" + tebligEdenveTarih + "')]").waitUntil(visible, 5000);

            SelenideElement currentRow = tableTebligGecmisi
                    .filterBy(Condition.text(tebligEdenveTarih))
                    .last()
                    .waitUntil(visible, 5000);

            if (currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).isDisplayed()) {
                currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).click();
            }

            ElementsCollection tableTebligEdilen = $$(By.xpath("//span[. = '" + tebligEdenveTarih + "']/../..//tbody/tr[@role='row']"));

            for (int i = 0; i < kullanicilar.length; i++) {

                tableTebligEdilen
                        .filterBy(Condition.text(kullanicilar[i]))
                        .first()
                        .shouldBe(visible);
            }


            return this;
        }

        @Step("Teblig geçmişinde tebellüğ olanları kontrol et")
        public TebligGecmisiTab tebligGecmisiKontrol(String tebligEdenveTarih, String[] kullanicilar, String[] tebellugTarih) {

            Selenide.sleep(5000);
            SelenideElement currentRow = tableTebligGecmisi
                    .filterBy(Condition.text(tebligEdenveTarih))
                    .last();

            if (currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).isDisplayed()) {
                currentRow.$(By.xpath(".//span[. = '" + tebligEdenveTarih + "']/..//span[contains(@class, 'ui-icon-plusthick')]")).click();
            }

            ElementsCollection tableTebligEdilen = $$(By.xpath("//span[. = '" + tebligEdenveTarih + "']/../..//tbody/tr[@role='row']"));

            for (int i = 0; i < kullanicilar.length; i++) {

                tableTebligEdilen
                        .filterBy(Condition.text(kullanicilar[i]))
                        .filterBy(Condition.text(tebellugTarih[i]))
                        .first()
                        .shouldBe(visible);
            }


            return this;
        }

    }

    public class HareketGecmisiTab extends MainPage {

        SelenideElement tabHareketGecmisi = $("button .kullaniciGecmisi");
        ElementsCollection tblHareketGecmisi = $$("tbody[id='inboxItemInfoForm:hareketGecmisiDataTable_data'] > tr[role='row']");

        private HareketGecmisiTab open() {
            tabHareketGecmisi.click();
            return this;
        }

        @Step("")
        public HareketGecmisiTab tabloKontol(String text) {
            tblHareketGecmisi
                    .filterBy(Condition.text(text))
                    .shouldHaveSize(1);
            return this;
        }

    }

}
