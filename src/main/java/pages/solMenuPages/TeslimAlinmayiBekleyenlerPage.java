package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class TeslimAlinmayiBekleyenlerPage extends MainPage {

    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt10493_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement dateTxtTarih = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:tarihSecCalendar_input"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt651_button"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    SelenideElement btnTeslimAlveHavaleYap = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:6:cmdbutton"));
    SelenideElement tblIlkEvrak = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection btnTeslimAlVeKapat = $$("[id='mainPreviewForm:onizlemeRightTab:onizlemeRightTab'] table td[class='buttonMenuContainerDefault']");
    BelgenetElement txtKaldirilacakKlasorler = comboLov(By.id("mainPreviewForm:klasorLov_id:LovText"));
    BelgenetElement txtKonuKodu = comboLov(By.id("mainPreviewForm:konuKoduLov:LovText"));
    SelenideElement btnTeslimAlVeKapatTeslimAlVeKapat = $("[id='mainPreviewForm:evrakOnizlemeTab'] div[class='form-buttons kapatButtonDirekt'] button");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtHavaleYapKisi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKullaniciLov:LovText"));
    BelgenetElement txtHavaleYapKullaniciListesi = comboLov(By.id("mainPreviewForm:dagitimBilgileriKisiListesiLov:LovText"));
    ElementsCollection btnTeslimAl = $$("[id^='mainInboxForm:inboxDataTable:j_idt'] > [class$='document-delivery']");

    //Önizleme
    SelenideElement formEvrakOnizleme = $(By.id("mainPreviewForm:evrakOnizlemeTab"));
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

    ElementsCollection tblOnIzlemeEkler = $$("[id*='ekListesiOnizlemeDataTable'] > tr[role='row']");
    ElementsCollection tblOnIzlemeIlgiBilgileri = $$("[id*='ilgiListesiDataTable_data'] > tr[role='row']");

    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    SelenideElement evrakTeslimAl = $("[id='inboxItemInfoForm:dialogTabMenuRight:uiRepeat:5:cmdbutton']");

    BelgenetElement cmbHavaleIslemleriBirim = comboLov(By.id("mainPreviewForm:dagitimBilgileriBirimLov:LovText"));
    SelenideElement teslimAlGönder = $("[id='mainPreviewForm:btnTeslimAlGonder']");
    ElementsCollection birimDegistirme = $$("a[id^='leftMenuForm:edysMenuItem'] span[class='ui-menuitem-text']");

    SelenideElement btnIadeEt = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));
    SelenideElement btnIadeEtIadeEt = $(By.id("mainPreviewForm:iadeEtButton_id"));

    public TeslimAlinmayiBekleyenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.TeslimAlinmayiBekleyenler);
