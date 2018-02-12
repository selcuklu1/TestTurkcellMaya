package pages.altMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.collections4.list.AbstractLinkedList;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.tabs.BilgilerTab;
import pages.pageComponents.tabs.EditorTab;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

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
    ElementsCollection tblHareketGecmisi = $$("tbody[id$='hareketGecmisiDataTable_data'] > tr[role='row']");
    SelenideElement txtAciklama = $(By.id("inboxItemInfoForm:onayIslemiAciklama"));
    SelenideElement btnGonder = $(By.id("inboxItemInfoForm:gonderButton"));

    private HareketGecmisiTab hareketGecmisiTab = new HareketGecmisiTab();
    private EditorTab editorTab = new EditorTab();
    private BilgileriTab bilgileriTab = new BilgileriTab();

    @Step("Sayfa geldiği kontrol edilir.")
    public EvrakDetayiPage sayfaAcilmali() {
        Assert.assertEquals(pageTitle.is(visible), true);
        return this;
    }


    @Step("Editör tab aç")
    public EditorTab editorTaAc() {
        return editorTab.open();
    }

    @Step("Bilgiler tab aç")
    public BilgileriTab bilgileriTabAc() {
        return bilgileriTab.open();
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

    @Step("\"{text}\" butonu tıklanır.")
    public EvrakDetayiPage btnTikla(String text) {
        SelenideElement btn = $(By.xpath("descendant::*[text()='" + text + "']/ancestor::tbody[1]//button"));
        btn.click();
        return this;
    }
    
    @Step("Açıklama girilir.")
    public EvrakDetayiPage kaydetVeOnayaSunAciklama(String aciklama){
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Gönder butonu tıklanır.")
    public EvrakDetayiPage gonder(){
        btnGonder.click();
        return this;
    }

    @Step("Kaydet Ve Onaya Sun Uyari PopUp kapatılır.")
    public EvrakDetayiPage kaydetVeOnayaSunUyariPopUpEvet(){
        SelenideElement btnEvet = $(By.id("kaydetEvetButton"));
        btnEvet.click();
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

    @Step("Evrak Detay ekranı \"{text}\" tabı açık.")
    public EvrakDetayiPage evrakDetayEkraniTabSeçimKontrolu(String text) {
        $x("//span[. = '" + text + "']").shouldHave(attribute("class", "tabMenuTextSelected")).shouldBe(visible);
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

    public class EditorTab extends MainPage {

        SelenideElement tabEditor = $(By.xpath("//span[. = 'Editör']/../../..//button"));

        private EditorTab open() {
            tabEditor.click();
            return this;
        }


        @Step("Editör İçerik Doldur")
        public EditorTab editorIcerikDoldur(String icerik) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            TextEditor editor = new TextEditor();
            editor.clear();
            editor.type(icerik);

            //divEditor.find(By.tagName("iframe")).click();
            //divEditor.find(By.tagName("iframe")).getWrappedElement().sendKeys(icerik);
            return this;
        }
    }


    public class BilgileriTab extends MainPage {

        SelenideElement tabBilgileri = $(By.xpath("//span[. = 'Bilgileri']/../../..//button"));
        BelgenetElement txtOnayAkisi = comboLov("[id^='inboxItemInfoForm:evrakBilgileriList:'][id$=':akisLov:LovText']");

        private BilgileriTab open() {
            tabBilgileri.click();
            return this;
        }

        @Step("Seçili Onay Akışı güncellendi.")
        public BilgileriTab onayAkişGuncelle(String onayAkisi) {
            txtOnayAkisi.clearAllSelectedItems();
            txtOnayAkisi.selectLov(onayAkisi);
            return this;
        }


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
        SelenideElement tblKolonGonderen = $(By.xpath("//span[text()='Gönderen']"));
        SelenideElement tblKolonTeslimAlan = $(By.xpath("//span[text()='Teslim Alan']"));
        SelenideElement tblKolonIslemSureci = $(By.xpath("//span[text()='İşlem Süreci']"));
        SelenideElement tblKolonIslemTarihi = $(By.xpath("//span[normalize-space(text())='İşlem Tarihi']"));
        SelenideElement tblKolonAciklama = $(By.xpath("//span[text()='Açıklama']"));

        private HareketGecmisiTab open() {
            tabHareketGecmisi.click();
            return this;
        }

        @Step("Hareket Geçmişi açıklama kontrolü :\n \"{text}\" ")
        public HareketGecmisiTab tabloKontol(String text) {
            tblHareketGecmisi
                    .filterBy(Condition.text(text))
                    .shouldHaveSize(1);

            Assert.assertEquals(tblKolonGonderen.isDisplayed(), true);
            Assert.assertEquals(tblKolonTeslimAlan.isDisplayed(), true);
            Assert.assertEquals(tblKolonIslemSureci.isDisplayed(), true);
            Assert.assertEquals(tblKolonIslemTarihi.isDisplayed(), true);
            Assert.assertEquals(tblKolonAciklama.isDisplayed(), true);

            Allure.addAttachment("Tablo kontolü", "Aşağıdaki kolonların listelendiği görülür. \n Gönderen\n" +
                    "Teslim Alan\n" +
                    "İşlem Süreci\n" +
                    "İşlem Tarihi\n" +
                    "Açıklama");
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
            $(By.xpath("//div[@id='windowItemInfoDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
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
