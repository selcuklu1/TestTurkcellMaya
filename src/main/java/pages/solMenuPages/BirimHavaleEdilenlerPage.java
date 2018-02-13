package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/****************************************************
 * Tarih: 2017-12-22
 * Proje: Türksat Functional Test Automation
 * Class: "BirimHavaleEdilenlerPage" konulu senaryoları içerir
 * Yazan: Emre Sencan
 ****************************************************/
public class BirimHavaleEdilenlerPage extends MainPage {


    SelenideElement f = $(By.xpath("//div[@id='mainInboxForm:inboxDataTable:filtersAccordion']//a[text()='Filtreler']/parent::h3"));
    SelenideElement cmbTopluSecim = $(By.id("mainInboxForm:inboxDataTable:j_idt666_button"));
    SelenideElement cmbFiltre = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement txtSayfadaAra = $(By.xpath("//select[starts-with(@id,'mainInboxForm:inboxDataTable:filtersAccordion:j_idt']"));
    SelenideElement dateTxtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt389_input"));
    SelenideElement dateTxtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt394_input"));
    SelenideElement btnIcerikGöster = $(By.id("mainInboxForm:inboxDataTable:0:detayGosterButton"));
    SelenideElement btnTamEkranGöster = $(By.id("mainInboxForm:inboxDataTable:0:tamEkranModuButton"));
    SelenideElement tblRapor = $(By.id("mainInboxForm:inboxDataTable:0:evrakTable"));
    ElementsCollection tblKaydedilenGelenEvraklar = $$("[id='mainInboxForm:inboxDataTable_data'] tr[data-ri]");
    ElementsCollection tblEvraklar = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");

    SelenideElement havaleGeriAl = $(By.id("inboxItemInfoForm:dialogTabMenuRight:uiRepeat:4:cmdbutton"));
    SelenideElement onizlemeHavaleGeriAl = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:4:cmdbutton"));

    SelenideElement notAlanıDoldur = $(By.id("inboxItemInfoForm:evrakGeriAlInputTextareaId"));
    SelenideElement onizlemeNotAlanıDoldur = $(By.id("mainPreviewForm:evrakGeriAlInputTextareaId"));
    SelenideElement btnGeriAl = $("[class='ui-button-icon-left ui-icon evrakGeriAl']");
    SelenideElement onizlemeGeriAl = $("[id^='mainPreviewForm:j_idt']");

    SelenideElement btnGeriAlGeriAl = $("[id='mainPreviewForm:evrakOnizlemeTab'] button");
    SelenideElement tctGeriAlNot = $("[id$='evrakGeriAlInputTextareaId']");
    SelenideElement evrakOnizlemeKontrol = $(By.id("mainPreviewForm:eastLayout"));

    //Birim
    ElementsCollection birimEvrakEkleri = $$("a[href^='#mainPreviewForm']");
    ElementsCollection birimEvrakEkleriKontrol = $$("div[id$='ekListesiOnizlemeDataTable'] tr[data-ri]");
    ElementsCollection ilgiBilgileriEkleriKontrol = $$("div[id$='ilgiListesiDataTable'] tr[data-ri]");

    @Step("Birim Havale Edilenler sayfası aç")
    public BirimHavaleEdilenlerPage openPage() {
        solMenu(SolMenuData.BirimEvraklari.BirimHavaleEdilenler);
        return this;
    }

    @Step("Filtrele alanını aç")
    public BirimHavaleEdilenlerPage filtreleAc() {
        f.click();
        return this;
    }

    @Step("Filtere seç")
    public BirimHavaleEdilenlerPage filtreleSec(String value) {
        cmbFiltre.selectOption(value);
        return this;
    }

    @Step("Sayfada Ara alanı doldur")
    public BirimHavaleEdilenlerPage sayfadaAraDoldur(String value) {
        txtSayfadaAra.sendKeys(value);
        return this;
    }

    @Step("Başlangıç Tarihi doldur")
    public BirimHavaleEdilenlerPage baslangiçTarihiDoldur(String tarih) {
        dateTxtBaslangicTarihi.sendKeys(tarih);
        return this;
    }

    @Step("Bitiş Tarihi doldur")
    public BirimHavaleEdilenlerPage bitisTarihiDoldur(String tarih) {
        dateTxtBitisTarihi.sendKeys(tarih);
        dateTxtBitisTarihi.pressEnter();
        return this;
    }

    @Step("Tablodan rapor seç")
    public BirimHavaleEdilenlerPage raporSec() {
        tblRapor.click();
        return this;
    }

    @Step("Tabloda evrak no kontrolü : \"{evrakNo}\" ")
    public BirimHavaleEdilenlerPage evrakNoIleTabloKontrolu(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Tabloda evrak kontrolü : \"{evrakNo}\"  \"{birim}\" \"{evrakTarihi}\" \"{No}\" ")
    public BirimHavaleEdilenlerPage evrakAlanKontrolleri(String evrakNo,String birim,String evrakTarihi,String No) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .filterBy(Condition.text(birim))
                .shouldHaveSize(1);
        return this;

