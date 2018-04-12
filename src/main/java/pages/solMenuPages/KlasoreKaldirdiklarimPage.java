package pages.solMenuPages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.MainPage;
import pages.pageData.SolMenuData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class KlasoreKaldirdiklarimPage extends MainPage {

    //Filtreler sekmesi
    SelenideElement cmbFiltre = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt9553_input"));
    SelenideElement txtSayfadaAra = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt353"));
    SelenideElement txtBaslangicTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt378_input"));
    SelenideElement txtBitisTarihi = $(By.id("mainInboxForm:inboxDataTable:filtersAccordion:j_idt383_input"));
    SelenideElement btnRaporAl = $(By.id("mainInboxForm:inboxDataTable:j_idt682"));

    SelenideElement btnEvrakGoster = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:0:cmdbutton"));
    SelenideElement btnEvrakKopyala = $(By.id("mainPreviewForm:onizlemeRightTab:uiRepeat:1:cmdbutton"));
    ElementsCollection tblKlasoreKaldirdiklarim = $$("[id='mainInboxForm:inboxDataTable_data'] > tr[role='row']");
    SelenideElement tabEvrakGecmisi = $(By.xpath("//a[text()='Evrak Geçmişi']"));
    ElementsCollection tblEvrakGecmisi = $$("[id^='mainPreviewForm:j_idt'] [id$='hareketGecmisiDataTable_data'] > tr[role='row']");


    @Step("Klasöre kaldırdıklarım sayfası aç")
    public KlasoreKaldirdiklarimPage openPage() {
        solMenu(SolMenuData.IslemYaptiklarim.Klasorekaldirdiklarim);
        return this;
    }

    @Step("Filtrele")
    public KlasoreKaldirdiklarimPage filtreSec(String filtre) {
        //selectCombobox(filtreSelectbox, filtre);
        cmbFiltre.selectOption(filtre);
        return this;
    }

    @Step("Sayfada ara")
    public KlasoreKaldirdiklarimPage sayfadaAraDoldur(String sayfadaAra) {
        //sendKeys(sayfadaAraInput, sayfadaAra, false);
        txtSayfadaAra.sendKeys(sayfadaAra);
        return this;
    }

    @Step("Başlangıç tarih doldur")
    public KlasoreKaldirdiklarimPage baslangicTarihiDoldur(String baslangicTarihi) {
        //sendKeys(baslangicTarihiInput, baslangicTarihi, false);
        txtBaslangicTarihi.sendKeys(baslangicTarihi);
        return this;
    }

    @Step("Bitiş tarihi doldur")
    public KlasoreKaldirdiklarimPage bitisTarihiDoldur(String bitisTarihi) {
        //sendKeys(bitisTarihiInput, bitisTarihi, false);
        txtBitisTarihi.sendKeys(bitisTarihi);
        return this;
    }

    @Step("Rapor al")
    public KlasoreKaldirdiklarimPage raporAl() {
        //click(raporAlButton);
        btnRaporAl.click();
        return this;
    }

    @Step("Evrak goster")
    public KlasoreKaldirdiklarimPage evrakGoster() {
        //click(evrakGosterButton);
        btnEvrakGoster.click();
        return this;
    }

    @Step("Evrak kopyala")
    public KlasoreKaldirdiklarimPage evrakKopyala() {
        //click(evrakKopyalaButton);
        btnEvrakKopyala.click();
        return this;
    }

    @Step("Cevap yazılan evrağın listeye düştüğü görülür")
    public KlasoreKaldirdiklarimPage cevapYazilanEvrakListeyeDustuguGorme(String konu) {
        boolean durum = tblKlasoreKaldirdiklarim
                .filterBy(text(konu)).size() == 0;

        Assert.assertEquals(durum, false);
        return this;
    }

    @Step("Konuya göre tablo kontrol : {konu}")
    public KlasoreKaldirdiklarimPage konuyaGoreTabloKontol(String konu) {
        tblKlasoreKaldirdiklarim
                .filterBy(text(konu))
                .shouldHaveSize(1);
        return this;
    }

    @Step("Evrak listesinde {konu} konulu evrak olmali mi? {evrakOlmali}")
    public KlasoreKaldirdiklarimPage evrakKontrol(String konu, boolean evrakOlmali){

        if(evrakOlmali == true){

            tblKlasoreKaldirdiklarim
                    .filterBy(text(konu))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));

            tblKlasoreKaldirdiklarim
                    .filterBy(text(konu))
                    .first()
                    .shouldBe(Condition.visible);
            Allure.addAttachment("Evrakın listelendiği görülür.", "");
        } else {

            tblKlasoreKaldirdiklarim
                    .filterBy(text(konu))
                    .shouldHaveSize(0);

            tblKlasoreKaldirdiklarim
                    .filterBy(text(konu))
                    .first()
                    .shouldNotBe(Condition.visible);
            Allure.addAttachment("Evrakın listelenmediği görülür.", "");
        }

        return this;
    }

    @Step("Evrak Seç")
    public KlasoreKaldirdiklarimPage evrakSec(String konu, String gidecegiYer, String gonderen, String evrakNo) {
        tblKlasoreKaldirdiklarim
                .filterBy(text(konu))
                .filterBy(text(gidecegiYer))
                .filterBy(text(gonderen))
                .filterBy(text(evrakNo))
                .first()
                .click();
        return this;
    }

    @Step("Evrak Geçmişi tabını aç")
    public KlasoreKaldirdiklarimPage btnEvrakGecmisiTab() {
        tabEvrakGecmisi.shouldBe(visible);
        tabEvrakGecmisi.click();
        return this;
    }

    @Step("Evrakın klasöre kaldırılmış olduğu ve görüntülenmek üzere listelendiği  görülür.")
    public KlasoreKaldirdiklarimPage evrakGoruntulenmekUzereKlasoreKaldirilmasi(String islemSureci){

            tblEvrakGecmisi
                    .filterBy(text(islemSureci))
                    .first()
                    .shouldBe(visible);

            Allure.addAttachment("Evrak Klasöre kaldırıldı görüntülendi.", "");

            return this;
    }
}
