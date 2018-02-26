package pages.altMenuPages;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.TextEditor;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageComponents.tabs.EkleriTab;
import pages.pageComponents.tabs.IlgileriTab;
import pages.ustMenuPages.EvrakOlusturPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class EvrakDetayiPage extends MainPage {

    SelenideElement pageTitle = $(By.xpath("//span[. = 'Evrak Detayı' and @class = 'ui-dialog-title']"));
    SelenideElement btnTebellugEt = $("button .tebellugEt");
    SelenideElement btnPanelHayir = $(By.id("mainInboxForm:tebellugEtHayirButton"));
    SelenideElement dialogTabMenuRight = $(By.id("inboxItemInfoForm:dialogTabMenuRight:dialogTabMenuRight"));
    SelenideElement btnTeslimAl = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton"));
    SelenideElement btnEvrakGoster = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:3:cmdbutton"));
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
    SelenideElement btnKaydet = $("span[class='ui-button-icon-left ui-icon kaydet']");
    SelenideElement btnKaydetEvet = $(By.id("kaydetConfirmForm:kaydetEvetButton"));
    SelenideElement btnKaydetHayir = $(By.id("kaydetConfirmForm:kaydetHayirButton"));

    SelenideElement txtAciklama = $(By.id("inboxItemInfoForm:onayIslemiAciklama"));
    SelenideElement btnGonder = $(By.id("inboxItemInfoForm:gonderButton"));
    SelenideElement btnHavaleOnayinaGonder = $(By.xpath("//span[text()='Havale Onayına Gönder']//..//..//button[2]"));
    SelenideElement btnGonder2 = $(By.xpath("//span[text()='Gönder']//..//..//button"));

    SelenideElement txtSilmeNotu = $("[id^='inboxItemInfoForm:j_idt'] [class*=' ui-inputtextarea']");
    SelenideElement btnEvrakNotSil = $("[class='form-buttons'] [id^='inboxItemInfoForm:j_idt']");

    BelgenetElement txtKullaniciListesi = comboLov(By.id("inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovText"));
    BelgenetElement txtOnaylayacakKisi = comboLov(By.id("inboxItemInfoForm:onaylayacakKisiLov:LovText"));
    BelgenetElement txtTebligEtKullniciListesi = comboLov(By.id("inboxItemInfoForm:kullaniciGrubuLov_id:LovText"));
    SelenideElement btnEvrakDetayiClose = $("div[id='windowItemInfoDialog'] span[class='ui-icon ui-icon-closethick']");
    SelenideElement btnIadeEt2 = $(By.id("inboxItemInfoForm:iadeEtButton_id"));
    SelenideElement btnTeslimAlPopup = $(By.id("teslimAlEvetButton"));

    SelenideElement btnDetay = $("[id$='inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovSecilenTable:0:']");
    ElementsCollection tblKullaniciGrupDetay = $$("[id='inboxItemInfoForm:kullaniciGrubuDetay_data'] tr[data-ri]");
    SelenideElement btnKullaniciGrupDetayKullan = $(By.id("inboxItemInfoForm:kullaniciGrubuDetayKullanViewDialog"));
    SelenideElement btnKullaniciGrupDetayEkraniKapat = $("div[id$='kullaniciGrubuDetayViewDialog'] span[class='ui-icon ui-icon-closethick']");


    private HareketGecmisiTab hareketGecmisiTab = new HareketGecmisiTab();
    private EditorTab editorTab = new EditorTab();
    private BilgileriTab bilgileriTab = new BilgileriTab();
    private EkleriTab ekleriTab = new EkleriTab();
    private IlgileriTab ilgileriTab = new IlgileriTab();

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

    @Step("Ekleri tab aç")
    public EkleriTab ekleriTabAc() {
        return ekleriTab.open();
    }

    @Step("İlgileri tab aç")
    public IlgileriTab ilgileriTabAc() {
        return ilgileriTab.open();
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

    @Step("Kullanici Listesinde detay butonuna tıklanır.")
    public EvrakDetayiPage kullaniciListesiDetay() {
        ElementsCollection tblKullaniciListesi = $$("[id='inboxItemInfoForm:dagitimBilgileriKisiListesiLov:LovSecilenTable_data'] tr[data-ri]");
        tblKullaniciListesi
                .first()
                .$("tr:nth-child(2) button").click();
//        btnDetay.click();
        return this;
    }

    @Step("Kullanici Listesinde detay butonuna tıklanır.")
    public EvrakDetayiPage tebligEtKullaniciListesiDetay() {
//        ElementsCollection tblKullaniciListesi = $$("[id='inboxItemInfoForm:kullaniciGrubuLov_id:LovSecilenTable_data'] tr[data-ri]");
//        tblKullaniciListesi
//                .first()
//                .$("tr:nth-child(2) button").click();
        $("[id='inboxItemInfoForm:kullaniciGrubuLov_id:LovSecilenTable_data'] tbody tbody tr:nth-child(2) button").click();
        return this;
    }


    @Step("Kullanici Grup Detay tablosunda \"{kullanici}\" kontrolü yapılır.")
    public EvrakDetayiPage kullaniciGrupDetayKontrol(String kullanici) {
        tblKullaniciGrupDetay
                .filterBy(text(kullanici))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Kullanici Grup Detay ekranı kapatılır.")
    public EvrakDetayiPage kullaniciGrupDetayEkraniKapat(){
        btnKullaniciGrupDetayEkraniKapat.click();
        return this;
    }

    @Step("Kullanici Grup Detay ekran kontrolü yapılır.")
    public EvrakDetayiPage tebligEtKullaniciGrupDetayKontrol() {
        SelenideElement popUpKullaniciGrupDetay = $(By.id("inboxItemInfoForm:tebligKullaniciGrubuDetayViewDialog"));
        Assert.assertEquals(popUpKullaniciGrupDetay.isDisplayed(),true,"Kullanıcı Grup Detay popupı gelir.");
        return this;
    }

    @Step("Kullanici Grup Detay ekranı kapatılır.")
    public EvrakDetayiPage tebligEtKullaniciGrupDetayEkraniKapat() {
        $(By.id("inboxItemInfoForm:kullaniciGrubuDetayKapatViewDialog")).click();
        return this;
    }

    @Step("Kullanici Grup Detay tablosunda checkbox kontrolü yapılır.")
    public EvrakDetayiPage kullaniciGrupDetayCheckBoxKontrolu(boolean shoulBeSelected) {
        tblKullaniciGrupDetay.size();
        for (int i = 0; i < tblKullaniciGrupDetay.size(); i++) {
            if (shoulBeSelected) {
                Assert.assertEquals(tblKullaniciGrupDetay
                        .get(i)
                        .$("div[class$='ui-state-active']").isDisplayed(), true);
                Allure.addAttachment("checkbox kontrol", "Seçili.");
            } else
                Allure.addAttachment("checkbox kontrol", "Seçili değil.");
        }
        takeScreenshot();

        return this;
    }

    @Step("Kullanici Grup Detay tablosunda checkbox seçimi. \"{shouldBeSelect}\" ")
    public EvrakDetayiPage kullaniciGrupDetayCheckBoxSecimi(String kullanici, boolean shouldBeSelect) {
        tblKullaniciGrupDetay.size();
        if (shouldBeSelect) {
            if (tblKullaniciGrupDetay.filterBy(text(kullanici)).first().$("div[class$='ui-state-active']").isDisplayed())
                Allure.addAttachment("checkbox", "Seçili");
            else {
                tblKullaniciGrupDetay
                        .filterBy(text(kullanici)).first()
                        .$("div[class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']").click();
            }
        } else {
            if (tblKullaniciGrupDetay.filterBy(text(kullanici)).first().$("div[class$='ui-state-active']").isDisplayed()) {
                tblKullaniciGrupDetay
                        .filterBy(text(kullanici)).first()
                        .$("div[class$='ui-state-active']").click();
            } else {
                Allure.addAttachment("checkbox", "Seçili değil");
            }
        }
        return this;
    }


    @Step("Kullanici Grup Detay ekranında Kullan butonu tıklanır.")
    public EvrakDetayiPage kullaniciGrupDetayKullan() {
        clickJs(btnKullaniciGrupDetayKullan);
        return this;
    }

    @Step("Kaydet")
    public EvrakDetayiPage kaydet() {
        btnKaydet.click();
        return this;
    }

    @Step("Evet tıklanır")
    public EvrakDetayiPage kaydetEvet() {
        btnKaydetEvet.click();
        return this;
    }

    @Step("Kaydet")
    public EvrakDetayiPage kaydet(boolean save) {
        btnKaydet.click();
        if (save)
            btnKaydetEvet.click();
        else
            btnKaydetHayir.click();
        return this;
    }

    @Step("Evrak Teslim Al popupı kapatılır. ")
    public EvrakDetayiPage evrakTeslimAlPopUpEvet() {
        btnTeslimAlPopup.click();
        return this;
    }

    @Step("Açıklama girilir.")
    public EvrakDetayiPage kaydetVeOnayaSunAciklama(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Gönder butonu tıklanır.")
    public EvrakDetayiPage iadeEt() {
        btnIadeEt2.click();
        return this;
    }


    @Step("Gönder butonu tıklanır.")
    public EvrakDetayiPage gonder() {
        btnGonder.click();
        return this;
    }

    @Step("Havale Yap ekranında Gönder butonu tıklanır.")
    public EvrakDetayiPage havaleYapGonder() {
        btnGonder2.click();
        return this;
    }

    @Step("Havale Yap ekranında Havele Onayına Gönder butonu tıklanır.")
    public EvrakDetayiPage havaleYapHavaleOnayınaGonder() {
        btnHavaleOnayinaGonder.click();
        return this;
    }

    @Step("Havale Yap ekranında Havele Onayına Gönder butonu tıklanır.")
    public EvrakDetayiPage havaleYapButon(String buttonName) {
        SelenideElement button = $(By.xpath("//button//span[text()='" + buttonName + "']//.."));
        button.click();
        return this;
    }

    @Step("Kullanici Lisesi alanında \"{kullaniciListesi}\" seçilir. ")
    public EvrakDetayiPage kullaniciListesiSec(String kullaniciListesi) {
        txtKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanici Lisesi alanında kullaniciListesi kontrolü. \"{kullaniciListesi}\" , {shouldBeExist}")
    public EvrakDetayiPage kullaniciListesiKontrolu(String kullaniciListesi,boolean shouldBeExist) {
        if(shouldBeExist)
            txtKullaniciListesi.openTreePanel().getSelectableItems().filterBy(text(kullaniciListesi)).shouldHaveSize(1);
        else
            txtKullaniciListesi.openTreePanel().getSelectableItems().filterBy(text(kullaniciListesi)).shouldHaveSize(0);

        return this;
    }

    @Step("Onaylayacak Kişi alanında \"{kisi}\" seçilir. ")
    public EvrakDetayiPage onaylayacakKisiSec(String kisi) {
        txtOnaylayacakKisi.openTreePanel().getSelectableItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kullanici Lisesi alanında \"{kullaniciListesi}\" seçilir. ")
    public EvrakDetayiPage tebligEtKullaniciListesiSec(String kullaniciListesi) {
        txtTebligEtKullniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanici Lisesi alanında kullaniciListesi kontrolü. \"{kullaniciListesi}\" , {shouldBeExist}")
    public EvrakDetayiPage tebligEtKullaniciListesiKontrolu(String kullaniciListesi,boolean shouldBeExist) {
        if(shouldBeExist)
            txtTebligEtKullniciListesi.openTreePanel().getSelectableItems().filterBy(text(kullaniciListesi)).shouldHaveSize(1);
        else
            txtTebligEtKullniciListesi.openTreePanel().getSelectableItems().filterBy(text(kullaniciListesi)).shouldHaveSize(0);

        return this;
    }

    @Step("Evrak Detay sayfası kapatılır.")
    public EvrakDetayiPage evrakDetayiSayfasiKapat() {
        btnEvrakDetayiClose.click();
        return this;
    }

    @Step("Kaydet Ve Onaya Sun Uyari PopUp kapatılır.")
    public EvrakDetayiPage kaydetVeOnayaSunUyariPopUpEvet() {
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

    @Step("Evrak Göster")
    public EvrakDetayiPage evrakGoster() {

        btnEvrakGoster.click();

        return this;
    }

    @Step("Pdf Dağıtımda eklerin gitmeyeceği yerler kontrolu: {dagitim}")
    public EvrakDetayiPage eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim1(String dagitim, String ekler) {
        String pdfDagitim = $(By.xpath("//*[@id=\"viewer\"]/div/div[2]/div[30]")).getText();
        Assert.assertEquals(pdfDagitim.contains(ekler), true);
        return this;
    }

    @Step("Pdf Dağıtımda eklerin gitmeyeceği yerler kontrolu: {dagitim}")
    public EvrakDetayiPage eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim2(String dagitim, String ekler) {
        String pdfDagitim2 = $(By.xpath("//*[@id=\"viewer\"]/div/div[2]/div[31]")).getText();
        String pdfDagitimDevami = $(By.xpath("//*[@id=\"viewer\"]/div/div[2]/div[32]")).getText();

        String pdfDagitim = pdfDagitim2 + " " + pdfDagitimDevami;

        Assert.assertEquals(pdfDagitim.contains(ekler), true);
        return this;
    }

    @Step("Pdf Dağıtımda eklerin gitmeyeceği yerler kontrolu: {dagitim}")
    public EvrakDetayiPage eklerinDagitimdaGitmeyecegiYerlerKontroluDagitim3(String dagitim, String ekler) {
        String pdfDagitim3 = $(By.xpath("//*[@id=\"viewer\"]/div/div[2]/div[33]")).getText();
        String pdfDagitimDevami = $(By.xpath("//*[@id=\"viewer\"]/div/div[2]/div[34]")).getText();

        String pdfDagitim = pdfDagitim3 + " " + pdfDagitimDevami;
        Assert.assertEquals(pdfDagitim.contains(ekler), true);

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

        @Step("Kaldırılacak klasör, Gereği, Onay akışı, bilgilerinin girildiği şekilde geldiği görülür.")
        public BilgileriTab bilgileriTabKaldirilacakKlasorOnayAkisiGeregiGeldigiGorme(String kaldirilacakKlasor,String geregi,String onayAkisi){
            boolean durum = $$("[id$='eklenecekKlasorlerLov:LovSecilenTable_data']").filterBy(Condition.text(kaldirilacakKlasor)).size()==1;
            boolean durum1 = $$("[id$='geregiLov:LovSecilenTable']").filterBy(Condition.text(geregi)).size()==1;
            boolean durum2 = $$("[id$='akisLov:LovSecilen']").filterBy(Condition.text(onayAkisi)).size()==1;
            Assert.assertEquals(durum,durum1);
            Assert.assertEquals(durum2,durum1);
            return this;
        }
    }

    public class EkleriTab extends MainPage {

        SelenideElement tabEkleri = $(By.xpath("//span[. = 'Ekleri']/../../..//button"));

        private EkleriTab open() {
            tabEkleri.click();
            return this;
        }


        @Step("Eklenen dosyanın geldiği görülür.")
        public EkleriTab eklenenDosyaninGeldigiGorulur(String dosya){
            boolean durum = $$(By.id("inboxItemInfoForm:ekListesiDataTable_data")).filterBy(Condition.text(dosya)).size()==1;
            Assert.assertEquals(durum,true);
            return this;
        }

        @Step("Kopyası oluşturulan evrak eklerinin aynısının geldiği ve değiştirilebildiği görülür")
        public EkleriTab eklenenDosyaninKopyalananDosyaAyniGeldigiGorulur(){
            boolean durum = $$(By.id("inboxItemInfoForm:ekListesiDataTable_data")).filterBy(Condition.text("Listelenecek Veri Bulunamamıştır.")).size()==1;
            Assert.assertEquals(durum,true);
            return this;
        }

    }

    public class IlgileriTab extends MainPage {

        SelenideElement tabIlgileri = $(By.xpath("//span[. = 'İlgileri']/../../..//button"));

        private IlgileriTab open() {
            tabIlgileri.click();
            return this;
        }


        @Step("Cevap yazılan evrak bilgisinin geldiği görülür.")
        public IlgileriTab cevapYazilanEvrakBilgisiGeldigiGorme(){
            boolean durum = $$("[id='inboxItemInfoForm:ilgiListesiDataTable_data'] > tr").size()==1;
            Assert.assertEquals(durum,true);
            return this;
        }

        @Step("Kopyası oluşturulan evrak ilgilerinin aynısının geldiği ve değiştirilebildiği görülür")
        public IlgileriTab cevapYazilanEvrakBilgisiKopyalananBosEvrakAyniGeldigiGorme(){
            boolean durum = $$("[id='inboxItemInfoForm:ilgiListesiDataTable_data'] > tr").filterBy(Condition.text("Listelenecek Veri Bulunamamıştır.")).size()==1;
            Assert.assertEquals(durum,true);
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