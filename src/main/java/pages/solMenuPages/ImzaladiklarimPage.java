package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;


public class ImzaladiklarimPage extends MainPage {

    //SelenideElement tblImzaladiklarimEvraklar = $(By.id("mainInboxForm:inboxDataTable_data"));
    SelenideElement tabEvrakGecmisi = $(By.xpath("//*[text()[contains(.,'Evrak Geçmişi')]]"));
    SelenideElement btnIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement tabEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
    ElementsCollection tableKararIzlemeEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row']");// span[class='ui-chkbox-icon']");
    ElementsCollection tblImzalananEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[role='row'] table");
    SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");
    SelenideElement btnGidecegiYer = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    BelgenetElement txtGidecegiYer = comboLov(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));
    SelenideElement txtGidecegiYer2 = $(By.id("inboxFiltreDialogForm:gidecegiYerFilterLovId:LovText"));

    SelenideElement txtBaslangicTarihi = $x("//label[normalize-space(text())='Başlangıç Tarihi :']/../../following-sibling::td//input");
    SelenideElement txtBitisTarihi = $x("//label[normalize-space(text())='Bitiş Tarihi :']/../../following-sibling::td//input");
    ElementsCollection tblImzaladiklarimEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnGeriAl = $x("//span[contains(@class, 'evrakGeriAl')]/..");
    SelenideElement txtGeriAlAciklama = $(By.id("mainPreviewForm:evrakGeriAlInputTextareaId"));
    SelenideElement btnGeriAlOnay = $x("//div[@class='form-buttons']//span[. = 'Geri Al']/..");

    SelenideElement filtrePanelHeader = $("div[id='mainInboxForm:inboxDataTable:filtersAccordion'] > h3");

    ElementsCollection evrakSecButonlar = $$("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] td");


    //Önizleme
    SelenideElement tabEvrakEkleri = $(By.xpath("//*[contains(text(),'Evrak Ekleri')]"));
    SelenideElement tabIlgiBilgileri = $(By.xpath("//*[contains(text(),'İlgi Bilgileri')]"));
    SelenideElement tabIlisikBilgileri = $(By.xpath("//*[contains(text(),'İlişik Bilgileri')]"));

    SelenideElement accordionEvrakEkleri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionEvrakEkleriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionEvrakEkleri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionEvrakEkleriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnl'] [class*='ui-state-active']:nth-child(3)");

    SelenideElement accordionIlgiBilgileri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionIlgiBilgileriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionIlgiBilgileri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionIlgiBilgileriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlI'] [class*='ui-state-active']:nth-child(3)");

    SelenideElement accordionIlisikBilgileri1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlIlisik'] [class*='ui-accordion-header']:nth-child(1)");
    SelenideElement accordionIlisikBilgileriOpen1 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlIlisik'] [class*='ui-state-active']:nth-child(1)");
    SelenideElement accordionIlisikBilgileri2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlIlisik'] [class*='ui-accordion-header']:nth-child(3)");
    SelenideElement accordionIlisikBilgileriOpen2 = $("[id^='mainPreviewForm:j_idt'] [id$='accpnlIlisik'] [class*='ui-state-active']:nth-child(3)");


    ElementsCollection tblOnIzlemeEkler = $$("[id*='ekListesiOnizlemeDataTable'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlgiBilgileri = $$("[id*='ilgiListesiDataTable_data'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlisikBilgileri = $$("[id*='ilisikListesiDataTable_data'] > tr[role='row']");

    @Step("Imzaladiklarim Sayfasini aç")
    public ImzaladiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Imzaladiklarim);
        String pageTitle = SolMenuData.IslemYaptiklarim.Imzaladiklarim.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Paylaş butonu gelmediği görülür")
    public ImzaladiklarimPage paylasButonGelmedigiGorme(String buton) {
        boolean t = evrakSecButonlar.filterBy(text(buton)).size() == 0;
        Assert.assertEquals(t, true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak geldiği görülür")
    public ImzaladiklarimPage evrakGeldigiGorme(String toplantiNo, String konu, String toplantiTarih) {
        tableKararIzlemeEvraklar.filterBy(text(toplantiNo))
                .filterBy(text(konu)).filterBy(text(konu))
                .filterBy(text(toplantiTarih)).filterBy(Condition.visible);
        takeScreenshot();
        return this;
    }

    @Step("ImzaladiklarimIlkPostaSec")
    public ImzaladiklarimPage evrakSec() {
        btnIlkEvrak.click();
        return this;
    }

    @Step("Dokümanı bul ve seç")
    public ImzaladiklarimPage dokumaniSec(String text) {
        filter().findRowsWith(text(text))
                .shouldHaveSize(1)
                .first()
                .click();
        return this;
    }

    @Step("Evrak Geçmişi tab ve evrak geçmişi kontrolleri")
    public ImzaladiklarimPage evrakGecmisi() {

        tabEvrakGecmisi.shouldBe(visible).click();
        String gecmis = tabEvrakGecmisi.getAttribute("innerText");
        System.out.println(gecmis);
        return this;

    }

    @Step("Evrak Geçmişi \"Evrak kurum içi otomatik postalandı\" tekst içermeli")
    public ImzaladiklarimPage evrakGecmisiWith(String text) {
        $("tbody[id$='hareketGecmisiDataTable_data']").shouldHave(text(text));
        return this;
    }

    @Step("")
    public String evrakIcerikKontroluveEvrakNoAl(String icerik) {
        int size = tblImzalananEvraklar.size();
        String evrakNo = "";
        boolean flag = false;

        for (int i = 0; i < size; i++) {
            $(By.id("mainInboxForm:inboxDataTable:" + i + ":detayGosterButton")).click();
            evrakNo = evrakDetayiEvrakNoAl();
            String icerikTxt = $("[id='inboxItemInfoForm:evrakBilgileriList_content'] tr:nth-child(13) tr textarea").text();
            if (icerik.equals(icerikTxt)) {
                flag = true;
                break;
            }
            $(By.xpath("//div[@id='windowItemInfoDialog']//span[@class='ui-icon ui-icon-closethick']")).click();
            islemPenceresiKapatmaOnayiPopup("Kapat");

        }
        Assert.assertEquals(flag, true, "Evrak listelenmiştir");
        return evrakNo;
    }

    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }

    @Step("Gideceği yer seç: {gidecegiYer}")
    public ImzaladiklarimPage gidecegiYerSec(String gidecegiYer) {
        btnGidecegiYer.click();
        txtGidecegiYer2.setValue(gidecegiYer);

        SelenideElement currentList = $$("div[id='inboxFiltreDialogForm:gidecegiYerFilterLovId:gidecegiYerFilterLovIdlovDialogId']").last();
        currentList.waitUntil(Condition.visible, 5000);
        currentList
                .$$("span[class='lovItemTitle ']")
                .filterBy(text(gidecegiYer))
                .last()
                .click();

        return this;
    }

    @Step("Başlangıç Tarihi doldur: {baslangicTarihi}")
    public ImzaladiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        txtBaslangicTarihi.setValue(baslangicTarihi);
        return this;
    }

    @Step("Bitiş Tarihi doldur: {bitisTarihi}")
    public ImzaladiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        txtBitisTarihi.setValue(bitisTarihi);
        return this;
    }

    @Step("Evrak Seç")
    public ImzaladiklarimPage evrakSec(String konu, String gidecegiYer, String evrakTarihi, String no) {

        ElementsCollection kisiselPages = $$("td[id$='mainInboxForm:inboxDataTable_paginator_bottom'] > span[class='ui-paginator-pages'] >  span");

        for (int i = 0; i < kisiselPages.size(); i++) {
            kisiselPages.get(i).click();

            SelenideElement postaListesi = tblImzaladiklarimEvraklar
                    .filterBy(text("Konu: " + konu))
                    .filterBy(text("Gideceği Yer: " + gidecegiYer))
                    .filterBy(text("Evrak Tarihi: " + evrakTarihi))
                    .filterBy(text(no))
                    .first();

            if (postaListesi.isDisplayed() && postaListesi.exists()) {
                postaListesi.click();
                return this;
            }
        }
        Assert.fail("Evrak bulunamadı.");
        return this;

    }

    @Step("Geri al butonuna tıkla")
    public ImzaladiklarimPage geriAl() {
        btnGeriAl.click();
        return this;
    }

    @Step("Geri Al açıklaması doldur: {aciklama}")
    public ImzaladiklarimPage geriAlAciklamaDoldurVeOnayla(String aciklama) {
        txtGeriAlAciklama.setValue(aciklama);
        btnGeriAlOnay.click();
        return this;
    }

    @Step("Filtre panelini aç")
    public ImzaladiklarimPage filtrePanelAc() {
        filtrePanelHeader.click();
        return this;
    }

    @Step("İmzaladıklarım listesinden evrak önizlemede aç")
    public ImzaladiklarimPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblImzaladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("İmzaladıklarımlistesinde evrak kontrolu")
    public ImzaladiklarimPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblImzaladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("İmzaladıklarım listesinde evrak kontrolü:  \"{konu}\" ")
    public ImzaladiklarimPage konuyaGoreEvrakKontroluAllPages(String konu) {
        searchTable().searchInAllPages(true).findRows(text(konu)).getFoundRow().shouldBe(exist);
        return this;
    }

    @Step("Evrak Ek/İlgi/İlişikler tablarının geldiği kontrolu")
    public ImzaladiklarimPage tabKontrolleri() {

        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);
        Assert.assertEquals(tabIlisikBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Parafladıklarım/Evrak Ekleri tabını aç")
    public ImzaladiklarimPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Parafladıklarım/İlgi Bilgieri tabını aç")
    public ImzaladiklarimPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }

    @Step("Parafladıklarım/İlişik Bilgieri tabını aç")
    public ImzaladiklarimPage tabIlisikBilgileriAc() {
        tabIlisikBilgileri.click();
        return this;
    }

    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public ImzaladiklarimPage evrakEkleriAccordionKontrol() {

        accordionEvrakEkleriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen1.isDisplayed(), true);
        accordionEvrakEkleri1.click();
        Selenide.sleep(1000);
        accordionEvrakEkleri2.click();
        accordionEvrakEkleriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionEvrakEkleriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlgi Bilgileri Accordion kontrolu")
    public ImzaladiklarimPage ilgiBilgileriAccordionKontrol() {

        accordionIlgiBilgileriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen1.isDisplayed(), true);
        accordionIlgiBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlgiBilgileri2.click();
        accordionIlgiBilgileriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlişik Bilgileri Accordion kontrolu")
    public ImzaladiklarimPage ilisikBilgileriAccordionKontrol() {

        accordionIlisikBilgileriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionIlisikBilgileriOpen1.isDisplayed(), true);
        accordionIlisikBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlisikBilgileri2.click();
        accordionIlisikBilgileriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionIlisikBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizlemede detay butonu kontrolu")
    public ImzaladiklarimPage detayButonKontrol(String ekSayisi) {

        tblOnIzlemeEkler
                .filterBy(Condition.text(ekSayisi))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ekleri detay butonu kontrolu")
    public ImzaladiklarimPage evrakEklerindeDetayButonuKontrol(String ek1, String ek2) {

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek1))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        tblOnIzlemeEkler
                .filterBy(Condition.text(ek2))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ilgi bilgileri detay butonu kontrolu")
    public ImzaladiklarimPage ilgiBilgilerindeDetayButonuKontrol(String ilgiSayisi1, String ilgiSayisi2) {

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi1))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        tblOnIzlemeIlgiBilgileri
                .filterBy(Condition.text(ilgiSayisi2))
                .get(0)
                .$("[id*='ilgiListesiDetayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ilişik bilgileri detay butonu kontrolu")
    public ImzaladiklarimPage ilisikBilgilerindeDetayButonuKontrol(String ilisikTuru1, String ilisikTuru2) {

        tblOnIzlemeIlisikBilgileri
                .filterBy(Condition.text(ilisikTuru1))
                .get(0)
                .$("[id*='ilisikListesiDetayButton']").shouldBe(visible);

        tblOnIzlemeIlisikBilgileri
                .filterBy(Condition.text(ilisikTuru2))
                .get(0)
                .$("[id*='ilisikListesiDetayButton']").shouldBe(visible);

        return this;
    }

    BelgenetElement txtKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection tblTakipListesi = $$("tbody[id='evrakTakibimeEkleDialogForm:takipListLov:LovSecilenTable_data'] > tr[role='row']");
    SelenideElement btnTakipListesiKapat = $("div[id='evrakTakibimeEkleDialogForm:takipDialog'] span[class*='ui-icon-closethick']");

    @Step("{konu} konulu evrak üzerinde Takip Listesi butonuna tıkla.")
    public ImzaladiklarimPage takipListesiAc(String konu) {
        tblImzaladiklarimEvraklar
                .filterBy(text(konu))
                .first()
                .$x(".//span[contains(@class,'ui-button-icon-left ui-icon document-addFollow')]/..")
                .click();
        return this;
    }

    @Step("Takip Listesinde {adiSoyadi} kullanıcısının ve {birim} birim bilgisinin olduğu görülür.")
    public ImzaladiklarimPage takipListesiKontrol(String adiSoyadi, String birim){
        tblTakipListesi.filterBy(text(adiSoyadi)).filterBy(text(birim)).first().shouldBe(visible);
        return this;
    }

    @Step("Kullacici listesinde \"{kullanici}\" kullanıcısını seç.")
    public ImzaladiklarimPage kullaniciListesiSec(String kullanici) {
        txtKullaniciListesi.selectLov(kullanici);
        return this;
    }

    @Step("Takip Listesi ekranında bulunan (X) \"Sayfayı Kapatma\" butonuna basılır. Takip listesi ekranın kapatıldığı görülür.")
    public ImzaladiklarimPage takipListesiKapat(){
        btnTakipListesiKapat.click();
        txtKullaniciListesi.shouldNotBe(visible);
        return this;
    }

    BelgenetElement txtTakipListesiKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    @Step("Takip listesinde {kullanicilar} kullanıcısını seç")
    public ImzaladiklarimPage takipListesiKullanicilarDoldur(String kullanicilar) {
        txtTakipListesiKullanicilar.type(kullanicilar).getTitleItems().filterBy(Condition.text(kullanicilar)).first().click();
        return this;
    }
}
