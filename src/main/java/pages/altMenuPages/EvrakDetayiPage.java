package pages.altMenuPages;

import com.codeborne.selenide.*;
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
    SelenideElement btnSil = $("[id^='inboxItemInfoForm:dialogTabMenuRight:uiRepeat'] [class$='evrakSil']");
    SelenideElement divContainer = $("#evrakBilgileriContainerDiv");
    SelenideElement spanBilgileri = $x("//span[. = 'Bilgileri']");
    SelenideElement tabEditor = $("button .editor");
    ElementsCollection tblHareketGecmisi = $$("tbody[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    SelenideElement txtSilmeNotu = $("[id^='inboxItemInfoForm:j_idt'] [class*=' ui-inputtextarea']");
    SelenideElement btnEvrakNotSil = $("[class='form-buttons'] [id^='inboxItemInfoForm:j_idt']");




    private HareketGecmisiTab hareketGecmisiTab = new HareketGecmisiTab();

    @Step("Sayfa açıldı mı kontrolü")
    public EvrakDetayiPage sayfaAcilmali() {
        Assert.assertEquals(pageTitle.is(visible), true);
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
    public EvrakDetayiPage silButonunGelmedigiKontrolu() {

        Assert.assertEquals(btnSil.isDisplayed(), false);

        return this;
    }

    @Step("Sil butonunun geldiği kontrolu")
    public EvrakDetayiPage silButonununGeldigiKontrolu() {

        Assert.assertEquals(btnSil.isDisplayed(), true);

        return this;
    }

    @Step("Evrak Sil")
    public EvrakDetayiPage evrakSil() {

        btnSil.click();

        return this;
    }

    @Step("Evrak Silme Notu Gir")
    public EvrakDetayiPage evrakSilmeNotuDoldur(String not) {

       txtSilmeNotu.setValue(not);

        return this;
    }

    @Step("Evrak Notu Sonrası Sil")
    public EvrakDetayiPage evrakSilmeNotuSonrasiSil() {

       btnEvrakNotSil.click();

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

    @Step("Silme Onayı: Kaydı silmek istediğinize emin misiniz?: {secim}")
    public EvrakDetayiPage evrakSilPopup(String secim) {

        SelenideElement btnEvet = $(By.id("inboxItemInfoForm:evrakSilEvetButton"));
        SelenideElement btnHayir = $(By.id("inboxItemInfoForm:evrakSilHayirButton"));

        switch (secim) {
            case "Evet":
                btnEvet.click();
                break;
            case "Hayır":
                btnHayir.click();
                break;
        }
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
        SelenideElement btnRaporAlExcel = $(By.id("inboxItemInfoForm:hareketGecmisiDataTable:evrakGecmisiExport"));
        SelenideElement txtBaslangicTarihi = $(By.id("inboxItemInfoForm:hareketGecmisiDataTable:hareketGecmisiBegin_input"));
        SelenideElement txtBitisTarihi = $(By.id("inboxItemInfoForm:hareketGecmisiDataTable:hareketGecmisiEnd_input"));

        private HareketGecmisiTab open() {
            tabHareketGecmisi.click();
            return this;
        }

        @Step("Hareket Geçmişi açıklama kontrolü :\n \"{text}\" ")
        public HareketGecmisiTab tabloKontol(String text) {
            tblHareketGecmisi
                    .filterBy(Condition.text(text))
                    .shouldHaveSize(1);
            return this;
        }

        @Step("Rapor al Excel")
        public HareketGecmisiTab raporAl(String remoteDownloadPath) {
            deleteSpecificFile("Rapor_");

            sleep(3000);

            btnRaporAlExcel.click();
            islemMesaji().basariliOlmali();
            waitForLoadingJS(WebDriverRunner.getWebDriver(), 180);
            sleep(3000);
            searchDownloadedFileWithName(remoteDownloadPath, "Rapor_.xls");

            return this;
        }

        @Step("Evrak Arama ekranı kapat")
        public HareketGecmisiTab evrakDetayiKapat() {
            $(By.xpath("//div[@id='windowReadOnlyEvrakDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
            islemPenceresiKapatmaOnayiPopup("Kapat");
            return this;
        }



        @Step("Hareket Geçmişi tablo kolon isimleri kontrolü.")
        public HareketGecmisiTab tabloKolonIsımleriKontol(String text) {
            Assert.assertEquals($(By.xpath("//span[text()='Gönderen']")).isDisplayed(), true);
            Assert.assertEquals($(By.xpath("//span[text()='Teslim Alan']")).isDisplayed(), true);
            Assert.assertEquals($(By.xpath("//span[text()='İşlem Süreci']")).isDisplayed(), true);
            Assert.assertEquals($(By.xpath("//span[normalize-space(text())='İşlem Tarihi'] ")).isDisplayed(), true);
            Assert.assertEquals($(By.xpath("//span[text()='Açıklama']")).isDisplayed(), true);
            return this;
        }


    }

}
