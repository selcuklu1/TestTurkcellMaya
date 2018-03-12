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

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;
import static pages.pageData.SolMenuData.IslemYaptiklarim;

public class ParafladiklarimPage extends MainPage {

    ElementsCollection tblParafladiklarimEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[data-ri]");
    SelenideElement btnEvrakSecEvrakKopyala = $("[class='ui-button-icon-left ui-icon evrakKopyala']");
    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt3011_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtBaslangicTarihi = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] tr:nth-child(2) td:nth-child(4) tr input");
    SelenideElement dateTxtBitisTarihi = $("[id='mainInboxForm:inboxDataTable:filtersAccordion:filtersGrid'] tr:nth-child(3) td:nth-child(2) tr input");
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt657_button"));
    SelenideElement btnGidecegiYerTree = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:gidecegiYerFilterOpenDialogButton"));
    SelenideElement btnKurumDisindanGelenBelge = $(By.id(""));
    SelenideElement btnIcerikGöster = $(By.id(""));
    SelenideElement btnTamEkranGöster = $(By.id(""));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnHavaleYap = $(By.id(""));
    SelenideElement btnIconPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    SelenideElement txtKisi = $(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnPaylas = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:5:cmdbutton"));
    SelenideElement btnIcerik = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement txtEvrakDetayiEvrakNo = $("[id^='inboxItemInfoForm:evrakBilgileriList'][id$='evrakNoPanelGrid'] td:nth-child(3) div");
    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement btnPaylasPaylas = $(By.id("mainPreviewForm:paylasButtonId"));
    BelgenetElement txtPaylasKisi = comboLov(By.id("mainPreviewForm:evrakPaylasKisiLov:LovText"));
    SelenideElement txtPaylasAciklama = $(By.id("mainPreviewForm:evrakPaylasAciklama"));
    SelenideElement btnEvrakKopyalaUyariEvet = $(By.id("evrakCopyConfirmForm:copyEvrakEvetButton"));

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

    @Step("Parafladıklarım sayfası aç")
    public ParafladiklarimPage openPage() {
        solMenu(IslemYaptiklarim.Parafladiklarim);
        String pageTitle = IslemYaptiklarim.Parafladiklarim.getMenuText();
        $("#mainInboxForm\\:inboxDataTable .ui-inbox-header-title")
                .shouldHave(text(pageTitle));
        System.out.println("Page: " + pageTitle);
        return this;
    }

    @Step("Evrak Seçilir")
    public ParafladiklarimPage evrakNoGoreEvrakSec(String konuKodu){
        tblParafladiklarimEvraklar.filterBy(Condition.text(konuKodu)).first().click();
        return this;
    }

    @Step("Evrak Kopyala tıklanır")
    public ParafladiklarimPage evrakSecEvrakKopyala(){
        btnEvrakSecEvrakKopyala.click();
        return this;
    }

    @Step("Evrak kopyalama uyarısının geldiği görülür.")
    public ParafladiklarimPage evrakKopyalaUyariGeldigiGorme(){
        boolean durum = $("[id='evrakCopyConfirmForm']").shouldBe(visible).exists()==true;
        Assert.assertEquals(durum,true);
        return this;
    }

    @Step("Evet tıklanır")
    public ParafladiklarimPage evrakKopyalaUyariEvet(){
        btnEvrakKopyalaUyariEvet.click();
        return this;
    }

    @Step("Açıklama doldur")
    public ParafladiklarimPage paylasAciklamaDoldur(String aciklama) {
        txtPaylasAciklama.setValue(aciklama);
        return this;
    }

    @Step("Kişi doldur")
    public ParafladiklarimPage paylasKisiDoldur(String kisi) {
        txtPaylasKisi.selectLov(kisi);
        return this;
    }

    @Step("Paylaş")
    public ParafladiklarimPage paylasPaylas() {
        btnPaylasPaylas.click();
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafladiklarimPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        return this;
    }

    @Step("Filtrele alanını aç")
    public ParafladiklarimPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public ParafladiklarimPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public ParafladiklarimPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public ParafladiklarimPage baslangicTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi alanına \"{tarih}\" doldur")
    public ParafladiklarimPage bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Tablodan rapor seç")
    public ParafladiklarimPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Parafladıklarım listesinden evrak seç")
    public ParafladiklarimPage konuyaGoreEvrakDetayiTikla(String konu) {

        tblParafladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='detayGosterButton']").click();

        return this;
    }

    @Step("Parafladıklarım listesinden evrak önizlemede aç")
    public ParafladiklarimPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblParafladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Parafladıklarım listesinde evrak kontrolu")
    public ParafladiklarimPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblParafladiklarimEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Parafladıklarım listesinde evrak kontrolü:  \"{konu}\" ")
    public ParafladiklarimPage konuyaGoreEvrakKontroluAllPages(String konu) {
        searchTable().searchInAllPages(true).findRows(text(konu)).getFoundRow().shouldBe(exist);
        return this;
    }

    @Step("Paylaş ikonuna  bas")
    public ParafladiklarimPage havaleYap() {
        btnIconPaylas.click();
        return this;
    }

    @Step("Kişi Seç")
    public ParafladiklarimPage kişiSec(String kisi) {
        txtKisi.sendKeys(kisi);
        return this;
    }

    @Step("Açıklama doldur")
    public ParafladiklarimPage aciklamaDoldur(String aciklama) {
        txtAciklama.sendKeys(aciklama);
        return this;
    }

    @Step("Paylaş butonuna bas")
    public ParafladiklarimPage paylas() {
        btnPaylas.click();
        return this;
    }

    @Step("İçerik ilk kayıt")
    public ParafladiklarimPage icerikIlkKayıt() {
        btnIcerik.click();
        return this;
    }

    @Step("Konuya göre İçerik tıklanır. {konu}")
    public ParafladiklarimPage icerikTikla(String konu) {
        tblParafladiklarimEvraklar.filterBy(text(konu)).first()
                .$("button[id$='detayGosterButton']").click();
        return this;
    }


    @Step("Evrak No al")
    public String evrakDetayiEvrakNoAl() {
        String evrakNo = txtEvrakDetayiEvrakNo.text();
        return evrakNo;
    }

    @Step("Evrak Ek/İlgi/İlişikler tablarının geldiği kontrolu")
    public ParafladiklarimPage tabKontrolleri() {

        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);
        Assert.assertEquals(tabIlisikBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Evrak Ek/İlgi tablarının geldiği kontrolu")
    public ParafladiklarimPage tabKontrolleriWithoutIlisik() {

        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Parafladıklarım/Evrak Ekleri tabını aç")
    public ParafladiklarimPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Parafladıklarım/İlgi Bilgieri tabını aç")
    public ParafladiklarimPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }

    @Step("Parafladıklarım/İlişik Bilgieri tabını aç")
    public ParafladiklarimPage tabIlisikBilgileriAc() {
        tabIlisikBilgileri.click();
        return this;
    }

    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public ParafladiklarimPage evrakEkleriAccordionKontrol() {

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
    public ParafladiklarimPage ilgiBilgileriAccordionKontrol() {

        Assert.assertEquals(accordionIlgiBilgileriOpen1.isDisplayed(), true);
        accordionIlgiBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlgiBilgileri2.click();
        Assert.assertEquals(accordionIlgiBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak önizleme/İlişik Bilgileri Accordion kontrolu")
    public ParafladiklarimPage ilisikBilgieriAccordionKontrol() {

        Assert.assertEquals(accordionIlisikBilgileriOpen1.isDisplayed(), true);
        accordionIlisikBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlisikBilgileri2.click();
        Assert.assertEquals(accordionIlisikBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizlemede ekleri detay butonu kontrolu")
    public ParafladiklarimPage evrakEklerindeDetayButonuKontrol(String ek1, String ek2) {

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
    public ParafladiklarimPage ilgiBilgilerindeDetayButonuKontrol(String ilgiSayisi1, String ilgiSayisi2) {

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
    public ParafladiklarimPage ilisikBilgilerindeDetayButonuKontrol(String ilisikTuru1, String ilisikTuru) {

        tblOnIzlemeIlisikBilgileri
                .filterBy(Condition.text(ilisikTuru1))
                .get(0)
                .$("[id*='ilisikListesiDetayButton']").shouldBe(visible);

        tblOnIzlemeIlisikBilgileri
                .filterBy(Condition.text(ilisikTuru))
                .get(0)
                .$("[id*='ilisikListesiDetayButton']").shouldBe(visible);

        return this;
    }
}