        //        Evrak tarihi  : evrakTarihi
        //        no alanlarının : evrakSayiSagDoldur()
    }

    @Step("Tabloda evrak no ile evrak seçme. \"{evrakNo}\" ")
    public BirimHavaleEdilenlerPage evrakNoIleTablodanEvrakSecme(String evrakNo) {
        tblKaydedilenGelenEvraklar
                .filterBy(Condition.text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak no ile teslim al")
    public BirimHavaleEdilenlerPage evrakSecIcerikGoster(String konu, boolean secim) {
        tblEvraklar.filterBy(text(konu)).get(0).$$("[id$='detayGosterButton']").first().click();
        return this;
    }

    @Step("Havale edilen evrak geri alma")
    public BirimHavaleEdilenlerPage havaleGeriAl() {
        if (havaleGeriAl.isDisplayed())
            havaleGeriAl.click();
        return this;
    }

    @Step("Havale edilen evrak geri alma")
    public BirimHavaleEdilenlerPage onizlemeHavaleGeriAl() {
        if (onizlemeHavaleGeriAl.isDisplayed())
            onizlemeHavaleGeriAl.click();
        return this;
    }

    @Step("Not Alanını Doldur")
    public BirimHavaleEdilenlerPage notAlanınıDoldur(String not) {
        notAlanıDoldur.setValue(not);
        return this;
    }

    @Step("Not Alanını Doldur")
    public BirimHavaleEdilenlerPage onizlemeNotAlanınıDoldur(String not) {
        onizlemeNotAlanıDoldur.setValue(not);
        return this;
    }

    @Step("Geri Al Butonu tıkla")
    public BirimHavaleEdilenlerPage geriAl() {
        btnGeriAl.click();
        return this;
    }

    @Step("Geri Al Butonu tıkla")
    public BirimHavaleEdilenlerPage onizlemeGeriAl() {
        onizlemeGeriAl.click();
        return this;
    }

    @Step("Geri al tıklanır")
    public BirimHavaleEdilenlerPage geriAlGeriAl(){
        btnGeriAlGeriAl.click();
        return this;
    }

    @Step("Not alanını doldur: {not}")
    public BirimHavaleEdilenlerPage geriAlNotAlaniniDoldur(String not){
        tctGeriAlNot.setValue(not);
        return this;
    }

    @Step("Evrak önizleme evrak kontrolü : \"{pdfText}\" ")
    public BirimHavaleEdilenlerPage evrakOnizlemeEklenenUstYaziKontrolu(String pdfText) {
        String text = "";
        switchTo().frame(1);
        sleep(1000);
        text = $(By.xpath("//div[@id='viewer']/div[@class='page']/div[@class='textLayer']/div[1]")).getText();
        text.equals(pdfText);
        switchTo().parentFrame();
        return this;
    }

    @Step("Evrak no ile evrak seçilir, evrak bulunamaz: \"{evrakNo}\" ")
    public BirimHavaleEdilenlerPage evrakBulunamadı(String evrakNo) {
        if (tblEvraklar.filterBy(Condition.text(evrakNo)).isEmpty()) {
            Allure.addAttachment(evrakNo, " Nolu evrak bulunamadı.");
        } else {
            Allure.addAttachment(evrakNo, " Nolu evrak bulundu.");
        }

        return this;
    }

    @Step("Evrak Onizleme Kontrolu")
    public BirimHavaleEdilenlerPage evrakOnizlemeKontrol() {
        if(evrakOnizlemeKontrol.isDisplayed())
            Allure.addAttachment("Evrak Önizleme Ekranı", "açılmıştır");
        return this;
    }

    @Step("Birim Evrak Ekleri Tıklama")
    public BirimHavaleEdilenlerPage birimEvrakEkleri(String select) {
        birimEvrakEkleri.filterBy(Condition.text(select)).get(0).click();
        return this;
    }

    @Step("Birim Evrak Ekleri Kontrol")
    public BirimHavaleEdilenlerPage birimEvrakEkleriKontrol(String ek1,String ek2,String ek3) {
        birimEvrakEkleriKontrol.filterBy(Condition.text(ek1)).shouldHaveSize(1);
        birimEvrakEkleriKontrol.filterBy(Condition.text(ek2)).shouldHaveSize(1);
        birimEvrakEkleriKontrol.filterBy(Condition.text(ek3)).shouldHaveSize(1);
        return this;
    }

    @Step("Birim Ilgi Bilgileri Kontrol")
    public BirimHavaleEdilenlerPage birimIlgiBilgileriEvrakEkleriKontrol(String ek1,String ek2) {
        boolean durum1 = ilgiBilgileriEkleriKontrol.filterBy(Condition.text(ek1)).size() > 0;
        boolean durum2 = ilgiBilgileriEkleriKontrol.filterBy(Condition.text(ek2)).size() > 0;
        return this;
    }


}