//        ustMenu("Teslim Alınmayı Bekleyenler");
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinmayiBekleyenlerPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(text(konu), text(yer), text(tarih), text(no))
//                .filterBy(text(yer))
//                .filterBy(text(tarih))
//                .filterBy(text(no))
                .shouldHaveSize(1).first();
        evrak.click();
        //evrak.click();
        return this;
    }

    @Step("Kladırılıcak klasörler doldur")
    public TeslimAlinmayiBekleyenlerPage kaldirilacakKlasorlerDoldur(String kaldirilicakKlasor) {
        txtKaldirilacakKlasorler.selectLov(kaldirilicakKlasor);
        return this;
    }

    @Step("Konu kodu doldur")
    public TeslimAlinmayiBekleyenlerPage konuKoduDoldur(String konuKodu) {
        txtKonuKodu.selectLov(konuKodu);
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlveKapatTeslimAlVeKapat() {
        btnTeslimAlVeKapatTeslimAlVeKapat.click();
        return this;
    }

    @Step("Teslim al ve kapat")
    public TeslimAlinmayiBekleyenlerPage teslimAlVeKapat() {
        btnTeslimAlVeKapat.filterBy(Condition.text("Teslim Al ve Kapat")).get(0).$("button").click();
        return this;
    }

   @Step("Kapatma tipi, Konu kodu, Kaldırılacak klasörler, Not ve Onay akışı alanlarının geldiği görülür.")
   public TeslimAlinmayiBekleyenlerPage teslimAlVeKapatAlanGeldigiGorme(){
        boolean durum = $$("[id='mainPreviewForm:evrakKapatPanelGrid']").size()==1;
        Assert.assertEquals(durum,true);
        takeScreenshot();
       return this;
   }

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec() {
        tblIlkEvrak.click();
        return this;
    }

    @Step("Evrak geldiği görünür")
    public TeslimAlinmayiBekleyenlerPage evrakGeldigiGorunur(String konuKodu, String tarih,String geldigiYer) {
        boolean durum = tblEvraklar
               .filterBy(Condition.text(konuKodu))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(geldigiYer)).size()==1;
        Assert.assertEquals(durum,true);
        takeScreenshot();
        return this;
    }

    @Step("Evrak no ile evrak seçilir : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakNoIleEvrakSec(String evrakNo) {
        tblEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Teslim Alinmayi Bekleyenler  sayfasında evrakın listeye düşmediği kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakNoGelmedigiGorme(String konu) {
        boolean durum = tableEvraklar
                .filterBy(text(konu)).size() > 0;
        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Evrak önizleme evrak kontrolü : \"{pdfText}\" ")
    public TeslimAlinmayiBekleyenlerPage evrakOnizlemeEklenenUstYaziKontrolu(String pdfText) {
        String text = "";
        switchTo().frame(1);
        sleep(1000);
        text = $(By.xpath("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[1]")).getText();
        text.equals(pdfText);
        switchTo().parentFrame();
        return this;
    }

    @Step("Evrak seçilir")
    public TeslimAlinmayiBekleyenlerPage evrakSec(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).click();
        return this;
    }

    @Step("Evrak içerik göster")
    public TeslimAlinmayiBekleyenlerPage evrakSecIcerikGoster(String konu, String yer, String tarih, String no) {
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Evrak teslim al")
    public TeslimAlinmayiBekleyenlerPage evrakSecTeslimAl(String konu, String yer, String tarih, String no, boolean secim){
        tblEvraklar.filterBy(text(konu))
                .filterBy(text(yer))
                .filterBy(text(tarih))
                .filterBy(text(no)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak no ile teslim al")
    public TeslimAlinmayiBekleyenlerPage evrakSecNoTeslimAl(String konu,boolean secim){
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='teslimAlButton']").first().click();

        if (secim == true) {
            $(By.id("teslimAlEvetButton")).click();
        } else {
            $(By.id("teslimAlHayirButton")).click();
        }

        return this;
    }

    @Step("Evrak no ile teslim al")
    public TeslimAlinmayiBekleyenlerPage evrakSecIcerikGoster(String konu,boolean secim){
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("İçerikten Evrak teslim al")
    public TeslimAlinmayiBekleyenlerPage içeriktenEvrakTeslimAl(){
        evrakTeslimAl.click();
        return this;
    }

    @Step("İçerikten Evrak teslim Evet tıklama")
    public TeslimAlinmayiBekleyenlerPage içeriktenEvrakEvet(){
        $(By.id("teslimAlEvetButton")).click();
        return this;
    }


    @Step("Evrak Sec Checkbox ile")
    public TeslimAlinmayiBekleyenlerPage evrakSecCheckBox(String konu1,String konu2,boolean secim){
        tblEvraklar.filterBy(text(konu1)).get(0).$$("div[class^='ui-chkbox-box']").first().click();
        tblEvraklar.filterBy(text(konu2)).get(0).$$("div[class^='ui-chkbox-box']").first().click();

        btnTeslimAl.get(0).click();

        return this;
    }

    @Step("Filtrele alanını aç")
    public TeslimAlinmayiBekleyenlerPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public TeslimAlinmayiBekleyenlerPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public TeslimAlinmayiBekleyenlerPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Tarihi doldur")
    public TeslimAlinmayiBekleyenlerPage tarihiDoldur(String tarih, Keys... keys) {
        dateTxtTarih.sendKeys(tarih);
        for (Keys k : keys) {
            dateTxtTarih.sendKeys(keys);
        }
        return this;
    }

    @Step("Tablodan rapor seç")
    public TeslimAlinmayiBekleyenlerPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Telim Al ve Havale Yap butonana bas")
    public TeslimAlinmayiBekleyenlerPage havaleYap() {
        btnTeslimAlveHavaleYap.click();
        return this;
    }

    @Step("Kisi doldur")
    public TeslimAlinmayiBekleyenlerPage havaleYapKisiDoldur(String kisi) {
        txtHavaleYapKisi.selectLov(kisi);
        return this;
    }

    @Step("Kişi alanında \"{kisi}\" seçmeye dene")
    public TeslimAlinmayiBekleyenlerPage havaleYapKisiKisiSecmeyeDene(String kisi) {
        txtHavaleYapKisi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Kullanıcı listesi doldur")
    public TeslimAlinmayiBekleyenlerPage havaleYapKullaniciListesiDoldur(String kullaniciListesi) {
        //txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        txtHavaleYapKullaniciListesi.selectLov(kullaniciListesi);
        return this;
    }

    @Step("Kullanıcı listesinde \"{kisi}\" seçmeye dene")
    public TeslimAlinmayiBekleyenlerPage havaleYapKullaniciListesiSecmeyeDene(String kisi) {
        txtHavaleYapKullaniciListesi.type(kisi).getTitleItems().filterBy(text(kisi)).first().click();
        return this;
    }

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public TeslimAlinmayiBekleyenlerPage tabloKontrolu(String evrakNo) {
        $$("tbody[id$='mainInboxForm:inboxDataTable_data'] tr[data-ri]")
                .filterBy(text(evrakNo))
                .shouldHaveSize(1);
        //log başarılı
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler listesinde evrak kontrolü:  \"{konu}\" ")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakKontroluAllPages(String konu){
        searchTable().searchInAllPages(true).findRows(text(konu)).getFoundRow().shouldBe(exist);
        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evraklar listesinde evrak kontrolu")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakKontrol(String konu) {

        boolean durum = tblEvraklar
                .filterBy(Condition.text(konu))
                .size() > 0;

        Assert.assertEquals(durum, true);

        return this;
    }

    @Step("Teslim Alınmayı Bekleyenler Evraklar listesinden evrak önizlemede aç")
    public TeslimAlinmayiBekleyenlerPage konuyaGoreEvrakOnizlemedeAc(String konu) {

        tblEvraklar
                .filterBy(Condition.text(konu))
                .get(0)
                .$("[id$='evrakTable']").click();

        return this;
    }

    @Step("Evrak Ek/İlgi tablarının geldiği kontrolu")
    public TeslimAlinmayiBekleyenlerPage tabKontrolleri() {

        formEvrakOnizleme.shouldBe(exist);
        Assert.assertEquals(tabEvrakEkleri.isDisplayed(), true);
        Assert.assertEquals(tabIlgiBilgileri.isDisplayed(), true);

        return this;
    }

    @Step("Postalanacak Evraklar/Evrak Ekleri tabını aç")
    public TeslimAlinmayiBekleyenlerPage tabEvrakEkleriAc() {
        tabEvrakEkleri.click();
        return this;
    }

    @Step("Postalanacak Evraklar/İlgi Bilgieri tabını aç")
    public TeslimAlinmayiBekleyenlerPage tabIlgiBilgileriAc() {
        tabIlgiBilgileri.click();
        return this;
    }


    @Step("Evrak önizleme/Evrak Ekleri Accordion kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakEkleriAccordionKontrol() {

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
    public TeslimAlinmayiBekleyenlerPage ilgiBilgieriAccordionKontrol() {

        accordionIlgiBilgileriOpen1.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen1.isDisplayed(), true);
        accordionIlgiBilgileri1.click();
        Selenide.sleep(1000);
        accordionIlgiBilgileri2.click();
        accordionIlgiBilgileriOpen2.shouldBe(visible);
        Assert.assertEquals(accordionIlgiBilgileriOpen2.isDisplayed(), true);
        return this;
    }

    @Step("Evrak Önizlemede detay butonu kontrolu")
    public TeslimAlinmayiBekleyenlerPage detayButonKontrol(String ekSayisi) {

      tblOnIzlemeEkler
                .filterBy(Condition.text(ekSayisi))
                .get(0)
                .$("[id*='detayButton']").shouldBe(visible);

        return this;
    }

    @Step("Evrak Önizlemede ekleri detay butonu kontrolu")
    public TeslimAlinmayiBekleyenlerPage evrakEklerindeDetayButonuKontrol(String ek1, String ek2) {

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
    public TeslimAlinmayiBekleyenlerPage ilgiBilgilerindeDetayButonuKontrol(String ilgiSayisi1, String ilgiSayisi2) {

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

    @Step("Dağıtım Bilgileri Birim alanında \"{birim}\" seçilir")
    public TeslimAlinmayiBekleyenlerPage dagitimBilgileriBirimDoldurWithDetails(String birim, String details) {
        cmbHavaleIslemleriBirim.type(birim).getDetailItems()
                .filterBy(Condition.exactText(details)).first().click();
        cmbHavaleIslemleriBirim.closeTreePanel();
        return this;
    }

    @Step("Birim Degisikligi Sonrası Teslim Al Gönder butonuna tıklama")
    public TeslimAlinmayiBekleyenlerPage teslimAlGonder() {
        teslimAlGönder.click();
        return this;
    }

    @Step("")
    public TeslimAlinmayiBekleyenlerPage birimDegistirme(String digerDetails) {
        birimDegistirme.filterBy(Condition.text(digerDetails)).get(0).click();
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi")
    public TeslimAlinmayiBekleyenlerPage btnIadeEt() {
        btnIadeEt.click();
        return this;
    }

    @Step("Teslim Alınan Evrakın Iade Edilmesi ve Iade Et Tıklanması")
    public TeslimAlinmayiBekleyenlerPage btnIadeEtIadeEt() {
        btnIadeEtIadeEt.click();
        return this;
    }


}
