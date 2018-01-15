package pages.solMenuPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageComponents.belgenetElements.BelgenetElement;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static pages.pageComponents.belgenetElements.Belgenet.comboLov;

public class CevapladiklarimPage extends MainPage {

    ElementsCollection tblEvrak = $$("[id^='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    BelgenetElement txtKullanicilar = comboLov(By.id("evrakTakibimeEkleDialogForm:takipListLov:LovText"));
    SelenideElement btnTakipListesiKapat = $("[id^='evrakTakibimeEkleDialogForm:takipDialog'] span[class='ui-icon ui-icon-closethick']");
    ElementsCollection tableEvraklar = $$("tbody[id='mainInboxForm:inboxDataTable_data'] > tr");
    ElementsCollection tblEvrakGecmisi = $$("[id$='hareketGecmisiDataTable_data'] tr[role='row']");
    //Filtreler sekmesi
    private SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    private SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    private SelenideElement txtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    private SelenideElement txtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));

    @Step("Birim Havale Edilenler sayfası aç")
    public CevapladiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Cevapladiklarim);
        return this;
    }


    @Step("Kullancılar doldur")
    public CevapladiklarimPage kullanicilarDoldur(String kullanicilar) {
        txtKullanicilar.selectLov(kullanicilar);
        return this;
    }

    @Step("Tablodan rapor seç")
    public CevapladiklarimPage gizlilikRaporSec(String konu, String yer, String tarih, String no) {
        SelenideElement evrak = filter().findRowsWith(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih))
                .filterBy(Condition.text(no))
                .shouldHaveSize(1).first();
        evrak.$$("button[id^='mainInboxForm:inboxDataTable:']").get(0).click();
        return this;
    }



    @Step("Evrak Geçmişinde geldiği görülür")
    public CevapladiklarimPage evrakGecmisiEvrakKapandiIbaresiGorme(){
        boolean durum = tblEvrakGecmisi.filterBy(Condition.text("Evrak"))
                .filterBy(Condition.text("tarihli yazı ile cevap yazılarak kapatılmıştır."))
                .size() == 1;

        Assert.assertEquals(durum,true);
        return this;
    }
    
    @Step("Evrak seçilir")
    public CevapladiklarimPage tabloEvrakSec(String konu, String yer, String tarih){
        tblEvrak.filterBy(Condition.text(konu))
                .filterBy(Condition.text(yer))
                .filterBy(Condition.text(tarih)).get(0).click();
        return this;
    }

    @Step("Evrak geçmişi alanına tıklanır")
    public CevapladiklarimPage secilenEvrakEvrakGecmisi(){
        $$("[id$='evrakOnizlemeTab'] ul li").filterBy(Condition.text("Evrak Geçmişi")).get(0).$("a").click();
        return this;
    }

    public CevapladiklarimPage takipListeKapat() {
        btnTakipListesiKapat.click();
        return this;
    }

    @Step("Filtrelenir")
    public CevapladiklarimPage filtreSec(String filtre) {
        //selectCombobox(filtreSelectbox, filtre);
        cmbFiltre.selectOption(filtre);
        return this;
    }

    @Step("Sayfada ara doldur")
    public CevapladiklarimPage sayfadaAraDoldur(String sayfadaAra) {
        //sendKeys(sayfadaAraInput, sayfadaAra, false);
        txtSayfadaAra.sendKeys(sayfadaAra);
        return this;
    }

    @Step("Başlangıç tarih doldur")
    public CevapladiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        //sendKeys(baslangicTarihiInput, baslangicTarihi, false);
        txtBaslangicTarihi.sendKeys(baslangicTarihi);
        return this;
    }

    @Step("Bitiş tarih doldur")
    public CevapladiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        //sendKeys(bitisTarihiInput, bitisTarihi, false);
        txtBitisTarihi.sendKeys(bitisTarihi);
        return this;
    }

    @Step("Tablo Erak Kontorlü ")
    public CevapladiklarimPage tabloKonuyaGoreEvrakKontrolu(String konu) {
        tblEvrak
                .filterBy(Condition.text(konu))
                .shouldHaveSize(1)
                .first()
                .$("[id^='mainInboxForm:inboxDataTable'][id$='detayGosterButton']").click();
        return this;
    }

    @Step("Cevapladıklarım sayfasında evrakın listeye düşmediği kontrolu")
    public CevapladiklarimPage evrakGelmedigiGorme(String konu, String geldigiYer, String kayitTarihiSayi, String no) {

        boolean durum = tableEvraklar
                .filterBy(text(konu))
                .filterBy(text(geldigiYer))
                .filterBy(text(kayitTarihiSayi))
                .filterBy(text(no)).size() > 0;
        Assert.assertEquals(durum, false);

        return this;
    }

}
